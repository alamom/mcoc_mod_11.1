package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.View;

public class ActivityOptionsCompat
{
  public static ActivityOptionsCompat makeCustomAnimation(Context paramContext, int paramInt1, int paramInt2)
  {
    if (Build.VERSION.SDK_INT >= 16) {}
    for (paramContext = new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeCustomAnimation(paramContext, paramInt1, paramInt2));; paramContext = new ActivityOptionsCompat()) {
      return paramContext;
    }
  }
  
  public static ActivityOptionsCompat makeScaleUpAnimation(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (Build.VERSION.SDK_INT >= 16) {}
    for (paramView = new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeScaleUpAnimation(paramView, paramInt1, paramInt2, paramInt3, paramInt4));; paramView = new ActivityOptionsCompat()) {
      return paramView;
    }
  }
  
  public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity paramActivity, View paramView, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    for (paramActivity = new ActivityOptionsImpl21(ActivityOptionsCompat21.makeSceneTransitionAnimation(paramActivity, paramView, paramString));; paramActivity = new ActivityOptionsCompat()) {
      return paramActivity;
    }
  }
  
  public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity paramActivity, Pair<View, String>... paramVarArgs)
  {
    Object localObject2;
    Object localObject1;
    if (Build.VERSION.SDK_INT >= 21)
    {
      localObject2 = null;
      localObject1 = null;
      if (paramVarArgs != null)
      {
        View[] arrayOfView = new View[paramVarArgs.length];
        String[] arrayOfString = new String[paramVarArgs.length];
        for (int i = 0;; i++)
        {
          localObject1 = arrayOfString;
          localObject2 = arrayOfView;
          if (i >= paramVarArgs.length) {
            break;
          }
          arrayOfView[i] = ((View)paramVarArgs[i].first);
          arrayOfString[i] = ((String)paramVarArgs[i].second);
        }
      }
    }
    for (paramActivity = new ActivityOptionsImpl21(ActivityOptionsCompat21.makeSceneTransitionAnimation(paramActivity, (View[])localObject2, (String[])localObject1));; paramActivity = new ActivityOptionsCompat()) {
      return paramActivity;
    }
  }
  
  public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(View paramView, Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    if (Build.VERSION.SDK_INT >= 16) {}
    for (paramView = new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeThumbnailScaleUpAnimation(paramView, paramBitmap, paramInt1, paramInt2));; paramView = new ActivityOptionsCompat()) {
      return paramView;
    }
  }
  
  public Bundle toBundle()
  {
    return null;
  }
  
  public void update(ActivityOptionsCompat paramActivityOptionsCompat) {}
  
  private static class ActivityOptionsImpl21
    extends ActivityOptionsCompat
  {
    private final ActivityOptionsCompat21 mImpl;
    
    ActivityOptionsImpl21(ActivityOptionsCompat21 paramActivityOptionsCompat21)
    {
      this.mImpl = paramActivityOptionsCompat21;
    }
    
    public Bundle toBundle()
    {
      return this.mImpl.toBundle();
    }
    
    public void update(ActivityOptionsCompat paramActivityOptionsCompat)
    {
      if ((paramActivityOptionsCompat instanceof ActivityOptionsImpl21))
      {
        paramActivityOptionsCompat = (ActivityOptionsImpl21)paramActivityOptionsCompat;
        this.mImpl.update(paramActivityOptionsCompat.mImpl);
      }
    }
  }
  
  private static class ActivityOptionsImplJB
    extends ActivityOptionsCompat
  {
    private final ActivityOptionsCompatJB mImpl;
    
    ActivityOptionsImplJB(ActivityOptionsCompatJB paramActivityOptionsCompatJB)
    {
      this.mImpl = paramActivityOptionsCompatJB;
    }
    
    public Bundle toBundle()
    {
      return this.mImpl.toBundle();
    }
    
    public void update(ActivityOptionsCompat paramActivityOptionsCompat)
    {
      if ((paramActivityOptionsCompat instanceof ActivityOptionsImplJB))
      {
        paramActivityOptionsCompat = (ActivityOptionsImplJB)paramActivityOptionsCompat;
        this.mImpl.update(paramActivityOptionsCompat.mImpl);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\app\ActivityOptionsCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */