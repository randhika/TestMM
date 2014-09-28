// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.viewparts;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.timer.api.data.TimeTableItemData;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;

public class CountdownPanelView extends LinearLayout
{

    private TextView btnWeek;
    private ImageView imgAlarm;
    private int nId;
    private int nWeek;
    private TimeTableItemData nextSt;
    private TextView txtHour;
    private TextView txtHourLabel;
    private TextView txtMinute;
    private TextView txtSecondLable;
    private TextView txtSeconds;

    public CountdownPanelView(Context context, int i, boolean flag)
    {
        super(context);
        nextSt = null;
        nId = i;
        LayoutInflater layoutinflater = (LayoutInflater)getContext().getSystemService("layout_inflater");
        if (flag)
        {
            layoutinflater.inflate(0x7f03004e, this, true);
        } else
        {
            layoutinflater.inflate(0x7f03004d, this, true);
        }
        txtHour = (TextView)findViewById(0x7f0a01db);
        txtHourLabel = (TextView)findViewById(0x7f0a01dc);
        txtMinute = (TextView)findViewById(0x7f0a01dd);
        txtSeconds = (TextView)findViewById(0x7f0a01de);
        txtSecondLable = (TextView)findViewById(0x7f0a01df);
        btnWeek = (TextView)findViewById(0x7f0a01d6);
        imgAlarm = (ImageView)findViewById(0x7f0a01d3);
    }

    public int getIndex()
    {
        return nId;
    }

    public TextView getWeekBtn()
    {
        return btnWeek;
    }

    public void setAlertLabel(boolean flag)
    {
        if (flag)
        {
            imgAlarm.setVisibility(0);
            return;
        } else
        {
            imgAlarm.setVisibility(8);
            return;
        }
    }

    public void setTimetable(TimeTableItemData timetableitemdata)
    {
        TextView textview;
        nextSt = timetableitemdata;
        Resources resources = getContext().getResources();
        textview = (TextView)findViewById(0x7f0a01d5);
        TextView textview1 = (TextView)findViewById(0x7f0a01d8);
        if (nextSt.getDestinfo() != null)
        {
            TextView textview2 = (TextView)findViewById(0x7f0a01d9);
            StringBuffer stringbuffer1 = new StringBuffer(nextSt.getDestinfo());
            if (!nextSt.getTraintype().equals("-1"))
            {
                String s3 = (new StringBuilder()).append(" ").append(getContext().getString(0x7f0d0287)).toString();
                stringbuffer1.append(s3);
                TransitUtil.setEllipsisTextBeforeLabel(textview2, s3);
            }
            textview2.setText(stringbuffer1);
        }
        if (nextSt.getTraininfo() != null)
        {
            textview1.setText(nextSt.getTraininfo());
        }
        String s;
        String s1;
        String s2;
        if (nextSt.getTraintype().equals("-1"))
        {
            textview1.setVisibility(8);
            setBackgroundColor(resources.getColor(0x7f090026));
        } else
        if (nextSt.getTraintype().equals("1"))
        {
            textview1.setBackgroundColor(resources.getColor(0x7f090029));
            setBackgroundColor(resources.getColor(0x7f090021));
        } else
        if (nextSt.getTraintype().equals("2"))
        {
            textview1.setBackgroundColor(resources.getColor(0x7f09002a));
            setBackgroundColor(resources.getColor(0x7f090022));
        } else
        if (nextSt.getTraintype().equals("3"))
        {
            textview1.setBackgroundColor(resources.getColor(0x7f09002b));
            setBackgroundColor(resources.getColor(0x7f090023));
        } else
        if (nextSt.getTraintype().equals("4"))
        {
            textview1.setBackgroundColor(resources.getColor(0x7f09002c));
            setBackgroundColor(resources.getColor(0x7f090024));
        } else
        {
            textview1.setBackgroundColor(resources.getColor(0x7f09002d));
            setBackgroundColor(resources.getColor(0x7f090025));
        }
        if (!nextSt.getTraintype().equals("-1"))
        {
            StringBuffer stringbuffer = new StringBuffer(nextSt.getTraininfo());
            if (timetableitemdata.isStartStation())
            {
                stringbuffer.append(" ").append(getContext().getString(0x7f0d051c));
            }
            if (timetableitemdata.isExtraLine())
            {
                stringbuffer.append(" ").append(getContext().getString(0x7f0d0518));
            }
            textview1.setText(stringbuffer);
        }
        s = CountdownUtil.getZeroNum(nextSt.getHour());
        s1 = CountdownUtil.getZeroNum(nextSt.getMinute());
        s2 = (new StringBuilder()).append(s).append(":").append(s1).append(resources.getString(0x7f0d0300)).toString();
        if (!nextSt.isFirstStation() || !nextSt.isLastStation()) goto _L2; else goto _L1
_L1:
        ((TextView)findViewById(0x7f0a01d4)).setText(s2);
        btnWeek.setText(CountdownUtil.getWeekLabel(nWeek, getContext()));
        return;
_L2:
        if (nextSt.isFirstStation())
        {
            textview.setText(getContext().getString(0x7f0d0283));
            textview.setVisibility(0);
        } else
        if (nextSt.isLastStation())
        {
            textview.setText(getContext().getString(0x7f0d0291));
            textview.setVisibility(0);
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public void setVisibleWeek(boolean flag)
    {
        if (flag)
        {
            btnWeek.setVisibility(0);
            return;
        } else
        {
            btnWeek.setVisibility(8);
            return;
        }
    }

    public void setWeek(int i)
    {
        nWeek = i;
    }

    public void update(int i)
    {
        int j = 60 * (60 * nextSt.getHour()) + 60 * nextSt.getMinute();
        int k = j - i;
        int l = k / 3600;
        int i1 = (k % 3600) / 60;
        int j1 = k % 60;
        if (i > j)
        {
            txtSecondLable.setText(getContext().getString(0x7f0d02f9));
            txtHour.setTextColor(getContext().getResources().getColor(0x7f09002f));
            txtMinute.setTextColor(getContext().getResources().getColor(0x7f09002f));
            txtSeconds.setTextColor(getContext().getResources().getColor(0x7f09002f));
        } else
        {
            txtSecondLable.setText(getContext().getString(0x7f0d02f7));
        }
        if (l == 0)
        {
            txtHour.setVisibility(8);
            txtHourLabel.setVisibility(8);
        } else
        {
            txtHour.setVisibility(0);
            txtHourLabel.setVisibility(0);
        }
        txtHour.setText(CountdownUtil.getZeroNum(Math.abs(l)));
        txtMinute.setText(CountdownUtil.getZeroNum(Math.abs(i1)));
        txtSeconds.setText(CountdownUtil.getZeroNum(Math.abs(j1)));
    }
}
