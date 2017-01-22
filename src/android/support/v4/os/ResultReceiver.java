package android.support.v4.os;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public class ResultReceiver
  implements Parcelable
{
  public static final Parcelable.Creator<ResultReceiver> CREATOR = new Parcelable.Creator()
  {
    public ResultReceiver createFromParcel(Parcel paramAnonymousParcel)
    {
      return new ResultReceiver(paramAnonymousParcel);
    }
    
    public ResultReceiver[] newArray(int paramAnonymousInt)
    {
      return new ResultReceiver[paramAnonymousInt];
    }
  };
  final Handler mHandler;
  final boolean mLocal;
  IResultReceiver mReceiver;
  
  public ResultReceiver(Handler paramHandler)
  {
    this.mLocal = true;
    this.mHandler = paramHandler;
  }
  
  ResultReceiver(Parcel paramParcel)
  {
    this.mLocal = false;
    this.mHandler = null;
    this.mReceiver = IResultReceiver.Stub.asInterface(paramParcel.readStrongBinder());
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  protected void onReceiveResult(int paramInt, Bundle paramBundle) {}
  
  public void send(int paramInt, Bundle paramBundle)
  {
    if (this.mLocal) {
      if (this.mHandler != null) {
        this.mHandler.post(new MyRunnable(paramInt, paramBundle));
      }
    }
    for (;;)
    {
      return;
      onReceiveResult(paramInt, paramBundle);
      continue;
      if (this.mReceiver != null) {
        try
        {
          this.mReceiver.send(paramInt, paramBundle);
        }
        catch (RemoteException paramBundle) {}
      }
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    try
    {
      if (this.mReceiver == null)
      {
        MyResultReceiver localMyResultReceiver = new android/support/v4/os/ResultReceiver$MyResultReceiver;
        localMyResultReceiver.<init>(this);
        this.mReceiver = localMyResultReceiver;
      }
      paramParcel.writeStrongBinder(this.mReceiver.asBinder());
      return;
    }
    finally {}
  }
  
  class MyResultReceiver
    extends IResultReceiver.Stub
  {
    MyResultReceiver() {}
    
    public void send(int paramInt, Bundle paramBundle)
    {
      if (ResultReceiver.this.mHandler != null) {
        ResultReceiver.this.mHandler.post(new ResultReceiver.MyRunnable(ResultReceiver.this, paramInt, paramBundle));
      }
      for (;;)
      {
        return;
        ResultReceiver.this.onReceiveResult(paramInt, paramBundle);
      }
    }
  }
  
  class MyRunnable
    implements Runnable
  {
    final int mResultCode;
    final Bundle mResultData;
    
    MyRunnable(int paramInt, Bundle paramBundle)
    {
      this.mResultCode = paramInt;
      this.mResultData = paramBundle;
    }
    
    public void run()
    {
      ResultReceiver.this.onReceiveResult(this.mResultCode, this.mResultData);
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\os\ResultReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */