package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ik;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CastDevice
  implements SafeParcelable
{
  public static final Parcelable.Creator<CastDevice> CREATOR = new b();
  private final int BR;
  private String ER;
  String ES;
  private Inet4Address ET;
  private String EU;
  private String EV;
  private String EW;
  private int EX;
  private List<WebImage> EY;
  private int EZ;
  private int Fa;
  
  private CastDevice()
  {
    this(3, null, null, null, null, null, -1, new ArrayList(), 0, -1);
  }
  
  CastDevice(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt2, List<WebImage> paramList, int paramInt3, int paramInt4)
  {
    this.BR = paramInt1;
    this.ER = paramString1;
    this.ES = paramString2;
    if (this.ES != null) {}
    try
    {
      paramString1 = InetAddress.getByName(this.ES);
      if ((paramString1 instanceof Inet4Address)) {
        this.ET = ((Inet4Address)paramString1);
      }
      this.EU = paramString3;
      this.EV = paramString4;
      this.EW = paramString5;
      this.EX = paramInt2;
      this.EY = paramList;
      this.EZ = paramInt3;
      this.Fa = paramInt4;
      return;
    }
    catch (UnknownHostException paramString1)
    {
      for (;;)
      {
        this.ET = null;
      }
    }
  }
  
  public static CastDevice getFromBundle(Bundle paramBundle)
  {
    if (paramBundle == null) {}
    for (paramBundle = null;; paramBundle = (CastDevice)paramBundle.getParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE"))
    {
      return paramBundle;
      paramBundle.setClassLoader(CastDevice.class.getClassLoader());
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof CastDevice))
      {
        bool = false;
      }
      else
      {
        paramObject = (CastDevice)paramObject;
        if (getDeviceId() == null)
        {
          if (((CastDevice)paramObject).getDeviceId() != null) {
            bool = false;
          }
        }
        else if ((!ik.a(this.ER, ((CastDevice)paramObject).ER)) || (!ik.a(this.ET, ((CastDevice)paramObject).ET)) || (!ik.a(this.EV, ((CastDevice)paramObject).EV)) || (!ik.a(this.EU, ((CastDevice)paramObject).EU)) || (!ik.a(this.EW, ((CastDevice)paramObject).EW)) || (this.EX != ((CastDevice)paramObject).EX) || (!ik.a(this.EY, ((CastDevice)paramObject).EY)) || (this.EZ != ((CastDevice)paramObject).EZ) || (this.Fa != ((CastDevice)paramObject).Fa)) {
          bool = false;
        }
      }
    }
  }
  
  public int getCapabilities()
  {
    return this.EZ;
  }
  
  public String getDeviceId()
  {
    return this.ER;
  }
  
  public String getDeviceVersion()
  {
    return this.EW;
  }
  
  public String getFriendlyName()
  {
    return this.EU;
  }
  
  public WebImage getIcon(int paramInt1, int paramInt2)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    if (this.EY.isEmpty()) {}
    for (localObject1 = localObject2;; localObject1 = (WebImage)this.EY.get(0))
    {
      return (WebImage)localObject1;
      if ((paramInt1 > 0) && (paramInt2 > 0)) {
        break;
      }
    }
    Iterator localIterator = this.EY.iterator();
    localObject2 = null;
    label65:
    WebImage localWebImage;
    int j;
    int i;
    if (localIterator.hasNext())
    {
      localWebImage = (WebImage)localIterator.next();
      j = localWebImage.getWidth();
      i = localWebImage.getHeight();
      if ((j >= paramInt1) && (i >= paramInt2))
      {
        if ((localObject2 != null) && ((((WebImage)localObject2).getWidth() <= j) || (((WebImage)localObject2).getHeight() <= i))) {
          break label226;
        }
        localObject2 = localWebImage;
      }
    }
    label226:
    for (;;)
    {
      break label65;
      if ((j < paramInt1) && (i < paramInt2) && ((localObject1 == null) || ((((WebImage)localObject1).getWidth() < j) && (((WebImage)localObject1).getHeight() < i))))
      {
        localObject1 = localWebImage;
        continue;
        if (localObject2 != null) {}
        for (;;)
        {
          localObject1 = localObject2;
          break;
          if (localObject1 != null) {
            localObject2 = localObject1;
          } else {
            localObject2 = (WebImage)this.EY.get(0);
          }
        }
      }
    }
  }
  
  public List<WebImage> getIcons()
  {
    return Collections.unmodifiableList(this.EY);
  }
  
  public Inet4Address getIpAddress()
  {
    return this.ET;
  }
  
  public String getModelName()
  {
    return this.EV;
  }
  
  public int getServicePort()
  {
    return this.EX;
  }
  
  public int getStatus()
  {
    return this.Fa;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public boolean hasIcons()
  {
    if (!this.EY.isEmpty()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int hashCode()
  {
    if (this.ER == null) {}
    for (int i = 0;; i = this.ER.hashCode()) {
      return i;
    }
  }
  
  public boolean isSameDevice(CastDevice paramCastDevice)
  {
    boolean bool = false;
    if (paramCastDevice == null) {}
    for (;;)
    {
      return bool;
      if (getDeviceId() == null)
      {
        if (paramCastDevice.getDeviceId() == null) {
          bool = true;
        }
      }
      else {
        bool = ik.a(getDeviceId(), paramCastDevice.getDeviceId());
      }
    }
  }
  
  public void putInBundle(Bundle paramBundle)
  {
    if (paramBundle == null) {}
    for (;;)
    {
      return;
      paramBundle.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", this);
    }
  }
  
  public String toString()
  {
    return String.format("\"%s\" (%s)", new Object[] { this.EU, this.ER });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    b.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\cast\CastDevice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */