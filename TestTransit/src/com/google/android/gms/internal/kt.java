// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            ks

public class kt
    implements android.os.Parcelable.Creator
{

    public kt()
    {
    }

    static void a(ks ks1, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        Set set = ks1.kk();
        if (set.contains(Integer.valueOf(1)))
        {
            b.c(parcel, 1, ks1.getVersionCode());
        }
        if (set.contains(Integer.valueOf(2)))
        {
            b.a(parcel, 2, ks1.getAboutMe(), true);
        }
        if (set.contains(Integer.valueOf(3)))
        {
            b.a(parcel, 3, ks1.kF(), i, true);
        }
        if (set.contains(Integer.valueOf(4)))
        {
            b.a(parcel, 4, ks1.getBirthday(), true);
        }
        if (set.contains(Integer.valueOf(5)))
        {
            b.a(parcel, 5, ks1.getBraggingRights(), true);
        }
        if (set.contains(Integer.valueOf(6)))
        {
            b.c(parcel, 6, ks1.getCircledByCount());
        }
        if (set.contains(Integer.valueOf(7)))
        {
            b.a(parcel, 7, ks1.kG(), i, true);
        }
        if (set.contains(Integer.valueOf(8)))
        {
            b.a(parcel, 8, ks1.getCurrentLocation(), true);
        }
        if (set.contains(Integer.valueOf(9)))
        {
            b.a(parcel, 9, ks1.getDisplayName(), true);
        }
        if (set.contains(Integer.valueOf(12)))
        {
            b.c(parcel, 12, ks1.getGender());
        }
        if (set.contains(Integer.valueOf(14)))
        {
            b.a(parcel, 14, ks1.getId(), true);
        }
        if (set.contains(Integer.valueOf(15)))
        {
            b.a(parcel, 15, ks1.kH(), i, true);
        }
        if (set.contains(Integer.valueOf(16)))
        {
            b.a(parcel, 16, ks1.isPlusUser());
        }
        if (set.contains(Integer.valueOf(19)))
        {
            b.a(parcel, 19, ks1.kI(), i, true);
        }
        if (set.contains(Integer.valueOf(18)))
        {
            b.a(parcel, 18, ks1.getLanguage(), true);
        }
        if (set.contains(Integer.valueOf(21)))
        {
            b.c(parcel, 21, ks1.getObjectType());
        }
        if (set.contains(Integer.valueOf(20)))
        {
            b.a(parcel, 20, ks1.getNickname(), true);
        }
        if (set.contains(Integer.valueOf(23)))
        {
            b.b(parcel, 23, ks1.kK(), true);
        }
        if (set.contains(Integer.valueOf(22)))
        {
            b.b(parcel, 22, ks1.kJ(), true);
        }
        if (set.contains(Integer.valueOf(25)))
        {
            b.c(parcel, 25, ks1.getRelationshipStatus());
        }
        if (set.contains(Integer.valueOf(24)))
        {
            b.c(parcel, 24, ks1.getPlusOneCount());
        }
        if (set.contains(Integer.valueOf(27)))
        {
            b.a(parcel, 27, ks1.getUrl(), true);
        }
        if (set.contains(Integer.valueOf(26)))
        {
            b.a(parcel, 26, ks1.getTagline(), true);
        }
        if (set.contains(Integer.valueOf(29)))
        {
            b.a(parcel, 29, ks1.isVerified());
        }
        if (set.contains(Integer.valueOf(28)))
        {
            b.b(parcel, 28, ks1.kL(), true);
        }
        b.G(parcel, j);
    }

    public ks bG(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        HashSet hashset = new HashSet();
        int j = 0;
        String s = null;
        ks.a a1 = null;
        String s1 = null;
        String s2 = null;
        int k = 0;
        ks.b b1 = null;
        String s3 = null;
        String s4 = null;
        int l = 0;
        String s5 = null;
        ks.c c = null;
        boolean flag = false;
        String s6 = null;
        ks.d d = null;
        String s7 = null;
        int i1 = 0;
        java.util.ArrayList arraylist = null;
        java.util.ArrayList arraylist1 = null;
        int j1 = 0;
        int k1 = 0;
        String s8 = null;
        String s9 = null;
        java.util.ArrayList arraylist2 = null;
        boolean flag1 = false;
        do
        {
            if (parcel.dataPosition() < i)
            {
                int l1 = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(l1))
                {
                case 10: // '\n'
                case 11: // '\013'
                case 13: // '\r'
                case 17: // '\021'
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, l1);
                    break;

                case 1: // '\001'
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l1);
                    hashset.add(Integer.valueOf(1));
                    break;

                case 2: // '\002'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, l1);
                    hashset.add(Integer.valueOf(2));
                    break;

                case 3: // '\003'
                    ks.a a2 = (ks.a)com.google.android.gms.common.internal.safeparcel.a.a(parcel, l1, ks.a.CREATOR);
                    hashset.add(Integer.valueOf(3));
                    a1 = a2;
                    break;

                case 4: // '\004'
                    s1 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, l1);
                    hashset.add(Integer.valueOf(4));
                    break;

                case 5: // '\005'
                    s2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, l1);
                    hashset.add(Integer.valueOf(5));
                    break;

                case 6: // '\006'
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l1);
                    hashset.add(Integer.valueOf(6));
                    break;

                case 7: // '\007'
                    ks.b b2 = (ks.b)com.google.android.gms.common.internal.safeparcel.a.a(parcel, l1, ks.b.CREATOR);
                    hashset.add(Integer.valueOf(7));
                    b1 = b2;
                    break;

                case 8: // '\b'
                    s3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, l1);
                    hashset.add(Integer.valueOf(8));
                    break;

                case 9: // '\t'
                    s4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, l1);
                    hashset.add(Integer.valueOf(9));
                    break;

                case 12: // '\f'
                    l = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l1);
                    hashset.add(Integer.valueOf(12));
                    break;

                case 14: // '\016'
                    s5 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, l1);
                    hashset.add(Integer.valueOf(14));
                    break;

                case 15: // '\017'
                    ks.c c1 = (ks.c)com.google.android.gms.common.internal.safeparcel.a.a(parcel, l1, ks.c.CREATOR);
                    hashset.add(Integer.valueOf(15));
                    c = c1;
                    break;

                case 16: // '\020'
                    flag = com.google.android.gms.common.internal.safeparcel.a.c(parcel, l1);
                    hashset.add(Integer.valueOf(16));
                    break;

                case 19: // '\023'
                    ks.d d1 = (ks.d)com.google.android.gms.common.internal.safeparcel.a.a(parcel, l1, ks.d.CREATOR);
                    hashset.add(Integer.valueOf(19));
                    d = d1;
                    break;

                case 18: // '\022'
                    s6 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, l1);
                    hashset.add(Integer.valueOf(18));
                    break;

                case 21: // '\025'
                    i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l1);
                    hashset.add(Integer.valueOf(21));
                    break;

                case 20: // '\024'
                    s7 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, l1);
                    hashset.add(Integer.valueOf(20));
                    break;

                case 23: // '\027'
                    arraylist1 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, l1, ks.g.CREATOR);
                    hashset.add(Integer.valueOf(23));
                    break;

                case 22: // '\026'
                    arraylist = com.google.android.gms.common.internal.safeparcel.a.c(parcel, l1, ks.f.CREATOR);
                    hashset.add(Integer.valueOf(22));
                    break;

                case 25: // '\031'
                    k1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l1);
                    hashset.add(Integer.valueOf(25));
                    break;

                case 24: // '\030'
                    j1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l1);
                    hashset.add(Integer.valueOf(24));
                    break;

                case 27: // '\033'
                    s9 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, l1);
                    hashset.add(Integer.valueOf(27));
                    break;

                case 26: // '\032'
                    s8 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, l1);
                    hashset.add(Integer.valueOf(26));
                    break;

                case 29: // '\035'
                    flag1 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, l1);
                    hashset.add(Integer.valueOf(29));
                    break;

                case 28: // '\034'
                    arraylist2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, l1, ks.h.CREATOR);
                    hashset.add(Integer.valueOf(28));
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new ks(hashset, j, s, a1, s1, s2, k, b1, s3, s4, l, s5, c, flag, s6, d, s7, i1, arraylist, arraylist1, j1, k1, s8, s9, arraylist2, flag1);
            }
        } while (true);
    }

    public Object createFromParcel(Parcel parcel)
    {
        return bG(parcel);
    }

    public ks[] dd(int i)
    {
        return new ks[i];
    }

    public Object[] newArray(int i)
    {
        return dd(i);
    }
}
