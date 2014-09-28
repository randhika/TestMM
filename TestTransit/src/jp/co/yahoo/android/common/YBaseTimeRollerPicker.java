// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

// Referenced classes of package jp.co.yahoo.android.common:
//            YRollerListView

public abstract class YBaseTimeRollerPicker extends YRollerListView
{
    static interface OnBaseTimeRollerPickerListener
    {

        public abstract void onScrollStateChanged();
    }


    private OnBaseTimeRollerPickerListener mOnBaseTimeRollerPickerListener;
    private int mPullbackItemPositionFromBottom;
    private int mPullbackItemPositionFromTop;
    private int mUpdateItemPostionFromBottom;
    private int mUpdateItemPostionFromTop;

    public YBaseTimeRollerPicker(Context context)
    {
        super(context);
        mUpdateItemPostionFromTop = 0;
        mUpdateItemPostionFromBottom = 0;
        mPullbackItemPositionFromTop = 0;
        mPullbackItemPositionFromBottom = 0;
        setSelector(new ColorDrawable(0));
    }

    public YBaseTimeRollerPicker(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mUpdateItemPostionFromTop = 0;
        mUpdateItemPostionFromBottom = 0;
        mPullbackItemPositionFromTop = 0;
        mPullbackItemPositionFromBottom = 0;
        setSelector(new ColorDrawable(0));
    }

    public YBaseTimeRollerPicker(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mUpdateItemPostionFromTop = 0;
        mUpdateItemPostionFromBottom = 0;
        mPullbackItemPositionFromTop = 0;
        mPullbackItemPositionFromBottom = 0;
        setSelector(new ColorDrawable(0));
    }

    public int getPullbackItemPostionFromBottom()
    {
        return mPullbackItemPositionFromBottom;
    }

    public int getPullbackItemPostionFromTop()
    {
        return mPullbackItemPositionFromTop;
    }

    public int getUpdateItemPostionFromBottom()
    {
        return mUpdateItemPostionFromBottom;
    }

    public int getUpdateItemPostionFromTop()
    {
        return mUpdateItemPostionFromTop;
    }

    public void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if (getAdapter() != null && getAdapter().getCount() > 0)
        {
            setCurrentPosition();
        }
    }

    protected abstract void onTimeChanged();

    public volatile void setAdapter(Adapter adapter)
    {
        setAdapter((ListAdapter)adapter);
    }

    public void setAdapter(ListAdapter listadapter)
    {
    }

    public void setArrayAdapter(Context context, int i, int j)
    {
        ArrayAdapter arrayadapter = ArrayAdapter.createFromResource(context, i, j);
        setUpdateItemPositionFromTop(mUpdateItemPostionFromTop);
        setUpdateItemPositionFromBottom(arrayadapter.getCount() - mUpdateItemPostionFromBottom);
        setPullBackItemPositionFromTop(mPullbackItemPositionFromTop);
        setPullBackItemPositionFromBottom(mPullbackItemPositionFromBottom);
        setCacheColorHint(0);
        super.setAdapter(arrayadapter);
    }

    public void setArrayAdapter(Context context, int i, CharSequence acharsequence[])
    {
        ArrayAdapter arrayadapter = new ArrayAdapter(context, i, acharsequence);
        setUpdateItemPositionFromTop(mUpdateItemPostionFromTop);
        setUpdateItemPositionFromBottom(arrayadapter.getCount() - mUpdateItemPostionFromBottom);
        setPullBackItemPositionFromTop(mPullbackItemPositionFromTop);
        setPullBackItemPositionFromBottom(mPullbackItemPositionFromBottom);
        setCacheColorHint(0);
        super.setAdapter(arrayadapter);
    }

    public abstract void setCurrentPosition();

    public void setOnBaseTimeRollerPickerListener(OnBaseTimeRollerPickerListener onbasetimerollerpickerlistener)
    {
        mOnBaseTimeRollerPickerListener = onbasetimerollerpickerlistener;
    }

    public void setSelectionFromTop(int i, int j)
    {
        super.setSelectionFromTop(i, j);
        onTimeChanged();
        if (mOnBaseTimeRollerPickerListener != null)
        {
            mOnBaseTimeRollerPickerListener.onScrollStateChanged();
        }
    }

    public void setUpdateAndPullbackPosition(int i, int j, int k, int l)
    {
        mUpdateItemPostionFromTop = i;
        mUpdateItemPostionFromBottom = j;
        mPullbackItemPositionFromTop = k;
        mPullbackItemPositionFromBottom = l;
    }
}
