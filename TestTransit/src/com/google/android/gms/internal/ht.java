// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.internal:
//            hu, hv

public class ht
    implements SafeParcelable
{

    public static final hu CREATOR = new hu();
    private final hv GV;
    private final int xM;

    ht(int i, hv hv1)
    {
        xM = i;
        GV = hv1;
    }

    private ht(hv hv1)
    {
        xM = 1;
        GV = hv1;
    }

    public static ht a(hy.b b)
    {
        if (b instanceof hv)
        {
            return new ht((hv)b);
        } else
        {
            throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
        }
    }

    public int describeContents()
    {
        hu _tmp = CREATOR;
        return 0;
    }

    hv fB()
    {
        return GV;
    }

    public hy.b fC()
    {
        if (GV != null)
        {
            return GV;
        } else
        {
            throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
        }
    }

    int getVersionCode()
    {
        return xM;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        hu _tmp = CREATOR;
        hu.a(this, parcel, i);
    }

}
