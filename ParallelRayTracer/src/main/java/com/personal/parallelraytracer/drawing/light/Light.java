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

   public boolean isShadow()
   {
      return shadow;
   }

   public void setShadow(boolean shadow)
   {
      this.shadow = shadow;
   }

   public double getLs()
   {
      return ls;
   }

   public void setLs(double ls)
   {
      this.ls = ls;
   }

   public RGBColor getColor()
   {
      return color;
   }

   public void setColor(RGBColor color)
   {
      this.color = color;
   }
   
   
}
