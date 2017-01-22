package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public abstract interface IMediaSession
  extends IInterface
{
  public abstract void adjustVolume(int paramInt1, int paramInt2, String paramString)
    throws RemoteException;
  
  public abstract void fastForward()
    throws RemoteException;
  
  public abstract Bundle getExtras()
    throws RemoteException;
  
  public abstract long getFlags()
    throws RemoteException;
  
  public abstract PendingIntent getLaunchPendingIntent()
    throws RemoteException;
  
  public abstract MediaMetadataCompat getMetadata()
    throws RemoteException;
  
  public abstract String getPackageName()
    throws RemoteException;
  
  public abstract PlaybackStateCompat getPlaybackState()
    throws RemoteException;
  
  public abstract List<MediaSessionCompat.QueueItem> getQueue()
    throws RemoteException;
  
  public abstract CharSequence getQueueTitle()
    throws RemoteException;
  
  public abstract int getRatingType()
    throws RemoteException;
  
  public abstract String getTag()
    throws RemoteException;
  
  public abstract ParcelableVolumeInfo getVolumeAttributes()
    throws RemoteException;
  
  public abstract boolean isTransportControlEnabled()
    throws RemoteException;
  
  public abstract void next()
    throws RemoteException;
  
  public abstract void pause()
    throws RemoteException;
  
  public abstract void play()
    throws RemoteException;
  
  public abstract void playFromMediaId(String paramString, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void playFromSearch(String paramString, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void playFromUri(Uri paramUri, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void previous()
    throws RemoteException;
  
  public abstract void rate(RatingCompat paramRatingCompat)
    throws RemoteException;
  
  public abstract void registerCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
    throws RemoteException;
  
  public abstract void rewind()
    throws RemoteException;
  
  public abstract void seekTo(long paramLong)
    throws RemoteException;
  
  public abstract void sendCommand(String paramString, Bundle paramBundle, MediaSessionCompat.ResultReceiverWrapper paramResultReceiverWrapper)
    throws RemoteException;
  
  public abstract void sendCustomAction(String paramString, Bundle paramBundle)
    throws RemoteException;
  
  public abstract boolean sendMediaButton(KeyEvent paramKeyEvent)
    throws RemoteException;
  
  public abstract void setVolumeTo(int paramInt1, int paramInt2, String paramString)
    throws RemoteException;
  
  public abstract void skipToQueueItem(long paramLong)
    throws RemoteException;
  
  public abstract void stop()
    throws RemoteException;
  
  public abstract void unregisterCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements IMediaSession
  {
    private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaSession";
    static final int TRANSACTION_adjustVolume = 11;
    static final int TRANSACTION_fastForward = 22;
    static final int TRANSACTION_getExtras = 31;
    static final int TRANSACTION_getFlags = 9;
    static final int TRANSACTION_getLaunchPendingIntent = 8;
    static final int TRANSACTION_getMetadata = 27;
    static final int TRANSACTION_getPackageName = 6;
    static final int TRANSACTION_getPlaybackState = 28;
    static final int TRANSACTION_getQueue = 29;
    static final int TRANSACTION_getQueueTitle = 30;
    static final int TRANSACTION_getRatingType = 32;
    static final int TRANSACTION_getTag = 7;
    static final int TRANSACTION_getVolumeAttributes = 10;
    static final int TRANSACTION_isTransportControlEnabled = 5;
    static final int TRANSACTION_next = 20;
    static final int TRANSACTION_pause = 18;
    static final int TRANSACTION_play = 13;
    static final int TRANSACTION_playFromMediaId = 14;
    static final int TRANSACTION_playFromSearch = 15;
    static final int TRANSACTION_playFromUri = 16;
    static final int TRANSACTION_previous = 21;
    static final int TRANSACTION_rate = 25;
    static final int TRANSACTION_registerCallbackListener = 3;
    static final int TRANSACTION_rewind = 23;
    static final int TRANSACTION_seekTo = 24;
    static final int TRANSACTION_sendCommand = 1;
    static final int TRANSACTION_sendCustomAction = 26;
    static final int TRANSACTION_sendMediaButton = 2;
    static final int TRANSACTION_setVolumeTo = 12;
    static final int TRANSACTION_skipToQueueItem = 17;
    static final int TRANSACTION_stop = 19;
    static final int TRANSACTION_unregisterCallbackListener = 4;
    
    public Stub()
    {
      attachInterface(this, "android.support.v4.media.session.IMediaSession");
    }
    
    public static IMediaSession asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
        if ((localIInterface != null) && ((localIInterface instanceof IMediaSession))) {
          paramIBinder = (IMediaSession)localIInterface;
        } else {
          paramIBinder = new Proxy(paramIBinder);
        }
      }
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      int j = 0;
      int i = 0;
      boolean bool1 = true;
      switch (paramInt1)
      {
      default: 
        bool1 = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool1;
        paramParcel2.writeString("android.support.v4.media.session.IMediaSession");
        continue;
        paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
        String str = paramParcel1.readString();
        Object localObject;
        if (paramParcel1.readInt() != 0)
        {
          localObject = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          label340:
          if (paramParcel1.readInt() == 0) {
            break label382;
          }
        }
        label382:
        for (paramParcel1 = (MediaSessionCompat.ResultReceiverWrapper)MediaSessionCompat.ResultReceiverWrapper.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          sendCommand(str, (Bundle)localObject, paramParcel1);
          paramParcel2.writeNoException();
          break;
          localObject = null;
          break label340;
        }
        paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (KeyEvent)KeyEvent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          bool2 = sendMediaButton(paramParcel1);
          paramParcel2.writeNoException();
          paramInt1 = i;
          if (bool2) {
            paramInt1 = 1;
          }
          paramParcel2.writeInt(paramInt1);
          break;
        }
        paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
        registerCallbackListener(IMediaControllerCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
        unregisterCallbackListener(IMediaControllerCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
        boolean bool2 = isTransportControlEnabled();
        paramParcel2.writeNoException();
        paramInt1 = j;
        if (bool2) {
          paramInt1 = 1;
        }
        paramParcel2.writeInt(paramInt1);
        continue;
        paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
        paramParcel1 = getPackageName();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        continue;
        paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
        paramParcel1 = getTag();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        continue;
        paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
        paramParcel1 = getLaunchPendingIntent();
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
        }
        else
        {
          paramParcel2.writeInt(0);
          continue;
          paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
          long l = getFlags();
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          continue;
          paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
          paramParcel1 = getVolumeAttributes();
          paramParcel2.writeNoException();
          if (paramParcel1 != null)
          {
            paramParcel2.writeInt(1);
            paramParcel1.writeToParcel(paramParcel2, 1);
          }
          else
          {
            paramParcel2.writeInt(0);
            continue;
            paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
            adjustVolume(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString());
            paramParcel2.writeNoException();
            continue;
            paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
            setVolumeTo(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString());
            paramParcel2.writeNoException();
            continue;
            paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
            play();
            paramParcel2.writeNoException();
            continue;
            paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
            localObject = paramParcel1.readString();
            if (paramParcel1.readInt() != 0) {}
            for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
            {
              playFromMediaId((String)localObject, paramParcel1);
              paramParcel2.writeNoException();
              break;
            }
            paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
            localObject = paramParcel1.readString();
            if (paramParcel1.readInt() != 0) {}
            for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
            {
              playFromSearch((String)localObject, paramParcel1);
              paramParcel2.writeNoException();
              break;
            }
            paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
            if (paramParcel1.readInt() != 0)
            {
              localObject = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
              label886:
              if (paramParcel1.readInt() == 0) {
                break label926;
              }
            }
            label926:
            for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
            {
              playFromUri((Uri)localObject, paramParcel1);
              paramParcel2.writeNoException();
              break;
              localObject = null;
              break label886;
            }
            paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
            skipToQueueItem(paramParcel1.readLong());
            paramParcel2.writeNoException();
            continue;
            paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
            pause();
            paramParcel2.writeNoException();
            continue;
            paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
            stop();
            paramParcel2.writeNoException();
            continue;
            paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
            next();
            paramParcel2.writeNoException();
            continue;
            paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
            previous();
            paramParcel2.writeNoException();
            continue;
            paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
            fastForward();
            paramParcel2.writeNoException();
            continue;
            paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
            rewind();
            paramParcel2.writeNoException();
            continue;
            paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
            seekTo(paramParcel1.readLong());
            paramParcel2.writeNoException();
            continue;
            paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
            if (paramParcel1.readInt() != 0) {}
            for (paramParcel1 = (RatingCompat)RatingCompat.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
            {
              rate(paramParcel1);
              paramParcel2.writeNoException();
              break;
            }
            paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
            localObject = paramParcel1.readString();
            if (paramParcel1.readInt() != 0) {}
            for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
            {
              sendCustomAction((String)localObject, paramParcel1);
              paramParcel2.writeNoException();
              break;
            }
            paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
            paramParcel1 = getMetadata();
            paramParcel2.writeNoException();
            if (paramParcel1 != null)
            {
              paramParcel2.writeInt(1);
              paramParcel1.writeToParcel(paramParcel2, 1);
            }
            else
            {
              paramParcel2.writeInt(0);
              continue;
              paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
              paramParcel1 = getPlaybackState();
              paramParcel2.writeNoException();
              if (paramParcel1 != null)
              {
                paramParcel2.writeInt(1);
                paramParcel1.writeToParcel(paramParcel2, 1);
              }
              else
              {
                paramParcel2.writeInt(0);
                continue;
                paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
                paramParcel1 = getQueue();
                paramParcel2.writeNoException();
                paramParcel2.writeTypedList(paramParcel1);
                continue;
                paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
                paramParcel1 = getQueueTitle();
                paramParcel2.writeNoException();
                if (paramParcel1 != null)
                {
                  paramParcel2.writeInt(1);
                  TextUtils.writeToParcel(paramParcel1, paramParcel2, 1);
                }
                else
                {
                  paramParcel2.writeInt(0);
                  continue;
                  paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
                  paramParcel1 = getExtras();
                  paramParcel2.writeNoException();
                  if (paramParcel1 != null)
                  {
                    paramParcel2.writeInt(1);
                    paramParcel1.writeToParcel(paramParcel2, 1);
                  }
                  else
                  {
                    paramParcel2.writeInt(0);
                    continue;
                    paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
                    paramInt1 = getRatingType();
                    paramParcel2.writeNoException();
                    paramParcel2.writeInt(paramInt1);
                  }
                }
              }
            }
          }
        }
      }
    }
    
    private static class Proxy
      implements IMediaSession
    {
      private IBinder mRemote;
      
      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }
      
      public void adjustVolume(int paramInt1, int paramInt2, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeString(paramString);
          this.mRemote.transact(11, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public IBinder asBinder()
      {
        return this.mRemote;
      }
      
      public void fastForward()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          this.mRemote.transact(22, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public Bundle getExtras()
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_3
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_0
        //   15: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   18: bipush 31
        //   20: aload_3
        //   21: aload_2
        //   22: iconst_0
        //   23: invokeinterface 49 5 0
        //   28: pop
        //   29: aload_2
        //   30: invokevirtual 52	android/os/Parcel:readException	()V
        //   33: aload_2
        //   34: invokevirtual 65	android/os/Parcel:readInt	()I
        //   37: ifeq +26 -> 63
        //   40: getstatic 71	android/os/Bundle:CREATOR	Landroid/os/Parcelable$Creator;
        //   43: aload_2
        //   44: invokeinterface 77 2 0
        //   49: checkcast 67	android/os/Bundle
        //   52: astore_1
        //   53: aload_2
        //   54: invokevirtual 55	android/os/Parcel:recycle	()V
        //   57: aload_3
        //   58: invokevirtual 55	android/os/Parcel:recycle	()V
        //   61: aload_1
        //   62: areturn
        //   63: aconst_null
        //   64: astore_1
        //   65: goto -12 -> 53
        //   68: astore_1
        //   69: aload_2
        //   70: invokevirtual 55	android/os/Parcel:recycle	()V
        //   73: aload_3
        //   74: invokevirtual 55	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	Proxy
        //   52	13	1	localBundle	Bundle
        //   68	10	1	localObject	Object
        //   7	63	2	localParcel1	Parcel
        //   3	71	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	53	68	finally
      }
      
      public long getFlags()
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          this.mRemote.transact(9, localParcel2, localParcel1, 0);
          localParcel1.readException();
          long l = localParcel1.readLong();
          return l;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      public String getInterfaceDescriptor()
      {
        return "android.support.v4.media.session.IMediaSession";
      }
      
      /* Error */
      public PendingIntent getLaunchPendingIntent()
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_0
        //   15: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   18: bipush 8
        //   20: aload_2
        //   21: aload_3
        //   22: iconst_0
        //   23: invokeinterface 49 5 0
        //   28: pop
        //   29: aload_3
        //   30: invokevirtual 52	android/os/Parcel:readException	()V
        //   33: aload_3
        //   34: invokevirtual 65	android/os/Parcel:readInt	()I
        //   37: ifeq +26 -> 63
        //   40: getstatic 89	android/app/PendingIntent:CREATOR	Landroid/os/Parcelable$Creator;
        //   43: aload_3
        //   44: invokeinterface 77 2 0
        //   49: checkcast 88	android/app/PendingIntent
        //   52: astore_1
        //   53: aload_3
        //   54: invokevirtual 55	android/os/Parcel:recycle	()V
        //   57: aload_2
        //   58: invokevirtual 55	android/os/Parcel:recycle	()V
        //   61: aload_1
        //   62: areturn
        //   63: aconst_null
        //   64: astore_1
        //   65: goto -12 -> 53
        //   68: astore_1
        //   69: aload_3
        //   70: invokevirtual 55	android/os/Parcel:recycle	()V
        //   73: aload_2
        //   74: invokevirtual 55	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	Proxy
        //   52	13	1	localPendingIntent	PendingIntent
        //   68	10	1	localObject	Object
        //   3	71	2	localParcel1	Parcel
        //   7	63	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	53	68	finally
      }
      
      /* Error */
      public MediaMetadataCompat getMetadata()
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_0
        //   15: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   18: bipush 27
        //   20: aload_2
        //   21: aload_3
        //   22: iconst_0
        //   23: invokeinterface 49 5 0
        //   28: pop
        //   29: aload_3
        //   30: invokevirtual 52	android/os/Parcel:readException	()V
        //   33: aload_3
        //   34: invokevirtual 65	android/os/Parcel:readInt	()I
        //   37: ifeq +26 -> 63
        //   40: getstatic 94	android/support/v4/media/MediaMetadataCompat:CREATOR	Landroid/os/Parcelable$Creator;
        //   43: aload_3
        //   44: invokeinterface 77 2 0
        //   49: checkcast 93	android/support/v4/media/MediaMetadataCompat
        //   52: astore_1
        //   53: aload_3
        //   54: invokevirtual 55	android/os/Parcel:recycle	()V
        //   57: aload_2
        //   58: invokevirtual 55	android/os/Parcel:recycle	()V
        //   61: aload_1
        //   62: areturn
        //   63: aconst_null
        //   64: astore_1
        //   65: goto -12 -> 53
        //   68: astore_1
        //   69: aload_3
        //   70: invokevirtual 55	android/os/Parcel:recycle	()V
        //   73: aload_2
        //   74: invokevirtual 55	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	Proxy
        //   52	13	1	localMediaMetadataCompat	MediaMetadataCompat
        //   68	10	1	localObject	Object
        //   3	71	2	localParcel1	Parcel
        //   7	63	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	53	68	finally
      }
      
      public String getPackageName()
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          this.mRemote.transact(6, localParcel2, localParcel1, 0);
          localParcel1.readException();
          String str = localParcel1.readString();
          return str;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      /* Error */
      public PlaybackStateCompat getPlaybackState()
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_3
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_0
        //   15: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   18: bipush 28
        //   20: aload_3
        //   21: aload_2
        //   22: iconst_0
        //   23: invokeinterface 49 5 0
        //   28: pop
        //   29: aload_2
        //   30: invokevirtual 52	android/os/Parcel:readException	()V
        //   33: aload_2
        //   34: invokevirtual 65	android/os/Parcel:readInt	()I
        //   37: ifeq +26 -> 63
        //   40: getstatic 103	android/support/v4/media/session/PlaybackStateCompat:CREATOR	Landroid/os/Parcelable$Creator;
        //   43: aload_2
        //   44: invokeinterface 77 2 0
        //   49: checkcast 102	android/support/v4/media/session/PlaybackStateCompat
        //   52: astore_1
        //   53: aload_2
        //   54: invokevirtual 55	android/os/Parcel:recycle	()V
        //   57: aload_3
        //   58: invokevirtual 55	android/os/Parcel:recycle	()V
        //   61: aload_1
        //   62: areturn
        //   63: aconst_null
        //   64: astore_1
        //   65: goto -12 -> 53
        //   68: astore_1
        //   69: aload_2
        //   70: invokevirtual 55	android/os/Parcel:recycle	()V
        //   73: aload_3
        //   74: invokevirtual 55	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	Proxy
        //   52	13	1	localPlaybackStateCompat	PlaybackStateCompat
        //   68	10	1	localObject	Object
        //   7	63	2	localParcel1	Parcel
        //   3	71	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	53	68	finally
      }
      
      public List<MediaSessionCompat.QueueItem> getQueue()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          this.mRemote.transact(29, localParcel1, localParcel2, 0);
          localParcel2.readException();
          ArrayList localArrayList = localParcel2.createTypedArrayList(MediaSessionCompat.QueueItem.CREATOR);
          return localArrayList;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public CharSequence getQueueTitle()
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_0
        //   15: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   18: bipush 30
        //   20: aload_2
        //   21: aload_3
        //   22: iconst_0
        //   23: invokeinterface 49 5 0
        //   28: pop
        //   29: aload_3
        //   30: invokevirtual 52	android/os/Parcel:readException	()V
        //   33: aload_3
        //   34: invokevirtual 65	android/os/Parcel:readInt	()I
        //   37: ifeq +26 -> 63
        //   40: getstatic 121	android/text/TextUtils:CHAR_SEQUENCE_CREATOR	Landroid/os/Parcelable$Creator;
        //   43: aload_3
        //   44: invokeinterface 77 2 0
        //   49: checkcast 123	java/lang/CharSequence
        //   52: astore_1
        //   53: aload_3
        //   54: invokevirtual 55	android/os/Parcel:recycle	()V
        //   57: aload_2
        //   58: invokevirtual 55	android/os/Parcel:recycle	()V
        //   61: aload_1
        //   62: areturn
        //   63: aconst_null
        //   64: astore_1
        //   65: goto -12 -> 53
        //   68: astore_1
        //   69: aload_3
        //   70: invokevirtual 55	android/os/Parcel:recycle	()V
        //   73: aload_2
        //   74: invokevirtual 55	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	Proxy
        //   52	13	1	localCharSequence	CharSequence
        //   68	10	1	localObject	Object
        //   3	71	2	localParcel1	Parcel
        //   7	63	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	53	68	finally
      }
      
      public int getRatingType()
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          this.mRemote.transact(32, localParcel2, localParcel1, 0);
          localParcel1.readException();
          int i = localParcel1.readInt();
          return i;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      public String getTag()
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          this.mRemote.transact(7, localParcel2, localParcel1, 0);
          localParcel1.readException();
          String str = localParcel1.readString();
          return str;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      /* Error */
      public ParcelableVolumeInfo getVolumeAttributes()
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_3
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_0
        //   15: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   18: bipush 10
        //   20: aload_3
        //   21: aload_2
        //   22: iconst_0
        //   23: invokeinterface 49 5 0
        //   28: pop
        //   29: aload_2
        //   30: invokevirtual 52	android/os/Parcel:readException	()V
        //   33: aload_2
        //   34: invokevirtual 65	android/os/Parcel:readInt	()I
        //   37: ifeq +26 -> 63
        //   40: getstatic 130	android/support/v4/media/session/ParcelableVolumeInfo:CREATOR	Landroid/os/Parcelable$Creator;
        //   43: aload_2
        //   44: invokeinterface 77 2 0
        //   49: checkcast 129	android/support/v4/media/session/ParcelableVolumeInfo
        //   52: astore_1
        //   53: aload_2
        //   54: invokevirtual 55	android/os/Parcel:recycle	()V
        //   57: aload_3
        //   58: invokevirtual 55	android/os/Parcel:recycle	()V
        //   61: aload_1
        //   62: areturn
        //   63: aconst_null
        //   64: astore_1
        //   65: goto -12 -> 53
        //   68: astore_1
        //   69: aload_2
        //   70: invokevirtual 55	android/os/Parcel:recycle	()V
        //   73: aload_3
        //   74: invokevirtual 55	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	Proxy
        //   52	13	1	localParcelableVolumeInfo	ParcelableVolumeInfo
        //   68	10	1	localObject	Object
        //   7	63	2	localParcel1	Parcel
        //   3	71	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	53	68	finally
      }
      
      public boolean isTransportControlEnabled()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          this.mRemote.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0) {
            bool = true;
          }
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void next()
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          this.mRemote.transact(20, localParcel2, localParcel1, 0);
          localParcel1.readException();
          return;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      public void pause()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          this.mRemote.transact(18, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void play()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          this.mRemote.transact(13, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public void playFromMediaId(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 32
        //   12: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_3
        //   16: aload_1
        //   17: invokevirtual 43	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   20: aload_2
        //   21: ifnull +45 -> 66
        //   24: aload_3
        //   25: iconst_1
        //   26: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   29: aload_2
        //   30: aload_3
        //   31: iconst_0
        //   32: invokevirtual 141	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload_0
        //   36: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   39: bipush 14
        //   41: aload_3
        //   42: aload 4
        //   44: iconst_0
        //   45: invokeinterface 49 5 0
        //   50: pop
        //   51: aload 4
        //   53: invokevirtual 52	android/os/Parcel:readException	()V
        //   56: aload 4
        //   58: invokevirtual 55	android/os/Parcel:recycle	()V
        //   61: aload_3
        //   62: invokevirtual 55	android/os/Parcel:recycle	()V
        //   65: return
        //   66: aload_3
        //   67: iconst_0
        //   68: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   71: goto -36 -> 35
        //   74: astore_1
        //   75: aload 4
        //   77: invokevirtual 55	android/os/Parcel:recycle	()V
        //   80: aload_3
        //   81: invokevirtual 55	android/os/Parcel:recycle	()V
        //   84: aload_1
        //   85: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	86	0	this	Proxy
        //   0	86	1	paramString	String
        //   0	86	2	paramBundle	Bundle
        //   3	78	3	localParcel1	Parcel
        //   7	69	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	20	74	finally
        //   24	35	74	finally
        //   35	56	74	finally
        //   66	71	74	finally
      }
      
      /* Error */
      public void playFromSearch(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore_3
        //   9: aload 4
        //   11: ldc 32
        //   13: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   16: aload 4
        //   18: aload_1
        //   19: invokevirtual 43	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   22: aload_2
        //   23: ifnull +46 -> 69
        //   26: aload 4
        //   28: iconst_1
        //   29: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   32: aload_2
        //   33: aload 4
        //   35: iconst_0
        //   36: invokevirtual 141	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   39: aload_0
        //   40: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   43: bipush 15
        //   45: aload 4
        //   47: aload_3
        //   48: iconst_0
        //   49: invokeinterface 49 5 0
        //   54: pop
        //   55: aload_3
        //   56: invokevirtual 52	android/os/Parcel:readException	()V
        //   59: aload_3
        //   60: invokevirtual 55	android/os/Parcel:recycle	()V
        //   63: aload 4
        //   65: invokevirtual 55	android/os/Parcel:recycle	()V
        //   68: return
        //   69: aload 4
        //   71: iconst_0
        //   72: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   75: goto -36 -> 39
        //   78: astore_1
        //   79: aload_3
        //   80: invokevirtual 55	android/os/Parcel:recycle	()V
        //   83: aload 4
        //   85: invokevirtual 55	android/os/Parcel:recycle	()V
        //   88: aload_1
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	Proxy
        //   0	90	1	paramString	String
        //   0	90	2	paramBundle	Bundle
        //   8	72	3	localParcel1	Parcel
        //   3	81	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	22	78	finally
        //   26	39	78	finally
        //   39	59	78	finally
        //   69	75	78	finally
      }
      
      public void playFromUri(Uri paramUri, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel2.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (paramUri != null)
            {
              localParcel2.writeInt(1);
              paramUri.writeToParcel(localParcel2, 0);
              if (paramBundle != null)
              {
                localParcel2.writeInt(1);
                paramBundle.writeToParcel(localParcel2, 0);
                this.mRemote.transact(16, localParcel2, localParcel1, 0);
                localParcel1.readException();
              }
            }
            else
            {
              localParcel2.writeInt(0);
              continue;
            }
            localParcel2.writeInt(0);
          }
          finally
          {
            localParcel1.recycle();
            localParcel2.recycle();
          }
        }
      }
      
      public void previous()
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          this.mRemote.transact(21, localParcel2, localParcel1, 0);
          localParcel1.readException();
          return;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      /* Error */
      public void rate(RatingCompat paramRatingCompat)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_3
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +42 -> 57
        //   18: aload_3
        //   19: iconst_1
        //   20: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_3
        //   25: iconst_0
        //   26: invokevirtual 153	android/support/v4/media/RatingCompat:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   33: bipush 25
        //   35: aload_3
        //   36: aload_2
        //   37: iconst_0
        //   38: invokeinterface 49 5 0
        //   43: pop
        //   44: aload_2
        //   45: invokevirtual 52	android/os/Parcel:readException	()V
        //   48: aload_2
        //   49: invokevirtual 55	android/os/Parcel:recycle	()V
        //   52: aload_3
        //   53: invokevirtual 55	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aload_3
        //   58: iconst_0
        //   59: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   62: goto -33 -> 29
        //   65: astore_1
        //   66: aload_2
        //   67: invokevirtual 55	android/os/Parcel:recycle	()V
        //   70: aload_3
        //   71: invokevirtual 55	android/os/Parcel:recycle	()V
        //   74: aload_1
        //   75: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	76	0	this	Proxy
        //   0	76	1	paramRatingCompat	RatingCompat
        //   7	60	2	localParcel1	Parcel
        //   3	68	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	65	finally
        //   18	29	65	finally
        //   29	48	65	finally
        //   57	62	65	finally
      }
      
      /* Error */
      public void registerCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_3
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +42 -> 57
        //   18: aload_1
        //   19: invokeinterface 159 1 0
        //   24: astore_1
        //   25: aload_3
        //   26: aload_1
        //   27: invokevirtual 162	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   34: iconst_3
        //   35: aload_3
        //   36: aload_2
        //   37: iconst_0
        //   38: invokeinterface 49 5 0
        //   43: pop
        //   44: aload_2
        //   45: invokevirtual 52	android/os/Parcel:readException	()V
        //   48: aload_2
        //   49: invokevirtual 55	android/os/Parcel:recycle	()V
        //   52: aload_3
        //   53: invokevirtual 55	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -34 -> 25
        //   62: astore_1
        //   63: aload_2
        //   64: invokevirtual 55	android/os/Parcel:recycle	()V
        //   67: aload_3
        //   68: invokevirtual 55	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	Proxy
        //   0	73	1	paramIMediaControllerCallback	IMediaControllerCallback
        //   7	57	2	localParcel1	Parcel
        //   3	65	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	62	finally
        //   18	25	62	finally
        //   25	48	62	finally
      }
      
      public void rewind()
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          this.mRemote.transact(23, localParcel2, localParcel1, 0);
          localParcel1.readException();
          return;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      public void seekTo(long paramLong)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          localParcel2.writeLong(paramLong);
          this.mRemote.transact(24, localParcel2, localParcel1, 0);
          localParcel1.readException();
          return;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      public void sendCommand(String paramString, Bundle paramBundle, MediaSessionCompat.ResultReceiverWrapper paramResultReceiverWrapper)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel2.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            localParcel2.writeString(paramString);
            if (paramBundle != null)
            {
              localParcel2.writeInt(1);
              paramBundle.writeToParcel(localParcel2, 0);
              if (paramResultReceiverWrapper != null)
              {
                localParcel2.writeInt(1);
                paramResultReceiverWrapper.writeToParcel(localParcel2, 0);
                this.mRemote.transact(1, localParcel2, localParcel1, 0);
                localParcel1.readException();
              }
            }
            else
            {
              localParcel2.writeInt(0);
              continue;
            }
            localParcel2.writeInt(0);
          }
          finally
          {
            localParcel1.recycle();
            localParcel2.recycle();
          }
        }
      }
      
      /* Error */
      public void sendCustomAction(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 32
        //   12: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_3
        //   16: aload_1
        //   17: invokevirtual 43	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   20: aload_2
        //   21: ifnull +45 -> 66
        //   24: aload_3
        //   25: iconst_1
        //   26: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   29: aload_2
        //   30: aload_3
        //   31: iconst_0
        //   32: invokevirtual 141	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload_0
        //   36: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   39: bipush 26
        //   41: aload_3
        //   42: aload 4
        //   44: iconst_0
        //   45: invokeinterface 49 5 0
        //   50: pop
        //   51: aload 4
        //   53: invokevirtual 52	android/os/Parcel:readException	()V
        //   56: aload 4
        //   58: invokevirtual 55	android/os/Parcel:recycle	()V
        //   61: aload_3
        //   62: invokevirtual 55	android/os/Parcel:recycle	()V
        //   65: return
        //   66: aload_3
        //   67: iconst_0
        //   68: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   71: goto -36 -> 35
        //   74: astore_1
        //   75: aload 4
        //   77: invokevirtual 55	android/os/Parcel:recycle	()V
        //   80: aload_3
        //   81: invokevirtual 55	android/os/Parcel:recycle	()V
        //   84: aload_1
        //   85: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	86	0	this	Proxy
        //   0	86	1	paramString	String
        //   0	86	2	paramBundle	Bundle
        //   3	78	3	localParcel1	Parcel
        //   7	69	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	20	74	finally
        //   24	35	74	finally
        //   35	56	74	finally
        //   66	71	74	finally
      }
      
      public boolean sendMediaButton(KeyEvent paramKeyEvent)
        throws RemoteException
      {
        boolean bool = true;
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel2.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (paramKeyEvent != null)
            {
              localParcel2.writeInt(1);
              paramKeyEvent.writeToParcel(localParcel2, 0);
              this.mRemote.transact(2, localParcel2, localParcel1, 0);
              localParcel1.readException();
              int i = localParcel1.readInt();
              if (i != 0) {
                return bool;
              }
            }
            else
            {
              localParcel2.writeInt(0);
              continue;
            }
            bool = false;
          }
          finally
          {
            localParcel1.recycle();
            localParcel2.recycle();
          }
        }
      }
      
      public void setVolumeTo(int paramInt1, int paramInt2, String paramString)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          localParcel2.writeInt(paramInt1);
          localParcel2.writeInt(paramInt2);
          localParcel2.writeString(paramString);
          this.mRemote.transact(12, localParcel2, localParcel1, 0);
          localParcel1.readException();
          return;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
      
      public void skipToQueueItem(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          localParcel1.writeLong(paramLong);
          this.mRemote.transact(17, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void stop()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          this.mRemote.transact(19, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public void unregisterCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +42 -> 57
        //   18: aload_1
        //   19: invokeinterface 159 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 162	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   34: iconst_4
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 49 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 52	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 55	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 55	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -34 -> 25
        //   62: astore_1
        //   63: aload_3
        //   64: invokevirtual 55	android/os/Parcel:recycle	()V
        //   67: aload_2
        //   68: invokevirtual 55	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	Proxy
        //   0	73	1	paramIMediaControllerCallback	IMediaControllerCallback
        //   3	65	2	localParcel1	Parcel
        //   7	57	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	62	finally
        //   18	25	62	finally
        //   25	48	62	finally
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\media\session\IMediaSession.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */