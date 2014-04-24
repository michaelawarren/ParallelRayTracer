package com.personal.parallelraytracer.math;

import org.junit.Test;
import static org.junit.Assert.*;

public class PointTest {

    public PointTest() {
    }

   @Test
   public void testMidPoint()
   {
      Point pt1 = new Point (1, 2, 3);
      Point pt2 = new Point (3, 2, 1);
      Point expected = new Point(2, 2, 2);
      assertEquals(expected, pt1.midPoint(pt2));
   }

}