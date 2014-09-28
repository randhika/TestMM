// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.yolp.common;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

// Referenced classes of package jp.co.yahoo.android.yolp.common:
//            ApiBase

protected class this._cls0 extends AsyncTask
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
        if (ApiBase.access$2(ApiBase.this) != null && ApiBase.access$2(ApiBase.this).isShowing())
        {
            ApiBase.access$2(ApiBase.this).dismiss();
        }
    }

    protected void onPostExecute(Object obj)
    {
        if (ApiBase.access$2(ApiBase.this) != null && ApiBase.access$2(ApiBase.this).isShowing())
        {
            ApiBase.access$2(ApiBase.this).dismiss();
        }
        ApiBase.access$3(ApiBase.this).ndApi(ApiBase.this, obj);
    }

    protected void onPreExecute()
    {
        if (ApiBase.access$0(ApiBase.this))
        {
            ApiBase.access$1(ApiBase.this, new ProgressDialog(context));
            ApiBase.access$2(ApiBase.this).setMessage("\u901A\u4FE1\u4E2D");
            ApiBase.access$2(ApiBase.this).setProgressStyle(0);
            if (!((Activity)context).isFinishing())
            {
                ApiBase.access$2(ApiBase.this).show();
            }
        }
    }

    protected ()
    {
        this$0 = ApiBase.this;
        super();
    }
}
