// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import java.util.Calendar;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.NaviSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.SaveCondition;
import jp.co.yahoo.android.apps.transit.common.StringUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultBaseActivity, SearchResultListActivity

class val.conditionData
    implements jp.co.yahoo.android.apps.transit.api.conditionData
{

    final sources this$1;
    final ConditionData val$conditionData;

    public boolean onCanceled()
    {
        return false;
    }

    public boolean onError(APIError apierror)
    {
        String s = apierror.getMessage();
        if (StringUtil.isEmpty(s))
        {
            s = getString(0x7f0d0108);
        }
        showSimpleErrorMessageDialog(s);
        return false;
    }

    public boolean onSuccess(ApiBase apibase, Object obj)
    {
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData navisearchdata = NaviSearch.m_routeList;
        Intent intent = new Intent(context, jp/co/yahoo/android/apps/transit/SearchResultListActivity);
        intent.putExtra(getString(0x7f0d0232), navisearchdata);
        intent.putExtra(getString(0x7f0d022c), val$conditionData);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0059));
        return false;
    }

    l.tmpSt()
    {
        this$1 = final_tmpst;
        val$conditionData = ConditionData.this;
        super();
    }

    // Unreferenced inner class jp/co/yahoo/android/apps/transit/SearchResultBaseActivity$11

/* anonymous class */
    class SearchResultBaseActivity._cls11
        implements android.content.DialogInterface.OnClickListener
    {

        final SearchResultBaseActivity this$0;
        final Bundle val$tmpSt;

        public void onClick(DialogInterface dialoginterface, int i)
        {
            Calendar calendar = Calendar.getInstance();
            ConditionData conditiondata = (ConditionData)conditionData.clone();
            StationData stationdata = (StationData)tmpSt.getSerializable(String.valueOf(i));
            conditiondata.startName = stationdata.getName();
            conditiondata.startCode = stationdata.getId();
            conditiondata.year = calendar.get(1);
            conditiondata.month = 1 + calendar.get(2);
            conditiondata.day = calendar.get(11);
            conditiondata.hour = calendar.get(11);
            conditiondata.minite = calendar.get(12);
            conditiondata.type = getResources().getInteger(0x7f0c006b);
            if (conditiondata.ticket == null)
            {
                ConditionData conditiondata1 = (new SaveCondition(context)).getCond();
                if (conditiondata1 != null)
                {
                    conditiondata.ticket = conditiondata1.ticket;
                }
            }
            NaviSearch navisearch = new NaviSearch(context, conditiondata. new SearchResultBaseActivity._cls11._cls1());
            navisearch.setCondition(conditiondata);
            navisearch.exec();
        }

            
            {
                this$0 = final_searchresultbaseactivity;
                tmpSt = Bundle.this;
                super();
            }
    }

}
