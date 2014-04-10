package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;

public class Box extends GeometricShape
{
   Point point1;
   Point point2;
   Point[] bounds = new Point[2];

   public Box(boolean visible, boolean reflective, Point point1, Point point2,
       Normal normal3, Object material)
   {
      super(visible, reflective, material, point1);
      this.point1 = point1;
      this.point2 = point2;
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
