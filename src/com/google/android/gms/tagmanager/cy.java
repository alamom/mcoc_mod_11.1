package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

class cy
  extends cx
{
  private static cy arA;
  private static final Object yc = new Object();
  private Context arq;
  private at arr;
  private volatile ar ars;
  private int art = 1800000;
  private boolean aru = true;
  private boolean arv = false;
  private boolean arw = true;
  private au arx = new au()
  {
    public void z(boolean paramAnonymousBoolean)
    {
      cy.this.a(paramAnonymousBoolean, cy.a(cy.this));
    }
  };
  private bo ary;
  private boolean arz = false;
  private boolean connected = true;
  private Handler handler;
  
  private void ea()
  {
    this.ary = new bo(this);
    this.ary.z(this.arq);
  }
  
  private void eb()
  {
    this.handler = new Handler(this.arq.getMainLooper(), new Handler.Callback()
    {
      public boolean handleMessage(Message paramAnonymousMessage)
      {
        if ((1 == paramAnonymousMessage.what) && (cy.ee().equals(paramAnonymousMessage.obj)))
        {
          cy.this.dispatch();
          if ((cy.b(cy.this) > 0) && (!cy.c(cy.this))) {
            cy.d(cy.this).sendMessageDelayed(cy.d(cy.this).obtainMessage(1, cy.ee()), cy.b(cy.this));
          }
        }
        return true;
      }
    });
    if (this.art > 0) {
      this.handler.sendMessageDelayed(this.handler.obtainMessage(1, yc), this.art);
    }
  }
  
  public static cy pw()
  {
    if (arA == null) {
      arA = new cy();
    }
    return arA;
  }
  
  void A(boolean paramBoolean)
  {
    try
    {
      a(this.arz, paramBoolean);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  void a(Context paramContext, ar paramar)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 86	com/google/android/gms/tagmanager/cy:arq	Landroid/content/Context;
    //   6: astore_3
    //   7: aload_3
    //   8: ifnull +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: aload_1
    //   16: invokevirtual 128	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   19: putfield 86	com/google/android/gms/tagmanager/cy:arq	Landroid/content/Context;
    //   22: aload_0
    //   23: getfield 130	com/google/android/gms/tagmanager/cy:ars	Lcom/google/android/gms/tagmanager/ar;
    //   26: ifnonnull -15 -> 11
    //   29: aload_0
    //   30: aload_2
    //   31: putfield 130	com/google/android/gms/tagmanager/cy:ars	Lcom/google/android/gms/tagmanager/ar;
    //   34: goto -23 -> 11
    //   37: astore_1
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	42	0	this	cy
    //   0	42	1	paramContext	Context
    //   0	42	2	paramar	ar
    //   6	2	3	localContext	Context
    // Exception table:
    //   from	to	target	type
    //   2	7	37	finally
    //   14	34	37	finally
  }
  
  void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    for (;;)
    {
      try
      {
        if (this.arz == paramBoolean1)
        {
          boolean bool = this.connected;
          if (bool == paramBoolean2) {
            return;
          }
        }
        if (((paramBoolean1) || (!paramBoolean2)) && (this.art > 0)) {
          this.handler.removeMessages(1, yc);
        }
        if ((!paramBoolean1) && (paramBoolean2) && (this.art > 0)) {
          this.handler.sendMessageDelayed(this.handler.obtainMessage(1, yc), this.art);
        }
        Object localObject1 = new java/lang/StringBuilder;
        ((StringBuilder)localObject1).<init>();
        StringBuilder localStringBuilder = ((StringBuilder)localObject1).append("PowerSaveMode ");
        if ((paramBoolean1) || (!paramBoolean2))
        {
          localObject1 = "initiated.";
          bh.V((String)localObject1);
          this.arz = paramBoolean1;
          this.connected = paramBoolean2;
        }
        else
        {
          String str = "terminated.";
        }
      }
      finally {}
    }
  }
  
  /* Error */
  public void dispatch()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 52	com/google/android/gms/tagmanager/cy:arv	Z
    //   6: ifne +16 -> 22
    //   9: ldc -96
    //   11: invokestatic 155	com/google/android/gms/tagmanager/bh:V	(Ljava/lang/String;)V
    //   14: aload_0
    //   15: iconst_1
    //   16: putfield 50	com/google/android/gms/tagmanager/cy:aru	Z
    //   19: aload_0
    //   20: monitorexit
    //   21: return
    //   22: aload_0
    //   23: getfield 130	com/google/android/gms/tagmanager/cy:ars	Lcom/google/android/gms/tagmanager/ar;
    //   26: astore_2
    //   27: new 10	com/google/android/gms/tagmanager/cy$3
    //   30: astore_1
    //   31: aload_1
    //   32: aload_0
    //   33: invokespecial 161	com/google/android/gms/tagmanager/cy$3:<init>	(Lcom/google/android/gms/tagmanager/cy;)V
    //   36: aload_2
    //   37: aload_1
    //   38: invokeinterface 166 2 0
    //   43: goto -24 -> 19
    //   46: astore_1
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_1
    //   50: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	51	0	this	cy
    //   30	8	1	local3	3
    //   46	4	1	localObject	Object
    //   26	11	2	localar	ar
    // Exception table:
    //   from	to	target	type
    //   2	19	46	finally
    //   22	43	46	finally
  }
  
  void ed()
  {
    try
    {
      if ((!this.arz) && (this.connected) && (this.art > 0))
      {
        this.handler.removeMessages(1, yc);
        this.handler.sendMessage(this.handler.obtainMessage(1, yc));
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  at px()
  {
    try
    {
      if (this.arr != null) {
        break label54;
      }
      if (this.arq == null)
      {
        IllegalStateException localIllegalStateException = new java/lang/IllegalStateException;
        localIllegalStateException.<init>("Cant get a store unless we have a context");
        throw localIllegalStateException;
      }
    }
    finally {}
    Object localObject2 = new com/google/android/gms/tagmanager/cb;
    ((cb)localObject2).<init>(this.arx, this.arq);
    this.arr = ((at)localObject2);
    label54:
    if (this.handler == null) {
      eb();
    }
    this.arv = true;
    if (this.aru)
    {
      dispatch();
      this.aru = false;
    }
    if ((this.ary == null) && (this.arw)) {
      ea();
    }
    localObject2 = this.arr;
    return (at)localObject2;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\cy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */