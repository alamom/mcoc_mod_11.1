package com.google.android.gms.internal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class ao
{
  private static MessageDigest nI = null;
  protected Object mw = new Object();
  
  protected MessageDigest ba()
  {
    for (;;)
    {
      MessageDigest localMessageDigest;
      int i;
      synchronized (this.mw)
      {
        if (nI != null)
        {
          localMessageDigest = nI;
          return localMessageDigest;
        }
        i = 0;
        if (i >= 2) {}
      }
      try
      {
        nI = MessageDigest.getInstance("MD5");
        i++;
        continue;
        localMessageDigest = nI;
        continue;
        localObject1 = finally;
        throw ((Throwable)localObject1);
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        for (;;) {}
      }
    }
  }
  
  abstract byte[] l(String paramString);
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */