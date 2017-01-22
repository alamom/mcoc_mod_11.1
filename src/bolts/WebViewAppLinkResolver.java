package bolts;

import android.content.Context;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebViewAppLinkResolver
  implements AppLinkResolver
{
  private static final String KEY_AL_VALUE = "value";
  private static final String KEY_ANDROID = "android";
  private static final String KEY_APP_NAME = "app_name";
  private static final String KEY_CLASS = "class";
  private static final String KEY_PACKAGE = "package";
  private static final String KEY_SHOULD_FALLBACK = "should_fallback";
  private static final String KEY_URL = "url";
  private static final String KEY_WEB = "web";
  private static final String KEY_WEB_URL = "url";
  private static final String META_TAG_PREFIX = "al";
  private static final String PREFER_HEADER = "Prefer-Html-Meta-Tags";
  private static final String TAG_EXTRACTION_JAVASCRIPT = "javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())";
  private final Context context;
  
  public WebViewAppLinkResolver(Context paramContext)
  {
    this.context = paramContext;
  }
  
  private static List<Map<String, Object>> getAlList(Map<String, Object> paramMap, String paramString)
  {
    paramString = (List)paramMap.get(paramString);
    paramMap = paramString;
    if (paramString == null) {
      paramMap = Collections.emptyList();
    }
    return paramMap;
  }
  
  private static AppLink makeAppLinkFromAlData(Map<String, Object> paramMap, Uri paramUri)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = (List)paramMap.get("android");
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = Collections.emptyList();
    }
    localObject2 = ((List)localObject1).iterator();
    List localList1;
    if (((Iterator)localObject2).hasNext())
    {
      localObject1 = (Map)((Iterator)localObject2).next();
      List localList2 = getAlList((Map)localObject1, "url");
      List localList3 = getAlList((Map)localObject1, "package");
      localList1 = getAlList((Map)localObject1, "class");
      List localList4 = getAlList((Map)localObject1, "app_name");
      int j = Math.max(localList2.size(), Math.max(localList3.size(), Math.max(localList1.size(), localList4.size())));
      int i = 0;
      label143:
      label179:
      Uri localUri;
      label223:
      String str1;
      label264:
      String str2;
      if (i < j)
      {
        if (localList2.size() <= i) {
          break label340;
        }
        localObject1 = ((Map)localList2.get(i)).get("value");
        localUri = tryCreateUrl((String)localObject1);
        if (localList3.size() <= i) {
          break label346;
        }
        localObject1 = ((Map)localList3.get(i)).get("value");
        str1 = (String)localObject1;
        if (localList1.size() <= i) {
          break label352;
        }
        localObject1 = ((Map)localList1.get(i)).get("value");
        str2 = (String)localObject1;
        if (localList4.size() <= i) {
          break label358;
        }
      }
      label340:
      label346:
      label352:
      label358:
      for (localObject1 = ((Map)localList4.get(i)).get("value");; localObject1 = null)
      {
        localArrayList.add(new AppLink.Target(str1, str2, localUri, (String)localObject1));
        i++;
        break label143;
        break;
        localObject1 = null;
        break label179;
        localObject1 = null;
        break label223;
        localObject1 = null;
        break label264;
      }
    }
    localObject1 = paramUri;
    paramMap = (List)paramMap.get("web");
    localObject2 = localObject1;
    if (paramMap != null)
    {
      localObject2 = localObject1;
      if (paramMap.size() > 0)
      {
        paramMap = (Map)paramMap.get(0);
        localList1 = (List)paramMap.get("url");
        localObject2 = (List)paramMap.get("should_fallback");
        paramMap = (Map<String, Object>)localObject1;
        if (localObject2 != null)
        {
          paramMap = (Map<String, Object>)localObject1;
          if (((List)localObject2).size() > 0)
          {
            localObject2 = (String)((Map)((List)localObject2).get(0)).get("value");
            paramMap = (Map<String, Object>)localObject1;
            if (Arrays.asList(new String[] { "no", "false", "0" }).contains(((String)localObject2).toLowerCase())) {
              paramMap = null;
            }
          }
        }
        localObject2 = paramMap;
        if (paramMap != null)
        {
          localObject2 = paramMap;
          if (localList1 != null)
          {
            localObject2 = paramMap;
            if (localList1.size() > 0) {
              localObject2 = tryCreateUrl((String)((Map)localList1.get(0)).get("value"));
            }
          }
        }
      }
    }
    return new AppLink(paramUri, localArrayList, (Uri)localObject2);
  }
  
  private static Map<String, Object> parseAlData(JSONArray paramJSONArray)
    throws JSONException
  {
    HashMap localHashMap = new HashMap();
    int i = 0;
    if (i < paramJSONArray.length())
    {
      JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
      String[] arrayOfString = localJSONObject.getString("property").split(":");
      if (!arrayOfString[0].equals("al")) {}
      for (;;)
      {
        i++;
        break;
        Object localObject1 = localHashMap;
        int j = 1;
        if (j < arrayOfString.length)
        {
          List localList = (List)((Map)localObject1).get(arrayOfString[j]);
          Object localObject2 = localList;
          if (localList == null)
          {
            localObject2 = new ArrayList();
            ((Map)localObject1).put(arrayOfString[j], localObject2);
          }
          if (((List)localObject2).size() > 0) {}
          for (localObject1 = (Map)((List)localObject2).get(((List)localObject2).size() - 1);; localObject1 = null)
          {
            if ((localObject1 == null) || (j == arrayOfString.length - 1))
            {
              localObject1 = new HashMap();
              ((List)localObject2).add(localObject1);
            }
            j++;
            break;
          }
        }
        if (localJSONObject.has("content")) {
          if (localJSONObject.isNull("content")) {
            ((Map)localObject1).put("value", null);
          } else {
            ((Map)localObject1).put("value", localJSONObject.getString("content"));
          }
        }
      }
    }
    return localHashMap;
  }
  
  /* Error */
  private static String readFromConnection(URLConnection paramURLConnection)
    throws java.io.IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: instanceof 220
    //   4: ifeq +72 -> 76
    //   7: aload_0
    //   8: checkcast 220	java/net/HttpURLConnection
    //   11: astore 4
    //   13: aload_0
    //   14: invokevirtual 226	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   17: astore_3
    //   18: new 228	java/io/ByteArrayOutputStream
    //   21: astore 6
    //   23: aload 6
    //   25: invokespecial 229	java/io/ByteArrayOutputStream:<init>	()V
    //   28: sipush 1024
    //   31: newarray <illegal type>
    //   33: astore 4
    //   35: aload_3
    //   36: aload 4
    //   38: invokevirtual 235	java/io/InputStream:read	([B)I
    //   41: istore_1
    //   42: iload_1
    //   43: iconst_m1
    //   44: if_icmpeq +40 -> 84
    //   47: aload 6
    //   49: aload 4
    //   51: iconst_0
    //   52: iload_1
    //   53: invokevirtual 239	java/io/ByteArrayOutputStream:write	([BII)V
    //   56: goto -21 -> 35
    //   59: astore_0
    //   60: aload_3
    //   61: invokevirtual 242	java/io/InputStream:close	()V
    //   64: aload_0
    //   65: athrow
    //   66: astore_3
    //   67: aload 4
    //   69: invokevirtual 245	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   72: astore_3
    //   73: goto -55 -> 18
    //   76: aload_0
    //   77: invokevirtual 226	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   80: astore_3
    //   81: goto -63 -> 18
    //   84: aload_0
    //   85: invokevirtual 248	java/net/URLConnection:getContentEncoding	()Ljava/lang/String;
    //   88: astore 5
    //   90: aload 5
    //   92: astore 4
    //   94: aload 5
    //   96: ifnonnull +69 -> 165
    //   99: aload_0
    //   100: invokevirtual 251	java/net/URLConnection:getContentType	()Ljava/lang/String;
    //   103: ldc -3
    //   105: invokevirtual 199	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   108: astore 4
    //   110: aload 4
    //   112: arraylength
    //   113: istore_2
    //   114: iconst_0
    //   115: istore_1
    //   116: aload 5
    //   118: astore_0
    //   119: iload_1
    //   120: iload_2
    //   121: if_icmpge +32 -> 153
    //   124: aload 4
    //   126: iload_1
    //   127: aaload
    //   128: invokevirtual 256	java/lang/String:trim	()Ljava/lang/String;
    //   131: astore_0
    //   132: aload_0
    //   133: ldc_w 258
    //   136: invokevirtual 261	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   139: ifeq +47 -> 186
    //   142: aload_0
    //   143: ldc_w 258
    //   146: invokevirtual 262	java/lang/String:length	()I
    //   149: invokevirtual 266	java/lang/String:substring	(I)Ljava/lang/String;
    //   152: astore_0
    //   153: aload_0
    //   154: astore 4
    //   156: aload_0
    //   157: ifnonnull +8 -> 165
    //   160: ldc_w 268
    //   163: astore 4
    //   165: new 135	java/lang/String
    //   168: dup
    //   169: aload 6
    //   171: invokevirtual 272	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   174: aload 4
    //   176: invokespecial 275	java/lang/String:<init>	([BLjava/lang/String;)V
    //   179: astore_0
    //   180: aload_3
    //   181: invokevirtual 242	java/io/InputStream:close	()V
    //   184: aload_0
    //   185: areturn
    //   186: iinc 1 1
    //   189: goto -73 -> 116
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	192	0	paramURLConnection	URLConnection
    //   41	146	1	i	int
    //   113	9	2	j	int
    //   17	44	3	localInputStream1	java.io.InputStream
    //   66	1	3	localException	Exception
    //   72	109	3	localInputStream2	java.io.InputStream
    //   11	164	4	localObject	Object
    //   88	29	5	str	String
    //   21	149	6	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    // Exception table:
    //   from	to	target	type
    //   18	35	59	finally
    //   35	42	59	finally
    //   47	56	59	finally
    //   84	90	59	finally
    //   99	114	59	finally
    //   124	153	59	finally
    //   165	180	59	finally
    //   13	18	66	java/lang/Exception
  }
  
  private static Uri tryCreateUrl(String paramString)
  {
    if (paramString == null) {}
    for (paramString = null;; paramString = Uri.parse(paramString)) {
      return paramString;
    }
  }
  
  public Task<AppLink> getAppLinkFromUrlInBackground(final Uri paramUri)
  {
    final Capture localCapture2 = new Capture();
    final Capture localCapture1 = new Capture();
    Task.callInBackground(new Callable()
    {
      public Void call()
        throws Exception
      {
        URL localURL = new URL(paramUri.toString());
        URLConnection localURLConnection = null;
        while (localURL != null)
        {
          localURLConnection = localURL.openConnection();
          if ((localURLConnection instanceof HttpURLConnection)) {
            ((HttpURLConnection)localURLConnection).setInstanceFollowRedirects(true);
          }
          localURLConnection.setRequestProperty("Prefer-Html-Meta-Tags", "al");
          localURLConnection.connect();
          if ((localURLConnection instanceof HttpURLConnection))
          {
            HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURLConnection;
            if ((localHttpURLConnection.getResponseCode() >= 300) && (localHttpURLConnection.getResponseCode() < 400))
            {
              localURL = new URL(localHttpURLConnection.getHeaderField("Location"));
              localHttpURLConnection.disconnect();
            }
            else
            {
              localURL = null;
            }
          }
          else
          {
            localURL = null;
          }
        }
        try
        {
          localCapture2.set(WebViewAppLinkResolver.readFromConnection(localURLConnection));
          localCapture1.set(localURLConnection.getContentType());
          return null;
        }
        finally
        {
          if ((localURLConnection instanceof HttpURLConnection)) {
            ((HttpURLConnection)localURLConnection).disconnect();
          }
        }
      }
    }).onSuccessTask(new Continuation()
    {
      public Task<JSONArray> then(Task<Void> paramAnonymousTask)
        throws Exception
      {
        final Task.TaskCompletionSource localTaskCompletionSource = Task.create();
        WebView localWebView = new WebView(WebViewAppLinkResolver.this.context);
        localWebView.getSettings().setJavaScriptEnabled(true);
        localWebView.setNetworkAvailable(false);
        localWebView.setWebViewClient(new WebViewClient()
        {
          private boolean loaded = false;
          
          private void runJavaScript(WebView paramAnonymous2WebView)
          {
            if (!this.loaded)
            {
              this.loaded = true;
              paramAnonymous2WebView.loadUrl("javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())");
            }
          }
          
          public void onLoadResource(WebView paramAnonymous2WebView, String paramAnonymous2String)
          {
            super.onLoadResource(paramAnonymous2WebView, paramAnonymous2String);
            runJavaScript(paramAnonymous2WebView);
          }
          
          public void onPageFinished(WebView paramAnonymous2WebView, String paramAnonymous2String)
          {
            super.onPageFinished(paramAnonymous2WebView, paramAnonymous2String);
            runJavaScript(paramAnonymous2WebView);
          }
        });
        localWebView.addJavascriptInterface(new Object()
        {
          @JavascriptInterface
          public void setValue(String paramAnonymous2String)
          {
            try
            {
              Task.TaskCompletionSource localTaskCompletionSource = localTaskCompletionSource;
              JSONArray localJSONArray = new org/json/JSONArray;
              localJSONArray.<init>(paramAnonymous2String);
              localTaskCompletionSource.trySetResult(localJSONArray);
              return;
            }
            catch (JSONException paramAnonymous2String)
            {
              for (;;)
              {
                localTaskCompletionSource.trySetError(paramAnonymous2String);
              }
            }
          }
        }, "boltsWebViewAppLinkResolverResult");
        paramAnonymousTask = null;
        if (localCapture1.get() != null) {
          paramAnonymousTask = ((String)localCapture1.get()).split(";")[0];
        }
        localWebView.loadDataWithBaseURL(paramUri.toString(), (String)localCapture2.get(), paramAnonymousTask, null, null);
        return localTaskCompletionSource.getTask();
      }
    }, Task.UI_THREAD_EXECUTOR).onSuccess(new Continuation()
    {
      public AppLink then(Task<JSONArray> paramAnonymousTask)
        throws Exception
      {
        return WebViewAppLinkResolver.makeAppLinkFromAlData(WebViewAppLinkResolver.access$000((JSONArray)paramAnonymousTask.getResult()), paramUri);
      }
    });
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\bolts\WebViewAppLinkResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */