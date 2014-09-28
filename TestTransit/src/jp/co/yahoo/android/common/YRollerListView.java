// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class YRollerListView extends ListView
    implements android.widget.AbsListView.OnScrollListener
{

    private static final int MSG_SCROLL_CHECK = 1;
    private static final int MSG_SCROLL_CONFIRM = 2;
    private static final String TAG = jp/co/yahoo/android/common/YRollerListView.getSimpleName();
    private int _itemHeight;
    private int _pullBackItemPositionFromBottom;
    private int _pullBackItemPositionFromTop;
    private Handler _scrollDetector = new Handler() {

        private int _scrollY;
        final YRollerListView this$0;

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 2: default 28
        //                       1 29
        //                       2 59;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            _scrollY = getScrollY();
            Message message1 = Message.obtain();
            message1.what = 2;
            sendMessageDelayed(message1, 10L);
            return;
_L3:
            if (getScrollY() - _scrollY == 0)
            {
                removeMessages(1);
                removeMessages(2);
                onScrollStateChanged(YRollerListView.this, 0);
                return;
            }
            if (true) goto _L1; else goto _L4
_L4:
        }

            
            {
                this$0 = YRollerListView.this;
                super();
            }
    };
    private int _updateItemPositionFromBottom;
    private int _updateItemPositionFromTop;

    public YRollerListView(Context context)
    {
        super(context);
        _itemHeight = 0;
        _updateItemPositionFromTop = 0;
        _updateItemPositionFromBottom = 0;
        _pullBackItemPositionFromTop = 0;
        _pullBackItemPositionFromBottom = 0;
        init();
    }

    public YRollerListView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        _itemHeight = 0;
        _updateItemPositionFromTop = 0;
        _updateItemPositionFromBottom = 0;
        _pullBackItemPositionFromTop = 0;
        _pullBackItemPositionFromBottom = 0;
        init();
    }

    public YRollerListView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        _itemHeight = 0;
        _updateItemPositionFromTop = 0;
        _updateItemPositionFromBottom = 0;
        _pullBackItemPositionFromTop = 0;
        _pullBackItemPositionFromBottom = 0;
        init();
    }

    private void init()
    {
        super.setOnScrollListener(this);
    }

    public void addFooterView(View view)
    {
        super.addFooterView(view);
    }

    public void addFooterView(View view, Object obj, boolean flag)
    {
        super.addFooterView(view, obj, flag);
    }

    public void addHeaderView(View view)
    {
        super.addHeaderView(view);
    }

    public void addHeaderView(View view, Object obj, boolean flag)
    {
        super.addHeaderView(view, obj, flag);
    }

    public int getItemHeight()
    {
        return _itemHeight;
    }

    public int getSelectedItemPosition()
    {
        return pointToPosition(0, getHeight() / 2);
    }

    public int getSelectedItemVisiblePosition()
    {
        return getSelectedItemPosition() - getFirstVisiblePosition();
    }

    public void onScroll(AbsListView abslistview, int i, int j, int k)
    {
    }

    public void onScrollStateChanged(AbsListView abslistview, int i)
    {
        if (getAdapter() == null || i != 0 || getAdapter().getCount() <= 0) goto _L2; else goto _L1
_L1:
        int j = pointToPosition(0, getHeight() / 2);
        if (j > _updateItemPositionFromTop) goto _L4; else goto _L3
_L3:
        j += _pullBackItemPositionFromTop;
_L6:
        setSelectionFromTop(j, (getHeight() - _itemHeight) / 2);
_L2:
        return;
_L4:
        if (j >= _updateItemPositionFromBottom)
        {
            j -= _pullBackItemPositionFromBottom;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (motionevent.getAction() == 1)
        {
            _scrollDetector.sendEmptyMessage(1);
        }
        return super.onTouchEvent(motionevent);
    }

    public void setItemHeight(int i)
    {
        if (i > 0)
        {
            _itemHeight = i;
        }
    }

    public void setOnScrollListener(final android.widget.AbsListView.OnScrollListener l)
    {
        super.setOnScrollListener(new android.widget.AbsListView.OnScrollListener() {

            final YRollerListView this$0;
            final android.widget.AbsListView.OnScrollListener val$l;

            public void onScroll(AbsListView abslistview, int i, int j, int k)
            {
                l.onScroll(abslistview, i, j, k);
            }

            public void onScrollStateChanged(AbsListView abslistview, int i)
            {
                l.onScrollStateChanged(abslistview, i);
                YRollerListView.this.onScrollStateChanged(abslistview, i);
            }

            
            {
                this$0 = YRollerListView.this;
                l = onscrolllistener;
                super();
            }
        });
    }

    public void setPullBackItemPositionFromBottom(int i)
    {
        for (ListAdapter listadapter = getAdapter(); listadapter != null && listadapter.getCount() < i || i <= 0;)
        {
            return;
        }

        _pullBackItemPositionFromBottom = i;
    }

    public void setPullBackItemPositionFromTop(int i)
    {
        for (ListAdapter listadapter = getAdapter(); listadapter != null && listadapter.getCount() < i || i <= 0;)
        {
            return;
        }

        _pullBackItemPositionFromTop = i;
    }

    public void setUpdateItemPositionFromBottom(int i)
    {
        for (ListAdapter listadapter = getAdapter(); listadapter != null && listadapter.getCount() < i || i <= 0;)
        {
            return;
        }

        _updateItemPositionFromBottom = i;
    }

    public void setUpdateItemPositionFromTop(int i)
    {
        for (ListAdapter listadapter = getAdapter(); listadapter != null && listadapter.getCount() < i || i <= 0;)
        {
            return;
        }

        _updateItemPositionFromTop = i;
    }

}
