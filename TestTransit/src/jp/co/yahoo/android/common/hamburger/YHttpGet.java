// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import android.net.Uri;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.Header;
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

// Referenced classes of package jp.co.yahoo.android.common.hamburger:
//            YSimpleHttpClient, YHBGUtils

public class YHttpGet extends YSimpleHttpClient
{

    private static final int DEFAULT_TIMEOUT_MSEC = 30000;
    private int _code;
    private final List _headers = new LinkedList();
    private String _redirectedUrl;
    private HttpResponse _response;

    public YHttpGet()
    {
        _response = null;
        _code = 0;
        _redirectedUrl = null;
        setTimeout(30000);
    }

    public void addHeader(String s, String s1)
    {
        _headers.add(new BasicHeader(s, s1));
    }

    public HttpResponse get(Uri uri)
    {
        HttpGet httpget = new HttpGet(uri.toString());
        Iterator iterator = _headers.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                BasicHttpContext basichttpcontext;
                HttpUriRequest httpurirequest;
                HttpResponse httpresponse;
                try
                {
                    basichttpcontext = new BasicHttpContext();
                    _code = 0;
                    _response = execute(httpget, basichttpcontext);
                    _code = _response.getStatusLine().getStatusCode();
                    YHBGUtils.debug((new StringBuilder("HttpRequest#get code=")).append(_code).append(" ").append(uri).toString());
                    if (_code == 200 || _code == 302)
                    {
                        break;
                    }
                    YHBGUtils.error((new StringBuilder("ERROR: HttpGet#get: ")).append(_response.getStatusLine().toString()).append(" ").append(uri).toString());
                }
                catch (UnknownHostException unknownhostexception)
                {
                    YHBGUtils.error((new StringBuilder("ERROR: HttpResponse#get ")).append(unknownhostexception).toString());
                    unknownhostexception.printStackTrace();
                    return null;
                }
                catch (ClientProtocolException clientprotocolexception)
                {
                    YHBGUtils.error((new StringBuilder("ERROR: HttpResponse#get ")).append(clientprotocolexception).toString());
                    clientprotocolexception.printStackTrace();
                    return null;
                }
                catch (IOException ioexception)
                {
                    YHBGUtils.error((new StringBuilder("ERROR: HttpResponse#get ")).append(ioexception).toString());
                    ioexception.printStackTrace();
                    return null;
                }
                return null;
            }
            httpget.addHeader((Header)iterator.next());
        } while (true);
        httpurirequest = (HttpUriRequest)basichttpcontext.getAttribute("http.request");
        _redirectedUrl = (new StringBuilder(String.valueOf(((HttpHost)basichttpcontext.getAttribute("http.target_host")).toURI()))).append(httpurirequest.getURI()).toString();
        httpresponse = _response;
        return httpresponse;
    }

    public HttpResponse get(String s)
    {
        return get(Uri.parse(s));
    }

    public int getCode()
    {
        return _code;
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
            YHBGUtils.error(jsonexception.getMessage());
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
            YHBGUtils.error(jsonexception.getMessage());
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
            break MISSING_BLOCK_LABEL_108;
        }
        YHBGUtils.error((new StringBuilder("ERROR: HttpGet#get ")).append(_response.getStatusLine().toString()).append(" ").append(uri).toString());
        UnknownHostException unknownhostexception;
        return null;
        String s;
        try
        {
            s = EntityUtils.toString(_response.getEntity());
        }
        // Misplaced declaration of an exception variable
        catch (UnknownHostException unknownhostexception)
        {
            YHBGUtils.error((new StringBuilder("ERROR: HttpResponse#getString ")).append(unknownhostexception).toString());
            unknownhostexception.printStackTrace();
            return null;
        }
        catch (ClientProtocolException clientprotocolexception)
        {
            YHBGUtils.error((new StringBuilder("ERROR: HttpResponse#getString ")).append(clientprotocolexception).toString());
            clientprotocolexception.printStackTrace();
            return null;
        }
        catch (IOException ioexception)
        {
            YHBGUtils.error((new StringBuilder("ERROR: HttpResponse#getString ")).append(ioexception).toString());
            ioexception.printStackTrace();
            return null;
        }
        return s;
    }

    public String getString(String s)
    {
        return getString(Uri.parse(s));
    }
}
