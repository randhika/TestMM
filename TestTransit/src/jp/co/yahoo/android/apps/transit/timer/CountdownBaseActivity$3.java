// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.view.View;
import android.widget.AdapterView;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.timer.db.SkinDb;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownBaseActivity, CountdownListActivity, CountdownActivity

class val.activity
    implements android.widget.edListener
{

    final CountdownBaseActivity this$0;
    final CountdownBaseActivity val$activity;
    final splayModeAdapter val$adapter;

    public void onItemSelected(AdapterView adapterview, View view, int i, long l)
    {
        jp.co.yahoo.android.apps.transit.timer.db..activity activity1 = (jp.co.yahoo.android.apps.transit.timer.db..activity)val$adapter.getItem(i);
        if (!(val$activity instanceof CountdownListActivity)) goto _L2; else goto _L1
_L1:
        if (TransitUtil.isEmpty(activity1.activity)) goto _L4; else goto _L3
_L3:
        if (!activity1.wnloaded) goto _L6; else goto _L5
_L5:
        skindb.updateSetting(activity1.b);
        ((CountdownListActivity)val$activity).launchCountDown();
_L4:
        return;
_L6:
        launchSettingSkin();
        return;
_L2:
        if (val$activity instanceof CountdownActivity)
        {
            if (TransitUtil.isEmpty(activity1.activity))
            {
                touchTapRD(getString(0x7f0d0432));
                if (!((CountdownActivity)val$activity).launchCountdownList())
                {
                    supportInvalidateOptionsMenu();
                    return;
                }
            } else
            {
                ((CountdownActivity)val$activity).changeSkin(activity1);
                return;
            }
        }
        if (true) goto _L4; else goto _L7
_L7:
    }

    public void onNothingSelected(AdapterView adapterview)
    {
    }

    splayModeAdapter()
    {
        this$0 = final_countdownbaseactivity;
        val$adapter = splaymodeadapter;
        val$activity = CountdownBaseActivity.this;
        super();
    }
}
