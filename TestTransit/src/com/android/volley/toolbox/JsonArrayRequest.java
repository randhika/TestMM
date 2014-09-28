// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;

// Referenced classes of package com.android.volley.toolbox:
//            JsonRequest, HttpHeaderParser

public class JsonArrayRequest extends JsonRequest
{

    public JsonArrayRequest(String s, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(0, s, null, listener, errorlistener);
    }

    protected Response parseNetworkResponse(NetworkResponse networkresponse)
    {
        Response response;
        try
        {
            response = Response.success(new JSONArray(new String(networkresponse.data, HttpHeaderParser.parseCharset(networkresponse.headers))), HttpHeaderParser.parseCacheHeaders(networkresponse));
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            return Response.error(new ParseError(unsupportedencodingexception));
        }
        catch (JSONException jsonexception)
        {
            return Response.error(new ParseError(jsonexception));
        }
        return response;
    }
}
