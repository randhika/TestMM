// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import java.util.Map;

// Referenced classes of package com.google.android.gms.tagmanager:
//            ay

class d
    implements DataLayer.b
{

    private final Context kO;

    public d(Context context)
    {
        kO = context;
    }

    public void x(Map map)
    {
        Object obj = map.get("gtm.url");
        if (obj != null) goto _L2; else goto _L1
_L1:
        Object obj2 = map.get("gtm");
        if (obj2 == null || !(obj2 instanceof Map)) goto _L2; else goto _L3
_L3:
        Object obj1 = ((Map)obj2).get("url");
_L8:
        if (obj1 != null && (obj1 instanceof String)) goto _L5; else goto _L4
_L4:
        String s;
        return;
_L5:
        if ((s = Uri.parse((String)obj1).getQueryParameter("referrer")) == null) goto _L4; else goto _L6
_L6:
        ay.f(kO, s);
        return;
_L2:
        obj1 = obj;
        if (true) goto _L8; else goto _L7
_L7:
    }
}
