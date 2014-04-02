package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.math.Point3;

public class Box extends GeometricShape
{
   public Box(boolean visible, boolean reflective, Point3 position,
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
