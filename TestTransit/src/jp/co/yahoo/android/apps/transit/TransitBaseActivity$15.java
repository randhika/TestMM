// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class val.cancelListener
    implements android.content.Listener
{

    final TransitBaseActivity this$0;
    final android.content.Listener val$cancelListener;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if (val$cancelListener != null)
        {
            val$cancelListener.onClick(null, -1);
        }
    }

    ()
    {
        this$0 = final_transitbaseactivity;
        val$cancelListener = android.content.Listener.this;
        super();
    }
}
