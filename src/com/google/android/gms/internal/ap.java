package com.google.android.gms.internal;

import android.util.Base64OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class ap
{
  private final int nJ;
  private final int nK;
  private final ao nL = new ar();
  private Base64OutputStream nM;
  private ByteArrayOutputStream nN;
  
  public ap(int paramInt)
  {
    this.nK = paramInt;
    this.nJ = 6;
  }
  
  private String m(String paramString)
  {
    String[] arrayOfString = paramString.split("\n");
    if ((arrayOfString == null) || (arrayOfString.length == 0)) {
      paramString = "";
    }
    for (;;)
    {
      return paramString;
      this.nN = new ByteArrayOutputStream();
      this.nM = new Base64OutputStream(this.nN, 10);
      Arrays.sort(arrayOfString, new Comparator()
      {
        public int compare(String paramAnonymousString1, String paramAnonymousString2)
        {
          return paramAnonymousString2.length() - paramAnonymousString1.length();
        }
      });
      int i = 0;
      if ((i < arrayOfString.length) && (i < this.nK))
      {
        if (arrayOfString[i].trim().length() == 0) {}
        for (;;)
        {
          i++;
          break;
          try
          {
            this.nM.write(this.nL.l(arrayOfString[i]));
          }
          catch (IOException paramString)
          {
            gs.b("Error while writing hash to byteStream", paramString);
          }
        }
      }
      try
      {
        this.nM.flush();
        this.nM.close();
        paramString = this.nN.toString();
      }
      catch (IOException paramString)
      {
        gs.b("HashManager: Unable to convert to base 64", paramString);
        paramString = "";
      }
    }
  }
  
  public String a(ArrayList<String> paramArrayList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      localStringBuffer.append(((String)paramArrayList.next()).toLowerCase());
      localStringBuffer.append('\n');
    }
    switch (0)
    {
    default: 
      paramArrayList = "";
    }
    for (;;)
    {
      return paramArrayList;
      paramArrayList = n(localStringBuffer.toString());
      continue;
      paramArrayList = m(localStringBuffer.toString());
    }
  }
  
  String n(String paramString)
  {
    Object localObject = paramString.split("\n");
    if ((localObject == null) || (localObject.length == 0)) {
      paramString = "";
    }
    for (;;)
    {
      return paramString;
      this.nN = new ByteArrayOutputStream();
      this.nM = new Base64OutputStream(this.nN, 10);
      paramString = new PriorityQueue(this.nK, new Comparator()
      {
        public int a(as.a paramAnonymousa1, as.a paramAnonymousa2)
        {
          return (int)(paramAnonymousa1.value - paramAnonymousa2.value);
        }
      });
      int i = 0;
      if (i < localObject.length)
      {
        String[] arrayOfString = aq.p(localObject[i]);
        if (arrayOfString.length < this.nJ) {}
        for (;;)
        {
          i++;
          break;
          as.a(arrayOfString, this.nK, this.nJ, paramString);
        }
      }
      paramString = paramString.iterator();
      while (paramString.hasNext())
      {
        localObject = (as.a)paramString.next();
        try
        {
          this.nM.write(this.nL.l(((as.a)localObject).nQ));
        }
        catch (IOException localIOException)
        {
          gs.b("Error while writing hash to byteStream", localIOException);
        }
      }
      try
      {
        this.nM.flush();
        this.nM.close();
        paramString = this.nN.toString();
      }
      catch (IOException paramString)
      {
        gs.b("HashManager: unable to convert to base 64", paramString);
        paramString = "";
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\ap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */