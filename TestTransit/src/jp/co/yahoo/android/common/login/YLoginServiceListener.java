// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import org.apache.http.Header;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            YJDNErrorData

public interface YLoginServiceListener
{

    public abstract void onLogin();

    public abstract void onLoginCanceled();

    public abstract void onLoginFailed(String s);

    public abstract void onLogout();

    public abstract void onServiceConnected();

    public abstract void onServiceDisconnected();

    public abstract void onUpdateCredential();

    public abstract void onYJDNCanceled(boolean flag, String as[], Object aobj[]);

    public abstract void onYJDNDownloadFailed(YJDNErrorData yjdnerrordata, byte abyte0[], Header aheader[], int i, String s, Object obj);

    public abstract void onYJDNDownloadFailedAtConverter(String s, boolean flag, String as[], Object aobj[]);

    public abstract void onYJDNDownloaded(byte abyte0[], Header aheader[], int i, String s, Object obj);

    public abstract void onYJDNDownloadedInBackground(byte abyte0[], Header aheader[], int i, String s, Object obj);

    public abstract void onYJDNHttpError(byte abyte0[], Header aheader[], int i, boolean flag, String s, Object obj);

    public abstract void onYJDNResponsed(byte abyte0[], Header aheader[], int i, String s, Object obj);

    public abstract void onYJDNResponsedInBackground(byte abyte0[], Header aheader[], int i, String s, Object obj);
}
