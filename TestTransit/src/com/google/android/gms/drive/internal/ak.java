// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.drive.internal:
//            OnListEntriesResponse

public class ak
    implements android.os.Parcelable.Creator
{

    public ak()
    {
    }

    static void a(OnListEntriesResponse onlistentriesresponse, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, onlistentriesresponse.xM);
        b.a(parcel, 2, onlistentriesresponse.JA, i, false);
        b.a(parcel, 3, onlistentriesresponse.IP);
        b.G(parcel, j);
    }

    public OnListEntriesResponse al(Parcel parcel)
    {
        boolean flag;
        int i;
        DataHolder dataholder;
        int j;
        flag = false;
        i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        dataholder = null;
        j = 0;
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
        boolean flag1;
        DataHolder dataholder1;
        int l;
        com.google.android.gms.common.internal.safeparcel.a.b(parcel, k);
        flag1 = flag;
        dataholder1 = dataholder;
        l = j;
_L7:
        j = l;
        dataholder = dataholder1;
        flag = flag1;
        if (true) goto _L6; else goto _L5
_L5:
        int i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
        boolean flag2 = flag;
        dataholder1 = dataholder;
        l = i1;
        flag1 = flag2;
          goto _L7
_L3:
        DataHolder dataholder2 = (DataHolder)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, DataHolder.CREATOR);
        l = j;
        flag1 = flag;
        dataholder1 = dataholder2;
          goto _L7
        flag1 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k);
        dataholder1 = dataholder;
        l = j;
          goto _L7
        if (parcel.dataPosition() != i)
        {
            throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
        } else
        {
            return new OnListEntriesResponse(j, dataholder, flag);
        }
    }

    public OnListEntriesResponse[] bh(int i)
    {
        return new OnListEntriesResponse[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return al(parcel);
    }

    public Object[] newArray(int i)
    {
        return bh(i);
    }
}
