// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.internal:
//            am, et

public final class al
    implements SafeParcelable
{

    public static final am CREATOR = new am();
    public final int height;
    public final int heightPixels;
    public final String me;
    public final boolean mf;
    public final al mg[];
    public final int versionCode;
    public final int width;
    public final int widthPixels;

    public al()
    {
        this(2, "interstitial_mb", 0, 0, true, 0, 0, null);
    }

    al(int i, String s, int j, int k, boolean flag, int l, int i1, 
            al aal[])
    {
        versionCode = i;
        me = s;
        height = j;
        heightPixels = k;
        mf = flag;
        width = l;
        widthPixels = i1;
        mg = aal;
    }

    public al(Context context, AdSize adsize)
    {
        this(context, new AdSize[] {
            adsize
        });
    }

    public al(Context context, AdSize aadsize[])
    {
        int i = 0;
        super();
        AdSize adsize = aadsize[0];
        versionCode = 2;
        mf = false;
        width = adsize.getWidth();
        height = adsize.getHeight();
        boolean flag;
        boolean flag1;
        DisplayMetrics displaymetrics;
        int k;
        int l;
        if (width == -1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (height == -2)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        displaymetrics = context.getResources().getDisplayMetrics();
        if (flag)
        {
            widthPixels = a(displaymetrics);
            k = (int)((float)widthPixels / displaymetrics.density);
        } else
        {
            int j = width;
            widthPixels = com.google.android.gms.internal.et.a(displaymetrics, width);
            k = j;
        }
        if (flag1)
        {
            l = c(displaymetrics);
        } else
        {
            l = height;
        }
        heightPixels = com.google.android.gms.internal.et.a(displaymetrics, l);
        if (flag || flag1)
        {
            me = (new StringBuilder()).append(k).append("x").append(l).append("_as").toString();
        } else
        {
            me = adsize.toString();
        }
        if (aadsize.length > 1)
        {
            mg = new al[aadsize.length];
            for (; i < aadsize.length; i++)
            {
                mg[i] = new al(context, aadsize[i]);
            }

        } else
        {
            mg = null;
        }
    }

    public al(al al1, al aal[])
    {
        this(2, al1.me, al1.height, al1.heightPixels, al1.mf, al1.width, al1.widthPixels, aal);
    }

    public static int a(DisplayMetrics displaymetrics)
    {
        return displaymetrics.widthPixels;
    }

    public static int b(DisplayMetrics displaymetrics)
    {
        return (int)((float)c(displaymetrics) * displaymetrics.density);
    }

    private static int c(DisplayMetrics displaymetrics)
    {
        int i = (int)((float)displaymetrics.heightPixels / displaymetrics.density);
        if (i <= 400)
        {
            return 32;
        }
        return i > 720 ? 90 : 50;
    }

    public AdSize aG()
    {
        return com.google.android.gms.ads.a.a(width, height, me);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        com.google.android.gms.internal.am.a(this, parcel, i);
    }

}
