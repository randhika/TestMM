// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class DragShadowView extends ImageView
{

    private static final int BACKGROUND_COLOR = Color.argb(0, 0, 0, 0);
    private static final android.graphics.Bitmap.Config DRAG_BITMAP_CONFIG;
    private int baseLocation[];
    private Rect dragRect;
    private boolean dragging;
    private android.view.WindowManager.LayoutParams layoutParams;
    private int viewLocation[];
    private int viewSize[];
    private WindowManager windowManager;

    public DragShadowView(Context context)
    {
        super(context);
        dragging = false;
        baseLocation = new int[2];
        viewLocation = new int[2];
        viewSize = new int[2];
        windowManager = (WindowManager)context.getSystemService("window");
        initLayoutParams();
    }

    private void initLayoutParams()
    {
        layoutParams = new android.view.WindowManager.LayoutParams();
        layoutParams.gravity = 51;
        layoutParams.height = -2;
        layoutParams.width = -2;
        layoutParams.flags = 920;
        layoutParams.format = -3;
        layoutParams.windowAnimations = 0;
    }

    private void setDragBitmap(View view)
    {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), DRAG_BITMAP_CONFIG);
        Canvas canvas = new Canvas();
        canvas.setBitmap(bitmap);
        view.draw(canvas);
        setImageBitmap(bitmap);
        setBackgroundColor(BACKGROUND_COLOR);
    }

    private void updateLayoutParams(int i, int j)
    {
        int k = (i + viewLocation[0]) - baseLocation[0];
        int l = (j + viewLocation[1]) - baseLocation[1];
        if (k < dragRect.left)
        {
            k = dragRect.left;
        } else
        if (k + viewSize[0] > dragRect.right)
        {
            k = dragRect.right - viewSize[0];
        }
        if (l < dragRect.top)
        {
            l = dragRect.top;
        } else
        if (l + viewSize[1] > dragRect.bottom)
        {
            l = dragRect.bottom - viewSize[1];
        }
        layoutParams.x = k;
        layoutParams.y = l;
    }

    public void doDrag(int i, int j)
    {
        if (!dragging)
        {
            return;
        } else
        {
            updateLayoutParams(i, j);
            windowManager.updateViewLayout(this, layoutParams);
            return;
        }
    }

    public int[] getViewLocation()
    {
        if (layoutParams != null)
        {
            int ai[] = new int[2];
            ai[0] = layoutParams.x;
            ai[1] = layoutParams.y;
            return ai;
        } else
        {
            return null;
        }
    }

    public void startDrag(int i, int j, View view, Rect rect)
    {
        if (dragging)
        {
            stopDrag();
        }
        dragRect = rect;
        baseLocation[0] = i;
        baseLocation[1] = j;
        view.getLocationInWindow(viewLocation);
        setDragBitmap(view);
        viewSize[0] = view.getWidth();
        viewSize[1] = view.getHeight();
        updateLayoutParams(i, j);
        windowManager.addView(this, layoutParams);
        dragging = true;
    }

    public void stopDrag()
    {
        if (!dragging)
        {
            return;
        } else
        {
            windowManager.removeView(this);
            dragging = false;
            return;
        }
    }

    static 
    {
        DRAG_BITMAP_CONFIG = android.graphics.Bitmap.Config.ARGB_8888;
    }
}
