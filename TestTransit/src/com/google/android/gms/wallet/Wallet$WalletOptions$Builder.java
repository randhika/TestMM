// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet;

import java.util.Locale;

// Referenced classes of package com.google.android.gms.wallet:
//            Wallet

public static final class mTheme
{

    private int akb;
    private int mTheme;

    static int a(mTheme mtheme)
    {
        return mtheme.akb;
    }

    static int b(akb akb1)
    {
        return akb1.mTheme;
    }

    public mTheme build()
    {
        return new mTheme(this, null);
    }

    public mTheme setEnvironment(int i)
    {
        if (i == 0 || i == 2 || i == 1)
        {
            akb = i;
            return this;
        } else
        {
            Locale locale = Locale.US;
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            throw new IllegalArgumentException(String.format(locale, "Invalid environment value %d", aobj));
        }
    }

    public akb setTheme(int i)
    {
        if (i == 0 || i == 1)
        {
            mTheme = i;
            return this;
        } else
        {
            Locale locale = Locale.US;
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            throw new IllegalArgumentException(String.format(locale, "Invalid theme value %d", aobj));
        }
    }

    public ()
    {
        akb = 0;
        mTheme = 0;
    }
}
