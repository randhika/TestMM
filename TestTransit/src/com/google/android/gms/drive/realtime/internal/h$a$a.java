// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.realtime.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

// Referenced classes of package com.google.android.gms.drive.realtime.internal:
//            h

private static class kq
    implements h
{

    private IBinder kq;

    public IBinder asBinder()
    {
        return kq;
    }

    public void c(boolean flag, boolean flag1)
        throws RemoteException
    {
        int i;
        Parcel parcel;
        Parcel parcel1;
        i = 1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IDocumentSaveStateEventCallback");
        int j;
        if (flag)
        {
            j = i;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        if (!flag1)
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

    (IBinder ibinder)
    {
        kq = ibinder;
    }
}
