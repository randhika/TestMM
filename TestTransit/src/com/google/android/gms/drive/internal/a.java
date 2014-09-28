// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import android.app.PendingIntent;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

// Referenced classes of package com.google.android.gms.drive.internal:
//            AddEventListenerRequest

public class a
    implements android.os.Parcelable.Creator
{

    public a()
    {
    }

    static void a(AddEventListenerRequest addeventlistenerrequest, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, addeventlistenerrequest.xM);
        b.a(parcel, 2, addeventlistenerrequest.Hz, i, false);
        b.c(parcel, 3, addeventlistenerrequest.Iq);
        b.a(parcel, 4, addeventlistenerrequest.Ir, i, false);
        b.G(parcel, j);
    }

    public AddEventListenerRequest R(Parcel parcel)
    {
        PendingIntent pendingintent;
        int i;
        int j;
        DriveId driveid;
        int k;
        pendingintent = null;
        i = 0;
        j = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        driveid = null;
        k = 0;
_L7:
        int l;
        if (parcel.dataPosition() >= j)
        {
            break MISSING_BLOCK_LABEL_227;
        }
        l = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
        com.google.android.gms.common.internal.safeparcel.a.ar(l);
        JVM INSTR tableswitch 1 4: default 68
    //                   1 105
    //                   2 134
    //                   3 169
    //                   4 199;
           goto _L1 _L2 _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_199;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        PendingIntent pendingintent1;
        int i1;
        DriveId driveid1;
        int j1;
        com.google.android.gms.common.internal.safeparcel.a.b(parcel, l);
        pendingintent1 = pendingintent;
        i1 = i;
        driveid1 = driveid;
        j1 = k;
_L8:
        k = j1;
        driveid = driveid1;
        i = i1;
        pendingintent = pendingintent1;
        if (true) goto _L7; else goto _L6
_L6:
        int i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
        PendingIntent pendingintent3 = pendingintent;
        i1 = i;
        driveid1 = driveid;
        j1 = i2;
        pendingintent1 = pendingintent3;
          goto _L8
_L3:
        DriveId driveid2 = (DriveId)com.google.android.gms.common.internal.safeparcel.a.a(parcel, l, DriveId.CREATOR);
        j1 = k;
        int l1 = i;
        driveid1 = driveid2;
        pendingintent1 = pendingintent;
        i1 = l1;
          goto _L8
_L4:
        int k1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
        driveid1 = driveid;
        j1 = k;
        PendingIntent pendingintent2 = pendingintent;
        i1 = k1;
        pendingintent1 = pendingintent2;
          goto _L8
        pendingintent1 = (PendingIntent)com.google.android.gms.common.internal.safeparcel.a.a(parcel, l, PendingIntent.CREATOR);
        i1 = i;
        driveid1 = driveid;
        j1 = k;
          goto _L8
        if (parcel.dataPosition() != j)
        {
            throw new com.google.android.gms.common.internal.safeparcel.a((new StringBuilder()).append("Overread allowed size end=").append(j).toString(), parcel);
        } else
        {
            return new AddEventListenerRequest(k, driveid, i, pendingintent);
        }
    }

    public AddEventListenerRequest[] aM(int i)
    {
        return new AddEventListenerRequest[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return R(parcel);
    }

    public Object[] newArray(int i)
    {
        return aM(i);
    }
}
