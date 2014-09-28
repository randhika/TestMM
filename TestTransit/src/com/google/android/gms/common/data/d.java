// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.hm;

// Referenced classes of package com.google.android.gms.common.data:
//            DataHolder

public abstract class d
{

    protected final DataHolder DG;
    protected int EC;
    private int ED;

    public d(DataHolder dataholder, int i)
    {
        DG = (DataHolder)hm.f(dataholder);
        ac(i);
    }

    protected void a(String s, CharArrayBuffer chararraybuffer)
    {
        DG.a(s, EC, ED, chararraybuffer);
    }

    protected void ac(int i)
    {
        boolean flag;
        if (i >= 0 && i < DG.getCount())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        hm.A(flag);
        EC = i;
        ED = DG.ae(EC);
    }

    public boolean av(String s)
    {
        return DG.av(s);
    }

    protected Uri aw(String s)
    {
        return DG.g(s, EC, ED);
    }

    protected boolean ax(String s)
    {
        return DG.h(s, EC, ED);
    }

    protected int eV()
    {
        return EC;
    }

    public boolean equals(Object obj)
    {
        boolean flag = obj instanceof d;
        boolean flag1 = false;
        if (flag)
        {
            d d1 = (d)obj;
            boolean flag2 = hk.equal(Integer.valueOf(d1.EC), Integer.valueOf(EC));
            flag1 = false;
            if (flag2)
            {
                boolean flag3 = hk.equal(Integer.valueOf(d1.ED), Integer.valueOf(ED));
                flag1 = false;
                if (flag3)
                {
                    DataHolder dataholder = d1.DG;
                    DataHolder dataholder1 = DG;
                    flag1 = false;
                    if (dataholder == dataholder1)
                    {
                        flag1 = true;
                    }
                }
            }
        }
        return flag1;
    }

    protected boolean getBoolean(String s)
    {
        return DG.d(s, EC, ED);
    }

    protected byte[] getByteArray(String s)
    {
        return DG.f(s, EC, ED);
    }

    protected float getFloat(String s)
    {
        return DG.e(s, EC, ED);
    }

    protected int getInteger(String s)
    {
        return DG.b(s, EC, ED);
    }

    protected long getLong(String s)
    {
        return DG.a(s, EC, ED);
    }

    protected String getString(String s)
    {
        return DG.c(s, EC, ED);
    }

    public int hashCode()
    {
        Object aobj[] = new Object[3];
        aobj[0] = Integer.valueOf(EC);
        aobj[1] = Integer.valueOf(ED);
        aobj[2] = DG;
        return hk.hashCode(aobj);
    }

    public boolean isDataValid()
    {
        return !DG.isClosed();
    }
}
