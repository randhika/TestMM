// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.internal:
//            ch, ce, ev, v

public class cg
    implements android.os.Parcelable.Creator
{

    public cg()
    {
    }

    static void a(ch ch1, Parcel parcel, int i)
    {
        int k = b.C(parcel);
        b.c(parcel, 1, ch1.versionCode);
        b.a(parcel, 2, ch1.ov, i, false);
        b.a(parcel, 3, ch1.aU(), false);
        b.a(parcel, 4, ch1.aV(), false);
        b.a(parcel, 5, ch1.aW(), false);
        b.a(parcel, 6, ch1.aX(), false);
        b.a(parcel, 7, ch1.oA, false);
        b.a(parcel, 8, ch1.oB);
        b.a(parcel, 9, ch1.oC, false);
        b.a(parcel, 10, ch1.aZ(), false);
        b.c(parcel, 11, ch1.orientation);
        b.c(parcel, 12, ch1.oE);
        b.a(parcel, 13, ch1.ob, false);
        b.a(parcel, 14, ch1.kQ, i, false);
        b.a(parcel, 15, ch1.aY(), false);
        b.a(parcel, 17, ch1.oH, i, false);
        b.a(parcel, 16, ch1.oG, false);
        b.G(parcel, k);
    }

    public Object createFromParcel(Parcel parcel)
    {
        return f(parcel);
    }

    public ch f(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int k = 0;
        ce ce1 = null;
        android.os.IBinder ibinder = null;
        android.os.IBinder ibinder1 = null;
        android.os.IBinder ibinder2 = null;
        android.os.IBinder ibinder3 = null;
        String s = null;
        boolean flag = false;
        String s1 = null;
        android.os.IBinder ibinder4 = null;
        int l = 0;
        int i1 = 0;
        String s2 = null;
        ev ev1 = null;
        android.os.IBinder ibinder5 = null;
        String s3 = null;
        v v1 = null;
        do
        {
            if (parcel.dataPosition() < i)
            {
                int j1 = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(j1))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, j1);
                    break;

                case 1: // '\001'
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, j1);
                    break;

                case 2: // '\002'
                    ce1 = (ce)com.google.android.gms.common.internal.safeparcel.a.a(parcel, j1, ce.CREATOR);
                    break;

                case 3: // '\003'
                    ibinder = com.google.android.gms.common.internal.safeparcel.a.p(parcel, j1);
                    break;

                case 4: // '\004'
                    ibinder1 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, j1);
                    break;

                case 5: // '\005'
                    ibinder2 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, j1);
                    break;

                case 6: // '\006'
                    ibinder3 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, j1);
                    break;

                case 7: // '\007'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 8: // '\b'
                    flag = com.google.android.gms.common.internal.safeparcel.a.c(parcel, j1);
                    break;

                case 9: // '\t'
                    s1 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 10: // '\n'
                    ibinder4 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, j1);
                    break;

                case 11: // '\013'
                    l = com.google.android.gms.common.internal.safeparcel.a.g(parcel, j1);
                    break;

                case 12: // '\f'
                    i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, j1);
                    break;

                case 13: // '\r'
                    s2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 14: // '\016'
                    ev1 = (ev)com.google.android.gms.common.internal.safeparcel.a.a(parcel, j1, ev.CREATOR);
                    break;

                case 15: // '\017'
                    ibinder5 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, j1);
                    break;

                case 17: // '\021'
                    v1 = (v)com.google.android.gms.common.internal.safeparcel.a.a(parcel, j1, v.CREATOR);
                    break;

                case 16: // '\020'
                    s3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new ch(k, ce1, ibinder, ibinder1, ibinder2, ibinder3, s, flag, s1, ibinder4, l, i1, s2, ev1, ibinder5, s3, v1);
            }
        } while (true);
    }

    public ch[] j(int i)
    {
        return new ch[i];
    }

    public Object[] newArray(int i)
    {
        return j(i);
    }
}
