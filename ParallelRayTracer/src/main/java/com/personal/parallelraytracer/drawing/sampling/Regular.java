package com.personal.parallelraytracer.drawing.sampling;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Regular extends Sampler
{
   public Regular(int numSamples)
   {
      super(numSamples);
   }

   @Override
   public void generateSamples()
   {
      int n = (int) Math.sqrt(numSamples);

      for (int p = 0; p < numSets; p++)
      {
         for (int j = 0; j < n; j++)
         {
            for (int k = 0; k < n; k++)
            {
               Vector2D sp = new Vector2D(k + 0.5, j + 0.5);
               samples.add(sp);
            }
         }
      }
   }
}
