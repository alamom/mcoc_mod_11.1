package android.support.v4.widget;

import android.util.Log;
import android.widget.PopupWindow;
import java.lang.reflect.Field;

class PopupWindowCompatApi21
{
  private static final String TAG = "PopupWindowCompatApi21";
  private static Field sOverlapAnchorField;
  
  static
  {
    try
    {
      sOverlapAnchorField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
      sOverlapAnchorField.setAccessible(true);
      return;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;)
      {
        Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", localNoSuchFieldException);
      }
    }
  }
  
  static boolean getOverlapAnchor(PopupWindow paramPopupWindow)
  {
    if (sOverlapAnchorField != null) {}
    for (;;)
    {
      try
      {
        bool = ((Boolean)sOverlapAnchorField.get(paramPopupWindow)).booleanValue();
        return bool;
      }
      catch (IllegalAccessException paramPopupWindow)
      {
        Log.i("PopupWindowCompatApi21", "Could not get overlap anchor field in PopupWindow", paramPopupWindow);
      }
      boolean bool = false;
    }
  }
  
  static void setOverlapAnchor(PopupWindow paramPopupWindow, boolean paramBoolean)
  {
    if (sOverlapAnchorField != null) {}
    try
    {
      sOverlapAnchorField.set(paramPopupWindow, Boolean.valueOf(paramBoolean));
      return;
    }
    catch (IllegalAccessException paramPopupWindow)
    {
      for (;;)
      {
        Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", paramPopupWindow);
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\widget\PopupWindowCompatApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */