package com.personal.parallelraytracer.drawing.cameras;

import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Vector;
import org.json.JSONArray;
import static org.junit.Assert.*;
import org.junit.Test;

public class PinHoleWorkerTest
{
   public PinHoleWorkerTest()
   {
   }

   @Test
   public void testRenderScene_World_int()
   {
      World world = new World();
      world.setUpTestScene1();
      PinHoleWorker phw = new PinHoleWorker(850.0d, 1, new Point(100, 100, 100),
          new Point(-5, 0, 0),
          new Vector(1, 1, 0), 1, "Single.png",
          4);
      final int height = world.vp.getHeight();
      for (int i = 0; i < height; i++)
      {
         JSONArray row = new JSONArray();
         row = phw.renderScene(world, i, row);
         String string = row.toString();
         assertFalse(string.contains("null"));
      }
   }

   @Test
   public void testRenderScene_World()
   {
   }

   @Test
   public void testToString()
   {
   }

   @Test
   public void testSetNumThreads()
   {
   }
}
