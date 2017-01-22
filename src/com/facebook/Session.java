package com.facebook;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.SessionAuthorizationType;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.model.GraphMultiResult;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import org.json.JSONException;
import org.json.JSONObject;

public class Session
  implements Serializable
{
  public static final String ACTION_ACTIVE_SESSION_CLOSED = "com.facebook.sdk.ACTIVE_SESSION_CLOSED";
  public static final String ACTION_ACTIVE_SESSION_OPENED = "com.facebook.sdk.ACTIVE_SESSION_OPENED";
  public static final String ACTION_ACTIVE_SESSION_SET = "com.facebook.sdk.ACTIVE_SESSION_SET";
  public static final String ACTION_ACTIVE_SESSION_UNSET = "com.facebook.sdk.ACTIVE_SESSION_UNSET";
  private static final String AUTH_BUNDLE_SAVE_KEY = "com.facebook.sdk.Session.authBundleKey";
  public static final int DEFAULT_AUTHORIZE_ACTIVITY_CODE = 64206;
  private static final String MANAGE_PERMISSION_PREFIX = "manage";
  private static final Set<String> OTHER_PUBLISH_PERMISSIONS = new HashSet() {};
  private static final String PUBLISH_PERMISSION_PREFIX = "publish";
  private static final String SESSION_BUNDLE_SAVE_KEY = "com.facebook.sdk.Session.saveSessionKey";
  private static final Object STATIC_LOCK;
  public static final String TAG = Session.class.getCanonicalName();
  private static final int TOKEN_EXTEND_RETRY_SECONDS = 3600;
  private static final int TOKEN_EXTEND_THRESHOLD_SECONDS = 86400;
  public static final String WEB_VIEW_ERROR_CODE_KEY = "com.facebook.sdk.WebViewErrorCode";
  public static final String WEB_VIEW_FAILING_URL_KEY = "com.facebook.sdk.FailingUrl";
  private static Session activeSession;
  private static final long serialVersionUID = 1L;
  private static volatile Context staticContext;
  private AppEventsLogger appEventsLogger;
  private String applicationId;
  private volatile Bundle authorizationBundle;
  private AuthorizationClient authorizationClient;
  private AutoPublishAsyncTask autoPublishAsyncTask;
  private final List<StatusCallback> callbacks;
  private volatile TokenRefreshRequest currentTokenRefreshRequest;
  private Handler handler;
  private Date lastAttemptedTokenExtendDate = new Date(0L);
  private final Object lock = new Object();
  private AuthorizationRequest pendingAuthorizationRequest;
  private SessionState state;
  private TokenCachingStrategy tokenCachingStrategy;
  private AccessToken tokenInfo;
  
  static
  {
    STATIC_LOCK = new Object();
  }
  
  public Session(Context paramContext)
  {
    this(paramContext, null, null, true);
  }
  
  Session(Context paramContext, String paramString, TokenCachingStrategy paramTokenCachingStrategy)
  {
    this(paramContext, paramString, paramTokenCachingStrategy, true);
  }
  
  Session(Context paramContext, String paramString, TokenCachingStrategy paramTokenCachingStrategy, boolean paramBoolean)
  {
    Object localObject1 = paramString;
    if (paramContext != null)
    {
      localObject1 = paramString;
      if (paramString == null) {
        localObject1 = Utility.getMetadataApplicationId(paramContext);
      }
    }
    Validate.notNull(localObject1, "applicationId");
    initializeStaticContext(paramContext);
    paramContext = paramTokenCachingStrategy;
    if (paramTokenCachingStrategy == null) {
      paramContext = new SharedPreferencesTokenCachingStrategy(staticContext);
    }
    this.applicationId = ((String)localObject1);
    this.tokenCachingStrategy = paramContext;
    this.state = SessionState.CREATED;
    this.pendingAuthorizationRequest = null;
    this.callbacks = new ArrayList();
    this.handler = new Handler(Looper.getMainLooper());
    paramString = (String)localObject2;
    if (paramBoolean) {
      paramString = paramContext.load();
    }
    if (TokenCachingStrategy.hasTokenInformation(paramString))
    {
      paramTokenCachingStrategy = TokenCachingStrategy.getDate(paramString, "com.facebook.TokenCachingStrategy.ExpirationDate");
      localObject1 = new Date();
      if ((paramTokenCachingStrategy == null) || (paramTokenCachingStrategy.before((Date)localObject1)))
      {
        paramContext.clear();
        this.tokenInfo = AccessToken.createEmptyToken();
      }
    }
    for (;;)
    {
      return;
      this.tokenInfo = AccessToken.createFromCache(paramString);
      this.state = SessionState.CREATED_TOKEN_LOADED;
      continue;
      this.tokenInfo = AccessToken.createEmptyToken();
    }
  }
  
  private Session(String paramString, SessionState paramSessionState, AccessToken paramAccessToken, Date paramDate, boolean paramBoolean, AuthorizationRequest paramAuthorizationRequest)
  {
    this.applicationId = paramString;
    this.state = paramSessionState;
    this.tokenInfo = paramAccessToken;
    this.lastAttemptedTokenExtendDate = paramDate;
    this.pendingAuthorizationRequest = paramAuthorizationRequest;
    this.handler = new Handler(Looper.getMainLooper());
    this.currentTokenRefreshRequest = null;
    this.tokenCachingStrategy = null;
    this.callbacks = new ArrayList();
  }
  
  private Session(String paramString, SessionState paramSessionState, AccessToken paramAccessToken, Date paramDate, boolean paramBoolean, AuthorizationRequest paramAuthorizationRequest, Set<String> paramSet)
  {
    this.applicationId = paramString;
    this.state = paramSessionState;
    this.tokenInfo = paramAccessToken;
    this.lastAttemptedTokenExtendDate = paramDate;
    this.pendingAuthorizationRequest = paramAuthorizationRequest;
    this.handler = new Handler(Looper.getMainLooper());
    this.currentTokenRefreshRequest = null;
    this.tokenCachingStrategy = null;
    this.callbacks = new ArrayList();
  }
  
  private static boolean areEqual(Object paramObject1, Object paramObject2)
  {
    boolean bool;
    if (paramObject1 == null) {
      if (paramObject2 == null) {
        bool = true;
      }
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      bool = paramObject1.equals(paramObject2);
    }
  }
  
  private void autoPublishAsync()
  {
    Object localObject3 = null;
    Object localObject1 = localObject3;
    try
    {
      if (this.autoPublishAsyncTask == null)
      {
        localObject1 = localObject3;
        if (Settings.getShouldAutoPublishInstall())
        {
          String str = this.applicationId;
          localObject1 = localObject3;
          if (str != null)
          {
            localObject1 = new com/facebook/Session$AutoPublishAsyncTask;
            ((AutoPublishAsyncTask)localObject1).<init>(this, str, staticContext);
            this.autoPublishAsyncTask = ((AutoPublishAsyncTask)localObject1);
          }
        }
      }
      if (localObject1 != null) {
        ((AutoPublishAsyncTask)localObject1).execute(new Void[0]);
      }
      return;
    }
    finally {}
  }
  
  private void finishAuthorization(AccessToken paramAccessToken, Exception paramException)
  {
    SessionState localSessionState = this.state;
    if (paramAccessToken != null)
    {
      this.tokenInfo = paramAccessToken;
      saveTokenToCache(paramAccessToken);
      this.state = SessionState.OPENED;
    }
    for (;;)
    {
      this.pendingAuthorizationRequest = null;
      postStateChange(localSessionState, this.state, paramException);
      return;
      if (paramException != null) {
        this.state = SessionState.CLOSED_LOGIN_FAILED;
      }
    }
  }
  
  private void finishReauthorization(AccessToken paramAccessToken, Exception paramException)
  {
    SessionState localSessionState = this.state;
    if (paramAccessToken != null)
    {
      this.tokenInfo = paramAccessToken;
      saveTokenToCache(paramAccessToken);
      this.state = SessionState.OPENED_TOKEN_UPDATED;
    }
    this.pendingAuthorizationRequest = null;
    postStateChange(localSessionState, this.state, paramException);
  }
  
  public static final Session getActiveSession()
  {
    synchronized (STATIC_LOCK)
    {
      Session localSession = activeSession;
      return localSession;
    }
  }
  
  private AppEventsLogger getAppEventsLogger()
  {
    synchronized (this.lock)
    {
      if (this.appEventsLogger == null) {
        this.appEventsLogger = AppEventsLogger.newLogger(staticContext, this.applicationId);
      }
      AppEventsLogger localAppEventsLogger = this.appEventsLogger;
      return localAppEventsLogger;
    }
  }
  
  private Intent getLoginActivityIntent(AuthorizationRequest paramAuthorizationRequest)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(getStaticContext(), LoginActivity.class);
    localIntent.setAction(paramAuthorizationRequest.getLoginBehavior().toString());
    localIntent.putExtras(LoginActivity.populateIntentExtras(paramAuthorizationRequest.getAuthorizationClientRequest()));
    return localIntent;
  }
  
  static Context getStaticContext()
  {
    return staticContext;
  }
  
  private void handleAuthorizationResult(int paramInt, AuthorizationClient.Result paramResult)
  {
    Object localObject3 = null;
    Object localObject1 = null;
    Object localObject2;
    if (paramInt == -1) {
      if (paramResult.code == AuthorizationClient.Result.Code.SUCCESS) {
        localObject2 = paramResult.token;
      }
    }
    for (;;)
    {
      logAuthorizationComplete(paramResult.code, paramResult.loggingExtras, (Exception)localObject1);
      this.authorizationClient = null;
      finishAuthOrReauth((AccessToken)localObject2, (Exception)localObject1);
      return;
      localObject1 = new FacebookAuthorizationException(paramResult.errorMessage);
      localObject2 = localObject3;
      continue;
      localObject2 = localObject3;
      if (paramInt == 0)
      {
        localObject1 = new FacebookOperationCanceledException(paramResult.errorMessage);
        localObject2 = localObject3;
      }
    }
  }
  
  static PermissionsPair handlePermissionResponse(Response paramResponse)
  {
    ArrayList localArrayList = null;
    if (paramResponse.getError() != null) {
      paramResponse = localArrayList;
    }
    for (;;)
    {
      return paramResponse;
      Object localObject1 = (GraphMultiResult)paramResponse.getGraphObjectAs(GraphMultiResult.class);
      paramResponse = localArrayList;
      if (localObject1 != null)
      {
        localObject1 = ((GraphMultiResult)localObject1).getData();
        paramResponse = localArrayList;
        if (localObject1 != null)
        {
          paramResponse = localArrayList;
          if (((GraphObjectList)localObject1).size() != 0)
          {
            localArrayList = new ArrayList(((GraphObjectList)localObject1).size());
            paramResponse = new ArrayList(((GraphObjectList)localObject1).size());
            Object localObject2 = (GraphObject)((GraphObjectList)localObject1).get(0);
            if (((GraphObject)localObject2).getProperty("permission") != null)
            {
              localObject2 = ((GraphObjectList)localObject1).iterator();
              while (((Iterator)localObject2).hasNext())
              {
                Object localObject3 = (GraphObject)((Iterator)localObject2).next();
                localObject1 = (String)((GraphObject)localObject3).getProperty("permission");
                if (!((String)localObject1).equals("installed"))
                {
                  localObject3 = (String)((GraphObject)localObject3).getProperty("status");
                  if (((String)localObject3).equals("granted")) {
                    localArrayList.add(localObject1);
                  } else if (((String)localObject3).equals("declined")) {
                    paramResponse.add(localObject1);
                  }
                }
              }
            }
            localObject1 = ((GraphObject)localObject2).asMap().entrySet().iterator();
            while (((Iterator)localObject1).hasNext())
            {
              localObject2 = (Map.Entry)((Iterator)localObject1).next();
              if ((!((String)((Map.Entry)localObject2).getKey()).equals("installed")) && (((Integer)((Map.Entry)localObject2).getValue()).intValue() == 1)) {
                localArrayList.add(((Map.Entry)localObject2).getKey());
              }
            }
            paramResponse = new PermissionsPair(localArrayList, paramResponse);
          }
        }
      }
    }
  }
  
  static void initializeStaticContext(Context paramContext)
  {
    if ((paramContext != null) && (staticContext == null))
    {
      Context localContext = paramContext.getApplicationContext();
      if (localContext == null) {
        break label26;
      }
      paramContext = localContext;
    }
    label26:
    for (;;)
    {
      staticContext = paramContext;
      return;
    }
  }
  
  public static boolean isPublishPermission(String paramString)
  {
    if ((paramString != null) && ((paramString.startsWith("publish")) || (paramString.startsWith("manage")) || (OTHER_PUBLISH_PERMISSIONS.contains(paramString)))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private void logAuthorizationComplete(AuthorizationClient.Result.Code paramCode, Map<String, String> paramMap, Exception paramException)
  {
    if (this.pendingAuthorizationRequest == null)
    {
      paramCode = AuthorizationClient.newAuthorizationLoggingBundle("");
      paramCode.putString("2_result", AuthorizationClient.Result.Code.ERROR.getLoggingValue());
      paramCode.putString("5_error_message", "Unexpected call to logAuthorizationComplete with null pendingAuthorizationRequest.");
    }
    for (;;)
    {
      paramCode.putLong("1_timestamp_ms", System.currentTimeMillis());
      getAppEventsLogger().logSdkEvent("fb_mobile_login_complete", null, paramCode);
      return;
      Bundle localBundle = AuthorizationClient.newAuthorizationLoggingBundle(this.pendingAuthorizationRequest.getAuthId());
      if (paramCode != null) {
        localBundle.putString("2_result", paramCode.getLoggingValue());
      }
      if ((paramException != null) && (paramException.getMessage() != null)) {
        localBundle.putString("5_error_message", paramException.getMessage());
      }
      paramCode = null;
      if (!this.pendingAuthorizationRequest.loggingExtras.isEmpty()) {
        paramCode = new JSONObject(this.pendingAuthorizationRequest.loggingExtras);
      }
      Object localObject = paramCode;
      if (paramMap != null)
      {
        paramException = paramCode;
        if (paramCode == null) {
          paramException = new JSONObject();
        }
        try
        {
          paramCode = paramMap.entrySet().iterator();
          for (;;)
          {
            localObject = paramException;
            if (!paramCode.hasNext()) {
              break;
            }
            paramMap = (Map.Entry)paramCode.next();
            paramException.put((String)paramMap.getKey(), paramMap.getValue());
          }
          paramCode = localBundle;
        }
        catch (JSONException paramCode)
        {
          localObject = paramException;
        }
      }
      if (localObject != null)
      {
        localBundle.putString("6_extras", ((JSONObject)localObject).toString());
        paramCode = localBundle;
      }
    }
  }
  
  private void logAuthorizationStart()
  {
    Bundle localBundle = AuthorizationClient.newAuthorizationLoggingBundle(this.pendingAuthorizationRequest.getAuthId());
    localBundle.putLong("1_timestamp_ms", System.currentTimeMillis());
    try
    {
      JSONObject localJSONObject = new org/json/JSONObject;
      localJSONObject.<init>();
      localJSONObject.put("login_behavior", this.pendingAuthorizationRequest.loginBehavior.toString());
      localJSONObject.put("request_code", this.pendingAuthorizationRequest.requestCode);
      localJSONObject.put("is_legacy", this.pendingAuthorizationRequest.isLegacy);
      localJSONObject.put("permissions", TextUtils.join(",", this.pendingAuthorizationRequest.permissions));
      localJSONObject.put("default_audience", this.pendingAuthorizationRequest.defaultAudience.toString());
      localBundle.putString("6_extras", localJSONObject.toString());
      getAppEventsLogger().logSdkEvent("fb_mobile_login_start", null, localBundle);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
  
  private void open(OpenRequest paramOpenRequest, SessionAuthorizationType paramSessionAuthorizationType)
  {
    validatePermissions(paramOpenRequest, paramSessionAuthorizationType);
    validateLoginBehavior(paramOpenRequest);
    SessionState localSessionState;
    synchronized (this.lock)
    {
      if (this.pendingAuthorizationRequest != null)
      {
        localSessionState = this.state;
        paramSessionAuthorizationType = this.state;
        paramOpenRequest = new java/lang/UnsupportedOperationException;
        paramOpenRequest.<init>("Session: an attempt was made to open a session that has a pending request.");
        postStateChange(localSessionState, paramSessionAuthorizationType, paramOpenRequest);
        return;
      }
      localSessionState = this.state;
      switch (this.state)
      {
      case ???: 
      default: 
        paramOpenRequest = new java/lang/UnsupportedOperationException;
        paramOpenRequest.<init>("Session: an attempt was made to open an already opened session.");
        throw paramOpenRequest;
      }
    }
    paramSessionAuthorizationType = SessionState.OPENING;
    this.state = paramSessionAuthorizationType;
    if (paramOpenRequest == null)
    {
      paramOpenRequest = new java/lang/IllegalArgumentException;
      paramOpenRequest.<init>("openRequest cannot be null when opening a new Session");
      throw paramOpenRequest;
    }
    this.pendingAuthorizationRequest = paramOpenRequest;
    for (;;)
    {
      if (paramOpenRequest != null) {
        addCallback(paramOpenRequest.getCallback());
      }
      postStateChange(localSessionState, paramSessionAuthorizationType, null);
      if (paramSessionAuthorizationType != SessionState.OPENING) {
        break;
      }
      authorize(paramOpenRequest);
      break;
      if ((paramOpenRequest != null) && (!Utility.isNullOrEmpty(paramOpenRequest.getPermissions())) && (!Utility.isSubset(paramOpenRequest.getPermissions(), getPermissions()))) {
        this.pendingAuthorizationRequest = paramOpenRequest;
      }
      if (this.pendingAuthorizationRequest == null)
      {
        paramSessionAuthorizationType = SessionState.OPENED;
        this.state = paramSessionAuthorizationType;
      }
      else
      {
        paramSessionAuthorizationType = SessionState.OPENING;
        this.state = paramSessionAuthorizationType;
      }
    }
  }
  
  public static Session openActiveSession(Activity paramActivity, boolean paramBoolean, StatusCallback paramStatusCallback)
  {
    return openActiveSession(paramActivity, paramBoolean, new OpenRequest(paramActivity).setCallback(paramStatusCallback));
  }
  
  public static Session openActiveSession(Activity paramActivity, boolean paramBoolean, List<String> paramList, StatusCallback paramStatusCallback)
  {
    return openActiveSession(paramActivity, paramBoolean, new OpenRequest(paramActivity).setCallback(paramStatusCallback).setPermissions(paramList));
  }
  
  public static Session openActiveSession(Context paramContext, Fragment paramFragment, boolean paramBoolean, StatusCallback paramStatusCallback)
  {
    return openActiveSession(paramContext, paramBoolean, new OpenRequest(paramFragment).setCallback(paramStatusCallback));
  }
  
  public static Session openActiveSession(Context paramContext, Fragment paramFragment, boolean paramBoolean, List<String> paramList, StatusCallback paramStatusCallback)
  {
    return openActiveSession(paramContext, paramBoolean, new OpenRequest(paramFragment).setCallback(paramStatusCallback).setPermissions(paramList));
  }
  
  private static Session openActiveSession(Context paramContext, boolean paramBoolean, OpenRequest paramOpenRequest)
  {
    paramContext = new Builder(paramContext).build();
    if ((SessionState.CREATED_TOKEN_LOADED.equals(paramContext.getState())) || (paramBoolean))
    {
      setActiveSession(paramContext);
      paramContext.openForRead(paramOpenRequest);
    }
    for (;;)
    {
      return paramContext;
      paramContext = null;
    }
  }
  
  public static Session openActiveSessionFromCache(Context paramContext)
  {
    return openActiveSession(paramContext, false, null);
  }
  
  public static Session openActiveSessionWithAccessToken(Context paramContext, AccessToken paramAccessToken, StatusCallback paramStatusCallback)
  {
    paramContext = new Session(paramContext, null, null, false);
    setActiveSession(paramContext);
    paramContext.open(paramAccessToken, paramStatusCallback);
    return paramContext;
  }
  
  static void postActiveSessionAction(String paramString)
  {
    paramString = new Intent(paramString);
    LocalBroadcastManager.getInstance(getStaticContext()).sendBroadcast(paramString);
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws InvalidObjectException
  {
    throw new InvalidObjectException("Cannot readObject, serialization proxy required");
  }
  
  private void requestNewPermissions(NewPermissionsRequest paramNewPermissionsRequest, SessionAuthorizationType arg2)
  {
    validatePermissions(paramNewPermissionsRequest, ???);
    validateLoginBehavior(paramNewPermissionsRequest);
    if (paramNewPermissionsRequest != null)
    {
      synchronized (this.lock)
      {
        if (this.pendingAuthorizationRequest != null)
        {
          paramNewPermissionsRequest = new java/lang/UnsupportedOperationException;
          paramNewPermissionsRequest.<init>("Session: an attempt was made to request new permissions for a session that has a pending request.");
          throw paramNewPermissionsRequest;
        }
      }
      if (this.state.isOpened())
      {
        this.pendingAuthorizationRequest = paramNewPermissionsRequest;
        paramNewPermissionsRequest.setValidateSameFbidAsToken(getAccessToken());
        addCallback(paramNewPermissionsRequest.getCallback());
        authorize(paramNewPermissionsRequest);
      }
    }
    else
    {
      return;
    }
    if (this.state.isClosed())
    {
      paramNewPermissionsRequest = new java/lang/UnsupportedOperationException;
      paramNewPermissionsRequest.<init>("Session: an attempt was made to request new permissions for a session that has been closed.");
      throw paramNewPermissionsRequest;
    }
    paramNewPermissionsRequest = new java/lang/UnsupportedOperationException;
    paramNewPermissionsRequest.<init>("Session: an attempt was made to request new permissions for a session that is not currently open.");
  }
  
  private boolean resolveIntent(Intent paramIntent)
  {
    boolean bool = false;
    if (getStaticContext().getPackageManager().resolveActivity(paramIntent, 0) == null) {}
    for (;;)
    {
      return bool;
      bool = true;
    }
  }
  
  public static final Session restoreSession(Context paramContext, TokenCachingStrategy paramTokenCachingStrategy, StatusCallback paramStatusCallback, Bundle paramBundle)
  {
    if (paramBundle == null) {
      paramContext = null;
    }
    for (;;)
    {
      return paramContext;
      Object localObject = paramBundle.getByteArray("com.facebook.sdk.Session.saveSessionKey");
      ByteArrayInputStream localByteArrayInputStream;
      if (localObject != null) {
        localByteArrayInputStream = new ByteArrayInputStream((byte[])localObject);
      }
      try
      {
        localObject = new java/io/ObjectInputStream;
        ((ObjectInputStream)localObject).<init>(localByteArrayInputStream);
        localObject = (Session)((ObjectInputStream)localObject).readObject();
        initializeStaticContext(paramContext);
        if (paramTokenCachingStrategy != null)
        {
          ((Session)localObject).tokenCachingStrategy = paramTokenCachingStrategy;
          if (paramStatusCallback != null) {
            ((Session)localObject).addCallback(paramStatusCallback);
          }
          ((Session)localObject).authorizationBundle = paramBundle.getBundle("com.facebook.sdk.Session.authBundleKey");
          paramContext = (Context)localObject;
        }
      }
      catch (ClassNotFoundException paramContext)
      {
        for (;;)
        {
          Log.w(TAG, "Unable to restore session", paramContext);
          paramContext = null;
          break;
          paramTokenCachingStrategy = new com/facebook/SharedPreferencesTokenCachingStrategy;
          paramTokenCachingStrategy.<init>(paramContext);
          ((Session)localObject).tokenCachingStrategy = paramTokenCachingStrategy;
        }
      }
      catch (IOException paramContext)
      {
        for (;;)
        {
          Log.w(TAG, "Unable to restore session.", paramContext);
        }
      }
    }
  }
  
  private static void runWithHandlerOrExecutor(Handler paramHandler, Runnable paramRunnable)
  {
    if (paramHandler != null) {
      paramHandler.post(paramRunnable);
    }
    for (;;)
    {
      return;
      Settings.getExecutor().execute(paramRunnable);
    }
  }
  
  public static final void saveSession(Session paramSession, Bundle paramBundle)
  {
    ByteArrayOutputStream localByteArrayOutputStream;
    if ((paramBundle != null) && (paramSession != null) && (!paramBundle.containsKey("com.facebook.sdk.Session.saveSessionKey"))) {
      localByteArrayOutputStream = new ByteArrayOutputStream();
    }
    try
    {
      ObjectOutputStream localObjectOutputStream = new java/io/ObjectOutputStream;
      localObjectOutputStream.<init>(localByteArrayOutputStream);
      localObjectOutputStream.writeObject(paramSession);
      paramBundle.putByteArray("com.facebook.sdk.Session.saveSessionKey", localByteArrayOutputStream.toByteArray());
      paramBundle.putBundle("com.facebook.sdk.Session.authBundleKey", paramSession.authorizationBundle);
      return;
    }
    catch (IOException paramSession)
    {
      throw new FacebookException("Unable to save session.", paramSession);
    }
  }
  
  private void saveTokenToCache(AccessToken paramAccessToken)
  {
    if ((paramAccessToken != null) && (this.tokenCachingStrategy != null)) {
      this.tokenCachingStrategy.save(paramAccessToken.toCacheBundle());
    }
  }
  
  public static final void setActiveSession(Session paramSession)
  {
    synchronized (STATIC_LOCK)
    {
      if (paramSession != activeSession)
      {
        Session localSession = activeSession;
        if (localSession != null) {
          localSession.close();
        }
        activeSession = paramSession;
        if (localSession != null) {
          postActiveSessionAction("com.facebook.sdk.ACTIVE_SESSION_UNSET");
        }
        if (paramSession != null)
        {
          postActiveSessionAction("com.facebook.sdk.ACTIVE_SESSION_SET");
          if (paramSession.isOpened()) {
            postActiveSessionAction("com.facebook.sdk.ACTIVE_SESSION_OPENED");
          }
        }
      }
      return;
    }
  }
  
  private void tryLegacyAuth(AuthorizationRequest paramAuthorizationRequest)
  {
    this.authorizationClient = new AuthorizationClient();
    this.authorizationClient.setOnCompletedListener(new AuthorizationClient.OnCompletedListener()
    {
      public void onCompleted(AuthorizationClient.Result paramAnonymousResult)
      {
        if (paramAnonymousResult.code == AuthorizationClient.Result.Code.CANCEL) {}
        for (int i = 0;; i = -1)
        {
          Session.this.handleAuthorizationResult(i, paramAnonymousResult);
          return;
        }
      }
    });
    this.authorizationClient.setContext(getStaticContext());
    this.authorizationClient.startOrContinueAuth(paramAuthorizationRequest.getAuthorizationClientRequest());
  }
  
  private boolean tryLoginActivity(AuthorizationRequest paramAuthorizationRequest)
  {
    boolean bool = false;
    Intent localIntent = getLoginActivityIntent(paramAuthorizationRequest);
    if (!resolveIntent(localIntent)) {}
    for (;;)
    {
      return bool;
      try
      {
        paramAuthorizationRequest.getStartActivityDelegate().startActivityForResult(localIntent, paramAuthorizationRequest.getRequestCode());
        bool = true;
      }
      catch (ActivityNotFoundException paramAuthorizationRequest) {}
    }
  }
  
  private void validateLoginBehavior(AuthorizationRequest paramAuthorizationRequest)
  {
    if ((paramAuthorizationRequest != null) && (!paramAuthorizationRequest.isLegacy))
    {
      Intent localIntent = new Intent();
      localIntent.setClass(getStaticContext(), LoginActivity.class);
      if (!resolveIntent(localIntent)) {
        throw new FacebookException(String.format("Cannot use SessionLoginBehavior %s when %s is not declared as an activity in AndroidManifest.xml", new Object[] { paramAuthorizationRequest.getLoginBehavior(), LoginActivity.class.getName() }));
      }
    }
  }
  
  private void validatePermissions(AuthorizationRequest paramAuthorizationRequest, SessionAuthorizationType paramSessionAuthorizationType)
  {
    if ((paramAuthorizationRequest == null) || (Utility.isNullOrEmpty(paramAuthorizationRequest.getPermissions())))
    {
      if (SessionAuthorizationType.PUBLISH.equals(paramSessionAuthorizationType)) {
        throw new FacebookException("Cannot request publish or manage authorization with no permissions.");
      }
    }
    else
    {
      Iterator localIterator = paramAuthorizationRequest.getPermissions().iterator();
      while (localIterator.hasNext())
      {
        paramAuthorizationRequest = (String)localIterator.next();
        if (isPublishPermission(paramAuthorizationRequest))
        {
          if (SessionAuthorizationType.READ.equals(paramSessionAuthorizationType)) {
            throw new FacebookException(String.format("Cannot pass a publish or manage permission (%s) to a request for read authorization", new Object[] { paramAuthorizationRequest }));
          }
        }
        else if (SessionAuthorizationType.PUBLISH.equals(paramSessionAuthorizationType)) {
          Log.w(TAG, String.format("Should not pass a read permission (%s) to a request for publish or manage authorization", new Object[] { paramAuthorizationRequest }));
        }
      }
    }
  }
  
  private Object writeReplace()
  {
    return new SerializationProxyV1(this.applicationId, this.state, this.tokenInfo, this.lastAttemptedTokenExtendDate, false, this.pendingAuthorizationRequest);
  }
  
  public final void addCallback(StatusCallback paramStatusCallback)
  {
    List localList = this.callbacks;
    if (paramStatusCallback != null) {}
    try
    {
      if (!this.callbacks.contains(paramStatusCallback)) {
        this.callbacks.add(paramStatusCallback);
      }
      return;
    }
    finally {}
  }
  
  void authorize(AuthorizationRequest arg1)
  {
    ???.setApplicationId(this.applicationId);
    autoPublishAsync();
    logAuthorizationStart();
    boolean bool2 = tryLoginActivity(???);
    Object localObject3 = this.pendingAuthorizationRequest.loggingExtras;
    Object localObject1;
    if (bool2) {
      localObject1 = "1";
    }
    for (;;)
    {
      ((Map)localObject3).put("try_login_activity", localObject1);
      boolean bool1 = bool2;
      if (!bool2)
      {
        bool1 = bool2;
        if (???.isLegacy)
        {
          this.pendingAuthorizationRequest.loggingExtras.put("try_legacy", "1");
          tryLegacyAuth(???);
          bool1 = true;
        }
      }
      if (!bool1) {}
      synchronized (this.lock)
      {
        localObject3 = this.state;
        switch (this.state)
        {
        default: 
          this.state = SessionState.CLOSED_LOGIN_FAILED;
          localObject1 = new com/facebook/FacebookException;
          ((FacebookException)localObject1).<init>("Log in attempt failed: LoginActivity could not be started, and not legacy request");
          logAuthorizationComplete(AuthorizationClient.Result.Code.ERROR, null, (Exception)localObject1);
          postStateChange((SessionState)localObject3, this.state, (Exception)localObject1);
          return;
          localObject1 = "0";
        }
      }
    }
  }
  
  public final void close()
  {
    for (;;)
    {
      SessionState localSessionState1;
      synchronized (this.lock)
      {
        localSessionState1 = this.state;
        switch (this.state)
        {
        default: 
          return;
        case ???: 
        case ???: 
          this.state = SessionState.CLOSED_LOGIN_FAILED;
          SessionState localSessionState2 = this.state;
          FacebookException localFacebookException = new com/facebook/FacebookException;
          localFacebookException.<init>("Log in attempt aborted.");
          postStateChange(localSessionState1, localSessionState2, localFacebookException);
        }
      }
      this.state = SessionState.CLOSED;
      postStateChange(localSessionState1, this.state, null);
    }
  }
  
  public final void closeAndClearTokenInformation()
  {
    if (this.tokenCachingStrategy != null) {
      this.tokenCachingStrategy.clear();
    }
    Utility.clearFacebookCookies(staticContext);
    Utility.clearCaches(staticContext);
    close();
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (!(paramObject instanceof Session)) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      paramObject = (Session)paramObject;
      bool1 = bool2;
      if (areEqual(((Session)paramObject).applicationId, this.applicationId))
      {
        bool1 = bool2;
        if (areEqual(((Session)paramObject).authorizationBundle, this.authorizationBundle))
        {
          bool1 = bool2;
          if (areEqual(((Session)paramObject).state, this.state))
          {
            bool1 = bool2;
            if (areEqual(((Session)paramObject).getExpirationDate(), getExpirationDate())) {
              bool1 = true;
            }
          }
        }
      }
    }
  }
  
  void extendAccessToken()
  {
    TokenRefreshRequest localTokenRefreshRequest = null;
    synchronized (this.lock)
    {
      if (this.currentTokenRefreshRequest == null)
      {
        localTokenRefreshRequest = new com/facebook/Session$TokenRefreshRequest;
        localTokenRefreshRequest.<init>(this);
      }
    }
    throw ((Throwable)localObject1);
  }
  
  void extendAccessTokenIfNeeded()
  {
    if (shouldExtendAccessToken()) {
      extendAccessToken();
    }
  }
  
  void extendTokenCompleted(Bundle paramBundle)
  {
    synchronized (this.lock)
    {
      Object localObject2 = this.state;
      switch (this.state)
      {
      default: 
        paramBundle = TAG;
        localObject2 = new java/lang/StringBuilder;
        ((StringBuilder)localObject2).<init>();
        Log.d(paramBundle, "refreshToken ignored in state " + this.state);
        return;
      case ???: 
        this.state = SessionState.OPENED_TOKEN_UPDATED;
        postStateChange((SessionState)localObject2, this.state, null);
      }
      this.tokenInfo = AccessToken.createFromRefresh(this.tokenInfo, paramBundle);
      if (this.tokenCachingStrategy != null) {
        this.tokenCachingStrategy.save(this.tokenInfo.toCacheBundle());
      }
    }
  }
  
  void finishAuthOrReauth(AccessToken arg1, Exception paramException)
  {
    AccessToken localAccessToken = ???;
    Object localObject = paramException;
    if (??? != null)
    {
      localAccessToken = ???;
      localObject = paramException;
      if (???.isInvalid())
      {
        localAccessToken = null;
        localObject = new FacebookException("Invalid access token.");
      }
    }
    for (;;)
    {
      synchronized (this.lock)
      {
        switch (this.state)
        {
        default: 
          return;
        case ???: 
          finishAuthorization(localAccessToken, (Exception)localObject);
        }
      }
      finishReauthorization(localAccessToken, (Exception)localObject);
      continue;
      paramException = TAG;
      localObject = new java/lang/StringBuilder;
      ((StringBuilder)localObject).<init>();
      Log.d(paramException, "Unexpected call to finishAuthOrReauth in state " + this.state);
    }
  }
  
  public final String getAccessToken()
  {
    synchronized (this.lock)
    {
      if (this.tokenInfo == null)
      {
        str = null;
        return str;
      }
      String str = this.tokenInfo.getToken();
    }
  }
  
  public final String getApplicationId()
  {
    return this.applicationId;
  }
  
  public final Bundle getAuthorizationBundle()
  {
    synchronized (this.lock)
    {
      Bundle localBundle = this.authorizationBundle;
      return localBundle;
    }
  }
  
  public final List<String> getDeclinedPermissions()
  {
    synchronized (this.lock)
    {
      if (this.tokenInfo == null)
      {
        localObject1 = null;
        return (List<String>)localObject1;
      }
      Object localObject1 = this.tokenInfo.getDeclinedPermissions();
    }
  }
  
  public final Date getExpirationDate()
  {
    synchronized (this.lock)
    {
      if (this.tokenInfo == null)
      {
        localDate = null;
        return localDate;
      }
      Date localDate = this.tokenInfo.getExpires();
    }
  }
  
  Date getLastAttemptedTokenExtendDate()
  {
    return this.lastAttemptedTokenExtendDate;
  }
  
  public final List<String> getPermissions()
  {
    synchronized (this.lock)
    {
      if (this.tokenInfo == null)
      {
        localObject1 = null;
        return (List<String>)localObject1;
      }
      Object localObject1 = this.tokenInfo.getPermissions();
    }
  }
  
  public final SessionState getState()
  {
    synchronized (this.lock)
    {
      SessionState localSessionState = this.state;
      return localSessionState;
    }
  }
  
  AccessToken getTokenInfo()
  {
    return this.tokenInfo;
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public final boolean isClosed()
  {
    synchronized (this.lock)
    {
      boolean bool = this.state.isClosed();
      return bool;
    }
  }
  
  public final boolean isOpened()
  {
    synchronized (this.lock)
    {
      boolean bool = this.state.isOpened();
      return bool;
    }
  }
  
  public boolean isPermissionGranted(String paramString)
  {
    List localList = getPermissions();
    if (localList != null) {}
    for (boolean bool = localList.contains(paramString);; bool = false) {
      return bool;
    }
  }
  
  public final boolean onActivityResult(Activity arg1, int paramInt1, int paramInt2, Intent paramIntent)
  {
    boolean bool = true;
    Validate.notNull(???, "currentActivity");
    initializeStaticContext(???);
    for (;;)
    {
      AuthorizationClient.Result.Code localCode;
      synchronized (this.lock)
      {
        if ((this.pendingAuthorizationRequest == null) || (paramInt1 != this.pendingAuthorizationRequest.getRequestCode()))
        {
          bool = false;
          return bool;
        }
        ??? = null;
        localCode = AuthorizationClient.Result.Code.ERROR;
        if (paramIntent == null) {
          break label118;
        }
        AuthorizationClient.Result localResult = (AuthorizationClient.Result)paramIntent.getSerializableExtra("com.facebook.LoginActivity:Result");
        if (localResult != null) {
          handleAuthorizationResult(paramInt2, localResult);
        }
      }
      if (this.authorizationClient != null)
      {
        this.authorizationClient.onActivityResult(paramInt1, paramInt2, paramIntent);
        continue;
        label118:
        if (paramInt2 == 0)
        {
          ??? = new FacebookOperationCanceledException("User canceled operation.");
          localCode = AuthorizationClient.Result.Code.CANCEL;
        }
      }
      paramIntent = ???;
      if (??? == null) {
        paramIntent = new FacebookException("Unexpected call to Session.onActivityResult");
      }
      logAuthorizationComplete(localCode, null, paramIntent);
      finishAuthOrReauth(null, paramIntent);
    }
  }
  
  public final void open(AccessToken paramAccessToken, StatusCallback paramStatusCallback)
  {
    synchronized (this.lock)
    {
      if (this.pendingAuthorizationRequest != null)
      {
        paramAccessToken = new java/lang/UnsupportedOperationException;
        paramAccessToken.<init>("Session: an attempt was made to open a session that has a pending request.");
        throw paramAccessToken;
      }
    }
    if (this.state.isClosed())
    {
      paramAccessToken = new java/lang/UnsupportedOperationException;
      paramAccessToken.<init>("Session: an attempt was made to open a previously-closed session.");
      throw paramAccessToken;
    }
    if ((this.state != SessionState.CREATED) && (this.state != SessionState.CREATED_TOKEN_LOADED))
    {
      paramAccessToken = new java/lang/UnsupportedOperationException;
      paramAccessToken.<init>("Session: an attempt was made to open an already opened session.");
      throw paramAccessToken;
    }
    if (paramStatusCallback != null) {
      addCallback(paramStatusCallback);
    }
    this.tokenInfo = paramAccessToken;
    if (this.tokenCachingStrategy != null) {
      this.tokenCachingStrategy.save(paramAccessToken.toCacheBundle());
    }
    paramAccessToken = this.state;
    this.state = SessionState.OPENED;
    postStateChange(paramAccessToken, this.state, null);
    autoPublishAsync();
  }
  
  public final void openForPublish(OpenRequest paramOpenRequest)
  {
    open(paramOpenRequest, SessionAuthorizationType.PUBLISH);
  }
  
  public final void openForRead(OpenRequest paramOpenRequest)
  {
    open(paramOpenRequest, SessionAuthorizationType.READ);
  }
  
  void postStateChange(SessionState paramSessionState1, final SessionState paramSessionState2, final Exception paramException)
  {
    if ((paramSessionState1 == paramSessionState2) && (paramSessionState1 != SessionState.OPENED_TOKEN_UPDATED) && (paramException == null)) {}
    for (;;)
    {
      return;
      if (paramSessionState2.isClosed()) {
        this.tokenInfo = AccessToken.createEmptyToken();
      }
      paramException = new Runnable()
      {
        public void run()
        {
          synchronized (Session.this.callbacks)
          {
            Iterator localIterator = Session.this.callbacks.iterator();
            if (localIterator.hasNext())
            {
              Session.StatusCallback localStatusCallback = (Session.StatusCallback)localIterator.next();
              Runnable local1 = new com/facebook/Session$4$1;
              local1.<init>(this, localStatusCallback);
              Session.runWithHandlerOrExecutor(Session.this.handler, local1);
            }
          }
        }
      };
      runWithHandlerOrExecutor(this.handler, paramException);
      if ((this == activeSession) && (paramSessionState1.isOpened() != paramSessionState2.isOpened())) {
        if (paramSessionState2.isOpened()) {
          postActiveSessionAction("com.facebook.sdk.ACTIVE_SESSION_OPENED");
        } else {
          postActiveSessionAction("com.facebook.sdk.ACTIVE_SESSION_CLOSED");
        }
      }
    }
  }
  
  public final void refreshPermissions()
  {
    Request localRequest = new Request(this, "me/permissions");
    localRequest.setCallback(new Request.Callback()
    {
      public void onCompleted(Response arg1)
      {
        Session.PermissionsPair localPermissionsPair = Session.handlePermissionResponse(???);
        if (localPermissionsPair != null) {}
        synchronized (Session.this.lock)
        {
          Session.access$302(Session.this, AccessToken.createFromTokenWithRefreshedPermissions(Session.this.tokenInfo, localPermissionsPair.getGrantedPermissions(), localPermissionsPair.getDeclinedPermissions()));
          Session.this.postStateChange(Session.this.state, SessionState.OPENED_TOKEN_UPDATED, null);
          return;
        }
      }
    });
    localRequest.executeAsync();
  }
  
  public final void removeCallback(StatusCallback paramStatusCallback)
  {
    synchronized (this.callbacks)
    {
      this.callbacks.remove(paramStatusCallback);
      return;
    }
  }
  
  public final void requestNewPublishPermissions(NewPermissionsRequest paramNewPermissionsRequest)
  {
    requestNewPermissions(paramNewPermissionsRequest, SessionAuthorizationType.PUBLISH);
  }
  
  public final void requestNewReadPermissions(NewPermissionsRequest paramNewPermissionsRequest)
  {
    requestNewPermissions(paramNewPermissionsRequest, SessionAuthorizationType.READ);
  }
  
  void setCurrentTokenRefreshRequest(TokenRefreshRequest paramTokenRefreshRequest)
  {
    this.currentTokenRefreshRequest = paramTokenRefreshRequest;
  }
  
  void setLastAttemptedTokenExtendDate(Date paramDate)
  {
    this.lastAttemptedTokenExtendDate = paramDate;
  }
  
  void setTokenInfo(AccessToken paramAccessToken)
  {
    this.tokenInfo = paramAccessToken;
  }
  
  boolean shouldExtendAccessToken()
  {
    boolean bool1;
    if (this.currentTokenRefreshRequest != null) {
      bool1 = false;
    }
    for (;;)
    {
      return bool1;
      boolean bool2 = false;
      Date localDate = new Date();
      bool1 = bool2;
      if (this.state.isOpened())
      {
        bool1 = bool2;
        if (this.tokenInfo.getSource().canExtendToken())
        {
          bool1 = bool2;
          if (localDate.getTime() - this.lastAttemptedTokenExtendDate.getTime() > 3600000L)
          {
            bool1 = bool2;
            if (localDate.getTime() - this.tokenInfo.getLastRefresh().getTime() > 86400000L) {
              bool1 = true;
            }
          }
        }
      }
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("{Session").append(" state:").append(this.state).append(", token:");
    if (this.tokenInfo == null)
    {
      localObject = "null";
      localStringBuilder = localStringBuilder.append(localObject).append(", appId:");
      if (this.applicationId != null) {
        break label90;
      }
    }
    label90:
    for (Object localObject = "null";; localObject = this.applicationId)
    {
      return (String)localObject + "}";
      localObject = this.tokenInfo;
      break;
    }
  }
  
  public static class AuthorizationRequest
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    private String applicationId;
    private final String authId = UUID.randomUUID().toString();
    private SessionDefaultAudience defaultAudience = SessionDefaultAudience.FRIENDS;
    private boolean isLegacy = false;
    private final Map<String, String> loggingExtras = new HashMap();
    private SessionLoginBehavior loginBehavior = SessionLoginBehavior.SSO_WITH_FALLBACK;
    private List<String> permissions = Collections.emptyList();
    private int requestCode = 64206;
    private final Session.StartActivityDelegate startActivityDelegate;
    private Session.StatusCallback statusCallback;
    private String validateSameFbidAsToken;
    
    AuthorizationRequest(final Activity paramActivity)
    {
      this.startActivityDelegate = new Session.StartActivityDelegate()
      {
        public Activity getActivityContext()
        {
          return paramActivity;
        }
        
        public void startActivityForResult(Intent paramAnonymousIntent, int paramAnonymousInt)
        {
          paramActivity.startActivityForResult(paramAnonymousIntent, paramAnonymousInt);
        }
      };
    }
    
    AuthorizationRequest(final Fragment paramFragment)
    {
      this.startActivityDelegate = new Session.StartActivityDelegate()
      {
        public Activity getActivityContext()
        {
          return paramFragment.getActivity();
        }
        
        public void startActivityForResult(Intent paramAnonymousIntent, int paramAnonymousInt)
        {
          paramFragment.startActivityForResult(paramAnonymousIntent, paramAnonymousInt);
        }
      };
    }
    
    private AuthorizationRequest(SessionLoginBehavior paramSessionLoginBehavior, int paramInt, List<String> paramList, String paramString1, boolean paramBoolean, String paramString2, String paramString3)
    {
      this.startActivityDelegate = new Session.StartActivityDelegate()
      {
        public Activity getActivityContext()
        {
          throw new UnsupportedOperationException("Cannot create an AuthorizationRequest without a valid Activity or Fragment");
        }
        
        public void startActivityForResult(Intent paramAnonymousIntent, int paramAnonymousInt)
        {
          throw new UnsupportedOperationException("Cannot create an AuthorizationRequest without a valid Activity or Fragment");
        }
      };
      this.loginBehavior = paramSessionLoginBehavior;
      this.requestCode = paramInt;
      this.permissions = paramList;
      this.defaultAudience = SessionDefaultAudience.valueOf(paramString1);
      this.isLegacy = paramBoolean;
      this.applicationId = paramString2;
      this.validateSameFbidAsToken = paramString3;
    }
    
    private void readObject(ObjectInputStream paramObjectInputStream)
      throws InvalidObjectException
    {
      throw new InvalidObjectException("Cannot readObject, serialization proxy required");
    }
    
    String getApplicationId()
    {
      return this.applicationId;
    }
    
    String getAuthId()
    {
      return this.authId;
    }
    
    AuthorizationClient.AuthorizationRequest getAuthorizationClientRequest()
    {
      AuthorizationClient.StartActivityDelegate local4 = new AuthorizationClient.StartActivityDelegate()
      {
        public Activity getActivityContext()
        {
          return Session.AuthorizationRequest.this.startActivityDelegate.getActivityContext();
        }
        
        public void startActivityForResult(Intent paramAnonymousIntent, int paramAnonymousInt)
        {
          Session.AuthorizationRequest.this.startActivityDelegate.startActivityForResult(paramAnonymousIntent, paramAnonymousInt);
        }
      };
      return new AuthorizationClient.AuthorizationRequest(this.loginBehavior, this.requestCode, this.isLegacy, this.permissions, this.defaultAudience, this.applicationId, this.validateSameFbidAsToken, local4, this.authId);
    }
    
    Session.StatusCallback getCallback()
    {
      return this.statusCallback;
    }
    
    SessionDefaultAudience getDefaultAudience()
    {
      return this.defaultAudience;
    }
    
    SessionLoginBehavior getLoginBehavior()
    {
      return this.loginBehavior;
    }
    
    List<String> getPermissions()
    {
      return this.permissions;
    }
    
    int getRequestCode()
    {
      return this.requestCode;
    }
    
    Session.StartActivityDelegate getStartActivityDelegate()
    {
      return this.startActivityDelegate;
    }
    
    String getValidateSameFbidAsToken()
    {
      return this.validateSameFbidAsToken;
    }
    
    boolean isLegacy()
    {
      return this.isLegacy;
    }
    
    void setApplicationId(String paramString)
    {
      this.applicationId = paramString;
    }
    
    AuthorizationRequest setCallback(Session.StatusCallback paramStatusCallback)
    {
      this.statusCallback = paramStatusCallback;
      return this;
    }
    
    AuthorizationRequest setDefaultAudience(SessionDefaultAudience paramSessionDefaultAudience)
    {
      if (paramSessionDefaultAudience != null) {
        this.defaultAudience = paramSessionDefaultAudience;
      }
      return this;
    }
    
    public void setIsLegacy(boolean paramBoolean)
    {
      this.isLegacy = paramBoolean;
    }
    
    AuthorizationRequest setLoginBehavior(SessionLoginBehavior paramSessionLoginBehavior)
    {
      if (paramSessionLoginBehavior != null) {
        this.loginBehavior = paramSessionLoginBehavior;
      }
      return this;
    }
    
    AuthorizationRequest setPermissions(List<String> paramList)
    {
      if (paramList != null) {
        this.permissions = paramList;
      }
      return this;
    }
    
    AuthorizationRequest setPermissions(String... paramVarArgs)
    {
      return setPermissions(Arrays.asList(paramVarArgs));
    }
    
    AuthorizationRequest setRequestCode(int paramInt)
    {
      if (paramInt >= 0) {
        this.requestCode = paramInt;
      }
      return this;
    }
    
    void setValidateSameFbidAsToken(String paramString)
    {
      this.validateSameFbidAsToken = paramString;
    }
    
    Object writeReplace()
    {
      return new AuthRequestSerializationProxyV1(this.loginBehavior, this.requestCode, this.permissions, this.defaultAudience.name(), this.isLegacy, this.applicationId, this.validateSameFbidAsToken, null);
    }
    
    private static class AuthRequestSerializationProxyV1
      implements Serializable
    {
      private static final long serialVersionUID = -8748347685113614927L;
      private final String applicationId;
      private final String defaultAudience;
      private boolean isLegacy;
      private final SessionLoginBehavior loginBehavior;
      private final List<String> permissions;
      private final int requestCode;
      private final String validateSameFbidAsToken;
      
      private AuthRequestSerializationProxyV1(SessionLoginBehavior paramSessionLoginBehavior, int paramInt, List<String> paramList, String paramString1, boolean paramBoolean, String paramString2, String paramString3)
      {
        this.loginBehavior = paramSessionLoginBehavior;
        this.requestCode = paramInt;
        this.permissions = paramList;
        this.defaultAudience = paramString1;
        this.isLegacy = paramBoolean;
        this.applicationId = paramString2;
        this.validateSameFbidAsToken = paramString3;
      }
      
      private Object readResolve()
      {
        return new Session.AuthorizationRequest(this.loginBehavior, this.requestCode, this.permissions, this.defaultAudience, this.isLegacy, this.applicationId, this.validateSameFbidAsToken, null);
      }
    }
  }
  
  private class AutoPublishAsyncTask
    extends AsyncTask<Void, Void, Void>
  {
    private final Context mApplicationContext;
    private final String mApplicationId;
    
    public AutoPublishAsyncTask(String paramString, Context paramContext)
    {
      this.mApplicationId = paramString;
      this.mApplicationContext = paramContext.getApplicationContext();
    }
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      try
      {
        Settings.publishInstallAndWaitForResponse(this.mApplicationContext, this.mApplicationId, true);
        return null;
      }
      catch (Exception paramVarArgs)
      {
        for (;;)
        {
          Utility.logd("Facebook-publish", paramVarArgs);
        }
      }
    }
    
    protected void onPostExecute(Void arg1)
    {
      synchronized (Session.this)
      {
        Session.access$1802(Session.this, null);
        return;
      }
    }
  }
  
  public static final class Builder
  {
    private String applicationId;
    private final Context context;
    private TokenCachingStrategy tokenCachingStrategy;
    
    public Builder(Context paramContext)
    {
      this.context = paramContext;
    }
    
    public Session build()
    {
      return new Session(this.context, this.applicationId, this.tokenCachingStrategy);
    }
    
    public Builder setApplicationId(String paramString)
    {
      this.applicationId = paramString;
      return this;
    }
    
    public Builder setTokenCachingStrategy(TokenCachingStrategy paramTokenCachingStrategy)
    {
      this.tokenCachingStrategy = paramTokenCachingStrategy;
      return this;
    }
  }
  
  public static final class NewPermissionsRequest
    extends Session.AuthorizationRequest
  {
    private static final long serialVersionUID = 1L;
    
    public NewPermissionsRequest(Activity paramActivity, List<String> paramList)
    {
      super();
      setPermissions(paramList);
    }
    
    public NewPermissionsRequest(Activity paramActivity, String... paramVarArgs)
    {
      super();
      setPermissions(paramVarArgs);
    }
    
    public NewPermissionsRequest(Fragment paramFragment, List<String> paramList)
    {
      super();
      setPermissions(paramList);
    }
    
    public NewPermissionsRequest(Fragment paramFragment, String... paramVarArgs)
    {
      super();
      setPermissions(paramVarArgs);
    }
    
    AuthorizationClient.AuthorizationRequest getAuthorizationClientRequest()
    {
      AuthorizationClient.AuthorizationRequest localAuthorizationRequest = super.getAuthorizationClientRequest();
      localAuthorizationRequest.setRerequest(true);
      return localAuthorizationRequest;
    }
    
    public final NewPermissionsRequest setCallback(Session.StatusCallback paramStatusCallback)
    {
      super.setCallback(paramStatusCallback);
      return this;
    }
    
    public final NewPermissionsRequest setDefaultAudience(SessionDefaultAudience paramSessionDefaultAudience)
    {
      super.setDefaultAudience(paramSessionDefaultAudience);
      return this;
    }
    
    public final NewPermissionsRequest setLoginBehavior(SessionLoginBehavior paramSessionLoginBehavior)
    {
      super.setLoginBehavior(paramSessionLoginBehavior);
      return this;
    }
    
    public final NewPermissionsRequest setRequestCode(int paramInt)
    {
      super.setRequestCode(paramInt);
      return this;
    }
  }
  
  public static final class OpenRequest
    extends Session.AuthorizationRequest
  {
    private static final long serialVersionUID = 1L;
    
    public OpenRequest(Activity paramActivity)
    {
      super();
    }
    
    public OpenRequest(Fragment paramFragment)
    {
      super();
    }
    
    public final OpenRequest setCallback(Session.StatusCallback paramStatusCallback)
    {
      super.setCallback(paramStatusCallback);
      return this;
    }
    
    public final OpenRequest setDefaultAudience(SessionDefaultAudience paramSessionDefaultAudience)
    {
      super.setDefaultAudience(paramSessionDefaultAudience);
      return this;
    }
    
    public final OpenRequest setLoginBehavior(SessionLoginBehavior paramSessionLoginBehavior)
    {
      super.setLoginBehavior(paramSessionLoginBehavior);
      return this;
    }
    
    public final OpenRequest setPermissions(List<String> paramList)
    {
      super.setPermissions(paramList);
      return this;
    }
    
    public final OpenRequest setPermissions(String... paramVarArgs)
    {
      super.setPermissions(paramVarArgs);
      return this;
    }
    
    public final OpenRequest setRequestCode(int paramInt)
    {
      super.setRequestCode(paramInt);
      return this;
    }
  }
  
  static class PermissionsPair
  {
    List<String> declinedPermissions;
    List<String> grantedPermissions;
    
    public PermissionsPair(List<String> paramList1, List<String> paramList2)
    {
      this.grantedPermissions = paramList1;
      this.declinedPermissions = paramList2;
    }
    
    public List<String> getDeclinedPermissions()
    {
      return this.declinedPermissions;
    }
    
    public List<String> getGrantedPermissions()
    {
      return this.grantedPermissions;
    }
  }
  
  private static class SerializationProxyV1
    implements Serializable
  {
    private static final long serialVersionUID = 7663436173185080063L;
    private final String applicationId;
    private final Date lastAttemptedTokenExtendDate;
    private final Session.AuthorizationRequest pendingAuthorizationRequest;
    private final boolean shouldAutoPublish;
    private final SessionState state;
    private final AccessToken tokenInfo;
    
    SerializationProxyV1(String paramString, SessionState paramSessionState, AccessToken paramAccessToken, Date paramDate, boolean paramBoolean, Session.AuthorizationRequest paramAuthorizationRequest)
    {
      this.applicationId = paramString;
      this.state = paramSessionState;
      this.tokenInfo = paramAccessToken;
      this.lastAttemptedTokenExtendDate = paramDate;
      this.shouldAutoPublish = paramBoolean;
      this.pendingAuthorizationRequest = paramAuthorizationRequest;
    }
    
    private Object readResolve()
    {
      return new Session(this.applicationId, this.state, this.tokenInfo, this.lastAttemptedTokenExtendDate, this.shouldAutoPublish, this.pendingAuthorizationRequest, null);
    }
  }
  
  private static class SerializationProxyV2
    implements Serializable
  {
    private static final long serialVersionUID = 7663436173185080064L;
    private final String applicationId;
    private final Date lastAttemptedTokenExtendDate;
    private final Session.AuthorizationRequest pendingAuthorizationRequest;
    private final Set<String> requestedPermissions;
    private final boolean shouldAutoPublish;
    private final SessionState state;
    private final AccessToken tokenInfo;
    
    SerializationProxyV2(String paramString, SessionState paramSessionState, AccessToken paramAccessToken, Date paramDate, boolean paramBoolean, Session.AuthorizationRequest paramAuthorizationRequest, Set<String> paramSet)
    {
      this.applicationId = paramString;
      this.state = paramSessionState;
      this.tokenInfo = paramAccessToken;
      this.lastAttemptedTokenExtendDate = paramDate;
      this.shouldAutoPublish = paramBoolean;
      this.pendingAuthorizationRequest = paramAuthorizationRequest;
      this.requestedPermissions = paramSet;
    }
    
    private Object readResolve()
    {
      return new Session(this.applicationId, this.state, this.tokenInfo, this.lastAttemptedTokenExtendDate, this.shouldAutoPublish, this.pendingAuthorizationRequest, this.requestedPermissions, null);
    }
  }
  
  static abstract interface StartActivityDelegate
  {
    public abstract Activity getActivityContext();
    
    public abstract void startActivityForResult(Intent paramIntent, int paramInt);
  }
  
  public static abstract interface StatusCallback
  {
    public abstract void call(Session paramSession, SessionState paramSessionState, Exception paramException);
  }
  
  class TokenRefreshRequest
    implements ServiceConnection
  {
    final Messenger messageReceiver = new Messenger(new Session.TokenRefreshRequestHandler(Session.this, this));
    Messenger messageSender = null;
    
    TokenRefreshRequest() {}
    
    private void cleanup()
    {
      if (Session.this.currentTokenRefreshRequest == this) {
        Session.access$1602(Session.this, null);
      }
    }
    
    private void refreshToken()
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("access_token", Session.this.getTokenInfo().getToken());
      Message localMessage = Message.obtain();
      localMessage.setData(localBundle);
      localMessage.replyTo = this.messageReceiver;
      try
      {
        this.messageSender.send(localMessage);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          cleanup();
        }
      }
    }
    
    public void bind()
    {
      Intent localIntent = NativeProtocol.createTokenRefreshIntent(Session.getStaticContext());
      if ((localIntent != null) && (Session.staticContext.bindService(localIntent, this, 1))) {
        Session.this.setLastAttemptedTokenExtendDate(new Date());
      }
      for (;;)
      {
        return;
        cleanup();
      }
    }
    
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      this.messageSender = new Messenger(paramIBinder);
      refreshToken();
    }
    
    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      cleanup();
      try
      {
        Session.staticContext.unbindService(this);
        return;
      }
      catch (IllegalArgumentException paramComponentName)
      {
        for (;;) {}
      }
    }
  }
  
  static class TokenRefreshRequestHandler
    extends Handler
  {
    private WeakReference<Session.TokenRefreshRequest> refreshRequestWeakReference;
    private WeakReference<Session> sessionWeakReference;
    
    TokenRefreshRequestHandler(Session paramSession, Session.TokenRefreshRequest paramTokenRefreshRequest)
    {
      super();
      this.sessionWeakReference = new WeakReference(paramSession);
      this.refreshRequestWeakReference = new WeakReference(paramTokenRefreshRequest);
    }
    
    public void handleMessage(Message paramMessage)
    {
      String str = paramMessage.getData().getString("access_token");
      Session localSession = (Session)this.sessionWeakReference.get();
      if ((localSession != null) && (str != null)) {
        localSession.extendTokenCompleted(paramMessage.getData());
      }
      paramMessage = (Session.TokenRefreshRequest)this.refreshRequestWeakReference.get();
      if (paramMessage != null)
      {
        Session.staticContext.unbindService(paramMessage);
        paramMessage.cleanup();
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\Session.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */