package com.personal.parallelraytracer.drawing.cameras;

public class PixelRunable extends RayRunable
{
   Pixel pixel;

   public PixelRunable(Pixel pixel)
   {
      super(pixel.r, pixel.c);
      this.pixel = pixel;
   }
   
   @Override
   public void run()
   {
      //TODO: write run
      throw new UnsupportedOperationException("Not supported yet.");
   }

}
