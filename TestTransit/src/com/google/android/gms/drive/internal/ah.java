// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.drive.internal:
//            OnDownloadProgressResponse

public class ah
    implements android.os.Parcelable.Creator
{

    public ah()
    {
    }

    static void a(OnDownloadProgressResponse ondownloadprogressresponse, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, ondownloadprogressresponse.xM);
        b.a(parcel, 2, ondownloadprogressresponse.Jw);
        b.a(parcel, 3, ondownloadprogressresponse.Jx);
        b.G(parcel, j);
    }

    public OnDownloadProgressResponse ai(Parcel parcel)
    {
        long l = 0L;
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 0;
        long l1 = l;
        do
        {
            if (parcel.dataPosition() < i)
            {
                int k = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(k))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, k);
                    break;

                case 1: // '\001'
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
                    break;

                case 2: // '\002'
                    l1 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, k);
                    break;

                case 3: // '\003'
                    l = com.google.android.gms.common.internal.safeparcel.a.i(parcel, k);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new OnDownloadProgressResponse(j, l1, l);
            }
        } while (true);
    }

    public OnDownloadProgressResponse[] be(int i)
    {
        return new OnDownloadProgressResponse[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return ai(parcel);
    }

    public Object[] newArray(int i)
    {
        return be(i);
    }
}
