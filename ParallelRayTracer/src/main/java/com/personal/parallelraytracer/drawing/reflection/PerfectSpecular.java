package com.personal.parallelraytracer.drawing.reflection;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;
import com.personal.parallelraytracer.math.Vector;

public class PerfectSpecular extends BRDF
{
   double kr;
   RGBColor cr;
   
   @Override
   public RGBColor f(ShadeRec sr, Vector wi, Vector wo)
   {
      //TODO: write f
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public RGBColor sampleF(ShadeRec sr, Vector wi, Vector wo)
   {
      //TODO: write sampleF
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public RGBColor rho(ShadeRec sr, Vector ro)
   {
      //TODO: write rho
      throw new UnsupportedOperationException("Not supported yet.");
   }

}
