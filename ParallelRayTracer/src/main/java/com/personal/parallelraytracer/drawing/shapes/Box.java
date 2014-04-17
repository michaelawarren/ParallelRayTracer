package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;

public class Box extends GeometricShape
{
   Point[] parameters;
   Point max;
   
   public Box(boolean visible, boolean reflective, Object material,
       Point min, Point max)
   {
      super(visible, reflective, material, min);
      parameters = new Point[] {min, max};
      this.max = max;
   }

   /**
    * Ray-box intersection using IEEE numerical properties to ensure that the
    * test is both robust and efficient, as described in:
    *
    * Amy Williams, Steve Barrus, R. Keith Morley, and Peter Shirley
    * "An Efficient and Robust Ray-Box Intersection Algorithm"
    * Journal of graphics tools, 10(1):49-54, 2005
    *
    * @param ray
    * @param t0
    * @param t1
    * @return 
    */
   @Override
   public double hitPoint(Ray ray)
   {
      double tmin, tmax, tymin, tymax, tzmin, tzmax;

      tmin = (parameters[ray.sign[0]].getX() - ray.origin.getX()) * ray.invDirection.getX();
      tmax = (parameters[1 - ray.sign[0]].getX() - ray.origin.getX()) * ray.invDirection
          .getX();
      tymin = (parameters[ray.sign[1]].getY() - ray.origin.getY()) * ray.invDirection.getY();
      tymax = (parameters[1 - ray.sign[1]].getY() - ray.origin.getY()) * ray.invDirection
          .getY();
      if ((tmin > tymax) || (tymin > tmax))
      {
         return Double.NaN;
      }
      if (tymin > tmin)
      {
         tmin = tymin;
      }
      if (tymax < tmax)
      {
         tmax = tymax;
      }
      tzmin = (parameters[ray.sign[2]].getZ() - ray.origin.getZ()) * ray.invDirection.getZ();
      tzmax = (parameters[1 - ray.sign[2]].getZ() - ray.origin.getZ()) * ray.invDirection
          .getZ();
      if ((tmin > tzmax) || (tzmin > tmax))
      {
         return Double.NaN;
      }
      if (tzmin > tmin)
      {
         tmin = tzmin;
      }
      if (tzmax < tmax)
      {
         tmax = tzmax;
      }
      if (tmax > EPSIOLON)
         return tmin > EPSIOLON ? tmin : tmax;
      return Double.NaN;
   }

   @Override
   public boolean contains(Point point)
   {
      return position.getX() <= point.getX()
          && max.getX() >= point.getX()
          && position.getY() <= point.getY()
          && max.getY() >= point.getY()
          && position.getZ() <= point.getZ()
          && max.getZ() >= point.getZ();
   }

   @Override
   public Normal getNormal(Ray ray, double t)
   {
      //TODO: write getNormal
      throw new UnsupportedOperationException("Not supported yet.");
   }
}
