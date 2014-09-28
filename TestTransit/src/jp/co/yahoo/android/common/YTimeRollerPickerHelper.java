// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;

// Referenced classes of package jp.co.yahoo.android.common:
//            YHourRollerPicker, YMinuteRollerPicker

public class YTimeRollerPickerHelper
{

    private static final int INVALID_VALUE = -1;
    private static final int PULLBACK_ITEM_POSITION_FROM_BOTTOM_FOR_HOUR = 24;
    private static final int PULLBACK_ITEM_POSITION_FROM_BOTTOM_FOR_MINUTE = 60;
    private static final int PULLBACK_ITEM_POSITION_FROM_TOP_FOR_HOUR = 24;
    private static final int PULLBACK_ITEM_POSITION_FROM_TOP_FOR_MINUTE = 60;
    private static final int UPDATE_ITEM_POSITION_FROM_BOTTOM_FOR_HOUR = 5;
    private static final int UPDATE_ITEM_POSITION_FROM_BOTTOM_FOR_MINUTE = 5;
    private static final int UPDATE_ITEM_POSITION_FROM_TOP_FOR_HOUR = 5;
    private static final int UPDATE_ITEM_POSITION_FROM_TOP_FOR_MINUTE = 5;
    private YHourRollerPicker mHourRoller;
    private YMinuteRollerPicker mMinuteRoller;
    private android.widget.TimePicker.OnTimeChangedListener mOnTimeChangedListener;

    public YTimeRollerPickerHelper(YHourRollerPicker yhourrollerpicker, YMinuteRollerPicker yminuterollerpicker)
    {
        mOnTimeChangedListener = null;
        mHourRoller = yhourrollerpicker;
        mMinuteRoller = yminuterollerpicker;
        if (mHourRoller != null)
        {
            mHourRoller.setUpdateAndPullbackPosition(5, 5, 24, 24);
            mHourRoller.setOnBaseTimeRollerPickerListener(new YBaseTimeRollerPicker.OnBaseTimeRollerPickerListener() {

                final YTimeRollerPickerHelper this$0;

                public void onScrollStateChanged()
                {
                    sendToOnTimeChangedListener();
                }

            
            {
                this$0 = YTimeRollerPickerHelper.this;
                super();
            }
            });
        }
        if (mMinuteRoller != null)
        {
            mMinuteRoller.setUpdateAndPullbackPosition(5, 5, 60, 60);
            mMinuteRoller.setOnBaseTimeRollerPickerListener(new YBaseTimeRollerPicker.OnBaseTimeRollerPickerListener() {

                final YTimeRollerPickerHelper this$0;

                public void onScrollStateChanged()
                {
                    sendToOnTimeChangedListener();
                }

            
            {
                this$0 = YTimeRollerPickerHelper.this;
                super();
            }
            });
        }
    }

    public int getCurrentHour()
    {
        if (mHourRoller == null)
        {
            return -1;
        } else
        {
            return mHourRoller.getCurrentHour();
        }
    }

    public int getCurrentMinute()
    {
        if (mMinuteRoller == null)
        {
            return -1;
        } else
        {
            return mMinuteRoller.getCurrentMinute();
        }
    }

    public void sendToOnTimeChangedListener()
    {
label0:
        {
            if (mOnTimeChangedListener != null)
            {
                if (mHourRoller != null)
                {
                    break label0;
                }
                if (mMinuteRoller != null)
                {
                    mOnTimeChangedListener.onTimeChanged(null, -1, mMinuteRoller.getCurrentMinute());
                }
            }
            return;
        }
        if (mMinuteRoller != null)
        {
            mOnTimeChangedListener.onTimeChanged(null, mHourRoller.getCurrentHour(), mMinuteRoller.getCurrentMinute());
            return;
        } else
        {
            mOnTimeChangedListener.onTimeChanged(null, mHourRoller.getCurrentHour(), -1);
            return;
        }
    }

    public void setCurrentHour(int i)
    {
        if (mHourRoller == null)
        {
            return;
        } else
        {
            mHourRoller.setCurrentHour(i);
            return;
        }
    }

    public void setCurrentMinute(int i)
    {
        if (mMinuteRoller == null)
        {
            return;
        } else
        {
            mMinuteRoller.setCurrentMinute(i);
            return;
        }
    }

    public void setHourArrayAdapter(Context context, int i, int j)
    {
        if (mHourRoller == null)
        {
            return;
        } else
        {
            mHourRoller.setArrayAdapter(context, i, j);
            return;
        }
    }

    public void setHourItemHeight(int i)
    {
        if (mHourRoller == null)
        {
            return;
        } else
        {
            mHourRoller.setItemHeight(i);
            return;
        }
    }

    public void setMinuteArrayAdapter(Context context, int i, int j)
    {
        if (mMinuteRoller == null)
        {
            return;
        } else
        {
            mMinuteRoller.setArrayAdapter(context, i, j);
            return;
        }
    }

    public void setMinuteItemHeight(int i)
    {
        if (mMinuteRoller == null)
        {
            return;
        } else
        {
            mMinuteRoller.setItemHeight(i);
            return;
        }
    }

    public void setOnTimeChangedListener(android.widget.TimePicker.OnTimeChangedListener ontimechangedlistener)
    {
        mOnTimeChangedListener = ontimechangedlistener;
    }
}
