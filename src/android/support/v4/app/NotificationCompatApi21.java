package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.app.RemoteInput.Builder;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;

class NotificationCompatApi21
{
  public static final String CATEGORY_ALARM = "alarm";
  public static final String CATEGORY_CALL = "call";
  public static final String CATEGORY_EMAIL = "email";
  public static final String CATEGORY_ERROR = "err";
  public static final String CATEGORY_EVENT = "event";
  public static final String CATEGORY_MESSAGE = "msg";
  public static final String CATEGORY_PROGRESS = "progress";
  public static final String CATEGORY_PROMO = "promo";
  public static final String CATEGORY_RECOMMENDATION = "recommendation";
  public static final String CATEGORY_SERVICE = "service";
  public static final String CATEGORY_SOCIAL = "social";
  public static final String CATEGORY_STATUS = "status";
  public static final String CATEGORY_SYSTEM = "sys";
  public static final String CATEGORY_TRANSPORT = "transport";
  private static final String KEY_AUTHOR = "author";
  private static final String KEY_MESSAGES = "messages";
  private static final String KEY_ON_READ = "on_read";
  private static final String KEY_ON_REPLY = "on_reply";
  private static final String KEY_PARTICIPANTS = "participants";
  private static final String KEY_REMOTE_INPUT = "remote_input";
  private static final String KEY_TEXT = "text";
  private static final String KEY_TIMESTAMP = "timestamp";
  
  private static RemoteInput fromCompatRemoteInput(RemoteInputCompatBase.RemoteInput paramRemoteInput)
  {
    return new RemoteInput.Builder(paramRemoteInput.getResultKey()).setLabel(paramRemoteInput.getLabel()).setChoices(paramRemoteInput.getChoices()).setAllowFreeFormInput(paramRemoteInput.getAllowFreeFormInput()).addExtras(paramRemoteInput.getExtras()).build();
  }
  
  static Bundle getBundleForUnreadConversation(NotificationCompatBase.UnreadConversation paramUnreadConversation)
  {
    if (paramUnreadConversation == null) {}
    Bundle localBundle1;
    for (paramUnreadConversation = null;; paramUnreadConversation = localBundle1)
    {
      return paramUnreadConversation;
      localBundle1 = new Bundle();
      Bundle localBundle2 = null;
      Object localObject = localBundle2;
      if (paramUnreadConversation.getParticipants() != null)
      {
        localObject = localBundle2;
        if (paramUnreadConversation.getParticipants().length > 1) {
          localObject = paramUnreadConversation.getParticipants()[0];
        }
      }
      Parcelable[] arrayOfParcelable = new Parcelable[paramUnreadConversation.getMessages().length];
      for (int i = 0; i < arrayOfParcelable.length; i++)
      {
        localBundle2 = new Bundle();
        localBundle2.putString("text", paramUnreadConversation.getMessages()[i]);
        localBundle2.putString("author", (String)localObject);
        arrayOfParcelable[i] = localBundle2;
      }
      localBundle1.putParcelableArray("messages", arrayOfParcelable);
      localObject = paramUnreadConversation.getRemoteInput();
      if (localObject != null) {
        localBundle1.putParcelable("remote_input", fromCompatRemoteInput((RemoteInputCompatBase.RemoteInput)localObject));
      }
      localBundle1.putParcelable("on_reply", paramUnreadConversation.getReplyPendingIntent());
      localBundle1.putParcelable("on_read", paramUnreadConversation.getReadPendingIntent());
      localBundle1.putStringArray("participants", paramUnreadConversation.getParticipants());
      localBundle1.putLong("timestamp", paramUnreadConversation.getLatestTimestamp());
    }
  }
  
  public static String getCategory(Notification paramNotification)
  {
    return paramNotification.category;
  }
  
  static NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(Bundle paramBundle, NotificationCompatBase.UnreadConversation.Factory paramFactory, RemoteInputCompatBase.RemoteInput.Factory paramFactory1)
  {
    Object localObject3 = null;
    Object localObject2 = null;
    if (paramBundle == null)
    {
      localObject1 = localObject2;
      return (NotificationCompatBase.UnreadConversation)localObject1;
    }
    Object localObject1 = paramBundle.getParcelableArray("messages");
    String[] arrayOfString1 = null;
    int k;
    if (localObject1 != null)
    {
      arrayOfString1 = new String[localObject1.length];
      k = 1;
    }
    label184:
    label215:
    for (int j = 0;; j++)
    {
      int i = k;
      if (j < arrayOfString1.length) {
        if ((localObject1[j] instanceof Bundle)) {
          break label184;
        }
      }
      for (i = 0;; i = 0)
      {
        localObject1 = localObject2;
        if (i == 0) {
          break;
        }
        PendingIntent localPendingIntent2 = (PendingIntent)paramBundle.getParcelable("on_read");
        PendingIntent localPendingIntent1 = (PendingIntent)paramBundle.getParcelable("on_reply");
        RemoteInput localRemoteInput = (RemoteInput)paramBundle.getParcelable("remote_input");
        String[] arrayOfString2 = paramBundle.getStringArray("participants");
        localObject1 = localObject2;
        if (arrayOfString2 == null) {
          break;
        }
        localObject1 = localObject2;
        if (arrayOfString2.length != 1) {
          break;
        }
        localObject1 = localObject3;
        if (localRemoteInput != null) {
          localObject1 = toCompatRemoteInput(localRemoteInput, paramFactory1);
        }
        localObject1 = paramFactory.build(arrayOfString1, (RemoteInputCompatBase.RemoteInput)localObject1, localPendingIntent1, localPendingIntent2, arrayOfString2, paramBundle.getLong("timestamp"));
        break;
        arrayOfString1[j] = ((Bundle)localObject1[j]).getString("text");
        if (arrayOfString1[j] != null) {
          break label215;
        }
      }
    }
  }
  
  private static RemoteInputCompatBase.RemoteInput toCompatRemoteInput(RemoteInput paramRemoteInput, RemoteInputCompatBase.RemoteInput.Factory paramFactory)
  {
    return paramFactory.build(paramRemoteInput.getResultKey(), paramRemoteInput.getLabel(), paramRemoteInput.getChoices(), paramRemoteInput.getAllowFreeFormInput(), paramRemoteInput.getExtras());
  }
  
  public static class Builder
    implements NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions
  {
    private Notification.Builder b;
    
    public Builder(Context paramContext, Notification paramNotification1, CharSequence paramCharSequence1, CharSequence paramCharSequence2, CharSequence paramCharSequence3, RemoteViews paramRemoteViews, int paramInt1, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2, Bitmap paramBitmap, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt4, CharSequence paramCharSequence4, boolean paramBoolean4, String paramString1, ArrayList<String> paramArrayList, Bundle paramBundle, int paramInt5, int paramInt6, Notification paramNotification2, String paramString2, boolean paramBoolean5, String paramString3)
    {
      paramContext = new Notification.Builder(paramContext).setWhen(paramNotification1.when).setShowWhen(paramBoolean2).setSmallIcon(paramNotification1.icon, paramNotification1.iconLevel).setContent(paramNotification1.contentView).setTicker(paramNotification1.tickerText, paramRemoteViews).setSound(paramNotification1.sound, paramNotification1.audioStreamType).setVibrate(paramNotification1.vibrate).setLights(paramNotification1.ledARGB, paramNotification1.ledOnMS, paramNotification1.ledOffMS);
      if ((paramNotification1.flags & 0x2) != 0)
      {
        paramBoolean2 = true;
        paramContext = paramContext.setOngoing(paramBoolean2);
        if ((paramNotification1.flags & 0x8) == 0) {
          break label325;
        }
        paramBoolean2 = true;
        label117:
        paramContext = paramContext.setOnlyAlertOnce(paramBoolean2);
        if ((paramNotification1.flags & 0x10) == 0) {
          break label331;
        }
        paramBoolean2 = true;
        label137:
        paramContext = paramContext.setAutoCancel(paramBoolean2).setDefaults(paramNotification1.defaults).setContentTitle(paramCharSequence1).setContentText(paramCharSequence2).setSubText(paramCharSequence4).setContentInfo(paramCharSequence3).setContentIntent(paramPendingIntent1).setDeleteIntent(paramNotification1.deleteIntent);
        if ((paramNotification1.flags & 0x80) == 0) {
          break label337;
        }
      }
      label325:
      label331:
      label337:
      for (paramBoolean2 = true;; paramBoolean2 = false)
      {
        this.b = paramContext.setFullScreenIntent(paramPendingIntent2, paramBoolean2).setLargeIcon(paramBitmap).setNumber(paramInt1).setUsesChronometer(paramBoolean3).setPriority(paramInt4).setProgress(paramInt2, paramInt3, paramBoolean1).setLocalOnly(paramBoolean4).setExtras(paramBundle).setGroup(paramString2).setGroupSummary(paramBoolean5).setSortKey(paramString3).setCategory(paramString1).setColor(paramInt5).setVisibility(paramInt6).setPublicVersion(paramNotification2);
        paramNotification1 = paramArrayList.iterator();
        while (paramNotification1.hasNext())
        {
          paramContext = (String)paramNotification1.next();
          this.b.addPerson(paramContext);
        }
        paramBoolean2 = false;
        break;
        paramBoolean2 = false;
        break label117;
        paramBoolean2 = false;
        break label137;
      }
    }
    
    public void addAction(NotificationCompatBase.Action paramAction)
    {
      NotificationCompatApi20.addAction(this.b, paramAction);
    }
    
    public Notification build()
    {
      return this.b.build();
    }
    
    public Notification.Builder getBuilder()
    {
      return this.b;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\app\NotificationCompatApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */