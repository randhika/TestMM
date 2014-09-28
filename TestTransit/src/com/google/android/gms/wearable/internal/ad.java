// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.c;

// Referenced classes of package com.google.android.gms.wearable.internal:
//            ab, ao, b

public interface ad
    extends IInterface
{
    public static abstract class a extends Binder
        implements ad
    {

        public static ad by(IBinder ibinder)
        {
            if (ibinder == null)
            {
                return null;
            }
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableService");
            if (iinterface != null && (iinterface instanceof ad))
            {
                return (ad)iinterface;
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
                parcel1.writeString("com.google.android.gms.wearable.internal.IWearableService");
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                ab ab7 = com.google.android.gms.wearable.internal.ab.a.bw(parcel.readStrongBinder());
                int j2 = parcel.readInt();
                c c1 = null;
                if (j2 != 0)
                {
                    c1 = (c)c.CREATOR.createFromParcel(parcel);
                }
                a(ab7, c1);
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                a(com.google.android.gms.wearable.internal.ab.a.bw(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                b(com.google.android.gms.wearable.internal.ab.a.bw(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                c(com.google.android.gms.wearable.internal.ab.a.bw(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                ab ab6 = com.google.android.gms.wearable.internal.ab.a.bw(parcel.readStrongBinder());
                int i2 = parcel.readInt();
                PutDataRequest putdatarequest = null;
                if (i2 != 0)
                {
                    putdatarequest = (PutDataRequest)PutDataRequest.CREATOR.createFromParcel(parcel);
                }
                a(ab6, putdatarequest);
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                ab ab5 = com.google.android.gms.wearable.internal.ab.a.bw(parcel.readStrongBinder());
                int l1 = parcel.readInt();
                Uri uri2 = null;
                if (l1 != 0)
                {
                    uri2 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                }
                a(ab5, uri2);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                d(com.google.android.gms.wearable.internal.ab.a.bw(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                ab ab4 = com.google.android.gms.wearable.internal.ab.a.bw(parcel.readStrongBinder());
                int k1 = parcel.readInt();
                Uri uri1 = null;
                if (k1 != 0)
                {
                    uri1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                }
                b(ab4, uri1);
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                ab ab3 = com.google.android.gms.wearable.internal.ab.a.bw(parcel.readStrongBinder());
                int j1 = parcel.readInt();
                Uri uri = null;
                if (j1 != 0)
                {
                    uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
                }
                c(ab3, uri);
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                a(com.google.android.gms.wearable.internal.ab.a.bw(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.createByteArray());
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                ab ab2 = com.google.android.gms.wearable.internal.ab.a.bw(parcel.readStrongBinder());
                int i1 = parcel.readInt();
                Asset asset = null;
                if (i1 != 0)
                {
                    asset = (Asset)Asset.CREATOR.createFromParcel(parcel);
                }
                a(ab2, asset);
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                e(com.google.android.gms.wearable.internal.ab.a.bw(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                f(com.google.android.gms.wearable.internal.ab.a.bw(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                ab ab1 = com.google.android.gms.wearable.internal.ab.a.bw(parcel.readStrongBinder());
                int l = parcel.readInt();
                b b1 = null;
                if (l != 0)
                {
                    b1 = (b)b.CREATOR.createFromParcel(parcel);
                }
                a(ab1, b1);
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                ab ab = com.google.android.gms.wearable.internal.ab.a.bw(parcel.readStrongBinder());
                int k = parcel.readInt();
                ao ao1 = null;
                if (k != 0)
                {
                    ao1 = (ao)ao.CREATOR.createFromParcel(parcel);
                }
                a(ab, ao1);
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                g(com.google.android.gms.wearable.internal.ab.a.bw(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                h(com.google.android.gms.wearable.internal.ab.a.bw(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
        }
    }

    private static class a.a
        implements ad
    {

        private IBinder kq;

        public void a(ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_59;
            }
            IBinder ibinder = ab1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
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

        public void a(ab ab1, Uri uri)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
            if (ab1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = ab1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if (uri == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L6:
            kq.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
              goto _L5
        }

        public void a(ab ab1, Asset asset)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
            if (ab1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = ab1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if (asset == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            asset.writeToParcel(parcel, 0);
_L6:
            kq.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
              goto _L5
        }

        public void a(ab ab1, PutDataRequest putdatarequest)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
            if (ab1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = ab1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if (putdatarequest == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            putdatarequest.writeToParcel(parcel, 0);
_L6:
            kq.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
              goto _L5
        }

        public void a(ab ab1, c c1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
            if (ab1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = ab1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if (c1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            c1.writeToParcel(parcel, 0);
_L6:
            kq.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
              goto _L5
        }

        public void a(ab ab1, ao ao1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
            if (ab1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = ab1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if (ao1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            ao1.writeToParcel(parcel, 0);
_L6:
            kq.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
              goto _L5
        }

        public void a(ab ab1, b b1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
            if (ab1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = ab1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if (b1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            b1.writeToParcel(parcel, 0);
_L6:
            kq.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
              goto _L5
        }

        public void a(ab ab1, String s, String s1, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_88;
            }
            IBinder ibinder = ab1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeByteArray(abyte0);
            kq.transact(12, parcel, parcel1, 0);
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

        public void b(ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_59;
            }
            IBinder ibinder = ab1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
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

        public void b(ab ab1, Uri uri)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
            if (ab1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = ab1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if (uri == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L6:
            kq.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
              goto _L5
        }

        public void c(ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_59;
            }
            IBinder ibinder = ab1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(5, parcel, parcel1, 0);
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

        public void c(ab ab1, Uri uri)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
            if (ab1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = ab1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if (uri == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L6:
            kq.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
              goto _L5
        }

        public void d(ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = ab1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(8, parcel, parcel1, 0);
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

        public void e(ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = ab1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(14, parcel, parcel1, 0);
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

        public void f(ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = ab1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(15, parcel, parcel1, 0);
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

        public void g(ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = ab1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(18, parcel, parcel1, 0);
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

        public void h(ab ab1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
            if (ab1 == null)
            {
                break MISSING_BLOCK_LABEL_60;
            }
            IBinder ibinder = ab1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(19, parcel, parcel1, 0);
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

        a.a(IBinder ibinder)
        {
            kq = ibinder;
        }
    }


    public abstract void a(ab ab)
        throws RemoteException;

    public abstract void a(ab ab, Uri uri)
        throws RemoteException;

    public abstract void a(ab ab, Asset asset)
        throws RemoteException;

    public abstract void a(ab ab, PutDataRequest putdatarequest)
        throws RemoteException;

    public abstract void a(ab ab, c c1)
        throws RemoteException;

    public abstract void a(ab ab, ao ao)
        throws RemoteException;

    public abstract void a(ab ab, b b1)
        throws RemoteException;

    public abstract void a(ab ab, String s, String s1, byte abyte0[])
        throws RemoteException;

    public abstract void b(ab ab)
        throws RemoteException;

    public abstract void b(ab ab, Uri uri)
        throws RemoteException;

    public abstract void c(ab ab)
        throws RemoteException;

    public abstract void c(ab ab, Uri uri)
        throws RemoteException;

    public abstract void d(ab ab)
        throws RemoteException;

    public abstract void e(ab ab)
        throws RemoteException;

    public abstract void f(ab ab)
        throws RemoteException;

    public abstract void g(ab ab)
        throws RemoteException;

    public abstract void h(ab ab)
        throws RemoteException;
}
