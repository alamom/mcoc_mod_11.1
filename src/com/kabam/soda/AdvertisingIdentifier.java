package com.kabam.soda;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AdvertisingIdentifier
{
  public static final String TAG = AdvertisingIdentifier.class.getSimpleName();
  private static AdvertisingIdentifier adId;
  private static final Lock lock = new ReentrantLock(true);
  private final String id;
  private final Type type;
  
  private AdvertisingIdentifier(Type paramType, String paramString)
  {
    this.type = paramType;
    this.id = paramString;
  }
  
  /* Error */
  public static AdvertisingIdentifier getInstance(android.content.Context paramContext)
  {
    // Byte code:
    //   0: getstatic 54	com/kabam/soda/AdvertisingIdentifier:adId	Lcom/kabam/soda/AdvertisingIdentifier;
    //   3: ifnull +15 -> 18
    //   6: getstatic 54	com/kabam/soda/AdvertisingIdentifier:adId	Lcom/kabam/soda/AdvertisingIdentifier;
    //   9: invokevirtual 58	com/kabam/soda/AdvertisingIdentifier:getType	()Lcom/kabam/soda/AdvertisingIdentifier$Type;
    //   12: getstatic 61	com/kabam/soda/AdvertisingIdentifier$Type:ANDROID_ID	Lcom/kabam/soda/AdvertisingIdentifier$Type;
    //   15: if_acmpne +91 -> 106
    //   18: getstatic 34	com/kabam/soda/AdvertisingIdentifier:lock	Ljava/util/concurrent/locks/Lock;
    //   21: invokeinterface 65 1 0
    //   26: getstatic 54	com/kabam/soda/AdvertisingIdentifier:adId	Lcom/kabam/soda/AdvertisingIdentifier;
    //   29: ifnonnull +29 -> 58
    //   32: aload_0
    //   33: invokevirtual 71	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   36: ldc 73
    //   38: invokestatic 79	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   41: astore_1
    //   42: new 2	com/kabam/soda/AdvertisingIdentifier
    //   45: astore_2
    //   46: aload_2
    //   47: getstatic 61	com/kabam/soda/AdvertisingIdentifier$Type:ANDROID_ID	Lcom/kabam/soda/AdvertisingIdentifier$Type;
    //   50: aload_1
    //   51: invokespecial 81	com/kabam/soda/AdvertisingIdentifier:<init>	(Lcom/kabam/soda/AdvertisingIdentifier$Type;Ljava/lang/String;)V
    //   54: aload_2
    //   55: putstatic 54	com/kabam/soda/AdvertisingIdentifier:adId	Lcom/kabam/soda/AdvertisingIdentifier;
    //   58: getstatic 54	com/kabam/soda/AdvertisingIdentifier:adId	Lcom/kabam/soda/AdvertisingIdentifier;
    //   61: invokevirtual 58	com/kabam/soda/AdvertisingIdentifier:getType	()Lcom/kabam/soda/AdvertisingIdentifier$Type;
    //   64: astore_2
    //   65: getstatic 61	com/kabam/soda/AdvertisingIdentifier$Type:ANDROID_ID	Lcom/kabam/soda/AdvertisingIdentifier$Type;
    //   68: astore_1
    //   69: aload_2
    //   70: aload_1
    //   71: if_acmpne +27 -> 98
    //   74: aload_0
    //   75: invokestatic 87	com/google/android/gms/ads/identifier/AdvertisingIdClient:getAdvertisingIdInfo	(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
    //   78: invokevirtual 92	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:getId	()Ljava/lang/String;
    //   81: astore_1
    //   82: new 2	com/kabam/soda/AdvertisingIdentifier
    //   85: astore_0
    //   86: aload_0
    //   87: getstatic 95	com/kabam/soda/AdvertisingIdentifier$Type:GOOGLEPLAY_ADVERTISING_ID	Lcom/kabam/soda/AdvertisingIdentifier$Type;
    //   90: aload_1
    //   91: invokespecial 81	com/kabam/soda/AdvertisingIdentifier:<init>	(Lcom/kabam/soda/AdvertisingIdentifier$Type;Ljava/lang/String;)V
    //   94: aload_0
    //   95: putstatic 54	com/kabam/soda/AdvertisingIdentifier:adId	Lcom/kabam/soda/AdvertisingIdentifier;
    //   98: getstatic 34	com/kabam/soda/AdvertisingIdentifier:lock	Ljava/util/concurrent/locks/Lock;
    //   101: invokeinterface 98 1 0
    //   106: getstatic 54	com/kabam/soda/AdvertisingIdentifier:adId	Lcom/kabam/soda/AdvertisingIdentifier;
    //   109: areturn
    //   110: astore_0
    //   111: getstatic 26	com/kabam/soda/AdvertisingIdentifier:TAG	Ljava/lang/String;
    //   114: ldc 100
    //   116: invokestatic 106	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   119: pop
    //   120: goto -22 -> 98
    //   123: astore_0
    //   124: getstatic 34	com/kabam/soda/AdvertisingIdentifier:lock	Ljava/util/concurrent/locks/Lock;
    //   127: invokeinterface 98 1 0
    //   132: aload_0
    //   133: athrow
    //   134: astore_0
    //   135: getstatic 26	com/kabam/soda/AdvertisingIdentifier:TAG	Ljava/lang/String;
    //   138: ldc 108
    //   140: invokestatic 106	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   143: pop
    //   144: goto -46 -> 98
    //   147: astore_0
    //   148: getstatic 26	com/kabam/soda/AdvertisingIdentifier:TAG	Ljava/lang/String;
    //   151: ldc 110
    //   153: invokestatic 113	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   156: pop
    //   157: aload_0
    //   158: athrow
    //   159: astore_2
    //   160: getstatic 26	com/kabam/soda/AdvertisingIdentifier:TAG	Ljava/lang/String;
    //   163: astore_0
    //   164: new 115	java/lang/StringBuilder
    //   167: astore_1
    //   168: aload_1
    //   169: invokespecial 116	java/lang/StringBuilder:<init>	()V
    //   172: aload_0
    //   173: aload_1
    //   174: ldc 118
    //   176: invokevirtual 122	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: aload_2
    //   180: invokevirtual 125	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   183: invokevirtual 128	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   186: invokestatic 113	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   189: pop
    //   190: goto -92 -> 98
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	193	0	paramContext	android.content.Context
    //   41	133	1	localObject1	Object
    //   45	25	2	localObject2	Object
    //   159	21	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   74	98	110	com/google/android/gms/common/GooglePlayServicesNotAvailableException
    //   26	58	123	finally
    //   58	69	123	finally
    //   74	98	123	finally
    //   111	120	123	finally
    //   135	144	123	finally
    //   148	159	123	finally
    //   160	190	123	finally
    //   74	98	134	com/google/android/gms/common/GooglePlayServicesRepairableException
    //   74	98	147	java/lang/IllegalStateException
    //   74	98	159	java/lang/Exception
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public Type getType()
  {
    return this.type;
  }
  
  public String toString()
  {
    if (this.id == null) {}
    for (String str = "";; str = this.id) {
      return str;
    }
  }
  
  public static enum Type
  {
    ANDROID_ID,  GOOGLEPLAY_ADVERTISING_ID;
    
    private Type() {}
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\AdvertisingIdentifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */