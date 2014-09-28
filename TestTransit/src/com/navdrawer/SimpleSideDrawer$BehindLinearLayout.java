// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.navdrawer;

import android.content.Context;
import android.graphics.Rect;
import android.widget.LinearLayout;

// Referenced classes of package com.navdrawer:
//            SimpleSideDrawer

private class this._cls0 extends LinearLayout
{

    final SimpleSideDrawer this$0;

    public void fitDisplay(Rect rect)
    {
        SimpleSideDrawer.access$8(SimpleSideDrawer.this).setPadding(rect.left, rect.top, 0, 0);
        requestLayout();
    }

    public (Context context)
    {
        this$0 = SimpleSideDrawer.this;
        super(context);
    }
}
