package com.playground.playground;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class RandomAccessImage
{
   public final static String FILEPATH = "image.png";

   public static void main(String[] args)
   {
      try
      {
         File outputfile = new File(FILEPATH);
         BufferedImage image = new BufferedImage(500, 500,
             BufferedImage.TYPE_INT_RGB);

         for (int i = 0; i < 500; i++)
         {
            for (int j = 0; j < 500; j++)
            {
               image.setRGB(j, i, Color.GREEN.getRGB());
            }
         }

         ImageIO.write(image, "png", outputfile);

         RandomAccessFile file = new RandomAccessFile(FILEPATH, "rw");
         final ImageOutputStream imageOut = ImageIO.createImageOutputStream(file);
         
         final int blue = Color.BLUE.getRGB();
         BufferedImage image2 = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
         for (int y = 0; y < 200; y++)
         {
            for (int x = 0; x < 200; x++)
            {
               image2.setRGB(x, y,  Color.BLUE.getRGB());
            }
         }
         final Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByFormatName("png");
         
         
         
         imageOut.flush();
         imageOut.close();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

   }

   private static void writeToFile(String filePath, String data, int position)
       throws IOException
   {

      RandomAccessFile file = new RandomAccessFile(filePath, "rw");
      file.seek(position);
      file.write(data.getBytes());
      file.close();
   }
}
