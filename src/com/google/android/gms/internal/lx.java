package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.a;
import com.google.android.gms.location.a.a;
import com.google.android.gms.location.b;
import com.google.android.gms.location.c;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.g;
import com.google.android.gms.maps.model.i;
import java.util.List;

public abstract interface lx
  extends IInterface
{
  public abstract void a(long paramLong, boolean paramBoolean, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void a(PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void a(PendingIntent paramPendingIntent, lw paramlw, String paramString)
    throws RemoteException;
  
  public abstract void a(Location paramLocation, int paramInt)
    throws RemoteException;
  
  public abstract void a(lw paramlw, String paramString)
    throws RemoteException;
  
  public abstract void a(ma paramma, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void a(ma paramma, a parama)
    throws RemoteException;
  
  public abstract void a(mh parammh, mx parammx, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void a(mj parammj, mx parammx, mv parammv)
    throws RemoteException;
  
  public abstract void a(ml paramml, mx parammx)
    throws RemoteException;
  
  public abstract void a(mn parammn, mx parammx, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void a(mr parammr, mx parammx, mv parammv)
    throws RemoteException;
  
  public abstract void a(mt parammt, LatLngBounds paramLatLngBounds, List<String> paramList, mx parammx, mv parammv)
    throws RemoteException;
  
  public abstract void a(mx parammx, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void a(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void a(LocationRequest paramLocationRequest, a parama)
    throws RemoteException;
  
  public abstract void a(LocationRequest paramLocationRequest, a parama, String paramString)
    throws RemoteException;
  
  public abstract void a(a parama)
    throws RemoteException;
  
  public abstract void a(LatLng paramLatLng, mj parammj, mx parammx, mv parammv)
    throws RemoteException;
  
  public abstract void a(LatLngBounds paramLatLngBounds, int paramInt, mj parammj, mx parammx, mv parammv)
    throws RemoteException;
  
  public abstract void a(LatLngBounds paramLatLngBounds, int paramInt, String paramString, mj parammj, mx parammx, mv parammv)
    throws RemoteException;
  
  public abstract void a(String paramString, mx parammx, mv parammv)
    throws RemoteException;
  
  public abstract void a(String paramString, LatLngBounds paramLatLngBounds, mf parammf, mx parammx, mv parammv)
    throws RemoteException;
  
  public abstract void a(List<mc> paramList, PendingIntent paramPendingIntent, lw paramlw, String paramString)
    throws RemoteException;
  
  public abstract void a(String[] paramArrayOfString, lw paramlw, String paramString)
    throws RemoteException;
  
  public abstract void b(mx parammx, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void b(String paramString, mx parammx, mv parammv)
    throws RemoteException;
  
  public abstract Location bW(String paramString)
    throws RemoteException;
  
  public abstract c bX(String paramString)
    throws RemoteException;
  
  public abstract Location lV()
    throws RemoteException;
  
  public abstract IBinder lW()
    throws RemoteException;
  
  public abstract IBinder lX()
    throws RemoteException;
  
  public abstract void removeActivityUpdates(PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void setMockLocation(Location paramLocation)
    throws RemoteException;
  
  public abstract void setMockMode(boolean paramBoolean)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements lx
  {
    public static lx aK(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        paramIBinder = null;
      }
      for (;;)
      {
        return paramIBinder;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        if ((localIInterface != null) && ((localIInterface instanceof lx))) {
          paramIBinder = (lx)localIInterface;
        } else {
          paramIBinder = new a(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool1 = false;
      boolean bool2 = true;
      Object localObject9 = null;
      Object localObject10 = null;
      Object localObject3 = null;
      Object localObject6 = null;
      Object localObject4 = null;
      Object localObject2 = null;
      Object localObject7 = null;
      Object localObject5 = null;
      Object localObject1 = null;
      Object localObject8 = null;
      switch (paramInt1)
      {
      default: 
        bool1 = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool1;
        paramParcel2.writeString("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        localObject2 = paramParcel1.createTypedArrayList(mc.CREATOR);
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
        {
          a((List)localObject2, (PendingIntent)localObject1, lw.a.aJ(paramParcel1.readStrongBinder()), paramParcel1.readString());
          paramParcel2.writeNoException();
          bool1 = bool2;
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
        {
          a((PendingIntent)localObject1, lw.a.aJ(paramParcel1.readStrongBinder()), paramParcel1.readString());
          paramParcel2.writeNoException();
          bool1 = bool2;
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        a(paramParcel1.createStringArray(), lw.a.aJ(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        a(lw.a.aJ(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        bool1 = bool2;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        long l = paramParcel1.readLong();
        if (paramParcel1.readInt() != 0)
        {
          bool1 = true;
          label586:
          if (paramParcel1.readInt() == 0) {
            break label632;
          }
        }
        label632:
        for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          a(l, bool1, paramParcel1);
          paramParcel2.writeNoException();
          bool1 = bool2;
          break;
          bool1 = false;
          break label586;
        }
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          removeActivityUpdates(paramParcel1);
          paramParcel2.writeNoException();
          bool1 = bool2;
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        paramParcel1 = lV();
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
          bool1 = bool2;
        }
        else
        {
          paramParcel2.writeInt(0);
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          localObject1 = localObject8;
          if (paramParcel1.readInt() != 0) {
            localObject1 = LocationRequest.CREATOR.cs(paramParcel1);
          }
          a((LocationRequest)localObject1, a.a.aI(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          localObject1 = localObject9;
          if (paramParcel1.readInt() != 0) {
            localObject1 = LocationRequest.CREATOR.cs(paramParcel1);
          }
          a((LocationRequest)localObject1, a.a.aI(paramParcel1.readStrongBinder()), paramParcel1.readString());
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramParcel1.readInt() != 0)
          {
            localObject1 = LocationRequest.CREATOR.cs(paramParcel1);
            label859:
            if (paramParcel1.readInt() == 0) {
              break label903;
            }
          }
          label903:
          for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
          {
            a((LocationRequest)localObject1, paramParcel1);
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
            localObject1 = null;
            break label859;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          localObject1 = localObject10;
          if (paramParcel1.readInt() != 0) {
            localObject1 = ma.CREATOR.cv(paramParcel1);
          }
          a((ma)localObject1, a.a.aI(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramParcel1.readInt() != 0)
          {
            localObject1 = ma.CREATOR.cv(paramParcel1);
            label980:
            if (paramParcel1.readInt() == 0) {
              break label1024;
            }
          }
          label1024:
          for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
          {
            a((ma)localObject1, paramParcel1);
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
            localObject1 = null;
            break label980;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          a(a.a.aI(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramParcel1.readInt() != 0) {}
          for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
          {
            a(paramParcel1);
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          }
          setMockMode(bool1);
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramParcel1.readInt() != 0) {}
          for (paramParcel1 = (Location)Location.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
          {
            setMockLocation(paramParcel1);
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramParcel1.readInt() != 0)
          {
            localObject1 = LatLngBounds.CREATOR.cL(paramParcel1);
            label1206:
            paramInt1 = paramParcel1.readInt();
            if (paramParcel1.readInt() == 0) {
              break label1278;
            }
            localObject2 = mj.CREATOR.cz(paramParcel1);
            label1227:
            if (paramParcel1.readInt() == 0) {
              break label1284;
            }
          }
          label1278:
          label1284:
          for (localObject3 = mx.CREATOR.cF(paramParcel1);; localObject3 = null)
          {
            a((LatLngBounds)localObject1, paramInt1, (mj)localObject2, (mx)localObject3, mv.a.aM(paramParcel1.readStrongBinder()));
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
            localObject1 = null;
            break label1206;
            localObject2 = null;
            break label1227;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramParcel1.readInt() != 0)
          {
            localObject1 = LatLngBounds.CREATOR.cL(paramParcel1);
            label1312:
            paramInt1 = paramParcel1.readInt();
            localObject4 = paramParcel1.readString();
            if (paramParcel1.readInt() == 0) {
              break label1392;
            }
          }
          label1392:
          for (localObject2 = mj.CREATOR.cz(paramParcel1);; localObject2 = null)
          {
            if (paramParcel1.readInt() != 0) {
              localObject3 = mx.CREATOR.cF(paramParcel1);
            }
            a((LatLngBounds)localObject1, paramInt1, (String)localObject4, (mj)localObject2, (mx)localObject3, mv.a.aM(paramParcel1.readStrongBinder()));
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
            localObject1 = null;
            break label1312;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          localObject2 = paramParcel1.readString();
          localObject1 = localObject6;
          if (paramParcel1.readInt() != 0) {
            localObject1 = mx.CREATOR.cF(paramParcel1);
          }
          a((String)localObject2, (mx)localObject1, mv.a.aM(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramParcel1.readInt() != 0)
          {
            localObject1 = LatLng.CREATOR.cM(paramParcel1);
            label1478:
            if (paramParcel1.readInt() == 0) {
              break label1548;
            }
          }
          label1548:
          for (localObject2 = mj.CREATOR.cz(paramParcel1);; localObject2 = null)
          {
            localObject3 = localObject4;
            if (paramParcel1.readInt() != 0) {
              localObject3 = mx.CREATOR.cF(paramParcel1);
            }
            a((LatLng)localObject1, (mj)localObject2, (mx)localObject3, mv.a.aM(paramParcel1.readStrongBinder()));
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
            localObject1 = null;
            break label1478;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramParcel1.readInt() != 0) {}
          for (localObject1 = mj.CREATOR.cz(paramParcel1);; localObject1 = null)
          {
            if (paramParcel1.readInt() != 0) {
              localObject2 = mx.CREATOR.cF(paramParcel1);
            }
            a((mj)localObject1, (mx)localObject2, mv.a.aM(paramParcel1.readStrongBinder()));
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          localObject2 = paramParcel1.readString();
          localObject1 = localObject7;
          if (paramParcel1.readInt() != 0) {
            localObject1 = mx.CREATOR.cF(paramParcel1);
          }
          b((String)localObject2, (mx)localObject1, mv.a.aM(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          bool1 = bool2;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramParcel1.readInt() != 0)
          {
            localObject1 = mt.CREATOR.cE(paramParcel1);
            label1704:
            if (paramParcel1.readInt() == 0) {
              break label1778;
            }
            localObject2 = LatLngBounds.CREATOR.cL(paramParcel1);
            label1720:
            localObject4 = paramParcel1.createStringArrayList();
            if (paramParcel1.readInt() == 0) {
              break label1784;
            }
          }
          label1778:
          label1784:
          for (localObject3 = mx.CREATOR.cF(paramParcel1);; localObject3 = null)
          {
            a((mt)localObject1, (LatLngBounds)localObject2, (List)localObject4, (mx)localObject3, mv.a.aM(paramParcel1.readStrongBinder()));
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
            localObject1 = null;
            break label1704;
            localObject2 = null;
            break label1720;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramParcel1.readInt() != 0)
          {
            localObject1 = mn.CREATOR.cB(paramParcel1);
            label1812:
            if (paramParcel1.readInt() == 0) {
              break label1874;
            }
            localObject2 = mx.CREATOR.cF(paramParcel1);
            label1828:
            if (paramParcel1.readInt() == 0) {
              break label1880;
            }
          }
          label1874:
          label1880:
          for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
          {
            a((mn)localObject1, (mx)localObject2, paramParcel1);
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
            localObject1 = null;
            break label1812;
            localObject2 = null;
            break label1828;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramParcel1.readInt() != 0)
          {
            localObject1 = mx.CREATOR.cF(paramParcel1);
            label1907:
            if (paramParcel1.readInt() == 0) {
              break label1951;
            }
          }
          label1951:
          for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
          {
            a((mx)localObject1, paramParcel1);
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
            localObject1 = null;
            break label1907;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramParcel1.readInt() != 0)
          {
            localObject1 = mh.CREATOR.cy(paramParcel1);
            label1978:
            if (paramParcel1.readInt() == 0) {
              break label2040;
            }
            localObject2 = mx.CREATOR.cF(paramParcel1);
            label1994:
            if (paramParcel1.readInt() == 0) {
              break label2046;
            }
          }
          label2040:
          label2046:
          for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
          {
            a((mh)localObject1, (mx)localObject2, paramParcel1);
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
            localObject1 = null;
            break label1978;
            localObject2 = null;
            break label1994;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramParcel1.readInt() != 0)
          {
            localObject1 = mx.CREATOR.cF(paramParcel1);
            label2073:
            if (paramParcel1.readInt() == 0) {
              break label2117;
            }
          }
          label2117:
          for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
          {
            b((mx)localObject1, paramParcel1);
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
            localObject1 = null;
            break label2073;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          localObject4 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0)
          {
            localObject1 = LatLngBounds.CREATOR.cL(paramParcel1);
            label2150:
            if (paramParcel1.readInt() == 0) {
              break label2218;
            }
            localObject2 = mf.CREATOR.cx(paramParcel1);
            label2166:
            if (paramParcel1.readInt() == 0) {
              break label2224;
            }
          }
          label2218:
          label2224:
          for (localObject3 = mx.CREATOR.cF(paramParcel1);; localObject3 = null)
          {
            a((String)localObject4, (LatLngBounds)localObject1, (mf)localObject2, (mx)localObject3, mv.a.aM(paramParcel1.readStrongBinder()));
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
            localObject1 = null;
            break label2150;
            localObject2 = null;
            break label2166;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramParcel1.readInt() != 0) {}
          for (localObject1 = (mr)mr.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
          {
            localObject2 = localObject5;
            if (paramParcel1.readInt() != 0) {
              localObject2 = mx.CREATOR.cF(paramParcel1);
            }
            a((mr)localObject1, (mx)localObject2, mv.a.aM(paramParcel1.readStrongBinder()));
            paramParcel2.writeNoException();
            bool1 = bool2;
            break;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          paramParcel1 = bW(paramParcel1.readString());
          paramParcel2.writeNoException();
          if (paramParcel1 != null)
          {
            paramParcel2.writeInt(1);
            paramParcel1.writeToParcel(paramParcel2, 1);
            bool1 = bool2;
          }
          else
          {
            paramParcel2.writeInt(0);
            bool1 = bool2;
            continue;
            paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramParcel1.readInt() != 0) {}
            for (paramParcel2 = ml.CREATOR.cA(paramParcel1);; paramParcel2 = null)
            {
              if (paramParcel1.readInt() != 0) {
                localObject1 = mx.CREATOR.cF(paramParcel1);
              }
              a(paramParcel2, (mx)localObject1);
              bool1 = bool2;
              break;
            }
            paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramParcel1.readInt() != 0) {}
            for (localObject1 = (Location)Location.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
            {
              a((Location)localObject1, paramParcel1.readInt());
              paramParcel2.writeNoException();
              bool1 = bool2;
              break;
            }
            paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            paramParcel1 = bX(paramParcel1.readString());
            paramParcel2.writeNoException();
            if (paramParcel1 != null)
            {
              paramParcel2.writeInt(1);
              paramParcel1.writeToParcel(paramParcel2, 1);
              bool1 = bool2;
            }
            else
            {
              paramParcel2.writeInt(0);
              bool1 = bool2;
              continue;
              paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
              paramParcel1 = lW();
              paramParcel2.writeNoException();
              paramParcel2.writeStrongBinder(paramParcel1);
              bool1 = bool2;
              continue;
              paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
              paramParcel1 = lX();
              paramParcel2.writeNoException();
              paramParcel2.writeStrongBinder(paramParcel1);
              bool1 = bool2;
            }
          }
        }
      }
    }
    
    private static class a
      implements lx
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      /* Error */
      public void a(long paramLong, boolean paramBoolean, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 5
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 7
        //   15: ldc 30
        //   17: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload 7
        //   22: lload_1
        //   23: invokevirtual 38	android/os/Parcel:writeLong	(J)V
        //   26: iload_3
        //   27: ifeq +61 -> 88
        //   30: aload 7
        //   32: iload 5
        //   34: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   37: aload 4
        //   39: ifnull +55 -> 94
        //   42: aload 7
        //   44: iconst_1
        //   45: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   48: aload 4
        //   50: aload 7
        //   52: iconst_0
        //   53: invokevirtual 48	android/app/PendingIntent:writeToParcel	(Landroid/os/Parcel;I)V
        //   56: aload_0
        //   57: getfield 18	com/google/android/gms/internal/lx$a$a:lb	Landroid/os/IBinder;
        //   60: iconst_5
        //   61: aload 7
        //   63: aload 6
        //   65: iconst_0
        //   66: invokeinterface 54 5 0
        //   71: pop
        //   72: aload 6
        //   74: invokevirtual 57	android/os/Parcel:readException	()V
        //   77: aload 6
        //   79: invokevirtual 60	android/os/Parcel:recycle	()V
        //   82: aload 7
        //   84: invokevirtual 60	android/os/Parcel:recycle	()V
        //   87: return
        //   88: iconst_0
        //   89: istore 5
        //   91: goto -61 -> 30
        //   94: aload 7
        //   96: iconst_0
        //   97: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   100: goto -44 -> 56
        //   103: astore 4
        //   105: aload 6
        //   107: invokevirtual 60	android/os/Parcel:recycle	()V
        //   110: aload 7
        //   112: invokevirtual 60	android/os/Parcel:recycle	()V
        //   115: aload 4
        //   117: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	118	0	this	a
        //   0	118	1	paramLong	long
        //   0	118	3	paramBoolean	boolean
        //   0	118	4	paramPendingIntent	PendingIntent
        //   1	89	5	i	int
        //   11	95	6	localParcel1	Parcel
        //   6	105	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	26	103	finally
        //   30	37	103	finally
        //   42	56	103	finally
        //   56	77	103	finally
        //   94	100	103	finally
      }
      
      /* Error */
      public void a(PendingIntent paramPendingIntent)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 30
        //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +42 -> 57
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 48	android/app/PendingIntent:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/internal/lx$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 11
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 54 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 57	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 60	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 60	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aload_2
        //   58: iconst_0
        //   59: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   62: goto -33 -> 29
        //   65: astore_1
        //   66: aload_3
        //   67: invokevirtual 60	android/os/Parcel:recycle	()V
        //   70: aload_2
        //   71: invokevirtual 60	android/os/Parcel:recycle	()V
        //   74: aload_1
        //   75: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	76	0	this	a
        //   0	76	1	paramPendingIntent	PendingIntent
        //   3	68	2	localParcel1	Parcel
        //   7	60	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	65	finally
        //   18	29	65	finally
        //   29	48	65	finally
        //   57	62	65	finally
      }
      
      public void a(PendingIntent paramPendingIntent, lw paramlw, String paramString)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel2.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramPendingIntent != null)
            {
              localParcel2.writeInt(1);
              paramPendingIntent.writeToParcel(localParcel2, 0);
              if (paramlw != null)
              {
                paramPendingIntent = paramlw.asBinder();
                localParcel2.writeStrongBinder(paramPendingIntent);
                localParcel2.writeString(paramString);
                this.lb.transact(2, localParcel2, localParcel1, 0);
                localParcel1.readException();
              }
            }
            else
            {
              localParcel2.writeInt(0);
              continue;
            }
            paramPendingIntent = null;
          }
          finally
          {
            localParcel1.recycle();
            localParcel2.recycle();
          }
        }
      }
      
      /* Error */
      public void a(Location paramLocation, int paramInt)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore_3
        //   9: aload 4
        //   11: ldc 30
        //   13: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   16: aload_1
        //   17: ifnull +52 -> 69
        //   20: aload 4
        //   22: iconst_1
        //   23: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   26: aload_1
        //   27: aload 4
        //   29: iconst_0
        //   30: invokevirtual 79	android/location/Location:writeToParcel	(Landroid/os/Parcel;I)V
        //   33: aload 4
        //   35: iload_2
        //   36: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   39: aload_0
        //   40: getfield 18	com/google/android/gms/internal/lx$a$a:lb	Landroid/os/IBinder;
        //   43: bipush 26
        //   45: aload 4
        //   47: aload_3
        //   48: iconst_0
        //   49: invokeinterface 54 5 0
        //   54: pop
        //   55: aload_3
        //   56: invokevirtual 57	android/os/Parcel:readException	()V
        //   59: aload_3
        //   60: invokevirtual 60	android/os/Parcel:recycle	()V
        //   63: aload 4
        //   65: invokevirtual 60	android/os/Parcel:recycle	()V
        //   68: return
        //   69: aload 4
        //   71: iconst_0
        //   72: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   75: goto -42 -> 33
        //   78: astore_1
        //   79: aload_3
        //   80: invokevirtual 60	android/os/Parcel:recycle	()V
        //   83: aload 4
        //   85: invokevirtual 60	android/os/Parcel:recycle	()V
        //   88: aload_1
        //   89: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	90	0	this	a
        //   0	90	1	paramLocation	Location
        //   0	90	2	paramInt	int
        //   8	72	3	localParcel1	Parcel
        //   3	81	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	16	78	finally
        //   20	33	78	finally
        //   33	59	78	finally
        //   69	75	78	finally
      }
      
      /* Error */
      public void a(lw paramlw, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore_3
        //   9: aload 4
        //   11: ldc 30
        //   13: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   16: aload_1
        //   17: ifnull +51 -> 68
        //   20: aload_1
        //   21: invokeinterface 69 1 0
        //   26: astore_1
        //   27: aload 4
        //   29: aload_1
        //   30: invokevirtual 72	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload 4
        //   35: aload_2
        //   36: invokevirtual 75	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   39: aload_0
        //   40: getfield 18	com/google/android/gms/internal/lx$a$a:lb	Landroid/os/IBinder;
        //   43: iconst_4
        //   44: aload 4
        //   46: aload_3
        //   47: iconst_0
        //   48: invokeinterface 54 5 0
        //   53: pop
        //   54: aload_3
        //   55: invokevirtual 57	android/os/Parcel:readException	()V
        //   58: aload_3
        //   59: invokevirtual 60	android/os/Parcel:recycle	()V
        //   62: aload 4
        //   64: invokevirtual 60	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -43 -> 27
        //   73: astore_1
        //   74: aload_3
        //   75: invokevirtual 60	android/os/Parcel:recycle	()V
        //   78: aload 4
        //   80: invokevirtual 60	android/os/Parcel:recycle	()V
        //   83: aload_1
        //   84: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	85	0	this	a
        //   0	85	1	paramlw	lw
        //   0	85	2	paramString	String
        //   8	67	3	localParcel1	Parcel
        //   3	76	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	16	73	finally
        //   20	27	73	finally
        //   27	58	73	finally
      }
      
      public void a(ma paramma, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel2.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramma != null)
            {
              localParcel2.writeInt(1);
              paramma.writeToParcel(localParcel2, 0);
              if (paramPendingIntent != null)
              {
                localParcel2.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel2, 0);
                this.lb.transact(53, localParcel2, localParcel1, 0);
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
      
      public void a(ma paramma, a parama)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramma != null)
            {
              localParcel1.writeInt(1);
              paramma.writeToParcel(localParcel1, 0);
              if (parama != null)
              {
                paramma = parama.asBinder();
                localParcel1.writeStrongBinder(paramma);
                this.lb.transact(52, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramma = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(mh parammh, mx parammx, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (parammh != null)
            {
              localParcel1.writeInt(1);
              parammh.writeToParcel(localParcel1, 0);
              if (parammx != null)
              {
                localParcel1.writeInt(1);
                parammx.writeToParcel(localParcel1, 0);
                if (paramPendingIntent == null) {
                  break label132;
                }
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                this.lb.transact(48, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label132:
          localParcel1.writeInt(0);
        }
      }
      
      public void a(mj parammj, mx parammx, mv parammv)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel2.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (parammj != null)
            {
              localParcel2.writeInt(1);
              parammj.writeToParcel(localParcel2, 0);
              if (parammx != null)
              {
                localParcel2.writeInt(1);
                parammx.writeToParcel(localParcel2, 0);
                if (parammv == null) {
                  break label132;
                }
                parammj = parammv.asBinder();
                localParcel2.writeStrongBinder(parammj);
                this.lb.transact(17, localParcel2, localParcel1, 0);
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
          continue;
          label132:
          parammj = null;
        }
      }
      
      public void a(ml paramml, mx parammx)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramml != null)
            {
              localParcel.writeInt(1);
              paramml.writeToParcel(localParcel, 0);
              if (parammx != null)
              {
                localParcel.writeInt(1);
                parammx.writeToParcel(localParcel, 0);
                this.lb.transact(25, localParcel, null, 1);
              }
            }
            else
            {
              localParcel.writeInt(0);
              continue;
            }
            localParcel.writeInt(0);
          }
          finally
          {
            localParcel.recycle();
          }
        }
      }
      
      public void a(mn parammn, mx parammx, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (parammn != null)
            {
              localParcel1.writeInt(1);
              parammn.writeToParcel(localParcel1, 0);
              if (parammx != null)
              {
                localParcel1.writeInt(1);
                parammx.writeToParcel(localParcel1, 0);
                if (paramPendingIntent == null) {
                  break label132;
                }
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                this.lb.transact(18, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label132:
          localParcel1.writeInt(0);
        }
      }
      
      public void a(mr parammr, mx parammx, mv parammv)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel2.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (parammr != null)
            {
              localParcel2.writeInt(1);
              parammr.writeToParcel(localParcel2, 0);
              if (parammx != null)
              {
                localParcel2.writeInt(1);
                parammx.writeToParcel(localParcel2, 0);
                if (parammv == null) {
                  break label132;
                }
                parammr = parammv.asBinder();
                localParcel2.writeStrongBinder(parammr);
                this.lb.transact(46, localParcel2, localParcel1, 0);
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
          continue;
          label132:
          parammr = null;
        }
      }
      
      public void a(mt parammt, LatLngBounds paramLatLngBounds, List<String> paramList, mx parammx, mv parammv)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel2.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (parammt != null)
            {
              localParcel2.writeInt(1);
              parammt.writeToParcel(localParcel2, 0);
              if (paramLatLngBounds != null)
              {
                localParcel2.writeInt(1);
                paramLatLngBounds.writeToParcel(localParcel2, 0);
                localParcel2.writeStringList(paramList);
                if (parammx == null) {
                  break label159;
                }
                localParcel2.writeInt(1);
                parammx.writeToParcel(localParcel2, 0);
                if (parammv == null) {
                  break label168;
                }
                parammt = parammv.asBinder();
                localParcel2.writeStrongBinder(parammt);
                this.lb.transact(50, localParcel2, localParcel1, 0);
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
          continue;
          label159:
          localParcel2.writeInt(0);
          continue;
          label168:
          parammt = null;
        }
      }
      
      public void a(mx parammx, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel2.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (parammx != null)
            {
              localParcel2.writeInt(1);
              parammx.writeToParcel(localParcel2, 0);
              if (paramPendingIntent != null)
              {
                localParcel2.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel2, 0);
                this.lb.transact(19, localParcel2, localParcel1, 0);
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
      
      public void a(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramLocationRequest != null)
            {
              localParcel1.writeInt(1);
              paramLocationRequest.writeToParcel(localParcel1, 0);
              if (paramPendingIntent != null)
              {
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                this.lb.transact(9, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(LocationRequest paramLocationRequest, a parama)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramLocationRequest != null)
            {
              localParcel1.writeInt(1);
              paramLocationRequest.writeToParcel(localParcel1, 0);
              if (parama != null)
              {
                paramLocationRequest = parama.asBinder();
                localParcel1.writeStrongBinder(paramLocationRequest);
                this.lb.transact(8, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramLocationRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(LocationRequest paramLocationRequest, a parama, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramLocationRequest != null)
            {
              localParcel1.writeInt(1);
              paramLocationRequest.writeToParcel(localParcel1, 0);
              if (parama != null)
              {
                paramLocationRequest = parama.asBinder();
                localParcel1.writeStrongBinder(paramLocationRequest);
                localParcel1.writeString(paramString);
                this.lb.transact(20, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramLocationRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public void a(a parama)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_3
        //   9: ldc 30
        //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 88 1 0
        //   24: astore_1
        //   25: aload_3
        //   26: aload_1
        //   27: invokevirtual 72	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/internal/lx$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 10
        //   36: aload_3
        //   37: aload_2
        //   38: iconst_0
        //   39: invokeinterface 54 5 0
        //   44: pop
        //   45: aload_2
        //   46: invokevirtual 57	android/os/Parcel:readException	()V
        //   49: aload_2
        //   50: invokevirtual 60	android/os/Parcel:recycle	()V
        //   53: aload_3
        //   54: invokevirtual 60	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_2
        //   65: invokevirtual 60	android/os/Parcel:recycle	()V
        //   68: aload_3
        //   69: invokevirtual 60	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	parama	a
        //   7	58	2	localParcel1	Parcel
        //   3	66	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      public void a(LatLng paramLatLng, mj parammj, mx parammx, mv parammv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramLatLng != null)
            {
              localParcel1.writeInt(1);
              paramLatLng.writeToParcel(localParcel1, 0);
              if (parammj != null)
              {
                localParcel1.writeInt(1);
                parammj.writeToParcel(localParcel1, 0);
                if (parammx == null) {
                  break label151;
                }
                localParcel1.writeInt(1);
                parammx.writeToParcel(localParcel1, 0);
                if (parammv == null) {
                  break label160;
                }
                paramLatLng = parammv.asBinder();
                localParcel1.writeStrongBinder(paramLatLng);
                this.lb.transact(16, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label151:
          localParcel1.writeInt(0);
          continue;
          label160:
          paramLatLng = null;
        }
      }
      
      public void a(LatLngBounds paramLatLngBounds, int paramInt, mj parammj, mx parammx, mv parammv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramLatLngBounds != null)
            {
              localParcel1.writeInt(1);
              paramLatLngBounds.writeToParcel(localParcel1, 0);
              localParcel1.writeInt(paramInt);
              if (parammj != null)
              {
                localParcel1.writeInt(1);
                parammj.writeToParcel(localParcel1, 0);
                if (parammx == null) {
                  break label159;
                }
                localParcel1.writeInt(1);
                parammx.writeToParcel(localParcel1, 0);
                if (parammv == null) {
                  break label168;
                }
                paramLatLngBounds = parammv.asBinder();
                localParcel1.writeStrongBinder(paramLatLngBounds);
                this.lb.transact(14, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label159:
          localParcel1.writeInt(0);
          continue;
          label168:
          paramLatLngBounds = null;
        }
      }
      
      public void a(LatLngBounds paramLatLngBounds, int paramInt, String paramString, mj parammj, mx parammx, mv parammv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramLatLngBounds != null)
            {
              localParcel1.writeInt(1);
              paramLatLngBounds.writeToParcel(localParcel1, 0);
              localParcel1.writeInt(paramInt);
              localParcel1.writeString(paramString);
              if (parammj != null)
              {
                localParcel1.writeInt(1);
                parammj.writeToParcel(localParcel1, 0);
                if (parammx == null) {
                  break label167;
                }
                localParcel1.writeInt(1);
                parammx.writeToParcel(localParcel1, 0);
                if (parammv == null) {
                  break label176;
                }
                paramLatLngBounds = parammv.asBinder();
                localParcel1.writeStrongBinder(paramLatLngBounds);
                this.lb.transact(47, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label167:
          localParcel1.writeInt(0);
          continue;
          label176:
          paramLatLngBounds = null;
        }
      }
      
      public void a(String paramString, mx parammx, mv parammv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            localParcel1.writeString(paramString);
            if (parammx != null)
            {
              localParcel1.writeInt(1);
              parammx.writeToParcel(localParcel1, 0);
              if (parammv != null)
              {
                paramString = parammv.asBinder();
                localParcel1.writeStrongBinder(paramString);
                this.lb.transact(15, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramString = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(String paramString, LatLngBounds paramLatLngBounds, mf parammf, mx parammx, mv parammv)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel2.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            localParcel2.writeString(paramString);
            if (paramLatLngBounds != null)
            {
              localParcel2.writeInt(1);
              paramLatLngBounds.writeToParcel(localParcel2, 0);
              if (parammf != null)
              {
                localParcel2.writeInt(1);
                parammf.writeToParcel(localParcel2, 0);
                if (parammx == null) {
                  break label159;
                }
                localParcel2.writeInt(1);
                parammx.writeToParcel(localParcel2, 0);
                if (parammv == null) {
                  break label168;
                }
                paramString = parammv.asBinder();
                localParcel2.writeStrongBinder(paramString);
                this.lb.transact(55, localParcel2, localParcel1, 0);
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
          continue;
          label159:
          localParcel2.writeInt(0);
          continue;
          label168:
          paramString = null;
        }
      }
      
      public void a(List<mc> paramList, PendingIntent paramPendingIntent, lw paramlw, String paramString)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel2.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            localParcel2.writeTypedList(paramList);
            if (paramPendingIntent != null)
            {
              localParcel2.writeInt(1);
              paramPendingIntent.writeToParcel(localParcel2, 0);
              if (paramlw != null)
              {
                paramList = paramlw.asBinder();
                localParcel2.writeStrongBinder(paramList);
                localParcel2.writeString(paramString);
                this.lb.transact(1, localParcel2, localParcel1, 0);
                localParcel1.readException();
              }
            }
            else
            {
              localParcel2.writeInt(0);
              continue;
            }
            paramList = null;
          }
          finally
          {
            localParcel1.recycle();
            localParcel2.recycle();
          }
        }
      }
      
      /* Error */
      public void a(String[] paramArrayOfString, lw paramlw, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 4
        //   10: aload 5
        //   12: ldc 30
        //   14: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 5
        //   19: aload_1
        //   20: invokevirtual 156	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   23: aload_2
        //   24: ifnull +54 -> 78
        //   27: aload_2
        //   28: invokeinterface 69 1 0
        //   33: astore_1
        //   34: aload 5
        //   36: aload_1
        //   37: invokevirtual 72	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   40: aload 5
        //   42: aload_3
        //   43: invokevirtual 75	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   46: aload_0
        //   47: getfield 18	com/google/android/gms/internal/lx$a$a:lb	Landroid/os/IBinder;
        //   50: iconst_3
        //   51: aload 5
        //   53: aload 4
        //   55: iconst_0
        //   56: invokeinterface 54 5 0
        //   61: pop
        //   62: aload 4
        //   64: invokevirtual 57	android/os/Parcel:readException	()V
        //   67: aload 4
        //   69: invokevirtual 60	android/os/Parcel:recycle	()V
        //   72: aload 5
        //   74: invokevirtual 60	android/os/Parcel:recycle	()V
        //   77: return
        //   78: aconst_null
        //   79: astore_1
        //   80: goto -46 -> 34
        //   83: astore_1
        //   84: aload 4
        //   86: invokevirtual 60	android/os/Parcel:recycle	()V
        //   89: aload 5
        //   91: invokevirtual 60	android/os/Parcel:recycle	()V
        //   94: aload_1
        //   95: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	96	0	this	a
        //   0	96	1	paramArrayOfString	String[]
        //   0	96	2	paramlw	lw
        //   0	96	3	paramString	String
        //   8	77	4	localParcel1	Parcel
        //   3	87	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	23	83	finally
        //   27	34	83	finally
        //   34	67	83	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      public void b(mx parammx, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel2.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (parammx != null)
            {
              localParcel2.writeInt(1);
              parammx.writeToParcel(localParcel2, 0);
              if (paramPendingIntent != null)
              {
                localParcel2.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel2, 0);
                this.lb.transact(49, localParcel2, localParcel1, 0);
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
      
      public void b(String paramString, mx parammx, mv parammv)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            localParcel1.writeString(paramString);
            if (parammx != null)
            {
              localParcel1.writeInt(1);
              parammx.writeToParcel(localParcel1, 0);
              if (parammv != null)
              {
                paramString = parammv.asBinder();
                localParcel1.writeStrongBinder(paramString);
                this.lb.transact(42, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramString = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public Location bW(String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_3
        //   9: ldc 30
        //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_3
        //   15: aload_1
        //   16: invokevirtual 75	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   19: aload_0
        //   20: getfield 18	com/google/android/gms/internal/lx$a$a:lb	Landroid/os/IBinder;
        //   23: bipush 21
        //   25: aload_3
        //   26: aload_2
        //   27: iconst_0
        //   28: invokeinterface 54 5 0
        //   33: pop
        //   34: aload_2
        //   35: invokevirtual 57	android/os/Parcel:readException	()V
        //   38: aload_2
        //   39: invokevirtual 163	android/os/Parcel:readInt	()I
        //   42: ifeq +26 -> 68
        //   45: getstatic 167	android/location/Location:CREATOR	Landroid/os/Parcelable$Creator;
        //   48: aload_2
        //   49: invokeinterface 173 2 0
        //   54: checkcast 78	android/location/Location
        //   57: astore_1
        //   58: aload_2
        //   59: invokevirtual 60	android/os/Parcel:recycle	()V
        //   62: aload_3
        //   63: invokevirtual 60	android/os/Parcel:recycle	()V
        //   66: aload_1
        //   67: areturn
        //   68: aconst_null
        //   69: astore_1
        //   70: goto -12 -> 58
        //   73: astore_1
        //   74: aload_2
        //   75: invokevirtual 60	android/os/Parcel:recycle	()V
        //   78: aload_3
        //   79: invokevirtual 60	android/os/Parcel:recycle	()V
        //   82: aload_1
        //   83: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	84	0	this	a
        //   0	84	1	paramString	String
        //   7	68	2	localParcel1	Parcel
        //   3	76	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	58	73	finally
      }
      
      /* Error */
      public c bX(String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 30
        //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_2
        //   15: aload_1
        //   16: invokevirtual 75	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   19: aload_0
        //   20: getfield 18	com/google/android/gms/internal/lx$a$a:lb	Landroid/os/IBinder;
        //   23: bipush 34
        //   25: aload_2
        //   26: aload_3
        //   27: iconst_0
        //   28: invokeinterface 54 5 0
        //   33: pop
        //   34: aload_3
        //   35: invokevirtual 57	android/os/Parcel:readException	()V
        //   38: aload_3
        //   39: invokevirtual 163	android/os/Parcel:readInt	()I
        //   42: ifeq +21 -> 63
        //   45: getstatic 180	com/google/android/gms/location/c:CREATOR	Lcom/google/android/gms/location/d;
        //   48: aload_3
        //   49: invokevirtual 186	com/google/android/gms/location/d:ct	(Landroid/os/Parcel;)Lcom/google/android/gms/location/c;
        //   52: astore_1
        //   53: aload_3
        //   54: invokevirtual 60	android/os/Parcel:recycle	()V
        //   57: aload_2
        //   58: invokevirtual 60	android/os/Parcel:recycle	()V
        //   61: aload_1
        //   62: areturn
        //   63: aconst_null
        //   64: astore_1
        //   65: goto -12 -> 53
        //   68: astore_1
        //   69: aload_3
        //   70: invokevirtual 60	android/os/Parcel:recycle	()V
        //   73: aload_2
        //   74: invokevirtual 60	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	a
        //   0	79	1	paramString	String
        //   3	71	2	localParcel1	Parcel
        //   7	63	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	53	68	finally
      }
      
      /* Error */
      public Location lV()
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_3
        //   9: ldc 30
        //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_0
        //   15: getfield 18	com/google/android/gms/internal/lx$a$a:lb	Landroid/os/IBinder;
        //   18: bipush 7
        //   20: aload_3
        //   21: aload_2
        //   22: iconst_0
        //   23: invokeinterface 54 5 0
        //   28: pop
        //   29: aload_2
        //   30: invokevirtual 57	android/os/Parcel:readException	()V
        //   33: aload_2
        //   34: invokevirtual 163	android/os/Parcel:readInt	()I
        //   37: ifeq +26 -> 63
        //   40: getstatic 167	android/location/Location:CREATOR	Landroid/os/Parcelable$Creator;
        //   43: aload_2
        //   44: invokeinterface 173 2 0
        //   49: checkcast 78	android/location/Location
        //   52: astore_1
        //   53: aload_2
        //   54: invokevirtual 60	android/os/Parcel:recycle	()V
        //   57: aload_3
        //   58: invokevirtual 60	android/os/Parcel:recycle	()V
        //   61: aload_1
        //   62: areturn
        //   63: aconst_null
        //   64: astore_1
        //   65: goto -12 -> 53
        //   68: astore_1
        //   69: aload_2
        //   70: invokevirtual 60	android/os/Parcel:recycle	()V
        //   73: aload_3
        //   74: invokevirtual 60	android/os/Parcel:recycle	()V
        //   77: aload_1
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	a
        //   52	13	1	localLocation	Location
        //   68	10	1	localObject	Object
        //   7	63	2	localParcel1	Parcel
        //   3	71	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	53	68	finally
      }
      
      public IBinder lW()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          this.lb.transact(51, localParcel1, localParcel2, 0);
          localParcel2.readException();
          IBinder localIBinder = localParcel2.readStrongBinder();
          return localIBinder;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public IBinder lX()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          this.lb.transact(54, localParcel1, localParcel2, 0);
          localParcel2.readException();
          IBinder localIBinder = localParcel2.readStrongBinder();
          return localIBinder;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public void removeActivityUpdates(PendingIntent paramPendingIntent)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 30
        //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +42 -> 57
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 48	android/app/PendingIntent:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/internal/lx$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 6
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 54 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 57	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 60	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 60	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aload_2
        //   58: iconst_0
        //   59: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   62: goto -33 -> 29
        //   65: astore_1
        //   66: aload_3
        //   67: invokevirtual 60	android/os/Parcel:recycle	()V
        //   70: aload_2
        //   71: invokevirtual 60	android/os/Parcel:recycle	()V
        //   74: aload_1
        //   75: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	76	0	this	a
        //   0	76	1	paramPendingIntent	PendingIntent
        //   3	68	2	localParcel1	Parcel
        //   7	60	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	65	finally
        //   18	29	65	finally
        //   29	48	65	finally
        //   57	62	65	finally
      }
      
      /* Error */
      public void setMockLocation(Location paramLocation)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 30
        //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +42 -> 57
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 79	android/location/Location:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/internal/lx$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 13
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 54 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 57	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 60	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 60	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aload_2
        //   58: iconst_0
        //   59: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   62: goto -33 -> 29
        //   65: astore_1
        //   66: aload_3
        //   67: invokevirtual 60	android/os/Parcel:recycle	()V
        //   70: aload_2
        //   71: invokevirtual 60	android/os/Parcel:recycle	()V
        //   74: aload_1
        //   75: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	76	0	this	a
        //   0	76	1	paramLocation	Location
        //   3	68	2	localParcel1	Parcel
        //   7	60	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	65	finally
        //   18	29	65	finally
        //   29	48	65	finally
        //   57	62	65	finally
      }
      
      public void setMockMode(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel2 = Parcel.obtain();
        Parcel localParcel1 = Parcel.obtain();
        try
        {
          localParcel2.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramBoolean) {
            i = 1;
          }
          localParcel2.writeInt(i);
          this.lb.transact(12, localParcel2, localParcel1, 0);
          localParcel1.readException();
          return;
        }
        finally
        {
          localParcel1.recycle();
          localParcel2.recycle();
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\lx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */