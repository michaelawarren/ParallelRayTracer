package com.personal.parallelraytracer.math;

/*
 * Ray class, for use with the optimized ray-box intersection test
 * described in:
 *
 *      Amy Williams, Steve Barrus, R. Keith Morley, and Peter Shirley
 *      "An Efficient and Robust Ray-Box Intersection Algorithm"
 *      Journal of graphics tools, 10(1):49-54, 2005
 * 
 *  Adapted to use in java
 */
public class Ray
{
   public Vector direction;
   public Point origin;
   public Vector invDirection;
   public int[] sign;

   public Ray(Vector direction, Point point)
   {
      this.direction = direction;
      this.origin = point;
      this.invDirection = new Vector(1 / direction.getX(), 1 / direction.getY(), 1 / direction.getZ());
      this.sign = new int[]
      {
         (invDirection.getX() < 0.0d) ? 1 : 0,
         (invDirection.getY() < 0.0d) ? 1 : 0,
         (invDirection.getZ() < 0.0d) ? 1 : 0
      };
   }

   public Point findLocalHitPoint(double t)
   {
      return new Point(origin.add(direction.scalarMultiply(t)));
   }
}
