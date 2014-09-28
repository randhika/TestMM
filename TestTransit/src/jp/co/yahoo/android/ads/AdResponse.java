// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;

import android.widget.RelativeLayout;

public class AdResponse
{

    private String a;
    private String b;
    private String c;
    private RelativeLayout d;
    private String e;

    public AdResponse()
    {
        a = "";
        b = "";
        c = "";
        d = null;
        e = null;
    }

    public RelativeLayout getAdlayout()
    {
        return d;
    }

    public String getCode()
    {
        return a;
    }

    public String getMessage()
    {
        return b;
    }

    public String getRslog()
    {
        return c;
    }

    public String getXmlString()
    {
        return e;
    }

    public void setAdlayout(RelativeLayout relativelayout)
    {
        d = relativelayout;
    }

    public void setCode(String s)
    {
        a = s;
    }

    public void setMessage(String s)
    {
        b = s;
    }

    public void setRslog(String s)
    {
        c = s;
    }

    public void setXmlString(String s)
    {
        e = s;
    }
}
