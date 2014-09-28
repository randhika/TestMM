// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.analytics;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

// Referenced classes of package com.google.android.gms.analytics:
//            m, aa

class g
    implements m
{

    private static g tG;
    private static Object tq = new Object();
    protected String tC;
    protected String tD;
    protected String tE;
    protected String tF;

    protected g()
    {
    }

    private g(Context context)
    {
        PackageManager packagemanager;
        String s;
        packagemanager = context.getPackageManager();
        tE = context.getPackageName();
        tF = packagemanager.getInstallerPackageName(tE);
        s = tE;
        PackageInfo packageinfo = packagemanager.getPackageInfo(context.getPackageName(), 0);
        String s1 = null;
        if (packageinfo != null)
        {
            try
            {
                s = packagemanager.getApplicationLabel(packageinfo.applicationInfo).toString();
                s1 = packageinfo.versionName;
            }
            catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
            {
                aa.A((new StringBuilder()).append("Error retrieving package info: appName set to ").append(s).toString());
                s1 = null;
            }
        }
        tC = s;
        tD = s1;
        return;
    }

    public static g cu()
    {
        return tG;
    }

    public static void u(Context context)
    {
        synchronized (tq)
        {
            if (tG == null)
            {
                tG = new g(context);
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean J(String s)
    {
        return "&an".equals(s) || "&av".equals(s) || "&aid".equals(s) || "&aiid".equals(s);
    }

    public String getValue(String s)
    {
        if (s != null)
        {
            if (s.equals("&an"))
            {
                return tC;
            }
            if (s.equals("&av"))
            {
                return tD;
            }
            if (s.equals("&aid"))
            {
                return tE;
            }
            if (s.equals("&aiid"))
            {
                return tF;
            }
        }
        return null;
    }

}
