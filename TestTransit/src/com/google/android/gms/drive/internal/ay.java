// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

// Referenced classes of package com.google.android.gms.drive.internal:
//            UpdateMetadataRequest

public class ay
    implements android.os.Parcelable.Creator
{

    public ay()
    {
    }

    static void a(UpdateMetadataRequest updatemetadatarequest, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, updatemetadatarequest.xM);
        b.a(parcel, 2, updatemetadatarequest.Iu, i, false);
        b.a(parcel, 3, updatemetadatarequest.Iv, i, false);
        b.G(parcel, j);
    }

    public UpdateMetadataRequest ay(Parcel parcel)
    {
        MetadataBundle metadatabundle;
        int i;
        int j;
        DriveId driveid;
        metadatabundle = null;
        i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        j = 0;
        driveid = null;
_L6:
        int k;
        if (parcel.dataPosition() >= i)
        {
            break MISSING_BLOCK_LABEL_170;
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
        MetadataBundle metadatabundle1;
        DriveId driveid1;
        int l;
        com.google.android.gms.common.internal.safeparcel.a.b(parcel, k);
        metadatabundle1 = metadatabundle;
        driveid1 = driveid;
        l = j;
_L7:
        j = l;
        driveid = driveid1;
        metadatabundle = metadatabundle1;
        if (true) goto _L6; else goto _L5
_L5:
        int i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
        MetadataBundle metadatabundle2 = metadatabundle;
        driveid1 = driveid;
        l = i1;
        metadatabundle1 = metadatabundle2;
          goto _L7
_L3:
        DriveId driveid2 = (DriveId)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, DriveId.CREATOR);
        l = j;
        metadatabundle1 = metadatabundle;
        driveid1 = driveid2;
          goto _L7
        metadatabundle1 = (MetadataBundle)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, MetadataBundle.CREATOR);
        driveid1 = driveid;
        l = j;
          goto _L7
        if (parcel.dataPosition() != i)
        {
            throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
        } else
        {
            return new UpdateMetadataRequest(j, driveid, metadatabundle);
        }
    }

    public UpdateMetadataRequest[] bu(int i)
    {
        return new UpdateMetadataRequest[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return ay(parcel);
    }

    public Object[] newArray(int i)
    {
        return bu(i);
    }
}
