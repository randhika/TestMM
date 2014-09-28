// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownActivity

class val.bDest
    implements android.content.ckListener
{

    final CountdownActivity this$0;
    final boolean val$bDest;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if (val$bDest)
        {
            showFilterDestDialog();
            return;
        } else
        {
            showFilterKindDialog();
            return;
        }
    }

    A()
    {
        this$0 = final_countdownactivity;
        val$bDest = Z.this;
        super();
    }
}
