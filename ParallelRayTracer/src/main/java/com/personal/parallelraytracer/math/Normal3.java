package com.personal.parallelraytracer.math;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Interface to use Vector3D to more easily denote the difference of point,
 * vector, and normals
 */
public class Normal3 extends Vector3D
{
   public Normal3(Vector3D vector)
   {
      super(vector.normalize().getX(), vector.normalize().getY(), vector
          .normalize().getZ());
   }

   public Normal3(double x, double y, double z)
   {
      super(x, y, z);
   }
}
