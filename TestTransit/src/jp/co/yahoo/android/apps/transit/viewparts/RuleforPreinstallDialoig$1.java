// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            RuleforPreinstallDialoig

class leDialogListener
    implements android.content.ener
{

    final RuleforPreinstallDialoig this$0;
    final leDialogListener val$listener;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        dialoginterface.cancel();
        if (val$listener != null)
        {
            val$listener.onCancel();
        }
    }

    leDialogListener()
    {
        this$0 = final_ruleforpreinstalldialoig;
        val$listener = leDialogListener.this;
        super();
    }
}
