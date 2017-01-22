package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.c;
import com.google.android.gms.internal.kj;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class DataPoint
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataPoint> CREATOR = new e();
  private final int BR;
  private long SG;
  private long SH;
  private final Value[] SI;
  private DataSource SJ;
  private long SK;
  private long SL;
  private final DataSource Sq;
  
  DataPoint(int paramInt, DataSource paramDataSource1, long paramLong1, long paramLong2, Value[] paramArrayOfValue, DataSource paramDataSource2, long paramLong3, long paramLong4)
  {
    this.BR = paramInt;
    this.Sq = paramDataSource1;
    this.SJ = paramDataSource2;
    this.SG = paramLong1;
    this.SH = paramLong2;
    this.SI = paramArrayOfValue;
    this.SK = paramLong3;
    this.SL = paramLong4;
  }
  
  private DataPoint(DataSource paramDataSource)
  {
    this.BR = 4;
    this.Sq = ((DataSource)o.b(paramDataSource, "Data source cannot be null"));
    paramDataSource = paramDataSource.getDataType().getFields();
    this.SI = new Value[paramDataSource.size()];
    paramDataSource = paramDataSource.iterator();
    for (int i = 0; paramDataSource.hasNext(); i++)
    {
      Field localField = (Field)paramDataSource.next();
      this.SI[i] = new Value(localField.getFormat());
    }
  }
  
  DataPoint(List<DataSource> paramList, RawDataPoint paramRawDataPoint)
  {
    this(4, a(paramList, paramRawDataPoint.Tm), paramRawDataPoint.SG, paramRawDataPoint.SH, paramRawDataPoint.SI, a(paramList, paramRawDataPoint.Tn), paramRawDataPoint.SK, paramRawDataPoint.SL);
  }
  
  private static DataSource a(List<DataSource> paramList, int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < paramList.size())) {}
    for (paramList = (DataSource)paramList.get(paramInt);; paramList = null) {
      return paramList;
    }
  }
  
  private boolean a(DataPoint paramDataPoint)
  {
    if ((n.equal(this.Sq, paramDataPoint.Sq)) && (this.SG == paramDataPoint.SG) && (this.SH == paramDataPoint.SH) && (Arrays.equals(this.SI, paramDataPoint.SI)) && (n.equal(this.SJ, paramDataPoint.SJ))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private void cA(int paramInt)
  {
    List localList = getDataType().getFields();
    int i = localList.size();
    if (paramInt == i) {}
    for (boolean bool = true;; bool = false)
    {
      o.b(bool, "Attempting to insert %s values, but needed %s: %s", new Object[] { Integer.valueOf(paramInt), Integer.valueOf(i), localList });
      return;
    }
  }
  
  public static DataPoint create(DataSource paramDataSource)
  {
    return new DataPoint(paramDataSource);
  }
  
  public static DataPoint extract(Intent paramIntent)
  {
    if (paramIntent == null) {}
    for (paramIntent = null;; paramIntent = (DataPoint)c.a(paramIntent, "com.google.android.gms.fitness.EXTRA_DATA_POINT", CREATOR)) {
      return paramIntent;
    }
  }
  
  private boolean iF()
  {
    if (getDataType() == DataType.TYPE_LOCATION_SAMPLE) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((this == paramObject) || (((paramObject instanceof DataPoint)) && (a((DataPoint)paramObject)))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public DataSource getDataSource()
  {
    return this.Sq;
  }
  
  public DataType getDataType()
  {
    return this.Sq.getDataType();
  }
  
  public long getEndTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.SG, TimeUnit.NANOSECONDS);
  }
  
  public DataSource getOriginalDataSource()
  {
    return this.SJ;
  }
  
  public long getStartTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.SH, TimeUnit.NANOSECONDS);
  }
  
  public long getTimestamp(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.SG, TimeUnit.NANOSECONDS);
  }
  
  public long getTimestampNanos()
  {
    return this.SG;
  }
  
  public Value getValue(Field paramField)
  {
    int i = getDataType().indexOf(paramField);
    return this.SI[i];
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.Sq, Long.valueOf(this.SG), Long.valueOf(this.SH) });
  }
  
  public Value[] iG()
  {
    return this.SI;
  }
  
  public long iH()
  {
    return this.SK;
  }
  
  public long iI()
  {
    return this.SL;
  }
  
  public long iJ()
  {
    return this.SH;
  }
  
  public DataPoint setFloatValues(float... paramVarArgs)
  {
    cA(paramVarArgs.length);
    for (int i = 0; i < paramVarArgs.length; i++) {
      this.SI[i].setFloat(paramVarArgs[i]);
    }
    return this;
  }
  
  public DataPoint setIntValues(int... paramVarArgs)
  {
    cA(paramVarArgs.length);
    for (int i = 0; i < paramVarArgs.length; i++) {
      this.SI[i].setInt(paramVarArgs[i]);
    }
    return this;
  }
  
  public DataPoint setTimeInterval(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
  {
    this.SH = paramTimeUnit.toNanos(paramLong1);
    this.SG = paramTimeUnit.toNanos(paramLong2);
    return this;
  }
  
  public DataPoint setTimestamp(long paramLong, TimeUnit paramTimeUnit)
  {
    this.SG = paramTimeUnit.toNanos(paramLong);
    if ((iF()) && (kj.a(paramTimeUnit)))
    {
      Log.w("Fitness", "Storing location at more than millisecond granularity is not supported. Extra precision is lost as the value is converted to milliseconds.");
      this.SG = kj.a(this.SG, TimeUnit.NANOSECONDS, TimeUnit.MILLISECONDS);
    }
    return this;
  }
  
  public String toString()
  {
    return String.format("DataPoint{%s@[%s, %s,raw=%s,insert=%s](%s %s)}", new Object[] { Arrays.toString(this.SI), Long.valueOf(this.SH), Long.valueOf(this.SG), Long.valueOf(this.SK), Long.valueOf(this.SL), this.Sq, this.SJ });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    e.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\fitness\data\DataPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */