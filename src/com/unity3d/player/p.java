package com.unity3d.player;

import android.app.Activity;
import android.content.ContextWrapper;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.View;
import android.view.Window;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class p
  implements j
{
  private final Queue a = new ConcurrentLinkedQueue();
  private final Activity b;
  private Runnable c = new Runnable()
  {
    private static void a(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
    {
      if (q.b) {
        q.j.a(paramAnonymousView, paramAnonymousMotionEvent);
      }
    }
    
    public final void run()
    {
      for (;;)
      {
        MotionEvent localMotionEvent = (MotionEvent)p.a(p.this).poll();
        if (localMotionEvent == null) {
          break;
        }
        View localView = p.b(p.this).getWindow().getDecorView();
        int i = localMotionEvent.getSource();
        if ((i & 0x2) != 0) {
          switch (localMotionEvent.getAction() & 0xFF)
          {
          default: 
            a(localView, localMotionEvent);
            break;
          case 0: 
          case 1: 
          case 2: 
          case 3: 
          case 4: 
          case 5: 
          case 6: 
            localView.dispatchTouchEvent(localMotionEvent);
            break;
          }
        } else if ((i & 0x4) != 0) {
          localView.dispatchTrackballEvent(localMotionEvent);
        } else {
          a(localView, localMotionEvent);
        }
      }
    }
  };
  
  public p(ContextWrapper paramContextWrapper)
  {
    this.b = ((Activity)paramContextWrapper);
  }
  
  private static int a(MotionEvent.PointerCoords[] paramArrayOfPointerCoords, float[] paramArrayOfFloat, int paramInt)
  {
    int j = 0;
    int i = paramInt;
    for (paramInt = j; paramInt < paramArrayOfPointerCoords.length; paramInt++)
    {
      MotionEvent.PointerCoords localPointerCoords = new MotionEvent.PointerCoords();
      paramArrayOfPointerCoords[paramInt] = localPointerCoords;
      j = i + 1;
      localPointerCoords.orientation = paramArrayOfFloat[i];
      i = j + 1;
      localPointerCoords.pressure = paramArrayOfFloat[j];
      j = i + 1;
      localPointerCoords.size = paramArrayOfFloat[i];
      i = j + 1;
      localPointerCoords.toolMajor = paramArrayOfFloat[j];
      j = i + 1;
      localPointerCoords.toolMinor = paramArrayOfFloat[i];
      i = j + 1;
      localPointerCoords.touchMajor = paramArrayOfFloat[j];
      int k = i + 1;
      localPointerCoords.touchMinor = paramArrayOfFloat[i];
      j = k + 1;
      localPointerCoords.x = paramArrayOfFloat[k];
      i = j + 1;
      localPointerCoords.y = paramArrayOfFloat[j];
    }
    return i;
  }
  
  private static MotionEvent.PointerCoords[] a(int paramInt, float[] paramArrayOfFloat)
  {
    MotionEvent.PointerCoords[] arrayOfPointerCoords = new MotionEvent.PointerCoords[paramInt];
    a(arrayOfPointerCoords, paramArrayOfFloat, 0);
    return arrayOfPointerCoords;
  }
  
  public final void a(long paramLong1, long paramLong2, int paramInt1, int paramInt2, int[] paramArrayOfInt, float[] paramArrayOfFloat1, int paramInt3, float paramFloat1, float paramFloat2, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long[] paramArrayOfLong, float[] paramArrayOfFloat2)
  {
    if (this.b != null)
    {
      paramArrayOfFloat1 = MotionEvent.obtain(paramLong1, paramLong2, paramInt1, paramInt2, paramArrayOfInt, a(paramInt2, paramArrayOfFloat1), paramInt3, paramFloat1, paramFloat2, paramInt4, paramInt5, paramInt6, paramInt7);
      paramInt1 = 0;
      for (paramInt4 = 0; paramInt4 < paramInt8; paramInt4++)
      {
        paramArrayOfInt = new MotionEvent.PointerCoords[paramInt2];
        paramInt1 = a(paramArrayOfInt, paramArrayOfFloat2, paramInt1);
        paramArrayOfFloat1.addBatch(paramArrayOfLong[paramInt4], paramArrayOfInt, paramInt3);
      }
      this.a.add(paramArrayOfFloat1);
      this.b.runOnUiThread(this.c);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\unity3d\player\p.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */