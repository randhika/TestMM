// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.wearable:
//            PutDataRequest

public class e
    implements android.os.Parcelable.Creator
{

    public e()
    {
    }

    static void a(PutDataRequest putdatarequest, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, putdatarequest.xM);
        b.a(parcel, 2, putdatarequest.getUri(), i, false);
        b.a(parcel, 4, putdatarequest.nm(), false);
        b.a(parcel, 5, putdatarequest.getData(), false);
        b.G(parcel, j);
    }

    public Object createFromParcel(Parcel parcel)
    {
        return cu(parcel);
    }

    public PutDataRequest cu(Parcel parcel)
    {
        byte abyte0[];
        int i;
        int j;
        android.os.Bundle bundle;
        Uri uri;
        abyte0 = null;
        i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        j = 0;
        bundle = null;
        uri = null;
_L7:
        int k;
        if (parcel.dataPosition() >= i)
        {
            break MISSING_BLOCK_LABEL_226;
        }
        k = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
        com.google.android.gms.common.internal.safeparcel.a.ar(k);
        JVM INSTR tableswitch 1 5: default 68
    //                   1 107
    //                   2 137
    //                   3 68
    //                   4 173
    //                   5 203;
           goto _L1 _L2 _L3 _L1 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_203;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        byte abyte1[];
        android.os.Bundle bundle1;
        Uri uri1;
        int l;
        com.google.android.gms.common.internal.safeparcel.a.b(parcel, k);
        abyte1 = abyte0;
        bundle1 = bundle;
        uri1 = uri;
        l = j;
_L8:
        j = l;
        uri = uri1;
        bundle = bundle1;
        abyte0 = abyte1;
        if (true) goto _L7; else goto _L6
_L6:
        int i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
        byte abyte3[] = abyte0;
        bundle1 = bundle;
        uri1 = uri;
        l = i1;
        abyte1 = abyte3;
          goto _L8
_L3:
        Uri uri2 = (Uri)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, Uri.CREATOR);
        l = j;
        android.os.Bundle bundle3 = bundle;
        uri1 = uri2;
        abyte1 = abyte0;
        bundle1 = bundle3;
          goto _L8
_L4:
        android.os.Bundle bundle2 = com.google.android.gms.common.internal.safeparcel.a.q(parcel, k);
        uri1 = uri;
        l = j;
        byte abyte2[] = abyte0;
        bundle1 = bundle2;
        abyte1 = abyte2;
          goto _L8
        abyte1 = com.google.android.gms.common.internal.safeparcel.a.r(parcel, k);
        bundle1 = bundle;
        uri1 = uri;
        l = j;
          goto _L8
        if (parcel.dataPosition() != i)
        {
            throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
        } else
        {
            return new PutDataRequest(j, uri, bundle, abyte0);
        }
    }

    public PutDataRequest[] ec(int i)
    {
        return new PutDataRequest[i];
    }

    public Object[] newArray(int i)
    {
        return ec(i);
    }
}
