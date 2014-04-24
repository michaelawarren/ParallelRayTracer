package com.personal.parallelraytracer;

import com.personal.parallelraytracer.drawing.Camera;
import com.personal.parallelraytracer.drawing.World;

public class ParallelRayTracer 
{
    public static void main( String[] args )
    {
        World world = new World();
        world.setUpSceen1();
        Camera camera = world.buildCamera();
        camera.renderScene();
    }
}
