package com.personal.parallelraytracer.drawing.cameras;

import com.personal.parallelraytracer.drawing.RGBColor;
import org.junit.Test;
import static org.junit.Assert.*;

public class PixelTest {

    public PixelTest() {
    }

   @Test
   public void testToJSONString()
   {
      Pixel pixel = new Pixel(1, 2);
      String actual = pixel.toJSONString();
     
      assertFalse(actual, actual.contains("error"));
      System.out.println(pixel.toJSONString());
   }
   
   @Test
   public void testToJSONString2()
   {
      Pixel pixel = new Pixel(1, 2);
      pixel.color = RGBColor.BLACK;
      String actual = pixel.toJSONString();
     
      assertFalse(actual, actual.contains("error"));
      System.out.println(pixel.toJSONString());
   }

}