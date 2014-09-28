// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import android.os.Handler;
import android.os.Message;
import android.view.inputmethod.InputMethodManager;

// Referenced classes of package jp.co.yahoo.android.common.hamburger:
//            YHBGSearchActivity

class this._cls0 extends Handler
{

    final YHBGSearchActivity this$0;

    public void dispatchMessage(Message message)
    {
        ((InputMethodManager)getSystemService("input_method")).showSoftInput(YHBGSearchActivity.access$6(YHBGSearchActivity.this), 0);
        super.dispatchMessage(message);
    }

    ()
    {
        this$0 = YHBGSearchActivity.this;
        super();
    }
}
