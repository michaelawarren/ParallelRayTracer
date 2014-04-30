package com.personal.parallelraytracer.drawing.cameras;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.ViewPlane;
import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.drawing.tracers.Tracer;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import com.personal.parallelraytracer.math.Vector;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Camera2 extends Camera
{
   private final ViewPlane viewWindow;
   private final Tracer tracer;

   public Camera2(ViewPlane viewWindow, Tracer tracer)
   {
      this.viewWindow = viewWindow;
      this.tracer = tracer;
   }

   /**
    * Algorithm from Raytracing from the ground up
    *
    * @param w
    */
   @Override
   public void renderScene(World w)
   {
      RGBColor pixelColor;
      Ray ray;
      double zw = 100.0d;
      double x;
      double y;
      int n = (int) Math.sqrt(viewWindow.getNumSamples());

      openWindow(viewWindow.getWidth(), viewWindow.getHeight());
      ray = new Ray(new Vector(0, 0, -1), new Point(0, 0, 0));
      
   // this is the part that can be paralized.
      for (int r = 0; r < viewWindow.getHeight(); r++)
      {
         for (int c = 0; c < viewWindow.getWidth(); c++)
         {
            pixelColor = RGBColor.BLACK;
            for (int q = 0; q < n; q++)
            {
               x = -viewWindow.getX(c);//, n, q);
               y = -viewWindow.getY(r);//, n, q);
               ray.setOrigin(new Point(x, y, zw));

               pixelColor = new RGBColor(tracer.trayRay(ray, 1).add(pixelColor));
            }
            displayPixel(r, c, pixelColor);
         }
      }

      try
      {
         // retrieve image
         File outputfile = new File("image.png");
         ImageIO.write(image, "png", outputfile);
      }
      catch (IOException e)
      {
         System.out.println(e.getMessage());
      }
   }
}
