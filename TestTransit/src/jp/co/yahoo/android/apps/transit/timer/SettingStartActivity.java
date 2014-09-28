// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.common.Alerm;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownBaseActivity, SettingStartDetailActivity

public class SettingStartActivity extends CountdownBaseActivity
    implements android.view.View.OnClickListener, android.widget.CompoundButton.OnCheckedChangeListener
{

    private Alerm alerm;
    private ArrayList aryStart;
    private Resources res;

    public SettingStartActivity()
    {
        alerm = null;
        res = null;
        aryStart = null;
    }

    private void showStartSetting()
    {
        aryStart = alerm.getStartAlermList();
        TextView textview = (TextView)findViewById(0x7f0a02f4);
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0a02f5);
        linearlayout.removeAllViews();
        if (aryStart == null || aryStart.size() < 1)
        {
            textview.setVisibility(0);
        } else
        {
            textview.setVisibility(8);
            int i = 0;
            while (i < aryStart.size()) 
            {
                AlermData alermdata = (AlermData)aryStart.get(i);
                LinearLayout linearlayout1 = (LinearLayout)inflater.inflate(0x7f03007a, null);
                TextView textview1 = (TextView)linearlayout1.findViewById(0x7f0a026e);
                TextView textview2 = (TextView)linearlayout1.findViewById(0x7f0a026f);
                TextView textview3 = (TextView)linearlayout1.findViewById(0x7f0a01d6);
                CompoundButton compoundbutton = (CompoundButton)linearlayout1.findViewById(0x7f0a0135);
                textview2.setText(alermdata.getLine());
                textview1.setText((new StringBuilder()).append(CountdownUtil.getZeroNum(alermdata.getStartTime() / 3600)).append(":").append(CountdownUtil.getZeroNum((alermdata.getStartTime() % 3600) / 60)).toString());
                textview3.setText(CountdownUtil.getRepeatWeek(alermdata.getRepeat(), this));
                compoundbutton.setChecked(alermdata.isSetting());
                compoundbutton.setTag(alermdata);
                compoundbutton.setOnCheckedChangeListener(this);
                if (alermdata.getStation().getSettingType() == res.getInteger(0x7f0c0073))
                {
                    ImageView imageview1 = (ImageView)linearlayout1.findViewById(0x7f0a026d);
                    imageview1.setImageResource(0x7f0201c8);
                    imageview1.setContentDescription(getString(0x7f0d0316));
                }
                linearlayout1.setTag(alermdata);
                linearlayout1.setOnClickListener(this);
                ImageView imageview = (ImageView)inflater.inflate(0x7f030059, null);
                linearlayout.addView(linearlayout1);
                linearlayout.addView(imageview);
                i++;
            }
        }
    }

    public void addSetting(View view)
    {
        if (aryStart != null && aryStart.size() >= 4)
        {
            showErrorMessageDialog(getString(0x7f0d0138), getString(0x7f0d015e));
            return;
        } else
        {
            launchStartDetail(null);
            return;
        }
    }

    public void launchStartDetail(AlermData alermdata)
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/timer/SettingStartDetailActivity);
        if (alermdata != null)
        {
            intent.putExtra(getString(0x7f0d023d), alermdata.getId());
        }
        startActivityForResult(intent, getResources().getInteger(0x7f0c005e));
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        if (-1 == j && getResources().getInteger(0x7f0c005e) == i)
        {
            showStartSetting();
        }
    }

    public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        AlermData alermdata = (AlermData)compoundbutton.getTag();
        alermdata.setSetting(flag);
        alerm.setAlerm(alermdata, true);
    }

    public void onClick(View view)
    {
        launchStartDetail((AlermData)view.getTag());
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f0300a2);
        setTitle(getString(0x7f0d04d8));
        alerm = new Alerm(this);
        res = getResources();
        showStartSetting();
    }

    public void onStart()
    {
        super.onStart();
        dispAd(this, "2080325036", "pv");
    }
}
