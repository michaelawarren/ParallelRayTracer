package com.personal.parallelraytracer.drawing.light;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;
import com.personal.parallelraytracer.math.Vector;

public abstract class Light
{
   protected boolean shadow = false;
   protected double ls;
   protected RGBColor color;

   public abstract Vector getDirection(ShadeRec sr);

   public RGBColor L(ShadeRec sr)
   {
      return new RGBColor(color.scalarMultiply(ls));
   }
}
