// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package jp.co.yahoo.android.common:
//            YHosts

private static class <init>
{

    public String host;
    public List ignore;
    public String ipaddr;
    public List path;

    private ()
    {
        path = new ArrayList();
        ignore = new ArrayList();
    }

    ignore(ignore ignore1)
    {
        this();
    }
}
