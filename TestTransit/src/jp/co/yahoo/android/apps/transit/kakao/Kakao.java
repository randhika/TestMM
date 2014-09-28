// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.kakao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.kakao:
//            KakaoDialog

public class Kakao
{
    private static class InflatingEntity extends HttpEntityWrapper
    {

        public InputStream getContent()
            throws IOException
        {
            return new GZIPInputStream(wrappedEntity.getContent());
        }

        public long getContentLength()
        {
            return -1L;
        }

        public InflatingEntity(HttpEntity httpentity)
        {
            super(httpentity);
        }
    }

    public static interface KakaoListener
    {

        public abstract void onResult(Status status, JSONObject jsonobject);
    }

    public static interface KakaoTokenListener
    {

        public abstract void onSetTokens(String s, String s1);
    }

    private static final class Method extends Enum
    {

        private static final Method $VALUES[];
        public static final Method GET;
        public static final Method POST;

        public static Method valueOf(String s)
        {
            return (Method)Enum.valueOf(jp/co/yahoo/android/apps/transit/kakao/Kakao$Method, s);
        }

        public static Method[] values()
        {
            return (Method[])$VALUES.clone();
        }

        static 
        {
            GET = new Method("GET", 0);
            POST = new Method("POST", 1);
            Method amethod[] = new Method[2];
            amethod[0] = GET;
            amethod[1] = POST;
            $VALUES = amethod;
        }

        private Method(String s, int i)
        {
            super(s, i);
        }
    }

    public static class Status
    {

        private Exception mException;
        private int mHttpStatus;
        private boolean mIsSuccess;
        private String mMessage;

        public Exception getException()
        {
            return mException;
        }

        public int getHttpStatus()
        {
            return mHttpStatus;
        }

        public String getMessage()
        {
            return mMessage;
        }

        public boolean isSuccess()
        {
            return mIsSuccess;
        }

        Status(int i, String s, Exception exception)
        {
            mIsSuccess = false;
            mHttpStatus = i;
            mMessage = s;
            mException = exception;
        }

        Status(boolean flag)
        {
            if (flag)
            {
                mHttpStatus = 200;
            }
            mIsSuccess = flag;
        }

        Status(boolean flag, String s, Exception exception)
        {
            mIsSuccess = flag;
            mMessage = s;
            mException = exception;
        }
    }


    private static final String API_SERVER_URL = "https://api.kakao.com/v1";
    private static final String AUTH_SERVER_URL = "https://auth.kakao.com";
    private static final int DEFAULT_AUTH_ACTIVITY_CODE = 17797;
    static final Status ERROR = new Status(false);
    private static final int MINIMUM_VERSION = 71;
    static final Status OK = new Status(true);
    private static final String SDK_VERSION = "1.0.0";
    public static final int STATUS_INSUFFICIENT_PERMISSION = -100;
    public static final int STATUS_NOT_FOUNT = -10;
    public static final int STATUS_SUCCESS = 0;
    public static final int STATUS_SUCCESS_NOT_VERIFIED = 10;
    public static final int STATUS_UNKOWN_ERROR = -500;
    public static final int STATUS_UNREGISTERED_USER = -13;
    static final String TAG = "kakao-android-sdk";
    private static ExecutorService exe = Executors.newCachedThreadPool();
    static boolean isLogging = false;
    private String mAccessToken;
    private Activity mActivity;
    private String mAuthCode;
    private DefaultHttpClient mClient;
    private String mClientId;
    private String mClientSecret;
    private Context mContext;
    private KakaoListener mListener;
    private String mRedirect;
    private String mRefreshToken;
    private KakaoTokenListener mTokenListener;

    public Kakao(Context context, String s, String s1, String s2)
    {
        mContext = context;
        mClientId = s;
        mClientSecret = s1;
        mRedirect = s2;
    }

    private int getAppVersion(Context context)
    {
        int i;
        try
        {
            i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }
        catch (Exception exception)
        {
            boolean flag = isLogging;
            i = 0;
            if (flag)
            {
                Log.w("kakao-android-sdk", exception);
                return 0;
            }
        }
        return i;
    }

    private DefaultHttpClient getClient()
    {
        if (mClient != null)
        {
            return mClient;
        } else
        {
            DefaultHttpClient defaulthttpclient = new DefaultHttpClient();
            defaulthttpclient.addRequestInterceptor(new HttpRequestInterceptor() {

                final Kakao this$0;

                public void process(HttpRequest httprequest, HttpContext httpcontext)
                {
                    if (!httprequest.containsHeader("Accept-Encoding"))
                    {
                        httprequest.addHeader("Accept-Encoding", "gzip");
                    }
                }

            
            {
                this$0 = Kakao.this;
                super();
            }
            });
            defaulthttpclient.addResponseInterceptor(new HttpResponseInterceptor() {

                final Kakao this$0;

                public void process(HttpResponse httpresponse, HttpContext httpcontext)
                {
                    Header header = httpresponse.getEntity().getContentEncoding();
                    if (header == null) goto _L2; else goto _L1
_L1:
                    HeaderElement aheaderelement[];
                    int i;
                    int j;
                    aheaderelement = header.getElements();
                    i = aheaderelement.length;
                    j = 0;
_L7:
                    if (j >= i) goto _L2; else goto _L3
_L3:
                    if (!aheaderelement[j].getName().equalsIgnoreCase("gzip")) goto _L5; else goto _L4
_L4:
                    httpresponse.setEntity(new InflatingEntity(httpresponse.getEntity()));
_L2:
                    return;
_L5:
                    j++;
                    if (true) goto _L7; else goto _L6
_L6:
                }

            
            {
                this$0 = Kakao.this;
                super();
            }
            });
            mClient = defaulthttpclient;
            return defaulthttpclient;
        }
    }

    private int getKakaoTalkVersion(Context context)
    {
        int i;
        try
        {
            i = context.getPackageManager().getPackageInfo("com.kakao.talk", 0).versionCode;
        }
        catch (Exception exception)
        {
            boolean flag = isLogging;
            i = 0;
            if (flag)
            {
                Log.w("kakao-android-sdk", exception);
                return 0;
            }
        }
        return i;
    }

    private static boolean isIntentAvailable(Context context, Intent intent)
    {
        for (List list = context.getPackageManager().queryIntentActivities(intent, 0x10000); list == null || list.size() <= 0;)
        {
            return false;
        }

        return true;
    }

    private static String makeJsonMetaInfo(List list)
    {
        JSONArray jsonarray = new JSONArray();
        Iterator iterator = list.iterator();
_L1:
        JSONObject jsonobject;
        if (iterator.hasNext())
        {
            Map map = (Map)iterator.next();
            jsonobject = new JSONObject();
            String s;
            for (Iterator iterator1 = map.keySet().iterator(); iterator1.hasNext(); jsonobject.put(s, map.get(s)))
            {
                s = (String)iterator1.next();
            }

            break MISSING_BLOCK_LABEL_122;
        }
        break MISSING_BLOCK_LABEL_117;
        JSONException jsonexception;
        jsonexception;
        if (isLogging)
        {
            Log.w("kakao-android-sdk", jsonexception);
        }
        return jsonarray.toString();
        jsonarray.put(jsonobject);
          goto _L1
    }

    private void refreshAccessToken()
        throws Exception
    {
        HttpPost httppost;
        DefaultHttpClient defaulthttpclient;
        httppost = new HttpPost("https://auth.kakao.com/oauth/token");
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("grant_type", "refresh_token"));
        arraylist.add(new BasicNameValuePair("client_id", mClientId));
        arraylist.add(new BasicNameValuePair("client_secret", mClientSecret));
        arraylist.add(new BasicNameValuePair("refresh_token", mRefreshToken));
        if (arraylist != null && arraylist.size() > 0)
        {
            httppost.setEntity(new UrlEncodedFormEntity(arraylist, "UTF-8"));
        }
        defaulthttpclient = new DefaultHttpClient();
        HttpEntity httpentity = defaulthttpclient.execute(httppost).getEntity();
        String s = EntityUtils.toString(httpentity);
        httpentity.consumeContent();
        JSONObject jsonobject = new JSONObject(s);
        mAccessToken = jsonobject.optString("access_token");
        String s1 = jsonobject.optString("refresh_token");
        if (!TextUtils.isEmpty(s1))
        {
            mRefreshToken = s1;
        }
        if (mTokenListener == null) goto _L2; else goto _L1
_L1:
        mTokenListener.onSetTokens(mAccessToken, mRefreshToken);
_L4:
        defaulthttpclient.getConnectionManager().shutdown();
        return;
_L2:
        if (!isLogging) goto _L4; else goto _L3
_L3:
        Log.e("kakao-android-sdk", "KakaoTokenListener is not set. see Kakao.setTokenListener().");
          goto _L4
        Exception exception1;
        exception1;
        throw exception1;
        Exception exception;
        exception;
        defaulthttpclient.getConnectionManager().shutdown();
        throw exception;
    }

    private void request(final Method method, final String function, final ArrayList params, final KakaoListener listener)
    {
        exe.execute(new Runnable() {

            final Kakao this$0;
            final String val$function;
            final KakaoListener val$listener;
            final Method val$method;
            final ArrayList val$params;

            public void run()
            {
                requestInBackground(method, function, params, false, listener);
_L1:
                return;
                Exception exception;
                exception;
                if (Kakao.isLogging)
                {
                    Log.w("kakao-android-sdk", exception);
                }
                if (listener != null)
                {
                    listener.onResult(new Status(false, exception.toString(), exception), null);
                    return;
                }
                  goto _L1
            }

            
            {
                this$0 = Kakao.this;
                method = method1;
                function = s;
                params = arraylist;
                listener = kakaolistener;
                super();
            }
        });
    }

    private void requestAccessToken()
        throws Exception
    {
        HttpPost httppost;
        DefaultHttpClient defaulthttpclient;
        httppost = new HttpPost("https://auth.kakao.com/oauth/token");
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("grant_type", "authorization_code"));
        arraylist.add(new BasicNameValuePair("client_id", mClientId));
        arraylist.add(new BasicNameValuePair("client_secret", mClientSecret));
        arraylist.add(new BasicNameValuePair("code", mAuthCode));
        arraylist.add(new BasicNameValuePair("redirect_uri", mRedirect));
        if (arraylist != null && arraylist.size() > 0)
        {
            httppost.setEntity(new UrlEncodedFormEntity(arraylist, "UTF-8"));
        }
        defaulthttpclient = new DefaultHttpClient();
        JSONObject jsonobject;
        HttpResponse httpresponse = defaulthttpclient.execute(httppost);
        HttpEntity httpentity = httpresponse.getEntity();
        String s = EntityUtils.toString(httpentity);
        httpentity.consumeContent();
        jsonobject = new JSONObject(s);
        if (httpresponse.getStatusLine().getStatusCode() == 200)
        {
            break MISSING_BLOCK_LABEL_233;
        }
        mListener.onResult(ERROR, jsonobject);
        defaulthttpclient.getConnectionManager().shutdown();
        return;
        mAccessToken = jsonobject.optString("access_token");
        String s1 = jsonobject.optString("refresh_token");
        if (!TextUtils.isEmpty(s1))
        {
            mRefreshToken = s1;
        }
        if (mTokenListener != null)
        {
            mTokenListener.onSetTokens(mAccessToken, mRefreshToken);
        }
        if (mListener != null)
        {
            mListener.onResult(OK, jsonobject);
        }
        defaulthttpclient.getConnectionManager().shutdown();
        return;
        Exception exception1;
        exception1;
        throw exception1;
        Exception exception;
        exception;
        defaulthttpclient.getConnectionManager().shutdown();
        throw exception;
    }

    private void requestInBackground(Method method, String s, ArrayList arraylist, boolean flag, KakaoListener kakaolistener)
        throws Exception
    {
        String s1;
        BasicNameValuePair basicnamevaluepair;
        BasicNameValuePair basicnamevaluepair1;
        boolean flag1;
        Iterator iterator;
        NameValuePair namevaluepair;
label0:
        {
            if (!isValidSession() && isLogging)
            {
                Log.e("kakao-android-sdk", "Tokens are not set. see Kakao.setTokens()");
                if (kakaolistener != null)
                {
                    kakaolistener.onResult(ERROR, null);
                }
            }
            s1 = (new StringBuilder()).append("https://api.kakao.com/v1/").append(s).toString();
            if (arraylist == null)
            {
                arraylist = new ArrayList();
                break label0;
            }
        }
        if (!flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        Iterator iterator1 = arraylist.iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                continue; /* Loop/switch isn't completed */
            }
        } while (!"access_token".equals(((NameValuePair)iterator1.next()).getName()));
        iterator1.remove();
        if (true) goto _L2; else goto _L1
_L2:
        Object obj;
        basicnamevaluepair = new BasicNameValuePair("sdkver", "1.0.0");
        arraylist.add(basicnamevaluepair);
        basicnamevaluepair1 = new BasicNameValuePair("access_token", mAccessToken);
        arraylist.add(basicnamevaluepair1);
        if (method != Method.GET)
        {
            break; /* Loop/switch isn't completed */
        }
        StringBuilder stringbuilder = (new StringBuilder(s1)).append('?');
        flag1 = true;
        for (iterator = arraylist.iterator(); iterator.hasNext();)
        {
            namevaluepair = (NameValuePair)iterator.next();
            if (namevaluepair.getValue() != null)
            {
                if (flag1)
                {
                    flag1 = false;
                } else
                {
                    stringbuilder.append('&');
                }
                stringbuilder.append(URLEncoder.encode(namevaluepair.getName(), "UTF-8")).append('=').append(URLEncoder.encode(namevaluepair.getValue(), "UTF-8"));
            }
        }

        String s3 = stringbuilder.toString();
        obj = new HttpGet(s3);
        if (isLogging)
        {
            Log.d("kakao-android-sdk", (new StringBuilder()).append("reqeust(GET): ").append(s3).toString());
        }
_L8:
        HttpResponse httpresponse;
        int i;
        ((HttpUriRequest) (obj)).addHeader("Accept-Encoding", "gzip");
        httpresponse = getClient().execute(((HttpUriRequest) (obj)));
        i = httpresponse.getStatusLine().getStatusCode();
        if (i != 401 || flag) goto _L4; else goto _L3
_L3:
        refreshAccessToken();
        requestInBackground(method, s, arraylist, true, kakaolistener);
_L6:
        return;
_L1:
        HttpPost httppost = new HttpPost(s1);
        httppost.setEntity(new UrlEncodedFormEntity(arraylist, "UTF-8"));
        obj = httppost;
        if (isLogging)
        {
            Log.d("kakao-android-sdk", (new StringBuilder()).append("reqeust(POST): ").append(s1).append(" / params: ").append(arraylist).toString());
        }
        continue; /* Loop/switch isn't completed */
_L4:
        String s2;
        s2 = EntityUtils.toString(httpresponse.getEntity());
        if (isLogging)
        {
            Log.d("kakao-android-sdk", (new StringBuilder()).append("response(").append(i).append("): ").append(s2).toString());
        }
        if (kakaolistener != null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (isLogging)
        {
            Log.w("kakao-android-sdk", "KakaoListener is not set on this request.");
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
        if (i == 200)
        {
            JSONObject jsonobject = new JSONObject(s2);
            int j = jsonobject.optInt("status", -500);
            if (j == 0 || j == 10)
            {
                kakaolistener.onResult(OK, jsonobject);
                return;
            } else
            {
                Status status = new Status(i, jsonobject.optString("message", null), null);
                kakaolistener.onResult(status, jsonobject);
                return;
            }
        }
        if (i == 401 && mTokenListener != null)
        {
            mTokenListener.onSetTokens(null, null);
            mAccessToken = null;
            mRefreshToken = null;
        }
        try
        {
            JSONObject jsonobject1 = new JSONObject(s2);
            kakaolistener.onResult(new Status(i, jsonobject1.optString("message", null), null), jsonobject1);
            return;
        }
        catch (Exception exception)
        {
            if (isLogging)
            {
                Log.w("kakao-android-sdk", exception);
            }
            kakaolistener.onResult(new Status(i, s2, exception), null);
            return;
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    private boolean startKakaoRegister(Activity activity)
    {
        Intent intent;
        boolean flag1;
        intent = new Intent("android.intent.action.SEND", Uri.parse((new StringBuilder()).append("kakaoauth://requestemailidlogin?client_id=").append(mClientId).toString()));
        int i = getKakaoTalkVersion(activity);
        boolean flag = isIntentAvailable(activity, intent);
        flag1 = false;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_78;
        }
        flag1 = false;
        if (i < 71)
        {
            break MISSING_BLOCK_LABEL_78;
        }
        activity.startActivityForResult(intent, 17797);
        flag1 = true;
_L2:
        return flag1;
        Exception exception;
        exception;
        boolean flag2 = isLogging;
        flag1 = false;
        if (flag2)
        {
            Log.w("kakao-android-sdk", exception);
            return false;
        }
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void friends(KakaoListener kakaolistener)
    {
        request(Method.GET, "friends.json", null, kakaolistener);
    }

    public boolean isValidSession()
    {
        return !TextUtils.isEmpty(mAccessToken) && !TextUtils.isEmpty(mRefreshToken);
    }

    public void localUser(KakaoListener kakaolistener)
    {
        request(Method.GET, "users/me.json", null, kakaolistener);
    }

    public void login(Activity activity, KakaoListener kakaolistener)
    {
        mListener = kakaolistener;
        mActivity = activity;
        if (!startKakaoRegister(activity))
        {
            startKakaoWebviewDialog(activity, kakaolistener);
        }
    }

    public void logout(final KakaoListener listener)
    {
        KakaoListener kakaolistener = new KakaoListener() {

            final Kakao this$0;
            final KakaoListener val$listener;

            public void onResult(Status status, JSONObject jsonobject)
            {
                if (status.isSuccess())
                {
                    setTokens(null, null);
                    if (mTokenListener != null)
                    {
                        mTokenListener.onSetTokens(null, null);
                    }
                }
                if (listener != null)
                {
                    listener.onResult(status, jsonobject);
                }
            }

            
            {
                this$0 = Kakao.this;
                listener = kakaolistener;
                super();
            }
        };
        request(Method.GET, "accounts/logout.json", null, kakaolistener);
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
label0:
        {
label1:
            {
label2:
                {
                    Bundle bundle;
label3:
                    {
                        if (i != 17797)
                        {
                            break label0;
                        }
                        if (mListener != null)
                        {
                            if (j != -1)
                            {
                                break label1;
                            }
                            mAuthCode = intent.getStringExtra("authorization_code");
                            bundle = new Bundle();
                            if (!TextUtils.isEmpty(mAuthCode))
                            {
                                break label2;
                            }
                            if (!Boolean.valueOf(intent.getBooleanExtra("changeaccount", false)).booleanValue() || mActivity == null)
                            {
                                break label3;
                            }
                            startKakaoWebviewDialog(mActivity, mListener);
                        }
                        return;
                    }
                    bundle.putString("message", "authorization code is null.");
                    mListener.onResult(ERROR, null);
                    return;
                }
                (new Thread() {

                    final Kakao this$0;

                    public void run()
                    {
                        try
                        {
                            requestAccessToken();
                            return;
                        }
                        catch (Exception exception)
                        {
                            if (Kakao.isLogging)
                            {
                                Log.w("kakao-android-sdk", exception);
                            }
                            mListener.onResult(new Status(false, exception.toString(), exception), null);
                            return;
                        }
                    }

            
            {
                this$0 = Kakao.this;
                super();
            }
                }).start();
                return;
            }
            mListener.onResult(new Status(false, "canceled", null), null);
            return;
        }
        mListener.onResult(new Status(false, "not valid request code", null), null);
    }

    public void sendMessage(long l, boolean flag, String s, List list, KakaoListener kakaolistener)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("msg", s));
        if (flag)
        {
            arraylist.add(new BasicNameValuePair("chat_token_id", Long.toString(l)));
        } else
        {
            arraylist.add(new BasicNameValuePair("receiver_id", Long.toString(l)));
        }
        if (list != null && list.size() > 0)
        {
            arraylist.add(new BasicNameValuePair("metainfo", makeJsonMetaInfo(list)));
        }
        arraylist.add(new BasicNameValuePair("appver", Integer.toString(getAppVersion(mContext))));
        request(Method.POST, "chats/link.json", arraylist, kakaolistener);
    }

    public void setAuthKey(String s)
    {
        mClientId = s;
    }

    public void setDebugLog(boolean flag)
    {
        isLogging = flag;
    }

    public void setTokenListener(KakaoTokenListener kakaotokenlistener)
    {
        mTokenListener = kakaotokenlistener;
    }

    public void setTokens(String s, String s1)
    {
        mAccessToken = s;
        mRefreshToken = s1;
    }

    public void startKakaoWebviewDialog(Activity activity, KakaoListener kakaolistener)
    {
        Locale locale = activity.getResources().getConfiguration().locale;
        int i = getKakaoTalkVersion(activity);
        (new KakaoDialog(activity, (new StringBuilder()).append("https://auth.kakao.com/sdks/main.html?client_id=").append(mClientId).append("&client_secret=").append(mClientSecret).append("&os=android&v=").append(i).append("&lang=").append(locale.getLanguage()).append("&sdkver=").append("1.0.0").toString(), this, kakaolistener, mTokenListener)).show();
    }

    public void unregister(KakaoListener kakaolistener)
    {
        request(Method.GET, "accounts/unregister.json", null, kakaolistener);
    }





}
