package com.google.example.games.basegameutils;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.google.android.gms.appstate.AppStateManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.GamesOptions;
import com.google.android.gms.games.Games.GamesOptions.Builder;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.plus.Account;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.Plus.PlusOptions;
import com.google.android.gms.plus.Plus.PlusOptions.Builder;

public class GameHelper
  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
  public static final int CLIENT_ALL = 7;
  public static final int CLIENT_APPSTATE = 4;
  public static final int CLIENT_GAMES = 1;
  public static final int CLIENT_NONE = 0;
  public static final int CLIENT_PLUS = 2;
  static final int DEFAULT_MAX_SIGN_IN_ATTEMPTS = 3;
  static final int RC_RESOLVE = 9001;
  static final int RC_UNUSED = 9002;
  static final String TAG = "GameHelper";
  private final String GAMEHELPER_SHARED_PREFS = "GAMEHELPER_SHARED_PREFS";
  private final String KEY_SIGN_IN_CANCELLATIONS = "KEY_SIGN_IN_CANCELLATIONS";
  Activity mActivity = null;
  Context mAppContext = null;
  boolean mConnectOnStart = true;
  private boolean mConnecting = false;
  ConnectionResult mConnectionResult = null;
  boolean mDebugLog = false;
  boolean mExpectingResolution = false;
  Api.ApiOptions mGamesApiOptions = null;
  GoogleApiClient mGoogleApiClient = null;
  GoogleApiClient.Builder mGoogleApiClientBuilder = null;
  Handler mHandler;
  Invitation mInvitation;
  GameHelperListener mListener = null;
  int mMaxAutoSignInAttempts = 3;
  Api.ApiOptions mPlusApiOptions = null;
  int mRequestedClients = 0;
  private boolean mSetupDone = false;
  boolean mShowErrorDialogs = true;
  boolean mSignInCancelled = false;
  SignInFailureReason mSignInFailureReason = null;
  TurnBasedMatch mTurnBasedMatch;
  boolean mUserInitiatedSignIn = false;
  
  public GameHelper(Activity paramActivity, int paramInt)
  {
    this.mActivity = paramActivity;
    this.mAppContext = paramActivity.getApplicationContext();
    this.mRequestedClients = paramInt;
    this.mHandler = new Handler();
  }
  
  private void doApiOptionsPreCheck()
  {
    if (this.mGoogleApiClientBuilder != null)
    {
      logError("GameHelper: you cannot call set*ApiOptions after the client builder has been created. Call it before calling createApiClientBuilder() or setup().");
      throw new IllegalStateException("GameHelper: you cannot call set*ApiOptions after the client builder has been created. Call it before calling createApiClientBuilder() or setup().");
    }
  }
  
  static Dialog makeSimpleDialog(Activity paramActivity, String paramString)
  {
    return new AlertDialog.Builder(paramActivity, 5).setMessage(paramString).setNeutralButton(17039370, null).create();
  }
  
  static Dialog makeSimpleDialog(Activity paramActivity, String paramString1, String paramString2)
  {
    return new AlertDialog.Builder(paramActivity, 5).setMessage(paramString2).setTitle(paramString1).setNeutralButton(17039370, null).create();
  }
  
  public static void showFailureDialog(Activity paramActivity, int paramInt1, int paramInt2)
  {
    if (paramActivity == null)
    {
      Log.e("GameHelper", "*** No Activity. Can't show failure dialog!");
      return;
    }
    Dialog localDialog1;
    switch (paramInt1)
    {
    default: 
      Dialog localDialog2 = GooglePlayServicesUtil.getErrorDialog(paramInt2, paramActivity, 9002, null);
      localDialog1 = localDialog2;
      if (localDialog2 == null)
      {
        Log.e("GameHelper", "No standard error dialog available. Making fallback dialog.");
        localDialog1 = makeSimpleDialog(paramActivity, GameHelperUtils.getString(paramActivity, 0) + " " + GameHelperUtils.errorCodeToString(paramInt2));
      }
      break;
    }
    for (;;)
    {
      localDialog1.show();
      break;
      localDialog1 = makeSimpleDialog(paramActivity, GameHelperUtils.getString(paramActivity, 2));
      continue;
      localDialog1 = makeSimpleDialog(paramActivity, GameHelperUtils.getString(paramActivity, 1));
      continue;
      localDialog1 = makeSimpleDialog(paramActivity, GameHelperUtils.getString(paramActivity, 3));
    }
  }
  
  void assertConfigured(String paramString)
  {
    if (!this.mSetupDone)
    {
      paramString = "GameHelper error: Operation attempted without setup: " + paramString + ". The setup() method must be called before attempting any other operation.";
      logError(paramString);
      throw new IllegalStateException(paramString);
    }
  }
  
  public void beginUserInitiatedSignIn()
  {
    debugLog("beginUserInitiatedSignIn: resetting attempt count.");
    resetSignInCancellations();
    this.mSignInCancelled = false;
    this.mConnectOnStart = true;
    if (this.mGoogleApiClient.isConnected())
    {
      logWarn("beginUserInitiatedSignIn() called when already connected. Calling listener directly to notify of success.");
      notifyListener(true);
    }
    for (;;)
    {
      return;
      if (this.mConnecting)
      {
        logWarn("beginUserInitiatedSignIn() called when already connecting. Be patient! You can only call this method after you get an onSignInSucceeded() or onSignInFailed() callback. Suggestion: disable the sign-in button on startup and also when it's clicked, and re-enable when you get the callback.");
      }
      else
      {
        debugLog("Starting USER-INITIATED sign-in flow.");
        this.mUserInitiatedSignIn = true;
        if (this.mConnectionResult != null)
        {
          debugLog("beginUserInitiatedSignIn: continuing pending sign-in flow.");
          this.mConnecting = true;
          resolveConnectionResult();
        }
        else
        {
          debugLog("beginUserInitiatedSignIn: starting new sign-in flow.");
          connect();
        }
      }
    }
  }
  
  public void clearInvitation()
  {
    this.mInvitation = null;
  }
  
  public void clearTurnBasedMatch()
  {
    this.mTurnBasedMatch = null;
  }
  
  void connect()
  {
    if (this.mGoogleApiClient.isConnected()) {
      debugLog("Already connected.");
    }
    for (;;)
    {
      return;
      debugLog("Starting connection.");
      this.mConnecting = true;
      this.mInvitation = null;
      this.mTurnBasedMatch = null;
      this.mGoogleApiClient.connect();
    }
  }
  
  public GoogleApiClient.Builder createApiClientBuilder()
  {
    if (this.mSetupDone)
    {
      logError("GameHelper: you called GameHelper.createApiClientBuilder() after calling setup. You can only get a client builder BEFORE performing setup.");
      throw new IllegalStateException("GameHelper: you called GameHelper.createApiClientBuilder() after calling setup. You can only get a client builder BEFORE performing setup.");
    }
    GoogleApiClient.Builder localBuilder = new GoogleApiClient.Builder(this.mActivity, this, this);
    Object localObject2;
    Object localObject1;
    if ((this.mRequestedClients & 0x1) != 0)
    {
      localObject2 = (Games.GamesOptions)this.mGamesApiOptions;
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = Games.GamesOptions.builder().build();
      }
      localBuilder.addApi(Games.API, (Api.ApiOptions.HasOptions)localObject1);
      localBuilder.addScope(Games.SCOPE_GAMES);
    }
    if ((this.mRequestedClients & 0x2) != 0)
    {
      localObject2 = (Plus.PlusOptions)this.mPlusApiOptions;
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = new Plus.PlusOptions.Builder().build();
      }
      localBuilder.addApi(Plus.API, (Api.ApiOptions.HasOptions)localObject1);
      localBuilder.addScope(Plus.SCOPE_PLUS_LOGIN);
    }
    if ((this.mRequestedClients & 0x4) != 0)
    {
      localBuilder.addApi(AppStateManager.API);
      localBuilder.addScope(AppStateManager.SCOPE_APP_STATE);
    }
    this.mGoogleApiClientBuilder = localBuilder;
    return localBuilder;
  }
  
  void debugLog(String paramString)
  {
    if (this.mDebugLog) {
      Log.d("GameHelper", "GameHelper: " + paramString);
    }
  }
  
  public void disconnect()
  {
    if (this.mGoogleApiClient.isConnected())
    {
      debugLog("Disconnecting client.");
      this.mGoogleApiClient.disconnect();
    }
    for (;;)
    {
      return;
      Log.w("GameHelper", "disconnect() called when client was already disconnected.");
    }
  }
  
  public void enableDebugLog(boolean paramBoolean)
  {
    this.mDebugLog = paramBoolean;
    if (paramBoolean) {
      debugLog("Debug log enabled.");
    }
  }
  
  @Deprecated
  public void enableDebugLog(boolean paramBoolean, String paramString)
  {
    Log.w("GameHelper", "GameHelper.enableDebugLog(boolean,String) is deprecated. Use GameHelper.enableDebugLog(boolean)");
    enableDebugLog(paramBoolean);
  }
  
  public GoogleApiClient getApiClient()
  {
    if (this.mGoogleApiClient == null) {
      throw new IllegalStateException("No GoogleApiClient. Did you call setup()?");
    }
    return this.mGoogleApiClient;
  }
  
  public Invitation getInvitation()
  {
    if (!this.mGoogleApiClient.isConnected()) {
      Log.w("GameHelper", "Warning: getInvitation() should only be called when signed in, that is, after getting onSignInSuceeded()");
    }
    return this.mInvitation;
  }
  
  public String getInvitationId()
  {
    if (!this.mGoogleApiClient.isConnected()) {
      Log.w("GameHelper", "Warning: getInvitationId() should only be called when signed in, that is, after getting onSignInSuceeded()");
    }
    if (this.mInvitation == null) {}
    for (String str = null;; str = this.mInvitation.getInvitationId()) {
      return str;
    }
  }
  
  int getSignInCancellations()
  {
    return this.mAppContext.getSharedPreferences("GAMEHELPER_SHARED_PREFS", 0).getInt("KEY_SIGN_IN_CANCELLATIONS", 0);
  }
  
  public SignInFailureReason getSignInError()
  {
    return this.mSignInFailureReason;
  }
  
  public TurnBasedMatch getTurnBasedMatch()
  {
    if (!this.mGoogleApiClient.isConnected()) {
      Log.w("GameHelper", "Warning: getTurnBasedMatch() should only be called when signed in, that is, after getting onSignInSuceeded()");
    }
    return this.mTurnBasedMatch;
  }
  
  void giveUp(SignInFailureReason paramSignInFailureReason)
  {
    this.mConnectOnStart = false;
    disconnect();
    this.mSignInFailureReason = paramSignInFailureReason;
    if (paramSignInFailureReason.mActivityResultCode == 10004) {
      GameHelperUtils.printMisconfiguredDebugInfo(this.mAppContext);
    }
    showFailureDialog();
    this.mConnecting = false;
    notifyListener(false);
  }
  
  public boolean hasInvitation()
  {
    if (this.mInvitation != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasSignInError()
  {
    if (this.mSignInFailureReason != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasTurnBasedMatch()
  {
    if (this.mTurnBasedMatch != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  int incrementSignInCancellations()
  {
    int i = getSignInCancellations();
    SharedPreferences.Editor localEditor = this.mAppContext.getSharedPreferences("GAMEHELPER_SHARED_PREFS", 0).edit();
    localEditor.putInt("KEY_SIGN_IN_CANCELLATIONS", i + 1);
    localEditor.commit();
    return i + 1;
  }
  
  public boolean isConnecting()
  {
    return this.mConnecting;
  }
  
  public boolean isSignedIn()
  {
    if ((this.mGoogleApiClient != null) && (this.mGoogleApiClient.isConnected())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  void logError(String paramString)
  {
    Log.e("GameHelper", "*** GameHelper ERROR: " + paramString);
  }
  
  void logWarn(String paramString)
  {
    Log.w("GameHelper", "!!! GameHelper WARNING: " + paramString);
  }
  
  public Dialog makeSimpleDialog(String paramString)
  {
    if (this.mActivity == null) {
      logError("*** makeSimpleDialog failed: no current Activity!");
    }
    for (paramString = null;; paramString = makeSimpleDialog(this.mActivity, paramString)) {
      return paramString;
    }
  }
  
  public Dialog makeSimpleDialog(String paramString1, String paramString2)
  {
    if (this.mActivity == null) {
      logError("*** makeSimpleDialog failed: no current Activity!");
    }
    for (paramString1 = null;; paramString1 = makeSimpleDialog(this.mActivity, paramString1, paramString2)) {
      return paramString1;
    }
  }
  
  void notifyListener(boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("Notifying LISTENER of sign-in ");
    String str;
    if (paramBoolean)
    {
      str = "SUCCESS";
      debugLog(str);
      if (this.mListener != null)
      {
        if (!paramBoolean) {
          break label76;
        }
        this.mListener.onSignInSucceeded();
      }
    }
    for (;;)
    {
      return;
      if (this.mSignInFailureReason != null)
      {
        str = "FAILURE (error)";
        break;
      }
      str = "FAILURE (no error)";
      break;
      label76:
      this.mListener.onSignInFailed();
    }
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("onActivityResult: req=");
    if (paramInt1 == 9001)
    {
      paramIntent = "RC_RESOLVE";
      debugLog(paramIntent + ", resp=" + GameHelperUtils.activityResponseCodeToString(paramInt2));
      if (paramInt1 == 9001) {
        break label75;
      }
      debugLog("onActivityResult: request code not meant for us. Ignoring.");
    }
    for (;;)
    {
      return;
      paramIntent = String.valueOf(paramInt1);
      break;
      label75:
      this.mExpectingResolution = false;
      if (!this.mConnecting)
      {
        debugLog("onActivityResult: ignoring because we are not connecting.");
      }
      else if (paramInt2 == -1)
      {
        debugLog("onAR: Resolution was RESULT_OK, so connecting current client again.");
        connect();
      }
      else if (paramInt2 == 10001)
      {
        debugLog("onAR: Resolution was RECONNECT_REQUIRED, so reconnecting.");
        connect();
      }
      else if (paramInt2 == 0)
      {
        debugLog("onAR: Got a cancellation result, so disconnecting.");
        this.mSignInCancelled = true;
        this.mConnectOnStart = false;
        this.mUserInitiatedSignIn = false;
        this.mSignInFailureReason = null;
        this.mConnecting = false;
        this.mGoogleApiClient.disconnect();
        paramInt2 = getSignInCancellations();
        paramInt1 = incrementSignInCancellations();
        debugLog("onAR: # of cancellations " + paramInt2 + " --> " + paramInt1 + ", max " + this.mMaxAutoSignInAttempts);
        notifyListener(false);
      }
      else
      {
        debugLog("onAR: responseCode=" + GameHelperUtils.activityResponseCodeToString(paramInt2) + ", so giving up.");
        giveUp(new SignInFailureReason(this.mConnectionResult.getErrorCode(), paramInt2));
      }
    }
  }
  
  public void onConnected(Bundle paramBundle)
  {
    debugLog("onConnected: connected!");
    if (paramBundle != null)
    {
      debugLog("onConnected: connection hint provided. Checking for invite.");
      Invitation localInvitation = (Invitation)paramBundle.getParcelable("invitation");
      if ((localInvitation != null) && (localInvitation.getInvitationId() != null))
      {
        debugLog("onConnected: connection hint has a room invite!");
        this.mInvitation = localInvitation;
        debugLog("Invitation ID: " + this.mInvitation.getInvitationId());
      }
      debugLog("onConnected: connection hint provided. Checking for TBMP game.");
      this.mTurnBasedMatch = ((TurnBasedMatch)paramBundle.getParcelable("turn_based_match"));
    }
    succeedSignIn();
  }
  
  public void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    debugLog("onConnectionFailed");
    this.mConnectionResult = paramConnectionResult;
    debugLog("Connection failure:");
    debugLog("   - code: " + GameHelperUtils.errorCodeToString(this.mConnectionResult.getErrorCode()));
    debugLog("   - resolvable: " + this.mConnectionResult.hasResolution());
    debugLog("   - details: " + this.mConnectionResult.toString());
    int j = getSignInCancellations();
    int i;
    if (this.mUserInitiatedSignIn)
    {
      debugLog("onConnectionFailed: WILL resolve because user initiated sign-in.");
      i = 1;
      if (i != 0) {
        break label271;
      }
      debugLog("onConnectionFailed: since we won't resolve, failing now.");
      this.mConnectionResult = paramConnectionResult;
      this.mConnecting = false;
      notifyListener(false);
    }
    for (;;)
    {
      return;
      if (this.mSignInCancelled)
      {
        debugLog("onConnectionFailed WILL NOT resolve (user already cancelled once).");
        i = 0;
        break;
      }
      if (j < this.mMaxAutoSignInAttempts)
      {
        debugLog("onConnectionFailed: WILL resolve because we have below the max# of attempts, " + j + " < " + this.mMaxAutoSignInAttempts);
        i = 1;
        break;
      }
      i = 0;
      debugLog("onConnectionFailed: Will NOT resolve; not user-initiated and max attempts reached: " + j + " >= " + this.mMaxAutoSignInAttempts);
      break;
      label271:
      debugLog("onConnectionFailed: resolving problem...");
      resolveConnectionResult();
    }
  }
  
  public void onConnectionSuspended(int paramInt)
  {
    debugLog("onConnectionSuspended, cause=" + paramInt);
    disconnect();
    this.mSignInFailureReason = null;
    debugLog("Making extraordinary call to onSignInFailed callback");
    this.mConnecting = false;
    notifyListener(false);
  }
  
  public void onStart(Activity paramActivity)
  {
    this.mActivity = paramActivity;
    this.mAppContext = paramActivity.getApplicationContext();
    debugLog("onStart");
    assertConfigured("onStart");
    if (this.mConnectOnStart) {
      if (this.mGoogleApiClient.isConnected()) {
        Log.w("GameHelper", "GameHelper: client was already connected on onStart()");
      }
    }
    for (;;)
    {
      return;
      debugLog("Connecting client.");
      this.mConnecting = true;
      this.mGoogleApiClient.connect();
      continue;
      debugLog("Not attempting to connect becase mConnectOnStart=false");
      debugLog("Instead, reporting a sign-in failure.");
      this.mHandler.postDelayed(new Runnable()
      {
        public void run()
        {
          GameHelper.this.notifyListener(false);
        }
      }, 1000L);
    }
  }
  
  public void onStop()
  {
    debugLog("onStop");
    assertConfigured("onStop");
    if (this.mGoogleApiClient.isConnected())
    {
      debugLog("Disconnecting client due to onStop");
      this.mGoogleApiClient.disconnect();
    }
    for (;;)
    {
      this.mConnecting = false;
      this.mExpectingResolution = false;
      this.mActivity = null;
      return;
      debugLog("Client already disconnected when we got onStop.");
    }
  }
  
  public void reconnectClient()
  {
    if (!this.mGoogleApiClient.isConnected())
    {
      Log.w("GameHelper", "reconnectClient() called when client is not connected.");
      connect();
    }
    for (;;)
    {
      return;
      debugLog("Reconnecting client.");
      this.mGoogleApiClient.reconnect();
    }
  }
  
  void resetSignInCancellations()
  {
    this.mAppContext.getSharedPreferences("GAMEHELPER_SHARED_PREFS", 0).edit().putInt("KEY_SIGN_IN_CANCELLATIONS", 0);
  }
  
  void resolveConnectionResult()
  {
    if (this.mExpectingResolution) {
      debugLog("We're already expecting the result of a previous resolution.");
    }
    for (;;)
    {
      return;
      if (this.mActivity == null)
      {
        Log.e("GameHelper", "Ignoring attempt to resolve connection result without an active Activity.");
      }
      else
      {
        debugLog("resolveConnectionResult: trying to resolve result: " + this.mConnectionResult);
        if (this.mConnectionResult.hasResolution())
        {
          debugLog("Result has resolution. Starting it.");
          try
          {
            this.mExpectingResolution = true;
            this.mConnectionResult.startResolutionForResult(this.mActivity, 9001);
          }
          catch (IntentSender.SendIntentException localSendIntentException)
          {
            debugLog("SendIntentException, so connecting again.");
            connect();
          }
        }
        else
        {
          debugLog("resolveConnectionResult: result has no resolution. Giving up.");
          giveUp(new SignInFailureReason(this.mConnectionResult.getErrorCode()));
        }
      }
    }
  }
  
  public void setConnectOnStart(boolean paramBoolean)
  {
    debugLog("Forcing mConnectOnStart=" + paramBoolean);
    this.mConnectOnStart = paramBoolean;
  }
  
  public void setGamesApiOptions(Api.ApiOptions paramApiOptions)
  {
    doApiOptionsPreCheck();
    this.mGamesApiOptions = paramApiOptions;
  }
  
  public void setMaxAutoSignInAttempts(int paramInt)
  {
    this.mMaxAutoSignInAttempts = paramInt;
  }
  
  public void setPlusApiOptions(Api.ApiOptions paramApiOptions)
  {
    doApiOptionsPreCheck();
    this.mPlusApiOptions = paramApiOptions;
  }
  
  public void setShowErrorDialogs(boolean paramBoolean)
  {
    this.mShowErrorDialogs = paramBoolean;
  }
  
  public void setup(GameHelperListener paramGameHelperListener)
  {
    if (this.mSetupDone)
    {
      logError("GameHelper: you cannot call GameHelper.setup() more than once!");
      throw new IllegalStateException("GameHelper: you cannot call GameHelper.setup() more than once!");
    }
    this.mListener = paramGameHelperListener;
    debugLog("Setup: requested clients: " + this.mRequestedClients);
    if (this.mGoogleApiClientBuilder == null) {
      createApiClientBuilder();
    }
    this.mGoogleApiClient = this.mGoogleApiClientBuilder.build();
    this.mGoogleApiClientBuilder = null;
    this.mSetupDone = true;
  }
  
  public void showFailureDialog()
  {
    if (this.mSignInFailureReason != null)
    {
      int j = this.mSignInFailureReason.getServiceErrorCode();
      int i = this.mSignInFailureReason.getActivityResultCode();
      if (!this.mShowErrorDialogs) {
        break label40;
      }
      showFailureDialog(this.mActivity, i, j);
    }
    for (;;)
    {
      return;
      label40:
      debugLog("Not showing error dialog because mShowErrorDialogs==false. Error was: " + this.mSignInFailureReason);
    }
  }
  
  public void signOut()
  {
    if (!this.mGoogleApiClient.isConnected()) {
      debugLog("signOut: was already disconnected, ignoring.");
    }
    for (;;)
    {
      return;
      if ((this.mRequestedClients & 0x2) != 0)
      {
        debugLog("Clearing default account on PlusClient.");
        Plus.AccountApi.clearDefaultAccount(this.mGoogleApiClient);
      }
      if ((this.mRequestedClients & 0x1) != 0)
      {
        debugLog("Signing out from GamesClient.");
        Games.signOut(this.mGoogleApiClient);
      }
      debugLog("Disconnecting client.");
      this.mConnectOnStart = false;
      this.mConnecting = false;
      this.mGoogleApiClient.disconnect();
    }
  }
  
  void succeedSignIn()
  {
    debugLog("succeedSignIn");
    this.mSignInFailureReason = null;
    this.mConnectOnStart = true;
    this.mUserInitiatedSignIn = false;
    this.mConnecting = false;
    notifyListener(true);
  }
  
  public static abstract interface GameHelperListener
  {
    public abstract void onSignInFailed();
    
    public abstract void onSignInSucceeded();
  }
  
  public static class SignInFailureReason
  {
    public static final int NO_ACTIVITY_RESULT_CODE = -100;
    int mActivityResultCode = -100;
    int mServiceErrorCode = 0;
    
    public SignInFailureReason(int paramInt)
    {
      this(paramInt, -100);
    }
    
    public SignInFailureReason(int paramInt1, int paramInt2)
    {
      this.mServiceErrorCode = paramInt1;
      this.mActivityResultCode = paramInt2;
    }
    
    public int getActivityResultCode()
    {
      return this.mActivityResultCode;
    }
    
    public int getServiceErrorCode()
    {
      return this.mServiceErrorCode;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder().append("SignInFailureReason(serviceErrorCode:").append(GameHelperUtils.errorCodeToString(this.mServiceErrorCode));
      if (this.mActivityResultCode == -100) {}
      for (String str = ")";; str = ",activityResultCode:" + GameHelperUtils.activityResponseCodeToString(this.mActivityResultCode) + ")") {
        return str;
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\example\games\basegameutils\GameHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */