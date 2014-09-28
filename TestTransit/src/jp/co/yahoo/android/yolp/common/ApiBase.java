// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yolp.common;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package jp.co.yahoo.android.yolp.common:
//            HttpClient

public class ApiBase
{
    public static interface ApiListener
    {

        public abstract boolean endApi(ApiBase apibase, Object obj);
    }

    protected class SearchThread extends AsyncTask
    {

        final ApiBase this$0;

        protected volatile transient Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        protected transient Object doInBackground(String as[])
        {
            execute();
            return getResult();
        }

        protected void onCancelled()
        {
            if (m_progDialog != null && m_progDialog.isShowing())
            {
                m_progDialog.dismiss();
            }
        }

        protected void onPostExecute(Object obj)
        {
            if (m_progDialog != null && m_progDialog.isShowing())
            {
                m_progDialog.dismiss();
            }
            m_listener.endApi(ApiBase.this, obj);
        }

        protected void onPreExecute()
        {
            if (bWaitDialog)
            {
                m_progDialog = new ProgressDialog(context);
                m_progDialog.setMessage("\u901A\u4FE1\u4E2D");
                m_progDialog.setProgressStyle(0);
                if (!((Activity)context).isFinishing())
                {
                    m_progDialog.show();
                }
            }
        }

        protected SearchThread()
        {
            this$0 = ApiBase.this;
            super();
        }
    }


    private boolean bWaitDialog;
    protected Context context;
    protected int count;
    private ApiListener m_listener;
    private ProgressDialog m_progDialog;
    private SearchThread objAsync;
    protected Object objResult;
    protected HashMap param;
    private HashMap params;
    protected String sWatiMsg;
    private int total;
    protected android.net.Uri.Builder uriBuilder;
    protected String url;

    public ApiBase(Context context1)
    {
        sWatiMsg = "\u901A\u4FE1\u4E2D";
        bWaitDialog = false;
        objAsync = null;
        count = 0;
        total = 0;
        uriBuilder = new android.net.Uri.Builder();
        param = new HashMap();
        param.put("output", "json");
        context = context1;
    }

    public void apiClose()
    {
        if (m_progDialog != null && m_progDialog.isShowing())
        {
            m_progDialog.dismiss();
        }
    }

    public void cancel(boolean flag)
    {
        if (objAsync != null)
        {
            objAsync.cancel(flag);
            objAsync = null;
            m_listener = null;
        }
    }

    public void clearParam()
    {
        uriBuilder = new android.net.Uri.Builder();
        param = new HashMap();
        param.put("output", "json");
        if (url != null)
        {
            setUri(url);
        }
    }

    public void execute()
    {
        request();
    }

    public void executeAsync(ApiListener apilistener, boolean flag)
    {
        m_listener = apilistener;
        bWaitDialog = flag;
        objAsync = new SearchThread();
        objAsync.execute(new String[] {
            ""
        });
    }

    public int getCount()
    {
        return count;
    }

    public Object getResult()
    {
        return objResult;
    }

    public int getTotal()
    {
        return total;
    }

    public String request()
    {
        Iterator iterator = param.keySet().iterator();
_L5:
        if (iterator.hasNext()) goto _L2; else goto _L1
_L1:
        if (params == null) goto _L4; else goto _L3
_L3:
        Iterator iterator1 = params.keySet().iterator();
_L6:
        if (iterator1.hasNext())
        {
            break MISSING_BLOCK_LABEL_137;
        }
_L4:
        String s1 = Uri.decode(uriBuilder.build().toString());
        objResult = (new HttpClient()).doGetString(s1);
        Object obj = objResult;
        String s2 = null;
        if (obj != null)
        {
            s2 = objResult.toString();
        }
        return s2;
_L2:
        String s = (String)iterator.next();
        setParameter(s, (String)param.get(s));
          goto _L5
        String s3 = (String)iterator1.next();
        setParameter(s3, (String)params.get(s3));
          goto _L6
    }

    public void setCount(int i)
    {
        count = i;
    }

    public void setParam(String s, int i)
    {
        param.put(s, Integer.toString(i));
    }

    public void setParam(String s, String s1)
    {
        param.put(s, s1);
    }

    protected void setParameter(String s, String s1)
    {
        if (s1 != null && !s1.equals(""))
        {
            uriBuilder.appendQueryParameter(s, s1);
        }
    }

    public void setParams(HashMap hashmap)
    {
        params = hashmap;
    }

    public void setResult(Object obj)
    {
        objResult = obj;
    }

    public void setTotal(int i)
    {
        total = i;
    }

    public void setUri(String s)
    {
        try
        {
            url = s;
            URL url1 = new URL(s);
            uriBuilder.scheme(url1.getProtocol());
            uriBuilder.authority(url1.getHost());
            uriBuilder.path(url1.getPath());
            return;
        }
        catch (MalformedURLException malformedurlexception)
        {
            malformedurlexception.printStackTrace();
        }
    }

    public void setWaitMsg(String s)
    {
        sWatiMsg = s;
    }




}
