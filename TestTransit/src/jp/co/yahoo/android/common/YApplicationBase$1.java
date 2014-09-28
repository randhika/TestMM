// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

// Referenced classes of package jp.co.yahoo.android.common:
//            YApplicationBase

class val.ua extends AsyncTask
{

    final YApplicationBase this$0;
    final String val$aURL;
    final String val$ua;

    protected transient Boolean doInBackground(Void avoid[])
    {
        InputStream inputstream;
        HttpURLConnection httpurlconnection = (HttpURLConnection)(new URL(val$aURL)).openConnection();
        httpurlconnection.setInstanceFollowRedirects(false);
        httpurlconnection.setConnectTimeout(10000);
        httpurlconnection.setReadTimeout(10000);
        httpurlconnection.setRequestProperty("User-Agent", val$ua);
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
        break MISSING_BLOCK_LABEL_110;
_L2:
        break MISSING_BLOCK_LABEL_77;
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
        onTouchSRDCompleted(boolean1.booleanValue());
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((Boolean)obj);
    }

    ()
    {
        this$0 = final_yapplicationbase;
        val$aURL = s;
        val$ua = String.this;
        super();
    }
}
