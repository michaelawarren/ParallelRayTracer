package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;

public class Sphere extends GeometricShape
{
   public Sphere(boolean visible, boolean reflective, Point position,
       Normal normal3, Object material)
   {
      super(visible, reflective, material, position);
   }

   @Override
   public double hitPoint(Ray ray)
   {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public boolean cointains(Point origin)
   {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
}
