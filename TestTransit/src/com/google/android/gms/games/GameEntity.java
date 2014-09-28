// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.ik;

// Referenced classes of package com.google.android.gms.games:
//            Game, GameEntityCreator

public final class GameEntity extends GamesDowngradeableSafeParcel
    implements Game
{
    static final class GameEntityCreatorCompat extends GameEntityCreator
    {

        public GameEntity bd(Parcel parcel)
        {
            if (GameEntity.b(GameEntity.gR()) || GameEntity.aQ(com/google/android/gms/games/GameEntity.getCanonicalName()))
            {
                return super.bd(parcel);
            }
            String s = parcel.readString();
            String s1 = parcel.readString();
            String s2 = parcel.readString();
            String s3 = parcel.readString();
            String s4 = parcel.readString();
            String s5 = parcel.readString();
            String s6 = parcel.readString();
            Uri uri;
            String s7;
            Uri uri1;
            String s8;
            Uri uri2;
            boolean flag;
            boolean flag1;
            if (s6 == null)
            {
                uri = null;
            } else
            {
                uri = Uri.parse(s6);
            }
            s7 = parcel.readString();
            if (s7 == null)
            {
                uri1 = null;
            } else
            {
                uri1 = Uri.parse(s7);
            }
            s8 = parcel.readString();
            if (s8 == null)
            {
                uri2 = null;
            } else
            {
                uri2 = Uri.parse(s8);
            }
            if (parcel.readInt() > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (parcel.readInt() > 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            return new GameEntity(4, s, s1, s2, s3, s4, s5, uri, uri1, uri2, flag, flag1, parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), false, false, null, null, null, false, false, false);
        }

        public Object createFromParcel(Parcel parcel)
        {
            return bd(parcel);
        }

        GameEntityCreatorCompat()
        {
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new GameEntityCreatorCompat();
    private final String Ln;
    private final boolean MA;
    private final boolean MB;
    private final String MC;
    private final String MD;
    private final String ME;
    private final boolean MF;
    private final boolean MG;
    private final boolean MH;
    private final String Mn;
    private final String Mo;
    private final String Mp;
    private final String Mq;
    private final Uri Mr;
    private final Uri Ms;
    private final Uri Mt;
    private final boolean Mu;
    private final boolean Mv;
    private final String Mw;
    private final int Mx;
    private final int My;
    private final int Mz;
    private final int xM;
    private final String zP;

    GameEntity(int i, String s, String s1, String s2, String s3, String s4, String s5, 
            Uri uri, Uri uri1, Uri uri2, boolean flag, boolean flag1, String s6, int j, 
            int k, int l, boolean flag2, boolean flag3, String s7, String s8, String s9, 
            boolean flag4, boolean flag5, boolean flag6)
    {
        xM = i;
        zP = s;
        Ln = s1;
        Mn = s2;
        Mo = s3;
        Mp = s4;
        Mq = s5;
        Mr = uri;
        MC = s7;
        Ms = uri1;
        MD = s8;
        Mt = uri2;
        ME = s9;
        Mu = flag;
        Mv = flag1;
        Mw = s6;
        Mx = j;
        My = k;
        Mz = l;
        MA = flag2;
        MB = flag3;
        MF = flag4;
        MG = flag5;
        MH = flag6;
    }

    public GameEntity(Game game)
    {
        xM = 4;
        zP = game.getApplicationId();
        Mn = game.getPrimaryCategory();
        Mo = game.getSecondaryCategory();
        Mp = game.getDescription();
        Mq = game.getDeveloperName();
        Ln = game.getDisplayName();
        Mr = game.getIconImageUri();
        MC = game.getIconImageUrl();
        Ms = game.getHiResImageUri();
        MD = game.getHiResImageUrl();
        Mt = game.getFeaturedImageUri();
        ME = game.getFeaturedImageUrl();
        Mu = game.gM();
        Mv = game.gO();
        Mw = game.gP();
        Mx = game.gQ();
        My = game.getAchievementTotalCount();
        Mz = game.getLeaderboardCount();
        MA = game.isRealTimeMultiplayerEnabled();
        MB = game.isTurnBasedMultiplayerEnabled();
        MF = game.isMuted();
        MG = game.gN();
        MH = game.areSnapshotsEnabled();
    }

    static int a(Game game)
    {
        Object aobj[] = new Object[20];
        aobj[0] = game.getApplicationId();
        aobj[1] = game.getDisplayName();
        aobj[2] = game.getPrimaryCategory();
        aobj[3] = game.getSecondaryCategory();
        aobj[4] = game.getDescription();
        aobj[5] = game.getDeveloperName();
        aobj[6] = game.getIconImageUri();
        aobj[7] = game.getHiResImageUri();
        aobj[8] = game.getFeaturedImageUri();
        aobj[9] = Boolean.valueOf(game.gM());
        aobj[10] = Boolean.valueOf(game.gO());
        aobj[11] = game.gP();
        aobj[12] = Integer.valueOf(game.gQ());
        aobj[13] = Integer.valueOf(game.getAchievementTotalCount());
        aobj[14] = Integer.valueOf(game.getLeaderboardCount());
        aobj[15] = Boolean.valueOf(game.isRealTimeMultiplayerEnabled());
        aobj[16] = Boolean.valueOf(game.isTurnBasedMultiplayerEnabled());
        aobj[17] = Boolean.valueOf(game.isMuted());
        aobj[18] = Boolean.valueOf(game.gN());
        aobj[19] = Boolean.valueOf(game.areSnapshotsEnabled());
        return hk.hashCode(aobj);
    }

    static boolean a(Game game, Object obj)
    {
        boolean flag = true;
        if (obj instanceof Game) goto _L2; else goto _L1
_L1:
        flag = false;
_L4:
        return flag;
_L2:
        if (game == obj) goto _L4; else goto _L3
_L3:
        Game game1 = (Game)obj;
        if (!hk.equal(game1.getApplicationId(), game.getApplicationId()) || !hk.equal(game1.getDisplayName(), game.getDisplayName()) || !hk.equal(game1.getPrimaryCategory(), game.getPrimaryCategory()) || !hk.equal(game1.getSecondaryCategory(), game.getSecondaryCategory()) || !hk.equal(game1.getDescription(), game.getDescription()) || !hk.equal(game1.getDeveloperName(), game.getDeveloperName()) || !hk.equal(game1.getIconImageUri(), game.getIconImageUri()) || !hk.equal(game1.getHiResImageUri(), game.getHiResImageUri()) || !hk.equal(game1.getFeaturedImageUri(), game.getFeaturedImageUri()) || !hk.equal(Boolean.valueOf(game1.gM()), Boolean.valueOf(game.gM())) || !hk.equal(Boolean.valueOf(game1.gO()), Boolean.valueOf(game.gO())) || !hk.equal(game1.gP(), game.gP()) || !hk.equal(Integer.valueOf(game1.gQ()), Integer.valueOf(game.gQ())) || !hk.equal(Integer.valueOf(game1.getAchievementTotalCount()), Integer.valueOf(game.getAchievementTotalCount())) || !hk.equal(Integer.valueOf(game1.getLeaderboardCount()), Integer.valueOf(game.getLeaderboardCount())) || !hk.equal(Boolean.valueOf(game1.isRealTimeMultiplayerEnabled()), Boolean.valueOf(game.isRealTimeMultiplayerEnabled())))
        {
            break; /* Loop/switch isn't completed */
        }
        Boolean boolean1 = Boolean.valueOf(game1.isTurnBasedMultiplayerEnabled());
        boolean flag1;
        if (game.isTurnBasedMultiplayerEnabled() && hk.equal(Boolean.valueOf(game1.isMuted()), Boolean.valueOf(game.isMuted())) && hk.equal(Boolean.valueOf(game1.gN()), Boolean.valueOf(game.gN())))
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        if (hk.equal(boolean1, Boolean.valueOf(flag1)) && hk.equal(Boolean.valueOf(game1.areSnapshotsEnabled()), Boolean.valueOf(game.areSnapshotsEnabled()))) goto _L4; else goto _L5
_L5:
        return false;
    }

    static boolean aQ(String s)
    {
        return aA(s);
    }

    static String b(Game game)
    {
        return hk.e(game).a("ApplicationId", game.getApplicationId()).a("DisplayName", game.getDisplayName()).a("PrimaryCategory", game.getPrimaryCategory()).a("SecondaryCategory", game.getSecondaryCategory()).a("Description", game.getDescription()).a("DeveloperName", game.getDeveloperName()).a("IconImageUri", game.getIconImageUri()).a("IconImageUrl", game.getIconImageUrl()).a("HiResImageUri", game.getHiResImageUri()).a("HiResImageUrl", game.getHiResImageUrl()).a("FeaturedImageUri", game.getFeaturedImageUri()).a("FeaturedImageUrl", game.getFeaturedImageUrl()).a("PlayEnabledGame", Boolean.valueOf(game.gM())).a("InstanceInstalled", Boolean.valueOf(game.gO())).a("InstancePackageName", game.gP()).a("AchievementTotalCount", Integer.valueOf(game.getAchievementTotalCount())).a("LeaderboardCount", Integer.valueOf(game.getLeaderboardCount())).a("RealTimeMultiplayerEnabled", Boolean.valueOf(game.isRealTimeMultiplayerEnabled())).a("TurnBasedMultiplayerEnabled", Boolean.valueOf(game.isTurnBasedMultiplayerEnabled())).a("AreSnapshotsEnabled", Boolean.valueOf(game.areSnapshotsEnabled())).toString();
    }

    static boolean b(Integer integer)
    {
        return c(integer);
    }

    static Integer gR()
    {
        return fq();
    }

    public boolean areSnapshotsEnabled()
    {
        return MH;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        return a(this, obj);
    }

    public Game freeze()
    {
        return this;
    }

    public volatile Object freeze()
    {
        return freeze();
    }

    public boolean gM()
    {
        return Mu;
    }

    public boolean gN()
    {
        return MG;
    }

    public boolean gO()
    {
        return Mv;
    }

    public String gP()
    {
        return Mw;
    }

    public int gQ()
    {
        return Mx;
    }

    public int getAchievementTotalCount()
    {
        return My;
    }

    public String getApplicationId()
    {
        return zP;
    }

    public String getDescription()
    {
        return Mp;
    }

    public void getDescription(CharArrayBuffer chararraybuffer)
    {
        ik.b(Mp, chararraybuffer);
    }

    public String getDeveloperName()
    {
        return Mq;
    }

    public void getDeveloperName(CharArrayBuffer chararraybuffer)
    {
        ik.b(Mq, chararraybuffer);
    }

    public String getDisplayName()
    {
        return Ln;
    }

    public void getDisplayName(CharArrayBuffer chararraybuffer)
    {
        ik.b(Ln, chararraybuffer);
    }

    public Uri getFeaturedImageUri()
    {
        return Mt;
    }

    public String getFeaturedImageUrl()
    {
        return ME;
    }

    public Uri getHiResImageUri()
    {
        return Ms;
    }

    public String getHiResImageUrl()
    {
        return MD;
    }

    public Uri getIconImageUri()
    {
        return Mr;
    }

    public String getIconImageUrl()
    {
        return MC;
    }

    public int getLeaderboardCount()
    {
        return Mz;
    }

    public String getPrimaryCategory()
    {
        return Mn;
    }

    public String getSecondaryCategory()
    {
        return Mo;
    }

    public int getVersionCode()
    {
        return xM;
    }

    public int hashCode()
    {
        return a(this);
    }

    public boolean isDataValid()
    {
        return true;
    }

    public boolean isMuted()
    {
        return MF;
    }

    public boolean isRealTimeMultiplayerEnabled()
    {
        return MA;
    }

    public boolean isTurnBasedMultiplayerEnabled()
    {
        return MB;
    }

    public String toString()
    {
        return b(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int j = 1;
        if (!fr())
        {
            GameEntityCreator.a(this, parcel, i);
            return;
        }
        parcel.writeString(zP);
        parcel.writeString(Ln);
        parcel.writeString(Mn);
        parcel.writeString(Mo);
        parcel.writeString(Mp);
        parcel.writeString(Mq);
        String s;
        String s1;
        Uri uri;
        String s2;
        int k;
        if (Mr == null)
        {
            s = null;
        } else
        {
            s = Mr.toString();
        }
        parcel.writeString(s);
        if (Ms == null)
        {
            s1 = null;
        } else
        {
            s1 = Ms.toString();
        }
        parcel.writeString(s1);
        uri = Mt;
        s2 = null;
        if (uri != null)
        {
            s2 = Mt.toString();
        }
        parcel.writeString(s2);
        if (Mu)
        {
            k = j;
        } else
        {
            k = 0;
        }
        parcel.writeInt(k);
        if (!Mv)
        {
            j = 0;
        }
        parcel.writeInt(j);
        parcel.writeString(Mw);
        parcel.writeInt(Mx);
        parcel.writeInt(My);
        parcel.writeInt(Mz);
    }

}
