// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationRequestCreator;
import com.google.android.gms.location.b;
import com.google.android.gms.location.c;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLngBoundsCreator;
import com.google.android.gms.maps.model.LatLngCreator;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            je, jh, jm, jn, 
//            ka, kb, jw, jq, 
//            jr, jk, jl, ju, 
//            jo, jp, jd, jy

public static abstract class a.kq extends Binder
    implements je
{
    private static class a
        implements je
    {

        private IBinder kq;

        public void a(long l, boolean flag, PendingIntent pendingintent)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            parcel.writeLong(l);
            if (!flag)
            {
                i = 0;
            }
            parcel.writeInt(i);
            if (pendingintent == null)
            {
                break MISSING_BLOCK_LABEL_94;
            }
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L1:
            kq.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (pendingintent == null)
            {
                break MISSING_BLOCK_LABEL_57;
            }
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L1:
            kq.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(PendingIntent pendingintent, jd jd1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (pendingintent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L3:
            if (jd1 == null)
            {
                break MISSING_BLOCK_LABEL_115;
            }
            IBinder ibinder = jd1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            kq.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            ibinder = null;
              goto _L4
        }

        public void a(Location location, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (location == null)
            {
                break MISSING_BLOCK_LABEL_66;
            }
            parcel.writeInt(1);
            location.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            kq.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(jd jd1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (jd1 == null)
            {
                break MISSING_BLOCK_LABEL_68;
            }
            IBinder ibinder = jd1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            kq.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(jk jk1, ka ka1, PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (jk1 == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            jk1.writeToParcel(parcel, 0);
_L5:
            if (ka1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            ka1.writeToParcel(parcel, 0);
_L6:
            if (pendingintent == null)
            {
                break MISSING_BLOCK_LABEL_134;
            }
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L7:
            kq.transact(48, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void a(jm jm1, ka ka1, jy jy1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (jm1 == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            jm1.writeToParcel(parcel, 0);
_L5:
            if (ka1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            ka1.writeToParcel(parcel, 0);
_L6:
            if (jy1 == null)
            {
                break MISSING_BLOCK_LABEL_136;
            }
            IBinder ibinder = jy1.asBinder();
_L7:
            parcel.writeStrongBinder(ibinder);
            kq.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
_L4:
            parcel.writeInt(0);
              goto _L6
            ibinder = null;
              goto _L7
        }

        public void a(jo jo1, ka ka1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (jo1 == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            jo1.writeToParcel(parcel, 0);
_L3:
            if (ka1 == null)
            {
                break MISSING_BLOCK_LABEL_77;
            }
            parcel.writeInt(1);
            ka1.writeToParcel(parcel, 0);
_L4:
            kq.transact(25, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
            parcel.writeInt(0);
              goto _L4
        }

        public void a(jq jq1, ka ka1, PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (jq1 == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            jq1.writeToParcel(parcel, 0);
_L5:
            if (ka1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            ka1.writeToParcel(parcel, 0);
_L6:
            if (pendingintent == null)
            {
                break MISSING_BLOCK_LABEL_134;
            }
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L7:
            kq.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void a(ju ju1, ka ka1, jy jy1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (ju1 == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            ju1.writeToParcel(parcel, 0);
_L5:
            if (ka1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            ka1.writeToParcel(parcel, 0);
_L6:
            if (jy1 == null)
            {
                break MISSING_BLOCK_LABEL_136;
            }
            IBinder ibinder = jy1.asBinder();
_L7:
            parcel.writeStrongBinder(ibinder);
            kq.transact(46, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
_L4:
            parcel.writeInt(0);
              goto _L6
            ibinder = null;
              goto _L7
        }

        public void a(ka ka1, PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (ka1 == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            ka1.writeToParcel(parcel, 0);
_L3:
            if (pendingintent == null)
            {
                break MISSING_BLOCK_LABEL_98;
            }
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L4:
            kq.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            parcel.writeInt(0);
              goto _L4
        }

        public void a(LocationRequest locationrequest, PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (locationrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            locationrequest.writeToParcel(parcel, 0);
_L3:
            if (pendingintent == null)
            {
                break MISSING_BLOCK_LABEL_98;
            }
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L4:
            kq.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            parcel.writeInt(0);
              goto _L4
        }

        public void a(LocationRequest locationrequest, com.google.android.gms.location.a a1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (locationrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            locationrequest.writeToParcel(parcel, 0);
_L3:
            if (a1 == null)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            IBinder ibinder = a1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            ibinder = null;
              goto _L4
        }

        public void a(LocationRequest locationrequest, com.google.android.gms.location.a a1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (locationrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            locationrequest.writeToParcel(parcel, 0);
_L3:
            if (a1 == null)
            {
                break MISSING_BLOCK_LABEL_116;
            }
            IBinder ibinder = a1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            kq.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            ibinder = null;
              goto _L4
        }

        public void a(com.google.android.gms.location.a a1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (a1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = a1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(LatLng latlng, jm jm1, ka ka1, jy jy1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (latlng == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            latlng.writeToParcel(parcel, 0);
_L7:
            if (jm1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            jm1.writeToParcel(parcel, 0);
_L8:
            if (ka1 == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            ka1.writeToParcel(parcel, 0);
_L9:
            if (jy1 == null)
            {
                break MISSING_BLOCK_LABEL_164;
            }
            IBinder ibinder = jy1.asBinder();
_L10:
            parcel.writeStrongBinder(ibinder);
            kq.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L7
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
_L4:
            parcel.writeInt(0);
              goto _L8
_L6:
            parcel.writeInt(0);
              goto _L9
            ibinder = null;
              goto _L10
        }

        public void a(LatLngBounds latlngbounds, int i, jm jm1, ka ka1, jy jy1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (latlngbounds == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            latlngbounds.writeToParcel(parcel, 0);
_L7:
            parcel.writeInt(i);
            if (jm1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            jm1.writeToParcel(parcel, 0);
_L8:
            if (ka1 == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            ka1.writeToParcel(parcel, 0);
_L9:
            if (jy1 == null)
            {
                break MISSING_BLOCK_LABEL_172;
            }
            IBinder ibinder = jy1.asBinder();
_L10:
            parcel.writeStrongBinder(ibinder);
            kq.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L7
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
_L4:
            parcel.writeInt(0);
              goto _L8
_L6:
            parcel.writeInt(0);
              goto _L9
            ibinder = null;
              goto _L10
        }

        public void a(LatLngBounds latlngbounds, int i, String s, jm jm1, ka ka1, jy jy1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (latlngbounds == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            latlngbounds.writeToParcel(parcel, 0);
_L7:
            parcel.writeInt(i);
            parcel.writeString(s);
            if (jm1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            jm1.writeToParcel(parcel, 0);
_L8:
            if (ka1 == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            ka1.writeToParcel(parcel, 0);
_L9:
            if (jy1 == null)
            {
                break MISSING_BLOCK_LABEL_180;
            }
            IBinder ibinder = jy1.asBinder();
_L10:
            parcel.writeStrongBinder(ibinder);
            kq.transact(47, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L7
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
_L4:
            parcel.writeInt(0);
              goto _L8
_L6:
            parcel.writeInt(0);
              goto _L9
            ibinder = null;
              goto _L10
        }

        public void a(String s, ka ka1, jy jy1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            parcel.writeString(s);
            if (ka1 == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            ka1.writeToParcel(parcel, 0);
_L3:
            if (jy1 == null)
            {
                break MISSING_BLOCK_LABEL_116;
            }
            IBinder ibinder = jy1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            ibinder = null;
              goto _L4
        }

        public void a(String s, LatLngBounds latlngbounds, jm jm1, ka ka1, jy jy1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            parcel.writeString(s);
            if (latlngbounds == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            latlngbounds.writeToParcel(parcel, 0);
_L7:
            if (jm1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            jm1.writeToParcel(parcel, 0);
_L8:
            if (ka1 == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            ka1.writeToParcel(parcel, 0);
_L9:
            if (jy1 == null)
            {
                break MISSING_BLOCK_LABEL_172;
            }
            IBinder ibinder = jy1.asBinder();
_L10:
            parcel.writeStrongBinder(ibinder);
            kq.transact(45, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L7
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
_L4:
            parcel.writeInt(0);
              goto _L8
_L6:
            parcel.writeInt(0);
              goto _L9
            ibinder = null;
              goto _L10
        }

        public void a(String s, List list, List list1, ka ka1, jy jy1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            parcel.writeString(s);
            parcel.writeStringList(list);
            parcel.writeTypedList(list1);
            if (ka1 == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            ka1.writeToParcel(parcel, 0);
_L3:
            if (jy1 == null)
            {
                break MISSING_BLOCK_LABEL_132;
            }
            IBinder ibinder = jy1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(50, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            ibinder = null;
              goto _L4
        }

        public void a(List list, PendingIntent pendingintent, jd jd1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            parcel.writeTypedList(list);
            if (pendingintent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L3:
            if (jd1 == null)
            {
                break MISSING_BLOCK_LABEL_122;
            }
            IBinder ibinder = jd1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            kq.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            ibinder = null;
              goto _L4
        }

        public void a(String as1[], jd jd1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            parcel.writeStringArray(as1);
            if (jd1 == null)
            {
                break MISSING_BLOCK_LABEL_80;
            }
            IBinder ibinder = jd1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            kq.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IBinder asBinder()
        {
            return kq;
        }

        public void b(ka ka1, PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (ka1 == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            ka1.writeToParcel(parcel, 0);
_L3:
            if (pendingintent == null)
            {
                break MISSING_BLOCK_LABEL_98;
            }
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L4:
            kq.transact(49, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            parcel.writeInt(0);
              goto _L4
        }

        public void b(String s, ka ka1, jy jy1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            parcel.writeString(s);
            if (ka1 == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            ka1.writeToParcel(parcel, 0);
_L3:
            if (jy1 == null)
            {
                break MISSING_BLOCK_LABEL_116;
            }
            IBinder ibinder = jy1.asBinder();
_L4:
            parcel.writeStrongBinder(ibinder);
            kq.transact(42, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            ibinder = null;
              goto _L4
        }

        public Location bo(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            parcel.writeString(s);
            kq.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Location location = (Location)Location.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return location;
_L2:
            location = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public b bp(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            parcel.writeString(s);
            kq.transact(34, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            b b2 = b.CREATOR.bs(parcel1);
            b b1 = b2;
_L4:
            parcel1.recycle();
            parcel.recycle();
            return b1;
_L2:
            b1 = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Location iW()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            kq.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Location location = (Location)Location.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return location;
_L2:
            location = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IBinder iX()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IBinder ibinder;
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            kq.transact(51, parcel, parcel1, 0);
            parcel1.readException();
            ibinder = parcel1.readStrongBinder();
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void removeActivityUpdates(PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (pendingintent == null)
            {
                break MISSING_BLOCK_LABEL_57;
            }
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L1:
            kq.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setMockLocation(Location location)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (location == null)
            {
                break MISSING_BLOCK_LABEL_57;
            }
            parcel.writeInt(1);
            location.writeToParcel(parcel, 0);
_L1:
            kq.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setMockMode(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            int i;
            i = 0;
            if (flag)
            {
                i = 1;
            }
            parcel.writeInt(i);
            kq.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        a(IBinder ibinder)
        {
            kq = ibinder;
        }
    }


    public static je as(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        android.os.IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        if (iinterface != null && (iinterface instanceof je))
        {
            return (je)iinterface;
        } else
        {
            return new a(ibinder);
        }
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
        throws RemoteException
    {
        switch (i)
        {
        default:
            return super.onTransact(i, parcel, parcel1, j);

        case 1598968902: 
            parcel1.writeString("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            return true;

        case 1: // '\001'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            java.util.ArrayList arraylist2 = parcel.createTypedArrayList(jh.CREATOR);
            PendingIntent pendingintent9;
            if (parcel.readInt() != 0)
            {
                pendingintent9 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
            } else
            {
                pendingintent9 = null;
            }
            a(arraylist2, pendingintent9, ar(parcel.readStrongBinder()), parcel.readString());
            parcel1.writeNoException();
            return true;

        case 2: // '\002'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            PendingIntent pendingintent8;
            if (parcel.readInt() != 0)
            {
                pendingintent8 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
            } else
            {
                pendingintent8 = null;
            }
            a(pendingintent8, ar(parcel.readStrongBinder()), parcel.readString());
            parcel1.writeNoException();
            return true;

        case 3: // '\003'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            a(parcel.createStringArray(), ar(parcel.readStrongBinder()), parcel.readString());
            parcel1.writeNoException();
            return true;

        case 4: // '\004'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            a(ar(parcel.readStrongBinder()), parcel.readString());
            parcel1.writeNoException();
            return true;

        case 5: // '\005'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            long l3 = parcel.readLong();
            boolean flag1;
            PendingIntent pendingintent7;
            if (parcel.readInt() != 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (parcel.readInt() != 0)
            {
                pendingintent7 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
            } else
            {
                pendingintent7 = null;
            }
            a(l3, flag1, pendingintent7);
            parcel1.writeNoException();
            return true;

        case 6: // '\006'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            PendingIntent pendingintent6;
            if (parcel.readInt() != 0)
            {
                pendingintent6 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
            } else
            {
                pendingintent6 = null;
            }
            removeActivityUpdates(pendingintent6);
            parcel1.writeNoException();
            return true;

        case 7: // '\007'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            Location location3 = iW();
            parcel1.writeNoException();
            if (location3 != null)
            {
                parcel1.writeInt(1);
                location3.writeToParcel(parcel1, 1);
                return true;
            } else
            {
                parcel1.writeInt(0);
                return true;
            }

        case 8: // '\b'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            int j3 = parcel.readInt();
            LocationRequest locationrequest2 = null;
            if (j3 != 0)
            {
                locationrequest2 = LocationRequest.CREATOR.createFromParcel(parcel);
            }
            a(locationrequest2, com.google.android.gms.location.q(parcel.readStrongBinder()));
            parcel1.writeNoException();
            return true;

        case 9: // '\t'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            LocationRequest locationrequest1;
            PendingIntent pendingintent5;
            if (parcel.readInt() != 0)
            {
                locationrequest1 = LocationRequest.CREATOR.createFromParcel(parcel);
            } else
            {
                locationrequest1 = null;
            }
            if (parcel.readInt() != 0)
            {
                pendingintent5 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
            } else
            {
                pendingintent5 = null;
            }
            a(locationrequest1, pendingintent5);
            parcel1.writeNoException();
            return true;

        case 10: // '\n'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            a(com.google.android.gms.location.q(parcel.readStrongBinder()));
            parcel1.writeNoException();
            return true;

        case 11: // '\013'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            PendingIntent pendingintent4;
            if (parcel.readInt() != 0)
            {
                pendingintent4 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
            } else
            {
                pendingintent4 = null;
            }
            a(pendingintent4);
            parcel1.writeNoException();
            return true;

        case 12: // '\f'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            int i3 = parcel.readInt();
            boolean flag = false;
            if (i3 != 0)
            {
                flag = true;
            }
            setMockMode(flag);
            parcel1.writeNoException();
            return true;

        case 13: // '\r'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            Location location2;
            if (parcel.readInt() != 0)
            {
                location2 = (Location)Location.CREATOR.createFromParcel(parcel);
            } else
            {
                location2 = null;
            }
            setMockLocation(location2);
            parcel1.writeNoException();
            return true;

        case 14: // '\016'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            LatLngBounds latlngbounds2;
            int l2;
            jm jm5;
            ka ka14;
            if (parcel.readInt() != 0)
            {
                latlngbounds2 = LatLngBounds.CREATOR.createFromParcel(parcel);
            } else
            {
                latlngbounds2 = null;
            }
            l2 = parcel.readInt();
            if (parcel.readInt() != 0)
            {
                jm5 = jm.CREATOR.bv(parcel);
            } else
            {
                jm5 = null;
            }
            if (parcel.readInt() != 0)
            {
                ka14 = ka.CREATOR.bB(parcel);
            } else
            {
                ka14 = null;
            }
            a(latlngbounds2, l2, jm5, ka14, au(parcel.readStrongBinder()));
            parcel1.writeNoException();
            return true;

        case 47: // '/'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            LatLngBounds latlngbounds1;
            int j2;
            String s4;
            jm jm4;
            int k2;
            ka ka13;
            if (parcel.readInt() != 0)
            {
                latlngbounds1 = LatLngBounds.CREATOR.createFromParcel(parcel);
            } else
            {
                latlngbounds1 = null;
            }
            j2 = parcel.readInt();
            s4 = parcel.readString();
            if (parcel.readInt() != 0)
            {
                jm4 = jm.CREATOR.bv(parcel);
            } else
            {
                jm4 = null;
            }
            k2 = parcel.readInt();
            ka13 = null;
            if (k2 != 0)
            {
                ka13 = ka.CREATOR.bB(parcel);
            }
            a(latlngbounds1, j2, s4, jm4, ka13, au(parcel.readStrongBinder()));
            parcel1.writeNoException();
            return true;

        case 15: // '\017'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            String s3 = parcel.readString();
            int i2 = parcel.readInt();
            ka ka12 = null;
            if (i2 != 0)
            {
                ka12 = ka.CREATOR.bB(parcel);
            }
            a(s3, ka12, au(parcel.readStrongBinder()));
            parcel1.writeNoException();
            return true;

        case 16: // '\020'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            LatLng latlng;
            jm jm3;
            int l1;
            ka ka11;
            if (parcel.readInt() != 0)
            {
                latlng = LatLng.CREATOR.createFromParcel(parcel);
            } else
            {
                latlng = null;
            }
            if (parcel.readInt() != 0)
            {
                jm3 = jm.CREATOR.bv(parcel);
            } else
            {
                jm3 = null;
            }
            l1 = parcel.readInt();
            ka11 = null;
            if (l1 != 0)
            {
                ka11 = ka.CREATOR.bB(parcel);
            }
            a(latlng, jm3, ka11, au(parcel.readStrongBinder()));
            parcel1.writeNoException();
            return true;

        case 17: // '\021'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            jm jm2;
            int k1;
            ka ka10;
            if (parcel.readInt() != 0)
            {
                jm2 = jm.CREATOR.bv(parcel);
            } else
            {
                jm2 = null;
            }
            k1 = parcel.readInt();
            ka10 = null;
            if (k1 != 0)
            {
                ka10 = ka.CREATOR.bB(parcel);
            }
            a(jm2, ka10, au(parcel.readStrongBinder()));
            parcel1.writeNoException();
            return true;

        case 42: // '*'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            String s2 = parcel.readString();
            int j1 = parcel.readInt();
            ka ka9 = null;
            if (j1 != 0)
            {
                ka9 = ka.CREATOR.bB(parcel);
            }
            b(s2, ka9, au(parcel.readStrongBinder()));
            parcel1.writeNoException();
            return true;

        case 50: // '2'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            String s1 = parcel.readString();
            java.util.ArrayList arraylist = parcel.createStringArrayList();
            java.util.ArrayList arraylist1 = parcel.createTypedArrayList(jw.CREATOR);
            ka ka8;
            if (parcel.readInt() != 0)
            {
                ka8 = ka.CREATOR.bB(parcel);
            } else
            {
                ka8 = null;
            }
            a(s1, arraylist, arraylist1, ka8, au(parcel.readStrongBinder()));
            parcel1.writeNoException();
            return true;

        case 18: // '\022'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            jq jq1;
            ka ka7;
            PendingIntent pendingintent3;
            if (parcel.readInt() != 0)
            {
                jq1 = jq.CREATOR.bx(parcel);
            } else
            {
                jq1 = null;
            }
            if (parcel.readInt() != 0)
            {
                ka7 = ka.CREATOR.bB(parcel);
            } else
            {
                ka7 = null;
            }
            if (parcel.readInt() != 0)
            {
                pendingintent3 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
            } else
            {
                pendingintent3 = null;
            }
            a(jq1, ka7, pendingintent3);
            parcel1.writeNoException();
            return true;

        case 19: // '\023'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            ka ka6;
            PendingIntent pendingintent2;
            if (parcel.readInt() != 0)
            {
                ka6 = ka.CREATOR.bB(parcel);
            } else
            {
                ka6 = null;
            }
            if (parcel.readInt() != 0)
            {
                pendingintent2 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
            } else
            {
                pendingintent2 = null;
            }
            a(ka6, pendingintent2);
            parcel1.writeNoException();
            return true;

        case 48: // '0'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            jk jk1;
            ka ka5;
            PendingIntent pendingintent1;
            if (parcel.readInt() != 0)
            {
                jk1 = jk.CREATOR.bu(parcel);
            } else
            {
                jk1 = null;
            }
            if (parcel.readInt() != 0)
            {
                ka5 = ka.CREATOR.bB(parcel);
            } else
            {
                ka5 = null;
            }
            if (parcel.readInt() != 0)
            {
                pendingintent1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
            } else
            {
                pendingintent1 = null;
            }
            a(jk1, ka5, pendingintent1);
            parcel1.writeNoException();
            return true;

        case 49: // '1'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            ka ka4;
            PendingIntent pendingintent;
            if (parcel.readInt() != 0)
            {
                ka4 = ka.CREATOR.bB(parcel);
            } else
            {
                ka4 = null;
            }
            if (parcel.readInt() != 0)
            {
                pendingintent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
            } else
            {
                pendingintent = null;
            }
            b(ka4, pendingintent);
            parcel1.writeNoException();
            return true;

        case 45: // '-'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            String s = parcel.readString();
            LatLngBounds latlngbounds;
            jm jm1;
            ka ka3;
            if (parcel.readInt() != 0)
            {
                latlngbounds = LatLngBounds.CREATOR.createFromParcel(parcel);
            } else
            {
                latlngbounds = null;
            }
            if (parcel.readInt() != 0)
            {
                jm1 = jm.CREATOR.bv(parcel);
            } else
            {
                jm1 = null;
            }
            if (parcel.readInt() != 0)
            {
                ka3 = ka.CREATOR.bB(parcel);
            } else
            {
                ka3 = null;
            }
            a(s, latlngbounds, jm1, ka3, au(parcel.readStrongBinder()));
            parcel1.writeNoException();
            return true;

        case 46: // '.'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            ju ju1;
            int i1;
            ka ka2;
            if (parcel.readInt() != 0)
            {
                ju1 = (ju)ju.CREATOR.createFromParcel(parcel);
            } else
            {
                ju1 = null;
            }
            i1 = parcel.readInt();
            ka2 = null;
            if (i1 != 0)
            {
                ka2 = ka.CREATOR.bB(parcel);
            }
            a(ju1, ka2, au(parcel.readStrongBinder()));
            parcel1.writeNoException();
            return true;

        case 20: // '\024'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            int l = parcel.readInt();
            LocationRequest locationrequest = null;
            if (l != 0)
            {
                locationrequest = LocationRequest.CREATOR.createFromParcel(parcel);
            }
            a(locationrequest, com.google.android.gms.location.q(parcel.readStrongBinder()), parcel.readString());
            parcel1.writeNoException();
            return true;

        case 21: // '\025'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            Location location1 = bo(parcel.readString());
            parcel1.writeNoException();
            if (location1 != null)
            {
                parcel1.writeInt(1);
                location1.writeToParcel(parcel1, 1);
                return true;
            } else
            {
                parcel1.writeInt(0);
                return true;
            }

        case 25: // '\031'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            jo jo1;
            int k;
            ka ka1;
            if (parcel.readInt() != 0)
            {
                jo1 = jo.CREATOR.bw(parcel);
            } else
            {
                jo1 = null;
            }
            k = parcel.readInt();
            ka1 = null;
            if (k != 0)
            {
                ka1 = ka.CREATOR.bB(parcel);
            }
            a(jo1, ka1);
            return true;

        case 26: // '\032'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            Location location;
            if (parcel.readInt() != 0)
            {
                location = (Location)Location.CREATOR.createFromParcel(parcel);
            } else
            {
                location = null;
            }
            a(location, parcel.readInt());
            parcel1.writeNoException();
            return true;

        case 34: // '"'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            b b1 = bp(parcel.readString());
            parcel1.writeNoException();
            if (b1 != null)
            {
                parcel1.writeInt(1);
                b1.writeToParcel(parcel1, 1);
                return true;
            } else
            {
                parcel1.writeInt(0);
                return true;
            }

        case 51: // '3'
            parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            IBinder ibinder = iX();
            parcel1.writeNoException();
            parcel1.writeStrongBinder(ibinder);
            return true;
        }
    }
}
