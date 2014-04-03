package com.personal.parallelraytracer.drawing;

import com.personal.parallelraytracer.drawing.shapes.GeometricShape;
import java.awt.Color;
import java.util.List;

public class World
{
   private ViewWindow viewWindow;
   private Color backgroundColor;
   private List<GeometricShape> shapes;
   private Tracer tracer;
}
