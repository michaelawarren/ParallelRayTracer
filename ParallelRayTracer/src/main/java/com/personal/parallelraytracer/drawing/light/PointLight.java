package com.personal.parallelraytracer.drawing.light;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.shapes.GeometricShape;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import com.personal.parallelraytracer.math.Vector;

/**
 * Based off of Ray Tracing from the Ground up
 *
 */
public class PointLight extends Light
{
   Point location;

   public PointLight(Point location, float ls, RGBColor color, boolean shadow)
   {
      this.location = location;
      this.ls = ls;
      this.color = color;
      this.shadow = shadow;
   }
   
   @Override
   public Vector getDirection(ShadeRec sr)
   {
      return new Vector(location.subtract(sr.hitPoint).normalize());
   }

   @Override
   public boolean inShadow(Ray ray, ShadeRec sr)
   {
      double t;
      double d = location.distance(ray.getOrigin());
      
      for (GeometricShape shape : sr.world.shapes)
      {
         t = shape.shadowHit(ray);
         
         if (!Double.isNaN(t) && t < d)
            return true;
      }
      return false;
   }
}
