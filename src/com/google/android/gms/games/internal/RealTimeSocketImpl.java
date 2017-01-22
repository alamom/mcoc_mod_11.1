package com.google.android.gms.games.internal;

import android.net.LocalSocket;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class RealTimeSocketImpl
  implements RealTimeSocket
{
  private ParcelFileDescriptor KE;
  private final String Xr;
  private final LocalSocket Ye;
  
  RealTimeSocketImpl(LocalSocket paramLocalSocket, String paramString)
  {
    this.Ye = paramLocalSocket;
    this.Xr = paramString;
  }
  
  public void close()
    throws IOException
  {
    this.Ye.close();
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    return this.Ye.getInputStream();
  }
  
  public OutputStream getOutputStream()
    throws IOException
  {
    return this.Ye.getOutputStream();
  }
  
  public ParcelFileDescriptor getParcelFileDescriptor()
    throws IOException
  {
    if ((this.KE == null) && (!isClosed()))
    {
      Parcel localParcel = Parcel.obtain();
      localParcel.writeFileDescriptor(this.Ye.getFileDescriptor());
      localParcel.setDataPosition(0);
      this.KE = localParcel.readFileDescriptor();
    }
    return this.KE;
  }
  
  public boolean isClosed()
  {
    if ((!this.Ye.isConnected()) && (!this.Ye.isBound())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\games\internal\RealTimeSocketImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */