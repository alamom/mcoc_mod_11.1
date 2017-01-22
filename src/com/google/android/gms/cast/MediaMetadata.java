package com.google.android.gms.cast;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.internal.iu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaMetadata
{
  private static final String[] Fn = { null, "String", "int", "double", "ISO-8601 date String" };
  private static final a Fo = new a().a("com.google.android.gms.cast.metadata.CREATION_DATE", "creationDateTime", 4).a("com.google.android.gms.cast.metadata.RELEASE_DATE", "releaseDate", 4).a("com.google.android.gms.cast.metadata.BROADCAST_DATE", "originalAirdate", 4).a("com.google.android.gms.cast.metadata.TITLE", "title", 1).a("com.google.android.gms.cast.metadata.SUBTITLE", "subtitle", 1).a("com.google.android.gms.cast.metadata.ARTIST", "artist", 1).a("com.google.android.gms.cast.metadata.ALBUM_ARTIST", "albumArtist", 1).a("com.google.android.gms.cast.metadata.ALBUM_TITLE", "albumName", 1).a("com.google.android.gms.cast.metadata.COMPOSER", "composer", 1).a("com.google.android.gms.cast.metadata.DISC_NUMBER", "discNumber", 2).a("com.google.android.gms.cast.metadata.TRACK_NUMBER", "trackNumber", 2).a("com.google.android.gms.cast.metadata.SEASON_NUMBER", "season", 2).a("com.google.android.gms.cast.metadata.EPISODE_NUMBER", "episode", 2).a("com.google.android.gms.cast.metadata.SERIES_TITLE", "seriesTitle", 1).a("com.google.android.gms.cast.metadata.STUDIO", "studio", 1).a("com.google.android.gms.cast.metadata.WIDTH", "width", 2).a("com.google.android.gms.cast.metadata.HEIGHT", "height", 2).a("com.google.android.gms.cast.metadata.LOCATION_NAME", "location", 1).a("com.google.android.gms.cast.metadata.LOCATION_LATITUDE", "latitude", 3).a("com.google.android.gms.cast.metadata.LOCATION_LONGITUDE", "longitude", 3);
  public static final String KEY_ALBUM_ARTIST = "com.google.android.gms.cast.metadata.ALBUM_ARTIST";
  public static final String KEY_ALBUM_TITLE = "com.google.android.gms.cast.metadata.ALBUM_TITLE";
  public static final String KEY_ARTIST = "com.google.android.gms.cast.metadata.ARTIST";
  public static final String KEY_BROADCAST_DATE = "com.google.android.gms.cast.metadata.BROADCAST_DATE";
  public static final String KEY_COMPOSER = "com.google.android.gms.cast.metadata.COMPOSER";
  public static final String KEY_CREATION_DATE = "com.google.android.gms.cast.metadata.CREATION_DATE";
  public static final String KEY_DISC_NUMBER = "com.google.android.gms.cast.metadata.DISC_NUMBER";
  public static final String KEY_EPISODE_NUMBER = "com.google.android.gms.cast.metadata.EPISODE_NUMBER";
  public static final String KEY_HEIGHT = "com.google.android.gms.cast.metadata.HEIGHT";
  public static final String KEY_LOCATION_LATITUDE = "com.google.android.gms.cast.metadata.LOCATION_LATITUDE";
  public static final String KEY_LOCATION_LONGITUDE = "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE";
  public static final String KEY_LOCATION_NAME = "com.google.android.gms.cast.metadata.LOCATION_NAME";
  public static final String KEY_RELEASE_DATE = "com.google.android.gms.cast.metadata.RELEASE_DATE";
  public static final String KEY_SEASON_NUMBER = "com.google.android.gms.cast.metadata.SEASON_NUMBER";
  public static final String KEY_SERIES_TITLE = "com.google.android.gms.cast.metadata.SERIES_TITLE";
  public static final String KEY_STUDIO = "com.google.android.gms.cast.metadata.STUDIO";
  public static final String KEY_SUBTITLE = "com.google.android.gms.cast.metadata.SUBTITLE";
  public static final String KEY_TITLE = "com.google.android.gms.cast.metadata.TITLE";
  public static final String KEY_TRACK_NUMBER = "com.google.android.gms.cast.metadata.TRACK_NUMBER";
  public static final String KEY_WIDTH = "com.google.android.gms.cast.metadata.WIDTH";
  public static final int MEDIA_TYPE_GENERIC = 0;
  public static final int MEDIA_TYPE_MOVIE = 1;
  public static final int MEDIA_TYPE_MUSIC_TRACK = 3;
  public static final int MEDIA_TYPE_PHOTO = 4;
  public static final int MEDIA_TYPE_TV_SHOW = 2;
  public static final int MEDIA_TYPE_USER = 100;
  private final List<WebImage> EA = new ArrayList();
  private final Bundle Fp = new Bundle();
  private int Fq;
  
  public MediaMetadata()
  {
    this(0);
  }
  
  public MediaMetadata(int paramInt)
  {
    this.Fq = paramInt;
  }
  
  private void a(JSONObject paramJSONObject, String... paramVarArgs)
  {
    Object localObject;
    try
    {
      int j = paramVarArgs.length;
      int i = 0;
      if (i < j)
      {
        localObject = paramVarArgs[i];
        if (!this.Fp.containsKey((String)localObject)) {}
        for (;;)
        {
          i++;
          break;
          switch (Fo.aB((String)localObject))
          {
          default: 
            break;
          case 1: 
          case 4: 
            paramJSONObject.put(Fo.az((String)localObject), this.Fp.getString((String)localObject));
          }
        }
        return;
      }
    }
    catch (JSONException paramJSONObject) {}
    for (;;)
    {
      paramJSONObject.put(Fo.az((String)localObject), this.Fp.getInt((String)localObject));
      break;
      paramJSONObject.put(Fo.az((String)localObject), this.Fp.getDouble((String)localObject));
      break;
      paramVarArgs = this.Fp.keySet().iterator();
      while (paramVarArgs.hasNext())
      {
        String str = (String)paramVarArgs.next();
        if (!str.startsWith("com.google."))
        {
          localObject = this.Fp.get(str);
          if ((localObject instanceof String)) {
            paramJSONObject.put(str, localObject);
          } else if ((localObject instanceof Integer)) {
            paramJSONObject.put(str, localObject);
          } else if ((localObject instanceof Double)) {
            paramJSONObject.put(str, localObject);
          }
        }
      }
    }
  }
  
  private boolean a(Bundle paramBundle1, Bundle paramBundle2)
  {
    boolean bool;
    if (paramBundle1.size() != paramBundle2.size()) {
      bool = false;
    }
    for (;;)
    {
      return bool;
      Iterator localIterator = paramBundle1.keySet().iterator();
      for (;;)
      {
        if (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          Object localObject1 = paramBundle1.get(str);
          Object localObject2 = paramBundle2.get(str);
          if (((localObject1 instanceof Bundle)) && ((localObject2 instanceof Bundle)) && (!a((Bundle)localObject1, (Bundle)localObject2)))
          {
            bool = false;
            break;
          }
          if (localObject1 == null)
          {
            if ((localObject2 == null) && (paramBundle2.containsKey(str))) {
              continue;
            }
            bool = false;
            break;
          }
          if (!localObject1.equals(localObject2))
          {
            bool = false;
            break;
          }
        }
      }
      bool = true;
    }
  }
  
  private void b(JSONObject paramJSONObject, String... paramVarArgs)
  {
    paramVarArgs = new HashSet(Arrays.asList(paramVarArgs));
    for (;;)
    {
      Object localObject2;
      Object localObject1;
      try
      {
        Iterator localIterator = paramJSONObject.keys();
        if (localIterator.hasNext())
        {
          localObject2 = (String)localIterator.next();
          if ("metadataType".equals(localObject2)) {
            continue;
          }
          String str = Fo.aA((String)localObject2);
          if (str != null)
          {
            boolean bool = paramVarArgs.contains(str);
            if (!bool) {
              continue;
            }
            try
            {
              localObject2 = paramJSONObject.get((String)localObject2);
              if (localObject2 == null) {
                continue;
              }
              switch (Fo.aB(str))
              {
              }
            }
            catch (JSONException localJSONException) {}
            if (!(localObject2 instanceof String)) {
              continue;
            }
            this.Fp.putString(str, (String)localObject2);
            continue;
            continue;
            if ((!(localObject2 instanceof String)) || (iu.aL((String)localObject2) == null)) {
              continue;
            }
            this.Fp.putString(localJSONException, (String)localObject2);
            continue;
            if (!(localObject2 instanceof Integer)) {
              continue;
            }
            this.Fp.putInt(localJSONException, ((Integer)localObject2).intValue());
            continue;
            if (!(localObject2 instanceof Double)) {
              continue;
            }
            this.Fp.putDouble(localJSONException, ((Double)localObject2).doubleValue());
            continue;
          }
          localObject1 = paramJSONObject.get((String)localObject2);
          if ((localObject1 instanceof String)) {
            this.Fp.putString((String)localObject2, (String)localObject1);
          }
        }
        else
        {
          return;
        }
      }
      catch (JSONException paramJSONObject) {}
      if ((localObject1 instanceof Integer)) {
        this.Fp.putInt((String)localObject2, ((Integer)localObject1).intValue());
      } else if ((localObject1 instanceof Double)) {
        this.Fp.putDouble((String)localObject2, ((Double)localObject1).doubleValue());
      }
    }
  }
  
  private void f(String paramString, int paramInt)
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("null and empty keys are not allowed");
    }
    int i = Fo.aB(paramString);
    if ((i != paramInt) && (i != 0)) {
      throw new IllegalArgumentException("Value for " + paramString + " must be a " + Fn[paramInt]);
    }
  }
  
  public void addImage(WebImage paramWebImage)
  {
    this.EA.add(paramWebImage);
  }
  
  public JSONObject bK()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("metadataType", this.Fq);
      iu.a(localJSONObject, this.EA);
      switch (this.Fq)
      {
      default: 
        a(localJSONObject, new String[0]);
      }
      for (;;)
      {
        return localJSONObject;
        a(localJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE" });
        continue;
        a(localJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.STUDIO", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE" });
        continue;
        a(localJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.SERIES_TITLE", "com.google.android.gms.cast.metadata.SEASON_NUMBER", "com.google.android.gms.cast.metadata.EPISODE_NUMBER", "com.google.android.gms.cast.metadata.BROADCAST_DATE" });
        continue;
        a(localJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.ALBUM_TITLE", "com.google.android.gms.cast.metadata.ALBUM_ARTIST", "com.google.android.gms.cast.metadata.COMPOSER", "com.google.android.gms.cast.metadata.TRACK_NUMBER", "com.google.android.gms.cast.metadata.DISC_NUMBER", "com.google.android.gms.cast.metadata.RELEASE_DATE" });
        continue;
        a(localJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.LOCATION_NAME", "com.google.android.gms.cast.metadata.LOCATION_LATITUDE", "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE", "com.google.android.gms.cast.metadata.WIDTH", "com.google.android.gms.cast.metadata.HEIGHT", "com.google.android.gms.cast.metadata.CREATION_DATE" });
      }
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
  
  public void c(JSONObject paramJSONObject)
  {
    clear();
    this.Fq = 0;
    try
    {
      this.Fq = paramJSONObject.getInt("metadataType");
      iu.a(this.EA, paramJSONObject);
      switch (this.Fq)
      {
      default: 
        b(paramJSONObject, new String[0]);
      }
      for (;;)
      {
        return;
        b(paramJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE" });
        continue;
        b(paramJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.STUDIO", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE" });
        continue;
        b(paramJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.SERIES_TITLE", "com.google.android.gms.cast.metadata.SEASON_NUMBER", "com.google.android.gms.cast.metadata.EPISODE_NUMBER", "com.google.android.gms.cast.metadata.BROADCAST_DATE" });
        continue;
        b(paramJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ALBUM_TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.ALBUM_ARTIST", "com.google.android.gms.cast.metadata.COMPOSER", "com.google.android.gms.cast.metadata.TRACK_NUMBER", "com.google.android.gms.cast.metadata.DISC_NUMBER", "com.google.android.gms.cast.metadata.RELEASE_DATE" });
        continue;
        b(paramJSONObject, new String[] { "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.LOCATION_NAME", "com.google.android.gms.cast.metadata.LOCATION_LATITUDE", "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE", "com.google.android.gms.cast.metadata.WIDTH", "com.google.android.gms.cast.metadata.HEIGHT", "com.google.android.gms.cast.metadata.CREATION_DATE" });
      }
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
  
  public void clear()
  {
    this.Fp.clear();
    this.EA.clear();
  }
  
  public void clearImages()
  {
    this.EA.clear();
  }
  
  public boolean containsKey(String paramString)
  {
    return this.Fp.containsKey(paramString);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof MediaMetadata))
      {
        bool = false;
      }
      else
      {
        paramObject = (MediaMetadata)paramObject;
        if ((!a(this.Fp, ((MediaMetadata)paramObject).Fp)) || (!this.EA.equals(((MediaMetadata)paramObject).EA))) {
          bool = false;
        }
      }
    }
  }
  
  public Calendar getDate(String paramString)
  {
    f(paramString, 4);
    paramString = this.Fp.getString(paramString);
    if (paramString != null) {}
    for (paramString = iu.aL(paramString);; paramString = null) {
      return paramString;
    }
  }
  
  public String getDateAsString(String paramString)
  {
    f(paramString, 4);
    return this.Fp.getString(paramString);
  }
  
  public double getDouble(String paramString)
  {
    f(paramString, 3);
    return this.Fp.getDouble(paramString);
  }
  
  public List<WebImage> getImages()
  {
    return this.EA;
  }
  
  public int getInt(String paramString)
  {
    f(paramString, 2);
    return this.Fp.getInt(paramString);
  }
  
  public int getMediaType()
  {
    return this.Fq;
  }
  
  public String getString(String paramString)
  {
    f(paramString, 1);
    return this.Fp.getString(paramString);
  }
  
  public boolean hasImages()
  {
    if ((this.EA != null) && (!this.EA.isEmpty())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int hashCode()
  {
    Iterator localIterator = this.Fp.keySet().iterator();
    String str;
    for (int i = 17; localIterator.hasNext(); i = this.Fp.get(str).hashCode() + i * 31) {
      str = (String)localIterator.next();
    }
    return i * 31 + this.EA.hashCode();
  }
  
  public Set<String> keySet()
  {
    return this.Fp.keySet();
  }
  
  public void putDate(String paramString, Calendar paramCalendar)
  {
    f(paramString, 4);
    this.Fp.putString(paramString, iu.a(paramCalendar));
  }
  
  public void putDouble(String paramString, double paramDouble)
  {
    f(paramString, 3);
    this.Fp.putDouble(paramString, paramDouble);
  }
  
  public void putInt(String paramString, int paramInt)
  {
    f(paramString, 2);
    this.Fp.putInt(paramString, paramInt);
  }
  
  public void putString(String paramString1, String paramString2)
  {
    f(paramString1, 1);
    this.Fp.putString(paramString1, paramString2);
  }
  
  private static class a
  {
    private final Map<String, String> Fr = new HashMap();
    private final Map<String, String> Fs = new HashMap();
    private final Map<String, Integer> Ft = new HashMap();
    
    public a a(String paramString1, String paramString2, int paramInt)
    {
      this.Fr.put(paramString1, paramString2);
      this.Fs.put(paramString2, paramString1);
      this.Ft.put(paramString1, Integer.valueOf(paramInt));
      return this;
    }
    
    public String aA(String paramString)
    {
      return (String)this.Fs.get(paramString);
    }
    
    public int aB(String paramString)
    {
      paramString = (Integer)this.Ft.get(paramString);
      if (paramString != null) {}
      for (int i = paramString.intValue();; i = 0) {
        return i;
      }
    }
    
    public String az(String paramString)
    {
      return (String)this.Fr.get(paramString);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\cast\MediaMetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */