package com.personal.parallelraytracer.drawing.shapes;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.materials.Material;
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
   public double EPSIOLON = 10e-6d;
   protected boolean visible;
   protected boolean reflective;
   protected Point position;
   protected RGBColor color;
   protected Material material;
   protected Map<String, Comparable> hitStats;

   public GeometricShape(boolean visible, boolean reflective, Material material,
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

   public boolean isVisible()
   {
      return visible;
   }

   public void setVisible(boolean visible)
   {
      this.visible = visible;
   }

   public boolean isReflective()
   {
      return reflective;
   }

   public void setReflective(boolean reflective)
   {
      this.reflective = reflective;
   }

   public Material getMaterial()
   {
      return material;
   }

   public void setMaterial(Material material)
   {
      this.material = material;
   }

   public double shadowHit(Ray ray)
   {
      return hitPoint(ray);
   }
}
