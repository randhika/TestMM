// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            eo, ei, ek, eg, 
//            ej

public class eh
{

    private static final eh rP;
    public static final String rQ;
    private final Object ls = new Object();
    public final String rR = eo.bT();
    private final ei rS;
    private BigInteger rT;
    private final HashSet rU = new HashSet();
    private final HashMap rV = new HashMap();
    private boolean rW;

    private eh()
    {
        rT = BigInteger.ONE;
        rW = false;
        rS = new ei(rR);
    }

    public static Bundle a(Context context, ej ej1, String s)
    {
        return rP.b(context, ej1, s);
    }

    public static void b(HashSet hashset)
    {
        rP.c(hashset);
    }

    public static eh bH()
    {
        return rP;
    }

    public static String bI()
    {
        return rP.bJ();
    }

    public static ei bK()
    {
        return rP.bL();
    }

    public static boolean bM()
    {
        return rP.bN();
    }

    public void a(eg eg1)
    {
        synchronized (ls)
        {
            rU.add(eg1);
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void a(String s, ek ek1)
    {
        synchronized (ls)
        {
            rV.put(s, ek1);
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Bundle b(Context context, ej ej1, String s)
    {
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
        Bundle bundle;
        Bundle bundle1;
        bundle = new Bundle();
        bundle.putBundle("app", rS.b(context, s));
        bundle1 = new Bundle();
        String s1;
        for (Iterator iterator = rV.keySet().iterator(); iterator.hasNext(); bundle1.putBundle(s1, ((ek)rV.get(s1)).toBundle()))
        {
            s1 = (String)iterator.next();
        }

        break MISSING_BLOCK_LABEL_112;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        ArrayList arraylist;
        bundle.putBundle("slots", bundle1);
        arraylist = new ArrayList();
        for (Iterator iterator1 = rU.iterator(); iterator1.hasNext(); arraylist.add(((eg)iterator1.next()).toBundle())) { }
        bundle.putParcelableArrayList("ads", arraylist);
        ej1.a(rU);
        rU.clear();
        obj;
        JVM INSTR monitorexit ;
        return bundle;
    }

    public String bJ()
    {
        String s;
        synchronized (ls)
        {
            s = rT.toString();
            rT = rT.add(BigInteger.ONE);
        }
        return s;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public ei bL()
    {
        ei ei1;
        synchronized (ls)
        {
            ei1 = rS;
        }
        return ei1;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean bN()
    {
        boolean flag;
        synchronized (ls)
        {
            flag = rW;
            rW = true;
        }
        return flag;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void c(HashSet hashset)
    {
        synchronized (ls)
        {
            rU.addAll(hashset);
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    static 
    {
        rP = new eh();
        rQ = rP.rR;
    }
}
