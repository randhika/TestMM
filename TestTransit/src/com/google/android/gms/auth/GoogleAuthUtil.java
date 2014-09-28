// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.auth;

import android.accounts.AccountManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.a;
import com.google.android.gms.internal.gd;
import com.google.android.gms.internal.hm;
import com.google.android.gms.internal.r;
import java.io.IOException;
import java.net.URISyntaxException;

// Referenced classes of package com.google.android.gms.auth:
//            UserRecoverableNotifiedException, GoogleAuthException, GooglePlayServicesAvailabilityException, UserRecoverableAuthException

public final class GoogleAuthUtil
{
    private static class a extends Handler
    {

        private final Context lz;

        public void handleMessage(Message message)
        {
            if (message.what == 1)
            {
                int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(lz);
                if (GooglePlayServicesUtil.isUserRecoverableError(i))
                {
                    GooglePlayServicesUtil.showErrorNotification(i, lz);
                }
                return;
            } else
            {
                Log.wtf("GoogleAuthUtil", (new StringBuilder()).append("Don't know how to handle this message: ").append(message.what).toString());
                return;
            }
        }

        a(Context context)
        {
            Looper looper;
            if (Looper.myLooper() == null)
            {
                looper = Looper.getMainLooper();
            } else
            {
                looper = Looper.myLooper();
            }
            super(looper);
            lz = context;
        }
    }


    public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
    public static final String KEY_ANDROID_PACKAGE_NAME;
    public static final String KEY_CALLER_UID;
    public static final String KEY_REQUEST_ACTIONS = "request_visible_actions";
    public static final String KEY_REQUEST_VISIBLE_ACTIVITIES = "request_visible_actions";
    public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
    private static final ComponentName yU;
    private static final ComponentName yV;
    private static final Intent yW;
    private static final Intent yX;

    private GoogleAuthUtil()
    {
    }

    private static String a(Context context, String s, String s1, Bundle bundle)
        throws IOException, UserRecoverableNotifiedException, GoogleAuthException
    {
        if (bundle == null)
        {
            bundle = new Bundle();
        }
        String s2;
        try
        {
            s2 = getToken(context, s, s1, bundle);
        }
        catch (GooglePlayServicesAvailabilityException googleplayservicesavailabilityexception)
        {
            int i = googleplayservicesavailabilityexception.getConnectionStatusCode();
            if (b(context, i))
            {
                a a1 = new a(context.getApplicationContext());
                a1.sendMessageDelayed(a1.obtainMessage(1), 30000L);
            } else
            {
                GooglePlayServicesUtil.showErrorNotification(i, context);
            }
            throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
        }
        catch (UserRecoverableAuthException userrecoverableauthexception)
        {
            throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
        }
        return s2;
    }

    private static boolean ac(String s)
    {
        return "NetworkError".equals(s) || "ServiceUnavailable".equals(s) || "Timeout".equals(s);
    }

    private static boolean ad(String s)
    {
        return "BadAuthentication".equals(s) || "CaptchaRequired".equals(s) || "DeviceManagementRequiredOrSyncDisabled".equals(s) || "NeedPermission".equals(s) || "NeedsBrowser".equals(s) || "UserCancel".equals(s) || "AppDownloadRequired".equals(s);
    }

    private static boolean b(Context context, int i)
    {
        PackageManager packagemanager;
        if (i != 1)
        {
            break MISSING_BLOCK_LABEL_32;
        }
        packagemanager = context.getPackageManager();
        boolean flag = packagemanager.getApplicationInfo("com.google.android.gms", 8192).enabled;
        if (flag)
        {
            return true;
        }
        break MISSING_BLOCK_LABEL_32;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        return false;
    }

    public static void clearToken(Context context, String s)
        throws GooglePlayServicesAvailabilityException, GoogleAuthException, IOException
    {
        Context context1;
        Bundle bundle;
        com.google.android.gms.common.a a1;
        context1 = context.getApplicationContext();
        hm.az("Calling this from your main thread can lead to deadlock");
        z(context1);
        bundle = new Bundle();
        String s1 = context.getApplicationInfo().packageName;
        bundle.putString("clientPackageName", s1);
        if (!bundle.containsKey(KEY_ANDROID_PACKAGE_NAME))
        {
            bundle.putString(KEY_ANDROID_PACKAGE_NAME, s1);
        }
        a1 = new com.google.android.gms.common.a();
        if (!context1.bindService(yW, a1, 1))
        {
            break MISSING_BLOCK_LABEL_181;
        }
        Bundle bundle1 = com.google.android.gms.internal.r.a.a(a1.ew()).a(s, bundle);
        String s2 = bundle1.getString(gd.zL);
        if (!bundle1.getBoolean("booleanResult"))
        {
            throw new GoogleAuthException(s2);
        }
        break MISSING_BLOCK_LABEL_161;
        RemoteException remoteexception;
        remoteexception;
        Log.i("GoogleAuthUtil", "GMS remote exception ", remoteexception);
        throw new IOException("remote exception");
        Exception exception;
        exception;
        context1.unbindService(a1);
        throw exception;
        context1.unbindService(a1);
        return;
        InterruptedException interruptedexception;
        interruptedexception;
        throw new GoogleAuthException("Interrupted");
        throw new IOException("Could not bind to service with the given context.");
    }

    private static void g(Intent intent)
    {
        if (intent == null)
        {
            throw new IllegalArgumentException("Callback cannot be null.");
        }
        String s = intent.toUri(1);
        try
        {
            Intent.parseUri(s, 1);
            return;
        }
        catch (URISyntaxException urisyntaxexception)
        {
            throw new IllegalArgumentException("Parameter callback contains invalid data. It must be serializable using toUri() and parseUri().");
        }
    }

    public static String getToken(Context context, String s, String s1)
        throws IOException, UserRecoverableAuthException, GoogleAuthException
    {
        return getToken(context, s, s1, new Bundle());
    }

    public static String getToken(Context context, String s, String s1, Bundle bundle)
        throws IOException, UserRecoverableAuthException, GoogleAuthException
    {
        Context context1;
        com.google.android.gms.common.a a1;
        Bundle bundle2;
        context1 = context.getApplicationContext();
        hm.az("Calling this from your main thread can lead to deadlock");
        z(context1);
        Bundle bundle1;
        String s2;
        String s3;
        boolean flag;
        if (bundle == null)
        {
            bundle1 = new Bundle();
        } else
        {
            bundle1 = new Bundle(bundle);
        }
        s2 = context.getApplicationInfo().packageName;
        bundle1.putString("clientPackageName", s2);
        if (!bundle1.containsKey(KEY_ANDROID_PACKAGE_NAME))
        {
            bundle1.putString(KEY_ANDROID_PACKAGE_NAME, s2);
        }
        a1 = new com.google.android.gms.common.a();
        if (!context1.bindService(yW, a1, 1))
        {
            break MISSING_BLOCK_LABEL_274;
        }
        bundle2 = com.google.android.gms.internal.r.a.a(a1.ew()).a(s, s1, bundle1);
        s3 = bundle2.getString("authtoken");
        flag = TextUtils.isEmpty(s3);
        if (!flag)
        {
            context1.unbindService(a1);
            return s3;
        }
        String s4;
        s4 = bundle2.getString("Error");
        Intent intent = (Intent)bundle2.getParcelable("userRecoveryIntent");
        if (ad(s4))
        {
            throw new UserRecoverableAuthException(s4, intent);
        }
        break MISSING_BLOCK_LABEL_233;
        RemoteException remoteexception;
        remoteexception;
        Log.i("GoogleAuthUtil", "GMS remote exception ", remoteexception);
        throw new IOException("remote exception");
        Exception exception;
        exception;
        context1.unbindService(a1);
        throw exception;
        if (ac(s4))
        {
            throw new IOException(s4);
        }
        break MISSING_BLOCK_LABEL_264;
        InterruptedException interruptedexception;
        interruptedexception;
        throw new GoogleAuthException("Interrupted");
        throw new GoogleAuthException(s4);
        throw new IOException("Could not bind to service with the given context.");
    }

    public static String getTokenWithNotification(Context context, String s, String s1, Bundle bundle)
        throws IOException, UserRecoverableNotifiedException, GoogleAuthException
    {
        if (bundle == null)
        {
            bundle = new Bundle();
        }
        bundle.putBoolean("handle_notification", true);
        return a(context, s, s1, bundle);
    }

    public static String getTokenWithNotification(Context context, String s, String s1, Bundle bundle, Intent intent)
        throws IOException, UserRecoverableNotifiedException, GoogleAuthException
    {
        g(intent);
        if (bundle == null)
        {
            bundle = new Bundle();
        }
        bundle.putParcelable("callback_intent", intent);
        bundle.putBoolean("handle_notification", true);
        return a(context, s, s1, bundle);
    }

    public static String getTokenWithNotification(Context context, String s, String s1, Bundle bundle, String s2, Bundle bundle1)
        throws IOException, UserRecoverableNotifiedException, GoogleAuthException
    {
        if (TextUtils.isEmpty(s2))
        {
            throw new IllegalArgumentException("Authority cannot be empty or null.");
        }
        if (bundle == null)
        {
            bundle = new Bundle();
        }
        if (bundle1 == null)
        {
            bundle1 = new Bundle();
        }
        ContentResolver.validateSyncExtrasBundle(bundle1);
        bundle.putString("authority", s2);
        bundle.putBundle("sync_extras", bundle1);
        bundle.putBoolean("handle_notification", true);
        return a(context, s, s1, bundle);
    }

    public static void invalidateToken(Context context, String s)
    {
        AccountManager.get(context).invalidateAuthToken("com.google", s);
    }

    private static void z(Context context)
        throws GoogleAuthException
    {
        try
        {
            GooglePlayServicesUtil.z(context);
            return;
        }
        catch (GooglePlayServicesRepairableException googleplayservicesrepairableexception)
        {
            throw new GooglePlayServicesAvailabilityException(googleplayservicesrepairableexception.getConnectionStatusCode(), googleplayservicesrepairableexception.getMessage(), googleplayservicesrepairableexception.getIntent());
        }
        catch (GooglePlayServicesNotAvailableException googleplayservicesnotavailableexception)
        {
            throw new GoogleAuthException(googleplayservicesnotavailableexception.getMessage());
        }
    }

    static 
    {
        String s;
        String s1;
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            s = "callerUid";
        } else
        {
            s = "callerUid";
        }
        KEY_CALLER_UID = s;
        if (android.os.Build.VERSION.SDK_INT >= 14)
        {
            s1 = "androidPackageName";
        } else
        {
            s1 = "androidPackageName";
        }
        KEY_ANDROID_PACKAGE_NAME = s1;
        yU = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
        yV = new ComponentName("com.google.android.gms", "com.google.android.gms.recovery.RecoveryService");
        yW = (new Intent()).setPackage("com.google.android.gms").setComponent(yU);
        yX = (new Intent()).setPackage("com.google.android.gms").setComponent(yV);
    }
}
