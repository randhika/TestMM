// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.ads;


// Referenced classes of package jp.co.yahoo.android.ads:
//            h, CreateAdLayoutDisplay

public class a
{

    final CreateAdLayoutDisplay a;

    public void getAdWebViewMessage(String s)
    {
        h.a(3, (new StringBuilder()).append("JavascriptInterface.getAdWebViewMessage(): returnValue=").append(s).toString());
        if ("ok".equals(s))
        {
            CreateAdLayoutDisplay.t(a);
        }
    }

    public (CreateAdLayoutDisplay createadlayoutdisplay)
    {
        a = createadlayoutdisplay;
        super();
    }
}
