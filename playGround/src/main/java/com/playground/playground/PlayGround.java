//package com.playground.playground;

/**
 * Hello world!
 *
 */
public class PlayGround
{
   public static void main(String[] args)
   {
      
      sshCall();

   }

   private static void sshCall()
   {
      long start = System.nanoTime();
      try
      {
         final Process exec
             = Runtime.getRuntime().exec(new String[]{"ssh", "-p215", "AUS213L24", "\"\"wall hello\"\""});
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
      long end = System.nanoTime();
      System.out.println(end - start);
   }
}
