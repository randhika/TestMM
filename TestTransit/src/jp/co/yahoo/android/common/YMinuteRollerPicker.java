// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.util.AttributeSet;

// Referenced classes of package jp.co.yahoo.android.common:
//            YBaseTimeRollerPicker

public class YMinuteRollerPicker extends YBaseTimeRollerPicker
{

    private static final int MINUTE_UNIT = 1;
    private int mCurrentMinute;

    public YMinuteRollerPicker(Context context)
    {
        super(context);
        mCurrentMinute = 0;
    }

    public YMinuteRollerPicker(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mCurrentMinute = 0;
    }

    public YMinuteRollerPicker(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mCurrentMinute = 0;
    }

    public int getCurrentMinute()
    {
        if (getSelectedItemPosition() < 0)
        {
            return -1;
        } else
        {
            mCurrentMinute = 1 * (getSelectedItemPosition() % 60);
            return mCurrentMinute;
        }
    }

    protected void onTimeChanged()
    {
        if (getSelectedItemPosition() < 0)
        {
            return;
        } else
        {
            mCurrentMinute = getSelectedItemPosition() / 1;
            return;
        }
    }

    public void setCurrentMinute(int i)
    {
        if (i < 0 || i > 60)
        {
            return;
        } else
        {
            mCurrentMinute = i;
            setCurrentPosition();
            return;
        }
    }

    public void setCurrentPosition()
    {
        setSelectionFromTop(getPullbackItemPostionFromTop() + mCurrentMinute / 1, (getHeight() - getItemHeight()) / 2);
    }
}
