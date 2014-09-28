// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.content.Context;
import android.view.WindowManager;
import android.widget.RelativeLayout;

// Referenced classes of package jp.co.yahoo.android.ads:
//            CreateAdLayoutDisplay, a, h

class a
    implements Runnable
{

    final CreateAdLayoutDisplay a;

    public void run()
    {
        try
        {
            if (CreateAdLayoutDisplay.a(a) != null && !h.d(CreateAdLayoutDisplay.b(a).g()))
            {
                CreateAdLayoutDisplay.a(a).removeAllViews();
                ((WindowManager)CreateAdLayoutDisplay.c(a).getSystemService("window")).removeView(CreateAdLayoutDisplay.a(a));
                CreateAdLayoutDisplay.a(a, CreateAdLayoutDisplay.a(a, CreateAdLayoutDisplay.b(a).k(), "close"));
            }
            return;
        }
        catch (Exception exception)
        {
            h.a(3, (new StringBuilder()).append("Remove expandLayout: ").append(exception.getMessage()).toString());
        }
    }

    (CreateAdLayoutDisplay createadlayoutdisplay)
    {
        a = createadlayoutdisplay;
        super();
    }
}
