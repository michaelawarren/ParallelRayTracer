package com.personal.parallelraytracer.drawing.reflection;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;
import com.personal.parallelraytracer.math.Vector;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class GlossySpecular extends BRDF
{
   double ks;
   RGBColor cs;
   double exp;

   @Override
   public RGBColor f(ShadeRec sr, Vector wi, Vector wo)
   {
      RGBColor L = RGBColor.BLACK;

      double ndotwi = sr.normal.dotProduct(wi);
      Vector3D r = wi.negate().add(sr.normal.scalarMultiply(2.0d)
          .scalarMultiply(ndotwi));
      double rdotwo = r.dotProduct(wo);

      if (rdotwo > 0.0d)
      {
         double l = ks * Math.pow(rdotwo, exp);
         L = new RGBColor(l, l, l);
      }

      return L;
   }

   public void setKs(double ks)
   {
      this.ks = ks;
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
      return RGBColor.BLACK;
   }
}
