// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownActivity

class this._cls0
    implements android.content.ckListener
{

    final CountdownActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        int j;
        if (i == 0)
        {
            j = 1;
        } else
        if (i == 1)
        {
            j = 2;
        } else
        {
            j = 4;
        }
        changeWeekActivity(j);
    }

    A()
    {
        this$0 = CountdownActivity.this;
        super();
    }
}
