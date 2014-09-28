// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.common;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.timer.api.data.TimeTableItemData;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;
import jp.co.yahoo.android.apps.transit.timer.viewparts.CountdownDragView;
import jp.co.yahoo.android.apps.transit.timer.viewparts.CountdownPanelView;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.common:
//            CountdownManager

public class CountdownPanelManager extends CountdownManager
{
    private class CountdownAdapter extends PagerAdapter
    {

        final CountdownPanelManager this$0;

        public void destroyItem(ViewGroup viewgroup, int i, Object obj)
        {
            ((ViewPager)viewgroup).removeView((View)obj);
        }

        public int getCount()
        {
            return nMaxCount;
        }

        public Object instantiateItem(ViewGroup viewgroup, int i)
        {
            TimeTableItemData timetableitemdata;
            if (objNowTimetable == null)
            {
                timetableitemdata = null;
            } else
            {
                timetableitemdata = (TimeTableItemData)objNowTimetable.getSerializable(Integer.toString(i));
            }
            if (timetableitemdata == null)
            {
                View view = new View(context);
                view.setBackgroundColor(context.getResources().getColor(0x7f090026));
                viewgroup.addView(view);
                return view;
            }
            CountdownPanelView countdownpanelview = new CountdownPanelView(context, i, bIsSkin);
            countdownpanelview.setVisibleWeek(bWeekVisible);
            countdownpanelview.setWeek(nWeek);
            countdownpanelview.setTimetable(timetableitemdata);
            int j = 60 * (60 * timetableitemdata.getHour()) + 60 * timetableitemdata.getMinute();
            int k = CountdownUtil.getNowTimeSec();
            if (bTommorow && k > j)
            {
                k -= 0x15180;
            }
            countdownpanelview.update(k);
            if (nAlerm >= 0 && nAlerm == 60 * (60 * timetableitemdata.getHour()) + 60 * timetableitemdata.getMinute())
            {
                countdownpanelview.setAlertLabel(true);
            }
            countdownpanelview.getWeekBtn().setOnClickListener(new android.view.View.OnClickListener() {

                final CountdownAdapter this$1;

                public void onClick(View view)
                {
                    if (listener != null)
                    {
                        listener.changeWeek(nWeek);
                    }
                }

            
            {
                this$1 = CountdownAdapter.this;
                super();
            }
            });
            viewgroup.addView(countdownpanelview);
            return countdownpanelview;
        }

        public boolean isViewFromObject(View view, Object obj)
        {
            return view.equals(obj);
        }

        private CountdownAdapter()
        {
            this$0 = CountdownPanelManager.this;
            super();
        }

    }


    private boolean bIsSkin;
    private boolean bWeekVisible;
    private CountdownDragView dragView;
    private ViewPager mViewPager;

    public CountdownPanelManager(Context context, ViewPager viewpager, CountdownManager.CountdownListener countdownlistener, boolean flag)
    {
        super(context);
        mViewPager = null;
        bWeekVisible = true;
        bIsSkin = false;
        mViewPager = viewpager;
        listener = countdownlistener;
        bIsSkin = flag;
    }

    public CountdownPanelManager(Context context, ViewPager viewpager, boolean flag)
    {
        super(context);
        mViewPager = null;
        bWeekVisible = true;
        bIsSkin = false;
        mViewPager = viewpager;
        bIsSkin = flag;
    }

    public void changeTarget(int i)
    {
        mViewPager.setCurrentItem(i, true);
    }

    public CountdownPanelView getNowPanel()
    {
        CountdownPanelView countdownpanelview = null;
        int i = 0;
        do
        {
label0:
            {
                if (i < mViewPager.getChildCount())
                {
                    View view = mViewPager.getChildAt(i);
                    if (view == null || !(view instanceof CountdownPanelView))
                    {
                        break label0;
                    }
                    countdownpanelview = (CountdownPanelView)view;
                    if (countdownpanelview.getIndex() != nCurrentIndex)
                    {
                        break label0;
                    }
                }
                return countdownpanelview;
            }
            i++;
        } while (true);
    }

    public void setCountDown(Bundle bundle)
    {
        if (mViewPager.getChildCount() > 0)
        {
            mViewPager.removeAllViews();
            mViewPager.setAdapter(null);
            mViewPager.setOnPageChangeListener(null);
        }
        super.setCountDown(bundle);
        CountdownAdapter countdownadapter = new CountdownAdapter();
        int i = nCurrentIndex;
        mViewPager.setAdapter(countdownadapter);
        mViewPager.setCurrentItem(i, true);
        mViewPager.setOnPageChangeListener(new android.support.v4.view.ViewPager.SimpleOnPageChangeListener() {

            final CountdownPanelManager this$0;

            public void onPageSelected(int j)
            {
                nCurrentIndex = j;
                objCurrentTime = (TimeTableItemData)objNowTimetable.getSerializable(Integer.toString(nCurrentIndex));
                if (listener != null)
                {
                    listener.updateTarget(objCurrentTime);
                }
            }

            
            {
                this$0 = CountdownPanelManager.this;
                super();
            }
        });
    }

    public void setSkinDragView(View view, String s)
    {
        dragView = new CountdownDragView(context, view, s);
    }

    public void setWeekVisible(boolean flag)
    {
        bWeekVisible = flag;
    }

    public void updateSkinDragViewItem()
    {
        TimeTableItemData timetableitemdata = getTargetTime();
        dragView.setTimetable(timetableitemdata);
        int i = 60 * (60 * timetableitemdata.getHour()) + 60 * timetableitemdata.getMinute();
        int j = CountdownUtil.getNowTimeSec();
        if (bTommorow && j > i)
        {
            j -= 0x15180;
        }
        dragView.update(j);
    }

    public void updateTime(int i)
    {
        for (int j = 0; j < mViewPager.getChildCount(); j++)
        {
            View view = mViewPager.getChildAt(j);
            if (view != null && (view instanceof CountdownPanelView))
            {
                ((CountdownPanelView)view).update(i);
            }
        }

        if (bIsSkin && dragView != null)
        {
            dragView.update(i);
        }
    }


}
