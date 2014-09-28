// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.api.data.TimeTableItemData;
import jp.co.yahoo.android.apps.transit.timer.common.CountdownListManager;
import jp.co.yahoo.android.apps.transit.timer.db.SettingDb;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownBaseActivity, CountdownActivity

public class CountdownListActivity extends CountdownBaseActivity
{

    private StationData Station;
    ArrayAdapter arrayAdapter;
    private LinearLayout body;
    private CountdownListManager objCountdown;
    private Resources res;
    private int type;
    private int week;
    private int window_option;

    public CountdownListActivity()
    {
        window_option = 128;
        res = null;
        type = 1;
        week = -1;
        objCountdown = null;
    }

    private void settingData()
    {
        SettingDb settingdb = new SettingDb(this);
        if (type != res.getInteger(0x7f0c0074)) goto _L2; else goto _L1
_L1:
        if (Station == null)
        {
            Station = settingdb.getTimetable(getResources().getInteger(0x7f0c0074));
        }
_L4:
        return;
_L2:
        if (type != res.getInteger(0x7f0c0073))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (Station != null) goto _L4; else goto _L3
_L3:
        Station = settingdb.getTimetable(getResources().getInteger(0x7f0c0073));
        return;
        if (type != res.getInteger(0x7f0c0072) || Station != null) goto _L4; else goto _L5
_L5:
        Station = settingdb.getTimetable(res.getInteger(0x7f0c0072), 0, -1);
        return;
    }

    private void showCountdown()
    {
        TextView textview = (TextView)findViewById(0x7f0a01fe);
        TextView textview1 = (TextView)findViewById(0x7f0a01ff);
        TextView textview2 = (TextView)findViewById(0x7f0a0200);
        if (Station.getRailname() != null)
        {
            textview.setText(Station.getRailname());
        }
        if (Station.getDirname() != null)
        {
            textview1.setText((new StringBuilder()).append(Station.getDirname()).append(res.getString(0x7f0d0275)).toString());
        }
        if (Station.getName() != null)
        {
            textview2.setText(Station.getName());
        }
        if (objCountdown != null)
        {
            objCountdown.stop();
            objCountdown = null;
        }
        objCountdown = new CountdownListManager(this, body, new jp.co.yahoo.android.apps.transit.timer.common.CountdownManager.CountdownListener() {

            final CountdownListActivity this$0;

            public boolean changeWeek(int i)
            {
                return false;
            }

            public boolean end(TimeTableItemData timetableitemdata)
            {
                return false;
            }

            public boolean onAllFiltered()
            {
                return false;
            }

            public boolean onNoTimetable()
            {
                return false;
            }

            public void updateTarget(TimeTableItemData timetableitemdata)
            {
            }

            public void updateTime(int i)
            {
            }

            
            {
                this$0 = CountdownListActivity.this;
                super();
            }
        });
        objCountdown.setType(type);
        objCountdown.setWeek(week);
        objCountdown.setCountDown(Station.getTimetable());
        objCountdown.startCountDown();
    }

    public void finish()
    {
        if (objCountdown != null)
        {
            objCountdown.setListener(null);
            objCountdown.stop();
        }
        super.finish();
    }

    public void launchCountDown()
    {
        Intent intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/timer/CountdownActivity);
        if (type < 0)
        {
            showSettingDialog(false, null);
            return;
        } else
        {
            intent.putExtra(getString(0x7f0d0247), type);
            intent.putExtra(getString(0x7f0d024c), week);
            startActivityForResult(intent, getResources().getInteger(0x7f0c003b));
            return;
        }
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (i == getResources().getInteger(0x7f0c003b))
        {
            supportInvalidateOptionsMenu();
        } else
        if (i == res.getInteger(0x7f0c005c))
        {
            if (j == -1)
            {
                launchCountDown();
            }
            supportInvalidateOptionsMenu();
            return;
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030062);
        setTitle(getString(0x7f0d0349));
        res = getResources();
        getWindow().addFlags(window_option);
        Intent intent = getIntent();
        type = intent.getIntExtra(getString(0x7f0d0247), -1);
        week = intent.getIntExtra(getString(0x7f0d024c), -1);
        settingData();
        body = (LinearLayout)findViewById(0x7f0a0201);
        showCountdown();
    }

    public void onDestroy()
    {
        if (objCountdown != null)
        {
            objCountdown.setListener(null);
            objCountdown.stop();
        }
        super.onDestroy();
        getWindow().clearFlags(window_option);
    }

    public void onRestart()
    {
        super.onRestart();
        supportInvalidateOptionsMenu();
    }

    public void onStart()
    {
        super.onStart();
        if (type == res.getInteger(0x7f0c0074))
        {
            dispAd(this, "2080325049", "pv");
            return;
        }
        if (type == res.getInteger(0x7f0c0073))
        {
            dispAd(this, "2080325050", "pv");
            return;
        } else
        {
            dispAd(this, "2080325051", "pv");
            return;
        }
    }

    public void onStop()
    {
        super.onStop();
    }
}
