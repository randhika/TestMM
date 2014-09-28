// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;


// Referenced classes of package jp.co.yahoo.android.ads:
//            i, AdViewListener

class a
    implements Runnable
{

    final i a;

    public void run()
    {
        a.f.onAdViewActivityEnd(a.e);
    }

    ewListener(i j)
    {
        a = j;
        super();
    }
}
