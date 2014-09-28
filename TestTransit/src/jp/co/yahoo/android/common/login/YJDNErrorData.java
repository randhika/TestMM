// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.login;

import java.util.Map;

public class YJDNErrorData
{

    private String mErrorCode;
    private String mErrorMsg;
    private boolean mIsLoginExpired;
    private Map mOptinalData;

    public YJDNErrorData()
    {
        mErrorMsg = "";
        mErrorCode = "";
        mIsLoginExpired = false;
    }

    public String getErrorCode()
    {
        return mErrorCode;
    }

    public String getErrorMsg()
    {
        return mErrorMsg;
    }

    public Map getOptinalData()
    {
        return mOptinalData;
    }

    public boolean isLoginExpired()
    {
        return mIsLoginExpired;
    }

    public void setErrorCode(String s)
    {
        mErrorCode = s;
    }

    public void setErrorMsg(String s)
    {
        mErrorMsg = s;
    }

    public void setIsLoginExpired(boolean flag)
    {
        mIsLoginExpired = flag;
    }

    public void setOptinalData(Map map)
    {
        mOptinalData = map;
    }
}
