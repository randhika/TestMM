// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.TimeTableSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.TimeTableData;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TimeTableActivity

class val.deleteNum
    implements jp.co.yahoo.android.apps.transit.api.deleteNum
{

    final TimeTableActivity this$0;
    final int val$deleteNum;
    final int val$saveType;

    public boolean onCanceled()
    {
        TimeTableActivity.access$1102(TimeTableActivity.this, null);
        return false;
    }

    public boolean onError(APIError apierror)
    {
        TimeTableActivity.access$1102(TimeTableActivity.this, null);
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        if (TimeTableActivity.access$1100(TimeTableActivity.this) == null)
        {
            return false;
        } else
        {
            TimeTableData timetabledata = (TimeTableData)TimeTableActivity.access$1100(TimeTableActivity.this).getResult();
            TimeTableActivity.access$2300(TimeTableActivity.this, timetabledata, val$saveType, val$deleteNum);
            TimeTableActivity.access$1102(TimeTableActivity.this, null);
            return false;
        }
    }

    ta()
    {
        this$0 = final_timetableactivity;
        val$saveType = i;
        val$deleteNum = I.this;
        super();
    }
}
