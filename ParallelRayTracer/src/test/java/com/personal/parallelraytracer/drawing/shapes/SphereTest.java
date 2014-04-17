package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import com.personal.parallelraytracer.math.Vector;
import org.junit.Test;
import static org.junit.Assert.*;

public class SphereTest
{
   public final Sphere testSphere;

   public SphereTest()
   {
      testSphere = new Sphere(true, true, null, new Point(2, 2, 0), 1);
   }

   @Test
   public void testHitPointMiss()
   {
      Point point = new Point(0, 4, 0);
      Vector vector = new Vector(1, 0, 0);
      Ray ray = new Ray(vector, point);
      assertEquals(true, Double.isNaN(testSphere.hitPoint(ray)));
   }

   @Test
   public void testHitPointTangent()
   {
      Point point = new Point(0, 3, 0);
      Vector vector = new Vector(1, 0, 0);
      Ray ray = new Ray(vector, point);
      final double t = testSphere.hitPoint(ray);
      assertEquals(2.0d, t, 0.00001d);
      assertTrue(!Double.isNaN(t));
   }

   @Test
   public void testHitPointTwoIntersections()
   {
      Point point = new Point(0, 2, 0);
      Vector vector = new Vector(1, 0, 0);
      Ray ray = new Ray(vector, point);
      final double t = testSphere.hitPoint(ray);
      assertEquals(1.0d, t, 0.00001d);
      assertTrue(!Double.isNaN(t));
   }

   @Test
   public void testHitPointOrginAfter()
   {
      Point point = new Point(4, 2, 0);
      Vector vector = new Vector(1, 0, 0);
      Ray ray = new Ray(vector, point);
      final double t = testSphere.hitPoint(ray);
      assertTrue(Double.isNaN(t));
   }

   @Test
   public void testHitPointOrginSecondEdge()
   {
      Point point = new Point(3, 2, 0);
      Vector vector = new Vector(1, 0, 0);
      Ray ray = new Ray(vector, point);
      assertEquals(true, Double.isNaN(testSphere.hitPoint(ray)));
   }

   @Test
   public void testHitPointOrginInside()
   {
      Point point = new Point(2, 2, 0);
      Vector vector = new Vector(1, 0, 0);
      Ray ray = new Ray(vector, point);
      final double t = testSphere.hitPoint(ray);
      assertEquals(1.0d, t, 0.00001d);
      assertTrue(Double.isNaN(t));
   }

   @Test
   public void testHitPointOrignFirstEdge()
   {
      Point point = new Point(1, 2, 0);
      Vector vector = new Vector(1, 0, 0);
      Ray ray = new Ray(vector, point);
      final double t = testSphere.hitPoint(ray);
      assertEquals(2.0d, t, 0.00001d);
      assertTrue(Double.isNaN(t));
   }

   @Test
   public void testContains()
   {
      fail();
   }
}
