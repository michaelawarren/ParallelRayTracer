package com.playground.raytracerserver;

import com.personal.parallelraytracer.ParallelRayTracer.Size;
import com.personal.parallelraytracer.cluster.RowRunnable;
import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.drawing.cameras.PinHoleWorker;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Vector;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
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

class ParallelProtocol
{
   private State state = State.UNINITIALIZED;
   final private World world;
   private final JSONStringer stringer = new JSONStringer();
   private int numThreads;
   private int rowStart;
   private int rowEnd;
   private Size size;
   private Socket socket;
   DataOutputStream out;

   public ParallelProtocol(World world, Socket socket) throws IOException
   {
      this.world = world;
      this.socket = socket;
      this.out = new DataOutputStream(socket.getOutputStream());
   }

   String processInput(String input)
   {
      switch (state)
      {
         case UNINITIALIZED:
         {
            return initializeScene(input);
         }
         case PROCESSING:
         {
            return processRows(input);
         }
         case FINISHED:
            return "Finished";
         default:
            throw new UnsupportedOperationException(
                "Invalid state shutting down.");
      }
   }

   public String processRows(String input)
   {
      try
      {
         ExecutorService threadPool = Executors.newFixedThreadPool(
             this.numThreads);
         List<Future<JSONArray>> futures = new ArrayList<>();
         world.vp.getSampler().generateSamples();
         final PinHoleWorker camera = (PinHoleWorker) world.getCamera();
         for (int row = rowStart; row <= rowEnd; row++)
         {
            futures.add(threadPool.submit(new RowRunnable(row, rowEnd)
            {
               @Override
               public JSONArray call()
               {
                  try
                  {
                     JSONArray array = new JSONArray();
                     array = camera.renderScene(world, row, array);
                     return array;
                  }
                  catch(Exception ex)
                  {
                     ex.printStackTrace();
                     return null;
                  }
               }
            }));
         }

         int count = 0;
         while (!futures.isEmpty())
         {
            final Future<JSONArray> removed = futures.remove(0);
            JSONArray array = removed.get();
            if (removed.isDone())
            {
               try
               {
                  sendMessage(array.toString() + "\n");
                  count++;
               }
               catch (IOException ex)
               {
                  ex.printStackTrace();
                  threadPool.shutdownNow();
                  throw new IllegalStateException(
                      "Bad stuff happened. count = " + count, ex);
               }
               catch(Exception ex)
               {
                  ex.printStackTrace();
               }
            }
         }

         threadPool.shutdown();
      }
      catch (InterruptedException | ExecutionException ex)
      {
         ex.printStackTrace();
      }
      finally
      {
         state = State.UNINITIALIZED;
      }
      // just display what we got
      return "finished\n";
   }

   public String initializeScene(String input)
   {
      try
      {
         JSONObject jSONObject = new JSONObject(input);
         if (jSONObject.has("status") && jSONObject.getInt("status") != 200)
         {
            return "Done";
         }
         state = State.PROCESSING;
         numThreads = jSONObject.getInt("numThreads");
         rowStart = jSONObject.getInt("rs");
         rowEnd = jSONObject.getInt("re");
         final int width = jSONObject.getInt("width");
         final int height = jSONObject.getInt("height");
         this.size = new Size(width, height);
         world.setRequiermentScene(
             new PinHoleWorker(850.0d, 1, new Point(100, 100, 100),
                 new Point(-5, 0, 0), new Vector(1, 1, 0), 1,
                 "Single.png", numThreads), width, height);
         return stringer.object().key("status").value("initialized")
             .endObject().toString();
      }
      catch (JSONException ex)
      {
         ex.printStackTrace();
      }
      throw new IllegalArgumentException("scene not initialized!");
   }

   public synchronized void sendMessage(String message) throws IOException
   {
      byte[] buffer = message.getBytes();
      int bytes = 0;

      // Copy requested file into the socket's output stream.
      bytes = buffer.length;
      out.write(buffer, 0, bytes);
      out.flush();
   }

   public State getState()
   {
      return state;
   }

   public enum State
   {
      UNINITIALIZED,
      PROCESSING,
      FINISHED
   }
}
