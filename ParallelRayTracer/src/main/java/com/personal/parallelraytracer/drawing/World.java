package com.personal.parallelraytracer.drawing;

import com.personal.parallelraytracer.drawing.materials.Matte;
import com.personal.parallelraytracer.drawing.shapes.Box;
import com.personal.parallelraytracer.drawing.shapes.GeometricShape;
import com.personal.parallelraytracer.drawing.shapes.Plane;
import com.personal.parallelraytracer.drawing.shapes.Sphere;
import com.personal.parallelraytracer.drawing.tracers.RayCasting;
import com.personal.parallelraytracer.drawing.tracers.Tracer;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;
import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import java.util.ArrayList;
import java.util.List;

public class World
{
   public List<GeometricShape> shapes;
   public final Sphere sphere;
   public final Box box;
   public final RGBColor backgroundColor;
   public final ViewWindow viewWindow;
   public final Tracer tracer;

   public World()
   {
      this.viewWindow = new ViewWindow(200, 200, 1.0, 1.0, 1);
      this.backgroundColor = RGBColor.BLACK;
      this.tracer = new RayCasting(this);
      this.sphere
          = new Sphere(true, false, new Matte(RGBColor.YELLOW), new Point(0, 0,
                  0), 85.0d);
      this.box = new Box(true, false, new Matte(RGBColor.BLUE),
          new Point(1, 1, 1), new Point(50, 50, 50));
   }

   public void setUpSceen1()
   {
      this.shapes = new ArrayList();
      shapes.add(new Sphere(true, false,
          new Matte(RGBColor.RED), new Point(0, -25, 0), 80));
      shapes.add(new Sphere(true, false,
          new Matte(RGBColor.YELLOW), new Point(0, 30, 0), 60));
      shapes.add(new Plane(true, false, new Point(0, 0, 0),
          new Normal(0, 1, 1), new Matte(new RGBColor(0, 0.3, 0))));
      shapes.add(new Box(true, false, new Matte(RGBColor.BLUE),
          new Point(0, 0, 0), 120, 120, 120));
   }

   public ShadeRec hitBareBonesObjects(Ray ray)
   {
      ShadeRec sr = new ShadeRec(this);
      double t;
      double tmin = Double.MAX_VALUE;

      for (GeometricShape shape : shapes)
      {
         t = shape.hitPoint(ray);
         if (!Double.isNaN(t) && t < tmin)
         {
            sr = setShadeRecProperties(sr, ray, t, shape);
            tmin = t;
         }
      }
      return sr;
   }

   void addObject(GeometricShape shape)
   {
      shapes.add(shape);
   }

   public Camera buildCamera()
   {
      return new Camera(viewWindow, tracer);
   }

   public static ShadeRec setShadeRecProperties(ShadeRec sr, Ray ray, double t,
       GeometricShape shape)
   {
      // looks usless now but will leave in for now.
      sr.hitAnObject = true;
      sr.localhitPoint = ray.findLocalHitPoint(t);
      sr.normal = shape.getNormal(ray, t);
      sr.color = shape.getColor();
      return sr;
   }
}
