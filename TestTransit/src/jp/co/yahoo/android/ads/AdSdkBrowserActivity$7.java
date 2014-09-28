// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

// Referenced classes of package jp.co.yahoo.android.ads:
//            AdSdkBrowserActivity

class a
    implements android.view.Activity._cls7
{

    final AdSdkBrowserActivity a;

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        if (motionevent.getAction() != 0) goto _L2; else goto _L1
_L1:
        AdSdkBrowserActivity.access$1000(a).setImageBitmap(AdSdkBrowserActivity.access$900(a));
_L4:
        return false;
_L2:
        if (motionevent.getAction() == 1)
        {
            AdSdkBrowserActivity.access$1000(a).setImageBitmap(AdSdkBrowserActivity.access$1100(a));
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    (AdSdkBrowserActivity adsdkbrowseractivity)
    {
        a = adsdkbrowseractivity;
        super();
    }
}
