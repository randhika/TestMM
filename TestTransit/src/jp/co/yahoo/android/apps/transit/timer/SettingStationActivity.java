// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import jp.co.yahoo.android.apps.transit.InputSearch;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.common.Alerm;
import jp.co.yahoo.android.apps.transit.timer.db.SettingDb;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownBaseActivity

public class SettingStationActivity extends CountdownBaseActivity
    implements android.view.View.OnClickListener, android.view.View.OnLongClickListener
{

    private ArrayList aryGoal;
    private ArrayList aryHome;
    private TextView btnCount;
    private Button btnGoal;
    private Button btnHome;
    private SettingDb db;
    private TextView noSetGoal;
    private TextView noSetHome;
    private StationData selStation;
    private LinearLayout toGoal;
    private LinearLayout toHome;

    public SettingStationActivity()
    {
        db = null;
    }

    private void showListView(ArrayList arraylist, LinearLayout linearlayout)
    {
        linearlayout.removeAllViews();
        if (arraylist != null && arraylist.size() > 0)
        {
            for (int i = 0; i < arraylist.size(); i++)
            {
                StationData stationdata = (StationData)arraylist.get(i);
                LinearLayout linearlayout1 = (LinearLayout)inflater.inflate(0x7f03007b, null);
                ((TextView)linearlayout1.findViewById(0x7f0a0203)).setText(Integer.toString(i + 1));
                ((TextView)linearlayout1.findViewById(0x7f0a0270)).setText(stationdata.getName());
                ((TextView)linearlayout1.findViewById(0x7f0a0271)).setText((new StringBuilder()).append(stationdata.getRailname()).append(" ").append(stationdata.getDirname()).append(getString(0x7f0d0275)).toString());
                linearlayout1.setTag(stationdata);
                linearlayout1.setOnLongClickListener(this);
                linearlayout1.setOnClickListener(this);
                if (stationdata.isSetting())
                {
                    ((ImageView)linearlayout1.findViewById(0x7f0a0272)).setVisibility(0);
                }
                linearlayout.addView(linearlayout1);
                linearlayout.addView((ImageView)inflater.inflate(0x7f030059, null));
            }

        }
    }

    private void updateSetting(StationData stationdata)
    {
        ArrayList arraylist;
        Iterator iterator;
        if (stationdata.getSettingType() == getResources().getInteger(0x7f0c0074))
        {
            arraylist = aryHome;
        } else
        {
            arraylist = aryGoal;
        }
        for (iterator = arraylist.iterator(); iterator.hasNext();)
        {
            StationData stationdata1 = (StationData)iterator.next();
            if (stationdata1.equals(stationdata))
            {
                stationdata1.setSetting(true);
            } else
            {
                stationdata1.setSetting(false);
            }
        }

    }

    public void launchCountDown(View view)
    {
        startCountDown();
    }

    public void launchSearch(int i)
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/InputSearch);
        intent.putExtra(getString(0x7f0d0233), 1);
        intent.putExtra(getString(0x7f0d01c3), false);
        intent.putExtra(getString(0x7f0d01de), false);
        intent.putExtra(getString(0x7f0d01df), getResources().getInteger(0x7f0c005f));
        intent.putExtra(getString(0x7f0d0243), String.valueOf(i));
        int j;
        if (i == getResources().getInteger(0x7f0c0074))
        {
            j = 0x7f0d016a;
        } else
        {
            j = 0x7f0d0163;
        }
        intent.putExtra(getString(0x7f0d022d), getString(j));
        startActivityForResult(intent, getResources().getInteger(0x7f0c0046));
    }

    public void launchSearchGoal(View view)
    {
        launchSearch(getResources().getInteger(0x7f0c0073));
    }

    public void launchSearchHome(View view)
    {
        launchSearch(getResources().getInteger(0x7f0c0074));
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        if (j == 0 && getResources().getInteger(0x7f0c003b) == i)
        {
            aryHome = null;
            aryGoal = null;
            show();
        }
    }

    public void onClick(View view)
    {
        StationData stationdata = (StationData)view.getTag();
        stationdata.setSetting(true);
        updateSetting(stationdata);
        show();
        db.updateSetting(stationdata, stationdata.getSettingType());
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f0300a4);
        setTitle(getString(0x7f0d04da));
        toHome = (LinearLayout)findViewById(0x7f0a030d);
        toGoal = (LinearLayout)findViewById(0x7f0a0311);
        btnHome = (Button)findViewById(0x7f0a030b);
        btnGoal = (Button)findViewById(0x7f0a030f);
        btnCount = (TextView)findViewById(0x7f0a02f2);
        noSetHome = (TextView)findViewById(0x7f0a030c);
        noSetGoal = (TextView)findViewById(0x7f0a0310);
        show();
    }

    public boolean onLongClick(View view)
    {
        selStation = (StationData)view.getTag();
        showEditDialog(selStation, selStation.getSettingType());
        return true;
    }

    public void onStart()
    {
        super.onStart();
        dispAd(this, "2080325029", "pv");
    }

    protected void show()
    {
        selStation = null;
        db = new SettingDb(this);
        if (aryHome == null && aryGoal == null)
        {
            aryHome = db.getAllStation(getResources().getInteger(0x7f0c0074));
            aryGoal = db.getAllStation(getResources().getInteger(0x7f0c0073));
        }
        showListView(aryHome, toHome);
        showListView(aryGoal, toGoal);
        if (aryHome.size() > 5)
        {
            btnHome.setVisibility(8);
        } else
        {
            btnHome.setVisibility(0);
        }
        if (aryHome.size() < 1)
        {
            noSetHome.setVisibility(0);
        } else
        {
            noSetHome.setVisibility(8);
        }
        if (aryGoal.size() > 5)
        {
            btnGoal.setVisibility(8);
        } else
        {
            btnGoal.setVisibility(0);
        }
        if (aryGoal.size() < 1)
        {
            noSetGoal.setVisibility(0);
        } else
        {
            noSetGoal.setVisibility(8);
        }
        if (aryHome.size() == 0 && aryGoal.size() == 0)
        {
            btnCount.setEnabled(false);
        }
    }

    public void showEditDialog(StationData stationdata, int i)
    {
        selStation = stationdata;
        (new TransitDialogBuilder(this)).setTitleWarnning(stationdata.getName()).setMessage((new StringBuilder()).append(stationdata.getRailname()).append(" ").append(stationdata.getDirname()).append(getString(0x7f0d0275)).toString()).setNegativeButton(getString(0x7f0d0071), new android.content.DialogInterface.OnClickListener() {

            final SettingStationActivity this$0;

            public void onClick(DialogInterface dialoginterface, int j)
            {
                dialoginterface.cancel();
                selStation = null;
            }

            
            {
                this$0 = SettingStationActivity.this;
                super();
            }
        }).setPositiveButton(getString(0x7f0d0069), new android.content.DialogInterface.OnClickListener() {

            final SettingStationActivity this$0;

            public void onClick(DialogInterface dialoginterface, int j)
            {
                if (selStation != null)
                {
                    db.deleteData(selStation);
                    (new Alerm(SettingStationActivity.this)).delAlarmByTimetableId(Integer.parseInt(selStation.getId()));
                    if (selStation.isSetting())
                    {
                        StationData stationdata1 = db.getStation(selStation.getSettingType());
                        if (stationdata1 != null)
                        {
                            db.updateSetting(stationdata1, selStation.getSettingType());
                        }
                    }
                }
                aryHome = null;
                aryGoal = null;
                show();
                dialoginterface.cancel();
            }

            
            {
                this$0 = SettingStationActivity.this;
                super();
            }
        }).show().setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

            final SettingStationActivity this$0;

            public void onCancel(DialogInterface dialoginterface)
            {
                selStation = null;
            }

            
            {
                this$0 = SettingStationActivity.this;
                super();
            }
        });
    }



/*
    static StationData access$002(SettingStationActivity settingstationactivity, StationData stationdata)
    {
        settingstationactivity.selStation = stationdata;
        return stationdata;
    }

*/



/*
    static ArrayList access$202(SettingStationActivity settingstationactivity, ArrayList arraylist)
    {
        settingstationactivity.aryHome = arraylist;
        return arraylist;
    }

*/


/*
    static ArrayList access$302(SettingStationActivity settingstationactivity, ArrayList arraylist)
    {
        settingstationactivity.aryGoal = arraylist;
        return arraylist;
    }

*/
}
