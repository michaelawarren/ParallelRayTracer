package com.personal.parallelraytracer.drawing.tracers;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.math.Ray;

public class SingleSphere extends Tracer 
{
   public SingleSphere(World world, String name)
   {
      super(name);
      this.world = world;
   }

   @Override
   public RGBColor trayRay(Ray ray)
   {
      double t = world.sphere.hitPoint(ray);
      if (Double.isNaN(t))
         return world.backgroundColor;
      
      return world.sphere.getColor();
   }
   
   /**
    * not supported will produce the same output as traceRa(ray)
    * 
    * @param ray
    * @param depth
    * @return 
    */
   @Deprecated
   @Override
   public RGBColor trayRay(Ray ray, int depth)
   {
      return trayRay(ray);
   }
   
   /**
    * not supported will produce the same output as traceRa(ray)
    * 
    * @param ray
    * @param depth
    * @return 
    */
   @Deprecated
   @Override
   public RGBColor trayRay(Ray ray, double tmin, int depth)
   {
      return trayRay(ray);
   }
}
