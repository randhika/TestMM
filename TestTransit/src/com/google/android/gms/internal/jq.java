// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.gms.internal:
//            jr, hk, jm

public final class jq
    implements SafeParcelable
{

    public static final jr CREATOR = new jr();
    static final long Wm;
    private final long Vl;
    private final jm Wn;
    private final int mPriority;
    final int xM;

    public jq(int i, jm jm, long l, int j)
    {
        xM = i;
        Wn = jm;
        Vl = l;
        mPriority = j;
    }

    public int describeContents()
    {
        jr _tmp = CREATOR;
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof jq))
            {
                return false;
            }
            jq jq1 = (jq)obj;
            if (!hk.equal(Wn, jq1.Wn) || Vl != jq1.Vl || mPriority != jq1.mPriority)
            {
                return false;
            }
        }
        return true;
    }

    public long getInterval()
    {
        return Vl;
    }

    public int getPriority()
    {
        return mPriority;
    }

    public int hashCode()
    {
        Object aobj[] = new Object[3];
        aobj[0] = Wn;
        aobj[1] = Long.valueOf(Vl);
        aobj[2] = Integer.valueOf(mPriority);
        return hk.hashCode(aobj);
    }

    public jm jf()
    {
        return Wn;
    }

    public String toString()
    {
        return hk.e(this).a("filter", Wn).a("interval", Long.valueOf(Vl)).a("priority", Integer.valueOf(mPriority)).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        jr _tmp = CREATOR;
        jr.a(this, parcel, i);
    }

    static 
    {
        Wm = TimeUnit.HOURS.toMillis(1L);
    }
}
