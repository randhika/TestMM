// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;


// Referenced classes of package jp.co.yahoo.android.ads:
//            CreateAdLayoutDisplay, f

class a extends Thread
{

    final String a;
    final CreateAdLayoutDisplay b;

    public void run()
    {
        f.a(CreateAdLayoutDisplay.c(b), a);
    }

    (CreateAdLayoutDisplay createadlayoutdisplay, String s)
    {
        b = createadlayoutdisplay;
        a = s;
        super();
    }
}
