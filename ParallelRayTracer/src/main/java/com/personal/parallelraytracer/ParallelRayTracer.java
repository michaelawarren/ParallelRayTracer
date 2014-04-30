package com.personal.parallelraytracer;

import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.drawing.cameras.Camera;

public class ParallelRayTracer 
{
    public static void main( String[] args )
    {
        World world = new World();
        world.setUpScene2();
        world.getCamera().renderScene(world);
    }
}
