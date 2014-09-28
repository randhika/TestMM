// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TimeTableActivity

class this._cls0
    implements android.content.ickListener
{

    final TimeTableActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if (i == 0)
        {
            TimeTableActivity.access$2400(TimeTableActivity.this, 1, 0);
            return;
        } else
        {
            TimeTableActivity.access$2500(TimeTableActivity.this);
            return;
        }
    }

    ()
    {
        this$0 = TimeTableActivity.this;
        super();
    }
}
