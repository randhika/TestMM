// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.viewparts;

import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer.viewparts:
//            CheckListDialog

class this._cls0
    implements android.content.MultiChoiceClickListener
{

    final CheckListDialog this$0;

    public void onClick(DialogInterface dialoginterface, int i, boolean flag)
    {
        checks[i] = flag;
    }

    ()
    {
        this$0 = CheckListDialog.this;
        super();
    }
}
