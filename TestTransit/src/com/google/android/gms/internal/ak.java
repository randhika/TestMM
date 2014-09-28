// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import com.google.ads.mediation.admob.AdMobAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            at, aw, ai

public class ak
{

    public static final ak md = new ak();

    private ak()
    {
    }

    public static ak aF()
    {
        return md;
    }

    public ai a(Context context, at at1)
    {
        Date date = at1.getBirthday();
        long l;
        String s;
        int i;
        Set set;
        java.util.List list;
        boolean flag;
        int j;
        android.location.Location location;
        android.os.Bundle bundle;
        boolean flag1;
        String s1;
        com.google.android.gms.ads.search.SearchAdRequest searchadrequest;
        aw aw1;
        if (date != null)
        {
            l = date.getTime();
        } else
        {
            l = -1L;
        }
        s = at1.getContentUrl();
        i = at1.getGender();
        set = at1.getKeywords();
        if (!set.isEmpty())
        {
            list = Collections.unmodifiableList(new ArrayList(set));
        } else
        {
            list = null;
        }
        flag = at1.isTestDevice(context);
        j = at1.aK();
        location = at1.getLocation();
        bundle = at1.getNetworkExtrasBundle(com/google/ads/mediation/admob/AdMobAdapter);
        flag1 = at1.getManualImpressionsEnabled();
        s1 = at1.getPublisherProvidedId();
        searchadrequest = at1.aH();
        if (searchadrequest != null)
        {
            aw1 = new aw(searchadrequest);
        } else
        {
            aw1 = null;
        }
        return new ai(4, l, bundle, i, list, flag, j, flag1, s1, aw1, location, s, at1.aJ());
    }

}
