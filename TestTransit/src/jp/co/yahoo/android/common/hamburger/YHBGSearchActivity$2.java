// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.widget.EditText;

// Referenced classes of package jp.co.yahoo.android.common.hamburger:
//            YHBGSearchActivity

class this._cls0 extends Handler
{

    final YHBGSearchActivity this$0;

    public void handleMessage(Message message)
    {
        String s;
        switch (message.what)
        {
        default:
            return;

        case 0: // '\0'
            s = YHBGSearchActivity.access$6(YHBGSearchActivity.this).getText().toString();
            break;
        }
        YHBGSearchActivity.access$7(YHBGSearchActivity.this, s);
    }

    (Looper looper)
    {
        this$0 = YHBGSearchActivity.this;
        super(looper);
    }
}
