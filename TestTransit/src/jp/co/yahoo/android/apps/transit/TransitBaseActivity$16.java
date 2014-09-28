// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import android.widget.ListView;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class val.listView
    implements android.content.Listener
{

    final TransitBaseActivity this$0;
    final ListView val$listView;
    final android.content.Listener val$listener;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if (val$listener != null)
        {
            int j = val$listView.getCheckedItemPosition();
            val$listener.onClick(null, j);
        }
    }

    ()
    {
        this$0 = final_transitbaseactivity;
        val$listener = listener1;
        val$listView = ListView.this;
        super();
    }
}
