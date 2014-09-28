// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import java.util.Calendar;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

public class OthresCopyrightActivity extends TransitBaseActivity
{

    private TextView kousyouTextView;

    public OthresCopyrightActivity()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03002f);
        setTitle(getString(0x7f0d000e));
        int i = Calendar.getInstance().get(1);
        TextView textview = (TextView)findViewById(0x7f0a013d);
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(getString(0x7f0d0586)).append(" ").append(i).append(" ").append(getString(0x7f0d058c)).append("<br>");
        stringbuffer.append(getString(0x7f0d0586)).append(" ").append(i).append(" ").append(getString(0x7f0d058b)).append("<br>");
        stringbuffer.append(getString(0x7f0d0586)).append(" ").append(i).append(" ").append(getString(0x7f0d058e)).append("<br>");
        stringbuffer.append(getString(0x7f0d058d).replace("\n", "<br>"));
        stringbuffer.append(getString(0x7f0d0586)).append(" ").append(i).append(" ").append(getString(0x7f0d058f));
        textview.setText(Html.fromHtml(stringbuffer.toString()));
        dispAd(this, "2080078817", "pv");
    }
}
