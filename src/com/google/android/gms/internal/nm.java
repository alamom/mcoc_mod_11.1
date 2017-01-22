package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class nm
  implements SafeParcelable
{
  public static final nn CREATOR = new nn();
  public final int akR;
  public final int akS;
  public final String akT;
  public final String akU;
  public final boolean akV;
  public final String packageName;
  public final int versionCode;
  
  public nm(int paramInt1, String paramString1, int paramInt2, int paramInt3, String paramString2, String paramString3, boolean paramBoolean)
  {
    this.versionCode = paramInt1;
    this.packageName = paramString1;
    this.akR = paramInt2;
    this.akS = paramInt3;
    this.akT = paramString2;
    this.akU = paramString3;
    this.akV = paramBoolean;
  }
  
  public nm(String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3, boolean paramBoolean)
  {
    this.versionCode = 1;
    this.packageName = ((String)o.i(paramString1));
    this.akR = paramInt1;
    this.akS = paramInt2;
    this.akT = paramString2;
    this.akU = paramString3;
    this.akV = paramBoolean;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    for (;;)
    {
      return bool;
      if ((paramObject instanceof nm))
      {
        paramObject = (nm)paramObject;
        if ((!this.packageName.equals(((nm)paramObject).packageName)) || (this.akR != ((nm)paramObject).akR) || (this.akS != ((nm)paramObject).akS) || (!n.equal(this.akT, ((nm)paramObject).akT)) || (!n.equal(this.akU, ((nm)paramObject).akU)) || (this.akV != ((nm)paramObject).akV)) {
          bool = false;
        }
      }
      else
      {
        bool = false;
      }
    }
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.packageName, Integer.valueOf(this.akR), Integer.valueOf(this.akS), this.akT, this.akU, Boolean.valueOf(this.akV) });
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PlayLoggerContext[");
    localStringBuilder.append("package=").append(this.packageName).append(',');
    localStringBuilder.append("versionCode=").append(this.versionCode).append(',');
    localStringBuilder.append("logSource=").append(this.akS).append(',');
    localStringBuilder.append("uploadAccount=").append(this.akT).append(',');
    localStringBuilder.append("loggingId=").append(this.akU).append(',');
    localStringBuilder.append("logAndroidId=").append(this.akV);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    nn.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\nm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */