package com.playground.raytracerserver;

import com.personal.parallelraytracer.drawing.World;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App
{
   public static void main(String[] args)
   {
      int port = 6780;
      try (ServerSocket listenSocket = new ServerSocket(port);)
      {
         System.out.println("Hello");
         while (true)
         {
            try (
                Socket socket = listenSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));)
            {
               String inputLine, outputLine;
               final World world = new World();
               // Initiate conversation with client
               ParallelProtocol protocol = new ParallelProtocol(world, socket);

               protocol.processInput(inputLine = in.readLine());
               outputLine = protocol.processInput(inputLine);

               protocol.sendMessage(outputLine);

               if (outputLine.equals("Done\n"))
               {
                  System.out.println("GoodBye");
                  protocol.out.close();
                  return;
               }
            }
            catch (IOException ex)
            {
               ex.printStackTrace();
            }
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public static void sendMessage(String message, DataOutputStream os) throws IOException
   {
      byte[] buffer = message.getBytes();
      int bytes = 0;

      // Copy requested file into the socket's output stream.
      bytes = buffer.length;
      os.write(buffer, 0, bytes);
      os.flush();
   }
}
