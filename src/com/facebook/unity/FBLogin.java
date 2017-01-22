package com.facebook.unity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import com.facebook.Request;
import com.facebook.Request.GraphUserCallback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.Builder;
import com.facebook.Session.NewPermissionsRequest;
import com.facebook.Session.OpenRequest;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionDefaultAudience;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FBLogin
{
  private static void finalizeLogin(Session paramSession, SessionState paramSessionState, Exception paramException, UnityMessage paramUnityMessage, Activity paramActivity)
  {
    if (paramActivity != null) {
      paramActivity.finish();
    }
    if ((!paramSession.isOpened()) && (paramSessionState != SessionState.CLOSED_LOGIN_FAILED)) {
      paramUnityMessage.sendError("Unknown error while opening session. Check logcat for details.");
    }
    for (;;)
    {
      return;
      if (paramSession.isOpened()) {
        paramUnityMessage.put("opened", Boolean.valueOf(true));
      }
      for (;;)
      {
        if ((paramSession.getAccessToken() != null) && (!paramSession.getAccessToken().equals(""))) {
          break label90;
        }
        paramUnityMessage.send();
        break;
        if (paramSessionState == SessionState.CLOSED_LOGIN_FAILED) {
          paramUnityMessage.putCancelled();
        }
      }
      label90:
      paramSession.addCallback(new Session.StatusCallback()
      {
        public void call(Session paramAnonymousSession, SessionState paramAnonymousSessionState, Exception paramAnonymousException)
        {
          if ((paramAnonymousSession == null) || (paramAnonymousSession.getAccessToken() == null)) {}
          for (;;)
          {
            return;
            paramAnonymousSessionState = new UnityMessage("OnAccessTokenUpdate");
            paramAnonymousSessionState.put("access_token", paramAnonymousSession.getAccessToken());
            paramAnonymousSessionState.put("expiration_timestamp", "" + paramAnonymousSession.getExpirationDate().getTime() / 1000L);
            paramAnonymousSessionState.send();
          }
        }
      });
      paramUnityMessage.put("access_token", paramSession.getAccessToken());
      paramUnityMessage.put("expiration_timestamp", "" + paramSession.getExpirationDate().getTime() / 1000L);
      Request.newMeRequest(paramSession, new Request.GraphUserCallback()
      {
        public void onCompleted(GraphUser paramAnonymousGraphUser, Response paramAnonymousResponse)
        {
          if (paramAnonymousGraphUser != null) {
            this.val$unityMessage.put("user_id", paramAnonymousGraphUser.getId());
          }
          this.val$unityMessage.send();
        }
      }).executeAsync();
    }
  }
  
  private static Session.StatusCallback getAfterReadPermissionLoginCallback(UnityMessage paramUnityMessage, final List<String> paramList, final Activity paramActivity)
  {
    new Session.StatusCallback()
    {
      public void call(Session paramAnonymousSession, SessionState paramAnonymousSessionState, Exception paramAnonymousException)
      {
        if (paramAnonymousSession.getState().equals(SessionState.OPENING)) {}
        for (;;)
        {
          return;
          paramAnonymousSession.removeCallback(this);
          if ((!paramAnonymousSession.isOpened()) && (paramAnonymousSessionState != SessionState.CLOSED_LOGIN_FAILED))
          {
            this.val$unityMessage.sendError("Unknown error while opening session. Check logcat for details.");
            paramActivity.finish();
          }
          else if ((paramAnonymousSession.getAccessToken() == null) || (paramAnonymousSession.getAccessToken().equals("")))
          {
            this.val$unityMessage.putCancelled();
            this.val$unityMessage.send();
            paramActivity.finish();
          }
          else if (paramAnonymousSession.getPermissions().containsAll(paramList))
          {
            FBLogin.finalizeLogin(paramAnonymousSession, paramAnonymousSessionState, paramAnonymousException, this.val$unityMessage, paramActivity);
          }
          else
          {
            FBLogin.sessionOpenRequest(paramAnonymousSession, FBLogin.getFinalCallback(this.val$unityMessage, paramActivity), paramActivity, paramList, true);
          }
        }
      }
    };
  }
  
  private static Session.StatusCallback getFinalCallback(UnityMessage paramUnityMessage, final Activity paramActivity)
  {
    new Session.StatusCallback()
    {
      public void call(Session paramAnonymousSession, SessionState paramAnonymousSessionState, Exception paramAnonymousException)
      {
        if (paramAnonymousSession.getState().equals(SessionState.OPENING)) {}
        for (;;)
        {
          return;
          paramAnonymousSession.removeCallback(this);
          FBLogin.finalizeLogin(paramAnonymousSession, paramAnonymousSessionState, paramAnonymousException, this.val$unityMessage, paramActivity);
        }
      }
    };
  }
  
  private static Session.NewPermissionsRequest getNewPermissionsRequest(Session paramSession, Session.StatusCallback paramStatusCallback, List<String> paramList, Activity paramActivity)
  {
    paramList = new Session.NewPermissionsRequest(paramActivity, paramList);
    paramList.setCallback(paramStatusCallback);
    paramSession.addCallback(paramStatusCallback);
    paramList.setDefaultAudience(SessionDefaultAudience.FRIENDS);
    return paramList;
  }
  
  private static Session.OpenRequest getOpenRequest(Session.StatusCallback paramStatusCallback, List<String> paramList, Activity paramActivity)
  {
    paramActivity = new Session.OpenRequest(paramActivity);
    paramActivity.setCallback(paramStatusCallback);
    paramActivity.setPermissions(paramList);
    paramActivity.setDefaultAudience(SessionDefaultAudience.FRIENDS);
    return paramActivity;
  }
  
  public static void init(String paramString)
  {
    Session localSession1;
    if (FB.isLoggedIn())
    {
      Session localSession2 = Session.getActiveSession();
      localSession1 = localSession2;
      if (paramString != localSession2.getApplicationId())
      {
        Log.w("FBUnitySDK", "App Id in active session (" + localSession2.getApplicationId() + ") doesn't match App Id passed in: " + paramString);
        localSession1 = new Session.Builder(FB.getUnityActivity()).setApplicationId(paramString).build();
      }
      Session.setActiveSession(localSession1);
      paramString = new UnityMessage("OnInitComplete");
      paramString.put("key_hash", FB.getKeyHash());
      if (!SessionState.CREATED_TOKEN_LOADED.equals(localSession1.getState())) {
        break label148;
      }
      sessionOpenRequest(localSession1, getFinalCallback(paramString, null), FB.getUnityActivity(), null, false);
    }
    for (;;)
    {
      return;
      localSession1 = new Session.Builder(FB.getUnityActivity()).setApplicationId(paramString).build();
      break;
      label148:
      paramString.send();
    }
  }
  
  public static void login(String paramString, Activity paramActivity)
  {
    int i = 1;
    Object localObject2 = Session.getActiveSession();
    if (localObject2 == null) {
      Log.w("FBUnitySDK", "Session not found. Call init() before calling login()");
    }
    for (;;)
    {
      return;
      Object localObject1 = localObject2;
      if (((Session)localObject2).isClosed())
      {
        localObject1 = new Session.Builder(FB.getUnityActivity()).setApplicationId(((Session)localObject2).getApplicationId()).build();
        Session.setActiveSession((Session)localObject1);
      }
      UnityMessage localUnityMessage = new UnityMessage("OnLoginComplete");
      localUnityMessage.put("key_hash", FB.getKeyHash());
      localObject2 = new ArrayList();
      Object localObject3 = UnityParams.parse(paramString, "couldn't parse login params: " + paramString);
      paramString = (String)localObject2;
      if (((UnityParams)localObject3).hasString("scope").booleanValue()) {
        paramString = new ArrayList(Arrays.asList(((UnityParams)localObject3).getString("scope").split(",")));
      }
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      if (paramString.size() > 0)
      {
        localObject3 = paramString.iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localObject2 = (String)((Iterator)localObject3).next();
          if (((String)localObject2).length() != 0) {
            if (Session.isPublishPermission((String)localObject2)) {
              localArrayList1.add(localObject2);
            } else {
              localArrayList2.add(localObject2);
            }
          }
        }
      }
      boolean bool;
      if (!localArrayList1.isEmpty())
      {
        bool = true;
        label271:
        if ((!bool) || (((Session)localObject1).getPermissions().containsAll(localArrayList2))) {
          break label319;
        }
      }
      for (;;)
      {
        if (i == 0) {
          break label324;
        }
        sessionOpenRequest((Session)localObject1, getAfterReadPermissionLoginCallback(localUnityMessage, localArrayList1, paramActivity), paramActivity, localArrayList2, false);
        break;
        bool = false;
        break label271;
        label319:
        i = 0;
      }
      label324:
      sessionOpenRequest((Session)localObject1, getFinalCallback(localUnityMessage, paramActivity), paramActivity, paramString, bool);
    }
  }
  
  public static void onActivityResult(Activity paramActivity, int paramInt1, int paramInt2, Intent paramIntent)
  {
    Session.getActiveSession().onActivityResult(paramActivity, paramInt1, paramInt2, paramIntent);
  }
  
  static void sessionOpenRequest(Session paramSession, Session.StatusCallback paramStatusCallback, Activity paramActivity, List<String> paramList, boolean paramBoolean)
  {
    if (paramSession.isOpened())
    {
      paramStatusCallback = getNewPermissionsRequest(paramSession, paramStatusCallback, paramList, paramActivity);
      if (paramBoolean) {
        paramSession.requestNewPublishPermissions(paramStatusCallback);
      }
    }
    for (;;)
    {
      return;
      paramSession.requestNewReadPermissions(paramStatusCallback);
      continue;
      paramStatusCallback = getOpenRequest(paramStatusCallback, paramList, paramActivity);
      if (paramBoolean) {
        paramSession.openForPublish(paramStatusCallback);
      } else {
        paramSession.openForRead(paramStatusCallback);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\unity\FBLogin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */