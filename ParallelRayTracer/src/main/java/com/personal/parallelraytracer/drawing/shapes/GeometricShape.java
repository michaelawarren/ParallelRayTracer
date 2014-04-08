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
   protected Normal normal;
   protected Object material;
   protected Map<String, Comparable> hitStats;

   public GeometricShape(boolean visible, boolean reflective, Point position,
       Normal normal3, Object material)
   {
      this.visible = visible;
      this.reflective = reflective;
      this.position = position;
      this.normal = normal3;
      this.material = material;
      this.hitStats = new HashMap<String, Comparable>();
   }

   public abstract double hitPoint(Ray ray);
   public abstract boolean cointains(Point origin);
}
