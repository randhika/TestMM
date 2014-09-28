// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.DialogInterface;
import android.view.KeyEvent;

// Referenced classes of package jp.co.yahoo.android.common:
//            YAppInfoChecker

class this._cls0
    implements android.content.KeyListener
{

    final YAppInfoChecker this$0;

    public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
    {
        return keyevent.getAction() != 0 || i != 84;
    }

    ()
    {
        this$0 = YAppInfoChecker.this;
        super();
    }
}
