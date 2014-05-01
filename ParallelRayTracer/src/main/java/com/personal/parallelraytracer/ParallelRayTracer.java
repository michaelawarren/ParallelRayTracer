package com.personal.parallelraytracer;

import com.personal.parallelraytracer.drawing.World;

public class ParallelRayTracer 
{
    public static void main( String[] args )
    {
        World world = new World();
        world.setUpShadowTest();
        long start = System.currentTimeMillis();
        world.getCamera().renderScene(world);
        System.out.println("DrawTime: " + ((System.currentTimeMillis() - start) / 1000.0d) + " seconds");
        
    }
}
