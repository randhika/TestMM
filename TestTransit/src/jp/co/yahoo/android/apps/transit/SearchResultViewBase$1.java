// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.Context;
import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            SearchResultViewBase, TransitBaseActivity

class this._cls0
    implements android.content.Listener
{

    final SearchResultViewBase this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        ((TransitBaseActivity)context).touchTapRD((new StringBuilder()).append(context.getString(0x7f0d03f6)).append("/").append(context.getString(0x7f0d03cd)).toString());
        dialoginterface.cancel();
    }

    ()
    {
        this$0 = SearchResultViewBase.this;
        super();
    }
}
