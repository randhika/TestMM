// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;

// Referenced classes of package com.google.android.gms.internal:
//            ai, bv, al, am, 
//            aj

public interface bu
    extends IInterface
{
    public static abstract class a extends Binder
        implements bu
    {

        public static bu j(IBinder ibinder)
        {
            if (ibinder == null)
            {
                return null;
            }
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            if (iinterface != null && (iinterface instanceof bu))
            {
                return (bu)iinterface;
            } else
            {
                return new a(ibinder);
            }
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int k)
            throws RemoteException
        {
            switch (i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, k);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                d d5 = com.google.android.gms.dynamic.d.a.ag(parcel.readStrongBinder());
                al al2;
                ai ai4;
                if (parcel.readInt() != 0)
                {
                    al2 = al.CREATOR.c(parcel);
                } else
                {
                    al2 = null;
                }
                if (parcel.readInt() != 0)
                {
                    ai4 = ai.CREATOR.b(parcel);
                } else
                {
                    ai4 = null;
                }
                a(d5, al2, ai4, parcel.readString(), bv.a.k(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                d d4 = getView();
                parcel1.writeNoException();
                IBinder ibinder = null;
                if (d4 != null)
                {
                    ibinder = d4.asBinder();
                }
                parcel1.writeStrongBinder(ibinder);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                d d3 = com.google.android.gms.dynamic.d.a.ag(parcel.readStrongBinder());
                int l = parcel.readInt();
                ai ai3 = null;
                if (l != 0)
                {
                    ai3 = ai.CREATOR.b(parcel);
                }
                a(d3, ai3, parcel.readString(), bv.a.k(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                showInterstitial();
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                destroy();
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                d d2 = com.google.android.gms.dynamic.d.a.ag(parcel.readStrongBinder());
                al al1;
                ai ai2;
                if (parcel.readInt() != 0)
                {
                    al1 = al.CREATOR.c(parcel);
                } else
                {
                    al1 = null;
                }
                if (parcel.readInt() != 0)
                {
                    ai2 = ai.CREATOR.b(parcel);
                } else
                {
                    ai2 = null;
                }
                a(d2, al1, ai2, parcel.readString(), parcel.readString(), bv.a.k(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                d d1 = com.google.android.gms.dynamic.d.a.ag(parcel.readStrongBinder());
                ai ai1;
                if (parcel.readInt() != 0)
                {
                    ai1 = ai.CREATOR.b(parcel);
                } else
                {
                    ai1 = null;
                }
                a(d1, ai1, parcel.readString(), parcel.readString(), bv.a.k(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                pause();
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                resume();
                parcel1.writeNoException();
                return true;
            }
        }

        public a()
        {
            attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        }
    }

    private static class a.a
        implements bu
    {

        private IBinder kq;

        public void a(d d1, ai ai1, String s, bv bv1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            if (d1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = d1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if (ai1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            ai1.writeToParcel(parcel, 0);
_L6:
            parcel.writeString(s);
            IBinder ibinder1;
            ibinder1 = null;
            if (bv1 == null)
            {
                break MISSING_BLOCK_LABEL_76;
            }
            ibinder1 = bv1.asBinder();
            parcel.writeStrongBinder(ibinder1);
            kq.transact(3, parcel, parcel1, 0);
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

        public void a(d d1, ai ai1, String s, String s1, bv bv1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            if (d1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = d1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if (ai1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            ai1.writeToParcel(parcel, 0);
_L6:
            parcel.writeString(s);
            parcel.writeString(s1);
            IBinder ibinder1;
            ibinder1 = null;
            if (bv1 == null)
            {
                break MISSING_BLOCK_LABEL_83;
            }
            ibinder1 = bv1.asBinder();
            parcel.writeStrongBinder(ibinder1);
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

        public void a(d d1, al al1, ai ai1, String s, bv bv1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            if (d1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = d1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if (al1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            al1.writeToParcel(parcel, 0);
_L6:
            if (ai1 == null)
            {
                break MISSING_BLOCK_LABEL_163;
            }
            parcel.writeInt(1);
            ai1.writeToParcel(parcel, 0);
_L7:
            parcel.writeString(s);
            IBinder ibinder1;
            ibinder1 = null;
            if (bv1 == null)
            {
                break MISSING_BLOCK_LABEL_94;
            }
            ibinder1 = bv1.asBinder();
            parcel.writeStrongBinder(ibinder1);
            kq.transact(1, parcel, parcel1, 0);
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
            parcel.writeInt(0);
              goto _L7
        }

        public void a(d d1, al al1, ai ai1, String s, String s1, bv bv1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            if (d1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = d1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if (al1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            al1.writeToParcel(parcel, 0);
_L6:
            if (ai1 == null)
            {
                break MISSING_BLOCK_LABEL_171;
            }
            parcel.writeInt(1);
            ai1.writeToParcel(parcel, 0);
_L7:
            parcel.writeString(s);
            parcel.writeString(s1);
            IBinder ibinder1;
            ibinder1 = null;
            if (bv1 == null)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            ibinder1 = bv1.asBinder();
            parcel.writeStrongBinder(ibinder1);
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
            parcel.writeInt(0);
              goto _L7
        }

        public IBinder asBinder()
        {
            return kq;
        }

        public void destroy()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
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

        public d getView()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            d d1;
            parcel.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            kq.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            d1 = com.google.android.gms.dynamic.d.a.ag(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return d1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void pause()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
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

        public void resume()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            kq.transact(9, parcel, parcel1, 0);
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

        public void showInterstitial()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
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

        a.a(IBinder ibinder)
        {
            kq = ibinder;
        }
    }


    public abstract void a(d d, ai ai, String s, bv bv)
        throws RemoteException;

    public abstract void a(d d, ai ai, String s, String s1, bv bv)
        throws RemoteException;

    public abstract void a(d d, al al, ai ai, String s, bv bv)
        throws RemoteException;

    public abstract void a(d d, al al, ai ai, String s, String s1, bv bv)
        throws RemoteException;

    public abstract void destroy()
        throws RemoteException;

    public abstract d getView()
        throws RemoteException;

    public abstract void pause()
        throws RemoteException;

    public abstract void resume()
        throws RemoteException;

    public abstract void showInterstitial()
        throws RemoteException;
}
