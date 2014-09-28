// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.timer.api.StationSearch;
import jp.co.yahoo.android.apps.transit.timer.api.data.RailDirectionData;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.db.SettingDb;
import jp.co.yahoo.android.yolp.common.ApiBase;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownBaseActivity, SettingStationActivity, CountdownActivity

public class RailDirectionActivity extends CountdownBaseActivity
{

    private int nTargetCode;
    private ArrayList railDirection;
    private StationData station;
    private int type;

    public RailDirectionActivity()
    {
        station = null;
        nTargetCode = 0;
        type = 1;
    }

    private void launchBack()
    {
        Intent intent = new Intent();
        intent.putExtra(getString(0x7f0d0247), type);
        setResult(-1, intent);
        finish();
    }

    private void launchCancel()
    {
        Intent intent = new Intent();
        intent.putExtra(getString(0x7f0d0247), type);
        setResult(0, intent);
        finish();
    }

    private void showList(ArrayList arraylist)
    {
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a02a3);
        linearlayout.setOrientation(1);
        int i = getResources().getDimensionPixelSize(0x7f0b003e);
        String s = "";
label0:
        for (int j = 0; j < arraylist.size(); j++)
        {
            RailDirectionData raildirectiondata = (RailDirectionData)arraylist.get(j);
            if (s.equals(raildirectiondata.getRailName()))
            {
                continue;
            }
            s = raildirectiondata.getRailName();
            LinearLayout linearlayout1 = new LinearLayout(this);
            linearlayout1.setOrientation(1);
            TextView textview = new TextView(this);
            textview.setBackgroundColor(getResources().getColor(0x7f090036));
            textview.setText(raildirectiondata.getRailName());
            textview.setPadding(i, i, i, i);
            textview.setTextAppearance(this, 0x7f0e0016);
            linearlayout1.addView(textview);
            linearlayout.addView(linearlayout1);
            int k = j;
            do
            {
                if (k >= arraylist.size())
                {
                    continue label0;
                }
                RailDirectionData raildirectiondata1 = (RailDirectionData)arraylist.get(k);
                if (!s.equals(raildirectiondata1.getRailName()))
                {
                    break;
                }
                TextView textview1 = (TextView)inflater.inflate(0x7f030077, null);
                textview1.setText((new StringBuilder()).append(raildirectiondata1.getDirection()).append(getString(0x7f0d0512)).toString());
                textview1.setTag(0x7f0d01b3, raildirectiondata1);
                textview1.setOnClickListener(new android.view.View.OnClickListener() {

                    final RailDirectionActivity this$0;

                    public void onClick(View view)
                    {
                        RailDirectionData raildirectiondata2 = (RailDirectionData)view.getTag(0x7f0d01b3);
                        station.setDirid(raildirectiondata2.getGroupid());
                        station.setDirname(raildirectiondata2.getDirection());
                        station.setRailname(raildirectiondata2.getRailName());
                        if (nTargetCode == getResources().getInteger(0x7f0c003b))
                        {
                            getAllTimetableToday(station);
                            return;
                        } else
                        {
                            getAllTimetable(station);
                            return;
                        }
                    }

            
            {
                this$0 = RailDirectionActivity.this;
                super();
            }
                });
                ImageView imageview = (ImageView)inflater.inflate(0x7f030059, null);
                linearlayout1.addView(textview1);
                linearlayout1.addView(imageview);
                k++;
            } while (true);
            j = k - 1;
        }

    }

    private void showTimeTableErrorDialog()
    {
        showErrorMessageDialog(getString(0x7f0d010b), getString(0x7f0d014f), new android.content.DialogInterface.OnClickListener() {

            final RailDirectionActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.cancel();
                launchCancel();
            }

            
            {
                this$0 = RailDirectionActivity.this;
                super();
            }
        });
    }

    protected void endGetAllTimetable(Bundle bundle, StationData stationdata, int i)
    {
        if (bundle == null)
        {
            showTimeTableErrorDialog();
        } else
        {
            station.setTimetable(bundle);
            station.setSettingType(type);
            if (nTargetCode == getResources().getInteger(0x7f0c005f))
            {
                (new SettingDb(this)).addSetting(station, type);
                Intent intent1 = new Intent(this, jp/co/yahoo/android/apps/transit/timer/SettingStationActivity);
                intent1.putExtra(getString(0x7f0d01e0), getResources().getInteger(0x7f0c0053));
                startActivityForResult(intent1, getResources().getInteger(0x7f0c005f));
                return;
            }
            if (nTargetCode == getResources().getInteger(0x7f0c003b))
            {
                (new SettingDb(this)).addTempSetting(station);
                Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/timer/CountdownActivity);
                if (i != -1)
                {
                    intent.putExtra(getString(0x7f0d024c), i);
                }
                intent.putExtra(getString(0x7f0d0247), type);
                startActivityForResult(intent, getResources().getInteger(0x7f0c003b));
                return;
            }
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03008f);
        Intent intent = getIntent();
        station = (StationData)intent.getSerializableExtra(getString(0x7f0d023e));
        type = intent.getIntExtra(getString(0x7f0d0247), 1);
        nTargetCode = intent.getIntExtra(getString(0x7f0d0244), 0);
        setTitle(getString(0x7f0d053a));
        if (type == getResources().getInteger(0x7f0c0074))
        {
            ((TextView)findViewById(0x7f0a02a4)).setText(getString(0x7f0d04fa));
        } else
        if (type == getResources().getInteger(0x7f0c0073))
        {
            ((TextView)findViewById(0x7f0a02a4)).setText(getString(0x7f0d04f9));
        } else
        {
            ((TextView)findViewById(0x7f0a02a4)).setText(getString(0x7f0d04f8));
        }
        ((TextView)findViewById(0x7f0a02a2)).setText((new StringBuilder()).append(station.getName()).append(getString(0x7f0d0530)).toString());
        railDirection = station.getRailDirection();
        if (railDirection != null)
        {
            showList(railDirection);
            return;
        } else
        {
            StationSearch stationsearch = new StationSearch(this);
            stationsearch.setQuery(station.getStationId());
            stationsearch.setResultCount(1);
            stationsearch.executeAsync(new jp.co.yahoo.android.yolp.common.ApiBase.ApiListener() {

                final RailDirectionActivity this$0;

                public boolean endApi(ApiBase apibase, Object obj)
                {
                    Bundle bundle1 = (Bundle)apibase.getResult();
                    if (bundle1 == null || bundle1.size() < 1)
                    {
                        showTimeTableErrorDialog();
                    } else
                    {
                        station = (StationData)bundle1.getSerializable("0");
                        station.setSettingType(type);
                        railDirection = station.getRailDirection();
                        showList(railDirection);
                    }
                    return false;
                }

            
            {
                this$0 = RailDirectionActivity.this;
                super();
            }
            }, true);
            return;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (i == 4)
        {
            launchBack();
        }
        return super.onKeyDown(i, keyevent);
    }

    public void onStart()
    {
        super.onStart();
        if (type == getResources().getInteger(0x7f0c0072))
        {
            dispAd(this, "2080325632", "pv");
            return;
        } else
        {
            dispAd(this, "2080325032", "pv");
            return;
        }
    }




/*
    static StationData access$102(RailDirectionActivity raildirectionactivity, StationData stationdata)
    {
        raildirectionactivity.station = stationdata;
        return stationdata;
    }

*/




/*
    static ArrayList access$302(RailDirectionActivity raildirectionactivity, ArrayList arraylist)
    {
        raildirectionactivity.railDirection = arraylist;
        return arraylist;
    }

*/





}
