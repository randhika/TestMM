// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.Context;
import android.os.Bundle;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            AuthApiBase

public class PushApiBase extends AuthApiBase
{

    private AuthApiBase.AuthApiListener listener;
    public String m_Consumeruri;
    private Context objContext;

    public PushApiBase(Context context, BearerToken bearertoken, AuthApiBase.AuthApiListener authapilistener)
    {
        super(context, bearertoken, authapilistener);
        objContext = context;
        listener = authapilistener;
    }

    protected Bundle parse(String s)
    {
        return null;
    }

    protected volatile Object parse(String s)
    {
        return parse(s);
    }

    protected void parseError(String s)
    {
        JSONObject jsonobject;
        jsonobject = new JSONObject(s);
        if (TransitUtil.isEmpty(jsonobject.toString()))
        {
            return;
        }
        try
        {
            JSONObject jsonobject1 = jsonobject.optJSONObject("Error");
            if (!TransitUtil.isEmpty(jsonobject1.toString()))
            {
                error = new APIError();
                error.setApiMessage(jsonobject1.optString("Message"));
                error.setCode(jsonobject1.optString("Code"));
                error.setMessage(sMsgErr);
                return;
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return;
    }

    public void requestAsync(boolean flag)
    {
        setWaitMsg(flag);
        setParameterAuto("prod_id", objContext.getString(0x7f0d0047));
        setParameterAuto("output", "json");
        setParameterAuto("consumeruri_type", "android");
        setHeader("Host", m_context.getString(0x7f0d0046));
        setParameterAuto("consumeruri", m_Consumeruri);
    }

    public void setErrorMsg(String s)
    {
        sMsgErr = s;
    }

    public void setListener(AuthApiBase.AuthApiListener authapilistener)
    {
        listener = authapilistener;
    }

    public void setMsg(String s)
    {
        sMsg = s;
    }

    public void setWaitMsg(boolean flag)
    {
        setDialogDisplay(flag);
    }
}
