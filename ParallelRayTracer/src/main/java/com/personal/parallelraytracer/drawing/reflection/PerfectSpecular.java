package com.personal.parallelraytracer.drawing.reflection;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;
import com.personal.parallelraytracer.math.Vector;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class PerfectSpecular extends BRDF
{
   double kr;
   RGBColor cr;

   @Override
   public RGBColor f(ShadeRec sr, Vector wi, Vector wo)
   {
      return RGBColor.BLACK;
   }

   @Override
   public RGBColor sampleF(ShadeRec sr, Vector wi, Vector wo)
   {
      return new RGBColor(cr.scalarMultiply(kr).scalarMultiply(1 / sr.normal
          .dotProduct(wi)));
   }

   public Vector buildWi(ShadeRec sr, Vector wo)
   {
      double nDotWo = sr.normal.dotProduct(wo);
      Vector3D wi = wo.negate();
      wi = wi.add(sr.normal.scalarMultiply(2.0d).scalarMultiply(nDotWo));
      return new Vector(wi);
   }

   @Override
   public RGBColor rho(ShadeRec sr, Vector ro)
   {
      return RGBColor.BLACK;
   }

   public double getKr()
   {
      return kr;
   }

   public void setKr(double kr)
   {
      this.kr = kr;
   }

   public RGBColor getCr()
   {
      return cr;
   }

   public void setCr(RGBColor cr)
   {
      this.cr = cr;
   }
}
