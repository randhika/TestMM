// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.os.Parcel;

// Referenced classes of package jp.co.yahoo.android.common:
//            MapParcelable

static final class 
    implements android.os.or
{

    public volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public MapParcelable createFromParcel(Parcel parcel)
    {
        return new MapParcelable(parcel);
    }

    public volatile Object[] newArray(int i)
    {
        return newArray(i);
    }

    public MapParcelable[] newArray(int i)
    {
        return new MapParcelable[i];
    }

    ()
    {
    }
}
