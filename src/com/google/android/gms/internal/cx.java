package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;
import java.util.Iterator;
import org.json.JSONObject;

@ez
public final class cx
  extends cu.a
{
  private final MediationAdapter qE;
  
  public cx(MediationAdapter paramMediationAdapter)
  {
    this.qE = paramMediationAdapter;
  }
  
  private Bundle a(String paramString1, int paramInt, String paramString2)
    throws RemoteException
  {
    gs.W("Server parameters: " + paramString1);
    Bundle localBundle;
    try
    {
      localBundle = new android/os/Bundle;
      localBundle.<init>();
      if (paramString1 != null)
      {
        JSONObject localJSONObject = new org/json/JSONObject;
        localJSONObject.<init>(paramString1);
        localBundle = new android/os/Bundle;
        localBundle.<init>();
        paramString1 = localJSONObject.keys();
        while (paramString1.hasNext())
        {
          String str = (String)paramString1.next();
          localBundle.putString(str, localJSONObject.getString(str));
        }
      }
      if (!(this.qE instanceof AdMobAdapter)) {
        break label141;
      }
    }
    catch (Throwable paramString1)
    {
      gs.d("Could not get Server Parameters Bundle.", paramString1);
      throw new RemoteException();
    }
    localBundle.putString("adJson", paramString2);
    localBundle.putInt("tagForChildDirectedTreatment", paramInt);
    label141:
    return localBundle;
  }
  
  public void a(d paramd, av paramav, String paramString, cv paramcv)
    throws RemoteException
  {
    a(paramd, paramav, paramString, null, paramcv);
  }
  
  /* Error */
  public void a(d paramd, av paramav, String paramString1, String paramString2, cv paramcv)
    throws RemoteException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 14	com/google/android/gms/internal/cx:qE	Lcom/google/android/gms/ads/mediation/MediationAdapter;
    //   4: instanceof 95
    //   7: ifne +42 -> 49
    //   10: new 23	java/lang/StringBuilder
    //   13: dup
    //   14: invokespecial 24	java/lang/StringBuilder:<init>	()V
    //   17: ldc 97
    //   19: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: aload_0
    //   23: getfield 14	com/google/android/gms/internal/cx:qE	Lcom/google/android/gms/ads/mediation/MediationAdapter;
    //   26: invokevirtual 103	java/lang/Object:getClass	()Ljava/lang/Class;
    //   29: invokevirtual 108	java/lang/Class:getCanonicalName	()Ljava/lang/String;
    //   32: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: invokevirtual 34	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   38: invokestatic 40	com/google/android/gms/internal/gs:W	(Ljava/lang/String;)V
    //   41: new 19	android/os/RemoteException
    //   44: dup
    //   45: invokespecial 78	android/os/RemoteException:<init>	()V
    //   48: athrow
    //   49: ldc 110
    //   51: invokestatic 113	com/google/android/gms/internal/gs:S	(Ljava/lang/String;)V
    //   54: aload_0
    //   55: getfield 14	com/google/android/gms/internal/cx:qE	Lcom/google/android/gms/ads/mediation/MediationAdapter;
    //   58: checkcast 95	com/google/android/gms/ads/mediation/MediationInterstitialAdapter
    //   61: astore 7
    //   63: aload_2
    //   64: getfield 119	com/google/android/gms/internal/av:nV	Ljava/util/List;
    //   67: ifnull +131 -> 198
    //   70: new 121	java/util/HashSet
    //   73: astore 6
    //   75: aload 6
    //   77: aload_2
    //   78: getfield 119	com/google/android/gms/internal/av:nV	Ljava/util/List;
    //   81: invokespecial 124	java/util/HashSet:<init>	(Ljava/util/Collection;)V
    //   84: new 126	com/google/android/gms/internal/cw
    //   87: astore 8
    //   89: new 128	java/util/Date
    //   92: astore 9
    //   94: aload 9
    //   96: aload_2
    //   97: getfield 132	com/google/android/gms/internal/av:nT	J
    //   100: invokespecial 135	java/util/Date:<init>	(J)V
    //   103: aload 8
    //   105: aload 9
    //   107: aload_2
    //   108: getfield 139	com/google/android/gms/internal/av:nU	I
    //   111: aload 6
    //   113: aload_2
    //   114: getfield 143	com/google/android/gms/internal/av:ob	Landroid/location/Location;
    //   117: aload_2
    //   118: getfield 147	com/google/android/gms/internal/av:nW	Z
    //   121: aload_2
    //   122: getfield 150	com/google/android/gms/internal/av:nX	I
    //   125: invokespecial 153	com/google/android/gms/internal/cw:<init>	(Ljava/util/Date;ILjava/util/Set;Landroid/location/Location;ZI)V
    //   128: aload_2
    //   129: getfield 157	com/google/android/gms/internal/av:od	Landroid/os/Bundle;
    //   132: ifnull +72 -> 204
    //   135: aload_2
    //   136: getfield 157	com/google/android/gms/internal/av:od	Landroid/os/Bundle;
    //   139: aload 7
    //   141: invokevirtual 103	java/lang/Object:getClass	()Ljava/lang/Class;
    //   144: invokevirtual 160	java/lang/Class:getName	()Ljava/lang/String;
    //   147: invokevirtual 164	android/os/Bundle:getBundle	(Ljava/lang/String;)Landroid/os/Bundle;
    //   150: astore 6
    //   152: aload_1
    //   153: invokestatic 170	com/google/android/gms/dynamic/e:f	(Lcom/google/android/gms/dynamic/d;)Ljava/lang/Object;
    //   156: checkcast 172	android/content/Context
    //   159: astore_1
    //   160: new 174	com/google/android/gms/internal/cy
    //   163: astore 9
    //   165: aload 9
    //   167: aload 5
    //   169: invokespecial 177	com/google/android/gms/internal/cy:<init>	(Lcom/google/android/gms/internal/cv;)V
    //   172: aload 7
    //   174: aload_1
    //   175: aload 9
    //   177: aload_0
    //   178: aload_3
    //   179: aload_2
    //   180: getfield 150	com/google/android/gms/internal/av:nX	I
    //   183: aload 4
    //   185: invokespecial 179	com/google/android/gms/internal/cx:a	(Ljava/lang/String;ILjava/lang/String;)Landroid/os/Bundle;
    //   188: aload 8
    //   190: aload 6
    //   192: invokeinterface 183 6 0
    //   197: return
    //   198: aconst_null
    //   199: astore 6
    //   201: goto -117 -> 84
    //   204: aconst_null
    //   205: astore 6
    //   207: goto -55 -> 152
    //   210: astore_1
    //   211: ldc -71
    //   213: aload_1
    //   214: invokestatic 77	com/google/android/gms/internal/gs:d	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   217: new 19	android/os/RemoteException
    //   220: dup
    //   221: invokespecial 78	android/os/RemoteException:<init>	()V
    //   224: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	225	0	this	cx
    //   0	225	1	paramd	d
    //   0	225	2	paramav	av
    //   0	225	3	paramString1	String
    //   0	225	4	paramString2	String
    //   0	225	5	paramcv	cv
    //   73	133	6	localObject1	Object
    //   61	112	7	localMediationInterstitialAdapter	MediationInterstitialAdapter
    //   87	102	8	localcw	cw
    //   92	84	9	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   54	84	210	java/lang/Throwable
    //   84	152	210	java/lang/Throwable
    //   152	197	210	java/lang/Throwable
  }
  
  public void a(d paramd, ay paramay, av paramav, String paramString, cv paramcv)
    throws RemoteException
  {
    a(paramd, paramay, paramav, paramString, null, paramcv);
  }
  
  /* Error */
  public void a(d paramd, ay paramay, av paramav, String paramString1, String paramString2, cv paramcv)
    throws RemoteException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 8
    //   3: aload_0
    //   4: getfield 14	com/google/android/gms/internal/cx:qE	Lcom/google/android/gms/ads/mediation/MediationAdapter;
    //   7: instanceof 191
    //   10: ifne +42 -> 52
    //   13: new 23	java/lang/StringBuilder
    //   16: dup
    //   17: invokespecial 24	java/lang/StringBuilder:<init>	()V
    //   20: ldc -63
    //   22: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: aload_0
    //   26: getfield 14	com/google/android/gms/internal/cx:qE	Lcom/google/android/gms/ads/mediation/MediationAdapter;
    //   29: invokevirtual 103	java/lang/Object:getClass	()Ljava/lang/Class;
    //   32: invokevirtual 108	java/lang/Class:getCanonicalName	()Ljava/lang/String;
    //   35: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: invokevirtual 34	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: invokestatic 40	com/google/android/gms/internal/gs:W	(Ljava/lang/String;)V
    //   44: new 19	android/os/RemoteException
    //   47: dup
    //   48: invokespecial 78	android/os/RemoteException:<init>	()V
    //   51: athrow
    //   52: ldc -61
    //   54: invokestatic 113	com/google/android/gms/internal/gs:S	(Ljava/lang/String;)V
    //   57: aload_0
    //   58: getfield 14	com/google/android/gms/internal/cx:qE	Lcom/google/android/gms/ads/mediation/MediationAdapter;
    //   61: checkcast 191	com/google/android/gms/ads/mediation/MediationBannerAdapter
    //   64: astore 9
    //   66: aload_3
    //   67: getfield 119	com/google/android/gms/internal/av:nV	Ljava/util/List;
    //   70: ifnull +151 -> 221
    //   73: new 121	java/util/HashSet
    //   76: astore 7
    //   78: aload 7
    //   80: aload_3
    //   81: getfield 119	com/google/android/gms/internal/av:nV	Ljava/util/List;
    //   84: invokespecial 124	java/util/HashSet:<init>	(Ljava/util/Collection;)V
    //   87: new 126	com/google/android/gms/internal/cw
    //   90: astore 10
    //   92: new 128	java/util/Date
    //   95: astore 11
    //   97: aload 11
    //   99: aload_3
    //   100: getfield 132	com/google/android/gms/internal/av:nT	J
    //   103: invokespecial 135	java/util/Date:<init>	(J)V
    //   106: aload 10
    //   108: aload 11
    //   110: aload_3
    //   111: getfield 139	com/google/android/gms/internal/av:nU	I
    //   114: aload 7
    //   116: aload_3
    //   117: getfield 143	com/google/android/gms/internal/av:ob	Landroid/location/Location;
    //   120: aload_3
    //   121: getfield 147	com/google/android/gms/internal/av:nW	Z
    //   124: aload_3
    //   125: getfield 150	com/google/android/gms/internal/av:nX	I
    //   128: invokespecial 153	com/google/android/gms/internal/cw:<init>	(Ljava/util/Date;ILjava/util/Set;Landroid/location/Location;ZI)V
    //   131: aload 8
    //   133: astore 7
    //   135: aload_3
    //   136: getfield 157	com/google/android/gms/internal/av:od	Landroid/os/Bundle;
    //   139: ifnull +20 -> 159
    //   142: aload_3
    //   143: getfield 157	com/google/android/gms/internal/av:od	Landroid/os/Bundle;
    //   146: aload 9
    //   148: invokevirtual 103	java/lang/Object:getClass	()Ljava/lang/Class;
    //   151: invokevirtual 160	java/lang/Class:getName	()Ljava/lang/String;
    //   154: invokevirtual 164	android/os/Bundle:getBundle	(Ljava/lang/String;)Landroid/os/Bundle;
    //   157: astore 7
    //   159: aload_1
    //   160: invokestatic 170	com/google/android/gms/dynamic/e:f	(Lcom/google/android/gms/dynamic/d;)Ljava/lang/Object;
    //   163: checkcast 172	android/content/Context
    //   166: astore_1
    //   167: new 174	com/google/android/gms/internal/cy
    //   170: astore 8
    //   172: aload 8
    //   174: aload 6
    //   176: invokespecial 177	com/google/android/gms/internal/cy:<init>	(Lcom/google/android/gms/internal/cv;)V
    //   179: aload 9
    //   181: aload_1
    //   182: aload 8
    //   184: aload_0
    //   185: aload 4
    //   187: aload_3
    //   188: getfield 150	com/google/android/gms/internal/av:nX	I
    //   191: aload 5
    //   193: invokespecial 179	com/google/android/gms/internal/cx:a	(Ljava/lang/String;ILjava/lang/String;)Landroid/os/Bundle;
    //   196: aload_2
    //   197: getfield 200	com/google/android/gms/internal/ay:width	I
    //   200: aload_2
    //   201: getfield 203	com/google/android/gms/internal/ay:height	I
    //   204: aload_2
    //   205: getfield 207	com/google/android/gms/internal/ay:of	Ljava/lang/String;
    //   208: invokestatic 212	com/google/android/gms/ads/a:a	(IILjava/lang/String;)Lcom/google/android/gms/ads/AdSize;
    //   211: aload 10
    //   213: aload 7
    //   215: invokeinterface 216 7 0
    //   220: return
    //   221: aconst_null
    //   222: astore 7
    //   224: goto -137 -> 87
    //   227: astore_1
    //   228: ldc -38
    //   230: aload_1
    //   231: invokestatic 77	com/google/android/gms/internal/gs:d	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   234: new 19	android/os/RemoteException
    //   237: dup
    //   238: invokespecial 78	android/os/RemoteException:<init>	()V
    //   241: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	242	0	this	cx
    //   0	242	1	paramd	d
    //   0	242	2	paramay	ay
    //   0	242	3	paramav	av
    //   0	242	4	paramString1	String
    //   0	242	5	paramString2	String
    //   0	242	6	paramcv	cv
    //   76	147	7	localObject	Object
    //   1	182	8	localcy	cy
    //   64	116	9	localMediationBannerAdapter	MediationBannerAdapter
    //   90	122	10	localcw	cw
    //   95	14	11	localDate	java.util.Date
    // Exception table:
    //   from	to	target	type
    //   57	87	227	java/lang/Throwable
    //   87	131	227	java/lang/Throwable
    //   135	159	227	java/lang/Throwable
    //   159	220	227	java/lang/Throwable
  }
  
  public void destroy()
    throws RemoteException
  {
    try
    {
      this.qE.onDestroy();
      return;
    }
    catch (Throwable localThrowable)
    {
      gs.d("Could not destroy adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public d getView()
    throws RemoteException
  {
    if (!(this.qE instanceof MediationBannerAdapter))
    {
      gs.W("MediationAdapter is not a MediationBannerAdapter: " + this.qE.getClass().getCanonicalName());
      throw new RemoteException();
    }
    try
    {
      d locald = e.k(((MediationBannerAdapter)this.qE).getBannerView());
      return locald;
    }
    catch (Throwable localThrowable)
    {
      gs.d("Could not get banner view from adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public void pause()
    throws RemoteException
  {
    try
    {
      this.qE.onPause();
      return;
    }
    catch (Throwable localThrowable)
    {
      gs.d("Could not pause adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public void resume()
    throws RemoteException
  {
    try
    {
      this.qE.onResume();
      return;
    }
    catch (Throwable localThrowable)
    {
      gs.d("Could not resume adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public void showInterstitial()
    throws RemoteException
  {
    if (!(this.qE instanceof MediationInterstitialAdapter))
    {
      gs.W("MediationAdapter is not a MediationInterstitialAdapter: " + this.qE.getClass().getCanonicalName());
      throw new RemoteException();
    }
    gs.S("Showing interstitial from adapter.");
    try
    {
      ((MediationInterstitialAdapter)this.qE).showInterstitial();
      return;
    }
    catch (Throwable localThrowable)
    {
      gs.d("Could not show interstitial from adapter.", localThrowable);
      throw new RemoteException();
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\cx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */