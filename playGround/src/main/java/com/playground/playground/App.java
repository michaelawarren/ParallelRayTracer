package com.playground.playground;

/**
 * Hello world!
 *
 */
public class App
{
   public static void main(String[] args)
   {
      try
      {
         while (true)
         {
            Runtime.getRuntime().exec("ssh AUS213L24");
            Runtime.getRuntime().exec("wall 'hello world'");
         }
      }
      catch (Exception e)
      {
         System.out.println(e.getMessage());
      }
   }
}
