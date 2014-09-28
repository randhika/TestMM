// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.widget.ProgressBar;
import android.widget.RelativeLayout;

// Referenced classes of package jp.co.yahoo.android.ads:
//            CreateAdLayoutDisplay, e, a, h

class a
    implements Runnable
{

    final CreateAdLayoutDisplay a;

    public void run()
    {
        if (CreateAdLayoutDisplay.j(a) != null)
        {
            CreateAdLayoutDisplay.a(a).removeView(CreateAdLayoutDisplay.j(a));
            CreateAdLayoutDisplay.a(a).addView(CreateAdLayoutDisplay.j(a));
        }
        if (e.g(CreateAdLayoutDisplay.c(a)) && !h.d(CreateAdLayoutDisplay.b(a).p()))
        {
            CreateAdLayoutDisplay.a(a).removeView(CreateAdLayoutDisplay.k(a));
            CreateAdLayoutDisplay.a(a).addView(CreateAdLayoutDisplay.k(a));
        } else
        {
            CreateAdLayoutDisplay.a(a).removeView(CreateAdLayoutDisplay.l(a));
            CreateAdLayoutDisplay.a(a).addView(CreateAdLayoutDisplay.l(a));
        }
        if (CreateAdLayoutDisplay.m(a) != null)
        {
            CreateAdLayoutDisplay.a(a).removeView(CreateAdLayoutDisplay.m(a));
            CreateAdLayoutDisplay.a(a).addView(CreateAdLayoutDisplay.m(a));
        }
        CreateAdLayoutDisplay.n(a).setVisibility(4);
        CreateAdLayoutDisplay.a(a, CreateAdLayoutDisplay.b(a).j());
        CreateAdLayoutDisplay.a(a, CreateAdLayoutDisplay.a(a, CreateAdLayoutDisplay.b(a).k(), "expand"));
    }

    (CreateAdLayoutDisplay createadlayoutdisplay)
    {
        a = createadlayoutdisplay;
        super();
    }
}
