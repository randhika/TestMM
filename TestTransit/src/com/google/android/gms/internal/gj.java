// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.internal:
//            gk, hk

public class gj
    implements SafeParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new gk();
    private double AP;
    private boolean AQ;
    private int BR;
    private final int xM;

    public gj()
    {
        this(1, (0.0D / 0.0D), false, -1);
    }

    gj(int i, double d, boolean flag, int j)
    {
        xM = i;
        AP = d;
        AQ = flag;
        BR = j;
    }

    public int describeContents()
    {
        return 0;
    }

    public double eh()
    {
        return AP;
    }

    public boolean en()
    {
        return AQ;
    }

    public int eo()
    {
        return BR;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof gj))
            {
                return false;
            }
            gj gj1 = (gj)obj;
            if (AP != gj1.AP || AQ != gj1.AQ || BR != gj1.BR)
            {
                return false;
            }
        }
        return true;
    }

    public int getVersionCode()
    {
        return xM;
    }

    public int hashCode()
    {
        Object aobj[] = new Object[3];
        aobj[0] = Double.valueOf(AP);
        aobj[1] = Boolean.valueOf(AQ);
        aobj[2] = Integer.valueOf(BR);
        return hk.hashCode(aobj);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        gk.a(this, parcel, i);
    }

}
