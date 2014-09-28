// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.navdrawer;

import android.view.View;
import android.widget.LinearLayout;

// Referenced classes of package com.navdrawer:
//            SimpleSideDrawer

class this._cls0
    implements android.view.r
{

    final SimpleSideDrawer this$0;

    public void onClick(View view)
    {
        if (SimpleSideDrawer.access$9(SimpleSideDrawer.this).getVisibility() != 8)
        {
            closeLeftSide();
        } else
        if (SimpleSideDrawer.access$10(SimpleSideDrawer.this).getVisibility() != 8)
        {
            closeRightSide();
            return;
        }
    }

    ()
    {
        this$0 = SimpleSideDrawer.this;
        super();
    }
}
