package com.personal.parallelraytracer.drawing.tracers;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.math.Ray;

public class SingleBox extends Tracer 
{
   public SingleBox(World world)
   {
      this.world = world;
   }

   @Override
   public RGBColor trayRay(Ray ray)
   {
//      ShadeRec sr = new ShadeRec(world);
      double t = world.box.hitPoint(ray);
      if (Double.isNaN(t))
         return world.backgroundColor;
//      sr = setShadeRecProperties(sr, ray, t, world.sphere);
      
      return world.box.getColor();
   }
}
