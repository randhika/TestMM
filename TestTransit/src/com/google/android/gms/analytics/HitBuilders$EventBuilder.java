// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.analytics;

import java.util.Map;

// Referenced classes of package com.google.android.gms.analytics:
//            u, HitBuilders

public static class setAction extends setAction
{

    public volatile Map build()
    {
        return super.ild();
    }

    public ild setAction(String s)
    {
        set("&ea", s);
        return this;
    }

    public set setCategory(String s)
    {
        set("&ec", s);
        return this;
    }

    public set setLabel(String s)
    {
        set("&el", s);
        return this;
    }

    public set setValue(long l)
    {
        set("&ev", Long.toString(l));
        return this;
    }

    public ()
    {
        u.cU().a();
        set("&t", "event");
    }

    public set(String s, String s1)
    {
        this();
        setCategory(s);
        setAction(s1);
    }
}
