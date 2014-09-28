// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.res.Resources;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import jp.co.yahoo.android.apps.transit.common.SectionListBaseAdapter;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            TimeTableHorizontalListView

private class <init> extends SectionListBaseAdapter
{

    final TimeTableHorizontalListView this$0;

    public int getCountForSection(int i)
    {
        return TimeTableHorizontalListView.access$200(TimeTableHorizontalListView.this, i);
    }

    public volatile Object getItem(int i, int j)
    {
        return getItem(i, j);
    }

    public ArrayList getItem(int i, int j)
    {
        return TimeTableHorizontalListView.access$100(TimeTableHorizontalListView.this, i);
    }

    public long getItemId(int i, int j)
    {
        return 0L;
    }

    public View getItemView(int i, int j, View view, ViewGroup viewgroup)
    {
        LinearLayout linearlayout;
        String s;
        ArrayList arraylist;
        int l;
        if (view == null || !(view instanceof LinearLayout))
        {
            linearlayout = TimeTableHorizontalListView.access$300(TimeTableHorizontalListView.this);
        } else
        {
            linearlayout = (LinearLayout)view;
        }
        if (currentSection != -1 && i == currentSection)
        {
            int k1 = res.getColor(0x7f090032);
            linearlayout.setBackgroundColor(k1);
        } else
        {
            int k = res.getColor(0x7f090084);
            linearlayout.setBackgroundColor(k);
        }
        s = TimeTableHorizontalListView.access$400(TimeTableHorizontalListView.this, i, j);
        linearlayout.setTag(s);
        arraylist = getItem(i, j);
        l = 0;
        while (l < 5) 
        {
            View view1 = linearlayout.getChildAt(l);
            view1.setBackgroundColor(res.getColor(0x7f09006e));
            int i1 = l + j * 5;
            if (i1 >= arraylist.size())
            {
                view1.setVisibility(8);
                view1.setTag(null);
            } else
            {
                jp.co.yahoo.android.apps.transit.api.data.  = (jp.co.yahoo.android.apps.transit.api.data..getItem)arraylist.get(i1);
                view1.setVisibility(0);
                view1.setTag();
                if (TimeTableHorizontalListView.access$500(TimeTableHorizontalListView.this) != null && TimeTableHorizontalListView.access$500(TimeTableHorizontalListView.this).equals())
                {
                    view1.setBackgroundColor(res.getColor(0x7f09004a));
                }
                TextView textview = (TextView)view1.findViewById(0x7f0a0277);
                TextView textview1 = (TextView)view1.findViewById(0x7f0a0278);
                TextView textview2 = (TextView)view1.findViewById(0x7f0a0279);
                ImageView imageview = (ImageView)view1.findViewById(0x7f0a027a);
                View view2 = view1.findViewById(0x7f0a027b);
                int j1 = getTextColor(._fld0);
                StringBuilder stringbuilder = new StringBuilder();
                jp.co.yahoo.android.apps.transit.api.data. 1 = (jp.co.yahoo.android.apps.transit.api.data..this._cls0)kindInfo.get(._fld0);
                jp.co.yahoo.android.apps.transit.api.data. 2 = (jp.co.yahoo.android.apps.transit.api.data..this._cls0)destInfo.get(._fld0);
                if (1 != null && !TextUtils.isEmpty(1._fld0))
                {
                    stringbuilder.append((new StringBuilder()).append("[").append(1._fld0).append("]").toString());
                }
                if (2 != null && !TextUtils.isEmpty(2._fld0))
                {
                    stringbuilder.append(2._fld0);
                }
                textview.setText(stringbuilder.toString());
                textview1.setText(String.valueOf(._fld0));
                textview.setTextColor(j1);
                textview1.setTextColor(j1);
                if (._fld0)
                {
                    textview2.setVisibility(0);
                    textview2.setTextColor(j1);
                } else
                {
                    textview2.setVisibility(8);
                }
                if (._fld0)
                {
                    imageview.setVisibility(0);
                } else
                {
                    imageview.setVisibility(8);
                }
                view2.setBackgroundColor(j1);
            }
            l++;
        }
        return linearlayout;
    }

    public int getSectionCount()
    {
        return aryDeparture.size();
    }

    public View getSectionHeaderView(int i, View view, ViewGroup viewgroup)
    {
        TextView textview;
        if (view == null || !(view instanceof TextView))
        {
            textview = getSectionView();
        } else
        {
            textview = (TextView)view;
        }
        textview.setText((new StringBuilder()).append(String.valueOf(TimeTableHorizontalListView.access$600(TimeTableHorizontalListView.this, i))).append("\u6642").toString());
        return textview;
    }

    public boolean isEnabled(int i)
    {
        return false;
    }

    private ()
    {
        this$0 = TimeTableHorizontalListView.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
