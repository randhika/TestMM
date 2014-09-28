// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.navdrawer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class SimpleSideDrawer extends FrameLayout
{
    private class BehindLinearLayout extends LinearLayout
    {

        final SimpleSideDrawer this$0;

        public void fitDisplay(Rect rect)
        {
            mBehindView.setPadding(rect.left, rect.top, 0, 0);
            requestLayout();
        }

        public BehindLinearLayout(Context context)
        {
            this$0 = SimpleSideDrawer.this;
            super(context);
        }
    }

    private abstract class DragAction
    {

        private boolean mDraggable;
        private float mLastMotionX;
        private boolean mOpening;
        final SimpleSideDrawer this$0;

        public abstract boolean onTouchEvent(MotionEvent motionevent);







        private DragAction()
        {
            this$0 = SimpleSideDrawer.this;
            super();
            mLastMotionX = 0.0F;
            mOpening = false;
            mDraggable = false;
        }

        DragAction(DragAction dragaction)
        {
            this();
        }
    }

    private class OverlayView extends View
    {

        private static final float CLICK_RANGE = 3F;
        private android.view.View.OnClickListener mClickListener;
        private float mDownX;
        private float mDownY;
        final SimpleSideDrawer this$0;

        public boolean onTouchEvent(MotionEvent motionevent)
        {
            motionevent.setLocation(motionevent.getX() - (float)mAboveView.getScrollX(), 0.0F);
            SimpleSideDrawer.this.onTouchEvent(motionevent);
            int i = 0xff & motionevent.getAction();
            float f = motionevent.getX();
            float f1 = motionevent.getY();
            if (i == 0)
            {
                mDownX = f;
                mDownY = f1;
            } else
            if (i == 1 && mClickListener != null && Math.abs(mDownX - f) < 3F && Math.abs(mDownY - f1) < 3F)
            {
                mClickListener.onClick(this);
                return true;
            }
            return true;
        }

        public void setOnClickListener(android.view.View.OnClickListener onclicklistener)
        {
            mClickListener = onclicklistener;
            super.setOnClickListener(onclicklistener);
        }

        public OverlayView(Context context)
        {
            this$0 = SimpleSideDrawer.this;
            super(context);
        }
    }


    private final ViewGroup mAboveView;
    private final BehindLinearLayout mBehindView;
    private int mDurationLeft;
    private int mDurationRight;
    private final LinearLayout mLeftBehindBase;
    private View mLeftBehindView;
    private int mLeftBehindViewWidth;
    private DragAction mLeftDragAction = new DragAction() {

        final SimpleSideDrawer this$0;

        public boolean onTouchEvent(MotionEvent motionevent)
        {
            boolean flag = true;
            0xff & motionevent.getAction();
            JVM INSTR tableswitch 0 2: default 36
        //                       0 38
        //                       1 91
        //                       2 182;
               goto _L1 _L2 _L3 _L4
_L1:
            return false;
_L2:
            float f3 = motionevent.getX();
            mLeftDragAction.mLastMotionX = f3;
            DragAction dragaction1 = mLeftDragAction;
            if (mAboveView.getScrollX() == 0)
            {
                flag = false;
            }
            dragaction1.mDraggable = flag;
            return false;
_L3:
            if (mLeftDragAction.mDraggable)
            {
                int k = mAboveView.getScrollX();
                int l;
                if (mLeftDragAction.mOpening)
                {
                    l = -(k + mLeftBehindViewWidth);
                } else
                {
                    l = -k;
                }
                mScroller.startScroll(k, 0, l, 0, mDurationLeft);
                invalidate();
                return false;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            if (mLeftDragAction.mDraggable)
            {
                float f = motionevent.getX();
                float f1 = -(f - mLeftDragAction.mLastMotionX);
                int j = mAboveView.getScrollX();
                DragAction dragaction = mLeftDragAction;
                float f2;
                if (mLeftDragAction.mLastMotionX >= f)
                {
                    flag = false;
                }
                dragaction.mOpening = flag;
                mLeftDragAction.mLastMotionX = f;
                f2 = f1 + (float)j;
                if (0.0F < f2)
                {
                    mAboveView.scrollTo(0, 0);
                    return false;
                }
                if (f2 < (float)(-mLeftBehindViewWidth))
                {
                    mAboveView.scrollTo(-mLeftBehindViewWidth, 0);
                    return false;
                } else
                {
                    mAboveView.scrollBy((int)f1, 0);
                    return false;
                }
            }
            if (true) goto _L1; else goto _L5
_L5:
        }

            
            {
                this$0 = SimpleSideDrawer.this;
                super(null);
            }
    };
    private Rect mLeftPaddingRect;
    private final View mOverlay;
    private final LinearLayout mRightBehindBase;
    private View mRightBehindView;
    private int mRightBehindViewWidth;
    private DragAction mRightDragAction = new DragAction() {

        final SimpleSideDrawer this$0;

        public boolean onTouchEvent(MotionEvent motionevent)
        {
            boolean flag = true;
            0xff & motionevent.getAction();
            JVM INSTR tableswitch 0 2: default 36
        //                       0 38
        //                       1 91
        //                       2 181;
               goto _L1 _L2 _L3 _L4
_L1:
            return false;
_L2:
            float f3 = motionevent.getX();
            mRightDragAction.mLastMotionX = f3;
            DragAction dragaction1 = mRightDragAction;
            if (mAboveView.getScrollX() == 0)
            {
                flag = false;
            }
            dragaction1.mDraggable = flag;
            return false;
_L3:
            if (mRightDragAction.mDraggable)
            {
                int k = mAboveView.getScrollX();
                int l;
                if (mRightDragAction.mOpening)
                {
                    l = mRightBehindViewWidth - k;
                } else
                {
                    l = -k;
                }
                mScroller.startScroll(k, 0, l, 0, mDurationRight);
                invalidate();
                return false;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            if (mRightDragAction.mDraggable)
            {
                float f = motionevent.getX();
                float f1 = -(f - mRightDragAction.mLastMotionX);
                int j = mAboveView.getScrollX();
                DragAction dragaction = mRightDragAction;
                float f2;
                if (f >= mRightDragAction.mLastMotionX)
                {
                    flag = false;
                }
                dragaction.mOpening = flag;
                mRightDragAction.mLastMotionX = f;
                f2 = f1 + (float)j;
                if (f2 < 0.0F)
                {
                    mAboveView.scrollTo(0, 0);
                    return false;
                }
                if (f2 < (float)mRightBehindViewWidth)
                {
                    mAboveView.scrollBy((int)f1, 0);
                    return false;
                } else
                {
                    mAboveView.scrollTo(mRightBehindViewWidth, 0);
                    return false;
                }
            }
            if (true) goto _L1; else goto _L5
_L5:
        }

            
            {
                this$0 = SimpleSideDrawer.this;
                super(null);
            }
    };
    private Rect mRightPaddingRect;
    private Scroller mScroller;
    private final Window mWindow;

    public SimpleSideDrawer(Activity activity)
    {
        this(activity, ((Interpolator) (new DecelerateInterpolator(0.9F))), 180);
    }

    public SimpleSideDrawer(Activity activity, Interpolator interpolator, int i)
    {
        super(activity.getApplicationContext());
        Context context = activity.getApplicationContext();
        mDurationLeft = i;
        mDurationRight = i;
        mWindow = activity.getWindow();
        mScroller = new Scroller(context, interpolator);
        mBehindView = new BehindLinearLayout(context);
        mBehindView.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        mBehindView.setOrientation(0);
        mLeftBehindBase = new BehindLinearLayout(context);
        mBehindView.addView(mLeftBehindBase, new android.widget.LinearLayout.LayoutParams(-2, -1));
        mBehindView.addView(new View(context), new android.widget.LinearLayout.LayoutParams(0, -1, 1.0F));
        mRightBehindBase = new BehindLinearLayout(context);
        mBehindView.addView(mRightBehindBase, new android.widget.LinearLayout.LayoutParams(-2, -1));
        addView(mBehindView);
        mAboveView = new FrameLayout(context);
        mAboveView.setLayoutParams(new android.widget.FrameLayout.LayoutParams(-1, -1));
        mOverlay = new OverlayView(getContext());
        mOverlay.setLayoutParams(new android.widget.FrameLayout.LayoutParams(-1, -1, 80));
        mOverlay.setEnabled(true);
        mOverlay.setVisibility(8);
        mOverlay.setOnClickListener(new android.view.View.OnClickListener() {

            final SimpleSideDrawer this$0;

            public void onClick(View view)
            {
                if (mLeftBehindBase.getVisibility() != 8)
                {
                    closeLeftSide();
                } else
                if (mRightBehindBase.getVisibility() != 8)
                {
                    closeRightSide();
                    return;
                }
            }

            
            {
                this$0 = SimpleSideDrawer.this;
                super();
            }
        });
        ViewGroup viewgroup = (ViewGroup)mWindow.getDecorView();
        ViewGroup viewgroup1 = (ViewGroup)viewgroup.getChildAt(0);
        viewgroup.removeView(viewgroup1);
        viewgroup1.setBackgroundDrawable(viewgroup.getBackground());
        mAboveView.addView(viewgroup1);
        mAboveView.addView(mOverlay);
        viewgroup.addView(this);
        addView(mAboveView);
    }

    private boolean isLeftSideOpened()
    {
        return mLeftBehindBase.getVisibility() == 0 && mRightBehindBase.getVisibility() == 8;
    }

    private boolean isRightSideOpened()
    {
        return mRightBehindBase.getVisibility() == 0 && mLeftBehindBase.getVisibility() == 8;
    }

    public void close()
    {
        closeLeftSide();
    }

    public void closeLeftSide()
    {
        int i = -mLeftBehindViewWidth;
        mScroller.startScroll(i, 0, -i, 0, mDurationLeft);
        invalidate();
    }

    public void closeRightSide()
    {
        int i = mRightBehindViewWidth;
        mScroller.startScroll(i, 0, -i, 0, mDurationRight);
        invalidate();
    }

    public void computeScroll()
    {
        if (mScroller.computeScrollOffset())
        {
            mAboveView.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
            return;
        }
        if (mAboveView.getScrollX() == 0)
        {
            mOverlay.setVisibility(8);
            mLeftBehindBase.setVisibility(8);
            mRightBehindBase.setVisibility(8);
            return;
        } else
        {
            mOverlay.setVisibility(0);
            return;
        }
    }

    public View getLeftBehindView()
    {
        return mLeftBehindBase.getChildAt(0);
    }

    public View getRightBehindView()
    {
        return mRightBehindBase.getChildAt(0);
    }

    public boolean isClosed()
    {
        return mAboveView != null && mAboveView.getScrollX() == 0;
    }

    public void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        mLeftBehindViewWidth = mLeftBehindBase.getMeasuredWidth();
        mRightBehindViewWidth = mRightBehindBase.getMeasuredWidth();
        ViewGroup viewgroup = (ViewGroup)mWindow.getDecorView();
        Rect rect = new Rect();
        viewgroup.getWindowVisibleDisplayFrame(rect);
        mBehindView.fitDisplay(rect);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (isLeftSideOpened())
        {
            return mLeftDragAction.onTouchEvent(motionevent);
        }
        if (isRightSideOpened())
        {
            return mRightDragAction.onTouchEvent(motionevent);
        } else
        {
            return true;
        }
    }

    public void open()
    {
        openLeftSide();
    }

    public void openLeftSide()
    {
        mLeftBehindBase.setVisibility(0);
        mRightBehindBase.setVisibility(8);
        int i = mAboveView.getScrollX();
        mScroller.startScroll(i, 0, -mLeftBehindViewWidth, 0, mDurationLeft);
        invalidate();
    }

    public void openRightSide()
    {
        mRightBehindBase.setVisibility(0);
        mLeftBehindBase.setVisibility(8);
        int i = mAboveView.getScrollX();
        mScroller.startScroll(i, 0, mRightBehindViewWidth, 0, mDurationRight);
        invalidate();
    }

    public void setAnimationDuration(int i)
    {
        setAnimationDurationLeft(i);
    }

    public void setAnimationDurationLeft(int i)
    {
        mDurationLeft = i;
    }

    public void setAnimationDurationRight(int i)
    {
        mDurationRight = i;
    }

    public View setBehindContentView(int i)
    {
        return setLeftBehindContentView(i);
    }

    public View setLeftBehindContentView(int i)
    {
        View view = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(i, mLeftBehindBase);
        mLeftPaddingRect = new Rect(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        mLeftBehindView = view;
        return view;
    }

    public View setRightBehindContentView(int i)
    {
        View view = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(i, mRightBehindBase);
        mRightPaddingRect = new Rect(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        mRightBehindView = view;
        return view;
    }

    public void setScrollInterpolator(Interpolator interpolator)
    {
        mScroller = new Scroller(getContext(), interpolator);
    }

    public void toggleDrawer()
    {
        toggleLeftDrawer();
    }

    public void toggleLeftDrawer()
    {
        if (isClosed())
        {
            openLeftSide();
            return;
        } else
        {
            closeLeftSide();
            return;
        }
    }

    public void toggleRightDrawer()
    {
        if (isClosed())
        {
            openRightSide();
            return;
        } else
        {
            closeRightSide();
            return;
        }
    }











}
