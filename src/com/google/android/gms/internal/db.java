package com.google.android.gms.internal;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdRequest.Gender;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.a;
import java.util.Date;
import java.util.HashSet;

@ez
public final class db
{
  public static int a(AdRequest.ErrorCode paramErrorCode)
  {
    int i;
    switch (1.qL[paramErrorCode.ordinal()])
    {
    default: 
      i = 0;
    }
    for (;;)
    {
      return i;
      i = 1;
      continue;
      i = 2;
      continue;
      i = 3;
    }
  }
  
  public static AdSize b(ay paramay)
  {
    int i = 0;
    AdSize[] arrayOfAdSize = new AdSize[6];
    arrayOfAdSize[0] = AdSize.SMART_BANNER;
    arrayOfAdSize[1] = AdSize.BANNER;
    arrayOfAdSize[2] = AdSize.IAB_MRECT;
    arrayOfAdSize[3] = AdSize.IAB_BANNER;
    arrayOfAdSize[4] = AdSize.IAB_LEADERBOARD;
    arrayOfAdSize[5] = AdSize.IAB_WIDE_SKYSCRAPER;
    if (i < arrayOfAdSize.length) {
      if ((arrayOfAdSize[i].getWidth() != paramay.width) || (arrayOfAdSize[i].getHeight() != paramay.height)) {}
    }
    for (paramay = arrayOfAdSize[i];; paramay = new AdSize(a.a(paramay.width, paramay.height, paramay.of)))
    {
      return paramay;
      i++;
      break;
    }
  }
  
  public static MediationAdRequest d(av paramav)
  {
    if (paramav.nV != null) {}
    for (HashSet localHashSet = new HashSet(paramav.nV);; localHashSet = null) {
      return new MediationAdRequest(new Date(paramav.nT), k(paramav.nU), localHashSet, paramav.nW, paramav.ob);
    }
  }
  
  public static AdRequest.Gender k(int paramInt)
  {
    AdRequest.Gender localGender;
    switch (paramInt)
    {
    default: 
      localGender = AdRequest.Gender.UNKNOWN;
    }
    for (;;)
    {
      return localGender;
      localGender = AdRequest.Gender.FEMALE;
      continue;
      localGender = AdRequest.Gender.MALE;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\db.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */