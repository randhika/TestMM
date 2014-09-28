// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.games:
//            PlayerLevel

public class PlayerLevelCreator
    implements android.os.Parcelable.Creator
{

    public static final int CONTENT_DESCRIPTION;

    public PlayerLevelCreator()
    {
    }

    static void a(PlayerLevel playerlevel, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, playerlevel.getLevelNumber());
        b.c(parcel, 1000, playerlevel.getVersionCode());
        b.a(parcel, 2, playerlevel.getMinXp());
        b.a(parcel, 3, playerlevel.getMaxXp());
        b.G(parcel, j);
    }

    public PlayerLevel createFromParcel(Parcel parcel)
    {
        long l = 0L;
        int i = 0;
        int j = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        long l1 = l;
        int k = 0;
        do
        {
            if (parcel.dataPosition() < j)
            {
                int i1 = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(i1))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, i1);
                    break;

                case 1: // '\001'
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;

                case 1000: 
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;

                case 2: // '\002'
                    l1 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, i1);
                    break;

                case 3: // '\003'
                    l = com.google.android.gms.common.internal.safeparcel.a.i(parcel, i1);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new PlayerLevel(k, i, l1, l);
            }
        } while (true);
    }

    public volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public PlayerLevel[] newArray(int i)
    {
        return new PlayerLevel[i];
    }

    public volatile Object[] newArray(int i)
    {
        return newArray(i);
    }
}
