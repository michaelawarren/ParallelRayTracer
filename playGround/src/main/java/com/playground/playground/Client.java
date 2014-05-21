package com.playground.playground;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client
{
   Socket socket;

   public static void main(String[] args)
   {
      try
      {
         if (args.length < 2)
         {
            return;
         }
         int port = Integer.parseInt(args[1]);
         Socket socket = new Socket(args[0], port);
         
         InputStream is;
         InputStreamReader ir;
         System.out.println("Waiting for input from server1");
         while((is = socket.getInputStream()) != null)
         {
            ir = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(ir);

            String requestLine = bufferedReader.readLine();
            System.out.println(requestLine);
            // Runtime.getRuntime().exec(requestLine);
         }
      }
      catch (IOException ex)
      {
        System.out.println(ex.toString());
      }
      System.out.println("close");
   }
}
