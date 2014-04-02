package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.math.Point3;

public class Plane extends GeometricShape
{
   public Plane(boolean visible, boolean reflective, Point3 position,
       Object material)
   {
      super(visible, reflective, position, material);
   }

   @Override
   public boolean hit(Object par0)
   {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
}
