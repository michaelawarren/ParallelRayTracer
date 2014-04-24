package com.personal.parallelraytracer.drawing.materials;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;

public class Matte 
{
   RGBColor color;

   public Matte(RGBColor color)
   {
      this.color = color;
   }
    
   public RGBColor getColor()
   {
      return color;
   }
   
   RGBColor shade(ShadeRec sr)
   {
      return sr.color;
   }
}
