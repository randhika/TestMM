// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            SettingStartDetailActivity

class this._cls0
    implements android.widget.er
{

    final SettingStartDetailActivity this$0;

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        StationData stationdata = (StationData)view.getTag();
        String s = (new StringBuilder()).append(stationdata.getRailname()).append(" ").append(stationdata.getDirname()).append(getString(0x7f0d0275)).append("\u3000").append(stationdata.getName()).append(getString(0x7f0d0304)).toString();
        SettingStartDetailActivity.access$000(SettingStartDetailActivity.this).setTimetableId(Integer.parseInt(stationdata.getId()));
        SettingStartDetailActivity.access$000(SettingStartDetailActivity.this).setLine(s);
        SettingStartDetailActivity.access$100(SettingStartDetailActivity.this).setText(s);
        onCheck();
        SettingStartDetailActivity.access$200(SettingStartDetailActivity.this).dismiss();
    }

    I()
    {
        this$0 = SettingStartDetailActivity.this;
        super();
    }
}
