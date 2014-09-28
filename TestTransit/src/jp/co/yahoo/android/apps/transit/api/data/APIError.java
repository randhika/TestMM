// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api.data;

import java.io.Serializable;

public class APIError
    implements Serializable
{

    private static final long serialVersionUID = 0x600d3873528c1a7L;
    private String apimessage;
    private String code;
    private String message;

    public APIError()
    {
    }

    public String getApiMessage()
    {
        return apimessage;
    }

    public String getCode()
    {
        return code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setApiMessage(String s)
    {
        apimessage = s;
    }

    public void setCode(String s)
    {
        code = s;
    }

    public void setMessage(String s)
    {
        message = s;
    }
}
