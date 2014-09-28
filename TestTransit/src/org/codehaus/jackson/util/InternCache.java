// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.util;

import java.util.LinkedHashMap;

public final class InternCache extends LinkedHashMap
{

    private static final int MAX_ENTRIES = 192;
    public static final InternCache instance = new InternCache();

    private InternCache()
    {
        super(192, 0.8F, true);
    }

    public String intern(String s)
    {
        this;
        JVM INSTR monitorenter ;
        String s1 = (String)get(s);
        if (s1 != null)
        {
            break MISSING_BLOCK_LABEL_27;
        }
        s1 = s.intern();
        put(s1, s1);
        this;
        JVM INSTR monitorexit ;
        return s1;
        Exception exception;
        exception;
        throw exception;
    }

    protected boolean removeEldestEntry(java.util.Map.Entry entry)
    {
        return size() > 192;
    }

}
