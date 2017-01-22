package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
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
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

class db
  implements ab
{
  private final String arH;
  private final HttpClient arI;
  private a arJ;
  private final Context arq;
  
  db(HttpClient paramHttpClient, Context paramContext, a parama)
  {
    this.arq = paramContext.getApplicationContext();
    this.arH = a("GoogleTagManager", "4.00", Build.VERSION.RELEASE, c(Locale.getDefault()), Build.MODEL, Build.ID);
    this.arI = paramHttpClient;
    this.arJ = parama;
  }
  
  private HttpEntityEnclosingRequest a(URL paramURL)
  {
    for (;;)
    {
      try
      {
        BasicHttpEntityEnclosingRequest localBasicHttpEntityEnclosingRequest = new org/apache/http/message/BasicHttpEntityEnclosingRequest;
        localBasicHttpEntityEnclosingRequest.<init>("GET", paramURL.toURI().toString());
        bh.W("Exception sending hit: " + localURISyntaxException1.getClass().getSimpleName());
      }
      catch (URISyntaxException localURISyntaxException1)
      {
        try
        {
          localBasicHttpEntityEnclosingRequest.addHeader("User-Agent", this.arH);
          paramURL = localBasicHttpEntityEnclosingRequest;
          return paramURL;
        }
        catch (URISyntaxException localURISyntaxException2)
        {
          for (;;)
          {
            paramURL = localURISyntaxException1;
            Object localObject = localURISyntaxException2;
          }
        }
        localURISyntaxException1 = localURISyntaxException1;
        paramURL = null;
      }
      bh.W(localURISyntaxException1.getMessage());
    }
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
        bh.V("Error Writing hit to log...");
      }
    }
    bh.V(localStringBuffer.toString());
  }
  
  static String c(Locale paramLocale)
  {
    Object localObject2 = null;
    Object localObject1;
    if (paramLocale == null) {
      localObject1 = localObject2;
    }
    for (;;)
    {
      return (String)localObject1;
      localObject1 = localObject2;
      if (paramLocale.getLanguage() != null)
      {
        localObject1 = localObject2;
        if (paramLocale.getLanguage().length() != 0)
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append(paramLocale.getLanguage().toLowerCase());
          if ((paramLocale.getCountry() != null) && (paramLocale.getCountry().length() != 0)) {
            ((StringBuilder)localObject1).append("-").append(paramLocale.getCountry().toLowerCase());
          }
          localObject1 = ((StringBuilder)localObject1).toString();
        }
      }
    }
  }
  
  String a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[] { paramString1, paramString2, paramString3, paramString4, paramString5, paramString6 });
  }
  
  URL d(ap paramap)
  {
    String str = paramap.ou();
    try
    {
      paramap = new java/net/URL;
      paramap.<init>(str);
      return paramap;
    }
    catch (MalformedURLException paramap)
    {
      for (;;)
      {
        bh.T("Error trying to parse the GTM url.");
        paramap = null;
      }
    }
  }
  
  public boolean dX()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.arq.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected())) {
      bh.V("...no network connectivity");
    }
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  public void j(List<ap> paramList)
  {
    int n = Math.min(paramList.size(), 40);
    int i = 1;
    int m = 0;
    if (m < n)
    {
      ap localap = (ap)paramList.get(m);
      Object localObject2 = d(localap);
      if (localObject2 == null)
      {
        bh.W("No destination: discarding hit.");
        this.arJ.b(localap);
      }
      for (;;)
      {
        m++;
        break;
        Object localObject1 = a((URL)localObject2);
        if (localObject1 == null)
        {
          this.arJ.b(localap);
        }
        else
        {
          localObject2 = new HttpHost(((URL)localObject2).getHost(), ((URL)localObject2).getPort(), ((URL)localObject2).getProtocol());
          ((HttpEntityEnclosingRequest)localObject1).addHeader("Host", ((HttpHost)localObject2).toHostString());
          a((HttpEntityEnclosingRequest)localObject1);
          int j = i;
          int k;
          if (i != 0) {
            k = i;
          }
          try
          {
            bo.A(this.arq);
            j = 0;
            k = j;
            i = j;
            localObject1 = this.arI.execute((HttpHost)localObject2, (HttpRequest)localObject1);
            k = j;
            i = j;
            int i1 = ((HttpResponse)localObject1).getStatusLine().getStatusCode();
            k = j;
            i = j;
            localObject2 = ((HttpResponse)localObject1).getEntity();
            if (localObject2 != null)
            {
              k = j;
              i = j;
              ((HttpEntity)localObject2).consumeContent();
            }
            if (i1 != 200)
            {
              k = j;
              i = j;
              localObject2 = new java/lang/StringBuilder;
              k = j;
              i = j;
              ((StringBuilder)localObject2).<init>();
              k = j;
              i = j;
              bh.W("Bad response: " + ((HttpResponse)localObject1).getStatusLine().getStatusCode());
              k = j;
              i = j;
              this.arJ.c(localap);
            }
            for (;;)
            {
              i = j;
              break;
              k = j;
              i = j;
              this.arJ.a(localap);
            }
          }
          catch (ClientProtocolException localClientProtocolException)
          {
            bh.W("ClientProtocolException sending hit; discarding hit...");
            this.arJ.b(localap);
            i = k;
          }
          catch (IOException localIOException)
          {
            bh.W("Exception sending hit: " + localIOException.getClass().getSimpleName());
            bh.W(localIOException.getMessage());
            this.arJ.c(localap);
          }
        }
      }
    }
  }
  
  public static abstract interface a
  {
    public abstract void a(ap paramap);
    
    public abstract void b(ap paramap);
    
    public abstract void c(ap paramap);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\tagmanager\db.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */