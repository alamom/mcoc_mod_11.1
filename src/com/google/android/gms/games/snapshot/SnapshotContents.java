package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.internal.GamesLog;
import com.google.android.gms.internal.jy;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public final class SnapshotContents
  implements SafeParcelable
{
  public static final SnapshotContentsCreator CREATOR = new SnapshotContentsCreator();
  private static final Object adg = new Object();
  private final int BR;
  private Contents Ox;
  
  SnapshotContents(int paramInt, Contents paramContents)
  {
    this.BR = paramInt;
    this.Ox = paramContents;
  }
  
  public SnapshotContents(Contents paramContents)
  {
    this(1, paramContents);
  }
  
  private boolean a(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!isClosed()) {
      bool1 = true;
    }
    for (;;)
    {
      o.a(bool1, "Must provide a previously opened SnapshotContents");
      synchronized (adg)
      {
        Object localObject3 = this.Ox.getParcelFileDescriptor();
        Object localObject2 = new java/io/FileOutputStream;
        ((FileOutputStream)localObject2).<init>(((ParcelFileDescriptor)localObject3).getFileDescriptor());
        localObject3 = new java/io/BufferedOutputStream;
        ((BufferedOutputStream)localObject3).<init>((OutputStream)localObject2);
        try
        {
          localObject2 = ((FileOutputStream)localObject2).getChannel();
          ((FileChannel)localObject2).position(paramInt1);
          ((OutputStream)localObject3).write(paramArrayOfByte, paramInt2, paramInt3);
          if (paramBoolean) {
            ((FileChannel)localObject2).truncate(paramArrayOfByte.length);
          }
          ((OutputStream)localObject3).flush();
          paramBoolean = bool2;
        }
        catch (IOException paramArrayOfByte)
        {
          for (;;)
          {
            GamesLog.a("SnapshotContents", "Failed to write snapshot data", paramArrayOfByte);
            paramBoolean = false;
          }
        }
        return paramBoolean;
        bool1 = false;
      }
    }
  }
  
  public void close()
  {
    this.Ox.hJ();
    this.Ox = null;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Contents getContents()
  {
    return this.Ox;
  }
  
  public ParcelFileDescriptor getParcelFileDescriptor()
  {
    if (!isClosed()) {}
    for (boolean bool = true;; bool = false)
    {
      o.a(bool, "Cannot mutate closed contents!");
      return this.Ox.getParcelFileDescriptor();
    }
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public boolean isClosed()
  {
    if (this.Ox == null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean modifyBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    return a(paramInt1, paramArrayOfByte, paramInt2, paramArrayOfByte.length, false);
  }
  
  public byte[] readFully()
    throws IOException
  {
    boolean bool = false;
    if (!isClosed()) {
      bool = true;
    }
    o.a(bool, "Must provide a previously opened Snapshot");
    synchronized (adg)
    {
      Object localObject3 = this.Ox.getParcelFileDescriptor();
      FileInputStream localFileInputStream = new java/io/FileInputStream;
      localFileInputStream.<init>(((ParcelFileDescriptor)localObject3).getFileDescriptor());
      localObject3 = new java/io/BufferedInputStream;
      ((BufferedInputStream)localObject3).<init>(localFileInputStream);
      try
      {
        localFileInputStream.getChannel().position(0L);
        localObject3 = jy.a((InputStream)localObject3, false);
        localFileInputStream.getChannel().position(0L);
        return (byte[])localObject3;
      }
      catch (IOException localIOException)
      {
        GamesLog.b("SnapshotContents", "Failed to read snapshot data", localIOException);
        throw localIOException;
      }
    }
  }
  
  public boolean writeBytes(byte[] paramArrayOfByte)
  {
    return a(0, paramArrayOfByte, 0, paramArrayOfByte.length, true);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    SnapshotContentsCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\snapshot\SnapshotContents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */