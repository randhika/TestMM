// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Xml;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.viewparts.CustomDialogTitle;
import jp.co.yahoo.android.common.HttpConfig;
import jp.co.yahoo.android.common.login.YJDNErrorData;
import jp.co.yahoo.android.common.login.YLoginServiceListener;
import jp.co.yahoo.android.common.login.YLoginServiceManager;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlSerializer;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            YolpApiBase, ApiBase

public class RegistSearch extends YolpApiBase
    implements YLoginServiceListener
{
    public static interface YLoginApiListener
    {

        public abstract void onLoginApiCanceled(int i, String s);

        public abstract void onLoginApiError(int i, String s);

        public abstract void onLoginApiSuccess(int i, Bundle bundle);

        public abstract void onLoginServiceConnected();
    }


    private boolean bRetry;
    private boolean bWait;
    private YLoginApiListener listener;
    protected Handler mainHandler;
    private Context objContext;
    private YLoginServiceManager objLogin;
    private ArrayList postData;
    protected ProgressDialog progressDialog;
    private Bundle results;
    private int resultscount;
    private String searchType;
    private String type;

    public RegistSearch(Context context, YLoginApiListener yloginapilistener)
    {
        super(context, null);
        resultscount = 50;
        bRetry = false;
        bWait = true;
        objContext = context;
        listener = yloginapilistener;
        super.sMsg = context.getString(0x7f0d04a5);
        super.sMsgErr = context.getString(0x7f0d0107);
        searchType = context.getString(0x7f0d023e);
        type = context.getString(0x7f0d057e);
        setParameter("output", "json");
        objLogin = new YLoginServiceManager(this, getContext().getString(0x7f0d033a));
        objLogin.bindService(getContext());
    }

    private void analyzeRail(JSONArray jsonarray)
    {
        results = new Bundle();
        JSONArray jsonarray1 = getFeature();
        int i = 0;
        while (i < jsonarray1.length()) 
        {
            try
            {
                JSONObject jsonobject = jsonarray1.optJSONObject(i).optJSONObject("Property").optJSONObject("Detail");
                DiainfoData diainfodata = new DiainfoData();
                diainfodata.setRailName(jsonarray1.optJSONObject(i).optString("Name"));
                diainfodata.setRailRangeCode(jsonobject.optString("RailRangeCode"));
                diainfodata.setRailcode(jsonobject.optString("RailCode"));
                results.putSerializable(String.valueOf(i), diainfodata);
            }
            catch (Exception exception) { }
            i++;
        }
    }

    private void analyzeSta(JSONArray jsonarray)
    {
        results = new Bundle();
        JSONArray jsonarray1 = getFeature();
        int i = 0;
        while (i < jsonarray1.length()) 
        {
            try
            {
                StationData stationdata = new StationData();
                stationdata.setName(jsonarray1.getJSONObject(i).getString("Name"));
                results.putSerializable(String.valueOf(i), stationdata);
            }
            catch (JSONException jsonexception) { }
            i++;
        }
    }

    private void changeMsg()
    {
        if (searchType.equals(objContext.getString(0x7f0d023e)))
        {
            if (method == ApiBase.METHOD_POST)
            {
                super.sMsgErr = objContext.getString(0x7f0d010f);
                super.sMsg = objContext.getString(0x7f0d04a7);
                return;
            } else
            {
                super.sMsgErr = objContext.getString(0x7f0d0107);
                super.sMsg = objContext.getString(0x7f0d04a5);
                return;
            }
        }
        if (method == ApiBase.METHOD_POST)
        {
            super.sMsgErr = objContext.getString(0x7f0d010f);
            super.sMsg = objContext.getString(0x7f0d04a7);
            return;
        } else
        {
            super.sMsgErr = objContext.getString(0x7f0d0107);
            super.sMsg = objContext.getString(0x7f0d04a8);
            return;
        }
    }

    private String getErrrMsg(String s)
    {
        if (s.equals("40001"))
        {
            if (getSearchType() == objContext.getString(0x7f0d023e))
            {
                return objContext.getString(0x7f0d010e);
            } else
            {
                return objContext.getString(0x7f0d010d);
            }
        }
        if (s.equals("40002"))
        {
            if (getSearchType() == objContext.getString(0x7f0d023e))
            {
                return objContext.getString(0x7f0d0111);
            } else
            {
                return objContext.getString(0x7f0d0110);
            }
        } else
        {
            return super.sMsgErr;
        }
    }

    private String makeBody()
    {
        XmlSerializer xmlserializer;
        StringWriter stringwriter;
        xmlserializer = Xml.newSerializer();
        stringwriter = new StringWriter();
        Iterator iterator;
        xmlserializer.setOutput(stringwriter);
        xmlserializer.startDocument("UTF-8", Boolean.valueOf(true));
        xmlserializer.startTag("", "YDF");
        if (postData == null || postData.size() <= 0)
        {
            break MISSING_BLOCK_LABEL_298;
        }
        iterator = postData.iterator();
_L2:
        Bundle bundle;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_308;
        }
        bundle = (Bundle)iterator.next();
        xmlserializer.startTag("", "Feature");
        if (searchType.equals(objContext.getString(0x7f0d023e)))
        {
            break; /* Loop/switch isn't completed */
        }
        xmlserializer.startTag("", "Property");
        xmlserializer.startTag("", "Detail");
        xmlserializer.startTag("", "RailCode");
        xmlserializer.text(bundle.getString("RailCode"));
        xmlserializer.endTag("", "RailCode");
        xmlserializer.startTag("", "RailRangeCode");
        xmlserializer.text(bundle.getString("RailRangeCode"));
        xmlserializer.endTag("", "RailRangeCode");
        xmlserializer.endTag("", "Detail");
        xmlserializer.endTag("", "Property");
_L3:
        xmlserializer.endTag("", "Feature");
        Exception exception;
        if (true) goto _L2; else goto _L1
_L1:
        try
        {
            xmlserializer.startTag("", "Name");
            xmlserializer.text(bundle.getString("Name"));
            xmlserializer.endTag("", "Name");
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
          goto _L3
        if (true) goto _L2; else goto _L4
_L4:
        xmlserializer.text("\n");
        String s;
        xmlserializer.endTag("", "YDF");
        xmlserializer.endDocument();
        s = stringwriter.toString();
        return s;
    }

    protected void analyzeFeature(JSONArray jsonarray)
    {
        while (jsonarray == null || method == METHOD_POST) 
        {
            return;
        }
        if (searchType.equals(objContext.getString(0x7f0d023e)))
        {
            analyzeSta(jsonarray);
            return;
        } else
        {
            analyzeRail(jsonarray);
            return;
        }
    }

    public void bindService()
    {
        objLogin.bindService(getContext());
    }

    public void closeDialog()
    {
        if (progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
    }

    public void destroy()
    {
        if (objLogin != null)
        {
            objLogin.unbindService(objContext);
        }
    }

    public void execute()
    {
        if (progressDialog != null && progressDialog.isShowing())
        {
            return;
        }
        setParameter(objContext.getString(0x7f0d01da), type);
        setParameter("results", resultscount);
        if (objLogin == null)
        {
            objLogin = new YLoginServiceManager(this, getContext().getString(0x7f0d033a));
            objLogin.bindService(getContext());
        }
        if (!objLogin.isLogin())
        {
            objLogin = new YLoginServiceManager(this, getContext().getString(0x7f0d033a));
            objLogin.bindService(getContext());
            if (bRetry)
            {
                bRetry = false;
            } else
            {
                bRetry = true;
            }
            objLogin.startLoginActivity(objContext);
            return;
        }
        progressDialog = new ProgressDialog(objContext);
        if (bWait)
        {
            progressDialog.setCustomTitle((new CustomDialogTitle(context, context.getString(0x7f0d04aa), 0)).setIconInfo());
            progressDialog.setMessage(super.sMsg);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(true);
            if (!((Activity)objContext).isFinishing())
            {
                progressDialog.show();
            }
            progressDialog.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() );
        }
        objLogin.registerCallback();
        String s;
        HttpConfig httpconfig;
        if (searchType.equals(objContext.getString(0x7f0d023e)))
        {
            setUri((new StringBuilder()).append(getContext().getString(0x7f0d003f)).append(objContext.getString(0x7f0d0041)).toString());
        } else
        {
            setUri((new StringBuilder()).append(getContext().getString(0x7f0d003f)).append(objContext.getString(0x7f0d0040)).toString());
        }
        s = Uri.decode(uriBuilder.build().toString());
        if (METHOD_GET == method)
        {
            httpconfig = new HttpConfig(1);
        } else
        {
            httpconfig = new HttpConfig(2);
            String s1 = makeBody();
            if (TransitUtil.isEmpty(s1))
            {
                progressDialog.dismiss();
                listener.onLoginApiError(2, super.sMsgErr);
                return;
            }
            httpconfig.setRequestBodyString(s1, "UTF-8");
        }
        objLogin.requestYJDN(s, null, httpconfig);
    }

    protected String[] getCredential()
    {
        YLoginServiceManager yloginservicemanager = objLogin;
        String as[] = null;
        if (yloginservicemanager != null)
        {
            boolean flag = objLogin.hasCredential();
            as = null;
            if (flag)
            {
                as = objLogin.getCredential();
            }
        }
        return as;
    }

    public ArrayList getPostData()
    {
        return postData;
    }

    public String getSearchType()
    {
        return searchType;
    }

    public String getType()
    {
        return type;
    }

    public boolean isLoigin()
    {
        if (objLogin != null)
        {
            return objLogin.isLogin();
        } else
        {
            return false;
        }
    }

    public void login()
    {
        if (objLogin == null)
        {
            objLogin = new YLoginServiceManager(this, getContext().getString(0x7f0d033a));
            objLogin.bindService(getContext());
        }
        objLogin.startLoginActivityForResult((Activity)context);
    }

    public void loginForResult(Activity activity)
    {
        if (objLogin == null)
        {
            objLogin = new YLoginServiceManager(this, getContext().getString(0x7f0d033a));
            objLogin.bindService(getContext());
        }
        objLogin.startLoginActivityForResult(activity);
    }

    public void onLogin()
    {
        if (bRetry && isLoigin())
        {
            execute();
        }
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
        objLogin.registerCallback();
        listener.onLoginServiceConnected();
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
        if (progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
        String s1 = getErrrMsg(yjdnerrordata.getErrorCode());
        listener.onLoginApiError(method, s1);
    }

    public void onYJDNDownloadFailedAtConverter(String s, boolean flag, String as[], Object aobj[])
    {
        if (progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
        if (flag)
        {
            if (objLogin != null)
            {
                objLogin.startLoginActivity(getContext());
            }
            return;
        } else
        {
            listener.onLoginApiError(method, super.sMsgErr);
            return;
        }
    }

    public void onYJDNDownloaded(byte abyte0[], Header aheader[], int i, String s, Object obj)
    {
        String s1;
        if (progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
        s1 = new String(abyte0);
        JSONObject jsonobject = new JSONObject(s1);
        JSONObject jsonobject1 = jsonobject;
_L3:
        if (METHOD_GET != method) goto _L2; else goto _L1
_L1:
        analyze(jsonobject1);
_L5:
        if (listener != null)
        {
            listener.onLoginApiSuccess(method, results);
        }
        return;
        Exception exception1;
        exception1;
        APIError apierror = new APIError();
        apierror.setCode("500");
        apierror.setMessage(getContext().getString(0x7f0d0112));
        setError(apierror);
        jsonobject1 = null;
          goto _L3
_L2:
        if (jsonobject1 == null || listener == null) goto _L5; else goto _L4
_L4:
        if (!jsonobject1.has("Code")) goto _L5; else goto _L6
_L6:
        String s2 = getErrrMsg(jsonobject1.optString("Code"));
        listener.onLoginApiError(method, s2);
        return;
        Exception exception;
        exception;
          goto _L5
    }

    public void onYJDNDownloadedInBackground(byte abyte0[], Header aheader[], int i, String s, Object obj)
    {
    }

    public void onYJDNHttpError(byte abyte0[], Header aheader[], int i, boolean flag, String s, Object obj)
    {
        if (progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
        listener.onLoginApiError(method, super.sMsgErr);
    }

    public void onYJDNResponsed(byte abyte0[], Header aheader[], int i, String s, Object obj)
    {
    }

    public void onYJDNResponsedInBackground(byte abyte0[], Header aheader[], int i, String s, Object obj)
    {
    }

    public void registerCallback()
    {
        if (objLogin != null)
        {
            objLogin.registerCallback();
        }
    }

    public void resetParam()
    {
        uriBuilder = new Builder();
        results = null;
        setMethod(ApiBase.METHOD_GET);
        postData = new ArrayList();
        setParameter("output", "json");
    }

    public void setListener(YLoginApiListener yloginapilistener)
    {
        listener = yloginapilistener;
    }

    public void setMethod(int i)
    {
        setMethod(i);
        changeMsg();
    }

    public void setPostData(ArrayList arraylist)
    {
        postData = arraylist;
    }

    public void setSearchType(String s)
    {
        searchType = s;
        changeMsg();
    }

    public void setStationName(String s)
    {
        if (postData == null)
        {
            postData = new ArrayList();
        }
        Bundle bundle = new Bundle();
        bundle.putString("Name", s);
        postData.add(bundle);
    }

    public void setType(String s)
    {
        type = s;
    }

    public void setWaitMsg(boolean flag)
    {
        bWait = flag;
    }

    public void unregisterCallback()
    {
        if (objLogin != null)
        {
            objLogin.unregisterCallback();
        }
    }



}
