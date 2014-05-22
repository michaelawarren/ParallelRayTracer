package com.personal.parallelraytracer.drawing.cameras;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.ViewPlane;
import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import com.personal.parallelraytracer.math.Vector;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.json.JSONArray;

public class PinHoleWorker extends Camera
{
   private final double d;
   private final double zoom;
   private int numThreads;
   private int row;

   /**
    *
    * @param d view plane distance
    * @param zoom zoom factor
    * @param eye
    * @param lookat
    * @param up
    * @param exposureTime
    * @param numThreads
    */
   public PinHoleWorker(double d, double zoom, Point eye, Point lookat,
       Vector up, double exposureTime, String fileName, int numThreads)
   {
      super(eye, lookat, up, exposureTime);
      this.d = d;
      this.zoom = zoom;
      this.numThreads = numThreads;
      this.fileName = fileName;
   }

   public PinHoleWorker()
   {
      d = 0;
      zoom = 0;
   }

   /**
    * Algorithm for rendering scene is from Ray Tracing From the Ground Up 
    * by Kevin Suffern
    * 
    * @param world
    * @param row
    * @param pixels
    * @return 
    */
   public JSONArray renderScene(World world, int row, JSONArray pixels)
   {
      ViewPlane vp = new ViewPlane(world.vp);
      vp.setPixelSize(vp.getPixelSize() / zoom);
      final int width = world.vp.getWidth();

      for (int c = 0; c < width; c++)
      {
         try
         {
            int depth = 0;
            Vector3D L = RGBColor.BLACK;

            for (int j = 0; j < vp.getNumSamples(); j++)
            {
               Vector2D sp = vp.getSampler()
                   .sampleUnitSquare();
               Vector2D pp = new Vector2D(
                   vp.getPixelSize() * (c - 0.5d * vp.getWidth() + sp.getX()),
                   vp.getPixelSize() * (row - 0.5d * vp.getHeight() + sp.getY()));
               Ray ray = new Ray(rayDirection(pp), eye);
               L = L.add(world.tracer.trayRay(ray, depth));
            }

            L = L.scalarMultiply(1.0d / vp.getNumSamples());
            L = L.scalarMultiply(exposureTime);

            pixels.put(new Pixel(row, c, new RGBColor(L).getColor().getRGB()));
         }
         catch (NullPointerException ex)
         {
            ex.printStackTrace();
         }
         catch (Exception ex)
         {
            ex.printStackTrace();
         }
      }

      if (pixels.length() < width)
      {
         throw new IllegalStateException("less pixels than there is really are.");
      }
      return pixels;
   }

   @Override
   public void renderScene(World world)
   {
      throw new UnsupportedOperationException("Method: renderScene not implemented");
      /*
      final JSONArray pixels = new JSONArray();
      ViewPlane vp = new ViewPlane(world.vp);
      vp.setPixelSize(vp.getPixelSize() / zoom);

      for (int c = 0; c < world.vp.getWidth(); c++)
      {
         try
         {
            int depth = 0;
            Vector3D L = RGBColor.BLACK;

            for (int j = 0; j < vp.getNumSamples(); j++)
            {
               Vector2D sp = vp.getSampler()
                   .sampleUnitSquare();
               Vector2D pp = new Vector2D(
                   vp.getPixelSize() * (c - 0.5d * vp
                   .getWidth() + sp
                   .getX()),
                   vp.getPixelSize() * (r - 0.5d * vp
                   .getHeight() + sp
                   .getY()));
               Ray ray = new Ray(rayDirection(pp), eye);
               L = L.add(world.tracer.trayRay(ray, depth));
            }

            L = L.scalarMultiply(1.0d / vp.getNumSamples());
            L = L.scalarMultiply(exposureTime);

            pixels.put(new Pixel(r, c, new RGBColor(L).getColor().getRGB()));
         }
         catch (NullPointerException ex)
         {
            ex.printStackTrace();
         }
      }*/
   }

   private Vector rayDirection(Vector2D p)
   {
      return new Vector(u.scalarMultiply(p.getX()).add(v
          .scalarMultiply(p.getY())).subtract(w.scalarMultiply(d)).normalize());
   }

   @Override
   public String toString()
   {
      return numThreads + " core 1 comp";
   }

   public void setNumThreads(int numThreads)
   {
      this.numThreads = numThreads;
   }
}
