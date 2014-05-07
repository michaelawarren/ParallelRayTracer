package com.personal.parallelraytracer.drawing.cameras;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Vector;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Camera
{
   protected Point eye;
   protected Point lookat;
   protected Vector up;
   protected Vector u;
   protected Vector v;
   protected Vector w;
   double exposureTime;
   protected BufferedImage image;
   protected String fileName;

   public Camera(Point eye, Point lookat, Vector up, double exposureTime)
   {
      this.eye = eye;
      this.lookat = lookat;
      this.up = up;
      computeUvw();
      this.exposureTime = exposureTime;
   }

   public Camera()
   {
      this.eye = null;
      this.lookat = null;
      this.up = null;
      this.u = null;
      this.v = null;
      this.w = null;
      this.exposureTime = 0;
      this.image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
   }

   public final void computeUvw()
   {
      w = new Vector(eye.subtract(lookat).normalize());
      u = new Vector(up.crossProduct(w).normalize());
      v = new Vector(w.crossProduct(u));
   }

   public String getFileName()
   {
      return fileName;
   }

   public void setFileName(String fileName)
   {
      this.fileName = fileName;
   }

   public Point getEye()
   {
      return eye;
   }

   public void setEye(Point eye)
   {
      this.eye = eye;
   }

   public Point getLookat()
   {
      return lookat;
   }

   public void setLookat(Point lookat)
   {
      this.lookat = lookat;
   }

   public Vector getUp()
   {
      return up;
   }

   public void setUp(Vector up)
   {
      this.up = up;
   }

   public Vector getU()
   {
      return u;
   }

   public void setU(Vector u)
   {
      this.u = u;
   }

   public Vector getV()
   {
      return v;
   }

   public void setV(Vector v)
   {
      this.v = v;
   }

   public Vector getW()
   {
      return w;
   }

   public void setW(Vector w)
   {
      this.w = w;
   }

   public double getExposureTime()
   {
      return exposureTime;
   }

   public void setExposureTime(double exposureTime)
   {
      this.exposureTime = exposureTime;
   }

   protected void openWindow(int width, int height)
   {
      image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
   }

   public void displayPixel(int r, int c, RGBColor pixelColor)
   {
      image.setRGB(c, r, pixelColor.getColor().getRGB());
   }

   public void writeImageToFile(String fileName)
   {
      try
      {
         // retrieve image
         File outputfile = new File(fileName);
         ImageIO.write(image, "png", outputfile);
      }
      catch (IOException e)
      {
         System.out.println(e.getMessage());
      }
   }

   public abstract void renderScene(World w);
}
