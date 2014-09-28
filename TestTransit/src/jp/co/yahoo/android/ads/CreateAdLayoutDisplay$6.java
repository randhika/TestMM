// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.view.View;

// Referenced classes of package jp.co.yahoo.android.ads:
//            CreateAdLayoutDisplay

class a
    implements android.view.tDisplay._cls6
{

    final String a;
    final CreateAdLayoutDisplay b;

    public void onClick(View view)
    {
        (new Thread(new <init>(b, a))).start();
    }

    (CreateAdLayoutDisplay createadlayoutdisplay, String s)
    {
        b = createadlayoutdisplay;
        a = s;
        super();
    }
}
