// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.Contents;

// Referenced classes of package com.google.android.gms.drive.internal:
//            CloseContentsRequest

public class f
    implements android.os.Parcelable.Creator
{

    public f()
    {
    }

    static void a(CloseContentsRequest closecontentsrequest, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, closecontentsrequest.xM);
        b.a(parcel, 2, closecontentsrequest.Iw, i, false);
        b.a(parcel, 3, closecontentsrequest.Iz, false);
        b.G(parcel, j);
    }

    public CloseContentsRequest V(Parcel parcel)
    {
        Boolean boolean1;
        int i;
        int j;
        Contents contents;
        boolean1 = null;
        i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        j = 0;
        contents = null;
_L6:
        int k;
        if (parcel.dataPosition() >= i)
        {
            break MISSING_BLOCK_LABEL_164;
        }
        k = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
        com.google.android.gms.common.internal.safeparcel.a.ar(k);
        JVM INSTR tableswitch 1 3: default 60
    //                   1 91
    //                   2 117
    //                   3 145;
           goto _L1 _L2 _L3 _L4
_L4:
        break MISSING_BLOCK_LABEL_145;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        Boolean boolean2;
        Contents contents1;
        int l;
        com.google.android.gms.common.internal.safeparcel.a.b(parcel, k);
        boolean2 = boolean1;
        contents1 = contents;
        l = j;
_L7:
        j = l;
        contents = contents1;
        boolean1 = boolean2;
        if (true) goto _L6; else goto _L5
_L5:
        int i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
        Boolean boolean3 = boolean1;
        contents1 = contents;
        l = i1;
        boolean2 = boolean3;
          goto _L7
_L3:
        Contents contents2 = (Contents)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, Contents.CREATOR);
        l = j;
        boolean2 = boolean1;
        contents1 = contents2;
          goto _L7
        boolean2 = com.google.android.gms.common.internal.safeparcel.a.d(parcel, k);
        contents1 = contents;
        l = j;
          goto _L7
        if (parcel.dataPosition() != i)
        {
            throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
        } else
        {
            return new CloseContentsRequest(j, contents, boolean1);
        }
    }

    public CloseContentsRequest[] aQ(int i)
    {
        return new CloseContentsRequest[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return V(parcel);
    }

    public Object[] newArray(int i)
    {
        return aQ(i);
    }
}
