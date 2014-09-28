// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.gms.tagmanager:
//            cs

private static class 
{

    private final Map ahE = new HashMap();
    private final Map ahF = new HashMap();
    private final Map ahG = new HashMap();
    private final Map ahH = new HashMap();
    private ahI ahI;
    private final Set ahu = new HashSet();

    public void a( ,  1)
    {
        Object obj = (List)ahE.get();
        if (obj == null)
        {
            obj = new ArrayList();
            ahE.put(, obj);
        }
        ((List) (obj)).add(1);
    }

    public void a(ahE ahe, String s)
    {
        Object obj = (List)ahG.get(ahe);
        if (obj == null)
        {
            obj = new ArrayList();
            ahG.put(ahe, obj);
        }
        ((List) (obj)).add(s);
    }

    public void b(ahG ahg)
    {
        ahu.add(ahg);
    }

    public void b(ahu ahu1, ahu ahu2)
    {
        Object obj = (List)ahF.get(ahu1);
        if (obj == null)
        {
            obj = new ArrayList();
            ahF.put(ahu1, obj);
        }
        ((List) (obj)).add(ahu2);
    }

    public void b(ahF ahf, String s)
    {
        Object obj = (List)ahH.get(ahf);
        if (obj == null)
        {
            obj = new ArrayList();
            ahH.put(ahf, obj);
        }
        ((List) (obj)).add(s);
    }

    public void i(ahH ahh)
    {
        ahI = ahh;
    }

    public Set mK()
    {
        return ahu;
    }

    public Map mL()
    {
        return ahE;
    }

    public Map mM()
    {
        return ahG;
    }

    public Map mN()
    {
        return ahH;
    }

    public Map mO()
    {
        return ahF;
    }

    public ahF mP()
    {
        return ahI;
    }

    public ()
    {
    }
}
