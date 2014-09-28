// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import android.widget.AdapterView;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            HomeDetailConditionActivity

class this._cls0
    implements android.widget.ener
{

    final HomeDetailConditionActivity this$0;

    public void onItemSelected(AdapterView adapterview, View view, int i, long l)
    {
        switch (i)
        {
        default:
            HomeDetailConditionActivity.access$000(HomeDetailConditionActivity.this).ticket = getString(0x7f0d0582);
            return;

        case 1: // '\001'
            HomeDetailConditionActivity.access$000(HomeDetailConditionActivity.this).ticket = getString(0x7f0d0583);
            break;
        }
    }

    public void onNothingSelected(AdapterView adapterview)
    {
    }

    ()
    {
        this$0 = HomeDetailConditionActivity.this;
        super();
    }
}
