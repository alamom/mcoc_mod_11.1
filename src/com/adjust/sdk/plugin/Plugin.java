package com.adjust.sdk.plugin;

import android.content.Context;
import java.util.Map.Entry;

public abstract interface Plugin
{
  public abstract Map.Entry<String, String> getParameter(Context paramContext);
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\adjust\sdk\plugin\Plugin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */