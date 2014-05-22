package com.personal.parallelraytracer.cluster;

public abstract class RayRunable implements Runnable
{
   public int r; 
   public int c;

   public RayRunable(int r, int c)
   {
      this.r = r;
      this.c = c;
   }
}
