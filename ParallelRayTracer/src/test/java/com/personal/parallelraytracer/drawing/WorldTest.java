package com.personal.parallelraytracer.drawing;

import com.personal.parallelraytracer.drawing.utils.ShadeRec;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import com.personal.parallelraytracer.math.Vector;
import static org.junit.Assert.*;
import org.junit.Test;

public class WorldTest
{
   public WorldTest()
   {
   }

   @Test
   public void testHitObjects()
   {
      World world = new World();
      world.setUpTestScene1();
      Ray ray = new Ray(new Vector(10, -5, 0), new Point(0, 0, 0));
//      hitBareBonesObjects(Ray ray)
      final ShadeRec hitObjects = world.hitObjects(ray);
      assertTrue(hitObjects.hitAnObject);
   }

   @Test
   public void testHitObjects2()
   {
      World world = new World();
      world.setUpTestScene2();
      Ray ray = new Ray(new Vector(10, -5, 0), new Point(0, 0, 0));
//      hitBareBonesObjects(Ray ray)
      final ShadeRec hitObjects = world.hitObjects(ray);
      assertTrue(hitObjects.hitAnObject);
   }

   @Test
   public void testHitBareBonesObjects()
   {
      World world = new World();
      world.setUpTestScene1();
      Ray ray = new Ray(new Vector(10, -5, 0), new Point(0, 0, 0));
      final ShadeRec hitObjects = world.hitBareBonesObjects(ray);
      assertTrue(hitObjects.hitAnObject);
   }
   
   @Test
   public void testHitBareBonesObjects2()
   {
      World world = new World();
      world.setUpTestScene2();
      Ray ray = new Ray(new Vector(10, -5, 0), new Point(0, 0, 0));
      final ShadeRec hitObjects = world.hitBareBonesObjects(ray);
      assertTrue(hitObjects.hitAnObject);
   }
}
