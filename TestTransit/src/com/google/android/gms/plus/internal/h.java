// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hk;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.plus.internal:
//            j, PlusCommonExtras

public class h
    implements SafeParcelable
{

    public static final j CREATOR = new j();
    private final String abT[];
    private final String abU[];
    private final String abV[];
    private final String abW;
    private final String abX;
    private final String abY;
    private final String abZ;
    private final PlusCommonExtras aca;
    private final int xM;
    private final String yQ;

    h(int i, String s, String as[], String as1[], String as2[], String s1, String s2, 
            String s3, String s4, PlusCommonExtras pluscommonextras)
    {
        xM = i;
        yQ = s;
        abT = as;
        abU = as1;
        abV = as2;
        abW = s1;
        abX = s2;
        abY = s3;
        abZ = s4;
        aca = pluscommonextras;
    }

    public h(String s, String as[], String as1[], String as2[], String s1, String s2, String s3, 
            PlusCommonExtras pluscommonextras)
    {
        xM = 1;
        yQ = s;
        abT = as;
        abU = as1;
        abV = as2;
        abW = s1;
        abX = s2;
        abY = s3;
        abZ = null;
        aca = pluscommonextras;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        h h1;
        if (obj instanceof h)
        {
            if (xM == (h1 = (h)obj).xM && hk.equal(yQ, h1.yQ) && Arrays.equals(abT, h1.abT) && Arrays.equals(abU, h1.abU) && Arrays.equals(abV, h1.abV) && hk.equal(abW, h1.abW) && hk.equal(abX, h1.abX) && hk.equal(abY, h1.abY) && hk.equal(abZ, h1.abZ) && hk.equal(aca, h1.aca))
            {
                return true;
            }
        }
        return false;
    }

    public String getAccountName()
    {
        return yQ;
    }

    public int getVersionCode()
    {
        return xM;
    }

    public int hashCode()
    {
        Object aobj[] = new Object[10];
        aobj[0] = Integer.valueOf(xM);
        aobj[1] = yQ;
        aobj[2] = abT;
        aobj[3] = abU;
        aobj[4] = abV;
        aobj[5] = abW;
        aobj[6] = abX;
        aobj[7] = abY;
        aobj[8] = abZ;
        aobj[9] = aca;
        return hk.hashCode(aobj);
    }

    public String[] jZ()
    {
        return abT;
    }

    public String[] ka()
    {
        return abU;
    }

    public String[] kb()
    {
        return abV;
    }

    public String kc()
    {
        return abW;
    }

    public String kd()
    {
        return abX;
    }

    public String ke()
    {
        return abY;
    }

    public String kf()
    {
        return abZ;
    }

    public PlusCommonExtras kg()
    {
        return aca;
    }

    public Bundle kh()
    {
        Bundle bundle = new Bundle();
        bundle.setClassLoader(com/google/android/gms/plus/internal/PlusCommonExtras.getClassLoader());
        aca.n(bundle);
        return bundle;
    }

    public String toString()
    {
        return hk.e(this).a("versionCode", Integer.valueOf(xM)).a("accountName", yQ).a("requestedScopes", abT).a("visibleActivities", abU).a("requiredFeatures", abV).a("packageNameForAuth", abW).a("callingPackageName", abX).a("applicationName", abY).a("extra", aca.toString()).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        j.a(this, parcel, i);
    }

}
