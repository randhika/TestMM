// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import java.net.URLEncoder;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.StringUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            StationInfo

class val.displayTxt
    implements android.view.stener
{

    final StationInfo this$0;
    final String val$displayTxt;
    final String val$urlTxt;

    public void onClick(View view)
    {
        String s = (String)view.getTag();
        touchTapRD(s);
        StationInfo stationinfo = StationInfo.this;
        String s1 = getString(0x7f0d0552);
        Object aobj[] = new Object[4];
        aobj[0] = URLEncoder.encode(StringUtil.trim(val$urlTxt));
        aobj[1] = val$displayTxt;
        aobj[2] = StationInfo.access$200(StationInfo.this).getLat().toString();
        aobj[3] = StationInfo.access$200(StationInfo.this).getLon().toString();
        StationInfo.access$600(stationinfo, String.format(s1, aobj));
    }

    onData()
    {
        this$0 = final_stationinfo;
        val$urlTxt = s;
        val$displayTxt = String.this;
        super();
    }
}
