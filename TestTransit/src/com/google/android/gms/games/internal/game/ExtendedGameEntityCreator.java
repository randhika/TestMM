// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;

// Referenced classes of package com.google.android.gms.games.internal.game:
//            ExtendedGameEntity, GameBadgeEntity

public class ExtendedGameEntityCreator
    implements android.os.Parcelable.Creator
{

    public ExtendedGameEntityCreator()
    {
    }

    static void a(ExtendedGameEntity extendedgameentity, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.a(parcel, 1, extendedgameentity.ia(), i, false);
        b.c(parcel, 1000, extendedgameentity.getVersionCode());
        b.c(parcel, 2, extendedgameentity.hR());
        b.a(parcel, 3, extendedgameentity.hS());
        b.c(parcel, 4, extendedgameentity.hT());
        b.a(parcel, 5, extendedgameentity.hU());
        b.a(parcel, 6, extendedgameentity.hV());
        b.a(parcel, 7, extendedgameentity.hW(), false);
        b.a(parcel, 8, extendedgameentity.hX());
        b.a(parcel, 9, extendedgameentity.hY(), false);
        b.b(parcel, 10, extendedgameentity.hQ(), false);
        b.a(parcel, 11, extendedgameentity.hZ(), i, false);
        b.G(parcel, j);
    }

    public ExtendedGameEntity bg(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 0;
        GameEntity gameentity = null;
        int k = 0;
        boolean flag = false;
        int l = 0;
        long l1 = 0L;
        long l2 = 0L;
        String s = null;
        long l3 = 0L;
        String s1 = null;
        java.util.ArrayList arraylist = null;
        SnapshotMetadataEntity snapshotmetadataentity = null;
        do
        {
            if (parcel.dataPosition() < i)
            {
                int i1 = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(i1))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, i1);
                    break;

                case 1: // '\001'
                    gameentity = (GameEntity)com.google.android.gms.common.internal.safeparcel.a.a(parcel, i1, GameEntity.CREATOR);
                    break;

                case 1000: 
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;

                case 2: // '\002'
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;

                case 3: // '\003'
                    flag = com.google.android.gms.common.internal.safeparcel.a.c(parcel, i1);
                    break;

                case 4: // '\004'
                    l = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;

                case 5: // '\005'
                    l1 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, i1);
                    break;

                case 6: // '\006'
                    l2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, i1);
                    break;

                case 7: // '\007'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, i1);
                    break;

                case 8: // '\b'
                    l3 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, i1);
                    break;

                case 9: // '\t'
                    s1 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, i1);
                    break;

                case 10: // '\n'
                    arraylist = com.google.android.gms.common.internal.safeparcel.a.c(parcel, i1, GameBadgeEntity.CREATOR);
                    break;

                case 11: // '\013'
                    snapshotmetadataentity = (SnapshotMetadataEntity)com.google.android.gms.common.internal.safeparcel.a.a(parcel, i1, SnapshotMetadataEntity.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new ExtendedGameEntity(j, gameentity, k, flag, l, l1, l2, s, l3, s1, arraylist, snapshotmetadataentity);
            }
        } while (true);
    }

    public ExtendedGameEntity[] co(int i)
    {
        return new ExtendedGameEntity[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return bg(parcel);
    }

    public Object[] newArray(int i)
    {
        return co(i);
    }
}
