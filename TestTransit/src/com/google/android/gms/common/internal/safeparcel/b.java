// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class b
{

    public static int C(Parcel parcel)
    {
        return E(parcel, 20293);
    }

    private static int E(Parcel parcel, int i)
    {
        parcel.writeInt(0xffff0000 | i);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    private static void F(Parcel parcel, int i)
    {
        int j = parcel.dataPosition();
        int k = j - i;
        parcel.setDataPosition(i - 4);
        parcel.writeInt(k);
        parcel.setDataPosition(j);
    }

    public static void G(Parcel parcel, int i)
    {
        F(parcel, i);
    }

    public static void a(Parcel parcel, int i, byte byte0)
    {
        b(parcel, i, 4);
        parcel.writeInt(byte0);
    }

    public static void a(Parcel parcel, int i, double d)
    {
        b(parcel, i, 8);
        parcel.writeDouble(d);
    }

    public static void a(Parcel parcel, int i, float f)
    {
        b(parcel, i, 4);
        parcel.writeFloat(f);
    }

    public static void a(Parcel parcel, int i, long l)
    {
        b(parcel, i, 8);
        parcel.writeLong(l);
    }

    public static void a(Parcel parcel, int i, Bundle bundle, boolean flag)
    {
        if (bundle == null)
        {
            if (flag)
            {
                b(parcel, i, 0);
            }
            return;
        } else
        {
            int j = E(parcel, i);
            parcel.writeBundle(bundle);
            F(parcel, j);
            return;
        }
    }

    public static void a(Parcel parcel, int i, IBinder ibinder, boolean flag)
    {
        if (ibinder == null)
        {
            if (flag)
            {
                b(parcel, i, 0);
            }
            return;
        } else
        {
            int j = E(parcel, i);
            parcel.writeStrongBinder(ibinder);
            F(parcel, j);
            return;
        }
    }

    public static void a(Parcel parcel, int i, Parcel parcel1, boolean flag)
    {
        if (parcel1 == null)
        {
            if (flag)
            {
                b(parcel, i, 0);
            }
            return;
        } else
        {
            int j = E(parcel, i);
            parcel.appendFrom(parcel1, 0, parcel1.dataSize());
            F(parcel, j);
            return;
        }
    }

    public static void a(Parcel parcel, int i, Parcelable parcelable, int j, boolean flag)
    {
        if (parcelable == null)
        {
            if (flag)
            {
                b(parcel, i, 0);
            }
            return;
        } else
        {
            int k = E(parcel, i);
            parcelable.writeToParcel(parcel, j);
            F(parcel, k);
            return;
        }
    }

    public static void a(Parcel parcel, int i, Boolean boolean1, boolean flag)
    {
        if (boolean1 == null)
        {
            if (flag)
            {
                b(parcel, i, 0);
            }
            return;
        }
        b(parcel, i, 4);
        boolean flag1 = boolean1.booleanValue();
        int j = 0;
        if (flag1)
        {
            j = 1;
        }
        parcel.writeInt(j);
    }

    public static void a(Parcel parcel, int i, Integer integer, boolean flag)
    {
        if (integer == null)
        {
            if (flag)
            {
                b(parcel, i, 0);
            }
            return;
        } else
        {
            b(parcel, i, 4);
            parcel.writeInt(integer.intValue());
            return;
        }
    }

    public static void a(Parcel parcel, int i, Long long1, boolean flag)
    {
        if (long1 == null)
        {
            if (flag)
            {
                b(parcel, i, 0);
            }
            return;
        } else
        {
            b(parcel, i, 8);
            parcel.writeLong(long1.longValue());
            return;
        }
    }

    public static void a(Parcel parcel, int i, String s, boolean flag)
    {
        if (s == null)
        {
            if (flag)
            {
                b(parcel, i, 0);
            }
            return;
        } else
        {
            int j = E(parcel, i);
            parcel.writeString(s);
            F(parcel, j);
            return;
        }
    }

    public static void a(Parcel parcel, int i, List list, boolean flag)
    {
        if (list == null)
        {
            if (flag)
            {
                b(parcel, i, 0);
            }
            return;
        } else
        {
            int j = E(parcel, i);
            parcel.writeStringList(list);
            F(parcel, j);
            return;
        }
    }

    public static void a(Parcel parcel, int i, short word0)
    {
        b(parcel, i, 4);
        parcel.writeInt(word0);
    }

    public static void a(Parcel parcel, int i, boolean flag)
    {
        b(parcel, i, 4);
        int j;
        if (flag)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
    }

    public static void a(Parcel parcel, int i, byte abyte0[], boolean flag)
    {
        if (abyte0 == null)
        {
            if (flag)
            {
                b(parcel, i, 0);
            }
            return;
        } else
        {
            int j = E(parcel, i);
            parcel.writeByteArray(abyte0);
            F(parcel, j);
            return;
        }
    }

    public static void a(Parcel parcel, int i, int ai[], boolean flag)
    {
        if (ai == null)
        {
            if (flag)
            {
                b(parcel, i, 0);
            }
            return;
        } else
        {
            int j = E(parcel, i);
            parcel.writeIntArray(ai);
            F(parcel, j);
            return;
        }
    }

    public static void a(Parcel parcel, int i, Parcelable aparcelable[], int j, boolean flag)
    {
        if (aparcelable == null)
        {
            if (flag)
            {
                b(parcel, i, 0);
            }
            return;
        }
        int k = E(parcel, i);
        int l = aparcelable.length;
        parcel.writeInt(l);
        int i1 = 0;
        while (i1 < l) 
        {
            Parcelable parcelable = aparcelable[i1];
            if (parcelable == null)
            {
                parcel.writeInt(0);
            } else
            {
                a(parcel, parcelable, j);
            }
            i1++;
        }
        F(parcel, k);
    }

    public static void a(Parcel parcel, int i, String as[], boolean flag)
    {
        if (as == null)
        {
            if (flag)
            {
                b(parcel, i, 0);
            }
            return;
        } else
        {
            int j = E(parcel, i);
            parcel.writeStringArray(as);
            F(parcel, j);
            return;
        }
    }

    public static void a(Parcel parcel, int i, byte abyte0[][], boolean flag)
    {
        int j = 0;
        if (abyte0 == null)
        {
            if (flag)
            {
                b(parcel, i, 0);
            }
            return;
        }
        int k = E(parcel, i);
        int l = abyte0.length;
        parcel.writeInt(l);
        for (; j < l; j++)
        {
            parcel.writeByteArray(abyte0[j]);
        }

        F(parcel, k);
    }

    private static void a(Parcel parcel, Parcelable parcelable, int i)
    {
        int j = parcel.dataPosition();
        parcel.writeInt(1);
        int k = parcel.dataPosition();
        parcelable.writeToParcel(parcel, i);
        int l = parcel.dataPosition();
        parcel.setDataPosition(j);
        parcel.writeInt(l - k);
        parcel.setDataPosition(l);
    }

    private static void b(Parcel parcel, int i, int j)
    {
        if (j >= 65535)
        {
            parcel.writeInt(0xffff0000 | i);
            parcel.writeInt(j);
            return;
        } else
        {
            parcel.writeInt(i | j << 16);
            return;
        }
    }

    public static void b(Parcel parcel, int i, List list, boolean flag)
    {
        if (list == null)
        {
            if (flag)
            {
                b(parcel, i, 0);
            }
            return;
        }
        int j = E(parcel, i);
        int k = list.size();
        parcel.writeInt(k);
        int l = 0;
        while (l < k) 
        {
            Parcelable parcelable = (Parcelable)list.get(l);
            if (parcelable == null)
            {
                parcel.writeInt(0);
            } else
            {
                a(parcel, parcelable, 0);
            }
            l++;
        }
        F(parcel, j);
    }

    public static void c(Parcel parcel, int i, int j)
    {
        b(parcel, i, 4);
        parcel.writeInt(j);
    }

    public static void c(Parcel parcel, int i, List list, boolean flag)
    {
        if (list == null)
        {
            if (flag)
            {
                b(parcel, i, 0);
            }
            return;
        } else
        {
            int j = E(parcel, i);
            parcel.writeList(list);
            F(parcel, j);
            return;
        }
    }
}
