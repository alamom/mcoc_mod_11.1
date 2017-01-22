package android.support.v4.media.session;

import android.content.ComponentName;
import android.content.Context;
import android.media.AudioManager;

class MediaSessionCompatApi8
{
  public static void registerMediaButtonEventReceiver(Context paramContext, ComponentName paramComponentName)
  {
    ((AudioManager)paramContext.getSystemService("audio")).registerMediaButtonEventReceiver(paramComponentName);
  }
  
  public static void unregisterMediaButtonEventReceiver(Context paramContext, ComponentName paramComponentName)
  {
    ((AudioManager)paramContext.getSystemService("audio")).unregisterMediaButtonEventReceiver(paramComponentName);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\media\session\MediaSessionCompatApi8.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */