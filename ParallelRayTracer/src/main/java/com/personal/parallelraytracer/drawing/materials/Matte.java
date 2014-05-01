package com.personal.parallelraytracer.drawing.materials;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.light.Light;
import com.personal.parallelraytracer.drawing.reflection.Lambertian;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;
import com.personal.parallelraytracer.math.Vector;

public class Matte extends Material
{
   private Lambertian ambientBRDF;
   private Lambertian diffuseBRDF;

   public Matte()
   {
      ambientBRDF = new Lambertian();
      diffuseBRDF = new Lambertian();
   }

   public void setKa(double ka)
   {
      ambientBRDF.setKd(ka);
   }

   public void setKd(double kd)
   {
      diffuseBRDF.setKd(kd);
   }

   public void setCd(RGBColor color)
   {
      diffuseBRDF.setCd(color);
      ambientBRDF.setCd(color);
      this.color = color;
   }

   @Override
   public RGBColor shade(ShadeRec sr)
   {
      Vector wo = new Vector(sr.ray.direction.negate());
      RGBColor l = ambientBRDF.rho(sr, wo).componmentMultiply(sr.world.ambient
          .L(sr));
      for (Light light : sr.world.lights)
      {
         Vector wi = light.getDirection(sr);
         if (sr.normal == null || wi == null)
            return RGBColor.BLACK;
         double nDotwWi = sr.normal.dotProduct(wi);

         if (nDotwWi > 0.0d)
         {
            l = new RGBColor(l.add(diffuseBRDF.f(sr, wo, wi).componmentMultiply(
                light.L(sr).scalarMultiply(nDotwWi))));
         }
      }
      return l;
   }

   @Override
   public RGBColor areLightShade(ShadeRec sr)
   {
      //TODO: write areLightShade
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public RGBColor pathShade(ShadeRec sr)
   {
      //TODO: write pathShade
      throw new UnsupportedOperationException("Not supported yet.");
   }
}
