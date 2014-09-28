// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.viewparts;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.text.format.Time;
import android.util.SparseArray;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.timer.api.data.TimeTableItemData;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;

public class CountdownTimelineView extends FrameLayout
    implements android.view.View.OnClickListener, android.view.ViewTreeObserver.OnGlobalLayoutListener
{
    public static interface CountdownTimelineListener
    {

        public abstract void onClickDia(int i);

        public abstract void onFinishShow(int i);
    }


    private final int DURATION = 10;
    private final int MARGIN = 2;
    private final int PADDING = 3;
    private int buttonW;
    private SparseArray diaButtons;
    private int displayW;
    private int endTime;
    private int headerH;
    private FrameLayout headerView;
    private int lineW;
    private CountdownTimelineListener listener;
    private int minuteW;
    private View nowLineView;
    private int nowLineW;
    private ImageView nowMarkView;
    private int nowMarkW;
    private Bundle objNowTimetable;
    private Resources res;
    private int staLabelTime;
    private int startTime;
    private View targetButton;
    private TimeTableItemData targetTime;
    private int totalH;
    private int totalW;

    public CountdownTimelineView(Context context)
    {
        super(context);
        res = context.getResources();
        headerH = res.getDimensionPixelSize(0x7f0b0020);
        buttonW = res.getDimensionPixelSize(0x7f0b001f);
        minuteW = res.getDimensionPixelSize(0x7f0b0022);
        lineW = res.getDimensionPixelSize(0x7f0b0021);
        nowMarkW = res.getDimensionPixelSize(0x7f0b0024);
        nowLineW = res.getDimensionPixelSize(0x7f0b0023);
        if (lineW % 2 != buttonW % 2)
        {
            buttonW = 1 + buttonW;
        }
        Display display = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        if (android.os.Build.VERSION.SDK_INT < 13)
        {
            displayW = getDisplayWidth(display);
        } else
        {
            displayW = getDisplaySizeWidth(display);
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    private int getDiaButtonResource(String s)
    {
        if (s.equals("-1"))
        {
            return 0x7f02011d;
        }
        if (s.equals("1"))
        {
            return 0x7f020117;
        }
        if (s.equals("2"))
        {
            return 0x7f020118;
        }
        if (s.equals("3"))
        {
            return 0x7f020119;
        }
        return !s.equals("4") ? 0x7f02011b : 0x7f02011a;
    }

    private int getDisplaySizeWidth(Display display)
    {
        Point point = new Point();
        display.getSize(point);
        return point.x;
    }

    private int getDisplayWidth(Display display)
    {
        return display.getWidth();
    }

    private int getNowTimePosition(int i)
    {
        int j = i / 60;
        int k = i % 60;
        return -(totalW / 2) + (j - startTime) * minuteW + (k * minuteW) / 60;
    }

    private boolean isInRange(int i)
    {
        return i >= -(totalW / 2) - nowMarkW / 2 && i <= totalW / 2 + nowMarkW / 2;
    }

    private void removeGlobalOnLayoutListener(ViewTreeObserver viewtreeobserver, android.view.ViewTreeObserver.OnGlobalLayoutListener ongloballayoutlistener)
    {
        viewtreeobserver.removeGlobalOnLayoutListener(ongloballayoutlistener);
    }

    private void removeOnGlobalLayoutListener(ViewTreeObserver viewtreeobserver, android.view.ViewTreeObserver.OnGlobalLayoutListener ongloballayoutlistener)
    {
        viewtreeobserver.removeOnGlobalLayoutListener(ongloballayoutlistener);
    }

    private void setView()
    {
        if (getChildCount() == 1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int l1;
        int i2;
        ImageView imageview1;
        int j2;
        ImageView imageview2;
        ImageView imageview3;
        int l2;
        int i3;
        int j5;
        int i6;
        Context context = getContext();
        int i = -(totalW / 2) + (staLabelTime - startTime) * minuteW;
        int j = 10 * minuteW;
        int k = staLabelTime;
        do
        {
            int l = totalW;
            if (i >= l)
            {
                break;
            }
            TextView textview = new TextView(context);
            textview.setTextAppearance(context, 0x7f0e00bd);
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(k / 60);
            aobj[1] = Integer.valueOf(k % 60);
            textview.setText(String.format("%02d:%02d", aobj));
            android.widget.FrameLayout.LayoutParams layoutparams = new android.widget.FrameLayout.LayoutParams(-2, -2);
            layoutparams.gravity = 17;
            layoutparams.leftMargin = i;
            layoutparams.topMargin = 0;
            headerView.addView(textview, layoutparams);
            View view = new View(context);
            view.setBackgroundColor(res.getColor(0x7f090030));
            android.widget.FrameLayout.LayoutParams layoutparams1 = new android.widget.FrameLayout.LayoutParams(lineW, totalH - headerH);
            layoutparams1.gravity = 49;
            layoutparams1.leftMargin = i;
            layoutparams1.topMargin = headerH;
            addView(view, layoutparams1);
            k += 10;
            i += j;
        } while (true);
        int i1 = getNowTimePosition(CountdownUtil.getNowTimeSec());
        boolean flag = isInRange(i1);
        ImageView imageview = new ImageView(context);
        nowMarkView = imageview;
        nowMarkView.setImageResource(0x7f020067);
        android.widget.FrameLayout.LayoutParams layoutparams2 = new android.widget.FrameLayout.LayoutParams(nowMarkW, -2);
        layoutparams2.gravity = 49;
        int j1;
        View view1;
        android.widget.FrameLayout.LayoutParams layoutparams3;
        int k1;
        TimeTableItemData timetableitemdata;
        String s;
        int k2;
        int j3;
        int l3;
        android.widget.FrameLayout.LayoutParams layoutparams4;
        android.widget.FrameLayout.LayoutParams layoutparams5;
        android.widget.FrameLayout.LayoutParams layoutparams6;
        TimeTableItemData timetableitemdata1;
        int i5;
        String s1;
        int k5;
        TimeTableItemData timetableitemdata2;
        int l5;
        String s2;
        if (!flag)
        {
            j1 = 0;
        } else
        {
            j1 = i1;
        }
        layoutparams2.leftMargin = j1;
        layoutparams2.topMargin = 0;
        view1 = new View(context);
        nowLineView = view1;
        nowLineView.setBackgroundColor(res.getColor(0x7f090031));
        layoutparams3 = new android.widget.FrameLayout.LayoutParams(nowLineW, totalH - headerH);
        layoutparams3.gravity = 49;
        if (!flag)
        {
            i1 = 0;
        }
        layoutparams3.leftMargin = i1;
        layoutparams3.topMargin = headerH;
        if (!flag)
        {
            nowMarkView.setVisibility(8);
            nowLineView.setVisibility(8);
        }
        addView(nowMarkView, layoutparams2);
        addView(nowLineView, layoutparams3);
        if (objNowTimetable == null) goto _L1; else goto _L3
_L3:
        k1 = objNowTimetable.size();
        l1 = 0;
_L12:
        if (l1 >= k1)
        {
            continue; /* Loop/switch isn't completed */
        }
        timetableitemdata = (TimeTableItemData)objNowTimetable.get(Integer.toString(l1));
        i2 = timetableitemdata.getTime();
        s = timetableitemdata.getTraintype();
        imageview1 = new ImageView(context);
        imageview1.setImageResource(getDiaButtonResource(s));
        imageview1.setTag(Integer.valueOf(timetableitemdata.getIndex()));
        imageview1.setOnClickListener(this);
        diaButtons.put(i2, imageview1);
_L15:
        j2 = l1 + 1;
          goto _L4
_L14:
        k2 = l1 + 1;
        imageview2 = null;
        imageview3 = null;
        l2 = 0;
        i3 = 0;
        if (k2 >= k1) goto _L6; else goto _L5
_L5:
        timetableitemdata1 = (TimeTableItemData)objNowTimetable.get(Integer.toString(k2));
        l2 = timetableitemdata1.getTime();
        i5 = l2 - i2;
        imageview2 = null;
        imageview3 = null;
        i3 = 0;
        if (i5 > 3) goto _L6; else goto _L7
_L7:
        l1++;
        s1 = ((TimeTableItemData)objNowTimetable.get(Integer.toString(l1))).getTraintype();
        imageview2 = new ImageView(context);
        imageview2.setImageResource(getDiaButtonResource(s1));
        imageview2.setTag(Integer.valueOf(timetableitemdata1.getIndex()));
        imageview2.setOnClickListener(this);
        diaButtons.put(l2, imageview2);
_L18:
        j5 = l1 + 1;
          goto _L8
_L17:
        k5 = l1 + 1;
        imageview3 = null;
        i3 = 0;
        if (k5 >= k1) goto _L6; else goto _L9
_L9:
        timetableitemdata2 = (TimeTableItemData)objNowTimetable.get(Integer.toString(k5));
        i3 = timetableitemdata2.getTime();
        l5 = i3 - l2;
        imageview3 = null;
        if (l5 > 3) goto _L6; else goto _L10
_L10:
        l1++;
        s2 = ((TimeTableItemData)objNowTimetable.get(Integer.toString(l1))).getTraintype();
        imageview3 = new ImageView(context);
        imageview3.setImageResource(getDiaButtonResource(s2));
        imageview3.setTag(Integer.valueOf(timetableitemdata2.getIndex()));
        imageview3.setOnClickListener(this);
        diaButtons.put(i3, imageview3);
_L20:
        i6 = l1 + 1;
          goto _L11
_L6:
        j3 = totalH - headerH;
        int k3 = j3 / 2;
        l3 = j3 / 3;
        int i4 = j3 / 4;
        int j4 = -1;
        if (imageview3 != null)
        {
            j4 = k3;
            layoutparams6 = new android.widget.FrameLayout.LayoutParams(buttonW, buttonW);
            layoutparams6.gravity = 49;
            layoutparams6.leftMargin = -(totalW / 2) + (i3 - startTime) * minuteW;
            layoutparams6.topMargin = (i4 + (k3 + headerH)) - buttonW / 2;
            addView(imageview3, layoutparams6);
        }
        int k4;
        if (imageview2 != null)
        {
            int l4;
            if (j4 == -1)
            {
                l4 = k3 + l3 / 2;
                j4 = k3 - l3 / 2;
            } else
            {
                l4 = j4;
                j4 = k3 - i4;
            }
            layoutparams5 = new android.widget.FrameLayout.LayoutParams(buttonW, buttonW);
            layoutparams5.gravity = 49;
            layoutparams5.leftMargin = -(totalW / 2) + (l2 - startTime) * minuteW;
            layoutparams5.topMargin = (l4 + headerH) - buttonW / 2;
            addView(imageview2, layoutparams5);
        }
        if (j4 == -1)
        {
            k4 = k3;
        } else
        {
            k4 = j4;
        }
        layoutparams4 = new android.widget.FrameLayout.LayoutParams(buttonW, buttonW);
        layoutparams4.gravity = 49;
        layoutparams4.leftMargin = -(totalW / 2) + (i2 - startTime) * minuteW;
        layoutparams4.topMargin = (k4 + headerH) - buttonW / 2;
        addView(imageview1, layoutparams4);
        l1++;
          goto _L12
_L4:
        if (j2 >= k1 || ((TimeTableItemData)objNowTimetable.get(Integer.toString(j2))).getTime() != i2) goto _L14; else goto _L13
_L13:
        l1++;
        imageview1.setImageResource(0x7f02011c);
          goto _L15
_L8:
        if (j5 >= k1 || ((TimeTableItemData)objNowTimetable.get(Integer.toString(j5))).getTime() != l2) goto _L17; else goto _L16
_L16:
        l1++;
        imageview2.setImageResource(0x7f02011c);
          goto _L18
_L11:
        if (i6 >= k1 || ((TimeTableItemData)objNowTimetable.get(Integer.toString(i6))).getTime() != i3) goto _L6; else goto _L19
_L19:
        l1++;
        imageview3.setImageResource(0x7f02011c);
          goto _L20
        if (listener == null || targetTime == null) goto _L1; else goto _L21
_L21:
        listener.onFinishShow(targetTime.getIndex());
        return;
    }

    public void initView(Bundle bundle, TimeTableItemData timetableitemdata)
    {
        objNowTimetable = bundle;
        targetTime = timetableitemdata;
        TimeTableItemData timetableitemdata1;
        TimeTableItemData timetableitemdata2;
        if (bundle == null || bundle.size() < 1)
        {
            Time time = new Time("Asia/Tokyo");
            time.setToNow();
            timetableitemdata1 = new TimeTableItemData();
            if (time.hour <= 3)
            {
                timetableitemdata1.setHour(24 + time.hour);
            } else
            {
                timetableitemdata1.setHour(time.hour);
            }
            timetableitemdata1.setMinute(time.minute);
            timetableitemdata2 = timetableitemdata1;
        } else
        {
            timetableitemdata1 = (TimeTableItemData)bundle.get(Integer.toString(0));
            timetableitemdata2 = (TimeTableItemData)bundle.get(Integer.toString(-1 + bundle.size()));
        }
        startTime = timetableitemdata1.getTime();
        endTime = timetableitemdata2.getTime();
        if ((4 + (endTime - startTime)) * minuteW <= displayW)
        {
            startTime = -2 + startTime;
            staLabelTime = -10 + 10 * ((9 + startTime) / 10);
            totalW = displayW;
        } else
        {
            startTime = -3 + 10 * ((-2 + startTime) / 10);
            endTime = 3 + 10 * ((2 + (9 + endTime)) / 10);
            staLabelTime = 10 * ((9 + startTime) / 10);
            totalW = (endTime - startTime) * minuteW;
        }
        headerView = new FrameLayout(getContext());
        headerView.setBackgroundColor(res.getColor(0x7f090030));
        addView(headerView, totalW, headerH);
        if (diaButtons == null)
        {
            diaButtons = new SparseArray();
            return;
        } else
        {
            diaButtons.clear();
            return;
        }
    }

    public void onClick(View view)
    {
        Object obj;
        if (listener != null)
        {
            if ((obj = view.getTag()) instanceof Integer)
            {
                int i = ((Integer)obj).intValue();
                listener.onClickDia(i);
                return;
            }
        }
    }

    public void onGlobalLayout()
    {
        totalH = getMeasuredHeight();
        setView();
        try
        {
            if (android.os.Build.VERSION.SDK_INT < 16)
            {
                removeGlobalOnLayoutListener(getViewTreeObserver(), this);
                return;
            }
        }
        catch (Exception exception)
        {
            return;
        }
        removeOnGlobalLayoutListener(getViewTreeObserver(), this);
        return;
    }

    public void resetView(Bundle bundle, TimeTableItemData timetableitemdata)
    {
        removeAllViews();
        initView(bundle, timetableitemdata);
        setView();
    }

    public void setListener(CountdownTimelineListener countdowntimelinelistener)
    {
        listener = countdowntimelinelistener;
    }

    public int updateTarget(TimeTableItemData timetableitemdata)
    {
        if (diaButtons != null) goto _L2; else goto _L1
_L1:
        ImageView imageview;
        return -1;
_L2:
        if ((imageview = (ImageView)diaButtons.get(timetableitemdata.getTime())) == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (targetButton == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (targetButton.equals(imageview))
        {
            continue; /* Loop/switch isn't completed */
        }
        targetButton.setSelected(false);
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L3
_L3:
        targetButton = imageview;
        targetButton.setSelected(true);
        return ((-2 + timetableitemdata.getTime()) - startTime) * minuteW;
    }

    public void updateTime(int i)
    {
        if (nowMarkView != null && nowLineView != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int j;
        int k;
        android.widget.FrameLayout.LayoutParams layoutparams;
        if (i < 14400)
        {
            j = i + 0x15180;
        } else
        {
            j = i;
        }
        k = getNowTimePosition(j);
        if (!isInRange(k))
        {
            continue; /* Loop/switch isn't completed */
        }
        layoutparams = (android.widget.FrameLayout.LayoutParams)nowMarkView.getLayoutParams();
        if (layoutparams.leftMargin != k)
        {
            layoutparams.leftMargin = k;
            nowMarkView.setLayoutParams(layoutparams);
            android.widget.FrameLayout.LayoutParams layoutparams1 = (android.widget.FrameLayout.LayoutParams)nowLineView.getLayoutParams();
            layoutparams1.leftMargin = k;
            nowLineView.setLayoutParams(layoutparams1);
        }
        if (nowMarkView.getVisibility() != 8) goto _L1; else goto _L3
_L3:
        nowMarkView.setVisibility(0);
        nowLineView.setVisibility(0);
        return;
        if (nowMarkView.getVisibility() != 0) goto _L1; else goto _L4
_L4:
        nowMarkView.setVisibility(8);
        nowLineView.setVisibility(8);
        return;
    }
}
