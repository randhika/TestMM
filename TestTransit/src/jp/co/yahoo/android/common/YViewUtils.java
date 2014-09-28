// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class YViewUtils
{

    public YViewUtils()
    {
    }

    public static String getSelectedItemTextOfSpinner(Activity activity, int i)
    {
        return ((TextView)((Spinner)activity.findViewById(i)).getSelectedView()).getText().toString();
    }

    public static void setItemsToSpinner(Activity activity, int i, CharSequence acharsequence[])
    {
        Spinner spinner = (Spinner)activity.findViewById(i);
        ArrayAdapter arrayadapter = new ArrayAdapter(activity, 0x1090008, acharsequence);
        arrayadapter.setDropDownViewResource(0x1090009);
        spinner.setAdapter(arrayadapter);
    }
}
