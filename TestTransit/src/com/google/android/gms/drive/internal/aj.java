// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.FileConflictEvent;

// Referenced classes of package com.google.android.gms.drive.internal:
//            OnEventResponse

public class aj
    implements android.os.Parcelable.Creator
{

    public aj()
    {
    }

    static void a(OnEventResponse oneventresponse, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, oneventresponse.xM);
        b.c(parcel, 2, oneventresponse.Iq);
        b.a(parcel, 3, oneventresponse.Jy, i, false);
        b.a(parcel, 4, oneventresponse.Jz, i, false);
        b.G(parcel, j);
    }

    public OnEventResponse ak(Parcel parcel)
    {
        FileConflictEvent fileconflictevent;
        int i;
        int j;
        ChangeEvent changeevent;
        int k;
        fileconflictevent = null;
        i = 0;
        j = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        changeevent = null;
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
    //                   3 164
    //                   4 199;
           goto _L1 _L2 _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_199;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        FileConflictEvent fileconflictevent1;
        ChangeEvent changeevent1;
        int i1;
        int j1;
        com.google.android.gms.common.internal.safeparcel.a.b(parcel, l);
        fileconflictevent1 = fileconflictevent;
        changeevent1 = changeevent;
        i1 = i;
        j1 = k;
_L8:
        k = j1;
        i = i1;
        changeevent = changeevent1;
        fileconflictevent = fileconflictevent1;
        if (true) goto _L7; else goto _L6
_L6:
        int l1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
        FileConflictEvent fileconflictevent3 = fileconflictevent;
        changeevent1 = changeevent;
        i1 = i;
        j1 = l1;
        fileconflictevent1 = fileconflictevent3;
          goto _L8
_L3:
        int k1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
        j1 = k;
        ChangeEvent changeevent3 = changeevent;
        i1 = k1;
        fileconflictevent1 = fileconflictevent;
        changeevent1 = changeevent3;
          goto _L8
_L4:
        ChangeEvent changeevent2 = (ChangeEvent)com.google.android.gms.common.internal.safeparcel.a.a(parcel, l, ChangeEvent.CREATOR);
        i1 = i;
        j1 = k;
        FileConflictEvent fileconflictevent2 = fileconflictevent;
        changeevent1 = changeevent2;
        fileconflictevent1 = fileconflictevent2;
          goto _L8
        fileconflictevent1 = (FileConflictEvent)com.google.android.gms.common.internal.safeparcel.a.a(parcel, l, FileConflictEvent.CREATOR);
        changeevent1 = changeevent;
        i1 = i;
        j1 = k;
          goto _L8
        if (parcel.dataPosition() != j)
        {
            throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(j).toString(), parcel);
        } else
        {
            return new OnEventResponse(k, i, changeevent, fileconflictevent);
        }
    }

    public OnEventResponse[] bg(int i)
    {
        return new OnEventResponse[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return ak(parcel);
    }

    public Object[] newArray(int i)
    {
        return bg(i);
    }
}
