package com.personal.parallelraytracer.math;

import static org.junit.Assert.*;
import org.junit.Test;

public class RayTest
{
   @Test
   public void testFindPoint1()
   {
      Ray testRay = new Ray(new Vector(1, 1, 1), new Point(0, 0, 0));
      Point expected = new Point(1, 1, 1);
      final Point testPoint = testRay.findPoint(1.0d);
      assertTrue("Vectors " + testPoint.toString() + "and " + expected
          .toString() + " are not equal", expected.equals(testPoint));
   }

   @Test
   public void testFindPoint2()
   {
      Ray testRay = new Ray(new Vector(1, 1, 1), new Point(0, 0, 0));
      Point expectedPoint = new Point(-1, -1, -1);
      final Point testPoint = testRay.findPoint(-1.0d);
      assertTrue("Vectors " + testPoint.toString() + "and " + expectedPoint
          .toString() + " are not equal", expectedPoint.equals(testPoint));
   }

   @Test
   public void testFindPoint3()
   {
      Ray testRay = new Ray(new Vector(1, 1, 1), new Point(0, 0, 0));
      final double oneThird = 1.0d / 3.0d;
      Point expectedPoint = new Point(oneThird, oneThird, oneThird);
      final Point testPoint = testRay.findPoint(oneThird);

      assertTrue("Vectors " + testPoint.toString() + "and " + expectedPoint
          .toString() + " are not equal", expectedPoint.equals(testPoint));
   }
   
   
   @Test
   public void testFindPoint4()
   {
      Ray testRay = new Ray(new Vector(1.0d, 2.0d, 3.0d), new Point(0, 0, 0));
      final double tMin = 1.0d;
      Point expectedPoint = new Point(1.0d, 2.0d, 3.0d);
      final Point testPoint = testRay.findPoint(tMin);

      assertTrue("Vectors " + testPoint.toString() + "and " + expectedPoint
          .toString() + " are not equal", expectedPoint.equals(testPoint));
   }
}
