package com.personal.parallelraytracer.drawing.utils;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.drawing.materials.Material;
import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import com.personal.parallelraytracer.math.Vector;

public class ShadeRec
{
   public boolean hitAnObject;
   public Point localHitPoint;
   public Point hitPoint;
   public Normal normal;
   public RGBColor color;
   public Material material;
   public Ray ray;
   public int depth;
   public Vector dir;
   public World world;
   public double t;

   public ShadeRec(World world)
   {
      this.world = world;
      this.hitAnObject = false;
      this.localHitPoint = null;
      this.normal = null;
      this.color = RGBColor.BLACK;
      this.material = null;
      this.ray = new Ray();
      this.depth = 0;
      this.dir = new Vector(0, 0, 0);
      this.hitPoint = new Point(0, 0, 0);
      this.t = 0;
   }

   public ShadeRec(ShadeRec shadeRec)
   {
      this.world         = shadeRec.world;
      this.hitAnObject   = shadeRec.hitAnObject;
      this.localHitPoint = shadeRec.localHitPoint;
      this.normal        = shadeRec.normal;
      this.color         = shadeRec.color;
      this.material      = shadeRec.material;
      this.ray           = shadeRec.ray;
      this.depth         = shadeRec.depth;
      this.dir           = shadeRec.dir;
      this.hitPoint      = shadeRec.hitPoint;
      this.t             = shadeRec.t;
   }
}
