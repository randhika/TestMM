// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class FixedHeaderScrollView extends FrameLayout
{
    public static class BaseScrollView extends ScrollView
    {

        private View headerTopView;
        private View headerView;
        private OnHiddenHeaderListener listener;
        private int scrollStartY;
        private Runnable scrollerTask;

        private void fitScrollTop(final int scrollY)
        {
            if (headerTopView != null && headerView != null && headerView.getVisibility() == 0)
            {
                int i = headerTopView.getTop();
                if (scrollY > 0 && scrollY < i)
                {
                    post(i. new Runnable() {

                        final BaseScrollView this$0;
                        final int val$headerTop;
                        final int val$scrollY;

                        public void run()
                        {
                            if (scrollY < headerTop / 2)
                            {
                                smoothScrollTo(getScrollX(), 0);
                                return;
                            } else
                            {
                                smoothScrollTo(getScrollX(), headerTop);
                                return;
                            }
                        }

            
            {
                this$0 = final_basescrollview;
                scrollY = i;
                headerTop = I.this;
                super();
            }
                    });
                    return;
                }
            }
        }

        private void init()
        {
            scrollerTask = new Runnable() {

                final BaseScrollView this$0;

                public void run()
                {
                    int i = getScrollY();
                    if (scrollStartY - i == 0)
                    {
                        fitScrollTop(i);
                        return;
                    } else
                    {
                        startScrollerTask();
                        return;
                    }
                }

            
            {
                this$0 = BaseScrollView.this;
                super();
            }
            };
            setOnTouchListener(new android.view.View.OnTouchListener() {

                final BaseScrollView this$0;

                public boolean onTouch(View view, MotionEvent motionevent)
                {
                    while (headerView == null || headerView.getVisibility() != 0 || motionevent.getAction() != 1) 
                    {
                        return false;
                    }
                    startScrollerTask();
                    return false;
                }

            
            {
                this$0 = BaseScrollView.this;
                super();
            }
            });
        }

        private void startScrollerTask()
        {
            scrollStartY = getScrollY();
            postDelayed(scrollerTask, 100L);
        }

        protected void dispatchDraw(Canvas canvas)
        {
            super.dispatchDraw(canvas);
            while (headerTopView == null || headerView == null || headerView.getVisibility() != 0 || getScrollY() <= headerTopView.getTop()) 
            {
                return;
            }
            int i = canvas.save();
            canvas.translate(0.0F, getScrollY());
            canvas.clipRect(0, 0, getWidth(), headerView.getMeasuredHeight());
            headerView.draw(canvas);
            canvas.restoreToCount(i);
        }

        protected void onScrollChanged(int i, int j, int k, int l)
        {
            super.onScrollChanged(i, j, k, l);
            if (listener == null || headerTopView == null || headerView == null || headerView.getVisibility() != 0)
            {
                return;
            }
            if (getScrollY() > headerTopView.getTop())
            {
                listener.onHiddenBaseHeader(true);
                return;
            } else
            {
                listener.onHiddenBaseHeader(false);
                return;
            }
        }

        public void setHeaderTopView(View view)
        {
            headerTopView = view;
        }

        public void setHeaderView(View view)
        {
            headerView = view;
        }

        public void setHeaderView(View view, View view1)
        {
            headerView = view;
            headerTopView = view1;
        }

        public void setOnHiddenHeaderListener(OnHiddenHeaderListener onhiddenheaderlistener)
        {
            listener = onhiddenheaderlistener;
        }





        public BaseScrollView(Context context)
        {
            super(context);
            init();
        }

        public BaseScrollView(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            init();
        }

        public BaseScrollView(Context context, AttributeSet attributeset, int i)
        {
            super(context, attributeset, i);
            init();
        }
    }

    public static interface BaseScrollView.OnHiddenHeaderListener
    {

        public abstract void onHiddenBaseHeader(boolean flag);
    }

    public static class HeaderLinearLayout extends LinearLayout
    {

        private OnHeaderDrawListener listener;

        protected void dispatchDraw(Canvas canvas)
        {
            if (listener != null)
            {
                listener.onHeaderDraw();
            }
        }

        public boolean onTouchEvent(MotionEvent motionevent)
        {
            return true;
        }

        public void setOnHeaderDrawListener(OnHeaderDrawListener onheaderdrawlistener)
        {
            listener = onheaderdrawlistener;
        }

        public HeaderLinearLayout(Context context)
        {
            super(context);
        }

        public HeaderLinearLayout(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }
    }

    public static interface HeaderLinearLayout.OnHeaderDrawListener
    {

        public abstract void onHeaderDraw();
    }


    private HeaderLinearLayout headerLayout;
    private BaseScrollView scrollView;

    public FixedHeaderScrollView(Context context)
    {
        super(context);
    }

    public FixedHeaderScrollView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public FixedHeaderScrollView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    public void init(BaseScrollView basescrollview, View view)
    {
        scrollView = basescrollview;
        scrollView.setHeaderTopView(view);
        scrollView.setOnHiddenHeaderListener(new BaseScrollView.OnHiddenHeaderListener() {

            final FixedHeaderScrollView this$0;

            public void onHiddenBaseHeader(boolean flag)
            {
                if (flag && headerLayout.getVisibility() != 0)
                {
                    headerLayout.setVisibility(0);
                } else
                if (!flag && headerLayout.getVisibility() != 8)
                {
                    headerLayout.setVisibility(8);
                    return;
                }
            }

            
            {
                this$0 = FixedHeaderScrollView.this;
                super();
            }
        });
    }

    public void setHeaderView(View view, HeaderLinearLayout headerlinearlayout)
    {
        scrollView.setHeaderView(view);
        headerLayout = headerlinearlayout;
        headerLayout.setVisibility(8);
        headerLayout.setOnHeaderDrawListener(new HeaderLinearLayout.OnHeaderDrawListener() {

            final FixedHeaderScrollView this$0;

            public void onHeaderDraw()
            {
                post(new Runnable() {

                    final _cls2 this$1;

                    public void run()
                    {
                        scrollView.invalidate();
                    }

            
            {
                this$1 = _cls2.this;
                super();
            }
                });
            }

            
            {
                this$0 = FixedHeaderScrollView.this;
                super();
            }
        });
    }


}
