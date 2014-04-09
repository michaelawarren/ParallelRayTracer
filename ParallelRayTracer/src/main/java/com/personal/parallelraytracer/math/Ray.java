package com.personal.parallelraytracer.math;

public class Ray
{
   public Vector direction;
   public Point origin;

   public Ray(Vector direction, Point point)
   {
      this.direction = direction;
      this.origin = point;
   }
}
