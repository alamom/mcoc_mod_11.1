package com.google.android.gms.analytics;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

class ag
  implements m
{
  private final HttpClient Bj;
  private URL Bk;
  private final Context mContext;
  private final String vW;
  private GoogleAnalytics yu;
  
  ag(HttpClient paramHttpClient, Context paramContext)
  {
    this(paramHttpClient, GoogleAnalytics.getInstance(paramContext), paramContext);
  }
  
  ag(HttpClient paramHttpClient, GoogleAnalytics paramGoogleAnalytics, Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
    this.vW = a("GoogleAnalytics", "3.0", Build.VERSION.RELEASE, aj.a(Locale.getDefault()), Build.MODEL, Build.ID);
    this.Bj = paramHttpClient;
    this.yu = paramGoogleAnalytics;
  }
  
  /* Error */
  private void a(aa paramaa, URL paramURL, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 90	com/google/android/gms/analytics/aa:eL	()Ljava/lang/String;
    //   4: invokestatic 96	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   7: ifne +10 -> 17
    //   10: aload_0
    //   11: invokevirtual 100	com/google/android/gms/analytics/ag:eS	()Z
    //   14: ifne +4 -> 18
    //   17: return
    //   18: aload_2
    //   19: ifnonnull +241 -> 260
    //   22: aload_0
    //   23: getfield 102	com/google/android/gms/analytics/ag:Bk	Ljava/net/URL;
    //   26: ifnull +178 -> 204
    //   29: aload_0
    //   30: getfield 102	com/google/android/gms/analytics/ag:Bk	Ljava/net/URL;
    //   33: astore_2
    //   34: new 104	org/apache/http/HttpHost
    //   37: dup
    //   38: aload_2
    //   39: invokevirtual 109	java/net/URL:getHost	()Ljava/lang/String;
    //   42: aload_2
    //   43: invokevirtual 113	java/net/URL:getPort	()I
    //   46: aload_2
    //   47: invokevirtual 116	java/net/URL:getProtocol	()Ljava/lang/String;
    //   50: invokespecial 119	org/apache/http/HttpHost:<init>	(Ljava/lang/String;ILjava/lang/String;)V
    //   53: astore 5
    //   55: aload_0
    //   56: aload_1
    //   57: invokevirtual 90	com/google/android/gms/analytics/aa:eL	()Ljava/lang/String;
    //   60: aload_2
    //   61: invokevirtual 122	java/net/URL:getPath	()Ljava/lang/String;
    //   64: invokespecial 126	com/google/android/gms/analytics/ag:h	(Ljava/lang/String;Ljava/lang/String;)Lorg/apache/http/HttpEntityEnclosingRequest;
    //   67: astore_1
    //   68: aload_1
    //   69: ifnull -52 -> 17
    //   72: aload_1
    //   73: ldc -128
    //   75: aload 5
    //   77: invokevirtual 131	org/apache/http/HttpHost:toHostString	()Ljava/lang/String;
    //   80: invokeinterface 137 3 0
    //   85: invokestatic 142	com/google/android/gms/analytics/z:eK	()Z
    //   88: ifeq +8 -> 96
    //   91: aload_0
    //   92: aload_1
    //   93: invokespecial 145	com/google/android/gms/analytics/ag:a	(Lorg/apache/http/HttpEntityEnclosingRequest;)V
    //   96: iload_3
    //   97: ifeq +10 -> 107
    //   100: aload_0
    //   101: getfield 39	com/google/android/gms/analytics/ag:mContext	Landroid/content/Context;
    //   104: invokestatic 151	com/google/android/gms/analytics/p:A	(Landroid/content/Context;)V
    //   107: aload_0
    //   108: getfield 75	com/google/android/gms/analytics/ag:Bj	Lorg/apache/http/client/HttpClient;
    //   111: aload 5
    //   113: aload_1
    //   114: invokeinterface 157 3 0
    //   119: astore_1
    //   120: aload_1
    //   121: invokeinterface 163 1 0
    //   126: invokeinterface 168 1 0
    //   131: istore 4
    //   133: aload_1
    //   134: invokeinterface 172 1 0
    //   139: astore_2
    //   140: aload_2
    //   141: ifnull +9 -> 150
    //   144: aload_2
    //   145: invokeinterface 177 1 0
    //   150: iload 4
    //   152: sipush 200
    //   155: if_icmpeq -138 -> 17
    //   158: new 179	java/lang/StringBuilder
    //   161: astore_2
    //   162: aload_2
    //   163: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   166: aload_2
    //   167: ldc -74
    //   169: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: aload_1
    //   173: invokeinterface 163 1 0
    //   178: invokeinterface 168 1 0
    //   183: invokevirtual 189	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   186: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   189: invokestatic 196	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   192: goto -175 -> 17
    //   195: astore_1
    //   196: ldc -58
    //   198: invokestatic 196	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   201: goto -184 -> 17
    //   204: new 106	java/net/URL
    //   207: dup
    //   208: ldc -56
    //   210: invokespecial 202	java/net/URL:<init>	(Ljava/lang/String;)V
    //   213: astore_2
    //   214: goto -180 -> 34
    //   217: astore_1
    //   218: goto -201 -> 17
    //   221: astore_1
    //   222: new 179	java/lang/StringBuilder
    //   225: dup
    //   226: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   229: ldc -52
    //   231: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: aload_1
    //   235: invokevirtual 208	java/lang/Object:getClass	()Ljava/lang/Class;
    //   238: invokevirtual 213	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   241: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   247: invokestatic 196	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   250: aload_1
    //   251: invokevirtual 216	java/io/IOException:getMessage	()Ljava/lang/String;
    //   254: invokestatic 196	com/google/android/gms/analytics/z:W	(Ljava/lang/String;)V
    //   257: goto -240 -> 17
    //   260: goto -226 -> 34
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	263	0	this	ag
    //   0	263	1	paramaa	aa
    //   0	263	2	paramURL	URL
    //   0	263	3	paramBoolean	boolean
    //   131	25	4	i	int
    //   53	59	5	localHttpHost	HttpHost
    // Exception table:
    //   from	to	target	type
    //   55	68	195	org/apache/http/client/ClientProtocolException
    //   72	96	195	org/apache/http/client/ClientProtocolException
    //   100	107	195	org/apache/http/client/ClientProtocolException
    //   107	140	195	org/apache/http/client/ClientProtocolException
    //   144	150	195	org/apache/http/client/ClientProtocolException
    //   158	192	195	org/apache/http/client/ClientProtocolException
    //   22	34	217	java/net/MalformedURLException
    //   204	214	217	java/net/MalformedURLException
    //   55	68	221	java/io/IOException
    //   72	96	221	java/io/IOException
    //   100	107	221	java/io/IOException
    //   107	140	221	java/io/IOException
    //   144	150	221	java/io/IOException
    //   158	192	221	java/io/IOException
  }
  
  private void a(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Object localObject = paramHttpEntityEnclosingRequest.getAllHeaders();
    int j = localObject.length;
    for (int i = 0; i < j; i++) {
      localStringBuffer.append(localObject[i].toString()).append("\n");
    }
    localStringBuffer.append(paramHttpEntityEnclosingRequest.getRequestLine().toString()).append("\n");
    if (paramHttpEntityEnclosingRequest.getEntity() != null) {}
    try
    {
      localObject = paramHttpEntityEnclosingRequest.getEntity().getContent();
      if (localObject != null)
      {
        i = ((InputStream)localObject).available();
        if (i > 0)
        {
          paramHttpEntityEnclosingRequest = new byte[i];
          ((InputStream)localObject).read(paramHttpEntityEnclosingRequest);
          localStringBuffer.append("POST:\n");
          localObject = new java/lang/String;
          ((String)localObject).<init>(paramHttpEntityEnclosingRequest);
          localStringBuffer.append((String)localObject).append("\n");
        }
      }
    }
    catch (IOException paramHttpEntityEnclosingRequest)
    {
      for (;;)
      {
        z.V("Error Writing hit to log...");
      }
    }
    z.V(localStringBuffer.toString());
  }
  
  private HttpEntityEnclosingRequest h(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1))
    {
      z.W("Empty hit, discarding.");
      paramString1 = null;
    }
    for (;;)
    {
      return paramString1;
      Object localObject = paramString2 + "?" + paramString1;
      if (((String)localObject).length() < 2036) {
        paramString1 = new BasicHttpEntityEnclosingRequest("GET", (String)localObject);
      }
      for (;;)
      {
        paramString1.addHeader("User-Agent", this.vW);
        break;
        paramString2 = new BasicHttpEntityEnclosingRequest("POST", paramString2);
        try
        {
          localObject = new org/apache/http/entity/StringEntity;
          ((StringEntity)localObject).<init>(paramString1);
          paramString2.setEntity((HttpEntity)localObject);
          paramString1 = paramString2;
        }
        catch (UnsupportedEncodingException paramString1)
        {
          z.W("Encoding error, discarding hit");
          paramString1 = null;
        }
      }
    }
  }
  
  public int a(List<w> paramList, aa paramaa, boolean paramBoolean)
  {
    int m = 0;
    int n = Math.min(paramList.size(), 40);
    paramaa.e("_hr", paramList.size());
    int i = 0;
    Object localObject1 = null;
    boolean bool1 = true;
    int k = 0;
    if (k < n)
    {
      w localw = (w)paramList.get(k);
      URL localURL = a(localw);
      int j;
      if (localURL == null)
      {
        if (z.eK()) {
          z.W("No destination: discarding hit: " + localw.eF());
        }
        for (;;)
        {
          i++;
          j = m + 1;
          k++;
          m = j;
          break;
          z.W("No destination: discarding hit.");
        }
      }
      Object localObject2 = new HttpHost(localURL.getHost(), localURL.getPort(), localURL.getProtocol());
      Object localObject3 = localURL.getPath();
      if (TextUtils.isEmpty(localw.eF())) {}
      for (localObject1 = "";; localObject1 = x.a(localw, System.currentTimeMillis()))
      {
        localObject3 = h((String)localObject1, (String)localObject3);
        if (localObject3 != null) {
          break label226;
        }
        i++;
        j = m + 1;
        localObject1 = localURL;
        break;
      }
      label226:
      ((HttpEntityEnclosingRequest)localObject3).addHeader("Host", ((HttpHost)localObject2).toHostString());
      if (z.eK()) {
        a((HttpEntityEnclosingRequest)localObject3);
      }
      if (((String)localObject1).length() > 8192)
      {
        z.W("Hit too long (> 8192 bytes)--not sent");
        j = i + 1;
      }
      for (;;)
      {
        paramaa.e("_td", ((String)localObject1).getBytes().length);
        m++;
        localObject1 = localURL;
        i = j;
        j = m;
        break;
        if (this.yu.isDryRunEnabled())
        {
          z.U("Dry run enabled. Hit not actually sent.");
          j = i;
        }
        else
        {
          boolean bool2 = bool1;
          boolean bool3;
          if (bool1) {
            bool3 = bool1;
          }
          try
          {
            p.A(this.mContext);
            bool2 = false;
            bool3 = bool2;
            bool1 = bool2;
            localObject2 = this.Bj.execute((HttpHost)localObject2, (HttpRequest)localObject3);
            bool3 = bool2;
            bool1 = bool2;
            int i1 = ((HttpResponse)localObject2).getStatusLine().getStatusCode();
            bool3 = bool2;
            bool1 = bool2;
            localObject3 = ((HttpResponse)localObject2).getEntity();
            if (localObject3 != null)
            {
              bool3 = bool2;
              bool1 = bool2;
              ((HttpEntity)localObject3).consumeContent();
            }
            bool1 = bool2;
            j = i;
            if (i1 != 200)
            {
              bool3 = bool2;
              bool1 = bool2;
              localObject3 = new java/lang/StringBuilder;
              bool3 = bool2;
              bool1 = bool2;
              ((StringBuilder)localObject3).<init>();
              bool3 = bool2;
              bool1 = bool2;
              z.W("Bad response: " + ((HttpResponse)localObject2).getStatusLine().getStatusCode());
              bool1 = bool2;
              j = i;
            }
          }
          catch (ClientProtocolException localClientProtocolException)
          {
            z.W("ClientProtocolException sending hit; discarding hit...");
            paramaa.e("_hd", i);
            bool1 = bool3;
            j = i;
          }
          catch (IOException paramList)
          {
            z.W("Exception sending hit: " + paramList.getClass().getSimpleName());
            z.W(paramList.getMessage());
            paramaa.e("_de", 1);
            paramaa.e("_hd", i);
            paramaa.e("_hs", m);
            a(paramaa, localURL, bool1);
          }
        }
      }
    }
    for (;;)
    {
      return m;
      paramaa.e("_hd", i);
      paramaa.e("_hs", m);
      if (paramBoolean) {
        a(paramaa, (URL)localObject1, bool1);
      }
    }
  }
  
  String a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[] { paramString1, paramString2, paramString3, paramString4, paramString5, paramString6 });
  }
  
  URL a(w paramw)
  {
    if (this.Bk != null)
    {
      paramw = this.Bk;
      return paramw;
    }
    for (paramw = paramw.eI();; paramw = "https://ssl.google-analytics.com/collect")
    {
      try
      {
        URL localURL = new java/net/URL;
        if (!"http:".equals(paramw)) {
          continue;
        }
        paramw = "http://www.google-analytics.com/collect";
        localURL.<init>(paramw);
        paramw = localURL;
      }
      catch (MalformedURLException paramw)
      {
        z.T("Error trying to parse the hardcoded host url. This really shouldn't happen.");
        paramw = null;
      }
      break;
    }
  }
  
  public void af(String paramString)
  {
    try
    {
      URL localURL = new java/net/URL;
      localURL.<init>(paramString);
      this.Bk = localURL;
      return;
    }
    catch (MalformedURLException paramString)
    {
      for (;;)
      {
        this.Bk = null;
      }
    }
  }
  
  public boolean dX()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected())) {
      z.V("...no network connectivity");
    }
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  boolean eS()
  {
    if (Math.random() * 100.0D <= 1.0D) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\analytics\ag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */