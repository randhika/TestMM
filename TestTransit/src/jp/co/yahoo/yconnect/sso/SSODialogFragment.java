// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class SSODialogFragment extends DialogFragment
{

    public static final String CANCELABLE = "Cancelable";
    public static final String MESSAGE = "Message";
    private static ProgressDialog waitDialog;

    public SSODialogFragment()
    {
    }

    public static SSODialogFragment newInstance()
    {
        return new SSODialogFragment();
    }

    public Dialog getDialog()
    {
        return waitDialog;
    }

    public Dialog onCreateDialog(Bundle bundle)
    {
        setCancelable(getArguments().getBoolean("Cancelable", false));
        if (waitDialog != null)
        {
            return waitDialog;
        } else
        {
            waitDialog = new ProgressDialog(getActivity());
            waitDialog.setMessage(getArguments().getString("Message"));
            waitDialog.setIndeterminate(false);
            waitDialog.setProgressStyle(0);
            waitDialog.setProgress(0);
            return waitDialog;
        }
    }

    public void onDestroy()
    {
        super.onDestroy();
        waitDialog = null;
    }
}
