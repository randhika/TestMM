// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TimeTableActivity

class val.dispFilterDialog
    implements android.content.ckListener
{

    final TimeTableActivity this$0;
    final boolean val$dispFilterDialog;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        dialoginterface.cancel();
        if (val$dispFilterDialog)
        {
            showSelectFilter(null);
        }
    }

    ()
    {
        this$0 = final_timetableactivity;
        val$dispFilterDialog = Z.this;
        super();
    }
}
