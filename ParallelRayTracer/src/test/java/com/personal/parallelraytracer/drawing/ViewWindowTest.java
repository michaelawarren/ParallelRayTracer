package com.personal.parallelraytracer.drawing;

import org.junit.Test;
import static org.junit.Assert.*;

public class ViewWindowTest
{
   ViewWindow viewWindow = new ViewWindow(6, 4, 1, 1);

   public ViewWindowTest()
   {
   }

   public void testGetXColumnLowerBoundTest()
   {
      final double value = viewWindow.getX(0);
      assertEquals(-1.5d, value, 0.00001);
   }

   @Test()
   public void testGetXColumn2()
   {
      final double value = viewWindow.getX(5);
      assertEquals(2.5, value, 0.00001);
   }

   @Test()
   public void testGetXColumn3()
   {
      final double value = viewWindow.getX(3);
      assertEquals(0.5, value, 0.00001);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testGetXToSmall()
   {
      viewWindow.getX(-1);
      fail();
   }

   @Test(expected = IllegalArgumentException.class)
   public void testGetXToBig()
   {
      viewWindow.getX(6);
      fail();
   }

   @Test(expected = IllegalArgumentException.class)
   public void testGetXToBig2()
   {
      viewWindow.getX(7);
      fail();
   }

   @Test
   public void testGetY()
   {
      final double value = viewWindow.getY(0);
      assertEquals(-1.5d, value, 0.00001d);
   }
   
   @Test
   public void testGetY2()
   {
      final double value = viewWindow.getY(1);
      assertEquals(-0.5d, value, 0.00001d);
   }

   @Test
   public void testGetY3()
   {
      final double value = viewWindow.getY(3);
      assertEquals(1.5d, value, 0.00001d);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testGetYToSmall()
   {
      viewWindow.getY(-1);
      fail();
   }

   @Test(expected = IllegalArgumentException.class)
   public void testGetYToBig()
   {
      viewWindow.getY(4);
      fail();
   }

   @Test(expected = IllegalArgumentException.class)
   public void testGetYToBig2()
   {
      viewWindow.getY(5);
      fail();
   }
}
