// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import jp.co.yahoo.android.apps.transit.api.data.TimeTableData;
import jp.co.yahoo.android.apps.transit.common.SectionListBaseAdapter;
import jp.co.yahoo.android.apps.transit.common.SectionListView;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            TimeTableBaseListView

public class TimeTableHorizontalListView extends TimeTableBaseListView
{
    private class SectionListAdapter extends SectionListBaseAdapter
    {

        final TimeTableHorizontalListView this$0;

        public int getCountForSection(int i)
        {
            return getListCountForSection(i);
        }

        public volatile Object getItem(int i, int j)
        {
            return getItem(i, j);
        }

        public ArrayList getItem(int i, int j)
        {
            return getListItem(i);
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
                linearlayout = getItemLayoutView();
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
            s = getListItemTag(i, j);
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
                    jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TimeData timedata = (jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TimeData)arraylist.get(i1);
                    view1.setVisibility(0);
                    view1.setTag(timedata);
                    if (selectItemTag != null && selectItemTag.equals(timedata))
                    {
                        view1.setBackgroundColor(res.getColor(0x7f09004a));
                    }
                    TextView textview = (TextView)view1.findViewById(0x7f0a0277);
                    TextView textview1 = (TextView)view1.findViewById(0x7f0a0278);
                    TextView textview2 = (TextView)view1.findViewById(0x7f0a0279);
                    ImageView imageview = (ImageView)view1.findViewById(0x7f0a027a);
                    View view2 = view1.findViewById(0x7f0a027b);
                    int j1 = getTextColor(timedata.kindId);
                    StringBuilder stringbuilder = new StringBuilder();
                    jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData typedata = (jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData)kindInfo.get(timedata.kindId);
                    jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData typedata1 = (jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData)destInfo.get(timedata.destId);
                    if (typedata != null && !TextUtils.isEmpty(typedata.mark))
                    {
                        stringbuilder.append((new StringBuilder()).append("[").append(typedata.mark).append("]").toString());
                    }
                    if (typedata1 != null && !TextUtils.isEmpty(typedata1.mark))
                    {
                        stringbuilder.append(typedata1.mark);
                    }
                    textview.setText(stringbuilder.toString());
                    textview1.setText(String.valueOf(timedata.minute));
                    textview.setTextColor(j1);
                    textview1.setTextColor(j1);
                    if (timedata.firstStation)
                    {
                        textview2.setVisibility(0);
                        textview2.setTextColor(j1);
                    } else
                    {
                        textview2.setVisibility(8);
                    }
                    if (timedata.extraLine)
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
            textview.setText((new StringBuilder()).append(String.valueOf(getKeyForSection(i))).append("\u6642").toString());
            return textview;
        }

        public boolean isEnabled(int i)
        {
            return false;
        }

        private SectionListAdapter()
        {
            this$0 = TimeTableHorizontalListView.this;
            super();
        }

    }


    private final int COLUMN_NUM = 5;
    private TextView explainDest;
    private TextView explainKind;
    private LinearLayout layoutPopup;
    private int paddingLR;
    private int popupWidth;
    private int popupXOffset;
    private int popupYOffset;
    private jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TimeData selectItemTag;

    public TimeTableHorizontalListView(Context context)
    {
        super(context);
        selectItemTag = null;
        layoutPopup = null;
        popupWidth = -1;
        popupXOffset = -1;
        popupYOffset = -1;
    }

    private LinearLayout getItemLayoutView()
    {
        LinearLayout linearlayout = new LinearLayout(getContext());
        linearlayout.setOrientation(0);
        linearlayout.setWeightSum(5F);
        linearlayout.setPadding(paddingLR, 0, paddingLR, 0);
        linearlayout.setEnabled(false);
        for (int i = 0; i < 5; i++)
        {
            View view = inflater.inflate(0x7f030080, null);
            view.setLayoutParams(new android.widget.LinearLayout.LayoutParams(0, -2, 1.0F));
            view.setOnClickListener(new android.view.View.OnClickListener() {

                final TimeTableHorizontalListView this$0;

                public void onClick(View view1)
                {
                    showItemPopup(view1);
                }

            
            {
                this$0 = TimeTableHorizontalListView.this;
                super();
            }
            });
            linearlayout.addView(view);
        }

        return linearlayout;
    }

    private Object getKeyForSection(int i)
    {
        return Integer.valueOf(((Integer)aryDeparture.keySet().toArray()[i]).intValue());
    }

    private int getListCountForSection(int i)
    {
        return (-1 + (5 + ((ArrayList)aryDeparture.get(getKeyForSection(i))).size())) / 5;
    }

    private ArrayList getListItem(int i)
    {
        return (ArrayList)aryDeparture.get(getKeyForSection(i));
    }

    private String getListItemTag(int i, int j)
    {
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Integer.valueOf(j);
        return String.format("%02d%03d", aobj);
    }

    private void hideItemPopup()
    {
        View view = listView.findViewWithTag(selectItemTag);
        if (view != null)
        {
            view.setBackgroundColor(res.getColor(0x7f09006e));
        }
        selectItemTag = null;
        listView.hidePopupView();
    }

    private void setExplainKindDest()
    {
        ArrayList arraylist = timeTableData.getSortedTypeInfo(timeTableData.kindInfo);
        ArrayList arraylist1 = timeTableData.getSortedTypeInfo(timeTableData.destInfo);
        String s = res.getString(0x7f0d052a);
        if (arraylist.size() > 0)
        {
            StringBuilder stringbuilder = new StringBuilder();
            Iterator iterator = arraylist.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData typedata1 = (jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData)iterator.next();
                String s3 = typedata1.info;
                String s4 = typedata1.mark;
                if (TransitUtil.isEmpty(s4))
                {
                    s4 = s;
                }
                stringbuilder.append((new StringBuilder()).append(s4).append("\uFF1A").append(s3).toString());
                if (arraylist.indexOf(typedata1) < -1 + arraylist.size())
                {
                    stringbuilder.append("\n");
                }
            } while (true);
            explainKind.setText(stringbuilder);
        } else
        {
            ((View)explainKind.getParent()).setVisibility(8);
        }
        if (arraylist1.size() > 0)
        {
            StringBuilder stringbuilder1 = new StringBuilder();
            Iterator iterator1 = arraylist1.iterator();
            do
            {
                if (!iterator1.hasNext())
                {
                    break;
                }
                jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData typedata = (jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData)iterator1.next();
                String s1 = typedata.info;
                String s2 = typedata.mark;
                if (TransitUtil.isEmpty(s2))
                {
                    s2 = s;
                }
                stringbuilder1.append((new StringBuilder()).append(s2).append("\uFF1A").append(s1).toString());
                if (arraylist1.indexOf(typedata) < -1 + arraylist1.size())
                {
                    stringbuilder1.append("\n");
                }
            } while (true);
            explainDest.setText(stringbuilder1);
            return;
        } else
        {
            ((View)explainDest.getParent()).setVisibility(8);
            return;
        }
    }

    private void setFooterView()
    {
        View view = inflater.inflate(0x7f0300b3, null);
        ((Button)view.findViewById(0x7f0a0339)).setText((new StringBuilder()).append(timeTableData.name).append(res.getString(0x7f0d0521)).toString());
        ((LinearLayout)view.findViewById(0x7f0a0333)).setVisibility(0);
        LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f0a0335);
        explainKind = (TextView)linearlayout.findViewById(0x7f0a0336);
        explainDest = (TextView)linearlayout.findViewById(0x7f0a0337);
        setExplainKindDest();
        listView.addFooterView(view);
    }

    private void setHeaderView()
    {
        View view = inflater.inflate(0x7f0300b4, null);
        listView.addHeaderView(view);
    }

    private void showItemPopup(View view)
    {
        int k;
        int l;
        jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TimeData timedata = (jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TimeData)view.getTag();
        String s = (String)((View)view.getParent()).getTag();
        int i = Integer.parseInt(s.substring(0, 2));
        int j = Integer.parseInt(s.substring(2, 5));
        if (selectItemTag != null && selectItemTag.equals(timedata))
        {
            scrollToPosition(i, j, true);
            return;
        }
        if (selectItemTag != null)
        {
            View view1 = listView.findViewWithTag(selectItemTag);
            if (view1 != null)
            {
                view1.setBackgroundColor(res.getColor(0x7f09006e));
            }
        }
        selectItemTag = timedata;
        view.setBackgroundColor(res.getColor(0x7f09004a));
        if (popupWidth == -1)
        {
            popupWidth = (int)TypedValue.applyDimension(1, 195F, res.getDisplayMetrics());
            popupXOffset = (int)TypedValue.applyDimension(1, 10F, res.getDisplayMetrics());
            popupYOffset = (int)TypedValue.applyDimension(1, 3F, res.getDisplayMetrics());
        }
        if (layoutPopup == null)
        {
            layoutPopup = (LinearLayout)inflater.inflate(0x7f0300b5, null);
            layoutPopup.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(popupWidth, -2));
            layoutPopup.setOnTouchListener(new android.view.View.OnTouchListener() {

                final TimeTableHorizontalListView this$0;

                public boolean onTouch(View view2, MotionEvent motionevent)
                {
                    return true;
                }

            
            {
                this$0 = TimeTableHorizontalListView.this;
                super();
            }
            });
            ((ImageView)layoutPopup.findViewById(0x7f0a018e)).setOnClickListener(new android.view.View.OnClickListener() {

                final TimeTableHorizontalListView this$0;

                public void onClick(View view2)
                {
                    hideItemPopup();
                }

            
            {
                this$0 = TimeTableHorizontalListView.this;
                super();
            }
            });
        }
        TextView textview = (TextView)layoutPopup.findViewById(0x7f0a0336);
        TextView textview1 = (TextView)layoutPopup.findViewById(0x7f0a033a);
        TextView textview2 = (TextView)layoutPopup.findViewById(0x7f0a0337);
        TextView textview3 = (TextView)layoutPopup.findViewById(0x7f0a033b);
        TextView textview4 = (TextView)layoutPopup.findViewById(0x7f0a033c);
        LinearLayout linearlayout = (LinearLayout)layoutPopup.findViewById(0x7f0a0338);
        LinearLayout linearlayout1 = (LinearLayout)layoutPopup.findViewById(0x7f0a033d);
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData typedata = (jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData)kindInfo.get(timedata.kindId);
        jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData typedata1 = (jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData)destInfo.get(timedata.destId);
        if (typedata != null)
        {
            int i1;
            if (TextUtils.isEmpty(typedata.mark))
            {
                s1 = (new StringBuilder()).append(res.getString(0x7f0d052a)).append("\uFF1A").toString();
            } else
            {
                s1 = (new StringBuilder()).append(typedata.mark).append("\uFF1A").toString();
            }
            if (!TextUtils.isEmpty(typedata.info))
            {
                s2 = typedata.info;
            }
            if (carInfo != null)
            {
                jp.co.yahoo.android.apps.transit.api.data.TimeTableData.CarTypeData cartypedata = (jp.co.yahoo.android.apps.transit.api.data.TimeTableData.CarTypeData)carInfo.get(timedata.carId);
                if (!TextUtils.isEmpty(cartypedata.cartype))
                {
                    s2 = (new StringBuilder()).append(s2).append("(").append(cartypedata.cartype).append(")").toString();
                }
            }
        }
        if (typedata1 != null)
        {
            if (TextUtils.isEmpty(typedata1.mark))
            {
                s3 = (new StringBuilder()).append(res.getString(0x7f0d052a)).append("\uFF1A").toString();
            } else
            {
                s3 = (new StringBuilder()).append(typedata1.mark).append("\uFF1A").toString();
            }
            if (!TextUtils.isEmpty(typedata1.info))
            {
                s4 = typedata1.info;
            }
        }
        textview.setText(s1);
        textview1.setText(s2);
        textview2.setText(s3);
        textview3.setText(s4);
        if (!timedata.firstStation && !timedata.extraLine)
        {
            textview4.setVisibility(8);
            linearlayout.setVisibility(8);
            linearlayout1.setVisibility(8);
        } else
        {
            textview4.setVisibility(0);
            if (timedata.firstStation)
            {
                linearlayout.setVisibility(0);
            } else
            {
                linearlayout.setVisibility(8);
            }
            if (timedata.extraLine)
            {
                linearlayout1.setVisibility(0);
            } else
            {
                linearlayout1.setVisibility(8);
            }
        }
        k = ((View)view.getParent()).getWidth();
        l = view.getLeft() - (popupWidth - view.getWidth()) / 2;
        if (l >= popupXOffset) goto _L2; else goto _L1
_L1:
        l = popupXOffset;
_L4:
        i1 = view.getHeight() - popupYOffset;
        listView.showPopupView(layoutPopup, timedata, i, j, l, i1);
        scrollToPosition(i, j, true);
        return;
_L2:
        if (l + popupWidth > k - popupXOffset)
        {
            l = k - popupXOffset - popupWidth;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public int[] getSelectTime()
    {
        int ai[] = listView.getFirstPosition();
        if (ai[0] != -2 && ai[0] != -3) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        i = ai[0];
        j = ai[1];
_L4:
        return (new int[] {
            i, j
        });
_L2:
        if (ai[0] != -1 && ai[1] != -1)
        {
            break; /* Loop/switch isn't completed */
        }
        i = -1;
        j = -1;
        if (true) goto _L4; else goto _L3
_L3:
        i = ((Integer)getKeyForSection(ai[0])).intValue();
        int k = 5 * ai[1];
        ArrayList arraylist = getListItem(ai[0]);
        if (arraylist.size() > k)
        {
            j = ((jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TimeData)arraylist.get(k)).minute;
        } else
        {
            j = -1;
        }
        if (selectTime[0] == i)
        {
            int l = 0;
            while (l < 5) 
            {
                int i1 = l + 5 * ai[1];
                if (i1 < arraylist.size())
                {
                    jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TimeData timedata = (jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TimeData)arraylist.get(i1);
                    if (selectTime[1] == timedata.minute)
                    {
                        j = timedata.minute;
                    }
                }
                l++;
            }
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    protected int[] getTimePosition(int i, int j)
    {
        int ai[] = super.getTimePosition(i, j);
        if (ai[1] != -1)
        {
            ai[1] = ai[1] / 5;
        }
        return ai;
    }

    protected void initView()
    {
        setHeaderView();
        setFooterView();
        paddingLR = res.getDimensionPixelSize(0x7f0b002e);
        SectionListAdapter sectionlistadapter = new SectionListAdapter();
        listView.setAdapter(sectionlistadapter);
    }

    public void refreshCurrentTime(int ai[], Calendar calendar)
    {
        if (isShowCurrent && calendar != null)
        {
            int i = currentSection;
            setCurrentTime(calendar);
            setCurrentPosition();
            if (i != currentSection)
            {
                if (i != -1)
                {
                    ArrayList arraylist1 = getListItem(i);
                    for (int k = 0; k < arraylist1.size(); k++)
                    {
                        View view1 = listView.findViewWithTag(getListItemTag(i, k));
                        if (view1 != null && (view1 instanceof LinearLayout))
                        {
                            view1.setBackgroundColor(res.getColor(0x7f090084));
                        }
                    }

                }
                if (currentSection != -1)
                {
                    ArrayList arraylist = getListItem(currentSection);
                    for (int j = 0; j < arraylist.size(); j++)
                    {
                        View view = listView.findViewWithTag(getListItemTag(currentSection, j));
                        if (view != null && (view instanceof LinearLayout))
                        {
                            view.setBackgroundColor(res.getColor(0x7f090032));
                        }
                    }

                }
            }
        }
        scrollToTime(ai, null, false);
    }

    public void refreshList(LinkedHashMap linkedhashmap, int ai[], Calendar calendar)
    {
        if (selectItemTag != null)
        {
            hideItemPopup();
        }
        super.refreshList(linkedhashmap, ai, calendar);
    }

    protected void resetView()
    {
        setExplainKindDest();
    }

    public void scrollToExplain()
    {
        scrollToBottom();
    }

    public void scrollToTimetable()
    {
        scrollToTop();
    }








}
