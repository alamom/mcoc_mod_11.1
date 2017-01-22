package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.UserMetadata;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class m
  extends j<UserMetadata>
{
  public m(String paramString, int paramInt)
  {
    super(paramString, bm(paramString), Collections.emptyList(), paramInt);
  }
  
  private String bl(String paramString)
  {
    return r(getName(), paramString);
  }
  
  private static Collection<String> bm(String paramString)
  {
    return Arrays.asList(new String[] { r(paramString, "permissionId"), r(paramString, "displayName"), r(paramString, "picture"), r(paramString, "isAuthenticatedUser"), r(paramString, "emailAddress") });
  }
  
  private static String r(String paramString1, String paramString2)
  {
    return paramString1 + "." + paramString2;
  }
  
  protected boolean b(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    if (!paramDataHolder.h(bl("permissionId"), paramInt1, paramInt2)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected UserMetadata j(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    String str2 = paramDataHolder.c(bl("permissionId"), paramInt1, paramInt2);
    String str1;
    String str3;
    boolean bool;
    if (str2 != null)
    {
      str1 = paramDataHolder.c(bl("displayName"), paramInt1, paramInt2);
      str3 = paramDataHolder.c(bl("picture"), paramInt1, paramInt2);
      bool = paramDataHolder.d(bl("isAuthenticatedUser"), paramInt1, paramInt2);
      paramDataHolder = paramDataHolder.c(bl("emailAddress"), paramInt1, paramInt2);
    }
    for (paramDataHolder = new UserMetadata(str2, str1, str3, Boolean.valueOf(bool).booleanValue(), paramDataHolder);; paramDataHolder = null) {
      return paramDataHolder;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\drive\metadata\internal\m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */