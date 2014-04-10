/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Michael
 */
public abstract class GeometricShape
{
   public final double EPSIOLON = 10e-6d;
   protected boolean visible;
   protected boolean reflective;
   protected Point position;
   protected Object material;
   protected Map<String, Comparable> hitStats;

   public GeometricShape(boolean visible, boolean reflective, Object material, Point position)
   {
      this.visible = visible;
      this.reflective = reflective;
      this.position = position;
      this.material = material;
      this.hitStats = new HashMap<String, Comparable>();
   }

   public abstract double hitPoint(Ray ray);
   public abstract boolean cointains(Point origin);
}
