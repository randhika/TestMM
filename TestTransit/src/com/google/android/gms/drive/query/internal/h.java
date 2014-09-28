// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.drive.query.internal:
//            LogicalFilter, Operator, FilterHolder

public class h
    implements android.os.Parcelable.Creator
{

    public h()
    {
    }

    static void a(LogicalFilter logicalfilter, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1000, logicalfilter.xM);
        b.a(parcel, 1, logicalfilter.KL, i, false);
        b.b(parcel, 2, logicalfilter.KY, false);
        b.G(parcel, j);
    }

    public LogicalFilter aL(Parcel parcel)
    {
        Object obj;
        int i;
        int j;
        Operator operator;
        obj = null;
        i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        j = 0;
        operator = null;
_L6:
        int k;
        if (parcel.dataPosition() >= i)
        {
            break MISSING_BLOCK_LABEL_175;
        }
        k = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
        com.google.android.gms.common.internal.safeparcel.a.ar(k);
        JVM INSTR lookupswitch 3: default 68
    //                   1: 125
    //                   2: 153
    //                   1000: 99;
           goto _L1 _L2 _L3 _L4
_L3:
        break MISSING_BLOCK_LABEL_153;
_L4:
        break; /* Loop/switch isn't completed */
_L1:
        Object obj1;
        Operator operator1;
        int l;
        com.google.android.gms.common.internal.safeparcel.a.b(parcel, k);
        obj1 = obj;
        operator1 = operator;
        l = j;
_L7:
        j = l;
        operator = operator1;
        obj = obj1;
        if (true) goto _L6; else goto _L5
_L5:
        int i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
        Object obj2 = obj;
        operator1 = operator;
        l = i1;
        obj1 = obj2;
          goto _L7
_L2:
        Operator operator2 = (Operator)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, Operator.CREATOR);
        l = j;
        obj1 = obj;
        operator1 = operator2;
          goto _L7
        obj1 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k, FilterHolder.CREATOR);
        operator1 = operator;
        l = j;
          goto _L7
        if (parcel.dataPosition() != i)
        {
            throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
        } else
        {
            return new LogicalFilter(j, operator, ((java.util.List) (obj)));
        }
    }

    public LogicalFilter[] bH(int i)
    {
        return new LogicalFilter[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return aL(parcel);
    }

    public Object[] newArray(int i)
    {
        return bH(i);
    }
}
