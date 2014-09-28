// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.util.AttributeSet;

// Referenced classes of package jp.co.yahoo.android.common:
//            YBaseTimeRollerPicker

public class YHourRollerPicker extends YBaseTimeRollerPicker
{

    private static final int HOUR_UNIT = 1;
    private int mCurrentHour;

    public YHourRollerPicker(Context context)
    {
        super(context);
        mCurrentHour = 0;
    }

    public YHourRollerPicker(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mCurrentHour = 0;
    }

    public YHourRollerPicker(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mCurrentHour = 0;
    }

    public int getCurrentHour()
    {
        if (getSelectedItemPosition() < 0)
        {
            return -1;
        } else
        {
            mCurrentHour = 1 * (getSelectedItemPosition() % 24);
            return mCurrentHour;
        }
    }

    protected void onTimeChanged()
    {
        if (getSelectedItemPosition() < 0)
        {
            return;
        } else
        {
            mCurrentHour = getSelectedItemPosition() / 1;
            return;
        }
    }

    public void setCurrentHour(int i)
    {
        if (i < 0 || i > 23)
        {
            return;
        } else
        {
            mCurrentHour = i;
            setCurrentPosition();
            return;
        }
    }

    public void setCurrentPosition()
    {
        setSelectionFromTop(getPullbackItemPostionFromTop() + mCurrentHour / 1, (getHeight() - getItemHeight()) / 2);
    }
}
