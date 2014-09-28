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
    implements android.view.Activity._cls9
{

    final AdSdkBrowserActivity a;

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        if (!AdSdkBrowserActivity.access$1200(a)) goto _L2; else goto _L1
_L1:
        if (motionevent.getAction() != 0) goto _L4; else goto _L3
_L3:
        AdSdkBrowserActivity.access$1400(a).setImageBitmap(AdSdkBrowserActivity.access$1300(a));
_L6:
        return false;
_L4:
        if (motionevent.getAction() == 1)
        {
            AdSdkBrowserActivity.access$1400(a).setImageBitmap(AdSdkBrowserActivity.access$1500(a));
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (motionevent.getAction() == 0)
        {
            AdSdkBrowserActivity.access$1400(a).setImageBitmap(AdSdkBrowserActivity.access$1600(a));
        } else
        if (motionevent.getAction() == 1)
        {
            AdSdkBrowserActivity.access$1400(a).setImageBitmap(AdSdkBrowserActivity.access$1700(a));
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    (AdSdkBrowserActivity adsdkbrowseractivity)
    {
        a = adsdkbrowseractivity;
        super();
    }
}
