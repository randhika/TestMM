// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import jp.co.yahoo.android.apps.transit.timer.common.SettingShortcut;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownBaseActivity, SettingDivideActivity, SettingSkinActivity, SettingStartActivity, 
//            SettingStationActivity, SettingTimetableActivity

public class SettingTopActivity extends CountdownBaseActivity
{

    private int reqCode;

    public SettingTopActivity()
    {
        reqCode = -1;
    }

    public void createShortcut(View view)
    {
        touchTapRD(getString(0x7f0d0419));
        (new SettingShortcut(this)).showCreate(0x7f0d02ff, 0x7f0d00b6);
    }

    public void launchDivide(View view)
    {
        touchTapRD(getString(0x7f0d0420));
        startActivityForResult(new Intent(this, jp/co/yahoo/android/apps/transit/timer/SettingDivideActivity), getResources().getInteger(0x7f0c005b));
    }

    public void launchSkin(View view)
    {
        touchTapRD(getString(0x7f0d041f));
        startActivityForResult(new Intent(this, jp/co/yahoo/android/apps/transit/timer/SettingSkinActivity), getResources().getInteger(0x7f0c005c));
    }

    public void launchStartup(View view)
    {
        touchTapRD(getString(0x7f0d03f1));
        startActivityForResult(new Intent(this, jp/co/yahoo/android/apps/transit/timer/SettingStartActivity), getResources().getInteger(0x7f0c005d));
    }

    public void launchStation(View view)
    {
        touchTapRD(getString(0x7f0d041a));
        startActivityForResult(new Intent(this, jp/co/yahoo/android/apps/transit/timer/SettingStationActivity), getResources().getInteger(0x7f0c005f));
    }

    public void launchTimetable(View view)
    {
        touchTapRD(getString(0x7f0d0422));
        startActivityForResult(new Intent(this, jp/co/yahoo/android/apps/transit/timer/SettingTimetableActivity), getResources().getInteger(0x7f0c0060));
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        startUp();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f0300a6);
        setTitle(getString(0x7f0d04b8));
        reqCode = getIntent().getIntExtra(getString(0x7f0d01df), -1);
        if (!startUp() && reqCode == getResources().getInteger(0x7f0c005f))
        {
            Intent intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/timer/SettingStationActivity);
            intent.putExtra(getString(0x7f0d01ca), 9);
            startActivityForResult(intent, getResources().getInteger(0x7f0c005f));
        }
        SharedPreferences sharedpreferences = getSharedPreferences(getString(0x7f0d01a0), 0);
        if (sharedpreferences.getBoolean(getString(0x7f0d0237), true))
        {
            ((ImageView)findViewById(0x7f0a0314)).setVisibility(0);
            android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(getString(0x7f0d0237), false);
            editor.commit();
        }
    }

    public void onStart()
    {
        super.onStart();
        dispAd(this, "2080325027", "pv");
    }
}
