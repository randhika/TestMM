// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api.data;

import java.io.Serializable;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api.data:
//            DiainfoData

public static class 
    implements Serializable
{

    public static final String STATUS_CHANGE = "000200010006";
    public static final String STATUS_DELAY = "000200010003";
    public static final String STATUS_ETC = "000200010099";
    public static final String STATUS_NORMAL = "000200010005";
    public static final String STATUS_RESTART = "000200010002";
    public static final String STATUS_STATUS = "000200010004";
    public static final String STATUS_STOP = "000200010001";
    private static final long serialVersionUID = 0x3675acca21bdd03eL;
    private String Message;
    private String UpdateDate;
    private String cause;
    private String status;
    private String statusCode;

    public String getCause()
    {
        return cause;
    }

    public String getMessage()
    {
        return Message;
    }

    public String getStatus()
    {
        return status;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public String getUpdateDate()
    {
        return UpdateDate;
    }

    public void setCause(String s)
    {
        cause = s;
    }

    public void setMessage(String s)
    {
        Message = s;
    }

    public void setStatus(String s)
    {
        status = s;
    }

    public void setStatusCode(String s)
    {
        statusCode = s;
    }

    public void setUpdateDate(String s)
    {
        UpdateDate = s;
    }

    public ()
    {
    }
}
