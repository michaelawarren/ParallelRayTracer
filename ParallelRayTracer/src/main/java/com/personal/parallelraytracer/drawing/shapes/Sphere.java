package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.math.Normal3;
import com.personal.parallelraytracer.math.Point3;
import com.personal.parallelraytracer.math.Ray;

public class Sphere extends GeometricShape
{
   public Sphere(boolean visible, boolean reflective, Point3 position,
       Normal3 normal3, Object material)
   {
      super(visible, reflective, position, normal3, material);
   }

   @Override
   public boolean hit(Ray ray, double tMin)
   {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
}
