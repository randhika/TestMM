// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.text.TextUtils;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.TimeTableSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.TimeTableData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TimeTableActivity

class val.sDate
    implements jp.co.yahoo.android.apps.transit.api.sDate
{

    final TimeTableActivity this$0;
    final String val$sDate;

    public boolean onCanceled()
    {
        if (TimeTableActivity.access$1900(TimeTableActivity.this) != -1)
        {
            TimeTableActivity.access$1902(TimeTableActivity.this, -1);
        }
        if (TimeTableActivity.access$2000(TimeTableActivity.this))
        {
            finish();
        }
        TimeTableActivity.access$1102(TimeTableActivity.this, null);
        return false;
    }

    public boolean onError(APIError apierror)
    {
        if (TimeTableActivity.access$1100(TimeTableActivity.this) == null)
        {
            return false;
        }
        TimeTableActivity.access$1802(TimeTableActivity.this, true);
        String s = TimeTableActivity.access$1100(TimeTableActivity.this).getError().getCode();
        if (!TransitUtil.isEmpty(s) && s.equals(getString(0x7f0d0516)))
        {
            showSimpleErrorMessageDialog(getString(0x7f0d0517));
        } else
        if (TimeTableActivity.access$1900(TimeTableActivity.this) != -1)
        {
            TimeTableActivity.access$1902(TimeTableActivity.this, -1);
        } else
        {
            launchDirection();
        }
        TimeTableActivity.access$1102(TimeTableActivity.this, null);
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        TimeTableData timetabledata;
        if (TimeTableActivity.access$1100(TimeTableActivity.this) != null)
        {
            if ((timetabledata = (TimeTableData)TimeTableActivity.access$1100(TimeTableActivity.this).getResult()) != null)
            {
                if (!TextUtils.isEmpty(val$sDate))
                {
                    TimeTableActivity.access$902(TimeTableActivity.this, 10);
                } else
                if (timetabledata.dateKind != 0)
                {
                    TimeTableActivity.access$902(TimeTableActivity.this, timetabledata.dateKind);
                } else
                {
                    TimeTableActivity.access$902(TimeTableActivity.this, Integer.valueOf(timetabledata.kind).intValue());
                }
                if (TimeTableActivity.access$900(TimeTableActivity.this) == 10)
                {
                    TimeTableActivity.access$1202(TimeTableActivity.this, timetabledata);
                } else
                if (TimeTableActivity.access$900(TimeTableActivity.this) == 4)
                {
                    TimeTableActivity.access$1302(TimeTableActivity.this, timetabledata);
                } else
                if (TimeTableActivity.access$900(TimeTableActivity.this) == 2)
                {
                    TimeTableActivity.access$1402(TimeTableActivity.this, timetabledata);
                } else
                {
                    TimeTableActivity.access$1502(TimeTableActivity.this, timetabledata);
                }
                if (!TimeTableActivity.access$1700(TimeTableActivity.this, TimeTableActivity.access$1600(TimeTableActivity.this), TimeTableActivity.access$900(TimeTableActivity.this)))
                {
                    TimeTableActivity.access$1000(TimeTableActivity.this);
                }
                TimeTableActivity.access$1102(TimeTableActivity.this, null);
                return false;
            }
        }
        return false;
    }

    ta()
    {
        this$0 = final_timetableactivity;
        val$sDate = String.this;
        super();
    }
}
