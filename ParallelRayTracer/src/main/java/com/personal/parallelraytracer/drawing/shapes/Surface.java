package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.drawing.materials.Material;
import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Surface extends Plane
{
   Point topRight;
   Point topLeft;
   Point bottomRight;
   Point bottomLeft;
   Point max;
   Point min;

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
   public Surface(boolean visible, boolean reflective, Material material,
       Point bottomLeft, Point bottomRight, Point topLeft, Point topRight,
       Side side)
   {
      super(visible, reflective, bottomLeft,
          buildNormal(bottomLeft, topRight, bottomRight, side),
          material);

      this.bottomLeft = bottomLeft;
      this.bottomRight = bottomRight;
      this.topLeft = topLeft;
      this.topRight = topRight;
      double[] mins = new double[3];
      double[] maxs = new double[3];
      final double[] bottomLeftArray = bottomLeft.toArray();
      final double[] topRightArray = topRight.toArray();
      for (int i = 0; i < bottomLeftArray.length; i++)
      {
         maxs[i] = (bottomLeftArray[i] > topRightArray[i]) ? bottomLeftArray[i]
             : topRightArray[i];
         mins[i] = (bottomLeftArray[i] < topRightArray[i]) ? bottomLeftArray[i]
             : topRightArray[i];
      }
      this.max = new Point(maxs);
      this.min = new Point(mins);
   }

   public Surface(Point bottomLeft, Point bottomRight, Point topLeft,
       Point topRight, Side side)
   {
      this(true, true, null, bottomLeft, bottomRight, topLeft, topRight, side);
   }

   static Normal buildNormal(Point min, Point max, Point pt3, Side side)
   {
      final Vector3D crossProduct
          = min.subtract(max).crossProduct(min.subtract(pt3));
      switch (side)
      {
         case TOP:
         case RIGHT:
         case BACK:
         default:
            return new Normal(crossProduct);
         // sides where the build normal function results should be negated.
         case FRONT:
         case BOTTOM:
         case LEFT:
            return new Normal(crossProduct.negate());
      }
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

   public static enum Side
   {
      FRONT,
      BACK,
      TOP,
      BOTTOM,
      LEFT,
      RIGHT;
   }
}
