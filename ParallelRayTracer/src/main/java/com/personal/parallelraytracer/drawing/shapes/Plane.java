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
      normal = normal;
   }

   @Override
   public double hitPoint(Ray ray)
   {
      double t = position.subtract(ray.origin).dotProduct(normal)
          / ray.direction.dotProduct(normal);
      if ((Double.isInfinite(t) || Double.isNaN(t)) && cointains(ray.origin))
      {
         return t;
      }
      else if (t > EPSIOLON && !Double.isInfinite(t))
      {
         return t;
      }
      return EPSIOLON;
   }

   @Override
   public boolean cointains(Point origin)
   {
      return Double.compare(position.subtract(origin)
          .dotProduct(normal), 0.0d) == 0;
   }
}
