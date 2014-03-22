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
         Runtime.getRuntime().exec("ssh AUS213L24 \"wall hello\"");
      }
      catch (Exception e)
      {
         System.out.println(e.getMessage());
      }
   }
}
