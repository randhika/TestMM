// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.net.URL;

// Referenced classes of package jp.co.yahoo.android.ads:
//            CreateAdLayoutDisplay, h

private class b
    implements Runnable
{

    final CreateAdLayoutDisplay a;
    private String b;
    private URL c;

    public void run()
    {
        CreateAdLayoutDisplay.r(a);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(b));
        intent.addFlags(0x10000000);
        try
        {
            CreateAdLayoutDisplay.c(a).startActivity(intent);
        }
        catch (Exception exception)
        {
            h.a(6, (new StringBuilder()).append("Could not open browser on ad click to ").append(c).toString(), exception);
        }
        CreateAdLayoutDisplay.d(a);
        CreateAdLayoutDisplay.s(a);
    }

    public (CreateAdLayoutDisplay createadlayoutdisplay, String s)
    {
        a = createadlayoutdisplay;
        super();
        b = s;
    }
}
