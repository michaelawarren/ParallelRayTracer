package com.personal.parallelraytracer.cluster;

import java.util.concurrent.Callable;
import org.json.JSONArray;

public abstract class RowRunnable implements Callable<JSONArray>
{
   protected int row;

   public RowRunnable(int row)
   {
      this.row = row;
   }
}
