package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.drawing.materials.Material;
import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import com.personal.parallelraytracer.math.Vector;

public class Sphere extends GeometricShape
{
   double radius;

   public Sphere(boolean visible, boolean reflective, Material material,
       Point position, double radius)
   {
      super(visible, reflective, material, position);
      this.radius = radius;
   }

   /**
    * Algorithm adapted from "Ray Tracing From the Ground Up"
    *
    * @param ray
    * @return
    */
   @Override
   public double hitPoint(Ray ray)
   {
      double tmin = Double.NaN;
      double t;
      Vector temp = new Vector(ray.getOrigin().subtract(position));
      double a = ray.direction.dotProduct(ray.direction);
      double b = temp.scalarMultiply(2.0).dotProduct(ray.direction);
      double c = temp.dotProduct(temp) - radius * radius;
      double disc = b * b - 4.0 * a * c;

      if (disc < 0.0)
      {
         return Double.NaN;
      }

      double e = Math.sqrt(disc);
      double denom = 2.0 * a;
      t = (-b - e) / denom; // smaller root

      if (t > EPSIOLON)
      {
         return t;
      }

      t = (-b + e) / denom; // larger root
      if (t > EPSIOLON)
      {
         return t;
      }

      return tmin;
   }

   /**
    * Algorithm adapted from "Ray Tracing From the Ground Up"
    *
    * @param point
    * @return
    */
   @Override
   public boolean contains(Point point)
   {
      return (point.subtract(position).dotProduct(point.subtract(position))) - (radius * radius) == 0;
   }

   /**
    * Equation used from "Ray Tracing From the Ground Up"
    * @param ray
    * @param t
    * @return 
    */
   @Override
   public Normal getNormal(Ray ray, double t)
   {
      if (Double.isNaN(t))
         return null;
      Vector temp = new Vector(ray.getOrigin().subtract(position));
      return new Normal(temp.add(ray.direction.scalarMultiply(t)));
   }
}
