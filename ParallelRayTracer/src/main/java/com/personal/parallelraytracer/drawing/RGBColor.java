package com.personal.parallelraytracer.drawing;

import java.awt.Color;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONStringer;

public class RGBColor extends Vector3D implements JSONString
{
   //Constance
   public static final RGBColor BLACK = new RGBColor(0, 0, 0);
   public static final RGBColor RED = new RGBColor(1, 0, 0);
   public static final RGBColor BLUE = new RGBColor(0, 0, 1);
   public static final RGBColor GREEN = new RGBColor(0, 1, 0);
   public static final RGBColor WHITE = new RGBColor(1, 1, 1);
   public static final RGBColor YELLOW = new RGBColor(1, 1, 0);

   // class stuff
   public RGBColor(double x, double y, double z)
   {
      super(x, y, z);
   }

   public RGBColor(Vector3D vector3D)
   {
      super(vector3D.toArray());
   }
   
   public RGBColor(JSONObject color) throws JSONException
   {
      super(color.getDouble("x"), color.getDouble("y"), color.getDouble("z"));
   }

   public RGBColor componmentMultiply(Vector3D rGBColor)
   {
      return new RGBColor(this.getX() * rGBColor.getX(),
          this.getY() * rGBColor.getY(), this.getZ() * rGBColor.getZ());
   }

   public Color getColor()
   {
      // if a color portion is greater than 1 make it one for now
      return new Color(
          getX() > 1.0d ? 1.0f : (float) getX(),
          getY() > 1.0d ? 1.0f : (float) getY(),
          getZ() > 1.0d ? 1.0f : (float) getZ());
   }

   @Override
   public String toJSONString()
   {
      try
      {
         return new JSONStringer()
             .object()
             .key("x")
             .value(getX())
             .key("y")
             .value(getY())
             .key("z")
             .value(getZ())
             .endObject()
             .toString();
      }
      catch (JSONException ex)
      {
         return "error: \n" + ex.toString();
      }
   }
}
