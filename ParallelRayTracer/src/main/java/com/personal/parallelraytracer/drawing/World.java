package com.personal.parallelraytracer.drawing;

import com.personal.parallelraytracer.drawing.shapes.GeometricShape;
import com.personal.parallelraytracer.drawing.shapes.Sphere;
import com.personal.parallelraytracer.drawing.tracers.SingleSphere;
import com.personal.parallelraytracer.drawing.tracers.Tracer;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import com.personal.parallelraytracer.math.Vector;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

public class World
{
   public List<GeometricShape> shapes;
   public final Sphere sphere;
   public final Color backgroundColor;
   public final ViewWindow viewWindow;
   public final Tracer tracer;
   private BufferedImage image;

   public World()
   {
      this.viewWindow = new ViewWindow(200, 200, 1.0, 1.0);
      this.backgroundColor = Color.BLACK;
      this.tracer = new SingleSphere(this);
      this.sphere
          = new Sphere(true, false, Color.RED, new Point(0, 0, 0), 85.0d);
   }

   public void renderScene()
   {
      Color pixelColor;
      Ray ray;
      double zw = 100.0d;
      double x;
      double y;

      openWindow(viewWindow.getWidth(), viewWindow.getHeight());
      ray = new Ray(new Vector(0, 0, -1), new Point(0, 0, 0));

      for (int r = 0; r < viewWindow.getHeight(); r++)
      {
         for (int c = 0; c < viewWindow.getWidth(); c++)
         {
            x = viewWindow.getX(c);
            y = viewWindow.getY(r);
            ray.setOrigin(new Point(x, y, zw));
            pixelColor = tracer.trayRay(ray);
            displayPixel(r, c, pixelColor);
         }
      }
      try
      {
         // retrieve image
         File outputfile = new File("C:\\Users\\Michael\\Desktop\\image.png");
         ImageIO.write(image, "png", outputfile);
      }
      catch (IOException e)
      {
         System.out.println(e.getMessage());
      }
   }

   private void openWindow(int width, int height)
   {
      image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
   }

   private void displayPixel(int r, int c, Color pixelColor)
   {
      image.setRGB(c, r, pixelColor.getRGB());
   }
}
