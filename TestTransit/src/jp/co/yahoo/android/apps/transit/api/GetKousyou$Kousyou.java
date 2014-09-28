// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import java.io.Serializable;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            GetKousyou

class this._cls0
    implements Serializable
{

    private static final long serialVersionUID = 0x6b1387e8b91b6efaL;
    private String attention;
    private String text;
    final GetKousyou this$0;

    public String getAttention()
    {
        return attention;
    }

    public String getText()
    {
        return text;
    }

    public void setAttention(String s)
    {
        attention = s;
    }

    public void setText(String s)
    {
        text = s;
    }

    ()
    {
        this$0 = GetKousyou.this;
        super();
    }
}
