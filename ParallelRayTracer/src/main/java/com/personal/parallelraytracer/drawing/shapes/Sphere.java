package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;

public class Sphere extends GeometricShape
{
   double radius;
   public Sphere(boolean visible, boolean reflective, Object material, Point position, double radius)
   {
      super(visible, reflective, material, position);
      this.radius = radius;
   }

   @Override
   public double hitPoint(Ray ray)
   {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public boolean contains(Point point)
   {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
}
