// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.res.Resources;
import android.view.View;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultListActivity

class this._cls0
    implements android.view.esultListActivity._cls7
{

    final SearchResultListActivity this$0;

    public void onClick(View view)
    {
        touchTapRD(getString(0x7f0d03c2));
        SearchResultListActivity.access$502(SearchResultListActivity.this, (ConditionData)SearchResultListActivity.access$400(SearchResultListActivity.this).clone());
        SearchResultListActivity.access$500(SearchResultListActivity.this).mtf = -1;
        HashMap hashmap = SearchResultListActivity.access$1300(SearchResultListActivity.this).points;
        ArrayList arraylist = SearchResultListActivity.access$1300(SearchResultListActivity.this).routes;
        ArrayList arraylist1 = ((jp.co.yahoo.android.apps.transit.api.data.a)arraylist.get(0)).edges;
        jp.co.yahoo.android.apps.transit.api.data.a a = (jp.co.yahoo.android.apps.transit.api.data.a)hashmap.get(((jp.co.yahoo.android.apps.transit.api.data.a)arraylist1.get(0)).intTarget);
        SearchResultListActivity.access$500(SearchResultListActivity.this).startName = a.stationName;
        SearchResultListActivity.access$500(SearchResultListActivity.this).startCode = "";
        jp.co.yahoo.android.apps.transit.api.data.a a1 = (jp.co.yahoo.android.apps.transit.api.data.a)hashmap.get(((jp.co.yahoo.android.apps.transit.api.data.a)arraylist1.get(-1 + arraylist1.size())).intTarget);
        SearchResultListActivity.access$500(SearchResultListActivity.this).goalName = a1.stationName;
        SearchResultListActivity.access$500(SearchResultListActivity.this).goalCode = "";
        String s = ((jp.co.yahoo.android.apps.transit.api.data.a)arraylist.get(0)).startTime;
        int i = Integer.parseInt(s.substring(0, 4));
        int j = Integer.parseInt(s.substring(4, 6));
        int k = Integer.parseInt(s.substring(6, 8));
        int l = Integer.parseInt(s.substring(8, 10));
        int i1 = Integer.parseInt(s.substring(10));
        GregorianCalendar gregoriancalendar = new GregorianCalendar(i, j - 1, k, l, i1);
        gregoriancalendar.add(12, 1);
        SearchResultListActivity.access$500(SearchResultListActivity.this).year = gregoriancalendar.get(1);
        SearchResultListActivity.access$500(SearchResultListActivity.this).month = 1 + gregoriancalendar.get(2);
        SearchResultListActivity.access$500(SearchResultListActivity.this).day = gregoriancalendar.get(5);
        SearchResultListActivity.access$500(SearchResultListActivity.this).hour = gregoriancalendar.get(11);
        SearchResultListActivity.access$500(SearchResultListActivity.this).minite = gregoriancalendar.get(12);
        if (SearchResultListActivity.access$1400(SearchResultListActivity.this) != null)
        {
            SearchResultListActivity.access$500(SearchResultListActivity.this).startLat = SearchResultListActivity.access$1400(SearchResultListActivity.this).lat;
            SearchResultListActivity.access$500(SearchResultListActivity.this).startLon = SearchResultListActivity.access$1400(SearchResultListActivity.this).lon;
        }
        if (SearchResultListActivity.access$1500(SearchResultListActivity.this) != null)
        {
            SearchResultListActivity.access$500(SearchResultListActivity.this).goalLat = SearchResultListActivity.access$1500(SearchResultListActivity.this).lat;
            SearchResultListActivity.access$500(SearchResultListActivity.this).goalLon = SearchResultListActivity.access$1500(SearchResultListActivity.this).lon;
        }
        SearchResultListActivity.access$500(SearchResultListActivity.this).type = getResources().getInteger(0x7f0c0070);
        SearchResultListActivity.access$500(SearchResultListActivity.this).afterFinal = true;
        SearchResultListActivity.access$500(SearchResultListActivity.this).midnightBus = true;
        search();
    }

    iPointData()
    {
        this$0 = SearchResultListActivity.this;
        super();
    }
}
