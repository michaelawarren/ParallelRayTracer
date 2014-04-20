package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import static org.junit.Assert.*;
import org.junit.Test;

public class SurfaceTest
{
   public SurfaceTest()
   {
   }

   @Test
   public void testBuildNormal()
   {
      Point p = new Point(1, -2, 0);
      Point q = new Point(3, 1, 4);
      Point r = new Point(0, -1, 2);
      Normal normal = new Normal(2, -8, 5);
      assertEquals(normal, Surface.buildNormal(p, q, r));
   }
}
