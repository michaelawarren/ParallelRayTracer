package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import com.personal.parallelraytracer.math.Vector;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoxTest
{
   static Box testBox;

   @BeforeClass
   public static void setUpClass() throws Exception
   {
      Point point1 = new Point(1, 1, 1);
      Point point2 = new Point(2, 3, 4);
      Normal normal = new Normal(1, 1, 1);

      testBox = new Box(true, true, point1, point2, normal, null);
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
            double testValue = testBox.hitPoint(new Ray(new Vector(1, 1, 1),
          new Point(0, 0, 0)));
      assertEquals(1.00d, testValue, 0.001d);
   }

   @Test
   public void testHitPointCorner3()
   {
            double testValue = testBox.hitPoint(new Ray(new Vector(1, 1, 1),
          new Point(0, 0, 0)));
      assertEquals(1.00d, testValue, 0.001d);
   }

   @Test
   public void testHitPointCorner4()
   {
      fail();
   }

   @Test
   public void testHitPointCorner5()
   {
      fail();
   }

   @Test
   public void testHitPointCorner6()
   {
      fail();
   }

   @Test
   public void testHitPointCorner7()
   {
      fail();
   }

   @Test
   public void testHitPointCorner8()
   {
      fail();
   }

   @Test
   public void testCointains()
   {
      fail();
   }

   @Test
   public void testHitPoint()
   {
      fail();
   }
}
