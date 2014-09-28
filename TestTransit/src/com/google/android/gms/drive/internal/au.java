// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

// Referenced classes of package com.google.android.gms.drive.internal:
//            RemoveEventListenerRequest

public class au
    implements android.os.Parcelable.Creator
{

    public au()
    {
    }

    static void a(RemoveEventListenerRequest removeeventlistenerrequest, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, removeeventlistenerrequest.xM);
        b.a(parcel, 2, removeeventlistenerrequest.Hz, i, false);
        b.c(parcel, 3, removeeventlistenerrequest.Iq);
        b.G(parcel, j);
    }

    public RemoveEventListenerRequest av(Parcel parcel)
    {
        int i;
        int j;
        DriveId driveid;
        int k;
        i = 0;
        j = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        driveid = null;
        k = 0;
_L6:
        int l;
        if (parcel.dataPosition() >= j)
        {
            break MISSING_BLOCK_LABEL_164;
        }
        l = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
        com.google.android.gms.common.internal.safeparcel.a.ar(l);
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
        int i1;
        DriveId driveid1;
        int j1;
        com.google.android.gms.common.internal.safeparcel.a.b(parcel, l);
        i1 = i;
        driveid1 = driveid;
        j1 = k;
_L7:
        k = j1;
        driveid = driveid1;
        i = i1;
        if (true) goto _L6; else goto _L5
_L5:
        int k1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
        int l1 = i;
        driveid1 = driveid;
        j1 = k1;
        i1 = l1;
          goto _L7
_L3:
        DriveId driveid2 = (DriveId)com.google.android.gms.common.internal.safeparcel.a.a(parcel, l, DriveId.CREATOR);
        j1 = k;
        i1 = i;
        driveid1 = driveid2;
          goto _L7
        i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
        driveid1 = driveid;
        j1 = k;
          goto _L7
        if (parcel.dataPosition() != j)
        {
            throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(j).toString(), parcel);
        } else
        {
            return new RemoveEventListenerRequest(k, driveid, i);
        }
    }

    public RemoveEventListenerRequest[] br(int i)
    {
        return new RemoveEventListenerRequest[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return av(parcel);
    }

    public Object[] newArray(int i)
    {
        return br(i);
    }
}
