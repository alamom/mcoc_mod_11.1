package android.support.v4.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ResourcesCompat
{
  @Nullable
  public static Drawable getDrawable(@NonNull Resources paramResources, @DrawableRes int paramInt, @Nullable Resources.Theme paramTheme)
    throws Resources.NotFoundException
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    for (paramResources = ResourcesCompatApi21.getDrawable(paramResources, paramInt, paramTheme);; paramResources = paramResources.getDrawable(paramInt)) {
      return paramResources;
    }
  }
  
  @Nullable
  public static Drawable getDrawableForDensity(@NonNull Resources paramResources, @DrawableRes int paramInt1, int paramInt2, @Nullable Resources.Theme paramTheme)
    throws Resources.NotFoundException
  {
    if (Build.VERSION.SDK_INT >= 21) {
      paramResources = ResourcesCompatApi21.getDrawableForDensity(paramResources, paramInt1, paramInt2, paramTheme);
    }
    for (;;)
    {
      return paramResources;
      if (Build.VERSION.SDK_INT >= 15) {
        paramResources = ResourcesCompatIcsMr1.getDrawableForDensity(paramResources, paramInt1, paramInt2);
      } else {
        paramResources = paramResources.getDrawable(paramInt1);
      }
    }
  }
  
  @ColorInt
  public int getColor(@NonNull Resources paramResources, @ColorRes int paramInt, @Nullable Resources.Theme paramTheme)
    throws Resources.NotFoundException
  {
    if (Build.VERSION.SDK_INT >= 23) {}
    for (paramInt = ResourcesCompatApi23.getColor(paramResources, paramInt, paramTheme);; paramInt = paramResources.getColor(paramInt)) {
      return paramInt;
    }
  }
  
  @Nullable
  public ColorStateList getColorStateList(@NonNull Resources paramResources, @ColorRes int paramInt, @Nullable Resources.Theme paramTheme)
    throws Resources.NotFoundException
  {
    if (Build.VERSION.SDK_INT >= 23) {}
    for (paramResources = ResourcesCompatApi23.getColorStateList(paramResources, paramInt, paramTheme);; paramResources = paramResources.getColorStateList(paramInt)) {
      return paramResources;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\content\res\ResourcesCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */