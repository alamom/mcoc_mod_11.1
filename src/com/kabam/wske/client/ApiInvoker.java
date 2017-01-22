package com.kabam.wske.client;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class ApiInvoker
{
  private static ApiInvoker INSTANCE = new ApiInvoker();
  protected static final char[] hexArray = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
  private HttpClient client = null;
  private ClientConnectionManager connectionManager;
  private final ThreadLocal<DateFormat> dateFormat = new ThreadLocal()
  {
    protected DateFormat initialValue()
    {
      return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
    }
  };
  private Map<String, String> defaultHeaderMap = new HashMap();
  private boolean ignoreSSLCertificates = false;
  private ClientConnectionManager ignoreSSLConnectionManager;
  protected Logger logger = Logger.getLogger(ApiInvoker.class.getName());
  private final ThreadLocal<Random> random = new ThreadLocal()
  {
    protected Random initialValue()
    {
      return new Random();
    }
  };
  private String wskeClientId;
  private String wskeKey;
  
  public ApiInvoker()
  {
    initConnectionManager();
  }
  
  public static String bytesToHex(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar = new char[paramArrayOfByte.length * 2];
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      int j = paramArrayOfByte[i] & 0xFF;
      arrayOfChar[(i * 2)] = hexArray[(j >>> 4)];
      arrayOfChar[(i * 2 + 1)] = hexArray[(j & 0xF)];
    }
    return new String(arrayOfChar);
  }
  
  /* Error */
  public static Object deserialize(String paramString1, String paramString2, Class paramClass)
    throws ApiException
  {
    // Byte code:
    //   0: ldc 120
    //   2: aload_1
    //   3: invokevirtual 124	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   6: ifne +12 -> 18
    //   9: ldc 126
    //   11: aload_1
    //   12: invokevirtual 124	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   15: ifeq +32 -> 47
    //   18: aconst_null
    //   19: ldc -128
    //   21: iconst_1
    //   22: anewarray 130	java/lang/reflect/Type
    //   25: dup
    //   26: iconst_0
    //   27: aload_2
    //   28: aastore
    //   29: invokestatic 136	com/google/gson/internal/$Gson$Types:newParameterizedTypeWithOwner	(Ljava/lang/reflect/Type;Ljava/lang/reflect/Type;[Ljava/lang/reflect/Type;)Ljava/lang/reflect/ParameterizedType;
    //   32: astore_1
    //   33: invokestatic 142	com/kabam/wske/client/JsonUtil:getJsonMapper	()Lcom/google/gson/Gson;
    //   36: aload_0
    //   37: aload_1
    //   38: invokevirtual 148	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/reflect/Type;)Ljava/lang/Object;
    //   41: checkcast 150	java/util/List
    //   44: astore_1
    //   45: aload_1
    //   46: areturn
    //   47: ldc 109
    //   49: aload_2
    //   50: invokevirtual 151	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   53: ifeq +56 -> 109
    //   56: aload_0
    //   57: astore_1
    //   58: aload_0
    //   59: ifnull -14 -> 45
    //   62: aload_0
    //   63: astore_1
    //   64: aload_0
    //   65: ldc -103
    //   67: invokevirtual 157	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   70: ifeq -25 -> 45
    //   73: aload_0
    //   74: astore_1
    //   75: aload_0
    //   76: ldc -103
    //   78: invokevirtual 160	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   81: ifeq -36 -> 45
    //   84: aload_0
    //   85: astore_1
    //   86: aload_0
    //   87: invokevirtual 164	java/lang/String:length	()I
    //   90: iconst_1
    //   91: if_icmple -46 -> 45
    //   94: aload_0
    //   95: iconst_1
    //   96: aload_0
    //   97: invokevirtual 164	java/lang/String:length	()I
    //   100: iconst_2
    //   101: isub
    //   102: invokevirtual 168	java/lang/String:substring	(II)Ljava/lang/String;
    //   105: astore_1
    //   106: goto -61 -> 45
    //   109: invokestatic 142	com/kabam/wske/client/JsonUtil:getJsonMapper	()Lcom/google/gson/Gson;
    //   112: aload_0
    //   113: aload_2
    //   114: invokevirtual 171	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   117: astore_1
    //   118: goto -73 -> 45
    //   121: astore_1
    //   122: new 116	com/kabam/wske/client/ApiException
    //   125: dup
    //   126: sipush 500
    //   129: new 173	java/lang/StringBuilder
    //   132: dup
    //   133: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   136: aload_1
    //   137: invokevirtual 177	com/google/gson/JsonParseException:getMessage	()Ljava/lang/String;
    //   140: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: ldc -73
    //   145: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: aload_0
    //   149: iconst_0
    //   150: sipush 255
    //   153: aload_0
    //   154: invokevirtual 164	java/lang/String:length	()I
    //   157: invokestatic 189	java/lang/Math:min	(II)I
    //   160: invokevirtual 168	java/lang/String:substring	(II)Ljava/lang/String;
    //   163: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: ldc -65
    //   168: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: invokevirtual 194	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   174: invokespecial 197	com/kabam/wske/client/ApiException:<init>	(ILjava/lang/String;)V
    //   177: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	178	0	paramString1	String
    //   0	178	1	paramString2	String
    //   0	178	2	paramClass	Class
    // Exception table:
    //   from	to	target	type
    //   0	18	121	com/google/gson/JsonParseException
    //   18	45	121	com/google/gson/JsonParseException
    //   47	56	121	com/google/gson/JsonParseException
    //   64	73	121	com/google/gson/JsonParseException
    //   75	84	121	com/google/gson/JsonParseException
    //   86	106	121	com/google/gson/JsonParseException
    //   109	118	121	com/google/gson/JsonParseException
  }
  
  private HttpClient getClient(String paramString)
  {
    if (this.client == null)
    {
      if ((!this.ignoreSSLCertificates) || (this.ignoreSSLConnectionManager == null)) {
        break label48;
      }
      this.client = new DefaultHttpClient(this.ignoreSSLConnectionManager, new BasicHttpParams());
    }
    for (;;)
    {
      return this.client;
      label48:
      if (this.connectionManager != null) {
        this.client = new DefaultHttpClient(this.connectionManager, new BasicHttpParams());
      } else {
        this.client = new DefaultHttpClient();
      }
    }
  }
  
  public static ApiInvoker getInstance()
  {
    return INSTANCE;
  }
  
  private void initConnectionManager()
  {
    initStandardConnectionManager();
    initIgnoreSSLConnectionManager();
  }
  
  private void initIgnoreSSLConnectionManager()
  {
    try
    {
      Object localObject1 = SSLContext.getInstance("SSL");
      Object localObject3 = new com/kabam/wske/client/ApiInvoker$3;
      ((3)localObject3).<init>(this);
      Object localObject2 = new java/security/SecureRandom;
      ((SecureRandom)localObject2).<init>();
      ((SSLContext)localObject1).init(null, new TrustManager[] { localObject3 }, (SecureRandom)localObject2);
      localObject2 = new com/kabam/wske/client/ApiInvoker$4;
      ((4)localObject2).<init>(this, (KeyStore)null, (SSLContext)localObject1);
      ((org.apache.http.conn.ssl.SSLSocketFactory)localObject2).setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      localObject3 = new org/apache/http/conn/scheme/Scheme;
      ((Scheme)localObject3).<init>("https", (SocketFactory)localObject2, 443);
      localObject1 = new org/apache/http/conn/scheme/SchemeRegistry;
      ((SchemeRegistry)localObject1).<init>();
      ((SchemeRegistry)localObject1).register((Scheme)localObject3);
      localObject2 = new org/apache/http/conn/scheme/Scheme;
      ((Scheme)localObject2).<init>("http", PlainSocketFactory.getSocketFactory(), 80);
      ((SchemeRegistry)localObject1).register((Scheme)localObject2);
      localObject2 = new org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager;
      localObject3 = new org/apache/http/params/BasicHttpParams;
      ((BasicHttpParams)localObject3).<init>();
      ((ThreadSafeClientConnManager)localObject2).<init>((HttpParams)localObject3, (SchemeRegistry)localObject1);
      this.ignoreSSLConnectionManager = ((ClientConnectionManager)localObject2);
      return;
    }
    catch (GeneralSecurityException localGeneralSecurityException)
    {
      for (;;) {}
    }
    catch (KeyManagementException localKeyManagementException)
    {
      for (;;) {}
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;) {}
    }
  }
  
  private void initStandardConnectionManager()
  {
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    localSchemeRegistry.register(new Scheme("https", org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory(), 443));
    localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
    this.connectionManager = new ThreadSafeClientConnManager(new BasicHttpParams(), localSchemeRegistry);
  }
  
  public static String serialize(Object paramObject)
    throws ApiException
  {
    if (paramObject != null) {}
    for (;;)
    {
      try
      {
        paramObject = JsonUtil.getJsonMapper().toJson(paramObject);
        return (String)paramObject;
      }
      catch (Exception paramObject)
      {
        throw new ApiException(500, ((Exception)paramObject).getMessage());
      }
      paramObject = null;
    }
  }
  
  public void addDefaultHeader(String paramString1, String paramString2)
  {
    this.defaultHeaderMap.put(paramString1, paramString2);
  }
  
  public String escapeString(String paramString)
  {
    return paramString;
  }
  
  public String getWSKEClientId()
  {
    return this.wskeClientId;
  }
  
  public String getWSKEKey()
  {
    return this.wskeKey;
  }
  
  public void ignoreSSLCertificates(boolean paramBoolean)
  {
    this.ignoreSSLCertificates = paramBoolean;
  }
  
  public String invokeAPI(String paramString1, String paramString2, String paramString3, Map<String, String> paramMap1, Object paramObject, Map<String, String> paramMap2, String paramString4)
    throws ApiException
  {
    HttpClient localHttpClient = getClient(paramString1);
    Object localObject1 = new StringBuilder();
    Iterator localIterator = paramMap1.keySet().iterator();
    while (localIterator.hasNext())
    {
      localObject2 = (String)localIterator.next();
      str = (String)paramMap1.get(localObject2);
      if (str != null)
      {
        if (((StringBuilder)localObject1).toString().length() == 0) {
          ((StringBuilder)localObject1).append("?");
        }
        for (;;)
        {
          ((StringBuilder)localObject1).append(escapeString((String)localObject2)).append("=").append(escapeString(str));
          break;
          ((StringBuilder)localObject1).append("&");
        }
      }
    }
    String str = ((StringBuilder)localObject1).toString();
    localObject1 = paramString1 + paramString2 + str;
    paramMap1 = new HashMap();
    Object localObject2 = paramMap2.keySet().iterator();
    while (((Iterator)localObject2).hasNext())
    {
      paramString1 = (String)((Iterator)localObject2).next();
      paramMap1.put(paramString1, paramMap2.get(paramString1));
    }
    localObject2 = this.defaultHeaderMap.keySet().iterator();
    while (((Iterator)localObject2).hasNext())
    {
      paramString1 = (String)((Iterator)localObject2).next();
      if (!paramMap2.containsKey(paramString1)) {
        paramMap1.put(paramString1, this.defaultHeaderMap.get(paramString1));
      }
    }
    paramMap1.put("Accept", "application/json");
    paramString1 = "";
    if (("POST".equals(paramString3)) || ("PUT".equals(paramString3)) || ("DELETE".equals(paramString3)) || ("PATCH".equals(paramString3))) {
      paramString1 = serialize(paramObject);
    }
    signWSKERequest(paramMap1, paramString3, paramString2 + str, paramString1);
    paramString1 = null;
    try
    {
      if (!"GET".equals(paramString3)) {
        break label523;
      }
      paramString1 = new org/apache/http/client/methods/HttpGet;
      paramString1.<init>((String)localObject1);
      paramString1.addHeader("Accept", "application/json");
      paramString2 = paramMap1.keySet().iterator();
      while (paramString2.hasNext())
      {
        paramString3 = (String)paramString2.next();
        paramString1.setHeader(paramString3, (String)paramMap1.get(paramString3));
      }
      paramString1 = localHttpClient.execute(paramString1);
    }
    catch (IOException paramString1)
    {
      throw new ApiException(500, paramString1.getMessage());
    }
    paramString3 = null;
    paramString2 = paramString3;
    int i;
    if (paramString1 != null)
    {
      i = paramString1.getStatusLine().getStatusCode();
      if (i != 204) {
        break label1060;
      }
      paramString2 = "";
    }
    for (;;)
    {
      return paramString2;
      label523:
      if ("POST".equals(paramString3))
      {
        paramString1 = new org/apache/http/client/methods/HttpPost;
        paramString1.<init>((String)localObject1);
        if (paramObject != null)
        {
          paramString1.setHeader("Content-Type", paramString4);
          paramString2 = new org/apache/http/entity/StringEntity;
          paramString2.<init>(serialize(paramObject), "UTF-8");
          paramString1.setEntity(paramString2);
        }
        paramString2 = paramMap1.keySet().iterator();
        while (paramString2.hasNext())
        {
          paramString3 = (String)paramString2.next();
          paramString1.setHeader(paramString3, (String)paramMap1.get(paramString3));
        }
        paramString1 = localHttpClient.execute(paramString1);
        break;
      }
      if ("PUT".equals(paramString3))
      {
        paramString1 = new org/apache/http/client/methods/HttpPut;
        paramString1.<init>((String)localObject1);
        if (paramObject != null)
        {
          paramString1.setHeader("Content-Type", paramString4);
          paramString2 = new org/apache/http/entity/StringEntity;
          paramString2.<init>(serialize(paramObject), "UTF-8");
          paramString1.setEntity(paramString2);
        }
        paramString3 = paramMap1.keySet().iterator();
        while (paramString3.hasNext())
        {
          paramString2 = (String)paramString3.next();
          paramString1.setHeader(paramString2, (String)paramMap1.get(paramString2));
        }
        paramString1 = localHttpClient.execute(paramString1);
        break;
      }
      if ("DELETE".equals(paramString3))
      {
        paramString1 = new com/kabam/wske/client/ApiInvoker$HttpDeleteWithBody;
        paramString1.<init>(this, (String)localObject1);
        if (paramObject != null)
        {
          paramString1.setHeader("Content-Type", paramString4);
          paramString2 = new org/apache/http/entity/StringEntity;
          paramString2.<init>(serialize(paramObject), "UTF-8");
          paramString1.setEntity(paramString2);
        }
        paramString2 = paramMap1.keySet().iterator();
        while (paramString2.hasNext())
        {
          paramString3 = (String)paramString2.next();
          paramString1.setHeader(paramString3, (String)paramMap1.get(paramString3));
        }
        paramString1 = localHttpClient.execute(paramString1);
        break;
      }
      if ("PATCH".equals(paramString3))
      {
        paramString1 = new com/kabam/wske/client/ApiInvoker$HttpPatch;
        paramString1.<init>(this, (String)localObject1);
        if (paramObject != null)
        {
          paramString1.setHeader("Content-Type", paramString4);
          paramString2 = new org/apache/http/entity/StringEntity;
          paramString2.<init>(serialize(paramObject), "UTF-8");
          paramString1.setEntity(paramString2);
        }
        paramString3 = paramMap1.keySet().iterator();
        while (paramString3.hasNext())
        {
          paramString2 = (String)paramString3.next();
          paramString1.setHeader(paramString2, (String)paramMap1.get(paramString2));
        }
        paramString1 = localHttpClient.execute(paramString1);
        break;
      }
      if (!"HEAD".equals(paramString3)) {
        break;
      }
      paramString1 = new org/apache/http/client/methods/HttpHead;
      paramString1.<init>((String)localObject1);
      paramString3 = paramMap1.keySet().iterator();
      while (paramString3.hasNext())
      {
        paramString2 = (String)paramString3.next();
        paramString1.setHeader(paramString2, (String)paramMap1.get(paramString2));
      }
      paramString1 = localHttpClient.execute(paramString1);
      break;
      label1060:
      if ((i < 200) || (i >= 300)) {
        break label1100;
      }
      paramString2 = paramString3;
      if (paramString1.getEntity() != null) {
        paramString2 = EntityUtils.toString(paramString1.getEntity());
      }
    }
    label1100:
    if (paramString1.getEntity() != null) {}
    for (paramString1 = EntityUtils.toString(paramString1.getEntity());; paramString1 = "no data")
    {
      paramString2 = new com/kabam/wske/client/ApiException;
      paramString2.<init>(i, paramString1);
      throw paramString2;
    }
  }
  
  public void setWSKEClientId(String paramString)
  {
    this.wskeClientId = paramString;
  }
  
  public void setWSKEKey(String paramString)
  {
    this.wskeKey = paramString;
  }
  
  public void signWSKERequest(Map<String, String> paramMap, String paramString1, String paramString2, String paramString3)
  {
    Object localObject1 = paramString3;
    if (paramString3 == null) {
      localObject1 = "";
    }
    try
    {
      paramString3 = new java/util/Date;
      paramString3.<init>();
      paramString3 = ((DateFormat)this.dateFormat.get()).format(paramString3);
      long l = Math.abs(((Random)this.random.get()).nextLong());
      Object localObject2 = new java/lang/StringBuilder;
      ((StringBuilder)localObject2).<init>();
      String str = paramString1 + paramString3 + l + paramString2 + (String)localObject1;
      localObject2 = new javax/crypto/spec/SecretKeySpec;
      ((SecretKeySpec)localObject2).<init>(this.wskeKey.getBytes("UTF-8"), "HmacSHA256");
      Object localObject3 = Mac.getInstance("HmacSHA256");
      ((Mac)localObject3).init((Key)localObject2);
      localObject2 = bytesToHex(((Mac)localObject3).doFinal(str.getBytes("UTF-8")));
      if (this.logger.isLoggable(Level.FINEST))
      {
        localObject3 = new java/lang/StringBuilder;
        ((StringBuilder)localObject3).<init>();
        ((StringBuilder)localObject3).append("Method: ").append(paramString1).append("\n");
        ((StringBuilder)localObject3).append("Timestamp: ").append(paramString3).append("\n");
        ((StringBuilder)localObject3).append("Nonce: ").append(l).append("\n");
        ((StringBuilder)localObject3).append("URL: ").append(paramString2).append("\n");
        ((StringBuilder)localObject3).append("Body: ").append((String)localObject1).append("\n");
        ((StringBuilder)localObject3).append("Data: ").append(str).append("\n");
        ((StringBuilder)localObject3).append("Signature: ").append((String)localObject2).append("\n");
        this.logger.finest(((StringBuilder)localObject3).toString());
      }
      paramString1 = str.replaceAll("\"password\":\"[^\"]*", "\"password\":\"REDACTED");
      paramString2 = this.logger;
      localObject1 = new java/lang/StringBuilder;
      ((StringBuilder)localObject1).<init>();
      paramString2.info("Payload: " + paramString1 + ", Signature: " + (String)localObject2);
      paramMap.put("X-KBM-Client-Id", this.wskeClientId);
      paramMap.put("X-KBM-Signature", localObject2);
      paramMap.put("X-KBM-Timestamp", paramString3);
      paramMap.put("X-KBM-Nonce", Long.toString(l));
      return;
    }
    catch (Exception paramMap)
    {
      for (;;)
      {
        this.logger.log(Level.SEVERE, "Error signing request: " + paramMap, paramMap);
      }
    }
  }
  
  private class HttpDeleteWithBody
    extends HttpEntityEnclosingRequestBase
  {
    public static final String METHOD_NAME = "DELETE";
    
    public HttpDeleteWithBody() {}
    
    public HttpDeleteWithBody(String paramString)
    {
      setURI(URI.create(paramString));
    }
    
    public HttpDeleteWithBody(URI paramURI)
    {
      setURI(paramURI);
    }
    
    public String getMethod()
    {
      return "DELETE";
    }
  }
  
  private class HttpPatch
    extends HttpPut
  {
    public HttpPatch() {}
    
    public HttpPatch(String paramString)
    {
      super();
    }
    
    public String getMethod()
    {
      return "PATCH";
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\wske\client\ApiInvoker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */