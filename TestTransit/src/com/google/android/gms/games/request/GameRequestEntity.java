// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.hk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package com.google.android.gms.games.request:
//            GameRequest, GameRequestEntityCreator

public final class GameRequestEntity
    implements SafeParcelable, GameRequest
{

    public static final GameRequestEntityCreator CREATOR = new GameRequestEntityCreator();
    private final int AT;
    private final String OB;
    private final GameEntity Rt;
    private final long SU;
    private final byte TF[];
    private final int Tb;
    private final PlayerEntity Ua;
    private final ArrayList Ub;
    private final long Uc;
    private final Bundle Ud;
    private final int xM;

    GameRequestEntity(int i, GameEntity gameentity, PlayerEntity playerentity, byte abyte0[], String s, ArrayList arraylist, int j, 
            long l, long l1, Bundle bundle, int k)
    {
        xM = i;
        Rt = gameentity;
        Ua = playerentity;
        TF = abyte0;
        OB = s;
        Ub = arraylist;
        AT = j;
        SU = l;
        Uc = l1;
        Ud = bundle;
        Tb = k;
    }

    public GameRequestEntity(GameRequest gamerequest)
    {
        xM = 2;
        Rt = new GameEntity(gamerequest.getGame());
        Ua = new PlayerEntity(gamerequest.getSender());
        OB = gamerequest.getRequestId();
        AT = gamerequest.getType();
        SU = gamerequest.getCreationTimestamp();
        Uc = gamerequest.getExpirationTimestamp();
        Tb = gamerequest.getStatus();
        byte abyte0[] = gamerequest.getData();
        List list;
        int i;
        if (abyte0 == null)
        {
            TF = null;
        } else
        {
            TF = new byte[abyte0.length];
            System.arraycopy(abyte0, 0, TF, 0, abyte0.length);
        }
        list = gamerequest.getRecipients();
        i = list.size();
        Ub = new ArrayList(i);
        Ud = new Bundle();
        for (int j = 0; j < i; j++)
        {
            Player player = (Player)((Player)list.get(j)).freeze();
            String s = player.getPlayerId();
            Ub.add((PlayerEntity)player);
            Ud.putInt(s, gamerequest.getRecipientStatus(s));
        }

    }

    static int a(GameRequest gamerequest)
    {
        Object aobj[] = new Object[8];
        aobj[0] = gamerequest.getGame();
        aobj[1] = gamerequest.getRecipients();
        aobj[2] = gamerequest.getRequestId();
        aobj[3] = gamerequest.getSender();
        aobj[4] = b(gamerequest);
        aobj[5] = Integer.valueOf(gamerequest.getType());
        aobj[6] = Long.valueOf(gamerequest.getCreationTimestamp());
        aobj[7] = Long.valueOf(gamerequest.getExpirationTimestamp());
        return hk.hashCode(aobj);
    }

    static boolean a(GameRequest gamerequest, Object obj)
    {
        boolean flag = true;
        if (!(obj instanceof GameRequest))
        {
            flag = false;
        } else
        if (gamerequest != obj)
        {
            GameRequest gamerequest1 = (GameRequest)obj;
            if (!hk.equal(gamerequest1.getGame(), gamerequest.getGame()) || !hk.equal(gamerequest1.getRecipients(), gamerequest.getRecipients()) || !hk.equal(gamerequest1.getRequestId(), gamerequest.getRequestId()) || !hk.equal(gamerequest1.getSender(), gamerequest.getSender()) || !Arrays.equals(b(gamerequest1), b(gamerequest)) || !hk.equal(Integer.valueOf(gamerequest1.getType()), Integer.valueOf(gamerequest.getType())) || !hk.equal(Long.valueOf(gamerequest1.getCreationTimestamp()), Long.valueOf(gamerequest.getCreationTimestamp())) || !hk.equal(Long.valueOf(gamerequest1.getExpirationTimestamp()), Long.valueOf(gamerequest.getExpirationTimestamp())))
            {
                return false;
            }
        }
        return flag;
    }

    private static int[] b(GameRequest gamerequest)
    {
        List list = gamerequest.getRecipients();
        int i = list.size();
        int ai[] = new int[i];
        for (int j = 0; j < i; j++)
        {
            ai[j] = gamerequest.getRecipientStatus(((Player)list.get(j)).getPlayerId());
        }

        return ai;
    }

    static String c(GameRequest gamerequest)
    {
        return hk.e(gamerequest).a("Game", gamerequest.getGame()).a("Sender", gamerequest.getSender()).a("Recipients", gamerequest.getRecipients()).a("Data", gamerequest.getData()).a("RequestId", gamerequest.getRequestId()).a("Type", Integer.valueOf(gamerequest.getType())).a("CreationTimestamp", Long.valueOf(gamerequest.getCreationTimestamp())).a("ExpirationTimestamp", Long.valueOf(gamerequest.getExpirationTimestamp())).toString();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        return a(this, obj);
    }

    public GameRequest freeze()
    {
        return this;
    }

    public volatile Object freeze()
    {
        return freeze();
    }

    public long getCreationTimestamp()
    {
        return SU;
    }

    public byte[] getData()
    {
        return TF;
    }

    public long getExpirationTimestamp()
    {
        return Uc;
    }

    public Game getGame()
    {
        return Rt;
    }

    public int getRecipientStatus(String s)
    {
        return Ud.getInt(s, 0);
    }

    public List getRecipients()
    {
        return new ArrayList(Ub);
    }

    public String getRequestId()
    {
        return OB;
    }

    public Player getSender()
    {
        return Ua;
    }

    public int getStatus()
    {
        return Tb;
    }

    public int getType()
    {
        return AT;
    }

    public int getVersionCode()
    {
        return xM;
    }

    public int hashCode()
    {
        return a(this);
    }

    public Bundle iL()
    {
        return Ud;
    }

    public boolean isConsumed(String s)
    {
        return getRecipientStatus(s) == 1;
    }

    public boolean isDataValid()
    {
        return true;
    }

    public String toString()
    {
        return c(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        GameRequestEntityCreator.a(this, parcel, i);
    }

}
