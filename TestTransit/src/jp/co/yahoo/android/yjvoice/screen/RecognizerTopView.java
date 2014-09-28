// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yjvoice.screen;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

// Referenced classes of package jp.co.yahoo.android.yjvoice.screen:
//            RecognizerTopViewListener

class RecognizerTopView extends FrameLayout
{

    private static final String TAG = "RecognizerTopView";
    private RecognizerTopViewListener mListener;

    public RecognizerTopView(Context context)
    {
        super(context);
        mListener = null;
    }

    public RecognizerTopView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mListener = null;
    }

    public RecognizerTopView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mListener = null;
    }

    protected void onAttachedToWindow()
    {
        if (mListener != null)
        {
            mListener.onAttachedToWindow();
        }
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow()
    {
        if (mListener != null)
        {
            mListener.onDetachedFromWindow();
        }
        super.onDetachedFromWindow();
    }

    public void setListener(RecognizerTopViewListener recognizertopviewlistener)
    {
        mListener = recognizertopviewlistener;
    }
}
