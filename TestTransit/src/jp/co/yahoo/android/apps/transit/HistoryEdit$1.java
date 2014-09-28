// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.viewparts.CheckListView;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            HistoryEdit

class this._cls0
    implements android.view.stener
{

    final HistoryEdit this$0;

    public void onClick(View view)
    {
        if (HistoryEdit.access$000(HistoryEdit.this).getCheckItems().size() < 1)
        {
            showErrorMessageDialog(getString(0x7f0d012d), getString(0x7f0d0150));
            return;
        } else
        {
            showdelMessageDialog(getString(0x7f0d00cc));
            return;
        }
    }

    kListView()
    {
        this$0 = HistoryEdit.this;
        super();
    }
}
