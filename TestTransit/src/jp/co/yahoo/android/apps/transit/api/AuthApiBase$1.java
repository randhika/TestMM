// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.DialogInterface;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            AuthApiBase

class this._cls0
    implements android.content.e.OnCancelListener
{

    final AuthApiBase this$0;

    public void onCancel(DialogInterface dialoginterface)
    {
        if (m_listener != null)
        {
            m_listener.onCanceled();
        }
    }

    thApiListener()
    {
        this$0 = AuthApiBase.this;
        super();
    }
}
