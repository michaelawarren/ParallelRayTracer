package com.personal.parallelraytracer.drawing.cameras;

import com.personal.parallelraytracer.ParallelRayTracer.Size;
import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Vector;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class PinHoleMaster extends Camera
{
   private final int numThreads;
   private ExecutorService threadPool;
   private final List<String> comptuers;
   final protected List<Connection> connections;
   private int numConnections;

   /**
    *
    * @param eye
    * @param lookat
    * @param up
    * @param exposureTime
    * @param computers host names of each computer
    * @param fileName
    * @param numThreads
    */
   public PinHoleMaster(Point eye, Point lookat, Vector up, double exposureTime,
       String fileName, int numThreads, List<String> computers)
   {
      super(eye, lookat, up, exposureTime);
      this.numThreads = numThreads;
      this.fileName = fileName;
      this.comptuers = computers;
      this.connections = new ArrayList<>();
   }

   @Override
   public void renderScene(World world)
   {
      try
      {
         final int height = world.vp.getHeight();
         final int width = world.vp.getWidth();

         threadPool = Executors.newFixedThreadPool(comptuers.size());
         List<Future> futures = new ArrayList<>();

         openWindow(width, height);
         renderImage(height, futures);

         threadPool.shutdown();
         for (Connection connection : connections)
         {
            connection.close();
         }
         connections.clear();
         writeImageToFile(fileName);
      }
      catch (IOException ex)
      {
         ex.printStackTrace();
      }
   }

   public void renderImage(final int height, List<Future> futures)
   {
      for (Connection connection : connections)
      {
         futures.add(threadPool.submit(new ConnectionRunnable(connection)
         {
            @Override
            public void run()
            {
               String line = "";
               int count = 0;
               try
               {
                  connection.sendMessage("start\n");
                  while ((line = connection.readLine()) != null)
                  {
                     if ((line.equals("finished") || line.equals("finished\n")))
                     {
                        break;
                     }
                     saveToImage(new JSONArray(line));
                     count++;
                  }
                  connection.sendMessage("finished!");
               }
               catch (JSONException | IllegalStateException ex)
               {

                  // display last line received
                  System.out.println("count: " + count + "\n" + line);
                  ex.printStackTrace();
               }
               catch (IOException ex)
               {
                     ex.printStackTrace();
               }
            }
         }));
      }

      for (Future future : futures)
      {
         try
         {
            future.get();
         }
         catch (InterruptedException | ExecutionException ex)
         {
            ex.printStackTrace();
         }
      }
      
   }

   public List<Connection> initializeConnections(Size size) throws IOException, JSONException
   {
      final int multiplier = (int) Math.ceil((double) size.height / comptuers
          .size());
      for (String hostName : comptuers)
      {
         final Connection connection = new Connection(hostName);
         connection.sendMessage(
             new JSONStringer()
             .object()
             .key("status").value(200)
             .key("width").value(size.width)
             .key("height").value(size.height)
             .key("numThreads").value(numThreads)
             .key("rs")
             .value( // 0 - 124, 125-249, 250-374, 375-499
                 (connections.isEmpty()) ? 0 : connections.size() * multiplier)
             .key("re")
             .value(((connections.size() + 1) * multiplier) - 1) // 1,2,3,4
             .key("fileName").value(fileName + ".png")
             .endObject()
             .toString() + "\n"
         );

         connections.add(connection);
      }
      return connections;
   }

   public void saveToImage(final JSONArray jsonArray) throws IllegalStateException, JSONException
   {
      for (int i = 0; i < jsonArray.length(); i++)
      {
         try
         {
            final JSONObject jsonObject = jsonArray.getJSONObject(i);
            displayPixel(
                jsonObject.getInt("r"),
                jsonObject.getInt("c"),
                jsonObject.getInt("color"));
         }
         catch (JSONException ex)
         {
            System.out.println(jsonArray.toString(3) + "\n\n\n\n\n");
            System.out.println("length: " + jsonArray.length() + " i: " + i);
            throw new IllegalStateException(jsonArray.get(i).toString(), ex);
         }
      }
   }

   @Override
   public String toString()
   {
      return numThreads * comptuers.size() + " core " + comptuers.size() + " comp";
   }

   private abstract class ConnectionRunnable implements Runnable
   {
      Connection connection;

      public ConnectionRunnable(Connection connection)
      {
         this.connection = connection;
      }
   }
}
