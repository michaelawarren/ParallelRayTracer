package com.personal.parallelraytracer.math;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Interface to use Vector3D to more easily denote the difference of point,
 * vector, and normals
 */
public class Point3 extends Vector3D
{
   public Point3(double x, double y, double z)
   {
      super(x, y, z);
   }
}