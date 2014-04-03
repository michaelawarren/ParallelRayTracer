package com.personal.parallelraytracer.drawing;

public class ViewWindow
{
   // numRays = width x height
   private final int width;
   private final int height;
   private final double pixelSize;
   private final double gamma;
   private final double invertGamma;

   public ViewWindow(int width, int height, double pixelSize, double gamma)
   {
      this.width = width;
      this.height = height;
      this.pixelSize = pixelSize;
      this.gamma = gamma;
      this.invertGamma = 1 / gamma;
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
      return pixelSize * (column - width / 2 + 0.5d);
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
      return pixelSize * (row - height / 2 + 0.5d);
   }
}
