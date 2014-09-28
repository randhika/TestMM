// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            DeepRadioGroup

private class listener
{

    nFoundListener listener;
    final DeepRadioGroup this$0;

    public void scan(View view)
    {
        if (!(view instanceof RadioButton)) goto _L2; else goto _L1
_L1:
        listener.onRadioButtonFound((RadioButton)view);
_L4:
        return;
_L2:
        if (view instanceof ViewGroup)
        {
            ViewGroup viewgroup = (ViewGroup)view;
            int i = 0;
            int j = viewgroup.getChildCount();
            while (i < j) 
            {
                scan(viewgroup.getChildAt(i));
                i++;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public nFoundListener(nFoundListener nfoundlistener)
    {
        this$0 = DeepRadioGroup.this;
        super();
        listener = nfoundlistener;
    }
}
