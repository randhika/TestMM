// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            PushDiainfoDialog

class this._cls0
    implements android.content.ickListener
{

    final PushDiainfoDialog this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        dialoginterface.cancel();
        startActivity(PushDiainfoDialog.access$000(PushDiainfoDialog.this));
        finish();
    }

    ()
    {
        this$0 = PushDiainfoDialog.this;
        super();
    }
}
