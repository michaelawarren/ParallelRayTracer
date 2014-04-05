package com.personal.parallelraytracer.math;

public class Ray
{
   public Vector3 direction;
   public Point3 point;

   public Ray(Vector3 direction, Point3 point)
   {
      this.direction = direction;
      this.point = point;
   }
}
