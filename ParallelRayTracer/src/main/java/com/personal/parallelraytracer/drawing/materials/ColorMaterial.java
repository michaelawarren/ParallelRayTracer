package com.personal.parallelraytracer.drawing.materials;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;

/**
 * Based off of Ray Tracing from the Ground up
 *
 */
public class ColorMaterial extends Material
{
   private final RGBColor color;

   public ColorMaterial(RGBColor color)
   {
      this.color = color;
   }

   @Override
   public RGBColor shade(ShadeRec sr)
   {
      return sr.color;
   }

   @Override
   public RGBColor areLightShade(ShadeRec sr)
   {
      return sr.color;
   }

   @Override
   public RGBColor pathShade(ShadeRec sr)
   {
      return sr.color;
   }
}
