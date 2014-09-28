// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.ik;
import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.games.leaderboard:
//            Leaderboard, LeaderboardVariant, LeaderboardVariantEntity

public final class LeaderboardEntity
    implements Leaderboard
{

    private final String Ln;
    private final String MC;
    private final Uri Mr;
    private final String Sp;
    private final int Sq;
    private final ArrayList Sr;
    private final Game Ss;

    public LeaderboardEntity(Leaderboard leaderboard)
    {
        Sp = leaderboard.getLeaderboardId();
        Ln = leaderboard.getDisplayName();
        Mr = leaderboard.getIconImageUri();
        MC = leaderboard.getIconImageUrl();
        Sq = leaderboard.getScoreOrder();
        Game game = leaderboard.getGame();
        Object obj;
        ArrayList arraylist;
        int i;
        if (game == null)
        {
            obj = null;
        } else
        {
            obj = new GameEntity(game);
        }
        Ss = ((Game) (obj));
        arraylist = leaderboard.getVariants();
        i = arraylist.size();
        Sr = new ArrayList(i);
        for (int j = 0; j < i; j++)
        {
            Sr.add((LeaderboardVariantEntity)(LeaderboardVariantEntity)((LeaderboardVariant)arraylist.get(j)).freeze());
        }

    }

    static int a(Leaderboard leaderboard)
    {
        Object aobj[] = new Object[5];
        aobj[0] = leaderboard.getLeaderboardId();
        aobj[1] = leaderboard.getDisplayName();
        aobj[2] = leaderboard.getIconImageUri();
        aobj[3] = Integer.valueOf(leaderboard.getScoreOrder());
        aobj[4] = leaderboard.getVariants();
        return hk.hashCode(aobj);
    }

    static boolean a(Leaderboard leaderboard, Object obj)
    {
        boolean flag = true;
        if (!(obj instanceof Leaderboard))
        {
            flag = false;
        } else
        if (leaderboard != obj)
        {
            Leaderboard leaderboard1 = (Leaderboard)obj;
            if (!hk.equal(leaderboard1.getLeaderboardId(), leaderboard.getLeaderboardId()) || !hk.equal(leaderboard1.getDisplayName(), leaderboard.getDisplayName()) || !hk.equal(leaderboard1.getIconImageUri(), leaderboard.getIconImageUri()) || !hk.equal(Integer.valueOf(leaderboard1.getScoreOrder()), Integer.valueOf(leaderboard.getScoreOrder())) || !hk.equal(leaderboard1.getVariants(), leaderboard.getVariants()))
            {
                return false;
            }
        }
        return flag;
    }

    static String b(Leaderboard leaderboard)
    {
        return hk.e(leaderboard).a("LeaderboardId", leaderboard.getLeaderboardId()).a("DisplayName", leaderboard.getDisplayName()).a("IconImageUri", leaderboard.getIconImageUri()).a("IconImageUrl", leaderboard.getIconImageUrl()).a("ScoreOrder", Integer.valueOf(leaderboard.getScoreOrder())).a("Variants", leaderboard.getVariants()).toString();
    }

    public boolean equals(Object obj)
    {
        return a(this, obj);
    }

    public Object freeze()
    {
        return iz();
    }

    public String getDisplayName()
    {
        return Ln;
    }

    public void getDisplayName(CharArrayBuffer chararraybuffer)
    {
        ik.b(Ln, chararraybuffer);
    }

    public Game getGame()
    {
        return Ss;
    }

    public Uri getIconImageUri()
    {
        return Mr;
    }

    public String getIconImageUrl()
    {
        return MC;
    }

    public String getLeaderboardId()
    {
        return Sp;
    }

    public int getScoreOrder()
    {
        return Sq;
    }

    public ArrayList getVariants()
    {
        return new ArrayList(Sr);
    }

    public int hashCode()
    {
        return a(this);
    }

    public boolean isDataValid()
    {
        return true;
    }

    public Leaderboard iz()
    {
        return this;
    }

    public String toString()
    {
        return b(this);
    }
}
