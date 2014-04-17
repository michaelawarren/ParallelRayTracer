package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;

public class Plane extends GeometricShape
{
   Normal normal;
   public Plane(boolean visible, boolean reflective, Point position,
       Normal normal, Object material)
   {
      super(visible, reflective, material, position);
      this.normal = normal;
   }

   /**
    * Algorithm based off plane algorithm found in 
    * "Ray Tracing From the Ground Up" by Kevin Suffern
    * 
    * @param ray
    * @return 
    */
   @Override
   public double hitPoint(Ray ray)
   {
      double t = position.subtract(ray.origin).dotProduct(normal)
          / ray.direction.dotProduct(normal);
      
      if (!Double.isInfinite(t) && t > EPSIOLON)
      {
         //TODO: figure out ShadeRec
         return t;
      }
      return Double.NaN; // Nan == false
   }

   @Override
   public boolean contains(Point point)
   {
      return Double.compare(position.subtract(point)
          .dotProduct(normal), 0.0d) == 0;
   }

   protected double intractableValue(Ray ray)
   {
      if (contains(ray.origin))
         return EPSIOLON + EPSIOLON;
      else 
         return Double.NaN;
   }

   @Override
   public Normal getNormal(Ray ray, double t)
   {
      //TODO: write getNormal
      throw new UnsupportedOperationException("Not supported yet.");
   }
}
