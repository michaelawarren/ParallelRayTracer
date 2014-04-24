package com.personal.parallelraytracer.drawing;

public class ViewWindow
{
   // numRays = width x height
   private final int width; // hres
   private final int height; //vres
   private final double pixelSize; //s
   private final double gamma; 
   private final double invertGamma;
   private final int numSamples;

   public ViewWindow(int width, int height, double pixelSize, double gamma, int numSamples)
   {
      this.width = width;
      this.height = height;
      this.pixelSize = pixelSize;
      this.gamma = gamma;
      this.invertGamma = 1 / gamma;
      this.numSamples = numSamples;
   }

   public int getNumSamples()
   {
      return numSamples;
   }

   public int getWidth()
   {
      return width;
   }

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
