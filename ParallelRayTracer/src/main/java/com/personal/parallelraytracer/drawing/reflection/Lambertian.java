package com.personal.parallelraytracer.drawing.reflection;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.sampling.Sampler;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;
import com.personal.parallelraytracer.math.MathConst;
import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Vector;

/**
 * Based off of Ray Tracing from the Ground up
 * 
 */
public class Lambertian extends BRDF
{
   double kd;
   RGBColor cd;

   public Lambertian(double kd, RGBColor cd, Normal normal, Sampler sampler)
   {
      this.kd = kd;
      this.cd = cd;
      this.normal = normal;
      this.sampler = sampler;
   }

   public double getKd()
   {
      return kd;
   }

   public RGBColor getCd()
   {
      return cd;
   }
   
   @Override
   public RGBColor f(ShadeRec sr, Vector wi, Vector wo)
   {
      return new RGBColor(cd.scalarMultiply(kd).scalarMultiply(MathConst.INV_PIE));
   }

   /**
    * 
    * @param sr
    * @param wi changeable
    * @param wo
    * @return 
    */
   @Override
   public RGBColor sampleF(ShadeRec sr, Vector wi, Vector wo)
   {
      //TODO: write sampleF
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public RGBColor rho(ShadeRec sr, Vector ro)
   {
      return new RGBColor(cd.scalarMultiply(kd));
   }

}
