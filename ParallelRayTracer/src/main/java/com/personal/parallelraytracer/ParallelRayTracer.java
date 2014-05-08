package com.personal.parallelraytracer;

import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.drawing.cameras.Camera;
import com.personal.parallelraytracer.drawing.cameras.PinHole;
import com.personal.parallelraytracer.drawing.cameras.PinHoleParallel;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Vector;

public class ParallelRayTracer
{
   public static void main(String[] args)
   {
      if (args.length > 0 && "server".equals(args[0]))
      {
         System.out.println("Starting server...");
         return;
      }
      long[][] matrix = new long[3][7];

      World world = new World();

      Camera[] cameras = new Camera[]
      {
         new PinHole(850.0d, 1, new Point(100, 100, 100), new Point(-5, 0, 0),
            new Vector(1, 1, 0), 1, "Single.png"),
         new PinHoleParallel(850.0d, 1, new Point(100, 100, 100),
            new Point(-5, 0, 0), new Vector(1, 1, 0), 1, "Parallel.png", 2),
         new PinHoleParallel(850.0d, 1, new Point(100, 100, 100),
            new Point(-5, 0, 0), new Vector(1, 1, 0), 1, "Parallel.png", 4)
      };
      Size[] sizes = new Size[]
      {
         new Size(500, 500)//, new Size(500, 1000), new Size(1000, 1000)
      };

      for (int tracerIndex = 0; tracerIndex < cameras.length; tracerIndex++)
      {
         for (int sizeIndex = 0; sizeIndex < sizes.length; sizeIndex++)
         {
            cameras[tracerIndex].setFileName(cameras[tracerIndex].toString()
                + sizes[sizeIndex].toString() + ".png");
            world.setRequiermentScene(cameras[tracerIndex],
                sizes[sizeIndex].width, sizes[sizeIndex].height);
            long start = System.currentTimeMillis();
            world.getCamera().renderScene(world);
            matrix[sizeIndex][tracerIndex]
                = (System.currentTimeMillis() - start);
         }
      }
      System.out.println("             1 core 1 comp | 2 core 1 comp | "
          + "4 core 1 comp | 2 core 2 comp | 4 core 2 comp | 8 core 2 comp "
          + "| 16 core 4 comp |");

      for (int i = 0; i < sizes.length; i++)
      {
         System.out.printf("%11s:", sizes[i]);
         for (int j = 0; j < matrix[i].length; j++)
         {
            System.out.printf("%11d ms |", matrix[i][j]);
         }
         System.out.println();
      }

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
