package android.support.v4.os;

import android.os.Build.VERSION;

public final class CancellationSignal
{
  private boolean mCancelInProgress;
  private Object mCancellationSignalObj;
  private boolean mIsCanceled;
  private OnCancelListener mOnCancelListener;
  
  private void waitForCancelFinishedLocked()
  {
    while (this.mCancelInProgress) {
      try
      {
        wait();
      }
      catch (InterruptedException localInterruptedException) {}
    }
  }
  
  /* Error */
  public void cancel()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 30	android/support/v4/os/CancellationSignal:mIsCanceled	Z
    //   6: ifeq +6 -> 12
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: aload_0
    //   13: iconst_1
    //   14: putfield 30	android/support/v4/os/CancellationSignal:mIsCanceled	Z
    //   17: aload_0
    //   18: iconst_1
    //   19: putfield 24	android/support/v4/os/CancellationSignal:mCancelInProgress	Z
    //   22: aload_0
    //   23: getfield 32	android/support/v4/os/CancellationSignal:mOnCancelListener	Landroid/support/v4/os/CancellationSignal$OnCancelListener;
    //   26: astore_2
    //   27: aload_0
    //   28: getfield 34	android/support/v4/os/CancellationSignal:mCancellationSignalObj	Ljava/lang/Object;
    //   31: astore_1
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_2
    //   35: ifnull +9 -> 44
    //   38: aload_2
    //   39: invokeinterface 37 1 0
    //   44: aload_1
    //   45: ifnull +7 -> 52
    //   48: aload_1
    //   49: invokestatic 42	android/support/v4/os/CancellationSignalCompatJellybean:cancel	(Ljava/lang/Object;)V
    //   52: aload_0
    //   53: monitorenter
    //   54: aload_0
    //   55: iconst_0
    //   56: putfield 24	android/support/v4/os/CancellationSignal:mCancelInProgress	Z
    //   59: aload_0
    //   60: invokevirtual 45	java/lang/Object:notifyAll	()V
    //   63: aload_0
    //   64: monitorexit
    //   65: goto -54 -> 11
    //   68: astore_1
    //   69: aload_0
    //   70: monitorexit
    //   71: aload_1
    //   72: athrow
    //   73: astore_1
    //   74: aload_0
    //   75: monitorexit
    //   76: aload_1
    //   77: athrow
    //   78: astore_1
    //   79: aload_0
    //   80: monitorenter
    //   81: aload_0
    //   82: iconst_0
    //   83: putfield 24	android/support/v4/os/CancellationSignal:mCancelInProgress	Z
    //   86: aload_0
    //   87: invokevirtual 45	java/lang/Object:notifyAll	()V
    //   90: aload_0
    //   91: monitorexit
    //   92: aload_1
    //   93: athrow
    //   94: astore_1
    //   95: aload_0
    //   96: monitorexit
    //   97: aload_1
    //   98: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	99	0	this	CancellationSignal
    //   31	18	1	localObject1	Object
    //   68	4	1	localObject2	Object
    //   73	4	1	localObject3	Object
    //   78	15	1	localObject4	Object
    //   94	4	1	localObject5	Object
    //   26	13	2	localOnCancelListener	OnCancelListener
    // Exception table:
    //   from	to	target	type
    //   54	65	68	finally
    //   69	71	68	finally
    //   2	11	73	finally
    //   12	34	73	finally
    //   74	76	73	finally
    //   38	44	78	finally
    //   48	52	78	finally
    //   81	92	94	finally
    //   95	97	94	finally
  }
  
  public Object getCancellationSignalObject()
  {
    Object localObject1;
    if (Build.VERSION.SDK_INT < 16) {
      localObject1 = null;
    }
    for (;;)
    {
      return localObject1;
      try
      {
        if (this.mCancellationSignalObj == null)
        {
          this.mCancellationSignalObj = CancellationSignalCompatJellybean.create();
          if (this.mIsCanceled) {
            CancellationSignalCompatJellybean.cancel(this.mCancellationSignalObj);
          }
        }
        localObject1 = this.mCancellationSignalObj;
      }
      finally {}
    }
  }
  
  public boolean isCanceled()
  {
    try
    {
      boolean bool = this.mIsCanceled;
      return bool;
    }
    finally {}
  }
  
  public void setOnCancelListener(OnCancelListener paramOnCancelListener)
  {
    for (;;)
    {
      try
      {
        waitForCancelFinishedLocked();
        if (this.mOnCancelListener == paramOnCancelListener) {
          return;
        }
        this.mOnCancelListener = paramOnCancelListener;
        if ((!this.mIsCanceled) || (paramOnCancelListener == null)) {
          continue;
        }
      }
      finally {}
      paramOnCancelListener.onCancel();
    }
  }
  
  public void throwIfCanceled()
  {
    if (isCanceled()) {
      throw new OperationCanceledException();
    }
  }
  
  public static abstract interface OnCancelListener
  {
    public abstract void onCancel();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\os\CancellationSignal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */