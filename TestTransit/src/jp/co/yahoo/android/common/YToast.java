// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.Context;
import android.widget.Toast;

public class YToast
{

    public YToast()
    {
    }

    public static void showErr(Context context, int i)
    {
        Toast.makeText(context, i, 1).show();
    }

    public static void showErr(Context context, CharSequence charsequence)
    {
        Toast.makeText(context, charsequence, 1).show();
    }

    public static void showMes(Context context, int i)
    {
        Toast.makeText(context, i, 0).show();
    }

    public static void showMes(Context context, CharSequence charsequence)
    {
        Toast.makeText(context, charsequence, 0).show();
    }
}
