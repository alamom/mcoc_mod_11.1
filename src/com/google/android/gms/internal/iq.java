package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.TextTrackStyle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class iq
  extends ii
{
  private static final long Hb = TimeUnit.HOURS.toMillis(24L);
  private static final long Hc = TimeUnit.HOURS.toMillis(24L);
  private static final long Hd = TimeUnit.HOURS.toMillis(24L);
  private static final long He = TimeUnit.SECONDS.toMillis(1L);
  private static final String NAMESPACE = ik.aG("com.google.cast.media");
  private long Hf;
  private MediaStatus Hg;
  private final it Hh = new it(Hc);
  private final it Hi;
  private final it Hj;
  private final it Hk;
  private final it Hl;
  private final it Hm;
  private final it Hn;
  private final it Ho;
  private final it Hp;
  private final it Hq;
  private final List<it> Hr = new ArrayList();
  private final Runnable Hs = new a(null);
  private boolean Ht;
  private final Handler mHandler = new Handler(Looper.getMainLooper());
  
  public iq()
  {
    this(null);
  }
  
  public iq(String paramString)
  {
    super(NAMESPACE, "MediaControlChannel", paramString);
    this.Hr.add(this.Hh);
    this.Hi = new it(Hb);
    this.Hr.add(this.Hi);
    this.Hj = new it(Hb);
    this.Hr.add(this.Hj);
    this.Hk = new it(Hb);
    this.Hr.add(this.Hk);
    this.Hl = new it(Hd);
    this.Hr.add(this.Hl);
    this.Hm = new it(Hb);
    this.Hr.add(this.Hm);
    this.Hn = new it(Hb);
    this.Hr.add(this.Hn);
    this.Ho = new it(Hb);
    this.Hr.add(this.Ho);
    this.Hp = new it(Hb);
    this.Hr.add(this.Hp);
    this.Hq = new it(Hb);
    this.Hr.add(this.Hq);
    fT();
  }
  
  private void H(boolean paramBoolean)
  {
    if (this.Ht != paramBoolean)
    {
      this.Ht = paramBoolean;
      if (!paramBoolean) {
        break label33;
      }
      this.mHandler.postDelayed(this.Hs, He);
    }
    for (;;)
    {
      return;
      label33:
      this.mHandler.removeCallbacks(this.Hs);
    }
  }
  
  private void a(long paramLong, JSONObject paramJSONObject)
    throws JSONException
  {
    int k = 1;
    boolean bool = this.Hh.p(paramLong);
    int j;
    if ((this.Hl.fV()) && (!this.Hl.p(paramLong)))
    {
      i = 1;
      if (this.Hm.fV())
      {
        j = k;
        if (!this.Hm.p(paramLong)) {}
      }
      else
      {
        if ((!this.Hn.fV()) || (this.Hn.p(paramLong))) {
          break label235;
        }
        j = k;
      }
      label87:
      if (i == 0) {
        break label257;
      }
    }
    label235:
    label257:
    for (int i = 2;; i = 0)
    {
      k = i;
      if (j != 0) {
        k = i | 0x1;
      }
      if ((bool) || (this.Hg == null))
      {
        this.Hg = new MediaStatus(paramJSONObject);
        this.Hf = SystemClock.elapsedRealtime();
      }
      for (i = 7;; i = this.Hg.a(paramJSONObject, k))
      {
        if ((i & 0x1) != 0)
        {
          this.Hf = SystemClock.elapsedRealtime();
          onStatusUpdated();
        }
        if ((i & 0x2) != 0)
        {
          this.Hf = SystemClock.elapsedRealtime();
          onStatusUpdated();
        }
        if ((i & 0x4) != 0) {
          onMetadataUpdated();
        }
        paramJSONObject = this.Hr.iterator();
        while (paramJSONObject.hasNext()) {
          ((it)paramJSONObject.next()).d(paramLong, 0);
        }
        i = 0;
        break;
        j = 0;
        break label87;
      }
      return;
    }
  }
  
  private void fT()
  {
    H(false);
    this.Hf = 0L;
    this.Hg = null;
    this.Hh.clear();
    this.Hl.clear();
    this.Hm.clear();
  }
  
  public long a(is paramis)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = fz();
    this.Ho.a(l, paramis);
    H(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "GET_STATUS");
      if (this.Hg != null) {
        localJSONObject.put("mediaSessionId", this.Hg.fw());
      }
      a(localJSONObject.toString(), l, null);
      return l;
    }
    catch (JSONException paramis)
    {
      for (;;) {}
    }
  }
  
  public long a(is paramis, double paramDouble, JSONObject paramJSONObject)
    throws IOException, IllegalStateException, IllegalArgumentException
  {
    if ((Double.isInfinite(paramDouble)) || (Double.isNaN(paramDouble))) {
      throw new IllegalArgumentException("Volume cannot be " + paramDouble);
    }
    JSONObject localJSONObject = new JSONObject();
    long l = fz();
    this.Hm.a(l, paramis);
    H(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "SET_VOLUME");
      localJSONObject.put("mediaSessionId", fw());
      paramis = new org/json/JSONObject;
      paramis.<init>();
      paramis.put("level", paramDouble);
      localJSONObject.put("volume", paramis);
      if (paramJSONObject != null) {
        localJSONObject.put("customData", paramJSONObject);
      }
    }
    catch (JSONException paramis)
    {
      for (;;) {}
    }
    a(localJSONObject.toString(), l, null);
    return l;
  }
  
  public long a(is paramis, long paramLong, int paramInt, JSONObject paramJSONObject)
    throws IOException, IllegalStateException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = fz();
    this.Hl.a(l, paramis);
    H(true);
    for (;;)
    {
      try
      {
        localJSONObject.put("requestId", l);
        localJSONObject.put("type", "SEEK");
        localJSONObject.put("mediaSessionId", fw());
        localJSONObject.put("currentTime", ik.o(paramLong));
        if (paramInt != 1) {
          continue;
        }
        localJSONObject.put("resumeState", "PLAYBACK_START");
        if (paramJSONObject != null) {
          localJSONObject.put("customData", paramJSONObject);
        }
      }
      catch (JSONException paramis)
      {
        continue;
      }
      a(localJSONObject.toString(), l, null);
      return l;
      if (paramInt == 2) {
        localJSONObject.put("resumeState", "PLAYBACK_PAUSE");
      }
    }
  }
  
  public long a(is paramis, MediaInfo paramMediaInfo, boolean paramBoolean, long paramLong, long[] paramArrayOfLong, JSONObject paramJSONObject)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = fz();
    this.Hh.a(l, paramis);
    H(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "LOAD");
      localJSONObject.put("media", paramMediaInfo.bK());
      localJSONObject.put("autoplay", paramBoolean);
      localJSONObject.put("currentTime", ik.o(paramLong));
      if ((paramArrayOfLong != null) && (paramArrayOfLong.length > 0))
      {
        paramis = new org/json/JSONArray;
        paramis.<init>();
        for (int i = 0; i < paramArrayOfLong.length; i++) {
          paramis.put(i, paramArrayOfLong[i]);
        }
        localJSONObject.put("activeTrackIds", paramis);
      }
      if (paramJSONObject != null) {
        localJSONObject.put("customData", paramJSONObject);
      }
    }
    catch (JSONException paramis)
    {
      for (;;) {}
    }
    a(localJSONObject.toString(), l, null);
    return l;
  }
  
  public long a(is paramis, TextTrackStyle paramTextTrackStyle)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = fz();
    this.Hq.a(l, paramis);
    H(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "EDIT_TRACKS_INFO");
      if (paramTextTrackStyle != null) {
        localJSONObject.put("textTrackStyle", paramTextTrackStyle.bK());
      }
      localJSONObject.put("mediaSessionId", fw());
    }
    catch (JSONException paramis)
    {
      for (;;) {}
    }
    a(localJSONObject.toString(), l, null);
    return l;
  }
  
  public long a(is paramis, JSONObject paramJSONObject)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = fz();
    this.Hi.a(l, paramis);
    H(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "PAUSE");
      localJSONObject.put("mediaSessionId", fw());
      if (paramJSONObject != null) {
        localJSONObject.put("customData", paramJSONObject);
      }
    }
    catch (JSONException paramis)
    {
      for (;;) {}
    }
    a(localJSONObject.toString(), l, null);
    return l;
  }
  
  public long a(is paramis, boolean paramBoolean, JSONObject paramJSONObject)
    throws IOException, IllegalStateException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = fz();
    this.Hn.a(l, paramis);
    H(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "SET_VOLUME");
      localJSONObject.put("mediaSessionId", fw());
      paramis = new org/json/JSONObject;
      paramis.<init>();
      paramis.put("muted", paramBoolean);
      localJSONObject.put("volume", paramis);
      if (paramJSONObject != null) {
        localJSONObject.put("customData", paramJSONObject);
      }
    }
    catch (JSONException paramis)
    {
      for (;;) {}
    }
    a(localJSONObject.toString(), l, null);
    return l;
  }
  
  public long a(is paramis, long[] paramArrayOfLong)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = fz();
    this.Hp.a(l, paramis);
    H(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "EDIT_TRACKS_INFO");
      localJSONObject.put("mediaSessionId", fw());
      paramis = new org/json/JSONArray;
      paramis.<init>();
      for (int i = 0; i < paramArrayOfLong.length; i++) {
        paramis.put(i, paramArrayOfLong[i]);
      }
      localJSONObject.put("activeTrackIds", paramis);
    }
    catch (JSONException paramis)
    {
      for (;;) {}
    }
    a(localJSONObject.toString(), l, null);
    return l;
  }
  
  public final void aD(String paramString)
  {
    this.Go.b("message received: %s", new Object[] { paramString });
    for (;;)
    {
      Object localObject3;
      long l;
      Object localObject2;
      try
      {
        Object localObject1 = new org/json/JSONObject;
        ((JSONObject)localObject1).<init>(paramString);
        localObject3 = ((JSONObject)localObject1).getString("type");
        l = ((JSONObject)localObject1).optLong("requestId", -1L);
        if (((String)localObject3).equals("MEDIA_STATUS"))
        {
          localObject1 = ((JSONObject)localObject1).getJSONArray("status");
          if (((JSONArray)localObject1).length() > 0)
          {
            a(l, ((JSONArray)localObject1).getJSONObject(0));
            return;
          }
          this.Hg = null;
          onStatusUpdated();
          onMetadataUpdated();
          this.Ho.d(l, 0);
          continue;
        }
      }
      catch (JSONException localJSONException)
      {
        this.Go.d("Message is malformed (%s); ignoring: %s", new Object[] { localJSONException.getMessage(), paramString });
        continue;
        if (((String)localObject3).equals("INVALID_PLAYER_STATE"))
        {
          this.Go.d("received unexpected error: Invalid Player State.", new Object[0]);
          localObject3 = localJSONException.optJSONObject("customData");
          localObject2 = this.Hr.iterator();
          if (!((Iterator)localObject2).hasNext()) {
            continue;
          }
          ((it)((Iterator)localObject2).next()).b(l, 2100, (JSONObject)localObject3);
          continue;
        }
        if (((String)localObject3).equals("LOAD_FAILED"))
        {
          localObject2 = ((JSONObject)localObject2).optJSONObject("customData");
          this.Hh.b(l, 2100, (JSONObject)localObject2);
          continue;
        }
        if (((String)localObject3).equals("LOAD_CANCELLED"))
        {
          localObject2 = ((JSONObject)localObject2).optJSONObject("customData");
          this.Hh.b(l, 2101, (JSONObject)localObject2);
          continue;
        }
        if (!((String)localObject3).equals("INVALID_REQUEST")) {
          continue;
        }
        this.Go.d("received unexpected error: Invalid Request.", new Object[0]);
        localObject3 = ((JSONObject)localObject2).optJSONObject("customData");
        localObject2 = this.Hr.iterator();
      }
      while (((Iterator)localObject2).hasNext()) {
        ((it)((Iterator)localObject2).next()).b(l, 2100, (JSONObject)localObject3);
      }
    }
  }
  
  public long b(is paramis, JSONObject paramJSONObject)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = fz();
    this.Hk.a(l, paramis);
    H(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "STOP");
      localJSONObject.put("mediaSessionId", fw());
      if (paramJSONObject != null) {
        localJSONObject.put("customData", paramJSONObject);
      }
    }
    catch (JSONException paramis)
    {
      for (;;) {}
    }
    a(localJSONObject.toString(), l, null);
    return l;
  }
  
  public void b(long paramLong, int paramInt)
  {
    Iterator localIterator = this.Hr.iterator();
    while (localIterator.hasNext()) {
      ((it)localIterator.next()).d(paramLong, paramInt);
    }
  }
  
  public long c(is paramis, JSONObject paramJSONObject)
    throws IOException, IllegalStateException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = fz();
    this.Hj.a(l, paramis);
    H(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "PLAY");
      localJSONObject.put("mediaSessionId", fw());
      if (paramJSONObject != null) {
        localJSONObject.put("customData", paramJSONObject);
      }
    }
    catch (JSONException paramis)
    {
      for (;;) {}
    }
    a(localJSONObject.toString(), l, null);
    return l;
  }
  
  public void fA()
  {
    fT();
  }
  
  public long fw()
    throws IllegalStateException
  {
    if (this.Hg == null) {
      throw new IllegalStateException("No current media session");
    }
    return this.Hg.fw();
  }
  
  public long getApproximateStreamPosition()
  {
    long l1 = 0L;
    MediaInfo localMediaInfo = getMediaInfo();
    if (localMediaInfo == null) {}
    label152:
    for (;;)
    {
      return l1;
      if (this.Hf != 0L)
      {
        double d = this.Hg.getPlaybackRate();
        l1 = this.Hg.getStreamPosition();
        int i = this.Hg.getPlayerState();
        if ((d == 0.0D) || (i == 2))
        {
          long l2 = SystemClock.elapsedRealtime() - this.Hf;
          if (l2 < 0L) {
            l2 = 0L;
          }
          for (;;)
          {
            if (l2 == 0L) {
              break label152;
            }
            long l3 = localMediaInfo.getStreamDuration();
            l1 += (l2 * d);
            if ((l3 > 0L) && (l1 > l3)) {
              l1 = l3;
            }
            for (;;)
            {
              break;
              if (l1 < 0L) {
                l1 = 0L;
              }
            }
          }
        }
      }
    }
  }
  
  public MediaInfo getMediaInfo()
  {
    if (this.Hg == null) {}
    for (MediaInfo localMediaInfo = null;; localMediaInfo = this.Hg.getMediaInfo()) {
      return localMediaInfo;
    }
  }
  
  public MediaStatus getMediaStatus()
  {
    return this.Hg;
  }
  
  public long getStreamDuration()
  {
    MediaInfo localMediaInfo = getMediaInfo();
    if (localMediaInfo != null) {}
    for (long l = localMediaInfo.getStreamDuration();; l = 0L) {
      return l;
    }
  }
  
  protected void onMetadataUpdated() {}
  
  protected void onStatusUpdated() {}
  
  private class a
    implements Runnable
  {
    private a() {}
    
    public void run()
    {
      boolean bool = false;
      iq.a(iq.this, false);
      long l = SystemClock.elapsedRealtime();
      ??? = iq.a(iq.this).iterator();
      while (((Iterator)???).hasNext()) {
        ((it)((Iterator)???).next()).e(l, 2102);
      }
      for (;;)
      {
        synchronized (it.Hz)
        {
          Iterator localIterator = iq.a(iq.this).iterator();
          if (localIterator.hasNext())
          {
            if (((it)localIterator.next()).fV()) {
              bool = true;
            }
          }
          else
          {
            iq.b(iq.this, bool);
            return;
          }
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\iq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */