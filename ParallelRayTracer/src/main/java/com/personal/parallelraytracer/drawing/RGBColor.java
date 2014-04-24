package com.personal.parallelraytracer.drawing;

import java.awt.Color;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class RGBColor extends Vector3D
{
   //Constance
   public static final RGBColor BLACK = new RGBColor(0, 0, 0);
   public static final RGBColor RED = new RGBColor(1, 0, 0);
   public static final RGBColor BLUE = new RGBColor(0, 0, 1);
   public static final RGBColor GREEN = new RGBColor(0, 1, 0);
   public static final RGBColor WHITE = new RGBColor(1, 1, 1);
   public static final RGBColor YELLOW = new RGBColor(1, 1, 0);

   // class stuff
   public RGBColor(double x, double y, double z)
   {
      super(x, y, z);
   }

   public RGBColor(Vector3D vector3D)
   {
      super(vector3D.toArray());
   }

   public RGBColor componmentMultiply(RGBColor rGBColor)
   {
      return new RGBColor(this.getX() * rGBColor.getX(),
          this.getY() * rGBColor.getY(), this.getZ() * rGBColor.getZ());
   }

   public Color getColor()
   {
      return new Color((float) getX(), (float) getY(), (float) getZ());
   }
}
