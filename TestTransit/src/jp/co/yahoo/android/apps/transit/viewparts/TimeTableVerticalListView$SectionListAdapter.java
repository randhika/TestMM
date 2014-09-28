// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.res.Resources;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;
import java.util.LinkedHashMap;
import java.util.Set;
import jp.co.yahoo.android.apps.transit.common.AlignImageSpan;
import jp.co.yahoo.android.apps.transit.common.SectionListBaseAdapter;
import jp.co.yahoo.android.apps.transit.common.SectionSelector;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            TimeTableVerticalListView

private class <init> extends SectionListBaseAdapter
    implements SectionIndexer
{

    final TimeTableVerticalListView this$0;

    public int getCountForSection(int i)
    {
        return TimeTableVerticalListView.access$200(TimeTableVerticalListView.this, i);
    }

    public volatile Object getItem(int i, int j)
    {
        return getItem(i, j);
    }

    public jp.co.yahoo.android.apps.transit.api.data. getItem(int i, int j)
    {
        return TimeTableVerticalListView.access$100(TimeTableVerticalListView.this, i, j);
    }

    public long getItemId(int i, int j)
    {
        return 0L;
    }

    public View getItemView(int i, int j, View view, ViewGroup viewgroup)
    {
        LinearLayout linearlayout;
        TextView textview1;
        TextView textview2;
        jp.co.yahoo.android.apps.transit.api.data. 1;
        jp.co.yahoo.android.apps.transit.api.data. ;
        TextView textview;
        TextView textview3;
        int k;
        Object aobj[];
        jp.co.yahoo.android.apps.transit.api.data. 2;
        jp.co.yahoo.android.apps.transit.api.data. 3;
        boolean flag;
        if (view == null || !(view instanceof LinearLayout))
        {
            linearlayout = TimeTableVerticalListView.access$300(TimeTableVerticalListView.this);
        } else
        {
            linearlayout = (LinearLayout)view;
        }
        if (currentSection != -1 && currentPosition != -1 && i == currentSection && j == currentPosition)
        {
            linearlayout.setBackgroundColor(res.getColor(0x7f090032));
        } else
        {
            linearlayout.setBackgroundColor(res.getColor(0x7f090084));
        }
        linearlayout.setTag(TimeTableVerticalListView.access$400(TimeTableVerticalListView.this, i, j));
         = getItem(i, j);
        textview = (TextView)linearlayout.findViewById(0x7f0a0278);
        textview1 = (TextView)linearlayout.findViewById(0x7f0a027c);
        textview2 = (TextView)linearlayout.findViewById(0x7f0a027e);
        textview3 = (TextView)linearlayout.findViewById(0x7f0a027d);
        k = getTextColor(.getItem);
        aobj = new Object[2];
        aobj[0] = TimeTableVerticalListView.access$500(TimeTableVerticalListView.this, i);
        aobj[1] = Integer.valueOf(._fld0);
        textview.setText(String.format("%02d:%02d", aobj));
        1 = (jp.co.yahoo.android.apps.transit.api.data..this._cls0)kindInfo.get(._fld0);
        if (1 == null || TextUtils.isEmpty(1._fld0)) goto _L2; else goto _L1
_L1:
        flag = ._fld0;
        SpannableString spannablestring = null;
        if (flag)
        {
            int l;
            if (._fld0)
            {
                spannablestring = new SpannableString((new StringBuilder()).append(1._fld0).append("    ").toString());
                l = -3 + spannablestring.length();
            } else
            {
                spannablestring = new SpannableString((new StringBuilder()).append(1._fld0).append("  ").toString());
                l = -1 + spannablestring.length();
            }
            spannablestring.setSpan(new AlignImageSpan(getContext(), 0x7f0201a8), l, l + 1, 33);
        }
        if (._fld0)
        {
            if (spannablestring == null)
            {
                spannablestring = new SpannableString((new StringBuilder()).append(1._fld0).append("  ").toString());
            }
            spannablestring.setSpan(new AlignImageSpan(getContext(), 0x7f0201a3), -1 + spannablestring.length(), spannablestring.length(), 33);
        }
        if (spannablestring != null)
        {
            textview1.setText(spannablestring);
        } else
        {
            textview1.setText(1._fld0);
        }
        if (textview1.getVisibility() != 0)
        {
            textview1.setVisibility(0);
        }
_L8:
        textview1.setTextColor(k);
        if (carInfo != null)
        {
            3 = (jp.co.yahoo.android.apps.transit.api.data..this._cls0)carInfo.get(._fld0);
            if (3 != null && !TextUtils.isEmpty(3._fld0))
            {
                textview3.setText(3._fld0);
                textview3.setVisibility(0);
            }
        }
        2 = (jp.co.yahoo.android.apps.transit.api.data..this._cls0)destInfo.get(._fld0);
        if (2 == null || TextUtils.isEmpty(2._fld0)) goto _L4; else goto _L3
_L3:
        textview2.setText((new StringBuilder()).append(2._fld0).append("\u884C\u304D").toString());
        if (textview2.getVisibility() != 0)
        {
            textview2.setVisibility(0);
        }
_L6:
        return linearlayout;
_L2:
        if (textview1.getVisibility() != 8)
        {
            textview1.setVisibility(8);
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (textview2.getVisibility() == 8) goto _L6; else goto _L5
_L5:
        textview2.setVisibility(8);
        return linearlayout;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public int getPositionForSection(int i)
    {
        return getPositionOfSection(i);
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
        textview.setText((new StringBuilder()).append(String.valueOf(TimeTableVerticalListView.access$500(TimeTableVerticalListView.this, i))).append("\u6642").toString());
        return textview;
    }

    public Object[] getSections()
    {
        Object aobj[] = aryDeparture.keySet().toArray();
        String as[] = new String[aobj.length];
        for (int i = 0; i < aobj.length; i++)
        {
            as[i] = String.valueOf(aobj[i]);
        }

        return as;
    }

    public boolean isEnabled(int i)
    {
        return false;
    }

    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
        TimeTableVerticalListView.access$600(TimeTableVerticalListView.this).setListView(listView);
    }

    private ()
    {
        this$0 = TimeTableVerticalListView.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
