// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextPaint;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import jp.co.yahoo.android.ads.AdResponse;
import jp.co.yahoo.android.apps.transit.api.data.TimeTableData;
import jp.co.yahoo.android.apps.transit.common.SectionListBaseAdapter;
import jp.co.yahoo.android.apps.transit.common.SectionListView;

public abstract class TimeTableBaseListView extends RelativeLayout
{

    protected LinkedHashMap aryDeparture;
    protected SparseArray carInfo;
    protected int currentHour;
    protected int currentMinute;
    protected int currentPosition;
    protected int currentSection;
    protected SparseArray destInfo;
    protected LayoutInflater inflater;
    private boolean isInitialized;
    private boolean isRefreshed;
    protected boolean isShowCurrent;
    protected SparseArray kindInfo;
    protected SectionListView listView;
    private int nColor;
    private int paddingLR;
    private int paddingTB;
    protected Resources res;
    protected int selectTime[] = {
        -1, -1
    };
    protected TimeTableData timeTableData;

    public TimeTableBaseListView(Context context)
    {
        super(context);
        timeTableData = null;
        isInitialized = false;
        isRefreshed = true;
        currentHour = -1;
        currentMinute = -1;
        currentSection = -1;
        currentPosition = -1;
        nColor = 0;
    }

    private void setAdLayout(AdResponse adresponse, RelativeLayout relativelayout)
    {
        if (adresponse != null && relativelayout != null && adresponse.getCode() == "200")
        {
            relativelayout.removeAllViews();
            relativelayout.setPadding(0, 2, 0, 2);
            RelativeLayout relativelayout1 = adresponse.getAdlayout();
            if (relativelayout1.getParent() != null)
            {
                ((ViewGroup)relativelayout1.getParent()).removeView(relativelayout1);
            }
            relativelayout.addView(relativelayout1);
        }
    }

    public void clearData()
    {
        timeTableData = null;
    }

    protected TextView getSectionView()
    {
        TextView textview = new TextView(getContext());
        textview.setBackgroundColor(nColor);
        textview.setPadding(paddingLR, paddingTB, paddingLR, paddingTB);
        textview.setTextAppearance(getContext(), 0x7f0e00ba);
        textview.setTypeface(null, 1);
        textview.setLayoutParams(new android.widget.AbsListView.LayoutParams(-1, -2));
        return textview;
    }

    protected int getSectionViewHeight()
    {
        android.graphics.Paint.FontMetrics fontmetrics = getSectionView().getPaint().getFontMetrics();
        return (int)(0.5F + (fontmetrics.bottom - fontmetrics.top)) + paddingTB + paddingTB;
    }

    public abstract int[] getSelectTime();

    protected int getTextColor(int i)
    {
        String s = String.valueOf(i);
        if (s.equals("2"))
        {
            return res.getColor(0x7f090014);
        }
        if (s.equals("3"))
        {
            return res.getColor(0x7f090041);
        }
        if (s.equals("4"))
        {
            return res.getColor(0x7f090051);
        }
        if (s.equals("5"))
        {
            return res.getColor(0x7f09004d);
        } else
        {
            return res.getColor(0x7f090013);
        }
    }

    protected int[] getTimePosition(int i, int j)
    {
        int ai[] = {
            -1, -1
        };
        if (i != -1 && j != -1) goto _L2; else goto _L1
_L1:
        return ai;
_L2:
        int k;
        int l;
        Object aobj[];
        int i1;
        k = -1;
        l = -1;
        aobj = aryDeparture.keySet().toArray();
        i1 = 0;
_L14:
        if (i1 >= aobj.length) goto _L4; else goto _L3
_L3:
        int j1 = ((Integer)aobj[i1]).intValue();
        if (j1 != i) goto _L6; else goto _L5
_L5:
        ArrayList arraylist1;
        int l1;
        arraylist1 = (ArrayList)aryDeparture.get(Integer.valueOf(j1));
        l1 = 0;
_L13:
        if (l1 >= arraylist1.size()) goto _L8; else goto _L7
_L7:
        if (((jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TimeData)arraylist1.get(l1)).minute < j) goto _L10; else goto _L9
_L9:
        l = l1;
_L8:
        if (l == -1) goto _L12; else goto _L11
_L11:
        k = i1;
_L4:
        if (k != -1 && l != -1)
        {
            ai[0] = k;
            ai[1] = l;
            return ai;
        }
          goto _L1
_L10:
        l1++;
          goto _L13
_L12:
        j = 0;
_L16:
        i1++;
          goto _L14
_L6:
        if (j1 <= i) goto _L16; else goto _L15
_L15:
        ArrayList arraylist;
        int k1;
        arraylist = (ArrayList)aryDeparture.get(Integer.valueOf(j1));
        k1 = 0;
_L18:
        if (k1 < arraylist.size())
        {
            if (((jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TimeData)arraylist.get(k1)).minute < 0)
            {
                break MISSING_BLOCK_LABEL_244;
            }
            l = k1;
        }
        j = 0;
        if (l == -1) goto _L16; else goto _L17
_L17:
        k = i1;
          goto _L4
        k1++;
          goto _L18
    }

    public TimeTableData getTimeTableData()
    {
        return timeTableData;
    }

    protected abstract void initView();

    public boolean isInitialized()
    {
        return isInitialized;
    }

    public boolean isRefreshed()
    {
        return isRefreshed;
    }

    public abstract void refreshCurrentTime(int ai[], Calendar calendar);

    public void refreshList(LinkedHashMap linkedhashmap, int ai[], Calendar calendar)
    {
        aryDeparture = linkedhashmap;
        if (isShowCurrent && calendar != null)
        {
            setCurrentTime(calendar);
            setCurrentPosition();
        }
        ((BaseAdapter)((HeaderViewListAdapter)listView.getAdapter()).getWrappedAdapter()).notifyDataSetChanged();
        scrollToTime(ai, calendar, true);
        setRefreshed(true);
    }

    public void resetData(TimeTableData timetabledata, boolean flag)
    {
        timeTableData = timetabledata;
        isShowCurrent = flag;
        kindInfo = timetabledata.getMappedTypeInfo(timetabledata.kindInfo);
        destInfo = timetabledata.getMappedTypeInfo(timetabledata.destInfo);
        carInfo = timetabledata.getMappedCarTypeInfo(timetabledata.carInfo);
        resetView();
    }

    protected abstract void resetView();

    protected void scrollToBottom()
    {
        int i = listView.getAdapter().getCount();
        listView.setSelection(i - 1);
    }

    protected void scrollToPosition(int i, int j)
    {
        scrollToPosition(i, j, false);
    }

    protected void scrollToPosition(int i, int j, boolean flag)
    {
        int k;
        int l;
        if (i == -1 || j == -1)
        {
            k = -2 + listView.getCount();
            l = getSectionViewHeight();
        } else
        {
            k = 1 + (j + ((SectionListBaseAdapter)((HeaderViewListAdapter)listView.getAdapter()).getWrappedAdapter()).getPositionOfSection(i)) + listView.getHeaderViewsCount();
            if (j == 0)
            {
                k--;
                l = 0;
            } else
            {
                l = getSectionViewHeight();
            }
        }
        if (flag)
        {
            listView.smoothScrollToPosFromTop(k, l);
            return;
        } else
        {
            listView.selectPosFromTop(k, l);
            return;
        }
    }

    protected void scrollToTime(int ai[], Calendar calendar, boolean flag)
    {
        if (ai[0] == -2)
        {
            scrollToTop();
        } else
        {
            if (ai[0] == -3)
            {
                scrollToBottom();
                return;
            }
            if (ai[0] != -1 && ai[1] != -1)
            {
                int ai1[] = getTimePosition(ai[0], ai[1]);
                if (ai1[0] != -1 && ai1[1] != -1)
                {
                    scrollToPosition(ai1[0], ai1[1]);
                }
                selectTime[0] = ai[0];
                selectTime[1] = ai[1];
                return;
            }
            if (flag)
            {
                if (isShowCurrent && calendar != null)
                {
                    scrollToPosition(currentSection, currentPosition);
                    return;
                } else
                {
                    scrollToTop();
                    return;
                }
            }
        }
    }

    protected void scrollToTop()
    {
        listView.setSelection(0);
    }

    public void setAdView(Map map)
    {
        if (map != null)
        {
            if (map.containsKey("pv"))
            {
                setAdLayout((AdResponse)map.get("pv"), (RelativeLayout)findViewById(0x7f0a006f));
            }
            if (map.containsKey("bottom"))
            {
                setAdLayout((AdResponse)map.get("bottom"), (RelativeLayout)findViewById(0x7f0a0070));
            }
            if (map.containsKey("top"))
            {
                setAdLayout((AdResponse)map.get("top"), (RelativeLayout)findViewById(0x7f0a0060));
                return;
            }
        }
    }

    protected void setCurrentPosition()
    {
        int ai[] = getTimePosition(currentHour, currentMinute);
        currentSection = ai[0];
        currentPosition = ai[1];
    }

    protected void setCurrentTime(Calendar calendar)
    {
        int i;
        int j;
        int k;
        int l;
        i = 1 + calendar.get(2);
        j = calendar.get(5);
        k = calendar.get(11);
        l = calendar.get(12);
        int i1 = calendar.get(13);
        if (l == 59 && i1 > 0)
        {
            k++;
            l = 0;
        }
        if (i != 1 || j != 1) goto _L2; else goto _L1
_L1:
        if (k == 0)
        {
            k += 24;
        }
_L4:
        currentHour = k;
        currentMinute = l;
        return;
_L2:
        if (k >= 0 && k <= 3)
        {
            k += 24;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void setInitialized(boolean flag)
    {
        isInitialized = flag;
    }

    public void setLineColor(int i)
    {
        nColor = i;
    }

    public void setRefreshed(boolean flag)
    {
        isRefreshed = flag;
    }

    public void showView(TimeTableData timetabledata, boolean flag, LinkedHashMap linkedhashmap, int ai[], Calendar calendar)
    {
        inflater = (LayoutInflater)getContext().getSystemService("layout_inflater");
        inflater.inflate(0x7f0300b2, this, true);
        res = getContext().getResources();
        paddingLR = res.getDimensionPixelSize(0x7f0b0034);
        paddingTB = res.getDimensionPixelSize(0x7f0b0034);
        listView = (SectionListView)findViewById(0x7f0a02e6);
        listView.setDivider(res.getDrawable(0x7f020165));
        timeTableData = timetabledata;
        isShowCurrent = flag;
        aryDeparture = linkedhashmap;
        kindInfo = timetabledata.getMappedTypeInfo(timetabledata.kindInfo);
        destInfo = timetabledata.getMappedTypeInfo(timetabledata.destInfo);
        carInfo = timetabledata.getMappedCarTypeInfo(timetabledata.carInfo);
        if (flag && calendar != null)
        {
            setCurrentTime(calendar);
            setCurrentPosition();
        }
        initView();
        scrollToTime(ai, calendar, true);
        setInitialized(true);
    }
}
