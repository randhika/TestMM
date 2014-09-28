// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;
import com.navdrawer.SimpleSideDrawer;
import java.lang.reflect.Field;

public class YSimpleSideDrawer extends SimpleSideDrawer
{
    public static interface StateListener
    {

        public abstract void onStateChanged(int i);
    }


    public static final int STATE_CLOSE = 1;
    public static final int STATE_LEFT_CLOSING = 3;
    public static final int STATE_LEFT_OPEN = 2;
    public static final int STATE_RIGHT_CLOSING = 5;
    public static final int STATE_RIGHT_OPEN = 4;
    protected final ViewGroup mAboveView;
    protected final LinearLayout mBehindView;
    protected final LinearLayout mLeftBehindBase;
    protected int mLeftBehindViewWidth;
    private StateListener mListener;
    protected final View mOverlay;
    protected final LinearLayout mRightBehindBase;
    protected int mRightBehindViewWidth;
    protected Scroller mScroller;
    protected int mState;
    protected final Window mWindow;

    public YSimpleSideDrawer(Activity activity)
    {
        this(activity, ((Interpolator) (new DecelerateInterpolator(0.9F))), 180);
    }

    private YSimpleSideDrawer(Activity activity, Interpolator interpolator, int i)
    {
        super(activity, interpolator, i);
        mState = 1;
        mWindow = activity.getWindow();
        mBehindView = (LinearLayout)getChildAt(0);
        mAboveView = (ViewGroup)getChildAt(1);
        mLeftBehindBase = (LinearLayout)mBehindView.getChildAt(0);
        mRightBehindBase = (LinearLayout)mBehindView.getChildAt(2);
        mOverlay = mAboveView.getChildAt(1);
        try
        {
            Field field = com/navdrawer/SimpleSideDrawer.getDeclaredField("mScroller");
            field.setAccessible(true);
            mScroller = (Scroller)field.get(this);
            field.setAccessible(false);
            return;
        }
        catch (NoSuchFieldException nosuchfieldexception)
        {
            return;
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public static String stateToString(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 1: // '\001'
            return "STATE_CLOSE";

        case 2: // '\002'
            return "STATE_LEFT_OPEN";

        case 3: // '\003'
            return "STATE_LEFT_CLOSING";

        case 4: // '\004'
            return "STATE_RIGHT_OPEN";

        case 5: // '\005'
            return "STATE_RIGHT_CLOSING";
        }
    }

    public void closeLeftSideFast()
    {
        setAnimationDurationLeft(0);
        closeLeftSide();
        setAnimationDurationLeft(180);
    }

    public void closeRightSideFast()
    {
        setAnimationDurationRight(0);
        closeRightSide();
        setAnimationDurationRight(180);
    }

    public void computeScroll()
    {
        super.computeScroll();
        if (mScroller != null && mScroller.computeScrollOffset()) goto _L2; else goto _L1
_L1:
        if (mAboveView.getScrollX() != 0) goto _L4; else goto _L3
_L3:
        if (mState != 2) goto _L6; else goto _L5
_L5:
        setState(3);
        setState(1);
_L2:
        return;
_L6:
        if (mState == 4)
        {
            setState(5);
            setState(1);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (mAboveView.getScrollX() == -mLeftBehindViewWidth)
        {
            setState(2);
            return;
        }
        if (mAboveView.getScrollX() == mRightBehindViewWidth)
        {
            setState(4);
            return;
        }
        if (true) goto _L2; else goto _L7
_L7:
    }

    public boolean isClosed()
    {
        return mAboveView != null && mAboveView.getScrollX() == 0;
    }

    public boolean isLeftSideOpened()
    {
        return mLeftBehindBase.getVisibility() == 0 && mRightBehindBase.getVisibility() == 8;
    }

    public boolean isRightSideOpened()
    {
        return mRightBehindBase.getVisibility() == 0 && mLeftBehindBase.getVisibility() == 8;
    }

    public void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        mLeftBehindViewWidth = mLeftBehindBase.getMeasuredWidth();
        mRightBehindViewWidth = mRightBehindBase.getMeasuredWidth();
    }

    public void openLeftSideFast()
    {
        setAnimationDurationLeft(0);
        openLeftSide();
        setAnimationDurationLeft(180);
    }

    public void openRightSideFast()
    {
        setAnimationDurationRight(0);
        openRightSide();
        setAnimationDurationRight(180);
    }

    public void setOverlayBackgroundColor(int i)
    {
        mOverlay.setBackgroundColor(i);
    }

    protected void setState(int i)
    {
        if (i != mState)
        {
            mState = i;
            if (mListener != null)
            {
                mListener.onStateChanged(mState);
            }
        }
    }

    public void setStateListener(StateListener statelistener)
    {
        mListener = statelistener;
    }
}
