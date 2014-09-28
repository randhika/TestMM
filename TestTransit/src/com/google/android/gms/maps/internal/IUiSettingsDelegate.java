// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IUiSettingsDelegate
    extends IInterface
{
    public static abstract class a extends Binder
        implements IUiSettingsDelegate
    {

        public static IUiSettingsDelegate aW(IBinder ibinder)
        {
            if (ibinder == null)
            {
                return null;
            }
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            if (iinterface != null && (iinterface instanceof IUiSettingsDelegate))
            {
                return (IUiSettingsDelegate)iinterface;
            } else
            {
                return new a(ibinder);
            }
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            boolean flag;
            int k;
            switch (i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                int k4 = parcel.readInt();
                boolean flag16 = false;
                if (k4 != 0)
                {
                    flag16 = true;
                }
                setZoomControlsEnabled(flag16);
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                int j4 = parcel.readInt();
                boolean flag15 = false;
                if (j4 != 0)
                {
                    flag15 = true;
                }
                setCompassEnabled(flag15);
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                int i4 = parcel.readInt();
                boolean flag14 = false;
                if (i4 != 0)
                {
                    flag14 = true;
                }
                setMyLocationButtonEnabled(flag14);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                int l3 = parcel.readInt();
                boolean flag13 = false;
                if (l3 != 0)
                {
                    flag13 = true;
                }
                setScrollGesturesEnabled(flag13);
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                int k3 = parcel.readInt();
                boolean flag12 = false;
                if (k3 != 0)
                {
                    flag12 = true;
                }
                setZoomGesturesEnabled(flag12);
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                int j3 = parcel.readInt();
                boolean flag11 = false;
                if (j3 != 0)
                {
                    flag11 = true;
                }
                setTiltGesturesEnabled(flag11);
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                int i3 = parcel.readInt();
                boolean flag10 = false;
                if (i3 != 0)
                {
                    flag10 = true;
                }
                setRotateGesturesEnabled(flag10);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                int l2 = parcel.readInt();
                boolean flag9 = false;
                if (l2 != 0)
                {
                    flag9 = true;
                }
                setAllGesturesEnabled(flag9);
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                boolean flag8 = isZoomControlsEnabled();
                parcel1.writeNoException();
                int k2 = 0;
                if (flag8)
                {
                    k2 = 1;
                }
                parcel1.writeInt(k2);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                boolean flag7 = isCompassEnabled();
                parcel1.writeNoException();
                int j2 = 0;
                if (flag7)
                {
                    j2 = 1;
                }
                parcel1.writeInt(j2);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                boolean flag6 = isMyLocationButtonEnabled();
                parcel1.writeNoException();
                int i2 = 0;
                if (flag6)
                {
                    i2 = 1;
                }
                parcel1.writeInt(i2);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                boolean flag5 = isScrollGesturesEnabled();
                parcel1.writeNoException();
                int l1 = 0;
                if (flag5)
                {
                    l1 = 1;
                }
                parcel1.writeInt(l1);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                boolean flag4 = isZoomGesturesEnabled();
                parcel1.writeNoException();
                int k1 = 0;
                if (flag4)
                {
                    k1 = 1;
                }
                parcel1.writeInt(k1);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                boolean flag3 = isTiltGesturesEnabled();
                parcel1.writeNoException();
                int j1 = 0;
                if (flag3)
                {
                    j1 = 1;
                }
                parcel1.writeInt(j1);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                boolean flag2 = isRotateGesturesEnabled();
                parcel1.writeNoException();
                int i1 = 0;
                if (flag2)
                {
                    i1 = 1;
                }
                parcel1.writeInt(i1);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                int l = parcel.readInt();
                boolean flag1 = false;
                if (l != 0)
                {
                    flag1 = true;
                }
                setIndoorLevelPickerEnabled(flag1);
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                flag = isIndoorLevelPickerEnabled();
                parcel1.writeNoException();
                k = 0;
                break;
            }
            if (flag)
            {
                k = 1;
            }
            parcel1.writeInt(k);
            return true;
        }
    }

    private static class a.a
        implements IUiSettingsDelegate
    {

        private IBinder kq;

        public IBinder asBinder()
        {
            return kq;
        }

        public boolean isCompassEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            kq.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isIndoorLevelPickerEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            kq.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isMyLocationButtonEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            kq.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isRotateGesturesEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            kq.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isScrollGesturesEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            kq.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isTiltGesturesEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            kq.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isZoomControlsEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            kq.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isZoomGesturesEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            kq.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setAllGesturesEnabled(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            int i;
            i = 0;
            if (flag)
            {
                i = 1;
            }
            parcel.writeInt(i);
            kq.transact(8, parcel, parcel1, 0);
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

        public void setCompassEnabled(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            int i;
            i = 0;
            if (flag)
            {
                i = 1;
            }
            parcel.writeInt(i);
            kq.transact(2, parcel, parcel1, 0);
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

        public void setIndoorLevelPickerEnabled(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            int i;
            i = 0;
            if (flag)
            {
                i = 1;
            }
            parcel.writeInt(i);
            kq.transact(16, parcel, parcel1, 0);
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

        public void setMyLocationButtonEnabled(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            int i;
            i = 0;
            if (flag)
            {
                i = 1;
            }
            parcel.writeInt(i);
            kq.transact(3, parcel, parcel1, 0);
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

        public void setRotateGesturesEnabled(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            int i;
            i = 0;
            if (flag)
            {
                i = 1;
            }
            parcel.writeInt(i);
            kq.transact(7, parcel, parcel1, 0);
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

        public void setScrollGesturesEnabled(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            int i;
            i = 0;
            if (flag)
            {
                i = 1;
            }
            parcel.writeInt(i);
            kq.transact(4, parcel, parcel1, 0);
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

        public void setTiltGesturesEnabled(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            int i;
            i = 0;
            if (flag)
            {
                i = 1;
            }
            parcel.writeInt(i);
            kq.transact(6, parcel, parcel1, 0);
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

        public void setZoomControlsEnabled(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            if (!flag)
            {
                i = 0;
            }
            parcel.writeInt(i);
            kq.transact(1, parcel, parcel1, 0);
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

        public void setZoomGesturesEnabled(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            int i;
            i = 0;
            if (flag)
            {
                i = 1;
            }
            parcel.writeInt(i);
            kq.transact(5, parcel, parcel1, 0);
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

        a.a(IBinder ibinder)
        {
            kq = ibinder;
        }
    }


    public abstract boolean isCompassEnabled()
        throws RemoteException;

    public abstract boolean isIndoorLevelPickerEnabled()
        throws RemoteException;

    public abstract boolean isMyLocationButtonEnabled()
        throws RemoteException;

    public abstract boolean isRotateGesturesEnabled()
        throws RemoteException;

    public abstract boolean isScrollGesturesEnabled()
        throws RemoteException;

    public abstract boolean isTiltGesturesEnabled()
        throws RemoteException;

    public abstract boolean isZoomControlsEnabled()
        throws RemoteException;

    public abstract boolean isZoomGesturesEnabled()
        throws RemoteException;

    public abstract void setAllGesturesEnabled(boolean flag)
        throws RemoteException;

    public abstract void setCompassEnabled(boolean flag)
        throws RemoteException;

    public abstract void setIndoorLevelPickerEnabled(boolean flag)
        throws RemoteException;

    public abstract void setMyLocationButtonEnabled(boolean flag)
        throws RemoteException;

    public abstract void setRotateGesturesEnabled(boolean flag)
        throws RemoteException;

    public abstract void setScrollGesturesEnabled(boolean flag)
        throws RemoteException;

    public abstract void setTiltGesturesEnabled(boolean flag)
        throws RemoteException;

    public abstract void setZoomControlsEnabled(boolean flag)
        throws RemoteException;

    public abstract void setZoomGesturesEnabled(boolean flag)
        throws RemoteException;
}
