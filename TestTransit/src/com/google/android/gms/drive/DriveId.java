// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive;

import android.os.Parcel;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.internal.af;
import com.google.android.gms.internal.hm;
import com.google.android.gms.internal.md;
import com.google.android.gms.internal.me;

// Referenced classes of package com.google.android.gms.drive:
//            c

public class DriveId
    implements SafeParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new c();
    final String HN;
    final long HO;
    final long HP;
    private volatile String HQ;
    final int xM;

    DriveId(int i, String s, long l, long l1)
    {
label0:
        {
            super();
            HQ = null;
            xM = i;
            HN = s;
            boolean flag;
            boolean flag1;
            if (!"".equals(s))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            hm.C(flag);
            if (s == null)
            {
                int j = l != -1L;
                flag1 = false;
                if (j == 0)
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        hm.C(flag1);
        HO = l;
        HP = l1;
    }

    public DriveId(String s, long l, long l1)
    {
        this(1, s, l, l1);
    }

    public static DriveId aL(String s)
    {
        hm.f(s);
        return new DriveId(s, -1L, -1L);
    }

    public static DriveId decodeFromString(String s)
    {
        hm.b(s.startsWith("DriveId:"), (new StringBuilder()).append("Invalid DriveId: ").append(s).toString());
        return f(Base64.decode(s.substring("DriveId:".length()), 10));
    }

    static DriveId f(byte abyte0[])
    {
        af af1;
        String s;
        try
        {
            af1 = af.g(abyte0);
        }
        catch (md md1)
        {
            throw new IllegalArgumentException();
        }
        if ("".equals(af1.Jt))
        {
            s = null;
        } else
        {
            s = af1.Jt;
        }
        return new DriveId(af1.versionCode, s, af1.Ju, af1.Jv);
    }

    public int describeContents()
    {
        return 0;
    }

    public final String encodeToString()
    {
        if (HQ == null)
        {
            String s = Base64.encodeToString(gk(), 10);
            HQ = (new StringBuilder()).append("DriveId:").append(s).toString();
        }
        return HQ;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof DriveId)
        {
            DriveId driveid = (DriveId)obj;
            if (driveid.HP != HP)
            {
                Log.w("DriveId", "Attempt to compare invalid DriveId detected. Has local storage been cleared?");
                return false;
            }
            if (driveid.HO == -1L && HO == -1L)
            {
                return driveid.HN.equals(HN);
            }
            if (driveid.HO == HO)
            {
                return true;
            }
        }
        return false;
    }

    public String getResourceId()
    {
        return HN;
    }

    final byte[] gk()
    {
        af af1 = new af();
        af1.versionCode = xM;
        String s;
        if (HN == null)
        {
            s = "";
        } else
        {
            s = HN;
        }
        af1.Jt = s;
        af1.Ju = HO;
        af1.Jv = HP;
        return me.d(af1);
    }

    public int hashCode()
    {
        if (HO == -1L)
        {
            return HN.hashCode();
        } else
        {
            return (new StringBuilder()).append(String.valueOf(HP)).append(String.valueOf(HO)).toString().hashCode();
        }
    }

    public String toString()
    {
        return encodeToString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        c.a(this, parcel, i);
    }

}
