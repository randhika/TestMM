// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            MapDisplay

class this._cls0
    implements android.content.ce.OnClickListener
{

    final MapDisplay this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        touchTapRD((new StringBuilder()).append(getString(0x7f0d03f6)).append("/").append(getString(0x7f0d03cd)).toString());
        dialoginterface.cancel();
    }

    r()
    {
        this$0 = MapDisplay.this;
        super();
    }
}
