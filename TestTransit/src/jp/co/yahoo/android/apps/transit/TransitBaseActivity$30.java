// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            TransitBaseActivity

class this._cls0
    implements android.content.Listener
{

    final TransitBaseActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if (i == -1)
        {
            StringBuilder stringbuilder = new StringBuilder(getString(0x7f0d0560));
            stringbuilder.append(getString(0x7f0d043f)).append("/");
            stringbuilder.append(getString(0x7f0d0442)).append("/");
            stringbuilder.append(getString(0x7f0d03e4));
            touchRD(stringbuilder.toString());
        }
    }

    ()
    {
        this$0 = TransitBaseActivity.this;
        super();
    }
}
