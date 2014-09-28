// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.gms.tagmanager:
//            df, dh, DataLayer

class w extends df
{

    private static final String ID;
    private static final String VALUE;
    private static final String aft;
    private final DataLayer aeu;

    public w(DataLayer datalayer)
    {
        String s = ID;
        String as[] = new String[1];
        as[0] = VALUE;
        super(s, as);
        aeu = datalayer;
    }

    private void a(com.google.android.gms.internal.d.a a1)
    {
        String s;
        if (a1 != null && a1 != dh.mX())
        {
            if ((s = dh.j(a1)) != dh.nc())
            {
                aeu.bN(s);
                return;
            }
        }
    }

    private void b(com.google.android.gms.internal.d.a a1)
    {
        Object obj;
        if (a1 != null && a1 != dh.mX())
        {
            if ((obj = dh.o(a1)) instanceof List)
            {
                Iterator iterator = ((List)obj).iterator();
                while (iterator.hasNext()) 
                {
                    Object obj1 = iterator.next();
                    if (obj1 instanceof Map)
                    {
                        Map map = (Map)obj1;
                        aeu.push(map);
                    }
                }
            }
        }
    }

    public void y(Map map)
    {
        b((com.google.android.gms.internal.d.a)map.get(VALUE));
        a((com.google.android.gms.internal.d.a)map.get(aft));
    }

    static 
    {
        ID = a.af.toString();
        VALUE = b.ew.toString();
        aft = b.bD.toString();
    }
}
