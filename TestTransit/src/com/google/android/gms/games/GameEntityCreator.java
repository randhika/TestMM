// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.games:
//            GameEntity

public class GameEntityCreator
    implements android.os.Parcelable.Creator
{

    public GameEntityCreator()
    {
    }

    static void a(GameEntity gameentity, Parcel parcel, int i)
    {
        int j = b.C(parcel);
        b.a(parcel, 1, gameentity.getApplicationId(), false);
        b.a(parcel, 2, gameentity.getDisplayName(), false);
        b.a(parcel, 3, gameentity.getPrimaryCategory(), false);
        b.a(parcel, 4, gameentity.getSecondaryCategory(), false);
        b.a(parcel, 5, gameentity.getDescription(), false);
        b.a(parcel, 6, gameentity.getDeveloperName(), false);
        b.a(parcel, 7, gameentity.getIconImageUri(), i, false);
        b.a(parcel, 8, gameentity.getHiResImageUri(), i, false);
        b.a(parcel, 9, gameentity.getFeaturedImageUri(), i, false);
        b.a(parcel, 10, gameentity.gM());
        b.a(parcel, 11, gameentity.gO());
        b.a(parcel, 12, gameentity.gP(), false);
        b.c(parcel, 13, gameentity.gQ());
        b.c(parcel, 14, gameentity.getAchievementTotalCount());
        b.c(parcel, 15, gameentity.getLeaderboardCount());
        b.a(parcel, 17, gameentity.isTurnBasedMultiplayerEnabled());
        b.a(parcel, 16, gameentity.isRealTimeMultiplayerEnabled());
        b.c(parcel, 1000, gameentity.getVersionCode());
        b.a(parcel, 19, gameentity.getHiResImageUrl(), false);
        b.a(parcel, 18, gameentity.getIconImageUrl(), false);
        b.a(parcel, 21, gameentity.isMuted());
        b.a(parcel, 20, gameentity.getFeaturedImageUrl(), false);
        b.a(parcel, 23, gameentity.areSnapshotsEnabled());
        b.a(parcel, 22, gameentity.gN());
        b.G(parcel, j);
    }

    public GameEntity bd(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int j = 0;
        String s = null;
        String s1 = null;
        String s2 = null;
        String s3 = null;
        String s4 = null;
        String s5 = null;
        Uri uri = null;
        Uri uri1 = null;
        Uri uri2 = null;
        boolean flag = false;
        boolean flag1 = false;
        String s6 = null;
        int k = 0;
        int l = 0;
        int i1 = 0;
        boolean flag2 = false;
        boolean flag3 = false;
        String s7 = null;
        String s8 = null;
        String s9 = null;
        boolean flag4 = false;
        boolean flag5 = false;
        boolean flag6 = false;
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
                    s = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 2: // '\002'
                    s1 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 3: // '\003'
                    s2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 4: // '\004'
                    s3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 5: // '\005'
                    s4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 6: // '\006'
                    s5 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 7: // '\007'
                    uri = (Uri)com.google.android.gms.common.internal.safeparcel.a.a(parcel, j1, Uri.CREATOR);
                    break;

                case 8: // '\b'
                    uri1 = (Uri)com.google.android.gms.common.internal.safeparcel.a.a(parcel, j1, Uri.CREATOR);
                    break;

                case 9: // '\t'
                    uri2 = (Uri)com.google.android.gms.common.internal.safeparcel.a.a(parcel, j1, Uri.CREATOR);
                    break;

                case 10: // '\n'
                    flag = com.google.android.gms.common.internal.safeparcel.a.c(parcel, j1);
                    break;

                case 11: // '\013'
                    flag1 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, j1);
                    break;

                case 12: // '\f'
                    s6 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 13: // '\r'
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, j1);
                    break;

                case 14: // '\016'
                    l = com.google.android.gms.common.internal.safeparcel.a.g(parcel, j1);
                    break;

                case 15: // '\017'
                    i1 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, j1);
                    break;

                case 17: // '\021'
                    flag3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, j1);
                    break;

                case 16: // '\020'
                    flag2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, j1);
                    break;

                case 1000: 
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, j1);
                    break;

                case 19: // '\023'
                    s8 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 18: // '\022'
                    s7 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 21: // '\025'
                    flag4 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, j1);
                    break;

                case 20: // '\024'
                    s9 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, j1);
                    break;

                case 23: // '\027'
                    flag6 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, j1);
                    break;

                case 22: // '\026'
                    flag5 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, j1);
                    break;
                }
            } else
            if (parcel.dataPosition() != i)
            {
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            } else
            {
                return new GameEntity(j, s, s1, s2, s3, s4, s5, uri, uri1, uri2, flag, flag1, s6, k, l, i1, flag2, flag3, s7, s8, s9, flag4, flag5, flag6);
            }
        } while (true);
    }

    public GameEntity[] cb(int i)
    {
        return new GameEntity[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return bd(parcel);
    }

    public Object[] newArray(int i)
    {
        return cb(i);
    }
}
