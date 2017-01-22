package android.support.v4.view.animation;

import android.graphics.Path;
import android.os.Build.VERSION;
import android.view.animation.Interpolator;

public class PathInterpolatorCompat
{
  public static Interpolator create(float paramFloat1, float paramFloat2)
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    for (Interpolator localInterpolator = PathInterpolatorCompatApi21.create(paramFloat1, paramFloat2);; localInterpolator = PathInterpolatorCompatBase.create(paramFloat1, paramFloat2)) {
      return localInterpolator;
    }
  }
  
  public static Interpolator create(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    for (Interpolator localInterpolator = PathInterpolatorCompatApi21.create(paramFloat1, paramFloat2, paramFloat3, paramFloat4);; localInterpolator = PathInterpolatorCompatBase.create(paramFloat1, paramFloat2, paramFloat3, paramFloat4)) {
      return localInterpolator;
    }
  }
  
  public static Interpolator create(Path paramPath)
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    for (paramPath = PathInterpolatorCompatApi21.create(paramPath);; paramPath = PathInterpolatorCompatBase.create(paramPath)) {
      return paramPath;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\view\animation\PathInterpolatorCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */