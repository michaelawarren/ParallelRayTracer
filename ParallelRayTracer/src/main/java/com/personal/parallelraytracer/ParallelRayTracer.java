package com.personal.parallelraytracer;

import com.personal.parallelraytracer.drawing.World;

public class ParallelRayTracer
{
   public static void main(String[] args)
   {
      double[][] matrix = new double[3][7];
      World world = new World();
      world.setRequiermentScene();
      long start = System.currentTimeMillis();
      world.getCamera().renderScene(world);
      matrix[0][0] = (System.currentTimeMillis() - start) / 1000.0d;
      System.out.println("DrawTime: " + matrix[0][0] + " seconds");

   }
}
