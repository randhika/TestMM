// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.metadata.CustomPropertyKey;

// Referenced classes of package com.google.android.gms.drive.metadata.internal:
//            CustomProperty

public class c
    implements android.os.Parcelable.Creator
{

    public c()
    {
    }

    static void a(CustomProperty customproperty, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, customproperty.xM);
        b.a(parcel, 2, customproperty.JQ, i, false);
        b.a(parcel, 3, customproperty.mValue, false);
        b.G(parcel, j);
    }

    public CustomProperty aB(Parcel parcel)
    {
        String s;
        int i;
        int j;
        CustomPropertyKey custompropertykey;
        s = null;
        i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        j = 0;
        custompropertykey = null;
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
        String s1;
        CustomPropertyKey custompropertykey1;
        int l;
        com.google.android.gms.common.internal.safeparcel.a.b(parcel, k);
        s1 = s;
        custompropertykey1 = custompropertykey;
        l = j;
_L7:
        j = l;
        custompropertykey = custompropertykey1;
        s = s1;
        if (true) goto _L6; else goto _L5
_L5:
        int i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
        String s2 = s;
        custompropertykey1 = custompropertykey;
        l = i1;
        s1 = s2;
          goto _L7
_L3:
        CustomPropertyKey custompropertykey2 = (CustomPropertyKey)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, CustomPropertyKey.CREATOR);
        l = j;
        s1 = s;
        custompropertykey1 = custompropertykey2;
          goto _L7
        s1 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
        custompropertykey1 = custompropertykey;
        l = j;
          goto _L7
        if (parcel.dataPosition() != i)
        {
            throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
        } else
        {
            return new CustomProperty(j, custompropertykey, s);
        }
    }

    public CustomProperty[] bx(int i)
    {
        return new CustomProperty[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return aB(parcel);
    }

    public Object[] newArray(int i)
    {
        return bx(i);
    }
}
