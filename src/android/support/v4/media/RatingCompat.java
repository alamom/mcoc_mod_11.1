package android.support.v4.media;

import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class RatingCompat
  implements Parcelable
{
  public static final Parcelable.Creator<RatingCompat> CREATOR = new Parcelable.Creator()
  {
    public RatingCompat createFromParcel(Parcel paramAnonymousParcel)
    {
      return new RatingCompat(paramAnonymousParcel.readInt(), paramAnonymousParcel.readFloat(), null);
    }
    
    public RatingCompat[] newArray(int paramAnonymousInt)
    {
      return new RatingCompat[paramAnonymousInt];
    }
  };
  public static final int RATING_3_STARS = 3;
  public static final int RATING_4_STARS = 4;
  public static final int RATING_5_STARS = 5;
  public static final int RATING_HEART = 1;
  public static final int RATING_NONE = 0;
  private static final float RATING_NOT_RATED = -1.0F;
  public static final int RATING_PERCENTAGE = 6;
  public static final int RATING_THUMB_UP_DOWN = 2;
  private static final String TAG = "Rating";
  private Object mRatingObj;
  private final int mRatingStyle;
  private final float mRatingValue;
  
  private RatingCompat(int paramInt, float paramFloat)
  {
    this.mRatingStyle = paramInt;
    this.mRatingValue = paramFloat;
  }
  
  public static RatingCompat fromRating(Object paramObject)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramObject != null) {
      if (Build.VERSION.SDK_INT >= 21) {
        break label20;
      }
    }
    label20:
    int i;
    for (localObject1 = localObject2;; localObject1 = localObject2)
    {
      return (RatingCompat)localObject1;
      i = RatingCompatApi21.getRatingStyle(paramObject);
      if (!RatingCompatApi21.isRated(paramObject)) {
        break;
      }
      switch (i)
      {
      }
    }
    localObject1 = newHeartRating(RatingCompatApi21.hasHeart(paramObject));
    for (;;)
    {
      ((RatingCompat)localObject1).mRatingObj = paramObject;
      break;
      localObject1 = newThumbRating(RatingCompatApi21.isThumbUp(paramObject));
      continue;
      localObject1 = newStarRating(i, RatingCompatApi21.getStarRating(paramObject));
      continue;
      localObject1 = newPercentageRating(RatingCompatApi21.getPercentRating(paramObject));
      continue;
      localObject1 = newUnratedRating(i);
    }
  }
  
  public static RatingCompat newHeartRating(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (float f = 1.0F;; f = 0.0F) {
      return new RatingCompat(1, f);
    }
  }
  
  public static RatingCompat newPercentageRating(float paramFloat)
  {
    if ((paramFloat < 0.0F) || (paramFloat > 100.0F)) {
      Log.e("Rating", "Invalid percentage-based rating value");
    }
    for (RatingCompat localRatingCompat = null;; localRatingCompat = new RatingCompat(6, paramFloat)) {
      return localRatingCompat;
    }
  }
  
  public static RatingCompat newStarRating(int paramInt, float paramFloat)
  {
    RatingCompat localRatingCompat = null;
    switch (paramInt)
    {
    default: 
      Log.e("Rating", "Invalid rating style (" + paramInt + ") for a star rating");
    }
    for (;;)
    {
      return localRatingCompat;
      float f = 3.0F;
      for (;;)
      {
        if ((paramFloat >= 0.0F) && (paramFloat <= f)) {
          break label98;
        }
        Log.e("Rating", "Trying to set out of range star-based rating");
        break;
        f = 4.0F;
        continue;
        f = 5.0F;
      }
      label98:
      localRatingCompat = new RatingCompat(paramInt, paramFloat);
    }
  }
  
  public static RatingCompat newThumbRating(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (float f = 1.0F;; f = 0.0F) {
      return new RatingCompat(2, f);
    }
  }
  
  public static RatingCompat newUnratedRating(int paramInt)
  {
    switch (paramInt)
    {
    }
    for (RatingCompat localRatingCompat = null;; localRatingCompat = new RatingCompat(paramInt, -1.0F)) {
      return localRatingCompat;
    }
  }
  
  public int describeContents()
  {
    return this.mRatingStyle;
  }
  
  public float getPercentRating()
  {
    if ((this.mRatingStyle != 6) || (!isRated())) {}
    for (float f = -1.0F;; f = this.mRatingValue) {
      return f;
    }
  }
  
  public Object getRating()
  {
    if ((this.mRatingObj != null) || (Build.VERSION.SDK_INT < 21)) {}
    label72:
    for (Object localObject = this.mRatingObj;; localObject = null)
    {
      return localObject;
      if (!isRated()) {
        break;
      }
      switch (this.mRatingStyle)
      {
      }
    }
    this.mRatingObj = RatingCompatApi21.newHeartRating(hasHeart());
    for (;;)
    {
      localObject = this.mRatingObj;
      break;
      this.mRatingObj = RatingCompatApi21.newThumbRating(isThumbUp());
      continue;
      this.mRatingObj = RatingCompatApi21.newStarRating(this.mRatingStyle, getStarRating());
      continue;
      this.mRatingObj = RatingCompatApi21.newPercentageRating(getPercentRating());
      break label72;
      this.mRatingObj = RatingCompatApi21.newUnratedRating(this.mRatingStyle);
    }
  }
  
  public int getRatingStyle()
  {
    return this.mRatingStyle;
  }
  
  public float getStarRating()
  {
    switch (this.mRatingStyle)
    {
    }
    for (float f = -1.0F;; f = this.mRatingValue)
    {
      return f;
      if (!isRated()) {
        break;
      }
    }
  }
  
  public boolean hasHeart()
  {
    boolean bool2 = true;
    boolean bool1 = false;
    if (this.mRatingStyle != 1) {
      return bool1;
    }
    if (this.mRatingValue == 1.0F) {}
    for (bool1 = bool2;; bool1 = false) {
      break;
    }
  }
  
  public boolean isRated()
  {
    if (this.mRatingValue >= 0.0F) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isThumbUp()
  {
    boolean bool = false;
    if (this.mRatingStyle != 2) {}
    for (;;)
    {
      return bool;
      if (this.mRatingValue == 1.0F) {
        bool = true;
      }
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("Rating:style=").append(this.mRatingStyle).append(" rating=");
    if (this.mRatingValue < 0.0F) {}
    for (String str = "unrated";; str = String.valueOf(this.mRatingValue)) {
      return str;
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.mRatingStyle);
    paramParcel.writeFloat(this.mRatingValue);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface StarStyle {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Style {}
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\media\RatingCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */