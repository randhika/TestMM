// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class val.listener
    implements android.content.Listener
{

    final TransitBaseActivity this$0;
    final DatePicker val$datePicker;
    final android.app.SetListener val$listener;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(val$datePicker.getWindowToken(), 2);
        if (val$listener != null)
        {
            int j = val$datePicker.getYear();
            int k = val$datePicker.getMonth();
            int l = val$datePicker.getDayOfMonth();
            val$listener.onDateSet(null, j, k, l);
        }
    }

    ()
    {
        this$0 = final_transitbaseactivity;
        val$datePicker = datepicker;
        val$listener = android.app.SetListener.this;
        super();
    }
}
