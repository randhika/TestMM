// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.widget.ProgressBar;

// Referenced classes of package jp.co.yahoo.android.ads:
//            CreateAdLayoutDisplay

class a
    implements Runnable
{

    final CreateAdLayoutDisplay a;

    public void run()
    {
        if (CreateAdLayoutDisplay.n(a) != null)
        {
            CreateAdLayoutDisplay.n(a).setVisibility(4);
        }
    }

    (CreateAdLayoutDisplay createadlayoutdisplay)
    {
        a = createadlayoutdisplay;
        super();
    }
}
