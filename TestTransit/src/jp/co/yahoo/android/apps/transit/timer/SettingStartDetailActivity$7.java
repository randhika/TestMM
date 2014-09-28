// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.DialogInterface;
import android.widget.TextView;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            SettingStartDetailActivity

class val.items
    implements android.content.er
{

    final SettingStartDetailActivity this$0;
    final String val$items[];

    public void onClick(DialogInterface dialoginterface, int i)
    {
        StringBuffer stringbuffer = new StringBuffer();
        StringBuffer stringbuffer1 = new StringBuffer();
        boolean flag = true;
        int j = 0;
        while (j < SettingStartDetailActivity.access$500(SettingStartDetailActivity.this).length) 
        {
            if (SettingStartDetailActivity.access$500(SettingStartDetailActivity.this)[j])
            {
                if (!flag)
                {
                    stringbuffer.append(",");
                    stringbuffer1.append("\u3001");
                } else
                {
                    flag = false;
                }
                stringbuffer.append(Integer.toString(j + 1));
                stringbuffer1.append(val$items[j]);
            }
            j++;
        }
        if (stringbuffer.length() < 1)
        {
            SettingStartDetailActivity.access$000(SettingStartDetailActivity.this).setRepeat("0");
            SettingStartDetailActivity.access$600(SettingStartDetailActivity.this).setText(getString(0x7f0d04cd));
        } else
        {
            SettingStartDetailActivity.access$000(SettingStartDetailActivity.this).setRepeat(stringbuffer.toString());
            SettingStartDetailActivity.access$600(SettingStartDetailActivity.this).setText(stringbuffer1.toString());
        }
        onCheck();
    }

    I()
    {
        this$0 = final_settingstartdetailactivity;
        val$items = _5B_Ljava.lang.String_3B_.this;
        super();
    }
}
