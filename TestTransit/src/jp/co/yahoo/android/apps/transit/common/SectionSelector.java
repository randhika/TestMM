// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;

public class SectionSelector extends View
{

    private final int PADDING;
    private final float TEXT_SIZE;
    private float charHeight;
    private boolean initSize;
    private ListView list;
    private Paint paint;
    private SectionIndexer sectionIndexer;
    private String sections[];
    private float textY;
    private float widthCenter;

    public SectionSelector(Context context)
    {
        super(context);
        PADDING = 10;
        TEXT_SIZE = 20F;
        sectionIndexer = null;
        initSize = false;
        widthCenter = -1F;
        charHeight = -1F;
        textY = -1F;
        init();
    }

    public SectionSelector(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        PADDING = 10;
        TEXT_SIZE = 20F;
        sectionIndexer = null;
        initSize = false;
        widthCenter = -1F;
        charHeight = -1F;
        textY = -1F;
        init();
    }

    public SectionSelector(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        PADDING = 10;
        TEXT_SIZE = 20F;
        sectionIndexer = null;
        initSize = false;
        widthCenter = -1F;
        charHeight = -1F;
        textY = -1F;
        init();
    }

    private int getPaddedHeight()
    {
        return -10 + getHeight();
    }

    private void init()
    {
        paint = new Paint();
        paint.setColor(0xff999999);
        paint.setTextSize(20F);
        paint.setTextAlign(android.graphics.Paint.Align.CENTER);
        paint.setFakeBoldText(true);
        paint.setAntiAlias(true);
    }

    protected void onDraw(Canvas canvas)
    {
        if (!initSize)
        {
            charHeight = (float)getPaddedHeight() / (float)sections.length;
            float f;
            int i;
            float f1;
            if (charHeight < 20F)
            {
                float f2 = 0.9F * charHeight;
                paint.setTextSize(f2);
            } else
            {
                paint.setTextSize(20F);
            }
            f1 = -paint.getFontMetrics().ascent;
            widthCenter = getMeasuredWidth() / 2;
            textY = 5F + f1;
            initSize = true;
        }
        f = textY;
        for (i = 0; i < sections.length; i++)
        {
            canvas.drawText(String.valueOf(sections[i]), widthCenter, f, paint);
            f += charHeight;
        }

        super.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        super.onTouchEvent(motionevent);
        if (motionevent.getAction() == 0 || motionevent.getAction() == 2)
        {
            float f = ((float)(int)motionevent.getY() / (float)getPaddedHeight()) * (float)sections.length;
            if (sectionIndexer == null)
            {
                android.widget.ListAdapter listadapter = list.getAdapter();
                if (listadapter instanceof HeaderViewListAdapter)
                {
                    sectionIndexer = (SectionIndexer)((HeaderViewListAdapter)listadapter).getWrappedAdapter();
                } else
                {
                    sectionIndexer = (SectionIndexer)list.getAdapter();
                }
            }
            int i;
            if ((int)f < sections.length)
            {
                if ((i = sectionIndexer.getPositionForSection((int)f)) != -1)
                {
                    list.setSelection(i);
                    return true;
                }
            }
        }
        return true;
    }

    public void setListView(ListView listview)
    {
        list = listview;
        android.widget.ListAdapter listadapter = list.getAdapter();
        Object aobj[];
        if (listadapter instanceof HeaderViewListAdapter)
        {
            sectionIndexer = (SectionIndexer)((HeaderViewListAdapter)listadapter).getWrappedAdapter();
        } else
        {
            sectionIndexer = (SectionIndexer)list.getAdapter();
        }
        aobj = sectionIndexer.getSections();
        sections = new String[aobj.length];
        for (int i = 0; i < aobj.length; i++)
        {
            sections[i] = aobj[i].toString();
        }

        initSize = false;
        invalidate();
    }
}
