// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.Context;
import android.os.Bundle;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            PushApiBase

public class PushUpdateToken extends PushApiBase
{

    public String m_Oldconsumeruri;

    public PushUpdateToken(Context context, BearerToken bearertoken, AuthApiBase.AuthApiListener authapilistener)
    {
        super(context, bearertoken, authapilistener);
        setUri(context.getString(0x7f0d0049), "PUT");
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
        setParameterAuto("old_consumeruri", m_Oldconsumeruri);
        super.execute(new Void[0]);
    }
}
