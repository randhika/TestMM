// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.util.AttributeSet;

// Referenced classes of package jp.co.yahoo.android.common:
//            YBaseTimeRollerPicker

public class YMonthRollerPicker extends YBaseTimeRollerPicker
{

    private final int MONTHS_OF_YEAR;
    private int mCurrentMonth;

    public YMonthRollerPicker(Context context)
    {
        super(context);
        mCurrentMonth = 0;
        MONTHS_OF_YEAR = 12;
    }

    public YMonthRollerPicker(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mCurrentMonth = 0;
        MONTHS_OF_YEAR = 12;
    }

    public YMonthRollerPicker(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mCurrentMonth = 0;
        MONTHS_OF_YEAR = 12;
    }

    public int getCurrentMonth()
    {
        if (getSelectedItemPosition() < 0)
        {
            return -1;
        } else
        {
            mCurrentMonth = getSelectedItemPosition() % 12;
            return mCurrentMonth;
        }
    }

    protected void onTimeChanged()
    {
        if (getSelectedItemPosition() >= 0)
        {
            mCurrentMonth = getSelectedItemPosition() % 12;
        }
    }

    public void setCurrentMonth(int i)
    {
        if (i < 0 || i >= 12)
        {
            return;
        } else
        {
            mCurrentMonth = i;
            setCurrentPosition();
            return;
        }
    }

    public void setCurrentPosition()
    {
        setSelectionFromTop(getPullbackItemPostionFromTop() + mCurrentMonth, (getHeight() - getItemHeight()) / 2);
    }
}
