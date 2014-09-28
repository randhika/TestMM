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
        touchTapRD(getString(0x7f0d042e));
        String s = Transit.access$100(Transit.this).getText().toString();
        if (s.equals(getString(0x7f0d0289)))
        {
            s = "";
        }
        launchInput(getString(0x7f0d0575), s, getString(0x7f0d0165), 3);
    }

    ()
    {
        this$0 = Transit.this;
        super();
    }
}
