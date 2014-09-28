// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.widget;

import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.apps.transit.widget:
//            WidgetSearchActivity

class this._cls0
    implements android.content.lListener
{

    final WidgetSearchActivity this$0;

    public void onCancel(DialogInterface dialoginterface)
    {
        dialoginterface.cancel();
        WidgetSearchActivity.access$400(WidgetSearchActivity.this).finish();
    }

    ()
    {
        this$0 = WidgetSearchActivity.this;
        super();
    }
}
