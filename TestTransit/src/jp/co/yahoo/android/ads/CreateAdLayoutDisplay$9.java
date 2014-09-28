// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.content.Context;

// Referenced classes of package jp.co.yahoo.android.ads:
//            AdContainer, CreateAdLayoutDisplay

class b extends AdContainer
{

    final CreateAdLayoutDisplay b;

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        CreateAdLayoutDisplay.d(b);
    }

    (CreateAdLayoutDisplay createadlayoutdisplay, Context context)
    {
        b = createadlayoutdisplay;
        super(context);
    }
}
