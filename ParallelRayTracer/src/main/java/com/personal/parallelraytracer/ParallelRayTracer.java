package com.personal.parallelraytracer;

import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.drawing.tracers.RayTrace;
import com.personal.parallelraytracer.drawing.tracers.Tracer;

public class ParallelRayTracer
{
   public static void main(String[] args)
   {
      double[][] matrix = new double[3][7];

      World world = new World();

      Tracer[] tracers = new Tracer[]
      {
         new RayTrace(world)
      };
      Size[] size = new Size[]
      {
         new Size(500, 500), new Size(500, 1000), new Size(1000, 1000)
      };

      System.out.println("             1 core 1 comp | 2 core 1 comp | 4 core 1 comp | 2 core 2 comp | 4 core 2 comp | 8 core 2 comp | 16 core 4 comp");
      for (int i = 0; i < tracers.length; i++)
      {
         for (int j = 0; j < size.length; j++)
         {
            
            world.setRequiermentScene(tracers[i], size[j].width, size[j].height);
            long start = System.currentTimeMillis();
            world.getCamera().renderScene(world);
            matrix[i][j] = (System.currentTimeMillis() - start) / 1000.0d;
         }
      }
      System.out.println("DrawTime: " + matrix[0][0] + " seconds");

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
