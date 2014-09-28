// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.internal:
//            fr, fi, fg

public class fs
    implements android.os.Parcelable.Creator
{

    public fs()
    {
    }

    static void a(fr fr1, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.a(parcel, 1, fr1.yq, i, false);
        b.c(parcel, 1000, fr1.xM);
        b.a(parcel, 2, fr1.yr);
        b.c(parcel, 3, fr1.ys);
        b.a(parcel, 4, fr1.mP, false);
        b.a(parcel, 5, fr1.yt, i, false);
        b.G(parcel, j);
    }

    public fr[] K(int i)
    {
        return new fr[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return q(parcel);
    }

    public Object[] newArray(int i)
    {
        return K(i);
    }

    public fr q(Parcel parcel)
    {
        int i = 0;
        fg fg1 = null;
        int j = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        long l = 0L;
        String s = null;
        fi fi1 = null;
        int k = 0;
        do
        {
            if (parcel.dataPosition() < j)
            {
                int i1 = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(i1))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, i1);
                    break;

                case 1: // '\001'
                    fi1 = (fi)com.google.android.gms.common.internal.safeparcel.a.a(parcel, i1, fi.CREATOR);
                    break;

                case 1000: 
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;

                case 2: // '\002'
                    l = com.google.android.gms.common.internal.safeparcel.a.i(parcel, i1);
                    break;

                case 3: // '\003'
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;

                case 4: // '\004'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, i1);
                    break;

                case 5: // '\005'
                    fg1 = (fg)com.google.android.gms.common.internal.safeparcel.a.a(parcel, i1, fg.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new fr(k, fi1, l, i, s, fg1);
            }
        } while (true);
    }
}
