package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class FinitePlane extends Plane
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
   public FinitePlane(boolean visible, boolean reflective, Object material,
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
   public boolean cointains(Point origin)
   {
      return super.cointains(origin); 
   }

   @Override
   public double hitPoint(Ray ray)
   {
      return super.hitPoint(ray);
   }
   
}
