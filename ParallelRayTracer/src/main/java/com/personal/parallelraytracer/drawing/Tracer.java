package com.personal.parallelraytracer.drawing;

import com.personal.parallelraytracer.drawing.shapes.GeometricShape;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;
import com.personal.parallelraytracer.math.Ray;
import java.awt.Color;

abstract class Tracer 
{
   World world;
   public Color color;
   
   public abstract Color trayRay(Ray ray);
   
   ShadeRec setShadeRecProperties(ShadeRec sr, Ray ray, double t, GeometricShape shape)
   {
      // looks usless now but will leave in for now.
      sr.hitAnObject = true;
      sr.localhitPoint = ray.findLocalHitPoint(t);
      sr.normal = shape.getNormal(ray, t);
      return sr;
   }
}
