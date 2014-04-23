package com.personal.parallelraytracer.drawing.tracers;

import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.math.Ray;
import java.awt.Color;

public class SingleSphere extends Tracer 
{
   public SingleSphere(World world)
   {
      this.world = world;
   }

   @Override
   public Color trayRay(Ray ray)
   {
//      ShadeRec sr = new ShadeRec(world);
      double t = world.sphere.hitPoint(ray);
      if (Double.isNaN(t))
         return world.backgroundColor;
//      sr = setShadeRecProperties(sr, ray, t, world.sphere);
      
      return world.sphere.getColor();
   }
}
