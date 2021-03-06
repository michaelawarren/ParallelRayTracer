package com.personal.parallelraytracer.drawing.light;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;
import com.personal.parallelraytracer.math.Ray;
import com.personal.parallelraytracer.math.Vector;

/**
 * Based off of Ray Tracing from the Ground up
 *
 */
public class Ambient extends Light
{
   public Ambient()
   {
      this.ls = 1.0d;
      this.color = RGBColor.WHITE;
   }
   
   public Ambient(float ls, RGBColor color, boolean shadow)
   {
      this.ls = ls;
      this.color = color;
      this.shadow = shadow;
   }
   
   @Override
   public Vector getDirection(ShadeRec sr)
   {
      return new Vector(0,0,0);
   }

   @Override
   public boolean inShadow(Ray shadowRay, ShadeRec sr)
   {
      return false;
   }
}
