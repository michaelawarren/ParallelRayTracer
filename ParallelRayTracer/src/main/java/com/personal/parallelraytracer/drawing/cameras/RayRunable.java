package com.personal.parallelraytracer.drawing.cameras;

public abstract class RayRunable implements Runnable
{
   int r; 
   int c;

   public RayRunable(int r, int c)
   {
      this.r = r;
      this.c = c;
   }
}
