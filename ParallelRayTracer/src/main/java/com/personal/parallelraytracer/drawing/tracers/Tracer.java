package com.personal.parallelraytracer.drawing.tracers;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.math.Ray;
import java.awt.Color;

public abstract class Tracer 
{
   World world;
   public Color color;
   public String name;

   public Tracer(String name)
   {
      this.name = name;
   }
   
   
   
   public abstract RGBColor trayRay(Ray ray);
   public abstract RGBColor trayRay(Ray ray, int depth);
   public abstract RGBColor trayRay(Ray ray, double tmin, int depth);
   
}
