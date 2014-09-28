// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.viewparts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

public class CheckListDialog
{
    public static interface DialogListener
    {

        public abstract void onCancel();

        public abstract void onOk(String s);
    }


    protected boolean bChecked;
    protected boolean checks[];
    protected Context context;
    protected String items[];
    protected String labels[];
    protected DialogListener listener;
    protected String title;

    public CheckListDialog(Context context1)
    {
        context = null;
        title = "";
        bChecked = true;
        context = context1;
    }

    public void setCheck(boolean aflag[])
    {
        checks = aflag;
    }

    public void setItems(String as[], String as1[])
    {
        labels = as;
        items = as1;
    }

    public void setResponseChecked(boolean flag)
    {
        bChecked = flag;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public void showDilaog(DialogListener dialoglistener)
    {
        listener = dialoglistener;
        TransitDialogBuilder transitdialogbuilder = new TransitDialogBuilder(context);
        transitdialogbuilder.setTitle(title);
        transitdialogbuilder.setMultiChoiceItems(labels, checks, new android.content.DialogInterface.OnMultiChoiceClickListener() {

            final CheckListDialog this$0;

            public void onClick(DialogInterface dialoginterface, int i, boolean flag)
            {
                checks[i] = flag;
            }

            
            {
                this$0 = CheckListDialog.this;
                super();
            }
        });
        transitdialogbuilder.setNegativeButton(context.getString(0x7f0d0071), new android.content.DialogInterface.OnClickListener() {

            final CheckListDialog this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.dismiss();
                if (listener != null)
                {
                    listener.onCancel();
                }
            }

            
            {
                this$0 = CheckListDialog.this;
                super();
            }
        });
        transitdialogbuilder.setPositiveButton(context.getString(0x7f0d007e), new android.content.DialogInterface.OnClickListener() {

            final CheckListDialog this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                String s = "";
                boolean flag = true;
                int j = 0;
                while (j < checks.length) 
                {
                    if (bChecked ? checks[j] : !checks[j])
                    {
                        if (!flag)
                        {
                            s = (new StringBuilder()).append(s).append(",").toString();
                        } else
                        {
                            flag = false;
                        }
                        s = (new StringBuilder()).append(s).append(items[j]).toString();
                    }
                    j++;
                }
                if (listener != null)
                {
                    listener.onOk(s);
                }
            }

            
            {
                this$0 = CheckListDialog.this;
                super();
            }
        });
        transitdialogbuilder.create().show();
    }
}
