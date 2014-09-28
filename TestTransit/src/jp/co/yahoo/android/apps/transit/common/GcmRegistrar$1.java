// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import java.io.IOException;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            AsyncTask, GcmRegistrar

static final class > extends AsyncTask
{

    final Context val$context;
    final mRegistrarListener val$listener;

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((Void[])aobj);
    }

    protected transient String doInBackground(Void avoid[])
    {
        String s;
        try
        {
            s = GoogleCloudMessaging.getInstance(val$context).register(new String[] {
                "407063512799"
            });
            GcmRegistrar.storeRegistrationId(val$context, s);
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
        if (val$listener != null)
        {
            val$listener.onRegistered(s);
        }
    }

    mRegistrarListener(Context context1, mRegistrarListener mregistrarlistener)
    {
        val$context = context1;
        val$listener = mregistrarlistener;
        super();
    }
}
