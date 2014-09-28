// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.quest;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.games.quest:
//            MilestoneEntity

public class MilestoneEntityCreator
    implements android.os.Parcelable.Creator
{

    public static final int CONTENT_DESCRIPTION;

    public MilestoneEntityCreator()
    {
    }

    static void a(MilestoneEntity milestoneentity, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.a(parcel, 1, milestoneentity.getMilestoneId(), false);
        b.c(parcel, 1000, milestoneentity.getVersionCode());
        b.a(parcel, 2, milestoneentity.getCurrentProgress());
        b.a(parcel, 3, milestoneentity.getTargetProgress());
        b.a(parcel, 4, milestoneentity.getCompletionRewardData(), false);
        b.c(parcel, 5, milestoneentity.getState());
        b.a(parcel, 6, milestoneentity.getEventId(), false);
        b.G(parcel, j);
    }

    public MilestoneEntity createFromParcel(Parcel parcel)
    {
        long l = 0L;
        int i = 0;
        String s = null;
        int j = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        byte abyte0[] = null;
        long l1 = l;
        String s1 = null;
        int k = 0;
        do
        {
            if (parcel.dataPosition() < j)
            {
                int i1 = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(i1))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, i1);
                    break;

                case 1: // '\001'
                    s1 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, i1);
                    break;

                case 1000: 
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;

                case 2: // '\002'
                    l1 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, i1);
                    break;

                case 3: // '\003'
                    l = com.google.android.gms.common.internal.safeparcel.a.i(parcel, i1);
                    break;

                case 4: // '\004'
                    abyte0 = com.google.android.gms.common.internal.safeparcel.a.r(parcel, i1);
                    break;

                case 5: // '\005'
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;

                case 6: // '\006'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, i1);
                    break;
                }
            } else
            if (parcel.dataPosition() != j)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(j).toString(), parcel);
            } else
            {
                return new MilestoneEntity(k, s1, l1, l, abyte0, i, s);
            }
        } while (true);
    }

    public volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public MilestoneEntity[] newArray(int i)
    {
        return new MilestoneEntity[i];
    }

    public volatile Object[] newArray(int i)
    {
        return newArray(i);
    }
}
