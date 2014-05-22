package com.personal.parallelraytracer.drawing.materials;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.reflection.PerfectSpecular;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;
import com.personal.parallelraytracer.math.Ray;
import com.personal.parallelraytracer.math.Vector;

/**
 * Based off of Ray Tracing from the Ground up
 *
 */
public class Reflective extends Phong
{
   private PerfectSpecular reflectiveBRDF;

   public Reflective()
   {
      super();
      reflectiveBRDF = new PerfectSpecular();
   }
   
   public void setKp(double kp)
   {
      reflectiveBRDF.setKr(kp);
   }
   
   public void setCr(RGBColor color)
   {
      reflectiveBRDF.setCr(color);
   }
   
   public void setKr(double kr)
   {
      reflectiveBRDF.setKr(kr);
   }
   
   @Override
   public RGBColor shade(ShadeRec sr)
   {
      RGBColor L = super.shade(sr);
      
      Vector wo = new Vector(sr.ray.direction.negate());
      Vector wi = reflectiveBRDF.buildWi(sr, wo);
      RGBColor fr = reflectiveBRDF.sampleF(sr, wi, wo);
      Ray reflectedRay = new Ray(wi, sr.hitPoint);
      
      L = new RGBColor(L.add(fr.componmentMultiply(sr.world.tracer.trayRay(reflectedRay,
          sr.depth + 1).scalarMultiply(sr.normal.dotProduct(wi)))));
      
      return L;
   }
}
