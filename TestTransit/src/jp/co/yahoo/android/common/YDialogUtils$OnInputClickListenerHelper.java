// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.TextView;

// Referenced classes of package jp.co.yahoo.android.common:
//            YDialogUtils

private static final class <init>
    implements android.content.enerHelper
{

    private mOkClickListener mCancelClickListener;
    private mOkClickListener mOkClickListener;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        i;
        JVM INSTR tableswitch -2 -1: default 24
    //                   -2 69
    //                   -1 25;
           goto _L1 _L2 _L3
_L1:
        return;
_L3:
        if (mOkClickListener != null)
        {
            TextView textview1 = (TextView)((Dialog)dialoginterface).findViewById(mOkClickListener);
            mOkClickListener.k(dialoginterface, i, textview1.getText().toString());
            return;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (mCancelClickListener != null)
        {
            TextView textview = (TextView)((Dialog)dialoginterface).findViewById(mCancelClickListener);
            mCancelClickListener.k(dialoginterface, i, textview.getText().toString());
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    void setOnCancelClickListener(k k)
    {
        mCancelClickListener = k;
    }

    void setOnOkClickListener(mCancelClickListener mcancelclicklistener)
    {
        mOkClickListener = mcancelclicklistener;
    }

    private ()
    {
        mOkClickListener = null;
        mCancelClickListener = null;
    }

    mCancelClickListener(mCancelClickListener mcancelclicklistener)
    {
        this();
    }
}
