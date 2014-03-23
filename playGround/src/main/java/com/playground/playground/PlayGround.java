//package com.playground.playground;

import java.io.IOException;

public class PlayGround
{
   public static void main(String[] args)
   {
      processSsh();
   }

   private static void processSsh()
   {
      long start = System.nanoTime();
      try
      {
         ProcessBuilder processBuilder = new ProcessBuilder("ssh",  "-p215", "AUS213L24", "\"wall hello\"");
         final Process process = processBuilder.start();
         if (process.waitFor() != 0)
         {
            byte[] bytes = new byte[255];
            process.getErrorStream().read(bytes, 0, 255);
            String string = new String(bytes);
            System.out.print(string);
         }
      }
      catch (IOException ex)
      {
         System.out.println(ex.toString());
      }
      catch (InterruptedException ex)
      {
         System.out.println(ex.toString());
      }
      long end = System.nanoTime();
      System.out.println(end - start);
   }

   private static void sshCall()
   {
      long start = System.nanoTime();
      try
      {
         final Process exec = Runtime.getRuntime().exec(new String[]
         {
            "ssh", "-p215", "AUS213L24", "\"\"wall hello\"\""
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
      long end = System.nanoTime();
      System.out.println(end - start);
   }
}
