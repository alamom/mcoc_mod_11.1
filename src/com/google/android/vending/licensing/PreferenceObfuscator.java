package com.google.android.vending.licensing;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class PreferenceObfuscator
{
  private static final String TAG = "PreferenceObfuscator";
  private SharedPreferences.Editor mEditor;
  private final Obfuscator mObfuscator;
  private final SharedPreferences mPreferences;
  
  public PreferenceObfuscator(SharedPreferences paramSharedPreferences, Obfuscator paramObfuscator)
  {
    this.mPreferences = paramSharedPreferences;
    this.mObfuscator = paramObfuscator;
    this.mEditor = null;
  }
  
  public void commit()
  {
    if (this.mEditor != null)
    {
      this.mEditor.commit();
      this.mEditor = null;
    }
  }
  
  public String getString(String paramString1, String paramString2)
  {
    String str = this.mPreferences.getString(paramString1, null);
    if (str != null) {}
    for (;;)
    {
      try
      {
        str = this.mObfuscator.unobfuscate(str, paramString1);
        paramString1 = str;
      }
      catch (ValidationException localValidationException)
      {
        Log.w("PreferenceObfuscator", "Validation error while reading preference: " + paramString1);
        paramString1 = paramString2;
        continue;
      }
      return paramString1;
      paramString1 = paramString2;
    }
  }
  
  public void putString(String paramString1, String paramString2)
  {
    if (this.mEditor == null) {
      this.mEditor = this.mPreferences.edit();
    }
    paramString2 = this.mObfuscator.obfuscate(paramString2, paramString1);
    this.mEditor.putString(paramString1, paramString2);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\vending\licensing\PreferenceObfuscator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */