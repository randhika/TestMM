// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.cast;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.gi;
import com.google.android.gms.internal.hk;
import java.util.Locale;

// Referenced classes of package com.google.android.gms.cast:
//            c

public class LaunchOptions
    implements SafeParcelable
{
    public static final class Builder
    {

        private LaunchOptions As;

        public LaunchOptions build()
        {
            return As;
        }

        public Builder setLocale(Locale locale)
        {
            As.setLanguage(gi.b(locale));
            return this;
        }

        public Builder setRelaunchIfRunning(boolean flag)
        {
            As.setRelaunchIfRunning(flag);
            return this;
        }

        public Builder()
        {
            As = new LaunchOptions();
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new c();
    private boolean Aq;
    private String Ar;
    private final int xM;

    public LaunchOptions()
    {
        this(1, false, gi.b(Locale.getDefault()));
    }

    LaunchOptions(int i, boolean flag, String s)
    {
        xM = i;
        Aq = flag;
        Ar = s;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof LaunchOptions))
            {
                return false;
            }
            LaunchOptions launchoptions = (LaunchOptions)obj;
            if (Aq != launchoptions.Aq || !gi.a(Ar, launchoptions.Ar))
            {
                return false;
            }
        }
        return true;
    }

    public String getLanguage()
    {
        return Ar;
    }

    public boolean getRelaunchIfRunning()
    {
        return Aq;
    }

    int getVersionCode()
    {
        return xM;
    }

    public int hashCode()
    {
        Object aobj[] = new Object[2];
        aobj[0] = Boolean.valueOf(Aq);
        aobj[1] = Ar;
        return hk.hashCode(aobj);
    }

    public void setLanguage(String s)
    {
        Ar = s;
    }

    public void setRelaunchIfRunning(boolean flag)
    {
        Aq = flag;
    }

    public String toString()
    {
        Object aobj[] = new Object[2];
        aobj[0] = Boolean.valueOf(Aq);
        aobj[1] = Ar;
        return String.format("LaunchOptions(relaunchIfRunning=%b, language=%s)", aobj);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        c.a(this, parcel, i);
    }

}
