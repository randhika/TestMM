// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import android.view.inputmethod.InputMethodManager;
import android.widget.TimePicker;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class val.listener
    implements android.content.Listener
{

    final TransitBaseActivity this$0;
    final android.app.SetListener val$listener;
    final TimePicker val$timePicker;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(val$timePicker.getWindowToken(), 2);
        if (val$listener != null)
        {
            int j = val$timePicker.getCurrentHour().intValue();
            int k = val$timePicker.getCurrentMinute().intValue();
            val$listener.onTimeSet(null, j, k);
        }
    }

    ()
    {
        this$0 = final_transitbaseactivity;
        val$timePicker = timepicker;
        val$listener = android.app.SetListener.this;
        super();
    }
}
