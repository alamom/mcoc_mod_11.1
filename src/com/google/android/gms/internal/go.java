package com.google.android.gms.internal;

import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@ez
public class go
{
  public static final a<Void> wy = new a()
  {
    public Void c(InputStream paramAnonymousInputStream)
    {
      return null;
    }
    
    public Void dq()
    {
      return null;
    }
  };
  
  public <T> Future<T> a(final String paramString, final a<T> parama)
  {
    gi.submit(new Callable()
    {
      /* Error */
      public T call()
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 6
        //   3: aconst_null
        //   4: astore_3
        //   5: aconst_null
        //   6: astore 5
        //   8: aload_3
        //   9: astore_2
        //   10: new 38	java/net/URL
        //   13: astore 4
        //   15: aload_3
        //   16: astore_2
        //   17: aload 4
        //   19: aload_0
        //   20: getfield 24	com/google/android/gms/internal/go$2:wz	Ljava/lang/String;
        //   23: invokespecial 41	java/net/URL:<init>	(Ljava/lang/String;)V
        //   26: aload_3
        //   27: astore_2
        //   28: aload 4
        //   30: invokevirtual 45	java/net/URL:openConnection	()Ljava/net/URLConnection;
        //   33: checkcast 47	java/net/HttpURLConnection
        //   36: astore_3
        //   37: aload_3
        //   38: invokevirtual 50	java/net/HttpURLConnection:connect	()V
        //   41: aload_3
        //   42: invokevirtual 54	java/net/HttpURLConnection:getResponseCode	()I
        //   45: istore_1
        //   46: iload_1
        //   47: sipush 200
        //   50: if_icmplt +34 -> 84
        //   53: iload_1
        //   54: sipush 299
        //   57: if_icmpgt +27 -> 84
        //   60: aload_0
        //   61: getfield 26	com/google/android/gms/internal/go$2:wA	Lcom/google/android/gms/internal/go$a;
        //   64: aload_3
        //   65: invokevirtual 58	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
        //   68: invokeinterface 64 2 0
        //   73: astore_2
        //   74: aload_3
        //   75: ifnull +7 -> 82
        //   78: aload_3
        //   79: invokevirtual 67	java/net/HttpURLConnection:disconnect	()V
        //   82: aload_2
        //   83: areturn
        //   84: aload_3
        //   85: ifnull +7 -> 92
        //   88: aload_3
        //   89: invokevirtual 67	java/net/HttpURLConnection:disconnect	()V
        //   92: aload_0
        //   93: getfield 26	com/google/android/gms/internal/go$2:wA	Lcom/google/android/gms/internal/go$a;
        //   96: invokeinterface 70 1 0
        //   101: astore_2
        //   102: goto -20 -> 82
        //   105: astore 4
        //   107: aload 5
        //   109: astore_3
        //   110: aload_3
        //   111: astore_2
        //   112: ldc 72
        //   114: aload 4
        //   116: invokestatic 78	com/google/android/gms/internal/gs:d	(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   119: aload_3
        //   120: ifnull -28 -> 92
        //   123: aload_3
        //   124: invokevirtual 67	java/net/HttpURLConnection:disconnect	()V
        //   127: goto -35 -> 92
        //   130: astore 4
        //   132: aload 6
        //   134: astore_3
        //   135: aload_3
        //   136: astore_2
        //   137: ldc 72
        //   139: aload 4
        //   141: invokestatic 78	com/google/android/gms/internal/gs:d	(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   144: aload_3
        //   145: ifnull -53 -> 92
        //   148: aload_3
        //   149: invokevirtual 67	java/net/HttpURLConnection:disconnect	()V
        //   152: goto -60 -> 92
        //   155: astore 4
        //   157: aload_2
        //   158: ifnull +7 -> 165
        //   161: aload_2
        //   162: invokevirtual 67	java/net/HttpURLConnection:disconnect	()V
        //   165: aload 4
        //   167: athrow
        //   168: astore 4
        //   170: aload_3
        //   171: astore_2
        //   172: goto -15 -> 157
        //   175: astore 4
        //   177: goto -42 -> 135
        //   180: astore 4
        //   182: goto -72 -> 110
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	185	0	this	2
        //   45	13	1	i	int
        //   9	163	2	localObject1	Object
        //   4	167	3	localObject2	Object
        //   13	16	4	localURL	java.net.URL
        //   105	10	4	localMalformedURLException1	java.net.MalformedURLException
        //   130	10	4	localIOException1	java.io.IOException
        //   155	11	4	localObject3	Object
        //   168	1	4	localObject4	Object
        //   175	1	4	localIOException2	java.io.IOException
        //   180	1	4	localMalformedURLException2	java.net.MalformedURLException
        //   6	102	5	localObject5	Object
        //   1	132	6	localObject6	Object
        // Exception table:
        //   from	to	target	type
        //   10	15	105	java/net/MalformedURLException
        //   17	26	105	java/net/MalformedURLException
        //   28	37	105	java/net/MalformedURLException
        //   10	15	130	java/io/IOException
        //   17	26	130	java/io/IOException
        //   28	37	130	java/io/IOException
        //   10	15	155	finally
        //   17	26	155	finally
        //   28	37	155	finally
        //   112	119	155	finally
        //   137	144	155	finally
        //   37	46	168	finally
        //   60	74	168	finally
        //   37	46	175	java/io/IOException
        //   60	74	175	java/io/IOException
        //   37	46	180	java/net/MalformedURLException
        //   60	74	180	java/net/MalformedURLException
      }
    });
  }
  
  public static abstract interface a<T>
  {
    public abstract T b(InputStream paramInputStream);
    
    public abstract T cJ();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\go.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */