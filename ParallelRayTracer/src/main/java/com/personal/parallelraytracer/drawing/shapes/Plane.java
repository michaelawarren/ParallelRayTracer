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
      
      if (Double.isInfinite(t) || Double.isNaN(t))
      {
         return intractableValue(ray);
      }
      if (t > EPSIOLON)
      {
         return t;
      }
      return Double.NaN;
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
         return 1.0;
      else 
         return Double.NaN;
   }
}
