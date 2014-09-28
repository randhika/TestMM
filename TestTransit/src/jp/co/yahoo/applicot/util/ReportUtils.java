// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.applicot.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.SparseArray;
import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import jp.co.yahoo.applicot.Applicot;
import jp.co.yahoo.applicot.log.ApplicotLog;

public final class ReportUtils
{

    public ReportUtils()
    {
    }

    public static int getAppInternalMemorySize(Context context)
    {
        int i;
        try
        {
            ActivityManager activitymanager = (ActivityManager)context.getSystemService("activity");
            int ai[] = new int[1];
            ai[0] = Process.myPid();
            i = activitymanager.getProcessMemoryInfo(ai)[0].getTotalPss();
        }
        catch (RuntimeException runtimeexception)
        {
            Log.w("Applicot", "Couldn't retrieve App ProcessMemoryInfo for : ", runtimeexception);
            return 0;
        }
        return i;
    }

    public static String getApplicationFilePath(Context context)
    {
        File file = context.getFilesDir();
        if (file != null)
        {
            return file.getAbsolutePath();
        } else
        {
            Log.w(Applicot.LOG_TAG, (new StringBuilder()).append("Couldn't retrieve ApplicationFilePath for : ").append(context.getPackageName()).toString());
            return "Couldn't retrieve ApplicationFilePath";
        }
    }

    public static long getAvailableInternalMemorySize()
    {
        StatFs statfs = new StatFs(Environment.getDataDirectory().getPath());
        return (long)statfs.getBlockSize() * (long)statfs.getAvailableBlocks();
    }

    public static String getDeviceId(Context context)
    {
        String s;
        try
        {
            s = ((TelephonyManager)context.getSystemService("phone")).getDeviceId();
        }
        catch (RuntimeException runtimeexception)
        {
            Log.w(Applicot.LOG_TAG, (new StringBuilder()).append("Couldn't retrieve DeviceId for : ").append(context.getPackageName()).toString(), runtimeexception);
            return null;
        }
        return s;
    }

    public static String getLocalIpAddress()
    {
        StringBuilder stringbuilder;
        boolean flag;
        stringbuilder = new StringBuilder();
        flag = true;
        Enumeration enumeration = NetworkInterface.getNetworkInterfaces();
_L4:
        Enumeration enumeration1;
        if (!enumeration.hasMoreElements())
        {
            break MISSING_BLOCK_LABEL_116;
        }
        enumeration1 = ((NetworkInterface)enumeration.nextElement()).getInetAddresses();
_L2:
        InetAddress inetaddress;
        if (!enumeration1.hasMoreElements())
        {
            break; /* Loop/switch isn't completed */
        }
        inetaddress = (InetAddress)enumeration1.nextElement();
        if (inetaddress.isLoopbackAddress())
        {
            continue; /* Loop/switch isn't completed */
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_81;
        }
        stringbuilder.append('\n');
        stringbuilder.append(inetaddress.getHostAddress().toString());
        flag = false;
        if (true) goto _L2; else goto _L1
_L1:
        if (true) goto _L4; else goto _L3
_L3:
        SocketException socketexception;
        socketexception;
        Applicot.log.w(Applicot.LOG_TAG, socketexception.toString());
        return stringbuilder.toString();
    }

    public static long getTotalInternalMemorySize()
    {
        StatFs statfs = new StatFs(Environment.getDataDirectory().getPath());
        return (long)statfs.getBlockSize() * (long)statfs.getBlockCount();
    }

    public static String sparseArrayToString(SparseArray sparsearray)
    {
        StringBuilder stringbuilder = new StringBuilder();
        if (sparsearray == null)
        {
            return "null";
        }
        stringbuilder.append('{');
        int i = 0;
        while (i < sparsearray.size()) 
        {
            stringbuilder.append(sparsearray.keyAt(i));
            stringbuilder.append(" => ");
            if (sparsearray.valueAt(i) == null)
            {
                stringbuilder.append("null");
            } else
            {
                stringbuilder.append(sparsearray.valueAt(i).toString());
            }
            if (i < -1 + sparsearray.size())
            {
                stringbuilder.append(", ");
            }
            i++;
        }
        stringbuilder.append('}');
        return stringbuilder.toString();
    }
}
