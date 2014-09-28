// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.ArrayList;

public final class ig
{

    public static void a(StringBuilder stringbuilder, double ad[])
    {
        int i = ad.length;
        for (int j = 0; j < i; j++)
        {
            if (j != 0)
            {
                stringbuilder.append(",");
            }
            stringbuilder.append(Double.toString(ad[j]));
        }

    }

    public static void a(StringBuilder stringbuilder, float af[])
    {
        int i = af.length;
        for (int j = 0; j < i; j++)
        {
            if (j != 0)
            {
                stringbuilder.append(",");
            }
            stringbuilder.append(Float.toString(af[j]));
        }

    }

    public static void a(StringBuilder stringbuilder, int ai[])
    {
        int i = ai.length;
        for (int j = 0; j < i; j++)
        {
            if (j != 0)
            {
                stringbuilder.append(",");
            }
            stringbuilder.append(Integer.toString(ai[j]));
        }

    }

    public static void a(StringBuilder stringbuilder, long al[])
    {
        int i = al.length;
        for (int j = 0; j < i; j++)
        {
            if (j != 0)
            {
                stringbuilder.append(",");
            }
            stringbuilder.append(Long.toString(al[j]));
        }

    }

    public static void a(StringBuilder stringbuilder, Object aobj[])
    {
        int i = aobj.length;
        for (int j = 0; j < i; j++)
        {
            if (j != 0)
            {
                stringbuilder.append(",");
            }
            stringbuilder.append(aobj[j].toString());
        }

    }

    public static void a(StringBuilder stringbuilder, String as[])
    {
        int i = as.length;
        for (int j = 0; j < i; j++)
        {
            if (j != 0)
            {
                stringbuilder.append(",");
            }
            stringbuilder.append("\"").append(as[j]).append("\"");
        }

    }

    public static void a(StringBuilder stringbuilder, boolean aflag[])
    {
        int i = aflag.length;
        for (int j = 0; j < i; j++)
        {
            if (j != 0)
            {
                stringbuilder.append(",");
            }
            stringbuilder.append(Boolean.toString(aflag[j]));
        }

    }

    public static ArrayList ga()
    {
        return new ArrayList();
    }
}
