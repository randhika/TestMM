// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.text.TextUtils;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.common.StringUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.CustomDialogTitle;
import jp.co.yahoo.yconnect.AppLoginExplicit;
import jp.co.yahoo.yconnect.core.api.ApiClient;
import jp.co.yahoo.yconnect.core.api.ApiClientException;
import jp.co.yahoo.yconnect.core.http.HttpHeaders;
import jp.co.yahoo.yconnect.core.http.HttpParameters;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import jp.co.yahoo.yconnect.core.oauth2.TokenException;
import jp.co.yahoo.yconnect.data.DataManager;
import jp.co.yahoo.yconnect.data.storage.SecretStorageException;
import org.json.JSONObject;

public abstract class AuthApiBase extends AsyncTask
{
    public static interface AuthApiListener
    {

        public abstract boolean onCanceled();

        public abstract boolean onError(APIError apierror);

        public abstract boolean onSuccess(Object obj);
    }


    public static String ERR_CODE_EXPIRED_TOKEN = "702";
    public static final String YJDN_ERROR_HEADER = "X-YahooJ-ProxyError";
    protected APIError error;
    protected String m_Response;
    boolean m_cancelable;
    protected ApiClient m_client;
    protected Context m_context;
    private byte m_count;
    private String m_httpMethod;
    protected AuthApiListener m_listener;
    private ProgressDialog m_progDialog;
    boolean m_showDialog;
    private String m_url;
    private HttpParameters parameters;
    protected String post;
    protected String sMsg;
    protected String sMsgErr;

    public AuthApiBase(Context context, BearerToken bearertoken, AuthApiListener authapilistener)
    {
        m_httpMethod = "GET";
        m_url = "";
        m_showDialog = true;
        m_cancelable = true;
        m_Response = "";
        m_context = context;
        m_client = new ApiClient(bearertoken);
        parameters = new HttpParameters();
        m_listener = authapilistener;
        sMsgErr = context.getString(0x7f0d0102);
        sMsg = context.getString(0x7f0d04a1);
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((Void[])aobj);
    }

    protected transient Object doInBackground(Void avoid[])
    {
        if (isCancelled())
        {
            return null;
        }
        m_count = (byte)(1 + m_count);
        if (!parameters.isEmpty())
        {
            m_url = (new StringBuilder()).append(m_url).append("?").append(parameters.toQueryString()).toString();
        }
        m_client.fetchResouce(m_url, m_httpMethod);
        m_Response = m_client.getResponse();
        if (m_client.getStatusCode() == 200)
        {
            break MISSING_BLOCK_LABEL_165;
        }
        error = new APIError();
        HttpHeaders httpheaders = m_client.getHeaders();
        if (!httpheaders.containsKey("X-YahooJ-ProxyError"))
        {
            break MISSING_BLOCK_LABEL_155;
        }
        parseYJDNError(m_Response, (String)httpheaders.get("X-YahooJ-ProxyError"));
        return null;
        Object obj;
        try
        {
            parseError(m_Response);
        }
        catch (ApiClientException apiclientexception)
        {
            apiclientexception.printStackTrace();
            if (apiclientexception.isInvalidToken())
            {
                AppLoginExplicit apploginexplicit = AppLoginExplicit.getInstance();
                apploginexplicit.setClientId(m_context.getString(0x7f0d0037));
                apploginexplicit.setCustomUriScheme(m_context.getString(0x7f0d0568));
                try
                {
                    apploginexplicit.refreshToken(m_context);
                    byte abyte0[] = DataManager.loadSecretKey(m_context, "default_yid");
                    DataManager datamanager = new DataManager(m_context, abyte0, "default_yid");
                    m_client.setHeader("Authorization", (new StringBuilder()).append("Bearer ").append(datamanager.loadAccessToken().getAccessToken()).toString());
                }
                catch (TokenException tokenexception)
                {
                    tokenexception.printStackTrace();
                    error = new APIError();
                    error.setCode(tokenexception.getErrorCode());
                    error.setApiMessage(tokenexception.getError());
                    return null;
                }
                catch (SecretStorageException secretstorageexception)
                {
                    secretstorageexception.printStackTrace();
                    error = new APIError();
                    error.setCode(secretstorageexception.getError());
                    error.setApiMessage(secretstorageexception.getError());
                    return null;
                }
                catch (Exception exception1)
                {
                    exception1.printStackTrace();
                    error = new APIError();
                    error.setCode("500");
                    error.setApiMessage(exception1.getMessage());
                    return null;
                }
                if (m_count < 2)
                {
                    return doInBackground(avoid);
                } else
                {
                    error = new APIError();
                    error.setCode("500");
                    return null;
                }
            } else
            {
                error = new APIError();
                error.setCode("500");
                return null;
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            error = new APIError();
            error.setCode("500");
            return null;
        }
        return null;
        obj = parse(m_Response);
        return obj;
    }

    public int getStatusCode()
    {
        return m_client.getStatusCode();
    }

    protected void onPostExecute(Object obj)
    {
label0:
        {
            if (m_progDialog != null && m_progDialog.isShowing())
            {
                m_progDialog.dismiss();
            }
            if (m_listener != null)
            {
                if (!isCancelled())
                {
                    break label0;
                }
                m_listener.onCanceled();
            }
            return;
        }
        if (obj != null || error == null)
        {
            m_listener.onSuccess(obj);
            return;
        }
        if (TransitUtil.isEmpty(error.getMessage()) && !TransitUtil.isEmpty(sMsgErr))
        {
            if (error == null)
            {
                error = new APIError();
            }
            error.setMessage(sMsgErr);
        }
        if (TransitUtil.isEmpty(error.getCode()))
        {
            error.setCode("500");
        }
        m_listener.onError(error);
    }

    protected void onPreExecute()
    {
        m_count = 0;
        if (m_showDialog)
        {
            String s = m_context.getString(0x7f0d04a1);
            m_progDialog = new ProgressDialog(m_context);
            m_progDialog.setCustomTitle((new CustomDialogTitle(m_context, m_context.getString(0x7f0d04aa), 0)).setIconInfo());
            m_progDialog.setMessage(s);
            m_progDialog.setIndeterminate(true);
            m_progDialog.setCancelable(true);
            m_progDialog.show();
            m_progDialog.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

                final AuthApiBase this$0;

                public void onCancel(DialogInterface dialoginterface)
                {
                    if (m_listener != null)
                    {
                        m_listener.onCanceled();
                    }
                }

            
            {
                this$0 = AuthApiBase.this;
                super();
            }
            });
        }
    }

    protected abstract Object parse(String s);

    protected abstract void parseError(String s);

    protected void parseYJDNError(String s, String s1)
    {
        if (!TextUtils.isEmpty(s)) goto _L2; else goto _L1
_L1:
        JSONObject jsonobject;
        return;
_L2:
        JSONObject jsonobject1;
        if (TransitUtil.isEmpty((jsonobject = new JSONObject(s)).toString()) || TransitUtil.isEmpty((jsonobject1 = jsonobject.optJSONObject("Error")).toString())) goto _L1; else goto _L3
_L3:
        error = new APIError();
        error.setApiMessage(jsonobject1.optString("Message"));
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
        return;
    }

    public abstract void requestAsync(boolean flag);

    public void setCharset(String s)
    {
        m_client.setCharset(s);
    }

    public void setContentType(String s)
    {
        m_client.setContentType(s);
    }

    public void setDialogDisplay(boolean flag)
    {
        m_showDialog = flag;
    }

    public void setHeader(String s, String s1)
    {
        m_client.setHeader(s, s1);
    }

    public void setParameter(String s, String s1)
    {
label0:
        {
            if (!StringUtil.isEmpty(s1))
            {
                if (m_httpMethod.equals("POST"))
                {
                    break label0;
                }
                m_client.setParameter(s, s1);
            }
            return;
        }
        parameters.put(s, s1);
    }

    public void setParameterAuto(String s, String s1)
    {
        if (!StringUtil.isEmpty(s1))
        {
            m_client.setParameter(s, s1);
        }
    }

    public void setPostBody(String s)
    {
        post = s;
        m_client.setPostBody(s);
    }

    public void setUri(String s, String s1)
    {
        m_httpMethod = s1;
        m_url = s;
    }

}
