// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.viewparts;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import jp.co.yahoo.android.apps.transit.timer.CountdownActivity;
import jp.co.yahoo.android.apps.transit.timer.CountdownBaseActivity;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.viewparts:
//            CountdownListView

class val.nId
    implements android.view.
{

    final CountdownListView this$0;
    final int val$nId;
    final int val$type;
    final int val$week;

    public void onClick(View view)
    {
        Intent intent = new Intent(view.getContext(), jp/co/yahoo/android/apps/transit/timer/CountdownActivity);
        intent.putExtra(view.getContext().getString(0x7f0d0247), val$type);
        intent.putExtra(view.getContext().getString(0x7f0d024c), val$week);
        intent.putExtra(view.getContext().getString(0x7f0d0246), val$nId);
        ((CountdownBaseActivity)view.getContext()).startActivityForResult(intent, getResources().getInteger(0x7f0c003b));
    }

    ()
    {
        this$0 = final_countdownlistview;
        val$type = i;
        val$week = j;
        val$nId = I.this;
        super();
    }
}
