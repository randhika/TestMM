// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.content.Context;
import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            TransitDialogBuilder

public class RuleforPreinstallDialoig
{
    public static interface RuleDialogListener
    {

        public abstract void onCancel();

        public abstract void onOK();
    }


    private Context context;

    public RuleforPreinstallDialoig(Context context1)
    {
        context = context1;
    }

    public void showDialog(final RuleDialogListener listener)
    {
        String s = context.getString(0x7f0d005f);
        (new TransitDialogBuilder(context)).setMessage(s).setTitleInfo(context.getString(0x7f0d0060)).setPositiveButton(context.getString(0x7f0d0074), new android.content.DialogInterface.OnClickListener() {

            final RuleforPreinstallDialoig this$0;
            final RuleDialogListener val$listener;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.cancel();
                if (listener != null)
                {
                    listener.onOK();
                }
            }

            
            {
                this$0 = RuleforPreinstallDialoig.this;
                listener = ruledialoglistener;
                super();
            }
        }).setNegativeButton(context.getString(0x7f0d0071), new android.content.DialogInterface.OnClickListener() {

            final RuleforPreinstallDialoig this$0;
            final RuleDialogListener val$listener;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.cancel();
                if (listener != null)
                {
                    listener.onCancel();
                }
            }

            
            {
                this$0 = RuleforPreinstallDialoig.this;
                listener = ruledialoglistener;
                super();
            }
        }).setCancelable(false).show();
    }
}
