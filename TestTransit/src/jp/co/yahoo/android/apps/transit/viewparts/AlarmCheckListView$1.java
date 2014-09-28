// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.widget.CompoundButton;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            AlarmCheckListView

class this._cls0
    implements android.widget.kedChangeListener
{

    final AlarmCheckListView this$0;

    public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        if (AlarmCheckListView.access$000(AlarmCheckListView.this) != null)
        {
            AlarmCheckListView.access$000(AlarmCheckListView.this).onCheckedChanged(compoundbutton, flag);
        }
    }

    ItemCheckedChangeListener()
    {
        this$0 = AlarmCheckListView.this;
        super();
    }
}
