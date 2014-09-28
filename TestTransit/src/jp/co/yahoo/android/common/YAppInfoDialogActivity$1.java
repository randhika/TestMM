// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.DialogInterface;
import android.content.Intent;
import java.util.Map;

// Referenced classes of package jp.co.yahoo.android.common:
//            YAppInfoDialogActivity

class val.isCancel
    implements android.content.stener
{

    final YAppInfoDialogActivity this$0;
    final Map val$data;
    final boolean val$isCancel;
    final String val$order;
    final String val$type;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        String s = (String)val$data.get("action");
        YAppInfoDialogActivity.access$000(YAppInfoDialogActivity.this).putExtra("clicked_button_action", s);
        YAppInfoDialogActivity.access$000(YAppInfoDialogActivity.this).putExtra("clicked_button_order", val$order);
        YAppInfoDialogActivity.access$000(YAppInfoDialogActivity.this).putExtra("clicked_button_type", val$type);
        if (val$isCancel)
        {
            dialoginterface.cancel();
        }
    }

    _cls9()
    {
        this$0 = final_yappinfodialogactivity;
        val$data = map;
        val$order = s;
        val$type = s1;
        val$isCancel = Z.this;
        super();
    }
}
