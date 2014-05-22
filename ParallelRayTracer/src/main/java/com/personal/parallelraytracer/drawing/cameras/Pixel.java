package com.personal.parallelraytracer.drawing.cameras;

import com.personal.parallelraytracer.drawing.RGBColor;
import org.json.JSONException;
import org.json.JSONString;
import org.json.JSONStringer;

public class Pixel implements JSONString
{
   public int r;
   public int c;
   public int rgb;

   public Pixel(int r, int c)
   {
      this.r = r;
      this.c = c;
   }

   public Pixel(int r, int c, int rgb)
   {
      this.r = r;
      this.c = c;
      this.rgb = rgb;
   }

   public Pixel(int r, int c, RGBColor color)
   {
      this.r = r;
      this.c = c;
      this.rgb = color.getColor().getRGB();
   }

   @Override
   public String toString()
   {
      return "Pixel{" + "r=" + r + ", c=" + c + ", color=" + rgb + '}';
   }

   @Override
   public String toJSONString()
   {
      try
      {
         return new JSONStringer()
             .object()
             .key("r").value(r)
             .key("c").value(c)
             .key("color").value(rgb)
             .endObject()
             .toString();
      }
      catch (JSONException ex)
      {
         return "error: " + ex.toString();
      }
   }
}
