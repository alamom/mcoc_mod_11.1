package android.support.v4.text;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.Locale;

public class TextUtilsCompatJellybeanMr1
{
  public static int getLayoutDirectionFromLocale(@Nullable Locale paramLocale)
  {
    return TextUtils.getLayoutDirectionFromLocale(paramLocale);
  }
  
  @NonNull
  public static String htmlEncode(@NonNull String paramString)
  {
    return TextUtils.htmlEncode(paramString);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\text\TextUtilsCompatJellybeanMr1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */