// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.f;

// Referenced classes of package com.google.android.gms.wearable.internal:
//            ac, af, ai

public static abstract class attachInterface extends Binder
    implements ac
{
    private static class a
        implements ac
    {

        private IBinder kq;

        public void Y(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableListener");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_44;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void a(af af1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableListener");
            if (af1 == null)
            {
                break MISSING_BLOCK_LABEL_44;
            }
            parcel.writeInt(1);
            af1.writeToParcel(parcel, 0);
_L1:
            kq.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void a(ai ai1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableListener");
            if (ai1 == null)
            {
                break MISSING_BLOCK_LABEL_44;
            }
            parcel.writeInt(1);
            ai1.writeToParcel(parcel, 0);
_L1:
            kq.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public IBinder asBinder()
        {
            return kq;
        }

        public void b(ai ai1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableListener");
            if (ai1 == null)
            {
                break MISSING_BLOCK_LABEL_44;
            }
            parcel.writeInt(1);
            ai1.writeToParcel(parcel, 0);
_L1:
            kq.transact(4, parcel, null, 1);
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


    public static ac bx(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        android.os.IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableListener");
        if (iinterface != null && (iinterface instanceof ac))
        {
            return (ac)iinterface;
        } else
        {
            return new a(ibinder);
        }
    }

    public IBinder asBinder()
    {
        return this;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
        throws RemoteException
    {
        int k;
        ai ai1;
        switch (i)
        {
        default:
            return super.onTransact(i, parcel, parcel1, j);

        case 1598968902: 
            parcel1.writeString("com.google.android.gms.wearable.internal.IWearableListener");
            return true;

        case 1: // '\001'
            parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
            int j1 = parcel.readInt();
            DataHolder dataholder = null;
            if (j1 != 0)
            {
                dataholder = DataHolder.CREATOR.x(parcel);
            }
            Y(dataholder);
            return true;

        case 2: // '\002'
            parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
            int i1 = parcel.readInt();
            af af1 = null;
            if (i1 != 0)
            {
                af1 = (af)af.CREATOR.createFromParcel(parcel);
            }
            a(af1);
            return true;

        case 3: // '\003'
            parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
            int l = parcel.readInt();
            ai ai2 = null;
            if (l != 0)
            {
                ai2 = (ai)ai.CREATOR.createFromParcel(parcel);
            }
            a(ai2);
            return true;

        case 4: // '\004'
            parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
            k = parcel.readInt();
            ai1 = null;
            break;
        }
        if (k != 0)
        {
            ai1 = (ai)ai.CREATOR.createFromParcel(parcel);
        }
        b(ai1);
        return true;
    }

    public a.kq()
    {
        attachInterface(this, "com.google.android.gms.wearable.internal.IWearableListener");
    }
}
