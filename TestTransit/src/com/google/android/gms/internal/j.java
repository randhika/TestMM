// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

// Referenced classes of package com.google.android.gms.internal:
//            i, e, p, m, 
//            n

public class j extends i
{
    class a
    {

        private String kd;
        private boolean ke;
        final j kf;

        public String getId()
        {
            return kd;
        }

        public boolean isLimitAdTrackingEnabled()
        {
            return ke;
        }

        public a(String s, boolean flag)
        {
            kf = j.this;
            super();
            kd = s;
            ke = flag;
        }
    }


    protected j(Context context, m m1, n n)
    {
        super(context, m1, n);
    }

    public static j a(String s, Context context)
    {
        e e1 = new e();
        a(s, context, ((m) (e1)));
        return new j(context, e1, new p(239));
    }

    protected void c(Context context)
    {
        super.c(context);
        a a2 = i(context);
        long l;
        String s;
        if (a2.isLimitAdTrackingEnabled())
        {
            l = 1L;
        } else
        {
            l = 0L;
        }
        a(28, l);
        s = a2.getId();
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        a(26, 5L);
        a(24, s);
        return;
        GooglePlayServicesNotAvailableException googleplayservicesnotavailableexception;
        googleplayservicesnotavailableexception;
        try
        {
            a(24, f(context));
            return;
        }
        catch (IOException ioexception)
        {
            return;
        }
        catch (i.a a1)
        {
            return;
        }
    }

    protected void d(Context context)
    {
        long l = 1L;
        super.c(context);
        GooglePlayServicesNotAvailableException googleplayservicesnotavailableexception;
        IOException ioexception;
        a a2;
        String s;
        try
        {
            a(24, f(context));
        }
        catch (i.a a1) { }
        catch (IOException ioexception1)
        {
            return;
        }
        a2 = i(context);
        if (!a2.isLimitAdTrackingEnabled())
        {
            l = 0L;
        }
        a(28, l);
        s = a2.getId();
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_86;
        }
        a(30, s);
        return;
        ioexception;
        a(28, 1L);
        return;
        googleplayservicesnotavailableexception;
        return;
    }

    a i(Context context)
        throws IOException, GooglePlayServicesNotAvailableException
    {
        int k = 0;
        com.google.android.gms.ads.identifier.AdvertisingIdClient.Info info;
        String s;
        try
        {
            info = AdvertisingIdClient.getAdvertisingIdInfo(context);
        }
        catch (GooglePlayServicesRepairableException googleplayservicesrepairableexception)
        {
            throw new IOException(googleplayservicesrepairableexception);
        }
        s = info.getId();
        String s1;
        if (s != null && s.matches("^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$"))
        {
            byte abyte0[] = new byte[16];
            int l = 0;
            for (; k < s.length(); k += 2)
            {
                if (k == 8 || k == 13 || k == 18 || k == 23)
                {
                    k++;
                }
                abyte0[l] = (byte)((Character.digit(s.charAt(k), 16) << 4) + Character.digit(s.charAt(k + 1), 16));
                l++;
            }

            s1 = jQ.a(abyte0, true);
        } else
        {
            s1 = s;
        }
        return new a(s1, info.isLimitAdTrackingEnabled());
    }
}
