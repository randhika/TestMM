// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.app.AlertDialog;
import android.view.View;
import jp.co.yahoo.android.apps.transit.viewparts.CheckListView;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TimeTableActivity

class iew
    implements android.view.
{

    final TimeTableActivity this$0;
    final CheckListView val$chkListDest;
    final CheckListView val$chkListKind;
    final AlertDialog val$dialog;

    public void onClick(View view)
    {
        touchTapRD(getString(0x7f0d040b));
        TimeTableActivity.access$2600(TimeTableActivity.this, val$dialog, val$chkListKind, val$chkListDest);
    }

    iew()
    {
        this$0 = final_timetableactivity;
        val$dialog = alertdialog;
        val$chkListKind = checklistview;
        val$chkListDest = CheckListView.this;
        super();
    }
}
