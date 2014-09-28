// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

public class YDialogUtils
{
    public static interface OnInputClickListener
    {

        public abstract void onClick(DialogInterface dialoginterface, int i, String s);
    }

    private static final class OnInputClickListenerHelper
        implements android.content.DialogInterface.OnClickListener
    {

        private OnInputClickListener mCancelClickListener;
        private OnInputClickListener mOkClickListener;

        public void onClick(DialogInterface dialoginterface, int i)
        {
            i;
            JVM INSTR tableswitch -2 -1: default 24
        //                       -2 69
        //                       -1 25;
               goto _L1 _L2 _L3
_L1:
            return;
_L3:
            if (mOkClickListener != null)
            {
                TextView textview1 = (TextView)((Dialog)dialoginterface).findViewById(R.id.edt_text);
                mOkClickListener.onClick(dialoginterface, i, textview1.getText().toString());
                return;
            }
            continue; /* Loop/switch isn't completed */
_L2:
            if (mCancelClickListener != null)
            {
                TextView textview = (TextView)((Dialog)dialoginterface).findViewById(R.id.edt_text);
                mCancelClickListener.onClick(dialoginterface, i, textview.getText().toString());
                return;
            }
            if (true) goto _L1; else goto _L4
_L4:
        }

        void setOnCancelClickListener(OnInputClickListener oninputclicklistener)
        {
            mCancelClickListener = oninputclicklistener;
        }

        void setOnOkClickListener(OnInputClickListener oninputclicklistener)
        {
            mOkClickListener = oninputclicklistener;
        }

        private OnInputClickListenerHelper()
        {
            mOkClickListener = null;
            mCancelClickListener = null;
        }

    }


    public YDialogUtils()
    {
    }

    public static Dialog buildInputDialog(Context context, String s, OnInputClickListener oninputclicklistener, OnInputClickListener oninputclicklistener1)
    {
        OnInputClickListenerHelper oninputclicklistenerhelper = new OnInputClickListenerHelper();
        oninputclicklistenerhelper.setOnOkClickListener(oninputclicklistener);
        oninputclicklistenerhelper.setOnCancelClickListener(oninputclicklistener1);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setView(View.inflate(context, R.layout.common_dialogutils_input, null)).setTitle(s).setNegativeButton(0x1040000, oninputclicklistenerhelper).setPositiveButton(0x104000a, oninputclicklistenerhelper);
        return builder.create();
    }

    public static Dialog buildMessageDialog(Context context, int i, android.content.DialogInterface.OnClickListener onclicklistener)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setMessage(i).setPositiveButton("OK", onclicklistener);
        return builder.create();
    }
}
