// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;

// Referenced classes of package com.google.android.gms.games:
//            PlayerEntityCreator, PlayerEntity

static final class I extends PlayerEntityCreator
{

    public PlayerEntity be(Parcel parcel)
    {
        if (PlayerEntity.b(PlayerEntity.gR()) || PlayerEntity.aQ(com/google/android/gms/games/PlayerEntity.getCanonicalName()))
        {
            return super.be(parcel);
        }
        String s = parcel.readString();
        String s1 = parcel.readString();
        String s2 = parcel.readString();
        String s3 = parcel.readString();
        Uri uri;
        Uri uri1;
        if (s2 == null)
        {
            uri = null;
        } else
        {
            uri = Uri.parse(s2);
        }
        if (s3 == null)
        {
            uri1 = null;
        } else
        {
            uri1 = Uri.parse(s3);
        }
        return new PlayerEntity(10, s, s1, uri, uri1, parcel.readLong(), -1, -1L, null, null, null, null, null, true);
    }

    public Object createFromParcel(Parcel parcel)
    {
        return be(parcel);
    }

    I()
    {
    }
}
