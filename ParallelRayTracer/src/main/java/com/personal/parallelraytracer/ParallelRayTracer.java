package com.personal.parallelraytracer;

import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.drawing.cameras.Camera;
import com.personal.parallelraytracer.cluster.Connection;
import com.personal.parallelraytracer.drawing.cameras.PinHole;
import com.personal.parallelraytracer.drawing.cameras.PinHoleMaster;
import com.personal.parallelraytracer.drawing.cameras.PinHoleParallel;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Vector;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONStringer;

public class ParallelRayTracer
{
   public static void main(String[] args)
   {
      if (args.length > 0 && "server".equals(args[0]))
      {
         return;
      }

      System.out.println("Starting tests");
      boolean debug = false;

      List<String> hosts = new ArrayList<>();
      hosts.add("LocalHost");
      if (!debug)
      {
         hosts.add("Aus213L15");
         hosts.add("Aus213L16");
         hosts.add("Aus213L17");
         hosts.add("Aus213L18");
      }
      Camera[] cameras = (debug) ? debug(hosts) : noDebug(hosts);

      Size[] sizes = new Size[]
      {
         new Size(500, 500), new Size(500, 1000), new Size(1000, 1000)
      };

      long[][] matrix = new long[sizes.length][cameras.length];
      runTests(cameras, sizes, matrix);

      for (String string : hosts)
      {
         try
         {
            Connection connection = new Connection(string);
            connection.sendMessage(new JSONStringer().object()
                .key("status").value(400)
                .endObject().toString() + "\n");
            connection.readLine();
         }
         catch (IOException ex)
         {

         }
         catch (JSONException ex)
         {
            ex.printStackTrace();
         }
      }
   }

   public static void runTests(Camera[] cameras, Size[] sizes, long[][] matrix)
   {
      World world = new World();
      System.out.println("             1 core 1 comp | 2 core 1 comp | "
          + "2 core 2 comp | 4 core 1 comp | 4 core 2 comp | 8 core 2 comp "
          + "| 16 core 4 comp|");
      for (int sizeIndex = 0; sizeIndex < sizes.length; sizeIndex++)
      {
         System.out.printf("%11s:", sizes[sizeIndex]);
         for (int cameraIndex = 0; cameraIndex < cameras.length; cameraIndex++)
         {
            long start = System.currentTimeMillis();
            cameras[cameraIndex].setFileName(cameras[cameraIndex].toString()
                + sizes[sizeIndex].toString() + ".png");
            initializeWorld(cameras, cameraIndex, sizes, sizeIndex, world);
            world.getCamera().renderScene(world);
            matrix[sizeIndex][cameraIndex]
                = (System.currentTimeMillis() - start);
            System.out.printf("%11d ms |", matrix[sizeIndex][cameraIndex]);
         }
         System.out.println();
      }
   }

   public static void initializeWorld(Camera[] cameras, int cameraIndex,
       Size[] sizes, int sizeIndex, World world)
   {
      world.setRequiermentScene(cameras[cameraIndex], sizes[sizeIndex].width,
          sizes[sizeIndex].height);
      if (cameras[cameraIndex] instanceof PinHoleMaster)
      {
         try
         {
            ((PinHoleMaster) cameras[cameraIndex]).initializeConnections(
                sizes[sizeIndex]);
         }
         catch (IOException | JSONException ex)
         {
            ex.printStackTrace();
         }
      }
   }

   public static Camera[] debug(List<String> host1)
   {
      return new Camera[]
      {

         new PinHoleMaster(new Point(100, 100, 100), new Point(-5, 0, 0),
         new Vector(1, 1, 0), 1, "cluster.png", 1, host1.subList(0, 1))
      };

   }

   public static Camera[] noDebug(List<String> host1)
   {
      return new Camera[]
      {
         new PinHole(850.0d, 1, new Point(100, 100, 100), new Point(-5, 0, 0),
         new Vector(1, 1, 0), 1, "Single.png"),
         new PinHoleParallel(850.0d, 1, new Point(100, 100, 100),
         new Point(-5, 0, 0), new Vector(1, 1, 0), 1, "Parallel.png", 2),
         new PinHoleMaster(new Point(100, 100, 100), new Point(-5, 0, 0),
         new Vector(1, 1, 0), 1, "cluster.png", 1, host1.subList(1, 3)),
         new PinHoleParallel(850.0d, 1, new Point(100, 100, 100),
         new Point(-5, 0, 0), new Vector(1, 1, 0), 1, "Parallel.png", 4),
         new PinHoleMaster(new Point(100, 100, 100), new Point(-5, 0, 0),
         new Vector(1, 1, 0), 1, "cluster.png", 2, host1.subList(1, 3)),
         new PinHoleMaster(new Point(100, 100, 100), new Point(-5, 0, 0),
         new Vector(1, 1, 0), 1, "cluster.png", 4, host1.subList(1, 3)),
         new PinHoleMaster(new Point(100, 100, 100), new Point(-5, 0, 0),
         new Vector(1, 1, 0), 1, "cluster.png", 4, host1.subList(1, 5))
      };
   }

   public static class Size
   {
      public final int width;
      public final int height;

      public Size(int width, int height)
      {
         this.width = width;
         this.height = height;
      }

      @Override
      public String toString()
      {
         return width + " x " + height;
      }
   }
}
