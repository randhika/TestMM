// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.common.Alerm;
import jp.co.yahoo.android.apps.transit.timer.db.SettingDb;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownBaseActivity

public class SettingTimetableActivity extends CountdownBaseActivity
    implements android.view.View.OnClickListener, android.widget.CompoundButton.OnCheckedChangeListener
{

    private Alerm alerm;
    private boolean bInUpdate;
    private boolean bUpdate;
    private SettingDb station;
    private CompoundButton toggle;
    private TextView txtUpdate;
    private AlermData updateData;

    public SettingTimetableActivity()
    {
        station = null;
        bUpdate = false;
        bInUpdate = false;
        alerm = null;
        updateData = null;
        txtUpdate = null;
        toggle = null;
    }

    private void saveSetting()
    {
        alerm.setAlerm(updateData, bUpdate);
        bUpdate = true;
    }

    private void showTimetableSetting()
    {
        toggle.setChecked(updateData.isSetting());
        toggle.setOnCheckedChangeListener(this);
        ArrayList arraylist;
        if (!bUpdate)
        {
            txtUpdate.setText(getString(0x7f0d0062));
        } else
        {
            String s = getString(0x7f0d015f);
            int i = updateData.getStartTime() / 3600;
            int j = (updateData.getStartTime() % 3600) / 60;
            String s1 = (new StringBuilder()).append(s).append(CountdownUtil.getZeroNum(i)).append(":").append(CountdownUtil.getZeroNum(j)).toString();
            txtUpdate.setText(s1);
        }
        txtUpdate.setOnClickListener(this);
        arraylist = station.getAllStation(0);
        if (arraylist == null || arraylist.size() < 1)
        {
            ((TextView)findViewById(0x7f0a02f4)).setVisibility(0);
        } else
        {
            LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a0313);
            linearlayout.removeAllViews();
            int k = 0;
            while (k < arraylist.size()) 
            {
                StationData stationdata = (StationData)arraylist.get(k);
                LinearLayout linearlayout1 = (LinearLayout)inflater.inflate(0x7f03007d, null);
                TextView textview = (TextView)linearlayout1.findViewById(0x7f0a0270);
                TextView textview1 = (TextView)linearlayout1.findViewById(0x7f0a0271);
                TextView textview2 = (TextView)linearlayout1.findViewById(0x7f0a0274);
                textview.setText(stationdata.getName());
                textview1.setText((new StringBuilder()).append(stationdata.getRailname()).append(" ").append(stationdata.getDirname()).append(getString(0x7f0d0275)).toString());
                textview2.setText((new StringBuilder()).append(getString(0x7f0d0326)).append(" ").append(stationdata.getUpdateDate().substring(0, 16)).toString());
                Button button = (Button)linearlayout1.findViewById(0x7f0a0275);
                button.setTag(stationdata);
                button.setOnClickListener(this);
                ImageView imageview = (ImageView)inflater.inflate(0x7f030059, null);
                linearlayout.addView(linearlayout1);
                linearlayout.addView(imageview);
                k++;
            }
        }
    }

    public void endGetAllTimetable(Bundle bundle, StationData stationdata, int i)
    {
        if (bundle != null && stationdata != null)
        {
            station.updateTimetable(stationdata, bundle);
            showTimetableSetting();
            Toast.makeText(this, getString(0x7f0d054c), 0).show();
        } else
        {
            showErrorMessageDialog(getString(0x7f0d010b), getString(0x7f0d014f));
        }
        bInUpdate = false;
    }

    public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        if (!bUpdate)
        {
            showStartTimeDialog();
            return;
        } else
        {
            updateData.setSetting(flag);
            saveSetting();
            return;
        }
    }

    public void onClick(View view)
    {
        if (view.equals(txtUpdate))
        {
            showStartTimeDialog();
        } else
        if (!bInUpdate)
        {
            bInUpdate = true;
            updateData((StationData)view.getTag());
            return;
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f0300a5);
        setTitle(getString(0x7f0d04db));
        station = new SettingDb(this);
        alerm = new Alerm(this);
        updateData = alerm.getUpdateAlerm();
        txtUpdate = (TextView)findViewById(0x7f0a0312);
        toggle = (CompoundButton)findViewById(0x7f0a0135);
        if (updateData == null)
        {
            bUpdate = false;
            Calendar calendar = Calendar.getInstance();
            int i = calendar.get(11);
            int j = calendar.get(12);
            updateData = new AlermData();
            updateData.setType(AlermData.TYPE_UPDATE);
            updateData.setRepeat("8");
            updateData.setStartTime(i * 3600 + j * 60);
            updateData.setCountdownTime(0);
            updateData.setLine("");
            updateData.setSetting(false);
        } else
        {
            bUpdate = true;
        }
        showTimetableSetting();
    }

    public void onStart()
    {
        super.onStart();
        dispAd(this, "2080325038", "pv");
    }

    public void showStartTimeDialog()
    {
        int i;
        int j;
        if (bUpdate)
        {
            i = updateData.getStartTime() / 3600;
            j = (updateData.getStartTime() % 3600) / 60;
        } else
        {
            Calendar calendar = Calendar.getInstance();
            i = calendar.get(11);
            j = calendar.get(12);
        }
        showTimePickerDialog(getString(0x7f0d0510), i, j, new android.app.TimePickerDialog.OnTimeSetListener() {

            final SettingTimetableActivity this$0;

            public void onTimeSet(TimePicker timepicker, int k, int l)
            {
                updateData.setStartTime(60 * (k * 60) + l * 60);
                String s = getString(0x7f0d015f);
                int i1 = updateData.getStartTime() / 3600;
                int j1 = (updateData.getStartTime() % 3600) / 60;
                String s1 = (new StringBuilder()).append(s).append(CountdownUtil.getZeroNum(i1)).append(":").append(CountdownUtil.getZeroNum(j1)).toString();
                txtUpdate.setText(s1);
                updateData.setSetting(true);
                toggle.setChecked(true);
                saveSetting();
            }

            
            {
                this$0 = SettingTimetableActivity.this;
                super();
            }
        });
    }

    public void updateData(StationData stationdata)
    {
        getAllTimetable(stationdata);
    }




}
