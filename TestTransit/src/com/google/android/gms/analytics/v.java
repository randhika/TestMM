// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.analytics;

import android.content.Context;

// Referenced classes of package com.google.android.gms.analytics:
//            k, w, aa, j

class v extends k
{
    private static class a
        implements k.a
    {

        private final w wi = new w();

        public void a(String s, int i)
        {
            if ("ga_dispatchPeriod".equals(s))
            {
                wi.wk = i;
                return;
            } else
            {
                aa.D((new StringBuilder()).append("int configuration name not recognized:  ").append(s).toString());
                return;
            }
        }

        public void c(String s, String s1)
        {
        }

        public void c(String s, boolean flag)
        {
            if ("ga_dryRun".equals(s))
            {
                w w1 = wi;
                int i;
                if (flag)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                w1.wl = i;
                return;
            } else
            {
                aa.D((new StringBuilder()).append("bool configuration name not recognized:  ").append(s).toString());
                return;
            }
        }

        public j cB()
        {
            return cX();
        }

        public w cX()
        {
            return wi;
        }

        public void d(String s, String s1)
        {
            if ("ga_appName".equals(s))
            {
                wi.tC = s1;
                return;
            }
            if ("ga_appVersion".equals(s))
            {
                wi.tD = s1;
                return;
            }
            if ("ga_logLevel".equals(s))
            {
                wi.wj = s1;
                return;
            } else
            {
                aa.D((new StringBuilder()).append("string configuration name not recognized:  ").append(s).toString());
                return;
            }
        }

        public a()
        {
        }
    }


    public v(Context context)
    {
        super(context, new a());
    }
}
