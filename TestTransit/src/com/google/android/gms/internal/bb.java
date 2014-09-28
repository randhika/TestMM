// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.android.gms.internal:
//            bh, bc, ex, eu, 
//            l, k, es, ev, 
//            cf, g

public final class bb
{

    public static final bc mT = new bc() {

        public void b(ex ex, Map map)
        {
        }

    };
    public static final bc mU = new bc() {

        public void b(ex ex1, Map map)
        {
            String s = (String)map.get("urls");
            if (TextUtils.isEmpty(s))
            {
                eu.D("URLs missing in canOpenURLs GMSG.");
                return;
            }
            String as[] = s.split(",");
            HashMap hashmap = new HashMap();
            PackageManager packagemanager = ex1.getContext().getPackageManager();
            int i = as.length;
            int j = 0;
            while (j < i) 
            {
                String s1 = as[j];
                String as1[] = s1.split(";", 2);
                String s2 = as1[0].trim();
                String s3;
                boolean flag;
                if (as1.length > 1)
                {
                    s3 = as1[1].trim();
                } else
                {
                    s3 = "android.intent.action.VIEW";
                }
                if (packagemanager.resolveActivity(new Intent(s3, Uri.parse(s2)), 0x10000) != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                hashmap.put(s1, Boolean.valueOf(flag));
                j++;
            }
            ex1.a("openableURLs", hashmap);
        }

    };
    public static final bc mV = new bc() {

        public void b(ex ex1, Map map)
        {
            String s;
            Uri uri;
            s = (String)map.get("u");
            if (s == null)
            {
                eu.D("URL missing from click GMSG.");
                return;
            }
            uri = Uri.parse(s);
            k k1 = ex1.cc();
            if (k1 == null) goto _L2; else goto _L1
_L1:
            if (!k1.b(uri)) goto _L2; else goto _L3
_L3:
            Uri uri2 = k1.a(uri, ex1.getContext());
            Uri uri1 = uri2;
_L5:
            String s1 = uri1.toString();
            (new es(ex1.getContext(), ex1.cd().sw, s1)).start();
            return;
            l l1;
            l1;
            eu.D((new StringBuilder()).append("Unable to append parameter to URL: ").append(s).toString());
_L2:
            uri1 = uri;
            if (true) goto _L5; else goto _L4
_L4:
        }

    };
    public static final bc mW = new bc() {

        public void b(ex ex1, Map map)
        {
            cf cf1 = ex1.ca();
            if (cf1 == null)
            {
                eu.D("A GMSG tried to close something that wasn't an overlay.");
                return;
            } else
            {
                cf1.close();
                return;
            }
        }

    };
    public static final bc mX = new bc() {

        public void b(ex ex1, Map map)
        {
            cf cf1 = ex1.ca();
            if (cf1 == null)
            {
                eu.D("A GMSG tried to use a custom close button on something that wasn't an overlay.");
                return;
            } else
            {
                cf1.j("1".equals(map.get("custom_close")));
                return;
            }
        }

    };
    public static final bc mY = new bc() {

        public void b(ex ex1, Map map)
        {
            String s = (String)map.get("u");
            if (s == null)
            {
                eu.D("URL missing from httpTrack GMSG.");
                return;
            } else
            {
                (new es(ex1.getContext(), ex1.cd().sw, s)).start();
                return;
            }
        }

    };
    public static final bc mZ = new bc() {

        public void b(ex ex, Map map)
        {
            eu.B((new StringBuilder()).append("Received log message: ").append((String)map.get("string")).toString());
        }

    };
    public static final bc na = new bc() {

        public void b(ex ex1, Map map)
        {
            String s = (String)map.get("tx");
            String s1 = (String)map.get("ty");
            String s2 = (String)map.get("td");
            int i;
            int j;
            int l;
            k k1;
            try
            {
                i = Integer.parseInt(s);
                j = Integer.parseInt(s1);
                l = Integer.parseInt(s2);
                k1 = ex1.cc();
            }
            catch (NumberFormatException numberformatexception)
            {
                eu.D("Could not parse touch parameters from gmsg.");
                return;
            }
            if (k1 == null)
            {
                break MISSING_BLOCK_LABEL_85;
            }
            k1.z().a(i, j, l);
        }

    };
    public static final bc nb = new bh();

}
