package com.unity3d.player;

import android.app.Presentation;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.hardware.display.DisplayManager.DisplayListener;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public final class k
  implements g
{
  private Object a = new Object[0];
  private Presentation b;
  private DisplayManager.DisplayListener c;
  
  public final void a(Context paramContext)
  {
    if (this.c == null) {}
    for (;;)
    {
      return;
      paramContext = (DisplayManager)paramContext.getSystemService("display");
      if (paramContext != null) {
        paramContext.unregisterDisplayListener(this.c);
      }
    }
  }
  
  public final void a(final UnityPlayer paramUnityPlayer, Context paramContext)
  {
    paramContext = (DisplayManager)paramContext.getSystemService("display");
    if (paramContext == null) {}
    for (;;)
    {
      return;
      paramContext.registerDisplayListener(new DisplayManager.DisplayListener()
      {
        public final void onDisplayAdded(int paramAnonymousInt)
        {
          paramUnityPlayer.displayChanged(-1, null);
        }
        
        public final void onDisplayChanged(int paramAnonymousInt)
        {
          paramUnityPlayer.displayChanged(-1, null);
        }
        
        public final void onDisplayRemoved(int paramAnonymousInt)
        {
          paramUnityPlayer.displayChanged(-1, null);
        }
      }, null);
    }
  }
  
  public final boolean a(UnityPlayer paramUnityPlayer, Context paramContext, int paramInt)
  {
    synchronized (this.a)
    {
      Object localObject2;
      if ((this.b != null) && (this.b.isShowing()))
      {
        localObject2 = this.b.getDisplay();
        if ((localObject2 != null) && (((Display)localObject2).getDisplayId() == paramInt)) {
          bool = true;
        }
      }
      for (;;)
      {
        return bool;
        localObject2 = (DisplayManager)paramContext.getSystemService("display");
        if (localObject2 == null)
        {
          bool = false;
        }
        else
        {
          localObject2 = ((DisplayManager)localObject2).getDisplay(paramInt);
          if (localObject2 != null) {
            break;
          }
          bool = false;
        }
      }
      Runnable local2 = new com/unity3d/player/k$2;
      local2.<init>(this, paramContext, (Display)localObject2, paramUnityPlayer);
      paramUnityPlayer.b(local2);
      boolean bool = true;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\unity3d\player\k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */