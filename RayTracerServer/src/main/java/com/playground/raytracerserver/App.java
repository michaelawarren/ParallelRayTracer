package com.playground.raytracerserver;

import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.drawing.cameras.Camera;
import com.personal.parallelraytracer.drawing.cameras.PinHole;
import com.personal.parallelraytracer.drawing.cameras.PinHoleWorker;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Vector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
      try
      {

         
         int port = 6789;

         try (
             ServerSocket listenSocket = new ServerSocket(port);
             Socket socket = listenSocket.accept();
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket
                     .getInputStream()));)
         {
            String inputLine, outputLine;
            final World world = new World();
            // Initiate conversation with client
            ParallelProtocol kkp = new ParallelProtocol(world);
            
            while ((inputLine = in.readLine()) != null)
            {
               outputLine = kkp.processInput(inputLine);
               //System.out.println(outputLine);
               out.println(outputLine);
               if (outputLine.equals("Bye."))
               {
                  break;
               }
            }
         }
      }
      catch (IOException ex)
      {
         System.out.println(ex.toString());
      }
   }
   

}