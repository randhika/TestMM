// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.metadata;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hm;

// Referenced classes of package com.google.android.gms.drive.metadata:
//            c

public class CustomPropertyKey
    implements SafeParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new c();
    final String JL;
    final int JM;
    final int xM;

    CustomPropertyKey(int i, String s, int j)
    {
        boolean flag = true;
        super();
        xM = i;
        hm.b(s, "key");
        if (j != 0 && j != flag)
        {
            flag = false;
        }
        hm.a(flag, "visibility must be either PUBLIC or PRIVATE");
        JL = s;
        JM = j;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if (obj != null)
        {
            if (obj == this)
            {
                return flag;
            }
            if (obj instanceof CustomPropertyKey)
            {
                CustomPropertyKey custompropertykey = (CustomPropertyKey)obj;
                if (!custompropertykey.getKey().equals(JL) || custompropertykey.getVisibility() != JM)
                {
                    flag = false;
                }
                return flag;
            }
        }
        return false;
    }

    public String getKey()
    {
        return JL;
    }

    public int getVisibility()
    {
        return JM;
    }

    public int hashCode()
    {
        return (new StringBuilder()).append(JL).append(JM).toString().hashCode();
    }

    public String toString()
    {
        return (new StringBuilder()).append("CustomPropertyKey(").append(JL).append(",").append(JM).append(")").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        c.a(this, parcel, i);
    }

}
