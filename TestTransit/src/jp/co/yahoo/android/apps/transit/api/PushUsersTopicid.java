// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.Context;
import android.os.Bundle;
import java.util.HashMap;
import jp.co.yahoo.android.apps.transit.api.data.APIError;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            PushApiBase

public class PushUsersTopicid extends PushApiBase
{

    public int m_Result;
    public int m_Start;
    public String m_UserId;
    public HashMap results;

    public PushUsersTopicid(Context context, BearerToken bearertoken, AuthApiBase.AuthApiListener authapilistener)
    {
        super(context, bearertoken, authapilistener);
        m_Start = 1;
        m_Result = 10;
        setUri(context.getString(0x7f0d004a), "GET");
        setParameter("userid_type", "yid");
    }

    public HashMap getResults()
    {
        return results;
    }

    protected Bundle parse(String s)
    {
        JSONObject jsonobject;
        JSONArray jsonarray;
        HashMap hashmap;
        int i;
        try
        {
            jsonobject = new JSONObject(s);
        }
        catch (Exception exception)
        {
            error = new APIError();
            error.setCode("500");
            error.setMessage(m_context.getString(0x7f0d0112));
            return null;
        }
        jsonarray = jsonobject.optJSONArray("Result");
        hashmap = new HashMap();
        i = 0;
label0:
        do
        {
label1:
            {
                if (i >= jsonarray.length())
                {
                    break label0;
                }
                JSONObject jsonobject1;
                boolean flag;
                try
                {
                    jsonobject1 = jsonarray.getJSONObject(i);
                }
                catch (JSONException jsonexception)
                {
                    break label1;
                }
                if ("1".equals(jsonobject1.optString("subflag")))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                hashmap.put(jsonobject1.optString("topic_id"), Boolean.valueOf(flag));
            }
            i++;
        } while (true);
        Bundle bundle = new Bundle();
        bundle.putSerializable("result", hashmap);
        return bundle;
    }

    protected volatile Object parse(String s)
    {
        return parse(s);
    }

    protected void parseError(String s)
    {
        super.parseError(s);
    }

    public void requestAsync(boolean flag)
    {
        super.requestAsync(flag);
        setParameter("userid", m_UserId);
        setParameter("start", Integer.toString(m_Start));
        setParameter("results", Integer.toString(m_Result));
        super.execute(new Void[0]);
    }
}
