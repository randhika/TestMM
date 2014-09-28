// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import jp.co.yahoo.android.apps.transit.api.data.StationData;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            StationInfo

class val.url
    implements android.view.stener
{

    final StationInfo this$0;
    final String val$mainText;
    final String val$url;

    public void onClick(View view)
    {
        StationInfo stationinfo;
        String s;
        Object aobj[];
        if (val$mainText.equals(getString(0x7f0d0501)))
        {
            touchTapRD(getString(0x7f0d03dc));
        } else
        {
            touchTapRD(getString(0x7f0d03dd));
        }
        stationinfo = StationInfo.this;
        s = val$url;
        aobj = new Object[1];
        aobj[0] = StationInfo.access$200(StationInfo.this).getId();
        StationInfo.access$600(stationinfo, String.format(s, aobj));
    }

    onData()
    {
        this$0 = final_stationinfo;
        val$mainText = s;
        val$url = String.this;
        super();
    }
}
