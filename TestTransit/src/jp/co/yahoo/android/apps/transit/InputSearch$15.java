// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import java.util.Calendar;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.SaveCondition;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            InputSearch

class this._cls0
    implements jp.co.yahoo.android.apps.transit.api.LocationSearchlListener
{

    final InputSearch this$0;

    public void onError(String s, String s1)
    {
        showErrorMessageDialog(s1, getString(0x7f0d014f));
    }

    public void onSuccess(String s, Bundle bundle)
    {
        ConditionData conditiondata = (new SaveCondition(InputSearch.this)).getCond();
        if (conditiondata == null)
        {
            conditiondata = new ConditionData();
        }
        conditiondata.startName = bundle.getString(getString(0x7f0d01a2));
        conditiondata.startLat = bundle.getString(getString(0x7f0d01a3));
        conditiondata.startLon = bundle.getString(getString(0x7f0d01a4));
        if (InputSearch.access$200(InputSearch.this).getId() != null)
        {
            conditiondata.goalCode = InputSearch.access$200(InputSearch.this).getId();
        }
        conditiondata.goalName = InputSearch.access$200(InputSearch.this).getName();
        conditiondata.afterFinal = false;
        Calendar calendar = Calendar.getInstance();
        conditiondata.year = calendar.get(1);
        conditiondata.month = 1 + calendar.get(2);
        conditiondata.day = calendar.get(5);
        conditiondata.hour = calendar.get(11);
        conditiondata.minite = calendar.get(12);
        launchSearchResult(conditiondata);
    }

    public void onTimeout(String s, String s1)
    {
        showErrorMessageDialog(s1, getString(0x7f0d014f));
    }

    nData()
    {
        this$0 = InputSearch.this;
        super();
    }
}
