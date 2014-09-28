// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import jp.co.yahoo.android.ads.AdResponse;
import jp.co.yahoo.android.ads.AdViewListener;
import jp.co.yahoo.android.apps.transit.api.ApiBase;
import jp.co.yahoo.android.apps.transit.api.TimeTableSearch;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.api.data.RailDirectionData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.api.data.TimeTableData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.db.ResultDB;
import jp.co.yahoo.android.apps.transit.viewparts.CheckListView;
import jp.co.yahoo.android.apps.transit.viewparts.TimeTableBaseListView;
import jp.co.yahoo.android.apps.transit.viewparts.TimeTableHorizontalListView;
import jp.co.yahoo.android.apps.transit.viewparts.TimeTableVerticalListView;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, MyPageActivity, RailDirection, StationInfo

public class TimeTableActivity extends TransitBaseActivity
{
    class AdviewListener
        implements AdViewListener
    {

        final TimeTableActivity this$0;

        private void setAdLayout(AdResponse adresponse, RelativeLayout relativelayout)
        {
            if (adresponse != null && relativelayout != null && adresponse.getCode() == "200")
            {
                relativelayout.removeAllViews();
                relativelayout.setPadding(0, 2, 0, 2);
                relativelayout.addView(adresponse.getAdlayout());
            }
        }

        public void onAdViewActivityEnd(Map map)
        {
            adResponses = map;
            if (map.containsKey("pv"))
            {
                AdResponse adresponse2 = (AdResponse)map.get("pv");
                if (lvCurrent != null)
                {
                    setAdLayout(adresponse2, (RelativeLayout)lvCurrent.findViewById(0x7f0a006f));
                }
            }
            if (map.containsKey("bottom"))
            {
                AdResponse adresponse1 = (AdResponse)map.get("bottom");
                if (lvCurrent != null)
                {
                    setAdLayout(adresponse1, (RelativeLayout)lvCurrent.findViewById(0x7f0a0070));
                }
            }
            if (map.containsKey("top"))
            {
                AdResponse adresponse = (AdResponse)map.get("top");
                if (lvCurrent != null)
                {
                    setAdLayout(adresponse, (RelativeLayout)lvCurrent.findViewById(0x7f0a0060));
                }
            }
        }

        public void onAdViewActivityStart()
        {
        }

        AdviewListener()
        {
            this$0 = TimeTableActivity.this;
            super();
        }
    }

    private class DisplayModeAdapter extends ArrayAdapter
    {

        private LayoutInflater inflater;
        final TimeTableActivity this$0;

        public View getDropDownView(int i, View view, ViewGroup viewgroup)
        {
            LinearLayout linearlayout;
            ImageView imageview;
            TextView textview;
            if (view == null)
            {
                linearlayout = (LinearLayout)inflater.inflate(0x7f030072, null);
            } else
            {
                linearlayout = (LinearLayout)view;
            }
            imageview = (ImageView)linearlayout.findViewById(0x7f0a0221);
            textview = (TextView)linearlayout.findViewById(0x7f0a0222);
            textview.setTextAppearance(TimeTableActivity.this, 0x7f0e00b8);
            if (i == 0)
            {
                imageview.setImageResource(0x7f020116);
                textview.setText(0x7f0d0514);
                return linearlayout;
            } else
            {
                imageview.setImageResource(0x7f02011f);
                textview.setText(0x7f0d0515);
                return linearlayout;
            }
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            ImageView imageview;
            if (view == null)
            {
                imageview = new ImageView(getContext());
            } else
            {
                imageview = (ImageView)view;
            }
            if (i == 0)
            {
                imageview.setImageResource(0x7f020116);
                return imageview;
            } else
            {
                imageview.setImageResource(0x7f02011f);
                return imageview;
            }
        }

        public DisplayModeAdapter(Context context, int i, Integer ainteger[])
        {
            this$0 = TimeTableActivity.this;
            super(context, i, ainteger);
            inflater = (LayoutInflater)context.getSystemService("layout_inflater");
        }
    }


    public static final int KIND_DATE = 10;
    private static final String TAB_ID_DATE_H = "7";
    private static final String TAB_ID_DATE_V = "3";
    private static final String TAB_ID_HOLIDAY_H = "6";
    private static final String TAB_ID_HOLIDAY_V = "2";
    private static final String TAB_ID_ORDINARY_H = "4";
    private static final String TAB_ID_ORDINARY_V = "0";
    private static final String TAB_ID_SATURDAY_H = "5";
    private static final String TAB_ID_SATURDAY_V = "1";
    private static final int TAB_INDEX_DATE_H = 7;
    private static final int TAB_INDEX_DATE_V = 3;
    private static final int TAB_INDEX_HOLIDAY_H = 6;
    private static final int TAB_INDEX_HOLIDAY_V = 2;
    private static final int TAB_INDEX_ORDINARY_H = 4;
    private static final int TAB_INDEX_ORDINARY_V = 0;
    private static final int TAB_INDEX_SATURDAY_H = 5;
    private static final int TAB_INDEX_SATURDAY_V = 1;
    private final String SEARCH_DATE_FORMAT = "yyyyMMdd";
    private Map adResponses;
    private int beforeKind;
    private LinkedHashMap departureDate;
    private LinkedHashMap departureHoliday;
    private LinkedHashMap departureOrdinary;
    private LinkedHashMap departureSaturday;
    private RailDirectionData direction;
    private int displayKind;
    private int displayMode;
    private ArrayList filterDest;
    private ArrayList filterKind;
    private ImageView imgClose;
    private ImageView imgTabLine;
    private View indDateH;
    private View indDateV;
    private View indHolidayH;
    private View indHolidayV;
    private View indOrdinaryH;
    private View indOrdinaryV;
    private View indSaturdayH;
    private View indSaturdayV;
    private boolean isChangedMemo;
    private boolean isEnableMemo;
    private boolean isError;
    private boolean isFirst;
    private boolean isMemoShowCurrent;
    private LinearLayout layoutDate;
    private LinearLayout layoutTab;
    private AdviewListener listener;
    private TimeTableBaseListView lvCurrent;
    private TimeTableBaseListView lvDateH;
    private TimeTableBaseListView lvDateV;
    private TimeTableBaseListView lvHolidayH;
    private TimeTableBaseListView lvHolidayV;
    private TimeTableBaseListView lvOrdinaryH;
    private TimeTableBaseListView lvOrdinaryV;
    private TimeTableBaseListView lvSaturdayH;
    private TimeTableBaseListView lvSaturdayV;
    private TimeTableSearch objSearch;
    private Bundle objTimetable;
    private String sMemoId;
    private String sReferDate;
    private int selectTime[] = {
        -1, -1
    };
    private ResultDB sql;
    private StationData station;
    private TimeTableData tableDataDate;
    private TimeTableData tableDataHoliday;
    private TimeTableData tableDataOrdinary;
    private TimeTableData tableDataSaturday;
    private TabHost tabs;
    private TimeTableData timeTableData;
    private TextView txtDate;
    private TextView txtFilter;

    public TimeTableActivity()
    {
        sql = null;
        isMemoShowCurrent = false;
        tableDataOrdinary = null;
        tableDataSaturday = null;
        tableDataHoliday = null;
        tableDataDate = null;
        departureOrdinary = null;
        departureSaturday = null;
        departureHoliday = null;
        departureDate = null;
        displayKind = -1;
        beforeKind = -1;
        displayMode = 1;
        filterKind = new ArrayList();
        filterDest = new ArrayList();
        isError = false;
        isFirst = true;
        isEnableMemo = false;
        isChangedMemo = false;
        adResponses = null;
        listener = new AdviewListener();
        tabs = null;
    }

    private boolean changeCurrentTab(int i, int j)
    {
        int k = tabs.getCurrentTab();
        byte byte0;
        if (i == 2)
        {
            if (j == 10)
            {
                byte0 = 7;
            } else
            if (j == 4)
            {
                byte0 = 6;
            } else
            if (j == 2)
            {
                byte0 = 5;
            } else
            {
                byte0 = 4;
            }
        } else
        if (j == 10)
        {
            byte0 = 3;
        } else
        if (j == 4)
        {
            byte0 = 2;
        } else
        if (j == 2)
        {
            byte0 = 1;
        } else
        {
            byte0 = 0;
        }
        if (k != byte0)
        {
            tabs.setCurrentTab(byte0);
            return true;
        } else
        {
            return false;
        }
    }

    private void dispAd()
    {
        if (displayMode == 1)
        {
            dispAd(((Context) (this)), "2080302612", "pv,bottom", ((AdViewListener) (listener)));
            return;
        } else
        {
            dispAd(((Context) (this)), "2080124764", "pv,bottom", ((AdViewListener) (listener)));
            return;
        }
    }

    private LinkedHashMap getDepartureData(int i)
    {
        if (i == 10)
        {
            return departureDate;
        }
        if (i == 4)
        {
            return departureHoliday;
        }
        if (i == 2)
        {
            return departureSaturday;
        } else
        {
            return departureOrdinary;
        }
    }

    private LinkedHashMap getDisplayDeparture(TimeTableData timetabledata, ArrayList arraylist, ArrayList arraylist1)
    {
        LinkedHashMap linkedhashmap = new LinkedHashMap();
        SparseArray sparsearray = timetabledata.getMappedTypeInfo(timetabledata.kindInfo);
        SparseArray sparsearray1 = timetabledata.getMappedTypeInfo(timetabledata.destInfo);
        for (int i = 1; i <= 35; i++)
        {
            if (!timetabledata.departure.containsKey(Integer.valueOf(i)))
            {
                continue;
            }
            ArrayList arraylist2 = null;
            Iterator iterator = ((ArrayList)timetabledata.departure.get(Integer.valueOf(i))).iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TimeData timedata = (jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TimeData)iterator.next();
                jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData typedata = (jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData)sparsearray.get(timedata.kindId);
                String s = null;
                if (typedata != null)
                {
                    String s3 = typedata.info;
                    s = null;
                    if (s3 != null)
                    {
                        s = typedata.info;
                    }
                }
                if (s == null || !arraylist.contains(s))
                {
                    jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData typedata1 = (jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData)sparsearray1.get(timedata.destId);
                    String s1 = null;
                    if (typedata1 != null)
                    {
                        String s2 = typedata1.info;
                        s1 = null;
                        if (s2 != null)
                        {
                            s1 = typedata1.info;
                        }
                    }
                    if (s1 == null || !arraylist1.contains(s1))
                    {
                        if (arraylist2 == null)
                        {
                            arraylist2 = new ArrayList();
                        }
                        arraylist2.add(timedata);
                    }
                }
            } while (true);
            if (arraylist2 != null)
            {
                linkedhashmap.put(Integer.valueOf(i), arraylist2);
            }
        }

        return linkedhashmap;
    }

    private TimeTableData getTimeTableData(int i)
    {
        if (i == 10)
        {
            return tableDataDate;
        }
        if (i == 4)
        {
            return tableDataHoliday;
        }
        if (i == 2)
        {
            return tableDataSaturday;
        } else
        {
            return tableDataOrdinary;
        }
    }

    private void initTabs(boolean flag)
    {
        LayoutInflater layoutinflater;
        android.widget.TabHost.TabContentFactory tabcontentfactory;
        android.widget.TabHost.TabSpec tabspec;
        android.widget.TabHost.TabSpec tabspec1;
        android.widget.TabHost.TabSpec tabspec2;
        android.widget.TabHost.TabSpec tabspec3;
        android.widget.TabHost.TabSpec tabspec4;
        android.widget.TabHost.TabSpec tabspec5;
        android.widget.TabHost.TabSpec tabspec6;
        android.widget.TabHost.TabSpec tabspec7;
        if (tabs != null)
        {
            tabs.clearAllTabs();
        } else
        {
            tabs = (TabHost)findViewById(0x1020012);
            tabs.setup();
        }
        lvOrdinaryV = new TimeTableVerticalListView(this);
        lvOrdinaryV.setId(0x7f0a003a);
        lvOrdinaryV.setLineColor(getResources().getColor(0x7f090036));
        lvSaturdayV = new TimeTableVerticalListView(this);
        lvSaturdayV.setId(0x7f0a003b);
        lvSaturdayV.setLineColor(getResources().getColor(0x7f090044));
        lvHolidayV = new TimeTableVerticalListView(this);
        lvHolidayV.setId(0x7f0a0039);
        lvHolidayV.setLineColor(getResources().getColor(0x7f09004f));
        lvDateV = new TimeTableVerticalListView(this);
        lvDateV.setId(0x7f0a0034);
        lvDateV.setLineColor(getResources().getColor(0x7f090036));
        lvOrdinaryH = new TimeTableHorizontalListView(this);
        lvOrdinaryH.setId(0x7f0a0037);
        lvOrdinaryH.setLineColor(getResources().getColor(0x7f090036));
        lvSaturdayH = new TimeTableHorizontalListView(this);
        lvSaturdayH.setId(0x7f0a0038);
        lvSaturdayH.setLineColor(getResources().getColor(0x7f090044));
        lvHolidayH = new TimeTableHorizontalListView(this);
        lvHolidayH.setId(0x7f0a0036);
        lvHolidayH.setLineColor(getResources().getColor(0x7f09004f));
        lvDateH = new TimeTableHorizontalListView(this);
        lvDateH.setId(0x7f0a0035);
        lvDateH.setLineColor(getResources().getColor(0x7f090036));
        layoutinflater = LayoutInflater.from(this);
        indOrdinaryV = layoutinflater.inflate(0x7f0300b6, null);
        indOrdinaryV.setBackgroundResource(0x7f020273);
        ((TextView)indOrdinaryV.findViewById(0x7f0a033e)).setText(getString(0x7f0d051f));
        indOrdinaryV.setFocusable(true);
        indSaturdayV = layoutinflater.inflate(0x7f0300b6, null);
        ((TextView)indSaturdayV.findViewById(0x7f0a033e)).setText(getString(0x7f0d0520));
        indSaturdayV.setFocusable(true);
        indHolidayV = layoutinflater.inflate(0x7f0300b6, null);
        ((TextView)indHolidayV.findViewById(0x7f0a033e)).setText(getString(0x7f0d051e));
        indHolidayV.setFocusable(true);
        indDateV = layoutinflater.inflate(0x7f0300b6, null);
        indOrdinaryH = layoutinflater.inflate(0x7f0300b6, null);
        indOrdinaryH.setBackgroundResource(0x7f020273);
        ((TextView)indOrdinaryH.findViewById(0x7f0a033e)).setText(getString(0x7f0d051f));
        indOrdinaryH.setFocusable(true);
        indSaturdayH = layoutinflater.inflate(0x7f0300b6, null);
        ((TextView)indSaturdayH.findViewById(0x7f0a033e)).setText(getString(0x7f0d0520));
        indSaturdayH.setFocusable(true);
        indHolidayH = layoutinflater.inflate(0x7f0300b6, null);
        ((TextView)indHolidayH.findViewById(0x7f0a033e)).setText(getString(0x7f0d051e));
        indHolidayV.setFocusable(true);
        indDateH = layoutinflater.inflate(0x7f0300b6, null);
        if (displayMode == 2)
        {
            indOrdinaryV.setVisibility(8);
            indSaturdayV.setVisibility(8);
            indHolidayV.setVisibility(8);
        } else
        {
            indOrdinaryH.setVisibility(8);
            indSaturdayH.setVisibility(8);
            indHolidayH.setVisibility(8);
        }
        indDateV.setVisibility(8);
        indDateH.setVisibility(8);
        tabcontentfactory = new android.widget.TabHost.TabContentFactory() {

            final TimeTableActivity this$0;

            public View createTabContent(String s)
            {
                if (s.equals("1"))
                {
                    return lvSaturdayV;
                }
                if (s.equals("2"))
                {
                    return lvHolidayV;
                }
                if (s.equals("3"))
                {
                    return lvDateV;
                }
                if (s.equals("4"))
                {
                    return lvOrdinaryH;
                }
                if (s.equals("5"))
                {
                    return lvSaturdayH;
                }
                if (s.equals("6"))
                {
                    return lvHolidayH;
                }
                if (s.equals("7"))
                {
                    return lvDateH;
                } else
                {
                    return lvOrdinaryV;
                }
            }

            
            {
                this$0 = TimeTableActivity.this;
                super();
            }
        };
        tabspec = tabs.newTabSpec("0").setIndicator(indOrdinaryV).setContent(tabcontentfactory);
        tabs.addTab(tabspec);
        tabspec1 = tabs.newTabSpec("1").setIndicator(indSaturdayV).setContent(tabcontentfactory);
        tabs.addTab(tabspec1);
        tabspec2 = tabs.newTabSpec("2").setIndicator(indHolidayV).setContent(tabcontentfactory);
        tabs.addTab(tabspec2);
        tabspec3 = tabs.newTabSpec("3").setIndicator(indDateV).setContent(tabcontentfactory);
        tabs.addTab(tabspec3);
        tabspec4 = tabs.newTabSpec("4").setIndicator(indOrdinaryH).setContent(tabcontentfactory);
        tabs.addTab(tabspec4);
        tabspec5 = tabs.newTabSpec("5").setIndicator(indSaturdayH).setContent(tabcontentfactory);
        tabs.addTab(tabspec5);
        tabspec6 = tabs.newTabSpec("6").setIndicator(indHolidayH).setContent(tabcontentfactory);
        tabs.addTab(tabspec6);
        tabspec7 = tabs.newTabSpec("7").setIndicator(indDateH).setContent(tabcontentfactory);
        tabs.addTab(tabspec7);
        if (timeTableData != null)
        {
            if (displayKind == 10)
            {
                tableDataDate = timeTableData;
                departureDate = getDisplayDeparture(timeTableData, filterKind, filterDest);
            } else
            if (displayKind == 4)
            {
                tableDataHoliday = timeTableData;
                departureHoliday = getDisplayDeparture(timeTableData, filterKind, filterDest);
            } else
            if (displayKind == 2)
            {
                tableDataSaturday = timeTableData;
                departureSaturday = getDisplayDeparture(timeTableData, filterKind, filterDest);
            } else
            {
                tableDataOrdinary = timeTableData;
                departureOrdinary = getDisplayDeparture(timeTableData, filterKind, filterDest);
            }
            changeCurrentTab(displayMode, displayKind);
            updateView();
            if (!TextUtils.isEmpty(sMemoId))
            {
                if (sql == null)
                {
                    sql = new ResultDB(this);
                }
                if (flag)
                {
                    sql.updateTimetable(sMemoId, new Bundle(), objTimetable);
                }
                sMemoId = null;
            }
        } else
        if (displayKind != -1)
        {
            changeCurrentTab(displayMode, displayKind);
            updateView();
        } else
        {
            search(null, true, 0);
        }
        tabs.setOnTabChangedListener(new android.widget.TabHost.OnTabChangeListener() {

            final TimeTableActivity this$0;

            public void onTabChanged(String s)
            {
                int i = tabs.getCurrentView().getId();
                if (i != 0x7f0a0034 && i != 0x7f0a0035) goto _L2; else goto _L1
_L1:
                displayKind = 10;
_L4:
                updateView();
                return;
_L2:
                if (i == 0x7f0a003a || i == 0x7f0a0037)
                {
                    displayKind = 1;
                } else
                if (i == 0x7f0a003b || i == 0x7f0a0038)
                {
                    displayKind = 2;
                } else
                if (i == 0x7f0a0039 || i == 0x7f0a0036)
                {
                    displayKind = 4;
                }
                if (true) goto _L4; else goto _L3
_L3:
            }

            
            {
                this$0 = TimeTableActivity.this;
                super();
            }
        });
    }

    private boolean isBeforeDay(Calendar calendar)
    {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(11, 0);
        calendar1.set(12, 0);
        calendar1.set(13, 0);
        calendar1.set(14, 0);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        boolean flag = calendar.before(calendar1);
        boolean flag1 = false;
        if (flag)
        {
            flag1 = true;
        }
        return flag1;
    }

    private void launchMyPageEdit()
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/MyPageActivity);
        intent.putExtra(getString(0x7f0d01a5), 1);
        intent.putExtra(getString(0x7f0d0247), 1);
        intent.putExtra(getString(0x7f0d01df), getResources().getInteger(0x7f0c0065));
        startActivityForResult(intent, getResources().getInteger(0x7f0c004b));
    }

    private void resetLayoutVisibility()
    {
        TimeTableData timetabledata = lvCurrent.getTimeTableData();
        if (timetabledata == null || timetabledata.isEntire) goto _L2; else goto _L1
_L1:
        if (txtFilter.getVisibility() != 4)
        {
            txtFilter.setVisibility(4);
        }
_L8:
        if (displayKind != 10) goto _L4; else goto _L3
_L3:
        if (layoutDate.getVisibility() == 8)
        {
            layoutDate.setVisibility(0);
        }
        if (timetabledata != null)
        {
            txtDate.setText((new StringBuilder()).append("\u65E5\u4ED8\u6307\u5B9A: ").append(timetabledata.getDisplayDateString()).toString());
        } else
        {
            txtDate.setText("\u65E5\u4ED8\u6307\u5B9A: ");
        }
        if (timeTableData != null && timeTableData.equals(timetabledata))
        {
            if (imgClose.getVisibility() != 4)
            {
                imgClose.setVisibility(4);
            }
        } else
        if (imgClose.getVisibility() != 0)
        {
            imgClose.setVisibility(0);
        }
        if (layoutTab.getVisibility() == 0)
        {
            layoutTab.setVisibility(8);
        }
        if (imgTabLine.getVisibility() == 0)
        {
            imgTabLine.setVisibility(8);
        }
_L6:
        return;
_L2:
        if (txtFilter.getVisibility() != 0)
        {
            txtFilter.setVisibility(0);
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (layoutDate.getVisibility() == 0)
        {
            layoutDate.setVisibility(8);
        }
        if (layoutTab.getVisibility() == 8)
        {
            layoutTab.setVisibility(0);
        }
        if (imgTabLine.getVisibility() != 8) goto _L6; else goto _L5
_L5:
        imgTabLine.setVisibility(0);
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }

    private void saveDisplayMemo(int i, int j)
    {
        TimeTableData timetabledata = getTimeTableData(displayKind);
        if (displayKind != 10 && !TextUtils.isEmpty(timetabledata.date))
        {
            searchTimeTableForMemo(displayKind, i, j);
            return;
        } else
        {
            saveDisplayMemo(timetabledata, i, j);
            return;
        }
    }

    private void saveDisplayMemo(TimeTableData timetabledata, int i, int j)
    {
        String s;
        Bundle bundle = new Bundle();
        Bundle bundle1 = new Bundle();
        timetabledata.filterKind = filterKind;
        timetabledata.filterDest = filterDest;
        timetabledata.displayMode = displayMode;
        bundle1.putSerializable(getString(0x7f0d0232), timetabledata);
        if (sql == null)
        {
            sql = new ResultDB(this);
        }
        sql.addTimetable(bundle, bundle1);
        s = getString(0x7f0d049e);
        i;
        JVM INSTR tableswitch 1 2: default 116
    //                   1 155
    //                   2 176;
           goto _L1 _L2 _L3
_L1:
        String s1 = getString(0x7f0d00ad, new Object[] {
            s
        });
_L5:
        showMessageDialog(s1, getString(0x7f0d00b0), getString(0x7f0d0074));
        return;
_L2:
        s1 = getString(0x7f0d00af, new Object[] {
            s
        });
        continue; /* Loop/switch isn't completed */
_L3:
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(j);
        aobj[1] = s;
        s1 = getString(0x7f0d00ae, aobj);
        if (true) goto _L5; else goto _L4
_L4:
    }

    private void search(final String sDate, boolean flag, int i)
    {
        if (objSearch != null)
        {
            return;
        }
        objSearch = new TimeTableSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final TimeTableActivity this$0;
            final String val$sDate;

            public boolean onCanceled()
            {
                if (beforeKind != -1)
                {
                    beforeKind = -1;
                }
                if (isFirst)
                {
                    finish();
                }
                objSearch = null;
                return false;
            }

            public boolean onError(APIError apierror)
            {
                if (objSearch == null)
                {
                    return false;
                }
                isError = true;
                String s = objSearch.getError().getCode();
                if (!TransitUtil.isEmpty(s) && s.equals(getString(0x7f0d0516)))
                {
                    showSimpleErrorMessageDialog(getString(0x7f0d0517));
                } else
                if (beforeKind != -1)
                {
                    beforeKind = -1;
                } else
                {
                    launchDirection();
                }
                objSearch = null;
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                TimeTableData timetabledata;
                if (objSearch != null)
                {
                    if ((timetabledata = (TimeTableData)objSearch.getResult()) != null)
                    {
                        if (!TextUtils.isEmpty(sDate))
                        {
                            displayKind = 10;
                        } else
                        if (timetabledata.dateKind != 0)
                        {
                            displayKind = timetabledata.dateKind;
                        } else
                        {
                            displayKind = Integer.valueOf(timetabledata.kind).intValue();
                        }
                        if (displayKind == 10)
                        {
                            tableDataDate = timetabledata;
                        } else
                        if (displayKind == 4)
                        {
                            tableDataHoliday = timetabledata;
                        } else
                        if (displayKind == 2)
                        {
                            tableDataSaturday = timetabledata;
                        } else
                        {
                            tableDataOrdinary = timetabledata;
                        }
                        if (!changeCurrentTab(displayMode, displayKind))
                        {
                            updateView();
                        }
                        objSearch = null;
                        return false;
                    }
                }
                return false;
            }

            
            {
                this$0 = TimeTableActivity.this;
                sDate = s;
                super();
            }
        });
        objSearch.setCode(station.getId());
        objSearch.setId(direction.getGroupid());
        if (!TextUtils.isEmpty(sDate))
        {
            objSearch.setDate(sDate);
        } else
        if (flag)
        {
            Calendar calendar = Calendar.getInstance();
            int j = 1 + calendar.get(2);
            int k = calendar.get(5);
            int l = calendar.get(11);
            SimpleDateFormat simpledateformat;
            if (j == 1 && k == 1)
            {
                calendar.set(5, calendar.get(5));
            } else
            if (l < 4)
            {
                calendar.set(5, -1 + calendar.get(5));
            } else
            {
                calendar.set(5, calendar.get(5));
            }
            simpledateformat = new SimpleDateFormat("yyyyMMdd", Locale.JAPANESE);
            objSearch.setDate(simpledateformat.format(calendar.getTime()));
        } else
        {
            objSearch.setKind(i);
        }
        objSearch.request();
    }

    private void searchLatestTimeTable(String s, int i)
    {
        if (objSearch != null)
        {
            return;
        }
        objSearch = new TimeTableSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

            final TimeTableActivity this$0;

            public boolean onCanceled()
            {
                initTabs(false);
                objSearch = null;
                return false;
            }

            public boolean onError(APIError apierror)
            {
                initTabs(false);
                objSearch = null;
                return false;
            }

            public boolean onSuccess(ApiBase apibase, Object obj)
            {
                TimeTableData timetabledata;
                if (objSearch == null)
                {
                    return false;
                }
                timetabledata = (TimeTableData)objSearch.getResult();
                if (!timeTableData.isEqual(timetabledata)) goto _L2; else goto _L1
_L1:
                initTabs(false);
_L4:
                objSearch = null;
                return false;
_L2:
                if (!isFinishing())
                {
                    (new TransitDialogBuilder(TimeTableActivity.this)).setTitleWarnning(getString(0x7f0d053d)).setMessage(getString(0x7f0d053b)).setPositiveButton(getString(0x7f0d053e), timetabledata. new android.content.DialogInterface.OnClickListener() {

                        final _cls5 this$1;
                        final TimeTableData val$latestTimeTable;

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            dialoginterface.dismiss();
                            timeTableData.updateTimeTable(latestTimeTable);
                            initTabs(true);
                        }

            
            {
                this$1 = final__pcls5;
                latestTimeTable = TimeTableData.this;
                super();
            }
                    }).setNegativeButton(getString(0x7f0d053c), new android.content.DialogInterface.OnClickListener() {

                        final _cls5 this$1;

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            dialoginterface.cancel();
                        }

            
            {
                this$1 = _cls5.this;
                super();
            }
                    }).setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

                        final _cls5 this$1;

                        public void onCancel(DialogInterface dialoginterface)
                        {
                            initTabs(false);
                        }

            
            {
                this$1 = _cls5.this;
                super();
            }
                    }).show();
                }
                if (true) goto _L4; else goto _L3
_L3:
            }

            
            {
                this$0 = TimeTableActivity.this;
                super();
            }
        });
        objSearch.setCode(station.getId());
        objSearch.setId(direction.getGroupid());
        if (!TextUtils.isEmpty(s))
        {
            objSearch.setDate(s);
        } else
        {
            objSearch.setKind(i);
        }
        objSearch.request();
    }

    private void searchTimeTableForMemo(int i, final int saveType, final int deleteNum)
    {
        if (objSearch != null)
        {
            return;
        } else
        {
            objSearch = new TimeTableSearch(this, new jp.co.yahoo.android.apps.transit.api.ApiBase.ApiListener() {

                final TimeTableActivity this$0;
                final int val$deleteNum;
                final int val$saveType;

                public boolean onCanceled()
                {
                    objSearch = null;
                    return false;
                }

                public boolean onError(APIError apierror)
                {
                    objSearch = null;
                    return false;
                }

                public boolean onSuccess(ApiBase apibase, Object obj)
                {
                    if (objSearch == null)
                    {
                        return false;
                    } else
                    {
                        TimeTableData timetabledata = (TimeTableData)objSearch.getResult();
                        saveDisplayMemo(timetabledata, saveType, deleteNum);
                        objSearch = null;
                        return false;
                    }
                }

            
            {
                this$0 = TimeTableActivity.this;
                saveType = i;
                deleteNum = j;
                super();
            }
            });
            objSearch.setCode(station.getId());
            objSearch.setId(direction.getGroupid());
            objSearch.setKind(i);
            objSearch.request();
            return;
        }
    }

    private void setDate(int i, int j, int k)
    {
        tableDataDate = null;
        departureDate = null;
        lvDateV.setRefreshed(false);
        lvDateH.setRefreshed(false);
        lvDateV.clearData();
        lvDateH.clearData();
        beforeKind = displayKind;
        Calendar calendar = Calendar.getInstance();
        calendar.set(i, j, k);
        search((new SimpleDateFormat("yyyyMMdd", Locale.JAPANESE)).format(calendar.getTime()), false, 0);
    }

    private void setEnableMemo(boolean flag)
    {
        if (flag != isEnableMemo)
        {
            isEnableMemo = flag;
            supportInvalidateOptionsMenu();
        }
    }

    private void setFilter(AlertDialog alertdialog, CheckListView checklistview, CheckListView checklistview1)
    {
        ArrayList arraylist = checklistview.getCheckItems();
        ArrayList arraylist1 = checklistview.getNoCheckItems();
        ArrayList arraylist2 = checklistview1.getCheckItems();
        ArrayList arraylist3 = checklistview1.getNoCheckItems();
        if (arraylist.size() < 1 || arraylist2.size() < 1)
        {
            showMessageDialog(getString(0x7f0d014d), getString(0x7f0d0151), getString(0x7f0d0074));
            return;
        }
        boolean flag = false;
        boolean flag1 = false;
        ArrayList arraylist4 = new ArrayList();
        ArrayList arraylist5 = new ArrayList();
        arraylist4.addAll(filterKind);
        arraylist5.addAll(filterDest);
        Iterator iterator = arraylist.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj3 = iterator.next();
            if (arraylist4.contains(obj3))
            {
                arraylist4.remove(obj3);
                flag = true;
            }
        } while (true);
        Iterator iterator1 = arraylist1.iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            Object obj2 = iterator1.next();
            if (!arraylist4.contains(obj2))
            {
                arraylist4.add((String)obj2);
                flag = true;
            }
        } while (true);
        Iterator iterator2 = arraylist2.iterator();
        do
        {
            if (!iterator2.hasNext())
            {
                break;
            }
            Object obj1 = iterator2.next();
            if (arraylist5.contains(obj1))
            {
                arraylist5.remove(obj1);
                flag1 = true;
            }
        } while (true);
        Iterator iterator3 = arraylist3.iterator();
        do
        {
            if (!iterator3.hasNext())
            {
                break;
            }
            Object obj = iterator3.next();
            if (!arraylist5.contains(obj))
            {
                arraylist5.add((String)obj);
                flag1 = true;
            }
        } while (true);
        if (flag || flag1)
        {
            LinkedHashMap linkedhashmap = getDisplayDeparture(getTimeTableData(displayKind), arraylist4, arraylist5);
            if (linkedhashmap.size() < 1)
            {
                showNoTrainMessageDialog(false);
                return;
            }
            filterKind = arraylist4;
            filterDest = arraylist5;
            lvOrdinaryV.setRefreshed(false);
            lvSaturdayV.setRefreshed(false);
            lvHolidayV.setRefreshed(false);
            lvOrdinaryH.setRefreshed(false);
            lvSaturdayH.setRefreshed(false);
            lvHolidayH.setRefreshed(false);
            if (displayKind == 10)
            {
                lvDateV.setRefreshed(false);
                lvDateH.setRefreshed(false);
                departureOrdinary = null;
                departureSaturday = null;
                departureHoliday = null;
                departureDate = linkedhashmap;
            } else
            if (displayKind == 4)
            {
                departureOrdinary = null;
                departureSaturday = null;
                departureHoliday = linkedhashmap;
                departureDate = null;
            } else
            if (displayKind == 2)
            {
                departureOrdinary = null;
                departureSaturday = linkedhashmap;
                departureHoliday = null;
                departureDate = null;
            } else
            {
                departureOrdinary = linkedhashmap;
                departureSaturday = null;
                departureHoliday = null;
                departureDate = null;
            }
            lvCurrent.refreshList(linkedhashmap, selectTime, Calendar.getInstance());
            isChangedMemo = true;
            setEnableMemo(true);
        }
        alertdialog.dismiss();
    }

    private void setView()
    {
        TextView textview = (TextView)findViewById(0x7f0a017b);
        TextView textview1 = (TextView)findViewById(0x7f0a017c);
        textview.setText((new StringBuilder()).append(station.getName()).append(getString(0x7f0d0304)).toString());
        textview1.setText((new StringBuilder()).append(station.getDiainfo().getRailName()).append("\n\u3010").append(direction.getDirection()).append(getString(0x7f0d0512)).append("\u3011").toString());
        if (!TextUtils.isEmpty(sReferDate))
        {
            Calendar calendar = TransitUtil.toCalendar(sReferDate);
            if (calendar != null && isBeforeDay(calendar))
            {
                int i = 1;
                String s;
                if (!TextUtils.isEmpty(timeTableData.date))
                {
                    s = timeTableData.date;
                } else
                {
                    i = Integer.parseInt(timeTableData.kind);
                    s = null;
                }
                searchLatestTimeTable(s, i);
                sReferDate = null;
                return;
            } else
            {
                initTabs(false);
                return;
            }
        } else
        {
            initTabs(false);
            return;
        }
    }

    private void showDeleteMemoDialog()
    {
        String s = getString(0x7f0d0154);
        Object aobj[] = new Object[2];
        aobj[0] = getString(0x7f0d049e);
        aobj[1] = sql.getMaxTimetable();
        showSingleChoiceListDialog(s, getString(0x7f0d013f, aobj), 0x7f070009, 0, new android.content.DialogInterface.OnClickListener() {

            final TimeTableActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                if (i == 0)
                {
                    saveDisplayMemo(1, 0);
                    return;
                } else
                {
                    launchMyPageEdit();
                    return;
                }
            }

            
            {
                this$0 = TimeTableActivity.this;
                super();
            }
        }, null);
    }

    private void showNoTrainMessageDialog(final boolean dispFilterDialog)
    {
        if (isFinishing())
        {
            return;
        } else
        {
            (new TransitDialogBuilder(this)).setMessage(getString(0x7f0d014e)).setTitle(getString(0x7f0d0152)).setNegativeButton(getString(0x7f0d0074), new android.content.DialogInterface.OnClickListener() {

                final TimeTableActivity this$0;
                final boolean val$dispFilterDialog;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.cancel();
                    if (dispFilterDialog)
                    {
                        showSelectFilter(null);
                    }
                }

            
            {
                this$0 = TimeTableActivity.this;
                dispFilterDialog = flag;
                super();
            }
            }).show();
            return;
        }
    }

    private void updateView()
    {
        lvCurrent = (TimeTableBaseListView)tabs.getCurrentView();
        TimeTableData timetabledata = getTimeTableData(displayKind);
        LinkedHashMap linkedhashmap = getDepartureData(displayKind);
        if (timetabledata == null)
        {
            search(null, false, displayKind);
        } else
        {
            Calendar calendar = Calendar.getInstance();
            if (linkedhashmap == null)
            {
                linkedhashmap = getDisplayDeparture(timetabledata, filterKind, filterDest);
                boolean flag;
                int i;
                SimpleDateFormat simpledateformat;
                if (displayKind == 10)
                {
                    departureDate = linkedhashmap;
                } else
                if (displayKind == 4)
                {
                    departureHoliday = linkedhashmap;
                } else
                if (displayKind == 2)
                {
                    departureSaturday = linkedhashmap;
                } else
                {
                    departureOrdinary = linkedhashmap;
                }
            }
            if (!lvCurrent.isInitialized())
            {
                flag = true;
                if (timeTableData != null && timeTableData.equals(timetabledata))
                {
                    i = timeTableData.dateKind;
                    if (i == 0)
                    {
                        i = Integer.parseInt(timeTableData.kind);
                    }
                    if (TransitUtil.getDayOfWeek(calendar) != i)
                    {
                        flag = false;
                    }
                    if (!TextUtils.isEmpty(timeTableData.date) && !flag)
                    {
                        simpledateformat = new SimpleDateFormat("yyyyMMdd", Locale.JAPANESE);
                        if (timeTableData.date.equals(simpledateformat.format(calendar.getTime())))
                        {
                            flag = true;
                        }
                    }
                    isMemoShowCurrent = flag;
                }
                if (!flag)
                {
                    calendar = null;
                }
                if (isFirst)
                {
                    String s;
                    if (displayKind == 10)
                    {
                        s = timeTableData.getDisplayDateString();
                    } else
                    if (displayKind == 4)
                    {
                        s = getString(0x7f0d051e);
                    } else
                    if (displayKind == 2)
                    {
                        s = getString(0x7f0d0520);
                    } else
                    {
                        s = getString(0x7f0d051f);
                    }
                    Toast.makeText(this, (new StringBuilder()).append(s).append(getString(0x7f0d0522)).toString(), 1).show();
                    isFirst = false;
                }
                lvCurrent.showView(timetabledata, flag, linkedhashmap, selectTime, calendar);
                selectTime[0] = -1;
                selectTime[1] = -1;
            } else
            if (!lvCurrent.isRefreshed())
            {
                if (lvCurrent.getTimeTableData() == null)
                {
                    if (timeTableData != null && timeTableData.equals(timetabledata))
                    {
                        lvCurrent.resetData(timetabledata, isMemoShowCurrent);
                    } else
                    {
                        lvCurrent.resetData(timetabledata, true);
                    }
                }
                lvCurrent.refreshList(linkedhashmap, selectTime, calendar);
                selectTime[0] = -1;
                selectTime[1] = -1;
            } else
            {
                lvCurrent.refreshCurrentTime(selectTime, calendar);
                selectTime[0] = -1;
                selectTime[1] = -1;
            }
            if (timeTableData != null && timeTableData.equals(timetabledata))
            {
                if (!isChangedMemo)
                {
                    setEnableMemo(false);
                }
            } else
            {
                setEnableMemo(true);
            }
            resetLayoutVisibility();
            lvCurrent.setAdView(adResponses);
            if (linkedhashmap.size() < 1)
            {
                showNoTrainMessageDialog(true);
                return;
            }
        }
    }

    public void cancelSelectDate(View view)
    {
        if (beforeKind == -1)
        {
            search(null, true, 0);
        } else
        {
            displayKind = beforeKind;
            changeCurrentTab(displayMode, displayKind);
        }
        beforeKind = -1;
    }

    public void launchDirection()
    {
        if (isError)
        {
            Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/RailDirection);
            intent.putExtra(getString(0x7f0d023e), station);
            intent.putExtra(getString(0x7f0d01df), getResources().getInteger(0x7f0c0065));
            intent.putExtra(getString(0x7f0d01b9), getString(0x7f0d010b));
            setResult(0, intent);
            finish();
        }
    }

    public void launchStationInfo(View view)
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/StationInfo);
        intent.putExtra(getString(0x7f0d023e), station);
        startActivityForResult(intent, getResources().getInteger(0x7f0c0062));
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (-1 == j && getResources().getInteger(0x7f0c004b) == i)
        {
            saveDisplayMemo(2, intent.getIntExtra(getString(0x7f0d01e4), 0));
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030034);
        Intent intent = getIntent();
        direction = (RailDirectionData)intent.getSerializableExtra(getString(0x7f0d01b3));
        station = (StationData)intent.getSerializableExtra(getString(0x7f0d023e));
        objTimetable = intent.getBundleExtra(getString(0x7f0d0232));
        sMemoId = intent.getStringExtra(getString(0x7f0d01c5));
        sReferDate = intent.getStringExtra(getString(0x7f0d01dd));
        if (objTimetable != null)
        {
            timeTableData = (TimeTableData)objTimetable.getSerializable(getString(0x7f0d0232));
        }
        if (direction == null)
        {
            direction = new RailDirectionData();
        }
        if (station == null)
        {
            station = new StationData();
        }
        if (timeTableData != null)
        {
            direction.setGroupid(timeTableData.gId);
            direction.setDirection(timeTableData.direction);
            station.setId(timeTableData.code);
            station.setName(timeTableData.name);
            station.getDiainfo().setRailName(timeTableData.railName);
            if (!TextUtils.isEmpty(timeTableData.date))
            {
                displayKind = 10;
            } else
            {
                displayKind = Integer.parseInt(timeTableData.kind);
            }
            if (timeTableData.filterKind != null)
            {
                filterKind = timeTableData.filterKind;
            }
            if (timeTableData.filterDest != null)
            {
                filterDest = timeTableData.filterDest;
            }
            displayMode = timeTableData.displayMode;
        }
        setTitle(getString(0x7f0d0511));
        layoutDate = (LinearLayout)findViewById(0x7f0a017e);
        txtDate = (TextView)findViewById(0x7f0a0180);
        layoutTab = (LinearLayout)findViewById(0x7f0a0181);
        imgTabLine = (ImageView)findViewById(0x7f0a0182);
        txtFilter = (TextView)findViewById(0x7f0a017d);
        imgClose = (ImageView)findViewById(0x7f0a017f);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        if (isEnableMemo)
        {
            MenuItemCompat.setShowAsAction(menu.add(0, 0, 0, getString(0x7f0d053d)).setIcon(0x7f0200e6), 1);
        }
        MenuItem menuitem = menu.add(0, 1, 0, getString(0x7f0d0513));
        Integer ainteger[] = new Integer[2];
        ainteger[0] = Integer.valueOf(0);
        ainteger[1] = Integer.valueOf(1);
        DisplayModeAdapter displaymodeadapter = new DisplayModeAdapter(this, 0x7f030072, ainteger);
        Spinner spinner = new Spinner(this);
        spinner.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -1));
        spinner.setAdapter(displaymodeadapter);
        if (displayMode == 1)
        {
            spinner.setSelection(0);
        } else
        {
            spinner.setSelection(1);
        }
        spinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

            final TimeTableActivity this$0;

            public void onItemSelected(AdapterView adapterview, View view, int i, long l)
            {
                if (i == 0)
                {
                    touchTapRD((new StringBuilder()).append(getString(0x7f0d03d7)).append("/").append(getString(0x7f0d03f2)).toString());
                    setDisplayMode(1);
                    return;
                } else
                {
                    touchTapRD((new StringBuilder()).append(getString(0x7f0d03d7)).append("/").append(getString(0x7f0d0434)).toString());
                    setDisplayMode(2);
                    return;
                }
            }

            public void onNothingSelected(AdapterView adapterview)
            {
            }

            
            {
                this$0 = TimeTableActivity.this;
                super();
            }
        });
        MenuItemCompat.setActionView(menuitem, spinner);
        MenuItemCompat.setShowAsAction(menuitem, 1);
        return true;
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (i == 4)
        {
            TimeTableData timetabledata = timeTableData;
            boolean flag = false;
            if (timetabledata != null)
            {
                if (lvCurrent == null || lvCurrent.getTimeTableData() == null)
                {
                    flag = true;
                } else
                {
                    boolean flag1 = timeTableData.equals(lvCurrent.getTimeTableData());
                    flag = false;
                    if (!flag1)
                    {
                        flag = true;
                    }
                }
            }
            if (flag)
            {
                if (!TextUtils.isEmpty(timeTableData.date))
                {
                    if (displayKind == 10)
                    {
                        departureDate = null;
                        lvDateV.setRefreshed(false);
                        lvDateH.setRefreshed(false);
                        lvDateV.clearData();
                        lvDateH.clearData();
                        tableDataDate = timeTableData;
                    }
                    displayKind = 10;
                } else
                {
                    displayKind = Integer.parseInt(timeTableData.kind);
                }
                if (!changeCurrentTab(displayMode, displayKind))
                {
                    updateView();
                }
                if (!isChangedMemo)
                {
                    setEnableMemo(false);
                }
                return true;
            }
        }
        return super.onKeyDown(i, keyevent);
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        super.onOptionsItemSelected(menuitem);
        menuitem.getItemId();
        JVM INSTR tableswitch 0 0: default 32
    //                   0 34;
           goto _L1 _L2
_L1:
        return true;
_L2:
        touchTapRD(getString(0x7f0d03f7));
        saveDisplayMemo();
        if (true) goto _L1; else goto _L3
_L3:
    }

    protected void onRestoreInstanceState(Bundle bundle)
    {
        super.onRestoreInstanceState(bundle);
        sReferDate = bundle.getString("sReferDate");
        sMemoId = bundle.getString("sMemoId");
        timeTableData = (TimeTableData)bundle.getSerializable("timeTableData");
        tableDataOrdinary = (TimeTableData)bundle.getSerializable("tableDataOrdinary");
        tableDataSaturday = (TimeTableData)bundle.getSerializable("tableDataSaturday");
        tableDataHoliday = (TimeTableData)bundle.getSerializable("tableDataHoliday");
        tableDataDate = (TimeTableData)bundle.getSerializable("tableDataDate");
        displayKind = bundle.getInt("displayKind");
        displayMode = bundle.getInt("displayMode");
        ArrayList arraylist = bundle.getStringArrayList("filterKind");
        if (arraylist != null)
        {
            filterKind = arraylist;
        }
        ArrayList arraylist1 = bundle.getStringArrayList("filterDest");
        if (arraylist1 != null)
        {
            filterDest = arraylist1;
        }
        isFirst = bundle.getBoolean("isFirst", false);
        isEnableMemo = bundle.getBoolean("isEnableMemo", true);
        isChangedMemo = bundle.getBoolean("isChangedMemo", true);
    }

    protected void onResume()
    {
        super.onResume();
        if (tabs == null)
        {
            setView();
            dispAd();
        }
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putString("sReferDate", sReferDate);
        bundle.putString("sMemoId", sMemoId);
        bundle.putSerializable("timeTableData", timeTableData);
        bundle.putSerializable("tableDataOrdinary", tableDataOrdinary);
        bundle.putSerializable("tableDataSaturday", tableDataSaturday);
        bundle.putSerializable("tableDataHoliday", tableDataHoliday);
        bundle.putSerializable("tableDataDate", tableDataDate);
        bundle.putInt("displayKind", displayKind);
        bundle.putInt("displayMode", displayMode);
        bundle.putStringArrayList("filterKind", filterKind);
        bundle.putStringArrayList("filterDest", filterDest);
        bundle.putBoolean("isFirst", isFirst);
        bundle.putBoolean("isEnableMemo", isEnableMemo);
        bundle.putBoolean("isChangedMemo", isChangedMemo);
    }

    public void saveDisplayMemo()
    {
        TimeTableData timetabledata;
        if (lvCurrent != null)
        {
            if ((timetabledata = getTimeTableData(displayKind)) != null && timetabledata.isEntire)
            {
                if (sql == null)
                {
                    sql = new ResultDB(this);
                }
                if (sql.countTimetable() >= Integer.parseInt(sql.getMaxTimetable()))
                {
                    showDeleteMemoDialog();
                    return;
                } else
                {
                    saveDisplayMemo(0, 0);
                    return;
                }
            }
        }
    }

    public void scrollToExplain(View view)
    {
        if (lvCurrent instanceof TimeTableHorizontalListView)
        {
            ((TimeTableHorizontalListView)lvCurrent).scrollToExplain();
        }
    }

    public void scrollToTimetable(View view)
    {
        if (lvCurrent instanceof TimeTableHorizontalListView)
        {
            ((TimeTableHorizontalListView)lvCurrent).scrollToTimetable();
        }
    }

    public void setDisplayMode(int i)
    {
        if (displayMode != i)
        {
            displayMode = i;
            if (lvCurrent != null && lvCurrent.isInitialized() && lvCurrent.isRefreshed())
            {
                selectTime = lvCurrent.getSelectTime();
            }
            adResponses = null;
            TimeTableData timetabledata;
            if (displayMode == 2)
            {
                indOrdinaryV.setVisibility(8);
                indSaturdayV.setVisibility(8);
                indHolidayV.setVisibility(8);
                indOrdinaryH.setVisibility(0);
                indSaturdayH.setVisibility(0);
                indHolidayH.setVisibility(0);
            } else
            {
                indOrdinaryV.setVisibility(0);
                indSaturdayV.setVisibility(0);
                indHolidayV.setVisibility(0);
                indOrdinaryH.setVisibility(8);
                indSaturdayH.setVisibility(8);
                indHolidayH.setVisibility(8);
            }
            if (!changeCurrentTab(displayMode, displayKind))
            {
                updateView();
            }
            timetabledata = lvCurrent.getTimeTableData();
            if (timeTableData != null && timetabledata != null && timeTableData.equals(timetabledata) && timeTableData.displayMode != displayMode)
            {
                isChangedMemo = true;
            }
            setEnableMemo(true);
            dispAd();
        }
    }

    public void showSelectDate(View view)
    {
        if (isFinishing())
        {
            return;
        } else
        {
            Calendar calendar = Calendar.getInstance();
            int i = calendar.get(1);
            int j = calendar.get(2);
            int k = calendar.get(5);
            showDatePickerDialog(getString(0x7f0d052b), i, j, k, new android.app.DatePickerDialog.OnDateSetListener() {

                final TimeTableActivity this$0;

                public void onDateSet(DatePicker datepicker, int l, int i1, int j1)
                {
                    touchTapRD(getString(0x7f0d03d3));
                    setDate(l, i1, j1);
                }

            
            {
                this$0 = TimeTableActivity.this;
                super();
            }
            });
            return;
        }
    }

    public void showSelectFilter(View view)
    {
        TimeTableData timetabledata;
        if (!isFinishing())
        {
            if ((timetabledata = lvCurrent.getTimeTableData()) != null && timetabledata.isEntire)
            {
                ScrollView scrollview = (ScrollView)LayoutInflater.from(this).inflate(0x7f0300b1, null);
                LinearLayout linearlayout = (LinearLayout)scrollview.findViewById(0x7f0a032f);
                LinearLayout linearlayout1 = (LinearLayout)scrollview.findViewById(0x7f0a0330);
                ArrayList arraylist = timetabledata.getSortedTypeInfo(timetabledata.kindInfo);
                ArrayList arraylist1 = new ArrayList();
                ArrayList arraylist2 = new ArrayList();
                for (Iterator iterator = arraylist.iterator(); iterator.hasNext();)
                {
                    jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData typedata1 = (jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData)iterator.next();
                    arraylist1.add(typedata1.info);
                    if (filterKind.contains(typedata1.info))
                    {
                        arraylist2.add(Boolean.valueOf(false));
                    } else
                    {
                        arraylist2.add(Boolean.valueOf(true));
                    }
                }

                final CheckListView chkListKind = new CheckListView(this);
                chkListKind.setListItems(arraylist1);
                chkListKind.setListChecks(arraylist2);
                linearlayout.addView(chkListKind);
                chkListKind.showView();
                ArrayList arraylist3 = timetabledata.getSortedTypeInfo(timetabledata.destInfo);
                ArrayList arraylist4 = new ArrayList();
                ArrayList arraylist5 = new ArrayList();
                for (Iterator iterator1 = arraylist3.iterator(); iterator1.hasNext();)
                {
                    jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData typedata = (jp.co.yahoo.android.apps.transit.api.data.TimeTableData.TypeData)iterator1.next();
                    arraylist4.add(typedata.info);
                    if (filterDest.contains(typedata.info))
                    {
                        arraylist5.add(Boolean.valueOf(false));
                    } else
                    {
                        arraylist5.add(Boolean.valueOf(true));
                    }
                }

                final CheckListView chkListDest = new CheckListView(this);
                chkListDest.setListItems(arraylist4);
                chkListDest.setListChecks(arraylist5);
                linearlayout1.addView(chkListDest);
                chkListDest.showView();
                TransitDialogBuilder transitdialogbuilder = new TransitDialogBuilder(this);
                transitdialogbuilder.setTitle(getString(0x7f0d051b));
                transitdialogbuilder.setView(scrollview);
                transitdialogbuilder.setPositiveButton(0x7f0d007f, null);
                transitdialogbuilder.setNegativeButton(0x7f0d0071, null);
                final AlertDialog dialog = transitdialogbuilder.show();
                Button button = dialog.getButton(-1);
                android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

                    final TimeTableActivity this$0;
                    final CheckListView val$chkListDest;
                    final CheckListView val$chkListKind;
                    final AlertDialog val$dialog;

                    public void onClick(View view1)
                    {
                        touchTapRD(getString(0x7f0d040b));
                        setFilter(dialog, chkListKind, chkListDest);
                    }

            
            {
                this$0 = TimeTableActivity.this;
                dialog = alertdialog;
                chkListKind = checklistview;
                chkListDest = checklistview1;
                super();
            }
                };
                button.setOnClickListener(onclicklistener);
                return;
            }
        }
    }






/*
    static TimeTableSearch access$1102(TimeTableActivity timetableactivity, TimeTableSearch timetablesearch)
    {
        timetableactivity.objSearch = timetablesearch;
        return timetablesearch;
    }

*/


/*
    static TimeTableData access$1202(TimeTableActivity timetableactivity, TimeTableData timetabledata)
    {
        timetableactivity.tableDataDate = timetabledata;
        return timetabledata;
    }

*/


/*
    static TimeTableData access$1302(TimeTableActivity timetableactivity, TimeTableData timetabledata)
    {
        timetableactivity.tableDataHoliday = timetabledata;
        return timetabledata;
    }

*/


/*
    static TimeTableData access$1402(TimeTableActivity timetableactivity, TimeTableData timetabledata)
    {
        timetableactivity.tableDataSaturday = timetabledata;
        return timetabledata;
    }

*/


/*
    static TimeTableData access$1502(TimeTableActivity timetableactivity, TimeTableData timetabledata)
    {
        timetableactivity.tableDataOrdinary = timetabledata;
        return timetabledata;
    }

*/




/*
    static boolean access$1802(TimeTableActivity timetableactivity, boolean flag)
    {
        timetableactivity.isError = flag;
        return flag;
    }

*/



/*
    static int access$1902(TimeTableActivity timetableactivity, int i)
    {
        timetableactivity.beforeKind = i;
        return i;
    }

*/











/*
    static Map access$2802(TimeTableActivity timetableactivity, Map map)
    {
        timetableactivity.adResponses = map;
        return map;
    }

*/










/*
    static int access$902(TimeTableActivity timetableactivity, int i)
    {
        timetableactivity.displayKind = i;
        return i;
    }

*/
}
