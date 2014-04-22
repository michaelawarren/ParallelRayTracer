package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;

public class Surface extends Plane
{
   Point topRight;
   Point topLeft;
   Point bottomRight;
   Point bottomLeft;

   /**
    *
    * @param visible
    * @param reflective
    * @param material
    * @param bottomLeft beginning of the rectangular finite plane
    * @param topRight end of rectangular finite plane
    * @param topLeft
    * @param bottomRight used to officially define the plane
    */
   public Surface(boolean visible, boolean reflective, Object material,
       Point bottomLeft, Point bottomRight, Point topLeft, Point topRight)
   {
      super(visible, reflective, bottomLeft,
          buildNormal(bottomLeft, topRight, bottomRight),
          material);
      this.bottomLeft = bottomLeft;
      this.bottomRight = bottomRight;
      this.topLeft = topLeft;
      this.topRight = topRight;
   }

   public Surface(Point bottomLeft, Point bottomRight, Point topLeft,
       Point topRight)
   {
      this(true, true, null, bottomLeft, bottomRight, topLeft, topRight);
   }

   static Normal buildNormal(Point min, Point max, Point pt3)
   {
      return new Normal(min.subtract(max).crossProduct(min.subtract(pt3)));
   }

   @Override
   public boolean contains(Point point)
   {
      return super.contains(point) && bottomLeft.getX() <= point.getX()
          && topRight.getX() >= point.getX()
          && bottomLeft.getY() <= point.getY()
          && topRight.getY() >= point.getY()
          && bottomLeft.getZ() <= point.getZ()
          && topRight.getZ() >= point.getZ();
   }

   @Override
   public double hitPoint(Ray ray)
   {
      return super.hitPoint(ray);
   }

   @Override
   public String toString()
   {
      return "bottomLeft: " + bottomLeft.toString() 
          + "\ntopRight  :" + topRight.toString();
   }
   
   
}
