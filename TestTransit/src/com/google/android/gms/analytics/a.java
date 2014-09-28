// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

// Referenced classes of package com.google.android.gms.analytics:
//            m, ak, aa, h

class a
    implements m
{

    private static Object tq = new Object();
    private static a tr;
    private Context mContext;
    private com.google.android.gms.ads.identifier.AdvertisingIdClient.Info ts;
    private long tt;
    private String tu;
    private boolean tv;
    private Object tw;

    a(Context context)
    {
        tv = false;
        tw = new Object();
        mContext = context.getApplicationContext();
    }

    static String H(String s1)
    {
        MessageDigest messagedigest = ak.W("MD5");
        if (messagedigest == null)
        {
            return null;
        } else
        {
            Locale locale = Locale.US;
            Object aobj[] = new Object[1];
            aobj[0] = new BigInteger(1, messagedigest.digest(s1.getBytes()));
            return String.format(locale, "%032X", aobj);
        }
    }

    private boolean I(String s1)
    {
        try
        {
            String s2 = H(s1);
            aa.C("Storing hashed adid.");
            FileOutputStream fileoutputstream = mContext.openFileOutput("gaClientIdData", 0);
            fileoutputstream.write(s2.getBytes());
            fileoutputstream.close();
            tu = s2;
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            aa.A("Error creating hash file.");
            return false;
        }
        catch (IOException ioexception)
        {
            aa.A("Error writing to hash file.");
            return false;
        }
        return true;
    }

    private boolean a(com.google.android.gms.ads.identifier.AdvertisingIdClient.Info info, com.google.android.gms.ads.identifier.AdvertisingIdClient.Info info1)
    {
        Object obj;
        h h1;
        String s1;
        if (info1 == null)
        {
            obj = null;
        } else
        {
            obj = info1.getId();
        }
        if (TextUtils.isEmpty(((CharSequence) (obj))))
        {
            return true;
        }
        h.u(mContext);
        h1 = h.cv();
        s1 = h1.getValue("&cid");
        Object obj1 = tw;
        obj1;
        JVM INSTR monitorenter ;
        if (tv) goto _L2; else goto _L1
_L1:
        tu = t(mContext);
        tv = true;
_L4:
        String s3;
        s3 = H((new StringBuilder()).append(((String) (obj))).append(s1).toString());
        if (!TextUtils.isEmpty(s3))
        {
            break MISSING_BLOCK_LABEL_213;
        }
        return false;
        Exception exception;
        exception;
        obj1;
        JVM INSTR monitorexit ;
        throw exception;
_L2:
        if (!TextUtils.isEmpty(tu)) goto _L4; else goto _L3
_L3:
        String s2;
        s2 = null;
        if (info != null)
        {
            break MISSING_BLOCK_LABEL_174;
        }
_L5:
        if (s2 != null)
        {
            break MISSING_BLOCK_LABEL_183;
        }
        boolean flag = I((new StringBuilder()).append(((String) (obj))).append(s1).toString());
        obj1;
        JVM INSTR monitorexit ;
        return flag;
        s2 = info.getId();
          goto _L5
        tu = H((new StringBuilder()).append(s2).append(s1).toString());
          goto _L4
        if (!s3.equals(tu))
        {
            break MISSING_BLOCK_LABEL_230;
        }
        obj1;
        JVM INSTR monitorexit ;
        return true;
        String s4;
        if (TextUtils.isEmpty(tu))
        {
            break MISSING_BLOCK_LABEL_306;
        }
        aa.C("Resetting the client id because Advertising Id changed.");
        s4 = h1.cw();
        aa.C((new StringBuilder()).append("New client Id: ").append(s4).toString());
_L6:
        boolean flag1 = I((new StringBuilder()).append(((String) (obj))).append(s4).toString());
        obj1;
        JVM INSTR monitorexit ;
        return flag1;
        s4 = s1;
          goto _L6
    }

    public static m s(Context context)
    {
        if (tr == null)
        {
            synchronized (tq)
            {
                if (tr == null)
                {
                    tr = new a(context);
                }
            }
        }
        return tr;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    static String t(Context context)
    {
        String s1 = null;
        FileInputStream fileinputstream;
        byte abyte0[];
        int i;
        fileinputstream = context.openFileInput("gaClientIdData");
        abyte0 = new byte[128];
        i = fileinputstream.read(abyte0, 0, 128);
        if (fileinputstream.available() <= 0)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        aa.D("Hash file seems corrupted, deleting it.");
        fileinputstream.close();
        context.deleteFile("gaClientIdData");
        return null;
        if (i > 0)
        {
            break MISSING_BLOCK_LABEL_74;
        }
        aa.B("Hash file is empty.");
        fileinputstream.close();
        return null;
        String s2 = new String(abyte0, 0, i);
        fileinputstream.close();
        return s2;
        IOException ioexception;
        ioexception;
_L2:
        aa.D("Error reading Hash file, deleting it.");
        context.deleteFile("gaClientIdData");
        return s1;
        IOException ioexception1;
        ioexception1;
        s1 = s2;
        if (true) goto _L2; else goto _L1
_L1:
        FileNotFoundException filenotfoundexception1;
        filenotfoundexception1;
        return s2;
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        return null;
    }

    com.google.android.gms.ads.identifier.AdvertisingIdClient.Info ck()
    {
        com.google.android.gms.ads.identifier.AdvertisingIdClient.Info info;
        try
        {
            info = AdvertisingIdClient.getAdvertisingIdInfo(mContext);
        }
        catch (IllegalStateException illegalstateexception)
        {
            aa.D("IllegalStateException getting Ad Id Info. If you would like to see Audience reports, please ensure that you have added '<meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />' to your application manifest file. See http://goo.gl/naFqQk for details.");
            return null;
        }
        catch (GooglePlayServicesRepairableException googleplayservicesrepairableexception)
        {
            aa.D("GooglePlayServicesRepairableException getting Ad Id Info");
            return null;
        }
        catch (IOException ioexception)
        {
            aa.D("IOException getting Ad Id Info");
            return null;
        }
        catch (GooglePlayServicesNotAvailableException googleplayservicesnotavailableexception)
        {
            aa.D("GooglePlayServicesNotAvailableException getting Ad Id Info");
            return null;
        }
        catch (Exception exception)
        {
            aa.D("Unknown exception. Could not get the ad Id.");
            return null;
        }
        return info;
    }

    public String getValue(String s1)
    {
        long l = System.currentTimeMillis();
        if (l - tt > 1000L)
        {
            com.google.android.gms.ads.identifier.AdvertisingIdClient.Info info = ck();
            if (a(ts, info))
            {
                ts = info;
            } else
            {
                ts = new com.google.android.gms.ads.identifier.AdvertisingIdClient.Info("", false);
            }
            tt = l;
        }
        if (ts != null)
        {
            if ("&adid".equals(s1))
            {
                return ts.getId();
            }
            if ("&ate".equals(s1))
            {
                if (ts.isLimitAdTrackingEnabled())
                {
                    return "0";
                } else
                {
                    return "1";
                }
            }
        }
        return null;
    }

}
