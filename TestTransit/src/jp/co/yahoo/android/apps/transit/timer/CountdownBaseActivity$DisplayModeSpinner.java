// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownBaseActivity

private class this._cls0 extends Spinner
{

    private android.widget.vity.DisplayModeSpinner.getSelectedView listener;
    final CountdownBaseActivity this$0;

    public void setOnItemSelectedListener(android.widget.vity.DisplayModeSpinner displaymodespinner)
    {
        listener = displaymodespinner;
    }

    public void setSelection(int i)
    {
        super.setSelection(i);
        if (listener != null && i == getSelectedItemPosition())
        {
            listener.Selected(this, getSelectedView(), i, 0L);
        }
    }

    public (Context context)
    {
        this$0 = CountdownBaseActivity.this;
        super(context);
    }

    public this._cls0(Context context, AttributeSet attributeset)
    {
        this$0 = CountdownBaseActivity.this;
        super(context, attributeset);
    }

    public this._cls0(Context context, AttributeSet attributeset, int i)
    {
        this$0 = CountdownBaseActivity.this;
        super(context, attributeset, i);
    }
}
