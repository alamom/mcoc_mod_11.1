package com.google.android.gms.plus;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class PlusOneDummyView
  extends FrameLayout
{
  public static final String TAG = "PlusOneDummyView";
  
  public PlusOneDummyView(Context paramContext, int paramInt)
  {
    super(paramContext);
    paramContext = new Button(paramContext);
    paramContext.setEnabled(false);
    paramContext.setBackgroundDrawable(na().getDrawable(paramInt));
    Point localPoint = eQ(paramInt);
    addView(paramContext, new FrameLayout.LayoutParams(localPoint.x, localPoint.y, 17));
  }
  
  private Point eQ(int paramInt)
  {
    int j = 24;
    int i = 20;
    Point localPoint = new Point();
    switch (paramInt)
    {
    default: 
      paramInt = 38;
      i = 24;
    }
    for (;;)
    {
      DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
      float f2 = TypedValue.applyDimension(1, paramInt, localDisplayMetrics);
      float f1 = TypedValue.applyDimension(1, i, localDisplayMetrics);
      localPoint.x = ((int)(f2 + 0.5D));
      localPoint.y = ((int)(f1 + 0.5D));
      return localPoint;
      paramInt = 32;
      continue;
      i = 14;
      paramInt = j;
      continue;
      paramInt = 50;
    }
  }
  
  private d na()
  {
    Object localObject2 = new b(getContext(), null);
    Object localObject1 = localObject2;
    if (!((d)localObject2).isValid()) {
      localObject1 = new c(getContext(), null);
    }
    localObject2 = localObject1;
    if (!((d)localObject1).isValid()) {
      localObject2 = new a(getContext(), null);
    }
    return (d)localObject2;
  }
  
  private static class a
    implements PlusOneDummyView.d
  {
    private Context mContext;
    
    private a(Context paramContext)
    {
      this.mContext = paramContext;
    }
    
    public Drawable getDrawable(int paramInt)
    {
      return this.mContext.getResources().getDrawable(17301508);
    }
    
    public boolean isValid()
    {
      return true;
    }
  }
  
  private static class b
    implements PlusOneDummyView.d
  {
    private Context mContext;
    
    private b(Context paramContext)
    {
      this.mContext = paramContext;
    }
    
    public Drawable getDrawable(int paramInt)
    {
      for (;;)
      {
        try
        {
          Resources localResources = this.mContext.createPackageContext("com.google.android.gms", 4).getResources();
          switch (paramInt)
          {
          default: 
            localObject1 = "ic_plusone_standard";
            localObject1 = localResources.getDrawable(localResources.getIdentifier((String)localObject1, "drawable", "com.google.android.gms"));
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          Object localObject1;
          Object localObject2 = null;
          continue;
        }
        return (Drawable)localObject1;
        localObject1 = "ic_plusone_small";
        continue;
        localObject1 = "ic_plusone_medium";
        continue;
        localObject1 = "ic_plusone_tall";
      }
    }
    
    public boolean isValid()
    {
      try
      {
        this.mContext.createPackageContext("com.google.android.gms", 4).getResources();
        bool = true;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          boolean bool = false;
        }
      }
      return bool;
    }
  }
  
  private static class c
    implements PlusOneDummyView.d
  {
    private Context mContext;
    
    private c(Context paramContext)
    {
      this.mContext = paramContext;
    }
    
    public Drawable getDrawable(int paramInt)
    {
      String str;
      switch (paramInt)
      {
      default: 
        str = "ic_plusone_standard_off_client";
      }
      for (;;)
      {
        paramInt = this.mContext.getResources().getIdentifier(str, "drawable", this.mContext.getPackageName());
        return this.mContext.getResources().getDrawable(paramInt);
        str = "ic_plusone_small_off_client";
        continue;
        str = "ic_plusone_medium_off_client";
        continue;
        str = "ic_plusone_tall_off_client";
      }
    }
    
    public boolean isValid()
    {
      int m = this.mContext.getResources().getIdentifier("ic_plusone_small_off_client", "drawable", this.mContext.getPackageName());
      int k = this.mContext.getResources().getIdentifier("ic_plusone_medium_off_client", "drawable", this.mContext.getPackageName());
      int j = this.mContext.getResources().getIdentifier("ic_plusone_tall_off_client", "drawable", this.mContext.getPackageName());
      int i = this.mContext.getResources().getIdentifier("ic_plusone_standard_off_client", "drawable", this.mContext.getPackageName());
      if ((m != 0) && (k != 0) && (j != 0) && (i != 0)) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  }
  
  private static abstract interface d
  {
    public abstract Drawable getDrawable(int paramInt);
    
    public abstract boolean isValid();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\plus\PlusOneDummyView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */