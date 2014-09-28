// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.util.AttributeSet;

// Referenced classes of package jp.co.yahoo.android.common:
//            YBaseTimeRollerPicker

public class YYearRollerPicker extends YBaseTimeRollerPicker
{

    public static final int MAX_YEAR = 2036;
    public static final int MIN_YEAR = 1970;
    private int mCurrentYear;

    public YYearRollerPicker(Context context)
    {
        super(context);
        mCurrentYear = 0;
    }

    public YYearRollerPicker(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mCurrentYear = 0;
    }

    public YYearRollerPicker(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mCurrentYear = 0;
    }

    public int getCurrentYear()
    {
        if (getSelectedItemPosition() < 0)
        {
            return -1;
        } else
        {
            mCurrentYear = 1970 + getSelectedItemPosition() % 67;
            return mCurrentYear;
        }
    }

    protected void onTimeChanged()
    {
        if (getSelectedItemPosition() >= 0)
        {
            mCurrentYear = getSelectedItemPosition() % 66;
        }
    }

    public void setCurrentPosition()
    {
        setSelectionFromTop(-1970 + (getPullbackItemPostionFromTop() + mCurrentYear), (getHeight() - getItemHeight()) / 2);
    }

    public void setCurrentYear(int i)
    {
        if (i < 1970 || i > 2036)
        {
            return;
        } else
        {
            mCurrentYear = i;
            setCurrentPosition();
            return;
        }
    }
}
