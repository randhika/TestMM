// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common.util;

import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common.util:
//            TransitUtil

static final class val.aURL extends AsyncTask
{

    final String val$aURL;

    protected transient Boolean doInBackground(Void avoid[])
    {
        InputStream inputstream;
        HttpURLConnection httpurlconnection = (HttpURLConnection)(new URL(val$aURL)).openConnection();
        httpurlconnection.setInstanceFollowRedirects(false);
        httpurlconnection.setConnectTimeout(3000);
        httpurlconnection.setReadTimeout(3000);
        httpurlconnection.connect();
        inputstream = httpurlconnection.getInputStream();
        boolean flag = true;
        Exception exception;
        Exception exception1;
        IOException ioexception1;
        if (inputstream != null)
        {
            try
            {
                inputstream.close();
            }
            catch (IOException ioexception2) { }
        }
        return Boolean.valueOf(flag);
        exception1;
        flag = false;
        if (true)
        {
            continue; /* Loop/switch isn't completed */
        }
        null.close();
        flag = false;
        continue; /* Loop/switch isn't completed */
        ioexception1;
        flag = false;
        if (true) goto _L2; else goto _L1
_L1:
        break MISSING_BLOCK_LABEL_99;
_L2:
        break MISSING_BLOCK_LABEL_66;
        exception;
        if (false)
        {
            try
            {
                null.close();
            }
            catch (IOException ioexception) { }
        }
        throw exception;
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((Void[])aobj);
    }

    protected void onPostExecute(Boolean boolean1)
    {
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((Boolean)obj);
    }

    (String s)
    {
        val$aURL = s;
        super();
    }
}
