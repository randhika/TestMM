// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.f;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;

public interface IGamesCallbacks
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IGamesCallbacks
    {

        public static IGamesCallbacks ai(IBinder ibinder)
        {
            if (ibinder == null)
            {
                return null;
            }
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.games.internal.IGamesCallbacks");
            if (iinterface != null && (iinterface instanceof IGamesCallbacks))
            {
                return (IGamesCallbacks)iinterface;
            } else
            {
                return new Proxy(ibinder);
            }
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i1, Parcel parcel, Parcel parcel1, int j1)
            throws RemoteException
        {
            int k1;
            switch (i1)
            {
            default:
                return super.onTransact(i1, parcel, parcel1, j1);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.games.internal.IGamesCallbacks");
                return true;

            case 5001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                d(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder dataholder49;
                if (parcel.readInt() != 0)
                {
                    dataholder49 = DataHolder.CREATOR.x(parcel);
                } else
                {
                    dataholder49 = null;
                }
                c(dataholder49);
                parcel1.writeNoException();
                return true;

            case 5003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                e(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5004: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k14 = parcel.readInt();
                DataHolder dataholder48 = null;
                if (k14 != 0)
                {
                    dataholder48 = DataHolder.CREATOR.x(parcel);
                }
                e(dataholder48);
                parcel1.writeNoException();
                return true;

            case 5005: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder dataholder46;
                int j14;
                DataHolder dataholder47;
                if (parcel.readInt() != 0)
                {
                    dataholder46 = DataHolder.CREATOR.x(parcel);
                } else
                {
                    dataholder46 = null;
                }
                j14 = parcel.readInt();
                dataholder47 = null;
                if (j14 != 0)
                {
                    dataholder47 = DataHolder.CREATOR.x(parcel);
                }
                a(dataholder46, dataholder47);
                parcel1.writeNoException();
                return true;

            case 5006: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i14 = parcel.readInt();
                DataHolder dataholder45 = null;
                if (i14 != 0)
                {
                    dataholder45 = DataHolder.CREATOR.x(parcel);
                }
                f(dataholder45);
                parcel1.writeNoException();
                return true;

            case 5007: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l13 = parcel.readInt();
                DataHolder dataholder44 = null;
                if (l13 != 0)
                {
                    dataholder44 = DataHolder.CREATOR.x(parcel);
                }
                g(dataholder44);
                parcel1.writeNoException();
                return true;

            case 5008: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k13 = parcel.readInt();
                DataHolder dataholder43 = null;
                if (k13 != 0)
                {
                    dataholder43 = DataHolder.CREATOR.x(parcel);
                }
                h(dataholder43);
                parcel1.writeNoException();
                return true;

            case 5009: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j13 = parcel.readInt();
                DataHolder dataholder42 = null;
                if (j13 != 0)
                {
                    dataholder42 = DataHolder.CREATOR.x(parcel);
                }
                i(dataholder42);
                parcel1.writeNoException();
                return true;

            case 5010: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i13 = parcel.readInt();
                DataHolder dataholder41 = null;
                if (i13 != 0)
                {
                    dataholder41 = DataHolder.CREATOR.x(parcel);
                }
                j(dataholder41);
                parcel1.writeNoException();
                return true;

            case 5011: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l12 = parcel.readInt();
                DataHolder dataholder40 = null;
                if (l12 != 0)
                {
                    dataholder40 = DataHolder.CREATOR.x(parcel);
                }
                k(dataholder40);
                parcel1.writeNoException();
                return true;

            case 5016: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                dT();
                parcel1.writeNoException();
                return true;

            case 5017: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k12 = parcel.readInt();
                DataHolder dataholder39 = null;
                if (k12 != 0)
                {
                    dataholder39 = DataHolder.CREATOR.x(parcel);
                }
                m(dataholder39);
                parcel1.writeNoException();
                return true;

            case 5037: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j12 = parcel.readInt();
                DataHolder dataholder38 = null;
                if (j12 != 0)
                {
                    dataholder38 = DataHolder.CREATOR.x(parcel);
                }
                n(dataholder38);
                parcel1.writeNoException();
                return true;

            case 5018: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i12 = parcel.readInt();
                DataHolder dataholder37 = null;
                if (i12 != 0)
                {
                    dataholder37 = DataHolder.CREATOR.x(parcel);
                }
                u(dataholder37);
                parcel1.writeNoException();
                return true;

            case 5019: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l11 = parcel.readInt();
                DataHolder dataholder36 = null;
                if (l11 != 0)
                {
                    dataholder36 = DataHolder.CREATOR.x(parcel);
                }
                v(dataholder36);
                parcel1.writeNoException();
                return true;

            case 5020: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                onLeftRoom(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5021: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k11 = parcel.readInt();
                DataHolder dataholder35 = null;
                if (k11 != 0)
                {
                    dataholder35 = DataHolder.CREATOR.x(parcel);
                }
                w(dataholder35);
                parcel1.writeNoException();
                return true;

            case 5022: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j11 = parcel.readInt();
                DataHolder dataholder34 = null;
                if (j11 != 0)
                {
                    dataholder34 = DataHolder.CREATOR.x(parcel);
                }
                x(dataholder34);
                parcel1.writeNoException();
                return true;

            case 5023: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i11 = parcel.readInt();
                DataHolder dataholder33 = null;
                if (i11 != 0)
                {
                    dataholder33 = DataHolder.CREATOR.x(parcel);
                }
                y(dataholder33);
                parcel1.writeNoException();
                return true;

            case 5024: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l10 = parcel.readInt();
                DataHolder dataholder32 = null;
                if (l10 != 0)
                {
                    dataholder32 = DataHolder.CREATOR.x(parcel);
                }
                z(dataholder32);
                parcel1.writeNoException();
                return true;

            case 5025: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k10 = parcel.readInt();
                DataHolder dataholder31 = null;
                if (k10 != 0)
                {
                    dataholder31 = DataHolder.CREATOR.x(parcel);
                }
                A(dataholder31);
                parcel1.writeNoException();
                return true;

            case 5026: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j10 = parcel.readInt();
                DataHolder dataholder30 = null;
                if (j10 != 0)
                {
                    dataholder30 = DataHolder.CREATOR.x(parcel);
                }
                a(dataholder30, parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 5027: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i10 = parcel.readInt();
                DataHolder dataholder29 = null;
                if (i10 != 0)
                {
                    dataholder29 = DataHolder.CREATOR.x(parcel);
                }
                b(dataholder29, parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 5028: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l9 = parcel.readInt();
                DataHolder dataholder28 = null;
                if (l9 != 0)
                {
                    dataholder28 = DataHolder.CREATOR.x(parcel);
                }
                c(dataholder28, parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 5029: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k9 = parcel.readInt();
                DataHolder dataholder27 = null;
                if (k9 != 0)
                {
                    dataholder27 = DataHolder.CREATOR.x(parcel);
                }
                d(dataholder27, parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 5030: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j9 = parcel.readInt();
                DataHolder dataholder26 = null;
                if (j9 != 0)
                {
                    dataholder26 = DataHolder.CREATOR.x(parcel);
                }
                e(dataholder26, parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 5031: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i9 = parcel.readInt();
                DataHolder dataholder25 = null;
                if (i9 != 0)
                {
                    dataholder25 = DataHolder.CREATOR.x(parcel);
                }
                f(dataholder25, parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 5032: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                RealTimeMessage realtimemessage;
                if (parcel.readInt() != 0)
                {
                    realtimemessage = (RealTimeMessage)RealTimeMessage.CREATOR.createFromParcel(parcel);
                } else
                {
                    realtimemessage = null;
                }
                onRealTimeMessageReceived(realtimemessage);
                parcel1.writeNoException();
                return true;

            case 5033: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                b(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5034: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l8 = parcel.readInt();
                String s2 = parcel.readString();
                boolean flag;
                if (parcel.readInt() != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                a(l8, s2, flag);
                parcel1.writeNoException();
                return true;

            case 5038: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k8 = parcel.readInt();
                DataHolder dataholder24 = null;
                if (k8 != 0)
                {
                    dataholder24 = DataHolder.CREATOR.x(parcel);
                }
                B(dataholder24);
                parcel1.writeNoException();
                return true;

            case 5035: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j8 = parcel.readInt();
                DataHolder dataholder23 = null;
                if (j8 != 0)
                {
                    dataholder23 = DataHolder.CREATOR.x(parcel);
                }
                C(dataholder23);
                parcel1.writeNoException();
                return true;

            case 5036: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                cd(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5039: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i8 = parcel.readInt();
                DataHolder dataholder22 = null;
                if (i8 != 0)
                {
                    dataholder22 = DataHolder.CREATOR.x(parcel);
                }
                D(dataholder22);
                parcel1.writeNoException();
                return true;

            case 5040: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                ce(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 6001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                onP2PConnected(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 6002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                onP2PDisconnected(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l7 = parcel.readInt();
                DataHolder dataholder21 = null;
                if (l7 != 0)
                {
                    dataholder21 = DataHolder.CREATOR.x(parcel);
                }
                E(dataholder21);
                parcel1.writeNoException();
                return true;

            case 8002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k7 = parcel.readInt();
                Bundle bundle4;
                if (parcel.readInt() != 0)
                {
                    bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                } else
                {
                    bundle4 = null;
                }
                b(k7, bundle4);
                parcel1.writeNoException();
                return true;

            case 8003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j7 = parcel.readInt();
                DataHolder dataholder20 = null;
                if (j7 != 0)
                {
                    dataholder20 = DataHolder.CREATOR.x(parcel);
                }
                p(dataholder20);
                parcel1.writeNoException();
                return true;

            case 8004: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i7 = parcel.readInt();
                DataHolder dataholder19 = null;
                if (i7 != 0)
                {
                    dataholder19 = DataHolder.CREATOR.x(parcel);
                }
                q(dataholder19);
                parcel1.writeNoException();
                return true;

            case 8005: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l6 = parcel.readInt();
                DataHolder dataholder18 = null;
                if (l6 != 0)
                {
                    dataholder18 = DataHolder.CREATOR.x(parcel);
                }
                r(dataholder18);
                parcel1.writeNoException();
                return true;

            case 8006: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k6 = parcel.readInt();
                DataHolder dataholder17 = null;
                if (k6 != 0)
                {
                    dataholder17 = DataHolder.CREATOR.x(parcel);
                }
                s(dataholder17);
                parcel1.writeNoException();
                return true;

            case 8007: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                f(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8008: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j6 = parcel.readInt();
                DataHolder dataholder16 = null;
                if (j6 != 0)
                {
                    dataholder16 = DataHolder.CREATOR.x(parcel);
                }
                t(dataholder16);
                parcel1.writeNoException();
                return true;

            case 8009: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                onTurnBasedMatchRemoved(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8010: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                onInvitationRemoved(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 9001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i6 = parcel.readInt();
                DataHolder dataholder15 = null;
                if (i6 != 0)
                {
                    dataholder15 = DataHolder.CREATOR.x(parcel);
                }
                l(dataholder15);
                parcel1.writeNoException();
                return true;

            case 10001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l5 = parcel.readInt();
                DataHolder dataholder14 = null;
                if (l5 != 0)
                {
                    dataholder14 = DataHolder.CREATOR.x(parcel);
                }
                o(dataholder14);
                parcel1.writeNoException();
                return true;

            case 10002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                onRequestRemoved(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 10003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k5 = parcel.readInt();
                DataHolder dataholder13 = null;
                if (k5 != 0)
                {
                    dataholder13 = DataHolder.CREATOR.x(parcel);
                }
                F(dataholder13);
                parcel1.writeNoException();
                return true;

            case 10004: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j5 = parcel.readInt();
                DataHolder dataholder12 = null;
                if (j5 != 0)
                {
                    dataholder12 = DataHolder.CREATOR.x(parcel);
                }
                G(dataholder12);
                parcel1.writeNoException();
                return true;

            case 10005: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i5 = parcel.readInt();
                Bundle bundle3;
                if (parcel.readInt() != 0)
                {
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                } else
                {
                    bundle3 = null;
                }
                c(i5, bundle3);
                parcel1.writeNoException();
                return true;

            case 10006: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l4 = parcel.readInt();
                DataHolder dataholder11 = null;
                if (l4 != 0)
                {
                    dataholder11 = DataHolder.CREATOR.x(parcel);
                }
                H(dataholder11);
                parcel1.writeNoException();
                return true;

            case 11001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k4 = parcel.readInt();
                Bundle bundle2;
                if (parcel.readInt() != 0)
                {
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                } else
                {
                    bundle2 = null;
                }
                d(k4, bundle2);
                parcel1.writeNoException();
                return true;

            case 12001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j4 = parcel.readInt();
                DataHolder dataholder10 = null;
                if (j4 != 0)
                {
                    dataholder10 = DataHolder.CREATOR.x(parcel);
                }
                I(dataholder10);
                parcel1.writeNoException();
                return true;

            case 12004: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder dataholder9;
                Contents contents3;
                if (parcel.readInt() != 0)
                {
                    dataholder9 = DataHolder.CREATOR.x(parcel);
                } else
                {
                    dataholder9 = null;
                }
                if (parcel.readInt() != 0)
                {
                    contents3 = (Contents)Contents.CREATOR.createFromParcel(parcel);
                } else
                {
                    contents3 = null;
                }
                a(dataholder9, contents3);
                parcel1.writeNoException();
                return true;

            case 12017: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder dataholder8;
                String s1;
                Contents contents;
                Contents contents1;
                int i4;
                Contents contents2;
                if (parcel.readInt() != 0)
                {
                    dataholder8 = DataHolder.CREATOR.x(parcel);
                } else
                {
                    dataholder8 = null;
                }
                s1 = parcel.readString();
                if (parcel.readInt() != 0)
                {
                    contents = (Contents)Contents.CREATOR.createFromParcel(parcel);
                } else
                {
                    contents = null;
                }
                if (parcel.readInt() != 0)
                {
                    contents1 = (Contents)Contents.CREATOR.createFromParcel(parcel);
                } else
                {
                    contents1 = null;
                }
                i4 = parcel.readInt();
                contents2 = null;
                if (i4 != 0)
                {
                    contents2 = (Contents)Contents.CREATOR.createFromParcel(parcel);
                }
                a(dataholder8, s1, contents, contents1, contents2);
                parcel1.writeNoException();
                return true;

            case 12005: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l3 = parcel.readInt();
                DataHolder dataholder7 = null;
                if (l3 != 0)
                {
                    dataholder7 = DataHolder.CREATOR.x(parcel);
                }
                J(dataholder7);
                parcel1.writeNoException();
                return true;

            case 12012: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                g(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 12003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k3 = parcel.readInt();
                Bundle bundle1;
                if (parcel.readInt() != 0)
                {
                    bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                } else
                {
                    bundle1 = null;
                }
                e(k3, bundle1);
                parcel1.writeNoException();
                return true;

            case 12013: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j3 = parcel.readInt();
                DataHolder dataholder6 = null;
                if (j3 != 0)
                {
                    dataholder6 = DataHolder.CREATOR.x(parcel);
                }
                P(dataholder6);
                parcel1.writeNoException();
                return true;

            case 12011: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i3 = parcel.readInt();
                DataHolder dataholder5 = null;
                if (i3 != 0)
                {
                    dataholder5 = DataHolder.CREATOR.x(parcel);
                }
                d(dataholder5);
                parcel1.writeNoException();
                return true;

            case 12006: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l2 = parcel.readInt();
                DataHolder dataholder4 = null;
                if (l2 != 0)
                {
                    dataholder4 = DataHolder.CREATOR.x(parcel);
                }
                K(dataholder4);
                parcel1.writeNoException();
                return true;

            case 12007: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k2 = parcel.readInt();
                DataHolder dataholder3 = null;
                if (k2 != 0)
                {
                    dataholder3 = DataHolder.CREATOR.x(parcel);
                }
                L(dataholder3);
                parcel1.writeNoException();
                return true;

            case 12014: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j2 = parcel.readInt();
                DataHolder dataholder2 = null;
                if (j2 != 0)
                {
                    dataholder2 = DataHolder.CREATOR.x(parcel);
                }
                M(dataholder2);
                parcel1.writeNoException();
                return true;

            case 12016: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i2 = parcel.readInt();
                DataHolder dataholder1 = null;
                if (i2 != 0)
                {
                    dataholder1 = DataHolder.CREATOR.x(parcel);
                }
                N(dataholder1);
                parcel1.writeNoException();
                return true;

            case 12008: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l1 = parcel.readInt();
                DataHolder dataholder = null;
                if (l1 != 0)
                {
                    dataholder = DataHolder.CREATOR.x(parcel);
                }
                O(dataholder);
                parcel1.writeNoException();
                return true;

            case 12015: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                k1 = parcel.readInt();
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
            f(k1, bundle);
            parcel1.writeNoException();
            return true;
        }

        public Stub()
        {
            attachInterface(this, "com.google.android.gms.games.internal.IGamesCallbacks");
        }
    }

    private static class Stub.Proxy
        implements IGamesCallbacks
    {

        private IBinder kq;

        public void A(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5025, parcel, parcel1, 0);
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

        public void B(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5038, parcel, parcel1, 0);
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

        public void C(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5035, parcel, parcel1, 0);
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

        public void D(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5039, parcel, parcel1, 0);
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

        public void E(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(8001, parcel, parcel1, 0);
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

        public void F(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(10003, parcel, parcel1, 0);
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

        public void G(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(10004, parcel, parcel1, 0);
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

        public void H(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(10006, parcel, parcel1, 0);
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

        public void I(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(12001, parcel, parcel1, 0);
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

        public void J(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(12005, parcel, parcel1, 0);
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

        public void K(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(12006, parcel, parcel1, 0);
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

        public void L(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(12007, parcel, parcel1, 0);
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

        public void M(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(12014, parcel, parcel1, 0);
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

        public void N(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(12016, parcel, parcel1, 0);
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

        public void O(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(12008, parcel, parcel1, 0);
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

        public void P(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(12013, parcel, parcel1, 0);
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

        public void a(int i1, String s1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            parcel.writeString(s1);
            int j1;
            j1 = 0;
            if (flag)
            {
                j1 = 1;
            }
            parcel.writeInt(j1);
            kq.transact(5034, parcel, parcel1, 0);
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

        public void a(DataHolder dataholder, DataHolder dataholder1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L3:
            if (dataholder1 == null)
            {
                break MISSING_BLOCK_LABEL_99;
            }
            parcel.writeInt(1);
            dataholder1.writeToParcel(parcel, 0);
_L4:
            kq.transact(5005, parcel, parcel1, 0);
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

        public void a(DataHolder dataholder, Contents contents)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L3:
            if (contents == null)
            {
                break MISSING_BLOCK_LABEL_99;
            }
            parcel.writeInt(1);
            contents.writeToParcel(parcel, 0);
_L4:
            kq.transact(12004, parcel, parcel1, 0);
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

        public void a(DataHolder dataholder, String s1, Contents contents, Contents contents1, Contents contents2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L7:
            parcel.writeString(s1);
            if (contents == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            contents.writeToParcel(parcel, 0);
_L8:
            if (contents1 == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            contents1.writeToParcel(parcel, 0);
_L9:
            if (contents2 == null)
            {
                break MISSING_BLOCK_LABEL_171;
            }
            parcel.writeInt(1);
            contents2.writeToParcel(parcel, 0);
_L10:
            kq.transact(12017, parcel, parcel1, 0);
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
            parcel.writeInt(0);
              goto _L10
        }

        public void a(DataHolder dataholder, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_67;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            kq.transact(5026, parcel, parcel1, 0);
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

        public IBinder asBinder()
        {
            return kq;
        }

        public void b(int i1, int j1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeString(s1);
            kq.transact(5033, parcel, parcel1, 0);
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

        public void b(int i1, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            if (bundle == null)
            {
                break MISSING_BLOCK_LABEL_67;
            }
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            kq.transact(8002, parcel, parcel1, 0);
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

        public void b(DataHolder dataholder, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_67;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            kq.transact(5027, parcel, parcel1, 0);
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

        public void c(int i1, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            if (bundle == null)
            {
                break MISSING_BLOCK_LABEL_67;
            }
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            kq.transact(10005, parcel, parcel1, 0);
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

        public void c(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5002, parcel, parcel1, 0);
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

        public void c(DataHolder dataholder, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_67;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            kq.transact(5028, parcel, parcel1, 0);
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

        public void cd(int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            kq.transact(5036, parcel, parcel1, 0);
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

        public void ce(int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            kq.transact(5040, parcel, parcel1, 0);
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

        public void d(int i1, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            if (bundle == null)
            {
                break MISSING_BLOCK_LABEL_67;
            }
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            kq.transact(11001, parcel, parcel1, 0);
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

        public void d(int i1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            parcel.writeString(s1);
            kq.transact(5001, parcel, parcel1, 0);
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

        public void d(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(12011, parcel, parcel1, 0);
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

        public void d(DataHolder dataholder, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_67;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            kq.transact(5029, parcel, parcel1, 0);
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

        public void dT()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            kq.transact(5016, parcel, parcel1, 0);
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

        public void e(int i1, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            if (bundle == null)
            {
                break MISSING_BLOCK_LABEL_67;
            }
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            kq.transact(12003, parcel, parcel1, 0);
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

        public void e(int i1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            parcel.writeString(s1);
            kq.transact(5003, parcel, parcel1, 0);
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

        public void e(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5004, parcel, parcel1, 0);
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

        public void e(DataHolder dataholder, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_67;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            kq.transact(5030, parcel, parcel1, 0);
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

        public void f(int i1, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            if (bundle == null)
            {
                break MISSING_BLOCK_LABEL_67;
            }
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            kq.transact(12015, parcel, parcel1, 0);
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

        public void f(int i1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            parcel.writeString(s1);
            kq.transact(8007, parcel, parcel1, 0);
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

        public void f(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5006, parcel, parcel1, 0);
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

        public void f(DataHolder dataholder, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_67;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            kq.transact(5031, parcel, parcel1, 0);
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

        public void g(int i1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            parcel.writeString(s1);
            kq.transact(12012, parcel, parcel1, 0);
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

        public void g(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5007, parcel, parcel1, 0);
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

        public void h(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5008, parcel, parcel1, 0);
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

        public void i(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5009, parcel, parcel1, 0);
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

        public void j(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5010, parcel, parcel1, 0);
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

        public void k(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5011, parcel, parcel1, 0);
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

        public void l(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(9001, parcel, parcel1, 0);
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

        public void m(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5017, parcel, parcel1, 0);
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

        public void n(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5037, parcel, parcel1, 0);
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

        public void o(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(10001, parcel, parcel1, 0);
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

        public void onInvitationRemoved(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeString(s1);
            kq.transact(8010, parcel, parcel1, 0);
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

        public void onLeftRoom(int i1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            parcel.writeString(s1);
            kq.transact(5020, parcel, parcel1, 0);
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

        public void onP2PConnected(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeString(s1);
            kq.transact(6001, parcel, parcel1, 0);
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

        public void onP2PDisconnected(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeString(s1);
            kq.transact(6002, parcel, parcel1, 0);
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

        public void onRealTimeMessageReceived(RealTimeMessage realtimemessage)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (realtimemessage == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            realtimemessage.writeToParcel(parcel, 0);
_L1:
            kq.transact(5032, parcel, parcel1, 0);
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

        public void onRequestRemoved(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeString(s1);
            kq.transact(10002, parcel, parcel1, 0);
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

        public void onTurnBasedMatchRemoved(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeString(s1);
            kq.transact(8009, parcel, parcel1, 0);
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

        public void p(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(8003, parcel, parcel1, 0);
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

        public void q(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(8004, parcel, parcel1, 0);
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

        public void r(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(8005, parcel, parcel1, 0);
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

        public void s(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(8006, parcel, parcel1, 0);
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

        public void t(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(8008, parcel, parcel1, 0);
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

        public void u(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5018, parcel, parcel1, 0);
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

        public void v(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5019, parcel, parcel1, 0);
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

        public void w(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5021, parcel, parcel1, 0);
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

        public void x(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5022, parcel, parcel1, 0);
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

        public void y(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5023, parcel, parcel1, 0);
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

        public void z(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if (dataholder == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            kq.transact(5024, parcel, parcel1, 0);
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

        Stub.Proxy(IBinder ibinder)
        {
            kq = ibinder;
        }
    }


    public abstract void A(DataHolder dataholder)
        throws RemoteException;

    public abstract void B(DataHolder dataholder)
        throws RemoteException;

    public abstract void C(DataHolder dataholder)
        throws RemoteException;

    public abstract void D(DataHolder dataholder)
        throws RemoteException;

    public abstract void E(DataHolder dataholder)
        throws RemoteException;

    public abstract void F(DataHolder dataholder)
        throws RemoteException;

    public abstract void G(DataHolder dataholder)
        throws RemoteException;

    public abstract void H(DataHolder dataholder)
        throws RemoteException;

    public abstract void I(DataHolder dataholder)
        throws RemoteException;

    public abstract void J(DataHolder dataholder)
        throws RemoteException;

    public abstract void K(DataHolder dataholder)
        throws RemoteException;

    public abstract void L(DataHolder dataholder)
        throws RemoteException;

    public abstract void M(DataHolder dataholder)
        throws RemoteException;

    public abstract void N(DataHolder dataholder)
        throws RemoteException;

    public abstract void O(DataHolder dataholder)
        throws RemoteException;

    public abstract void P(DataHolder dataholder)
        throws RemoteException;

    public abstract void a(int i1, String s1, boolean flag)
        throws RemoteException;

    public abstract void a(DataHolder dataholder, DataHolder dataholder1)
        throws RemoteException;

    public abstract void a(DataHolder dataholder, Contents contents)
        throws RemoteException;

    public abstract void a(DataHolder dataholder, String s1, Contents contents, Contents contents1, Contents contents2)
        throws RemoteException;

    public abstract void a(DataHolder dataholder, String as[])
        throws RemoteException;

    public abstract void b(int i1, int j1, String s1)
        throws RemoteException;

    public abstract void b(int i1, Bundle bundle)
        throws RemoteException;

    public abstract void b(DataHolder dataholder, String as[])
        throws RemoteException;

    public abstract void c(int i1, Bundle bundle)
        throws RemoteException;

    public abstract void c(DataHolder dataholder)
        throws RemoteException;

    public abstract void c(DataHolder dataholder, String as[])
        throws RemoteException;

    public abstract void cd(int i1)
        throws RemoteException;

    public abstract void ce(int i1)
        throws RemoteException;

    public abstract void d(int i1, Bundle bundle)
        throws RemoteException;

    public abstract void d(int i1, String s1)
        throws RemoteException;

    public abstract void d(DataHolder dataholder)
        throws RemoteException;

    public abstract void d(DataHolder dataholder, String as[])
        throws RemoteException;

    public abstract void dT()
        throws RemoteException;

    public abstract void e(int i1, Bundle bundle)
        throws RemoteException;

    public abstract void e(int i1, String s1)
        throws RemoteException;

    public abstract void e(DataHolder dataholder)
        throws RemoteException;

    public abstract void e(DataHolder dataholder, String as[])
        throws RemoteException;

    public abstract void f(int i1, Bundle bundle)
        throws RemoteException;

    public abstract void f(int i1, String s1)
        throws RemoteException;

    public abstract void f(DataHolder dataholder)
        throws RemoteException;

    public abstract void f(DataHolder dataholder, String as[])
        throws RemoteException;

    public abstract void g(int i1, String s1)
        throws RemoteException;

    public abstract void g(DataHolder dataholder)
        throws RemoteException;

    public abstract void h(DataHolder dataholder)
        throws RemoteException;

    public abstract void i(DataHolder dataholder)
        throws RemoteException;

    public abstract void j(DataHolder dataholder)
        throws RemoteException;

    public abstract void k(DataHolder dataholder)
        throws RemoteException;

    public abstract void l(DataHolder dataholder)
        throws RemoteException;

    public abstract void m(DataHolder dataholder)
        throws RemoteException;

    public abstract void n(DataHolder dataholder)
        throws RemoteException;

    public abstract void o(DataHolder dataholder)
        throws RemoteException;

    public abstract void onInvitationRemoved(String s1)
        throws RemoteException;

    public abstract void onLeftRoom(int i1, String s1)
        throws RemoteException;

    public abstract void onP2PConnected(String s1)
        throws RemoteException;

    public abstract void onP2PDisconnected(String s1)
        throws RemoteException;

    public abstract void onRealTimeMessageReceived(RealTimeMessage realtimemessage)
        throws RemoteException;

    public abstract void onRequestRemoved(String s1)
        throws RemoteException;

    public abstract void onTurnBasedMatchRemoved(String s1)
        throws RemoteException;

    public abstract void p(DataHolder dataholder)
        throws RemoteException;

    public abstract void q(DataHolder dataholder)
        throws RemoteException;

    public abstract void r(DataHolder dataholder)
        throws RemoteException;

    public abstract void s(DataHolder dataholder)
        throws RemoteException;

    public abstract void t(DataHolder dataholder)
        throws RemoteException;

    public abstract void u(DataHolder dataholder)
        throws RemoteException;

    public abstract void v(DataHolder dataholder)
        throws RemoteException;

    public abstract void w(DataHolder dataholder)
        throws RemoteException;

    public abstract void x(DataHolder dataholder)
        throws RemoteException;

    public abstract void y(DataHolder dataholder)
        throws RemoteException;

    public abstract void z(DataHolder dataholder)
        throws RemoteException;
}
