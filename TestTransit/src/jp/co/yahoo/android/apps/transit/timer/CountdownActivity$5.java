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

class this._cls0
    implements android.view.
{

    final CountdownActivity this$0;

    public void onClick(View view)
    {
        final ArrayList stations;
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
        stations = CountdownActivity.access$500(CountdownActivity.this).getAllStation(type);
        if (type == res.getInteger(0x7f0c0074))
        {
            s = getString(0x7f0d0179);
        } else
        {
            s = getString(0x7f0d0178);
        }
        as = new String[stations.size()];
        i = 0;
        for (int j = 0; j < stations.size(); j++)
        {
            StationData stationdata = (StationData)stations.get(j);
            as[j] = (new StringBuilder()).append(stationdata.getName()).append("\n").append(stationdata.getRailname()).append(" ").append(stationdata.getDirname()).append(getString(0x7f0d0275)).toString();
            if (stationdata.isSetting())
            {
                i = j;
            }
        }

        CountdownActivity.access$600(CountdownActivity.this, s, 0, null, as, i, 0x7f0d007f, new android.content.DialogInterface.OnClickListener() {

            final CountdownActivity._cls5 this$1;
            final ArrayList val$stations;

            public void onClick(DialogInterface dialoginterface, int k)
            {
                StationData stationdata1 = (StationData)stations.get(k);
                stationdata1.setSetting(true);
                (new SettingDb(this$0)).updateSetting(stationdata1, type);
                changeActivity(type);
            }

            
            {
                this$1 = CountdownActivity._cls5.this;
                stations = arraylist;
                super();
            }
        });
    }

    _cls1.val.stations()
    {
        this$0 = CountdownActivity.this;
        super();
    }
}
