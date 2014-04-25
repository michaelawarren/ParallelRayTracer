package com.personal.parallelraytracer.drawing.light;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Vector;

public class PointLight extends Light
{
   Point location;

   public PointLight(Point location, float ls, RGBColor color, boolean shadow)
   {
      this.location = location;
      this.ls = ls;
      this.color = color;
      this.shadow = shadow;
   }
   
   @Override
   public Vector getDirection(ShadeRec sr)
   {
      return new Vector(location.subtract(sr.localhitPoint).normalize());
   }
}
