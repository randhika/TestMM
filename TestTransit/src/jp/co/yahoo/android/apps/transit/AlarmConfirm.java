// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;
import jp.co.yahoo.android.apps.transit.common.Alarm;
import jp.co.yahoo.android.apps.transit.viewparts.AlarmConfirmListView;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

public class AlarmConfirm extends TransitBaseActivity
{

    private Alarm alarm;
    private AlarmConfirmListView alarmList;
    private Button btnDelete;
    private TextView errText;
    private LinearLayout listLayout;

    public AlarmConfirm()
    {
    }

    private String getDateTimeString(String s)
    {
        StringBuilder stringbuilder = new StringBuilder(s.substring(0, 4));
        stringbuilder.append("\u5E74");
        stringbuilder.append(s.substring(4, 6));
        stringbuilder.append("\u6708");
        stringbuilder.append(s.substring(6, 8));
        stringbuilder.append("\u65E5 ");
        stringbuilder.append(s.substring(8, 10));
        stringbuilder.append(":");
        stringbuilder.append(s.substring(10, 12));
        return stringbuilder.toString();
    }

    private void showAlarmList()
    {
        ArrayList arraylist;
        ArrayList arraylist1;
        ArrayList arraylist2;
        ArrayList arraylist3;
        ArrayList arraylist4;
        ArrayList arraylist5;
        ArrayList arraylist6;
        int i;
        Iterator iterator;
        if (alarmList != null)
        {
            alarmList.removeAllViews();
        }
        alarmList = new AlarmConfirmListView(this);
        arraylist = (ArrayList)alarm.getAlarmList();
        if (arraylist == null || arraylist.size() == 0)
        {
            errText.setVisibility(0);
            listLayout.setVisibility(8);
            return;
        }
        errText.setVisibility(8);
        arraylist1 = new ArrayList();
        arraylist2 = new ArrayList();
        arraylist3 = new ArrayList();
        arraylist4 = new ArrayList();
        arraylist5 = new ArrayList();
        arraylist6 = new ArrayList();
        i = 0;
        iterator = arraylist.iterator();
_L5:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        Bundle bundle;
        bundle = (Bundle)iterator.next();
        arraylist1.add(String.valueOf(i + 1));
        ConditionData conditiondata;
        StringBuilder stringbuilder1;
        conditiondata = (ConditionData)bundle.getSerializable(getString(0x7f0d022c));
        NaviSearchData navisearchdata1 = (NaviSearchData)bundle.getSerializable(getString(0x7f0d0232));
        ArrayList arraylist12 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)navisearchdata1.routes.get(0)).edges;
        HashMap hashmap1 = navisearchdata1.points;
        int k2 = arraylist12.size();
        String s3 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap1.get(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist12.get(0)).startPointTarget)).stationName;
        String s4 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap1.get(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist12.get(k2 - 1)).startPointTarget)).stationName;
        SpannableString spannablestring1 = new SpannableString((new StringBuilder()).append(s3).append(" \u21D2 ").append(s4).toString());
        Drawable drawable = getResources().getDrawable(0x7f020254);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan imagespan = new ImageSpan(drawable, 1);
        spannablestring1.setSpan(imagespan, -2 + (spannablestring1.length() - s4.length()), -1 + (spannablestring1.length() - s4.length()), 33);
        arraylist2.add(spannablestring1);
        stringbuilder1 = new StringBuilder();
        if (conditiondata.type != 5) goto _L4; else goto _L3
_L3:
        stringbuilder1.append(getString(0x7f0d048b));
_L6:
        arraylist3.add(stringbuilder1.toString());
_L7:
        ArrayList arraylist7;
        ArrayList arraylist8;
        ArrayList arraylist9;
        ArrayList arraylist10;
        arraylist7 = new ArrayList();
        arraylist8 = new ArrayList();
        arraylist9 = new ArrayList();
        arraylist4.add(arraylist7);
        arraylist5.add(arraylist8);
        arraylist6.add(arraylist9);
        arraylist10 = bundle.getParcelableArrayList(getString(0x7f0d0234));
        if (arraylist10 != null)
        {
            break MISSING_BLOCK_LABEL_657;
        }
        i++;
          goto _L5
_L4:
        NaviSearchData navisearchdata2 = (NaviSearchData)bundle.getSerializable(getString(0x7f0d0232));
        String s5 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)navisearchdata2.routes.get(0)).startTime;
        String s6 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)navisearchdata2.routes.get(0)).goalTime;
        stringbuilder1.append(getDateTimeString(s5));
        stringbuilder1.append("\u2192");
        stringbuilder1.append(s6.substring(8, 10));
        stringbuilder1.append(":");
        stringbuilder1.append(s6.substring(10, 12));
          goto _L6
        Exception exception;
        exception;
        arraylist2.add(new SpannableString("-"));
        arraylist3.add("-");
          goto _L7
        ArrayList arraylist11;
        HashMap hashmap;
        Iterator iterator1;
        NaviSearchData navisearchdata = (NaviSearchData)bundle.getSerializable(getString(0x7f0d0232));
        arraylist11 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)navisearchdata.routes.get(0)).edges;
        hashmap = navisearchdata.points;
        iterator1 = arraylist10.iterator();
_L17:
        if (!iterator1.hasNext()) goto _L9; else goto _L8
_L8:
        Bundle bundle1 = (Bundle)iterator1.next();
        int l = bundle1.getInt(getString(0x7f0d01d4));
        if (arraylist11.size() <= 0) goto _L11; else goto _L10
_L10:
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist11.get(l);
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge1;
        edge1 = null;
        if (l <= 0)
        {
            break MISSING_BLOCK_LABEL_781;
        }
        edge1 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist11.get(l - 1);
        String s1;
        int j1;
        int k1;
        StringBuilder stringbuilder;
        s1 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(edge.startPointTarget)).stationName;
        j1 = bundle1.getInt(getString(0x7f0d01cc));
        k1 = bundle1.getInt(getString(0x7f0d0247));
        stringbuilder = new StringBuilder((new StringBuilder()).append(s1).append("\u3000").toString());
        stringbuilder.append("<font color=\"#");
        stringbuilder.append(Integer.toHexString(getResources().getColor(0x7f090054)).substring(2));
        stringbuilder.append("\">");
        if (k1 != getResources().getInteger(0x7f0c000a)) goto _L13; else goto _L12
_L12:
        stringbuilder.append(getString(0x7f0d0301));
_L18:
        SpannableString spannablestring;
        Object aobj1[] = new Object[1];
        aobj1[0] = Integer.valueOf(j1);
        String s2 = getString(0x7f0d0029, aobj1);
        stringbuilder.append(s2);
        stringbuilder.append("</font>");
        int l1 = s2.length();
        android.text.Spanned spanned = Html.fromHtml(stringbuilder.toString());
        spannablestring = new SpannableString(spanned);
        RelativeSizeSpan relativesizespan = new RelativeSizeSpan(1.2F);
        int i2 = spannablestring.length() - l1;
        int j2 = spannablestring.length() - (l1 - String.valueOf(j1).length());
        spannablestring.setSpan(relativesizespan, i2, j2, 33);
        if (l != 0) goto _L15; else goto _L14
_L14:
        if (edge.departureDatetime == null) goto _L15; else goto _L16
_L16:
        String s = getDateTimeString(edge.departureDatetime);
_L19:
        bundle.putString(getString(0x7f0d0241), s1);
_L20:
        arraylist7.add(spannablestring);
        arraylist8.add(s);
        int i1 = bundle1.getInt(getString(0x7f0d01c8));
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i1);
        arraylist9.add(getString(0x7f0d0026, aobj));
          goto _L17
        Exception exception2;
        exception2;
        arraylist7.add(new SpannableString("-"));
        arraylist8.add("-");
        arraylist9.add("-");
          goto _L17
        Exception exception1;
        exception1;
        int j = 0;
        do
        {
            int k = arraylist10.size();
            if (j >= k)
            {
                break;
            }
            arraylist7.add(new SpannableString("-"));
            arraylist8.add("-");
            arraylist9.add("-");
            j++;
        } while (true);
          goto _L9
_L13:
        stringbuilder.append(getString(0x7f0d0284));
          goto _L18
_L15:
        if (edge1 == null)
        {
            break MISSING_BLOCK_LABEL_1380;
        }
        if (edge1.arrivalDatetime == null)
        {
            break MISSING_BLOCK_LABEL_1380;
        }
        s = getDateTimeString(edge1.arrivalDatetime);
          goto _L19
_L11:
        spannablestring = new SpannableString("");
        s = "";
        bundle.putString(getString(0x7f0d0241), "");
          goto _L20
_L9:
        i++;
          goto _L5
_L2:
        alarmList.setListLabels(arraylist1, arraylist2, arraylist3, arraylist4, arraylist5, arraylist6);
        alarmList.setListItems(arraylist);
        listLayout.removeAllViews();
        listLayout.addView(alarmList);
        listLayout.setVisibility(0);
        alarmList.showView();
        return;
        s = "";
          goto _L19
    }

    protected void delClickListener()
    {
        ArrayList arraylist = alarmList.getCheckItems();
        alarm.deleteAlarmSetting(arraylist);
        showAlarmList();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030036);
        setTitle(getString(0x7f0d0025));
        alarm = new Alarm(this);
        listLayout = (LinearLayout)findViewById(0x7f0a018b);
        errText = (TextView)findViewById(0x7f0a0189);
        btnDelete = (Button)findViewById(0x7f0a00ee);
        btnDelete.setOnClickListener(new android.view.View.OnClickListener() {

            final AlarmConfirm this$0;

            public void onClick(View view)
            {
                if (alarmList.getCheckItems().size() < 1)
                {
                    showErrorMessageDialog(getString(0x7f0d012a), getString(0x7f0d0150));
                    return;
                } else
                {
                    showdelMessageDialog(getString(0x7f0d00cc));
                    return;
                }
            }

            
            {
                this$0 = AlarmConfirm.this;
                super();
            }
        });
        setResult(-1);
        dispAd(this, "2080294929", "pv");
    }

    protected void onResume()
    {
        super.onResume();
        showAlarmList();
    }

}
