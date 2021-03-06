package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import org.json.JSONException;
import org.json.JSONObject;

public final class WebImage
  implements SafeParcelable
{
  public static final Parcelable.Creator<WebImage> CREATOR = new b();
  private final int BR;
  private final Uri KQ;
  private final int lf;
  private final int lg;
  
  WebImage(int paramInt1, Uri paramUri, int paramInt2, int paramInt3)
  {
    this.BR = paramInt1;
    this.KQ = paramUri;
    this.lf = paramInt2;
    this.lg = paramInt3;
  }
  
  public WebImage(Uri paramUri)
    throws IllegalArgumentException
  {
    this(paramUri, 0, 0);
  }
  
  public WebImage(Uri paramUri, int paramInt1, int paramInt2)
    throws IllegalArgumentException
  {
    this(1, paramUri, paramInt1, paramInt2);
    if (paramUri == null) {
      throw new IllegalArgumentException("url cannot be null");
    }
    if ((paramInt1 < 0) || (paramInt2 < 0)) {
      throw new IllegalArgumentException("width and height must not be negative");
    }
  }
  
  public WebImage(JSONObject paramJSONObject)
    throws IllegalArgumentException
  {
    this(d(paramJSONObject), paramJSONObject.optInt("width", 0), paramJSONObject.optInt("height", 0));
  }
  
  private static Uri d(JSONObject paramJSONObject)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramJSONObject.has("url")) {}
    try
    {
      localObject1 = Uri.parse(paramJSONObject.getString("url"));
      return (Uri)localObject1;
    }
    catch (JSONException paramJSONObject)
    {
      for (;;)
      {
        localObject1 = localObject2;
      }
    }
  }
  
  public JSONObject bK()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("url", this.KQ.toString());
      localJSONObject.put("width", this.lf);
      localJSONObject.put("height", this.lg);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
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
      if ((paramObject == null) || (!(paramObject instanceof WebImage)))
      {
        bool = false;
      }
      else
      {
        paramObject = (WebImage)paramObject;
        if ((!n.equal(this.KQ, ((WebImage)paramObject).KQ)) || (this.lf != ((WebImage)paramObject).lf) || (this.lg != ((WebImage)paramObject).lg)) {
          bool = false;
        }
      }
    }
  }
  
  public int getHeight()
  {
    return this.lg;
  }
  
  public Uri getUrl()
  {
    return this.KQ;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int getWidth()
  {
    return this.lf;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.KQ, Integer.valueOf(this.lf), Integer.valueOf(this.lg) });
  }
  
  public String toString()
  {
    return String.format("Image %dx%d %s", new Object[] { Integer.valueOf(this.lf), Integer.valueOf(this.lg), this.KQ.toString() });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    b.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\common\images\WebImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */