// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.content.res.Resources;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Set;
import jp.co.yahoo.android.apps.transit.api.data.TimeTableData;
import jp.co.yahoo.android.apps.transit.common.AlignImageSpan;
import jp.co.yahoo.android.apps.transit.common.SectionListBaseAdapter;
import jp.co.yahoo.android.apps.transit.common.SectionListView;
import jp.co.yahoo.android.apps.transit.common.SectionSelector;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            TimeTableBaseListView

public class TimeTableVerticalListView extends TimeTableBaseListView
{
    private class SectionListAdapter extends SectionListBaseAdapter
        implements SectionIndexer
    {

        final TimeTableVerticalListView this$0;

        public int getCountForSection(int i)
        {
            return getListCountForSection(i);
        }

        public volatile Object getItem(int i, int j)
        {
            return getItem(i, j);
        }

        public jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TimeData getItem(int i, int j)
        {
            return getListItem(i, j);
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
            jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData typedata;
            jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TimeData timedata;
            TextView textview;
            TextView textview3;
            int k;
            Object aobj[];
            jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData typedata1;
            jp.co.yahoo.android.apps.transit.api.data.TimeTableData.CarTypeData cartypedata;
            boolean flag;
            if (view == null || !(view instanceof LinearLayout))
            {
                linearlayout = getItemLayoutView();
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
            linearlayout.setTag(getListItemTag(i, j));
            timedata = getItem(i, j);
            textview = (TextView)linearlayout.findViewById(0x7f0a0278);
            textview1 = (TextView)linearlayout.findViewById(0x7f0a027c);
            textview2 = (TextView)linearlayout.findViewById(0x7f0a027e);
            textview3 = (TextView)linearlayout.findViewById(0x7f0a027d);
            k = getTextColor(timedata.kindId);
            aobj = new Object[2];
            aobj[0] = getKeyForSection(i);
            aobj[1] = Integer.valueOf(timedata.minute);
            textview.setText(String.format("%02d:%02d", aobj));
            typedata = (jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData)kindInfo.get(timedata.kindId);
            if (typedata == null || TextUtils.isEmpty(typedata.info)) goto _L2; else goto _L1
_L1:
            flag = timedata.firstStation;
            SpannableString spannablestring = null;
            if (flag)
            {
                int l;
                if (timedata.extraLine)
                {
                    spannablestring = new SpannableString((new StringBuilder()).append(typedata.info).append("    ").toString());
                    l = -3 + spannablestring.length();
                } else
                {
                    spannablestring = new SpannableString((new StringBuilder()).append(typedata.info).append("  ").toString());
                    l = -1 + spannablestring.length();
                }
                spannablestring.setSpan(new AlignImageSpan(getContext(), 0x7f0201a8), l, l + 1, 33);
            }
            if (timedata.extraLine)
            {
                if (spannablestring == null)
                {
                    spannablestring = new SpannableString((new StringBuilder()).append(typedata.info).append("  ").toString());
                }
                spannablestring.setSpan(new AlignImageSpan(getContext(), 0x7f0201a3), -1 + spannablestring.length(), spannablestring.length(), 33);
            }
            if (spannablestring != null)
            {
                textview1.setText(spannablestring);
            } else
            {
                textview1.setText(typedata.info);
            }
            if (textview1.getVisibility() != 0)
            {
                textview1.setVisibility(0);
            }
_L8:
            textview1.setTextColor(k);
            if (carInfo != null)
            {
                cartypedata = (jp.co.yahoo.android.apps.transit.api.data.TimeTableData.CarTypeData)carInfo.get(timedata.carId);
                if (cartypedata != null && !TextUtils.isEmpty(cartypedata.cartype))
                {
                    textview3.setText(cartypedata.cartype);
                    textview3.setVisibility(0);
                }
            }
            typedata1 = (jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData)destInfo.get(timedata.destId);
            if (typedata1 == null || TextUtils.isEmpty(typedata1.info)) goto _L4; else goto _L3
_L3:
            textview2.setText((new StringBuilder()).append(typedata1.info).append("\u884C\u304D").toString());
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
            textview.setText((new StringBuilder()).append(String.valueOf(getKeyForSection(i))).append("\u6642").toString());
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
            sectionSelector.setListView(listView);
        }

        private SectionListAdapter()
        {
            this$0 = TimeTableVerticalListView.this;
            super();
        }

    }


    private SectionSelector sectionSelector;

    public TimeTableVerticalListView(Context context)
    {
        super(context);
    }

    private LinearLayout getItemLayoutView()
    {
        return (LinearLayout)inflater.inflate(0x7f030081, null);
    }

    private Object getKeyForSection(int i)
    {
        return Integer.valueOf(((Integer)aryDeparture.keySet().toArray()[i]).intValue());
    }

    private int getListCountForSection(int i)
    {
        return ((ArrayList)aryDeparture.get(getKeyForSection(i))).size();
    }

    private jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TimeData getListItem(int i, int j)
    {
        return (jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TimeData)((ArrayList)aryDeparture.get(getKeyForSection(i))).get(j);
    }

    private String getListItemTag(int i, int j)
    {
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Integer.valueOf(j);
        return String.format("%02d%03d", aobj);
    }

    private void setFooterView()
    {
        View view = inflater.inflate(0x7f0300b3, null);
        ((Button)view.findViewById(0x7f0a0339)).setText((new StringBuilder()).append(timeTableData.name).append(res.getString(0x7f0d0521)).toString());
        ((TextView)view.findViewById(0x7f0a0332)).setVisibility(8);
        ((LinearLayout)view.findViewById(0x7f0a0333)).setVisibility(8);
        listView.addFooterView(view);
    }

    public int[] getSelectTime()
    {
        int ai[] = listView.getFirstPosition();
        int i;
        int j;
        if (ai[0] == -2 || ai[0] == -3)
        {
            i = ai[0];
            j = ai[1];
        } else
        if (ai[0] == -1 || ai[1] == -1)
        {
            i = -1;
            j = -1;
        } else
        {
            i = ((Integer)getKeyForSection(ai[0])).intValue();
            j = getListItem(ai[0], ai[1]).minute;
        }
        return (new int[] {
            i, j
        });
    }

    protected void initView()
    {
        setFooterView();
        SectionListAdapter sectionlistadapter = new SectionListAdapter();
        listView.setAdapter(sectionlistadapter);
        sectionSelector = (SectionSelector)findViewById(0x7f0a0331);
        sectionSelector.setVisibility(0);
        sectionSelector.setListView(listView);
    }

    public void refreshCurrentTime(int ai[], Calendar calendar)
    {
        if (isShowCurrent && calendar != null)
        {
            int i = currentSection;
            int j = currentPosition;
            setCurrentTime(calendar);
            setCurrentPosition();
            if (j != currentPosition)
            {
                if (i != -1 && j != -1)
                {
                    View view1 = listView.findViewWithTag(getListItemTag(i, j));
                    if (view1 != null && (view1 instanceof LinearLayout))
                    {
                        view1.setBackgroundColor(res.getColor(0x7f090084));
                    }
                }
                if (currentSection != -1 && currentPosition != -1)
                {
                    View view = listView.findViewWithTag(getListItemTag(currentSection, currentPosition));
                    if (view != null && (view instanceof LinearLayout))
                    {
                        view.setBackgroundColor(res.getColor(0x7f090032));
                    }
                }
            }
        }
        scrollToTime(ai, null, false);
    }

    protected void resetView()
    {
    }






}
