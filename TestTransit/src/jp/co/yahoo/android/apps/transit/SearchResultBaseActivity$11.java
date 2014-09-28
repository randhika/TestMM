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

class val.tmpSt
    implements android.content.ner
{

    final SearchResultBaseActivity this$0;
    final Bundle val$tmpSt;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        Calendar calendar = Calendar.getInstance();
        final ConditionData conditionData = (ConditionData)SearchResultBaseActivity.this.conditionData.clone();
        StationData stationdata = (StationData)val$tmpSt.getSerializable(String.valueOf(i));
        conditionData.startName = stationdata.getName();
        conditionData.startCode = stationdata.getId();
        conditionData.year = calendar.get(1);
        conditionData.month = 1 + calendar.get(2);
        conditionData.day = calendar.get(11);
        conditionData.hour = calendar.get(11);
        conditionData.minite = calendar.get(12);
        conditionData.type = getResources().getInteger(0x7f0c006b);
        if (conditionData.ticket == null)
        {
            ConditionData conditiondata = (new SaveCondition(context)).getCond();
            if (conditiondata != null)
            {
                conditionData.ticket = conditiondata.ticket;
            }
        }
        NaviSearch navisearch = new NaviSearch(context, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final SearchResultBaseActivity._cls11 this$1;
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
                intent.putExtra(getString(0x7f0d022c), conditionData);
                startActivityForResult(intent, getResources().getInteger(0x7f0c0059));
                return false;
            }

            
            {
                this$1 = SearchResultBaseActivity._cls11.this;
                conditionData = conditiondata;
                super();
            }
        });
        navisearch.setCondition(conditionData);
        navisearch.exec();
    }

    _cls1.val.conditionData()
    {
        this$0 = final_searchresultbaseactivity;
        val$tmpSt = Bundle.this;
        super();
    }
}
