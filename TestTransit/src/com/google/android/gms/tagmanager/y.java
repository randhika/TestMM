// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import android.content.Context;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

// Referenced classes of package com.google.android.gms.tagmanager:
//            aq, as, cv, cf, 
//            bh, ar

class y
    implements aq
{

    private static y afu;
    private static final Object tq = new Object();
    private cf aeJ;
    private String afv;
    private String afw;
    private ar afx;

    private y(Context context)
    {
        this(((ar) (as.P(context))), ((cf) (new cv())));
    }

    y(ar ar1, cf cf1)
    {
        afx = ar1;
        aeJ = cf1;
    }

    public static aq N(Context context)
    {
        y y1;
        synchronized (tq)
        {
            if (afu == null)
            {
                afu = new y(context);
            }
            y1 = afu;
        }
        return y1;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean bR(String s)
    {
        if (!aeJ._mthdo())
        {
            bh.D("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
            return false;
        }
        if (afv != null && afw != null)
        {
            try
            {
                s = (new StringBuilder()).append(afv).append("?").append(afw).append("=").append(URLEncoder.encode(s, "UTF-8")).toString();
                bh.C((new StringBuilder()).append("Sending wrapped url hit: ").append(s).toString());
            }
            catch (UnsupportedEncodingException unsupportedencodingexception)
            {
                bh.c("Error wrapping URL for testing.", unsupportedencodingexception);
                return false;
            }
        }
        afx.bU(s);
        return true;
    }

}
