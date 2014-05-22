package com.personal.parallelraytracer.drawing.reflection;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.sampling.Sampler;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;
import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Vector;

/**
 * Based off of Ray Tracing from the Ground up
 *
 */
public abstract class BRDF
{
   protected Sampler sampler;
   protected Normal normal;

   public Sampler getSampler()
   {
      return sampler;
   }

   public void setSampler(Sampler sampler)
   {
      this.sampler = sampler;
   }

   public Normal getNormal()
   {
      return normal;
   }

   public void setNormal(Normal normal)
   {
      this.normal = normal;
   }

   abstract public RGBColor f(ShadeRec sr, Vector wi, Vector wo);

   abstract public RGBColor sampleF(ShadeRec sr, Vector wi, Vector wo);

   abstract public RGBColor rho(ShadeRec sr, Vector ro);
}
