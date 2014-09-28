// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot;

import android.content.SharedPreferences;

// Referenced classes of package jp.co.yahoo.applicot:
//            Applicot, ErrorReporter

static final class s
    implements android.content.rences.OnSharedPreferenceChangeListener
{

    public void onSharedPreferenceChanged(SharedPreferences sharedpreferences, String s)
    {
        if ("applicot.disable".equals(s) || "applicot.enable".equals(s))
        {
            boolean flag;
            if (!Applicot.access$000(sharedpreferences))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            Applicot.getErrorReporter().setEnabled(flag);
        }
    }

    s()
    {
    }
}
