package com.playground.raytracerserver;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.json.JSONException;
import org.json.JSONStringer;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
       try
       {
          String actual = new JSONStringer()
              .object()
              .key("initialize").value(1)
              .key("width").value(500)
              .key("height").value(500)
              .key("numThreads").value(2)
              .endObject()
              .toString();
       }
       catch (JSONException ex)
       {
          ex.printStackTrace();
       }
    }
}
