// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;


// Referenced classes of package com.google.android.gms.tagmanager:
//            v

class afq
    implements Runnable
{

    final v afp;
    final Layer.c.a afq;

    public void run()
    {
        afq.d(v.a(afp));
    }

    Layer.c.a(v v1, Layer.c.a a)
    {
        afp = v1;
        afq = a;
        super();
    }
}
