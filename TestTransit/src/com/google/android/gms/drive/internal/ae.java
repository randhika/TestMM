// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

// Referenced classes of package com.google.android.gms.drive.internal:
//            LoadRealtimeRequest

public class ae
    implements android.os.Parcelable.Creator
{

    public ae()
    {
    }

    static void a(LoadRealtimeRequest loadrealtimerequest, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, loadrealtimerequest.xM);
        b.a(parcel, 2, loadrealtimerequest.Hz, i, false);
        b.a(parcel, 3, loadrealtimerequest.Js);
        b.G(parcel, j);
    }

    public LoadRealtimeRequest ag(Parcel parcel)
    {
        boolean flag;
        int i;
        DriveId driveid;
        int j;
        flag = false;
        i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        driveid = null;
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
        DriveId driveid1;
        int l;
        com.google.android.gms.common.internal.safeparcel.a.b(parcel, k);
        flag1 = flag;
        driveid1 = driveid;
        l = j;
_L7:
        j = l;
        driveid = driveid1;
        flag = flag1;
        if (true) goto _L6; else goto _L5
_L5:
        int i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
        boolean flag2 = flag;
        driveid1 = driveid;
        l = i1;
        flag1 = flag2;
          goto _L7
_L3:
        DriveId driveid2 = (DriveId)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, DriveId.CREATOR);
        l = j;
        flag1 = flag;
        driveid1 = driveid2;
          goto _L7
        flag1 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k);
        driveid1 = driveid;
        l = j;
          goto _L7
        if (parcel.dataPosition() != i)
        {
            throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
        } else
        {
            return new LoadRealtimeRequest(j, driveid, flag);
        }
    }

    public LoadRealtimeRequest[] bc(int i)
    {
        return new LoadRealtimeRequest[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return ag(parcel);
    }

    public Object[] newArray(int i)
    {
        return bc(i);
    }
}
