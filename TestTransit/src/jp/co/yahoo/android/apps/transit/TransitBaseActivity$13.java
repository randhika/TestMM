// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class val.context
    implements android.content.Listener
{

    final TransitBaseActivity this$0;
    final Context val$context;
    final boolean val$dispMessage;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        String s1;
        delClickListener();
        s1 = getString(0x7f0d00ca);
        String s = s1;
_L2:
        if (val$dispMessage && s.length() > 0)
        {
            Toast.makeText(val$context, s, 0).show();
        }
        return;
        Exception exception;
        exception;
        s = getString(0x7f0d00c8);
        if (true) goto _L2; else goto _L1
_L1:
    }

    ()
    {
        this$0 = final_transitbaseactivity;
        val$dispMessage = flag;
        val$context = Context.this;
        super();
    }
}
