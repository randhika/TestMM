// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import jp.co.yahoo.android.common.HttpConfig;
import jp.co.yahoo.android.common.YHttpRequest;
import jp.co.yahoo.android.common.YLogger;
import org.apache.http.Header;

// Referenced classes of package jp.co.yahoo.android.common.login:
//            YJDNErrorParser, YLoginActivity, YLogoutDialogActivity, YLoginService, 
//            YLoginServiceListener, IYLoginService, IYLoginServiceCallback, YJDNErrorData

public class YLoginServiceManager
{
    private static class RequestData
    {

        private HttpConfig _config;
        private Map _header;
        private Object _optionalObj;
        private String _url;
        private boolean _useErrorCheck;

        public Map getHeader()
        {
            return _header;
        }

        public HttpConfig getHttpConfig()
        {
            return _config;
        }

        public Object getOptionalObj()
        {
            return _optionalObj;
        }

        public String getUrl()
        {
            return _url;
        }

        public boolean getUseErrorCheck()
        {
            return _useErrorCheck;
        }

        public RequestData(String s, Map map, HttpConfig httpconfig, Object obj, boolean flag)
        {
            _url = s;
            _header = map;
            _config = httpconfig;
            _optionalObj = obj;
            _useErrorCheck = flag;
        }
    }

    private class YJDNDownloader
    {

        private boolean _downloading;
        private final Object _lock;
        private RequestData _requestData;
        private YHttpRequest _yHttpRequest;
        final YLoginServiceManager this$0;

        public boolean cancel()
        {
            Object obj = _lock;
            obj;
            JVM INSTR monitorenter ;
            if (!_downloading) goto _L2; else goto _L1
_L1:
            _downloading = false;
            boolean flag = true;
_L4:
            obj;
            JVM INSTR monitorexit ;
            if (flag && _yHttpRequest != null)
            {
                _yHttpRequest.cancel();
            }
            return flag;
_L2:
            flag = false;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public boolean fetch()
        {
            int i;
            Map map;
            String s2;
            String s3;
            String s4;
            String s5;
            if (_requestData == null)
            {
                return false;
            }
            String as[] = getCredential();
            String s = as[0];
            String s1 = as[1];
            HttpConfig httpconfig = _requestData.getHttpConfig();
            i = httpconfig.getMethod();
            map = httpconfig.getRequestBodyMap();
            s2 = httpconfig.getRequestBodyString();
            s3 = httpconfig.getCharset();
            s4 = httpconfig.getUploadFilePath();
            s5 = httpconfig.getContentType();
            int j = httpconfig.getTimeout();
            boolean flag = httpconfig.isAuthApi();
            String s6 = _requestData.getUrl();
            StringBuilder stringbuilder = new StringBuilder(s6);
            Object obj = _requestData.getHeader();
            if (flag)
            {
                if (i == 2 && map != null)
                {
                    map.put("appid", mAppId);
                    map.put("wssid", s1);
                    if (m_keep_flg == 1)
                    {
                        YHttpRequest yhttprequest;
                        if (s6.contains("?"))
                        {
                            stringbuilder.append("&");
                        } else
                        {
                            stringbuilder.append("?");
                        }
                        stringbuilder.append("appid=");
                        stringbuilder.append(mAppId);
                        stringbuilder.append("&wssid=");
                        stringbuilder.append(s1);
                    }
                } else
                {
                    if (s6.contains("?"))
                    {
                        stringbuilder.append("&");
                    } else
                    {
                        stringbuilder.append("?");
                    }
                    stringbuilder.append("appid=");
                    stringbuilder.append(mAppId);
                    stringbuilder.append("&wssid=");
                    stringbuilder.append(s1);
                }
                if (obj == null)
                {
                    obj = new HashMap();
                }
                ((Map) (obj)).put("Cookie", s);
            }
            yhttprequest = new YHttpRequest(mContext, stringbuilder.toString(), ((Map) (obj))) {

                final YJDNDownloader this$1;

                public boolean onCompleteInThread()
                {
label0:
                    {
                        synchronized (_lock)
                        {
                            if (_downloading)
                            {
                                break label0;
                            }
                        }
                        return false;
                    }
                    String s;
                    Object obj1;
                    boolean flag;
                    _downloading = false;
                    s = _requestData.getUrl();
                    obj1 = _requestData.getOptionalObj();
                    flag = _requestData.getUseErrorCheck();
                    obj;
                    JVM INSTR monitorexit ;
                    onYJDNDownload(getStatusCode(), getResponseByteArray(), getAllHeader(), s, obj1, flag);
                    clearResponse();
                    return false;
                    exception;
                    obj;
                    JVM INSTR monitorexit ;
                    throw exception;
                }

                public void onTimeoutInThread()
                {
label0:
                    {
                        synchronized (_lock)
                        {
                            if (_downloading)
                            {
                                break label0;
                            }
                        }
                        return;
                    }
                    String s;
                    Object obj1;
                    boolean flag;
                    _downloading = false;
                    s = _requestData.getUrl();
                    obj1 = _requestData.getOptionalObj();
                    flag = _requestData.getUseErrorCheck();
                    obj;
                    JVM INSTR monitorexit ;
                    onYJDNTimeout(getStatusCode(), getResponseByteArray(), getAllHeader(), s, obj1, flag);
                    clearResponse();
                    return;
                    exception;
                    obj;
                    JVM INSTR monitorexit ;
                    throw exception;
                }

            
            {
                this$1 = YJDNDownloader.this;
                super(context, s, map);
            }
            };
            _yHttpRequest = yhttprequest;
            _yHttpRequest.setTimeout(j);
            synchronized (_lock)
            {
                _downloading = true;
            }
            if (i != 2) goto _L2; else goto _L1
_L1:
            if (map == null) goto _L4; else goto _L3
_L3:
            _yHttpRequest.asyncPost(map);
_L6:
            return true;
            exception;
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
_L4:
            if (s2 != null)
            {
                _yHttpRequest.asyncPost(s2, s3);
            } else
            if (s4 != null)
            {
                YHttpRequest yhttprequest3 = _yHttpRequest;
                File file2 = new File(s4);
                yhttprequest3.asyncPost(file2, s5);
            }
            continue; /* Loop/switch isn't completed */
_L2:
            if (i == 3)
            {
                if (map != null)
                {
                    _yHttpRequest.asyncDelete(map);
                } else
                if (s2 != null)
                {
                    _yHttpRequest.asyncDelete(s2, s3);
                } else
                if (s4 != null)
                {
                    YHttpRequest yhttprequest2 = _yHttpRequest;
                    File file1 = new File(s4);
                    yhttprequest2.asyncDelete(file1, s5);
                } else
                {
                    _yHttpRequest.asyncDelete();
                }
            } else
            if (i == 4)
            {
                if (map != null)
                {
                    _yHttpRequest.asyncPut(map);
                } else
                if (s2 != null)
                {
                    _yHttpRequest.asyncPut(s2, s3);
                } else
                if (s4 != null)
                {
                    YHttpRequest yhttprequest1 = _yHttpRequest;
                    File file = new File(s4);
                    yhttprequest1.asyncPut(file, s5);
                }
            } else
            {
                _yHttpRequest.asyncGet();
            }
            if (true) goto _L6; else goto _L5
_L5:
        }

        public String getDownloadingUrl()
        {
            String s;
            RequestData requestdata = _requestData;
            s = null;
            if (requestdata != null)
            {
                s = _requestData.getUrl();
            }
            Object obj = _lock;
            obj;
            JVM INSTR monitorenter ;
            if (_downloading)
            {
                return s;
            }
            obj;
            JVM INSTR monitorexit ;
            return null;
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public RequestData getRequestData()
        {
            return _requestData;
        }

        public boolean isDownloading()
        {
            boolean flag;
            synchronized (_lock)
            {
                flag = _downloading;
            }
            return flag;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void setRequestData(RequestData requestdata)
        {
            _requestData = requestdata;
        }




/*
        static boolean access$1102(YJDNDownloader yjdndownloader, boolean flag)
        {
            yjdndownloader._downloading = flag;
            return flag;
        }

*/


        public YJDNDownloader()
        {
            this$0 = YLoginServiceManager.this;
            super();
            _yHttpRequest = null;
            _downloading = false;
            _requestData = null;
            _lock = new Object();
        }

        public YJDNDownloader(RequestData requestdata)
        {
            this$0 = YLoginServiceManager.this;
            super();
            _yHttpRequest = null;
            _downloading = false;
            _requestData = null;
            _lock = new Object();
            setRequestData(requestdata);
        }
    }


    private static final int DOWNLOADER_NUM = 3;
    public static final String EXTRA_KEY_APPID = "extra_key_appid";
    public static final String EXTRA_KEY_IS_CHANGE_YID = "extra_key_is_change_yid";
    public static final String EXTRA_KEY_MESSAGE = "extra_key_message";
    public static final String EXTRA_KEY_OTHER_ID_LOGIN = "extra_key_other_id_login";
    public static final String EXTRA_KEY_REGISTRATION_URL = "extra_key_registration_url";
    public static final String EXTRA_KEY_REQUEST_CODE = "extra_key_request_code";
    public static final String REGISTRATION_URL = "https://account.edit.yahoo.co.jp/registration";
    public static final int REQUEST_CODE_LOGIN = 1059;
    public static final int REQUEST_CODE_LOGOUT = 1060;
    private static final String TAG = "YLoginServiceManager";
    private String mAppId;
    private IYLoginServiceCallback mCallback = new IYLoginServiceCallback.Stub() {

        final YLoginServiceManager this$0;

        public void receiveMessage(String s1, String s2, String s3)
            throws RemoteException
        {
            sendMessage(s1, s2, s3);
        }

            
            {
                this$0 = YLoginServiceManager.this;
                super();
            }
    };
    private Context mContext;
    private List mDownloaders;
    private final Handler mHandler;
    private YLoginServiceListener mListener;
    private final Object mLock;
    private IYLoginService mLoginService;
    private LinkedList mRequesDataList;
    private YJDNErrorParser mYJDNErrorParser;
    private ServiceConnection mYLoginServiceConn = new ServiceConnection() {

        final YLoginServiceManager this$0;

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            mLoginService = IYLoginService.Stub.asInterface(ibinder);
            try
            {
                mLoginService.setAppid(mAppId);
            }
            catch (RemoteException remoteexception)
            {
                YLogger.log("YLoginServiceManager", remoteexception.toString());
            }
            catch (Exception exception)
            {
                YLogger.log("YLoginServiceManager", exception.toString());
            }
            if (mListener != null)
            {
                mListener.onServiceConnected();
            }
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            mLoginService = null;
            if (mListener != null)
            {
                mListener.onServiceDisconnected();
            }
        }

            
            {
                this$0 = YLoginServiceManager.this;
                super();
            }
    };
    private int m_keep_flg;

    public YLoginServiceManager(YLoginServiceListener yloginservicelistener, String s)
    {
        this(yloginservicelistener, s, new YJDNErrorParser());
    }

    public YLoginServiceManager(YLoginServiceListener yloginservicelistener, String s, YJDNErrorParser yjdnerrorparser)
    {
        mAppId = "";
        mContext = null;
        mListener = null;
        mLoginService = null;
        mYJDNErrorParser = null;
        mDownloaders = new ArrayList();
        mRequesDataList = new LinkedList();
        mLock = new Object();
        m_keep_flg = 0;
        mHandler = new Handler() {

            final YLoginServiceManager this$0;

            public void handleMessage(Message message)
            {
                String s1 = message.getData().getString("Topic");
                if (!"on-login-changed".equals(s1)) goto _L2; else goto _L1
_L1:
                if (mListener == null) goto _L4; else goto _L3
_L3:
                if (!isLogin()) goto _L6; else goto _L5
_L5:
                mListener.onLogin();
_L4:
                return;
_L6:
                mListener.onLogout();
                return;
_L2:
                if (!"on-login-failed".equals(s1))
                {
                    break; /* Loop/switch isn't completed */
                }
                if (mListener != null)
                {
                    mListener.onLoginFailed(message.getData().getString("Data"));
                    return;
                }
                if (true) goto _L4; else goto _L7
_L7:
                if (!"on-login-canceled".equals(s1))
                {
                    break; /* Loop/switch isn't completed */
                }
                if (mListener != null)
                {
                    mListener.onLoginCanceled();
                    return;
                }
                if (true) goto _L4; else goto _L8
_L8:
                if ("on-update-credential".equals(s1))
                {
                    if (mListener != null)
                    {
                        mListener.onUpdateCredential();
                    }
                    fetchApi();
                    return;
                }
                if (!"on-update-credential-failed".equals(s1))
                {
                    continue; /* Loop/switch isn't completed */
                }
                LinkedList linkedlist1 = clearApiRequest();
                if (mListener != null)
                {
                    mListener.onYJDNDownloadFailedAtConverter(message.getData().getString("Data"), false, clipUrls(linkedlist1), clipOptionalObjs(linkedlist1));
                    return;
                }
                continue; /* Loop/switch isn't completed */
                if (!"on-update-credential-invalidtoken".equals(s1)) goto _L4; else goto _L9
_L9:
                LinkedList linkedlist = clearApiRequest();
                if (mListener != null)
                {
                    mListener.onYJDNDownloadFailedAtConverter(message.getData().getString("Data"), true, clipUrls(linkedlist), clipOptionalObjs(linkedlist));
                    return;
                }
                if (true) goto _L4; else goto _L10
_L10:
            }

            
            {
                this$0 = YLoginServiceManager.this;
                super();
            }
        };
        mListener = yloginservicelistener;
        mAppId = s;
        mYJDNErrorParser = yjdnerrorparser;
    }

    private void cancelFetchApi()
    {
        LinkedList linkedlist = new LinkedList();
        int i = mDownloaders.size();
        for (int j = 0; j < i; j++)
        {
            YJDNDownloader yjdndownloader = (YJDNDownloader)mDownloaders.get(j);
            if (yjdndownloader.isDownloading() && yjdndownloader.cancel())
            {
                linkedlist.add(yjdndownloader.getRequestData());
            }
        }

        LinkedList linkedlist1 = clearApiRequest();
        for (Iterator iterator = linkedlist.iterator(); iterator.hasNext(); linkedlist1.add((RequestData)iterator.next())) { }
        onYJDNCancel(false, linkedlist1);
    }

    private void cancelFetchApi(String s)
    {
        LinkedList linkedlist;
        linkedlist = new LinkedList();
        int i = mDownloaders.size();
        for (int j = 0; j < i; j++)
        {
            YJDNDownloader yjdndownloader = (YJDNDownloader)mDownloaders.get(j);
            if (!yjdndownloader.isDownloading())
            {
                continue;
            }
            String s2 = yjdndownloader.getDownloadingUrl();
            if (s2 != null && s2.equals(s) && yjdndownloader.cancel())
            {
                linkedlist.add(yjdndownloader.getRequestData());
            }
        }

        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int k = -1 + mRequesDataList.size();
_L2:
        if (k < 0)
        {
            break MISSING_BLOCK_LABEL_176;
        }
        RequestData requestdata;
        String s1;
        requestdata = (RequestData)mRequesDataList.get(k);
        s1 = requestdata.getUrl();
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_194;
        }
        if (s1.equals(s))
        {
            linkedlist.add(requestdata);
            mRequesDataList.remove(k);
        }
        break MISSING_BLOCK_LABEL_194;
        obj;
        JVM INSTR monitorexit ;
        onYJDNCancel(false, linkedlist);
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        k--;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private LinkedList clearApiRequest()
    {
        LinkedList linkedlist = new LinkedList();
        linkedlist.addAll(mRequesDataList);
        synchronized (mLock)
        {
            mRequesDataList.clear();
        }
        return linkedlist;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private Object[] clipOptionalObjs(LinkedList linkedlist)
    {
        Object aobj[];
        if (linkedlist != null && linkedlist.size() > 0)
        {
            int i = linkedlist.size();
            aobj = new Object[i];
            for (int j = 0; j < i; j++)
            {
                aobj[j] = ((RequestData)linkedlist.get(j)).getOptionalObj();
            }

        } else
        {
            aobj = null;
        }
        return aobj;
    }

    private String[] clipUrls(LinkedList linkedlist)
    {
        String as[];
        if (linkedlist != null && linkedlist.size() > 0)
        {
            int i = linkedlist.size();
            as = new String[i];
            for (int j = 0; j < i; j++)
            {
                as[j] = ((RequestData)linkedlist.get(j)).getUrl();
            }

        } else
        {
            as = null;
        }
        return as;
    }

    private void createYJDNDownloaders()
    {
        if (mDownloaders.size() == 0)
        {
            for (int i = 0; i < 3; i++)
            {
                mDownloaders.add(new YJDNDownloader());
            }

        }
    }

    private void fetchApi()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mDownloaders.size();
        int j = 0;
_L2:
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_36;
        }
        if (!mRequesDataList.isEmpty())
        {
            break MISSING_BLOCK_LABEL_39;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        RequestData requestdata;
        requestdata = (RequestData)mRequesDataList.get(0);
        if (requestdata.getHttpConfig().isAuthApi() && !hasCredential())
        {
            requestCredential();
            break MISSING_BLOCK_LABEL_131;
        }
        YJDNDownloader yjdndownloader = (YJDNDownloader)mDownloaders.get(j);
        if (!yjdndownloader.isDownloading())
        {
            mRequesDataList.remove(0);
            yjdndownloader.setRequestData(requestdata);
            yjdndownloader.fetch();
        }
        break MISSING_BLOCK_LABEL_131;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        j++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private Intent getLoginActivityIntent(Context context)
    {
        return new Intent(context, jp/co/yahoo/android/common/login/YLoginActivity);
    }

    private Intent getLogoutActivityIntent(Context context)
    {
        return new Intent(context, jp/co/yahoo/android/common/login/YLogoutDialogActivity);
    }

    public static float getVersion(Context context)
    {
        PackageManager packagemanager;
        Intent intent;
        packagemanager = context.getPackageManager();
        intent = new Intent("jp.co.yahoo.android.common.login.IYLoginService", null, context, jp/co/yahoo/android/common/login/YLoginService);
        List list = packagemanager.queryIntentServices(intent, 128);
        if (list.size() != 1) goto _L2; else goto _L1
_L1:
        XmlResourceParser xmlresourceparser;
        int i;
        xmlresourceparser = ((ResolveInfo)list.get(0)).serviceInfo.loadXmlMetaData(packagemanager, "jp.co.yahoo.android.common.login.YLoginServiceInfo");
        i = xmlresourceparser.getEventType();
          goto _L3
_L5:
        i = xmlresourceparser.next();
          goto _L3
_L6:
        float f;
        if (!xmlresourceparser.getName().equals("YLoginServiceInfo"))
        {
            break; /* Loop/switch isn't completed */
        }
        f = xmlresourceparser.getAttributeFloatValue(null, "version", 0.0F);
        return f;
        Exception exception;
        exception;
        YLogger.log("YLoginServiceManager", exception.toString());
_L2:
        return 0.0F;
_L3:
        if (i == 1) goto _L2; else goto _L4
_L4:
        if (i == 2) goto _L6; else goto _L5
    }

    private void onYJDNCancel(boolean flag, LinkedList linkedlist)
    {
        if (mListener != null && linkedlist != null && linkedlist.size() > 0)
        {
            mListener.onYJDNCanceled(flag, clipUrls(linkedlist), clipOptionalObjs(linkedlist));
        }
    }

    private void onYJDNDownload(int i, byte abyte0[], Header aheader[], String s, Object obj, boolean flag)
    {
        byte abyte2[];
        int j;
        YJDNErrorData yjdnerrordata;
        byte abyte1[] = new byte[0];
        Handler handler;
        if (abyte0 == null)
        {
            abyte2 = abyte1;
        } else
        {
            abyte2 = abyte0;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        if (mYJDNErrorParser != null)
        {
            yjdnerrordata = mYJDNErrorParser.getData(abyte2);
        } else
        {
            yjdnerrordata = null;
        }
        if (i >= 200 && i < 300)
        {
            if (yjdnerrordata == null)
            {
                j = 0;
            } else
            {
                j = 1;
            }
        } else
        if (yjdnerrordata == null)
        {
            j = 2;
        } else
        {
            j = 1;
        }
        if (j == 0 && mListener != null)
        {
            mListener.onYJDNDownloadedInBackground(abyte2, aheader, i, s, obj);
        }
_L4:
        handler = new Handler(yjdnerrordata) {

            final YLoginServiceManager this$0;
            final byte val$bytes[];
            final int val$code;
            final YJDNErrorData val$errorData;
            final Header val$headers[];
            final Object val$optionalObj;
            final int val$status;
            final String val$url;
            final boolean val$useErrorCheck;

            public void handleMessage(Message message)
            {
                if (!useErrorCheck) goto _L2; else goto _L1
_L1:
                if (status != 0 || mListener == null) goto _L4; else goto _L3
_L3:
                mListener.onYJDNDownloaded(bytes, headers, code, url, optionalObj);
_L6:
                fetchApi();
                return;
_L4:
                if (status == 1 && mListener != null)
                {
                    mListener.onYJDNDownloadFailed(errorData, bytes, headers, code, url, optionalObj);
                } else
                if (status == 2 && mListener != null)
                {
                    mListener.onYJDNHttpError(bytes, headers, code, false, url, optionalObj);
                }
                continue; /* Loop/switch isn't completed */
_L2:
                if (mListener != null)
                {
                    mListener.onYJDNResponsed(bytes, headers, code, url, optionalObj);
                }
                if (true) goto _L6; else goto _L5
_L5:
            }

            
            {
                this$0 = YLoginServiceManager.this;
                useErrorCheck = flag;
                status = i;
                bytes = abyte0;
                headers = aheader;
                code = j;
                url = s;
                optionalObj = obj;
                errorData = yjdnerrordata;
                super(final_looper);
            }
        };
        handler.sendMessage(handler.obtainMessage());
        return;
_L2:
        YLoginServiceListener yloginservicelistener = mListener;
        j = 0;
        yjdnerrordata = null;
        if (yloginservicelistener != null)
        {
            mListener.onYJDNResponsedInBackground(abyte2, aheader, i, s, obj);
            j = 0;
            yjdnerrordata = null;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private void onYJDNTimeout(int i, byte abyte0[], Header aheader[], String s, Object obj, boolean flag)
    {
        byte abyte1[] = new byte[0];
        byte abyte2[];
        Handler handler;
        if (abyte0 == null)
        {
            abyte2 = abyte1;
        } else
        {
            abyte2 = abyte0;
        }
        if (mListener != null && !flag)
        {
            mListener.onYJDNResponsedInBackground(abyte2, aheader, i, s, obj);
        }
        handler = new Handler(obj) {

            final YLoginServiceManager this$0;
            final byte val$bytes[];
            final int val$code;
            final Header val$headers[];
            final Object val$optionalObj;
            final String val$url;
            final boolean val$useErrorCheck;

            public void handleMessage(Message message)
            {
                if (!useErrorCheck) goto _L2; else goto _L1
_L1:
                if (mListener != null)
                {
                    mListener.onYJDNHttpError(bytes, headers, code, true, url, optionalObj);
                }
_L4:
                fetchApi();
                return;
_L2:
                if (mListener != null)
                {
                    mListener.onYJDNResponsed(bytes, headers, code, url, optionalObj);
                }
                if (true) goto _L4; else goto _L3
_L3:
            }

            
            {
                this$0 = YLoginServiceManager.this;
                useErrorCheck = flag;
                bytes = abyte0;
                headers = aheader;
                code = i;
                url = s;
                optionalObj = obj;
                super(final_looper);
            }
        };
        handler.sendMessage(handler.obtainMessage());
    }

    private void sendMessage(String s, String s1, String s2)
    {
        Message message = mHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putString("Subject", s);
        bundle.putString("Topic", s1);
        bundle.putString("Data", s2);
        message.setData(bundle);
        mHandler.sendMessage(message);
    }

    public boolean bindService(Context context)
    {
        try
        {
            mContext = context;
            Intent intent = new Intent(context, jp/co/yahoo/android/common/login/YLoginService);
            intent.setAction("jp.co.yahoo.android.common.login.IYLoginService");
            intent.putExtra("packagename", context.getPackageName());
            context.bindService(intent, mYLoginServiceConn, 1);
        }
        catch (Exception exception)
        {
            YLogger.log("YLoginServiceManager", exception.toString());
            return false;
        }
        return true;
    }

    public void cancelLogin()
    {
        try
        {
            mLoginService.cancelLogin();
            return;
        }
        catch (RemoteException remoteexception)
        {
            YLogger.log("YLoginServiceManager", remoteexception.toString());
            return;
        }
        catch (Exception exception)
        {
            YLogger.log("YLoginServiceManager", exception.toString());
        }
    }

    public void cancelYJDN()
    {
        if (!mLoginService.requestingCredential()) goto _L2; else goto _L1
_L1:
        mLoginService.cancelCredential();
        boolean flag = true;
_L3:
        Exception exception;
        RemoteException remoteexception;
        if (flag)
        {
            onYJDNCancel(true, clearApiRequest());
            return;
        } else
        {
            cancelFetchApi();
            return;
        }
_L2:
        flag = false;
          goto _L3
        remoteexception;
        YLogger.log("YLoginServiceManager", remoteexception.toString());
        flag = false;
          goto _L3
        exception;
        YLogger.log("YLoginServiceManager", exception.toString());
        flag = false;
          goto _L3
    }

    public void cancelYJDN(String s)
    {
        cancelFetchApi(s);
    }

    public String[] getCredential()
    {
        String as[] = new String[3];
        String as1[] = mLoginService.getCredential().split("\n");
        int i = 0;
_L4:
        String s;
        if (i < as1.length)
        {
            s = as1[i];
            if (s.startsWith("Cookie=", 0))
            {
                as[0] = s.substring("Cookie=".length());
                break MISSING_BLOCK_LABEL_151;
            }
            break MISSING_BLOCK_LABEL_69;
        }
_L2:
        return as;
        RemoteException remoteexception;
        if (s.startsWith("WSSID=", 0))
        {
            as[1] = s.substring("WSSID=".length());
            break MISSING_BLOCK_LABEL_151;
        }
        try
        {
            if (s.startsWith("Expiration=", 0))
            {
                as[2] = s.substring("Expiration=".length());
            }
            break MISSING_BLOCK_LABEL_151;
        }
        // Misplaced declaration of an exception variable
        catch (RemoteException remoteexception)
        {
            YLogger.log("YLoginServiceManager", remoteexception.toString());
        }
        catch (Exception exception)
        {
            YLogger.log("YLoginServiceManager", exception.toString());
            return as;
        }
        if (true) goto _L2; else goto _L1
_L1:
        i++;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public String getGuid()
    {
        String s = mLoginService.getGuid();
        return s;
        RemoteException remoteexception;
        remoteexception;
        YLogger.log("YLoginServiceManager", remoteexception.toString());
_L2:
        return "";
        Exception exception;
        exception;
        YLogger.log("YLoginServiceManager", exception.toString());
        if (true) goto _L2; else goto _L1
_L1:
    }

    public List getRequestingURL()
    {
        ArrayList arraylist = new ArrayList();
        int i = mDownloaders.size();
        for (int j = 0; j < i; j++)
        {
            YJDNDownloader yjdndownloader = (YJDNDownloader)mDownloaders.get(j);
            if (yjdndownloader.isDownloading())
            {
                arraylist.add(yjdndownloader.getDownloadingUrl());
            }
        }

        return arraylist;
    }

    public List getStockedURL()
    {
        ArrayList arraylist = new ArrayList();
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mRequesDataList.size();
        int j = 0;
_L2:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        arraylist.add(((RequestData)mRequesDataList.get(j)).getUrl());
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        obj;
        JVM INSTR monitorexit ;
        return arraylist;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public String getStoredYID()
    {
        String s = mLoginService.getStoredYID();
        return s;
        RemoteException remoteexception;
        remoteexception;
        YLogger.log("YLoginServiceManager", remoteexception.toString());
_L2:
        return "";
        Exception exception;
        exception;
        YLogger.log("YLoginServiceManager", exception.toString());
        if (true) goto _L2; else goto _L1
_L1:
    }

    public String getYID()
    {
        String s = mLoginService.getYID();
        return s;
        RemoteException remoteexception;
        remoteexception;
        YLogger.log("YLoginServiceManager", remoteexception.toString());
_L2:
        return "";
        Exception exception;
        exception;
        YLogger.log("YLoginServiceManager", exception.toString());
        if (true) goto _L2; else goto _L1
_L1:
    }

    public boolean hasCredential()
    {
        boolean flag = mLoginService.hasCredential();
        return flag;
        RemoteException remoteexception;
        remoteexception;
        YLogger.log("YLoginServiceManager", remoteexception.toString());
_L2:
        return false;
        Exception exception;
        exception;
        YLogger.log("YLoginServiceManager", exception.toString());
        if (true) goto _L2; else goto _L1
_L1:
    }

    public boolean isAutoLogin()
    {
        boolean flag = mLoginService.isAutoLogin();
        return flag;
        RemoteException remoteexception;
        remoteexception;
        YLogger.log("YLoginServiceManager", remoteexception.toString());
_L2:
        return false;
        Exception exception;
        exception;
        YLogger.log("YLoginServiceManager", exception.toString());
        if (true) goto _L2; else goto _L1
_L1:
    }

    public boolean isConnectingService()
    {
        return mLoginService != null;
    }

    public boolean isLogin()
    {
        boolean flag = mLoginService.hasToken();
        return flag;
        RemoteException remoteexception;
        remoteexception;
        YLogger.log("YLoginServiceManager", remoteexception.toString());
_L2:
        return false;
        Exception exception;
        exception;
        YLogger.log("YLoginServiceManager", exception.toString());
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void login(String s, String s1, boolean flag)
    {
        try
        {
            mLoginService.login(s, s1, flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            YLogger.log("YLoginServiceManager", remoteexception.toString());
            return;
        }
        catch (Exception exception)
        {
            YLogger.log("YLoginServiceManager", exception.toString());
        }
    }

    public void logout()
    {
        try
        {
            mLoginService.logout();
            return;
        }
        catch (RemoteException remoteexception)
        {
            YLogger.log("YLoginServiceManager", remoteexception.toString());
            return;
        }
        catch (Exception exception)
        {
            YLogger.log("YLoginServiceManager", exception.toString());
        }
    }

    public void registerCallback()
    {
        if (!isConnectingService())
        {
            break MISSING_BLOCK_LABEL_20;
        }
        mLoginService.registerCallback(mCallback);
        return;
        RemoteException remoteexception;
        remoteexception;
        YLogger.log("YLoginServiceManager", remoteexception.toString());
        return;
        Exception exception;
        exception;
        YLogger.log("YLoginServiceManager", exception.toString());
        return;
    }

    public void requestCredential()
    {
        try
        {
            mLoginService.requestCredential();
            return;
        }
        catch (RemoteException remoteexception)
        {
            YLogger.log("YLoginServiceManager", remoteexception.toString());
            return;
        }
        catch (Exception exception)
        {
            YLogger.log("YLoginServiceManager", exception.toString());
        }
    }

    public void requestYJDN(String s, Map map, Map map1)
    {
        HttpConfig httpconfig;
        if (map1 == null)
        {
            httpconfig = new HttpConfig(1);
        } else
        {
            httpconfig = new HttpConfig(2);
            httpconfig.setRequestBodyMap(map1);
        }
        requestYJDN(s, map, httpconfig, null, true);
    }

    public void requestYJDN(String s, Map map, HttpConfig httpconfig)
    {
        requestYJDN(s, map, httpconfig, null, true);
    }

    public void requestYJDN(String s, Map map, HttpConfig httpconfig, Object obj, boolean flag)
    {
        synchronized (mLock)
        {
            mRequesDataList.add(new RequestData(s, map, httpconfig, obj, flag));
        }
        createYJDNDownloaders();
        fetchApi();
        return;
        exception;
        obj1;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setAutoLogin(boolean flag)
    {
        try
        {
            mLoginService.setAutoLogin(flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            YLogger.log("YLoginServiceManager", remoteexception.toString());
            return;
        }
        catch (Exception exception)
        {
            YLogger.log("YLoginServiceManager", exception.toString());
        }
    }

    public void setKeepFlg(int i)
    {
        m_keep_flg = i;
    }

    public void startLoginActivity(Context context)
    {
        startLoginActivity(context, false, "https://account.edit.yahoo.co.jp/registration", null);
    }

    public void startLoginActivity(Context context, boolean flag)
    {
        startLoginActivity(context, flag, "https://account.edit.yahoo.co.jp/registration", null);
    }

    public void startLoginActivity(Context context, boolean flag, String s, String s1)
    {
        Intent intent = getLoginActivityIntent(context);
        intent.putExtra("extra_key_appid", mAppId);
        intent.putExtra("extra_key_other_id_login", flag);
        intent.putExtra("extra_key_registration_url", s);
        intent.putExtra("extra_key_message", s1);
        context.startActivity(intent);
    }

    public void startLoginActivityForResult(Activity activity)
    {
        startLoginActivityForResult(activity, false, "https://account.edit.yahoo.co.jp/registration", null);
    }

    public void startLoginActivityForResult(Activity activity, boolean flag)
    {
        startLoginActivityForResult(activity, flag, "https://account.edit.yahoo.co.jp/registration", null);
    }

    public void startLoginActivityForResult(Activity activity, boolean flag, String s, String s1)
    {
        Intent intent = getLoginActivityIntent(activity);
        intent.putExtra("extra_key_appid", mAppId);
        intent.putExtra("extra_key_other_id_login", flag);
        intent.putExtra("extra_key_registration_url", s);
        intent.putExtra("extra_key_message", s1);
        activity.startActivityForResult(intent, 0);
    }

    public void startLogoutDialogActivity(Context context)
    {
        Intent intent = getLogoutActivityIntent(context);
        intent.putExtra("extra_key_appid", mAppId);
        context.startActivity(intent);
    }

    public void startLogoutDialogActivityForResult(Activity activity)
    {
        Intent intent = getLogoutActivityIntent(activity);
        intent.putExtra("extra_key_appid", mAppId);
        activity.startActivityForResult(intent, 0);
    }

    public void unbindService(Context context)
    {
        try
        {
            context.unbindService(mYLoginServiceConn);
            return;
        }
        catch (Exception exception)
        {
            YLogger.log("YLoginServiceManager", exception.toString());
        }
    }

    public void unregisterCallback()
    {
        if (!isConnectingService())
        {
            break MISSING_BLOCK_LABEL_20;
        }
        mLoginService.unregisterCallback(mCallback);
        return;
        RemoteException remoteexception;
        remoteexception;
        YLogger.log("YLoginServiceManager", remoteexception.toString());
        return;
        Exception exception;
        exception;
        YLogger.log("YLoginServiceManager", exception.toString());
        return;
    }










/*
    static IYLoginService access$502(YLoginServiceManager yloginservicemanager, IYLoginService iyloginservice)
    {
        yloginservicemanager.mLoginService = iyloginservice;
        return iyloginservice;
    }

*/




}
