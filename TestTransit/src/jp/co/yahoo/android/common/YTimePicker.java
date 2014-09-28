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
//            YTimeRollerPickerHelper, YHourRollerPicker, YMinuteRollerPicker

public class YTimePicker extends LinearLayout
{

    private final int ITEM_HEIGHT_DIP;
    private YHourRollerPicker mHourRollerPicker;
    private YMinuteRollerPicker mMinuteRollerPicker;
    private YTimeRollerPickerHelper mTimePickerHelper;

    public YTimePicker(Context context)
    {
        super(context);
        ITEM_HEIGHT_DIP = 36;
        init(context);
    }

    public YTimePicker(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        ITEM_HEIGHT_DIP = 36;
        init(context);
    }

    private int getPxFromDip(Context context, float f)
    {
        return (int)(f * context.getResources().getDisplayMetrics().density);
    }

    public int getCurrentHour()
    {
        return mTimePickerHelper.getCurrentHour();
    }

    public int getCurrentMinute()
    {
        return mTimePickerHelper.getCurrentMinute();
    }

    public void init(Context context)
    {
        ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(R.layout.common_time_picker, this, true);
        mHourRollerPicker = (YHourRollerPicker)findViewById(R.id.hour_roller);
        mMinuteRollerPicker = (YMinuteRollerPicker)findViewById(R.id.minute_roller);
        mTimePickerHelper = new YTimeRollerPickerHelper(mHourRollerPicker, mMinuteRollerPicker);
        mTimePickerHelper.setHourArrayAdapter(context, R.array.common_hour_picker_array, R.layout.common_roller_item);
        mTimePickerHelper.setHourItemHeight(getPxFromDip(context, 36F));
        mTimePickerHelper.setMinuteArrayAdapter(context, R.array.common_min_picker_array, R.layout.common_roller_item);
        mTimePickerHelper.setMinuteItemHeight(getPxFromDip(context, 36F));
    }

    public void setCurrentHour(int i)
    {
        mTimePickerHelper.setCurrentHour(i);
    }

    public void setCurrentMinute(int i)
    {
        mTimePickerHelper.setCurrentMinute(i);
    }

    public void setOnTimeChangedListener(android.widget.TimePicker.OnTimeChangedListener ontimechangedlistener)
    {
        mTimePickerHelper.setOnTimeChangedListener(ontimechangedlistener);
    }
}
