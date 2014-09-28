// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            mg, ly, me, mh

public class mb
{

    protected final Class amY;
    protected final boolean amZ;
    protected final int tag;
    protected final int type;

    private mb(int j, Class class1, int k, boolean flag)
    {
        type = j;
        amY = class1;
        tag = k;
        amZ = flag;
    }

    public static mb a(int j, Class class1, int k)
    {
        return new mb(j, class1, k, false);
    }

    protected void a(mg mg1, List list)
    {
        list.add(u(ly.p(mg1.anc)));
    }

    protected boolean eM(int j)
    {
        return j == tag;
    }

    final Object i(List list)
    {
        int j = 0;
        if (list != null) goto _L2; else goto _L1
_L1:
        Object obj = null;
_L4:
        return obj;
_L2:
        if (!amZ)
        {
            break; /* Loop/switch isn't completed */
        }
        ArrayList arraylist = new ArrayList();
        for (int k = 0; k < list.size(); k++)
        {
            mg mg1 = (mg)list.get(k);
            if (eM(mg1.tag) && mg1.anc.length != 0)
            {
                a(mg1, arraylist);
            }
        }

        int l = arraylist.size();
        if (l == 0)
        {
            return null;
        }
        obj = amY.cast(Array.newInstance(amY.getComponentType(), l));
        while (j < l) 
        {
            Array.set(obj, j, arraylist.get(j));
            j++;
        }
        if (true) goto _L4; else goto _L3
_L3:
        int i1 = -1 + list.size();
        mg mg2 = null;
        while (mg2 == null && i1 >= 0) 
        {
            mg mg3 = (mg)list.get(i1);
            if (!eM(mg3.tag) || mg3.anc.length == 0)
            {
                mg3 = mg2;
            }
            i1--;
            mg2 = mg3;
        }
        if (mg2 == null)
        {
            return null;
        } else
        {
            return amY.cast(u(ly.p(mg2.anc)));
        }
    }

    protected Object u(ly ly1)
    {
        Class class1;
        InstantiationException instantiationexception;
        if (amZ)
        {
            class1 = amY.getComponentType();
        } else
        {
            class1 = amY;
        }
        type;
        JVM INSTR tableswitch 10 11: default 40
    //                   10 109
    //                   11 134;
           goto _L1 _L2 _L3
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown type ").append(type).toString());
_L2:
        me me1;
        me me2;
        try
        {
            me2 = (me)class1.newInstance();
            ly1.a(me2, mh.eO(tag));
        }
        // Misplaced declaration of an exception variable
        catch (InstantiationException instantiationexception)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Error creating instance of class ").append(class1).toString(), instantiationexception);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Error creating instance of class ").append(class1).toString(), illegalaccessexception);
        }
        catch (IOException ioexception)
        {
            throw new IllegalArgumentException("Error reading extension field", ioexception);
        }
        return me2;
_L3:
        me1 = (me)class1.newInstance();
        ly1.a(me1);
        return me1;
    }
}
