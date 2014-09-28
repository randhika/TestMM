// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import jp.co.yahoo.android.apps.transit.SearchResultTeikiActivity;
import jp.co.yahoo.android.apps.transit.SearchResultView;
import jp.co.yahoo.android.apps.transit.TransitBaseActivity;
import jp.co.yahoo.android.apps.transit.api.LocationSearch;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.common.util.YBrowserFRUtils;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            SearchDetailCondition

public class SearchResultFragmentView extends LinearLayout
    implements jp.co.yahoo.android.apps.transit.api.LocationSearch.LocationSearchlListener, jp.co.yahoo.android.apps.transit.SearchResultView.SearchResultCallback
{

    public static final String FORMAT_DIA_WARN = "\u203B%s - %s";
    public static final String FORMAT_TIME_GOAL_FOR_ROUTE = "%02d:%02d";
    public static final String FORMAT_TIME_START_FOR_ROUTE = "%02d:%02d";
    private ConditionData conditionData;
    private Context context;
    private LinearLayout convertView;
    private SearchResultView detail;
    private Map diaWarns;
    private Bitmap iconCheep;
    private Bitmap iconEasy;
    private Bitmap iconFast;
    private boolean isAverage;
    private String linkTags;
    private HashMap points;
    private Resources res;
    public int result_id;
    public jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData route;
    private boolean warningsExist;

    public SearchResultFragmentView(Context context1)
    {
        super(context1);
        convertView = null;
        warningsExist = false;
        diaWarns = new HashMap();
        context = context1;
        res = context1.getResources();
    }

    private void getNowPlace()
    {
        (new LocationSearch(context, this)).getCurrentAddress();
    }

    private void setFinalSideLink()
    {
        LayoutInflater layoutinflater = (LayoutInflater)context.getSystemService("layout_inflater");
        LinearLayout linearlayout = (LinearLayout)convertView.findViewById(0x7f0a02d7);
        LinearLayout linearlayout1 = (LinearLayout)layoutinflater.inflate(0x7f03009b, null);
        TextView textview = (TextView)linearlayout1.findViewById(0x7f0a0222);
        textview.setText(0x7f0d02e1);
        textview.setOnClickListener(new android.view.View.OnClickListener() {

            final SearchResultFragmentView this$0;

            public void onClick(View view)
            {
                getNowPlace();
                linkTags = "hotel";
            }

            
            {
                this$0 = SearchResultFragmentView.this;
                super();
            }
        });
        linearlayout.addView(linearlayout1);
        LinearLayout linearlayout2 = (LinearLayout)layoutinflater.inflate(0x7f03009b, null);
        TextView textview1 = (TextView)linearlayout2.findViewById(0x7f0a0222);
        textview1.setText(0x7f0d02e2);
        textview1.setOnClickListener(new android.view.View.OnClickListener() {

            final SearchResultFragmentView this$0;

            public void onClick(View view)
            {
                getNowPlace();
                linkTags = "taxi";
            }

            
            {
                this$0 = SearchResultFragmentView.this;
                super();
            }
        });
        linearlayout.addView(linearlayout2);
        linearlayout.setVisibility(0);
    }

    private void setResultToView()
    {
        iconCheep = BitmapFactory.decodeResource(res, 0x7f0201ed);
        iconEasy = BitmapFactory.decodeResource(res, 0x7f0201d0);
        iconFast = BitmapFactory.decodeResource(res, 0x7f0201b6);
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData naviroutedata = route;
        if (conditionData.afterFinal)
        {
            setFinalSideLink();
        }
        TextView textview = (TextView)convertView.findViewById(0x7f0a02c7);
        TextView textview1 = (TextView)convertView.findViewById(0x7f0a02c9);
        int i;
        int j;
        int k;
        String s;
        TextView textview2;
        TextView textview3;
        String s1;
        String s2;
        TextView textview4;
        ImageView imageview;
        ImageView imageview1;
        ImageView imageview2;
        LinearLayout linearlayout;
        TextView textview5;
        double d;
        if (!isAverage)
        {
            String s3 = naviroutedata.startTime;
            String s4 = naviroutedata.goalTime;
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(Integer.parseInt(s3.substring(8, 10)));
            aobj[1] = Integer.valueOf(Integer.parseInt(s3.substring(10)));
            textview.setText(String.format("%02d:%02d", aobj));
            Object aobj1[] = new Object[2];
            aobj1[0] = Integer.valueOf(Integer.parseInt(s4.substring(8, 10)));
            aobj1[1] = Integer.valueOf(Integer.parseInt(s4.substring(10)));
            textview1.setText(String.format("%02d:%02d", aobj1));
        } else
        {
            ((ImageView)convertView.findViewById(0x7f0a02c8)).setVisibility(8);
            textview.setText("");
            textview1.setText("");
        }
        i = naviroutedata.totaltime;
        j = i;
        k = 0;
        if (60 <= j)
        {
            k = i / 60;
            i %= 60;
        }
        if (k > 0)
        {
            s = Integer.toString(k) + context.getString(0x7f0d02d2) + Integer.toString(i) + context.getString(0x7f0d02d4);
        } else
        {
            s = Integer.toString(i) + context.getString(0x7f0d02d4);
        }
        textview2 = (TextView)convertView.findViewById(0x7f0a02ca);
        textview3 = (TextView)convertView.findViewById(0x7f0a02cb);
        if (!isAverage)
        {
            textview2.setText((new StringBuilder()).append("(").append(s).append(")").toString());
            textview3.setText("");
        } else
        {
            textview3.setText(s);
            textview2.setText("");
        }
        setTeiki(naviroutedata);
        s1 = naviroutedata.totalPrice;
        s2 = (new StringBuilder()).append(s1).append(context.getString(0x7f0d02da)).toString();
        ((TextView)convertView.findViewById(0x7f0a02d0)).setText(s2);
        textview4 = (TextView)convertView.findViewById(0x7f0a02cf);
        if (conditionData.ticket == null)
        {
            textview4.setVisibility(8);
        } else
        if (conditionData.ticket.equals(res.getString(0x7f0d0582)))
        {
            textview4.setText(res.getString(0x7f0d02e9));
            textview4.setCompoundDrawablesWithIntrinsicBounds(0x7f0201b9, 0, 0, 0);
        } else
        {
            textview4.setText(res.getString(0x7f0d02ea));
            textview4.setCompoundDrawablesWithIntrinsicBounds(0x7f020199, 0, 0, 0);
        }
        ((TextView)convertView.findViewById(0x7f0a02d2)).setText((new StringBuilder()).append(Integer.toString(naviroutedata.transfercount)).append(context.getString(0x7f0d02d1)).toString());
        imageview = (ImageView)convertView.findViewById(0x7f0a02ce);
        if (naviroutedata.cheap)
        {
            imageview.setImageBitmap(iconCheep);
        } else
        {
            imageview.setVisibility(8);
        }
        imageview1 = (ImageView)convertView.findViewById(0x7f0a02cd);
        if (naviroutedata.easy)
        {
            imageview1.setImageBitmap(iconEasy);
        } else
        {
            imageview1.setVisibility(8);
        }
        imageview2 = (ImageView)convertView.findViewById(0x7f0a02cc);
        if (naviroutedata.fast)
        {
            imageview2.setImageBitmap(iconFast);
        } else
        {
            imageview2.setVisibility(8);
        }
        linearlayout = (LinearLayout)convertView.findViewById(0x7f0a02d6);
        detail = new SearchResultView(context, this);
        detail.setValues(conditionData);
        detail.setChildren(linearlayout, context, result_id, route, points);
        textview5 = (TextView)convertView.findViewById(0x7f0a02d3);
        d = (double)naviroutedata.totaldistance / 10D;
        textview5.setText((new StringBuilder()).append(d).append("km").toString());
        showWarning(naviroutedata, detail.isAirPlane());
    }

    private void setSearchConditions()
    {
        ((TransitBaseActivity)context).setTitle((new StringBuilder()).append(context.getString(0x7f0d04ab)).append(" - ").append(getSearchDate()).toString());
        SearchDetailCondition searchdetailcondition = new SearchDetailCondition(context);
        LinearLayout linearlayout = (LinearLayout)convertView.findViewById(0x7f0a0177);
        linearlayout.removeAllViews();
        linearlayout.addView(searchdetailcondition);
        ImageView imageview = (ImageView)convertView.findViewById(0x7f0a01c8);
        ImageView imageview1 = (ImageView)convertView.findViewById(0x7f0a02d5);
        if (isAverage)
        {
            imageview.setVisibility(8);
            imageview1.setVisibility(8);
        }
    }

    private void setTeiki(final jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData result)
    {
        LinearLayout linearlayout = (LinearLayout)convertView.findViewById(0x7f0a02e1);
        if (TransitUtil.isEmpty(result.Teiki1) || result.Teiki1.equals("0"))
        {
            linearlayout.setVisibility(8);
            ((ImageView)convertView.findViewById(0x7f0a02e0)).setVisibility(8);
            return;
        } else
        {
            linearlayout.setOnClickListener(new android.view.View.OnClickListener() {

                final SearchResultFragmentView this$0;
                final jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData val$result;

                public void onClick(View view)
                {
                    ((TransitBaseActivity)context).touchTapRD(context.getString(0x7f0d0405));
                    Intent intent = new Intent(context, jp/co/yahoo/android/apps/transit/SearchResultTeikiActivity);
                    intent.putExtra(context.getString(0x7f0d0231), result_id);
                    intent.putExtra(context.getString(0x7f0d022f), points);
                    intent.putExtra(context.getString(0x7f0d0230), result);
                    intent.putExtra(context.getString(0x7f0d022c), conditionData);
                    ((Activity)context).startActivityForResult(intent, getResources().getInteger(0x7f0c0058));
                }

            
            {
                this$0 = SearchResultFragmentView.this;
                result = naviroutedata;
                super();
            }
            });
            return;
        }
    }

    private void showWarning(jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData naviroutedata, boolean flag)
    {
        LinearLayout linearlayout;
label0:
        {
label1:
            {
label2:
                {
                    warningsExist = false;
                    ArrayList arraylist = naviroutedata.edges;
                    int i = 0;
                    linearlayout = (LinearLayout)convertView.findViewById(0x7f0a02d8);
                    TextView textview = (TextView)linearlayout.findViewById(0x7f0a02d9);
                    if (arraylist.size() <= 0)
                    {
                        break label1;
                    }
                    int j = arraylist.size();
                    int k = 0;
                    LinearLayout linearlayout1;
                    boolean flag1;
label3:
                    do
                    {
                        String s2;
                        int l;
label4:
                        {
                            if (k < j)
                            {
                                jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge edge = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist.get(i);
                                String s = Integer.toString(edge.attentionId);
                                String s1 = edge.attentionText;
                                s2 = edge.timeType;
                                l = edge.traffic;
                                if (s != "" && !"0".equals(s) && !diaWarns.containsKey(s) && s1 != null && s1.length() > 0)
                                {
                                    diaWarns.put(s, s1);
                                    warningsExist = true;
                                }
                                i++;
                                if (500 >= i)
                                {
                                    break label4;
                                }
                            }
                            if (diaWarns.size() <= 0)
                            {
                                break label2;
                            }
                            LayoutInflater layoutinflater = (LayoutInflater)context.getSystemService("layout_inflater");
                            linearlayout1 = (LinearLayout)linearlayout.findViewById(0x7f0a02dd);
                            flag1 = false;
                            for (Iterator iterator = diaWarns.entrySet().iterator(); iterator.hasNext();)
                            {
                                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                                LinearLayout linearlayout2 = (LinearLayout)layoutinflater.inflate(0x7f0300b8, null);
                                TextView textview1 = (TextView)linearlayout2.findViewById(0x7f0a0347);
                                Object aobj[] = new Object[2];
                                aobj[0] = entry.getKey();
                                aobj[1] = entry.getValue();
                                textview1.setText(String.format("\u203B%s - %s", aobj));
                                linearlayout1.addView(linearlayout2);
                                flag1 = true;
                                warningsExist = true;
                            }

                            break label3;
                        }
                        if ("None".equals(s2) || "Interval".equals(s2) || "OutSide".equals(s2))
                        {
                            textview.setVisibility(0);
                            warningsExist = true;
                        } else
                        if ("Average".equals(s2) && l != res.getInteger(0x7f0c001f) && l != res.getInteger(0x7f0c001a))
                        {
                            textview.setVisibility(0);
                            warningsExist = true;
                        }
                        k++;
                    } while (true);
                    if (flag1)
                    {
                        linearlayout1.setVisibility(0);
                    }
                }
                if (flag)
                {
                    ((TextView)linearlayout.findViewById(0x7f0a02dc)).setVisibility(0);
                    warningsExist = true;
                }
                if (naviroutedata.totalPreviousTaxFare)
                {
                    ((LinearLayout)linearlayout.findViewById(0x7f0a02db)).setVisibility(0);
                    warningsExist = true;
                }
                if (!warningsExist)
                {
                    break label0;
                }
                linearlayout.setVisibility(0);
            }
            return;
        }
        linearlayout.setVisibility(8);
    }

    public void createSearchResultView()
    {
        setSearchConditions();
        setResultToView();
    }

    public View getRouteView()
    {
        return convertView;
    }

    public String getSearchDate()
    {
        String s = route.startTime;
        return (new StringBuilder()).append(s.substring(4, 6)).append("\u6708").append(s.substring(6, 8)).append("\u65E5").toString();
    }

    public void onChangedImakoko(boolean flag)
    {
        LinearLayout linearlayout = (LinearLayout)convertView.findViewById(0x7f0a02d8);
        LinearLayout linearlayout1 = (LinearLayout)linearlayout.findViewById(0x7f0a02da);
        if (flag)
        {
            if (linearlayout1.getVisibility() != 0)
            {
                linearlayout1.setVisibility(0);
            }
            if (linearlayout.getVisibility() != 0)
            {
                linearlayout.setVisibility(0);
            }
        } else
        {
            if (linearlayout1.getVisibility() != 8)
            {
                linearlayout1.setVisibility(8);
            }
            if (!warningsExist && linearlayout.getVisibility() != 8)
            {
                linearlayout.setVisibility(8);
                return;
            }
        }
    }

    public void onError(String s, String s1)
    {
        if (s1 != null)
        {
            ((TransitBaseActivity)context).showSimpleErrorMessageDialog(s1);
        }
    }

    public void onSuccess(String s, Bundle bundle)
    {
        String s1 = bundle.getString(context.getString(0x7f0d01a3));
        String s2 = bundle.getString(context.getString(0x7f0d01a4));
        if (linkTags == "hotel")
        {
            String s4 = (new StringBuilder()).append(context.getString(0x7f0d0551)).append("?genrecd=0304001&lat=").append(s1).append("&lon=").append(s2).toString();
            YBrowserFRUtils.openURL((Activity)context, s4, context.getString(0x7f0d0597));
        } else
        if (linkTags == "taxi")
        {
            String s3 = (new StringBuilder()).append(context.getString(0x7f0d0551)).append("?genrecd=0306001&lat=").append(s1).append("&lon=").append(s2).toString();
            YBrowserFRUtils.openURL((Activity)context, s3, context.getString(0x7f0d0597));
            return;
        }
    }

    public void onTimeout(String s, String s1)
    {
        if (s1 != null)
        {
            ((TransitBaseActivity)context).showSimpleErrorMessageDialog(s1);
        }
    }

    public void pauseImakokoUpdate()
    {
        if (detail != null)
        {
            detail.pauseImakokoUpdate();
        }
    }

    public void scrollToImakoko(int i)
    {
        ScrollView scrollview = (ScrollView)convertView.findViewById(0x7f0a00fe);
        scrollview.smoothScrollTo(0, i - scrollview.getHeight() / 2);
    }

    public void setCond(ConditionData conditiondata)
    {
        conditionData = conditiondata;
    }

    public void setImakokoUpdate(boolean flag)
    {
        if (detail != null)
        {
            detail.setImakokoUpdate(flag);
        }
    }

    public void setPoint(HashMap hashmap)
    {
        points = hashmap;
    }

    public void setRoute(NaviSearchData navisearchdata)
    {
        route = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)navisearchdata.routes.get(result_id);
        isAverage = false;
    }

    public void setRouteID(int i)
    {
        result_id = i;
    }

    public void setView(LinearLayout linearlayout)
    {
        convertView = linearlayout;
    }



/*
    static String access$102(SearchResultFragmentView searchresultfragmentview, String s)
    {
        searchresultfragmentview.linkTags = s;
        return s;
    }

*/



}
