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
//            YCredentialDownloader, YCredentialDownloadListener

class this._cls0 extends Handler
{

    final YCredentialDownloader this$0;

    public void handleMessage(Message message)
    {
        String s = message.getData().getString("Topic");
        if (!s.equals("fetch-challenge-done")) goto _L2; else goto _L1
_L1:
        String s7 = message.getData().getString("Response");
        YCredentialDownloader.access$300(YCredentialDownloader.this, YCredentialDownloader.access$100(YCredentialDownloader.this), YCredentialDownloader.access$200(YCredentialDownloader.this), s7);
        YCredentialDownloader.access$102(YCredentialDownloader.this, "");
        YCredentialDownloader.access$202(YCredentialDownloader.this, "");
_L16:
        return;
_L2:
        String s6;
        if (s.equals("fetch-challenge-failed"))
        {
            if (YCredentialDownloader.access$400(YCredentialDownloader.this) != null)
            {
                YCredentialDownloader.access$400(YCredentialDownloader.this).onCredentialFailed("", 1, false);
            }
            YCredentialDownloader.access$502(YCredentialDownloader.this, 0);
            return;
        }
        if (s.equals("fetch-challenge-canceled"))
        {
            if (YCredentialDownloader.access$400(YCredentialDownloader.this) != null)
            {
                YCredentialDownloader.access$400(YCredentialDownloader.this).onCredentialCanceled();
            }
            YCredentialDownloader.access$502(YCredentialDownloader.this, 0);
            return;
        }
        if (!s.equals("fetch-token-done"))
        {
            break MISSING_BLOCK_LABEL_562;
        }
        s6 = message.getData().getString("Response");
        int j = s6.indexOf("AuthToken=");
        if (j == -1) goto _L4; else goto _L3
_L3:
        int k = s6.indexOf("\n", j);
        YCredentialDownloader.access$602(YCredentialDownloader.this, s6.substring(j + "AuthToken=".length(), k).trim());
_L7:
        int i1 = s6.indexOf("Login=");
        if (i1 == -1) goto _L6; else goto _L5
_L5:
        int j1 = s6.indexOf("\n", i1);
        YCredentialDownloader.access$702(YCredentialDownloader.this, s6.substring(i1 + "Login=".length(), j1).trim());
_L8:
        int k1 = s6.indexOf("Guid=");
        if (k1 == -1)
        {
            break MISSING_BLOCK_LABEL_471;
        }
        try
        {
            int l1 = s6.indexOf("\n", k1);
            YCredentialDownloader.access$802(YCredentialDownloader.this, s6.substring(k1 + "Guid=".length(), l1).trim());
        }
        catch (StringIndexOutOfBoundsException stringindexoutofboundsexception1)
        {
            YLogger.log(stringindexoutofboundsexception1.toString());
            YCredentialDownloader.access$602(YCredentialDownloader.this, "");
            YCredentialDownloader.access$702(YCredentialDownloader.this, "");
            YCredentialDownloader.access$802(YCredentialDownloader.this, "");
        }
_L9:
        if ("".equals(YCredentialDownloader.access$600(YCredentialDownloader.this)) || "".equals(YCredentialDownloader.access$700(YCredentialDownloader.this)))
        {
            if (YCredentialDownloader.access$400(YCredentialDownloader.this) != null)
            {
                YCredentialDownloader.access$400(YCredentialDownloader.this).onCredentialFailed("", 2, false);
            }
            YCredentialDownloader.access$502(YCredentialDownloader.this, 0);
            return;
        } else
        {
            YCredentialDownloader.access$1000(YCredentialDownloader.this, YCredentialDownloader.access$900(YCredentialDownloader.this), YCredentialDownloader.access$700(YCredentialDownloader.this));
            YCredentialDownloader.access$1000(YCredentialDownloader.this, YCredentialDownloader.access$1100(YCredentialDownloader.this), YCredentialDownloader.access$800(YCredentialDownloader.this));
            YCredentialDownloader.access$1300(YCredentialDownloader.this, YCredentialDownloader.access$1200(YCredentialDownloader.this), YCredentialDownloader.access$600(YCredentialDownloader.this));
            YCredentialDownloader.access$1400(YCredentialDownloader.this, YCredentialDownloader.access$600(YCredentialDownloader.this));
            return;
        }
_L4:
        YCredentialDownloader.access$602(YCredentialDownloader.this, "");
          goto _L7
_L6:
        YCredentialDownloader.access$702(YCredentialDownloader.this, "");
          goto _L8
        YCredentialDownloader.access$802(YCredentialDownloader.this, "");
          goto _L9
        if (s.equals("fetch-token-failed"))
        {
            if (YCredentialDownloader.access$400(YCredentialDownloader.this) != null)
            {
                YCredentialDownloader.access$400(YCredentialDownloader.this).onCredentialFailed(message.getData().getString("Response"), 2, false);
            }
            YCredentialDownloader.access$502(YCredentialDownloader.this, 0);
            return;
        }
        if (s.equals("fetch-token-canceled"))
        {
            if (YCredentialDownloader.access$400(YCredentialDownloader.this) != null)
            {
                YCredentialDownloader.access$400(YCredentialDownloader.this).onCredentialCanceled();
            }
            YCredentialDownloader.access$502(YCredentialDownloader.this, 0);
            return;
        }
        if (!s.equals("fetch-credential-done")) goto _L11; else goto _L10
_L10:
        String s1;
        String s2;
        String s3;
        long l;
        s1 = message.getData().getString("Response");
        s2 = "";
        s3 = "";
        l = 0L;
        String as[] = s1.split("\n");
        int i = 0;
_L17:
        if (i >= as.length) goto _L13; else goto _L12
_L12:
        String s4;
        s4 = as[i];
        if (s4.startsWith("WSSID=", 0))
        {
            s2 = s4.substring("WSSID=".length());
            break MISSING_BLOCK_LABEL_1040;
        }
        if (s4.startsWith("Cookie=", 0))
        {
            s3 = s4.substring("Cookie=".length());
            break MISSING_BLOCK_LABEL_1040;
        }
        try
        {
            if (s4.startsWith("Expiration=", 0))
            {
                String s5 = s4.substring("Expiration=".length());
                l = ((new Date()).getTime() + (long)(1000 * Integer.parseInt(s5))) - 60000L;
            }
            break MISSING_BLOCK_LABEL_1040;
        }
        catch (StringIndexOutOfBoundsException stringindexoutofboundsexception)
        {
            YLogger.log(stringindexoutofboundsexception.toString());
            if (YCredentialDownloader.access$400(YCredentialDownloader.this) != null)
            {
                YCredentialDownloader.access$400(YCredentialDownloader.this).onCredentialFailed("", 3, false);
            }
        }
          goto _L14
_L13:
        if (YCredentialDownloader.access$400(YCredentialDownloader.this) != null)
        {
            YCredentialDownloader.access$400(YCredentialDownloader.this).onCredentialDownloaded(s2, s3, l);
        }
_L15:
        YCredentialDownloader.access$502(YCredentialDownloader.this, 0);
        return;
_L14:
        if (true) goto _L15; else goto _L11
_L11:
        if (s.equals("fetch-credential-failed"))
        {
            if (YCredentialDownloader.access$400(YCredentialDownloader.this) != null)
            {
                YCredentialDownloader.access$400(YCredentialDownloader.this).onCredentialFailed(message.getData().getString("Response"), 3, false);
            }
            YCredentialDownloader.access$502(YCredentialDownloader.this, 0);
            return;
        }
        if (s.equals("fetch-credential-invalidtoken"))
        {
            if (YCredentialDownloader.access$400(YCredentialDownloader.this) != null)
            {
                YCredentialDownloader.access$400(YCredentialDownloader.this).onCredentialFailed(message.getData().getString("Response"), 3, true);
            }
            YCredentialDownloader.access$502(YCredentialDownloader.this, 0);
            return;
        }
        if (s.equals("fetch-credential-canceled"))
        {
            if (YCredentialDownloader.access$400(YCredentialDownloader.this) != null)
            {
                YCredentialDownloader.access$400(YCredentialDownloader.this).onCredentialCanceled();
            }
            YCredentialDownloader.access$502(YCredentialDownloader.this, 0);
            return;
        }
          goto _L16
        i++;
          goto _L17
    }

    ner()
    {
        this$0 = YCredentialDownloader.this;
        super();
    }
}
