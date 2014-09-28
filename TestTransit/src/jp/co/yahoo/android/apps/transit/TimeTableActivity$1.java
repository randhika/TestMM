// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import android.widget.AdapterView;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TimeTableActivity

class this._cls0
    implements android.widget.lectedListener
{

    final TimeTableActivity this$0;

    public void onItemSelected(AdapterView adapterview, View view, int i, long l)
    {
        if (i == 0)
        {
            touchTapRD((new StringBuilder()).append(getString(0x7f0d03d7)).append("/").append(getString(0x7f0d03f2)).toString());
            setDisplayMode(1);
            return;
        } else
        {
            touchTapRD((new StringBuilder()).append(getString(0x7f0d03d7)).append("/").append(getString(0x7f0d0434)).toString());
            setDisplayMode(2);
            return;
        }
    }

    public void onNothingSelected(AdapterView adapterview)
    {
    }

    ()
    {
        this$0 = TimeTableActivity.this;
        super();
    }
}
