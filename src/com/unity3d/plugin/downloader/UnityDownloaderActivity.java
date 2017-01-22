package com.unity3d.plugin.downloader;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.vending.expansion.downloader.DownloadProgressInfo;
import com.google.android.vending.expansion.downloader.DownloaderClientMarshaller;
import com.google.android.vending.expansion.downloader.DownloaderServiceMarshaller;
import com.google.android.vending.expansion.downloader.Helpers;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.google.android.vending.expansion.downloader.IDownloaderService;
import com.google.android.vending.expansion.downloader.IStub;
import java.io.InputStream;

public class UnityDownloaderActivity
  extends Activity
  implements IDownloaderClient
{
  private static final String LOG_TAG = "OBB";
  private TextView mAverageSpeed;
  private View mCellMessage;
  private View mDashboard;
  private IStub mDownloaderClientStub;
  private ProgressBar mPB;
  private Button mPauseButton;
  private TextView mProgressFraction;
  private TextView mProgressPercent;
  private IDownloaderService mRemoteService;
  private int mState;
  private boolean mStatePaused;
  private TextView mStatusText;
  private TextView mTimeRemaining;
  private Button mWiFiSettingsButton;
  
  private void initializeDownloadUI()
  {
    this.mDownloaderClientStub = DownloaderClientMarshaller.CreateStub(this, UnityDownloaderService.class);
    setContentView(Helpers.getLayoutResource(this, "main"));
    try
    {
      InputStream localInputStream = getAssets().open("bin/Data/splash.png");
      Object localObject = new android/graphics/BitmapFactory$Options;
      ((BitmapFactory.Options)localObject).<init>();
      ((BitmapFactory.Options)localObject).inPreferredConfig = Bitmap.Config.ARGB_8888;
      localObject = BitmapFactory.decodeStream(localInputStream, null, (BitmapFactory.Options)localObject);
      localInputStream.close();
      ((ImageView)findViewById(Helpers.getIdResource(this, "splashImage"))).setImageBitmap((Bitmap)localObject);
      this.mPB = ((ProgressBar)findViewById(Helpers.getIdResource(this, "progressBar")));
      this.mStatusText = ((TextView)findViewById(Helpers.getIdResource(this, "statusText")));
      this.mProgressFraction = ((TextView)findViewById(Helpers.getIdResource(this, "progressAsFraction")));
      this.mProgressPercent = ((TextView)findViewById(Helpers.getIdResource(this, "progressAsPercentage")));
      this.mAverageSpeed = ((TextView)findViewById(Helpers.getIdResource(this, "progressAverageSpeed")));
      this.mTimeRemaining = ((TextView)findViewById(Helpers.getIdResource(this, "progressTimeRemaining")));
      this.mDashboard = findViewById(Helpers.getIdResource(this, "downloaderDashboard"));
      this.mCellMessage = findViewById(Helpers.getIdResource(this, "approveCellular"));
      this.mPauseButton = ((Button)findViewById(Helpers.getIdResource(this, "pauseButton")));
      this.mWiFiSettingsButton = ((Button)findViewById(Helpers.getIdResource(this, "wifiSettingsButton")));
      this.mPauseButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (UnityDownloaderActivity.this.mStatePaused)
          {
            UnityDownloaderActivity.this.mRemoteService.requestContinueDownload();
            paramAnonymousView = UnityDownloaderActivity.this;
            if (UnityDownloaderActivity.this.mStatePaused) {
              break label60;
            }
          }
          label60:
          for (boolean bool = true;; bool = false)
          {
            paramAnonymousView.setButtonPausedState(bool);
            return;
            UnityDownloaderActivity.this.mRemoteService.requestPauseDownload();
            break;
          }
        }
      });
      this.mWiFiSettingsButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          UnityDownloaderActivity.this.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
        }
      });
      ((Button)findViewById(Helpers.getIdResource(this, "resumeOverCellular"))).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          UnityDownloaderActivity.this.mRemoteService.setDownloadFlags(1);
          UnityDownloaderActivity.this.mRemoteService.requestContinueDownload();
          UnityDownloaderActivity.this.mCellMessage.setVisibility(8);
        }
      });
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  private void setButtonPausedState(boolean paramBoolean)
  {
    this.mStatePaused = paramBoolean;
    if (paramBoolean) {}
    for (String str = "text_button_resume";; str = "text_button_pause")
    {
      int i = Helpers.getStringResource(this, str);
      this.mPauseButton.setText(i);
      return;
    }
  }
  
  private void setState(int paramInt)
  {
    if (this.mState != paramInt)
    {
      this.mState = paramInt;
      this.mStatusText.setText(Helpers.getDownloaderStringResourceIDFromState(this, paramInt));
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    try
    {
      paramBundle = Class.forName(getIntent().getStringExtra("unityplayer.Activity"));
      Intent localIntent = new android/content/Intent;
      localIntent.<init>(this, paramBundle);
      localIntent.setFlags(270532608);
      localIntent.setAction("android.intent.action.MAIN");
      localIntent.addCategory("android.intent.category.LAUNCHER");
      if (DownloaderClientMarshaller.startDownloadServiceIfRequired(this, PendingIntent.getActivity(this, 0, localIntent, 134217728), UnityDownloaderService.class) != 0)
      {
        initializeDownloadUI();
        return;
      }
    }
    catch (ClassNotFoundException paramBundle)
    {
      for (;;)
      {
        Log.e("OBB", "Cannot find own package! MAYDAY!");
        paramBundle.printStackTrace();
        finish();
      }
    }
    catch (PackageManager.NameNotFoundException paramBundle)
    {
      for (;;)
      {
        Log.e("OBB", "Cannot find own package! MAYDAY!");
        paramBundle.printStackTrace();
      }
    }
  }
  
  public void onDownloadProgress(DownloadProgressInfo paramDownloadProgressInfo)
  {
    this.mAverageSpeed.setText(getString(Helpers.getStringResource(this, "kilobytes_per_second"), new Object[] { Helpers.getSpeedString(paramDownloadProgressInfo.mCurrentSpeed) }));
    this.mTimeRemaining.setText(getString(Helpers.getStringResource(this, "time_remaining"), new Object[] { Helpers.getTimeRemaining(paramDownloadProgressInfo.mTimeRemaining) }));
    paramDownloadProgressInfo.mOverallTotal = paramDownloadProgressInfo.mOverallTotal;
    this.mPB.setMax((int)(paramDownloadProgressInfo.mOverallTotal >> 8));
    this.mPB.setProgress((int)(paramDownloadProgressInfo.mOverallProgress >> 8));
    this.mProgressPercent.setText(Long.toString(paramDownloadProgressInfo.mOverallProgress * 100L / paramDownloadProgressInfo.mOverallTotal) + "%");
    this.mProgressFraction.setText(Helpers.getDownloadProgressString(paramDownloadProgressInfo.mOverallProgress, paramDownloadProgressInfo.mOverallTotal));
  }
  
  public void onDownloadStateChanged(int paramInt)
  {
    setState(paramInt);
    int j = 1;
    int i = 0;
    boolean bool2;
    boolean bool1;
    switch (paramInt)
    {
    case 6: 
    case 10: 
    case 11: 
    case 13: 
    case 17: 
    default: 
      bool2 = true;
      bool1 = true;
      paramInt = 1;
      if (paramInt != 0)
      {
        paramInt = 0;
        label114:
        if (this.mDashboard.getVisibility() != paramInt) {
          this.mDashboard.setVisibility(paramInt);
        }
        if (i == 0) {
          break label266;
        }
      }
      break;
    }
    label266:
    for (paramInt = 0;; paramInt = 8)
    {
      if (this.mCellMessage.getVisibility() != paramInt) {
        this.mCellMessage.setVisibility(paramInt);
      }
      this.mPB.setIndeterminate(bool1);
      setButtonPausedState(bool2);
      for (;;)
      {
        return;
        bool2 = false;
        bool1 = true;
        paramInt = j;
        break;
        paramInt = 1;
        bool2 = false;
        bool1 = true;
        break;
        bool2 = false;
        paramInt = 1;
        bool1 = false;
        break;
        bool2 = true;
        paramInt = 0;
        bool1 = false;
        break;
        paramInt = 0;
        bool2 = true;
        bool1 = false;
        i = 1;
        break;
        bool2 = true;
        bool1 = false;
        paramInt = j;
        break;
        bool2 = true;
        bool1 = false;
        paramInt = j;
        break;
        finish();
      }
      paramInt = 8;
      break label114;
    }
  }
  
  protected void onResume()
  {
    if (this.mDownloaderClientStub != null) {
      this.mDownloaderClientStub.connect(this);
    }
    super.onResume();
  }
  
  public void onServiceConnected(Messenger paramMessenger)
  {
    this.mRemoteService = DownloaderServiceMarshaller.CreateProxy(paramMessenger);
    this.mRemoteService.onClientUpdated(this.mDownloaderClientStub.getMessenger());
  }
  
  protected void onStop()
  {
    if (this.mDownloaderClientStub != null) {
      this.mDownloaderClientStub.disconnect(this);
    }
    super.onStop();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\unity3d\plugin\downloader\UnityDownloaderActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */