/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.math.Point3;

/**
 *
 * @author Michael
 */
public abstract class GeometricShape
{
   private boolean visible;
   private boolean reflective;
   private Point3 position;
   private Object material;

   public GeometricShape(boolean visible, boolean reflective, Point3 position,
       Object material)
   {
      this.visible = visible;
      this.reflective = reflective;
      this.position = position;
      this.material = material;
   }
   
   public abstract boolean hit(Object par0);
}
