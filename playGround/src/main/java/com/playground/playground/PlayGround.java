//package com.playground.playground;

/**
 * Hello world!
 *
 */
public class PlayGround
{
   public static void main(String[] args)
   {
      try
      {
         final Process exec
             = Runtime.getRuntime().exec("ssh AUS213L24 \"wall hello\"");
         if (exec.waitFor() != 0)
         {
            System.out.print(exec.getErrorStream().toString());
         }
      }
      catch (Exception e)
      {
         System.out.println(e.toString());
      }
   }
}
