// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import jp.co.yahoo.android.common.Base64;
import jp.co.yahoo.android.common.MD5;
import jp.co.yahoo.android.common.YAesCipher;
import jp.co.yahoo.android.common.YHttpRequest;
import jp.co.yahoo.android.common.YLogger;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            IYLoginServiceCallback

public class YLoginService extends Service
{

    private static final char YID_CIPHER_PASSWORD[] = "oP^c31c.I$_wmNTv".toCharArray();
    private static final String sActionName = "jp.co.yahoo.android.common.login.IYLoginService";
    private String APPID;
    private String PREFS_KEY_GUID;
    private String PREFS_KEY_ID;
    private String PREFS_KEY_IS_AUTO_LOGIN;
    private String PREFS_KEY_TOKEN;
    private String RAWAUTH_GETCHARRENGE_URL;
    private String RAWAUTH_GETCREDENTIAL_URL;
    private String RAWAUTH_GETTOKEN_URL;
    private IYLoginService.Stub interfaceImpl;
    private final RemoteCallbackList mCallbackList = new RemoteCallbackList();
    private Context mConnectingContext;
    private YHttpRequest mConverterHttpRequest;
    private String mCredential;
    private boolean mDownloadingCredential;
    private boolean mDuringLogin;
    private long mExpiredTime;
    private String mGuid;
    private final Handler mHandler = new Handler() {

        final YLoginService this$0;

        public void handleMessage(Message message)
        {
            String s = message.getData().getString("Topic");
            if (!"fetch-challenge-done".equals(s)) goto _L2; else goto _L1
_L1:
            if (mDuringLogin) goto _L4; else goto _L3
_L3:
            return;
_L4:
            String s8 = message.getData().getString("Response");
            fetchToken(mInputId, mPasswd, s8);
            mInputId = "";
            return;
_L2:
            String s7;
            if (!"fetch-token-done".equals(s))
            {
                break MISSING_BLOCK_LABEL_482;
            }
            if (!mDuringLogin)
            {
                continue; /* Loop/switch isn't completed */
            }
            s7 = message.getData().getString("Response");
            int k = s7.indexOf("AuthToken=");
            if (k == -1) goto _L6; else goto _L5
_L5:
            int l = s7.indexOf("\n", k);
            mToken = s7.substring(k + "AuthToken=".length(), l).trim();
_L9:
            int i1 = s7.indexOf("Login=");
            if (i1 == -1) goto _L8; else goto _L7
_L7:
            int j1 = s7.indexOf("\n", i1);
            mId = s7.substring(i1 + "Login=".length(), j1).trim();
_L10:
            int k1 = s7.indexOf("Guid=");
            if (k1 == -1)
            {
                break MISSING_BLOCK_LABEL_374;
            }
            try
            {
                int l1 = s7.indexOf("\n", k1);
                mGuid = s7.substring(k1 + "Guid=".length(), l1).trim();
            }
            catch (StringIndexOutOfBoundsException stringindexoutofboundsexception)
            {
                YLogger.log(stringindexoutofboundsexception.toString());
                mToken = "";
                mId = "";
                mGuid = "";
            }
_L11:
            if ("".equals(mToken) || "".equals(mId))
            {
                sendMessageToAppli("", "on-login-failed", "");
                return;
            }
            break MISSING_BLOCK_LABEL_387;
_L6:
            mToken = "";
              goto _L9
_L8:
            mId = "";
              goto _L10
            mGuid = "";
              goto _L11
            if (readPreference(PREFS_KEY_IS_AUTO_LOGIN, true))
            {
                writeEncryptPreference(PREFS_KEY_ID, mId);
                writeEncryptPreference(PREFS_KEY_GUID, mGuid);
                writePreference(PREFS_KEY_TOKEN, mToken);
            }
            sendMessageToAppli("", "on-login-changed", "");
            return;
            if (!"fetch-token-failed".equals(s))
            {
                break; /* Loop/switch isn't completed */
            }
            if (mDuringLogin)
            {
                sendMessageToAppli("", "on-login-failed", message.getData().getString("Response"));
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
                sendMessageToAppli("", "on-login-canceled", "");
                return;
            }
            if ("logout-done".equals(s))
            {
                sendMessageToAppli("", "on-login-changed", "");
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
                mCredential = "";
                mExpiredTime = 0L;
                sendMessageToAppli("", "on-update-credential-failed", s1);
                return;
            } else
            {
                mCredential = s1;
                mExpiredTime = ((new Date()).getTime() + (long)(1000 * Integer.parseInt(s4))) - 60000L;
                sendMessageToAppli("", "on-update-credential", "");
                return;
            }
            if ("fetch-credential-failed".equals(s))
            {
                mCredential = "";
                sendMessageToAppli("", "on-update-credential-failed", message.getData().getString("Response"));
                return;
            }
            if ("fetch-credential-invalidtoken".equals(s))
            {
                mCredential = "";
                sendMessageToAppli(message.getData().getString("Subject"), "on-update-credential-invalidtoken", message.getData().getString("Response"));
                return;
            }
            if ("fetch-credential-canceled".equals(s))
            {
                sendMessageToAppli("", "on-credential-canceled", "");
                return;
            }
            if ("fetch-api-done".equals(s))
            {
                sendMessageToAppli(message.getData().getString("Subject"), "on-yjdn-downloaded", message.getData().getString("Response"));
                return;
            }
            if ("fetch-api-failed".equals(s))
            {
                sendMessageToAppli(message.getData().getString("Subject"), "on-yjdn-download-failed", message.getData().getString("Response"));
                return;
            }
            if ("fetch-api-timeout".equals(s))
            {
                sendMessageToAppli(message.getData().getString("Subject"), "on-yjdn-timeout", "");
                return;
            }
            if ("fetch-api-canceled".equals(s))
            {
                sendMessageToAppli(message.getData().getString("Subject"), "on-yjdn-canceled", "");
                return;
            }
            if (true) goto _L3; else goto _L13
_L13:
        }

            
            {
                this$0 = YLoginService.this;
                super();
            }
    };
    private String mId;
    private String mInputId;
    private YHttpRequest mLoginHttpRequest;
    private String mPasswd;
    private String mToken;

    public YLoginService()
    {
        mToken = "";
        mCredential = "";
        mId = "";
        mInputId = "";
        mPasswd = "";
        mConnectingContext = null;
        RAWAUTH_GETCHARRENGE_URL = "https://login.yahoo.co.jp/ws/get_challenge";
        RAWAUTH_GETTOKEN_URL = "https://login.yahoo.co.jp/WSLogin/V1/get_auth_token";
        RAWAUTH_GETCREDENTIAL_URL = "https://login.yahoo.co.jp/WSLogin/V1/get_auth";
        APPID = "";
        PREFS_KEY_IS_AUTO_LOGIN = "PREFS_KEY_IS_AUTO_LOGIN";
        PREFS_KEY_ID = "KEY_ID";
        PREFS_KEY_TOKEN = "KEY_TOKEN";
        PREFS_KEY_GUID = "KEY_GUID";
        mDuringLogin = false;
        mDownloadingCredential = false;
        interfaceImpl = new IYLoginService.Stub() {

            final YLoginService this$0;

            public void cancelCredential()
                throws RemoteException
            {
                if (isDownloadingCredential())
                {
                    mDownloadingCredential = false;
                    if (mConverterHttpRequest != null)
                    {
                        mConverterHttpRequest.cancel();
                    }
                    sendMessage("", "fetch-credential-canceled", "");
                }
            }

            public void cancelLogin()
                throws RemoteException
            {
                if (mDuringLogin)
                {
                    mDuringLogin = false;
                    if (mLoginHttpRequest != null)
                    {
                        mLoginHttpRequest.cancel();
                    }
                    sendMessage("", "fetch-token-canceled", "");
                }
            }

            public String getCredential()
                throws RemoteException
            {
                return mCredential;
            }

            public String getGuid()
                throws RemoteException
            {
                return mGuid;
            }

            public String getStoredYID()
                throws RemoteException
            {
                return readDecryptPreference(PREFS_KEY_ID, "");
            }

            public String getYID()
                throws RemoteException
            {
                return mId;
            }

            public boolean hasCredential()
                throws RemoteException
            {
                return enableCredential();
            }

            public boolean hasToken()
                throws RemoteException
            {
                while (mToken.equals("") || mId.equals("")) 
                {
                    return false;
                }
                return true;
            }

            public boolean isAutoLogin()
                throws RemoteException
            {
                return readPreference(PREFS_KEY_IS_AUTO_LOGIN, true);
            }

            public void login(String s, String s1, boolean flag)
                throws RemoteException
            {
                writePreference(PREFS_KEY_IS_AUTO_LOGIN, flag);
                mInputId = s;
                mPasswd = s1;
                mDuringLogin = true;
                fetchChallenge();
            }

            public void logout()
                throws RemoteException
            {
                mId = "";
                mPasswd = "";
                mToken = "";
                mGuid = "";
                mCredential = "";
                if (!readPreference(PREFS_KEY_IS_AUTO_LOGIN, true))
                {
                    writePreference(PREFS_KEY_ID, "");
                }
                writePreference(PREFS_KEY_TOKEN, "");
                writePreference(PREFS_KEY_GUID, "");
                sendMessage("", "logout-done", "");
            }

            public void registerCallback(IYLoginServiceCallback iyloginservicecallback)
                throws RemoteException
            {
                mCallbackList.register(iyloginservicecallback);
            }

            public void requestCredential()
                throws RemoteException
            {
                if (isDownloadingCredential())
                {
                    return;
                } else
                {
                    fetchCredential(mToken);
                    return;
                }
            }

            public boolean requestingCredential()
                throws RemoteException
            {
                return isDownloadingCredential();
            }

            public void setAppid(String s)
                throws RemoteException
            {
                APPID = s;
            }

            public void setAutoLogin(boolean flag)
                throws RemoteException
            {
                if (flag)
                {
                    writePreference(PREFS_KEY_IS_AUTO_LOGIN, true);
                    writeEncryptPreference(PREFS_KEY_ID, mId);
                    writeEncryptPreference(PREFS_KEY_GUID, mGuid);
                    writePreference(PREFS_KEY_TOKEN, mToken);
                    return;
                } else
                {
                    writePreference(PREFS_KEY_IS_AUTO_LOGIN, false);
                    writePreference(PREFS_KEY_ID, "");
                    writePreference(PREFS_KEY_TOKEN, "");
                    writePreference(PREFS_KEY_GUID, "");
                    return;
                }
            }

            public void unregisterCallback(IYLoginServiceCallback iyloginservicecallback)
                throws RemoteException
            {
                mCallbackList.unregister(iyloginservicecallback);
            }

            
            {
                this$0 = YLoginService.this;
                super();
            }
        };
    }

    private boolean enableCredential()
    {
        while (mCredential.equals("") || (new Date()).getTime() > mExpiredTime) 
        {
            return false;
        }
        return true;
    }

    private void fetchChallenge()
    {
        mLoginHttpRequest = new YHttpRequest(mConnectingContext, RAWAUTH_GETCHARRENGE_URL) {

            final YLoginService this$0;

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
                sendMessage("", "fetch-challenge-done", s);
                clearResponse();
            }

            public void onTimeout()
            {
                sendMessage("", "fetch-token-failed", "");
                clearResponse();
            }

            
            {
                this$0 = YLoginService.this;
                super(context, s);
            }
        };
        mLoginHttpRequest.asyncGet();
    }

    private void fetchCredential(String s)
    {
        StringBuilder stringbuilder = new StringBuilder(RAWAUTH_GETCREDENTIAL_URL);
        stringbuilder.append("?appid=");
        stringbuilder.append(APPID);
        stringbuilder.append("&token=");
        stringbuilder.append(s);
        mConverterHttpRequest = new YHttpRequest(mConnectingContext, stringbuilder.toString()) {

            final YLoginService this$0;

            public void onComplete()
            {
                if (!isDownloadingCredential())
                {
                    return;
                }
                String s1 = getResponseString();
                String s2 = "";
                int i = getStatusCode();
                if (i != 200)
                {
                    try
                    {
                        s2 = s1.split("\n")[0].substring("Error=".length());
                        YLogger.log((new StringBuilder()).append("Status Code is ").append(String.valueOf(i)).append("(").append(s2).append(")").toString());
                    }
                    catch (StringIndexOutOfBoundsException stringindexoutofboundsexception)
                    {
                        YLogger.log((new StringBuilder()).append("Status Code is ").append(String.valueOf(i)).toString());
                    }
                    s1 = "";
                }
                if ("".equals(s1))
                {
                    if ("InvalidToken".equals(s2))
                    {
                        sendMessage("", "fetch-credential-invalidtoken", s2);
                    } else
                    {
                        sendMessage("", "fetch-credential-failed", s2);
                    }
                } else
                {
                    sendMessage("", "fetch-credential-done", s1);
                }
                mDownloadingCredential = false;
                clearResponse();
            }

            public void onTimeout()
            {
                if (!isDownloadingCredential())
                {
                    return;
                } else
                {
                    sendMessage("", "fetch-credential-failed", "");
                    mDownloadingCredential = false;
                    clearResponse();
                    return;
                }
            }

            
            {
                this$0 = YLoginService.this;
                super(context, s);
            }
        };
        mConverterHttpRequest.asyncGet();
        mDownloadingCredential = true;
    }

    private void fetchToken(String s, String s1, String s2)
    {
        StringBuilder stringbuilder = new StringBuilder(RAWAUTH_GETTOKEN_URL);
        stringbuilder.append("?appid=");
        stringbuilder.append(APPID);
        stringbuilder.append("&login=");
        stringbuilder.append(s);
        stringbuilder.append("&passwd=");
        try
        {
            stringbuilder.append(MD5.crypt((new StringBuilder()).append(MD5.crypt(s1)).append(s2).toString()));
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception) { }
        stringbuilder.append("&challenge=");
        stringbuilder.append(s2);
        mLoginHttpRequest = new YHttpRequest(mConnectingContext, stringbuilder.toString()) {

            final YLoginService this$0;

            public void onComplete()
            {
                String s3 = getResponseString();
                String s4 = "";
                int i = getStatusCode();
                if (i != 200)
                {
                    try
                    {
                        s4 = s3.split("\n")[0].substring("Error=".length());
                        YLogger.log((new StringBuilder()).append("Status Code is ").append(String.valueOf(i)).append("(").append(s4).append(")").toString());
                    }
                    catch (StringIndexOutOfBoundsException stringindexoutofboundsexception)
                    {
                        YLogger.log((new StringBuilder()).append("Status Code is ").append(String.valueOf(i)).toString());
                    }
                    s3 = "";
                }
                if ("".equals(s3))
                {
                    sendMessage("", "fetch-token-failed", s4);
                } else
                {
                    sendMessage("", "fetch-token-done", s3);
                }
                clearResponse();
            }

            public void onTimeout()
            {
                sendMessage("", "fetch-token-failed", "");
                clearResponse();
            }

            
            {
                this$0 = YLoginService.this;
                super(context, s);
            }
        };
        mLoginHttpRequest.asyncGet();
    }

    private boolean isDownloadingCredential()
    {
        return mDownloadingCredential;
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
        return getSharedPreferences("PREFS_YAHOO_LOGIN_SERVICE", 0).getString(s, s1);
    }

    private boolean readPreference(String s, boolean flag)
    {
        return getSharedPreferences("PREFS_YAHOO_LOGIN_SERVICE", 0).getBoolean(s, flag);
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

    private void sendMessageToAppli(String s, String s1, String s2)
    {
        this;
        JVM INSTR monitorenter ;
        int i = mCallbackList.beginBroadcast();
        int j = 0;
        while (j < i) 
        {
            Exception exception;
            try
            {
                ((IYLoginServiceCallback)mCallbackList.getBroadcastItem(j)).receiveMessage(s, s1, s2);
            }
            catch (Exception exception1) { }
            finally
            {
                this;
            }
            j++;
        }
        mCallbackList.finishBroadcast();
        this;
        JVM INSTR monitorexit ;
        return;
        throw exception;
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
        android.content.SharedPreferences.Editor editor = getSharedPreferences("PREFS_YAHOO_LOGIN_SERVICE", 0).edit();
        editor.putString(s, s1);
        editor.commit();
    }

    private void writePreference(String s, boolean flag)
    {
        android.content.SharedPreferences.Editor editor = getSharedPreferences("PREFS_YAHOO_LOGIN_SERVICE", 0).edit();
        editor.putBoolean(s, flag);
        editor.commit();
    }

    public IBinder onBind(Intent intent)
    {
        if ("jp.co.yahoo.android.common.login.IYLoginService".equals(intent.getAction()))
        {
            try
            {
                mConnectingContext = createPackageContext(intent.getExtras().getString("packagename"), 4);
            }
            catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception) { }
            return interfaceImpl;
        } else
        {
            return null;
        }
    }

    public void onCreate()
    {
        super.onCreate();
        mToken = readPreference(PREFS_KEY_TOKEN, "");
        if (!mToken.equals(""))
        {
            mId = readDecryptPreference(PREFS_KEY_ID, "");
            mGuid = readDecryptPreference(PREFS_KEY_GUID, "");
        }
    }

    public void onDestroy()
    {
        super.onDestroy();
    }










/*
    static String access$1402(YLoginService yloginservice, String s)
    {
        yloginservice.mInputId = s;
        return s;
    }

*/



/*
    static String access$1502(YLoginService yloginservice, String s)
    {
        yloginservice.mPasswd = s;
        return s;
    }

*/



/*
    static boolean access$1602(YLoginService yloginservice, boolean flag)
    {
        yloginservice.mDuringLogin = flag;
        return flag;
    }

*/







/*
    static String access$202(YLoginService yloginservice, String s)
    {
        yloginservice.mId = s;
        return s;
    }

*/



/*
    static boolean access$2202(YLoginService yloginservice, boolean flag)
    {
        yloginservice.mDownloadingCredential = flag;
        return flag;
    }

*/




/*
    static String access$2502(YLoginService yloginservice, String s)
    {
        yloginservice.APPID = s;
        return s;
    }

*/




/*
    static long access$2802(YLoginService yloginservice, long l)
    {
        yloginservice.mExpiredTime = l;
        return l;
    }

*/



/*
    static String access$302(YLoginService yloginservice, String s)
    {
        yloginservice.mGuid = s;
        return s;
    }

*/



/*
    static String access$402(YLoginService yloginservice, String s)
    {
        yloginservice.mCredential = s;
        return s;
    }

*/




/*
    static String access$602(YLoginService yloginservice, String s)
    {
        yloginservice.mToken = s;
        return s;
    }

*/



}
