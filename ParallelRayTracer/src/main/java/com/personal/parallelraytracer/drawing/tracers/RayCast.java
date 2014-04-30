package com.personal.parallelraytracer.drawing.tracers;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;
import com.personal.parallelraytracer.math.Ray;

public class RayCast extends Tracer 
{
   public RayCast(World world)
   {
      this.world = world;
   }

   @Override
   public RGBColor trayRay(Ray ray)
   {
      ShadeRec sr = new ShadeRec(world.hitBareBonesObjects(ray));
      if (sr.hitAnObject)
         return sr.color;
      
      return world.backgroundColor;
   }

   @Override
   public RGBColor trayRay(Ray ray, int depth)
   {
      ShadeRec sr = new ShadeRec(world.hitObjects(ray));
      if (sr.hitAnObject)
      {
         sr.ray = ray;
         return sr.material.shade(sr);
      }
      else 
         return world.backgroundColor;
   }

   @Override
   public RGBColor trayRay(Ray ray, double tmin, int depth)
   {
      return trayRay(ray, depth);
   }
}
