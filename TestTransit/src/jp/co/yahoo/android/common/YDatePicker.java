// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

// Referenced classes of package jp.co.yahoo.android.common:
//            YDateRollerPickerHelper, YYearRollerPicker, YMonthRollerPicker, YDayOfMonthRollerPicker

public class YDatePicker extends LinearLayout
{

    private final int ITEM_HEIGHT_DIP;
    private YDateRollerPickerHelper mDatePickerHelper;
    private YDayOfMonthRollerPicker mDayOfMonthRollerPicker;
    private YMonthRollerPicker mMonthRollerPicker;
    private YYearRollerPicker mYearRollerPicker;

    public YDatePicker(Context context)
    {
        super(context);
        ITEM_HEIGHT_DIP = 36;
        init(context);
    }

    public YDatePicker(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        ITEM_HEIGHT_DIP = 36;
        init(context);
    }

    private int getPxFromDip(Context context, float f)
    {
        return (int)(f * context.getResources().getDisplayMetrics().density);
    }

    public int getDayOfMonth()
    {
        return mDatePickerHelper.getDayOfMonth();
    }

    public int getMonth()
    {
        return mDatePickerHelper.getMonth();
    }

    public int getYear()
    {
        return mDatePickerHelper.getYear();
    }

    public void init(int i, int j, int k, android.widget.DatePicker.OnDateChangedListener ondatechangedlistener)
    {
        mDatePickerHelper.init(i, j, k, ondatechangedlistener);
    }

    public void init(Context context)
    {
        ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(R.layout.common_date_picker, this, true);
        mYearRollerPicker = (YYearRollerPicker)findViewById(R.id.year_roller);
        mMonthRollerPicker = (YMonthRollerPicker)findViewById(R.id.month_roller);
        mDayOfMonthRollerPicker = (YDayOfMonthRollerPicker)findViewById(R.id.day_of_month_roller);
        mDatePickerHelper = new YDateRollerPickerHelper(context, mYearRollerPicker, mMonthRollerPicker, mDayOfMonthRollerPicker);
        mDatePickerHelper.setYearArrayAdapter(R.layout.common_roller_item);
        mDatePickerHelper.setYearItemHeight(getPxFromDip(context, 36F));
        mDatePickerHelper.setMonthArrayAdapter(R.layout.common_roller_item);
        mDatePickerHelper.setMonthItemHeight(getPxFromDip(context, 36F));
        mDatePickerHelper.setDayOfMonthArrayAdapter(R.layout.common_roller_item);
        mDatePickerHelper.setDayOfMonthItemHeight(getPxFromDip(context, 36F));
    }

    public void setOnDateChangedListener(android.widget.DatePicker.OnDateChangedListener ondatechangedlistener)
    {
        mDatePickerHelper.setOnDateChangedListener(ondatechangedlistener);
    }

    public void updateDate(int i, int j, int k)
    {
        mDatePickerHelper.updateDate(i, j, k);
    }
}
