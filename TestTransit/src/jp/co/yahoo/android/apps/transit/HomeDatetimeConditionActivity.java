// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity, Transit

public class HomeDatetimeConditionActivity extends TransitBaseActivity
{

    public static final String FORMAT_DATE_JP = "%d\u5E74%d\u6708%d\u65E5(%s)";
    private ConditionData conditions;
    private android.widget.DatePicker.OnDateChangedListener dateChangedListener;
    private TextView dateDisplay;
    private DatePicker datePicker;
    private Button first;
    private Button goal;
    boolean is24HourView;
    private Button last;
    private Intent returnIntent;
    private Button start;
    private android.widget.TimePicker.OnTimeChangedListener timeChangedListener;
    private TextView timeDisplay;
    private TimePicker timePicker;

    public HomeDatetimeConditionActivity()
    {
        is24HourView = true;
        dateChangedListener = new android.widget.DatePicker.OnDateChangedListener() {

            final HomeDatetimeConditionActivity this$0;

            public void onDateChanged(DatePicker datepicker, int i, int j, int k)
            {
                updateDateDisplay(i, j + 1, k);
                setDatetimeToConditions();
                getButtonStatus();
            }

            
            {
                this$0 = HomeDatetimeConditionActivity.this;
                super();
            }
        };
        timeChangedListener = new android.widget.TimePicker.OnTimeChangedListener() {

            final HomeDatetimeConditionActivity this$0;

            public void onTimeChanged(TimePicker timepicker, int i, int j)
            {
                updateTimeDisplay(i, j);
                setDatetimeToConditions();
                getButtonStatus();
            }

            
            {
                this$0 = HomeDatetimeConditionActivity.this;
                super();
            }
        };
    }

    private void getButtonStatus()
    {
        if (start.isSelected())
        {
            conditions.type = getResources().getInteger(0x7f0c0070);
        } else
        {
            if (goal.isSelected())
            {
                conditions.type = getResources().getInteger(0x7f0c006e);
                return;
            }
            if (last.isSelected())
            {
                conditions.type = getResources().getInteger(0x7f0c006f);
                return;
            }
            if (first.isSelected())
            {
                conditions.type = getResources().getInteger(0x7f0c006d);
                return;
            }
        }
    }

    private static String pad(int i)
    {
        if (i >= 10)
        {
            return String.valueOf(i);
        } else
        {
            return (new StringBuilder()).append("0").append(String.valueOf(i)).toString();
        }
    }

    private void setDatetimeToConditions()
    {
        conditions.year = datePicker.getYear();
        conditions.month = 1 + datePicker.getMonth();
        conditions.day = datePicker.getDayOfMonth();
        conditions.hour = timePicker.getCurrentHour().intValue();
        conditions.minite = timePicker.getCurrentMinute().intValue();
    }

    private void updateDateDisplay(int i, int j, int k)
    {
        String s = Transit.getDayOfWeekJP(i, j, k);
        TextView textview = dateDisplay;
        Object aobj[] = new Object[4];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Integer.valueOf(j);
        aobj[2] = Integer.valueOf(k);
        aobj[3] = s;
        textview.setText(String.format("%d\u5E74%d\u6708%d\u65E5(%s)", aobj));
    }

    private void updateTimeDisplay(int i, int j)
    {
        timeDisplay.setText((new StringBuilder()).append(pad(i)).append(":").append(pad(j)));
    }

    public void minus5min(View view)
    {
        touchTapRD(getString(0x7f0d0404));
        int i = timePicker.getCurrentMinute().intValue();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, datePicker.getYear());
        calendar.set(2, datePicker.getMonth());
        calendar.set(5, datePicker.getDayOfMonth());
        calendar.set(11, timePicker.getCurrentHour().intValue());
        calendar.set(12, i - 5);
        int j = calendar.get(1);
        int k = calendar.get(2);
        int l = calendar.get(5);
        int i1 = calendar.get(11);
        int j1 = calendar.get(12);
        datePicker.init(j, k, l, dateChangedListener);
        timePicker.setCurrentHour(Integer.valueOf(i1));
        timePicker.setCurrentMinute(Integer.valueOf(j1));
        timePicker.setIs24HourView(Boolean.valueOf(is24HourView));
        timePicker.setOnTimeChangedListener(timeChangedListener);
        updateDateDisplay(j, k + 1, l);
        updateTimeDisplay(i1, j1);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03001e);
        setTitle(getString(0x7f0d00c0));
        returnIntent = new Intent();
        setResult(0, returnIntent);
        conditions = (ConditionData)getIntent().getSerializableExtra(getString(0x7f0d022c));
        dateDisplay = (TextView)findViewById(0x7f0a009e);
        timeDisplay = (TextView)findViewById(0x7f0a009f);
        updateDateDisplay(conditions.year, conditions.month, conditions.day);
        updateTimeDisplay(conditions.hour, conditions.minite);
        datePicker = (DatePicker)findViewById(0x7f0a00a0);
        datePicker.init(conditions.year, -1 + conditions.month, conditions.day, dateChangedListener);
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            datePicker.setCalendarViewShown(false);
        }
        int i = Resources.getSystem().getIdentifier("day", "id", "android");
        int j = Resources.getSystem().getIdentifier("month", "id", "android");
        int k = Resources.getSystem().getIdentifier("year", "id", "android");
        LinearLayout linearlayout;
        LinearLayout linearlayout1;
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            datePicker.findViewById(k).setVisibility(8);
            float f2 = TypedValue.applyDimension(1, 60F, getResources().getDisplayMetrics());
            float f3 = TypedValue.applyDimension(1, 55F, getResources().getDisplayMetrics());
            float f4 = TypedValue.applyDimension(1, 8F, getResources().getDisplayMetrics());
            android.widget.LinearLayout.LayoutParams layoutparams4 = (android.widget.LinearLayout.LayoutParams)datePicker.findViewById(j).getLayoutParams();
            android.widget.LinearLayout.LayoutParams layoutparams5 = (android.widget.LinearLayout.LayoutParams)datePicker.findViewById(i).getLayoutParams();
            if ((float)layoutparams4.width > f2)
            {
                layoutparams4.width = (int)f2;
            }
            int l;
            int i1;
            android.widget.LinearLayout.LayoutParams layoutparams;
            android.widget.LinearLayout.LayoutParams layoutparams1;
            Button button;
            android.view.View.OnFocusChangeListener onfocuschangelistener;
            float f;
            float f1;
            if ((float)layoutparams4.rightMargin > f4)
            {
                layoutparams4.setMargins((int)f4, 0, (int)f4, 0);
            } else
            {
                layoutparams4.setMargins(layoutparams4.rightMargin, 0, layoutparams4.rightMargin, 0);
            }
            if ((float)layoutparams5.width > f3)
            {
                layoutparams5.width = (int)f3;
            }
            if ((float)layoutparams5.leftMargin > f4)
            {
                layoutparams5.setMargins((int)f4, 0, (int)f4, 0);
            } else
            {
                layoutparams5.setMargins(layoutparams5.leftMargin, 0, layoutparams5.leftMargin, 0);
            }
            datePicker.findViewById(j).setLayoutParams(layoutparams4);
            datePicker.findViewById(i).setLayoutParams(layoutparams5);
        }
        timePicker = (TimePicker)findViewById(0x7f0a00a1);
        timePicker.setIs24HourView(Boolean.valueOf(is24HourView));
        timePicker.setCurrentHour(Integer.valueOf(conditions.hour));
        timePicker.setCurrentMinute(Integer.valueOf(conditions.minite));
        timePicker.setOnTimeChangedListener(timeChangedListener);
        l = Resources.getSystem().getIdentifier("hour", "id", "android");
        i1 = Resources.getSystem().getIdentifier("minute", "id", "android");
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            f = TypedValue.applyDimension(1, 55F, getResources().getDisplayMetrics());
            f1 = TypedValue.applyDimension(1, 8F, getResources().getDisplayMetrics());
            android.widget.LinearLayout.LayoutParams layoutparams2 = (android.widget.LinearLayout.LayoutParams)timePicker.findViewById(l).getLayoutParams();
            android.widget.LinearLayout.LayoutParams layoutparams3 = (android.widget.LinearLayout.LayoutParams)timePicker.findViewById(i1).getLayoutParams();
            if ((float)layoutparams2.width > f)
            {
                layoutparams2.width = (int)f;
            }
            if ((float)layoutparams2.rightMargin > f1)
            {
                layoutparams2.setMargins(0, 0, (int)f1, 0);
            } else
            {
                layoutparams2.setMargins(0, 0, layoutparams2.rightMargin, 0);
            }
            if ((float)layoutparams3.width > f)
            {
                layoutparams3.width = (int)f;
            }
            if ((float)layoutparams3.leftMargin > f1)
            {
                layoutparams3.setMargins((int)f1, 0, 0, 0);
            } else
            {
                layoutparams3.setMargins(layoutparams3.leftMargin, 0, 0, 0);
            }
            timePicker.findViewById(l).setLayoutParams(layoutparams2);
            timePicker.findViewById(i1).setLayoutParams(layoutparams3);
        }
        start = (Button)findViewById(0x7f0a00a2);
        goal = (Button)findViewById(0x7f0a00a3);
        first = (Button)findViewById(0x7f0a00a4);
        last = (Button)findViewById(0x7f0a00a5);
        setButton(conditions.type);
        Exception exception1;
        String as[];
        try
        {
            button = (Button)findViewById(0x7f0a009a);
            onfocuschangelistener = new android.view.View.OnFocusChangeListener() {

                final HomeDatetimeConditionActivity this$0;

                public void onFocusChange(View view, boolean flag)
                {
                    InputMethodManager inputmethodmanager = (InputMethodManager)getSystemService("input_method");
                    if (inputmethodmanager != null)
                    {
                        inputmethodmanager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }

            
            {
                this$0 = HomeDatetimeConditionActivity.this;
                super();
            }
            };
            button.setOnFocusChangeListener(onfocuschangelistener);
        }
        catch (Exception exception) { }
        linearlayout = (LinearLayout)datePicker.getChildAt(0);
        linearlayout1 = (LinearLayout)timePicker.getChildAt(0);
        layoutparams = (android.widget.LinearLayout.LayoutParams)linearlayout.getChildAt(1).getLayoutParams();
        layoutparams1 = (android.widget.LinearLayout.LayoutParams)linearlayout.getChildAt(2).getLayoutParams();
        if (20 < linearlayout1.getChildAt(0).getLayoutParams().width && 20 < linearlayout1.getChildAt(1).getLayoutParams().width)
        {
            linearlayout1.getChildAt(0).setLayoutParams(layoutparams);
            linearlayout1.getChildAt(1).setLayoutParams(layoutparams1);
        }
        if (!Build.MODEL.equals("SO-03C") && !Build.MODEL.equals("SO-03D")) goto _L2; else goto _L1
_L1:
        linearlayout.setBackgroundColor(0xff000000);
        linearlayout1.setBackgroundColor(0xff000000);
_L4:
        dispAd(this, "2080078819", "Z");
        return;
_L2:
        try
        {
            if (Build.MODEL.equals("SO-01B") || Build.MODEL.equals("SO-01C") || Build.MODEL.equals("SO-02C") || Build.MODEL.equals("SO-02D") || Build.MODEL.equals("IS11S") || Build.MODEL.equals("IS12S"))
            {
                as = android.os.Build.VERSION.RELEASE.split("\\.");
                if (2 < Integer.valueOf(as[0]).intValue() || 2 == as.length && "2".equals(as[0]) && 3 < Integer.valueOf(as[1].substring(0, 1)).intValue() || 3 <= as.length && "2".equals(as[0]) && "3".equals(as[1]) && 3 < Integer.valueOf(as[2].substring(0, 1)).intValue())
                {
                    linearlayout.setBackgroundColor(0xff000000);
                    linearlayout1.setBackgroundColor(0xff000000);
                }
            }
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception1) { }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void onRestoreInstanceState(Bundle bundle)
    {
        super.onRestoreInstanceState(bundle);
        int i = bundle.getInt(getString(0x7f0d019f));
        int j = bundle.getInt(getString(0x7f0d018b));
        int k = bundle.getInt(getString(0x7f0d0184));
        int l = bundle.getInt(getString(0x7f0d0189));
        int i1 = bundle.getInt(getString(0x7f0d018a));
        datePicker.init(i, j, k, dateChangedListener);
        timePicker.setCurrentHour(Integer.valueOf(l));
        timePicker.setCurrentMinute(Integer.valueOf(i1));
        timePicker.setIs24HourView(Boolean.valueOf(is24HourView));
        timePicker.setOnTimeChangedListener(timeChangedListener);
        updateDateDisplay(i, j, k);
        updateTimeDisplay(l, i1);
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putInt(getString(0x7f0d019f), datePicker.getYear());
        bundle.putInt(getString(0x7f0d018b), datePicker.getMonth());
        bundle.putInt(getString(0x7f0d0184), datePicker.getDayOfMonth());
        bundle.putInt(getString(0x7f0d0189), timePicker.getCurrentHour().intValue());
        bundle.putInt(getString(0x7f0d018a), timePicker.getCurrentMinute().intValue());
    }

    public void pulus5min(View view)
    {
        touchTapRD(getString(0x7f0d03fd));
        int i = timePicker.getCurrentMinute().intValue();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, datePicker.getYear());
        calendar.set(2, datePicker.getMonth());
        calendar.set(5, datePicker.getDayOfMonth());
        calendar.set(11, timePicker.getCurrentHour().intValue());
        calendar.set(12, i + 5);
        int j = calendar.get(1);
        int k = calendar.get(2);
        int l = calendar.get(5);
        int i1 = calendar.get(11);
        int j1 = calendar.get(12);
        datePicker.init(j, k, l, dateChangedListener);
        timePicker.setCurrentHour(Integer.valueOf(i1));
        timePicker.setCurrentMinute(Integer.valueOf(j1));
        timePicker.setIs24HourView(Boolean.valueOf(is24HourView));
        timePicker.setOnTimeChangedListener(timeChangedListener);
        updateDateDisplay(j, k + 1, l);
        updateTimeDisplay(i1, j1);
    }

    public void setButton(int i)
    {
        if (i == getResources().getInteger(0x7f0c006e))
        {
            controlButton(start, false);
            controlButton(goal, true);
            controlButton(last, false);
            controlButton(first, false);
            timePicker.setEnabled(true);
            return;
        }
        if (i == getResources().getInteger(0x7f0c006f))
        {
            controlButton(start, false);
            controlButton(goal, false);
            controlButton(last, true);
            controlButton(first, false);
            timePicker.setEnabled(false);
            return;
        }
        if (i == getResources().getInteger(0x7f0c006d))
        {
            controlButton(start, false);
            controlButton(goal, false);
            controlButton(last, false);
            controlButton(first, true);
            timePicker.setEnabled(false);
            return;
        } else
        {
            controlButton(start, true);
            controlButton(goal, false);
            controlButton(last, false);
            controlButton(first, false);
            timePicker.setEnabled(true);
            return;
        }
    }

    public void setDatetimeAverage(View view)
    {
        setDatetimeToConditions();
        conditions.type = getResources().getInteger(0x7f0c006a);
    }

    public void setDatetimeCurrent(View view)
    {
        touchTapRD(getString(0x7f0d03fe));
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int j = calendar.get(2);
        int k = calendar.get(5);
        int l = calendar.get(11);
        int i1 = calendar.get(12);
        datePicker.init(i, j, k, dateChangedListener);
        timePicker.setCurrentHour(Integer.valueOf(l));
        timePicker.setCurrentMinute(Integer.valueOf(i1));
        timePicker.setIs24HourView(Boolean.valueOf(is24HourView));
        timePicker.setOnTimeChangedListener(timeChangedListener);
        updateDateDisplay(i, j + 1, k);
        updateTimeDisplay(l, i1);
        conditions.type = getResources().getInteger(0x7f0c006b);
        setButton(getResources().getInteger(0x7f0c0070));
    }

    public void setDatetimeFinish(View view)
    {
        datePicker.clearFocus();
        timePicker.clearFocus();
        returnIntent.putExtra(getString(0x7f0d022c), conditions);
        setResult(-1, returnIntent);
        finish();
    }

    public void setDatetimeFirst(View view)
    {
        touchTapRD(getString(0x7f0d03e2));
        setDatetimeToConditions();
        conditions.type = getResources().getInteger(0x7f0c006d);
        setButton(getResources().getInteger(0x7f0c006d));
    }

    public void setDatetimeGoal(View view)
    {
        touchTapRD(getString(0x7f0d03c8));
        setDatetimeToConditions();
        conditions.type = getResources().getInteger(0x7f0c006e);
        setButton(getResources().getInteger(0x7f0c006e));
    }

    public void setDatetimeLast(View view)
    {
        touchTapRD(getString(0x7f0d03f0));
        setDatetimeToConditions();
        conditions.type = getResources().getInteger(0x7f0c006f);
        setButton(getResources().getInteger(0x7f0c006f));
    }

    public void setDatetimeStart(View view)
    {
        touchTapRD(getString(0x7f0d03d6));
        setDatetimeToConditions();
        conditions.type = getResources().getInteger(0x7f0c0070);
        setButton(getResources().getInteger(0x7f0c0070));
    }




}
