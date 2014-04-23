package com.personal.parallelraytracer.drawing.utils;

import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import java.awt.Color;

public class ShadeRec
{
   public boolean hitAnObject;
   public Point localhitPoint;
   public Normal normal;
   public Color color;
   public World world;
   
   public ShadeRec(World world)
   {
      this.world = world;
      this.hitAnObject = false;
      this.localhitPoint = null;
      this.normal = null;
      this.color = Color.BLACK;
   }
   
   public ShadeRec(ShadeRec shadeRec)
   {
      this.world = shadeRec.world;
      this.hitAnObject = shadeRec.hitAnObject;
      this.localhitPoint = new Point(shadeRec.localhitPoint);
      this.normal = new Normal(shadeRec.normal);
      this.color = shadeRec.color;
   }
}
