// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.drive.realtime.internal.event:
//            f

public class TextInsertedDetails
    implements SafeParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new f();
    final int LL;
    final int mIndex;
    final int xM;

    TextInsertedDetails(int i, int j, int k)
    {
        xM = i;
        mIndex = j;
        LL = k;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        f.a(this, parcel, i);
    }

}
