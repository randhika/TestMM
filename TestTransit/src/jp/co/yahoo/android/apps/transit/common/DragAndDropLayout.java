// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            DragShadowView

public class DragAndDropLayout extends FrameLayout
    implements android.view.View.OnTouchListener
{
    public static interface DragAndDropListener
    {

        public abstract void onDrop(int i, int j);
    }


    private View dragView;
    private boolean dragging;
    private Rect layoutRect;
    private DragAndDropListener listener;
    private DragShadowView shadowView;

    public DragAndDropLayout(Context context)
    {
        super(context);
        dragging = false;
        initialize(context);
    }

    public DragAndDropLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        dragging = false;
        initialize(context);
    }

    public DragAndDropLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        dragging = false;
        initialize(context);
    }

    private boolean doDrag(MotionEvent motionevent)
    {
        if (!dragging)
        {
            return false;
        } else
        {
            int i = (int)motionevent.getX();
            int j = (int)motionevent.getY();
            shadowView.doDrag(i, j);
            return true;
        }
    }

    private void initialize(Context context)
    {
        shadowView = new DragShadowView(context);
    }

    private boolean isTouchDragView(MotionEvent motionevent)
    {
        int i = (int)motionevent.getRawX();
        int j = (int)motionevent.getRawY();
        if (layoutRect == null)
        {
            layoutRect = new Rect();
            getGlobalVisibleRect(layoutRect);
        }
        Rect rect = new Rect();
        dragView.getGlobalVisibleRect(rect);
        return rect.contains(i, j);
    }

    private boolean startDrag(MotionEvent motionevent)
    {
        dragging = false;
        int i = (int)motionevent.getRawX();
        int j = (int)motionevent.getRawY();
        if (!isTouchDragView(motionevent))
        {
            return false;
        } else
        {
            int k = i - layoutRect.left;
            int l = j - layoutRect.top;
            shadowView.startDrag(k, l, dragView, layoutRect);
            dragView.setVisibility(4);
            dragging = true;
            return true;
        }
    }

    private boolean stopDrag(MotionEvent motionevent)
    {
        if (!dragging)
        {
            return false;
        }
        shadowView.stopDrag();
        if (listener != null)
        {
            int ai[] = shadowView.getViewLocation();
            if (ai != null)
            {
                int i = ai[0] - layoutRect.left;
                int j = ai[1] - layoutRect.top;
                listener.onDrop(i, j);
            }
        }
        dragView.setVisibility(0);
        dragging = false;
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i;
        boolean flag;
        i = motionevent.getAction();
        flag = false;
        i;
        JVM INSTR tableswitch 0 4: default 44
    //                   0 56
    //                   1 74
    //                   2 65
    //                   3 74
    //                   4 74;
           goto _L1 _L2 _L3 _L4 _L3 _L3
_L1:
        if (!flag)
        {
            flag = super.onTouchEvent(motionevent);
        }
        return flag;
_L2:
        flag = startDrag(motionevent);
        continue; /* Loop/switch isn't completed */
_L4:
        flag = doDrag(motionevent);
        continue; /* Loop/switch isn't completed */
_L3:
        flag = stopDrag(motionevent);
        if (true) goto _L1; else goto _L5
_L5:
    }

    public void setDragView(View view, DragAndDropListener draganddroplistener)
    {
        dragView = view;
        listener = draganddroplistener;
        view.setOnTouchListener(this);
    }
}
