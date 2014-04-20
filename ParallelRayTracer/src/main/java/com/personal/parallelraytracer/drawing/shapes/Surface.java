package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;

public class Surface extends Plane
{
   int max;

   /**
    *
    * @param visible
    * @param reflective
    * @param material
    * @param min beginning of the rectangular finite plane
    * @param max end of rectangular finite plane
    * @param pt3 used to officially define the plane
    */
   public Surface(boolean visible, boolean reflective, Object material,
       Point min, Point max, Point pt3)
   {
      super(visible, reflective, min, new Normal(buildNormal(min, max, pt3)),
          material);
   }

   static Normal buildNormal(Point min, Point max, Point pt3)
   {
      return new Normal(min.subtract(max).crossProduct(min.subtract(pt3)));
   }
   
   @Override
   public boolean contains(Point origin)
   {
      return super.contains(origin); 
   }

   @Override
   public double hitPoint(Ray ray)
   {
      return super.hitPoint(ray);
   }
   
}