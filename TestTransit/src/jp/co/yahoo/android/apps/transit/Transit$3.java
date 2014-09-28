// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import android.widget.TextView;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            Transit

class this._cls0
    implements android.view.ckListener
{

    final Transit this$0;

    public void onClick(View view)
    {
        touchTapRD(getString(0x7f0d0430));
        String s = (new StringBuilder()).append(getString(0x7f0d0577)).append(view.getTag()).toString();
        launchInput(s, ((TextView)view).getText().toString(), (new StringBuilder()).append(getString(0x7f0d0167)).append(view.getTag()).append(getString(0x7f0d0168)).toString(), 5);
    }

    ()
    {
        this$0 = Transit.this;
        super();
    }
}
