package com.personal.parallelraytracer.math;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Interface to use Vector3D to more easily denote the difference of point,
 * vector, and normals
 */
public class Point extends Vector3D
{
   public Point(double x, double y, double z)
   {
      super(x, y, z);
   }

   public Point(Vector3D vector)
   {
      // rounding taking place to turn close to zero to zero
      super(Math.round(vector.getX() * 1000) / 1000d,
          Math.round(vector.getY() * 1000) / 1000d,
          Math.round(vector.getZ() * 1000) / 1000d);
   }

   public Point(double[] array)
   {
      super(array);
   }

   public Point midPoint(Point pt2)
   {
      return new Point(this.add(pt2).scalarMultiply(0.5d));
   }
}
