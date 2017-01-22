package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class GoogleCloudMessaging
{
  public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
  public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
  public static final String MESSAGE_TYPE_DELETED = "deleted_messages";
  public static final String MESSAGE_TYPE_MESSAGE = "gcm";
  public static final String MESSAGE_TYPE_SEND_ERROR = "send_error";
  static GoogleCloudMessaging adv;
  private PendingIntent adw;
  final BlockingQueue<Intent> adx = new LinkedBlockingQueue();
  private Handler ady = new Handler(Looper.getMainLooper())
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      paramAnonymousMessage = (Intent)paramAnonymousMessage.obj;
      GoogleCloudMessaging.this.adx.add(paramAnonymousMessage);
    }
  };
  private Messenger adz = new Messenger(this.ady);
  private Context lB;
  
  private void a(String paramString1, String paramString2, long paramLong, int paramInt, Bundle paramBundle)
    throws IOException
  {
    if (Looper.getMainLooper() == Looper.myLooper()) {
      throw new IOException("MAIN_THREAD");
    }
    if (paramString1 == null) {
      throw new IllegalArgumentException("Missing 'to'");
    }
    Intent localIntent = new Intent("com.google.android.gcm.intent.SEND");
    localIntent.putExtras(paramBundle);
    j(localIntent);
    localIntent.setPackage("com.google.android.gms");
    localIntent.putExtra("google.to", paramString1);
    localIntent.putExtra("google.message_id", paramString2);
    localIntent.putExtra("google.ttl", Long.toString(paramLong));
    localIntent.putExtra("google.delay", Integer.toString(paramInt));
    this.lB.sendOrderedBroadcast(localIntent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
  }
  
  private void d(String... paramVarArgs)
  {
    paramVarArgs = e(paramVarArgs);
    Intent localIntent = new Intent("com.google.android.c2dm.intent.REGISTER");
    localIntent.setPackage("com.google.android.gms");
    localIntent.putExtra("google.messenger", this.adz);
    j(localIntent);
    localIntent.putExtra("sender", paramVarArgs);
    this.lB.startService(localIntent);
  }
  
  public static GoogleCloudMessaging getInstance(Context paramContext)
  {
    try
    {
      if (adv == null)
      {
        GoogleCloudMessaging localGoogleCloudMessaging = new com/google/android/gms/gcm/GoogleCloudMessaging;
        localGoogleCloudMessaging.<init>();
        adv = localGoogleCloudMessaging;
        adv.lB = paramContext;
      }
      paramContext = adv;
      return paramContext;
    }
    finally {}
  }
  
  private void lN()
  {
    Intent localIntent = new Intent("com.google.android.c2dm.intent.UNREGISTER");
    localIntent.setPackage("com.google.android.gms");
    this.adx.clear();
    localIntent.putExtra("google.messenger", this.adz);
    j(localIntent);
    this.lB.startService(localIntent);
  }
  
  public void close()
  {
    lO();
  }
  
  String e(String... paramVarArgs)
  {
    if ((paramVarArgs == null) || (paramVarArgs.length == 0)) {
      throw new IllegalArgumentException("No senderIds");
    }
    StringBuilder localStringBuilder = new StringBuilder(paramVarArgs[0]);
    for (int i = 1; i < paramVarArgs.length; i++) {
      localStringBuilder.append(',').append(paramVarArgs[i]);
    }
    return localStringBuilder.toString();
  }
  
  public String getMessageType(Intent paramIntent)
  {
    if (!"com.google.android.c2dm.intent.RECEIVE".equals(paramIntent.getAction())) {
      paramIntent = null;
    }
    for (;;)
    {
      return paramIntent;
      String str = paramIntent.getStringExtra("message_type");
      paramIntent = str;
      if (str == null) {
        paramIntent = "gcm";
      }
    }
  }
  
  void j(Intent paramIntent)
  {
    try
    {
      if (this.adw == null)
      {
        Intent localIntent = new android/content/Intent;
        localIntent.<init>();
        localIntent.setPackage("com.google.example.invalidpackage");
        this.adw = PendingIntent.getBroadcast(this.lB, 0, localIntent, 0);
      }
      paramIntent.putExtra("app", this.adw);
      return;
    }
    finally {}
  }
  
  void lO()
  {
    try
    {
      if (this.adw != null)
      {
        this.adw.cancel();
        this.adw = null;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String register(String... paramVarArgs)
    throws IOException
  {
    if (Looper.getMainLooper() == Looper.myLooper()) {
      throw new IOException("MAIN_THREAD");
    }
    this.adx.clear();
    d(paramVarArgs);
    try
    {
      localObject = (Intent)this.adx.poll(5000L, TimeUnit.MILLISECONDS);
      if (localObject == null)
      {
        paramVarArgs = new java/io/IOException;
        paramVarArgs.<init>("SERVICE_NOT_AVAILABLE");
        throw paramVarArgs;
      }
    }
    catch (InterruptedException paramVarArgs)
    {
      throw new IOException(paramVarArgs.getMessage());
    }
    paramVarArgs = ((Intent)localObject).getStringExtra("registration_id");
    if (paramVarArgs != null) {
      return paramVarArgs;
    }
    ((Intent)localObject).getStringExtra("error");
    Object localObject = ((Intent)localObject).getStringExtra("error");
    if (localObject != null)
    {
      paramVarArgs = new java/io/IOException;
      paramVarArgs.<init>((String)localObject);
      throw paramVarArgs;
    }
    paramVarArgs = new java/io/IOException;
    paramVarArgs.<init>("SERVICE_NOT_AVAILABLE");
    throw paramVarArgs;
  }
  
  public void send(String paramString1, String paramString2, long paramLong, Bundle paramBundle)
    throws IOException
  {
    a(paramString1, paramString2, paramLong, -1, paramBundle);
  }
  
  public void send(String paramString1, String paramString2, Bundle paramBundle)
    throws IOException
  {
    send(paramString1, paramString2, -1L, paramBundle);
  }
  
  public void unregister()
    throws IOException
  {
    if (Looper.getMainLooper() == Looper.myLooper()) {
      throw new IOException("MAIN_THREAD");
    }
    lN();
    try
    {
      Object localObject1 = (Intent)this.adx.poll(5000L, TimeUnit.MILLISECONDS);
      if (localObject1 == null)
      {
        localObject1 = new java/io/IOException;
        ((IOException)localObject1).<init>("SERVICE_NOT_AVAILABLE");
        throw ((Throwable)localObject1);
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      throw new IOException(localInterruptedException.getMessage());
    }
    if (localInterruptedException.getStringExtra("unregistered") != null) {
      return;
    }
    Object localObject2 = localInterruptedException.getStringExtra("error");
    if (localObject2 != null)
    {
      IOException localIOException = new java/io/IOException;
      localIOException.<init>((String)localObject2);
      throw localIOException;
    }
    localObject2 = new java/io/IOException;
    ((IOException)localObject2).<init>("SERVICE_NOT_AVAILABLE");
    throw ((Throwable)localObject2);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\gcm\GoogleCloudMessaging.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */