package com.personal.parallelraytracer;

import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.drawing.tracers.RayTrace;
import com.personal.parallelraytracer.drawing.tracers.Tracer;

public class ParallelRayTracer
{
   public static void main(String[] args)
   {
      long[][] matrix = new long[3][7];

      World world = new World();

      Tracer[] tracers = new Tracer[]
      {
         new RayTrace(world)
      };
      Size[] size = new Size[]
      {
         new Size(500, 500), new Size(500, 1000), new Size(1000, 1000)
      };

      for (int tracerIndex = 0; tracerIndex < tracers.length; tracerIndex++)
      {
         for (int sizeIndex = 0; sizeIndex < size.length; sizeIndex++)
         {
            world.setRequiermentScene(tracers[tracerIndex], size[sizeIndex].width, size[sizeIndex].height);
            long start = System.currentTimeMillis();
            world.getCamera().renderScene(world);
            matrix[sizeIndex][tracerIndex] = (System.currentTimeMillis() - start);
         }
      }
      System.out.println("             1 core 1 comp | 2 core 1 comp | 4 core 1 comp | 2 core 2 comp | 4 core 2 comp | 8 core 2 comp | 16 core 4 comp |");
      
      for (int i = 0; i < matrix.length; i++)
      {
         System.out.printf("%11s:",size[i]);
         for (int j = 0; j < matrix[i].length; j++)
         {
            System.out.printf("%11d ms |", matrix[i][j]);
         }
         System.out.println();
      }

   }

   static class Size
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
         return  width + " x " + height;
      }
      
      
   }
}
