// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            AlarmDialog

class this._cls0
    implements android.content.e.OnClickListener
{

    final AlarmDialog this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        dialoginterface.cancel();
        if (AlarmDialog.access$000(AlarmDialog.this) != null)
        {
            startActivity(AlarmDialog.access$000(AlarmDialog.this));
        }
        finish();
    }

    ()
    {
        this$0 = AlarmDialog.this;
        super();
    }
}
