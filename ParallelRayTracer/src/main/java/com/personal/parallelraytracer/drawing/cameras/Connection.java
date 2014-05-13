package com.personal.parallelraytracer.drawing.cameras;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection
{
   Socket socket;
   InputStream is;
   InputStreamReader ir;
   BufferedReader bufferedReader;
   DataOutputStream os;

   public Connection(Socket socket) throws IOException
   {
      this.socket = socket;
      is = this.socket.getInputStream();
      ir = new InputStreamReader(is);
      bufferedReader = new BufferedReader(ir);

      os = new DataOutputStream(this.socket.getOutputStream());
   }

   public void sendMessage(String message) throws IOException
   {
      byte[] buffer = message.getBytes();
      int bytes = 0;

      // Copy requested file into the socket's output stream.
      bytes = buffer.length;
      os.write(buffer, 0, bytes);
      os.flush();
   }

   public String readLine() throws IOException
   {
      return bufferedReader.readLine();
   }

   public void close() throws IOException
   {
      socket.close();
   }
}
