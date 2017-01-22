package com.unity3d.plugin.downloader;

import com.google.android.vending.expansion.downloader.impl.DownloaderService;

public class UnityDownloaderService
  extends DownloaderService
{
  static String BASE64_PUBLIC_KEY = "REPLACE THIS WITH YOUR PUBLIC KEY - DONE FROM C#";
  static byte[] SALT = { 1, 43, -12, -1, 54, 98, -100, -12, 43, 2, -8, -4, 9, 5, -106, -108, -33, 45, -1, 84 };
  
  public String getAlarmReceiverClassName()
  {
    return UnityAlarmReceiver.class.getName();
  }
  
  public String getPublicKey()
  {
    return BASE64_PUBLIC_KEY;
  }
  
  public byte[] getSALT()
  {
    return SALT;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\unity3d\plugin\downloader\UnityDownloaderService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */