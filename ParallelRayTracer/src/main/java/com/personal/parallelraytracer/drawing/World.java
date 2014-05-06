package com.personal.parallelraytracer.drawing;

import com.personal.parallelraytracer.drawing.cameras.Camera;
import com.personal.parallelraytracer.drawing.cameras.Camera2;
import com.personal.parallelraytracer.drawing.cameras.PinHole;
import com.personal.parallelraytracer.drawing.light.Ambient;
import com.personal.parallelraytracer.drawing.light.Light;
import com.personal.parallelraytracer.drawing.light.PointLight;
import com.personal.parallelraytracer.drawing.materials.ColorMaterial;
import com.personal.parallelraytracer.drawing.materials.Matte;
import com.personal.parallelraytracer.drawing.materials.Phong;
import com.personal.parallelraytracer.drawing.sampling.Jittered;
import com.personal.parallelraytracer.drawing.sampling.Sampler;
import com.personal.parallelraytracer.drawing.shapes.Box;
import com.personal.parallelraytracer.drawing.shapes.GeometricShape;
import com.personal.parallelraytracer.drawing.shapes.Plane;
import com.personal.parallelraytracer.drawing.shapes.Sphere;
import com.personal.parallelraytracer.drawing.tracers.RayCast;
import com.personal.parallelraytracer.drawing.tracers.Tracer;
import com.personal.parallelraytracer.drawing.utils.ShadeRec;
import com.personal.parallelraytracer.math.Normal;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Ray;
import com.personal.parallelraytracer.math.Vector;
import java.util.ArrayList;
import java.util.List;

public class World
{
   public List<GeometricShape> shapes;
   public final Sphere sphere;
   public final Box box;
   public RGBColor backgroundColor;
   public ViewPlane vp;
   public Tracer tracer;
   public Light ambient;
   public List<Light> lights;
   public Camera camera;

   public World()
   {
      final Sampler jittered = new Jittered(16);
      this.vp = new ViewPlane(400, 400, 1.0, 1.0, 16, jittered);
      this.backgroundColor = RGBColor.BLACK;
      this.tracer = new RayCast(this);
      this.lights = new ArrayList<>();
      this.ambient = new Ambient();
      ambient.setLs(1.0);
      camera = new Camera2(vp, tracer);

      this.sphere
          = new Sphere(true, false, new ColorMaterial(RGBColor.YELLOW),
              new Point(0, 0,
                  0), 85.0d);
      this.box = new Box(true, false, new ColorMaterial(RGBColor.BLUE),
          new Point(1, 1, 1), new Point(50, 50, 50));
   }

   public void setUpScene1()
   {
      this.shapes = new ArrayList();
      shapes.add(new Sphere(true, false,
          new ColorMaterial(RGBColor.RED), new Point(0, -25, 0), 80));
      shapes.add(new Sphere(true, false,
          new ColorMaterial(RGBColor.YELLOW), new Point(0, 30, 0), 60));
      shapes.add(new Plane(true, false, new Point(0, 0, 0),
          new Normal(0, 1, 1), new ColorMaterial(new RGBColor(0, 0.3, 0))));
      shapes.add(new Box(true, false, new ColorMaterial(RGBColor.BLUE),
          new Point(0, 0, 0), 120, 120, 120));
   }

   public void setUpShadowTest()
   {
      this.shapes = new ArrayList(); // empty the array
      this.vp = new ViewPlane(400, 400, 1.0, 1.0, 25, new Jittered(25));
      this.backgroundColor = RGBColor.BLACK;

      this.tracer = new RayCast(this);

      this.ambient = new Ambient();
      ambient.setLs(1.0);

      this.camera = new PinHole(850.0d, 1, new Point(100, 100, 100),
          new Point(-5, 0, 0), new Vector(1, 1, 0), 1);
      this.camera.computeUvw();

      this.lights = new ArrayList<>();
      lights.add(new PointLight(new Point(100, 50, 150), 3.0f, RGBColor.WHITE,
          true));

      Phong matteSphere1 = new Phong();
      matteSphere1.setKa(0.15d);
      matteSphere1.setKd(0.55d);
      matteSphere1.setKs(0.20d);
      matteSphere1.setExp(100);
      matteSphere1.setCd(new RGBColor(1, 1, 0));
      shapes.add(new Sphere(true, false, matteSphere1, new Point(0, 0, 10), 10));
      
      Matte matteBox1 = new Matte();
      matteBox1.setKa(0.15d);
      matteBox1.setKd(0.55d);
//      matteBox1.setKs(0.20d);
      matteBox1.setCd(new RGBColor(0, 0, 1));
      shapes
          .add(new Box(true, false, matteBox1, new Point(0, 0, -10), 20, 20, 20));
      
      Matte matteSphere2 = new Matte();
      matteSphere2.setKa(0.25d);
      matteSphere2.setKd(0.65d);
      matteSphere2.setCd(new RGBColor(1, 1, 0));
      shapes.add(new Sphere(true, false, matteSphere2, new Point(20, 10, 10), 10));
      
      Matte matteBox2 = new Matte();
      matteBox2.setKa(0.25d);
      matteBox2.setKd(0.65d);
      matteBox2.setCd(new RGBColor(0, 0, 1));
      shapes
          .add(new Box(true, false, matteBox2, new Point(20, 10, -10), 20, 20, 20));
      
      Matte mattePlane = new Matte();
      mattePlane.setKa(0.25d);
      mattePlane.setKd(0.65d);
      mattePlane.setCd(new RGBColor(0, .5, .5));
//      shapes.add(new Plane(true, true, new Point(0,0,-20), new Normal(0, 0, 1), mattePlane));
   }

   public void setUpTestScene1()
   {
      this.shapes = new ArrayList(); // empty the array
      this.vp = new ViewPlane(400, 400, 1.0, 1.0, 16, new Jittered(16));
      this.backgroundColor = RGBColor.BLACK;

      this.tracer = new RayCast(this);

      this.ambient = new Ambient();
      ambient.setLs(1.0);
      final Point eye = new Point(0, 0, 500);
      final Point lookAt = new Point(-5, 0, 0);
      final Vector up = new Vector(0, 1, 0);

      this.camera = new PinHole(850.0d, 1.0d, eye, lookAt, up, 1.0d);
      this.camera.computeUvw();

      this.lights = new ArrayList<>();
      lights.add(new PointLight(new Point(100, 50, 150), 3.0f, RGBColor.WHITE,
          false));

      Matte matte = new Matte();
      matte.setKa(0.25d);
      matte.setKd(0.65d);
      matte.setCd(new RGBColor(1, 1, 0));
      shapes.add(new Sphere(true, false, matte, new Point(10, -5, 0), 27));
   }
   
   public void setUpTestScene2()
   {
      this.shapes = new ArrayList(); // empty the array
      this.vp = new ViewPlane(400, 400, 1.0, 1.0, 16, new Jittered(16));
      this.backgroundColor = RGBColor.BLACK;

      this.tracer = new RayCast(this);

      this.ambient = new Ambient();
      ambient.setLs(1.0);
      final Point eye = new Point(0, 0, 500);
      final Point lookAt = new Point(-5, 0, 0);
      final Vector up = new Vector(0, 1, 0);

      this.camera = new PinHole(850.0d, 1.0d, eye, lookAt, up, 1.0d);
      this.camera.computeUvw();

      this.lights = new ArrayList<>();
      lights.add(new PointLight(new Point(100, 50, 150), 3.0f, RGBColor.WHITE,
          false));

      Matte matte = new Matte();
      matte.setKa(0.25d);
      matte.setKd(0.65d);
      matte.setCd(new RGBColor(1, 1, 0));
      shapes.add(new Box(true, false, matte, new Point(10, -5, 0), 30, 30, 30));
   }

   public ShadeRec hitObjects(Ray ray)
   {
      ShadeRec sr = new ShadeRec(this);
      double t;
      Normal normal = null;
      Point localHitPoint = null;
      double tMin = Double.MAX_VALUE;

      for (GeometricShape shape : shapes)
      {
         t = shape.hitPoint(ray);
         if (!Double.isNaN(t) && t < tMin)
         {
            sr = setShadeRecProperties(sr, ray, t, shape);
            tMin = t;
            sr.material = shape.getMaterial();
            normal = sr.normal;
            localHitPoint = sr.localHitPoint;
         }
      }

      if (sr.hitAnObject)
      {
         sr.t = tMin;
         sr.normal = normal;
         sr.localHitPoint = localHitPoint;
      }

      return sr;
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

   void addLight(Light light)
   {
      lights.add(light);
   }

   public Camera2 buildCamera()
   {
      return new Camera2(vp, tracer);
   }

   public static ShadeRec setShadeRecProperties(ShadeRec sr, Ray ray, double t,
       GeometricShape shape)
   {
      // looks usless now but will leave in for now.
      sr.hitAnObject = true;
      sr.hitPoint = ray.findLocalHitPoint(t);
      sr.localHitPoint = ray.findLocalHitPoint(t);
      sr.normal = shape.getNormal(ray, t);
      sr.color = shape.getColor();
      return sr;
   }

   public Camera getCamera()
   {
      return camera;
   }

   public void build()
   {

   }
}
