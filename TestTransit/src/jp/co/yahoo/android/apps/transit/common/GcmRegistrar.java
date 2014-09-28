// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import java.io.IOException;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            GcmDialogListener, AsyncTask

public class GcmRegistrar
{
    public static interface GcmRegistrarListener
    {

        public abstract void onRegistered(String s);
    }


    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_REG_ID_OLD = "registration_id_old";
    private static final String SENDER_ID = "407063512799";

    public GcmRegistrar()
    {
    }

    public static boolean checkPlayServices(Activity activity)
    {
        int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if (i != 0)
        {
            getErrorDialog(i, activity, 9000).show();
            return false;
        } else
        {
            return true;
        }
    }

    public static boolean checkUpdateAppVersion(Context context)
    {
        return getGcmPreferences(context).getInt("appVersion", 0x80000000) != TransitUtil.getVersionCode(context);
    }

    public static void clearRegistrationIdOld(Context context)
    {
        android.content.SharedPreferences.Editor editor = getGcmPreferences(context).edit();
        editor.remove("registration_id_old");
        editor.commit();
    }

    private static Dialog getErrorDialog(int i, Activity activity, int j)
    {
        TransitDialogBuilder transitdialogbuilder = new TransitDialogBuilder(activity);
        transitdialogbuilder.setMessage(GooglePlayServicesUtil.d(activity, i));
        GcmDialogListener gcmdialoglistener = new GcmDialogListener(activity, (Intent)GooglePlayServicesUtil.Z(i), j);
        String s = GooglePlayServicesUtil.e(activity, i);
        if (s != null)
        {
            transitdialogbuilder.setPositiveButton(s, (android.content.DialogInterface.OnClickListener)gcmdialoglistener);
        }
        switch (i)
        {
        default:
            Log.e("GooglePlayServicesUtil", (new StringBuilder()).append("Unexpected error code ").append(i).toString());
            return transitdialogbuilder.create();

        case 0: // '\0'
            return null;

        case 4: // '\004'
        case 6: // '\006'
            return transitdialogbuilder.create();

        case 1: // '\001'
            return transitdialogbuilder.setTitle(activity.getString(0x7f0d0090)).create();

        case 3: // '\003'
            return transitdialogbuilder.setTitle(activity.getString(0x7f0d008b)).create();

        case 2: // '\002'
            return transitdialogbuilder.setTitle(activity.getString(0x7f0d009f)).create();

        case 9: // '\t'
            Log.e("GooglePlayServicesUtil", "Google Play services is invalid. Cannot recover.");
            return transitdialogbuilder.setTitle(activity.getString(0x7f0d009c)).create();

        case 7: // '\007'
            Log.e("GooglePlayServicesUtil", "Network error occurred. Please retry request later.");
            return transitdialogbuilder.setTitle(activity.getString(0x7f0d0095)).create();

        case 8: // '\b'
            Log.e("GooglePlayServicesUtil", "Internal error occurred. Please see logs for detailed information");
            return transitdialogbuilder.create();

        case 10: // '\n'
            Log.e("GooglePlayServicesUtil", "Developer error occurred. Please see logs for detailed information");
            return transitdialogbuilder.create();

        case 5: // '\005'
            Log.e("GooglePlayServicesUtil", "An invalid account was specified when connecting. Please provide a valid account.");
            return transitdialogbuilder.setTitle(activity.getString(0x7f0d0092)).create();

        case 11: // '\013'
            Log.e("GooglePlayServicesUtil", "The application is not licensed to the user.");
            return transitdialogbuilder.create();

        case 12: // '\f'
            Log.e("GooglePlayServicesUtil", "The date of the device is not valid.");
            break;
        }
        return transitdialogbuilder.setTitle(activity.getString(0x7f0d009c)).create();
    }

    private static SharedPreferences getGcmPreferences(Context context)
    {
        return context.getSharedPreferences("gcm_registrar", 0);
    }

    public static String getRegistrationId(Context context)
    {
        String s = getGcmPreferences(context).getString("registration_id", "");
        if (TextUtils.isEmpty(s))
        {
            s = "";
        }
        return s;
    }

    public static String getRegistrationIdOld(Context context)
    {
        String s = getGcmPreferences(context).getString("registration_id_old", "");
        if (TextUtils.isEmpty(s))
        {
            s = "";
        }
        return s;
    }

    public static void registerInBackground(Context context, GcmRegistrarListener gcmregistrarlistener)
    {
        (new AsyncTask(context, gcmregistrarlistener) {

            final Context val$context;
            final GcmRegistrarListener val$listener;

            protected volatile Object doInBackground(Object aobj[])
            {
                return doInBackground((Void[])aobj);
            }

            protected transient String doInBackground(Void avoid[])
            {
                String s;
                try
                {
                    s = GoogleCloudMessaging.getInstance(context).register(new String[] {
                        "407063512799"
                    });
                    GcmRegistrar.storeRegistrationId(context, s);
                }
                catch (IOException ioexception)
                {
                    return null;
                }
                return s;
            }

            protected volatile void onPostExecute(Object obj)
            {
                onPostExecute((String)obj);
            }

            protected void onPostExecute(String s)
            {
                if (listener != null)
                {
                    listener.onRegistered(s);
                }
            }

            
            {
                context = context1;
                listener = gcmregistrarlistener;
                super();
            }
        }).execute(new Void[] {
            null, null, null
        });
    }

    public static void storeRegistrationId(Context context, String s)
    {
        SharedPreferences sharedpreferences = getGcmPreferences(context);
        int i = TransitUtil.getVersionCode(context);
        android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("registration_id", s);
        editor.putInt("appVersion", i);
        editor.commit();
    }

    public static void storeRegistrationIdOld(Context context, String s)
    {
        android.content.SharedPreferences.Editor editor = getGcmPreferences(context).edit();
        editor.putString("registration_id_old", s);
        editor.commit();
    }
}
