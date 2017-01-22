package com.facebook;

import android.net.Uri;
import android.os.Bundle;
import bolts.AppLink;
import bolts.AppLink.Target;
import bolts.AppLinkResolver;
import bolts.Continuation;
import bolts.Task;
import bolts.Task.TaskCompletionSource;
import com.facebook.model.GraphObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FacebookAppLinkResolver
  implements AppLinkResolver
{
  private static final String APP_LINK_ANDROID_TARGET_KEY = "android";
  private static final String APP_LINK_KEY = "app_links";
  private static final String APP_LINK_TARGET_APP_NAME_KEY = "app_name";
  private static final String APP_LINK_TARGET_CLASS_KEY = "class";
  private static final String APP_LINK_TARGET_PACKAGE_KEY = "package";
  private static final String APP_LINK_TARGET_SHOULD_FALLBACK_KEY = "should_fallback";
  private static final String APP_LINK_TARGET_URL_KEY = "url";
  private static final String APP_LINK_WEB_TARGET_KEY = "web";
  private final HashMap<Uri, AppLink> cachedAppLinks = new HashMap();
  
  private static AppLink.Target getAndroidTargetFromJson(JSONObject paramJSONObject)
  {
    String str1 = null;
    String str2 = tryGetStringFromJson(paramJSONObject, "package", null);
    if (str2 == null) {}
    String str3;
    for (paramJSONObject = str1;; paramJSONObject = new AppLink.Target(str2, str1, paramJSONObject, str3))
    {
      return paramJSONObject;
      str1 = tryGetStringFromJson(paramJSONObject, "class", null);
      str3 = tryGetStringFromJson(paramJSONObject, "app_name", null);
      String str4 = tryGetStringFromJson(paramJSONObject, "url", null);
      paramJSONObject = null;
      if (str4 != null) {
        paramJSONObject = Uri.parse(str4);
      }
    }
  }
  
  private static Uri getWebFallbackUriFromJson(Uri paramUri, JSONObject paramJSONObject)
  {
    Uri localUri = null;
    for (;;)
    {
      try
      {
        paramJSONObject = paramJSONObject.getJSONObject("web");
        if (tryGetBooleanFromJson(paramJSONObject, "should_fallback", true)) {
          continue;
        }
        paramJSONObject = localUri;
      }
      catch (JSONException paramJSONObject)
      {
        paramJSONObject = paramUri;
        continue;
      }
      return paramJSONObject;
      paramJSONObject = tryGetStringFromJson(paramJSONObject, "url", null);
      localUri = null;
      if (paramJSONObject != null) {
        localUri = Uri.parse(paramJSONObject);
      }
      paramJSONObject = localUri;
      if (localUri == null) {
        paramJSONObject = paramUri;
      }
    }
  }
  
  private static boolean tryGetBooleanFromJson(JSONObject paramJSONObject, String paramString, boolean paramBoolean)
  {
    try
    {
      boolean bool = paramJSONObject.getBoolean(paramString);
      paramBoolean = bool;
    }
    catch (JSONException paramJSONObject)
    {
      for (;;) {}
    }
    return paramBoolean;
  }
  
  private static String tryGetStringFromJson(JSONObject paramJSONObject, String paramString1, String paramString2)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString(paramString1);
      paramString2 = paramJSONObject;
    }
    catch (JSONException paramJSONObject)
    {
      for (;;) {}
    }
    return paramString2;
  }
  
  public Task<AppLink> getAppLinkFromUrlInBackground(final Uri paramUri)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramUri);
    getAppLinkFromUrlsInBackground(localArrayList).onSuccess(new Continuation()
    {
      public AppLink then(Task<Map<Uri, AppLink>> paramAnonymousTask)
        throws Exception
      {
        return (AppLink)((Map)paramAnonymousTask.getResult()).get(paramUri);
      }
    });
  }
  
  public Task<Map<Uri, AppLink>> getAppLinkFromUrlsInBackground(List<Uri> arg1)
  {
    final HashMap localHashMap = new HashMap();
    HashSet localHashSet1 = new HashSet();
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = ???.iterator();
    Object localObject;
    while (localIterator.hasNext())
    {
      localObject = (Uri)localIterator.next();
      synchronized (this.cachedAppLinks)
      {
        AppLink localAppLink = (AppLink)this.cachedAppLinks.get(localObject);
        if (localAppLink != null) {
          localHashMap.put(localObject, localAppLink);
        }
      }
      if (!localHashSet2.isEmpty()) {
        localStringBuilder.append(',');
      }
      localStringBuilder.append(((Uri)localObject).toString());
      localHashSet2.add(localObject);
    }
    if (localHashSet2.isEmpty()) {}
    for (??? = Task.forResult(localHashMap);; ??? = ???.getTask())
    {
      return (Task<Map<Uri, AppLink>>)???;
      ??? = Task.create();
      localObject = new Bundle();
      ((Bundle)localObject).putString("ids", localStringBuilder.toString());
      ((Bundle)localObject).putString("fields", String.format("%s.fields(%s,%s)", new Object[] { "app_links", "android", "web" }));
      new Request(null, "", (Bundle)localObject, null, new Request.Callback()
      {
        public void onCompleted(Response paramAnonymousResponse)
        {
          Object localObject1 = paramAnonymousResponse.getError();
          if (localObject1 != null) {
            paramList.setError(((FacebookRequestError)localObject1).getException());
          }
          for (;;)
          {
            return;
            paramAnonymousResponse = paramAnonymousResponse.getGraphObject();
            if (paramAnonymousResponse != null) {}
            for (paramAnonymousResponse = paramAnonymousResponse.getInnerJSONObject();; paramAnonymousResponse = null)
            {
              if (paramAnonymousResponse != null) {
                break label63;
              }
              paramList.setResult(localHashMap);
              break;
            }
            label63:
            localObject1 = localHashSet2.iterator();
            for (;;)
            {
              if (((Iterator)localObject1).hasNext())
              {
                Uri localUri = (Uri)((Iterator)localObject1).next();
                if (!paramAnonymousResponse.has(localUri.toString())) {
                  continue;
                }
                try
                {
                  Object localObject4 = paramAnonymousResponse.getJSONObject(localUri.toString()).getJSONObject("app_links");
                  Object localObject5 = ((JSONObject)localObject4).getJSONArray("android");
                  int j = ((JSONArray)localObject5).length();
                  ??? = new java/util/ArrayList;
                  ((ArrayList)???).<init>(j);
                  for (int i = 0; i < j; i++)
                  {
                    AppLink.Target localTarget = FacebookAppLinkResolver.getAndroidTargetFromJson(((JSONArray)localObject5).getJSONObject(i));
                    if (localTarget != null) {
                      ((List)???).add(localTarget);
                    }
                  }
                  localObject5 = FacebookAppLinkResolver.getWebFallbackUriFromJson(localUri, (JSONObject)localObject4);
                  localObject4 = new bolts/AppLink;
                  ((AppLink)localObject4).<init>(localUri, (List)???, (Uri)localObject5);
                  localHashMap.put(localUri, localObject4);
                  synchronized (FacebookAppLinkResolver.this.cachedAppLinks)
                  {
                    FacebookAppLinkResolver.this.cachedAppLinks.put(localUri, localObject4);
                  }
                }
                catch (JSONException localJSONException) {}
              }
            }
            paramList.setResult(localHashMap);
          }
        }
      }).executeAsync();
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\FacebookAppLinkResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */