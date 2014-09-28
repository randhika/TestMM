// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.gms.tagmanager:
//            aj, dh, bh

class s extends aj
{
    public static interface a
    {

        public abstract Object b(String s1, Map map);
    }


    private static final String ID;
    private static final String aeW;
    private static final String aem;
    private final a aeX;

    public s(a a1)
    {
        String s1 = ID;
        String as[] = new String[1];
        as[0] = aeW;
        super(s1, as);
        aeX = a1;
    }

    public boolean lh()
    {
        return false;
    }

    public com.google.android.gms.internal.d.a w(Map map)
    {
        String s1 = dh.j((com.google.android.gms.internal.d.a)map.get(aeW));
        HashMap hashmap = new HashMap();
        com.google.android.gms.internal.d.a a1 = (com.google.android.gms.internal.d.a)map.get(aem);
        if (a1 != null)
        {
            Object obj = dh.o(a1);
            if (!(obj instanceof Map))
            {
                bh.D("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
                return dh.nd();
            }
            java.util.Map.Entry entry;
            for (Iterator iterator = ((Map)obj).entrySet().iterator(); iterator.hasNext(); hashmap.put(entry.getKey().toString(), entry.getValue()))
            {
                entry = (java.util.Map.Entry)iterator.next();
            }

        }
        com.google.android.gms.internal.d.a a2;
        try
        {
            a2 = dh.r(aeX.b(s1, hashmap));
        }
        catch (Exception exception)
        {
            bh.D((new StringBuilder()).append("Custom macro/tag ").append(s1).append(" threw exception ").append(exception.getMessage()).toString());
            return dh.nd();
        }
        return a2;
    }

    static 
    {
        ID = com.google.android.gms.internal.a.J.toString();
        aeW = b.cy.toString();
        aem = b.aX.toString();
    }
}
