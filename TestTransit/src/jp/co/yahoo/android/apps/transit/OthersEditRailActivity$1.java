// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.view.View;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.viewparts.CheckListView;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            OthersEditRailActivity

class this._cls0
    implements android.view.sEditRailActivity._cls1
{

    final OthersEditRailActivity this$0;

    public void onClick(View view)
    {
        if (OthersEditRailActivity.access$000(OthersEditRailActivity.this).getCheckItems().size() < 1)
        {
            showErrorMessageDialog(getString(0x7f0d012b), getString(0x7f0d0150));
            return;
        } else
        {
            showdelMessageDialog(getString(0x7f0d00cc));
            return;
        }
    }

    ()
    {
        this$0 = OthersEditRailActivity.this;
        super();
    }
}
