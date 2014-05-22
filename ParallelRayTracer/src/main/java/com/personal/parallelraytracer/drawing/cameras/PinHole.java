package com.personal.parallelraytracer.drawing.cameras;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.ViewPlane;
import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import com.personal.parallelraytracer.math.Vector;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class PinHole extends Camera
{
   private double d;
   private double zoom;

   /**
    * 
    * @param d view plane distance
    * @param zoom zoom factor
    * @param eye
    * @param lookat
    * @param up
    * @param exposureTime 
    */
   public PinHole(double d, double zoom, Point eye, Point lookat, Vector up,
       double exposureTime, String fileName)
   {
      super(eye, lookat, up, exposureTime);
      this.d = d;
      this.zoom = zoom;
      this.fileName = fileName;
   }

   public PinHole()
   {
      d = 0;
      zoom = 0;
   }

   /**
    * Algorithm comes from Ray Tracing from the Ground Up. by Kevin Suffern
    * 
    * @param w 
    */
   @Override
   public void renderScene(World w)
   {
      Vector3D L = null;
      ViewPlane vp = new ViewPlane(w.vp);
      Ray ray = new Ray();
      int depth = 0;
      Vector2D sp;
      Vector2D pp;

      openWindow(vp.getWidth(), vp.getHeight());
      vp.setPixelSize(vp.getPixelSize() / zoom);
      ray.setOrigin(eye);

      for (int r = 0; r < vp.getHeight(); r++)
      {
         for (int c = 0; c < vp.getWidth(); c++)
         {
            L = RGBColor.BLACK;
            
            for (int j = 0; j < vp.getNumSamples(); j++)
            {
               sp = vp.getSampler().sampleUnitSquare();
               pp = new Vector2D(
                   vp.getPixelSize() * (c - 0.5d * vp.getWidth() + sp.getX()),
                   vp.getPixelSize() * (r - 0.5d * vp.getHeight() + sp.getY()));
               ray.setDirection(rayDirection(pp));
               L = L.add(w.tracer.trayRay(ray, depth));
            }
            
            L = L.scalarMultiply(1.0d / vp.getNumSamples());
            L = L.scalarMultiply(exposureTime);
            displayPixel(r, c, new RGBColor(L));
         }
      }
      writeImageToFile(fileName);
   }

   private Vector rayDirection(Vector2D p)
   {
      return new Vector(u.scalarMultiply(p.getX()).add(v
          .scalarMultiply(p.getY())).subtract(w.scalarMultiply(d)).normalize());
   }

   @Override
   public String toString()
   {
      return "1 core 1 comp";
   }
}
