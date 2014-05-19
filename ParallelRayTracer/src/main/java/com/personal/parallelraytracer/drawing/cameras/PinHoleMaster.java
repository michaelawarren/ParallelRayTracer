package com.personal.parallelraytracer.drawing.cameras;

import com.personal.parallelraytracer.ParallelRayTracer.Size;
import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Vector;
import java.io.IOException;
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
   
   public void renderImage(int height, List<Future> futures)
   {
      for (int r = 0; r < height; r++)
      {
         futures.add(threadPool.submit(new RowRunnable(r)
         {
            @Override
            public void run()
            {
               Connection connection = connections.get(row % connections.size()); 
               
               try
               {
                  JSONArray jsonObject = getColors(connection);
                  saveToImage(jsonObject);
               }
               catch (IOException | JSONException | IllegalStateException ex)
               {
                  ex.printStackTrace();
               }
            }
            
            public JSONArray getColors(Connection connection) throws JSONException, IOException
            {
               connection.sendMessage(new JSONStringer().object()
                   .key("r").value(row)
                   .endObject()
                   .toString() + "\n");
               final String line = connection.readLine();
               final JSONArray jsonObject = new JSONArray(line);
               return jsonObject;
            }
         }));
      }
   }
   
   public List<Connection> initializeConnections(Size size) throws IOException, JSONException
   {
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
             .endObject()
             .toString() + "\n"
         );
         connection.readLine();
         
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
            throw new IllegalStateException(jsonArray.get(i).toString(), ex);
         }
      }
   }
   
   @Override
   public String toString()
   {
      return numThreads * comptuers.size() + " core " + comptuers.size() + " comp";
   }
   
   private abstract class RowRunnable implements Runnable
   {
      protected int row;
      
      public RowRunnable(int row)
      {
         this.row = row;
      }
   }
}
