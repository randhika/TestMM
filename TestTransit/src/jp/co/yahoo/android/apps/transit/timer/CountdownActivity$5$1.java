// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.View;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.db.SettingDb;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownActivity

class val.stations
    implements android.content.kListener
{

    final  this$1;
    final ArrayList val$stations;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        StationData stationdata = (StationData)val$stations.get(i);
        stationdata.setSetting(true);
        (new SettingDb(_fld0)).updateSetting(stationdata, type);
        changeActivity(type);
    }

    is._cls0()
    {
        this$1 = final__pcls0;
        val$stations = ArrayList.this;
        super();
    }

    // Unreferenced inner class jp/co/yahoo/android/apps/transit/timer/CountdownActivity$5

/* anonymous class */
    class CountdownActivity._cls5
        implements android.view.View.OnClickListener
    {

        final CountdownActivity this$0;

        public void onClick(View view)
        {
            ArrayList arraylist;
            String s;
            String as[];
            int i;
            if (type == res.getInteger(0x7f0c0074))
            {
                touchTapRD(getString(0x7f0d041d));
            } else
            {
                touchTapRD(getString(0x7f0d041c));
            }
            arraylist = CountdownActivity.access$500(CountdownActivity.this).getAllStation(type);
            if (type == res.getInteger(0x7f0c0074))
            {
                s = getString(0x7f0d0179);
            } else
            {
                s = getString(0x7f0d0178);
            }
            as = new String[arraylist.size()];
            i = 0;
            for (int j = 0; j < arraylist.size(); j++)
            {
                StationData stationdata = (StationData)arraylist.get(j);
                as[j] = (new StringBuilder()).append(stationdata.getName()).append("\n").append(stationdata.getRailname()).append(" ").append(stationdata.getDirname()).append(getString(0x7f0d0275)).toString();
                if (stationdata.isSetting())
                {
                    i = j;
                }
            }

            CountdownActivity.access$600(CountdownActivity.this, s, 0, null, as, i, 0x7f0d007f, arraylist. new CountdownActivity._cls5._cls1());
        }

            
            {
                this$0 = CountdownActivity.this;
                super();
            }
    }

}
