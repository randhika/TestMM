// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.util.AttributeSet;

// Referenced classes of package jp.co.yahoo.android.common:
//            YBaseTimeRollerPicker

public class YDayOfMonthRollerPicker extends YBaseTimeRollerPicker
{

    private int mCurrentDayOfMonth;
    private int mEndDayOfMonth;

    public YDayOfMonthRollerPicker(Context context)
    {
        super(context);
        mCurrentDayOfMonth = 0;
        mEndDayOfMonth = 31;
    }

    public YDayOfMonthRollerPicker(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mCurrentDayOfMonth = 0;
        mEndDayOfMonth = 31;
    }

    public YDayOfMonthRollerPicker(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mCurrentDayOfMonth = 0;
        mEndDayOfMonth = 31;
    }

    public int getCurrentDayOfMonth()
    {
        if (getSelectedItemPosition() < 0)
        {
            return -1;
        } else
        {
            mCurrentDayOfMonth = 1 + getSelectedItemPosition() % mEndDayOfMonth;
            return mCurrentDayOfMonth;
        }
    }

    public int getRawCurrentDayOfMonth()
    {
        return mCurrentDayOfMonth;
    }

    protected void onTimeChanged()
    {
        if (getSelectedItemPosition() >= 0)
        {
            mCurrentDayOfMonth = 1 + getSelectedItemPosition() % mEndDayOfMonth;
        }
    }

    public void setCurrentDayOfMonth(int i)
    {
        if (i < 1)
        {
            return;
        }
        if (i > mEndDayOfMonth)
        {
            mCurrentDayOfMonth = mEndDayOfMonth;
        } else
        {
            mCurrentDayOfMonth = i;
        }
        setCurrentPosition();
    }

    public void setCurrentPosition()
    {
        setSelectionFromTop(-1 + (getPullbackItemPostionFromTop() + mCurrentDayOfMonth), (getHeight() - getItemHeight()) / 2);
    }

    public void setEndDayOfMonth(int i)
    {
        mEndDayOfMonth = i;
    }
}
