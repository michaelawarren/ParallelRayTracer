package com.personal.parallelraytracer.drawing.tracers;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;
import com.personal.parallelraytracer.math.Ray;

public class RayCasting extends Tracer 
{
   public RayCasting(World world)
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
}
