// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit;

import android.content.DialogInterface;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;
import jp.co.yahoo.android.common.login.YJDNErrorData;
import jp.co.yahoo.android.common.login.YLoginServiceListener;
import jp.co.yahoo.android.common.login.YLoginServiceManager;
import org.apache.http.Header;

// Referenced classes of package jp.co.yahoo.android.apps.transit:
//            Transit

class this._cls0
    implements YLoginServiceListener
{

    final Transit this$0;

    public void onLogin()
    {
    }

    public void onLoginCanceled()
    {
    }

    public void onLoginFailed(String s)
    {
    }

    public void onLogout()
    {
    }

    public void onServiceConnected()
    {
        if (objServiceManager.isLogin())
        {
            (new TransitDialogBuilder(Transit.this)).setMessage(getString(0x7f0d00ff)).setTitle(getString(0x7f0d0100)).setCancelable(false).setPositiveButton(getString(0x7f0d0074), new android.content.DialogInterface.OnClickListener() {

                final Transit._cls6 this$1;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    TransitUtil.login(this$0);
                    objServiceManager.logout();
                }

            
            {
                this$1 = Transit._cls6.this;
                super();
            }
            }).show();
            return;
        } else
        {
            initTransit();
            return;
        }
    }

    public void onServiceDisconnected()
    {
    }

    public void onUpdateCredential()
    {
    }

    public void onYJDNCanceled(boolean flag, String as[], Object aobj[])
    {
    }

    public void onYJDNDownloadFailed(YJDNErrorData yjdnerrordata, byte abyte0[], Header aheader[], int i, String s, Object obj)
    {
    }

    public void onYJDNDownloadFailedAtConverter(String s, boolean flag, String as[], Object aobj[])
    {
    }

    public void onYJDNDownloaded(byte abyte0[], Header aheader[], int i, String s, Object obj)
    {
    }

    public void onYJDNDownloadedInBackground(byte abyte0[], Header aheader[], int i, String s, Object obj)
    {
    }

    public void onYJDNHttpError(byte abyte0[], Header aheader[], int i, boolean flag, String s, Object obj)
    {
    }

    public void onYJDNResponsed(byte abyte0[], Header aheader[], int i, String s, Object obj)
    {
    }

    public void onYJDNResponsedInBackground(byte abyte0[], Header aheader[], int i, String s, Object obj)
    {
    }

    _cls1.this._cls1()
    {
        this$0 = Transit.this;
        super();
    }
}
