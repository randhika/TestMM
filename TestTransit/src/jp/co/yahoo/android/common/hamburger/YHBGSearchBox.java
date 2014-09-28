// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

// Referenced classes of package jp.co.yahoo.android.common.hamburger:
//            YHBGSearchActivity

public class YHBGSearchBox extends EditText
{

    public YHBGSearchBox(Context context)
    {
        super(context);
    }

    public YHBGSearchBox(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public YHBGSearchBox(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    public boolean onKeyPreIme(int i, KeyEvent keyevent)
    {
        if (i != 4 || keyevent.getAction() != 1)
        {
            break MISSING_BLOCK_LABEL_32;
        }
        YHBGSearchActivity yhbgsearchactivity = (YHBGSearchActivity)getContext();
        if (yhbgsearchactivity != null)
        {
            try
            {
                yhbgsearchactivity.close();
            }
            catch (ClassCastException classcastexception) { }
        }
        return super.onKeyPreIme(i, keyevent);
    }
}
