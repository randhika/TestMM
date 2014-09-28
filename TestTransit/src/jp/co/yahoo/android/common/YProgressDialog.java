// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.app.ProgressDialog;
import android.content.Context;

public class YProgressDialog extends ProgressDialog
{

    public YProgressDialog(Context context)
    {
        super(context);
    }

    public YProgressDialog(Context context, int i)
    {
        super(context);
        setMessage(getContext().getString(i));
    }

    public void show(int i)
    {
        show(getContext().getString(i));
    }

    public void show(String s)
    {
        setProgressStyle(0);
        setMessage(s);
        show();
    }
}
