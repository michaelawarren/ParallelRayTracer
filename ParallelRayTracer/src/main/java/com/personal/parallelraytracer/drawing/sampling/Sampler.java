package com.personal.parallelraytracer.drawing.sampling;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Based off of Ray Tracing from the Ground up
 *
 */
public abstract class Sampler
{
   protected int numSamples;
   protected int numSets;
   protected List<Vector2D> samples;
   protected List<Integer> shuffledIndices;
   protected long count; // hopefully not to big
   int jump;

   public Sampler()
   {
      numSamples = 0;
      numSets = 0;
      samples = new ArrayList<>();
      shuffledIndices = new ArrayList<>();
      count = 0;
      jump = 0;
   }

   public Sampler(int numSamples)
   {
      this.numSamples = numSamples;
      samples = new ArrayList<>();
      shuffledIndices = new ArrayList<>();
      this.numSets = 1;
   }

   public Sampler(int numSamples, int numSets, List<Vector2D> samples,
       List<Integer> shuffledIndices, int jump)
   {
      this.numSamples = numSamples;
      this.numSets = numSets;
      this.samples = samples;
      this.shuffledIndices = shuffledIndices;
      this.count = 0;
      this.jump = jump;
   }

   abstract public void generateSamples();

   public void setupShuffledIndicies()
   {

   }

   public void shuffleSamples()
   {

   }

   public Vector2D sampleUnitSquare()
   {
      if (count < 0)
      {
         count = 0;
      }
      if (samples.isEmpty())
      {
         generateSamples();
      }
      return samples.get((int) (count++ % (numSamples * numSets)));
   }

   public int getNumSamples()
   {
      return numSamples;
   }

   public void setNumSamples(int numSamples)
   {
      this.numSamples = numSamples;
   }

   public int getNumSets()
   {
      return numSets;
   }

   public void setNumSets(int numSets)
   {
      this.numSets = numSets;
   }

   public List<Vector2D> getSamples()
   {
      return samples;
   }

   public void setSamples(List<Vector2D> samples)
   {
      this.samples = samples;
   }

   public List<Integer> getShuffledIndices()
   {
      return shuffledIndices;
   }

   public void setShuffledIndices(List<Integer> shuffledIndices)
   {
      this.shuffledIndices = shuffledIndices;
   }

   public long getCount()
   {
      return count;
   }

   public int getJump()
   {
      return jump;
   }

   public void setJump(int jump)
   {
      this.jump = jump;
   }
}
