package com.facebook.unity;

import android.util.Log;
import com.unity3d.player.UnityPlayer;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UnityMessage
{
  private String methodName;
  private Map<String, Serializable> params = new HashMap();
  
  static
  {
    if (!UnityMessage.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public UnityMessage(String paramString)
  {
    this.methodName = paramString;
  }
  
  public UnityMessage put(String paramString, Serializable paramSerializable)
  {
    this.params.put(paramString, paramSerializable);
    return this;
  }
  
  public UnityMessage putCancelled()
  {
    put("cancelled", Boolean.valueOf(true));
    return this;
  }
  
  public UnityMessage putID(String paramString)
  {
    put("id", paramString);
    return this;
  }
  
  public void send()
  {
    assert (this.methodName != null) : "no method specified";
    String str = new UnityParams(this.params).toString();
    Log.v("FBUnitySDK", "sending to Unity " + this.methodName + "(" + str + ")");
    try
    {
      UnityPlayer.UnitySendMessage("UnityFacebookSDKPlugin", this.methodName, str);
      return;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      for (;;)
      {
        Log.v("FBUnitySDK", "message not send, Unity not initialized");
      }
    }
  }
  
  public void sendError(String paramString)
  {
    put("error", paramString);
    send();
  }
  
  public void sendNotLoggedInError()
  {
    sendError("not logged in");
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\facebook\unity\UnityMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */