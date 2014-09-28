// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.widget.DatePicker;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class val.dateDisplay
    implements android.widget.dListener
{

    final TransitBaseActivity this$0;
    final TextView val$dateDisplay;

    public void onDateChanged(DatePicker datepicker, int i, int j, int k)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i, j, k);
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy\u5E74M\u6708d\u65E5\uFF08E\uFF09", Locale.JAPANESE);
        val$dateDisplay.setText(simpledateformat.format(calendar.getTime()));
    }

    ()
    {
        this$0 = final_transitbaseactivity;
        val$dateDisplay = TextView.this;
        super();
    }
}
