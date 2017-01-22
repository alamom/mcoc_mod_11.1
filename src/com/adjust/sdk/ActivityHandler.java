package com.adjust.sdk;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class ActivityHandler
  extends HandlerThread
  implements IActivityHandler
{
  private static final String ACTIVITY_STATE_NAME = "Activity state";
  private static final String ADJUST_PREFIX = "adjust_";
  private static final String ATTRIBUTION_NAME = "Attribution";
  private static long SESSION_INTERVAL = 0L;
  private static long SUBSESSION_INTERVAL = 0L;
  private static long TIMER_INTERVAL = 0L;
  private static long TIMER_START = 0L;
  private static final String TIME_TRAVEL = "Time travel!";
  private static ScheduledExecutorService timer;
  private ActivityState activityState;
  private AdjustConfig adjustConfig;
  private AdjustAttribution attribution;
  private IAttributionHandler attributionHandler;
  private DeviceInfo deviceInfo;
  private boolean enabled;
  private ILogger logger;
  private boolean offline;
  private IPackageHandler packageHandler;
  private SessionHandler sessionHandler;
  
  private ActivityHandler(AdjustConfig paramAdjustConfig)
  {
    super("Adjust", 1);
    setDaemon(true);
    start();
    this.logger = AdjustFactory.getLogger();
    this.sessionHandler = new SessionHandler(getLooper(), this);
    this.enabled = true;
    init(paramAdjustConfig);
    paramAdjustConfig = Message.obtain();
    paramAdjustConfig.arg1 = 72631;
    this.sessionHandler.sendMessage(paramAdjustConfig);
  }
  
  private ActivityPackage buildQueryStringClickPackage(String paramString1, String paramString2, long paramLong)
  {
    if (paramString1 == null) {
      paramString1 = null;
    }
    for (;;)
    {
      return paramString1;
      long l = System.currentTimeMillis();
      LinkedHashMap localLinkedHashMap = new LinkedHashMap();
      AdjustAttribution localAdjustAttribution = new AdjustAttribution();
      int j = 0;
      paramString1 = paramString1.split("&");
      int k = paramString1.length;
      for (int i = 0; i < k; i++) {
        if (readQueryString(paramString1[i], localLinkedHashMap, localAdjustAttribution)) {
          j = 1;
        }
      }
      if (j == 0)
      {
        paramString1 = null;
      }
      else
      {
        paramString1 = (String)localLinkedHashMap.remove("reftag");
        PackageBuilder localPackageBuilder = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, l);
        localPackageBuilder.extraParameters = localLinkedHashMap;
        localPackageBuilder.attribution = localAdjustAttribution;
        localPackageBuilder.reftag = paramString1;
        paramString1 = localPackageBuilder.buildClickPackage(paramString2, paramLong);
      }
    }
  }
  
  private void checkAttributionState()
  {
    if (this.activityState.subsessionCount <= 1) {}
    for (;;)
    {
      return;
      if ((this.attribution == null) || (this.activityState.askingAttribution)) {
        getAttributionHandler().getAttribution();
      }
    }
  }
  
  private boolean checkEvent(AdjustEvent paramAdjustEvent)
  {
    boolean bool = false;
    if (paramAdjustEvent == null) {
      this.logger.error("Event missing", new Object[0]);
    }
    for (;;)
    {
      return bool;
      if (!paramAdjustEvent.isValid()) {
        this.logger.error("Event not initialized correctly", new Object[0]);
      } else {
        bool = true;
      }
    }
  }
  
  public static boolean deleteActivityState(Context paramContext)
  {
    return paramContext.deleteFile("AdjustIoActivityState");
  }
  
  public static boolean deleteAttribution(Context paramContext)
  {
    return paramContext.deleteFile("AdjustAttribution");
  }
  
  private void endInternal()
  {
    this.packageHandler.pauseSending();
    getAttributionHandler().pauseSending();
    stopTimer();
    if (updateActivityState(System.currentTimeMillis())) {
      writeActivityState();
    }
  }
  
  private void finishedTrackingActivityInternal(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {}
    for (;;)
    {
      return;
      launchDeeplinkMain(paramJSONObject.optString("deeplink", null));
      getAttributionHandler().checkAttribution(paramJSONObject);
    }
  }
  
  private IAttributionHandler getAttributionHandler()
  {
    if (this.attributionHandler == null) {
      this.attributionHandler = AdjustFactory.getAttributionHandler(this, getAttributionPackage(), toPause(), this.adjustConfig.hasListener());
    }
    return this.attributionHandler;
  }
  
  public static ActivityHandler getInstance(AdjustConfig paramAdjustConfig)
  {
    Object localObject2 = null;
    Object localObject1;
    if (paramAdjustConfig == null)
    {
      AdjustFactory.getLogger().error("AdjustConfig missing", new Object[0]);
      localObject1 = localObject2;
    }
    for (;;)
    {
      return (ActivityHandler)localObject1;
      if (!paramAdjustConfig.isValid())
      {
        AdjustFactory.getLogger().error("AdjustConfig not initialized correctly", new Object[0]);
        localObject1 = localObject2;
      }
      else if (paramAdjustConfig.processName != null)
      {
        int i = Process.myPid();
        Object localObject3 = (ActivityManager)paramAdjustConfig.context.getSystemService("activity");
        localObject1 = localObject2;
        if (localObject3 != null)
        {
          localObject3 = ((ActivityManager)localObject3).getRunningAppProcesses().iterator();
          for (;;)
          {
            if (((Iterator)localObject3).hasNext())
            {
              localObject1 = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject3).next();
              if (((ActivityManager.RunningAppProcessInfo)localObject1).pid == i) {
                if (!((ActivityManager.RunningAppProcessInfo)localObject1).processName.equalsIgnoreCase(paramAdjustConfig.processName))
                {
                  AdjustFactory.getLogger().info("Skipping initialization in background process (%s)", new Object[] { ((ActivityManager.RunningAppProcessInfo)localObject1).processName });
                  localObject1 = localObject2;
                  break;
                }
              }
            }
          }
        }
      }
      else
      {
        localObject1 = new ActivityHandler(paramAdjustConfig);
      }
    }
  }
  
  private void initInternal()
  {
    TIMER_INTERVAL = AdjustFactory.getTimerInterval();
    TIMER_START = AdjustFactory.getTimerStart();
    SESSION_INTERVAL = AdjustFactory.getSessionInterval();
    SUBSESSION_INTERVAL = AdjustFactory.getSubsessionInterval();
    this.deviceInfo = new DeviceInfo(this.adjustConfig.context, this.adjustConfig.sdkPrefix);
    if ("production".equals(this.adjustConfig.environment)) {
      this.logger.setLogLevel(LogLevel.ASSERT);
    }
    for (;;)
    {
      if (this.adjustConfig.eventBufferingEnabled.booleanValue()) {
        this.logger.info("Event buffering is enabled", new Object[0]);
      }
      if (Util.getPlayAdId(this.adjustConfig.context) == null) {
        this.logger.info("Unable to get Google Play Services Advertising ID at start time", new Object[0]);
      }
      if (this.adjustConfig.defaultTracker != null) {
        this.logger.info("Default tracker: '%s'", new Object[] { this.adjustConfig.defaultTracker });
      }
      if (this.adjustConfig.referrer != null) {
        sendReferrer(this.adjustConfig.referrer, this.adjustConfig.referrerClickTime);
      }
      readAttribution();
      readActivityState();
      this.packageHandler = AdjustFactory.getPackageHandler(this, this.adjustConfig.context, toPause());
      startInternal();
      return;
      this.logger.setLogLevel(this.adjustConfig.logLevel);
    }
  }
  
  private void launchAttributionListener()
  {
    if (this.adjustConfig.onAttributionChangedListener == null) {}
    for (;;)
    {
      return;
      new Handler(this.adjustConfig.context.getMainLooper()).post(new Runnable()
      {
        public void run()
        {
          ActivityHandler.this.adjustConfig.onAttributionChangedListener.onAttributionChanged(ActivityHandler.this.attribution);
        }
      });
    }
  }
  
  private void launchDeeplinkMain(String paramString)
  {
    if (paramString == null) {}
    for (;;)
    {
      return;
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      localIntent.setFlags(268435456);
      if (this.adjustConfig.context.getPackageManager().queryIntentActivities(localIntent, 0).size() > 0) {}
      for (int i = 1;; i = 0)
      {
        if (i != 0) {
          break label85;
        }
        this.logger.error("Unable to open deep link (%s)", new Object[] { paramString });
        break;
      }
      label85:
      this.logger.info("Open deep link (%s)", new Object[] { paramString });
      this.adjustConfig.context.startActivity(localIntent);
    }
  }
  
  private void processSession()
  {
    long l1 = System.currentTimeMillis();
    if (this.activityState == null)
    {
      this.activityState = new ActivityState();
      this.activityState.sessionCount = 1;
      transferSessionPackage(l1);
      this.activityState.resetSessionAttributes(l1);
      this.activityState.enabled = this.enabled;
      writeActivityState();
    }
    for (;;)
    {
      return;
      long l2 = l1 - this.activityState.lastActivity;
      if (l2 < 0L)
      {
        this.logger.error("Time travel!", new Object[0]);
        this.activityState.lastActivity = l1;
        writeActivityState();
      }
      else
      {
        ActivityState localActivityState;
        if (l2 > SESSION_INTERVAL)
        {
          localActivityState = this.activityState;
          localActivityState.sessionCount += 1;
          this.activityState.lastInterval = l2;
          transferSessionPackage(l1);
          this.activityState.resetSessionAttributes(l1);
          writeActivityState();
        }
        else if (l2 > SUBSESSION_INTERVAL)
        {
          localActivityState = this.activityState;
          localActivityState.subsessionCount += 1;
          localActivityState = this.activityState;
          localActivityState.sessionLength += l2;
          this.activityState.lastActivity = l1;
          writeActivityState();
          this.logger.info("Started subsession %d of session %d", new Object[] { Integer.valueOf(this.activityState.subsessionCount), Integer.valueOf(this.activityState.sessionCount) });
        }
      }
    }
  }
  
  private void readActivityState()
  {
    this.activityState = ((ActivityState)Util.readObject(this.adjustConfig.context, "AdjustIoActivityState", "Activity state"));
  }
  
  private void readAttribution()
  {
    this.attribution = ((AdjustAttribution)Util.readObject(this.adjustConfig.context, "AdjustAttribution", "Attribution"));
  }
  
  private void readOpenUrlInternal(Uri paramUri, long paramLong)
  {
    if (paramUri == null) {}
    for (;;)
    {
      return;
      paramUri = buildQueryStringClickPackage(paramUri.getQuery(), "deeplink", paramLong);
      if (paramUri != null)
      {
        getAttributionHandler().getAttribution();
        this.packageHandler.sendClickPackage(paramUri);
      }
    }
  }
  
  private boolean readQueryString(String paramString, Map<String, String> paramMap, AdjustAttribution paramAdjustAttribution)
  {
    boolean bool2 = false;
    paramString = paramString.split("=");
    boolean bool1;
    if (paramString.length != 2) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      String str = paramString[0];
      bool1 = bool2;
      if (str.startsWith("adjust_"))
      {
        paramString = paramString[1];
        bool1 = bool2;
        if (paramString.length() != 0)
        {
          str = str.substring("adjust_".length());
          bool1 = bool2;
          if (str.length() != 0)
          {
            if (!trySetAttribution(paramAdjustAttribution, str, paramString)) {
              paramMap.put(str, paramString);
            }
            bool1 = true;
          }
        }
      }
    }
  }
  
  private void saveAttribution(AdjustAttribution paramAdjustAttribution)
  {
    this.attribution = paramAdjustAttribution;
    writeAttribution();
  }
  
  private void sendReferrerInternal(String paramString, long paramLong)
  {
    paramString = buildQueryStringClickPackage(paramString, "reftag", paramLong);
    if (paramString == null) {}
    for (;;)
    {
      return;
      getAttributionHandler().getAttribution();
      this.packageHandler.sendClickPackage(paramString);
    }
  }
  
  private void startInternal()
  {
    if ((this.activityState != null) && (!this.activityState.enabled)) {}
    for (;;)
    {
      return;
      updateStatusInternal();
      processSession();
      checkAttributionState();
      startTimer();
    }
  }
  
  private void startTimer()
  {
    stopTimer();
    if (!this.activityState.enabled) {}
    for (;;)
    {
      return;
      timer = Executors.newSingleThreadScheduledExecutor();
      timer.scheduleWithFixedDelay(new Runnable()
      {
        public void run()
        {
          ActivityHandler.this.timerFired();
        }
      }, TIMER_START, TIMER_INTERVAL, TimeUnit.MILLISECONDS);
    }
  }
  
  private void stopTimer()
  {
    if (timer != null)
    {
      timer.shutdown();
      timer = null;
    }
  }
  
  private void timerFired()
  {
    if (!this.activityState.enabled) {
      stopTimer();
    }
    for (;;)
    {
      return;
      this.packageHandler.sendFirstPackage();
      if (updateActivityState(System.currentTimeMillis())) {
        writeActivityState();
      }
    }
  }
  
  private boolean toPause()
  {
    if ((this.offline) || (!isEnabled())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private void trackEventInternal(AdjustEvent paramAdjustEvent)
  {
    if (!isEnabled()) {
      return;
    }
    if (checkEvent(paramAdjustEvent))
    {
      long l = System.currentTimeMillis();
      ActivityState localActivityState = this.activityState;
      localActivityState.eventCount += 1;
      updateActivityState(l);
      paramAdjustEvent = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, l).buildEventPackage(paramAdjustEvent);
      this.packageHandler.addPackage(paramAdjustEvent);
      if (!this.adjustConfig.eventBufferingEnabled.booleanValue()) {
        break label122;
      }
      this.logger.info("Buffered event %s", new Object[] { paramAdjustEvent.getSuffix() });
    }
    for (;;)
    {
      writeActivityState();
      break;
      break;
      label122:
      this.packageHandler.sendFirstPackage();
    }
  }
  
  private void transferSessionPackage(long paramLong)
  {
    ActivityPackage localActivityPackage = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, paramLong).buildSessionPackage();
    this.packageHandler.addPackage(localActivityPackage);
    this.packageHandler.sendFirstPackage();
  }
  
  private boolean trySetAttribution(AdjustAttribution paramAdjustAttribution, String paramString1, String paramString2)
  {
    boolean bool = true;
    if (paramString1.equals("tracker")) {
      paramAdjustAttribution.trackerName = paramString2;
    }
    for (;;)
    {
      return bool;
      if (paramString1.equals("campaign")) {
        paramAdjustAttribution.campaign = paramString2;
      } else if (paramString1.equals("adgroup")) {
        paramAdjustAttribution.adgroup = paramString2;
      } else if (paramString1.equals("creative")) {
        paramAdjustAttribution.creative = paramString2;
      } else {
        bool = false;
      }
    }
  }
  
  private boolean updateActivityState(long paramLong)
  {
    boolean bool = false;
    long l = paramLong - this.activityState.lastActivity;
    if (l > SESSION_INTERVAL) {
      return bool;
    }
    this.activityState.lastActivity = paramLong;
    if (l < 0L) {
      this.logger.error("Time travel!", new Object[0]);
    }
    for (;;)
    {
      bool = true;
      break;
      ActivityState localActivityState = this.activityState;
      localActivityState.sessionLength += l;
      localActivityState = this.activityState;
      localActivityState.timeSpent += l;
    }
  }
  
  private void updateAttributionHandlerStatus()
  {
    if (this.attributionHandler == null) {}
    for (;;)
    {
      return;
      if (toPause()) {
        this.attributionHandler.pauseSending();
      } else {
        this.attributionHandler.resumeSending();
      }
    }
  }
  
  private void updatePackageHandlerStatus()
  {
    if (this.packageHandler == null) {}
    for (;;)
    {
      return;
      if (toPause()) {
        this.packageHandler.pauseSending();
      } else {
        this.packageHandler.resumeSending();
      }
    }
  }
  
  private void updateStatus()
  {
    Message localMessage = Message.obtain();
    localMessage.arg1 = 72638;
    this.sessionHandler.sendMessage(localMessage);
  }
  
  private void updateStatusInternal()
  {
    updateAttributionHandlerStatus();
    updatePackageHandlerStatus();
  }
  
  private void writeActivityState()
  {
    Util.writeObject(this.activityState, this.adjustConfig.context, "AdjustIoActivityState", "Activity state");
  }
  
  private void writeAttribution()
  {
    Util.writeObject(this.attribution, this.adjustConfig.context, "AdjustAttribution", "Attribution");
  }
  
  public void finishedTrackingActivity(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {}
    for (;;)
    {
      return;
      Message localMessage = Message.obtain();
      localMessage.arg1 = 72635;
      localMessage.obj = paramJSONObject;
      this.sessionHandler.sendMessage(localMessage);
    }
  }
  
  public ActivityPackage getAttributionPackage()
  {
    long l = System.currentTimeMillis();
    return new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, l).buildAttributionPackage();
  }
  
  public void init(AdjustConfig paramAdjustConfig)
  {
    this.adjustConfig = paramAdjustConfig;
  }
  
  public boolean isEnabled()
  {
    if (this.activityState != null) {}
    for (boolean bool = this.activityState.enabled;; bool = this.enabled) {
      return bool;
    }
  }
  
  public void readOpenUrl(Uri paramUri, long paramLong)
  {
    Message localMessage = Message.obtain();
    localMessage.arg1 = 72636;
    localMessage.obj = new UrlClickTime(paramUri, paramLong);
    this.sessionHandler.sendMessage(localMessage);
  }
  
  public void sendReferrer(String paramString, long paramLong)
  {
    Message localMessage = Message.obtain();
    localMessage.arg1 = 72637;
    localMessage.obj = new ReferrerClickTime(paramString, paramLong);
    this.sessionHandler.sendMessage(localMessage);
  }
  
  public void setAskingAttribution(boolean paramBoolean)
  {
    this.activityState.askingAttribution = paramBoolean;
    writeActivityState();
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    if (paramBoolean == this.enabled) {
      if (paramBoolean) {
        this.logger.debug("Adjust already enabled", new Object[0]);
      }
    }
    for (;;)
    {
      return;
      this.logger.debug("Adjust already disabled", new Object[0]);
      continue;
      this.enabled = paramBoolean;
      if (this.activityState != null)
      {
        this.activityState.enabled = paramBoolean;
        writeActivityState();
      }
      if (paramBoolean)
      {
        if (toPause()) {
          this.logger.info("Package and attribution handler remain paused due to the SDK is offline", new Object[0]);
        }
        for (;;)
        {
          trackSubsessionStart();
          break;
          this.logger.info("Resuming package handler and attribution handler to enabled the SDK", new Object[0]);
        }
      }
      this.logger.info("Pausing package handler and attribution handler to disable the SDK", new Object[0]);
      trackSubsessionEnd();
    }
  }
  
  public void setOfflineMode(boolean paramBoolean)
  {
    if (paramBoolean == this.offline)
    {
      if (paramBoolean) {
        this.logger.debug("Adjust already in offline mode", new Object[0]);
      }
      for (;;)
      {
        return;
        this.logger.debug("Adjust already in online mode", new Object[0]);
      }
    }
    this.offline = paramBoolean;
    if (paramBoolean) {
      this.logger.info("Pausing package and attribution handler to put in offline mode", new Object[0]);
    }
    for (;;)
    {
      updateStatus();
      break;
      if (toPause()) {
        this.logger.info("Package and attribution handler remain paused because the SDK is disabled", new Object[0]);
      } else {
        this.logger.info("Resuming package handler and attribution handler to put in online mode", new Object[0]);
      }
    }
  }
  
  public void trackEvent(AdjustEvent paramAdjustEvent)
  {
    Message localMessage = Message.obtain();
    localMessage.arg1 = 72634;
    localMessage.obj = paramAdjustEvent;
    this.sessionHandler.sendMessage(localMessage);
  }
  
  public void trackSubsessionEnd()
  {
    Message localMessage = Message.obtain();
    localMessage.arg1 = 72633;
    this.sessionHandler.sendMessage(localMessage);
  }
  
  public void trackSubsessionStart()
  {
    Message localMessage = Message.obtain();
    localMessage.arg1 = 72632;
    this.sessionHandler.sendMessage(localMessage);
  }
  
  public boolean tryUpdateAttribution(AdjustAttribution paramAdjustAttribution)
  {
    boolean bool = false;
    if (paramAdjustAttribution == null) {}
    for (;;)
    {
      return bool;
      if (!paramAdjustAttribution.equals(this.attribution))
      {
        saveAttribution(paramAdjustAttribution);
        launchAttributionListener();
        bool = true;
      }
    }
  }
  
  private class ReferrerClickTime
  {
    long clickTime;
    String referrer;
    
    ReferrerClickTime(String paramString, long paramLong)
    {
      this.referrer = paramString;
      this.clickTime = paramLong;
    }
  }
  
  private static final class SessionHandler
    extends Handler
  {
    private static final int BASE_ADDRESS = 72630;
    private static final int DEEP_LINK = 72636;
    private static final int END = 72633;
    private static final int EVENT = 72634;
    private static final int FINISH_TRACKING = 72635;
    private static final int INIT = 72631;
    private static final int SEND_REFERRER = 72637;
    private static final int START = 72632;
    private static final int UPDATE_STATUS = 72638;
    private final WeakReference<ActivityHandler> sessionHandlerReference;
    
    protected SessionHandler(Looper paramLooper, ActivityHandler paramActivityHandler)
    {
      super();
      this.sessionHandlerReference = new WeakReference(paramActivityHandler);
    }
    
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      ActivityHandler localActivityHandler = (ActivityHandler)this.sessionHandlerReference.get();
      if (localActivityHandler == null) {}
      for (;;)
      {
        return;
        switch (paramMessage.arg1)
        {
        default: 
          break;
        case 72631: 
          localActivityHandler.initInternal();
          break;
        case 72632: 
          localActivityHandler.startInternal();
          break;
        case 72633: 
          localActivityHandler.endInternal();
          break;
        case 72634: 
          localActivityHandler.trackEventInternal((AdjustEvent)paramMessage.obj);
          break;
        case 72635: 
          localActivityHandler.finishedTrackingActivityInternal((JSONObject)paramMessage.obj);
          break;
        case 72636: 
          paramMessage = (ActivityHandler.UrlClickTime)paramMessage.obj;
          localActivityHandler.readOpenUrlInternal(paramMessage.url, paramMessage.clickTime);
          break;
        case 72637: 
          paramMessage = (ActivityHandler.ReferrerClickTime)paramMessage.obj;
          localActivityHandler.sendReferrerInternal(paramMessage.referrer, paramMessage.clickTime);
          break;
        case 72638: 
          localActivityHandler.updateStatusInternal();
        }
      }
    }
  }
  
  private class UrlClickTime
  {
    long clickTime;
    Uri url;
    
    UrlClickTime(Uri paramUri, long paramLong)
    {
      this.url = paramUri;
      this.clickTime = paramLong;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\adjust\sdk\ActivityHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */