// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;

// Referenced classes of package com.google.android.gms.internal:
//            ge, gj

public interface gm
    extends IInterface
{
    public static abstract class a extends Binder
        implements gm
    {

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            int k;
            gj gj1;
            switch (i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                T(parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                int j1 = parcel.readInt();
                ApplicationMetadata applicationmetadata = null;
                if (j1 != 0)
                {
                    applicationmetadata = (ApplicationMetadata)ApplicationMetadata.CREATOR.createFromParcel(parcel);
                }
                String s1 = parcel.readString();
                String s2 = parcel.readString();
                int k1 = parcel.readInt();
                boolean flag1 = false;
                if (k1 != 0)
                {
                    flag1 = true;
                }
                a(applicationmetadata, s1, s2, flag1);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                U(parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                String s = parcel.readString();
                double d = parcel.readDouble();
                int i1 = parcel.readInt();
                boolean flag = false;
                if (i1 != 0)
                {
                    flag = true;
                }
                a(s, d, flag);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                g(parcel.readString(), parcel.readString());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                b(parcel.readString(), parcel.createByteArray());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                W(parcel.readInt());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                V(parcel.readInt());
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                onApplicationDisconnected(parcel.readInt());
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                a(parcel.readString(), parcel.readLong(), parcel.readInt());
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                a(parcel.readString(), parcel.readLong());
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                int l = parcel.readInt();
                ge ge1 = null;
                if (l != 0)
                {
                    ge1 = (ge)ge.CREATOR.createFromParcel(parcel);
                }
                b(ge1);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                k = parcel.readInt();
                gj1 = null;
                break;
            }
            if (k != 0)
            {
                gj1 = (gj)gj.CREATOR.createFromParcel(parcel);
            }
            b(gj1);
            return true;
        }

        public a()
        {
            attachInterface(this, "com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        }
    }


    public abstract void T(int i)
        throws RemoteException;

    public abstract void U(int i)
        throws RemoteException;

    public abstract void V(int i)
        throws RemoteException;

    public abstract void W(int i)
        throws RemoteException;

    public abstract void a(ApplicationMetadata applicationmetadata, String s, String s1, boolean flag)
        throws RemoteException;

    public abstract void a(String s, double d, boolean flag)
        throws RemoteException;

    public abstract void a(String s, long l)
        throws RemoteException;

    public abstract void a(String s, long l, int i)
        throws RemoteException;

    public abstract void b(ge ge)
        throws RemoteException;

    public abstract void b(gj gj)
        throws RemoteException;

    public abstract void b(String s, byte abyte0[])
        throws RemoteException;

    public abstract void g(String s, String s1)
        throws RemoteException;

    public abstract void onApplicationDisconnected(int i)
        throws RemoteException;
}
