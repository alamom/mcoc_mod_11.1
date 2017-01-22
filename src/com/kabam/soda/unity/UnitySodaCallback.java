package com.kabam.soda.unity;

import com.kabam.soda.SodaCallback;
import com.kabam.soda.SodaCallback.AccountError;
import com.unity3d.player.UnityPlayer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONObject;

public class UnitySodaCallback
  implements SodaCallback
{
  public static final String CALLBACK_ROUTER = "SODACallbackRouter";
  private Queue<UnityMessage> messageQueue = new ConcurrentLinkedQueue();
  private String receiverObject;
  
  private void emptyQueue()
  {
    try
    {
      String str = this.receiverObject;
      if (str != null) {
        for (UnityMessage localUnityMessage = (UnityMessage)this.messageQueue.poll(); localUnityMessage != null; localUnityMessage = (UnityMessage)this.messageQueue.poll()) {
          UnityPlayer.UnitySendMessage(str, localUnityMessage.name, localUnityMessage.payload);
        }
      }
      return;
    }
    finally {}
  }
  
  public String getReceiverObject()
  {
    return this.receiverObject;
  }
  
  public void onCertificateExpiry(String paramString1, String paramString2)
  {
    paramString2 = new LinkedHashMap();
    paramString2.put("callback", CallbackType.CERTIFICATE_EXPIRED.name());
    paramString2.put("playerId", paramString1);
    sendMessage("SODACallbackRouter", new JSONObject(paramString2).toString());
  }
  
  public void onKabamAccountError(SodaCallback.AccountError paramAccountError)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    localLinkedHashMap.put("callback", CallbackType.KABAM_ACCOUNT_LOGIN_FAILED.name());
    localLinkedHashMap.put("error", Integer.valueOf(paramAccountError.ordinal()));
    sendMessage("SODACallbackRouter", new JSONObject(localLinkedHashMap).toString());
  }
  
  public void onKabamLogin(String paramString)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    localLinkedHashMap.put("callback", CallbackType.KABAM_ACCOUNT_LOGIN.name());
    localLinkedHashMap.put("authToken", paramString);
    sendMessage("SODACallbackRouter", new JSONObject(localLinkedHashMap).toString());
  }
  
  public void sendMessage(String paramString1, String paramString2)
  {
    emptyQueue();
    if (this.receiverObject != null) {
      UnityPlayer.UnitySendMessage(this.receiverObject, paramString1, paramString2);
    }
    for (;;)
    {
      return;
      this.messageQueue.add(new UnityMessage(paramString1, paramString2));
    }
  }
  
  public void setReceiverObject(String paramString)
  {
    this.receiverObject = paramString;
    emptyQueue();
  }
  
  public static enum CallbackType
  {
    CERTIFICATE_EXPIRED,  KABAM_ACCOUNT_LOGIN,  KABAM_ACCOUNT_LOGIN_FAILED;
    
    private CallbackType() {}
  }
  
  private class UnityMessage
  {
    public String name;
    public String payload;
    
    public UnityMessage(String paramString1, String paramString2)
    {
      this.name = paramString1;
      this.payload = paramString2;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\kabam\soda\unity\UnitySodaCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */