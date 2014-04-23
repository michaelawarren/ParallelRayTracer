package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import com.personal.parallelraytracer.math.Vector;
import static org.junit.Assert.*;
import org.junit.Test;

public class BoxTest
{
   final Box testBox;
   final Point min = new Point(1, 1, 1);
   final Point max = new Point(3, 2, 4);

   public BoxTest()
   {
      testBox = new Box(true, true, null, min, max);
   }

   @Test
   public void testHitPointCorner1()
   {
      double testValue = testBox.hitPoint(new Ray(new Vector(1, 1, 1),
          new Point(0, 0, 0)));
      assertEquals(1.00d, testValue, 0.001d);
   }

   @Test
   public void testHitPointCorner2()
   {
      double testValue = testBox.hitPoint(new Ray(new Vector(1, 1, 4),
          new Point(0, 0, 0)));
      assertEquals(1.00d, testValue, 0.001d);
   }

   @Test
   public void testHitPointCorner3()
   {
      double testValue = testBox.hitPoint(new Ray(new Vector(1, 2, 1),
          new Point(0, 0, 0)));
      assertEquals(1.00d, testValue, 0.001d);
   }

   @Test
   public void testHitPointCorner4()
   {
      double testValue = testBox.hitPoint(new Ray(new Vector(1, 2, 4),
          new Point(0, 0, 0)));
      assertEquals(1.00d, testValue, 0.001d);
   }

   @Test
   public void testHitPointCorner5()
   {
      double testValue = testBox.hitPoint(new Ray(new Vector(3, 1, 1),
          new Point(0, 0, 0)));
      assertEquals(1.00d, testValue, 0.001d);
   }

   @Test
   public void testHitPointCorner6()
   {
      double testValue = testBox.hitPoint(new Ray(new Vector(3, 1, 4),
          new Point(0, 0, 0)));
      assertEquals(1.00d, testValue, 0.001d);
   }

   @Test
   public void testHitPointCorner7()
   {
      double testValue = testBox.hitPoint(new Ray(new Vector(3, 2, 1),
          new Point(0, 0, 0)));
      assertEquals(1.00d, testValue, 0.001d);
   }

   @Test
   public void testHitPointCorner8()
   {
      double testValue = testBox.hitPoint(new Ray(new Vector(1.5, 1, 2),
          new Point(1, 1, 1)));
      assertEquals(1.00d, testValue, 0.001d);
   }

   @Test
   public void testContainsCorner1()
   {
      final Point point = new Point(1, 1, 1);
      boolean testValue = testBox.contains(point);
      assertEquals("does not contan pont " + point.toString(), true, testValue);
   }

   @Test
   public void testContainsCorner2()
   {
      final Point point = new Point(1, 1, 4);
      boolean testValue = testBox.contains(point);
      assertEquals("does not contan pont " + point.toString(), true, testValue);
   }

   @Test
   public void testContainsCorner3()
   {
      final Point point = new Point(1, 2, 1);
      boolean testValue = testBox.contains(point);
      assertEquals("does not contan pont " + point.toString(), true, testValue);
   }

   @Test
   public void testContainsCorner4()
   {
      final Point point = new Point(1, 2, 4);
      boolean testValue = testBox.contains(point);
      assertEquals("does not contan pont " + point.toString(), true, testValue);
   }

   @Test
   public void testContainsCorner5()
   {
      final Point point = new Point(3, 1, 1);
      boolean testValue = testBox.contains(point);
      assertEquals("does not contan pont " + point.toString(), true, testValue);
   }

   @Test
   public void testContainsCorner6()
   {
      final Point point = new Point(3, 1, 4);
      boolean testValue = testBox.contains(point);
      assertEquals("does not contan pont " + point.toString(), true, testValue);
   }

   @Test
   public void testContainsCorner7()
   {
      final Point point = new Point(3, 2, 1);
      boolean testValue = testBox.contains(point);
      assertEquals("does not contan pont " + point.toString(), true, testValue);
   }

   @Test
   public void testContainsCorner8()
   {
      final Point point = new Point(3, 2, 4);
      boolean testValue = testBox.contains(point);
      assertEquals("does not contan pont " + point.toString(), true, testValue);
   }

   @Test
   public void testGetNormal1()
   {
      Point point = new Point(2, 1.5, 2.5);
      Vector vector = new Vector(0, -1, 0);
      Ray ray = new Ray(vector, point);
      final double t = testBox.hitPoint(ray);
      Normal expected = new Normal(0, 1, 0);
      Normal acutal = testBox.getNormal(ray, t);
      assertEquals("Invalid normal", expected, acutal);
   }

   @Test
   public void testGetNormal2()
   {
      Point point = new Point(2, 1.5, 2.5);
      Vector vector = new Vector(0, 1, 0);
      Ray ray = new Ray(vector, point);
      final double t = testBox.hitPoint(ray);
      Normal expected = new Normal(0, 1, 0);
      Normal acutal = testBox.getNormal(ray, t);
      assertEquals("Invalid normal", expected, acutal);
   }

   @Test
   public void testGetNormal3()
   {
      Point point = new Point(2, 1.5, 2.5);
      Vector vector = new Vector(1, 0, 0);
      Ray ray = new Ray(vector, point);
      final double t = testBox.hitPoint(ray);
      Normal expected = new Normal(1, 0, 0);
      Normal acutal = testBox.getNormal(ray, t);
      assertEquals("Invalid normal", expected, acutal);
   }

   @Test
   public void testGetNormal4()
   {
      Point point = new Point(2, 1.5, 2.5);
      Vector vector = new Vector(-1, 0, 0);
      Ray ray = new Ray(vector, point);
      final double t = testBox.hitPoint(ray);
      Normal expected = new Normal(1, 0, 0);
      Normal acutal = testBox.getNormal(ray, t);
      assertEquals("Invalid normal", expected, acutal);
   }

   @Test
   public void testGetNormal5()
   {
      Point point = new Point(2, 1.5, 2.5);
      Vector vector = new Vector(0, 0, 1);
      Ray ray = new Ray(vector, point);
      final double t = testBox.hitPoint(ray);
      Normal expected = new Normal(0, 0, -1);
      Normal acutal = testBox.getNormal(ray, t);
      assertEquals("Invalid normal", expected, acutal);
   }

   @Test
   public void testGetNormal6()
   {
      Point point = new Point(2, 1.5, 2.5);
      Vector vector = new Vector(0, 0, -1);
      Ray ray = new Ray(vector, point);
      final double t = testBox.hitPoint(ray);
      Normal expected = new Normal(0, 0, -1);
      Normal acutal = testBox.getNormal(ray, t);
      assertEquals("Invalid normal", expected, acutal);
   }
}
