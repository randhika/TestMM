// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.net.Uri;
import android.util.Xml;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package jp.co.yahoo.android.common:
//            YHttpClient, YLog, YDomUtils, YIoUtils

public class YHttpGet extends YHttpClient
{
    public static final class YSaxStopException extends SAXException
    {

        public YSaxStopException()
        {
        }
    }


    private static final String TAG = jp/co/yahoo/android/common/YHttpGet.getSimpleName();
    private int TIMEOUT;
    private final List _headers = new LinkedList();
    private String _redirectedUrl;
    private HttpResponse _response;

    public YHttpGet()
    {
        TIMEOUT = 30000;
        _response = null;
        _redirectedUrl = null;
        setTimeout(TIMEOUT);
    }

    public void addHeader(String s, String s1)
    {
        _headers.add(new BasicHeader(s, s1));
    }

    public HttpResponse get(Uri uri)
    {
        HttpGet httpget;
        httpget = new HttpGet(uri.toString());
        for (Iterator iterator = _headers.iterator(); iterator.hasNext(); httpget.addHeader((Header)iterator.next())) { }
        BasicHttpContext basichttpcontext;
        int i;
        basichttpcontext = new BasicHttpContext();
        _response = execute(httpget, basichttpcontext);
        i = _response.getStatusLine().getStatusCode();
        if (i == 200 || i == 302)
        {
            break MISSING_BLOCK_LABEL_142;
        }
        String s2 = TAG;
        Object aobj2[] = new Object[2];
        aobj2[0] = _response.getStatusLine().toString();
        aobj2[1] = uri;
        YLog.e(s2, "HttpGet error: %s(%s)", aobj2);
        return null;
        HttpResponse httpresponse;
        HttpUriRequest httpurirequest = (HttpUriRequest)basichttpcontext.getAttribute("http.request");
        HttpHost httphost = (HttpHost)basichttpcontext.getAttribute("http.target_host");
        _redirectedUrl = (new StringBuilder()).append(httphost.toURI()).append(httpurirequest.getURI()).toString();
        httpresponse = _response;
        return httpresponse;
        ClientProtocolException clientprotocolexception;
        clientprotocolexception;
        String s1 = TAG;
        Object aobj1[] = new Object[3];
        aobj1[0] = clientprotocolexception.getClass().getSimpleName();
        aobj1[1] = clientprotocolexception.getMessage();
        aobj1[2] = uri;
        YLog.e(s1, "%s : %s : %s", aobj1);
        clientprotocolexception.printStackTrace();
_L2:
        return null;
        IOException ioexception;
        ioexception;
        String s = TAG;
        Object aobj[] = new Object[3];
        aobj[0] = ioexception.getClass().getSimpleName();
        aobj[1] = ioexception.getMessage();
        aobj[2] = uri;
        YLog.e(s, "%s : %s : %s", aobj);
        ioexception.printStackTrace();
        if (true) goto _L2; else goto _L1
_L1:
    }

    public HttpResponse get(String s)
    {
        return get(Uri.parse(s));
    }

    public JSONObject getJson(Uri uri)
    {
        String s;
        JSONObject jsonobject;
        try
        {
            s = getString(uri);
        }
        catch (JSONException jsonexception)
        {
            YLog.e(TAG, jsonexception.getMessage());
            jsonexception.printStackTrace();
            return null;
        }
        if (s == null)
        {
            return null;
        }
        jsonobject = new JSONObject(s);
        return jsonobject;
    }

    public JSONObject getJson(String s)
    {
        return getJson(Uri.parse(s));
    }

    public JSONArray getJsonArray(Uri uri)
    {
        String s;
        JSONArray jsonarray;
        try
        {
            s = getString(uri);
        }
        catch (JSONException jsonexception)
        {
            YLog.e(TAG, jsonexception.getMessage());
            jsonexception.printStackTrace();
            return null;
        }
        if (s == null)
        {
            return null;
        }
        jsonarray = new JSONArray(s);
        return jsonarray;
    }

    public JSONArray getJsonArray(String s)
    {
        return getJsonArray(Uri.parse(s));
    }

    public String getRedirectedUrl()
    {
        return _redirectedUrl;
    }

    public HttpResponse getResponse()
    {
        return _response;
    }

    public String getString(Uri uri)
    {
        _response = get(uri);
        if (_response == null)
        {
            return null;
        }
        if (_response.getStatusLine().getStatusCode() == 200)
        {
            break MISSING_BLOCK_LABEL_98;
        }
        String s1 = TAG;
        Object aobj[] = new Object[2];
        aobj[0] = _response.getStatusLine().toString();
        aobj[1] = uri;
        YLog.e(s1, "HttpGet error: %s (%s)", aobj);
        ClientProtocolException clientprotocolexception;
        return null;
        String s;
        try
        {
            s = EntityUtils.toString(_response.getEntity());
        }
        // Misplaced declaration of an exception variable
        catch (ClientProtocolException clientprotocolexception)
        {
            YLog.e(TAG, clientprotocolexception.getMessage());
            clientprotocolexception.printStackTrace();
            return null;
        }
        catch (IOException ioexception)
        {
            YLog.e(TAG, ioexception.getMessage());
            ioexception.printStackTrace();
            return null;
        }
        return s;
    }

    public String getString(String s)
    {
        return getString(Uri.parse(s));
    }

    public Document getXmlDom(Uri uri)
    {
        _response = get(uri);
        if (_response == null)
        {
            return null;
        }
        Document document;
        try
        {
            document = YDomUtils.BUILDER_FACTORY.newDocumentBuilder().parse(_response.getEntity().getContent());
        }
        catch (IllegalStateException illegalstateexception)
        {
            YLog.e(TAG, illegalstateexception.getMessage());
            illegalstateexception.printStackTrace();
            return null;
        }
        catch (SAXException saxexception)
        {
            YLog.e(TAG, saxexception.getMessage());
            saxexception.printStackTrace();
            return null;
        }
        catch (IOException ioexception)
        {
            YLog.e(TAG, ioexception.getMessage());
            ioexception.printStackTrace();
            return null;
        }
        catch (ParserConfigurationException parserconfigurationexception)
        {
            YLog.e(TAG, parserconfigurationexception.getMessage());
            parserconfigurationexception.printStackTrace();
            return null;
        }
        return document;
    }

    public boolean getXmlPull(Uri uri, XmlPullParser xmlpullparser)
    {
        java.io.InputStream inputstream;
        for (_response = get(uri); _response == null || _response.getStatusLine().getStatusCode() != 200;)
        {
            return false;
        }

        inputstream = null;
        inputstream = _response.getEntity().getContent();
        xmlpullparser.setInput(inputstream, "utf-8");
        YIoUtils.close(inputstream);
        return true;
        IllegalStateException illegalstateexception;
        illegalstateexception;
        YLog.e(TAG, illegalstateexception.getMessage());
        illegalstateexception.printStackTrace();
        YIoUtils.close(inputstream);
        return false;
        IOException ioexception;
        ioexception;
        YLog.e(TAG, ioexception.getMessage());
        ioexception.printStackTrace();
        YIoUtils.close(inputstream);
        return false;
        XmlPullParserException xmlpullparserexception;
        xmlpullparserexception;
        YLog.e(TAG, xmlpullparserexception.getMessage());
        xmlpullparserexception.printStackTrace();
        YIoUtils.close(inputstream);
        return false;
        Exception exception;
        exception;
        YIoUtils.close(inputstream);
        throw exception;
    }

    public boolean getXmlPull(String s, XmlPullParser xmlpullparser)
    {
        return getXmlPull(Uri.parse(s), xmlpullparser);
    }

    public boolean getXmlSax(Uri uri, DefaultHandler defaulthandler)
    {
        java.io.InputStream inputstream;
        for (_response = get(uri); _response == null || _response.getStatusLine().getStatusCode() != 200;)
        {
            return false;
        }

        inputstream = null;
        inputstream = _response.getEntity().getContent();
        Xml.parse(inputstream, android.util.Xml.Encoding.UTF_8, defaulthandler);
        YIoUtils.close(inputstream);
        return true;
        YSaxStopException ysaxstopexception;
        ysaxstopexception;
        YIoUtils.close(inputstream);
        return true;
        IllegalStateException illegalstateexception;
        illegalstateexception;
        YLog.e(TAG, illegalstateexception.getMessage());
        illegalstateexception.printStackTrace();
        YIoUtils.close(inputstream);
        return false;
        SAXException saxexception;
        saxexception;
        YLog.e(TAG, saxexception.getMessage());
        saxexception.printStackTrace();
        YIoUtils.close(inputstream);
        return false;
        IOException ioexception;
        ioexception;
        YLog.e(TAG, ioexception.getMessage());
        ioexception.printStackTrace();
        YIoUtils.close(inputstream);
        return false;
        Exception exception;
        exception;
        YIoUtils.close(inputstream);
        throw exception;
    }

    public boolean getXmlSax(String s, DefaultHandler defaulthandler)
    {
        return getXmlSax(Uri.parse(s), defaulthandler);
    }

}
