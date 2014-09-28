// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.game;

import android.net.Uri;
import android.os.Parcel;

// Referenced classes of package com.google.android.gms.games.internal.game:
//            GameBadgeEntityCreator, GameBadgeEntity

static final class  extends GameBadgeEntityCreator
{

    public GameBadgeEntity bh(Parcel parcel)
    {
        if (GameBadgeEntity.b(GameBadgeEntity.gR()) || GameBadgeEntity.aQ(com/google/android/gms/games/internal/game/GameBadgeEntity.getCanonicalName()))
        {
            return super.bh(parcel);
        }
        int i = parcel.readInt();
        String s = parcel.readString();
        String s1 = parcel.readString();
        String s2 = parcel.readString();
        Uri uri;
        if (s2 == null)
        {
            uri = null;
        } else
        {
            uri = Uri.parse(s2);
        }
        return new GameBadgeEntity(1, i, s, s1, uri);
    }

    public Object createFromParcel(Parcel parcel)
    {
        return bh(parcel);
    }

    ()
    {
    }
}
