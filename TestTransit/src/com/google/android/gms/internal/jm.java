// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            jn, hk

public final class jm
    implements SafeParcelable
{

    public static final jn CREATOR = new jn();
    final List Wc;
    private final String Wd;
    private final boolean We;
    final List Wf;
    private final String Wg;
    final List Wh;
    private final Set Wi;
    private final Set Wj;
    private final Set Wk;
    final int xM;

    jm(int i, List list, String s, boolean flag, List list1, String s1, List list2)
    {
        xM = i;
        List list3;
        List list4;
        List list5;
        if (list == null)
        {
            list3 = Collections.emptyList();
        } else
        {
            list3 = Collections.unmodifiableList(list);
        }
        Wc = list3;
        if (s == null)
        {
            s = "";
        }
        Wd = s;
        We = flag;
        if (list1 == null)
        {
            list4 = Collections.emptyList();
        } else
        {
            list4 = Collections.unmodifiableList(list1);
        }
        Wf = list4;
        if (s1 == null)
        {
            s1 = "";
        }
        Wg = s1;
        if (list2 == null)
        {
            list5 = Collections.emptyList();
        } else
        {
            list5 = Collections.unmodifiableList(list2);
        }
        Wh = list5;
        Wi = c(Wc);
        Wj = c(Wf);
        Wk = c(Wh);
    }

    private static Set c(List list)
    {
        if (list.isEmpty())
        {
            return Collections.emptySet();
        } else
        {
            return Collections.unmodifiableSet(new HashSet(list));
        }
    }

    public int describeContents()
    {
        jn _tmp = CREATOR;
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof jm))
            {
                return false;
            }
            jm jm1 = (jm)obj;
            if (!Wi.equals(jm1.Wi) || We != jm1.We || !Wg.equals(jm1.Wg) || !Wj.equals(jm1.Wj) || !Wk.equals(jm1.Wk))
            {
                return false;
            }
        }
        return true;
    }

    public int hashCode()
    {
        Object aobj[] = new Object[5];
        aobj[0] = Wi;
        aobj[1] = Boolean.valueOf(We);
        aobj[2] = Wj;
        aobj[3] = Wg;
        aobj[4] = Wk;
        return hk.hashCode(aobj);
    }

    public String jg()
    {
        return Wd;
    }

    public boolean jh()
    {
        return We;
    }

    public String ji()
    {
        return Wg;
    }

    public String toString()
    {
        return hk.e(this).a("types", Wi).a("placeIds", Wk).a("requireOpenNow", Boolean.valueOf(We)).a("userAccountName", Wg).a("requestedUserDataTypes", Wj).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        jn _tmp = CREATOR;
        jn.a(this, parcel, i);
    }

}
