package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

class CompoundButtonCompatDonut
{
  private static final String TAG = "CompoundButtonCompatDonut";
  private static Field sButtonDrawableField;
  private static boolean sButtonDrawableFieldFetched;
  
  static Drawable getButtonDrawable(CompoundButton paramCompoundButton)
  {
    if (!sButtonDrawableFieldFetched) {}
    try
    {
      sButtonDrawableField = CompoundButton.class.getDeclaredField("mButtonDrawable");
      sButtonDrawableField.setAccessible(true);
      sButtonDrawableFieldFetched = true;
      if (sButtonDrawableField == null) {}
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;)
      {
        try
        {
          paramCompoundButton = (Drawable)sButtonDrawableField.get(paramCompoundButton);
          return paramCompoundButton;
        }
        catch (IllegalAccessException paramCompoundButton)
        {
          Log.i("CompoundButtonCompatDonut", "Failed to get button drawable via reflection", paramCompoundButton);
          sButtonDrawableField = null;
        }
        localNoSuchFieldException = localNoSuchFieldException;
        Log.i("CompoundButtonCompatDonut", "Failed to retrieve mButtonDrawable field", localNoSuchFieldException);
        continue;
        paramCompoundButton = null;
      }
    }
  }
  
  static ColorStateList getButtonTintList(CompoundButton paramCompoundButton)
  {
    if ((paramCompoundButton instanceof TintableCompoundButton)) {}
    for (paramCompoundButton = ((TintableCompoundButton)paramCompoundButton).getSupportButtonTintList();; paramCompoundButton = null) {
      return paramCompoundButton;
    }
  }
  
  static PorterDuff.Mode getButtonTintMode(CompoundButton paramCompoundButton)
  {
    if ((paramCompoundButton instanceof TintableCompoundButton)) {}
    for (paramCompoundButton = ((TintableCompoundButton)paramCompoundButton).getSupportButtonTintMode();; paramCompoundButton = null) {
      return paramCompoundButton;
    }
  }
  
  static void setButtonTintList(CompoundButton paramCompoundButton, ColorStateList paramColorStateList)
  {
    if ((paramCompoundButton instanceof TintableCompoundButton)) {
      ((TintableCompoundButton)paramCompoundButton).setSupportButtonTintList(paramColorStateList);
    }
  }
  
  static void setButtonTintMode(CompoundButton paramCompoundButton, PorterDuff.Mode paramMode)
  {
    if ((paramCompoundButton instanceof TintableCompoundButton)) {
      ((TintableCompoundButton)paramCompoundButton).setSupportButtonTintMode(paramMode);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\widget\CompoundButtonCompatDonut.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */