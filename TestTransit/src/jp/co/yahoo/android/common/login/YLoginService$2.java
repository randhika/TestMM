// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.util.Date;
import jp.co.yahoo.android.common.YLogger;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            YLoginService

class this._cls0 extends Handler
{

    final YLoginService this$0;

    public void handleMessage(Message message)
    {
        String s = message.getData().getString("Topic");
        if (!"fetch-challenge-done".equals(s)) goto _L2; else goto _L1
_L1:
        if (YLoginService.access$1600(YLoginService.this)) goto _L4; else goto _L3
_L3:
        return;
_L4:
        String s8 = message.getData().getString("Response");
        YLoginService.access$2600(YLoginService.this, YLoginService.access$1400(YLoginService.this), YLoginService.access$1500(YLoginService.this), s8);
        YLoginService.access$1402(YLoginService.this, "");
        return;
_L2:
        String s7;
        if (!"fetch-token-done".equals(s))
        {
            break MISSING_BLOCK_LABEL_482;
        }
        if (!YLoginService.access$1600(YLoginService.this))
        {
            continue; /* Loop/switch isn't completed */
        }
        s7 = message.getData().getString("Response");
        int k = s7.indexOf("AuthToken=");
        if (k == -1) goto _L6; else goto _L5
_L5:
        int l = s7.indexOf("\n", k);
        YLoginService.access$602(YLoginService.this, s7.substring(k + "AuthToken=".length(), l).trim());
_L9:
        int i1 = s7.indexOf("Login=");
        if (i1 == -1) goto _L8; else goto _L7
_L7:
        int j1 = s7.indexOf("\n", i1);
        YLoginService.access$202(YLoginService.this, s7.substring(i1 + "Login=".length(), j1).trim());
_L10:
        int k1 = s7.indexOf("Guid=");
        if (k1 == -1)
        {
            break MISSING_BLOCK_LABEL_374;
        }
        try
        {
            int l1 = s7.indexOf("\n", k1);
            YLoginService.access$302(YLoginService.this, s7.substring(k1 + "Guid=".length(), l1).trim());
        }
        catch (StringIndexOutOfBoundsException stringindexoutofboundsexception)
        {
            YLogger.log(stringindexoutofboundsexception.toString());
            YLoginService.access$602(YLoginService.this, "");
            YLoginService.access$202(YLoginService.this, "");
            YLoginService.access$302(YLoginService.this, "");
        }
_L11:
        if ("".equals(YLoginService.access$600(YLoginService.this)) || "".equals(YLoginService.access$200(YLoginService.this)))
        {
            YLoginService.access$2700(YLoginService.this, "", "on-login-failed", "");
            return;
        }
        break MISSING_BLOCK_LABEL_387;
_L6:
        YLoginService.access$602(YLoginService.this, "");
          goto _L9
_L8:
        YLoginService.access$202(YLoginService.this, "");
          goto _L10
        YLoginService.access$302(YLoginService.this, "");
          goto _L11
        if (YLoginService.access$800(YLoginService.this, YLoginService.access$700(YLoginService.this), true))
        {
            YLoginService.access$1000(YLoginService.this, YLoginService.access$000(YLoginService.this), YLoginService.access$200(YLoginService.this));
            YLoginService.access$1000(YLoginService.this, YLoginService.access$1100(YLoginService.this), YLoginService.access$300(YLoginService.this));
            YLoginService.access$1300(YLoginService.this, YLoginService.access$1200(YLoginService.this), YLoginService.access$600(YLoginService.this));
        }
        YLoginService.access$2700(YLoginService.this, "", "on-login-changed", "");
        return;
        if (!"fetch-token-failed".equals(s))
        {
            break; /* Loop/switch isn't completed */
        }
        if (YLoginService.access$1600(YLoginService.this))
        {
            YLoginService.access$2700(YLoginService.this, "", "on-login-failed", message.getData().getString("Response"));
            return;
        }
        if (true) goto _L3; else goto _L12
_L12:
        String s1;
        String s2;
        String s3;
        String s4;
        if ("fetch-token-canceled".equals(s))
        {
            YLoginService.access$2700(YLoginService.this, "", "on-login-canceled", "");
            return;
        }
        if ("logout-done".equals(s))
        {
            YLoginService.access$2700(YLoginService.this, "", "on-login-changed", "");
            return;
        }
        if (!"fetch-credential-done".equals(s))
        {
            break MISSING_BLOCK_LABEL_843;
        }
        s1 = message.getData().getString("Response");
        s2 = "";
        s3 = "";
        s4 = "";
        String as[];
        int i;
        as = s1.split("\n");
        i = as.length;
        Exception exception;
        String s5;
        String s6;
        for (int j = 0; j >= i; j++)
        {
            break MISSING_BLOCK_LABEL_723;
        }

        s5 = as[j];
        if (s5.startsWith("Cookie=", 0))
        {
            s2 = s5.substring("Cookie=".length());
            break MISSING_BLOCK_LABEL_1087;
        }
        if (s5.startsWith("WSSID=", 0))
        {
            s3 = s5.substring("WSSID=".length());
            break MISSING_BLOCK_LABEL_1087;
        }
        if (!s5.startsWith("Expiration=", 0))
        {
            break MISSING_BLOCK_LABEL_1087;
        }
        s6 = s5.substring("Expiration=".length());
        s4 = s6;
        break MISSING_BLOCK_LABEL_1087;
        exception;
        YLogger.log(exception.toString());
        if ("".equals(s2) || "".equals(s3) || "".equals(s4))
        {
            YLoginService.access$402(YLoginService.this, "");
            YLoginService.access$2802(YLoginService.this, 0L);
            YLoginService.access$2700(YLoginService.this, "", "on-update-credential-failed", s1);
            return;
        } else
        {
            YLoginService.access$402(YLoginService.this, s1);
            YLoginService.access$2802(YLoginService.this, ((new Date()).getTime() + (long)(1000 * Integer.parseInt(s4))) - 60000L);
            YLoginService.access$2700(YLoginService.this, "", "on-update-credential", "");
            return;
        }
        if ("fetch-credential-failed".equals(s))
        {
            YLoginService.access$402(YLoginService.this, "");
            YLoginService.access$2700(YLoginService.this, "", "on-update-credential-failed", message.getData().getString("Response"));
            return;
        }
        if ("fetch-credential-invalidtoken".equals(s))
        {
            YLoginService.access$402(YLoginService.this, "");
            YLoginService.access$2700(YLoginService.this, message.getData().getString("Subject"), "on-update-credential-invalidtoken", message.getData().getString("Response"));
            return;
        }
        if ("fetch-credential-canceled".equals(s))
        {
            YLoginService.access$2700(YLoginService.this, "", "on-credential-canceled", "");
            return;
        }
        if ("fetch-api-done".equals(s))
        {
            YLoginService.access$2700(YLoginService.this, message.getData().getString("Subject"), "on-yjdn-downloaded", message.getData().getString("Response"));
            return;
        }
        if ("fetch-api-failed".equals(s))
        {
            YLoginService.access$2700(YLoginService.this, message.getData().getString("Subject"), "on-yjdn-download-failed", message.getData().getString("Response"));
            return;
        }
        if ("fetch-api-timeout".equals(s))
        {
            YLoginService.access$2700(YLoginService.this, message.getData().getString("Subject"), "on-yjdn-timeout", "");
            return;
        }
        if ("fetch-api-canceled".equals(s))
        {
            YLoginService.access$2700(YLoginService.this, message.getData().getString("Subject"), "on-yjdn-canceled", "");
            return;
        }
        if (true) goto _L3; else goto _L13
_L13:
    }

    ()
    {
        this$0 = YLoginService.this;
        super();
    }
}
