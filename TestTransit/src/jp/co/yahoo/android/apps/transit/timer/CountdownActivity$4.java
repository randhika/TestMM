// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import java.util.Calendar;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.api.data.TimeTableItemData;
import jp.co.yahoo.android.apps.transit.timer.common.CountdownPanelManager;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownActivity

class this._cls0
    implements android.view.
{

    final CountdownActivity this$0;

    public void onClick(View view)
    {
        touchTapRD(getString(0x7f0d0415));
        if (CountdownActivity.access$200(CountdownActivity.this) != null && CountdownActivity.access$200(CountdownActivity.this).getSettingType() == getResources().getInteger(0x7f0c0075))
        {
            finish();
        } else
        {
            TimeTableItemData timetableitemdata = CountdownActivity.access$300(CountdownActivity.this).getTargetTime();
            if (timetableitemdata != null)
            {
                int i = timetableitemdata.getHour();
                int j = timetableitemdata.getMinute();
                Calendar calendar = Calendar.getInstance();
                if (i >= 24)
                {
                    i -= 24;
                    int k = Calendar.getInstance().get(11);
                    if (k < 24 && k > 3)
                    {
                        calendar.add(5, 1);
                    }
                }
                String s = Integer.toString(calendar.get(1));
                if (1 + calendar.get(2) < 10)
                {
                    s = (new StringBuilder()).append(s).append("0").toString();
                }
                String s1 = (new StringBuilder()).append(s).append(Integer.toString(1 + calendar.get(2))).toString();
                if (calendar.get(5) < 10)
                {
                    s1 = (new StringBuilder()).append(s1).append("0").toString();
                }
                String s2 = (new StringBuilder()).append(s1).append(Integer.toString(calendar.get(5))).toString();
                String s3;
                String s4;
                String s5;
                Bundle bundle;
                if (i < 10)
                {
                    s3 = (new StringBuilder()).append("0").append(Integer.toString(i)).toString();
                } else
                {
                    s3 = Integer.toString(i);
                }
                if (j < 10)
                {
                    s4 = (new StringBuilder()).append(s3).append("0").append(Integer.toString(j)).toString();
                } else
                {
                    s4 = (new StringBuilder()).append(s3).append(Integer.toString(j)).toString();
                }
                s5 = "android.intent.action.VIEW";
                bundle = new Bundle();
                if (CountdownActivity.access$400(CountdownActivity.this) == null)
                {
                    s5 = "android.intent.action.EDIT";
                    bundle.putString(getString(0x7f0d038c), "");
                } else
                {
                    bundle.putString(getString(0x7f0d038c), CountdownActivity.access$400(CountdownActivity.this).getName());
                    bundle.putString(getString(0x7f0d038d), CountdownActivity.access$400(CountdownActivity.this).getStationId());
                }
                bundle.putString(getString(0x7f0d0385), getString(0x7f0d005a));
                bundle.putString(getString(0x7f0d037c), CountdownActivity.access$200(CountdownActivity.this).getName());
                bundle.putString(getString(0x7f0d037d), CountdownActivity.access$200(CountdownActivity.this).getStationId());
                bundle.putString(getString(0x7f0d038f), "1");
                bundle.putString(getString(0x7f0d038b), s4);
                bundle.putString(getString(0x7f0d0376), s2);
                intentToTransit(bundle, s5);
                return;
            }
        }
    }

    emData()
    {
        this$0 = CountdownActivity.this;
        super();
    }
}
