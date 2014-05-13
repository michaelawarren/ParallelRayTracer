package com.personal.parallelraytracer.drawing.cameras;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class PixelRunable extends RayRunable
{
   JSONObject pixel;

   public PixelRunable(JSONObject pixel) throws JSONException
   {
      super(pixel.getInt("r"), pixel.getInt("c"));
      this.pixel = pixel;
   }
}
