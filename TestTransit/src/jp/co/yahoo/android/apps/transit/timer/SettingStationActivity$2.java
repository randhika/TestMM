// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.DialogInterface;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.common.Alerm;
import jp.co.yahoo.android.apps.transit.timer.db.SettingDb;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            SettingStationActivity

class this._cls0
    implements android.content.stener
{

    final SettingStationActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if (SettingStationActivity.access$000(SettingStationActivity.this) != null)
        {
            SettingStationActivity.access$100(SettingStationActivity.this).deleteData(SettingStationActivity.access$000(SettingStationActivity.this));
            (new Alerm(SettingStationActivity.this)).delAlarmByTimetableId(Integer.parseInt(SettingStationActivity.access$000(SettingStationActivity.this).getId()));
            if (SettingStationActivity.access$000(SettingStationActivity.this).isSetting())
            {
                StationData stationdata = SettingStationActivity.access$100(SettingStationActivity.this).getStation(SettingStationActivity.access$000(SettingStationActivity.this).getSettingType());
                if (stationdata != null)
                {
                    SettingStationActivity.access$100(SettingStationActivity.this).updateSetting(stationdata, SettingStationActivity.access$000(SettingStationActivity.this).getSettingType());
                }
            }
        }
        SettingStationActivity.access$202(SettingStationActivity.this, null);
        SettingStationActivity.access$302(SettingStationActivity.this, null);
        show();
        dialoginterface.cancel();
    }

    ()
    {
        this$0 = SettingStationActivity.this;
        super();
    }
}
