package com.facebook.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.SessionDefaultAudience;
import com.facebook.Settings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;

public final class NativeProtocol
{
  public static final String ACTION_FEED_DIALOG = "com.facebook.platform.action.request.FEED_DIALOG";
  public static final String ACTION_FEED_DIALOG_REPLY = "com.facebook.platform.action.reply.FEED_DIALOG";
  public static final String ACTION_LIKE_DIALOG = "com.facebook.platform.action.request.LIKE_DIALOG";
  public static final String ACTION_LIKE_DIALOG_REPLY = "com.facebook.platform.action.reply.LIKE_DIALOG";
  public static final String ACTION_MESSAGE_DIALOG = "com.facebook.platform.action.request.MESSAGE_DIALOG";
  public static final String ACTION_MESSAGE_DIALOG_REPLY = "com.facebook.platform.action.reply.MESSAGE_DIALOG";
  public static final String ACTION_OGACTIONPUBLISH_DIALOG = "com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG";
  public static final String ACTION_OGACTIONPUBLISH_DIALOG_REPLY = "com.facebook.platform.action.reply.OGACTIONPUBLISH_DIALOG";
  public static final String ACTION_OGMESSAGEPUBLISH_DIALOG = "com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG";
  public static final String ACTION_OGMESSAGEPUBLISH_DIALOG_REPLY = "com.facebook.platform.action.reply.OGMESSAGEPUBLISH_DIALOG";
  public static final String AUDIENCE_EVERYONE = "everyone";
  public static final String AUDIENCE_FRIENDS = "friends";
  public static final String AUDIENCE_ME = "only_me";
  public static final String BRIDGE_ARG_ACTION_ID_STRING = "action_id";
  public static final String BRIDGE_ARG_APP_NAME_STRING = "app_name";
  public static final String BRIDGE_ARG_ERROR_BUNDLE = "error";
  private static final String CONTENT_SCHEME = "content://";
  public static final int DIALOG_REQUEST_CODE = 64207;
  public static final String ERROR_APPLICATION_ERROR = "ApplicationError";
  public static final String ERROR_NETWORK_ERROR = "NetworkError";
  public static final String ERROR_PERMISSION_DENIED = "PermissionDenied";
  public static final String ERROR_PROTOCOL_ERROR = "ProtocolError";
  public static final String ERROR_SERVICE_DISABLED = "ServiceDisabled";
  public static final String ERROR_UNKNOWN_ERROR = "UnknownError";
  public static final String ERROR_USER_CANCELED = "UserCanceled";
  public static final String EXTRA_ACCESS_TOKEN = "com.facebook.platform.extra.ACCESS_TOKEN";
  public static final String EXTRA_ACTION = "com.facebook.platform.extra.ACTION";
  public static final String EXTRA_ACTION_TYPE = "com.facebook.platform.extra.ACTION_TYPE";
  public static final String EXTRA_APPLICATION_ID = "com.facebook.platform.extra.APPLICATION_ID";
  public static final String EXTRA_APPLICATION_NAME = "com.facebook.platform.extra.APPLICATION_NAME";
  public static final String EXTRA_DATA_FAILURES_FATAL = "com.facebook.platform.extra.DATA_FAILURES_FATAL";
  public static final String EXTRA_DESCRIPTION = "com.facebook.platform.extra.DESCRIPTION";
  public static final String EXTRA_EXPIRES_SECONDS_SINCE_EPOCH = "com.facebook.platform.extra.EXPIRES_SECONDS_SINCE_EPOCH";
  public static final String EXTRA_FRIEND_TAGS = "com.facebook.platform.extra.FRIENDS";
  public static final String EXTRA_GET_INSTALL_DATA_PACKAGE = "com.facebook.platform.extra.INSTALLDATA_PACKAGE";
  public static final String EXTRA_IMAGE = "com.facebook.platform.extra.IMAGE";
  public static final String EXTRA_LIKE_COUNT_STRING_WITHOUT_LIKE = "com.facebook.platform.extra.LIKE_COUNT_STRING_WITHOUT_LIKE";
  public static final String EXTRA_LIKE_COUNT_STRING_WITH_LIKE = "com.facebook.platform.extra.LIKE_COUNT_STRING_WITH_LIKE";
  public static final String EXTRA_LINK = "com.facebook.platform.extra.LINK";
  public static final String EXTRA_OBJECT_ID = "com.facebook.platform.extra.OBJECT_ID";
  public static final String EXTRA_OBJECT_IS_LIKED = "com.facebook.platform.extra.OBJECT_IS_LIKED";
  public static final String EXTRA_PERMISSIONS = "com.facebook.platform.extra.PERMISSIONS";
  public static final String EXTRA_PHOTOS = "com.facebook.platform.extra.PHOTOS";
  public static final String EXTRA_PLACE_TAG = "com.facebook.platform.extra.PLACE";
  public static final String EXTRA_PREVIEW_PROPERTY_NAME = "com.facebook.platform.extra.PREVIEW_PROPERTY_NAME";
  public static final String EXTRA_PROTOCOL_ACTION = "com.facebook.platform.protocol.PROTOCOL_ACTION";
  public static final String EXTRA_PROTOCOL_BRIDGE_ARGS = "com.facebook.platform.protocol.BRIDGE_ARGS";
  public static final String EXTRA_PROTOCOL_CALL_ID = "com.facebook.platform.protocol.CALL_ID";
  public static final String EXTRA_PROTOCOL_METHOD_ARGS = "com.facebook.platform.protocol.METHOD_ARGS";
  public static final String EXTRA_PROTOCOL_METHOD_RESULTS = "com.facebook.platform.protocol.RESULT_ARGS";
  public static final String EXTRA_PROTOCOL_VERSION = "com.facebook.platform.protocol.PROTOCOL_VERSION";
  static final String EXTRA_PROTOCOL_VERSIONS = "com.facebook.platform.extra.PROTOCOL_VERSIONS";
  public static final String EXTRA_REF = "com.facebook.platform.extra.REF";
  public static final String EXTRA_SOCIAL_SENTENCE_WITHOUT_LIKE = "com.facebook.platform.extra.SOCIAL_SENTENCE_WITHOUT_LIKE";
  public static final String EXTRA_SOCIAL_SENTENCE_WITH_LIKE = "com.facebook.platform.extra.SOCIAL_SENTENCE_WITH_LIKE";
  public static final String EXTRA_SUBTITLE = "com.facebook.platform.extra.SUBTITLE";
  public static final String EXTRA_TITLE = "com.facebook.platform.extra.TITLE";
  public static final String EXTRA_UNLIKE_TOKEN = "com.facebook.platform.extra.UNLIKE_TOKEN";
  private static final NativeAppInfo FACEBOOK_APP_INFO = new KatanaAppInfo(null);
  private static final String FACEBOOK_PROXY_AUTH_ACTIVITY = "com.facebook.katana.ProxyAuth";
  public static final String FACEBOOK_PROXY_AUTH_APP_ID_KEY = "client_id";
  public static final String FACEBOOK_PROXY_AUTH_E2E_KEY = "e2e";
  public static final String FACEBOOK_PROXY_AUTH_PERMISSIONS_KEY = "scope";
  private static final String FACEBOOK_TOKEN_REFRESH_ACTIVITY = "com.facebook.katana.platform.TokenRefreshService";
  public static final String IMAGE_URL_KEY = "url";
  public static final String IMAGE_USER_GENERATED_KEY = "user_generated";
  static final String INTENT_ACTION_PLATFORM_ACTIVITY = "com.facebook.platform.PLATFORM_ACTIVITY";
  static final String INTENT_ACTION_PLATFORM_SERVICE = "com.facebook.platform.PLATFORM_SERVICE";
  private static final List<Integer> KNOWN_PROTOCOL_VERSIONS = Arrays.asList(new Integer[] { Integer.valueOf(20141001), Integer.valueOf(20140701), Integer.valueOf(20140324), Integer.valueOf(20140204), Integer.valueOf(20131107), Integer.valueOf(20130618), Integer.valueOf(20130502), Integer.valueOf(20121101) });
  public static final int MESSAGE_GET_ACCESS_TOKEN_REPLY = 65537;
  public static final int MESSAGE_GET_ACCESS_TOKEN_REQUEST = 65536;
  public static final int MESSAGE_GET_INSTALL_DATA_REPLY = 65541;
  public static final int MESSAGE_GET_INSTALL_DATA_REQUEST = 65540;
  public static final int MESSAGE_GET_LIKE_STATUS_REPLY = 65543;
  public static final int MESSAGE_GET_LIKE_STATUS_REQUEST = 65542;
  static final int MESSAGE_GET_PROTOCOL_VERSIONS_REPLY = 65539;
  static final int MESSAGE_GET_PROTOCOL_VERSIONS_REQUEST = 65538;
  public static final String METHOD_ARGS_ACTION = "ACTION";
  public static final String METHOD_ARGS_ACTION_TYPE = "ACTION_TYPE";
  public static final String METHOD_ARGS_DATA_FAILURES_FATAL = "DATA_FAILURES_FATAL";
  public static final String METHOD_ARGS_DESCRIPTION = "DESCRIPTION";
  public static final String METHOD_ARGS_FRIEND_TAGS = "FRIENDS";
  public static final String METHOD_ARGS_IMAGE = "IMAGE";
  public static final String METHOD_ARGS_LINK = "LINK";
  public static final String METHOD_ARGS_OBJECT_ID = "object_id";
  public static final String METHOD_ARGS_PHOTOS = "PHOTOS";
  public static final String METHOD_ARGS_PLACE_TAG = "PLACE";
  public static final String METHOD_ARGS_PREVIEW_PROPERTY_NAME = "PREVIEW_PROPERTY_NAME";
  public static final String METHOD_ARGS_REF = "REF";
  public static final String METHOD_ARGS_SUBTITLE = "SUBTITLE";
  public static final String METHOD_ARGS_TITLE = "TITLE";
  public static final int NO_PROTOCOL_AVAILABLE = -1;
  public static final String OPEN_GRAPH_CREATE_OBJECT_KEY = "fbsdk:create_object";
  private static final String PLATFORM_PROVIDER_VERSIONS = ".provider.PlatformProvider/versions";
  private static final String PLATFORM_PROVIDER_VERSION_COLUMN = "version";
  public static final int PROTOCOL_VERSION_20121101 = 20121101;
  public static final int PROTOCOL_VERSION_20130502 = 20130502;
  public static final int PROTOCOL_VERSION_20130618 = 20130618;
  public static final int PROTOCOL_VERSION_20131107 = 20131107;
  public static final int PROTOCOL_VERSION_20140204 = 20140204;
  public static final int PROTOCOL_VERSION_20140324 = 20140324;
  public static final int PROTOCOL_VERSION_20140701 = 20140701;
  public static final int PROTOCOL_VERSION_20141001 = 20141001;
  public static final String STATUS_ERROR_CODE = "com.facebook.platform.status.ERROR_CODE";
  public static final String STATUS_ERROR_DESCRIPTION = "com.facebook.platform.status.ERROR_DESCRIPTION";
  public static final String STATUS_ERROR_JSON = "com.facebook.platform.status.ERROR_JSON";
  public static final String STATUS_ERROR_SUBCODE = "com.facebook.platform.status.ERROR_SUBCODE";
  public static final String STATUS_ERROR_TYPE = "com.facebook.platform.status.ERROR_TYPE";
  private static Map<String, List<NativeAppInfo>> actionToAppInfoMap;
  private static List<NativeAppInfo> facebookAppInfoList = buildFacebookAppList();
  
  static
  {
    actionToAppInfoMap = buildActionToAppInfoMap();
  }
  
  private static Map<String, List<NativeAppInfo>> buildActionToAppInfoMap()
  {
    HashMap localHashMap = new HashMap();
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new MessengerAppInfo(null));
    localHashMap.put("com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG", facebookAppInfoList);
    localHashMap.put("com.facebook.platform.action.request.FEED_DIALOG", facebookAppInfoList);
    localHashMap.put("com.facebook.platform.action.request.LIKE_DIALOG", facebookAppInfoList);
    localHashMap.put("com.facebook.platform.action.request.MESSAGE_DIALOG", localArrayList);
    localHashMap.put("com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG", localArrayList);
    return localHashMap;
  }
  
  private static List<NativeAppInfo> buildFacebookAppList()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(FACEBOOK_APP_INFO);
    localArrayList.add(new WakizashiAppInfo(null));
    return localArrayList;
  }
  
  private static Uri buildPlatformProviderVersionURI(NativeAppInfo paramNativeAppInfo)
  {
    return Uri.parse("content://" + paramNativeAppInfo.getPackage() + ".provider.PlatformProvider/versions");
  }
  
  public static int computeLatestAvailableVersionFromVersionSpec(TreeSet<Integer> paramTreeSet, int paramInt, int[] paramArrayOfInt)
  {
    int m = -1;
    int i = paramArrayOfInt.length - 1;
    paramTreeSet = paramTreeSet.descendingIterator();
    int j = -1;
    int k;
    int i1;
    int n;
    do
    {
      k = m;
      if (paramTreeSet.hasNext())
      {
        i1 = ((Integer)paramTreeSet.next()).intValue();
        n = Math.max(j, i1);
        for (k = i; (k >= 0) && (paramArrayOfInt[k] > i1); k--) {}
        if (k < 0) {
          k = m;
        }
      }
      else
      {
        return k;
      }
      j = n;
      i = k;
    } while (paramArrayOfInt[k] != i1);
    if (k % 2 == 0) {}
    for (paramInt = Math.min(n, paramInt);; paramInt = -1)
    {
      k = paramInt;
      break;
    }
  }
  
  public static Intent createPlatformActivityIntent(Context paramContext, String paramString1, String paramString2, int paramInt, String paramString3, Bundle paramBundle)
  {
    Intent localIntent = findActivityIntent(paramContext, "com.facebook.platform.PLATFORM_ACTIVITY", paramString2);
    if (localIntent == null) {
      paramContext = null;
    }
    for (;;)
    {
      return paramContext;
      paramContext = Utility.getMetadataApplicationId(paramContext);
      localIntent.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", paramInt).putExtra("com.facebook.platform.protocol.PROTOCOL_ACTION", paramString2).putExtra("com.facebook.platform.extra.APPLICATION_ID", paramContext);
      if (isVersionCompatibleWithBucketedIntent(paramInt))
      {
        paramContext = new Bundle();
        paramContext.putString("action_id", paramString1);
        paramContext.putString("app_name", paramString3);
        localIntent.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", paramContext);
        if (paramBundle == null) {
          paramBundle = new Bundle();
        }
        for (;;)
        {
          localIntent.putExtra("com.facebook.platform.protocol.METHOD_ARGS", paramBundle);
          paramContext = localIntent;
          break;
        }
      }
      localIntent.putExtra("com.facebook.platform.protocol.CALL_ID", paramString1);
      localIntent.putExtra("com.facebook.platform.extra.APPLICATION_NAME", paramString3);
      localIntent.putExtras(paramBundle);
      paramContext = localIntent;
    }
  }
  
  public static Intent createPlatformServiceIntent(Context paramContext)
  {
    Iterator localIterator = facebookAppInfoList.iterator();
    Object localObject;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localObject = (NativeAppInfo)localIterator.next();
      localObject = validateServiceIntent(paramContext, new Intent("com.facebook.platform.PLATFORM_SERVICE").setPackage(((NativeAppInfo)localObject).getPackage()).addCategory("android.intent.category.DEFAULT"), (NativeAppInfo)localObject);
    } while (localObject == null);
    for (paramContext = (Context)localObject;; paramContext = null) {
      return paramContext;
    }
  }
  
  public static Intent createProxyAuthIntent(Context paramContext, String paramString1, List<String> paramList, String paramString2, boolean paramBoolean, SessionDefaultAudience paramSessionDefaultAudience)
  {
    Iterator localIterator = facebookAppInfoList.iterator();
    Intent localIntent;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      NativeAppInfo localNativeAppInfo = (NativeAppInfo)localIterator.next();
      localIntent = new Intent().setClassName(localNativeAppInfo.getPackage(), "com.facebook.katana.ProxyAuth").putExtra("client_id", paramString1);
      if (!Utility.isNullOrEmpty(paramList)) {
        localIntent.putExtra("scope", TextUtils.join(",", paramList));
      }
      if (!Utility.isNullOrEmpty(paramString2)) {
        localIntent.putExtra("e2e", paramString2);
      }
      localIntent.putExtra("response_type", "token");
      localIntent.putExtra("return_scopes", "true");
      localIntent.putExtra("default_audience", paramSessionDefaultAudience.getNativeProtocolAudience());
      if (!Settings.getPlatformCompatibilityEnabled())
      {
        localIntent.putExtra("legacy_override", "v2.2");
        if (paramBoolean) {
          localIntent.putExtra("auth_type", "rerequest");
        }
      }
      localIntent = validateActivityIntent(paramContext, localIntent, localNativeAppInfo);
    } while (localIntent == null);
    for (paramContext = localIntent;; paramContext = null) {
      return paramContext;
    }
  }
  
  public static Intent createTokenRefreshIntent(Context paramContext)
  {
    Iterator localIterator = facebookAppInfoList.iterator();
    Object localObject;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localObject = (NativeAppInfo)localIterator.next();
      localObject = validateServiceIntent(paramContext, new Intent().setClassName(((NativeAppInfo)localObject).getPackage(), "com.facebook.katana.platform.TokenRefreshService"), (NativeAppInfo)localObject);
    } while (localObject == null);
    for (paramContext = (Context)localObject;; paramContext = null) {
      return paramContext;
    }
  }
  
  private static Intent findActivityIntent(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject = (List)actionToAppInfoMap.get(paramString2);
    if (localObject == null) {
      paramString2 = null;
    }
    for (;;)
    {
      return paramString2;
      paramString2 = null;
      Iterator localIterator = ((List)localObject).iterator();
      if (localIterator.hasNext())
      {
        paramString2 = (NativeAppInfo)localIterator.next();
        localObject = validateActivityIntent(paramContext, new Intent().setAction(paramString1).setPackage(paramString2.getPackage()).addCategory("android.intent.category.DEFAULT"), paramString2);
        paramString2 = (String)localObject;
        if (localObject == null) {
          break;
        }
        paramString2 = (String)localObject;
      }
    }
  }
  
  private static TreeSet<Integer> getAllAvailableProtocolVersionsForAppInfo(Context paramContext, NativeAppInfo paramNativeAppInfo)
  {
    TreeSet localTreeSet = new TreeSet();
    ContentResolver localContentResolver = paramContext.getContentResolver();
    paramNativeAppInfo = buildPlatformProviderVersionURI(paramNativeAppInfo);
    paramContext = null;
    try
    {
      paramNativeAppInfo = localContentResolver.query(paramNativeAppInfo, new String[] { "version" }, null, null, null);
      if (paramNativeAppInfo != null) {
        for (;;)
        {
          paramContext = paramNativeAppInfo;
          if (!paramNativeAppInfo.moveToNext()) {
            break;
          }
          paramContext = paramNativeAppInfo;
          localTreeSet.add(Integer.valueOf(paramNativeAppInfo.getInt(paramNativeAppInfo.getColumnIndex("version"))));
        }
      }
      if (paramNativeAppInfo == null) {
        return localTreeSet;
      }
    }
    finally
    {
      if (paramContext != null) {
        paramContext.close();
      }
    }
    paramNativeAppInfo.close();
    return localTreeSet;
  }
  
  public static Bundle getBridgeArgumentsFromIntent(Intent paramIntent)
  {
    if (!isVersionCompatibleWithBucketedIntent(getProtocolVersionFromIntent(paramIntent))) {}
    for (paramIntent = null;; paramIntent = paramIntent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS")) {
      return paramIntent;
    }
  }
  
  public static UUID getCallIdFromIntent(Intent paramIntent)
  {
    Object localObject;
    if (paramIntent == null) {
      localObject = null;
    }
    Bundle localBundle;
    for (;;)
    {
      return (UUID)localObject;
      int i = getProtocolVersionFromIntent(paramIntent);
      localObject = null;
      if (isVersionCompatibleWithBucketedIntent(i))
      {
        localBundle = paramIntent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS");
        paramIntent = (Intent)localObject;
        if (localBundle == null) {}
      }
      for (paramIntent = localBundle.getString("action_id");; paramIntent = paramIntent.getStringExtra("com.facebook.platform.protocol.CALL_ID"))
      {
        localBundle = null;
        localObject = localBundle;
        if (paramIntent == null) {
          break;
        }
        try
        {
          localObject = UUID.fromString(paramIntent);
        }
        catch (IllegalArgumentException paramIntent)
        {
          localObject = localBundle;
        }
      }
    }
  }
  
  public static Bundle getErrorDataFromResultIntent(Intent paramIntent)
  {
    if (!isErrorResult(paramIntent)) {
      paramIntent = null;
    }
    for (;;)
    {
      return paramIntent;
      Bundle localBundle = getBridgeArgumentsFromIntent(paramIntent);
      if (localBundle != null) {
        paramIntent = localBundle.getBundle("error");
      } else {
        paramIntent = paramIntent.getExtras();
      }
    }
  }
  
  public static Exception getExceptionFromErrorData(Bundle paramBundle)
  {
    if (paramBundle == null) {
      paramBundle = null;
    }
    for (;;)
    {
      return paramBundle;
      String str = paramBundle.getString("com.facebook.platform.status.ERROR_TYPE");
      paramBundle = paramBundle.getString("com.facebook.platform.status.ERROR_DESCRIPTION");
      if ((str != null) && (str.equalsIgnoreCase("UserCanceled"))) {
        paramBundle = new FacebookOperationCanceledException(paramBundle);
      } else {
        paramBundle = new FacebookException(paramBundle);
      }
    }
  }
  
  public static int getLatestAvailableProtocolVersionForAction(Context paramContext, String paramString, int[] paramArrayOfInt)
  {
    return getLatestAvailableProtocolVersionForAppInfoList(paramContext, (List)actionToAppInfoMap.get(paramString), paramArrayOfInt);
  }
  
  private static int getLatestAvailableProtocolVersionForAppInfo(Context paramContext, NativeAppInfo paramNativeAppInfo, int[] paramArrayOfInt)
  {
    return computeLatestAvailableVersionFromVersionSpec(getAllAvailableProtocolVersionsForAppInfo(paramContext, paramNativeAppInfo), getLatestKnownVersion(), paramArrayOfInt);
  }
  
  private static int getLatestAvailableProtocolVersionForAppInfoList(Context paramContext, List<NativeAppInfo> paramList, int[] paramArrayOfInt)
  {
    int i;
    if (paramList == null) {
      i = -1;
    }
    for (;;)
    {
      return i;
      paramList = paramList.iterator();
      for (;;)
      {
        if (paramList.hasNext())
        {
          i = getLatestAvailableProtocolVersionForAppInfo(paramContext, (NativeAppInfo)paramList.next(), paramArrayOfInt);
          if (i != -1) {
            break;
          }
        }
      }
      i = -1;
    }
  }
  
  public static int getLatestAvailableProtocolVersionForService(Context paramContext, int paramInt)
  {
    return getLatestAvailableProtocolVersionForAppInfoList(paramContext, facebookAppInfoList, new int[] { paramInt });
  }
  
  public static final int getLatestKnownVersion()
  {
    return ((Integer)KNOWN_PROTOCOL_VERSIONS.get(0)).intValue();
  }
  
  public static int getProtocolVersionFromIntent(Intent paramIntent)
  {
    return paramIntent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0);
  }
  
  public static Bundle getSuccessResultsFromIntent(Intent paramIntent)
  {
    int i = getProtocolVersionFromIntent(paramIntent);
    Bundle localBundle = paramIntent.getExtras();
    paramIntent = localBundle;
    if (isVersionCompatibleWithBucketedIntent(i)) {
      if (localBundle != null) {
        break label27;
      }
    }
    label27:
    for (paramIntent = localBundle;; paramIntent = localBundle.getBundle("com.facebook.platform.protocol.RESULT_ARGS")) {
      return paramIntent;
    }
  }
  
  public static boolean isErrorResult(Intent paramIntent)
  {
    Bundle localBundle = getBridgeArgumentsFromIntent(paramIntent);
    if (localBundle != null) {}
    for (boolean bool = localBundle.containsKey("error");; bool = paramIntent.hasExtra("com.facebook.platform.status.ERROR_TYPE")) {
      return bool;
    }
  }
  
  public static boolean isVersionCompatibleWithBucketedIntent(int paramInt)
  {
    if ((KNOWN_PROTOCOL_VERSIONS.contains(Integer.valueOf(paramInt))) && (paramInt >= 20140701)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  static Intent validateActivityIntent(Context paramContext, Intent paramIntent, NativeAppInfo paramNativeAppInfo)
  {
    if (paramIntent == null) {
      paramIntent = null;
    }
    for (;;)
    {
      return paramIntent;
      ResolveInfo localResolveInfo = paramContext.getPackageManager().resolveActivity(paramIntent, 0);
      if (localResolveInfo == null) {
        paramIntent = null;
      } else if (!paramNativeAppInfo.validateSignature(paramContext, localResolveInfo.activityInfo.packageName)) {
        paramIntent = null;
      }
    }
  }
  
  static Intent validateServiceIntent(Context paramContext, Intent paramIntent, NativeAppInfo paramNativeAppInfo)
  {
    if (paramIntent == null) {
      paramIntent = null;
    }
    for (;;)
    {
      return paramIntent;
      ResolveInfo localResolveInfo = paramContext.getPackageManager().resolveService(paramIntent, 0);
      if (localResolveInfo == null) {
        paramIntent = null;
      } else if (!paramNativeAppInfo.validateSignature(paramContext, localResolveInfo.serviceInfo.packageName)) {
        paramIntent = null;
      }
    }
  }
  
  private static class KatanaAppInfo
    extends NativeProtocol.NativeAppInfo
  {
    static final String KATANA_PACKAGE = "com.facebook.katana";
    
    private KatanaAppInfo()
    {
      super();
    }
    
    protected String getPackage()
    {
      return "com.facebook.katana";
    }
  }
  
  private static class MessengerAppInfo
    extends NativeProtocol.NativeAppInfo
  {
    static final String MESSENGER_PACKAGE = "com.facebook.orca";
    
    private MessengerAppInfo()
    {
      super();
    }
    
    protected String getPackage()
    {
      return "com.facebook.orca";
    }
  }
  
  private static abstract class NativeAppInfo
  {
    private static final String FBI_HASH = "a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc";
    private static final String FBL_HASH = "5e8f16062ea3cd2c4a0d547876baa6f38cabf625";
    private static final String FBR_HASH = "8a3c4b262d721acd49a4bf97d5213199c86fa2b9";
    private static final HashSet<String> validAppSignatureHashes = ;
    
    private static HashSet<String> buildAppSignatureHashes()
    {
      HashSet localHashSet = new HashSet();
      localHashSet.add("8a3c4b262d721acd49a4bf97d5213199c86fa2b9");
      localHashSet.add("a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc");
      localHashSet.add("5e8f16062ea3cd2c4a0d547876baa6f38cabf625");
      return localHashSet;
    }
    
    protected abstract String getPackage();
    
    public boolean validateSignature(Context paramContext, String paramString)
    {
      boolean bool2 = true;
      String str = Build.BRAND;
      int i = paramContext.getApplicationInfo().flags;
      if ((str.startsWith("generic")) && ((i & 0x2) != 0)) {}
      label104:
      for (boolean bool1 = bool2;; bool1 = false) {
        for (;;)
        {
          return bool1;
          try
          {
            paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 64);
            paramString = paramContext.signatures;
            int j = paramString.length;
            for (i = 0;; i++)
            {
              if (i >= j) {
                break label104;
              }
              paramContext = Utility.sha1hash(paramString[i].toByteArray());
              bool1 = bool2;
              if (validAppSignatureHashes.contains(paramContext)) {
                break;
              }
            }
          }
          catch (PackageManager.NameNotFoundException paramContext)
          {
            bool1 = false;
          }
        }
      }
    }
  }
  
  private static class WakizashiAppInfo
    extends NativeProtocol.NativeAppInfo
  {
    static final String WAKIZASHI_PACKAGE = "com.facebook.wakizashi";
    
    private WakizashiAppInfo()
    {
      super();
    }
    
    protected String getPackage()
    {
      return "com.facebook.wakizashi";
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\internal\NativeProtocol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */