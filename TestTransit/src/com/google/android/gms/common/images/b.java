// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;

// Referenced classes of package com.google.android.gms.common.images:
//            WebImage

public class b
    implements android.os.Parcelable.Creator
{

    public b()
    {
    }

    static void a(WebImage webimage, Parcel parcel, int i)
    {
        int j = com.google.android.gms.common.internal.safeparcel.b.C(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, webimage.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, webimage.getUrl(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, webimage.getWidth());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, webimage.getHeight());
        com.google.android.gms.common.internal.safeparcel.b.G(parcel, j);
    }

    public WebImage[] ak(int i)
    {
        return new WebImage[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return y(parcel);
    }

    public Object[] newArray(int i)
    {
        return ak(i);
    }

    public WebImage y(Parcel parcel)
    {
        int i;
        int j;
        Uri uri;
        int k;
        int l;
        i = 0;
        j = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        uri = null;
        k = 0;
        l = 0;
_L7:
        int i1;
        if (parcel.dataPosition() >= j)
        {
            break MISSING_BLOCK_LABEL_222;
        }
        i1 = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
        com.google.android.gms.common.internal.safeparcel.a.ar(i1);
        JVM INSTR tableswitch 1 4: default 64
    //                   1 103
    //                   2 133
    //                   3 169
    //                   4 199;
           goto _L1 _L2 _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_199;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        int j1;
        int k1;
        Uri uri1;
        int l1;
        com.google.android.gms.common.internal.safeparcel.a.b(parcel, i1);
        j1 = i;
        k1 = k;
        uri1 = uri;
        l1 = l;
_L8:
        l = l1;
        uri = uri1;
        k = k1;
        i = j1;
        if (true) goto _L7; else goto _L6
_L6:
        int l2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
        int i3 = i;
        k1 = k;
        uri1 = uri;
        l1 = l2;
        j1 = i3;
          goto _L8
_L3:
        Uri uri2 = (Uri)com.google.android.gms.common.internal.safeparcel.a.a(parcel, i1, Uri.CREATOR);
        l1 = l;
        int k2 = k;
        uri1 = uri2;
        j1 = i;
        k1 = k2;
          goto _L8
_L4:
        int i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
        uri1 = uri;
        l1 = l;
        int j2 = i;
        k1 = i2;
        j1 = j2;
          goto _L8
        j1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
        k1 = k;
        uri1 = uri;
        l1 = l;
          goto _L8
        if (parcel.dataPosition() != j)
        {
            throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(j).toString(), parcel);
        } else
        {
            return new WebImage(l, uri, k, i);
        }
    }
}
