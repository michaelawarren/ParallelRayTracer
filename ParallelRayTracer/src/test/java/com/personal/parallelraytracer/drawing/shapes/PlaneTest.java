package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import com.personal.parallelraytracer.math.Vector;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlaneTest
{
   Plane testPlane;

   public PlaneTest()
   {
      testPlane = new Plane(true, true, new Point(2, 2, 2), new Normal(3, 3, 3),
          null);
   }

   @Test
   public void testHitSingleInterSectionHit()
   {
      final Ray testRay = new Ray(new Vector(3, 3, 3), new Point(0, 0, 0));
      double tMin = testPlane.hitPoint(testRay);
      assertEquals(0.6666d, tMin, .001d);
   }

   @Test
   public void testHitSingleInterSectionHitOrign()
   {
      Plane testPlane2 = new Plane(true, true, new Point(0, 0, 0),
          new Normal(3, 3, 3), null);
      final Ray testRay = new Ray(new Vector(3, 3, 3), new Point(0, 0, 0));
      double tMin = testPlane2.hitPoint(testRay);
      assertEquals(Double.NaN, tMin, .001d);
   }

   @Test
   public void testHitNoIntersection()
   {
      final Point orgin = new Point(0, 0, 0);
      final Vector direction = new Vector(-3, 4, -1);

      double tMin = testPlane.hitPoint(new Ray(direction, orgin));

      assertEquals(Double.NaN, tMin, .0000001d);
   }

   @Test
   public void testHitRayIsOnPlane1()
   {
      final Point orgin = new Point(3, 1.5, 1.5);
      Vector vector = new Vector(-3, 3, 0);

      // assert assumption that vector is parallel to the plane
      assertEquals(0d, vector.dotProduct(testPlane.normal), 0);

      double tMin = testPlane.hitPoint(new Ray(vector, orgin));
      assertEquals(Double.NaN, tMin, .0000001d);
   }

   @Test
   public void testContainsNo()
   {
      final Point orgin = new Point(0, 0, 2);
      assertFalse(testPlane.contains(orgin));
   }

   @Test
   public void testContainsYes()
   {
      final Point orgin = new Point(0, 0, 0);
      assertFalse(testPlane.contains(orgin));
   }
}
