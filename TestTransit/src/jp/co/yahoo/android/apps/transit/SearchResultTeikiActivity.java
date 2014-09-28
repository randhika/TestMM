// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.NaviSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;
import jp.co.yahoo.android.apps.transit.common.StringUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

public class SearchResultTeikiActivity extends TransitBaseActivity
{

    private ConditionData conditionData;
    private LayoutInflater inflater;
    private HashMap points;
    private jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData routeBussiness;
    private jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData routeHighSchool;
    private ArrayList routeTitles;
    private jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData routeUniversity;
    private int route_id;
    private ArrayList routeinfo;
    private String sAllTitle;

    public SearchResultTeikiActivity()
    {
        sAllTitle = "";
    }

    private void onApiError()
    {
        if (routeBussiness != null && routeBussiness.teikiDetials != null)
        {
            ((Button)findViewById(0x7f0a015a)).performClick();
            return;
        } else
        {
            finish();
            return;
        }
    }

    private void setBtn()
    {
        final Button btnBussiness = (Button)findViewById(0x7f0a015a);
        btnBussiness.setTag(getString(0x7f0d057a));
        btnBussiness.setSelected(true);
        final Button btnUniversity = (Button)findViewById(0x7f0a015b);
        btnUniversity.setTag(getString(0x7f0d057c));
        final Button btnHighSchool = (Button)findViewById(0x7f0a015c);
        btnHighSchool.setTag(getString(0x7f0d057b));
        android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

            final SearchResultTeikiActivity this$0;
            final Button val$btnBussiness;
            final Button val$btnHighSchool;
            final Button val$btnUniversity;

            public void onClick(View view)
            {
                String s = (String)view.getTag();
                if (!TransitUtil.isEmpty(s)) goto _L2; else goto _L1
_L1:
                return;
_L2:
                if (s.equals(getString(0x7f0d057a)))
                {
                    touchTapRD(getString(0x7f0d0408));
                    btnBussiness.setSelected(true);
                    btnUniversity.setSelected(false);
                    btnHighSchool.setSelected(false);
                } else
                {
                    if (!s.equals(getString(0x7f0d057c)))
                    {
                        continue; /* Loop/switch isn't completed */
                    }
                    touchTapRD(getString(0x7f0d0407));
                    btnBussiness.setSelected(false);
                    btnUniversity.setSelected(true);
                    btnHighSchool.setSelected(false);
                }
_L4:
                research((String)view.getTag());
                return;
                if (!s.equals(getString(0x7f0d057b))) goto _L1; else goto _L3
_L3:
                touchTapRD(getString(0x7f0d0406));
                btnBussiness.setSelected(false);
                btnUniversity.setSelected(false);
                btnHighSchool.setSelected(true);
                  goto _L4
                if (true) goto _L1; else goto _L5
_L5:
            }

            
            {
                this$0 = SearchResultTeikiActivity.this;
                btnBussiness = button;
                btnUniversity = button1;
                btnHighSchool = button2;
                super();
            }
        };
        btnBussiness.setOnClickListener(onclicklistener);
        btnUniversity.setOnClickListener(onclicklistener);
        btnHighSchool.setOnClickListener(onclicklistener);
    }

    private void setRouteData()
    {
        ArrayList arraylist = routeBussiness.teikiDetials;
        if (arraylist == null)
        {
            return;
        }
        routeinfo = new ArrayList(arraylist.size());
        routeTitles = new ArrayList(arraylist.size());
        for (int i = 0; i < arraylist.size(); i++)
        {
            jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.TeikiDetail teikidetail = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.TeikiDetail)arraylist.get(i);
            routeinfo.add((new StringBuilder()).append(Integer.toString(teikidetail.edgeFrom)).append("-").append(Integer.toString(teikidetail.edgeTo)).toString());
            String s = null;
            String s1 = null;
            Iterator iterator = routeBussiness.edges.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)iterator.next();
                if (edge.edgeid == teikidetail.edgeFrom)
                {
                    s = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)points.get(edge.startPointTarget)).stationName;
                }
                if (edge.edgeid == teikidetail.edgeTo)
                {
                    s1 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)points.get(edge.goalPointTarget)).stationName;
                }
                if (s == null || s1 == null)
                {
                    continue;
                }
                routeTitles.add((new StringBuilder()).append(s).append("\u21D4").append(s1).toString());
                break;
            } while (true);
            if (i == 0)
            {
                sAllTitle = s;
            }
            if (i == -1 + arraylist.size())
            {
                sAllTitle = (new StringBuilder()).append(sAllTitle).append("\u21D4").append(s1).toString();
            }
        }

        ((TextView)findViewById(0x7f0a015e)).setText(sAllTitle);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030031);
        inflater = (LayoutInflater)getSystemService("layout_inflater");
        setTitle(getString(0x7f0d030b));
        Intent intent = getIntent();
        conditionData = (ConditionData)intent.getSerializableExtra(getString(0x7f0d022c));
        routeBussiness = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)intent.getSerializableExtra(getString(0x7f0d0230));
        points = (HashMap)intent.getSerializableExtra(getString(0x7f0d022f));
        route_id = intent.getIntExtra(getString(0x7f0d0231), 1);
        if (routeBussiness == null || conditionData == null || points == null)
        {
            return;
        }
        if (routeBussiness.teikiDetials == null)
        {
            research(getString(0x7f0d057a));
        } else
        {
            showTeiki(routeBussiness);
        }
        setBtn();
        dispAd(this, "2080333378", "pv");
    }

    public void research(String s)
    {
        if (!TransitUtil.isEmpty(s)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (s.equals(getString(0x7f0d057a)))
        {
            if (routeBussiness != null && routeBussiness.teikiDetials != null)
            {
                showTeiki(routeBussiness);
                return;
            }
            break; /* Loop/switch isn't completed */
        }
        if (s.equals(getString(0x7f0d057c)))
        {
            if (routeUniversity != null && routeUniversity.teikiDetials != null)
            {
                showTeiki(routeUniversity);
                return;
            }
            break; /* Loop/switch isn't completed */
        }
        if (!s.equals(getString(0x7f0d057b)))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (routeHighSchool != null && routeHighSchool.teikiDetials != null)
        {
            showTeiki(routeHighSchool);
            return;
        }
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L3
_L3:
        NaviSearch navisearch = new NaviSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final SearchResultTeikiActivity this$0;

            public boolean onCanceled()
            {
                onApiError();
                return false;
            }

            public boolean onError(APIError apierror)
            {
                String s1 = apierror.getMessage();
                if (StringUtil.isEmpty(s1))
                {
                    s1 = getString(0x7f0d0108);
                }
                showSimpleErrorMessageDialog(s1, new android.content.DialogInterface.OnClickListener() {

                    final _cls2 this$1;

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        onApiError();
                    }

            
            {
                this$1 = _cls2.this;
                super();
            }
                }, new android.content.DialogInterface.OnCancelListener() {

                    final _cls2 this$1;

                    public void onCancel(DialogInterface dialoginterface)
                    {
                        onApiError();
                    }

            
            {
                this$1 = _cls2.this;
                super();
            }
                });
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                NaviSearchData navisearchdata = NaviSearch.m_routeList;
                if (!TransitUtil.isEmpty(conditionData.passtype)) goto _L2; else goto _L1
_L1:
                return false;
_L2:
                if (conditionData.passtype.equals(getString(0x7f0d057a)))
                {
                    routeBussiness = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)navisearchdata.routes.get(0);
                } else
                {
                    if (!conditionData.passtype.equals(getString(0x7f0d057c)))
                    {
                        continue; /* Loop/switch isn't completed */
                    }
                    routeUniversity = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)navisearchdata.routes.get(0);
                }
_L4:
                showTeiki((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)navisearchdata.routes.get(0));
                return false;
                if (!conditionData.passtype.equals(getString(0x7f0d057b))) goto _L1; else goto _L3
_L3:
                routeHighSchool = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)navisearchdata.routes.get(0);
                  goto _L4
                if (true) goto _L1; else goto _L5
_L5:
            }

            
            {
                this$0 = SearchResultTeikiActivity.this;
                super();
            }
        });
        conditionData.passtype = s;
        navisearch.setStart(1 + route_id);
        navisearch.setResultCount(1);
        navisearch.setCondition(conditionData);
        navisearch.exec();
        return;
    }

    protected void showTeiki(jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData naviroutedata)
    {
        if (routeinfo == null || routeinfo.size() < 1)
        {
            setRouteData();
        }
        boolean flag;
        LinearLayout linearlayout;
        ArrayList arraylist;
        int i;
        Iterator iterator;
        if (TransitUtil.isEmpty(naviroutedata.Teiki1) || naviroutedata.Teiki1.equals("0"))
        {
            ((TextView)findViewById(0x7f0a015f)).setText("\u30FC");
        } else
        {
            ((TextView)findViewById(0x7f0a015f)).setText((new StringBuilder()).append(naviroutedata.Teiki1).append(getString(0x7f0d0339)).toString());
        }
        if (TransitUtil.isEmpty(naviroutedata.Teiki3) || naviroutedata.Teiki3.equals("0"))
        {
            ((TextView)findViewById(0x7f0a0161)).setText("\u30FC");
        } else
        {
            ((TextView)findViewById(0x7f0a0161)).setText((new StringBuilder()).append(naviroutedata.Teiki3).append(getString(0x7f0d0339)).toString());
        }
        if (TransitUtil.isEmpty(naviroutedata.Teiki6) || naviroutedata.Teiki6.equals("0"))
        {
            ((TextView)findViewById(0x7f0a0163)).setText("\u30FC");
        } else
        {
            ((TextView)findViewById(0x7f0a0163)).setText((new StringBuilder()).append(naviroutedata.Teiki6).append(getString(0x7f0d0339)).toString());
        }
        if (naviroutedata.previousTaxFareTeiki1)
        {
            ((ImageView)findViewById(0x7f0a0160)).setVisibility(0);
            flag = true;
        } else
        {
            ((ImageView)findViewById(0x7f0a0160)).setVisibility(8);
            flag = false;
        }
        if (naviroutedata.previousTaxFareTeiki3)
        {
            ((ImageView)findViewById(0x7f0a0162)).setVisibility(0);
            flag = true;
        } else
        {
            ((ImageView)findViewById(0x7f0a0162)).setVisibility(8);
        }
        if (naviroutedata.previousTaxFareTeiki6)
        {
            ((ImageView)findViewById(0x7f0a0164)).setVisibility(0);
            flag = true;
        } else
        {
            ((ImageView)findViewById(0x7f0a0164)).setVisibility(8);
        }
        if (flag)
        {
            ((RelativeLayout)findViewById(0x7f0a0166)).setVisibility(0);
        } else
        {
            ((RelativeLayout)findViewById(0x7f0a0166)).setVisibility(8);
        }
        linearlayout = (LinearLayout)findViewById(0x7f0a0165);
        linearlayout.removeAllViews();
        arraylist = naviroutedata.teikiDetials;
        i = -1;
        iterator = routeinfo.iterator();
        while (iterator.hasNext()) 
        {
            jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.TeikiDetail teikidetail;
label0:
            {
                String s = (String)iterator.next();
                i++;
                teikidetail = null;
                if (arraylist == null)
                {
                    break label0;
                }
                int j = arraylist.size();
                teikidetail = null;
                if (j <= 0)
                {
                    break label0;
                }
                Iterator iterator1 = arraylist.iterator();
                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.TeikiDetail teikidetail1;
                do
                {
                    boolean flag1 = iterator1.hasNext();
                    teikidetail = null;
                    if (!flag1)
                    {
                        break label0;
                    }
                    teikidetail1 = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.TeikiDetail)iterator1.next();
                } while (!s.equals((new StringBuilder()).append(Integer.toString(teikidetail1.edgeFrom)).append("-").append(Integer.toString(teikidetail1.edgeTo)).toString()));
                teikidetail = teikidetail1;
            }
            LinearLayout linearlayout1 = new LinearLayout(this);
            linearlayout1.setOrientation(1);
            RelativeLayout relativelayout = (RelativeLayout)inflater.inflate(0x7f03005d, null);
            ((TextView)relativelayout.findViewById(0x7f0a01fc)).setText((CharSequence)routeTitles.get(i));
            linearlayout1.addView(relativelayout);
            RelativeLayout relativelayout1 = (RelativeLayout)inflater.inflate(0x7f03007f, null);
            ((TextView)relativelayout1.findViewById(0x7f0a004d)).setText(getString(0x7f0d030c));
            RelativeLayout relativelayout2;
            RelativeLayout relativelayout3;
            if (teikidetail == null || teikidetail.teiki1 == null || teikidetail.teiki1.equals("0"))
            {
                ((TextView)relativelayout1.findViewById(0x7f0a0276)).setText(getString(0x7f0d02a0));
            } else
            {
                ((TextView)relativelayout1.findViewById(0x7f0a0276)).setText((new StringBuilder()).append(teikidetail.teiki1).append(getString(0x7f0d0339)).toString());
            }
            relativelayout2 = (RelativeLayout)inflater.inflate(0x7f03007f, null);
            ((TextView)relativelayout2.findViewById(0x7f0a004d)).setText(getString(0x7f0d030d));
            if (teikidetail == null || teikidetail.teiki3 == null || teikidetail.teiki3.equals("0"))
            {
                ((TextView)relativelayout2.findViewById(0x7f0a0276)).setText(getString(0x7f0d02a0));
            } else
            {
                ((TextView)relativelayout2.findViewById(0x7f0a0276)).setText((new StringBuilder()).append(teikidetail.teiki3).append(getString(0x7f0d0339)).toString());
            }
            relativelayout3 = (RelativeLayout)inflater.inflate(0x7f03007f, null);
            ((TextView)relativelayout3.findViewById(0x7f0a004d)).setText(getString(0x7f0d030e));
            if (teikidetail == null || teikidetail.teiki6 == null || teikidetail.teiki6.equals("0"))
            {
                ((TextView)relativelayout3.findViewById(0x7f0a0276)).setText(getString(0x7f0d02a0));
            } else
            {
                ((TextView)relativelayout3.findViewById(0x7f0a0276)).setText((new StringBuilder()).append(teikidetail.teiki6).append(getString(0x7f0d0339)).toString());
            }
            if (teikidetail != null && teikidetail.previous)
            {
                ((ImageView)relativelayout1.findViewById(0x7f0a0049)).setVisibility(0);
                ((ImageView)relativelayout2.findViewById(0x7f0a0049)).setVisibility(0);
                ((ImageView)relativelayout3.findViewById(0x7f0a0049)).setVisibility(0);
            }
            linearlayout1.addView(relativelayout1);
            linearlayout1.addView(relativelayout2);
            linearlayout1.addView(relativelayout3);
            linearlayout.addView(linearlayout1);
        }
    }



/*
    static jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData access$102(SearchResultTeikiActivity searchresultteikiactivity, jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData naviroutedata)
    {
        searchresultteikiactivity.routeBussiness = naviroutedata;
        return naviroutedata;
    }

*/


/*
    static jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData access$202(SearchResultTeikiActivity searchresultteikiactivity, jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData naviroutedata)
    {
        searchresultteikiactivity.routeUniversity = naviroutedata;
        return naviroutedata;
    }

*/


/*
    static jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData access$302(SearchResultTeikiActivity searchresultteikiactivity, jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData naviroutedata)
    {
        searchresultteikiactivity.routeHighSchool = naviroutedata;
        return naviroutedata;
    }

*/

}
