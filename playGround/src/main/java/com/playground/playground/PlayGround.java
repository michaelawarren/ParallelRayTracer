package com.playground.playground;

import java.io.IOException;

public class PlayGround
{
   public static void main(String[] args)
   {
      long start = System.currentTimeMillis();
      System.out.println("Println: ");
      long end = System.currentTimeMillis();
      System.out.println((end - start));

      // System.out.println("Process: ");
      //processSshOverHead();
      // System.out.println("RunTime: ");
      //runTimeSshOverhead();
      // System.out.println("Sockets: ");
      SocketsOverHead.timeOverhead();
   }

   private static void processSshOverHead()
   {
      long start = System.currentTimeMillis();
      try
      {
         ProcessBuilder processBuilder = new ProcessBuilder("ssh", "-p215",
             "AUS213L24", "\"\"wall hello\"\"");
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
      long end = System.currentTimeMillis();
      System.out.println((end - start));
   }

   private static void runTimeSshOverhead()
   {
      long start = System.currentTimeMillis();
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
      long end = System.currentTimeMillis();
      System.out.println((end - start));
   }
}
