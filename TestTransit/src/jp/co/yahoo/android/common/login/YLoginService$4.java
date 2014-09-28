// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import android.content.Context;
import jp.co.yahoo.android.common.YHttpRequest;
import jp.co.yahoo.android.common.YLogger;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            YLoginService

class this._cls0 extends YHttpRequest
{

    final YLoginService this$0;

    public void onComplete()
    {
        String s = getResponseString();
        String s1 = "";
        int i = getStatusCode();
        if (i != 200)
        {
            try
            {
                s1 = s.split("\n")[0].substring("Error=".length());
                YLogger.log((new StringBuilder()).append("Status Code is ").append(String.valueOf(i)).append("(").append(s1).append(")").toString());
            }
            catch (StringIndexOutOfBoundsException stringindexoutofboundsexception)
            {
                YLogger.log((new StringBuilder()).append("Status Code is ").append(String.valueOf(i)).toString());
            }
            s = "";
        }
        if ("".equals(s))
        {
            YLoginService.access$1900(YLoginService.this, "", "fetch-token-failed", s1);
        } else
        {
            YLoginService.access$1900(YLoginService.this, "", "fetch-token-done", s);
        }
        clearResponse();
    }

    public void onTimeout()
    {
        YLoginService.access$1900(YLoginService.this, "", "fetch-token-failed", "");
        clearResponse();
    }

    (Context context, String s)
    {
        this$0 = YLoginService.this;
        super(context, s);
    }
}
