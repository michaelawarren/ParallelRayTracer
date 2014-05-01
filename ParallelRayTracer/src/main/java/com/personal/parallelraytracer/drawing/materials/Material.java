package com.personal.parallelraytracer.drawing.materials;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;

public abstract class Material
{
   RGBColor color;
   
   public Material()
   {
      this.color = RGBColor.WHITE;
   }

   public RGBColor getColor()
   {
      return color;
   }
   public abstract RGBColor shade(ShadeRec sr);
   public abstract RGBColor areLightShade(ShadeRec sr);
   public abstract RGBColor pathShade(ShadeRec sr);
}
