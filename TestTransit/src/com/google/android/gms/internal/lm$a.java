// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.d;

// Referenced classes of package com.google.android.gms.internal:
//            lm, lg, lp, lo

public static abstract class a.kq extends Binder
    implements lm
{
    private static class a
        implements lm
    {

        private IBinder kq;

        public void a(Bundle bundle, lp lp1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            if (bundle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            IBinder ibinder;
            ibinder = null;
            if (lp1 == null)
            {
                break MISSING_BLOCK_LABEL_40;
            }
            ibinder = lp1.asBinder();
            parcel.writeStrongBinder(ibinder);
            kq.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void a(lg lg1, Bundle bundle, lp lp1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            if (lg1 == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            lg1.writeToParcel(parcel, 0);
_L3:
            if (bundle == null)
            {
                break MISSING_BLOCK_LABEL_109;
            }
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            IBinder ibinder;
            ibinder = null;
            if (lp1 == null)
            {
                break MISSING_BLOCK_LABEL_61;
            }
            ibinder = lp1.asBinder();
            parcel.writeStrongBinder(ibinder);
            kq.transact(8, parcel, null, 1);
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

        public void a(FullWalletRequest fullwalletrequest, Bundle bundle, lp lp1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            if (fullwalletrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            fullwalletrequest.writeToParcel(parcel, 0);
_L3:
            if (bundle == null)
            {
                break MISSING_BLOCK_LABEL_108;
            }
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            IBinder ibinder;
            ibinder = null;
            if (lp1 == null)
            {
                break MISSING_BLOCK_LABEL_61;
            }
            ibinder = lp1.asBinder();
            parcel.writeStrongBinder(ibinder);
            kq.transact(2, parcel, null, 1);
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

        public void a(MaskedWalletRequest maskedwalletrequest, Bundle bundle, lo lo1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            if (maskedwalletrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            maskedwalletrequest.writeToParcel(parcel, 0);
_L3:
            if (bundle == null)
            {
                break MISSING_BLOCK_LABEL_109;
            }
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            IBinder ibinder;
            ibinder = null;
            if (lo1 == null)
            {
                break MISSING_BLOCK_LABEL_61;
            }
            ibinder = lo1.asBinder();
            parcel.writeStrongBinder(ibinder);
            kq.transact(7, parcel, null, 1);
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

        public void a(MaskedWalletRequest maskedwalletrequest, Bundle bundle, lp lp1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            if (maskedwalletrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            maskedwalletrequest.writeToParcel(parcel, 0);
_L3:
            if (bundle == null)
            {
                break MISSING_BLOCK_LABEL_108;
            }
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            IBinder ibinder;
            ibinder = null;
            if (lp1 == null)
            {
                break MISSING_BLOCK_LABEL_61;
            }
            ibinder = lp1.asBinder();
            parcel.writeStrongBinder(ibinder);
            kq.transact(1, parcel, null, 1);
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

        public void a(NotifyTransactionStatusRequest notifytransactionstatusrequest, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            if (notifytransactionstatusrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            notifytransactionstatusrequest.writeToParcel(parcel, 0);
_L3:
            if (bundle == null)
            {
                break MISSING_BLOCK_LABEL_76;
            }
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            kq.transact(4, parcel, null, 1);
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

        public void a(d d1, Bundle bundle, lp lp1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            if (d1 == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            d1.writeToParcel(parcel, 0);
_L3:
            if (bundle == null)
            {
                break MISSING_BLOCK_LABEL_109;
            }
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            IBinder ibinder;
            ibinder = null;
            if (lp1 == null)
            {
                break MISSING_BLOCK_LABEL_61;
            }
            ibinder = lp1.asBinder();
            parcel.writeStrongBinder(ibinder);
            kq.transact(6, parcel, null, 1);
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

        public void a(String s, String s1, Bundle bundle, lp lp1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            parcel.writeString(s);
            parcel.writeString(s1);
            if (bundle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            IBinder ibinder;
            ibinder = null;
            if (lp1 == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            ibinder = lp1.asBinder();
            parcel.writeStrongBinder(ibinder);
            kq.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public IBinder asBinder()
        {
            return kq;
        }

        public void o(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            if (bundle == null)
            {
                break MISSING_BLOCK_LABEL_45;
            }
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            kq.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        a(IBinder ibinder)
        {
            kq = ibinder;
        }
    }


    public static lm bq(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        android.os.IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.wallet.internal.IOwService");
        if (iinterface != null && (iinterface instanceof lm))
        {
            return (lm)iinterface;
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
            parcel1.writeString("com.google.android.gms.wallet.internal.IOwService");
            return true;

        case 1: // '\001'
            parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
            MaskedWalletRequest maskedwalletrequest1;
            Bundle bundle8;
            if (parcel.readInt() != 0)
            {
                maskedwalletrequest1 = (MaskedWalletRequest)MaskedWalletRequest.CREATOR.createFromParcel(parcel);
            } else
            {
                maskedwalletrequest1 = null;
            }
            if (parcel.readInt() != 0)
            {
                bundle8 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            } else
            {
                bundle8 = null;
            }
            a(maskedwalletrequest1, bundle8, bt(parcel.readStrongBinder()));
            return true;

        case 2: // '\002'
            parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
            FullWalletRequest fullwalletrequest;
            Bundle bundle7;
            if (parcel.readInt() != 0)
            {
                fullwalletrequest = (FullWalletRequest)FullWalletRequest.CREATOR.createFromParcel(parcel);
            } else
            {
                fullwalletrequest = null;
            }
            if (parcel.readInt() != 0)
            {
                bundle7 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            } else
            {
                bundle7 = null;
            }
            a(fullwalletrequest, bundle7, bt(parcel.readStrongBinder()));
            return true;

        case 3: // '\003'
            parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
            String s = parcel.readString();
            String s1 = parcel.readString();
            Bundle bundle6;
            if (parcel.readInt() != 0)
            {
                bundle6 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            } else
            {
                bundle6 = null;
            }
            a(s, s1, bundle6, bt(parcel.readStrongBinder()));
            return true;

        case 4: // '\004'
            parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
            NotifyTransactionStatusRequest notifytransactionstatusrequest;
            Bundle bundle5;
            if (parcel.readInt() != 0)
            {
                notifytransactionstatusrequest = (NotifyTransactionStatusRequest)NotifyTransactionStatusRequest.CREATOR.createFromParcel(parcel);
            } else
            {
                notifytransactionstatusrequest = null;
            }
            if (parcel.readInt() != 0)
            {
                bundle5 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            } else
            {
                bundle5 = null;
            }
            a(notifytransactionstatusrequest, bundle5);
            return true;

        case 5: // '\005'
            parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
            Bundle bundle4;
            if (parcel.readInt() != 0)
            {
                bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            } else
            {
                bundle4 = null;
            }
            a(bundle4, bt(parcel.readStrongBinder()));
            return true;

        case 6: // '\006'
            parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
            d d1;
            Bundle bundle3;
            if (parcel.readInt() != 0)
            {
                d1 = (d)d.CREATOR.createFromParcel(parcel);
            } else
            {
                d1 = null;
            }
            if (parcel.readInt() != 0)
            {
                bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            } else
            {
                bundle3 = null;
            }
            a(d1, bundle3, bt(parcel.readStrongBinder()));
            return true;

        case 7: // '\007'
            parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
            MaskedWalletRequest maskedwalletrequest;
            Bundle bundle2;
            if (parcel.readInt() != 0)
            {
                maskedwalletrequest = (MaskedWalletRequest)MaskedWalletRequest.CREATOR.createFromParcel(parcel);
            } else
            {
                maskedwalletrequest = null;
            }
            if (parcel.readInt() != 0)
            {
                bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            } else
            {
                bundle2 = null;
            }
            a(maskedwalletrequest, bundle2, bs(parcel.readStrongBinder()));
            return true;

        case 8: // '\b'
            parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
            lg lg1;
            Bundle bundle1;
            if (parcel.readInt() != 0)
            {
                lg1 = (lg)lg.CREATOR.createFromParcel(parcel);
            } else
            {
                lg1 = null;
            }
            if (parcel.readInt() != 0)
            {
                bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            } else
            {
                bundle1 = null;
            }
            a(lg1, bundle1, bt(parcel.readStrongBinder()));
            return true;

        case 9: // '\t'
            parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
            break;
        }
        Bundle bundle;
        if (parcel.readInt() != 0)
        {
            bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
        } else
        {
            bundle = null;
        }
        o(bundle);
        return true;
    }
}
