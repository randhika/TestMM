// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.cast;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.cast:
//            LaunchOptions

public class c
    implements android.os.Parcelable.Creator
{

    public c()
    {
    }

    static void a(LaunchOptions launchoptions, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, launchoptions.getVersionCode());
        b.a(parcel, 2, launchoptions.getRelaunchIfRunning());
        b.a(parcel, 3, launchoptions.getLanguage(), false);
        b.G(parcel, j);
    }

    public LaunchOptions[] Q(int i)
    {
        return new LaunchOptions[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return t(parcel);
    }

    public Object[] newArray(int i)
    {
        return Q(i);
    }

    public LaunchOptions t(Parcel parcel)
    {
        boolean flag = false;
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        String s = null;
        int j = 0;
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
                    flag = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k);
                    break;

                case 3: // '\003'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new LaunchOptions(j, flag, s);
            }
        } while (true);
    }
}
