/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.personal.parallelraytracer.drawing.shapes;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author Michael
 */
public class BoxTest extends TestCase
{
   
   public BoxTest(String testName)
   {
      super(testName);
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite(BoxTest.class);
      return suite;
   }

   public void testHit()
   {
   }
   
}
