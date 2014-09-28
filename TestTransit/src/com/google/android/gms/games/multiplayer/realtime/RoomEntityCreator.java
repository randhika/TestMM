// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.multiplayer.realtime;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.multiplayer.ParticipantEntity;

// Referenced classes of package com.google.android.gms.games.multiplayer.realtime:
//            RoomEntity

public class RoomEntityCreator
    implements android.os.Parcelable.Creator
{

    public RoomEntityCreator()
    {
    }

    static void a(RoomEntity roomentity, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.a(parcel, 1, roomentity.getRoomId(), false);
        b.c(parcel, 1000, roomentity.getVersionCode());
        b.a(parcel, 2, roomentity.getCreatorId(), false);
        b.a(parcel, 3, roomentity.getCreationTimestamp());
        b.c(parcel, 4, roomentity.getStatus());
        b.a(parcel, 5, roomentity.getDescription(), false);
        b.c(parcel, 6, roomentity.getVariant());
        b.a(parcel, 7, roomentity.getAutoMatchCriteria(), false);
        b.b(parcel, 8, roomentity.getParticipants(), false);
        b.c(parcel, 9, roomentity.getAutoMatchWaitEstimateSeconds());
        b.G(parcel, j);
    }

    public RoomEntity bo(Parcel parcel)
    {
        int i = 0;
        java.util.ArrayList arraylist = null;
        int j = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        long l = 0L;
        android.os.Bundle bundle = null;
        int k = 0;
        String s = null;
        int i1 = 0;
        String s1 = null;
        String s2 = null;
        int j1 = 0;
        do
        {
            if (parcel.dataPosition() < j)
            {
                int k1 = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(k1))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, k1);
                    break;

                case 1: // '\001'
                    s2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k1);
                    break;

                case 1000: 
                    j1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k1);
                    break;

                case 2: // '\002'
                    s1 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k1);
                    break;

                case 3: // '\003'
                    l = com.google.android.gms.common.internal.safeparcel.a.i(parcel, k1);
                    break;

                case 4: // '\004'
                    i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k1);
                    break;

                case 5: // '\005'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k1);
                    break;

                case 6: // '\006'
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k1);
                    break;

                case 7: // '\007'
                    bundle = com.google.android.gms.common.internal.safeparcel.a.q(parcel, k1);
                    break;

                case 8: // '\b'
                    arraylist = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k1, ParticipantEntity.CREATOR);
                    break;

                case 9: // '\t'
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k1);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new RoomEntity(j1, s2, s1, l, i1, s, k, bundle, arraylist, i);
            }
        } while (true);
    }

    public RoomEntity[] cA(int i)
    {
        return new RoomEntity[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return bo(parcel);
    }

    public Object[] newArray(int i)
    {
        return cA(i);
    }
}
