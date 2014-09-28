// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import jp.co.yahoo.applicot.Applicot;

public final class ToastSender
{

    public ToastSender()
    {
    }

    public static void sendToast(Context context, int i, int j)
    {
        try
        {
            Toast.makeText(context, i, j).show();
            return;
        }
        catch (RuntimeException runtimeexception)
        {
            Log.e(Applicot.LOG_TAG, "Could not send crash Toast", runtimeexception);
        }
    }
}
