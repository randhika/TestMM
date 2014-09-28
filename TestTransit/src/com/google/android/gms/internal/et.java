// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

// Referenced classes of package com.google.android.gms.internal:
//            al, eu

public final class et
{

    public static final Handler sv = new Handler(Looper.getMainLooper());

    public static int a(Context context, int i)
    {
        return a(context.getResources().getDisplayMetrics(), i);
    }

    public static int a(DisplayMetrics displaymetrics, int i)
    {
        return (int)TypedValue.applyDimension(1, i, displaymetrics);
    }

    public static void a(ViewGroup viewgroup, al al1, String s)
    {
        a(viewgroup, al1, s, 0xff000000, -1);
    }

    private static void a(ViewGroup viewgroup, al al1, String s, int i, int j)
    {
        if (viewgroup.getChildCount() != 0)
        {
            return;
        } else
        {
            Context context = viewgroup.getContext();
            TextView textview = new TextView(context);
            textview.setGravity(17);
            textview.setText(s);
            textview.setTextColor(i);
            textview.setBackgroundColor(j);
            FrameLayout framelayout = new FrameLayout(context);
            framelayout.setBackgroundColor(i);
            int k = a(context, 3);
            framelayout.addView(textview, new android.widget.FrameLayout.LayoutParams(al1.widthPixels - k, al1.heightPixels - k, 17));
            viewgroup.addView(framelayout, al1.widthPixels, al1.heightPixels);
            return;
        }
    }

    public static void a(ViewGroup viewgroup, al al1, String s, String s1)
    {
        eu.D(s1);
        a(viewgroup, al1, s, 0xffff0000, 0xff000000);
    }

    public static boolean bV()
    {
        return Build.DEVICE.startsWith("generic");
    }

    public static boolean bW()
    {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static String r(Context context)
    {
        String s = android.provider.Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (s == null || bV())
        {
            s = "emulator";
        }
        return y(s);
    }

    public static String y(String s)
    {
        int i = 0;
_L2:
        if (i >= 2)
        {
            break; /* Loop/switch isn't completed */
        }
        String s1;
        MessageDigest messagedigest = MessageDigest.getInstance("MD5");
        messagedigest.update(s.getBytes());
        Locale locale = Locale.US;
        Object aobj[] = new Object[1];
        aobj[0] = new BigInteger(1, messagedigest.digest());
        s1 = String.format(locale, "%032X", aobj);
        return s1;
        NoSuchAlgorithmException nosuchalgorithmexception;
        nosuchalgorithmexception;
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        return null;
    }

}
