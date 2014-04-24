package com.personal.parallelraytracer.drawing.tracers;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.math.Ray;
import java.awt.Color;

public abstract class Tracer 
{
   World world;
   public Color color;
   
   public abstract RGBColor trayRay(Ray ray);
}
