package com.personal.parallelraytracer.drawing.materials;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.light.Light;
import com.personal.parallelraytracer.drawing.reflection.GlossySpecular;
import com.personal.parallelraytracer.drawing.reflection.Lambertian;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;
import com.personal.parallelraytracer.math.Ray;
import com.personal.parallelraytracer.math.Vector;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Phong extends Material
{
   private Lambertian ambientBRDF;
   private Lambertian diffuseBRDF;
   private GlossySpecular specularBRDF;

   public Phong(Lambertian ambientBRDF, Lambertian diffuseBRDF,
       GlossySpecular specularBRDF)
   {
      this.ambientBRDF = ambientBRDF;
      this.diffuseBRDF = diffuseBRDF;
      this.specularBRDF = specularBRDF;
   }

   public Phong()
   {
      this.ambientBRDF = new Lambertian();
      this.diffuseBRDF = new Lambertian();
      this.specularBRDF = new GlossySpecular();
   }

   public void setKa(double ka)
   {
      ambientBRDF.setKd(ka);
   }

   public void setKd(double kd)
   {
      diffuseBRDF.setKd(kd);
   }

   public void setKs(double ks)
   {
      specularBRDF.setKs(ks);
   }

   public void setCd(RGBColor cd)
   {
      specularBRDF.setCs(cd);
      ambientBRDF.setCd(cd);
      diffuseBRDF.setCd(cd);
   }
   
   public void setExp(double exp)
   {
      specularBRDF.setExp(exp);
   }
   
   @Override
   public RGBColor shade(ShadeRec sr)
   {
      Vector wo = new Vector(sr.ray.direction.negate());
      Vector3D L = ambientBRDF.rho(sr, wo)
          .componmentMultiply(sr.world.ambient.L(sr));

      for (Light light : sr.world.lights)
      {
         Vector wi = light.getDirection(sr);
         double ndotwi = sr.normal.dotProduct(wi);

         if (ndotwi > 0.0)
         {
            boolean inShadow = false;

            if (light.castsShadows())
            {
               Ray shadowRay = new Ray(wi, sr.hitPoint);
               inShadow = light.inShadow(shadowRay, sr);
            }

            if (!inShadow)
            {
               RGBColor dAddS = new RGBColor(diffuseBRDF.f(sr, wi, wo)
                   .add(specularBRDF.f(sr, wi, wo)));
               L = L.add(dAddS.componmentMultiply(light.L(sr)
                   .scalarMultiply(ndotwi)));
            }
         }
      }

      return new RGBColor(L);
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
