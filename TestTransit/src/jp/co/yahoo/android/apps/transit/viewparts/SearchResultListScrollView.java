// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class SearchResultListScrollView extends ScrollView
{

    private boolean scrolled;

    public SearchResultListScrollView(Context context)
    {
        super(context);
        scrolled = false;
    }

    public SearchResultListScrollView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        scrolled = false;
    }

    public SearchResultListScrollView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        scrolled = false;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (!scrolled)
        {
            scrollTo(0, 0);
            scrolled = true;
        }
    }

    public void setScrolledFalse()
    {
        scrolled = false;
    }
}
