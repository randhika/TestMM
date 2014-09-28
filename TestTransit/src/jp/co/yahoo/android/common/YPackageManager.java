// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentProvider;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Binder;
import android.os.Process;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;

public class YPackageManager
{

    private static final String TAG = "YPackageManager";
    private static int _debugSignature = -1;

    public YPackageManager()
    {
    }

    public static boolean checkBinderSignature(Activity activity)
    {
        String s = activity.getPackageName();
        String s1 = activity.getCallingPackage();
        return activity.getPackageManager().checkSignatures(s, s1) == 0;
    }

    public static boolean checkBinderSignature(ContentProvider contentprovider)
    {
        int i;
        int j;
        i = Process.myPid();
        j = Binder.getCallingPid();
        if (i != j) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        Context context = contentprovider.getContext();
        ActivityManager activitymanager = (ActivityManager)context.getSystemService("activity");
        HashSet hashset = new HashSet();
        Iterator iterator = activitymanager.getRunningAppProcesses().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            android.app.ActivityManager.RunningAppProcessInfo runningappprocessinfo = (android.app.ActivityManager.RunningAppProcessInfo)iterator.next();
            if (runningappprocessinfo.pid != j)
            {
                continue;
            }
            for (int k = 0; k < runningappprocessinfo.pkgList.length; k++)
            {
                hashset.add(runningappprocessinfo.pkgList[k]);
            }

            break;
        } while (true);
        PackageManager packagemanager = context.getPackageManager();
        String s = context.getPackageName();
        flag = false;
        Iterator iterator1 = hashset.iterator();
        while (iterator1.hasNext()) 
        {
            if (packagemanager.checkSignatures(s, (String)iterator1.next()) == 0)
            {
                flag = true;
            } else
            {
                return false;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static boolean isDebugSignature(Context context)
    {
        if (_debugSignature == -1)
        {
            int i;
            if (isDebugSignature(context, context.getPackageName()))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            _debugSignature = i;
        }
        return _debugSignature == 1;
    }

    public static boolean isDebugSignature(Context context, String s)
    {
        PackageInfo packageinfo;
        CertificateFactory certificatefactory;
        int i;
        packageinfo = context.getPackageManager().getPackageInfo(s, 64);
        certificatefactory = CertificateFactory.getInstance("X.509");
        i = packageinfo.signatures.length;
        int j = 0;
_L2:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        boolean flag;
        java.security.cert.Certificate certificate = certificatefactory.generateCertificate(new ByteArrayInputStream(packageinfo.signatures[j].toByteArray()));
        if (!(certificate instanceof X509Certificate))
        {
            break MISSING_BLOCK_LABEL_95;
        }
        flag = ((X509Certificate)certificate).getIssuerX500Principal().getName().contains("CN=Android Debug");
        if (flag)
        {
            return true;
        }
        j++;
        if (true) goto _L2; else goto _L1
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        namenotfoundexception.printStackTrace();
_L1:
        return false;
        CertificateException certificateexception;
        certificateexception;
        certificateexception.printStackTrace();
        if (true) goto _L1; else goto _L3
_L3:
    }

}
