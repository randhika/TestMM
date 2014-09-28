// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import com.google.android.gms.internal.hk;
import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.games.internal.game:
//            ExtendedGame, GameBadge, GameBadgeEntity, ExtendedGameEntityCreator, 
//            GameBadgeEntityCreator

public final class ExtendedGameEntity extends GamesDowngradeableSafeParcel
    implements ExtendedGame
{
    static final class ExtendedGameEntityCreatorCompat extends ExtendedGameEntityCreator
    {

        public ExtendedGameEntity bg(Parcel parcel)
        {
            if (ExtendedGameEntity.b(ExtendedGameEntity.gR()) || ExtendedGameEntity.aQ(com/google/android/gms/games/internal/game/ExtendedGameEntity.getCanonicalName()))
            {
                return super.bg(parcel);
            }
            GameEntity gameentity = (GameEntity)GameEntity.CREATOR.createFromParcel(parcel);
            int i = parcel.readInt();
            boolean flag;
            int j;
            long l;
            long l1;
            String s;
            long l2;
            String s1;
            int k;
            ArrayList arraylist;
            if (parcel.readInt() == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            j = parcel.readInt();
            l = parcel.readLong();
            l1 = parcel.readLong();
            s = parcel.readString();
            l2 = parcel.readLong();
            s1 = parcel.readString();
            k = parcel.readInt();
            arraylist = new ArrayList(k);
            for (int i1 = 0; i1 < k; i1++)
            {
                arraylist.add(GameBadgeEntity.CREATOR.bh(parcel));
            }

            return new ExtendedGameEntity(2, gameentity, i, flag, j, l, l1, s, l2, s1, arraylist, null);
        }

        public Object createFromParcel(Parcel parcel)
        {
            return bg(parcel);
        }

        ExtendedGameEntityCreatorCompat()
        {
        }
    }


    public static final ExtendedGameEntityCreator CREATOR = new ExtendedGameEntityCreatorCompat();
    private final long RA;
    private final String RB;
    private final ArrayList RC;
    private final SnapshotMetadataEntity RD;
    private final GameEntity Rt;
    private final int Ru;
    private final boolean Rv;
    private final int Rw;
    private final long Rx;
    private final long Ry;
    private final String Rz;
    private final int xM;

    ExtendedGameEntity(int i, GameEntity gameentity, int j, boolean flag, int k, long l, 
            long l1, String s, long l2, String s1, ArrayList arraylist, 
            SnapshotMetadataEntity snapshotmetadataentity)
    {
        xM = i;
        Rt = gameentity;
        Ru = j;
        Rv = flag;
        Rw = k;
        Rx = l;
        Ry = l1;
        Rz = s;
        RA = l2;
        RB = s1;
        RC = arraylist;
        RD = snapshotmetadataentity;
    }

    public ExtendedGameEntity(ExtendedGame extendedgame)
    {
        xM = 2;
        Game game = extendedgame.getGame();
        GameEntity gameentity;
        SnapshotMetadata snapshotmetadata;
        SnapshotMetadataEntity snapshotmetadataentity;
        ArrayList arraylist;
        int i;
        if (game == null)
        {
            gameentity = null;
        } else
        {
            gameentity = new GameEntity(game);
        }
        Rt = gameentity;
        Ru = extendedgame.hR();
        Rv = extendedgame.hS();
        Rw = extendedgame.hT();
        Rx = extendedgame.hU();
        Ry = extendedgame.hV();
        Rz = extendedgame.hW();
        RA = extendedgame.hX();
        RB = extendedgame.hY();
        snapshotmetadata = extendedgame.hZ();
        snapshotmetadataentity = null;
        if (snapshotmetadata != null)
        {
            snapshotmetadataentity = new SnapshotMetadataEntity(snapshotmetadata);
        }
        RD = snapshotmetadataentity;
        arraylist = extendedgame.hQ();
        i = arraylist.size();
        RC = new ArrayList(i);
        for (int j = 0; j < i; j++)
        {
            RC.add((GameBadgeEntity)((GameBadge)arraylist.get(j)).freeze());
        }

    }

    static int a(ExtendedGame extendedgame)
    {
        Object aobj[] = new Object[9];
        aobj[0] = extendedgame.getGame();
        aobj[1] = Integer.valueOf(extendedgame.hR());
        aobj[2] = Boolean.valueOf(extendedgame.hS());
        aobj[3] = Integer.valueOf(extendedgame.hT());
        aobj[4] = Long.valueOf(extendedgame.hU());
        aobj[5] = Long.valueOf(extendedgame.hV());
        aobj[6] = extendedgame.hW();
        aobj[7] = Long.valueOf(extendedgame.hX());
        aobj[8] = extendedgame.hY();
        return hk.hashCode(aobj);
    }

    static boolean a(ExtendedGame extendedgame, Object obj)
    {
        boolean flag = true;
        if (!(obj instanceof ExtendedGame))
        {
            flag = false;
        } else
        if (extendedgame != obj)
        {
            ExtendedGame extendedgame1 = (ExtendedGame)obj;
            if (!hk.equal(extendedgame1.getGame(), extendedgame.getGame()) || !hk.equal(Integer.valueOf(extendedgame1.hR()), Integer.valueOf(extendedgame.hR())) || !hk.equal(Boolean.valueOf(extendedgame1.hS()), Boolean.valueOf(extendedgame.hS())) || !hk.equal(Integer.valueOf(extendedgame1.hT()), Integer.valueOf(extendedgame.hT())) || !hk.equal(Long.valueOf(extendedgame1.hU()), Long.valueOf(extendedgame.hU())) || !hk.equal(Long.valueOf(extendedgame1.hV()), Long.valueOf(extendedgame.hV())) || !hk.equal(extendedgame1.hW(), extendedgame.hW()) || !hk.equal(Long.valueOf(extendedgame1.hX()), Long.valueOf(extendedgame.hX())) || !hk.equal(extendedgame1.hY(), extendedgame.hY()))
            {
                return false;
            }
        }
        return flag;
    }

    static boolean aQ(String s)
    {
        return aA(s);
    }

    static String b(ExtendedGame extendedgame)
    {
        return hk.e(extendedgame).a("Game", extendedgame.getGame()).a("Availability", Integer.valueOf(extendedgame.hR())).a("Owned", Boolean.valueOf(extendedgame.hS())).a("AchievementUnlockedCount", Integer.valueOf(extendedgame.hT())).a("LastPlayedServerTimestamp", Long.valueOf(extendedgame.hU())).a("PriceMicros", Long.valueOf(extendedgame.hV())).a("FormattedPrice", extendedgame.hW()).a("FullPriceMicros", Long.valueOf(extendedgame.hX())).a("FormattedFullPrice", extendedgame.hY()).a("Snapshot", extendedgame.hZ()).toString();
    }

    static boolean b(Integer integer)
    {
        return c(integer);
    }

    static Integer gR()
    {
        return fq();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        return a(this, obj);
    }

    public Object freeze()
    {
        return ib();
    }

    public Game getGame()
    {
        return ia();
    }

    public int getVersionCode()
    {
        return xM;
    }

    public ArrayList hQ()
    {
        return new ArrayList(RC);
    }

    public int hR()
    {
        return Ru;
    }

    public boolean hS()
    {
        return Rv;
    }

    public int hT()
    {
        return Rw;
    }

    public long hU()
    {
        return Rx;
    }

    public long hV()
    {
        return Ry;
    }

    public String hW()
    {
        return Rz;
    }

    public long hX()
    {
        return RA;
    }

    public String hY()
    {
        return RB;
    }

    public SnapshotMetadata hZ()
    {
        return RD;
    }

    public int hashCode()
    {
        return a(this);
    }

    public GameEntity ia()
    {
        return Rt;
    }

    public ExtendedGame ib()
    {
        return this;
    }

    public boolean isDataValid()
    {
        return true;
    }

    public String toString()
    {
        return b(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int j = 0;
        if (!fr())
        {
            ExtendedGameEntityCreator.a(this, parcel, i);
        } else
        {
            Rt.writeToParcel(parcel, i);
            parcel.writeInt(Ru);
            int k;
            int l;
            if (Rv)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            parcel.writeInt(k);
            parcel.writeInt(Rw);
            parcel.writeLong(Rx);
            parcel.writeLong(Ry);
            parcel.writeString(Rz);
            parcel.writeLong(RA);
            parcel.writeString(RB);
            l = RC.size();
            parcel.writeInt(l);
            while (j < l) 
            {
                ((GameBadgeEntity)RC.get(j)).writeToParcel(parcel, i);
                j++;
            }
        }
    }

}
