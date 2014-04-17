package com.personal.parallelraytracer.math;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Interface to use Vector3D to more easily denote the difference of point,
 * vector, and normals
 */
public class Vector extends Vector3D
{
   public Vector(double x, double y, double z)
   {
      super(x, y, z);
   }
   
   public Vector(Vector3D vector)
   {
      super(vector.toArray());
   }
}
