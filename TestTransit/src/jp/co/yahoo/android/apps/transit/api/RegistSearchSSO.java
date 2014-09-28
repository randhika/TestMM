// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Xml;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import jp.co.yahoo.android.apps.transit.PushDiainfoService;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.api.data.DiainfoData;
import jp.co.yahoo.android.apps.transit.api.data.StationData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlSerializer;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            AuthApiBase

public class RegistSearchSSO extends AuthApiBase
{

    private AuthApiBase.AuthApiListener listener;
    protected Handler mainHandler;
    private Context objContext;
    private ArrayList postData;
    private Bundle results;
    private int resultscount;
    private String sMethod;
    private String searchType;
    private String type;

    public RegistSearchSSO(Context context, BearerToken bearertoken, AuthApiBase.AuthApiListener authapilistener)
    {
        super(context, bearertoken, authapilistener);
        resultscount = 50;
        sMethod = "GET";
        objContext = context;
        listener = authapilistener;
        super.sMsg = context.getString(0x7f0d04a5);
        super.sMsgErr = context.getString(0x7f0d0107);
        searchType = context.getString(0x7f0d023e);
        type = context.getString(0x7f0d057e);
        setParameter("output", "json");
    }

    private void analyzeRail(JSONArray jsonarray)
    {
        results = new Bundle();
        int i = 0;
        while (i < jsonarray.length()) 
        {
            try
            {
                JSONObject jsonobject = jsonarray.optJSONObject(i).optJSONObject("Property").optJSONObject("Detail");
                DiainfoData diainfodata = new DiainfoData();
                diainfodata.setRailName(jsonarray.optJSONObject(i).optString("Name"));
                diainfodata.setRailRangeCode(jsonobject.optString("RailRangeCode"));
                diainfodata.setRailcode(jsonobject.optString("RailCode"));
                results.putSerializable(String.valueOf(i), diainfodata);
            }
            catch (Exception exception) { }
            i++;
        }
        Intent intent = new Intent(objContext.getApplicationContext(), jp/co/yahoo/android/apps/transit/PushDiainfoService);
        intent.setAction("jp.co.yahoo.android.apps.transit.PushDiainfoService.ACTION_ON_GET_RAIL");
        intent.putExtra(objContext.getString(0x7f0d01d5), results);
        objContext.getApplicationContext().startService(intent);
    }

    private void analyzeSta(JSONArray jsonarray)
    {
        results = new Bundle();
        int i = 0;
        while (i < jsonarray.length()) 
        {
            try
            {
                StationData stationdata = new StationData();
                stationdata.setName(jsonarray.getJSONObject(i).getString("Name"));
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
            if (sMethod == "POST")
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
        if (sMethod == "POST")
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
            break MISSING_BLOCK_LABEL_310;
        }
        iterator = postData.iterator();
_L1:
        Bundle bundle;
        String s1;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_320;
        }
        bundle = (Bundle)iterator.next();
        xmlserializer.startTag("", "Feature");
        if (searchType.equals(objContext.getString(0x7f0d023e)))
        {
            break MISSING_BLOCK_LABEL_271;
        }
        xmlserializer.startTag("", "Property");
        xmlserializer.startTag("", "Detail");
        xmlserializer.startTag("", "RailCode");
        xmlserializer.text(bundle.getString("RailCode"));
        xmlserializer.endTag("", "RailCode");
        xmlserializer.startTag("", "RailRangeCode");
        s1 = bundle.getString("RailRangeCode");
        if (s1 == null)
        {
            s1 = "0";
        }
        xmlserializer.text(s1);
        xmlserializer.endTag("", "RailRangeCode");
        xmlserializer.endTag("", "Detail");
        xmlserializer.endTag("", "Property");
_L2:
        Exception exception;
        xmlserializer.endTag("", "Feature");
          goto _L1
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
          goto _L2
        xmlserializer.text("\n");
        String s;
        xmlserializer.endTag("", "YDF");
        xmlserializer.endDocument();
        s = stringwriter.toString();
        return s;
    }

    protected void analyzeFeature(JSONArray jsonarray)
    {
        while (jsonarray == null || sMethod == "POST") 
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

    protected boolean analyzePostResponse(String s)
    {
        if (s.indexOf("200") == -1)
        {
            if (s.indexOf("40001") != -1)
            {
                error = new APIError();
                error.setMessage(getErrrMsg("40001"));
                error.setCode("40001");
            } else
            if (s.indexOf("40002") != -1)
            {
                error = new APIError();
                error.setMessage(getErrrMsg("40002"));
                error.setCode("40002");
            } else
            {
                error = new APIError();
                error.setCode("500");
                error.setMessage(getErrrMsg("500"));
            }
        }
        return false;
    }

    protected boolean analyzeYDF(JSONObject jsonobject)
    {
        if (jsonobject == null)
        {
            error = new APIError();
            error.setCode("500");
            error.setMessage(m_context.getString(0x7f0d0102));
            return false;
        }
        if (jsonobject.optJSONObject("ResultInfo") == null)
        {
            analyzeYolpError(jsonobject);
            return false;
        }
        JSONArray jsonarray = jsonobject.optJSONArray("Feature");
        if (jsonarray == null && jsonobject.has("Feature"))
        {
            jsonarray = new JSONArray();
            jsonarray.put(jsonobject.optJSONObject("Feature"));
        }
        if (jsonarray == null)
        {
            return true;
        } else
        {
            analyzeFeature(jsonarray);
            return true;
        }
    }

    protected void analyzeYolpError(JSONObject jsonobject)
    {
        if (jsonobject != null && !jsonobject.isNull("Code"))
        {
            error = new APIError();
            error.setCode(jsonobject.optString("Code"));
            error.setApiMessage(jsonobject.optString("Message"));
            error.setMessage(sMsgErr);
        }
    }

    public void closeDialog()
    {
    }

    protected String[] getCredential()
    {
        return null;
    }

    public String getMethod()
    {
        return sMethod;
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

    protected Bundle parse(String s)
    {
        Log.d("response", s);
        if ("GET" != sMethod)
        {
            break MISSING_BLOCK_LABEL_89;
        }
        JSONObject jsonobject = new JSONObject(s);
        JSONObject jsonobject1 = jsonobject;
_L1:
        analyzeYDF(jsonobject1);
_L2:
        return results;
        Exception exception;
        exception;
        error = new APIError();
        error.setCode("500");
        error.setMessage(m_context.getString(0x7f0d0112));
        jsonobject1 = null;
          goto _L1
        analyzePostResponse(s);
          goto _L2
    }

    protected volatile Object parse(String s)
    {
        return parse(s);
    }

    protected void parseError(String s)
    {
    }

    public void requestAsync(boolean flag)
    {
        String s = m_context.getString(0x7f0d003f);
        setHeader("Host", "transit.yahooapis.jp");
        if (searchType.equals(objContext.getString(0x7f0d023e)))
        {
            setUri((new StringBuilder()).append(s).append(objContext.getString(0x7f0d0041)).toString(), sMethod);
        } else
        {
            setUri((new StringBuilder()).append(s).append(objContext.getString(0x7f0d0040)).toString(), sMethod);
        }
        setParameter(objContext.getString(0x7f0d01da), type);
        setParameter("results", Integer.toString(resultscount));
        setWaitMsg(flag);
        if ("POST".equals(sMethod))
        {
            String s1 = makeBody();
            if (TransitUtil.isEmpty(s1))
            {
                listener.onError(new APIError());
                return;
            }
            setPostBody(s1);
            setContentType("application/xml");
            setCharset("UTF-8");
        }
        super.execute(new Void[0]);
    }

    public void resetParam()
    {
        results = null;
        setUri("", "GET");
        postData = new ArrayList();
        setParameter("output", "json");
    }

    public void setErrorMsg(String s)
    {
        sMsgErr = s;
    }

    public void setListener(AuthApiBase.AuthApiListener authapilistener)
    {
        listener = authapilistener;
    }

    public void setMethod(String s)
    {
        sMethod = s;
        changeMsg();
    }

    public void setMsg(String s)
    {
        sMsg = s;
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
        setDialogDisplay(flag);
    }
}
