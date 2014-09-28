// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.common.StringUtil;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            PushApiBase

public class PushSubscribe extends PushApiBase
{

    public boolean m_Subflag;
    public ArrayList m_Topicid;
    public String m_Userid;

    public PushSubscribe(Context context, BearerToken bearertoken, AuthApiBase.AuthApiListener authapilistener)
    {
        super(context, bearertoken, authapilistener);
        setUri(context.getString(0x7f0d0048), "POST");
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
        super.parseError(s);
    }

    public void requestAsync(boolean flag)
    {
        super.requestAsync(flag);
        setContentType("aapplication/x-www-form-urlencoded");
        setCharset("UTF-8");
        setParameterAuto("userid_type", "yid");
        setParameterAuto("userid", m_Userid);
        setParameterAuto("topic_id", StringUtil.join(m_Topicid, ","));
        if (m_Subflag)
        {
            setParameterAuto("subflag", "1");
        } else
        {
            setParameterAuto("subflag", "0");
        }
        super.execute(new Void[0]);
    }
}
