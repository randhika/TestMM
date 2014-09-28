// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.viewparts;

import android.view.View;

// Referenced classes of package jp.co.yahoo.android.apps.transit.viewparts:
//            PopupNew

class pupListener
    implements android.view.kListener
{

    final PopupNew this$0;
    final pupListener val$listener;

    public void onClick(View view)
    {
        dismiss();
        if (val$listener != null)
        {
            val$listener.onClick();
        }
    }

    pupListener()
    {
        this$0 = final_popupnew;
        val$listener = pupListener.this;
        super();
    }
}
