// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, SearchResultActivity

public class SearchResultListView extends LinearLayout
{

    private Context SearchResultContext;
    private Boolean afterFinalFlag;
    private ConditionData conditionData;
    private NaviSearchData contentData;
    private boolean dataFilled;

    public SearchResultListView(Context context)
    {
        super(context);
        dataFilled = false;
        contentData = null;
    }

    public NaviSearchData getData()
    {
        return contentData;
    }

    public boolean isDataFilled()
    {
        return dataFilled;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
    }

    public void setChildren(Context context, NaviSearchData navisearchdata)
    {
        LayoutInflater layoutinflater;
        Resources resources;
        android.graphics.Bitmap bitmap;
        android.graphics.Bitmap bitmap1;
        android.graphics.Bitmap bitmap2;
        HashMap hashmap;
        ArrayList arraylist;
        boolean flag;
        removeAllViews();
        SearchResultContext = context;
        setOrientation(1);
        layoutinflater = LayoutInflater.from(SearchResultContext);
        resources = getResources();
        bitmap = BitmapFactory.decodeResource(resources, 0x7f0201ed);
        bitmap1 = BitmapFactory.decodeResource(resources, 0x7f0201d0);
        bitmap2 = BitmapFactory.decodeResource(resources, 0x7f0201b6);
        hashmap = navisearchdata.points;
        arraylist = navisearchdata.routes;
        int i = resources.getInteger(0x7f0c006a);
        int j = conditionData.type;
        flag = false;
        if (i == j)
        {
            flag = true;
        }
        if (arraylist.size() <= 0) goto _L2; else goto _L1
_L1:
        int k;
        int l;
        k = arraylist.size();
        l = 0;
_L6:
        if (l >= k) goto _L2; else goto _L3
_L3:
        jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData naviroutedata = (jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviRouteData)arraylist.get(l);
        StringBuilder stringbuilder = new StringBuilder(resources.getString(0x7f0d04ac));
        LinearLayout linearlayout = (LinearLayout)layoutinflater.inflate(0x7f030075, null);
        TextView textview = (TextView)linearlayout.findViewById(0x7f0a0227);
        TextView textview1 = (TextView)linearlayout.findViewById(0x7f0a022a);
        TextView textview2 = (TextView)linearlayout.findViewById(0x7f0a022c);
        TextView textview3 = (TextView)linearlayout.findViewById(0x7f0a022d);
        TextView textview4 = (TextView)linearlayout.findViewById(0x7f0a022e);
        ImageView imageview = (ImageView)linearlayout.findViewById(0x7f0a0237);
        ImageView imageview1 = (ImageView)linearlayout.findViewById(0x7f0a0236);
        ImageView imageview2 = (ImageView)linearlayout.findViewById(0x7f0a0235);
        TextView textview5 = (TextView)linearlayout.findViewById(0x7f0a0231);
        TextView textview6 = (TextView)linearlayout.findViewById(0x7f0a0233);
        TextView textview7 = (TextView)linearlayout.findViewById(0x7f0a0238);
        textview.setText(Integer.toString(l + 1));
        stringbuilder.append(Integer.toString(l + 1));
        stringbuilder.append("\u3000");
        int i1;
        int j1;
        int k1;
        String s;
        String s1;
        final int INDEX;
        android.view.View.OnClickListener onclicklistener;
        ArrayList arraylist1;
        int l1;
        ArrayList arraylist2;
        int i2;
        StringBuilder stringbuilder1;
        if (!flag)
        {
            String s4 = naviroutedata.startTime;
            String s5 = s4.substring(8, 10);
            String s6 = s4.substring(10);
            String s7 = naviroutedata.goalTime;
            String s8 = s7.substring(8, 10);
            String s9 = s7.substring(10);
            textview1.setText(s5 + ":" + s6);
            textview2.setText(s8 + ":" + s9);
            stringbuilder.append(textview1.getText());
            stringbuilder.append(resources.getString(0x7f0d026b));
            stringbuilder.append(textview2.getText());
            stringbuilder.append(resources.getString(0x7f0d025b));
            stringbuilder.append("\u3000");
        } else
        {
            textview1.setText("");
            textview2.setText("");
            ((ImageView)linearlayout.findViewById(0x7f0a022b)).setVisibility(8);
        }
        i1 = naviroutedata.totaltime;
        j1 = i1;
        k1 = 0;
        if (60 <= j1)
        {
            k1 = i1 / 60;
            i1 %= 60;
        }
        if (k1 > 0)
        {
            s = Integer.toString(k1) + resources.getString(0x7f0d02d2) + Integer.toString(i1) + resources.getString(0x7f0d02d4);
        } else
        {
            s = Integer.toString(i1) + resources.getString(0x7f0d02d4);
        }
        if (!flag)
        {
            textview3.setText((new StringBuilder()).append("(").append(s).append(")").toString());
            textview4.setText("");
        } else
        {
            textview4.setText(s);
            textview3.setText("");
        }
        stringbuilder.append(s);
        stringbuilder.append("\u3000");
        s1 = naviroutedata.totalPrice;
        textview5.setText((new StringBuilder()).append(s1).append(resources.getString(0x7f0d02da)).toString());
        stringbuilder.append(resources.getString(0x7f0d02a3));
        stringbuilder.append(textview5.getText());
        stringbuilder.append("\u3000");
        textview6.setText((new StringBuilder()).append(Integer.toString(naviroutedata.transfercount)).append(resources.getString(0x7f0d02d1)).toString());
        stringbuilder.append(resources.getString(0x7f0d0266));
        stringbuilder.append(textview6.getText());
        stringbuilder.append("\u3000");
        if (naviroutedata.cheap)
        {
            imageview.setImageBitmap(bitmap);
            textview5.setTextColor(resources.getColor(0x7f090054));
        } else
        {
            imageview.setVisibility(8);
        }
        if (naviroutedata.easy)
        {
            imageview1.setImageBitmap(bitmap1);
            textview6.setTextColor(resources.getColor(0x7f090054));
        } else
        {
            imageview1.setVisibility(8);
        }
        if (naviroutedata.fast)
        {
            imageview2.setImageBitmap(bitmap2);
            textview2.setTextColor(resources.getColor(0x7f090054));
        } else
        {
            imageview2.setVisibility(8);
        }
        afterFinalFlag = Boolean.valueOf(conditionData.afterFinal);
        INDEX = l;
        onclicklistener = new android.view.View.OnClickListener() {

            final SearchResultListView this$0;
            final int val$INDEX;

            public void onClick(View view)
            {
                ((TransitBaseActivity)SearchResultContext).touchTapRD((new StringBuilder()).append(SearchResultContext.getString(0x7f0d0412)).append("/").append(Integer.toString(INDEX)).toString());
                ConditionData conditiondata = (ConditionData)conditionData.clone();
                conditiondata.afterFinal = afterFinalFlag.booleanValue();
                conditiondata.mtf = 1 + INDEX;
                Intent intent = new Intent(SearchResultContext, jp/co/yahoo/android/apps/transit/SearchResultActivity);
                intent.putExtra(SearchResultContext.getString(0x7f0d0231), INDEX);
                intent.putExtra(SearchResultContext.getString(0x7f0d0232), contentData);
                intent.putExtra(SearchResultContext.getString(0x7f0d022c), conditiondata);
                ((TransitBaseActivity)SearchResultContext).startActivityForResult(intent, getResources().getInteger(0x7f0c0058));
            }

            
            {
                this$0 = SearchResultListView.this;
                INDEX = i;
                super();
            }
        };
        linearlayout.setOnClickListener(onclicklistener);
        arraylist1 = naviroutedata.edges;
        l1 = arraylist1.size();
        arraylist2 = new ArrayList();
        i2 = 0;
label0:
        do
        {
label1:
            {
                if (i2 < l1)
                {
                    String s3 = ((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.NaviPointData)hashmap.get(((jp.co.yahoo.android.apps.transit.api.data.NaviSearchData.Edge)arraylist1.get(i2)).startPointTarget)).stationName;
                    arraylist2.add(s3);
                    if (afterFinalFlag.booleanValue() && i2 == l1 - 2)
                    {
                        ((TextView)linearlayout.findViewById(0x7f0a0226)).setText(s3);
                    }
                    if (500 >= i2)
                    {
                        break label1;
                    }
                }
                if (!afterFinalFlag.booleanValue())
                {
                    ((RelativeLayout)linearlayout.findViewById(0x7f0a0225)).setVisibility(8);
                }
                int j2 = 1;
                stringbuilder1 = new StringBuilder();
                Iterator iterator = arraylist2.iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    String s2 = (String)iterator.next();
                    if (arraylist2.size() == j2)
                    {
                        stringbuilder1.append("\u2192");
                    } else
                    if (j2 != 1)
                    {
                        stringbuilder1.append("\u2192");
                        stringbuilder1.append(s2);
                    }
                    j2++;
                } while (true);
                break label0;
            }
            i2++;
        } while (true);
        textview7.setText(stringbuilder1.toString());
        stringbuilder.append(textview7.getText());
        linearlayout.setContentDescription(stringbuilder.toString());
        addView(linearlayout);
        if (50 >= l) goto _L4; else goto _L2
_L2:
        return;
_L4:
        l++;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void setData(NaviSearchData navisearchdata)
    {
        contentData = navisearchdata;
    }

    public void setDataFilled(boolean flag)
    {
        dataFilled = flag;
    }

    public void setValues(ConditionData conditiondata)
    {
        conditionData = conditiondata;
    }




}
