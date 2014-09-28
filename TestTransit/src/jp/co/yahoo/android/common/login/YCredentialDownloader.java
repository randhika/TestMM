// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import jp.co.yahoo.android.common.Base64;
import jp.co.yahoo.android.common.MD5;
import jp.co.yahoo.android.common.YAesCipher;
import jp.co.yahoo.android.common.YHttpRequest;
import jp.co.yahoo.android.common.YLogger;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            YCredentialDownloadListener

public class YCredentialDownloader
{

    public static final int DOWNLOADING_STATUS_CHALLENGE = 1;
    public static final int DOWNLOADING_STATUS_CREDENTIAL = 3;
    public static final int DOWNLOADING_STATUS_NONE = 0;
    public static final int DOWNLOADING_STATUS_TOKEN = 2;
    private static YCredentialDownloader INSTANCE = null;
    private static final char YID_CIPHER_PASSWORD[] = "oP^c31c.I$_wmNTv".toCharArray();
    private String PREFS_KEY_GUID;
    private String PREFS_KEY_ID;
    private String PREFS_KEY_TOKEN;
    private String RAWAUTH_GETCHARRENGE_URL;
    private String RAWAUTH_GETCREDENTIAL_URL;
    private String RAWAUTH_GETTOKEN_URL;
    private String mAppid;
    private Context mContext;
    private int mDownloadingStatus;
    private String mGuid;
    private final Handler mHandler = new Handler() {

        final YCredentialDownloader this$0;

        public void handleMessage(Message message)
        {
            String s = message.getData().getString("Topic");
            if (!s.equals("fetch-challenge-done")) goto _L2; else goto _L1
_L1:
            String s7 = message.getData().getString("Response");
            fetchToken(mTmpYid, mTmpPasswd, s7);
            mTmpYid = "";
            mTmpPasswd = "";
_L16:
            return;
_L2:
            String s6;
            if (s.equals("fetch-challenge-failed"))
            {
                if (mListener != null)
                {
                    mListener.onCredentialFailed("", 1, false);
                }
                mDownloadingStatus = 0;
                return;
            }
            if (s.equals("fetch-challenge-canceled"))
            {
                if (mListener != null)
                {
                    mListener.onCredentialCanceled();
                }
                mDownloadingStatus = 0;
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
            mToken = s6.substring(j + "AuthToken=".length(), k).trim();
_L7:
            int i1 = s6.indexOf("Login=");
            if (i1 == -1) goto _L6; else goto _L5
_L5:
            int j1 = s6.indexOf("\n", i1);
            mYid = s6.substring(i1 + "Login=".length(), j1).trim();
_L8:
            int k1 = s6.indexOf("Guid=");
            if (k1 == -1)
            {
                break MISSING_BLOCK_LABEL_471;
            }
            try
            {
                int l1 = s6.indexOf("\n", k1);
                mGuid = s6.substring(k1 + "Guid=".length(), l1).trim();
            }
            catch (StringIndexOutOfBoundsException stringindexoutofboundsexception1)
            {
                YLogger.log(stringindexoutofboundsexception1.toString());
                mToken = "";
                mYid = "";
                mGuid = "";
            }
_L9:
            if ("".equals(mToken) || "".equals(mYid))
            {
                if (mListener != null)
                {
                    mListener.onCredentialFailed("", 2, false);
                }
                mDownloadingStatus = 0;
                return;
            } else
            {
                writeEncryptPreference(PREFS_KEY_ID, mYid);
                writeEncryptPreference(PREFS_KEY_GUID, mGuid);
                writePreference(PREFS_KEY_TOKEN, mToken);
                fetchCredential(mToken);
                return;
            }
_L4:
            mToken = "";
              goto _L7
_L6:
            mYid = "";
              goto _L8
            mGuid = "";
              goto _L9
            if (s.equals("fetch-token-failed"))
            {
                if (mListener != null)
                {
                    mListener.onCredentialFailed(message.getData().getString("Response"), 2, false);
                }
                mDownloadingStatus = 0;
                return;
            }
            if (s.equals("fetch-token-canceled"))
            {
                if (mListener != null)
                {
                    mListener.onCredentialCanceled();
                }
                mDownloadingStatus = 0;
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
                if (mListener != null)
                {
                    mListener.onCredentialFailed("", 3, false);
                }
            }
              goto _L14
_L13:
            if (mListener != null)
            {
                mListener.onCredentialDownloaded(s2, s3, l);
            }
_L15:
            mDownloadingStatus = 0;
            return;
_L14:
            if (true) goto _L15; else goto _L11
_L11:
            if (s.equals("fetch-credential-failed"))
            {
                if (mListener != null)
                {
                    mListener.onCredentialFailed(message.getData().getString("Response"), 3, false);
                }
                mDownloadingStatus = 0;
                return;
            }
            if (s.equals("fetch-credential-invalidtoken"))
            {
                if (mListener != null)
                {
                    mListener.onCredentialFailed(message.getData().getString("Response"), 3, true);
                }
                mDownloadingStatus = 0;
                return;
            }
            if (s.equals("fetch-credential-canceled"))
            {
                if (mListener != null)
                {
                    mListener.onCredentialCanceled();
                }
                mDownloadingStatus = 0;
                return;
            }
              goto _L16
            i++;
              goto _L17
        }

            
            {
                this$0 = YCredentialDownloader.this;
                super();
            }
    };
    private YCredentialDownloadListener mListener;
    private String mTmpPasswd;
    private String mTmpYid;
    private String mToken;
    private YHttpRequest mYHttpRequest;
    private String mYid;

    private YCredentialDownloader(Context context)
    {
        mContext = null;
        mAppid = "";
        mYid = "";
        mToken = "";
        mGuid = "";
        mTmpYid = "";
        mTmpPasswd = "";
        RAWAUTH_GETCHARRENGE_URL = "https://login.yahoo.co.jp/ws/get_challenge";
        RAWAUTH_GETTOKEN_URL = "https://login.yahoo.co.jp/WSLogin/V1/get_auth_token";
        RAWAUTH_GETCREDENTIAL_URL = "https://login.yahoo.co.jp/WSLogin/V1/get_auth";
        PREFS_KEY_ID = "KEY_ID";
        PREFS_KEY_TOKEN = "KEY_TOKEN";
        PREFS_KEY_GUID = "KEY_GUID";
        mDownloadingStatus = 0;
        mContext = context;
        mToken = readPreference(PREFS_KEY_TOKEN, "");
        if (!mToken.equals(""))
        {
            mYid = readDecryptPreference(PREFS_KEY_ID, "");
            mGuid = readDecryptPreference(PREFS_KEY_GUID, "");
        }
    }

    private void fetchChallenge()
    {
        mYHttpRequest = new YHttpRequest(mContext, RAWAUTH_GETCHARRENGE_URL) {

            final YCredentialDownloader this$0;

            public void onComplete()
            {
                String s = "";
                int i = getStatusCode();
                if (i == 200)
                {
                    String as[] = getResponseString().split("\n");
                    if (as.length >= 2 && as[0].equals("0"))
                    {
                        s = as[1];
                    } else
                    {
                        YLogger.log("Response is incorrect");
                    }
                } else
                {
                    YLogger.log((new StringBuilder()).append("Status Code is ").append(String.valueOf(i)).toString());
                }
                if ("".equals(s))
                {
                    sendMessage("", "fetch-challenge-failed", "");
                    return;
                } else
                {
                    sendMessage("", "fetch-challenge-done", s);
                    return;
                }
            }

            public void onTimeout()
            {
                sendMessage("", "fetch-challenge-failed", "");
            }

            
            {
                this$0 = YCredentialDownloader.this;
                super(context, s);
            }
        };
        mYHttpRequest.asyncGet();
        mDownloadingStatus = 1;
    }

    private void fetchCredential(String s)
    {
        String s1 = (new StringBuilder()).append("").append(RAWAUTH_GETCREDENTIAL_URL).toString();
        String s2 = (new StringBuilder()).append(s1).append("?appid=").toString();
        String s3 = (new StringBuilder()).append(s2).append(mAppid).toString();
        String s4 = (new StringBuilder()).append(s3).append("&token=").toString();
        String s5 = (new StringBuilder()).append(s4).append(s).toString();
        mYHttpRequest = new YHttpRequest(mContext, s5) {

            private String mResponse;
            final YCredentialDownloader this$0;

            public void onComplete()
            {
                String s6 = mResponse;
                mResponse = null;
                String s7 = "";
                int i = getStatusCode();
                if (i != 200)
                {
                    try
                    {
                        s7 = s6.split("\n")[0].substring("Error=".length());
                        YLogger.log((new StringBuilder()).append("Status Code is ").append(String.valueOf(i)).append("(").append(s7).append(")").toString());
                    }
                    catch (StringIndexOutOfBoundsException stringindexoutofboundsexception)
                    {
                        YLogger.log((new StringBuilder()).append("Status Code is ").append(String.valueOf(i)).toString());
                    }
                    s6 = "";
                }
                if (s6.equals(""))
                {
                    if (s7.equals("InvalidToken"))
                    {
                        sendMessage("", "fetch-credential-invalidtoken", s7);
                        return;
                    } else
                    {
                        sendMessage("", "fetch-credential-failed", s7);
                        return;
                    }
                } else
                {
                    sendMessage("", "fetch-credential-done", s6);
                    return;
                }
            }

            public boolean onCompleteInThread()
            {
                mResponse = getResponseString();
                return true;
            }

            public void onTimeout()
            {
                sendMessage("", "fetch-credential-failed", "");
            }

            
            {
                this$0 = YCredentialDownloader.this;
                super(context, s);
            }
        };
        mYHttpRequest.asyncGet();
        mDownloadingStatus = 3;
    }

    private void fetchToken(String s, String s1, String s2)
    {
        String s3;
        if (s1 == null || s1.length() == 0)
        {
            sendMessage("", "fetch-token-failed", "");
            return;
        }
        s3 = "";
        String s9;
        s3 = (new StringBuilder()).append(s3).append("login=").toString();
        s3 = (new StringBuilder()).append(s3).append(s).toString();
        s3 = (new StringBuilder()).append(s3).append("&passwd=").toString();
        s3 = (new StringBuilder()).append(s3).append(MD5.crypt((new StringBuilder()).append(MD5.crypt(s1)).append(s2).toString())).toString();
        s3 = (new StringBuilder()).append(s3).append("&challenge=").toString();
        s9 = (new StringBuilder()).append(s3).append(s2).toString();
        s3 = s9;
_L2:
        String s4 = (new StringBuilder()).append("").append(RAWAUTH_GETTOKEN_URL).toString();
        String s5 = (new StringBuilder()).append(s4).append("?appid=").toString();
        String s6 = (new StringBuilder()).append(s5).append(mAppid).toString();
        String s7 = (new StringBuilder()).append(s6).append("&").toString();
        String s8 = (new StringBuilder()).append(s7).append(s3).toString();
        mYHttpRequest = new YHttpRequest(mContext, s8) {

            final YCredentialDownloader this$0;

            public void onComplete()
            {
                String s10 = getResponseString();
                String s11 = "";
                int i = getStatusCode();
                if (i != 200)
                {
                    try
                    {
                        s11 = s10.split("\n")[0].substring("Error=".length());
                        YLogger.log((new StringBuilder()).append("Status Code is ").append(String.valueOf(i)).append("(").append(s11).append(")").toString());
                    }
                    catch (StringIndexOutOfBoundsException stringindexoutofboundsexception)
                    {
                        YLogger.log((new StringBuilder()).append("Status Code is ").append(String.valueOf(i)).toString());
                    }
                    s10 = "";
                }
                if (s10.equals(""))
                {
                    sendMessage("", "fetch-token-failed", s11);
                    return;
                } else
                {
                    sendMessage("", "fetch-token-done", s10);
                    return;
                }
            }

            public void onTimeout()
            {
                sendMessage("", "fetch-token-failed", "");
            }

            
            {
                this$0 = YCredentialDownloader.this;
                super(context, s);
            }
        };
        mYHttpRequest.asyncGet();
        mDownloadingStatus = 2;
        return;
        NoSuchAlgorithmException nosuchalgorithmexception;
        nosuchalgorithmexception;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static YCredentialDownloader getInstance(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = new YCredentialDownloader(context);
        }
        return INSTANCE;
    }

    private String readDecryptPreference(String s, String s1)
    {
        String s2;
        try
        {
            YAesCipher yaescipher = new YAesCipher();
            byte abyte0[] = Base64.decode(readPreference(s, s1));
            s2 = new String(yaescipher.decrypt(YID_CIPHER_PASSWORD, abyte0));
        }
        catch (Exception exception)
        {
            return s1;
        }
        return s2;
    }

    private String readPreference(String s, String s1)
    {
        return mContext.getSharedPreferences("PREFS_YAHOO_LOGIN_SERVICE", 0).getString(s, s1);
    }

    private boolean readPreference(String s, boolean flag)
    {
        return mContext.getSharedPreferences("PREFS_YAHOO_LOGIN_SERVICE", 0).getBoolean(s, flag);
    }

    private void sendMessage(String s, String s1, String s2)
    {
        Message message = mHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putString("Subject", s);
        bundle.putString("Topic", s1);
        bundle.putString("Response", s2);
        message.setData(bundle);
        mHandler.sendMessage(message);
    }

    private void writeEncryptPreference(String s, String s1)
    {
        try
        {
            writePreference(s, Base64.encodeBytes((new YAesCipher()).encrypt(YID_CIPHER_PASSWORD, s1.getBytes())));
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    private void writePreference(String s, String s1)
    {
        android.content.SharedPreferences.Editor editor = mContext.getSharedPreferences("PREFS_YAHOO_LOGIN_SERVICE", 0).edit();
        editor.putString(s, s1);
        editor.commit();
    }

    private void writePreference(String s, boolean flag)
    {
        android.content.SharedPreferences.Editor editor = mContext.getSharedPreferences("PREFS_YAHOO_LOGIN_SERVICE", 0).edit();
        editor.putBoolean(s, flag);
        editor.commit();
    }

    public void cancelCredential()
    {
        if (mDownloadingStatus != 0 && mYHttpRequest != null)
        {
            mYHttpRequest.cancel();
            if (mDownloadingStatus == 1)
            {
                sendMessage("", "fetch-challenge-canceled", "");
                return;
            }
            if (mDownloadingStatus == 2)
            {
                sendMessage("", "fetch-token-canceled", "");
                return;
            }
            if (mDownloadingStatus == 3)
            {
                sendMessage("", "fetch-credential-canceled", "");
                return;
            }
        }
    }

    public void clearToken()
    {
        writePreference(PREFS_KEY_TOKEN, "");
        writeEncryptPreference(PREFS_KEY_ID, "");
        writeEncryptPreference(PREFS_KEY_GUID, "");
        mToken = "";
        mYid = "";
        mGuid = "";
    }

    public String getYid()
    {
        return mYid;
    }

    public boolean hasToken()
    {
        return !"".equals(mToken) && !"".equals(mYid);
    }

    public void requestCredential(String s, String s1, String s2, YCredentialDownloadListener ycredentialdownloadlistener)
    {
        mListener = ycredentialdownloadlistener;
        mTmpYid = s;
        mTmpPasswd = s1;
        mAppid = s2;
        fetchChallenge();
    }

    public void requestCredential(String s, YCredentialDownloadListener ycredentialdownloadlistener)
    {
        mListener = ycredentialdownloadlistener;
        mAppid = s;
        fetchCredential(mToken);
    }






/*
    static String access$102(YCredentialDownloader ycredentialdownloader, String s)
    {
        ycredentialdownloader.mTmpYid = s;
        return s;
    }

*/







/*
    static String access$202(YCredentialDownloader ycredentialdownloader, String s)
    {
        ycredentialdownloader.mTmpPasswd = s;
        return s;
    }

*/




/*
    static int access$502(YCredentialDownloader ycredentialdownloader, int i)
    {
        ycredentialdownloader.mDownloadingStatus = i;
        return i;
    }

*/



/*
    static String access$602(YCredentialDownloader ycredentialdownloader, String s)
    {
        ycredentialdownloader.mToken = s;
        return s;
    }

*/



/*
    static String access$702(YCredentialDownloader ycredentialdownloader, String s)
    {
        ycredentialdownloader.mYid = s;
        return s;
    }

*/



/*
    static String access$802(YCredentialDownloader ycredentialdownloader, String s)
    {
        ycredentialdownloader.mGuid = s;
        return s;
    }

*/

}
