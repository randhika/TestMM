// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            HomeDatetimeConditionActivity

class this._cls0
    implements android.view.ConditionActivity._cls1
{

    final HomeDatetimeConditionActivity this$0;

    public void onFocusChange(View view, boolean flag)
    {
        InputMethodManager inputmethodmanager = (InputMethodManager)getSystemService("input_method");
        if (inputmethodmanager != null)
        {
            inputmethodmanager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    ()
    {
        this$0 = HomeDatetimeConditionActivity.this;
        super();
    }
}
