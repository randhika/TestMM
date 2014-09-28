// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.content.Context;
import android.content.SharedPreferences;

// Referenced classes of package jp.co.yahoo.android.ads:
//            d

static final class b
    implements Runnable
{

    final Context a;
    final String b;

    public void run()
    {
        android.content.edPreferences.Editor editor = a.getSharedPreferences("yjadviewpref", 0).edit();
        editor.putString("lc_info", b);
        editor.commit();
    }

    ences.Editor(Context context, String s)
    {
        a = context;
        b = s;
        super();
    }
}
