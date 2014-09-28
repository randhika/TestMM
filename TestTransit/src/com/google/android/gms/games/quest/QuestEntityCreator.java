// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.quest;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.GameEntity;

// Referenced classes of package com.google.android.gms.games.quest:
//            QuestEntity, MilestoneEntity

public class QuestEntityCreator
    implements android.os.Parcelable.Creator
{

    public static final int CONTENT_DESCRIPTION;

    public QuestEntityCreator()
    {
    }

    static void a(QuestEntity questentity, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.a(parcel, 1, questentity.getGame(), i, false);
        b.a(parcel, 2, questentity.getQuestId(), false);
        b.a(parcel, 3, questentity.getAcceptedTimestamp());
        b.a(parcel, 4, questentity.getBannerImageUri(), i, false);
        b.a(parcel, 5, questentity.getBannerImageUrl(), false);
        b.a(parcel, 6, questentity.getDescription(), false);
        b.a(parcel, 7, questentity.getEndTimestamp());
        b.a(parcel, 8, questentity.getLastUpdatedTimestamp());
        b.a(parcel, 9, questentity.getIconImageUri(), i, false);
        b.a(parcel, 10, questentity.getIconImageUrl(), false);
        b.a(parcel, 12, questentity.getName(), false);
        b.a(parcel, 13, questentity.iK());
        b.a(parcel, 14, questentity.getStartTimestamp());
        b.c(parcel, 15, questentity.getState());
        b.b(parcel, 17, questentity.iJ(), false);
        b.c(parcel, 16, questentity.getType());
        b.c(parcel, 1000, questentity.getVersionCode());
        b.G(parcel, j);
    }

    public QuestEntity createFromParcel(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 0;
        GameEntity gameentity = null;
        String s = null;
        long l = 0L;
        Uri uri = null;
        String s1 = null;
        String s2 = null;
        long l1 = 0L;
        long l2 = 0L;
        Uri uri1 = null;
        String s3 = null;
        String s4 = null;
        long l3 = 0L;
        long l4 = 0L;
        int k = 0;
        int i1 = 0;
        java.util.ArrayList arraylist = null;
        do
        {
            if (parcel.dataPosition() < i)
            {
                int j1 = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
                switch (com.google.android.gms.common.internal.safeparcel.a.ar(j1))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, j1);
                    break;

                case 1: // '\001'
                    gameentity = (GameEntity)com.google.android.gms.common.internal.safeparcel.a.a(parcel, j1, GameEntity.CREATOR);
                    break;

                case 2: // '\002'
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 3: // '\003'
                    l = com.google.android.gms.common.internal.safeparcel.a.i(parcel, j1);
                    break;

                case 4: // '\004'
                    uri = (Uri)com.google.android.gms.common.internal.safeparcel.a.a(parcel, j1, Uri.CREATOR);
                    break;

                case 5: // '\005'
                    s1 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 6: // '\006'
                    s2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 7: // '\007'
                    l1 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, j1);
                    break;

                case 8: // '\b'
                    l2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, j1);
                    break;

                case 9: // '\t'
                    uri1 = (Uri)com.google.android.gms.common.internal.safeparcel.a.a(parcel, j1, Uri.CREATOR);
                    break;

                case 10: // '\n'
                    s3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 12: // '\f'
                    s4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 13: // '\r'
                    l3 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, j1);
                    break;

                case 14: // '\016'
                    l4 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, j1);
                    break;

                case 15: // '\017'
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, j1);
                    break;

                case 17: // '\021'
                    arraylist = com.google.android.gms.common.internal.safeparcel.a.c(parcel, j1, MilestoneEntity.CREATOR);
                    break;

                case 16: // '\020'
                    i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, j1);
                    break;

                case 1000: 
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, j1);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new QuestEntity(j, gameentity, s, l, uri, s1, s2, l1, l2, uri1, s3, s4, l3, l4, k, i1, arraylist);
            }
        } while (true);
    }

    public volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public QuestEntity[] newArray(int i)
    {
        return new QuestEntity[i];
    }

    public volatile Object[] newArray(int i)
    {
        return newArray(i);
    }
}
