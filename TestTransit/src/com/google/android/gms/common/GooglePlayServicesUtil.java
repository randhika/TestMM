// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import com.google.android.gms.internal.gz;
import com.google.android.gms.internal.he;
import com.google.android.gms.internal.ii;
import com.google.android.gms.internal.ip;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Referenced classes of package com.google.android.gms.common:
//            SupportErrorDialogFragment, ErrorDialogFragment, GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException

public final class GooglePlayServicesUtil
{

    static final byte CV[][];
    static final byte CW[][];
    static final byte CX[][];
    static final byte CY[][];
    static final byte CZ[][];
    static final byte Da[][];
    static final byte Db[][];
    static final byte Dc[][];
    private static final byte Dd[][];
    private static final byte De[][];
    public static boolean Df = false;
    public static boolean Dg = false;
    private static int Dh = 0;
    private static final Object Di = new Object();
    public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 0x4da6e8;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";

    private GooglePlayServicesUtil()
    {
    }

    private static void A(Context context)
    {
        ApplicationInfo applicationinfo1 = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        ApplicationInfo applicationinfo = applicationinfo1;
_L1:
        Bundle bundle = applicationinfo.metaData;
        if (bundle != null)
        {
            int i = bundle.getInt("com.google.android.gms.version");
            android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
            if (i == 0x4da6e8)
            {
                return;
            } else
            {
                throw new IllegalStateException((new StringBuilder()).append("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected 5089000 but found ").append(i).append(".  You must have the").append(" following declaration within the <application> element: ").append("    <meta-data android:name=\"").append("com.google.android.gms.version").append("\" android:value=\"@integer/google_play_services_version\" />").toString());
            }
        } else
        {
            throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
        }
        namenotfoundexception;
        Log.wtf("GooglePlayServicesUtil", "This should never happen.", namenotfoundexception);
        applicationinfo = null;
          goto _L1
    }

    private static String B(Context context)
    {
        String s = context.getApplicationInfo().name;
        if (!TextUtils.isEmpty(s)) goto _L2; else goto _L1
_L1:
        PackageManager packagemanager;
        s = context.getPackageName();
        packagemanager = context.getApplicationContext().getPackageManager();
        ApplicationInfo applicationinfo1 = packagemanager.getApplicationInfo(context.getPackageName(), 0);
        ApplicationInfo applicationinfo = applicationinfo1;
_L4:
        if (applicationinfo != null)
        {
            s = packagemanager.getApplicationLabel(applicationinfo).toString();
        }
_L2:
        return s;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        applicationinfo = null;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static Intent Z(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 1: // '\001'
        case 2: // '\002'
            return he.aD("com.google.android.gms");

        case 3: // '\003'
            return he.aB("com.google.android.gms");

        case 12: // '\f'
            return he.fA();
        }
    }

    private static Dialog a(int i, Activity activity, Fragment fragment, int j, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        if (!ip.gf()) goto _L2; else goto _L1
_L1:
        TypedValue typedvalue;
        typedvalue = new TypedValue();
        activity.getTheme().resolveAttribute(0x1010309, typedvalue, true);
        if (!"Theme.Dialog.Alert".equals(activity.getResources().getResourceEntryName(typedvalue.resourceId))) goto _L2; else goto _L3
_L3:
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity, 5);
_L5:
        if (builder == null)
        {
            builder = new android.app.AlertDialog.Builder(activity);
        }
        builder.setMessage(d(activity, i));
        if (oncancellistener != null)
        {
            builder.setOnCancelListener(oncancellistener);
        }
        Intent intent = c(activity, i);
        gz gz1;
        String s;
        if (fragment == null)
        {
            gz1 = new gz(activity, intent, j);
        } else
        {
            gz1 = new gz(fragment, intent, j);
        }
        s = e(activity, i);
        if (s != null)
        {
            builder.setPositiveButton(s, gz1);
        }
        switch (i)
        {
        default:
            Log.e("GooglePlayServicesUtil", (new StringBuilder()).append("Unexpected error code ").append(i).toString());
            return builder.create();

        case 0: // '\0'
            return null;

        case 4: // '\004'
        case 6: // '\006'
            return builder.create();

        case 1: // '\001'
            return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_install_title).create();

        case 3: // '\003'
            return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_enable_title).create();

        case 2: // '\002'
            return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_update_title).create();

        case 9: // '\t'
            Log.e("GooglePlayServicesUtil", "Google Play services is invalid. Cannot recover.");
            return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_unsupported_title).create();

        case 7: // '\007'
            Log.e("GooglePlayServicesUtil", "Network error occurred. Please retry request later.");
            return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_network_error_title).create();

        case 8: // '\b'
            Log.e("GooglePlayServicesUtil", "Internal error occurred. Please see logs for detailed information");
            return builder.create();

        case 10: // '\n'
            Log.e("GooglePlayServicesUtil", "Developer error occurred. Please see logs for detailed information");
            return builder.create();

        case 5: // '\005'
            Log.e("GooglePlayServicesUtil", "An invalid account was specified when connecting. Please provide a valid account.");
            return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_invalid_account_title).create();

        case 11: // '\013'
            Log.e("GooglePlayServicesUtil", "The application is not licensed to the user.");
            return builder.create();

        case 12: // '\f'
            Log.e("GooglePlayServicesUtil", "The date of the device is not valid.");
            break;
        }
        return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_unsupported_title).create();
_L2:
        builder = null;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public static boolean a(PackageManager packagemanager, PackageInfo packageinfo)
    {
        boolean flag = true;
        boolean flag1 = false;
        if (packageinfo != null)
        {
            if (c(packagemanager))
            {
                if (a(packageinfo, Dd) == null)
                {
                    flag = false;
                }
                return flag;
            }
            byte abyte0[] = a(packageinfo, De);
            flag1 = false;
            if (abyte0 != null)
            {
                flag1 = flag;
            }
            if (!flag1 && a(packageinfo, Dd) != null)
            {
                Log.w("GooglePlayServicesUtil", "Test-keys aren't accepted on this build.");
                return flag1;
            }
        }
        return flag1;
    }

    public static boolean a(Resources resources)
    {
        if (resources != null)
        {
            boolean flag;
            if ((0xf & resources.getConfiguration().screenLayout) > 3)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (ip.gc() && flag || b(resources))
            {
                return true;
            }
        }
        return false;
    }

    private static transient byte[] a(PackageInfo packageinfo, byte abyte0[][])
    {
        CertificateFactory certificatefactory;
        try
        {
            certificatefactory = CertificateFactory.getInstance("X509");
        }
        catch (CertificateException certificateexception)
        {
            Log.w("GooglePlayServicesUtil", "Could not get certificate instance.");
            return null;
        }
        if (packageinfo.signatures.length != 1)
        {
            Log.w("GooglePlayServicesUtil", "Package has more than one signature.");
            return null;
        }
        byte abyte1[] = packageinfo.signatures[0].toByteArray();
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte1);
        X509Certificate x509certificate;
        int i;
        try
        {
            x509certificate = (X509Certificate)certificatefactory.generateCertificate(bytearrayinputstream);
        }
        catch (CertificateException certificateexception1)
        {
            Log.w("GooglePlayServicesUtil", "Could not generate certificate.");
            return null;
        }
        try
        {
            x509certificate.checkValidity();
        }
        catch (CertificateExpiredException certificateexpiredexception)
        {
            Log.w("GooglePlayServicesUtil", "Certificate has expired.");
            return null;
        }
        catch (CertificateNotYetValidException certificatenotyetvalidexception)
        {
            Log.w("GooglePlayServicesUtil", "Certificate is not yet valid.");
            return null;
        }
        for (i = 0; i < abyte0.length; i++)
        {
            byte abyte2[] = abyte0[i];
            if (Arrays.equals(abyte2, abyte1))
            {
                return abyte2;
            }
        }

        if (Log.isLoggable("GooglePlayServicesUtil", 2))
        {
            Log.v("GooglePlayServicesUtil", (new StringBuilder()).append("Signature not valid.  Found: \n").append(Base64.encodeToString(abyte1, 0)).toString());
        }
        return null;
    }

    private static transient byte[][] a(byte abyte0[][][])
    {
        int i = abyte0.length;
        int j = 0;
        int k = 0;
        for (; j < i; j++)
        {
            k += abyte0[j].length;
        }

        byte abyte1[][] = new byte[k][];
        int l = abyte0.length;
        int i1 = 0;
        int k1;
        for (int j1 = 0; i1 < l; j1 = k1)
        {
            byte abyte2[][] = abyte0[i1];
            k1 = j1;
            for (int l1 = 0; l1 < abyte2.length;)
            {
                int i2 = k1 + 1;
                abyte1[k1] = abyte2[l1];
                l1++;
                k1 = i2;
            }

            i1++;
        }

        return abyte1;
    }

    private static byte[] au(String s)
    {
        byte abyte0[];
        try
        {
            abyte0 = s.getBytes("ISO-8859-1");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new AssertionError(unsupportedencodingexception);
        }
        return abyte0;
    }

    public static boolean b(int i, Activity activity, Fragment fragment, int j, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        Dialog dialog = a(i, activity, fragment, j, oncancellistener);
        if (dialog == null)
        {
            return false;
        }
        boolean flag;
        try
        {
            flag = activity instanceof FragmentActivity;
        }
        catch (NoClassDefFoundError noclassdeffounderror)
        {
            flag = false;
        }
        if (flag)
        {
            android.support.v4.app.FragmentManager fragmentmanager1 = ((FragmentActivity)activity).getSupportFragmentManager();
            SupportErrorDialogFragment.newInstance(dialog, oncancellistener).show(fragmentmanager1, "GooglePlayServicesErrorDialog");
        } else
        if (ip.gc())
        {
            android.app.FragmentManager fragmentmanager = activity.getFragmentManager();
            ErrorDialogFragment.newInstance(dialog, oncancellistener).show(fragmentmanager, "GooglePlayServicesErrorDialog");
        } else
        {
            throw new RuntimeException("This Activity does not support Fragments.");
        }
        return true;
    }

    public static boolean b(PackageManager packagemanager)
    {
        Object obj = Di;
        obj;
        JVM INSTR monitorenter ;
        int i = Dh;
        if (i != -1) goto _L2; else goto _L1
_L1:
        PackageInfo packageinfo;
        byte abyte0[][];
        packageinfo = packagemanager.getPackageInfo("com.google.android.gms", 64);
        abyte0 = new byte[1][];
        abyte0[0] = Dd[1];
        if (a(packageinfo, abyte0) == null) goto _L4; else goto _L3
_L3:
        Dh = 1;
_L2:
        obj;
        JVM INSTR monitorexit ;
        Exception exception;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        return Dh != 0;
_L4:
        Dh = 0;
          goto _L2
        namenotfoundexception;
        Dh = 0;
          goto _L2
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static boolean b(PackageManager packagemanager, String s)
    {
        PackageInfo packageinfo;
        try
        {
            packageinfo = packagemanager.getPackageInfo(s, 64);
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            if (Log.isLoggable("GooglePlayServicesUtil", 3))
            {
                Log.d("GooglePlayServicesUtil", (new StringBuilder()).append("Package manager can't find package ").append(s).append(", defaulting to false").toString());
            }
            return false;
        }
        return a(packagemanager, packageinfo);
    }

    private static boolean b(Resources resources)
    {
        Configuration configuration = resources.getConfiguration();
        boolean flag = ip.ge();
        boolean flag1 = false;
        if (flag)
        {
            int i = 0xf & configuration.screenLayout;
            flag1 = false;
            if (i <= 3)
            {
                int j = configuration.smallestScreenWidthDp;
                flag1 = false;
                if (j >= 600)
                {
                    flag1 = true;
                }
            }
        }
        return flag1;
    }

    public static Intent c(Context context, int i)
    {
        return Z(i);
    }

    public static boolean c(PackageManager packagemanager)
    {
        return b(packagemanager) || !ey();
    }

    public static String d(Context context, int i)
    {
        Resources resources = context.getResources();
        switch (i)
        {
        case 4: // '\004'
        case 6: // '\006'
        case 8: // '\b'
        case 10: // '\n'
        case 11: // '\013'
        default:
            return resources.getString(com.google.android.gms.R.string.common_google_play_services_unknown_issue);

        case 1: // '\001'
            if (a(context.getResources()))
            {
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_install_text_tablet);
            } else
            {
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_install_text_phone);
            }

        case 3: // '\003'
            return resources.getString(com.google.android.gms.R.string.common_google_play_services_enable_text);

        case 2: // '\002'
            return resources.getString(com.google.android.gms.R.string.common_google_play_services_update_text);

        case 9: // '\t'
            return resources.getString(com.google.android.gms.R.string.common_google_play_services_unsupported_text);

        case 7: // '\007'
            return resources.getString(com.google.android.gms.R.string.common_google_play_services_network_error_text);

        case 5: // '\005'
            return resources.getString(com.google.android.gms.R.string.common_google_play_services_invalid_account_text);

        case 12: // '\f'
            return resources.getString(com.google.android.gms.R.string.common_google_play_services_unsupported_date_text);
        }
    }

    public static String e(Context context, int i)
    {
        Resources resources = context.getResources();
        switch (i)
        {
        default:
            return resources.getString(0x104000a);

        case 1: // '\001'
            return resources.getString(com.google.android.gms.R.string.common_google_play_services_install_button);

        case 3: // '\003'
            return resources.getString(com.google.android.gms.R.string.common_google_play_services_enable_button);

        case 2: // '\002'
            return resources.getString(com.google.android.gms.R.string.common_google_play_services_update_button);
        }
    }

    public static boolean ey()
    {
        if (Df)
        {
            return Dg;
        } else
        {
            return "user".equals(Build.TYPE);
        }
    }

    public static String f(Context context, int i)
    {
        Resources resources = context.getResources();
        switch (i)
        {
        case 4: // '\004'
        case 6: // '\006'
        case 8: // '\b'
        case 10: // '\n'
        case 11: // '\013'
        default:
            return resources.getString(com.google.android.gms.R.string.common_google_play_services_unknown_issue);

        case 1: // '\001'
            return resources.getString(com.google.android.gms.R.string.common_google_play_services_notification_needs_installation_title);

        case 2: // '\002'
            return resources.getString(com.google.android.gms.R.string.common_google_play_services_notification_needs_update_title);

        case 3: // '\003'
            return resources.getString(com.google.android.gms.R.string.common_google_play_services_needs_enabling_title);

        case 9: // '\t'
            return resources.getString(com.google.android.gms.R.string.common_google_play_services_unsupported_text);

        case 7: // '\007'
            return resources.getString(com.google.android.gms.R.string.common_google_play_services_network_error_text);

        case 5: // '\005'
            return resources.getString(com.google.android.gms.R.string.common_google_play_services_invalid_account_text);

        case 12: // '\f'
            return resources.getString(com.google.android.gms.R.string.common_google_play_services_unsupported_date_text);
        }
    }

    public static Dialog getErrorDialog(int i, Activity activity, int j)
    {
        return getErrorDialog(i, activity, j, null);
    }

    public static Dialog getErrorDialog(int i, Activity activity, int j, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        return a(i, activity, null, j, oncancellistener);
    }

    public static PendingIntent getErrorPendingIntent(int i, Context context, int j)
    {
        Intent intent = c(context, i);
        if (intent == null)
        {
            return null;
        } else
        {
            return PendingIntent.getActivity(context, j, intent, 0x10000000);
        }
    }

    public static String getErrorString(int i)
    {
        switch (i)
        {
        default:
            return "UNKNOWN_ERROR_CODE";

        case 0: // '\0'
            return "SUCCESS";

        case 1: // '\001'
            return "SERVICE_MISSING";

        case 2: // '\002'
            return "SERVICE_VERSION_UPDATE_REQUIRED";

        case 3: // '\003'
            return "SERVICE_DISABLED";

        case 4: // '\004'
            return "SIGN_IN_REQUIRED";

        case 5: // '\005'
            return "INVALID_ACCOUNT";

        case 6: // '\006'
            return "RESOLUTION_REQUIRED";

        case 7: // '\007'
            return "NETWORK_ERROR";

        case 8: // '\b'
            return "INTERNAL_ERROR";

        case 9: // '\t'
            return "SERVICE_INVALID";

        case 10: // '\n'
            return "DEVELOPER_ERROR";

        case 11: // '\013'
            return "LICENSE_CHECK_FAILED";

        case 12: // '\f'
            return "DATE_INVALID";
        }
    }

    public static String getOpenSourceSoftwareLicenseInfo(Context context)
    {
        android.net.Uri uri = (new android.net.Uri.Builder()).scheme("android.resource").authority("com.google.android.gms").appendPath("raw").appendPath("oss_notice").build();
        InputStream inputstream = context.getContentResolver().openInputStream(uri);
        String s;
        String s1;
        try
        {
            s1 = (new Scanner(inputstream)).useDelimiter("\\A").next();
        }
        catch (NoSuchElementException nosuchelementexception)
        {
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_114;
            }
            Exception exception1;
            try
            {
                inputstream.close();
                break MISSING_BLOCK_LABEL_114;
            }
            catch (Exception exception)
            {
                s = null;
            }
            break MISSING_BLOCK_LABEL_112;
        }
        finally
        {
            if (inputstream == null) goto _L0; else goto _L0
        }
        s = s1;
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_112;
        }
        inputstream.close();
        return s;
        inputstream.close();
        throw exception1;
        return s;
        return null;
    }

    public static Context getRemoteContext(Context context)
    {
        Context context1;
        try
        {
            context1 = context.createPackageContext("com.google.android.gms", 3);
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            return null;
        }
        return context1;
    }

    public static Resources getRemoteResource(Context context)
    {
        Resources resources;
        try
        {
            resources = context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            return null;
        }
        return resources;
    }

    public static int isGooglePlayServicesAvailable(Context context)
    {
        PackageManager packagemanager = context.getPackageManager();
        try
        {
            context.getResources().getString(com.google.android.gms.R.string.common_google_play_services_unknown_issue);
        }
        catch (Throwable throwable)
        {
            Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }
        if (System.currentTimeMillis() < 0x11dc17eed20L)
        {
            return 12;
        }
        A(context);
        PackageInfo packageinfo;
        try
        {
            packageinfo = packagemanager.getPackageInfo("com.google.android.gms", 64);
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 1;
        }
        if (ii.aD(packageinfo.versionCode))
        {
            PackageInfo packageinfo2;
            try
            {
                packageinfo2 = packagemanager.getPackageInfo(context.getPackageName(), 64);
            }
            catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception3)
            {
                Log.w("GooglePlayServicesUtil", "Calling package info missing.");
                return 9;
            }
            if (!ey())
            {
                byte abyte3[][] = new byte[2][];
                abyte3[0] = CV[1];
                abyte3[1] = Da[1];
                if (a(packageinfo, abyte3) == null)
                {
                    Log.w("GooglePlayServicesUtil", "Google Play services signature (test key) invalid on Glass.");
                    return 9;
                }
                byte abyte4[][] = new byte[2][];
                abyte4[0] = CV[1];
                abyte4[1] = Da[1];
                if (a(packageinfo2, abyte4) == null)
                {
                    Log.w("GooglePlayServicesUtil", (new StringBuilder()).append("Calling package ").append(packageinfo2.packageName).append(" signature (test key) invalid on Glass.").toString());
                    return 9;
                }
            } else
            {
                byte abyte1[][] = new byte[2][];
                abyte1[0] = CV[0];
                abyte1[1] = Da[0];
                if (a(packageinfo, abyte1) == null)
                {
                    Log.w("GooglePlayServicesUtil", "Google Play services signature invalid (release key) on Glass.");
                    return 9;
                }
                byte abyte2[][] = new byte[1][];
                abyte2[0] = Da[0];
                if (a(packageinfo2, abyte2) == null)
                {
                    Log.w("GooglePlayServicesUtil", (new StringBuilder()).append("Calling package ").append(packageinfo2.packageName).append(" signature (release key) invalid on Glass.").toString());
                    return 9;
                }
            }
        } else
        if (ii.F(context))
        {
            if (a(packageinfo, CV) == null)
            {
                Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                return 9;
            }
        } else
        {
            PackageInfo packageinfo1;
            byte abyte0[];
            try
            {
                packageinfo1 = packagemanager.getPackageInfo("com.android.vending", 64);
            }
            catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception1)
            {
                Log.w("GooglePlayServicesUtil", "Google Play Store is missing.");
                return 9;
            }
            abyte0 = a(packageinfo1, CV);
            if (abyte0 == null)
            {
                Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
                return 9;
            }
            if (a(packageinfo, new byte[][] {
    abyte0
}) == null)
            {
                Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                return 9;
            }
        }
        int i = ii.aB(0x4da6e8);
        if (ii.aB(packageinfo.versionCode) < i)
        {
            Log.w("GooglePlayServicesUtil", (new StringBuilder()).append("Google Play services out of date.  Requires 5089000 but found ").append(packageinfo.versionCode).toString());
            return 2;
        }
        ApplicationInfo applicationinfo;
        try
        {
            applicationinfo = packagemanager.getApplicationInfo("com.google.android.gms", 0);
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception2)
        {
            Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.");
            namenotfoundexception2.printStackTrace();
            return 1;
        }
        return applicationinfo.enabled ? 0 : 3;
    }

    public static boolean isUserRecoverableError(int i)
    {
        switch (i)
        {
        default:
            return false;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 9: // '\t'
        case 12: // '\f'
            return true;
        }
    }

    public static boolean showErrorDialogFragment(int i, Activity activity, int j)
    {
        return showErrorDialogFragment(i, activity, j, null);
    }

    public static boolean showErrorDialogFragment(int i, Activity activity, int j, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        return b(i, activity, null, j, oncancellistener);
    }

    public static void showErrorNotification(int i, Context context)
    {
        Resources resources = context.getResources();
        Notification notification = new Notification(0x108008a, resources.getString(com.google.android.gms.R.string.common_google_play_services_notification_ticker), System.currentTimeMillis());
        notification.flags = 0x10 | notification.flags;
        String s = f(context, i);
        String s1 = B(context);
        notification.setLatestEventInfo(context, s, resources.getString(com.google.android.gms.R.string.common_google_play_services_error_notification_requested_by_msg, new Object[] {
            s1
        }), getErrorPendingIntent(i, context, 0));
        ((NotificationManager)context.getSystemService("notification")).notify(39789, notification);
    }

    public static void z(Context context)
        throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
    {
        int i = isGooglePlayServicesAvailable(context);
        if (i != 0)
        {
            Intent intent = c(context, i);
            Log.e("GooglePlayServicesUtil", (new StringBuilder()).append("GooglePlayServices not available due to error ").append(i).toString());
            if (intent == null)
            {
                throw new GooglePlayServicesNotAvailableException(i);
            } else
            {
                throw new GooglePlayServicesRepairableException(i, "Google Play Services not available", intent);
            }
        } else
        {
            return;
        }
    }

    static 
    {
        byte abyte0[][] = new byte[2][];
        abyte0[0] = au("0\202\004C0\202\003+\240\003\002\001\002\002\t\000\302\340\207FdJ0\2150\r\006\t*\206H\206\367\r\001\001\004\005\0000t1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0200\016\006\003U\004\003\023\007Android0\036\027\r080821231334Z\027\r360107231334Z0t1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0200\016\006\003U\004\003\023\007Android0\202\001 0\r\006\t*\206H\206\367\r\001\001\001\005\000\003\202\001\r\0000\202\001\b\002\202\001\001\000\253V.\000\330;\242\b\256\n\226o\022N)\332\021\362\253V\320\217X\342\314\251\023\003\351\267T\323r\366@\247\033\035\313\023\tgbNFV\247wj\222\031=\262\345\277\267$\251\036w\030\213\016jG\244;3\331`\233w\0301E\314\337{.Xft\311\341V[\037LjYU\277\362Q\246=\253\371\305\\'\"\"R\350u\344\370\025Jd_\211qh\300\261\277\306\022\352\277xWi\2734\252y\204\334~.\242vL\256\203\007\330\301qT\327\356_d\245\032D\246\002\302I\005AW\334\002\315_\\\016U\373\357\205\031\373\343'\360\261Q\026\222\305\240o\031\321\203\205\365\304\333\302\326\271?h\314)y\307\016\030\253\223\206k;\325\333\211\231U*\016;L\231\337X\373\221\213\355\301\202\2725\340\003\301\264\261\r\322D\250\356$\377\37538r\253R!\230^\332\260\374\r\013\024[j\241\222\205\216y\002\001\003\243\201\3310\201\3260\035\006\003U\035\016\004\026\004\024\307}\214\302!\027V%\232\177\323\202\337k\343\230\344\327\206\2450\201\246\006\003U\035#\004\201\2360\201\233\200\024\307}\214\302!\027V%\232\177\323\202\337k\343\230\344\327\206\245\241x\244v0t1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0200\016\006\003U\004\003\023\007Android\202\t\000\302\340\207FdJ0\2150\f\006\003U\035\023\004\0050\003\001\001\3770\r\006\t*\206H\206\367\r\001\001\004\005\000\003\202\001\001\000m\322R\316\357\2050,6\n\252\316\223\233\317\362\314\251\004\273]z\026a\370\256F\262\231B\004\320\377Jh\307\355\032S\036\304YZb<\346\007c\261g)zz\343W\022\304\007\362\b\360\313\020\224)\022M{\020b\031\300\204\312>\263\371\255_\270q\357\222&\232\213\342\213\361mD\310\331\240\216l\262\360\005\273?\342\313\226D~\206\216s\020v\255E\263?`\t\352\031\301a\346&A\252\231'\035\375R(\305\305\207\207]\333\177E'X\326a\366\314\f\314\2675.BL\3046\\R52\3672Q7Y<J\343A\364\333A\355\332\r\013\020q\247\304@\360\376\236\240\034\266'\312gCi\320\204\275/\331\021\377\006\315\277,\372\020\334\017\211:\343Wb\221\220H\307\357\306LqD\027\203B\367\005\201\311\336W:\365[9\r\327\375\271A\2061\211]_u\2370\021&\207\377b\024\020\300i0\212");
        abyte0[1] = au("0\202\004\2500\202\003\220\240\003\002\001\002\002\t\000\325\205\270l}\323N\3650\r\006\t*\206H\206\367\r\001\001\004\005\0000\201\2241\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0200\016\006\003U\004\n\023\007Android1\0200\016\006\003U\004\013\023\007Android1\0200\016\006\003U\004\003\023\007Android1\"0 \006\t*\206H\206\367\r\001\t\001\026\023android@android.com0\036\027\r080415233656Z\027\r350901233656Z0\201\2241\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0200\016\006\003U\004\n\023\007Android1\0200\016\006\003U\004\013\023\007Android1\0200\016\006\003U\004\003\023\007Android1\"0 \006\t*\206H\206\367\r\001\t\001\026\023android@android.com0\202\001 0\r\006\t*\206H\206\367\r\001\001\001\005\000\003\202\001\r\0000\202\001\b\002\202\001\001\000\326\316.\b\n\277\3421M\321\215\263\317\323\030\\\264=3\372\ft\341\275\266\321\333\211\023\366,\\9\337V\370F\201=e\276\300\363\312Bk\007\305\250\355Z9\220\301g\347k\311\231\271'\211K\217\013\"\000\031\224\251)\025\345r\305m*0\033\243o\305\374\021:\326\313\236t5\241m#\253}\372\356\341e\344\337\037\n\215\275\247\n\206\235QlN\235\005\021\226\312|\fU\177\027[\303u\371H\305j\256\206\b\233\244O\212\246\244\335\232}\277,\n5\"\202\255\006\270\314\030^\261Uy\356\370m\b\013\035a\211\300\371\257\230\261\302\353\321\007\352E\253\333h\243\307\203\212^T\210\307lS\324\013\022\035\347\273\323\016b\f\030\212\341\252a\333\274\207\335<d_/U\363\324\303u\354@p\251?qQ\3306p\301j\227\032\276^\362\321\030\220\341\270\256\363)\214\360f\277\236l\341D\254\232\350m\034\033\017\002\001\003\243\201\3740\201\3710\035\006\003U\035\016\004\026\004\024\215\034\305\276\225LC<a\206:\025\260L\274\003\362O\340\2620\201\311\006\003U\035#\004\201\3010\201\276\200\024\215\034\305\276\225LC<a\206:\025\260L\274\003\362O\340\262\241\201\232\244\201\2270\201\2241\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0200\016\006\003U\004\n\023\007Android1\0200\016\006\003U\004\013\023\007Android1\0200\016\006\003U\004\003\023\007Android1\"0 \006\t*\206H\206\367\r\001\t\001\026\023android@android.com\202\t\000\325\205\270l}\323N\3650\f\006\003U\035\023\004\0050\003\001\001\3770\r\006\t*\206H\206\367\r\001\001\004\005\000\003\202\001\001\000\031\323\f\361\005\373x\222?L\r}\322##=@\226z\317\316\000\b\035[\327\306\351\326\355 k\016\021 \225\006Al\242D\223\231\023\322kJ\240\340\365$\312\322\273\\nL\241\001j\025\221n\241\354]\311Z^:\001\0006\364\222H\325\020\233\277.\036a\201\206g:;\345m\257\013w\261\302)\343\302U\343\350L\220]#\207\357\272\t\313\361; +NZ\"\3112cHJ#\322\374)\372\237\0319u\2273\257\330\252\026\017B\226\302\320\026>\201\202\205\234fC\351\301\226/\240\301\20333[\300\220\377\232k\"\336\321\255DB)\2459\251N\357\255\253\320e\316\322K>Q\345\335{fx{\357\022\376\227\373\244\204\304#\373O\370\314IL\002\360\365\005\026\022\377e)9>\216F\352\305\273!\362w\301Q\252_*\246'\321\350\235\247\n\266\0035i\336;\230\227\277\377|\251\332>\022C\366\013");
        CV = abyte0;
        byte abyte1[][] = new byte[2][];
        abyte1[0] = au("0\202\002R0\202\001\273\002\004I4\230~0\r\006\t*\206H\206\367\r\001\001\004\005\0000p1\0130\t\006\003U\004\006\023\002US1\0130\t\006\003U\004\b\023\002CA1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google, Inc1\0240\022\006\003U\004\013\023\013Google, Inc1\0200\016\006\003U\004\003\023\007Unknown0\036\027\r081202020758Z\027\r360419020758Z0p1\0130\t\006\003U\004\006\023\002US1\0130\t\006\003U\004\b\023\002CA1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google, Inc1\0240\022\006\003U\004\013\023\013Google, Inc1\0200\016\006\003U\004\003\023\007Unknown0\201\2370\r\006\t*\206H\206\367\r\001\001\001\005\000\003\201\215\0000\201\211\002\201\201\000\237H\003\031\220\371\261G&8N\004S\321\217\214\013\277\215\307{%\004\244\261 |LlD\272\274\000\255\306a\017\246\266\253-\250\0163\362\356\361k&\243\366\270[\232\372\312\220\237\373\276\263\364\311O~\201\"\247\230\340\353\247\\\355=\322)\372se\364\025\026AZ\251\301a}\325\203\316\031\272\350\240\273\330\205\374\027\251\264\275&@\200Q!\252\333\223w\336\264\000\0238\024\030\210.\305\"\202\374X\r\002\003\001\000\0010\r\006\t*\206H\206\367\r\001\001\004\005\000\003\201\201\000@\206f\236\3261\332C\204\335\320a\322&\340s\271\214\304\271\235\370\265\344\276\236<\276\227P\036\203\337\034o\251Y\300\316`\\O\322\254m\034\204\316\336 Gl\272\261\233\350\362 :\377w\027\255e-\217\314\211\007\b\321!m\250DWY&I\340\351\323\304\273L\365\215\241\235\261\324\374A\274\271XOd\346_A\r\005)\375[h\203\214\024\035\n\233\321\333\021\221\313*\r\367\220\352\f\261-\263\244");
        abyte1[1] = au("0\202\004\2500\202\003\220\240\003\002\001\002\002\t\000\204~O\362\326\265\336\2160\r\006\t*\206H\206\367\r\001\001\005\005\0000\201\2241\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0200\016\006\003U\004\n\023\007Android1\0200\016\006\003U\004\013\023\007Android1\0200\016\006\003U\004\003\023\007Android1\"0 \006\t*\206H\206\367\r\001\t\001\026\023android@android.com0\036\027\r100120010135Z\027\r370607010135Z0\201\2241\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0200\016\006\003U\004\n\023\007Android1\0200\016\006\003U\004\013\023\007Android1\0200\016\006\003U\004\003\023\007Android1\"0 \006\t*\206H\206\367\r\001\t\001\026\023android@android.com0\202\001 0\r\006\t*\206H\206\367\r\001\001\001\005\000\003\202\001\r\0000\202\001\b\002\202\001\001\000\330(q|6\321\027\017\324M\n{\017\007\021&\350[\277\3373\2604`\000Z\224\314\373e\245\333\240\262C\337`\261\221\277\235\006\337\035\212\\\n3\342\321c\365\023\337\035\"SA\352<3y\"\350\\\002\3544\316\331L\270\007#\246#\377K\257\373\264\345\357\346w;>\242\276\270\274\262\002g\317\347\205Q\037\203.\371\207\253u\224\376\036)\317\274M\b:\037\022R\000ws\226\362\026[i{\000\243\240\301:\3140\212\223\362!c\301n\234=J\262\024\2376LE\300C\0242p9\361\332\t`\223\361\263\374\030\266V\020\225\306\"_\307\020+\230|o\023\244]$\343\340\305N\205\235g\343[g\b'\023\322\326\360W\3354W\321\237\304\376\215\335\354\214:O?\227#\005\031\247\n(64\2545\201\243J\275\241}\204Z\n\t\205\373\370\006\013\003j'x`\201c\372\f7\271\347\362\241\016v\274w\002\001\003\243\201\3740\201\3710\035\006\003U\035\016\004\026\004\024\265\307\371\022ox\r:\373\312ess?\365\"k\233\02770\201\311\006\003U\035#\004\201\3010\201\276\200\024\265\307\371\022ox\r:\373\312ess?\365\"k\233\0277\241\201\232\244\201\2270\201\2241\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0200\016\006\003U\004\n\023\007Android1\0200\016\006\003U\004\013\023\007Android1\0200\016\006\003U\004\003\023\007Android1\"0 \006\t*\206H\206\367\r\001\t\001\026\023android@android.com\202\t\000\204~O\362\326\265\336\2160\f\006\003U\035\023\004\0050\003\001\001\3770\r\006\t*\206H\206\367\r\001\001\005\005\000\003\202\001\001\000L>\247e}&\346\273\327\021\f\217\031\337\037\215\241\t}3\206\017i\336\277\312\333F\243~\207\345\263\017\2734{\034uU\274\273<\231T\024\200F\226_\234y*\002\320\333\345\246Ga\263yG\253k\377\260\272\306\242\301\240\315\370b\370w\251g\r\375o\006.@n\316\030\006\f`I\215\3746\237'\021q\230\345o\313\241R\346\005\215\316\224\316Y\037\304\364\251\230+3\272\330\031mwoU\267\320\032\3171\335\327\f\354\267\211xv\006e\020\371I\245RJ11\263\313eA\317\2135B\016\274\304R%Y\226?Bfi\005rfbO\263\230\317\333R\027\210\035\021\034n\003F\026\370Q!\030\320\242\246\235\023\327\222\360\315\021\333\325\216#\203ZT\245J\302Q\347\322,Dj?\356\024\022\020\351DGK@c\007\273&\204+Ok\323U\202\034s\226Q\377\242`[\005\342$\225\327\025\330z\221\366");
        CW = abyte1;
        byte abyte2[][] = new byte[1][];
        abyte2[0] = au("0\202\002\2470\202\002e\240\003\002\001\002\002\004P\005|B0\013\006\007*\206H\3168\004\003\005\000071\0130\t\006\003U\004\006\023\002US1\0200\016\006\003U\004\n\023\007Android1\0260\024\006\003U\004\003\023\rAndroid Debug0\036\027\r120717145250Z\027\r220715145250Z071\0130\t\006\003U\004\006\023\002US1\0200\016\006\003U\004\n\023\007Android1\0260\024\006\003U\004\003\023\rAndroid Debug0\202\001\2670\202\001,\006\007*\206H\3168\004\0010\202\001\037\002\201\201\000\375\177S\201\035u\022)R\337J\234.\354\344\347\366\021\267R<\357D\000\303\036?\200\266Q&iE]@\"Q\373Y=\215X\372\277\305\365\2720\366\313\233Ul\327\201;\200\0354o\362f`\267k\231P\245\244\237\237\350\004{\020\"\302O\273\251\327\376\267\306\033\370;W\347\306\250\246\025\017\004\373\203\366\323\305\036\303\0025T\023Z\026\2212\366u\363\256+a\327*\357\362\"\003\031\235\321H\001\307\002\025\000\227`P\217\025#\013\314\262\222\271\202\242\353\204\013\360X\034\365\002\201\201\000\367\341\240\205\326\233=\336\313\274\253\\6\270W\271y\224\257\273\372:\352\202\371WL\013=\007\202gQYW\216\272\324YO\346q\007\020\201\200\264I\026q#\350L(\026\023\267\317\t2\214\310\246\341<\026z\213T|\215(\340\243\256\036+\263\246u\221n\243\177\013\372!5b\361\373bz\001$;\314\244\361\276\250Q\220\211\250\203\337\341Z\345\237\006\222\213f^\200{U%d\001L;\376\317I*\003\201\204\000\002\201\200j\321\033\327\325f\322z\3649\300.Ah\254\375E\264\276\205\274\231\214{\233\216\034wTi?\214\rB\212\244\374\341\020\204\2018BO\246\214\3210RN\357\366\36178c\202/\2467)\213\376MF\240\270fe\356\360A\0279\001\003[\034\200j\243\030\030\r0:\250\314\236Y#\340jo\253\372uh<E;\262\007w|\362\375\347\317\261\233\02408\024\252\035\367\264=[\"+W\006\264\213\2240\013\006\007*\206H\3168\004\003\005\000\003/\0000,\002\024\t\322\321\260G\002)\265\276\322\220&a\321\022\362p\305\346\035\002\024gP\002\006\247\200P\272x\256\307\027O\026\004\177\204\352\242\367");
        CX = abyte2;
        byte abyte3[][] = new byte[2][];
        abyte3[0] = au("0\202\004L0\202\0034\240\003\002\001\002\002\t\000\250\315\027\311=\245\331\2200\r\006\t*\206H\206\367\r\001\001\005\005\0000w1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0230\021\006\003U\004\003\023\nGoogle NFC0\036\027\r110324010653Z\027\r380809010653Z0w1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0230\021\006\003U\004\003\023\nGoogle NFC0\202\001 0\r\006\t*\206H\206\367\r\001\001\001\005\000\003\202\001\r\0000\202\001\b\002\202\001\001\000\303\017\210\255\331\264\222\tj,XjZ\232\2005k\372\002iX\370\377\f]\372\365\237I&\212\330p\336\350!\245>\037[\027\017\311bE\243\311\202\247\313E'\005;\343^4\363\226\322K\"\221\354\fR\215n&\222te\340hu\352b\037\177\371\214@\3434[ I\007\314\223Tt:\315\252\316eV_H\272t\315A!\315\310v\3375\"\272\333\t\\ \3314\305j>\\9>\345\360\340/\217\340b\037\221\215\0375\250$\211%,o\246\2663\222\247hk>Ha-\006\251\317oI\277\361\035]\226(\234\235\376\024\254WbC\226\227\335)\352\375\271\201\r\343&5\023\251\005\254\216\216\257 \220~Fu\nZ\267\277\232w&/G\260?Z<nm{Q4?i\307\367%\367\013\314\033J\325\222%\013pZ\206\346\350>\342\2567\376W\001\274\275\262o\356\375\377\366\017j[\337\265\266G\223\002\001\003\243\201\3340\201\3310\035\006\003U\035\016\004\026\004\024\034\316\316\016\352M\301\022\037\307Q_\r\n\fr\340\214\311m0\201\251\006\003U\035#\004\201\2410\201\236\200\024\034\316\316\016\352M\301\022\037\307Q_\r\n\fr\340\214\311m\241{\244y0w1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0230\021\006\003U\004\003\023\nGoogle NFC\202\t\000\250\315\027\311=\245\331\2200\f\006\003U\035\023\004\0050\003\001\001\3770\r\006\t*\206H\206\367\r\001\001\005\005\000\003\202\001\001\000\244p\307(\341\323\033\006\331\257j\347h\265e\004lW\200k\230CrI1\327]L\241\f2\025 \323<\317\355*\246Tb#L\236\371\266\371\020\314gk\231\313\177\230\225\326\300gcWO\273x3\022u\334\\\363\217\272\251\030\327\223\214\005\037\373\242\255\350\363\003\315\350\331\346\212\004\215\037\333\236|\237*I\262\"\306\217\377B+\361Ui\270^\356\355\260J\243\bs\333\346K\234\236t\370\362\302\366\304\001$\252\250\321x\r\030Q+T\n\335(\263\351X\031q\244\027\r\330h\317_1\344G\022\262\302;\265\0207\327\357\237\207\246\345\275\263^,\353k\260\"cl\027\245j\226\274zP%\214\013\322\355{1UZ\030E.\0272\032\rR\203\214\202\366?t-t\377yXj\\\273\177\257q\230\250K\317tC\020\351\351'Y\177\000\242=\320\006`\200\f\"8\331\013/\263r\337\333\272u\275\205.");
        abyte3[1] = au("0\202\004L0\202\0034\240\003\002\001\002\002\t\000\336v\225\004\035vP\3000\r\006\t*\206H\206\367\r\001\001\005\005\0000w1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0230\021\006\003U\004\003\023\nGoogle NFC0\036\027\r110324010324Z\027\r380809010324Z0w1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0230\021\006\003U\004\003\023\nGoogle NFC0\202\001 0\r\006\t*\206H\206\367\r\001\001\001\005\000\003\202\001\r\0000\202\001\b\002\202\001\001\000\346\377=\357\351*\241\rq\353\017\246@\213\3006\267\342C\356\355h\246\244v=\307\245*1u|\332\306\037\345\020\273s\307\026\344\000\001\004&[4\177\316\316\364\304+\361\3417\235\320\250v\360(\"\177\273\301\371\275\325\327\023\262\366\2515\243y\322\313\251\311o\222\322\320x|\021\361\353\031T\200\b\246\240r\263K\221\203l\372\n\341'g\200\351\000u0\026i\206\241\034\234\357F\316\367\307\004\200m\336\2241\373`(M\022\n\260\347\336\035c?\007h}F\214Q\023\232\377\375\306\274\232 |\251\004\270\276\035\240\252{N\227uoC`d\210\276\\\256<h\350\273yB\315\365\026\007\3110\242\374\332e[u\320u\234\272\211\255\006\3479\275\013\242\233\037@B\226\302\300\250Z\204\177Z\260\320g\306\303\354\234I! B\254c\247\345;Tle\264`\200\264\343\346\200\342>\037w\317\347\366\336tK\032e\002\001\003\243\201\3340\201\3310\035\006\003U\035\016\004\026\004\024\242\350\220d\260]\b\206\\4\333\223\n\235\204\000P\021z\3540\201\251\006\003U\035#\004\201\2410\201\236\200\024\242\350\220d\260]\b\206\\4\333\223\n\235\204\000P\021z\354\241{\244y0w1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0230\021\006\003U\004\003\023\nGoogle NFC\202\t\000\336v\225\004\035vP\3000\f\006\003U\035\023\004\0050\003\001\001\3770\r\006\t*\206H\206\367\r\001\001\005\005\000\003\202\001\001\0007q\207\f\350|<R\352\204\211\2220\306\351b\331KM_\022\223\302]\210&\025A\375\220\265U]\022\205\316\363\2701,?]\366\221\250\252\340L\271\201\263\005\344'\375\035-\236\031\207\341\322\220x\361<\204R\231\017\030!\230\002c\330\324\2756Q\223H\330\330\272&\330\271\237\277\t\365\375>\273\016\243\302\360\3117o\036\037\312v\363\246\244\005B\235\b\033u*z\220\267V\351\253D\332A\253\310\341\350\370\212\302u\215\247C\373s\346Pq\232W\204\f\313kz\335!\271\237\306\201\344V\341\207,\"=\\\007J\337U\366\253\332&\214-\213d\352\n\210E\356\315\226\217\222\264\223\022~u\307S\303\3770\313\306x\265\034\237R\226\024r\361}\242\n\r\306'J\242F44\301\251\266\024\337i}\217\365\312\201\001\347\242\\}\263\373\005]eV\234\004\260\0358\234\253\272W\263\241p>\302\347J\210\3234");
        CY = abyte3;
        byte abyte4[][] = new byte[2][];
        abyte4[0] = au("0\202\005a0\202\003K\002\006\001D\236\221\226\3230\013\006\t*\206H\206\367\r\001\001\0050v1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0220\020\006\003U\004\003\023\tClockWork0\036\027\r140307220225Z\027\r380119031407Z0v1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0240\022\006\003U\004\n\023\013Google Inc.1\0200\016\006\003U\004\013\023\007Android1\0220\020\006\003U\004\003\023\tClockWork0\202\002\"0\r\006\t*\206H\206\367\r\001\001\001\005\000\003\202\002\017\0000\202\002\n\002\202\002\001\000\272<\1779\013\376Y\212b\274\374\213<\224\306'Z\231\025\355\334\3357:Uj\231\013\342\375C\367\237\030\263\001\322@'\343r\177\t\316\375\342\234|&\260\212 \3366}\032\337\343N\247\217\2567\363\220\365\340&rzN\b(;\357v\370\366C\274\025'6\240H?\311\267\221\253R<\363\275\206{f-*'L\000\330\220\347\235\021\350\260&_\355\251u\334\310\345B\231\211\216\220\023jbq\214.\013/9yQ\333$\261W\241\277\347\305k\316J8\013%\372\271&c>\250\224\0048\340\266\224\013\271\236\211~.\372\005<2)\233\331ao\244\275\226\202!{7C\037\315\330\202\355!\247\362\360F\177\340\225\034\274Z\230b\343J\025k\341Z\027\377\002\027\230dD\326\023\261\036\327_\203\200\030\356\264\375\224\344\217Z\343\034\344\257\24468\266\227,\205\\\322\333\n\001\3042a(\344\305\031z\276\254\314m\302\350\255\244B_\017\220\325\245\245X$a\277x\021\341.\316\016\352\006\003?\226T9\355\340q\377\304l \362\337\276##:\177d\301\316\t\255\241\313\316k\366\274\242.\233\230\234J\300\311j\235luO\354\030q\330{\020\230\301\240\336`\274}w\3360\325N\270G\316k\022|\031\036\247\223o\nF\301F\3636\2714\352\272Z_\034\003d\267R\226UD2P\375c\252\345{\353\253\340&?\t\bM\031D\006\f:\331\273\272\177y\364\336<+-7\272\263\rK\271\021\334Qi\340\257\225R\364\323\216=\263\362\313\200\034R\002Rpa\277\001\260B\320~\211\344\217\021\251\252\240'\360D\225\236\332(\305\335\330SW\247\0369\273\202Q\263W\353or\030\374\314\027\030\201\2460gF1\340U\2249\032zg\232\362Z\240b\001\326\"\270\320\t\335\021\325\006\242\003\017$'\256g\330\03347yy\002\003\001\000\0010\013\006\t*\206H\206\367\r\001\001\005\003\202\002\001\000\244\304\226\2264a\310\2255\245\261\n\315\001$7j\211\332'C\235\2540\003Hg\013 +\255\343?/\272*\007d\003\265\013\350q\312*\262\233\276\275\273\304\006\333\t9A\311\214\027j\016F\377\220\377\000\026\026\004D\200n\334\202\3410\376\020\206\036\343\005\235\267~=\235\251\242\2554\251\322\264\332\033&\375Z[p\034\325l\376\351dz\344\024;\227\246|\002\200e\261\177\236\024\3622\245\357\027\341d\241I\027\222\226\224\0340\275Z6\253\370\363B\310\343\257\274oICs\007}j\234\021\3279\"\rZ\327\265\031/\233\034\376\226\217Jr\261\270Tu\340\351\210\276hr\210fe\261+\364\356\303\"VT\365\341\362+\213\353U\216\276fw\213\326_\t\221-\371^\200\235\376\357\017\307\352\312]\016\276\035A\004\037\347 \3132\2330~9.\023\227\361 9Ti0\204\213\177\002\027@\211-\366\307\240r\347\3378\272\303\327\"5o\346T\177j|W\212\337g\311=+5\210\223T5\360\371\241\023\316-\354\315m\241\235\303KA\202\354\256\326 \353R\2050%\305\340\004\354\264Q\274E\341HZ\3146\177\266I\222\257YLU\033\013\3118\313\326\032\325gY\220 \367:e\341\251\310\244\210\333\254\203\036\353\221\217\f\222)\t^\336A\005{<\256\352N\026\305\271EK\222\342Y\212\021\264\224\242\037?z\277\203\300g\364\030.\230A\233\244\344\223\2124\200\360\206/\355\257WrJU3W\217\332_\263\315\374\371T\200\177\377\330\311Qw\347u\004\246B\276\\\333\240\341\000e\374|h\022\234\355'\263\250\004\327\244\315\331\f\323\354\313\005\250\310\212`\232\320V\240N\264\212\036\005\225\3759\\\037\247{\263\035\245$4^\n\275N\001\265\006\202O\352\272B\323-\324\222g>\317\300\027\235\206\035&\351\315\\F\357\320");
        abyte4[1] = au("0\202\003\2770\202\002\247\240\003\002\001\002\002\t\000\332\230\303\331\025s\323\3570\r\006\t*\206H\206\367\r\001\001\005\005\0000v1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\f\nCalifornia1\0260\024\006\003U\004\007\f\rMountain View1\0240\022\006\003U\004\n\f\013Google Inc.1\0200\016\006\003U\004\013\f\007Android1\0220\020\006\003U\004\003\f\tClockWork0\036\027\r140307220118Z\027\r410723220118Z0v1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\f\nCalifornia1\0260\024\006\003U\004\007\f\rMountain View1\0240\022\006\003U\004\n\f\013Google Inc.1\0200\016\006\003U\004\013\f\007Android1\0220\020\006\003U\004\003\f\tClockWork0\202\001\"0\r\006\t*\206H\206\367\r\001\001\001\005\000\003\202\001\017\0000\202\001\n\002\202\001\001\000\334\035oK(\35580\024\262\234\202\366\332\377\323\035\336{\214\036c\b@e\013X\261e\243j\256\266,qS\225.\004E\t\257\202\037\224\272\237O\030d\303\247\265\326S\314\000\025\235\000\020\341\345f\3727\252\377\0306]\256{J\205\335\261\363\203\314Gp\242>\225b\221\376\265r\301i1Z\257N\364\352\245\256\206\037\315\326\347\345\352\3241\023tFF\f|(\3732,\222\225\\\\z\250\225w\303p?\227\340\230\267~\266\240n\254kr\352\240\255!\n\260*\037\334\374vbttA\251?<\352\212\026\364\214\227\"\301\3432A2~\302\311\36701.\215\033\357\356)\013E\0324\211,\254\357[\024r\326\331~\371T(\314\212\325\357\004\270\304\361\365\r\322B\325]rXf\205P[^K\033\036Y\255\214\035\205/\240\202H\025g;\306\346C)\354\304\352\324\333\204d\251k1\203\237\237\333\311\007\002\003\001\000\001\243P0N0\035\006\003U\035\016\004\026\004\024\204\205G\020\204\244<\263\352\370?\253!b\240\225\000\316,z0\037\006\003U\035#\004\0300\026\200\024\204\205G\020\204\244<\263\352\370?\253!b\240\225\000\316,z0\f\006\003U\035\023\004\0050\003\001\001\3770\r\006\t*\206H\206\367\r\001\001\005\005\000\003\202\001\001\000\0079b\013\242}*\017T\210C\255\033`\216\034)\331\001(\201\352\374?\326(__bj\227>\360W\346\227\356\262\254\\\242\346\005\312=3\365\220\231k\213\0002\304G\346P\017%\275\027\312\371\225\0039\203@\310\356l\334\265;\355\344\261\362H\347\320 \231\236\201\347\312\352\2452\317\332\231\376J\245\355@@ND\367[\357\322\177\312\3335\270\262\033\224xF^\027\"\362z\373+\013n\025\216D\304\253\fOe{\031\327}\217S\311\317\271\356-OE\266T\340\022\274\215\351\201\344\302\342\303\323\236Q\223\003\330\256M,\301\310b\215xW\256u?\035{\002\243\247\005x\306\005\343\005\034l\035\251I\032\316\023\273\210\320}\201}\324\224&Q\227\204\256\226\225\244G5\r\211\353@^\220\362\253\363f\256/\312X\322\366\277\035\277K\034H\236\340\240\001T\337\317\002%\022\365\241\307\"\236s\035\343\360G\326\370");
        CZ = abyte4;
        byte abyte5[][] = new byte[2][];
        abyte5[0] = au("0\202\003m0\202\002W\002\006\001=d\370\326\2630\013\006\t*\206H\206\367\r\001\001\0050|1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0250\023\006\003U\004\n\023\fGoogle, Inc.1\0200\016\006\003U\004\013\023\007GoogleX1\0270\025\006\003U\004\003\023\016Glass Platform0\036\027\r130313181742Z\027\r380119031407Z0|1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\023\nCalifornia1\0260\024\006\003U\004\007\023\rMountain View1\0250\023\006\003U\004\n\023\fGoogle, Inc.1\0200\016\006\003U\004\013\023\007GoogleX1\0270\025\006\003U\004\003\023\016Glass Platform0\202\001\"0\r\006\t*\206H\206\367\r\001\001\001\005\000\003\202\001\017\0000\202\001\n\002\202\001\001\000\2413\246\322i\257\3306\343\256\334-\244\255\2169\277\370\342\031\346H3\264\030\265\204=5l\354\024\270\236G\227F\264\230\013\217\203\337\036B\241\376\364\321\221\365\327B\f\300\205\330\233+\347\332e\t\304\257?\333\316/PT\357\354\347A\251\351\221RZ\023#\003\377\316\211\025D\246\2149\273\312\330t\257\240\274\274\365\026\366\217Y\3327\301\255\236/\350\243\304Z\346\036\206\312\213\250W\242\005C9\355\277o\226\360@n\302\201A\275\buq\211c).\212s\251)\004\371=\354k\275@c\274\245>y\270\226\251Cp|\271\374A;X\0170\360GE\264\3657\237\255\247\346\207\201\217\032\377\374w\013N\323<>\201<ttb\036z\255\324w\255\005\334uL\204\r3\r\301X\365\346\341\207\365\242`<Q&\254B\364\030\333 xF)\340\205\r\247\353\006\2225\331\230\272G\265E\fZ\242d\330\212/\225\002\003\001\000\0010\013\006\t*\206H\206\367\r\001\001\005\003\202\001\001\000\206\350\025J\364\330\364u\260\343[\360\322R\006c\bL\317\321\206r%\351K\376\301J\037\2767Erp\300\205\377V\366V\301P\177\211\351\313\271\331l\207\034;\013\241\346<\352\365\324\346\253\231C*\334\261\227\023W\362c\264\202\350\226\210\315\256\320\274\213p}\363\026\355.\251\266Vx\324M\376\355\344/\260#\257cc\261NS\304\201B\215\262+\206\370\223\253\215\300\277i\207\211\005\367\363[(\022K\346\230c\035F\f9_5\351u\253F\336\f?\3370\317\017\007\331E\255}\307\250d;IC\340.&[\020\213t\325\371K\371X\205\354\332\372\252o\305\276\250\314f\375!\273\220\262n\317\232e\351\370.\265{g\354\301\351x\267\332'\027\210\346\326\035\347\200\246l!\277\302Y\266\336\311\275z\265\362\323\333\305\023\005\332\237\332\250\357\342)<\242\027:\354#\204\b`pNw\223\205\311\300\246b0\247");
        abyte5[1] = au("0\202\003\3110\202\002\261\240\003\002\001\002\002\t\000\303i \255\337t\235\3070\r\006\t*\206H\206\367\r\001\001\005\005\0000|1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\f\nCalifornia1\0260\024\006\003U\004\007\f\rMountain View1\0250\023\006\003U\004\n\f\fGoogle, Inc.1\0200\016\006\003U\004\013\f\007GoogleX1\0270\025\006\003U\004\003\f\016Glass Platform0\036\027\r130226205628Z\027\r400714205628Z0|1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\f\nCalifornia1\0260\024\006\003U\004\007\f\rMountain View1\0250\023\006\003U\004\n\f\fGoogle, Inc.1\0200\016\006\003U\004\013\f\007GoogleX1\0270\025\006\003U\004\003\f\016Glass Platform0\202\001 0\r\006\t*\206H\206\367\r\001\001\001\005\000\003\202\001\r\0000\202\001\b\002\202\001\001\000\257\222\312G\352\344\222\320;\036\b\340\t\227w\242\370\006\306\242\027\035\355\247[p\342:\242\361\271\277h\214\245/?v,\276\306:\b\321BZ\303\033\3512m\001\036|\006\316\241\310J\353p?\3209\227*1\006\262}\230\004^|\341T\004K\"\312\245\035[\365\371\261$\342\"\272sA-\324Y0h\202,Fg1\260Y\257\246\244\350\335?^\265\177@\370\272\021](G@)\327\0371\345\273\260\352^0\240\344\206u\265\244\3754\027\n\324.\330P\3539T,+\351\265m5\217\222\376\266\262\250\2304i\372K\346+\310\244|\350\000\003l\256\360\3653s\311X\025\270\312\331s[\277\267\000e\204h\276m\346w\020-E\277\2669z\224\237:\037\001%\270\025\005\217\005\267\253\335\210\223\302`\037~\361X\226\325(\341\255;pmhE\017%:\023\355y\300\002l\250\204\023\021\325\217\313\223\204\337\267\002\001\003\243P0N0\035\006\003U\035\016\004\026\004\024\363SB\037\017\315{#j_\204\265\037fWc\031\320{\2170\037\006\003U\035#\004\0300\026\200\024\363SB\037\017\315{#j_\204\265\037fWc\031\320{\2170\f\006\003U\035\023\004\0050\003\001\001\3770\r\006\t*\206H\206\367\r\001\001\005\005\000\003\202\001\001\000\251\220X\261/\007\357\024}C\305=\302\311\350\341\001\245b\bfF\221\274\004\267\035\367yx\334!\211/\374|\003\372\257Y%;\350\225\3652\302_]u\272\346\357\272\264XRp(hk\001B'\245A\267C\247\263/\2124\321[Y\364\240\025X%\375\274\236\354>\322t\316\354\307\001\313\253[Ug\"3wn\367\344\264\342\001R\016F\325EI\\\024\300y\255}\037\375\234\343\242\261\312\033Q[\240+7[\367/\2126\017\363\227\225-\202\255`S:\332\327\305~#dRE\245\272\312\312BT\375!\263d\233!_\004v\362\351\206I\031\367\210W\240\213\023Xv\nF!\356\003U\331\273@h\017\332\363\261\216\250\215\220f\004+\313@\2053\340w,\326\343\252\036\203\240\306\361\002\004\372\345[\243\341\331\337\353\324@U\374\007\250\346\255\215;\324\0217\366/_f\243\367\030$\365O({\332\370?");
        Da = abyte5;
        byte abyte6[][] = new byte[2][];
        abyte6[0] = au("0\202\003\3010\202\002\251\240\003\002\001\002\002\t\000\351\005DY+\204P\2020\r\006\t*\206H\206\367\r\001\001\005\005\0000w1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\f\nCalifornia1\0260\024\006\003U\004\007\f\rMountain View1\0240\022\006\003U\004\n\f\013Google Inc.1\0200\016\006\003U\004\013\f\007Android1\0230\021\006\003U\004\003\f\nmediashell0\036\027\r140527043400Z\027\r411012043400Z0w1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\f\nCalifornia1\0260\024\006\003U\004\007\f\rMountain View1\0240\022\006\003U\004\n\f\013Google Inc.1\0200\016\006\003U\004\013\f\007Android1\0230\021\006\003U\004\003\f\nmediashell0\202\001\"0\r\006\t*\206H\206\367\r\001\001\001\005\000\003\202\001\017\0000\202\001\n\002\202\001\001\000\320u\233k\027*HI*\325\356\222>r~\353`\"\254\330\b\273W\fE\227C\332\233C\304a\345\347b\034\235\005\n]\347\242\230\275\017\f\002`\377\216\223\342r\202]\330HH\201$\032\006\356\222f\254+\375\341\237\336\r\212\tE\235W\313\307\"ne\215\224\277)\035\017\345-\250\321R\222g\2237\354j\016.7\371\263\316\323\234\323\331\345\260\t\252Y\210\333!V\310\312#u\264\034f\3669\n-\016\016\230I\301xt\t\237\300\331%_\230\230\313\305Y\022\033\206O\225\362\236\213\342-\261\342t\032\000\230\332IY\364a\227n\255\016\022m\223\227\300Ke\360\230\357\017\274Bz\324\2352\317O\357M:d`\312\304\225\367rX\352-\350\275\212@\277\007\363?`]}Wf\276z!\362\236J',\252X\2459\\\315\377&}\013\324\362~D\177-\311\274\304J\024dW\247\212:J\275!_w\002\003\001\000\001\243P0N0\035\006\003U\035\016\004\026\004\024\263\314D*\333\3506x\360\202[4q4\254c\036` \2230\037\006\003U\035#\004\0300\026\200\024\263\314D*\333\3506x\360\202[4q4\254c\036` \2230\f\006\003U\035\023\004\0050\003\001\001\3770\r\006\t*\206H\206\367\r\001\001\005\005\000\003\202\001\001\000\226\207+\371\230\033q\351\350\356\002[U\351<\342\250R\027%\241\252\031\020B\357\2556\270)\240\311\033'\037\343\373i\231D3\023g\372\246&\201\244\222\342(\035\354\245\337\314\335\003\306B\252\361{t\334\177&\374\205^P\275OL\342\342\213\033k\373\354\353\033\335\230\230p\345\377\310[\376\177\262A\031D'3\364\320\332Mc<^\276\345\205\203\n\037\206\024?E\243\317\316@\256\320a\177\325+\007\271b\274f\321\230_>\324\371\326\306H\262\0218\312\236\201\372\231\376\221v\364\334\273\345\"\242l.\325\b\246\267\t\341W\177*P\257S\321\006\201:\0271\275\027\206\206i[G\331\207\2414!g\201.\fp\034\312\316\266\026g5.Ia\301\f\327JD6y+\2226N5\263\020\240_+\024\262hsbK7\350i:\034\264\3553oV\341\330 z\351\002\263>9\016Q\262\227\3124@\"\234\205");
        abyte6[1] = au("0\202\003\3010\202\002\251\240\003\002\001\002\002\t\000\320T\343\316\333\316\004\2130\r\006\t*\206H\206\367\r\001\001\005\005\0000w1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\f\nCalifornia1\0260\024\006\003U\004\007\f\rMountain View1\0240\022\006\003U\004\n\f\013Google Inc.1\0200\016\006\003U\004\013\f\007Android1\0230\021\006\003U\004\003\f\nmediashell0\036\027\r140603192622Z\027\r411019192622Z0w1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\f\nCalifornia1\0260\024\006\003U\004\007\f\rMountain View1\0240\022\006\003U\004\n\f\013Google Inc.1\0200\016\006\003U\004\013\f\007Android1\0230\021\006\003U\004\003\f\nmediashell0\202\001\"0\r\006\t*\206H\206\367\r\001\001\001\005\000\003\202\001\017\0000\202\001\n\002\202\001\001\000\265\037{\260\213\214\354\316p4c\316\323\0354\220r\204\254\266\251\243\365?\337\320\346\206#\360={4]\037\365\"\330\355ze\3453\264\342>\361@k}\325\225\024h\035>v\316\320e\005\363M#\021_\000WG6I-h\214\216\317|\266\330|\247u\302e\336!\037\255\222pg\b\217\370\2571,\3553.\307gb\b\340/{\355{4f\2412\005tu\262\212gm\270q\034v\340\202;\3147\244c\005(#\344_\202*rN8'\020&\007oT\302\351\310\271\377#\177A\224z/\310\265 s\256\t_\036\236\253\334J\232\316z\363\227%\003s\003d\022\324\361\363,(\222\222L\342\276\215\375\212_\366\350\214\303\021\344\316\033TgH\222\373\206\375\241\343\3370\246\370,\344\245\313\325*?\207\362\222\025\202s:\260\310Kz\030\220\357Ql\320\341\257\260\017\b\255\213\037\006\205\214\3772\351;\251\002\003\001\000\001\243P0N0\035\006\003U\035\016\004\026\004\024\247L:\300:o\260\n\372\372\311\327@(\\ \202\210\253\0200\037\006\003U\035#\004\0300\026\200\024\247L:\300:o\260\n\372\372\311\327@(\\ \202\210\253\0200\f\006\003U\035\023\004\0050\003\001\001\3770\r\006\t*\206H\206\367\r\001\001\005\005\000\003\202\001\001\000\240\215>dBCQ\274>Y_\324\340\245\n=-\340\"s7\000K\314;7|y\352\262\254\031\355\316]$<W2\244s\363Ph\2239\000\376\326d\006\316\b\310\023&\322\023\324\373\303\205%\337k\370\352\273\271\033<.a\260\332tFS`sj\037H-\311t;\342\236\367\207a\024\030\350CY]\236\032\032\266\230\241-\320v\306}\235L#\252\017\327\221\260\361\b\343;\367\037En\267R\303\206\272\007\366\320{\177\244W\337\001t\262\b\247\241^\311D#\227n[\324\3307\001\335_\f  r\2368b\370\002a\3104\231r\2359\244\220\013\315\020\017\f(\342\317*\203~\272#%o_\254\220$f#\017\247\317}\306\342DD\235\020\017\335\205\276\231)\301\355|G\0200\357V\006\375\005\307`\027\245\257\360\2252Sh\257\001\243 \367\017O\r\000+E\026\250\232\2064\332\314\t\315q\2346");
        Db = abyte6;
        byte abyte7[][] = new byte[2][];
        abyte7[0] = au("0\202\003\2650\202\002\235\240\003\002\001\002\002\t\000\345\327\200\027\316\203t\2410\r\006\t*\206H\206\367\r\001\001\005\005\0000q1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\f\nCalifornia1\0260\024\006\003U\004\007\f\rMountain View1\0240\022\006\003U\004\n\f\013Google Inc.1\0200\016\006\003U\004\013\f\007Android1\r0\013\006\003U\004\003\f\004nova0\036\027\r140529162639Z\027\r411014162639Z0q1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\f\nCalifornia1\0260\024\006\003U\004\007\f\rMountain View1\0240\022\006\003U\004\n\f\013Google Inc.1\0200\016\006\003U\004\013\f\007Android1\r0\013\006\003U\004\003\f\004nova0\202\001\"0\r\006\t*\206H\206\367\r\001\001\001\005\000\003\202\001\017\0000\202\001\n\002\202\001\001\000\276\241\300\221\237\204W\232\037\270\f]xw\226#T\021;\211\003b\032\326rV&-.-\212\214hI\260\021\321\371K7k\375\027\374\177\3367\244\377\232\021\211+*?/Rn\013\357\346\305\314\303B\341t\3212\303$_{\363\215\030U'\357\262-\265\016\277y\313\3623\207\335\3321K\004\273\315\362|\350/7\2075\354$e\\\324+/5\207\256J\233R\203\315\242\356t\322+\333\205R\023S\350gZ\311\270\375R\256\031\252\252o^\353_\331\272s\263b \205&\200\251h\234\214\324\302\233nW'f\017p\256A\031/v;=pUV1*\375\037\n`\326\376\021\372R\246\022: \3163A$\375\234\306\n~*z6y\333\243=\205\370\"\233\264\007m\312\272\273\220\253\204\316\333\034\220&\313\001\353\317\302\023f\360\0330\016\232\265\227H\312\361\005x\306\210\r:kihR\\x<\003\335\002\003\001\000\001\243P0N0\035\006\003U\035\016\004\026\004\024\302\270\347\001\220A\360\n\340\013\\\006?Mn\372\007p\246\2100\037\006\003U\035#\004\0300\026\200\024\302\270\347\001\220A\360\n\340\013\\\006?Mn\372\007p\246\2100\f\006\003U\035\023\004\0050\003\001\001\3770\r\006\t*\206H\206\367\r\001\001\005\005\000\003\202\001\001\000\215H\210\241L\317sf\246\005\363\235S\003,P\020$Ze%\220\205\020\305)@\206\354\333\276_\334} \3354\200\255\202\322\033\231\224\317V\360\302-\3039\376I\023:\213\373\320\302!\243\355_\232rE\345!9\274\220\022Vw\362\205\236I2\177\3553\2134\213\267|\253\255\025F\022\221tN\274\030\335&C\363c#UH\314^\301?JqxP?\234c\367\0176C@\371c7\206]\310\311=\372zO'\302\237\231\330Z\356\375\254\242\271\317\234\013\236q\244\rr\376A\314\004d\257,1\b\354\222|\215\006\177\211U\365\253\322\203\253\242\221U\341\315Y\"\263v\267K\272\033\205\005[\307\327\203\266\363'\224 \247\322\365 \274\003\347\265n+\333'\246\360\210\237)qG\025$ \351\321D\300\376\345\316\242\210)\337\b-\371\233\234\0207p\000u\356<\350\300\316\\\003\2715\323\375\025v\005Fl");
        abyte7[1] = au("0\202\003\2650\202\002\235\240\003\002\001\002\002\t\000\345o\234\t\033\222\204\0370\r\006\t*\206H\206\367\r\001\001\005\005\0000q1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\f\nCalifornia1\0260\024\006\003U\004\007\f\rMountain View1\0240\022\006\003U\004\n\f\013Google Inc.1\0200\016\006\003U\004\013\f\007Android1\r0\013\006\003U\004\003\f\004nova0\036\027\r140529162612Z\027\r411014162612Z0q1\0130\t\006\003U\004\006\023\002US1\0230\021\006\003U\004\b\f\nCalifornia1\0260\024\006\003U\004\007\f\rMountain View1\0240\022\006\003U\004\n\f\013Google Inc.1\0200\016\006\003U\004\013\f\007Android1\r0\013\006\003U\004\003\f\004nova0\202\001\"0\r\006\t*\206H\206\367\r\001\001\001\005\000\003\202\001\017\0000\202\001\n\002\202\001\001\000\304\222_U\2724\\\226\321\233@_I\332\31796\326r\017\3136\341M\332\377\000\337[\211r\302\321N\255\314d\226\321\376\310/\360L\302\256\326\232\346\206\244\321Kc\037\330\326\030]\340\020A\024\036w\tU\361q\255\253*\255\311V94O+\372zQ\327\177\353\334.\204\316\361h~\266\202\036\001\300\237\361I?\003Tj\032\255\255$\\\36722\203Z\377Z\267\300\235\222\033\205\t\001a~\022\036\025\273\306{(Wi\313Z6+6\2462s\247\001\236y \353@Q\305\246\233@\235\371vTOo\025\301\224\273\214\207\300\\\256r&.t#2\241\337K\260\360;\340U\2676T\333\222?\363\372\223)\260\324\315\232Z\313=\247\304\370~j\224\374\033Yy\t\267\025\305\"e\235\177\326\303\223\201\231h,\346\025E\202\255\352)\207\345\255\316\347W\340\257\203\002\365x\r\251\030\236Z9\030-\205#G\002\003\001\000\001\243P0N0\035\006\003U\035\016\004\026\004\024\340\273\370\301W\373\343\273\371f\311\344;\244\334\004'\n\365H0\037\006\003U\035#\004\0300\026\200\024\340\273\370\301W\373\343\273\371f\311\344;\244\334\004'\n\365H0\f\006\003U\035\023\004\0050\003\001\001\3770\r\006\t*\206H\206\367\r\001\001\005\005\000\003\202\001\001\000\215\200\356\305\200\364/j\352\342\2346\035\360\307\325\bQ\351r\026e\340F\000\305\201\370\226\314\327^\23564\314\234 \365\305\323\2312\273g\326\340\255\025k\207\003o\355m\322[\036h\277\2237\345i\345[vN\f\222\031\027\276\035\317\277|\t\016Q\277\220\257\006\004\254\031\317\230\230\327@\220\345\370v\344j'\234\275\374\276\246\267\256\036 Up\035\"0\005\354 \275\300\376z\021\222a\244\333}\321\027\353\024\213th\214\276\233\330(\254Jb\345\356W\2675\302\022\277\362^t\362IJ\n\216Z\3268Y\217\275\f\203e\363\315\322DN\261*\024\305\227\270\217\n\222*\335\327{\006\213\323q\314\267\316\242\253S:\037z[(S4\351\020 \363\213\262E\334k\312\246\237\221\206\304,\313(;\354\341/1\333\024t%KPI\331\027y\315#}\362\231\246*b\243\b\232\334?\3217\237$\200g\315`q");
        Dc = abyte7;
        byte abyte8[][][] = new byte[8][][];
        abyte8[0] = CV;
        abyte8[1] = CW;
        abyte8[2] = CX;
        abyte8[3] = CY;
        abyte8[4] = CZ;
        abyte8[5] = Da;
        abyte8[6] = Db;
        abyte8[7] = Dc;
        Dd = a(abyte8);
        byte abyte9[][] = new byte[7][];
        abyte9[0] = CV[0];
        abyte9[1] = CW[0];
        abyte9[2] = CY[0];
        abyte9[3] = CZ[0];
        abyte9[4] = Da[0];
        abyte9[5] = Db[0];
        abyte9[6] = Dc[0];
        De = abyte9;
        Df = false;
        Dg = false;
        Dh = -1;
    }
}
