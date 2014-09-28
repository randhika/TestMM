// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import android.widget.TabHost;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TimeTableActivity

class this._cls0
    implements android.widget.istener
{

    final TimeTableActivity this$0;

    public void onTabChanged(String s)
    {
        int i = TimeTableActivity.access$800(TimeTableActivity.this).getCurrentView().getId();
        if (i != 0x7f0a0034 && i != 0x7f0a0035) goto _L2; else goto _L1
_L1:
        TimeTableActivity.access$902(TimeTableActivity.this, 10);
_L4:
        TimeTableActivity.access$1000(TimeTableActivity.this);
        return;
_L2:
        if (i == 0x7f0a003a || i == 0x7f0a0037)
        {
            TimeTableActivity.access$902(TimeTableActivity.this, 1);
        } else
        if (i == 0x7f0a003b || i == 0x7f0a0038)
        {
            TimeTableActivity.access$902(TimeTableActivity.this, 2);
        } else
        if (i == 0x7f0a0039 || i == 0x7f0a0036)
        {
            TimeTableActivity.access$902(TimeTableActivity.this, 4);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    ()
    {
        this$0 = TimeTableActivity.this;
        super();
    }
}
