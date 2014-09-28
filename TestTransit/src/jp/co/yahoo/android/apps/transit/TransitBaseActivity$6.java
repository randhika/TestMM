// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.view.View;
import jp.co.yahoo.android.apps.transit.timer.SettingTopActivity;
import jp.co.yahoo.android.common.hamburger.YSimpleSideDrawer;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, Transit, DiainfoActivity, TimeTableTopActivity, 
//            RailmapActivity, MyPageActivity, OthersActivity

class this._cls0
    implements android.view.ansitBaseActivity._cls6
{

    final TransitBaseActivity this$0;

    public void onClick(View view)
    {
        int i;
        Intent intent;
        i = ((Integer)view.getTag()).intValue();
        TransitBaseActivity.access$200(TransitBaseActivity.this, i);
        intent = null;
        i;
        JVM INSTR tableswitch 1 9: default 72
    //                   1 117
    //                   2 157
    //                   3 197
    //                   4 177
    //                   5 137
    //                   6 217
    //                   7 237
    //                   8 248
    //                   9 260;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
        if (intent != null)
        {
            intent.putExtra(getString(0x7f0d01ca), i);
            intent.setAction("android.intent.action.VIEW");
            startActivity(intent);
        }
        mDrawer.closeLeftSide();
        return;
_L2:
        intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/Transit);
        continue; /* Loop/switch isn't completed */
_L6:
        intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/DiainfoActivity);
        continue; /* Loop/switch isn't completed */
_L3:
        intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/TimeTableTopActivity);
        continue; /* Loop/switch isn't completed */
_L5:
        intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/RailmapActivity);
        continue; /* Loop/switch isn't completed */
_L4:
        intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/MyPageActivity);
        continue; /* Loop/switch isn't completed */
_L7:
        intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/OthersActivity);
        continue; /* Loop/switch isn't completed */
_L8:
        intent = TransitBaseActivity.access$300(TransitBaseActivity.this);
        continue; /* Loop/switch isn't completed */
_L9:
        showAroundStation();
        intent = null;
        continue; /* Loop/switch isn't completed */
_L10:
        intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/timer/SettingTopActivity);
        if (true) goto _L1; else goto _L11
_L11:
    }

    ty()
    {
        this$0 = TransitBaseActivity.this;
        super();
    }
}
