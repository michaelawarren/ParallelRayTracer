package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.drawing.materials.Material;
import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import java.util.ArrayList;
import java.util.List;

public class Box extends GeometricShape
{
   private final Point[] parameters;
   private final Point max;
   private final Point min;
   private final List<Surface> surfaces;

   public Box(boolean visible, boolean reflective, Material material,
       Point min, Point max)
   {
      super(visible, reflective, material, min.midPoint(max));
      this.max = max;
      this.min = min;
      parameters = new Point[]
      {
         min, max
      };
      surfaces = new ArrayList<>();
      
            Point ooo = new Point(min.getX(), min.getY(), min.getZ());
      Point ool = new Point(min.getX(), min.getY(), max.getZ());
      Point olo = new Point(min.getX(), max.getY(), min.getZ());
      Point oll = new Point(min.getX(), max.getY(), max.getZ());
      Point loo = new Point(max.getX(), min.getY(), min.getZ());
      Point lol = new Point(max.getX(), min.getY(), max.getZ());
      Point llo = new Point(max.getX(), max.getY(), min.getZ());
      Point lll = new Point(max.getX(), max.getY(), max.getZ());

      // sides
      surfaces.add(new Surface(ooo, ool, olo, oll));
      surfaces.add(new Surface(llo, loo, lll, lol));

      // top bottom
      surfaces.add(new Surface(ooo, loo, ool, lol));
      surfaces.add(new Surface(olo, llo, oll, lll));

      // front back
      surfaces.add(new Surface(ooo, loo, olo, llo));
      surfaces.add(new Surface(ool, lol, oll, lll));
   }

   public Box(boolean visible, boolean reflective, Material material,
       Point center, double width, double height, double length)
   {
      this(visible, reflective, material, 
          new Point(center.subtract(delta(width, height, length))),
          new Point(center.add(delta(width, height, length))));
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
    * @return
    */
   @Override
   public double hitPoint(Ray ray)
   {
      double tmin, tmax, tymin, tymax, tzmin, tzmax;

      tmin = (parameters[ray.sign[0]].getX() - ray.getOrigin().getX())
          * ray.invDirection.getX();
      tmax = (parameters[1 - ray.sign[0]].getX() - ray.getOrigin().getX())
          * ray.invDirection.getX();
      tymin = (parameters[ray.sign[1]].getY() - ray.getOrigin().getY())
          * ray.invDirection.getY();
      tymax = (parameters[1 - ray.sign[1]].getY() - ray.getOrigin().getY())
          * ray.invDirection.getY();

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
      tzmin = (parameters[ray.sign[2]].getZ() - ray.getOrigin().getZ())
          * ray.invDirection.getZ();
      tzmax = (parameters[1 - ray.sign[2]].getZ() - ray.getOrigin().getZ())
          * ray.invDirection.getZ();
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
      {
         return tmin > EPSIOLON ? tmin : tmax;
      }
      return Double.NaN;
   }

   @Override
   public boolean contains(Point point)
   {
      return min.getX() <= point.getX()
          && max.getX() >= point.getX()
          && min.getY() <= point.getY()
          && max.getY() >= point.getY()
          && min.getZ() <= point.getZ()
          && max.getZ() >= point.getZ();
   }

   @Override
   public Normal getNormal(Ray ray, double t)
   {
      Point point = ray.findLocalHitPoint(t);
      for (Surface surface : surfaces)
      {
         if (surface.contains(point))
         {
            return surface.getNormal(ray, t);
         }
      }
      return null;
   }

   private static Point delta(double width, double height, double length)
   {
      return new Point(width / 2, height / 2, length / 2);
   }
}
