package com.playground.raytracerserver;

import com.personal.parallelraytracer.drawing.RGBColor;
import com.personal.parallelraytracer.drawing.World;
import com.personal.parallelraytracer.drawing.cameras.PinHoleWorker;
import com.personal.parallelraytracer.math.Point;
import com.personal.parallelraytracer.math.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
               JSONObject jSONObject = new JSONObject(input);
               if (jSONObject.has("initialize"))
                  return "Done";
               state = State.WAITING;
               world.setRequiermentScene(
                   new PinHoleWorker(850.0d, 1, new Point(100, 100, 100),
                       new Point(-5, 0, 0),
                       new Vector(1, 1, 0), 1, "Single.png",
                       jSONObject.getInt("numThreads")),
                   jSONObject.getInt("width"),
                   jSONObject.getInt("height"));
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
            if (input.contains("uninitialize"))
               return "Done";
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
