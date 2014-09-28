// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

// Referenced classes of package jp.co.yahoo.android.ads:
//            CreateAdLayoutDisplay, a

class a
    implements android.view.Display._cls16
{

    final CreateAdLayoutDisplay a;

    public void onClick(View view)
    {
        CreateAdLayoutDisplay.a(a).removeAllViews();
        ((WindowManager)CreateAdLayoutDisplay.c(a).getSystemService("window")).removeView(CreateAdLayoutDisplay.a(a));
        CreateAdLayoutDisplay.a(a, CreateAdLayoutDisplay.a(a, CreateAdLayoutDisplay.b(a).k(), "close"));
    }

    (CreateAdLayoutDisplay createadlayoutdisplay)
    {
        a = createadlayoutdisplay;
        super();
    }
}
