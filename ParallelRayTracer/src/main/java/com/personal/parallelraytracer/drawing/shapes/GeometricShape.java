/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.math.Normal3;
import com.personal.parallelraytracer.math.Point3;
import com.personal.parallelraytracer.math.Ray;

/**
 *
 * @author Michael
 */
public abstract class GeometricShape
{
   public final double EPSIOLON = 10e-6d;
   private boolean visible;
   private boolean reflective;
   private Point3 position;
   private Normal3 normal3;
   private Object material;

   public GeometricShape(boolean visible, boolean reflective, Point3 position,
       Normal3 normal3, Object material)
   {
      this.visible = visible;
      this.reflective = reflective;
      this.position = position;
      this.normal3 = normal3;
      this.material = material;
   }

   public abstract boolean hit(Ray ray, double tMin);
}
