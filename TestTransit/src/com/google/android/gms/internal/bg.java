// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

// Referenced classes of package com.google.android.gms.internal:
//            bc, eo, eu, ex, 
//            ey, bd, ce

public final class bg
    implements bc
{

    private final bd nd;

    public bg(bd bd1)
    {
        nd = bd1;
    }

    private static boolean a(Map map)
    {
        return "1".equals(map.get("custom_close"));
    }

    private static int b(Map map)
    {
        String s = (String)map.get("o");
        if (s != null)
        {
            if ("p".equalsIgnoreCase(s))
            {
                return eo.bS();
            }
            if ("l".equalsIgnoreCase(s))
            {
                return eo.bR();
            }
        }
        return -1;
    }

    public void b(ex ex1, Map map)
    {
        String s = (String)map.get("a");
        if (s == null)
        {
            eu.D("Action missing from an open GMSG.");
        } else
        {
            ey ey1 = ex1.cb();
            if ("expand".equalsIgnoreCase(s))
            {
                if (ex1.ce())
                {
                    eu.D("Cannot expand WebView that is already expanded.");
                    return;
                } else
                {
                    ey1.a(a(map), b(map));
                    return;
                }
            }
            if ("webapp".equalsIgnoreCase(s))
            {
                String s3 = (String)map.get("u");
                if (s3 != null)
                {
                    ey1.a(a(map), b(map), s3);
                    return;
                } else
                {
                    ey1.a(a(map), b(map), (String)map.get("html"), (String)map.get("baseurl"));
                    return;
                }
            }
            if ("in_app_purchase".equalsIgnoreCase(s))
            {
                String s1 = (String)map.get("product_id");
                String s2 = (String)map.get("report_urls");
                if (nd != null)
                {
                    if (s2 != null && !s2.isEmpty())
                    {
                        String as[] = s2.split(" ");
                        nd.a(s1, new ArrayList(Arrays.asList(as)));
                        return;
                    } else
                    {
                        nd.a(s1, new ArrayList());
                        return;
                    }
                }
            } else
            {
                ey1.a(new ce((String)map.get("i"), (String)map.get("u"), (String)map.get("m"), (String)map.get("p"), (String)map.get("c"), (String)map.get("f"), (String)map.get("e")));
                return;
            }
        }
    }
}
