// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.DialogInterface;
import android.view.View;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownActivity

class this._cls0
    implements android.content.ckListener
{

    final CountdownActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        showFilterDialog(new View(CountdownActivity.this));
    }

    A()
    {
        this$0 = CountdownActivity.this;
        super();
    }
}
