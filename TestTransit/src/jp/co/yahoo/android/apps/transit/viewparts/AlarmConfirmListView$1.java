// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.widget.CompoundButton;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            AlarmConfirmListView

class this._cls0
    implements android.widget.dChangeListener
{

    final AlarmConfirmListView this$0;

    public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        if (AlarmConfirmListView.access$000(AlarmConfirmListView.this) != null)
        {
            AlarmConfirmListView.access$000(AlarmConfirmListView.this).onCheckedChanged(compoundbutton, flag);
        }
    }

    ItemCheckedChangeListener()
    {
        this$0 = AlarmConfirmListView.this;
        super();
    }
}
