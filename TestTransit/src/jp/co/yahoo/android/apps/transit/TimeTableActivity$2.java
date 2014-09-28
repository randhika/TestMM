// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TimeTableActivity

class this._cls0
    implements android.widget.ctory
{

    final TimeTableActivity this$0;

    public View createTabContent(String s)
    {
        if (s.equals("1"))
        {
            return TimeTableActivity.access$000(TimeTableActivity.this);
        }
        if (s.equals("2"))
        {
            return TimeTableActivity.access$100(TimeTableActivity.this);
        }
        if (s.equals("3"))
        {
            return TimeTableActivity.access$200(TimeTableActivity.this);
        }
        if (s.equals("4"))
        {
            return TimeTableActivity.access$300(TimeTableActivity.this);
        }
        if (s.equals("5"))
        {
            return TimeTableActivity.access$400(TimeTableActivity.this);
        }
        if (s.equals("6"))
        {
            return TimeTableActivity.access$500(TimeTableActivity.this);
        }
        if (s.equals("7"))
        {
            return TimeTableActivity.access$600(TimeTableActivity.this);
        } else
        {
            return TimeTableActivity.access$700(TimeTableActivity.this);
        }
    }

    ()
    {
        this$0 = TimeTableActivity.this;
        super();
    }
}
