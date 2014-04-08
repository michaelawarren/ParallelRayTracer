package com.personal.parallelraytracer.math;

public class Ray
{
   public Vector3 direction;
   public Point origin;

   public Ray(Vector3 direction, Point point)
   {
      this.direction = direction;
      this.origin = point;
   }
}
