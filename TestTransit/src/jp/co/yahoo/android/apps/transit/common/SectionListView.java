// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            SectionListBaseAdapter

public class SectionListView extends ListView
    implements android.widget.AbsListView.OnScrollListener
{
    public static abstract class OnItemClickListener
        implements android.widget.AdapterView.OnItemClickListener
    {

        public abstract void onItemClick(AdapterView adapterview, View view, int i, int j, long l);

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            SectionListBaseAdapter sectionlistbaseadapter;
            int j;
            int k;
            if (adapterview.getAdapter().getClass().equals(android/widget/HeaderViewListAdapter))
            {
                sectionlistbaseadapter = (SectionListBaseAdapter)((HeaderViewListAdapter)adapterview.getAdapter()).getWrappedAdapter();
            } else
            {
                sectionlistbaseadapter = (SectionListBaseAdapter)adapterview.getAdapter();
            }
            j = sectionlistbaseadapter.getSectionForPosition(i);
            k = sectionlistbaseadapter.getPositionInSectionForPosition(i);
            if (k == -1)
            {
                onSectionClick(adapterview, view, j, l);
                return;
            } else
            {
                onItemClick(adapterview, view, j, k, l);
                return;
            }
        }

        public abstract void onSectionClick(AdapterView adapterview, View view, int i, long l);

        public OnItemClickListener()
        {
        }
    }

    public static interface SectionListAdapter
    {

        public abstract int getCount();

        public abstract int getItemViewType(int i);

        public abstract int getPositionOfSection(int i);

        public abstract int getSectionForPosition(int i);

        public abstract View getSectionHeaderView(int i, View view, ViewGroup viewgroup);

        public abstract int getSectionHeaderViewType(int i);

        public abstract boolean isSectionHeader(int i);
    }


    private SectionListAdapter adapter;
    private View currentHeader;
    private int currentHeaderViewType;
    private int currentSection;
    private float headerOffset;
    private int itemHeight;
    private Float popupOffset;
    private int popupOffsetX;
    private int popupOffsetY;
    private int popupPosition;
    private int popupSection;
    private Object popupTag;
    private View popupView;
    private android.widget.AbsListView.OnScrollListener scrollListener;
    private int sectionHeight;
    private boolean shouldPin;
    private int widthMode;
    private WindowManager windowManager;

    public SectionListView(Context context)
    {
        super(context);
        currentHeaderViewType = 0;
        shouldPin = true;
        currentSection = 0;
        popupSection = -1;
        popupPosition = -1;
        popupOffset = null;
        sectionHeight = -1;
        itemHeight = -1;
        super.setOnScrollListener(this);
    }

    public SectionListView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        currentHeaderViewType = 0;
        shouldPin = true;
        currentSection = 0;
        popupSection = -1;
        popupPosition = -1;
        popupOffset = null;
        sectionHeight = -1;
        itemHeight = -1;
        super.setOnScrollListener(this);
    }

    public SectionListView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        currentHeaderViewType = 0;
        shouldPin = true;
        currentSection = 0;
        popupSection = -1;
        popupPosition = -1;
        popupOffset = null;
        sectionHeight = -1;
        itemHeight = -1;
        super.setOnScrollListener(this);
    }

    private void ensureSectionHeaderLayout(View view)
    {
        if (view.isLayoutRequested())
        {
            int i = android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), widthMode);
            android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
            int j;
            if (layoutparams != null && layoutparams.height > 0)
            {
                j = android.view.View.MeasureSpec.makeMeasureSpec(layoutparams.height, 0x40000000);
            } else
            {
                j = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
            }
            view.measure(i, j);
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    private View getSectionHeaderView(int i, View view)
    {
        boolean flag;
        View view1;
        if (i != currentSection || view == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        view1 = adapter.getSectionHeaderView(i, view, this);
        if (flag)
        {
            ensureSectionHeaderLayout(view1);
            currentSection = i;
        }
        return view1;
    }

    protected void dispatchDraw(Canvas canvas)
    {
        super.dispatchDraw(canvas);
        if (adapter != null && shouldPin && currentHeader != null)
        {
            int j = canvas.save();
            canvas.translate(0.0F, headerOffset);
            canvas.clipRect(0, 0, getWidth(), currentHeader.getMeasuredHeight());
            currentHeader.draw(canvas);
            canvas.restoreToCount(j);
            Drawable drawable = getDivider();
            if (drawable != null && getDividerHeight() > 0)
            {
                int k = (int)(headerOffset + (float)currentHeader.getMeasuredHeight());
                drawable.setBounds(0, k, getWidth(), k + getDividerHeight());
                drawable.draw(canvas);
            }
        }
        if (popupView != null && popupOffset != null)
        {
            int i = canvas.save();
            canvas.translate(popupOffsetX, popupOffset.floatValue());
            canvas.clipRect(0, 0, popupView.getWidth(), popupView.getMeasuredHeight());
            popupView.draw(canvas);
            canvas.restoreToCount(i);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        if (popupView != null && popupOffset != null && (motionevent.getAction() == 1 || motionevent.getAction() == 3 || motionevent.getAction() == 0) && (new RectF(popupOffsetX, popupOffset.floatValue(), popupOffsetX + popupView.getWidth(), popupOffset.floatValue() + (float)popupView.getHeight())).contains(motionevent.getX(), motionevent.getY()))
        {
            MotionEvent motionevent1 = MotionEvent.obtain(motionevent);
            motionevent1.offsetLocation(-popupOffsetX, -popupOffset.floatValue());
            popupView.dispatchTouchEvent(motionevent1);
            motionevent1.recycle();
        }
        return super.dispatchTouchEvent(motionevent);
    }

    public int[] getFirstPosition()
    {
        SectionListBaseAdapter sectionlistbaseadapter;
        int i;
        int k;
        int l;
        if (getAdapter().getClass().equals(android/widget/HeaderViewListAdapter))
        {
            sectionlistbaseadapter = (SectionListBaseAdapter)((HeaderViewListAdapter)getAdapter()).getWrappedAdapter();
        } else
        {
            sectionlistbaseadapter = (SectionListBaseAdapter)getAdapter();
        }
        i = getFirstVisiblePosition();
        if (i >= getHeaderViewsCount()) goto _L2; else goto _L1
_L1:
        k = -2;
        l = -1;
_L4:
        return (new int[] {
            k, l
        });
_L2:
        if (i >= super.getCount() - getFooterViewsCount())
        {
            k = -3;
            l = -1;
        } else
        {
            int j = i - getHeaderViewsCount();
            k = sectionlistbaseadapter.getSectionForPosition(j);
            l = sectionlistbaseadapter.getPositionInSectionForPosition(j);
            if (l == -1)
            {
                l = 0;
            } else
            {
                View view = getChildAt(0);
                if (view.getHeight() + view.getTop() <= sectionHeight)
                {
                    if (l >= -1 + sectionlistbaseadapter.getCountForSection(k))
                    {
                        if (k >= -1 + sectionlistbaseadapter.getSectionCount())
                        {
                            l = -1 + sectionlistbaseadapter.getCountForSection(k);
                        } else
                        {
                            k++;
                            l = 0;
                        }
                    } else
                    {
                        l++;
                    }
                }
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void hidePopupView()
    {
        if (windowManager != null && popupView != null)
        {
            windowManager.removeViewImmediate(popupView);
        }
        popupView = null;
        popupOffset = null;
        invalidateViews();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
label0:
        {
            View view = popupView;
            flag = false;
            if (view == null)
            {
                break label0;
            }
            Float float1 = popupOffset;
            flag = false;
            if (float1 == null)
            {
                break label0;
            }
            if (motionevent.getAction() != 1)
            {
                int i = motionevent.getAction();
                flag = false;
                if (i != 3)
                {
                    break label0;
                }
            }
            boolean flag1 = (new RectF(popupOffsetX, popupOffset.floatValue(), popupOffsetX + popupView.getWidth(), popupOffset.floatValue() + (float)popupView.getHeight())).contains(motionevent.getX(), motionevent.getY());
            flag = false;
            if (flag1)
            {
                flag = true;
            }
        }
        if (flag)
        {
            return flag;
        } else
        {
            return super.onInterceptTouchEvent(motionevent);
        }
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        widthMode = android.view.View.MeasureSpec.getMode(i);
    }

    public void onScroll(AbsListView abslistview, int i, int j, int k)
    {
        if (scrollListener != null)
        {
            scrollListener.onScroll(abslistview, i, j, k);
        }
        if (sectionHeight != -1) goto _L2; else goto _L1
_L1:
        int k3 = i;
_L18:
        if (k3 >= i + j) goto _L2; else goto _L3
_L3:
        if (k3 < getHeaderViewsCount() || !adapter.isSectionHeader(k3 - getHeaderViewsCount())) goto _L5; else goto _L4
_L4:
        sectionHeight = abslistview.getChildAt(k3 - i).getHeight();
_L2:
        if (itemHeight != -1) goto _L7; else goto _L6
_L6:
        int j3 = i;
_L15:
        if (j3 >= i + j) goto _L7; else goto _L8
_L8:
        if (j3 < getHeaderViewsCount() || adapter.isSectionHeader(j3 - getHeaderViewsCount())) goto _L10; else goto _L9
_L9:
        itemHeight = abslistview.getChildAt(j3 - i).getHeight();
_L7:
        if (popupView == null) goto _L12; else goto _L11
_L11:
        int l1;
        int i2;
        l1 = i - getHeaderViewsCount();
        i2 = 1 + (adapter.getPositionOfSection(popupSection) + popupPosition);
        if (i2 >= l1) goto _L14; else goto _L13
_L5:
        k3++;
        continue; /* Loop/switch isn't completed */
_L10:
        j3++;
          goto _L15
_L13:
        int j2 = 0;
        int k2 = i2;
        while (k2 < l1) 
        {
            int i3;
            if (adapter.isSectionHeader(k2))
            {
                i3 = j2 - sectionHeight;
            } else
            {
                i3 = j2 - itemHeight;
            }
            j2 = i3 - getDividerHeight();
            k2++;
        }
        View view2 = abslistview.getChildAt(0);
        int l2;
        if (view2 == null)
        {
            l2 = 0;
        } else
        {
            l2 = view2.getTop();
        }
        popupOffset = Float.valueOf(j2 + l2 + popupOffsetY);
_L12:
        if (adapter == null || adapter.getCount() == 0 || !shouldPin || i < getHeaderViewsCount() || i >= k - getFooterViewsCount())
        {
            currentHeader = null;
            headerOffset = 0.0F;
            return;
        }
        break; /* Loop/switch isn't completed */
_L14:
        if (i2 >= l1 + j)
        {
            popupOffset = null;
        } else
        {
            View view1 = findViewWithTag(popupTag);
            if (view1 != null)
            {
                popupOffset = Float.valueOf(((View)view1.getParent()).getTop() + popupOffsetY);
            }
        }
        if (true) goto _L12; else goto _L16
_L16:
        int l = i - getHeaderViewsCount();
        int i1 = adapter.getSectionForPosition(l);
        int j1 = adapter.getSectionHeaderViewType(i1);
        View view;
        if (currentHeaderViewType != j1)
        {
            view = null;
        } else
        {
            view = currentHeader;
        }
        currentHeader = getSectionHeaderView(i1, view);
        ensureSectionHeaderLayout(currentHeader);
        currentHeaderViewType = j1;
        headerOffset = 0.0F;
        for (int k1 = l; k1 < l + j; k1++)
        {
            if (!adapter.isSectionHeader(k1) && k1 + getHeaderViewsCount() < k - getFooterViewsCount())
            {
                continue;
            }
            float f = abslistview.getChildAt(k1 - l).getTop();
            if ((float)currentHeader.getMeasuredHeight() >= f && f > 0.0F)
            {
                headerOffset = f - (float)currentHeader.getHeight();
            }
        }

        invalidate();
        return;
        if (true) goto _L18; else goto _L17
_L17:
    }

    public void onScrollStateChanged(AbsListView abslistview, int i)
    {
        if (scrollListener != null)
        {
            scrollListener.onScrollStateChanged(abslistview, i);
        }
    }

    public void selectPosFromTop(final int position, final int offset)
    {
        post(new Runnable() {

            final SectionListView this$0;
            final int val$offset;
            final int val$position;

            public void run()
            {
                setSelectionFromTop(position, offset);
            }

            
            {
                this$0 = SectionListView.this;
                position = i;
                offset = j;
                super();
            }
        });
    }

    public volatile void setAdapter(Adapter adapter1)
    {
        setAdapter((ListAdapter)adapter1);
    }

    public void setAdapter(ListAdapter listadapter)
    {
        currentHeader = null;
        adapter = (SectionListAdapter)listadapter;
        super.setAdapter(listadapter);
    }

    public void setOnItemClickListener(OnItemClickListener onitemclicklistener)
    {
        super.setOnItemClickListener(onitemclicklistener);
    }

    public void setOnScrollListener(android.widget.AbsListView.OnScrollListener onscrolllistener)
    {
        scrollListener = onscrolllistener;
    }

    public void setPinHeaders(boolean flag)
    {
        shouldPin = flag;
    }

    public void showPopupView(final View view, Object obj, int i, int j, int k, int l)
    {
        if (windowManager != null) goto _L2; else goto _L1
_L1:
        windowManager = (WindowManager)getContext().getSystemService("window");
_L4:
        view.setVisibility(4);
        android.view.WindowManager.LayoutParams layoutparams = new android.view.WindowManager.LayoutParams();
        layoutparams.gravity = 51;
        layoutparams.height = view.getLayoutParams().height;
        layoutparams.width = view.getLayoutParams().width;
        layoutparams.flags = 792;
        layoutparams.format = -3;
        layoutparams.windowAnimations = 0;
        windowManager.addView(view, layoutparams);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new android.view.ViewTreeObserver.OnGlobalLayoutListener() {

            final SectionListView this$0;
            final View val$view;

            public void onGlobalLayout()
            {
                popupView = view;
            }

            
            {
                this$0 = SectionListView.this;
                view = view1;
                super();
            }
        });
        popupTag = obj;
        popupSection = i;
        popupPosition = j;
        popupOffsetX = k;
        popupOffsetY = l;
        popupOffset = null;
        invalidateViews();
        post(new Runnable() {

            final SectionListView this$0;

            public void run()
            {
                invalidateViews();
            }

            
            {
                this$0 = SectionListView.this;
                super();
            }
        });
        return;
_L2:
        if (popupView != null && popupView.getParent() != null)
        {
            windowManager.removeViewImmediate(popupView);
            popupView = null;
            popupOffset = null;
        } else
        if (view != null && view.getParent() != null)
        {
            windowManager.removeViewImmediate(view);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void smoothScrollToPosFromTop(final int position, final int offset)
    {
        post(new Runnable() {

            final SectionListView this$0;
            final int val$offset;
            final int val$position;

            public void run()
            {
                if (android.os.Build.VERSION.SDK_INT >= 11)
                {
                    smoothScrollToPositionFromTop(position, offset);
                    return;
                } else
                {
                    setSelectionFromTop(position, offset);
                    return;
                }
            }

            
            {
                this$0 = SectionListView.this;
                position = i;
                offset = j;
                super();
            }
        });
    }


/*
    static View access$002(SectionListView sectionlistview, View view)
    {
        sectionlistview.popupView = view;
        return view;
    }

*/
}
