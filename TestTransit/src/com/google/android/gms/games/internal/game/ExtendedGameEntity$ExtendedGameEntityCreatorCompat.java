// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import com.google.android.gms.games.GameEntity;
import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.games.internal.game:
//            ExtendedGameEntityCreator, ExtendedGameEntity, GameBadgeEntity, GameBadgeEntityCreator

static final class  extends ExtendedGameEntityCreator
{

    public ExtendedGameEntity bg(Parcel parcel)
    {
        if (ExtendedGameEntity.b(ExtendedGameEntity.gR()) || ExtendedGameEntity.aQ(com/google/android/gms/games/internal/game/ExtendedGameEntity.getCanonicalName()))
        {
            return super.bg(parcel);
        }
        GameEntity gameentity = (GameEntity)GameEntity.CREATOR.bg(parcel);
        int i = parcel.readInt();
        boolean flag;
        int j;
        long l;
        long l1;
        String s;
        long l2;
        String s1;
        int k;
        ArrayList arraylist;
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        j = parcel.readInt();
        l = parcel.readLong();
        l1 = parcel.readLong();
        s = parcel.readString();
        l2 = parcel.readLong();
        s1 = parcel.readString();
        k = parcel.readInt();
        arraylist = new ArrayList(k);
        for (int i1 = 0; i1 < k; i1++)
        {
            arraylist.add(GameBadgeEntity.CREATOR.bh(parcel));
        }

        return new ExtendedGameEntity(2, gameentity, i, flag, j, l, l1, s, l2, s1, arraylist, null);
    }

    public Object createFromParcel(Parcel parcel)
    {
        return bg(parcel);
    }

    ()
    {
    }
}
