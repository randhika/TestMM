// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.data;

import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.common.data:
//            DataBuffer, DataHolder

public abstract class g extends DataBuffer
{

    private boolean EU;
    private ArrayList EV;

    protected g(DataHolder dataholder)
    {
        super(dataholder);
        EU = false;
    }

    private void fa()
    {
        this;
        JVM INSTR monitorenter ;
        if (EU) goto _L2; else goto _L1
_L1:
        int i;
        i = DG.getCount();
        EV = new ArrayList();
        if (i <= 0) goto _L4; else goto _L3
_L3:
        String s;
        String s1;
        EV.add(Integer.valueOf(0));
        s = eZ();
        int j = DG.ae(0);
        s1 = DG.c(s, 0, j);
        int k = 1;
_L10:
        if (k >= i) goto _L4; else goto _L5
_L5:
        String s2;
        int l = DG.ae(k);
        s2 = DG.c(s, k, l);
        if (s2.equals(s1)) goto _L7; else goto _L6
_L6:
        EV.add(Integer.valueOf(k));
          goto _L8
_L4:
        EU = true;
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
_L7:
        s2 = s1;
_L8:
        k++;
        s1 = s2;
        if (true) goto _L10; else goto _L9
_L9:
    }

    int ah(int i)
    {
        if (i < 0 || i >= EV.size())
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Position ").append(i).append(" is out of bounds for this buffer").toString());
        } else
        {
            return ((Integer)EV.get(i)).intValue();
        }
    }

    protected int ai(int i)
    {
        int j;
        if (i < 0 || i == EV.size())
        {
            j = 0;
        } else
        {
            if (i == -1 + EV.size())
            {
                j = DG.getCount() - ((Integer)EV.get(i)).intValue();
            } else
            {
                j = ((Integer)EV.get(i + 1)).intValue() - ((Integer)EV.get(i)).intValue();
            }
            if (j == 1)
            {
                int k = ah(i);
                int l = DG.ae(k);
                String s = fb();
                if (s != null && DG.c(s, k, l) == null)
                {
                    return 0;
                }
            }
        }
        return j;
    }

    protected abstract Object c(int i, int j);

    protected abstract String eZ();

    protected String fb()
    {
        return null;
    }

    public final Object get(int i)
    {
        fa();
        return c(ah(i), ai(i));
    }

    public int getCount()
    {
        fa();
        return EV.size();
    }
}
