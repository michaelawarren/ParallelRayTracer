package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.materials.Matte;
import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import java.util.Map;

/**
 *
 * @author Michael
 */
public abstract class GeometricShape
{
   public final double EPSIOLON = 10e-6d;
   protected boolean visible;
   protected boolean reflective;
   protected Point position;
   protected Matte material;
   protected Map<String, Comparable> hitStats;

   public GeometricShape(boolean visible, boolean reflective, Matte material,
       Point position)
   {
      this.visible = visible;
      this.reflective = reflective;
      this.position = position;
      this.material = material;
   }

   public RGBColor getColor()
   {
      return material.getColor();
   }

   public abstract double hitPoint(Ray ray);

   public abstract boolean contains(Point point);

   public abstract Normal getNormal(Ray ray, double t);
}
