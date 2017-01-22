package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import com.google.android.gms.internal.mc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeofencingEvent
{
  private final int aep;
  private final List<Geofence> aeq;
  private final Location aer;
  private final int tc;
  
  private GeofencingEvent(int paramInt1, int paramInt2, List<Geofence> paramList, Location paramLocation)
  {
    this.tc = paramInt1;
    this.aep = paramInt2;
    this.aeq = paramList;
    this.aer = paramLocation;
  }
  
  public static GeofencingEvent fromIntent(Intent paramIntent)
  {
    if (paramIntent == null) {}
    for (paramIntent = null;; paramIntent = new GeofencingEvent(paramIntent.getIntExtra("gms_error_code", -1), getGeofenceTransition(paramIntent), getTriggeringGeofences(paramIntent), (Location)paramIntent.getParcelableExtra("com.google.android.location.intent.extra.triggering_location"))) {
      return paramIntent;
    }
  }
  
  private static int getGeofenceTransition(Intent paramIntent)
  {
    int i = -1;
    int j = paramIntent.getIntExtra("com.google.android.location.intent.extra.transition", -1);
    if (j == -1) {}
    for (;;)
    {
      return i;
      if ((j == 1) || (j == 2) || (j == 4)) {
        i = j;
      }
    }
  }
  
  private static List<Geofence> getTriggeringGeofences(Intent paramIntent)
  {
    Object localObject = (ArrayList)paramIntent.getSerializableExtra("com.google.android.location.intent.extra.geofence_list");
    if (localObject == null) {
      paramIntent = null;
    }
    for (;;)
    {
      return paramIntent;
      paramIntent = new ArrayList(((ArrayList)localObject).size());
      localObject = ((ArrayList)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramIntent.add(mc.h((byte[])((Iterator)localObject).next()));
      }
    }
  }
  
  public int getErrorCode()
  {
    return this.tc;
  }
  
  public int getGeofenceTransition()
  {
    return this.aep;
  }
  
  public List<Geofence> getTriggeringGeofences()
  {
    return this.aeq;
  }
  
  public Location getTriggeringLocation()
  {
    return this.aer;
  }
  
  public boolean hasError()
  {
    if (this.tc != -1) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\location\GeofencingEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */