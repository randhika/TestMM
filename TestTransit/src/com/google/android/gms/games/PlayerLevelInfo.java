// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.hm;

// Referenced classes of package com.google.android.gms.games:
//            PlayerLevelInfoCreator, PlayerLevel

public final class PlayerLevelInfo
    implements SafeParcelable
{

    public static final PlayerLevelInfoCreator CREATOR = new PlayerLevelInfoCreator();
    private final long Nc;
    private final long Nd;
    private final PlayerLevel Ne;
    private final PlayerLevel Nf;
    private final int xM;

    PlayerLevelInfo(int i, long l, long l1, PlayerLevel playerlevel, PlayerLevel playerlevel1)
    {
        boolean flag;
        if (l != -1L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        hm.A(flag);
        hm.f(playerlevel);
        hm.f(playerlevel1);
        xM = i;
        Nc = l;
        Nd = l1;
        Ne = playerlevel;
        Nf = playerlevel1;
    }

    public PlayerLevelInfo(long l, long l1, PlayerLevel playerlevel, PlayerLevel playerlevel1)
    {
        this(1, l, l1, playerlevel, playerlevel1);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if (!(obj instanceof PlayerLevelInfo))
        {
            flag = false;
        } else
        if (obj != this)
        {
            PlayerLevelInfo playerlevelinfo = (PlayerLevelInfo)obj;
            if (!hk.equal(Long.valueOf(Nc), Long.valueOf(playerlevelinfo.Nc)) || !hk.equal(Long.valueOf(Nd), Long.valueOf(playerlevelinfo.Nd)) || !hk.equal(Ne, playerlevelinfo.Ne) || !hk.equal(Nf, playerlevelinfo.Nf))
            {
                return false;
            }
        }
        return flag;
    }

    public PlayerLevel getCurrentLevel()
    {
        return Ne;
    }

    public long getCurrentXpTotal()
    {
        return Nc;
    }

    public long getLastLevelUpTimestamp()
    {
        return Nd;
    }

    public PlayerLevel getNextLevel()
    {
        return Nf;
    }

    public int getVersionCode()
    {
        return xM;
    }

    public int hashCode()
    {
        Object aobj[] = new Object[4];
        aobj[0] = Long.valueOf(Nc);
        aobj[1] = Long.valueOf(Nd);
        aobj[2] = Ne;
        aobj[3] = Nf;
        return hk.hashCode(aobj);
    }

    public boolean isMaxLevel()
    {
        return Ne.equals(Nf);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        PlayerLevelInfoCreator.a(this, parcel, i);
    }

}
