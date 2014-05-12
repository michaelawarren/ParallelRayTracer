package com.personal.parallelraytracer.drawing;

import org.junit.Test;
import static org.junit.Assert.*;

public class RGBColorTest {

    public RGBColorTest() {
    }

   @Test
   public void testComponmentMultiply()
   {
   }

   @Test
   public void testToJSONString()
   {
      String actual = RGBColor.BLACK.toJSONString();
      assertFalse(actual, actual.contains("error"));
   }

}