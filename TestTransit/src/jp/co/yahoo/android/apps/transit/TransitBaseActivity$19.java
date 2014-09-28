// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.widget.TextView;
import android.widget.TimePicker;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class val.timeDisplay
    implements android.widget.dListener
{

    final TransitBaseActivity this$0;
    final TextView val$timeDisplay;

    public void onTimeChanged(TimePicker timepicker, int i, int j)
    {
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Integer.valueOf(j);
        String s = String.format("%02d\uFF1A%02d", aobj);
        val$timeDisplay.setText(s);
    }

    ()
    {
        this$0 = final_transitbaseactivity;
        val$timeDisplay = TextView.this;
        super();
    }
}
