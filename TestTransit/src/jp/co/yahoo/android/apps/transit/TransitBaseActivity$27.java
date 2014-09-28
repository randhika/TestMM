// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import jp.co.yahoo.android.apps.transit.timer.SettingStationActivity;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class this._cls0
    implements android.content.Listener
{

    final TransitBaseActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        Intent intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/timer/SettingStationActivity);
        intent.putExtra(getString(0x7f0d01ca), 9);
        startActivityForResult(intent, getResources().getInteger(0x7f0c005f));
    }

    ivity()
    {
        this$0 = TransitBaseActivity.this;
        super();
    }
}
