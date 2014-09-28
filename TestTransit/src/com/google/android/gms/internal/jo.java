// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.internal:
//            jp, hk

public class jo
    implements SafeParcelable
{

    public static final jp CREATOR = new jp();
    private final String Wl;
    private final String mTag;
    final int xM;

    jo(int i, String s, String s1)
    {
        xM = i;
        Wl = s;
        mTag = s1;
    }

    public int describeContents()
    {
        jp _tmp = CREATOR;
        return 0;
    }

    public boolean equals(Object obj)
    {
        jo jo1;
        if (obj instanceof jo)
        {
            if (hk.equal(Wl, (jo1 = (jo)obj).Wl) && hk.equal(mTag, jo1.mTag))
            {
                return true;
            }
        }
        return false;
    }

    public String getTag()
    {
        return mTag;
    }

    public int hashCode()
    {
        Object aobj[] = new Object[2];
        aobj[0] = Wl;
        aobj[1] = mTag;
        return hk.hashCode(aobj);
    }

    public String jj()
    {
        return Wl;
    }

    public String toString()
    {
        return hk.e(this).a("mPlaceId", Wl).a("mTag", mTag).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        jp _tmp = CREATOR;
        jp.a(this, parcel, i);
    }

}
