// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.internal:
//            ds, ai, al, ev

public class dt
    implements android.os.Parcelable.Creator
{

    public dt()
    {
    }

    static void a(ds ds1, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, ds1.versionCode);
        b.a(parcel, 2, ds1.pW, false);
        b.a(parcel, 3, ds1.pX, i, false);
        b.a(parcel, 4, ds1.kT, i, false);
        b.a(parcel, 5, ds1.kN, false);
        b.a(parcel, 6, ds1.applicationInfo, i, false);
        b.a(parcel, 7, ds1.pY, i, false);
        b.a(parcel, 8, ds1.pZ, false);
        b.a(parcel, 9, ds1.qa, false);
        b.a(parcel, 10, ds1.qb, false);
        b.a(parcel, 11, ds1.kQ, i, false);
        b.a(parcel, 12, ds1.qc, false);
        b.a(parcel, 13, ds1.qd, false);
        b.G(parcel, j);
    }

    public Object createFromParcel(Parcel parcel)
    {
        return h(parcel);
    }

    public ds h(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 0;
        android.os.Bundle bundle = null;
        ai ai1 = null;
        al al1 = null;
        String s = null;
        ApplicationInfo applicationinfo = null;
        PackageInfo packageinfo = null;
        String s1 = null;
        String s2 = null;
        String s3 = null;
        ev ev1 = null;
        android.os.Bundle bundle1 = null;
        String s4 = null;
        do
        {
            if (parcel.dataPosition() < i)
            {
                int k = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(k))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, k);
                    break;

                case 1: // '\001'
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
                    break;

                case 2: // '\002'
                    bundle = com.google.android.gms.common.internal.safeparcel.a.q(parcel, k);
                    break;

                case 3: // '\003'
                    ai1 = (ai)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, ai.CREATOR);
                    break;

                case 4: // '\004'
                    al1 = (al)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, al.CREATOR);
                    break;

                case 5: // '\005'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 6: // '\006'
                    applicationinfo = (ApplicationInfo)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, ApplicationInfo.CREATOR);
                    break;

                case 7: // '\007'
                    packageinfo = (PackageInfo)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, PackageInfo.CREATOR);
                    break;

                case 8: // '\b'
                    s1 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 9: // '\t'
                    s2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 10: // '\n'
                    s3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 11: // '\013'
                    ev1 = (ev)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, ev.CREATOR);
                    break;

                case 12: // '\f'
                    bundle1 = com.google.android.gms.common.internal.safeparcel.a.q(parcel, k);
                    break;

                case 13: // '\r'
                    s4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new ds(j, bundle, ai1, al1, s, applicationinfo, packageinfo, s1, s2, s3, ev1, bundle1, s4);
            }
        } while (true);
    }

    public ds[] m(int i)
    {
        return new ds[i];
    }

    public Object[] newArray(int i)
    {
        return m(i);
    }
}
