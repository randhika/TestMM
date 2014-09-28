// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.f;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.internal.multiplayer.InvitationClusterCreator;
import com.google.android.gms.games.internal.multiplayer.ZInvitationCluster;
import com.google.android.gms.games.internal.request.GameRequestCluster;
import com.google.android.gms.games.internal.request.GameRequestClusterCreator;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.SnapshotMetadataChangeCreator;

// Referenced classes of package com.google.android.gms.games.internal:
//            IGamesCallbacks

public interface IGamesService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IGamesService
    {

        public static IGamesService aj(IBinder ibinder)
        {
            if (ibinder == null)
            {
                return null;
            }
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.games.internal.IGamesService");
            if (iinterface != null && (iinterface instanceof IGamesService))
            {
                return (IGamesService)iinterface;
            } else
            {
                return new Proxy(ibinder);
            }
        }

        public boolean onTransact(int i1, Parcel parcel, Parcel parcel1, int j1)
            throws RemoteException
        {
            IGamesCallbacks igamescallbacks;
            String s1;
            String s2;
            int k1;
            switch (i1)
            {
            default:
                return super.onTransact(i1, parcel, parcel1, j1);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.games.internal.IGamesService");
                return true;

            case 5001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                q(parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 5002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                String s50 = ho();
                parcel1.writeNoException();
                parcel1.writeString(s50);
                return true;

            case 5004: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Bundle bundle9 = ef();
                parcel1.writeNoException();
                if (bundle9 != null)
                {
                    parcel1.writeInt(1);
                    bundle9.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 5005: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IBinder ibinder6 = parcel.readStrongBinder();
                Bundle bundle8;
                if (parcel.readInt() != 0)
                {
                    bundle8 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                } else
                {
                    bundle8 = null;
                }
                a(ibinder6, bundle8);
                parcel1.writeNoException();
                return true;

            case 5006: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                hw();
                parcel1.writeNoException();
                return true;

            case 5007: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                String s49 = gZ();
                parcel1.writeNoException();
                parcel1.writeString(s49);
                return true;

            case 5064: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                String s48 = aV(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(s48);
                return true;

            case 5065: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                m(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5012: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                String s47 = ha();
                parcel1.writeNoException();
                parcel1.writeString(s47);
                return true;

            case 5013: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                DataHolder dataholder1 = hy();
                parcel1.writeNoException();
                if (dataholder1 != null)
                {
                    parcel1.writeInt(1);
                    dataholder1.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 5014: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5015: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks50 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                int k23 = parcel.readInt();
                boolean flag66;
                int l23;
                boolean flag67;
                if (parcel.readInt() != 0)
                {
                    flag66 = true;
                } else
                {
                    flag66 = false;
                }
                l23 = parcel.readInt();
                flag67 = false;
                if (l23 != 0)
                {
                    flag67 = true;
                }
                a(igamescallbacks50, k23, flag66, flag67);
                parcel1.writeNoException();
                return true;

            case 5016: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString(), parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 5017: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                b(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5018: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                b(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5019: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks49 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s46 = parcel.readString();
                int l22 = parcel.readInt();
                int i23 = parcel.readInt();
                int j23 = parcel.readInt();
                boolean flag65;
                if (parcel.readInt() != 0)
                {
                    flag65 = true;
                } else
                {
                    flag65 = false;
                }
                a(igamescallbacks49, s46, l22, i23, j23, flag65);
                parcel1.writeNoException();
                return true;

            case 5020: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks48 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s45 = parcel.readString();
                int i22 = parcel.readInt();
                int j22 = parcel.readInt();
                int k22 = parcel.readInt();
                boolean flag64;
                if (parcel.readInt() != 0)
                {
                    flag64 = true;
                } else
                {
                    flag64 = false;
                }
                b(igamescallbacks48, s45, i22, j22, k22, flag64);
                parcel1.writeNoException();
                return true;

            case 5021: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks47 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                Bundle bundle7;
                if (parcel.readInt() != 0)
                {
                    bundle7 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                } else
                {
                    bundle7 = null;
                }
                a(igamescallbacks47, bundle7, parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5022: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                c(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5023: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks46 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s44 = parcel.readString();
                IBinder ibinder5 = parcel.readStrongBinder();
                Bundle bundle6;
                if (parcel.readInt() != 0)
                {
                    bundle6 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                } else
                {
                    bundle6 = null;
                }
                a(igamescallbacks46, s44, ibinder5, bundle6);
                parcel1.writeNoException();
                return true;

            case 5024: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks45 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s43 = parcel.readString();
                IBinder ibinder4 = parcel.readStrongBinder();
                Bundle bundle5;
                if (parcel.readInt() != 0)
                {
                    bundle5 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                } else
                {
                    bundle5 = null;
                }
                b(igamescallbacks45, s43, ibinder4, bundle5);
                parcel1.writeNoException();
                return true;

            case 5025: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks44 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s42 = parcel.readString();
                int k21 = parcel.readInt();
                IBinder ibinder3 = parcel.readStrongBinder();
                int l21 = parcel.readInt();
                Bundle bundle4 = null;
                if (l21 != 0)
                {
                    bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                a(igamescallbacks44, s42, k21, ibinder3, bundle4);
                parcel1.writeNoException();
                return true;

            case 5026: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                d(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5027: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                e(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5028: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                n(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5029: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                m(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5058: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 5059: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                r(parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 5030: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks43 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                IBinder ibinder2 = parcel.readStrongBinder();
                int i21 = parcel.readInt();
                String as3[] = parcel.createStringArray();
                int j21 = parcel.readInt();
                Bundle bundle3 = null;
                if (j21 != 0)
                {
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                boolean flag63;
                if (parcel.readInt() != 0)
                {
                    flag63 = true;
                } else
                {
                    flag63 = false;
                }
                a(igamescallbacks43, ibinder2, i21, as3, bundle3, flag63, parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 5031: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks42 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                IBinder ibinder1 = parcel.readStrongBinder();
                String s41 = parcel.readString();
                boolean flag62;
                if (parcel.readInt() != 0)
                {
                    flag62 = true;
                } else
                {
                    flag62 = false;
                }
                a(igamescallbacks42, ibinder1, s41, flag62, parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 5032: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                c(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5033: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int l20 = a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.createByteArray(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(l20);
                return true;

            case 5034: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int k20 = b(parcel.createByteArray(), parcel.readString(), parcel.createStringArray());
                parcel1.writeNoException();
                parcel1.writeInt(k20);
                return true;

            case 5035: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                String s40 = aW(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(s40);
                return true;

            case 5036: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ch(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5037: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                d(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5038: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5039: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks41 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s38 = parcel.readString();
                String s39 = parcel.readString();
                int k19 = parcel.readInt();
                int l19 = parcel.readInt();
                int i20 = parcel.readInt();
                int j20 = parcel.readInt();
                boolean flag61 = false;
                if (j20 != 0)
                {
                    flag61 = true;
                }
                a(igamescallbacks41, s38, s39, k19, l19, i20, flag61);
                parcel1.writeNoException();
                return true;

            case 5040: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks40 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s36 = parcel.readString();
                String s37 = parcel.readString();
                int k18 = parcel.readInt();
                int l18 = parcel.readInt();
                int i19 = parcel.readInt();
                int j19 = parcel.readInt();
                boolean flag60 = false;
                if (j19 != 0)
                {
                    flag60 = true;
                }
                b(igamescallbacks40, s36, s37, k18, l18, i19, flag60);
                parcel1.writeNoException();
                return true;

            case 5041: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                b(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5042: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                e(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5043: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                f(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5044: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks39 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                int i18 = parcel.readInt();
                int j18 = parcel.readInt();
                boolean flag58;
                boolean flag59;
                if (parcel.readInt() != 0)
                {
                    flag58 = true;
                } else
                {
                    flag58 = false;
                }
                if (parcel.readInt() != 0)
                {
                    flag59 = true;
                } else
                {
                    flag59 = false;
                }
                a(igamescallbacks39, i18, j18, flag58, flag59);
                parcel1.writeNoException();
                return true;

            case 5045: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks38 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s35 = parcel.readString();
                int l17 = parcel.readInt();
                boolean flag56;
                boolean flag57;
                if (parcel.readInt() != 0)
                {
                    flag56 = true;
                } else
                {
                    flag56 = false;
                }
                if (parcel.readInt() != 0)
                {
                    flag57 = true;
                } else
                {
                    flag57 = false;
                }
                a(igamescallbacks38, s35, l17, flag56, flag57);
                parcel1.writeNoException();
                return true;

            case 5046: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks37 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                int j17 = parcel.readInt();
                boolean flag54;
                int k17;
                boolean flag55;
                if (parcel.readInt() != 0)
                {
                    flag54 = true;
                } else
                {
                    flag54 = false;
                }
                k17 = parcel.readInt();
                flag55 = false;
                if (k17 != 0)
                {
                    flag55 = true;
                }
                b(igamescallbacks37, j17, flag54, flag55);
                parcel1.writeNoException();
                return true;

            case 5047: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                f(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5048: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks36 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                int l16 = parcel.readInt();
                boolean flag52;
                int i17;
                boolean flag53;
                if (parcel.readInt() != 0)
                {
                    flag52 = true;
                } else
                {
                    flag52 = false;
                }
                i17 = parcel.readInt();
                flag53 = false;
                if (i17 != 0)
                {
                    flag53 = true;
                }
                c(igamescallbacks36, l16, flag52, flag53);
                parcel1.writeNoException();
                return true;

            case 5049: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                g(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5050: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                aX(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5051: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                b(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5052: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                g(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5053: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                RoomEntity roomentity1 = h(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                if (roomentity1 != null)
                {
                    parcel1.writeInt(1);
                    roomentity1.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 5060: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int k16 = aY(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(k16);
                return true;

            case 5054: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks35 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s34 = parcel.readString();
                int j16 = parcel.readInt();
                boolean flag51 = false;
                if (j16 != 0)
                {
                    flag51 = true;
                }
                a(igamescallbacks35, s34, flag51);
                parcel1.writeNoException();
                return true;

            case 5061: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                i(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5055: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                p(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5067: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                boolean flag50 = hz();
                parcel1.writeNoException();
                int i16 = 0;
                if (flag50)
                {
                    i16 = 1;
                }
                parcel1.writeInt(i16);
                return true;

            case 5068: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int l15 = parcel.readInt();
                boolean flag49 = false;
                if (l15 != 0)
                {
                    flag49 = true;
                }
                E(flag49);
                parcel1.writeNoException();
                return true;

            case 5056: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                h(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5057: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                j(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5062: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                i(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5063: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks34 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                int k15 = parcel.readInt();
                boolean flag48 = false;
                if (k15 != 0)
                {
                    flag48 = true;
                }
                Bundle bundle2;
                if (parcel.readInt() != 0)
                {
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                } else
                {
                    bundle2 = null;
                }
                a(igamescallbacks34, flag48, bundle2);
                parcel1.writeNoException();
                return true;

            case 5066: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Uri uri3 = aZ(parcel.readString());
                parcel1.writeNoException();
                if (uri3 != null)
                {
                    parcel1.writeInt(1);
                    uri3.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 5501: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks33 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s33 = parcel.readString();
                int j15 = parcel.readInt();
                boolean flag46;
                boolean flag47;
                if (parcel.readInt() != 0)
                {
                    flag46 = true;
                } else
                {
                    flag46 = false;
                }
                if (parcel.readInt() != 0)
                {
                    flag47 = true;
                } else
                {
                    flag47 = false;
                }
                b(igamescallbacks33, s33, j15, flag46, flag47);
                parcel1.writeNoException();
                return true;

            case 5502: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                DataHolder dataholder = hA();
                parcel1.writeNoException();
                if (dataholder != null)
                {
                    parcel1.writeInt(1);
                    dataholder.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 6001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks32 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                int i15 = parcel.readInt();
                boolean flag45 = false;
                if (i15 != 0)
                {
                    flag45 = true;
                }
                a(igamescallbacks32, flag45);
                parcel1.writeNoException();
                return true;

            case 6002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks31 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s31 = parcel.readString();
                String s32 = parcel.readString();
                int l14 = parcel.readInt();
                boolean flag44 = false;
                if (l14 != 0)
                {
                    flag44 = true;
                }
                a(igamescallbacks31, s31, s32, flag44);
                parcel1.writeNoException();
                return true;

            case 6003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks30 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                int j14 = parcel.readInt();
                boolean flag42;
                int k14;
                boolean flag43;
                if (parcel.readInt() != 0)
                {
                    flag42 = true;
                } else
                {
                    flag42 = false;
                }
                k14 = parcel.readInt();
                flag43 = false;
                if (k14 != 0)
                {
                    flag43 = true;
                }
                d(igamescallbacks30, j14, flag42, flag43);
                parcel1.writeNoException();
                return true;

            case 6004: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks29 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                int l13 = parcel.readInt();
                boolean flag40;
                int i14;
                boolean flag41;
                if (parcel.readInt() != 0)
                {
                    flag40 = true;
                } else
                {
                    flag40 = false;
                }
                i14 = parcel.readInt();
                flag41 = false;
                if (i14 != 0)
                {
                    flag41 = true;
                }
                e(igamescallbacks29, l13, flag40, flag41);
                parcel1.writeNoException();
                return true;

            case 6501: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks28 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s30 = parcel.readString();
                int j13 = parcel.readInt();
                boolean flag36;
                boolean flag37;
                boolean flag38;
                int k13;
                boolean flag39;
                if (parcel.readInt() != 0)
                {
                    flag36 = true;
                } else
                {
                    flag36 = false;
                }
                if (parcel.readInt() != 0)
                {
                    flag37 = true;
                } else
                {
                    flag37 = false;
                }
                if (parcel.readInt() != 0)
                {
                    flag38 = true;
                } else
                {
                    flag38 = false;
                }
                k13 = parcel.readInt();
                flag39 = false;
                if (k13 != 0)
                {
                    flag39 = true;
                }
                a(igamescallbacks28, s30, j13, flag36, flag37, flag38, flag39);
                parcel1.writeNoException();
                return true;

            case 6502: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks27 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s29 = parcel.readString();
                int i13 = parcel.readInt();
                boolean flag35 = false;
                if (i13 != 0)
                {
                    flag35 = true;
                }
                b(igamescallbacks27, s29, flag35);
                parcel1.writeNoException();
                return true;

            case 6503: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks26 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                int l12 = parcel.readInt();
                boolean flag34 = false;
                if (l12 != 0)
                {
                    flag34 = true;
                }
                b(igamescallbacks26, flag34);
                parcel1.writeNoException();
                return true;

            case 6504: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks25 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s28 = parcel.readString();
                int k12 = parcel.readInt();
                boolean flag33 = false;
                if (k12 != 0)
                {
                    flag33 = true;
                }
                c(igamescallbacks25, s28, flag33);
                parcel1.writeNoException();
                return true;

            case 6505: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks24 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s27 = parcel.readString();
                int j12 = parcel.readInt();
                boolean flag32 = false;
                if (j12 != 0)
                {
                    flag32 = true;
                }
                d(igamescallbacks24, s27, flag32);
                parcel1.writeNoException();
                return true;

            case 6506: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks23 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s25 = parcel.readString();
                String s26 = parcel.readString();
                int i12 = parcel.readInt();
                boolean flag31 = false;
                if (i12 != 0)
                {
                    flag31 = true;
                }
                b(igamescallbacks23, s25, s26, flag31);
                parcel1.writeNoException();
                return true;

            case 6507: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Uri uri2;
                ParcelFileDescriptor parcelfiledescriptor1;
                if (parcel.readInt() != 0)
                {
                    uri2 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                } else
                {
                    uri2 = null;
                }
                parcelfiledescriptor1 = h(uri2);
                parcel1.writeNoException();
                if (parcelfiledescriptor1 != null)
                {
                    parcel1.writeInt(1);
                    parcelfiledescriptor1.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 7001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                k(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 7002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString(), parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 7003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks22 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s24 = parcel.readString();
                int k11 = parcel.readInt();
                IBinder ibinder = parcel.readStrongBinder();
                int l11 = parcel.readInt();
                Bundle bundle1 = null;
                if (l11 != 0)
                {
                    bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                b(igamescallbacks22, s24, k11, ibinder, bundle1);
                parcel1.writeNoException();
                return true;

            case 8001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 8002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ba(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.createIntArray());
                parcel1.writeNoException();
                return true;

            case 8004: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks21 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                int l10 = parcel.readInt();
                int i11 = parcel.readInt();
                String as2[] = parcel.createStringArray();
                int j11 = parcel.readInt();
                Bundle bundle = null;
                if (j11 != 0)
                {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                a(igamescallbacks21, l10, i11, as2, bundle);
                parcel1.writeNoException();
                return true;

            case 8005: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                l(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8006: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                m(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8007: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString(), parcel.createByteArray(), parcel.readString(), (ParticipantResult[])parcel.createTypedArray(ParticipantResult.CREATOR));
                parcel1.writeNoException();
                return true;

            case 8008: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString(), parcel.createByteArray(), (ParticipantResult[])parcel.createTypedArray(ParticipantResult.CREATOR));
                parcel1.writeNoException();
                return true;

            case 8009: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                n(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8010: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                o(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8011: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                c(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8012: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                b(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 8013: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                s(parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 8014: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                p(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8024: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int k10 = hp();
                parcel1.writeNoException();
                parcel1.writeInt(k10);
                return true;

            case 8025: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                n(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8015: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                d(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8016: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                e(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8017: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString(), parcel.createIntArray());
                parcel1.writeNoException();
                return true;

            case 8026: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                c(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 8018: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8019: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8020: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                b(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8021: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                b(parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8022: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                hB();
                parcel1.writeNoException();
                return true;

            case 8023: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks20 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s23 = parcel.readString();
                int i10 = parcel.readInt();
                int j10 = parcel.readInt();
                boolean flag30 = false;
                if (j10 != 0)
                {
                    flag30 = true;
                }
                a(igamescallbacks20, s23, i10, flag30);
                parcel1.writeNoException();
                return true;

            case 8027: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks19 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                int l9 = parcel.readInt();
                boolean flag29 = false;
                if (l9 != 0)
                {
                    flag29 = true;
                }
                c(igamescallbacks19, flag29);
                parcel1.writeNoException();
                return true;

            case 9001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks18 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s22 = parcel.readString();
                int k9 = parcel.readInt();
                boolean flag27;
                boolean flag28;
                if (parcel.readInt() != 0)
                {
                    flag27 = true;
                } else
                {
                    flag27 = false;
                }
                if (parcel.readInt() != 0)
                {
                    flag28 = true;
                } else
                {
                    flag28 = false;
                }
                c(igamescallbacks18, s22, k9, flag27, flag28);
                parcel1.writeNoException();
                return true;

            case 9002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                q(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 9003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent18 = hd();
                parcel1.writeNoException();
                if (intent18 != null)
                {
                    parcel1.writeInt(1);
                    intent18.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9004: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent17 = aR(parcel.readString());
                parcel1.writeNoException();
                if (intent17 != null)
                {
                    parcel1.writeInt(1);
                    intent17.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9005: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent16 = he();
                parcel1.writeNoException();
                if (intent16 != null)
                {
                    parcel1.writeInt(1);
                    intent16.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9006: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent15 = hf();
                parcel1.writeNoException();
                if (intent15 != null)
                {
                    parcel1.writeInt(1);
                    intent15.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9007: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent14 = hg();
                parcel1.writeNoException();
                if (intent14 != null)
                {
                    parcel1.writeInt(1);
                    intent14.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9008: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int i9 = parcel.readInt();
                int j9 = parcel.readInt();
                boolean flag26;
                Intent intent13;
                if (parcel.readInt() != 0)
                {
                    flag26 = true;
                } else
                {
                    flag26 = false;
                }
                intent13 = a(i9, j9, flag26);
                parcel1.writeNoException();
                if (intent13 != null)
                {
                    parcel1.writeInt(1);
                    intent13.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9009: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int k8 = parcel.readInt();
                int l8 = parcel.readInt();
                boolean flag25;
                Intent intent12;
                if (parcel.readInt() != 0)
                {
                    flag25 = true;
                } else
                {
                    flag25 = false;
                }
                intent12 = b(k8, l8, flag25);
                parcel1.writeNoException();
                if (intent12 != null)
                {
                    parcel1.writeInt(1);
                    intent12.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9010: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent11 = hl();
                parcel1.writeNoException();
                if (intent11 != null)
                {
                    parcel1.writeInt(1);
                    intent11.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9011: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                RoomEntity roomentity;
                Intent intent10;
                if (parcel.readInt() != 0)
                {
                    roomentity = (RoomEntity)RoomEntity.CREATOR.createFromParcel(parcel);
                } else
                {
                    roomentity = null;
                }
                intent10 = a(roomentity, parcel.readInt());
                parcel1.writeNoException();
                if (intent10 != null)
                {
                    parcel1.writeInt(1);
                    intent10.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9012: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent9 = hm();
                parcel1.writeNoException();
                if (intent9 != null)
                {
                    parcel1.writeInt(1);
                    intent9.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9013: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent8 = hC();
                parcel1.writeNoException();
                if (intent8 != null)
                {
                    parcel1.writeInt(1);
                    intent8.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9031: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ParticipantEntity aparticipantentity[] = (ParticipantEntity[])parcel.createTypedArray(ParticipantEntity.CREATOR);
                String s20 = parcel.readString();
                String s21 = parcel.readString();
                Uri uri;
                int j8;
                Uri uri1;
                Intent intent7;
                if (parcel.readInt() != 0)
                {
                    uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
                } else
                {
                    uri = null;
                }
                j8 = parcel.readInt();
                uri1 = null;
                if (j8 != 0)
                {
                    uri1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                }
                intent7 = a(aparticipantentity, s20, s21, uri, uri1);
                parcel1.writeNoException();
                if (intent7 != null)
                {
                    parcel1.writeInt(1);
                    intent7.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9019: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int i8 = hn();
                parcel1.writeNoException();
                parcel1.writeInt(i8);
                return true;

            case 9020: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks17 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s19 = parcel.readString();
                int l7 = parcel.readInt();
                boolean flag23;
                boolean flag24;
                if (parcel.readInt() != 0)
                {
                    flag23 = true;
                } else
                {
                    flag23 = false;
                }
                if (parcel.readInt() != 0)
                {
                    flag24 = true;
                } else
                {
                    flag24 = false;
                }
                d(igamescallbacks17, s19, l7, flag23, flag24);
                parcel1.writeNoException();
                return true;

            case 9028: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks16 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s17 = parcel.readString();
                String s18 = parcel.readString();
                int k7 = parcel.readInt();
                boolean flag21;
                boolean flag22;
                if (parcel.readInt() != 0)
                {
                    flag21 = true;
                } else
                {
                    flag21 = false;
                }
                if (parcel.readInt() != 0)
                {
                    flag22 = true;
                } else
                {
                    flag22 = false;
                }
                a(igamescallbacks16, s17, s18, k7, flag21, flag22);
                parcel1.writeNoException();
                return true;

            case 9030: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ParcelFileDescriptor parcelfiledescriptor = bb(parcel.readString());
                parcel1.writeNoException();
                if (parcelfiledescriptor != null)
                {
                    parcel1.writeInt(1);
                    parcelfiledescriptor.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 10001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                c(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 10002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                t(parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 10003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                c(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 10004: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                c(parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 10005: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString(), parcel.createStringArray(), parcel.readInt(), parcel.createByteArray(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10006: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 10007: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                b(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 10008: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 10009: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10010: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10011: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10012: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent6 = a(parcel.readInt(), parcel.createByteArray(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if (intent6 != null)
                {
                    parcel1.writeInt(1);
                    intent6.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 10013: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int j7 = hr();
                parcel1.writeNoException();
                parcel1.writeInt(j7);
                return true;

            case 10023: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int i7 = hs();
                parcel1.writeNoException();
                parcel1.writeInt(i7);
                return true;

            case 10015: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent5 = hq();
                parcel1.writeNoException();
                if (intent5 != null)
                {
                    parcel1.writeInt(1);
                    intent5.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 10022: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int l6 = parcel.readInt();
                GameRequestCluster gamerequestcluster = null;
                if (l6 != 0)
                {
                    gamerequestcluster = GameRequestCluster.CREATOR.bk(parcel);
                }
                Intent intent4 = a(gamerequestcluster, parcel.readString());
                parcel1.writeNoException();
                if (intent4 != null)
                {
                    parcel1.writeInt(1);
                    intent4.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 10014: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                q(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10016: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10017: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks15 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s16 = parcel.readString();
                int j6 = parcel.readInt();
                int k6 = parcel.readInt();
                boolean flag20 = false;
                if (k6 != 0)
                {
                    flag20 = true;
                }
                b(igamescallbacks15, s16, j6, flag20);
                parcel1.writeNoException();
                return true;

            case 10021: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int i6 = parcel.readInt();
                ZInvitationCluster zinvitationcluster = null;
                if (i6 != 0)
                {
                    zinvitationcluster = ZInvitationCluster.CREATOR.bi(parcel);
                }
                Intent intent3 = a(zinvitationcluster, parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if (intent3 != null)
                {
                    parcel1.writeInt(1);
                    intent3.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 10018: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readInt(), parcel.createIntArray());
                parcel1.writeNoException();
                return true;

            case 10019: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString(), parcel.readInt(), parcel.createIntArray());
                parcel1.writeNoException();
                return true;

            case 10020: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                c(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 11001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                j(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 11002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                hD();
                parcel1.writeNoException();
                return true;

            case 12001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                String s15 = parcel.readString();
                boolean flag18;
                boolean flag19;
                Intent intent2;
                if (parcel.readInt() != 0)
                {
                    flag18 = true;
                } else
                {
                    flag18 = false;
                }
                if (parcel.readInt() != 0)
                {
                    flag19 = true;
                } else
                {
                    flag19 = false;
                }
                intent2 = a(s15, flag18, flag19, parcel.readInt());
                parcel1.writeNoException();
                if (intent2 != null)
                {
                    parcel1.writeInt(1);
                    intent2.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 12002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks14 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                int l5 = parcel.readInt();
                boolean flag17 = false;
                if (l5 != 0)
                {
                    flag17 = true;
                }
                d(igamescallbacks14, flag17);
                parcel1.writeNoException();
                return true;

            case 12003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks13 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s13 = parcel.readString();
                String s14 = parcel.readString();
                int k5 = parcel.readInt();
                boolean flag16 = false;
                if (k5 != 0)
                {
                    flag16 = true;
                }
                c(igamescallbacks13, s13, s14, flag16);
                parcel1.writeNoException();
                return true;

            case 12006: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks12 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s12 = parcel.readString();
                int j5 = parcel.readInt();
                boolean flag15 = false;
                if (j5 != 0)
                {
                    flag15 = true;
                }
                e(igamescallbacks12, s12, flag15);
                parcel1.writeNoException();
                return true;

            case 12007: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks11 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s11 = parcel.readString();
                SnapshotMetadataChange snapshotmetadatachange1;
                Contents contents2;
                if (parcel.readInt() != 0)
                {
                    snapshotmetadatachange1 = SnapshotMetadataChange.CREATOR.createFromParcel(parcel);
                } else
                {
                    snapshotmetadatachange1 = null;
                }
                if (parcel.readInt() != 0)
                {
                    contents2 = (Contents)Contents.CREATOR.createFromParcel(parcel);
                } else
                {
                    contents2 = null;
                }
                a(igamescallbacks11, s11, snapshotmetadatachange1, contents2);
                parcel1.writeNoException();
                return true;

            case 12019: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Contents contents1;
                if (parcel.readInt() != 0)
                {
                    contents1 = (Contents)Contents.CREATOR.createFromParcel(parcel);
                } else
                {
                    contents1 = null;
                }
                a(contents1);
                parcel1.writeNoException();
                return true;

            case 12020: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                r(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 12033: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks10 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s9 = parcel.readString();
                String s10 = parcel.readString();
                SnapshotMetadataChange snapshotmetadatachange;
                int i5;
                Contents contents;
                if (parcel.readInt() != 0)
                {
                    snapshotmetadatachange = SnapshotMetadataChange.CREATOR.createFromParcel(parcel);
                } else
                {
                    snapshotmetadatachange = null;
                }
                i5 = parcel.readInt();
                contents = null;
                if (i5 != 0)
                {
                    contents = (Contents)Contents.CREATOR.createFromParcel(parcel);
                }
                a(igamescallbacks10, s9, s10, snapshotmetadatachange, contents);
                parcel1.writeNoException();
                return true;

            case 12035: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int l4 = ht();
                parcel1.writeNoException();
                parcel1.writeInt(l4);
                return true;

            case 12036: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int k4 = hu();
                parcel1.writeNoException();
                parcel1.writeInt(k4);
                return true;

            case 12005: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                s(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 12023: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                b(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 12024: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                c(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 12021: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks9 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s8 = parcel.readString();
                int j4 = parcel.readInt();
                boolean flag13;
                boolean flag14;
                if (parcel.readInt() != 0)
                {
                    flag13 = true;
                } else
                {
                    flag13 = false;
                }
                if (parcel.readInt() != 0)
                {
                    flag14 = true;
                } else
                {
                    flag14 = false;
                }
                e(igamescallbacks9, s8, j4, flag13, flag14);
                parcel1.writeNoException();
                return true;

            case 12022: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks8 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s7 = parcel.readString();
                int i4 = parcel.readInt();
                boolean flag11;
                boolean flag12;
                if (parcel.readInt() != 0)
                {
                    flag11 = true;
                } else
                {
                    flag11 = false;
                }
                if (parcel.readInt() != 0)
                {
                    flag12 = true;
                } else
                {
                    flag12 = false;
                }
                f(igamescallbacks8, s7, i4, flag11, flag12);
                parcel1.writeNoException();
                return true;

            case 12025: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                boolean flag10 = hE();
                parcel1.writeNoException();
                int l3 = 0;
                if (flag10)
                {
                    l3 = 1;
                }
                parcel1.writeInt(l3);
                return true;

            case 12026: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int k3 = parcel.readInt();
                boolean flag9 = false;
                if (k3 != 0)
                {
                    flag9 = true;
                }
                F(flag9);
                parcel1.writeNoException();
                return true;

            case 12027: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                t(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 12032: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks7 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                int j3 = parcel.readInt();
                boolean flag8 = false;
                if (j3 != 0)
                {
                    flag8 = true;
                }
                e(igamescallbacks7, flag8);
                parcel1.writeNoException();
                return true;

            case 12016: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks6 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                int i3 = parcel.readInt();
                boolean flag7 = false;
                if (i3 != 0)
                {
                    flag7 = true;
                }
                f(igamescallbacks6, flag7);
                parcel1.writeNoException();
                return true;

            case 12031: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks5 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                int l2 = parcel.readInt();
                boolean flag6 = false;
                if (l2 != 0)
                {
                    flag6 = true;
                }
                a(igamescallbacks5, flag6, parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 12017: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                l(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 12008: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                u(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 12009: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                f(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 12010: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks4 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                int ai1[] = parcel.createIntArray();
                int j2 = parcel.readInt();
                int k2 = parcel.readInt();
                boolean flag5 = false;
                if (k2 != 0)
                {
                    flag5 = true;
                }
                a(igamescallbacks4, ai1, j2, flag5);
                parcel1.writeNoException();
                return true;

            case 12029: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks3 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String as1[] = parcel.createStringArray();
                int i2 = parcel.readInt();
                boolean flag4 = false;
                if (i2 != 0)
                {
                    flag4 = true;
                }
                a(igamescallbacks3, as1, flag4);
                parcel1.writeNoException();
                return true;

            case 12015: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks2 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s5 = parcel.readString();
                String s6 = parcel.readString();
                int ai[] = parcel.createIntArray();
                int l1 = parcel.readInt();
                boolean flag3;
                if (parcel.readInt() != 0)
                {
                    flag3 = true;
                } else
                {
                    flag3 = false;
                }
                a(igamescallbacks2, s5, s6, ai, l1, flag3);
                parcel1.writeNoException();
                return true;

            case 12028: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IGamesCallbacks igamescallbacks1 = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                String s3 = parcel.readString();
                String s4 = parcel.readString();
                String as[] = parcel.createStringArray();
                boolean flag2;
                if (parcel.readInt() != 0)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                a(igamescallbacks1, s3, s4, as, flag2);
                parcel1.writeNoException();
                return true;

            case 12011: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                d(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 12013: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                d(IGamesCallbacks.Stub.ai(parcel.readStrongBinder()), parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 12012: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                u(parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 12014: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                d(parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 12030: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent1 = a(parcel.createIntArray());
                parcel1.writeNoException();
                if (intent1 != null)
                {
                    parcel1.writeInt(1);
                    intent1.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 12034: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent = aU(parcel.readString());
                parcel1.writeNoException();
                if (intent != null)
                {
                    parcel1.writeInt(1);
                    intent.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 12018: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                igamescallbacks = IGamesCallbacks.Stub.ai(parcel.readStrongBinder());
                s1 = parcel.readString();
                s2 = parcel.readString();
                k1 = parcel.readInt();
                break;
            }
            boolean flag;
            boolean flag1;
            if (parcel.readInt() != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (parcel.readInt() != 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            b(igamescallbacks, s1, s2, k1, flag, flag1);
            parcel1.writeNoException();
            return true;
        }

        public Stub()
        {
            attachInterface(this, "com.google.android.gms.games.internal.IGamesService");
        }
    }

    private static class Stub.Proxy
        implements IGamesService
    {

        private IBinder kq;

        public void E(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(5068, parcel, parcel1, 0);
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

        public void F(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(12026, parcel, parcel1, 0);
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

        public int a(IGamesCallbacks igamescallbacks, byte abyte0[], String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_98;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            int i1;
            parcel.writeStrongBinder(ibinder);
            parcel.writeByteArray(abyte0);
            parcel.writeString(s1);
            parcel.writeString(s2);
            kq.transact(5033, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i1;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent a(int i1, int j1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            int k1;
            k1 = 0;
            if (flag)
            {
                k1 = 1;
            }
            parcel.writeInt(k1);
            kq.transact(9008, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent a(int i1, byte abyte0[], int j1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeInt(i1);
            parcel.writeByteArray(abyte0);
            parcel.writeInt(j1);
            parcel.writeString(s1);
            kq.transact(10012, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent a(ZInvitationCluster zinvitationcluster, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (zinvitationcluster == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            zinvitationcluster.writeToParcel(parcel, 0);
_L3:
            Intent intent;
            parcel.writeString(s1);
            parcel.writeString(s2);
            kq.transact(10021, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0)
            {
                break MISSING_BLOCK_LABEL_129;
            }
            intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            intent = null;
              goto _L4
        }

        public Intent a(GameRequestCluster gamerequestcluster, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (gamerequestcluster == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            gamerequestcluster.writeToParcel(parcel, 0);
_L3:
            Intent intent;
            parcel.writeString(s1);
            kq.transact(10022, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0)
            {
                break MISSING_BLOCK_LABEL_114;
            }
            intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            intent = null;
              goto _L4
        }

        public Intent a(RoomEntity roomentity, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (roomentity == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            roomentity.writeToParcel(parcel, 0);
_L3:
            Intent intent;
            parcel.writeInt(i1);
            kq.transact(9011, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0)
            {
                break MISSING_BLOCK_LABEL_114;
            }
            intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            intent = null;
              goto _L4
        }

        public Intent a(String s1, boolean flag, boolean flag1, int i1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s1);
            int k1;
            Intent intent;
            if (flag)
            {
                k1 = j1;
            } else
            {
                k1 = 0;
            }
            parcel.writeInt(k1);
            if (!flag1)
            {
                j1 = 0;
            }
            parcel.writeInt(j1);
            parcel.writeInt(i1);
            kq.transact(12001, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent a(int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeIntArray(ai);
            kq.transact(12030, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent a(ParticipantEntity aparticipantentity[], String s1, String s2, Uri uri, Uri uri1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeTypedArray(aparticipantentity, 0);
            parcel.writeString(s1);
            parcel.writeString(s2);
            if (uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L5:
            if (uri1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            uri1.writeToParcel(parcel, 0);
_L6:
            Intent intent;
            kq.transact(9031, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0)
            {
                break MISSING_BLOCK_LABEL_166;
            }
            intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L7:
            parcel1.recycle();
            parcel.recycle();
            return intent;
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
            intent = null;
              goto _L7
        }

        public void a(long l1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeLong(l1);
            parcel.writeString(s1);
            kq.transact(8019, parcel, parcel1, 0);
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

        public void a(IBinder ibinder, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeStrongBinder(ibinder);
            if (bundle == null)
            {
                break MISSING_BLOCK_LABEL_67;
            }
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            kq.transact(5005, parcel, parcel1, 0);
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

        public void a(Contents contents)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (contents == null)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            parcel.writeInt(1);
            contents.writeToParcel(parcel, 0);
_L1:
            kq.transact(12019, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_61;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(5002, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            kq.transact(10016, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, int i1, int j1, int k1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_89;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeInt(k1);
            kq.transact(10009, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, int i1, int j1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int k1;
            Parcel parcel;
            Parcel parcel1;
            k1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_113;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            int l1;
            if (flag)
            {
                l1 = k1;
            } else
            {
                l1 = 0;
            }
            parcel.writeInt(l1);
            if (!flag1)
            {
                k1 = 0;
            }
            parcel.writeInt(k1);
            kq.transact(5044, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, int i1, int j1, String as[], Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = igamescallbacks.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeStringArray(as);
            if (bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L6:
            kq.transact(8004, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_106;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            int k1;
            if (flag)
            {
                k1 = j1;
            } else
            {
                k1 = 0;
            }
            parcel.writeInt(k1);
            if (!flag1)
            {
                j1 = 0;
            }
            parcel.writeInt(j1);
            kq.transact(5015, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, int i1, int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_82;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            parcel.writeIntArray(ai);
            kq.transact(10018, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_76;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l1);
            kq.transact(5058, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, long l1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_83;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l1);
            parcel.writeString(s1);
            kq.transact(8018, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, Bundle bundle, int i1, int j1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = igamescallbacks.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if (bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L6:
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            kq.transact(5021, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, IBinder ibinder, int i1, String as[], Bundle bundle, boolean flag, long l1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder1 = igamescallbacks.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder1);
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            parcel.writeStringArray(as);
            if (bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
            break MISSING_BLOCK_LABEL_158;
_L6:
            parcel.writeInt(j1);
            parcel.writeLong(l1);
            kq.transact(5030, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder1 = null;
              goto _L5
_L4:
            parcel.writeInt(0);
            break MISSING_BLOCK_LABEL_158;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            while (!flag) 
            {
                j1 = 0;
                break;
            }
              goto _L6
        }

        public void a(IGamesCallbacks igamescallbacks, IBinder ibinder, String s1, boolean flag, long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_107;
            }
            IBinder ibinder1 = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder1);
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            parcel.writeLong(l1);
            kq.transact(5031, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder1 = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(5014, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_82;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            kq.transact(10011, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, int i1, int j1, int k1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_114;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeInt(k1);
            int l1;
            l1 = 0;
            if (flag)
            {
                l1 = 1;
            }
            parcel.writeInt(l1);
            kq.transact(5019, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, int i1, IBinder ibinder, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder1 = igamescallbacks.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder1);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            parcel.writeStrongBinder(ibinder);
            if (bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L6:
            kq.transact(5025, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder1 = null;
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

        public void a(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_100;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            int j1;
            j1 = 0;
            if (flag)
            {
                j1 = 1;
            }
            parcel.writeInt(j1);
            kq.transact(8023, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_113;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            int k1;
            if (flag)
            {
                k1 = j1;
            } else
            {
                k1 = 0;
            }
            parcel.writeInt(k1);
            if (!flag1)
            {
                j1 = 0;
            }
            parcel.writeInt(j1);
            kq.transact(5045, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1, boolean flag2, boolean flag3)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_145;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            int k1;
            int l1;
            int i2;
            if (flag)
            {
                k1 = j1;
            } else
            {
                k1 = 0;
            }
            parcel.writeInt(k1);
            if (flag1)
            {
                l1 = j1;
            } else
            {
                l1 = 0;
            }
            parcel.writeInt(l1);
            if (flag2)
            {
                i2 = j1;
            } else
            {
                i2 = 0;
            }
            parcel.writeInt(i2);
            if (!flag3)
            {
                j1 = 0;
            }
            parcel.writeInt(j1);
            kq.transact(6501, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, int i1, int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_89;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            parcel.writeIntArray(ai);
            kq.transact(10019, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_82;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeLong(l1);
            kq.transact(5016, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, long l1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_89;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeLong(l1);
            parcel.writeString(s2);
            kq.transact(7002, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, IBinder ibinder, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder1 = igamescallbacks.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder1);
            parcel.writeString(s1);
            parcel.writeStrongBinder(ibinder);
            if (bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L6:
            kq.transact(5023, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder1 = null;
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

        public void a(IGamesCallbacks igamescallbacks, String s1, SnapshotMetadataChange snapshotmetadatachange, Contents contents)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = igamescallbacks.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            if (snapshotmetadatachange == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            snapshotmetadatachange.writeToParcel(parcel, 0);
_L6:
            if (contents == null)
            {
                break MISSING_BLOCK_LABEL_142;
            }
            parcel.writeInt(1);
            contents.writeToParcel(parcel, 0);
_L7:
            kq.transact(12007, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_82;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeString(s2);
            kq.transact(5038, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, String s2, int i1, int j1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_96;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            kq.transact(8001, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, String s2, int i1, int j1, int k1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_103;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeInt(k1);
            kq.transact(10010, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, String s2, int i1, int j1, int k1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_121;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeInt(k1);
            int l1;
            l1 = 0;
            if (flag)
            {
                l1 = 1;
            }
            parcel.writeInt(l1);
            kq.transact(5039, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, String s2, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_120;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeInt(i1);
            int k1;
            if (flag)
            {
                k1 = j1;
            } else
            {
                k1 = 0;
            }
            parcel.writeInt(k1);
            if (!flag1)
            {
                j1 = 0;
            }
            parcel.writeInt(j1);
            kq.transact(9028, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, String s2, SnapshotMetadataChange snapshotmetadatachange, Contents contents)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = igamescallbacks.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeString(s2);
            if (snapshotmetadatachange == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            snapshotmetadatachange.writeToParcel(parcel, 0);
_L6:
            if (contents == null)
            {
                break MISSING_BLOCK_LABEL_150;
            }
            parcel.writeInt(1);
            contents.writeToParcel(parcel, 0);
_L7:
            kq.transact(12033, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, String s2, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_100;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeString(s2);
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(6002, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, String s2, int ai[], int i1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_114;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeIntArray(ai);
            parcel.writeInt(i1);
            int j1;
            j1 = 0;
            if (flag)
            {
                j1 = 1;
            }
            parcel.writeInt(j1);
            kq.transact(12015, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, String s2, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_89;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeStringArray(as);
            kq.transact(10008, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, String s2, String as[], boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_107;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeStringArray(as);
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(12028, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_93;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(5054, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, byte abyte0[], String s2, ParticipantResult aparticipantresult[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_97;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeByteArray(abyte0);
            parcel.writeString(s2);
            parcel.writeTypedArray(aparticipantresult, 0);
            kq.transact(8007, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, byte abyte0[], ParticipantResult aparticipantresult[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_90;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeByteArray(abyte0);
            parcel.writeTypedArray(aparticipantresult, 0);
            kq.transact(8008, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_82;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeIntArray(ai);
            kq.transact(8017, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String s1, String as[], int i1, byte abyte0[], int j1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_103;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeStringArray(as);
            parcel.writeInt(i1);
            parcel.writeByteArray(abyte0);
            parcel.writeInt(j1);
            kq.transact(10005, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_81;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(6001, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, boolean flag, Bundle bundle)
            throws RemoteException
        {
            int i1;
            Parcel parcel;
            Parcel parcel1;
            i1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = igamescallbacks.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if (!flag)
            {
                i1 = 0;
            }
            parcel.writeInt(i1);
            if (bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L6:
            kq.transact(5063, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, boolean flag, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_93;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            parcel.writeStringArray(as);
            kq.transact(12031, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeIntArray(ai);
            kq.transact(8003, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, int ai[], int i1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_100;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeIntArray(ai);
            parcel.writeInt(i1);
            int j1;
            j1 = 0;
            if (flag)
            {
                j1 = 1;
            }
            parcel.writeInt(j1);
            kq.transact(12010, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeStringArray(as);
            kq.transact(10006, parcel, parcel1, 0);
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

        public void a(IGamesCallbacks igamescallbacks, String as[], boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_93;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeStringArray(as);
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(12029, parcel, parcel1, 0);
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

        public Intent aR(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s1);
            kq.transact(9004, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent aU(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s1);
            kq.transact(12034, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String aV(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s2;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s1);
            kq.transact(5064, parcel, parcel1, 0);
            parcel1.readException();
            s2 = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s2;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String aW(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s2;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s1);
            kq.transact(5035, parcel, parcel1, 0);
            parcel1.readException();
            s2 = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s2;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void aX(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s1);
            kq.transact(5050, parcel, parcel1, 0);
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

        public int aY(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s1);
            kq.transact(5060, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Uri aZ(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s1);
            kq.transact(5066, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Uri uri = (Uri)Uri.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return uri;
_L2:
            uri = null;
            if (true) goto _L4; else goto _L3
_L3:
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

        public int b(byte abyte0[], String s1, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeByteArray(abyte0);
            parcel.writeString(s1);
            parcel.writeStringArray(as);
            kq.transact(5034, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent b(int i1, int j1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            int k1;
            k1 = 0;
            if (flag)
            {
                k1 = 1;
            }
            parcel.writeInt(k1);
            kq.transact(9009, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void b(long l1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeLong(l1);
            parcel.writeString(s1);
            kq.transact(8021, parcel, parcel1, 0);
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

        public void b(IGamesCallbacks igamescallbacks)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_61;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(5017, parcel, parcel1, 0);
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

        public void b(IGamesCallbacks igamescallbacks, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_106;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            int k1;
            if (flag)
            {
                k1 = j1;
            } else
            {
                k1 = 0;
            }
            parcel.writeInt(k1);
            if (!flag1)
            {
                j1 = 0;
            }
            parcel.writeInt(j1);
            kq.transact(5046, parcel, parcel1, 0);
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

        public void b(IGamesCallbacks igamescallbacks, long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_76;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l1);
            kq.transact(8012, parcel, parcel1, 0);
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

        public void b(IGamesCallbacks igamescallbacks, long l1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_83;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l1);
            parcel.writeString(s1);
            kq.transact(8020, parcel, parcel1, 0);
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

        public void b(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(5018, parcel, parcel1, 0);
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

        public void b(IGamesCallbacks igamescallbacks, String s1, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_82;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            kq.transact(12023, parcel, parcel1, 0);
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

        public void b(IGamesCallbacks igamescallbacks, String s1, int i1, int j1, int k1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_114;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeInt(k1);
            int l1;
            l1 = 0;
            if (flag)
            {
                l1 = 1;
            }
            parcel.writeInt(l1);
            kq.transact(5020, parcel, parcel1, 0);
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

        public void b(IGamesCallbacks igamescallbacks, String s1, int i1, IBinder ibinder, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder1 = igamescallbacks.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder1);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            parcel.writeStrongBinder(ibinder);
            if (bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L6:
            kq.transact(7003, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder1 = null;
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

        public void b(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_100;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            int j1;
            j1 = 0;
            if (flag)
            {
                j1 = 1;
            }
            parcel.writeInt(j1);
            kq.transact(10017, parcel, parcel1, 0);
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

        public void b(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_113;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            int k1;
            if (flag)
            {
                k1 = j1;
            } else
            {
                k1 = 0;
            }
            parcel.writeInt(k1);
            if (!flag1)
            {
                j1 = 0;
            }
            parcel.writeInt(j1);
            kq.transact(5501, parcel, parcel1, 0);
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

        public void b(IGamesCallbacks igamescallbacks, String s1, IBinder ibinder, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder1 = igamescallbacks.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder1);
            parcel.writeString(s1);
            parcel.writeStrongBinder(ibinder);
            if (bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L6:
            kq.transact(5024, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder1 = null;
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

        public void b(IGamesCallbacks igamescallbacks, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_82;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeString(s2);
            kq.transact(5041, parcel, parcel1, 0);
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

        public void b(IGamesCallbacks igamescallbacks, String s1, String s2, int i1, int j1, int k1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_121;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeInt(k1);
            int l1;
            l1 = 0;
            if (flag)
            {
                l1 = 1;
            }
            parcel.writeInt(l1);
            kq.transact(5040, parcel, parcel1, 0);
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

        public void b(IGamesCallbacks igamescallbacks, String s1, String s2, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_120;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeInt(i1);
            int k1;
            if (flag)
            {
                k1 = j1;
            } else
            {
                k1 = 0;
            }
            parcel.writeInt(k1);
            if (!flag1)
            {
                j1 = 0;
            }
            parcel.writeInt(j1);
            kq.transact(12018, parcel, parcel1, 0);
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

        public void b(IGamesCallbacks igamescallbacks, String s1, String s2, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_100;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeString(s2);
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(6506, parcel, parcel1, 0);
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

        public void b(IGamesCallbacks igamescallbacks, String s1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_93;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(6502, parcel, parcel1, 0);
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

        public void b(IGamesCallbacks igamescallbacks, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_81;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(6503, parcel, parcel1, 0);
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

        public void b(IGamesCallbacks igamescallbacks, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeStringArray(as);
            kq.transact(10007, parcel, parcel1, 0);
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

        public void b(String s1, String s2, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeInt(i1);
            kq.transact(5051, parcel, parcel1, 0);
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

        public void ba(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s1);
            kq.transact(8002, parcel, parcel1, 0);
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

        public ParcelFileDescriptor bb(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s1);
            kq.transact(9030, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ParcelFileDescriptor parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return parcelfiledescriptor;
_L2:
            parcelfiledescriptor = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void c(long l1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeLong(l1);
            parcel.writeString(s1);
            kq.transact(10004, parcel, parcel1, 0);
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

        public void c(IGamesCallbacks igamescallbacks)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_61;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(5022, parcel, parcel1, 0);
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

        public void c(IGamesCallbacks igamescallbacks, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_106;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            int k1;
            if (flag)
            {
                k1 = j1;
            } else
            {
                k1 = 0;
            }
            parcel.writeInt(k1);
            if (!flag1)
            {
                j1 = 0;
            }
            parcel.writeInt(j1);
            kq.transact(5048, parcel, parcel1, 0);
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

        public void c(IGamesCallbacks igamescallbacks, long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_76;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l1);
            kq.transact(10001, parcel, parcel1, 0);
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

        public void c(IGamesCallbacks igamescallbacks, long l1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_83;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l1);
            parcel.writeString(s1);
            kq.transact(10003, parcel, parcel1, 0);
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

        public void c(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(5032, parcel, parcel1, 0);
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

        public void c(IGamesCallbacks igamescallbacks, String s1, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_82;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            kq.transact(12024, parcel, parcel1, 0);
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

        public void c(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_113;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            int k1;
            if (flag)
            {
                k1 = j1;
            } else
            {
                k1 = 0;
            }
            parcel.writeInt(k1);
            if (!flag1)
            {
                j1 = 0;
            }
            parcel.writeInt(j1);
            kq.transact(9001, parcel, parcel1, 0);
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

        public void c(IGamesCallbacks igamescallbacks, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_82;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeString(s2);
            kq.transact(8011, parcel, parcel1, 0);
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

        public void c(IGamesCallbacks igamescallbacks, String s1, String s2, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_100;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeString(s2);
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(12003, parcel, parcel1, 0);
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

        public void c(IGamesCallbacks igamescallbacks, String s1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_93;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(6504, parcel, parcel1, 0);
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

        public void c(IGamesCallbacks igamescallbacks, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_81;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(8027, parcel, parcel1, 0);
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

        public void c(IGamesCallbacks igamescallbacks, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeStringArray(as);
            kq.transact(10020, parcel, parcel1, 0);
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

        public void c(String s1, String s2, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeInt(i1);
            kq.transact(8026, parcel, parcel1, 0);
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

        public void ch(int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
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

        public void d(long l1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeLong(l1);
            parcel.writeString(s1);
            kq.transact(12014, parcel, parcel1, 0);
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

        public void d(IGamesCallbacks igamescallbacks)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_61;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(5026, parcel, parcel1, 0);
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

        public void d(IGamesCallbacks igamescallbacks, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_106;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            int k1;
            if (flag)
            {
                k1 = j1;
            } else
            {
                k1 = 0;
            }
            parcel.writeInt(k1);
            if (!flag1)
            {
                j1 = 0;
            }
            parcel.writeInt(j1);
            kq.transact(6003, parcel, parcel1, 0);
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

        public void d(IGamesCallbacks igamescallbacks, long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_76;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l1);
            kq.transact(12011, parcel, parcel1, 0);
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

        public void d(IGamesCallbacks igamescallbacks, long l1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_83;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l1);
            parcel.writeString(s1);
            kq.transact(12013, parcel, parcel1, 0);
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

        public void d(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(5037, parcel, parcel1, 0);
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

        public void d(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_113;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            int k1;
            if (flag)
            {
                k1 = j1;
            } else
            {
                k1 = 0;
            }
            parcel.writeInt(k1);
            if (!flag1)
            {
                j1 = 0;
            }
            parcel.writeInt(j1);
            kq.transact(9020, parcel, parcel1, 0);
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

        public void d(IGamesCallbacks igamescallbacks, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_82;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeString(s2);
            kq.transact(8015, parcel, parcel1, 0);
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

        public void d(IGamesCallbacks igamescallbacks, String s1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_93;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(6505, parcel, parcel1, 0);
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

        public void d(IGamesCallbacks igamescallbacks, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_81;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(12002, parcel, parcel1, 0);
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

        public void e(IGamesCallbacks igamescallbacks)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_61;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(5027, parcel, parcel1, 0);
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

        public void e(IGamesCallbacks igamescallbacks, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_106;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            int k1;
            if (flag)
            {
                k1 = j1;
            } else
            {
                k1 = 0;
            }
            parcel.writeInt(k1);
            if (!flag1)
            {
                j1 = 0;
            }
            parcel.writeInt(j1);
            kq.transact(6004, parcel, parcel1, 0);
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

        public void e(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(5042, parcel, parcel1, 0);
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

        public void e(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_113;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            int k1;
            if (flag)
            {
                k1 = j1;
            } else
            {
                k1 = 0;
            }
            parcel.writeInt(k1);
            if (!flag1)
            {
                j1 = 0;
            }
            parcel.writeInt(j1);
            kq.transact(12021, parcel, parcel1, 0);
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

        public void e(IGamesCallbacks igamescallbacks, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_82;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeString(s2);
            kq.transact(8016, parcel, parcel1, 0);
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

        public void e(IGamesCallbacks igamescallbacks, String s1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_93;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(12006, parcel, parcel1, 0);
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

        public void e(IGamesCallbacks igamescallbacks, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_81;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(12032, parcel, parcel1, 0);
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

        public Bundle ef()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(5004, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return bundle;
_L2:
            bundle = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void f(IGamesCallbacks igamescallbacks)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_61;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(5047, parcel, parcel1, 0);
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

        public void f(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(5043, parcel, parcel1, 0);
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

        public void f(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_113;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            int k1;
            if (flag)
            {
                k1 = j1;
            } else
            {
                k1 = 0;
            }
            parcel.writeInt(k1);
            if (!flag1)
            {
                j1 = 0;
            }
            parcel.writeInt(j1);
            kq.transact(12022, parcel, parcel1, 0);
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

        public void f(IGamesCallbacks igamescallbacks, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_82;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            parcel.writeString(s2);
            kq.transact(12009, parcel, parcel1, 0);
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

        public void f(IGamesCallbacks igamescallbacks, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_81;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            int i1;
            i1 = 0;
            if (flag)
            {
                i1 = 1;
            }
            parcel.writeInt(i1);
            kq.transact(12016, parcel, parcel1, 0);
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

        public void g(IGamesCallbacks igamescallbacks)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_61;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(5049, parcel, parcel1, 0);
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

        public void g(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(5052, parcel, parcel1, 0);
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

        public String gZ()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(5007, parcel, parcel1, 0);
            parcel1.readException();
            s1 = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ParcelFileDescriptor h(Uri uri)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L3:
            ParcelFileDescriptor parcelfiledescriptor;
            kq.transact(6507, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0)
            {
                break MISSING_BLOCK_LABEL_102;
            }
            parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return parcelfiledescriptor;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            parcelfiledescriptor = null;
              goto _L4
        }

        public RoomEntity h(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_102;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            int i1;
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(5053, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            RoomEntity roomentity;
            roomentity = null;
            if (i1 == 0)
            {
                break MISSING_BLOCK_LABEL_90;
            }
            roomentity = (RoomEntity)RoomEntity.CREATOR.createFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return roomentity;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void h(IGamesCallbacks igamescallbacks)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_61;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(5056, parcel, parcel1, 0);
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

        public DataHolder hA()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(5502, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            DataHolder dataholder1 = DataHolder.CREATOR.x(parcel1);
            DataHolder dataholder = dataholder1;
_L4:
            parcel1.recycle();
            parcel.recycle();
            return dataholder;
_L2:
            dataholder = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void hB()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(8022, parcel, parcel1, 0);
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

        public Intent hC()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(9013, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void hD()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(11002, parcel, parcel1, 0);
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

        public boolean hE()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(12025, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            boolean flag = false;
            if (i1 != 0)
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

        public String ha()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(5012, parcel, parcel1, 0);
            parcel1.readException();
            s1 = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent hd()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(9003, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent he()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(9005, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent hf()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(9006, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent hg()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(9007, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent hl()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(9010, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent hm()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(9012, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int hn()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(9019, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String ho()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(5003, parcel, parcel1, 0);
            parcel1.readException();
            s1 = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int hp()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(8024, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent hq()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(10015, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int hr()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(10013, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int hs()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(10023, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int ht()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(12035, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int hu()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(12036, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void hw()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(5006, parcel, parcel1, 0);
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

        public DataHolder hy()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(5013, parcel, parcel1, 0);
            parcel1.readException();
            if (parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            DataHolder dataholder1 = DataHolder.CREATOR.x(parcel1);
            DataHolder dataholder = dataholder1;
_L4:
            parcel1.recycle();
            parcel.recycle();
            return dataholder;
_L2:
            dataholder = null;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean hz()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            kq.transact(5067, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            boolean flag = false;
            if (i1 != 0)
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

        public void i(IGamesCallbacks igamescallbacks)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_61;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(5062, parcel, parcel1, 0);
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

        public void i(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(5061, parcel, parcel1, 0);
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

        public void j(IGamesCallbacks igamescallbacks)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_61;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            kq.transact(11001, parcel, parcel1, 0);
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

        public void j(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(5057, parcel, parcel1, 0);
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

        public void k(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(7001, parcel, parcel1, 0);
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

        public void l(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(8005, parcel, parcel1, 0);
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

        public void l(String s1, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s1);
            parcel.writeInt(i1);
            kq.transact(12017, parcel, parcel1, 0);
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

        public void m(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(8006, parcel, parcel1, 0);
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

        public void m(String s1, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s1);
            parcel.writeInt(i1);
            kq.transact(5029, parcel, parcel1, 0);
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

        public void m(String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s1);
            parcel.writeString(s2);
            kq.transact(5065, parcel, parcel1, 0);
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

        public void n(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(8009, parcel, parcel1, 0);
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

        public void n(String s1, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s1);
            parcel.writeInt(i1);
            kq.transact(5028, parcel, parcel1, 0);
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

        public void n(String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s1);
            parcel.writeString(s2);
            kq.transact(8025, parcel, parcel1, 0);
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

        public void o(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(8010, parcel, parcel1, 0);
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

        public void p(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(8014, parcel, parcel1, 0);
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

        public void p(String s1, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s1);
            parcel.writeInt(i1);
            kq.transact(5055, parcel, parcel1, 0);
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

        public void q(long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeLong(l1);
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

        public void q(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(9002, parcel, parcel1, 0);
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

        public void q(String s1, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s1);
            parcel.writeInt(i1);
            kq.transact(10014, parcel, parcel1, 0);
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

        public void r(long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeLong(l1);
            kq.transact(5059, parcel, parcel1, 0);
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

        public void r(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(12020, parcel, parcel1, 0);
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

        public void s(long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeLong(l1);
            kq.transact(8013, parcel, parcel1, 0);
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

        public void s(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(12005, parcel, parcel1, 0);
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

        public void t(long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeLong(l1);
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

        public void t(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(12027, parcel, parcel1, 0);
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

        public void u(long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeLong(l1);
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

        public void u(IGamesCallbacks igamescallbacks, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (igamescallbacks == null)
            {
                break MISSING_BLOCK_LABEL_70;
            }
            IBinder ibinder = igamescallbacks.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s1);
            kq.transact(12008, parcel, parcel1, 0);
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

        Stub.Proxy(IBinder ibinder)
        {
            kq = ibinder;
        }
    }


    public abstract void E(boolean flag)
        throws RemoteException;

    public abstract void F(boolean flag)
        throws RemoteException;

    public abstract int a(IGamesCallbacks igamescallbacks, byte abyte0[], String s1, String s2)
        throws RemoteException;

    public abstract Intent a(int i1, int j1, boolean flag)
        throws RemoteException;

    public abstract Intent a(int i1, byte abyte0[], int j1, String s1)
        throws RemoteException;

    public abstract Intent a(ZInvitationCluster zinvitationcluster, String s1, String s2)
        throws RemoteException;

    public abstract Intent a(GameRequestCluster gamerequestcluster, String s1)
        throws RemoteException;

    public abstract Intent a(RoomEntity roomentity, int i1)
        throws RemoteException;

    public abstract Intent a(String s1, boolean flag, boolean flag1, int i1)
        throws RemoteException;

    public abstract Intent a(int ai[])
        throws RemoteException;

    public abstract Intent a(ParticipantEntity aparticipantentity[], String s1, String s2, Uri uri, Uri uri1)
        throws RemoteException;

    public abstract void a(long l1, String s1)
        throws RemoteException;

    public abstract void a(IBinder ibinder, Bundle bundle)
        throws RemoteException;

    public abstract void a(Contents contents)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, int i1)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, int i1, int j1, int k1)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, int i1, int j1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, int i1, int j1, String as[], Bundle bundle)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, int i1, int ai[])
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, long l1)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, long l1, String s1)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, Bundle bundle, int i1, int j1)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, IBinder ibinder, int i1, String as[], Bundle bundle, boolean flag, long l1)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, IBinder ibinder, String s1, boolean flag, long l1)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, int i1)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, int i1, int j1, int k1, boolean flag)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, int i1, IBinder ibinder, Bundle bundle)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1, boolean flag2, boolean flag3)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, int i1, int ai[])
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, long l1)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, long l1, String s2)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, IBinder ibinder, Bundle bundle)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, SnapshotMetadataChange snapshotmetadatachange, Contents contents)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2, int i1, int j1)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2, int i1, int j1, int k1)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2, int i1, int j1, int k1, boolean flag)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2, SnapshotMetadataChange snapshotmetadatachange, Contents contents)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2, boolean flag)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2, int ai[], int i1, boolean flag)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2, String as[])
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2, String as[], boolean flag)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, boolean flag)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, byte abyte0[], String s2, ParticipantResult aparticipantresult[])
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, byte abyte0[], ParticipantResult aparticipantresult[])
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, int ai[])
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String as[], int i1, byte abyte0[], int j1)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, boolean flag)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, boolean flag, Bundle bundle)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, boolean flag, String as[])
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, int ai[])
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, int ai[], int i1, boolean flag)
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String as[])
        throws RemoteException;

    public abstract void a(IGamesCallbacks igamescallbacks, String as[], boolean flag)
        throws RemoteException;

    public abstract Intent aR(String s1)
        throws RemoteException;

    public abstract Intent aU(String s1)
        throws RemoteException;

    public abstract String aV(String s1)
        throws RemoteException;

    public abstract String aW(String s1)
        throws RemoteException;

    public abstract void aX(String s1)
        throws RemoteException;

    public abstract int aY(String s1)
        throws RemoteException;

    public abstract Uri aZ(String s1)
        throws RemoteException;

    public abstract int b(byte abyte0[], String s1, String as[])
        throws RemoteException;

    public abstract Intent b(int i1, int j1, boolean flag)
        throws RemoteException;

    public abstract void b(long l1, String s1)
        throws RemoteException;

    public abstract void b(IGamesCallbacks igamescallbacks)
        throws RemoteException;

    public abstract void b(IGamesCallbacks igamescallbacks, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void b(IGamesCallbacks igamescallbacks, long l1)
        throws RemoteException;

    public abstract void b(IGamesCallbacks igamescallbacks, long l1, String s1)
        throws RemoteException;

    public abstract void b(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, int i1)
        throws RemoteException;

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, int i1, int j1, int k1, boolean flag)
        throws RemoteException;

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, int i1, IBinder ibinder, Bundle bundle)
        throws RemoteException;

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag)
        throws RemoteException;

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, IBinder ibinder, Bundle bundle)
        throws RemoteException;

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, String s2)
        throws RemoteException;

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, String s2, int i1, int j1, int k1, boolean flag)
        throws RemoteException;

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, String s2, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, String s2, boolean flag)
        throws RemoteException;

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, boolean flag)
        throws RemoteException;

    public abstract void b(IGamesCallbacks igamescallbacks, boolean flag)
        throws RemoteException;

    public abstract void b(IGamesCallbacks igamescallbacks, String as[])
        throws RemoteException;

    public abstract void b(String s1, String s2, int i1)
        throws RemoteException;

    public abstract void ba(String s1)
        throws RemoteException;

    public abstract ParcelFileDescriptor bb(String s1)
        throws RemoteException;

    public abstract void c(long l1, String s1)
        throws RemoteException;

    public abstract void c(IGamesCallbacks igamescallbacks)
        throws RemoteException;

    public abstract void c(IGamesCallbacks igamescallbacks, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void c(IGamesCallbacks igamescallbacks, long l1)
        throws RemoteException;

    public abstract void c(IGamesCallbacks igamescallbacks, long l1, String s1)
        throws RemoteException;

    public abstract void c(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract void c(IGamesCallbacks igamescallbacks, String s1, int i1)
        throws RemoteException;

    public abstract void c(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void c(IGamesCallbacks igamescallbacks, String s1, String s2)
        throws RemoteException;

    public abstract void c(IGamesCallbacks igamescallbacks, String s1, String s2, boolean flag)
        throws RemoteException;

    public abstract void c(IGamesCallbacks igamescallbacks, String s1, boolean flag)
        throws RemoteException;

    public abstract void c(IGamesCallbacks igamescallbacks, boolean flag)
        throws RemoteException;

    public abstract void c(IGamesCallbacks igamescallbacks, String as[])
        throws RemoteException;

    public abstract void c(String s1, String s2, int i1)
        throws RemoteException;

    public abstract void ch(int i1)
        throws RemoteException;

    public abstract void d(long l1, String s1)
        throws RemoteException;

    public abstract void d(IGamesCallbacks igamescallbacks)
        throws RemoteException;

    public abstract void d(IGamesCallbacks igamescallbacks, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void d(IGamesCallbacks igamescallbacks, long l1)
        throws RemoteException;

    public abstract void d(IGamesCallbacks igamescallbacks, long l1, String s1)
        throws RemoteException;

    public abstract void d(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract void d(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void d(IGamesCallbacks igamescallbacks, String s1, String s2)
        throws RemoteException;

    public abstract void d(IGamesCallbacks igamescallbacks, String s1, boolean flag)
        throws RemoteException;

    public abstract void d(IGamesCallbacks igamescallbacks, boolean flag)
        throws RemoteException;

    public abstract void e(IGamesCallbacks igamescallbacks)
        throws RemoteException;

    public abstract void e(IGamesCallbacks igamescallbacks, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void e(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract void e(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void e(IGamesCallbacks igamescallbacks, String s1, String s2)
        throws RemoteException;

    public abstract void e(IGamesCallbacks igamescallbacks, String s1, boolean flag)
        throws RemoteException;

    public abstract void e(IGamesCallbacks igamescallbacks, boolean flag)
        throws RemoteException;

    public abstract Bundle ef()
        throws RemoteException;

    public abstract void f(IGamesCallbacks igamescallbacks)
        throws RemoteException;

    public abstract void f(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract void f(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void f(IGamesCallbacks igamescallbacks, String s1, String s2)
        throws RemoteException;

    public abstract void f(IGamesCallbacks igamescallbacks, boolean flag)
        throws RemoteException;

    public abstract void g(IGamesCallbacks igamescallbacks)
        throws RemoteException;

    public abstract void g(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract String gZ()
        throws RemoteException;

    public abstract ParcelFileDescriptor h(Uri uri)
        throws RemoteException;

    public abstract RoomEntity h(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract void h(IGamesCallbacks igamescallbacks)
        throws RemoteException;

    public abstract DataHolder hA()
        throws RemoteException;

    public abstract void hB()
        throws RemoteException;

    public abstract Intent hC()
        throws RemoteException;

    public abstract void hD()
        throws RemoteException;

    public abstract boolean hE()
        throws RemoteException;

    public abstract String ha()
        throws RemoteException;

    public abstract Intent hd()
        throws RemoteException;

    public abstract Intent he()
        throws RemoteException;

    public abstract Intent hf()
        throws RemoteException;

    public abstract Intent hg()
        throws RemoteException;

    public abstract Intent hl()
        throws RemoteException;

    public abstract Intent hm()
        throws RemoteException;

    public abstract int hn()
        throws RemoteException;

    public abstract String ho()
        throws RemoteException;

    public abstract int hp()
        throws RemoteException;

    public abstract Intent hq()
        throws RemoteException;

    public abstract int hr()
        throws RemoteException;

    public abstract int hs()
        throws RemoteException;

    public abstract int ht()
        throws RemoteException;

    public abstract int hu()
        throws RemoteException;

    public abstract void hw()
        throws RemoteException;

    public abstract DataHolder hy()
        throws RemoteException;

    public abstract boolean hz()
        throws RemoteException;

    public abstract void i(IGamesCallbacks igamescallbacks)
        throws RemoteException;

    public abstract void i(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract void j(IGamesCallbacks igamescallbacks)
        throws RemoteException;

    public abstract void j(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract void k(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract void l(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract void l(String s1, int i1)
        throws RemoteException;

    public abstract void m(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract void m(String s1, int i1)
        throws RemoteException;

    public abstract void m(String s1, String s2)
        throws RemoteException;

    public abstract void n(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract void n(String s1, int i1)
        throws RemoteException;

    public abstract void n(String s1, String s2)
        throws RemoteException;

    public abstract void o(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract void p(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract void p(String s1, int i1)
        throws RemoteException;

    public abstract void q(long l1)
        throws RemoteException;

    public abstract void q(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract void q(String s1, int i1)
        throws RemoteException;

    public abstract void r(long l1)
        throws RemoteException;

    public abstract void r(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract void s(long l1)
        throws RemoteException;

    public abstract void s(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract void t(long l1)
        throws RemoteException;

    public abstract void t(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;

    public abstract void u(long l1)
        throws RemoteException;

    public abstract void u(IGamesCallbacks igamescallbacks, String s1)
        throws RemoteException;
}
