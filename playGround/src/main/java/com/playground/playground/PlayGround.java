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
             = Runtime.getRuntime().exec("ssh -p215 AUS213L24 \"wall hello\"");
         Runtime.getRuntime().exec("ssh", new String[]
         {
            "-p215" ,"AUS213L24", "wall hello"
         });
         if (exec.waitFor() != 0)
         {
            byte[] bytes = new byte[255];
            exec.getErrorStream().read(bytes, 0, 255);
            String string = new String(bytes);
            System.out.print(string);
         }
      }
      catch (Exception e)
      {
         System.out.println(e.toString());
      }
   }
}
