package com.playground.playground;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketsOverHead
{
   public static void timeOverhead()
   {
      try
      {
         int port = 6789;
         // initializeClient(port);

         try (ServerSocket listenSocket = new ServerSocket(port); Socket socket
             = listenSocket.accept())
         {
            long start = System.currentTimeMillis();
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            // Construct a 1K buffer to hold bytes on their way to the socket.
            String message = "wall Hello";

            byte[] buffer = message.getBytes();
            int bytes = 0;

            // Copy requested file into the socket's output stream.
            bytes = buffer.length;
            os.write(buffer, 0, bytes);
            long end = System.currentTimeMillis();
            System.out.println(end - start);
         }
      }
      catch (IOException ex)
      {
         System.out.println(ex.toString());
      }
   }

   private static void initializeClient(int port)
   {
      try
      {
         final Process exec = Runtime.getRuntime().exec(new String[]
         {
            "ssh", "-p215", "AUS213L24", "\"\"client " + port + "\"\""
         });
      }
      catch (IOException ex)
      {
         System.out.println(ex);
      }
   }
}
