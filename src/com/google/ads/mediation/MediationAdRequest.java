package com.google.ads.mediation;

import android.location.Location;
import com.google.ads.AdRequest.Gender;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Deprecated
public final class MediationAdRequest
{
  private final Date d;
  private final AdRequest.Gender e;
  private final Set<String> f;
  private final boolean g;
  private final Location h;
  
  public MediationAdRequest(Date paramDate, AdRequest.Gender paramGender, Set<String> paramSet, boolean paramBoolean, Location paramLocation)
  {
    this.d = paramDate;
    this.e = paramGender;
    this.f = paramSet;
    this.g = paramBoolean;
    this.h = paramLocation;
  }
  
  public Integer getAgeInYears()
  {
    Integer localInteger2;
    if (this.d != null)
    {
      Calendar localCalendar1 = Calendar.getInstance();
      Calendar localCalendar2 = Calendar.getInstance();
      localCalendar1.setTime(this.d);
      localInteger2 = Integer.valueOf(localCalendar2.get(1) - localCalendar1.get(1));
      if (localCalendar2.get(2) >= localCalendar1.get(2))
      {
        localInteger1 = localInteger2;
        if (localCalendar2.get(2) != localCalendar1.get(2)) {
          break label96;
        }
        localInteger1 = localInteger2;
        if (localCalendar2.get(5) >= localCalendar1.get(5)) {
          break label96;
        }
      }
    }
    for (Integer localInteger1 = Integer.valueOf(localInteger2.intValue() - 1);; localInteger1 = null) {
      label96:
      return localInteger1;
    }
  }
  
  public Date getBirthday()
  {
    return this.d;
  }
  
  public AdRequest.Gender getGender()
  {
    return this.e;
  }
  
  public Set<String> getKeywords()
  {
    return this.f;
  }
  
  public Location getLocation()
  {
    return this.h;
  }
  
  public boolean isTesting()
  {
    return this.g;
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\ads\mediation\MediationAdRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */