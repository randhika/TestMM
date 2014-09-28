// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.viewparts;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.timer.CountdownActivity;
import jp.co.yahoo.android.apps.transit.timer.CountdownBaseActivity;
import jp.co.yahoo.android.apps.transit.timer.api.data.TimeTableItemData;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;

public class CountdownListView extends LinearLayout
{

    private TimeTableItemData nextSt;
    private LinearLayout panel;
    private LinearLayout panelNow;
    private TextView txtHour;
    private TextView txtHourLabel;
    private TextView txtMinute;
    private TextView txtMinuteAfter;
    private TextView txtSecond;
    private TextView txtSecondAfter;

    public CountdownListView(Context context, final int nId, final int type, final int week, LinearLayout linearlayout)
    {
        super(context);
        nextSt = null;
        panelNow = linearlayout;
        panel = (LinearLayout)linearlayout.findViewById(0x7f0a0202);
        txtHour = (TextView)linearlayout.findViewById(0x7f0a0208);
        txtHourLabel = (TextView)linearlayout.findViewById(0x7f0a0209);
        txtMinute = (TextView)linearlayout.findViewById(0x7f0a020a);
        txtMinuteAfter = (TextView)linearlayout.findViewById(0x7f0a020b);
        txtSecond = (TextView)linearlayout.findViewById(0x7f0a020c);
        txtSecondAfter = (TextView)linearlayout.findViewById(0x7f0a020d);
        linearlayout.setOnClickListener(new android.view.View.OnClickListener() {

            final CountdownListView this$0;
            final int val$nId;
            final int val$type;
            final int val$week;

            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext(), jp/co/yahoo/android/apps/transit/timer/CountdownActivity);
                intent.putExtra(view.getContext().getString(0x7f0d0247), type);
                intent.putExtra(view.getContext().getString(0x7f0d024c), week);
                intent.putExtra(view.getContext().getString(0x7f0d0246), nId);
                ((CountdownBaseActivity)view.getContext()).startActivityForResult(intent, getResources().getInteger(0x7f0c003b));
            }

            
            {
                this$0 = CountdownListView.this;
                type = i;
                week = j;
                nId = k;
                super();
            }
        });
    }

    public View getList()
    {
        return panelNow;
    }

    public void setNumber(int i, int j)
    {
        ((TextView)panelNow.findViewById(0x7f0a0203)).setText(Integer.toString(i + 1));
        if (i > j - 1 && txtSecond.getVisibility() != 8)
        {
            txtSecond.setVisibility(8);
            txtSecondAfter.setVisibility(8);
            txtMinuteAfter.setText(0x7f0d029c);
        }
    }

    public boolean setTimetable(TimeTableItemData timetableitemdata)
    {
        nextSt = timetableitemdata;
        Resources resources = getContext().getResources();
        TextView textview = (TextView)panelNow.findViewById(0x7f0a0204);
        TextView textview1 = (TextView)panelNow.findViewById(0x7f0a0206);
        TextView textview2 = (TextView)panelNow.findViewById(0x7f0a0207);
        if (nextSt.getDestinfo() != null)
        {
            TextView textview3 = (TextView)panelNow.findViewById(0x7f0a0205);
            String s2 = (new StringBuilder()).append(" ").append(getContext().getString(0x7f0d0287)).toString();
            textview3.setText((new StringBuilder()).append(nextSt.getDestinfo()).append(s2).toString());
            TransitUtil.setEllipsisTextBeforeLabel(textview3, s2);
        }
        String s;
        String s1;
        if (nextSt.getTraintype().equals("1"))
        {
            textview.setBackgroundColor(resources.getColor(0x7f090029));
            panel.setBackgroundColor(resources.getColor(0x7f090021));
        } else
        if (nextSt.getTraintype().equals("2"))
        {
            textview.setBackgroundColor(resources.getColor(0x7f09002a));
            panel.setBackgroundColor(resources.getColor(0x7f090022));
        } else
        if (nextSt.getTraintype().equals("3"))
        {
            textview.setBackgroundColor(resources.getColor(0x7f09002b));
            panel.setBackgroundColor(resources.getColor(0x7f090023));
        } else
        if (nextSt.getTraintype().equals("4"))
        {
            textview.setBackgroundColor(resources.getColor(0x7f09002c));
            panel.setBackgroundColor(resources.getColor(0x7f090024));
        } else
        if (!nextSt.getTraintype().equals("-1"))
        {
            textview.setBackgroundColor(resources.getColor(0x7f09002d));
            panel.setBackgroundColor(resources.getColor(0x7f090025));
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
            textview.setText(stringbuffer);
        } else
        {
            textview.setVisibility(8);
        }
        s = CountdownUtil.getZeroNum(nextSt.getHour());
        s1 = CountdownUtil.getZeroNum(nextSt.getMinute());
        textview2.setText((new StringBuilder()).append(s).append(":").append(s1).append(resources.getString(0x7f0d0300)).toString());
        if (!nextSt.isFirstStation() || !nextSt.isLastStation())
        {
            if (nextSt.isFirstStation())
            {
                textview1.setText(resources.getString(0x7f0d0283));
                textview1.setVisibility(0);
            } else
            if (nextSt.isLastStation())
            {
                textview1.setText(resources.getString(0x7f0d0291));
                textview1.setVisibility(0);
            }
        }
        return false;
    }

    public void update(int i)
    {
        int j = (60 * (60 * nextSt.getHour()) + 60 * nextSt.getMinute()) - i;
        int k = j / 3600;
        int l = (j % 3600) / 60;
        int i1 = j % 60;
        if (k == 0)
        {
            txtHour.setVisibility(8);
            txtHourLabel.setVisibility(8);
        } else
        {
            txtHour.setVisibility(0);
            txtHourLabel.setVisibility(0);
            txtHour.setText(CountdownUtil.getZeroNum(Math.abs(k)));
        }
        if (CountdownUtil.getZeroNum(Math.abs(l)).equals("00"))
        {
            txtMinute.setVisibility(8);
            txtMinuteAfter.setVisibility(8);
        } else
        {
            txtMinute.setText(CountdownUtil.getZeroNum(Math.abs(l)));
        }
        txtSecond.setText(CountdownUtil.getZeroNum(Math.abs(i1)));
    }
}
