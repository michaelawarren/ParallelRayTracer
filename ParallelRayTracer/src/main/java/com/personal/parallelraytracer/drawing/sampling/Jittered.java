package com.personal.parallelraytracer.drawing.sampling;

import java.util.List;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Jittered extends Sampler
{
   public Jittered(int numSamples)
   {
      super(numSamples);
   }

   public Jittered()
   {
      super();
   }

   public Jittered(int numSamples, int numSets, List<Vector2D> samples,
       List<Integer> shuffledIndices, int jump)
   {
      super(numSamples, numSets, samples, shuffledIndices, jump);
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
               Vector2D sp = new Vector2D((k + Math.random()) / n, (j + Math
                   .random()) / n);
               samples.add(sp);
            }
         }
      }
   }
}
