// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.viewparts;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.timer.api.data.TimeTableItemData;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;

public class CountdownDragView
{

    private Context context;
    private ImageView imgColor;
    private TimeTableItemData nextSt;
    private TextView txtGoal;
    private TextView txtGoalLabel;
    private TextView txtHour;
    private TextView txtHourLabel;
    private TextView txtMinute;
    private TextView txtSecond;
    private TextView txtSecondLabel;
    private TextView txtStart;
    private TextView txtType;
    private View view;

    public CountdownDragView(Context context1, View view1, String s)
    {
        nextSt = null;
        context = context1;
        view = view1;
        ((TextView)view1.findViewById(0x7f0a01e5)).setText(s);
        txtStart = (TextView)view1.findViewById(0x7f0a01e4);
        txtType = (TextView)view1.findViewById(0x7f0a01e7);
        txtGoal = (TextView)view1.findViewById(0x7f0a01e8);
        txtGoalLabel = (TextView)view1.findViewById(0x7f0a01e9);
        imgColor = (ImageView)view1.findViewById(0x7f0a01eb);
        View view2 = view1.findViewById(0x7f0a01ea);
        txtHour = (TextView)view2.findViewById(0x7f0a01db);
        txtHourLabel = (TextView)view2.findViewById(0x7f0a01dc);
        txtMinute = (TextView)view2.findViewById(0x7f0a01dd);
        txtSecond = (TextView)view2.findViewById(0x7f0a01de);
        txtSecondLabel = (TextView)view2.findViewById(0x7f0a01df);
    }

    public void setTimetable(TimeTableItemData timetableitemdata)
    {
        nextSt = timetableitemdata;
        Resources resources = context.getResources();
        String s = CountdownUtil.getZeroNum(nextSt.getHour());
        String s1 = CountdownUtil.getZeroNum(nextSt.getMinute());
        String s2 = (new StringBuilder()).append(s).append(":").append(s1).append(resources.getString(0x7f0d0300)).toString();
        txtStart.setText(s2);
        if (nextSt.getDestinfo() != null)
        {
            txtGoal.setText(nextSt.getDestinfo());
            if (nextSt.getTraintype().equals("-1"))
            {
                txtGoalLabel.setVisibility(8);
            }
        }
        if (nextSt.getTraintype().equals("-1"))
        {
            txtType.setVisibility(8);
            imgColor.setImageResource(0x7f02017e);
        } else
        if (nextSt.getTraintype().equals("1"))
        {
            txtType.setBackgroundColor(resources.getColor(0x7f090029));
            imgColor.setImageResource(0x7f020179);
        } else
        if (nextSt.getTraintype().equals("2"))
        {
            txtType.setBackgroundColor(resources.getColor(0x7f09002a));
            imgColor.setImageResource(0x7f02017a);
        } else
        if (nextSt.getTraintype().equals("3"))
        {
            txtType.setBackgroundColor(resources.getColor(0x7f09002b));
            imgColor.setImageResource(0x7f02017b);
        } else
        if (nextSt.getTraintype().equals("4"))
        {
            txtType.setBackgroundColor(resources.getColor(0x7f09002c));
            imgColor.setImageResource(0x7f02017c);
        } else
        {
            txtType.setBackgroundColor(resources.getColor(0x7f09002d));
            imgColor.setImageResource(0x7f02017d);
        }
        if (!nextSt.getTraintype().equals("-1"))
        {
            StringBuffer stringbuffer = new StringBuffer(nextSt.getTraininfo());
            if (timetableitemdata.isStartStation())
            {
                stringbuffer.append(" ").append(context.getString(0x7f0d051c));
            }
            if (timetableitemdata.isExtraLine())
            {
                stringbuffer.append(" ").append(context.getString(0x7f0d0518));
            }
            txtType.setText(stringbuffer);
        }
    }

    public void update(int i)
    {
        if (((View)view.getParent()).getVisibility() != 0)
        {
            return;
        }
        int j = 60 * (60 * nextSt.getHour()) + 60 * nextSt.getMinute();
        int k = j - i;
        int l = k / 3600;
        int i1 = (k % 3600) / 60;
        int j1 = k % 60;
        if (i > j)
        {
            txtSecondLabel.setText(context.getString(0x7f0d02f9));
            txtHour.setTextColor(context.getResources().getColor(0x7f09002f));
            txtMinute.setTextColor(context.getResources().getColor(0x7f09002f));
            txtSecond.setTextColor(context.getResources().getColor(0x7f09002f));
        } else
        {
            txtSecondLabel.setText(context.getString(0x7f0d02f7));
            txtHour.setTextColor(context.getResources().getColor(0x7f090084));
            txtMinute.setTextColor(context.getResources().getColor(0x7f090084));
            txtSecond.setTextColor(context.getResources().getColor(0x7f090084));
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
        txtSecond.setText(CountdownUtil.getZeroNum(Math.abs(j1)));
    }
}
