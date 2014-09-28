// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            Transit

class this._cls0
    implements android.view.ckListener
{

    final Transit this$0;

    public void onClick(View view)
    {
        if (Transit.access$200(Transit.this) < 1)
        {
            return;
        } else
        {
            int _tmp = Transit.access$210(Transit.this);
            String s = (String)view.getTag();
            mergeVia(null, Integer.parseInt(s));
            return;
        }
    }

    ()
    {
        this$0 = Transit.this;
        super();
    }
}
