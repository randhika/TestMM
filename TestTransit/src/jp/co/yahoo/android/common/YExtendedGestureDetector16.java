// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public class YExtendedGestureDetector16
{
    private class GestureHandler extends Handler
    {

        final YExtendedGestureDetector16 this$0;

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 4: default 36
        //                       1 63
        //                       2 83
        //                       3 145
        //                       4 91;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            throw new RuntimeException((new StringBuilder()).append("Unknown message ").append(message).toString());
_L2:
            mListener.onShowPress(mCurrentDownEvent);
_L7:
            return;
_L3:
            dispatchLongPress();
            return;
_L5:
            mListener.onHoldAfterMove(mCurrentMoveEvent);
            if (!mHandler.hasMessages(4))
            {
                mHandler.sendEmptyMessageDelayed(4, HOLD_CHECK_PERIOD);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            if (mDoubleTapListener != null && !mStillDown)
            {
                mDoubleTapListener.onSingleTapConfirmed(mCurrentDownEvent);
                return;
            }
            if (true) goto _L7; else goto _L6
_L6:
        }

        GestureHandler()
        {
            this$0 = YExtendedGestureDetector16.this;
            super();
        }

        GestureHandler(Handler handler)
        {
            this$0 = YExtendedGestureDetector16.this;
            super(handler.getLooper());
        }
    }

    public static interface OnDoubleTapListener16
    {

        public abstract boolean onDoubleTap(MotionEvent motionevent);

        public abstract boolean onDoubleTapEvent(MotionEvent motionevent);

        public abstract boolean onSingleTapConfirmed(MotionEvent motionevent);
    }

    public static interface OnExtendedGestureListener16
    {

        public abstract boolean onDown(MotionEvent motionevent);

        public abstract boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1);

        public abstract void onHoldAfterMove(MotionEvent motionevent);

        public abstract void onLongPress(MotionEvent motionevent);

        public abstract boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1);

        public abstract void onShowPress(MotionEvent motionevent);

        public abstract boolean onSingleTapUp(MotionEvent motionevent);
    }

    public static class SimpleOnExtendedGestureListener
        implements OnExtendedGestureListener16, OnDoubleTapListener16
    {

        public boolean onDoubleTap(MotionEvent motionevent)
        {
            return false;
        }

        public boolean onDoubleTapEvent(MotionEvent motionevent)
        {
            return false;
        }

        public boolean onDown(MotionEvent motionevent)
        {
            return false;
        }

        public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
        {
            return false;
        }

        public void onHoldAfterMove(MotionEvent motionevent)
        {
        }

        public void onLongPress(MotionEvent motionevent)
        {
        }

        public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
        {
            return false;
        }

        public void onShowPress(MotionEvent motionevent)
        {
        }

        public boolean onSingleTapConfirmed(MotionEvent motionevent)
        {
            return false;
        }

        public boolean onSingleTapUp(MotionEvent motionevent)
        {
            return false;
        }

        public SimpleOnExtendedGestureListener()
        {
        }
    }


    private static final int DOUBLE_TAP_TIMEOUT = 0;
    private static final int HOLD_AFTER_MOVE = 4;
    private static final int LONGPRESS_TIMEOUT = 0;
    private static final int LONG_PRESS = 2;
    private static final int SHOW_PRESS = 1;
    private static final int TAP = 3;
    private static final int TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
    private int HOLD_CHECK_PERIOD;
    private boolean mAlwaysInBiggerTapRegion;
    private boolean mAlwaysInTapRegion;
    private int mBiggerTouchSlopSquare;
    private MotionEvent mCurrentDownEvent;
    private MotionEvent mCurrentMoveEvent;
    private OnDoubleTapListener16 mDoubleTapListener;
    private int mDoubleTapSlopSquare;
    private final Handler mHandler;
    private boolean mInLongPress;
    private boolean mIsDoubleTapping;
    private boolean mIsLongpressEnabled;
    private float mLastMotionX;
    private float mLastMotionY;
    private final OnExtendedGestureListener16 mListener;
    private int mMaximumFlingVelocity;
    private int mMinimumFlingVelocity;
    private MotionEvent mPreviousUpEvent;
    private boolean mStillDown;
    private int mTouchSlopSquare;
    private VelocityTracker mVelocityTracker;

    public YExtendedGestureDetector16(Context context, OnExtendedGestureListener16 onextendedgesturelistener16)
    {
        this(context, onextendedgesturelistener16, null);
    }

    public YExtendedGestureDetector16(Context context, OnExtendedGestureListener16 onextendedgesturelistener16, Handler handler)
    {
        mBiggerTouchSlopSquare = 400;
        HOLD_CHECK_PERIOD = 200;
        if (handler != null)
        {
            mHandler = new GestureHandler(handler);
        } else
        {
            mHandler = new GestureHandler();
        }
        mListener = onextendedgesturelistener16;
        if (onextendedgesturelistener16 instanceof OnDoubleTapListener16)
        {
            setOnDoubleTapListener((OnDoubleTapListener16)onextendedgesturelistener16);
        }
        init(context);
    }

    public YExtendedGestureDetector16(OnExtendedGestureListener16 onextendedgesturelistener16)
    {
        this(null, onextendedgesturelistener16, null);
    }

    public YExtendedGestureDetector16(OnExtendedGestureListener16 onextendedgesturelistener16, Handler handler)
    {
        this(null, onextendedgesturelistener16, handler);
    }

    private void dispatchLongPress()
    {
        mHandler.removeMessages(3);
        mInLongPress = true;
        mListener.onLongPress(mCurrentDownEvent);
    }

    private void init(Context context)
    {
        if (mListener == null)
        {
            throw new NullPointerException("OnExtendedGestureListener must not be null");
        }
        mIsLongpressEnabled = true;
        int i;
        int j;
        if (context == null)
        {
            i = ViewConfiguration.getTouchSlop();
            j = 100;
            mMinimumFlingVelocity = ViewConfiguration.getMinimumFlingVelocity();
            mMaximumFlingVelocity = ViewConfiguration.getMaximumFlingVelocity();
        } else
        {
            ViewConfiguration viewconfiguration = ViewConfiguration.get(context);
            i = viewconfiguration.getScaledTouchSlop();
            j = viewconfiguration.getScaledDoubleTapSlop();
            mMinimumFlingVelocity = viewconfiguration.getScaledMinimumFlingVelocity();
            mMaximumFlingVelocity = viewconfiguration.getScaledMaximumFlingVelocity();
        }
        mTouchSlopSquare = i * i;
        mDoubleTapSlopSquare = j * j;
    }

    private boolean isConsideredDoubleTap(MotionEvent motionevent, MotionEvent motionevent1, MotionEvent motionevent2)
    {
        if (mAlwaysInBiggerTapRegion && motionevent2.getEventTime() - motionevent1.getEventTime() <= (long)DOUBLE_TAP_TIMEOUT)
        {
            int i = (int)motionevent.getX() - (int)motionevent2.getX();
            int j = (int)motionevent.getY() - (int)motionevent2.getY();
            if (i * i + j * j < mDoubleTapSlopSquare)
            {
                return true;
            }
        }
        return false;
    }

    public boolean isLongpressEnabled()
    {
        return mIsLongpressEnabled;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i;
        float f;
        float f1;
        boolean flag;
        i = motionevent.getAction();
        f = motionevent.getY();
        f1 = motionevent.getX();
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(motionevent);
        flag = false;
        i;
        JVM INSTR tableswitch 0 3: default 72
    //                   0 75
    //                   1 657
    //                   2 314
    //                   3 881;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return flag;
_L2:
        OnDoubleTapListener16 ondoubletaplistener16 = mDoubleTapListener;
        boolean flag4 = false;
        if (ondoubletaplistener16 != null)
        {
            boolean flag5 = mHandler.hasMessages(3);
            if (flag5)
            {
                mHandler.removeMessages(3);
            }
            if (mCurrentDownEvent != null && mPreviousUpEvent != null && flag5 && isConsideredDoubleTap(mCurrentDownEvent, mPreviousUpEvent, motionevent))
            {
                mIsDoubleTapping = true;
                flag4 = false | mDoubleTapListener.onDoubleTap(mCurrentDownEvent) | mDoubleTapListener.onDoubleTapEvent(motionevent);
            } else
            {
                mHandler.sendEmptyMessageDelayed(3, DOUBLE_TAP_TIMEOUT);
                flag4 = false;
            }
        }
        mLastMotionX = f1;
        mLastMotionY = f;
        mCurrentDownEvent = MotionEvent.obtain(motionevent);
        mAlwaysInTapRegion = true;
        mAlwaysInBiggerTapRegion = true;
        mStillDown = true;
        mInLongPress = false;
        if (mIsLongpressEnabled)
        {
            mHandler.removeMessages(2);
            mHandler.sendEmptyMessageAtTime(2, mCurrentDownEvent.getDownTime() + (long)TAP_TIMEOUT + (long)LONGPRESS_TIMEOUT);
        }
        mHandler.sendEmptyMessageAtTime(1, mCurrentDownEvent.getDownTime() + (long)TAP_TIMEOUT);
        return flag4 | mListener.onDown(motionevent);
_L4:
        mCurrentMoveEvent = MotionEvent.obtain(motionevent);
        if (!mInLongPress)
        {
            break; /* Loop/switch isn't completed */
        }
        boolean flag3 = mHandler.hasMessages(4);
        flag = false;
        if (!flag3)
        {
            mHandler.sendEmptyMessageDelayed(4, HOLD_CHECK_PERIOD);
            return false;
        }
        if (true) goto _L1; else goto _L6
_L6:
        float f4;
        float f5;
        f4 = mLastMotionX - f1;
        f5 = mLastMotionY - f;
        if (mIsDoubleTapping)
        {
            return false | mDoubleTapListener.onDoubleTapEvent(motionevent);
        }
        if (!mAlwaysInTapRegion)
        {
            break; /* Loop/switch isn't completed */
        }
        int l = (int)(f1 - mCurrentDownEvent.getX());
        int i1 = (int)(f - mCurrentDownEvent.getY());
        int j1 = l * l + i1 * i1;
        int k1 = mTouchSlopSquare;
        flag = false;
        if (j1 > k1)
        {
            flag = mListener.onScroll(mCurrentDownEvent, motionevent, f4, f5);
            mLastMotionX = f1;
            mLastMotionY = f;
            mAlwaysInTapRegion = false;
            mHandler.removeMessages(3);
            mHandler.removeMessages(1);
            mHandler.removeMessages(2);
            mHandler.removeMessages(4);
        }
        if (j1 > mBiggerTouchSlopSquare)
        {
            mAlwaysInBiggerTapRegion = false;
        }
        if (!mHandler.hasMessages(4))
        {
            mHandler.sendEmptyMessageDelayed(4, HOLD_CHECK_PERIOD);
            return flag;
        }
        if (true) goto _L1; else goto _L7
_L7:
        int k;
        if (Math.abs(f4) >= 1.0F)
        {
            break; /* Loop/switch isn't completed */
        }
        k = Math.abs(f5) != 1.0F;
        flag = false;
        if (k < 0) goto _L1; else goto _L8
_L8:
        flag = mListener.onScroll(mCurrentDownEvent, motionevent, f4, f5);
        mLastMotionX = f1;
        mLastMotionY = f;
        if (!mHandler.hasMessages(4))
        {
            mHandler.sendEmptyMessageDelayed(4, HOLD_CHECK_PERIOD);
            return flag;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        MotionEvent motionevent1;
        mStillDown = false;
        motionevent1 = MotionEvent.obtain(motionevent);
        if (!mIsDoubleTapping) goto _L10; else goto _L9
_L9:
        boolean flag2 = false | mDoubleTapListener.onDoubleTapEvent(motionevent);
_L13:
        mPreviousUpEvent = MotionEvent.obtain(motionevent);
        mVelocityTracker.recycle();
        mVelocityTracker = null;
        mIsDoubleTapping = false;
        mHandler.removeMessages(1);
        mHandler.removeMessages(2);
        mHandler.removeMessages(4);
        return flag2;
_L10:
        if (!mInLongPress) goto _L12; else goto _L11
_L11:
        mHandler.removeMessages(3);
        mInLongPress = false;
        flag2 = false;
          goto _L13
_L12:
        if (!mAlwaysInTapRegion) goto _L15; else goto _L14
_L14:
        flag2 = mListener.onSingleTapUp(motionevent);
          goto _L13
_L15:
        float f2;
        float f3;
        VelocityTracker velocitytracker = mVelocityTracker;
        velocitytracker.computeCurrentVelocity(1000, mMaximumFlingVelocity);
        f2 = velocitytracker.getYVelocity();
        f3 = velocitytracker.getXVelocity();
        if (Math.abs(f2) > (float)mMinimumFlingVelocity) goto _L17; else goto _L16
_L16:
        int j;
        j = Math.abs(f3) != (float)mMinimumFlingVelocity;
        flag2 = false;
        if (j <= 0) goto _L13; else goto _L17
_L17:
        flag2 = mListener.onFling(mCurrentDownEvent, motionevent1, f3, f2);
          goto _L13
_L5:
        mHandler.removeMessages(1);
        mHandler.removeMessages(2);
        mHandler.removeMessages(3);
        mHandler.removeMessages(4);
        mVelocityTracker.recycle();
        mVelocityTracker = null;
        mIsDoubleTapping = false;
        mStillDown = false;
        boolean flag1 = mInLongPress;
        flag = false;
        if (flag1)
        {
            mInLongPress = false;
            return false;
        }
        if (true) goto _L1; else goto _L18
_L18:
    }

    public void setHoldCheckPeriod(int i)
    {
        if (i > 0)
        {
            HOLD_CHECK_PERIOD = i;
        }
    }

    public void setIsLongpressEnabled(boolean flag)
    {
        mIsLongpressEnabled = flag;
    }

    public void setOnDoubleTapListener(OnDoubleTapListener16 ondoubletaplistener16)
    {
        mDoubleTapListener = ondoubletaplistener16;
    }

    static 
    {
        LONGPRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
        DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
    }








}
