package com.google.android.gms.internal;

import android.content.Context;

@ez
public final class gq
  extends gg
{
  private final Context mContext;
  private final String mv;
  private final String uR;
  private String vW = null;
  
  public gq(Context paramContext, String paramString1, String paramString2)
  {
    this.mContext = paramContext;
    this.mv = paramString1;
    this.uR = paramString2;
  }
  
  public gq(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    this.mContext = paramContext;
    this.mv = paramString1;
    this.uR = paramString2;
    this.vW = paramString3;
  }
  
  /* Error */
  public void co()
  {
    // Byte code:
    //   0: new 33	java/lang/StringBuilder
    //   3: astore_2
    //   4: aload_2
    //   5: invokespecial 34	java/lang/StringBuilder:<init>	()V
    //   8: aload_2
    //   9: ldc 36
    //   11: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   14: aload_0
    //   15: getfield 24	com/google/android/gms/internal/gq:uR	Ljava/lang/String;
    //   18: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   24: invokestatic 50	com/google/android/gms/internal/gs:V	(Ljava/lang/String;)V
    //   27: new 52	java/net/URL
    //   30: astore_2
    //   31: aload_2
    //   32: aload_0
    //   33: getfield 24	com/google/android/gms/internal/gq:uR	Ljava/lang/String;
    //   36: invokespecial 54	java/net/URL:<init>	(Ljava/lang/String;)V
    //   39: aload_2
    //   40: invokevirtual 58	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   43: checkcast 60	java/net/HttpURLConnection
    //   46: astore_2
    //   47: aload_0
    //   48: getfield 18	com/google/android/gms/internal/gq:vW	Ljava/lang/String;
    //   51: ifnonnull +76 -> 127
    //   54: aload_0
    //   55: getfield 20	com/google/android/gms/internal/gq:mContext	Landroid/content/Context;
    //   58: aload_0
    //   59: getfield 22	com/google/android/gms/internal/gq:mv	Ljava/lang/String;
    //   62: iconst_1
    //   63: aload_2
    //   64: invokestatic 66	com/google/android/gms/internal/gj:a	(Landroid/content/Context;Ljava/lang/String;ZLjava/net/HttpURLConnection;)V
    //   67: aload_2
    //   68: invokevirtual 70	java/net/HttpURLConnection:getResponseCode	()I
    //   71: istore_1
    //   72: iload_1
    //   73: sipush 200
    //   76: if_icmplt +10 -> 86
    //   79: iload_1
    //   80: sipush 300
    //   83: if_icmplt +39 -> 122
    //   86: new 33	java/lang/StringBuilder
    //   89: astore_3
    //   90: aload_3
    //   91: invokespecial 34	java/lang/StringBuilder:<init>	()V
    //   94: aload_3
    //   95: ldc 72
    //   97: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: iload_1
    //   101: invokevirtual 75	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   104: ldc 77
    //   106: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload_0
    //   110: getfield 24	com/google/android/gms/internal/gq:uR	Ljava/lang/String;
    //   113: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   119: invokestatic 80	com/google/android/gms/internal/gs:W	(Ljava/lang/String;)V
    //   122: aload_2
    //   123: invokevirtual 83	java/net/HttpURLConnection:disconnect	()V
    //   126: return
    //   127: aload_0
    //   128: getfield 20	com/google/android/gms/internal/gq:mContext	Landroid/content/Context;
    //   131: aload_0
    //   132: getfield 22	com/google/android/gms/internal/gq:mv	Ljava/lang/String;
    //   135: iconst_1
    //   136: aload_2
    //   137: aload_0
    //   138: getfield 18	com/google/android/gms/internal/gq:vW	Ljava/lang/String;
    //   141: invokestatic 86	com/google/android/gms/internal/gj:a	(Landroid/content/Context;Ljava/lang/String;ZLjava/net/HttpURLConnection;Ljava/lang/String;)V
    //   144: goto -77 -> 67
    //   147: astore_3
    //   148: aload_2
    //   149: invokevirtual 83	java/net/HttpURLConnection:disconnect	()V
    //   152: aload_3
    //   153: athrow
    //   154: astore_2
    //   155: new 33	java/lang/StringBuilder
    //   158: dup
    //   159: invokespecial 34	java/lang/StringBuilder:<init>	()V
    //   162: ldc 88
    //   164: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: aload_0
    //   168: getfield 24	com/google/android/gms/internal/gq:uR	Ljava/lang/String;
    //   171: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: ldc 90
    //   176: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: aload_2
    //   180: invokevirtual 93	java/lang/IndexOutOfBoundsException:getMessage	()Ljava/lang/String;
    //   183: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   189: invokestatic 80	com/google/android/gms/internal/gs:W	(Ljava/lang/String;)V
    //   192: goto -66 -> 126
    //   195: astore_2
    //   196: new 33	java/lang/StringBuilder
    //   199: dup
    //   200: invokespecial 34	java/lang/StringBuilder:<init>	()V
    //   203: ldc 95
    //   205: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: aload_0
    //   209: getfield 24	com/google/android/gms/internal/gq:uR	Ljava/lang/String;
    //   212: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: ldc 90
    //   217: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: aload_2
    //   221: invokevirtual 96	java/io/IOException:getMessage	()Ljava/lang/String;
    //   224: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   230: invokestatic 80	com/google/android/gms/internal/gs:W	(Ljava/lang/String;)V
    //   233: goto -107 -> 126
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	236	0	this	gq
    //   71	30	1	i	int
    //   3	146	2	localObject1	Object
    //   154	26	2	localIndexOutOfBoundsException	IndexOutOfBoundsException
    //   195	26	2	localIOException	java.io.IOException
    //   89	6	3	localStringBuilder	StringBuilder
    //   147	6	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   47	67	147	finally
    //   67	72	147	finally
    //   86	122	147	finally
    //   127	144	147	finally
    //   0	47	154	java/lang/IndexOutOfBoundsException
    //   122	126	154	java/lang/IndexOutOfBoundsException
    //   148	154	154	java/lang/IndexOutOfBoundsException
    //   0	47	195	java/io/IOException
    //   122	126	195	java/io/IOException
    //   148	154	195	java/io/IOException
  }
  
  public void onStop() {}
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\gq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */