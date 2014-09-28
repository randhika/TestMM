// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.drive.realtime.internal.event:
//            ParcelableEvent, TextInsertedDetails, TextDeletedDetails, ValuesAddedDetails, 
//            ValuesRemovedDetails, ValuesSetDetails, ValueChangedDetails, ReferenceShiftedDetails

public class a
    implements android.os.Parcelable.Creator
{

    public a()
    {
    }

    static void a(ParcelableEvent parcelableevent, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.c(parcel, 1, parcelableevent.xM);
        b.a(parcel, 2, parcelableevent.rR, false);
        b.a(parcel, 3, parcelableevent.Lm, false);
        b.a(parcel, 4, parcelableevent.Ls);
        b.a(parcel, 5, parcelableevent.Lq, false);
        b.a(parcel, 6, parcelableevent.Lt, false);
        b.a(parcel, 7, parcelableevent.Lu, i, false);
        b.a(parcel, 8, parcelableevent.Lv, i, false);
        b.a(parcel, 9, parcelableevent.Lw, i, false);
        b.a(parcel, 10, parcelableevent.Lx, i, false);
        b.a(parcel, 11, parcelableevent.Ly, i, false);
        b.a(parcel, 12, parcelableevent.Lz, i, false);
        b.a(parcel, 13, parcelableevent.LA, i, false);
        b.G(parcel, j);
    }

    public ParcelableEvent aT(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 0;
        String s = null;
        String s1 = null;
        boolean flag = false;
        String s2 = null;
        String s3 = null;
        TextInsertedDetails textinserteddetails = null;
        TextDeletedDetails textdeleteddetails = null;
        ValuesAddedDetails valuesaddeddetails = null;
        ValuesRemovedDetails valuesremoveddetails = null;
        ValuesSetDetails valuessetdetails = null;
        ValueChangedDetails valuechangeddetails = null;
        ReferenceShiftedDetails referenceshifteddetails = null;
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
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 3: // '\003'
                    s1 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 4: // '\004'
                    flag = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k);
                    break;

                case 5: // '\005'
                    s2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 6: // '\006'
                    s3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, k);
                    break;

                case 7: // '\007'
                    textinserteddetails = (TextInsertedDetails)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, TextInsertedDetails.CREATOR);
                    break;

                case 8: // '\b'
                    textdeleteddetails = (TextDeletedDetails)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, TextDeletedDetails.CREATOR);
                    break;

                case 9: // '\t'
                    valuesaddeddetails = (ValuesAddedDetails)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, ValuesAddedDetails.CREATOR);
                    break;

                case 10: // '\n'
                    valuesremoveddetails = (ValuesRemovedDetails)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, ValuesRemovedDetails.CREATOR);
                    break;

                case 11: // '\013'
                    valuessetdetails = (ValuesSetDetails)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, ValuesSetDetails.CREATOR);
                    break;

                case 12: // '\f'
                    valuechangeddetails = (ValueChangedDetails)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, ValueChangedDetails.CREATOR);
                    break;

                case 13: // '\r'
                    referenceshifteddetails = (ReferenceShiftedDetails)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, ReferenceShiftedDetails.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new ParcelableEvent(j, s, s1, flag, s2, s3, textinserteddetails, textdeleteddetails, valuesaddeddetails, valuesremoveddetails, valuessetdetails, valuechangeddetails, referenceshifteddetails);
            }
        } while (true);
    }

    public ParcelableEvent[] bQ(int i)
    {
        return new ParcelableEvent[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return aT(parcel);
    }

    public Object[] newArray(int i)
    {
        return bQ(i);
    }
}
