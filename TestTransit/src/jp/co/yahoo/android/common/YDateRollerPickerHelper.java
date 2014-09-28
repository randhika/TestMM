// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;

// Referenced classes of package jp.co.yahoo.android.common:
//            YTime, YYearRollerPicker, YMonthRollerPicker, YDayOfMonthRollerPicker

public class YDateRollerPickerHelper
{

    private static final int INVALID_VALUE = -1;
    private static final int PULLBACK_ITEM_POSITION_FROM_BOTTOM_FOR_MONTH = 12;
    private static final int PULLBACK_ITEM_POSITION_FROM_BOTTOM_FOR_YEAR = 67;
    private static final int PULLBACK_ITEM_POSITION_FROM_TOP_FOR_MONTH = 12;
    private static final int PULLBACK_ITEM_POSITION_FROM_TOP_FOR_YEAR = 67;
    private static final int UPDATE_ITEM_POSITION_FROM_BOTTOM_FOR_DAY_OF_MONTH = 5;
    private static final int UPDATE_ITEM_POSITION_FROM_BOTTOM_FOR_MONTH = 5;
    private static final int UPDATE_ITEM_POSITION_FROM_BOTTOM_FOR_YEAR = 5;
    private static final int UPDATE_ITEM_POSITION_FROM_TOP_FOR_DAY_OF_MONTH = 5;
    private static final int UPDATE_ITEM_POSITION_FROM_TOP_FOR_MONTH = 5;
    private static final int UPDATE_ITEM_POSITION_FROM_TOP_FOR_YEAR = 5;
    private Context mContext;
    private int mDayOfMonthAdapterTextViewResId;
    private YDayOfMonthRollerPicker mDayOfMonthRoller;
    private YMonthRollerPicker mMonthRoller;
    private android.widget.DatePicker.OnDateChangedListener mOnDateChangedListener;
    private int mPullbackItemPositionFromBottomForDayOfMonth;
    private int mPullbackItemPositionFromTopForDayOfMonth;
    private final YTime mYTime = new YTime();
    private YYearRollerPicker mYearRoller;

    public YDateRollerPickerHelper(Context context, YYearRollerPicker yyearrollerpicker, YMonthRollerPicker ymonthrollerpicker, YDayOfMonthRollerPicker ydayofmonthrollerpicker)
    {
        mPullbackItemPositionFromTopForDayOfMonth = 0;
        mPullbackItemPositionFromBottomForDayOfMonth = 0;
        mDayOfMonthAdapterTextViewResId = 0;
        mOnDateChangedListener = null;
        mContext = context;
        mYearRoller = yyearrollerpicker;
        mMonthRoller = ymonthrollerpicker;
        mDayOfMonthRoller = ydayofmonthrollerpicker;
        mYTime.setToNow();
        if (mYearRoller != null)
        {
            mYearRoller.setUpdateAndPullbackPosition(5, 5, 67, 67);
            mYearRoller.setOnBaseTimeRollerPickerListener(new YBaseTimeRollerPicker.OnBaseTimeRollerPickerListener() {

                final YDateRollerPickerHelper this$0;

                public void onScrollStateChanged()
                {
                    sendToOnDateChangedListener(true);
                }

            
            {
                this$0 = YDateRollerPickerHelper.this;
                super();
            }
            });
        }
        if (mMonthRoller != null)
        {
            mMonthRoller.setUpdateAndPullbackPosition(5, 5, 12, 12);
            mMonthRoller.setOnBaseTimeRollerPickerListener(new YBaseTimeRollerPicker.OnBaseTimeRollerPickerListener() {

                final YDateRollerPickerHelper this$0;

                public void onScrollStateChanged()
                {
                    sendToOnDateChangedListener(true);
                }

            
            {
                this$0 = YDateRollerPickerHelper.this;
                super();
            }
            });
        }
        if (mDayOfMonthRoller != null)
        {
            mDayOfMonthRoller.setUpdateAndPullbackPosition(5, 5, mPullbackItemPositionFromTopForDayOfMonth, mPullbackItemPositionFromBottomForDayOfMonth);
            mDayOfMonthRoller.setOnBaseTimeRollerPickerListener(new YBaseTimeRollerPicker.OnBaseTimeRollerPickerListener() {

                final YDateRollerPickerHelper this$0;

                public void onScrollStateChanged()
                {
                    sendToOnDateChangedListener(false);
                }

            
            {
                this$0 = YDateRollerPickerHelper.this;
                super();
            }
            });
        }
    }

    private final CharSequence[] getDayOfMonthArray()
    {
        updateCurrentDate();
        YTime ytime = getTimeOfBeginDayOfMonth();
        YTime ytime1 = new YTime(ytime);
        ytime1.month = 1 + ytime1.month;
        ytime1.normalize(false);
        int i = (int)((ytime1.toMillis(false) - ytime.toMillis(false)) / 0x5265c00L);
        if (i < 0)
        {
            i = 31;
        }
        mPullbackItemPositionFromTopForDayOfMonth = i;
        mPullbackItemPositionFromBottomForDayOfMonth = i;
        int j = i * 3;
        mDayOfMonthRoller.setEndDayOfMonth(i);
        ytime1.set(ytime);
        ytime1.normalize(false);
        CharSequence acharsequence[] = new CharSequence[j];
        for (int k = 0; k < j;)
        {
            for (int l = 0; l < mPullbackItemPositionFromTopForDayOfMonth; l++)
            {
                String s = getJapaneseNameOfWeekDay(ytime1, "(", ")");
                ytime1.monthDay = 1 + ytime1.monthDay;
                ytime1.normalize(false);
                acharsequence[k] = (new StringBuilder()).append(String.valueOf(l + 1)).append(s).toString();
                k++;
            }

            ytime1.set(ytime);
            ytime1.normalize(false);
        }

        return acharsequence;
    }

    private final CharSequence[] getMonthArray()
    {
        CharSequence acharsequence[] = new CharSequence[36];
        for (int i = 0; i < 36;)
        {
            int j = 0;
            while (j < 12) 
            {
                acharsequence[i] = String.valueOf(j + 1);
                i++;
                j++;
            }
        }

        return acharsequence;
    }

    private YTime getTimeOfBeginDayOfMonth()
    {
        YTime ytime = new YTime(mYTime);
        ytime.monthDay = 1;
        ytime.normalize(false);
        return ytime;
    }

    private final CharSequence[] getYearArray()
    {
        CharSequence acharsequence[] = new CharSequence[201];
        for (int i = 0; i < 201;)
        {
            int j = 0;
            while (j < 67) 
            {
                acharsequence[i] = String.valueOf(j + 1970);
                i++;
                j++;
            }
        }

        return acharsequence;
    }

    private void setDayOfMonth(int i)
    {
        if (mDayOfMonthRoller == null)
        {
            return;
        } else
        {
            mDayOfMonthRoller.setCurrentDayOfMonth(i);
            return;
        }
    }

    private void setMonth(int i)
    {
        if (mMonthRoller == null)
        {
            return;
        } else
        {
            mMonthRoller.setCurrentMonth(i);
            return;
        }
    }

    private void setYear(int i)
    {
        if (mYearRoller == null)
        {
            return;
        } else
        {
            mYearRoller.setCurrentYear(i);
            return;
        }
    }

    private void updateCurrentDate()
    {
        int i = mYTime.year;
        int j = mYTime.month;
        int k = mYTime.monthDay;
        if (mYearRoller != null && mYearRoller.getCurrentYear() >= 0)
        {
            i = mYearRoller.getCurrentYear();
        }
        if (mMonthRoller != null && mMonthRoller.getCurrentMonth() >= 0)
        {
            j = mMonthRoller.getCurrentMonth();
        }
        YTime ytime = new YTime();
        ytime.set(1, j, i);
        mYTime.normalize(false);
        YTime ytime1 = new YTime(ytime);
        ytime1.month = 1 + ytime1.month;
        ytime1.normalize(false);
        int l = (int)((ytime1.toMillis(false) - ytime.toMillis(false)) / 0x5265c00L);
        if (mDayOfMonthRoller != null && mDayOfMonthRoller.getCurrentDayOfMonth() >= 0)
        {
            k = mDayOfMonthRoller.getCurrentDayOfMonth();
            if (k > l)
            {
                k = l;
            }
        }
        mYTime.set(k, j, i);
        mYTime.normalize(false);
    }

    public int getDayOfMonth()
    {
        if (mDayOfMonthRoller == null)
        {
            return -1;
        } else
        {
            return mDayOfMonthRoller.getCurrentDayOfMonth();
        }
    }

    public String getJapaneseNameOfWeekDay(YTime ytime, String s, String s1)
    {
        switch (ytime.weekDay)
        {
        default:
            return "";

        case 0: // '\0'
            return (new StringBuilder()).append(s).append("\u65E5").append(s1).toString();

        case 1: // '\001'
            return (new StringBuilder()).append(s).append("\u6708").append(s1).toString();

        case 2: // '\002'
            return (new StringBuilder()).append(s).append("\u706B").append(s1).toString();

        case 3: // '\003'
            return (new StringBuilder()).append(s).append("\u6C34").append(s1).toString();

        case 4: // '\004'
            return (new StringBuilder()).append(s).append("\u6728").append(s1).toString();

        case 5: // '\005'
            return (new StringBuilder()).append(s).append("\u91D1").append(s1).toString();

        case 6: // '\006'
            return (new StringBuilder()).append(s).append("\u571F").append(s1).toString();
        }
    }

    public int getMonth()
    {
        if (mMonthRoller == null)
        {
            return -1;
        } else
        {
            return mMonthRoller.getCurrentMonth();
        }
    }

    public int getYear()
    {
        if (mYearRoller == null)
        {
            return -1;
        } else
        {
            return mYearRoller.getCurrentYear();
        }
    }

    public void init(int i, int j, int k, android.widget.DatePicker.OnDateChangedListener ondatechangedlistener)
    {
        updateDate(i, j, k);
        setOnDateChangedListener(ondatechangedlistener);
    }

    public void sendToOnDateChangedListener(boolean flag)
    {
        int i = -1;
        int j = -1;
        int k = -1;
        if (mYearRoller != null)
        {
            i = mYearRoller.getCurrentYear();
        }
        if (mMonthRoller != null)
        {
            j = mMonthRoller.getCurrentMonth();
        }
        if (mDayOfMonthRoller != null)
        {
            k = mDayOfMonthRoller.getCurrentDayOfMonth();
        }
        mYTime.normalize(false);
        if (flag)
        {
            setDayOfMonthArrayAdapter(mDayOfMonthAdapterTextViewResId);
            if (mDayOfMonthRoller != null)
            {
                mDayOfMonthRoller.setCurrentDayOfMonth(k);
                k = mDayOfMonthRoller.getRawCurrentDayOfMonth();
            }
        }
        if (mOnDateChangedListener != null)
        {
            mOnDateChangedListener.onDateChanged(null, i, j, k);
        }
    }

    public void setDayOfMonthArrayAdapter(int i)
    {
        if (mDayOfMonthRoller == null)
        {
            return;
        } else
        {
            mDayOfMonthAdapterTextViewResId = i;
            mDayOfMonthRoller.setArrayAdapter(mContext, i, getDayOfMonthArray());
            mDayOfMonthRoller.setUpdateAndPullbackPosition(5, 5, mPullbackItemPositionFromTopForDayOfMonth, mPullbackItemPositionFromBottomForDayOfMonth);
            return;
        }
    }

    public void setDayOfMonthItemHeight(int i)
    {
        if (mDayOfMonthRoller == null)
        {
            return;
        } else
        {
            mDayOfMonthRoller.setItemHeight(i);
            return;
        }
    }

    public void setEnabled(boolean flag)
    {
    }

    public void setMonthArrayAdapter(int i)
    {
        if (mMonthRoller == null)
        {
            return;
        } else
        {
            mMonthRoller.setArrayAdapter(mContext, i, getMonthArray());
            return;
        }
    }

    public void setMonthItemHeight(int i)
    {
        if (mMonthRoller == null)
        {
            return;
        } else
        {
            mMonthRoller.setItemHeight(i);
            return;
        }
    }

    public void setOnDateChangedListener(android.widget.DatePicker.OnDateChangedListener ondatechangedlistener)
    {
        mOnDateChangedListener = ondatechangedlistener;
    }

    public void setYearArrayAdapter(int i)
    {
        if (mYearRoller == null)
        {
            return;
        } else
        {
            mYearRoller.setArrayAdapter(mContext, i, getYearArray());
            return;
        }
    }

    public void setYearItemHeight(int i)
    {
        if (mYearRoller == null)
        {
            return;
        } else
        {
            mYearRoller.setItemHeight(i);
            return;
        }
    }

    public void updateDate(int i, int j, int k)
    {
        mYTime.set(k, j, i);
        mYTime.normalize(false);
        setYear(mYTime.year);
        setMonth(mYTime.month);
        setDayOfMonth(mYTime.monthDay);
    }
}
