package com.personal.parallelraytracer.drawing;

import com.personal.parallelraytracer.drawing.sampling.Jittered;
import com.personal.parallelraytracer.drawing.sampling.Sampler;
import com.personal.parallelraytracer.drawing.sampling.Regular;

public class ViewPlane
{
   // numRays = width x height
   private final int width; // hres
   private final int height; //vres
   private double pixelSize; //s
   private final double gamma;
   private final double invertGamma;
   private int numSamples;
   private Sampler sampler;

   public ViewPlane(ViewPlane viewPlane)
   {
      this.width = viewPlane.width;
      this.height = viewPlane.height;
      this.pixelSize = viewPlane.pixelSize;
      this.gamma = viewPlane.gamma;
      this.invertGamma = viewPlane.invertGamma;
      this.numSamples = viewPlane.numSamples;
      this.sampler = viewPlane.sampler;
   }

   public ViewPlane(int width, int height, double pixelSize, double gamma,
       int numSamples, Sampler sampler)
   {
      this.width = width;
      this.height = height;
      this.pixelSize = pixelSize;
      this.gamma = gamma;
      this.invertGamma = 1 / gamma;
      this.numSamples = numSamples;
      this.sampler = sampler;
   }


   public int getNumSamples()
   {
      return numSamples;
   }

   /**
    * 
    * @return hres
    */
   public int getWidth()
   {
      return width;
   }

   /**
    * 
    * @return vres
    */
   public int getHeight()
   {
      return height;
   }

   public double getPixelSize()
   {
      return pixelSize;
   }

   public double getGamma()
   {
      return gamma;
   }

   public double getInvertGamma()
   {
      return invertGamma;
   }

   public void setPixelSize(double pixelSize)
   {
      this.pixelSize = pixelSize;
   }

   public Sampler getSampler()
   {
      return sampler;
   }

   public void setSampler(Sampler sampler)
   {
      this.numSamples = sampler.getNumSamples();
      this.sampler = sampler;
   }
   
   public void setSamples(int n)
   {
      numSamples = n;
      
      if (numSamples > 1)
         sampler = new Jittered(numSamples);
      else 
         sampler = new Regular(1);
   }
   
   /**
    * Gives the x coordinate for a given column.
    *
    * @param column integer between 0 and width - 1
    * @return
    */
   public double getX(int column)
   {
      if (column >= width || column < 0)
      {
         throw new IllegalArgumentException(
             "column must be between 0 and width - 1");
      }
      return pixelSize * (column - 0.5 * (width - 1.0d));
   }

   /**
    * Gives the y coordinate for a given row.
    *
    * @param row integer between 0 and height - 1
    * @return
    */
   public double getY(int row)
   {
      if (row >= height || row < 0)
      {
         throw new IllegalArgumentException(
             "row must be between 0 and height - 1");
      }
      return pixelSize * (row - 0.5d * (height - 1.0d));
   }

   double getAX(int col, int n, int q)
   {
      return pixelSize * (col - 0.5d * (width) + (q + 0.5d) / n);
   }

   double getAY(int row, int n, int q)
   {
      return pixelSize * (row - 0.5d * (height) + (q + 0.5d) / n);
   }
}
