// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

// Referenced classes of package com.google.android.gms.drive.query.internal:
//            ComparisonFilter, Operator

public class a
    implements android.os.Parcelable.Creator
{

    public a()
    {
    }

    static void a(ComparisonFilter comparisonfilter, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1000, comparisonfilter.xM);
        b.a(parcel, 1, comparisonfilter.KL, i, false);
        b.a(parcel, 2, comparisonfilter.KM, i, false);
        b.G(parcel, j);
    }

    public ComparisonFilter aF(Parcel parcel)
    {
        MetadataBundle metadatabundle;
        int i;
        int j;
        Operator operator;
        metadatabundle = null;
        i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        j = 0;
        operator = null;
_L6:
        int k;
        if (parcel.dataPosition() >= i)
        {
            break MISSING_BLOCK_LABEL_178;
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
        MetadataBundle metadatabundle1;
        Operator operator1;
        int l;
        com.google.android.gms.common.internal.safeparcel.a.b(parcel, k);
        metadatabundle1 = metadatabundle;
        operator1 = operator;
        l = j;
_L7:
        j = l;
        operator = operator1;
        metadatabundle = metadatabundle1;
        if (true) goto _L6; else goto _L5
_L5:
        int i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
        MetadataBundle metadatabundle2 = metadatabundle;
        operator1 = operator;
        l = i1;
        metadatabundle1 = metadatabundle2;
          goto _L7
_L2:
        Operator operator2 = (Operator)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, Operator.CREATOR);
        l = j;
        metadatabundle1 = metadatabundle;
        operator1 = operator2;
          goto _L7
        metadatabundle1 = (MetadataBundle)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, MetadataBundle.CREATOR);
        operator1 = operator;
        l = j;
          goto _L7
        if (parcel.dataPosition() != i)
        {
            throw new com.google.android.gms.common.internal.safeparcel.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
        } else
        {
            return new ComparisonFilter(j, operator, metadatabundle);
        }
    }

    public ComparisonFilter[] bB(int i)
    {
        return new ComparisonFilter[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return aF(parcel);
    }

    public Object[] newArray(int i)
    {
        return bB(i);
    }
}
