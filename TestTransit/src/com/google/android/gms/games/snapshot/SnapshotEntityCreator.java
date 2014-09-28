// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.Contents;

// Referenced classes of package com.google.android.gms.games.snapshot:
//            SnapshotEntity, SnapshotMetadataEntity

public class SnapshotEntityCreator
    implements android.os.Parcelable.Creator
{

    public static final int CONTENT_DESCRIPTION;

    public SnapshotEntityCreator()
    {
    }

    static void a(SnapshotEntity snapshotentity, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.a(parcel, 1, snapshotentity.getMetadata(), i, false);
        b.c(parcel, 1000, snapshotentity.getVersionCode());
        b.a(parcel, 2, snapshotentity.getContents(), i, false);
        b.G(parcel, j);
    }

    public SnapshotEntity createFromParcel(Parcel parcel)
    {
        Contents contents;
        int i;
        int j;
        Object obj;
        contents = null;
        i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        j = 0;
        obj = null;
_L6:
        int k;
        if (parcel.dataPosition() >= i)
        {
            break MISSING_BLOCK_LABEL_178;
        }
        k = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
        com.google.android.gms.common.internal.safeparcel.a.ar(k);
        JVM INSTR lookupswitch 3: default 68
    //                   1: 99
    //                   2: 153
    //                   1000: 127;
           goto _L1 _L2 _L3 _L4
_L3:
        break MISSING_BLOCK_LABEL_153;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        Contents contents1;
        Object obj1;
        int l;
        com.google.android.gms.common.internal.safeparcel.a.b(parcel, k);
        contents1 = contents;
        obj1 = obj;
        l = j;
_L7:
        j = l;
        obj = obj1;
        contents = contents1;
        if (true) goto _L6; else goto _L5
_L5:
        SnapshotMetadataEntity snapshotmetadataentity = (SnapshotMetadataEntity)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, SnapshotMetadataEntity.CREATOR);
        l = j;
        contents1 = contents;
        obj1 = snapshotmetadataentity;
          goto _L7
_L4:
        int i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
        Contents contents2 = contents;
        obj1 = obj;
        l = i1;
        contents1 = contents2;
          goto _L7
        contents1 = (Contents)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, Contents.CREATOR);
        obj1 = obj;
        l = j;
          goto _L7
        if (parcel.dataPosition() != i)
        {
            throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
        } else
        {
            return new SnapshotEntity(j, ((SnapshotMetadata) (obj)), contents);
        }
    }

    public volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public SnapshotEntity[] newArray(int i)
    {
        return new SnapshotEntity[i];
    }

    public volatile Object[] newArray(int i)
    {
        return newArray(i);
    }
}
