package com.facebook.internal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.facebook.AppEventsLogger;
import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.Request;
import com.facebook.Request.Callback;
import com.facebook.RequestBatch;
import com.facebook.RequestBatch.Callback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.FacebookDialog.Builder;
import com.facebook.widget.FacebookDialog.Callback;
import com.facebook.widget.FacebookDialog.DialogFeature;
import com.facebook.widget.FacebookDialog.PendingCall;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LikeActionController
{
  public static final String ACTION_LIKE_ACTION_CONTROLLER_DID_ERROR = "com.facebook.sdk.LikeActionController.DID_ERROR";
  public static final String ACTION_LIKE_ACTION_CONTROLLER_DID_RESET = "com.facebook.sdk.LikeActionController.DID_RESET";
  public static final String ACTION_LIKE_ACTION_CONTROLLER_UPDATED = "com.facebook.sdk.LikeActionController.UPDATED";
  public static final String ACTION_OBJECT_ID_KEY = "com.facebook.sdk.LikeActionController.OBJECT_ID";
  private static final int ERROR_CODE_OBJECT_ALREADY_LIKED = 3501;
  public static final String ERROR_INVALID_OBJECT_ID = "Invalid Object Id";
  private static final String JSON_BOOL_IS_OBJECT_LIKED_KEY = "is_object_liked";
  private static final String JSON_BUNDLE_PENDING_CALL_ANALYTICS_BUNDLE = "pending_call_analytics_bundle";
  private static final String JSON_INT_VERSION_KEY = "com.facebook.internal.LikeActionController.version";
  private static final String JSON_STRING_LIKE_COUNT_WITHOUT_LIKE_KEY = "like_count_string_without_like";
  private static final String JSON_STRING_LIKE_COUNT_WITH_LIKE_KEY = "like_count_string_with_like";
  private static final String JSON_STRING_OBJECT_ID_KEY = "object_id";
  private static final String JSON_STRING_PENDING_CALL_ID_KEY = "pending_call_id";
  private static final String JSON_STRING_SOCIAL_SENTENCE_WITHOUT_LIKE_KEY = "social_sentence_without_like";
  private static final String JSON_STRING_SOCIAL_SENTENCE_WITH_LIKE_KEY = "social_sentence_with_like";
  private static final String JSON_STRING_UNLIKE_TOKEN_KEY = "unlike_token";
  private static final String LIKE_ACTION_CONTROLLER_STORE = "com.facebook.LikeActionController.CONTROLLER_STORE_KEY";
  private static final String LIKE_ACTION_CONTROLLER_STORE_OBJECT_SUFFIX_KEY = "OBJECT_SUFFIX";
  private static final String LIKE_ACTION_CONTROLLER_STORE_PENDING_OBJECT_ID_KEY = "PENDING_CONTROLLER_KEY";
  private static final int LIKE_ACTION_CONTROLLER_VERSION = 2;
  private static final String LIKE_DIALOG_RESPONSE_LIKE_COUNT_STRING_KEY = "like_count_string";
  private static final String LIKE_DIALOG_RESPONSE_OBJECT_IS_LIKED_KEY = "object_is_liked";
  private static final String LIKE_DIALOG_RESPONSE_SOCIAL_SENTENCE_KEY = "social_sentence";
  private static final String LIKE_DIALOG_RESPONSE_UNLIKE_TOKEN_KEY = "unlike_token";
  private static final int MAX_CACHE_SIZE = 128;
  private static final int MAX_OBJECT_SUFFIX = 1000;
  private static final String TAG = LikeActionController.class.getSimpleName();
  private static final ConcurrentHashMap<String, LikeActionController> cache = new ConcurrentHashMap();
  private static FileLruCache controllerDiskCache;
  private static WorkQueue diskIOWorkQueue = new WorkQueue(1);
  private static Handler handler;
  private static boolean isInitialized;
  private static boolean isPendingBroadcastReset;
  private static WorkQueue mruCacheWorkQueue = new WorkQueue(1);
  private static String objectIdForPendingController;
  private static volatile int objectSuffix;
  private AppEventsLogger appEventsLogger;
  private Context context;
  private boolean isObjectLiked;
  private boolean isObjectLikedOnServer;
  private boolean isPendingLikeOrUnlike;
  private String likeCountStringWithLike;
  private String likeCountStringWithoutLike;
  private String objectId;
  private boolean objectIsPage;
  private Bundle pendingCallAnalyticsBundle;
  private UUID pendingCallId;
  private Session session;
  private String socialSentenceWithLike;
  private String socialSentenceWithoutLike;
  private String unlikeToken;
  private String verifiedObjectId;
  
  private LikeActionController(Context paramContext, Session paramSession, String paramString)
  {
    this.context = paramContext;
    this.session = paramSession;
    this.objectId = paramString;
    this.appEventsLogger = AppEventsLogger.newLogger(paramContext, paramSession);
  }
  
  private static void broadcastAction(Context paramContext, LikeActionController paramLikeActionController, String paramString)
  {
    broadcastAction(paramContext, paramLikeActionController, paramString, null);
  }
  
  private static void broadcastAction(Context paramContext, LikeActionController paramLikeActionController, String paramString, Bundle paramBundle)
  {
    Intent localIntent = new Intent(paramString);
    paramString = paramBundle;
    if (paramLikeActionController != null)
    {
      paramString = paramBundle;
      if (paramBundle == null) {
        paramString = new Bundle();
      }
      paramString.putString("com.facebook.sdk.LikeActionController.OBJECT_ID", paramLikeActionController.getObjectId());
    }
    if (paramString != null) {
      localIntent.putExtras(paramString);
    }
    LocalBroadcastManager.getInstance(paramContext.getApplicationContext()).sendBroadcast(localIntent);
  }
  
  private boolean canUseOGPublish(boolean paramBoolean)
  {
    if ((!this.objectIsPage) && (this.verifiedObjectId != null) && (this.session != null) && (this.session.getPermissions() != null) && (this.session.getPermissions().contains("publish_actions")) && ((paramBoolean) || (!Utility.isNullOrEmpty(this.unlikeToken)))) {}
    for (paramBoolean = true;; paramBoolean = false) {
      return paramBoolean;
    }
  }
  
  private static void createControllerForObjectId(Context paramContext, String paramString, CreationCallback paramCreationCallback)
  {
    Object localObject = getControllerFromInMemoryCache(paramString);
    if (localObject != null) {
      invokeCallbackWithController(paramCreationCallback, (LikeActionController)localObject);
    }
    for (;;)
    {
      return;
      LikeActionController localLikeActionController = deserializeFromDiskSynchronously(paramContext, paramString);
      localObject = localLikeActionController;
      if (localLikeActionController == null)
      {
        localObject = new LikeActionController(paramContext, Session.getActiveSession(), paramString);
        serializeToDiskAsync((LikeActionController)localObject);
      }
      putControllerInMemoryCache(paramString, (LikeActionController)localObject);
      handler.post(new Runnable()
      {
        public void run()
        {
          this.val$controllerToRefresh.refreshStatusAsync();
        }
      });
      invokeCallbackWithController(paramCreationCallback, (LikeActionController)localObject);
    }
  }
  
  private static LikeActionController deserializeFromDiskSynchronously(Context paramContext, String paramString)
  {
    Object localObject2 = null;
    String str = null;
    InputStream localInputStream2 = null;
    localInputStream1 = localInputStream2;
    localObject1 = str;
    try
    {
      paramString = getCacheKeyForObjectId(paramString);
      localInputStream1 = localInputStream2;
      localObject1 = str;
      localInputStream2 = controllerDiskCache.get(paramString);
      paramString = (String)localObject2;
      if (localInputStream2 != null)
      {
        localInputStream1 = localInputStream2;
        localObject1 = localInputStream2;
        str = Utility.readStreamToString(localInputStream2);
        paramString = (String)localObject2;
        localInputStream1 = localInputStream2;
        localObject1 = localInputStream2;
        if (!Utility.isNullOrEmpty(str))
        {
          localInputStream1 = localInputStream2;
          localObject1 = localInputStream2;
          paramString = deserializeFromJson(paramContext, str);
        }
      }
      paramContext = paramString;
      if (localInputStream2 != null)
      {
        Utility.closeQuietly(localInputStream2);
        paramContext = paramString;
      }
    }
    catch (IOException paramContext)
    {
      for (;;)
      {
        localObject1 = localInputStream1;
        Log.e(TAG, "Unable to deserialize controller from disk", paramContext);
        paramString = null;
        paramContext = paramString;
        if (localInputStream1 != null)
        {
          Utility.closeQuietly(localInputStream1);
          paramContext = paramString;
        }
      }
    }
    finally
    {
      if (localObject1 == null) {
        break label142;
      }
      Utility.closeQuietly((Closeable)localObject1);
    }
    return paramContext;
  }
  
  private static LikeActionController deserializeFromJson(Context paramContext, String paramString)
  {
    JSONObject localJSONObject1 = null;
    for (;;)
    {
      try
      {
        localJSONObject2 = new org/json/JSONObject;
        localJSONObject2.<init>(paramString);
        if (localJSONObject2.optInt("com.facebook.internal.LikeActionController.version", -1) == 2) {
          continue;
        }
        paramContext = localJSONObject1;
      }
      catch (JSONException paramContext)
      {
        JSONObject localJSONObject2;
        Log.e(TAG, "Unable to deserialize controller from JSON", paramContext);
        paramContext = null;
        continue;
      }
      return paramContext;
      paramString = new com/facebook/internal/LikeActionController;
      paramString.<init>(paramContext, Session.getActiveSession(), localJSONObject2.getString("object_id"));
      paramString.likeCountStringWithLike = localJSONObject2.optString("like_count_string_with_like", null);
      paramString.likeCountStringWithoutLike = localJSONObject2.optString("like_count_string_without_like", null);
      paramString.socialSentenceWithLike = localJSONObject2.optString("social_sentence_with_like", null);
      paramString.socialSentenceWithoutLike = localJSONObject2.optString("social_sentence_without_like", null);
      paramString.isObjectLiked = localJSONObject2.optBoolean("is_object_liked");
      paramString.unlikeToken = localJSONObject2.optString("unlike_token", null);
      paramContext = localJSONObject2.optString("pending_call_id", null);
      if (!Utility.isNullOrEmpty(paramContext)) {
        paramString.pendingCallId = UUID.fromString(paramContext);
      }
      localJSONObject1 = localJSONObject2.optJSONObject("pending_call_analytics_bundle");
      paramContext = paramString;
      if (localJSONObject1 != null)
      {
        paramString.pendingCallAnalyticsBundle = BundleJSONConverter.convertToBundle(localJSONObject1);
        paramContext = paramString;
      }
    }
  }
  
  private void fetchVerifiedObjectId(final RequestCompletionCallback paramRequestCompletionCallback)
  {
    if (!Utility.isNullOrEmpty(this.verifiedObjectId)) {
      if (paramRequestCompletionCallback != null) {
        paramRequestCompletionCallback.onComplete();
      }
    }
    for (;;)
    {
      return;
      final GetOGObjectIdRequestWrapper localGetOGObjectIdRequestWrapper = new GetOGObjectIdRequestWrapper(this.objectId);
      final GetPageIdRequestWrapper localGetPageIdRequestWrapper = new GetPageIdRequestWrapper(this.objectId);
      RequestBatch localRequestBatch = new RequestBatch();
      localGetOGObjectIdRequestWrapper.addToBatch(localRequestBatch);
      localGetPageIdRequestWrapper.addToBatch(localRequestBatch);
      localRequestBatch.addCallback(new RequestBatch.Callback()
      {
        public void onBatchCompleted(RequestBatch paramAnonymousRequestBatch)
        {
          LikeActionController.access$1402(LikeActionController.this, localGetOGObjectIdRequestWrapper.verifiedObjectId);
          if (Utility.isNullOrEmpty(LikeActionController.this.verifiedObjectId))
          {
            LikeActionController.access$1402(LikeActionController.this, localGetPageIdRequestWrapper.verifiedObjectId);
            LikeActionController.access$2502(LikeActionController.this, localGetPageIdRequestWrapper.objectIsPage);
          }
          LikeActionController localLikeActionController;
          if (Utility.isNullOrEmpty(LikeActionController.this.verifiedObjectId))
          {
            Logger.log(LoggingBehavior.DEVELOPER_ERRORS, LikeActionController.TAG, "Unable to verify the FB id for '%s'. Verify that it is a valid FB object or page", new Object[] { LikeActionController.this.objectId });
            localLikeActionController = LikeActionController.this;
            if (localGetPageIdRequestWrapper.error == null) {
              break label143;
            }
          }
          label143:
          for (paramAnonymousRequestBatch = localGetPageIdRequestWrapper.error;; paramAnonymousRequestBatch = localGetOGObjectIdRequestWrapper.error)
          {
            localLikeActionController.logAppEventForError("get_verified_id", paramAnonymousRequestBatch);
            if (paramRequestCompletionCallback != null) {
              paramRequestCompletionCallback.onComplete();
            }
            return;
          }
        }
      });
      localRequestBatch.executeAsync();
    }
  }
  
  private static String getCacheKeyForObjectId(String paramString)
  {
    Object localObject2 = null;
    Session localSession = Session.getActiveSession();
    Object localObject1 = localObject2;
    if (localSession != null)
    {
      localObject1 = localObject2;
      if (localSession.isOpened()) {
        localObject1 = localSession.getAccessToken();
      }
    }
    localObject2 = localObject1;
    if (localObject1 != null) {
      localObject2 = Utility.md5hash((String)localObject1);
    }
    return String.format("%s|%s|com.fb.sdk.like|%d", new Object[] { paramString, Utility.coerceValueIfNullOrEmpty((String)localObject2, ""), Integer.valueOf(objectSuffix) });
  }
  
  public static void getControllerForObjectId(Context paramContext, String paramString, CreationCallback paramCreationCallback)
  {
    if (!isInitialized) {
      performFirstInitialize(paramContext);
    }
    LikeActionController localLikeActionController = getControllerFromInMemoryCache(paramString);
    if (localLikeActionController != null) {
      invokeCallbackWithController(paramCreationCallback, localLikeActionController);
    }
    for (;;)
    {
      return;
      diskIOWorkQueue.addActiveWorkItem(new CreateLikeActionControllerWorkItem(paramContext, paramString, paramCreationCallback));
    }
  }
  
  private static LikeActionController getControllerFromInMemoryCache(String paramString)
  {
    String str = getCacheKeyForObjectId(paramString);
    paramString = (LikeActionController)cache.get(str);
    if (paramString != null) {
      mruCacheWorkQueue.addActiveWorkItem(new MRUCacheWorkItem(str, false));
    }
    return paramString;
  }
  
  private FacebookDialog.Callback getFacebookDialogCallback(final Bundle paramBundle)
  {
    new FacebookDialog.Callback()
    {
      public void onComplete(FacebookDialog.PendingCall paramAnonymousPendingCall, Bundle paramAnonymousBundle)
      {
        if (!paramAnonymousBundle.containsKey("object_is_liked")) {
          return;
        }
        boolean bool = paramAnonymousBundle.getBoolean("object_is_liked");
        String str1 = paramAnonymousBundle.getString("like_count_string");
        String str2 = paramAnonymousBundle.getString("social_sentence");
        String str3 = paramAnonymousBundle.getString("unlike_token");
        if (paramBundle == null) {}
        for (paramAnonymousBundle = new Bundle();; paramAnonymousBundle = paramBundle)
        {
          paramAnonymousBundle.putString("call_id", paramAnonymousPendingCall.getCallId().toString());
          LikeActionController.this.appEventsLogger.logSdkEvent("fb_like_control_dialog_did_succeed", null, paramAnonymousBundle);
          LikeActionController.this.updateState(bool, str1, str1, str2, str2, str3);
          break;
        }
      }
      
      public void onError(FacebookDialog.PendingCall paramAnonymousPendingCall, Exception paramAnonymousException, Bundle paramAnonymousBundle)
      {
        Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Like Dialog failed with error : %s", new Object[] { paramAnonymousException });
        if (paramBundle == null) {}
        for (paramAnonymousException = new Bundle();; paramAnonymousException = paramBundle)
        {
          paramAnonymousException.putString("call_id", paramAnonymousPendingCall.getCallId().toString());
          LikeActionController.this.logAppEventForError("present_dialog", paramAnonymousException);
          LikeActionController.broadcastAction(LikeActionController.this.context, LikeActionController.this, "com.facebook.sdk.LikeActionController.DID_ERROR", paramAnonymousBundle);
          return;
        }
      }
    };
  }
  
  public static boolean handleOnActivityResult(Context paramContext, int paramInt1, final int paramInt2, final Intent paramIntent)
  {
    boolean bool = false;
    final UUID localUUID = NativeProtocol.getCallIdFromIntent(paramIntent);
    if (localUUID == null) {}
    for (;;)
    {
      return bool;
      if (Utility.isNullOrEmpty(objectIdForPendingController)) {
        objectIdForPendingController = paramContext.getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).getString("PENDING_CONTROLLER_KEY", null);
      }
      if (!Utility.isNullOrEmpty(objectIdForPendingController))
      {
        getControllerForObjectId(paramContext, objectIdForPendingController, new CreationCallback()
        {
          public void onComplete(LikeActionController paramAnonymousLikeActionController)
          {
            paramAnonymousLikeActionController.onActivityResult(this.val$requestCode, paramInt2, paramIntent, localUUID);
          }
        });
        bool = true;
      }
    }
  }
  
  private static void invokeCallbackWithController(CreationCallback paramCreationCallback, final LikeActionController paramLikeActionController)
  {
    if (paramCreationCallback == null) {}
    for (;;)
    {
      return;
      handler.post(new Runnable()
      {
        public void run()
        {
          this.val$callback.onComplete(paramLikeActionController);
        }
      });
    }
  }
  
  private void logAppEventForError(String paramString, Bundle paramBundle)
  {
    paramBundle = new Bundle(paramBundle);
    paramBundle.putString("object_id", this.objectId);
    paramBundle.putString("current_action", paramString);
    this.appEventsLogger.logSdkEvent("fb_like_control_error", null, paramBundle);
  }
  
  private void logAppEventForError(String paramString, FacebookRequestError paramFacebookRequestError)
  {
    Bundle localBundle = new Bundle();
    if (paramFacebookRequestError != null)
    {
      paramFacebookRequestError = paramFacebookRequestError.getRequestResult();
      if (paramFacebookRequestError != null) {
        localBundle.putString("error", paramFacebookRequestError.toString());
      }
    }
    logAppEventForError(paramString, localBundle);
  }
  
  private boolean onActivityResult(int paramInt1, int paramInt2, Intent paramIntent, UUID paramUUID)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.pendingCallId != null)
    {
      if (this.pendingCallId.equals(paramUUID)) {
        break label33;
      }
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      label33:
      paramUUID = PendingCallStore.getInstance().getPendingCallById(this.pendingCallId);
      bool1 = bool2;
      if (paramUUID != null)
      {
        FacebookDialog.handleActivityResult(this.context, paramUUID, paramInt1, paramIntent, getFacebookDialogCallback(this.pendingCallAnalyticsBundle));
        stopTrackingPendingCall();
        bool1 = true;
      }
    }
  }
  
  /* Error */
  private static void performFirstInitialize(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 568	com/facebook/internal/LikeActionController:isInitialized	Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifeq +7 -> 15
    //   11: ldc 2
    //   13: monitorexit
    //   14: return
    //   15: new 431	android/os/Handler
    //   18: astore_2
    //   19: aload_2
    //   20: invokestatic 665	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   23: invokespecial 668	android/os/Handler:<init>	(Landroid/os/Looper;)V
    //   26: aload_2
    //   27: putstatic 339	com/facebook/internal/LikeActionController:handler	Landroid/os/Handler;
    //   30: aload_0
    //   31: ldc 126
    //   33: iconst_0
    //   34: invokevirtual 603	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   37: ldc -127
    //   39: iconst_1
    //   40: invokeinterface 671 3 0
    //   45: putstatic 323	com/facebook/internal/LikeActionController:objectSuffix	I
    //   48: new 443	com/facebook/internal/FileLruCache
    //   51: astore_3
    //   52: getstatic 195	com/facebook/internal/LikeActionController:TAG	Ljava/lang/String;
    //   55: astore 4
    //   57: new 673	com/facebook/internal/FileLruCache$Limits
    //   60: astore_2
    //   61: aload_2
    //   62: invokespecial 674	com/facebook/internal/FileLruCache$Limits:<init>	()V
    //   65: aload_3
    //   66: aload_0
    //   67: aload 4
    //   69: aload_2
    //   70: invokespecial 677	com/facebook/internal/FileLruCache:<init>	(Landroid/content/Context;Ljava/lang/String;Lcom/facebook/internal/FileLruCache$Limits;)V
    //   73: aload_3
    //   74: putstatic 331	com/facebook/internal/LikeActionController:controllerDiskCache	Lcom/facebook/internal/FileLruCache;
    //   77: aload_0
    //   78: invokestatic 680	com/facebook/internal/LikeActionController:registerSessionBroadcastReceivers	(Landroid/content/Context;)V
    //   81: iconst_1
    //   82: putstatic 568	com/facebook/internal/LikeActionController:isInitialized	Z
    //   85: goto -74 -> 11
    //   88: astore_0
    //   89: ldc 2
    //   91: monitorexit
    //   92: aload_0
    //   93: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	94	0	paramContext	Context
    //   6	2	1	bool	boolean
    //   18	52	2	localObject	Object
    //   51	23	3	localFileLruCache	FileLruCache
    //   55	13	4	str	String
    // Exception table:
    //   from	to	target	type
    //   3	7	88	finally
    //   15	85	88	finally
  }
  
  private void performLikeOrUnlike(Activity paramActivity, boolean paramBoolean, Bundle paramBundle)
  {
    if (canUseOGPublish(paramBoolean)) {
      if (paramBoolean) {
        publishLikeAsync(paramActivity, paramBundle);
      }
    }
    for (;;)
    {
      return;
      publishUnlikeAsync(paramActivity, paramBundle);
      continue;
      presentLikeDialog(paramActivity, paramBundle);
    }
  }
  
  private void presentLikeDialog(Activity paramActivity, Bundle paramBundle)
  {
    LikeDialogBuilder localLikeDialogBuilder = new LikeDialogBuilder(paramActivity, this.objectId);
    if (localLikeDialogBuilder.canPresent())
    {
      trackPendingCall(localLikeDialogBuilder.build().present(), paramBundle);
      this.appEventsLogger.logSdkEvent("fb_like_control_did_present_dialog", null, paramBundle);
    }
    for (;;)
    {
      return;
      String str = localLikeDialogBuilder.getWebFallbackUrl();
      if ((!Utility.isNullOrEmpty(str)) && (FacebookWebFallbackDialog.presentWebFallback(paramActivity, str, localLikeDialogBuilder.getApplicationId(), localLikeDialogBuilder.getAppCall(), getFacebookDialogCallback(paramBundle)))) {
        this.appEventsLogger.logSdkEvent("fb_like_control_did_present_fallback_dialog", null, paramBundle);
      }
    }
  }
  
  private void publishLikeAsync(final Activity paramActivity, final Bundle paramBundle)
  {
    this.isPendingLikeOrUnlike = true;
    fetchVerifiedObjectId(new RequestCompletionCallback()
    {
      public void onComplete()
      {
        final Object localObject;
        if (Utility.isNullOrEmpty(LikeActionController.this.verifiedObjectId))
        {
          localObject = new Bundle();
          ((Bundle)localObject).putString("com.facebook.platform.status.ERROR_DESCRIPTION", "Invalid Object Id");
          LikeActionController.broadcastAction(LikeActionController.this.context, LikeActionController.this, "com.facebook.sdk.LikeActionController.DID_ERROR", (Bundle)localObject);
        }
        for (;;)
        {
          return;
          RequestBatch localRequestBatch = new RequestBatch();
          localObject = new LikeActionController.PublishLikeRequestWrapper(LikeActionController.this, LikeActionController.this.verifiedObjectId);
          ((LikeActionController.PublishLikeRequestWrapper)localObject).addToBatch(localRequestBatch);
          localRequestBatch.addCallback(new RequestBatch.Callback()
          {
            public void onBatchCompleted(RequestBatch paramAnonymous2RequestBatch)
            {
              LikeActionController.access$1502(LikeActionController.this, false);
              if (localObject.error != null)
              {
                LikeActionController.this.updateState(false, LikeActionController.this.likeCountStringWithLike, LikeActionController.this.likeCountStringWithoutLike, LikeActionController.this.socialSentenceWithLike, LikeActionController.this.socialSentenceWithoutLike, LikeActionController.this.unlikeToken);
                LikeActionController.this.presentLikeDialog(LikeActionController.6.this.val$activity, LikeActionController.6.this.val$analyticsParameters);
              }
              for (;;)
              {
                return;
                LikeActionController.access$2002(LikeActionController.this, Utility.coerceValueIfNullOrEmpty(localObject.unlikeToken, null));
                LikeActionController.access$2202(LikeActionController.this, true);
                LikeActionController.this.appEventsLogger.logSdkEvent("fb_like_control_did_like", null, LikeActionController.6.this.val$analyticsParameters);
                LikeActionController.this.toggleAgainIfNeeded(LikeActionController.6.this.val$activity, LikeActionController.6.this.val$analyticsParameters);
              }
            }
          });
          localRequestBatch.executeAsync();
        }
      }
    });
  }
  
  private void publishUnlikeAsync(final Activity paramActivity, final Bundle paramBundle)
  {
    this.isPendingLikeOrUnlike = true;
    RequestBatch localRequestBatch = new RequestBatch();
    final PublishUnlikeRequestWrapper localPublishUnlikeRequestWrapper = new PublishUnlikeRequestWrapper(this.unlikeToken);
    localPublishUnlikeRequestWrapper.addToBatch(localRequestBatch);
    localRequestBatch.addCallback(new RequestBatch.Callback()
    {
      public void onBatchCompleted(RequestBatch paramAnonymousRequestBatch)
      {
        LikeActionController.access$1502(LikeActionController.this, false);
        if (localPublishUnlikeRequestWrapper.error != null)
        {
          LikeActionController.this.updateState(true, LikeActionController.this.likeCountStringWithLike, LikeActionController.this.likeCountStringWithoutLike, LikeActionController.this.socialSentenceWithLike, LikeActionController.this.socialSentenceWithoutLike, LikeActionController.this.unlikeToken);
          LikeActionController.this.presentLikeDialog(paramActivity, paramBundle);
        }
        for (;;)
        {
          return;
          LikeActionController.access$2002(LikeActionController.this, null);
          LikeActionController.access$2202(LikeActionController.this, false);
          LikeActionController.this.appEventsLogger.logSdkEvent("fb_like_control_did_unlike", null, paramBundle);
          LikeActionController.this.toggleAgainIfNeeded(paramActivity, paramBundle);
        }
      }
    });
    localRequestBatch.executeAsync();
  }
  
  private static void putControllerInMemoryCache(String paramString, LikeActionController paramLikeActionController)
  {
    paramString = getCacheKeyForObjectId(paramString);
    mruCacheWorkQueue.addActiveWorkItem(new MRUCacheWorkItem(paramString, true));
    cache.put(paramString, paramLikeActionController);
  }
  
  private void refreshStatusAsync()
  {
    if ((this.session == null) || (this.session.isClosed()) || (SessionState.CREATED.equals(this.session.getState()))) {
      refreshStatusViaService();
    }
    for (;;)
    {
      return;
      fetchVerifiedObjectId(new RequestCompletionCallback()
      {
        public void onComplete()
        {
          final LikeActionController.GetOGObjectLikesRequestWrapper localGetOGObjectLikesRequestWrapper = new LikeActionController.GetOGObjectLikesRequestWrapper(LikeActionController.this, LikeActionController.this.verifiedObjectId);
          final LikeActionController.GetEngagementRequestWrapper localGetEngagementRequestWrapper = new LikeActionController.GetEngagementRequestWrapper(LikeActionController.this, LikeActionController.this.verifiedObjectId);
          RequestBatch localRequestBatch = new RequestBatch();
          localGetOGObjectLikesRequestWrapper.addToBatch(localRequestBatch);
          localGetEngagementRequestWrapper.addToBatch(localRequestBatch);
          localRequestBatch.addCallback(new RequestBatch.Callback()
          {
            public void onBatchCompleted(RequestBatch paramAnonymous2RequestBatch)
            {
              if ((localGetOGObjectLikesRequestWrapper.error != null) || (localGetEngagementRequestWrapper.error != null)) {
                Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Unable to refresh like state for id: '%s'", new Object[] { LikeActionController.this.objectId });
              }
              for (;;)
              {
                return;
                LikeActionController.this.updateState(localGetOGObjectLikesRequestWrapper.objectIsLiked, localGetEngagementRequestWrapper.likeCountStringWithLike, localGetEngagementRequestWrapper.likeCountStringWithoutLike, localGetEngagementRequestWrapper.socialSentenceStringWithLike, localGetEngagementRequestWrapper.socialSentenceStringWithoutLike, localGetOGObjectLikesRequestWrapper.unlikeToken);
              }
            }
          });
          localRequestBatch.executeAsync();
        }
      });
    }
  }
  
  private void refreshStatusViaService()
  {
    LikeStatusClient localLikeStatusClient = new LikeStatusClient(this.context, Settings.getApplicationId(), this.objectId);
    if (!localLikeStatusClient.start()) {}
    for (;;)
    {
      return;
      localLikeStatusClient.setCompletedListener(new PlatformServiceClient.CompletedListener()
      {
        public void completed(Bundle paramAnonymousBundle)
        {
          if ((paramAnonymousBundle == null) || (!paramAnonymousBundle.containsKey("com.facebook.platform.extra.OBJECT_IS_LIKED"))) {}
          for (;;)
          {
            return;
            boolean bool = paramAnonymousBundle.getBoolean("com.facebook.platform.extra.OBJECT_IS_LIKED");
            String str2 = paramAnonymousBundle.getString("com.facebook.platform.extra.LIKE_COUNT_STRING_WITH_LIKE");
            String str4 = paramAnonymousBundle.getString("com.facebook.platform.extra.LIKE_COUNT_STRING_WITHOUT_LIKE");
            String str3 = paramAnonymousBundle.getString("com.facebook.platform.extra.SOCIAL_SENTENCE_WITH_LIKE");
            String str1 = paramAnonymousBundle.getString("com.facebook.platform.extra.SOCIAL_SENTENCE_WITHOUT_LIKE");
            paramAnonymousBundle = paramAnonymousBundle.getString("com.facebook.platform.extra.UNLIKE_TOKEN");
            LikeActionController.this.updateState(bool, str2, str4, str3, str1, paramAnonymousBundle);
          }
        }
      });
    }
  }
  
  private static void registerSessionBroadcastReceivers(Context paramContext)
  {
    paramContext = LocalBroadcastManager.getInstance(paramContext);
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.facebook.sdk.ACTIVE_SESSION_UNSET");
    localIntentFilter.addAction("com.facebook.sdk.ACTIVE_SESSION_CLOSED");
    localIntentFilter.addAction("com.facebook.sdk.ACTIVE_SESSION_OPENED");
    paramContext.registerReceiver(new BroadcastReceiver()
    {
      public void onReceive(final Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        if (LikeActionController.isPendingBroadcastReset) {
          return;
        }
        paramAnonymousIntent = paramAnonymousIntent.getAction();
        if ((Utility.areObjectsEqual("com.facebook.sdk.ACTIVE_SESSION_UNSET", paramAnonymousIntent)) || (Utility.areObjectsEqual("com.facebook.sdk.ACTIVE_SESSION_CLOSED", paramAnonymousIntent))) {}
        for (final boolean bool = true;; bool = false)
        {
          LikeActionController.access$202(true);
          LikeActionController.handler.postDelayed(new Runnable()
          {
            public void run()
            {
              if (bool)
              {
                LikeActionController.access$302((LikeActionController.objectSuffix + 1) % 1000);
                paramAnonymousContext.getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).edit().putInt("OBJECT_SUFFIX", LikeActionController.objectSuffix).apply();
                LikeActionController.cache.clear();
                LikeActionController.controllerDiskCache.clearCache();
              }
              LikeActionController.broadcastAction(paramAnonymousContext, null, "com.facebook.sdk.LikeActionController.DID_RESET");
              LikeActionController.access$202(false);
            }
          }, 100L);
          break;
        }
      }
    }, localIntentFilter);
  }
  
  private static void serializeToDiskAsync(LikeActionController paramLikeActionController)
  {
    String str = serializeToJson(paramLikeActionController);
    paramLikeActionController = getCacheKeyForObjectId(paramLikeActionController.objectId);
    if ((!Utility.isNullOrEmpty(str)) && (!Utility.isNullOrEmpty(paramLikeActionController))) {
      diskIOWorkQueue.addActiveWorkItem(new SerializeToDiskWorkItem(paramLikeActionController, str));
    }
  }
  
  private static void serializeToDiskSynchronously(String paramString1, String paramString2)
  {
    Object localObject = null;
    String str = null;
    try
    {
      paramString1 = controllerDiskCache.openPutStream(paramString1);
      str = paramString1;
      localObject = paramString1;
      paramString1.write(paramString2.getBytes());
      if (paramString1 != null) {
        Utility.closeQuietly(paramString1);
      }
      return;
    }
    catch (IOException paramString1)
    {
      for (;;)
      {
        localObject = str;
        Log.e(TAG, "Unable to serialize controller to disk", paramString1);
        if (str != null) {
          Utility.closeQuietly(str);
        }
      }
    }
    finally
    {
      if (localObject != null) {
        Utility.closeQuietly((Closeable)localObject);
      }
    }
  }
  
  private static String serializeToJson(LikeActionController paramLikeActionController)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("com.facebook.internal.LikeActionController.version", 2);
      localJSONObject.put("object_id", paramLikeActionController.objectId);
      localJSONObject.put("like_count_string_with_like", paramLikeActionController.likeCountStringWithLike);
      localJSONObject.put("like_count_string_without_like", paramLikeActionController.likeCountStringWithoutLike);
      localJSONObject.put("social_sentence_with_like", paramLikeActionController.socialSentenceWithLike);
      localJSONObject.put("social_sentence_without_like", paramLikeActionController.socialSentenceWithoutLike);
      localJSONObject.put("is_object_liked", paramLikeActionController.isObjectLiked);
      localJSONObject.put("unlike_token", paramLikeActionController.unlikeToken);
      if (paramLikeActionController.pendingCallId != null) {
        localJSONObject.put("pending_call_id", paramLikeActionController.pendingCallId.toString());
      }
      if (paramLikeActionController.pendingCallAnalyticsBundle != null)
      {
        paramLikeActionController = BundleJSONConverter.convertToJSON(paramLikeActionController.pendingCallAnalyticsBundle);
        if (paramLikeActionController != null) {
          localJSONObject.put("pending_call_analytics_bundle", paramLikeActionController);
        }
      }
      paramLikeActionController = localJSONObject.toString();
    }
    catch (JSONException paramLikeActionController)
    {
      for (;;)
      {
        Log.e(TAG, "Unable to serialize controller to JSON", paramLikeActionController);
        paramLikeActionController = null;
      }
    }
    return paramLikeActionController;
  }
  
  private void stopTrackingPendingCall()
  {
    PendingCallStore.getInstance().stopTrackingPendingCall(this.pendingCallId);
    this.pendingCallId = null;
    this.pendingCallAnalyticsBundle = null;
    storeObjectIdForPendingController(null);
  }
  
  private void storeObjectIdForPendingController(String paramString)
  {
    objectIdForPendingController = paramString;
    this.context.getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).edit().putString("PENDING_CONTROLLER_KEY", objectIdForPendingController).apply();
  }
  
  private void toggleAgainIfNeeded(Activity paramActivity, Bundle paramBundle)
  {
    if (this.isObjectLiked != this.isObjectLikedOnServer) {
      performLikeOrUnlike(paramActivity, this.isObjectLiked, paramBundle);
    }
  }
  
  private void trackPendingCall(FacebookDialog.PendingCall paramPendingCall, Bundle paramBundle)
  {
    PendingCallStore.getInstance().trackPendingCall(paramPendingCall);
    this.pendingCallId = paramPendingCall.getCallId();
    storeObjectIdForPendingController(this.objectId);
    this.pendingCallAnalyticsBundle = paramBundle;
    serializeToDiskAsync(this);
  }
  
  private void updateState(boolean paramBoolean, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    paramString1 = Utility.coerceValueIfNullOrEmpty(paramString1, null);
    paramString2 = Utility.coerceValueIfNullOrEmpty(paramString2, null);
    paramString3 = Utility.coerceValueIfNullOrEmpty(paramString3, null);
    paramString4 = Utility.coerceValueIfNullOrEmpty(paramString4, null);
    paramString5 = Utility.coerceValueIfNullOrEmpty(paramString5, null);
    int i;
    if ((paramBoolean != this.isObjectLiked) || (!Utility.areObjectsEqual(paramString1, this.likeCountStringWithLike)) || (!Utility.areObjectsEqual(paramString2, this.likeCountStringWithoutLike)) || (!Utility.areObjectsEqual(paramString3, this.socialSentenceWithLike)) || (!Utility.areObjectsEqual(paramString4, this.socialSentenceWithoutLike)) || (!Utility.areObjectsEqual(paramString5, this.unlikeToken)))
    {
      i = 1;
      if (i != 0) {
        break label117;
      }
    }
    for (;;)
    {
      return;
      i = 0;
      break;
      label117:
      this.isObjectLiked = paramBoolean;
      this.likeCountStringWithLike = paramString1;
      this.likeCountStringWithoutLike = paramString2;
      this.socialSentenceWithLike = paramString3;
      this.socialSentenceWithoutLike = paramString4;
      this.unlikeToken = paramString5;
      serializeToDiskAsync(this);
      broadcastAction(this.context, this, "com.facebook.sdk.LikeActionController.UPDATED");
    }
  }
  
  public String getLikeCountString()
  {
    if (this.isObjectLiked) {}
    for (String str = this.likeCountStringWithLike;; str = this.likeCountStringWithoutLike) {
      return str;
    }
  }
  
  public String getObjectId()
  {
    return this.objectId;
  }
  
  public String getSocialSentence()
  {
    if (this.isObjectLiked) {}
    for (String str = this.socialSentenceWithLike;; str = this.socialSentenceWithoutLike) {
      return str;
    }
  }
  
  public boolean isObjectLiked()
  {
    return this.isObjectLiked;
  }
  
  public void toggleLike(Activity paramActivity, Bundle paramBundle)
  {
    this.appEventsLogger.logSdkEvent("fb_like_control_did_tap", null, paramBundle);
    boolean bool;
    if (!this.isObjectLiked)
    {
      bool = true;
      if (!canUseOGPublish(bool)) {
        break label79;
      }
      updateState(bool, this.likeCountStringWithLike, this.likeCountStringWithoutLike, this.socialSentenceWithLike, this.socialSentenceWithoutLike, this.unlikeToken);
      if (!this.isPendingLikeOrUnlike) {
        break label79;
      }
      this.appEventsLogger.logSdkEvent("fb_like_control_did_undo_quickly", null, paramBundle);
    }
    for (;;)
    {
      return;
      bool = false;
      break;
      label79:
      performLikeOrUnlike(paramActivity, bool, paramBundle);
    }
  }
  
  private abstract class AbstractRequestWrapper
  {
    FacebookRequestError error;
    protected String objectId;
    private Request request;
    
    protected AbstractRequestWrapper(String paramString)
    {
      this.objectId = paramString;
    }
    
    void addToBatch(RequestBatch paramRequestBatch)
    {
      paramRequestBatch.add(this.request);
    }
    
    protected void processError(FacebookRequestError paramFacebookRequestError)
    {
      Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error running request for object '%s' : %s", new Object[] { this.objectId, paramFacebookRequestError });
    }
    
    protected abstract void processSuccess(Response paramResponse);
    
    protected void setRequest(Request paramRequest)
    {
      this.request = paramRequest;
      paramRequest.setVersion("v2.2");
      paramRequest.setCallback(new Request.Callback()
      {
        public void onCompleted(Response paramAnonymousResponse)
        {
          LikeActionController.AbstractRequestWrapper.this.error = paramAnonymousResponse.getError();
          if (LikeActionController.AbstractRequestWrapper.this.error != null) {
            LikeActionController.AbstractRequestWrapper.this.processError(LikeActionController.AbstractRequestWrapper.this.error);
          }
          for (;;)
          {
            return;
            LikeActionController.AbstractRequestWrapper.this.processSuccess(paramAnonymousResponse);
          }
        }
      });
    }
  }
  
  private static class CreateLikeActionControllerWorkItem
    implements Runnable
  {
    private LikeActionController.CreationCallback callback;
    private Context context;
    private String objectId;
    
    CreateLikeActionControllerWorkItem(Context paramContext, String paramString, LikeActionController.CreationCallback paramCreationCallback)
    {
      this.context = paramContext;
      this.objectId = paramString;
      this.callback = paramCreationCallback;
    }
    
    public void run()
    {
      LikeActionController.createControllerForObjectId(this.context, this.objectId, this.callback);
    }
  }
  
  public static abstract interface CreationCallback
  {
    public abstract void onComplete(LikeActionController paramLikeActionController);
  }
  
  private class GetEngagementRequestWrapper
    extends LikeActionController.AbstractRequestWrapper
  {
    String likeCountStringWithLike;
    String likeCountStringWithoutLike;
    String socialSentenceStringWithLike;
    String socialSentenceStringWithoutLike;
    
    GetEngagementRequestWrapper(String paramString)
    {
      super(paramString);
      Bundle localBundle = new Bundle();
      localBundle.putString("fields", "engagement.fields(count_string_with_like,count_string_without_like,social_sentence_with_like,social_sentence_without_like)");
      setRequest(new Request(LikeActionController.this.session, paramString, localBundle, HttpMethod.GET));
    }
    
    protected void processError(FacebookRequestError paramFacebookRequestError)
    {
      Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error fetching engagement for object '%s' : %s", new Object[] { this.objectId, paramFacebookRequestError });
      LikeActionController.this.logAppEventForError("get_engagement", paramFacebookRequestError);
    }
    
    protected void processSuccess(Response paramResponse)
    {
      paramResponse = Utility.tryGetJSONObjectFromResponse(paramResponse.getGraphObject(), "engagement");
      if (paramResponse != null)
      {
        this.likeCountStringWithLike = paramResponse.optString("count_string_with_like");
        this.likeCountStringWithoutLike = paramResponse.optString("count_string_without_like");
        this.socialSentenceStringWithLike = paramResponse.optString("social_sentence_with_like");
        this.socialSentenceStringWithoutLike = paramResponse.optString("social_sentence_without_like");
      }
    }
  }
  
  private class GetOGObjectIdRequestWrapper
    extends LikeActionController.AbstractRequestWrapper
  {
    String verifiedObjectId;
    
    GetOGObjectIdRequestWrapper(String paramString)
    {
      super(paramString);
      Bundle localBundle = new Bundle();
      localBundle.putString("fields", "og_object.fields(id)");
      localBundle.putString("ids", paramString);
      setRequest(new Request(LikeActionController.this.session, "", localBundle, HttpMethod.GET));
    }
    
    protected void processError(FacebookRequestError paramFacebookRequestError)
    {
      if (paramFacebookRequestError.getErrorMessage().contains("og_object")) {
        this.error = null;
      }
      for (;;)
      {
        return;
        Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error getting the FB id for object '%s' : %s", new Object[] { this.objectId, paramFacebookRequestError });
      }
    }
    
    protected void processSuccess(Response paramResponse)
    {
      paramResponse = Utility.tryGetJSONObjectFromResponse(paramResponse.getGraphObject(), this.objectId);
      if (paramResponse != null)
      {
        paramResponse = paramResponse.optJSONObject("og_object");
        if (paramResponse != null) {
          this.verifiedObjectId = paramResponse.optString("id");
        }
      }
    }
  }
  
  private class GetOGObjectLikesRequestWrapper
    extends LikeActionController.AbstractRequestWrapper
  {
    boolean objectIsLiked;
    String unlikeToken;
    
    GetOGObjectLikesRequestWrapper(String paramString)
    {
      super(paramString);
      Bundle localBundle = new Bundle();
      localBundle.putString("fields", "id,application");
      localBundle.putString("object", paramString);
      setRequest(new Request(LikeActionController.this.session, "me/og.likes", localBundle, HttpMethod.GET));
    }
    
    protected void processError(FacebookRequestError paramFacebookRequestError)
    {
      Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error fetching like status for object '%s' : %s", new Object[] { this.objectId, paramFacebookRequestError });
      LikeActionController.this.logAppEventForError("get_og_object_like", paramFacebookRequestError);
    }
    
    protected void processSuccess(Response paramResponse)
    {
      paramResponse = Utility.tryGetJSONArrayFromResponse(paramResponse.getGraphObject(), "data");
      if (paramResponse != null) {
        for (int i = 0; i < paramResponse.length(); i++)
        {
          JSONObject localJSONObject2 = paramResponse.optJSONObject(i);
          if (localJSONObject2 != null)
          {
            this.objectIsLiked = true;
            JSONObject localJSONObject1 = localJSONObject2.optJSONObject("application");
            if ((localJSONObject1 != null) && (Utility.areObjectsEqual(LikeActionController.this.session.getApplicationId(), localJSONObject1.optString("id")))) {
              this.unlikeToken = localJSONObject2.optString("id");
            }
          }
        }
      }
    }
  }
  
  private class GetPageIdRequestWrapper
    extends LikeActionController.AbstractRequestWrapper
  {
    boolean objectIsPage;
    String verifiedObjectId;
    
    GetPageIdRequestWrapper(String paramString)
    {
      super(paramString);
      Bundle localBundle = new Bundle();
      localBundle.putString("fields", "id");
      localBundle.putString("ids", paramString);
      setRequest(new Request(LikeActionController.this.session, "", localBundle, HttpMethod.GET));
    }
    
    protected void processError(FacebookRequestError paramFacebookRequestError)
    {
      Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error getting the FB id for object '%s' : %s", new Object[] { this.objectId, paramFacebookRequestError });
    }
    
    protected void processSuccess(Response paramResponse)
    {
      paramResponse = Utility.tryGetJSONObjectFromResponse(paramResponse.getGraphObject(), this.objectId);
      if (paramResponse != null)
      {
        this.verifiedObjectId = paramResponse.optString("id");
        if (Utility.isNullOrEmpty(this.verifiedObjectId)) {
          break label44;
        }
      }
      label44:
      for (boolean bool = true;; bool = false)
      {
        this.objectIsPage = bool;
        return;
      }
    }
  }
  
  private static class LikeDialogBuilder
    extends FacebookDialog.Builder<LikeDialogBuilder>
  {
    private String objectId;
    
    public LikeDialogBuilder(Activity paramActivity, String paramString)
    {
      super();
      this.objectId = paramString;
    }
    
    public FacebookDialog.PendingCall getAppCall()
    {
      return this.appCall;
    }
    
    public String getApplicationId()
    {
      return this.applicationId;
    }
    
    protected EnumSet<? extends FacebookDialog.DialogFeature> getDialogFeatures()
    {
      return EnumSet.of(LikeActionController.LikeDialogFeature.LIKE_DIALOG);
    }
    
    protected Bundle getMethodArguments()
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("object_id", this.objectId);
      return localBundle;
    }
    
    public String getWebFallbackUrl()
    {
      return getWebFallbackUrlInternal();
    }
  }
  
  private static enum LikeDialogFeature
    implements FacebookDialog.DialogFeature
  {
    LIKE_DIALOG(20140701);
    
    private int minVersion;
    
    private LikeDialogFeature(int paramInt)
    {
      this.minVersion = paramInt;
    }
    
    public String getAction()
    {
      return "com.facebook.platform.action.request.LIKE_DIALOG";
    }
    
    public int getMinVersion()
    {
      return this.minVersion;
    }
  }
  
  private static class MRUCacheWorkItem
    implements Runnable
  {
    private static ArrayList<String> mruCachedItems = new ArrayList();
    private String cacheItem;
    private boolean shouldTrim;
    
    MRUCacheWorkItem(String paramString, boolean paramBoolean)
    {
      this.cacheItem = paramString;
      this.shouldTrim = paramBoolean;
    }
    
    public void run()
    {
      if (this.cacheItem != null)
      {
        mruCachedItems.remove(this.cacheItem);
        mruCachedItems.add(0, this.cacheItem);
      }
      if ((this.shouldTrim) && (mruCachedItems.size() >= 128)) {
        while (64 < mruCachedItems.size())
        {
          String str = (String)mruCachedItems.remove(mruCachedItems.size() - 1);
          LikeActionController.cache.remove(str);
        }
      }
    }
  }
  
  private class PublishLikeRequestWrapper
    extends LikeActionController.AbstractRequestWrapper
  {
    String unlikeToken;
    
    PublishLikeRequestWrapper(String paramString)
    {
      super(paramString);
      Bundle localBundle = new Bundle();
      localBundle.putString("object", paramString);
      setRequest(new Request(LikeActionController.this.session, "me/og.likes", localBundle, HttpMethod.POST));
    }
    
    protected void processError(FacebookRequestError paramFacebookRequestError)
    {
      if (paramFacebookRequestError.getErrorCode() == 3501) {
        this.error = null;
      }
      for (;;)
      {
        return;
        Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error liking object '%s' : %s", new Object[] { this.objectId, paramFacebookRequestError });
        LikeActionController.this.logAppEventForError("publish_like", paramFacebookRequestError);
      }
    }
    
    protected void processSuccess(Response paramResponse)
    {
      this.unlikeToken = Utility.safeGetStringFromResponse(paramResponse.getGraphObject(), "id");
    }
  }
  
  private class PublishUnlikeRequestWrapper
    extends LikeActionController.AbstractRequestWrapper
  {
    private String unlikeToken;
    
    PublishUnlikeRequestWrapper(String paramString)
    {
      super(null);
      this.unlikeToken = paramString;
      setRequest(new Request(LikeActionController.this.session, paramString, null, HttpMethod.DELETE));
    }
    
    protected void processError(FacebookRequestError paramFacebookRequestError)
    {
      Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error unliking object with unlike token '%s' : %s", new Object[] { this.unlikeToken, paramFacebookRequestError });
      LikeActionController.this.logAppEventForError("publish_unlike", paramFacebookRequestError);
    }
    
    protected void processSuccess(Response paramResponse) {}
  }
  
  private static abstract interface RequestCompletionCallback
  {
    public abstract void onComplete();
  }
  
  private static class SerializeToDiskWorkItem
    implements Runnable
  {
    private String cacheKey;
    private String controllerJson;
    
    SerializeToDiskWorkItem(String paramString1, String paramString2)
    {
      this.cacheKey = paramString1;
      this.controllerJson = paramString2;
    }
    
    public void run()
    {
      LikeActionController.serializeToDiskSynchronously(this.cacheKey, this.controllerJson);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\internal\LikeActionController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */