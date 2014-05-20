package com.personal.parallelraytracer.cluster;

import java.util.concurrent.Callable;
import org.json.JSONArray;

public abstract class RowRunnable implements Callable<JSONArray>
{
   protected int row;
   protected int height;

   public RowRunnable(int row, int height)
   {
      this.row = row;
      this.height = height;
   }
}
