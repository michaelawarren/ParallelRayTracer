package com.personal.parallelraytracer.drawing.cameras;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Vector;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONStringer;

public class PinHoleMaster extends Camera
{
   private final int numThreads;
   private ExecutorService threadPool;
   private final List<String> comptuers;
   protected List<Connection> connections;

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
         initializeConnections(world);

         openWindow(width, height);
         renderImage(height, futures, width);

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
         writeImageToFile(fileName);
      }
      catch (IOException ex)
      {
         ex.printStackTrace();
      }
      catch (JSONException ex)
      {
         ex.printStackTrace();
      }
   }

   public void renderImage(int height, List<Future> futures, final int width)
   {
      for (int r = 0; r < height; r++)
      {
         futures.add(threadPool.submit(new RowRunnable(r)
         {
            @Override
            public void run()
            {
               Connection connection = aquireConnection();

               try
               {
                  JSONArray jsonObject = getColors(connection);
                  saveToImage(jsonObject);
               }
               catch (IOException ex)
               {
                  ex.printStackTrace();
                  releaseConnection(connection);
               }
               catch (JSONException ex)
               {
                  ex.printStackTrace();
               }
            }

            public JSONArray getColors(Connection connection) throws JSONException, IOException
            {
               JSONArray jSONArray = new JSONArray();
               for (int c = 0; c < width; c++)
               {
                  jSONArray.put(new Pixel(row, c));
               }
               connection.sendMessage(jSONArray.toString() + "\n");
               final JSONArray jsonObject
                   = new JSONArray(connection.readLine());
               releaseConnection(connection);
               return jsonObject;
            }
         }));
      }
   }

   public List<Connection> initializeConnections(World world) throws IOException, JSONException
   {
      connections = new ArrayList<>();
      for (String hostName : comptuers)
      {
         final Connection connection
             = new Connection(new Socket(hostName, 6789));
         connection.sendMessage(
             new JSONStringer()
             .object()
             .key("initialize").value(1)
             .key("width").value(world.vp.getWidth())
             .key("height").value(world.vp.getHeight())
             .key("numThreads").value(numThreads)
             .endObject()
             .toString() + "\n"
         );
         connection.readLine();

         connections.add(connection);
      }
      return connections;
   }

   public Connection aquireConnection()
   {
      // get available connection
      Connection connection = null;
      while (connection == null)
      {
         synchronized (connections)
         {
            if (!connections.isEmpty())
            {
               connection = connections.remove(0);
            }
         }
      }
      return connection;
   }

   public void releaseConnection(Connection connection)
   {
      synchronized (connections)
      {
         connections.add(connection);
         connection = null;
      }
   }

   public void saveToImage(final JSONArray jsonObject) throws JSONException
   {
      for (int i = 0; i < jsonObject.length(); i++)
      {
         displayPixel(
             jsonObject.getJSONObject(i).getInt("r"),
             jsonObject.getJSONObject(i).getInt("c"),
             new RGBColor(jsonObject.getJSONObject(i)
                 .getJSONObject(
                     "color")));
      }
   }

   @Override
   public String toString()
   {
      return numThreads + " core " + comptuers.size() + " comp";
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
