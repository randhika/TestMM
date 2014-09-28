// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.navdrawer;

import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Scroller;

// Referenced classes of package com.navdrawer:
//            SimpleSideDrawer

class agAction extends agAction
{

    final SimpleSideDrawer this$0;

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag = true;
        0xff & motionevent.getAction();
        JVM INSTR tableswitch 0 2: default 36
    //                   0 38
    //                   1 91
    //                   2 182;
           goto _L1 _L2 _L3 _L4
_L1:
        return false;
_L2:
        float f3 = motionevent.getX();
        agAction.access._mth1(SimpleSideDrawer.access$0(SimpleSideDrawer.this), f3);
        agAction agaction1 = SimpleSideDrawer.access$0(SimpleSideDrawer.this);
        if (SimpleSideDrawer.access$1(SimpleSideDrawer.this).getScrollX() == 0)
        {
            flag = false;
        }
        agAction.access._mth2(agaction1, flag);
        return false;
_L3:
        if (agAction.access._mth3(SimpleSideDrawer.access$0(SimpleSideDrawer.this)))
        {
            int j = SimpleSideDrawer.access$1(SimpleSideDrawer.this).getScrollX();
            int k;
            if (agAction.access._mth4(SimpleSideDrawer.access$0(SimpleSideDrawer.this)))
            {
                k = -(j + SimpleSideDrawer.access$2(SimpleSideDrawer.this));
            } else
            {
                k = -j;
            }
            SimpleSideDrawer.access$3(SimpleSideDrawer.this).startScroll(j, 0, k, 0, SimpleSideDrawer.access$4(SimpleSideDrawer.this));
            invalidate();
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (agAction.access._mth3(SimpleSideDrawer.access$0(SimpleSideDrawer.this)))
        {
            float f = motionevent.getX();
            float f1 = -(f - agAction.access._mth5(SimpleSideDrawer.access$0(SimpleSideDrawer.this)));
            int i = SimpleSideDrawer.access$1(SimpleSideDrawer.this).getScrollX();
            agAction agaction = SimpleSideDrawer.access$0(SimpleSideDrawer.this);
            float f2;
            if (agAction.access._mth5(SimpleSideDrawer.access$0(SimpleSideDrawer.this)) >= f)
            {
                flag = false;
            }
            agAction.access._mth6(agaction, flag);
            agAction.access._mth1(SimpleSideDrawer.access$0(SimpleSideDrawer.this), f);
            f2 = f1 + (float)i;
            if (0.0F < f2)
            {
                SimpleSideDrawer.access$1(SimpleSideDrawer.this).scrollTo(0, 0);
                return false;
            }
            if (f2 < (float)(-SimpleSideDrawer.access$2(SimpleSideDrawer.this)))
            {
                SimpleSideDrawer.access$1(SimpleSideDrawer.this).scrollTo(-SimpleSideDrawer.access$2(SimpleSideDrawer.this), 0);
                return false;
            } else
            {
                SimpleSideDrawer.access$1(SimpleSideDrawer.this).scrollBy((int)f1, 0);
                return false;
            }
        }
        if (true) goto _L1; else goto _L5
_L5:
    }

    agAction()
    {
        this$0 = SimpleSideDrawer.this;
        super(SimpleSideDrawer.this, null);
    }
}
