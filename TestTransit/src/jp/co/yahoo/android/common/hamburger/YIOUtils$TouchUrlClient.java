// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import android.net.Uri;
import android.os.AsyncTask;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

// Referenced classes of package jp.co.yahoo.android.common.hamburger:
//            YHttpGet, YHBGRd, YHBGUtils, YIOUtils

static class  extends AsyncTask
{

    protected volatile transient Object doInBackground(Object aobj[])
    {
        return doInBackground((String[])aobj);
    }

    protected transient Void doInBackground(String as[])
    {
        int i = 0;
        int j = as.length;
        do
        {
            if (i >= j)
            {
                return null;
            }
            String s = as[i];
            if (s != null)
            {
                try
                {
                    Uri uri = Uri.parse(s);
                    YHttpGet yhttpget = new YHttpGet();
                    if (YHBGRd.sBCookie != null)
                    {
                        BasicClientCookie basicclientcookie = new BasicClientCookie("B", YHBGRd.sBCookie);
                        basicclientcookie.setDomain(".yahoo.co.jp");
                        basicclientcookie.setPath("/");
                        yhttpget.getCookieStore().addCookie(basicclientcookie);
                    }
                    yhttpget.enableRedirect(false);
                    yhttpget.get(uri);
                    YHBGUtils.debug((new StringBuilder("rd => ")).append(uri).toString());
                }
                catch (Exception exception) { }
            }
            i++;
        } while (true);
    }

    ()
    {
    }
}
