package com.playground.raytracerserver;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.drawing.cameras.PinHoleWorker;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONStringer;

class ParallelProtocol
{
   private State state = State.UNINITIALIZED;
   private World world;
   private final JSONStringer stringer = new JSONStringer();

   public ParallelProtocol(World world)
   {
      this.world = world;
   }

   public String processInput(String input)
   {
      switch (state)
      {
         case UNINITIALIZED:
         {
            try
            {
               state = State.WAITING;
               return stringer.object().key("status").value("initialized")
                   .endObject().toString();
            }
            catch (JSONException ex)
            {
               Logger.getLogger(ParallelProtocol.class.getName())
                   .log(Level.SEVERE, null, ex);
            }
         }
         case WAITING:
         {
            state = State.PROCESSING;
            try
            {
               JSONArray array = ((PinHoleWorker) world.getCamera())
                   .renderScene(world, new JSONArray(input));
               input = array.toString();
            }
            catch (JSONException ex)
            {
               input = "error: " + ex;
            }
            finally
            {
               state = State.WAITING;
            }
            // just display what we got
            return input;
         }
         case PROCESSING:
            return "processing";
         default:
            return "in a bad state please restart";
      }
   }

   private enum State
   {
      UNINITIALIZED,
      WAITING,
      PROCESSING,
   }
}
