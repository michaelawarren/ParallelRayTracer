package com.personal.parallelraytracer.drawing.cameras;

import com.personal.parallelraytracer.cluster.RayRunable;
import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.ViewPlane;
import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import com.personal.parallelraytracer.math.Vector;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class PinHoleParallel extends Camera
{
   private final double d;
   private final double zoom;
   private int numThreads;
   private ExecutorService threadPool;

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
   public PinHoleParallel(double d, double zoom, Point eye, Point lookat,
       Vector up, double exposureTime, String fileName, int numThreads)
   {
      super(eye, lookat, up, exposureTime);
      this.d = d;
      this.zoom = zoom;
      this.numThreads = numThreads;
      this.fileName = fileName;
   }

   public PinHoleParallel()
   {
      d = 0;
      zoom = 0;
   }

   @Override
   public void renderScene(World world)
   {
      ViewPlane vp = new ViewPlane(world.vp);
      vp.setPixelSize(vp.getPixelSize() / zoom);

      // samples need to be generated before threads start else we could get a null pointer.
      vp.getSampler().generateSamples();
      final ViewPlane viewPlane = new ViewPlane(vp);
      final World w = world;

      openWindow(vp.getWidth(), vp.getHeight());
      threadPool = Executors.newFixedThreadPool(numThreads);
      List<Future> futures = new ArrayList<>();

      for (int r = 0; r < vp.getHeight(); r++)
      {
         for (int c = 0; c < vp.getWidth(); c++)
         {
            futures.add(threadPool.submit(new RayRunable(r, c)
            {
               /**
                * alrogirthm credited to Ray Tracing from the ground up
                * by Kevin Suffern
                * 
                */
               @Override
               public void run()
               {
                  try
                  {
                     int depth = 0;
                     Vector3D L = RGBColor.BLACK;

                     for (int j = 0; j < viewPlane.getNumSamples(); j++)
                     {
                        Vector2D sp = viewPlane.getSampler().sampleUnitSquare();
                        Vector2D pp = new Vector2D(
                            viewPlane.getPixelSize() * (c - 0.5d * viewPlane
                            .getWidth() + sp
                            .getX()),
                            viewPlane.getPixelSize() * (r - 0.5d * viewPlane
                            .getHeight() + sp
                            .getY()));
                        Ray ray = new Ray(rayDirection(pp), eye);
                        L = L.add(w.tracer.trayRay(ray, depth));
                     }

                     L = L.scalarMultiply(1.0d / viewPlane.getNumSamples());
                     L = L.scalarMultiply(exposureTime);
                     displayPixel(r, c, new RGBColor(L));
                  }
                  catch (NullPointerException ex)
                  {
                     ex.printStackTrace();
                  }
               }
            }));
         }
      }
      for (Future future : futures)
      {
         try
         {
            future.get();
         }
         catch (InterruptedException | ExecutionException ex)
         {
            ex.printStackTrace();
         }
      }
      threadPool.shutdown();
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
      return numThreads + " core 1 comp";
   }
}
