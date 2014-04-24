package com.personal.parallelraytracer.drawing.utils;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;

public class ShadeRec
{
   public boolean hitAnObject;
   public Point localhitPoint;
   public Normal normal;
   public RGBColor color;
   public World world;
   
   public ShadeRec(World world)
   {
      this.world = world;
      this.hitAnObject = false;
      this.localhitPoint = null;
      this.normal = null;
      this.color = RGBColor.BLACK;
   }
   
   public ShadeRec(ShadeRec shadeRec)
   {
      this.world = shadeRec.world;
      this.hitAnObject = shadeRec.hitAnObject;
      this.localhitPoint = shadeRec.localhitPoint;
      this.normal = shadeRec.normal;
      this.color = shadeRec.color;
   }
}
