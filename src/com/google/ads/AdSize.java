package com.google.ads;

import android.content.Context;

@Deprecated
public final class AdSize
{
  public static final int AUTO_HEIGHT = -2;
  public static final AdSize BANNER;
  public static final int FULL_WIDTH = -1;
  public static final AdSize IAB_BANNER = new AdSize(468, 60, "as");
  public static final AdSize IAB_LEADERBOARD = new AdSize(728, 90, "as");
  public static final AdSize IAB_MRECT;
  public static final AdSize IAB_WIDE_SKYSCRAPER = new AdSize(160, 600, "as");
  public static final int LANDSCAPE_AD_HEIGHT = 32;
  public static final int LARGE_AD_HEIGHT = 90;
  public static final int PORTRAIT_AD_HEIGHT = 50;
  public static final AdSize SMART_BANNER = new AdSize(-1, -2, "mb");
  private final com.google.android.gms.ads.AdSize c;
  
  static
  {
    BANNER = new AdSize(320, 50, "mb");
    IAB_MRECT = new AdSize(300, 250, "as");
  }
  
  public AdSize(int paramInt1, int paramInt2)
  {
    this(new com.google.android.gms.ads.AdSize(paramInt1, paramInt2));
  }
  
  private AdSize(int paramInt1, int paramInt2, String paramString)
  {
    this(new com.google.android.gms.ads.AdSize(paramInt1, paramInt2));
  }
  
  public AdSize(com.google.android.gms.ads.AdSize paramAdSize)
  {
    this.c = paramAdSize;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof AdSize)) {}
    for (boolean bool = false;; bool = this.c.equals(((AdSize)paramObject).c))
    {
      return bool;
      paramObject = (AdSize)paramObject;
    }
  }
  
  public AdSize findBestSize(AdSize... paramVarArgs)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    if (paramVarArgs == null) {}
    float f1;
    int k;
    int m;
    int j;
    int i;
    do
    {
      return (AdSize)localObject2;
      f1 = 0.0F;
      k = getWidth();
      m = getHeight();
      j = paramVarArgs.length;
      i = 0;
      localObject2 = localObject1;
    } while (i >= j);
    localObject2 = paramVarArgs[i];
    int i1 = ((AdSize)localObject2).getWidth();
    int n = ((AdSize)localObject2).getHeight();
    float f2;
    if (isSizeAppropriate(i1, n))
    {
      float f3 = i1 * n / (k * m);
      f2 = f3;
      if (f3 > 1.0F) {
        f2 = 1.0F / f3;
      }
      if (f2 > f1) {
        localObject1 = localObject2;
      }
    }
    for (;;)
    {
      i++;
      f1 = f2;
      break;
      f2 = f1;
    }
  }
  
  public int getHeight()
  {
    return this.c.getHeight();
  }
  
  public int getHeightInPixels(Context paramContext)
  {
    return this.c.getHeightInPixels(paramContext);
  }
  
  public int getWidth()
  {
    return this.c.getWidth();
  }
  
  public int getWidthInPixels(Context paramContext)
  {
    return this.c.getWidthInPixels(paramContext);
  }
  
  public int hashCode()
  {
    return this.c.hashCode();
  }
  
  public boolean isAutoHeight()
  {
    return this.c.isAutoHeight();
  }
  
  public boolean isCustomAdSize()
  {
    return false;
  }
  
  public boolean isFullWidth()
  {
    return this.c.isFullWidth();
  }
  
  public boolean isSizeAppropriate(int paramInt1, int paramInt2)
  {
    int j = getWidth();
    int i = getHeight();
    if ((paramInt1 <= j * 1.25F) && (paramInt1 >= j * 0.8F) && (paramInt2 <= i * 1.25F) && (paramInt2 >= i * 0.8F)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public String toString()
  {
    return this.c.toString();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\ads\AdSize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */