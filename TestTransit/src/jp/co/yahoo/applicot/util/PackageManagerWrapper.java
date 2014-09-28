// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import jp.co.yahoo.applicot.Applicot;

public final class PackageManagerWrapper
{

    private final Context context;

    public PackageManagerWrapper(Context context1)
    {
        context = context1;
    }

    public PackageInfo getPackageInfo()
    {
        PackageManager packagemanager = context.getPackageManager();
        if (packagemanager == null)
        {
            return null;
        }
        PackageInfo packageinfo;
        try
        {
            packageinfo = packagemanager.getPackageInfo(context.getPackageName(), 0);
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            Log.v(Applicot.LOG_TAG, (new StringBuilder()).append("Failed to find PackageInfo for current App : ").append(context.getPackageName()).toString());
            return null;
        }
        catch (RuntimeException runtimeexception)
        {
            return null;
        }
        return packageinfo;
    }

    public boolean hasPermission(String s)
    {
        PackageManager packagemanager = context.getPackageManager();
        if (packagemanager != null)
        {
            int i;
            try
            {
                i = packagemanager.checkPermission(s, context.getPackageName());
            }
            catch (RuntimeException runtimeexception)
            {
                return false;
            }
            if (i == 0)
            {
                return true;
            }
        }
        return false;
    }
}
