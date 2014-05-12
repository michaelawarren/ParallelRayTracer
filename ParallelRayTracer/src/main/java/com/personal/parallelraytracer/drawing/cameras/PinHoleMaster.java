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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;

public class PinHoleMaster extends Camera
{
   private final int numThreads;
   private ExecutorService threadPool;
   private final List<String> comptuers;
   private final List<Socket> sockets;

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
      this.sockets = new ArrayList<>();
   }

   @Override
   public void renderScene(World world)
   {
      try
      {
         final int height = world.vp.getHeight();
         final int width = world.vp.getWidth();
         // initailzie our clients
         threadPool = Executors.newFixedThreadPool(numThreads);
//       List<Future> futures = new ArrayList<>();
         List<Connection> connections = new ArrayList<>();
         for (String hostName : comptuers)
         {
            connections.add(new Connection(new Socket(hostName, 6789)));
         }

         // Construct a 1K buffer to hold bytes on their way to the socket.
         openWindow(width, height);
         for (int r = 0; r < height; r++)
         {
            JSONArray jSONArray = new JSONArray();

            for (int c = 0; c < width; c++)
            {
               jSONArray.put(new Pixel(r, c));
            }
            connections.get(0).sendMessage(jSONArray.toString() + "\n");
            final JSONArray jsonObject
                = new JSONArray(connections.get(0).readLine());
            System.out.println(jSONArray.toString());
            for (int i = 0; i < jsonObject.length(); i++)
            {
               displayPixel(
                   jsonObject.getJSONObject(i).getInt("r"),
                   jsonObject.getJSONObject(i).getInt("c"),
                   new RGBColor(jsonObject.getJSONObject(i).getJSONObject(
                           "color")));
            }
         }

//         for (Future future : futures)
//         {
//            try
//            {
//               future.get();
//            }
//            catch (InterruptedException | ExecutionException ex)
//            {
//               ex.printStackTrace();
//            }
//         }
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
         Logger.getLogger(PinHoleMaster.class.getName()).log(Level.SEVERE, null,
             ex);
      }
   }

   @Override
   public String toString()
   {
      return numThreads + " core " + comptuers.size() + " comp";
   }

   private class Connection
   {
      Socket socket;
      InputStream is;
      InputStreamReader ir;
      BufferedReader bufferedReader;
      DataOutputStream os;

      public Connection(Socket socket) throws IOException
      {
         is = socket.getInputStream();
         ir = new InputStreamReader(is);
         bufferedReader = new BufferedReader(ir);
         os = new DataOutputStream(socket.getOutputStream());
      }

      public void sendMessage(String message) throws IOException
      {
         byte[] buffer = message.getBytes();
         int bytes = 0;

         // Copy requested file into the socket's output stream.
         bytes = buffer.length;
         os.write(buffer, 0, bytes);
         os.flush();
      }
      
      public String readLine() throws IOException
      {
         return bufferedReader.readLine();
      }
      
      public void close() throws IOException
      {
         socket.close();
      }
   }
}
