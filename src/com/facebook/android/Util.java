package com.facebook.android;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import com.facebook.internal.Utility;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public final class Util
{
  private static final String UTF8 = "UTF-8";
  
  @Deprecated
  public static Bundle decodeUrl(String paramString)
  {
    int i = 0;
    Bundle localBundle = new Bundle();
    if (paramString != null)
    {
      paramString = paramString.split("&");
      int j = paramString.length;
      for (;;)
      {
        if (i < j)
        {
          String[] arrayOfString = paramString[i].split("=");
          try
          {
            if (arrayOfString.length == 2) {
              localBundle.putString(URLDecoder.decode(arrayOfString[0], "UTF-8"), URLDecoder.decode(arrayOfString[1], "UTF-8"));
            }
            for (;;)
            {
              i++;
              break;
              if (arrayOfString.length == 1) {
                localBundle.putString(URLDecoder.decode(arrayOfString[0], "UTF-8"), "");
              }
            }
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException)
          {
            for (;;) {}
          }
        }
      }
    }
    return localBundle;
  }
  
  @Deprecated
  public static String encodePostBody(Bundle paramBundle, String paramString)
  {
    if (paramBundle == null) {}
    StringBuilder localStringBuilder;
    for (paramBundle = "";; paramBundle = localStringBuilder.toString())
    {
      return paramBundle;
      localStringBuilder = new StringBuilder();
      Iterator localIterator = paramBundle.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Object localObject = paramBundle.get(str);
        if ((localObject instanceof String))
        {
          localStringBuilder.append("Content-Disposition: form-data; name=\"" + str + "\"\r\n\r\n" + (String)localObject);
          localStringBuilder.append("\r\n--" + paramString + "\r\n");
        }
      }
    }
  }
  
  @Deprecated
  public static String encodeUrl(Bundle paramBundle)
  {
    if (paramBundle == null) {}
    StringBuilder localStringBuilder;
    for (paramBundle = "";; paramBundle = localStringBuilder.toString())
    {
      return paramBundle;
      localStringBuilder = new StringBuilder();
      int i = 1;
      Iterator localIterator = paramBundle.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if ((paramBundle.get(str) instanceof String))
        {
          if (i != 0) {
            i = 0;
          }
          for (;;)
          {
            localStringBuilder.append(URLEncoder.encode(str) + "=" + URLEncoder.encode(paramBundle.getString(str)));
            break;
            localStringBuilder.append("&");
          }
        }
      }
    }
  }
  
  @Deprecated
  public static String openUrl(String paramString1, String paramString2, Bundle paramBundle)
    throws MalformedURLException, IOException
  {
    Object localObject1 = paramString1;
    if (paramString2.equals("GET")) {
      localObject1 = paramString1 + "?" + encodeUrl(paramBundle);
    }
    Utility.logd("Facebook-Util", paramString2 + " URL: " + (String)localObject1);
    localObject1 = (HttpURLConnection)new URL((String)localObject1).openConnection();
    ((HttpURLConnection)localObject1).setRequestProperty("User-Agent", System.getProperties().getProperty("http.agent") + " FacebookAndroidSDK");
    if (!paramString2.equals("GET"))
    {
      paramString1 = new Bundle();
      Object localObject2 = paramBundle.keySet().iterator();
      Object localObject3;
      while (((Iterator)localObject2).hasNext())
      {
        String str = (String)((Iterator)localObject2).next();
        localObject3 = paramBundle.get(str);
        if ((localObject3 instanceof byte[])) {
          paramString1.putByteArray(str, (byte[])localObject3);
        }
      }
      if (!paramBundle.containsKey("method")) {
        paramBundle.putString("method", paramString2);
      }
      if (paramBundle.containsKey("access_token")) {
        paramBundle.putString("access_token", URLDecoder.decode(paramBundle.getString("access_token")));
      }
      ((HttpURLConnection)localObject1).setRequestMethod("POST");
      ((HttpURLConnection)localObject1).setRequestProperty("Content-Type", "multipart/form-data;boundary=" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
      ((HttpURLConnection)localObject1).setDoOutput(true);
      ((HttpURLConnection)localObject1).setDoInput(true);
      ((HttpURLConnection)localObject1).setRequestProperty("Connection", "Keep-Alive");
      ((HttpURLConnection)localObject1).connect();
      paramString2 = new BufferedOutputStream(((HttpURLConnection)localObject1).getOutputStream());
      try
      {
        localObject2 = new java/lang/StringBuilder;
        ((StringBuilder)localObject2).<init>();
        paramString2.write(("--" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" + "\r\n").getBytes());
        paramString2.write(encodePostBody(paramBundle, "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f").getBytes());
        paramBundle = new java/lang/StringBuilder;
        paramBundle.<init>();
        paramString2.write(("\r\n" + "--" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" + "\r\n").getBytes());
        if (!paramString1.isEmpty())
        {
          paramBundle = paramString1.keySet().iterator();
          while (paramBundle.hasNext())
          {
            localObject2 = (String)paramBundle.next();
            localObject3 = new java/lang/StringBuilder;
            ((StringBuilder)localObject3).<init>();
            paramString2.write(("Content-Disposition: form-data; filename=\"" + (String)localObject2 + "\"" + "\r\n").getBytes());
            localObject3 = new java/lang/StringBuilder;
            ((StringBuilder)localObject3).<init>();
            paramString2.write(("Content-Type: content/unknown" + "\r\n" + "\r\n").getBytes());
            paramString2.write(paramString1.getByteArray((String)localObject2));
            localObject2 = new java/lang/StringBuilder;
            ((StringBuilder)localObject2).<init>();
            paramString2.write(("\r\n" + "--" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" + "\r\n").getBytes());
          }
        }
      }
      finally
      {
        paramString2.close();
      }
      paramString2.close();
    }
    try
    {
      paramString1 = read(((HttpURLConnection)localObject1).getInputStream());
      return paramString1;
    }
    catch (FileNotFoundException paramString1)
    {
      for (;;)
      {
        paramString1 = read(((HttpURLConnection)localObject1).getErrorStream());
      }
    }
  }
  
  @Deprecated
  public static JSONObject parseJson(String paramString)
    throws JSONException, FacebookError
  {
    if (paramString.equals("false")) {
      throw new FacebookError("request failed");
    }
    String str = paramString;
    if (paramString.equals("true")) {
      str = "{value : true}";
    }
    paramString = new JSONObject(str);
    if (paramString.has("error"))
    {
      paramString = paramString.getJSONObject("error");
      throw new FacebookError(paramString.getString("message"), paramString.getString("type"), 0);
    }
    if ((paramString.has("error_code")) && (paramString.has("error_msg"))) {
      throw new FacebookError(paramString.getString("error_msg"), "", Integer.parseInt(paramString.getString("error_code")));
    }
    if (paramString.has("error_code")) {
      throw new FacebookError("request failed", "", Integer.parseInt(paramString.getString("error_code")));
    }
    if (paramString.has("error_msg")) {
      throw new FacebookError(paramString.getString("error_msg"));
    }
    if (paramString.has("error_reason")) {
      throw new FacebookError(paramString.getString("error_reason"));
    }
    return paramString;
  }
  
  @Deprecated
  public static Bundle parseUrl(String paramString)
  {
    paramString = paramString.replace("fbconnect", "http");
    try
    {
      URL localURL = new java/net/URL;
      localURL.<init>(paramString);
      paramString = decodeUrl(localURL.getQuery());
      paramString.putAll(decodeUrl(localURL.getRef()));
      return paramString;
    }
    catch (MalformedURLException paramString)
    {
      for (;;)
      {
        paramString = new Bundle();
      }
    }
  }
  
  @Deprecated
  private static String read(InputStream paramInputStream)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream), 1000);
    for (String str = localBufferedReader.readLine(); str != null; str = localBufferedReader.readLine()) {
      localStringBuilder.append(str);
    }
    paramInputStream.close();
    return localStringBuilder.toString();
  }
  
  @Deprecated
  public static void showAlert(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = new AlertDialog.Builder(paramContext);
    paramContext.setTitle(paramString1);
    paramContext.setMessage(paramString2);
    paramContext.create().show();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\android\Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */