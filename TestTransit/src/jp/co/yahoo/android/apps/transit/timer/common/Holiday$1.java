// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.common;

import android.content.SharedPreferences;
import jp.co.yahoo.android.apps.transit.timer.api.HolidaySearch;
import jp.co.yahoo.android.yolp.common.ApiBase;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.common:
//            Holiday

class this._cls0
    implements jp.co.yahoo.android.yolp.common.iListener
{

    final Holiday this$0;

    public boolean endApi(ApiBase apibase, Object obj)
    {
        android.content.erences.Editor editor = Holiday.access$000(Holiday.this).edit();
        editor.putString("holiday_expired", Holiday.access$100(Holiday.this).getExpired());
        editor.putString("holiday_date", Holiday.access$100(Holiday.this).getHoliday());
        editor.commit();
        return false;
    }

    ()
    {
        this$0 = Holiday.this;
        super();
    }
}
