package com.personal.parallelraytracer.math;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Interface to use Vector3D to more easily denote the difference of point,
 * vector, and normals
 */
public class Normal extends Vector3D
{
   /**
    * builds the normal based off the vector given.
    *
    * @param vector
    */
   public Normal(Vector3D vector)
   {
      super(vector.normalize().getX(), vector.normalize().getY(), vector
          .normalize().getZ());
   }

   /**
    * Builds the normal based off the x, y, and z components given
    *
    * @param x
    * @param y
    * @param z
    */
   public Normal(double x, double y, double z)
   {
      this(new Vector(x, y, z));
   }
}
