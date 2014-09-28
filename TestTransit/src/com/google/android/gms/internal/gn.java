// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.text.TextUtils;
import android.util.Log;

public class gn
{

    private static boolean Cl = false;
    private boolean Cm;
    private boolean Cn;
    private String Co;
    private final String mTag;

    public gn(String s)
    {
        this(s, es());
    }

    public gn(String s, boolean flag)
    {
        mTag = s;
        Cm = flag;
        Cn = false;
    }

    private transient String e(String s, Object aobj[])
    {
        String s1 = String.format(s, aobj);
        if (!TextUtils.isEmpty(Co))
        {
            s1 = (new StringBuilder()).append(Co).append(s1).toString();
        }
        return s1;
    }

    public static boolean es()
    {
        return Cl;
    }

    public transient void a(String s, Object aobj[])
    {
        if (er())
        {
            Log.v(mTag, e(s, aobj));
        }
    }

    public transient void a(Throwable throwable, String s, Object aobj[])
    {
        if (eq() || Cl)
        {
            Log.d(mTag, e(s, aobj), throwable);
        }
    }

    public void ap(String s)
    {
        String s1;
        if (TextUtils.isEmpty(s))
        {
            s1 = null;
        } else
        {
            s1 = String.format("[%s] ", new Object[] {
                s
            });
        }
        Co = s1;
    }

    public transient void b(String s, Object aobj[])
    {
        if (eq() || Cl)
        {
            Log.d(mTag, e(s, aobj));
        }
    }

    public transient void c(String s, Object aobj[])
    {
        Log.i(mTag, e(s, aobj));
    }

    public transient void d(String s, Object aobj[])
    {
        Log.w(mTag, e(s, aobj));
    }

    public boolean eq()
    {
        return Cm;
    }

    public boolean er()
    {
        return Cn;
    }

}
