// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class com.google.android.gms.common.internal.safeparcel.a
{
    public static class a extends RuntimeException
    {

        public a(String s1, Parcel parcel)
        {
            super((new StringBuilder()).append(s1).append(" Parcel: pos=").append(parcel.dataPosition()).append(" size=").append(parcel.dataSize()).toString());
        }
    }


    public static int A(Parcel parcel)
    {
        return parcel.readInt();
    }

    public static String[] A(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        } else
        {
            String as[] = parcel.createStringArray();
            parcel.setDataPosition(j1 + k1);
            return as;
        }
    }

    public static int B(Parcel parcel)
    {
        int i1 = A(parcel);
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (ar(i1) != 20293)
        {
            throw new a((new StringBuilder()).append("Expected object header. Got 0x").append(Integer.toHexString(i1)).toString(), parcel);
        }
        int l1 = k1 + j1;
        if (l1 < k1 || l1 > parcel.dataSize())
        {
            throw new a((new StringBuilder()).append("Size read is invalid start=").append(k1).append(" end=").append(l1).toString(), parcel);
        } else
        {
            return l1;
        }
    }

    public static ArrayList B(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        } else
        {
            ArrayList arraylist = parcel.createStringArrayList();
            parcel.setDataPosition(j1 + k1);
            return arraylist;
        }
    }

    public static Parcel C(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        } else
        {
            Parcel parcel1 = Parcel.obtain();
            parcel1.appendFrom(parcel, k1, j1);
            parcel.setDataPosition(j1 + k1);
            return parcel1;
        }
    }

    public static Parcel[] D(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        }
        int l1 = parcel.readInt();
        Parcel aparcel[] = new Parcel[l1];
        int i2 = 0;
        while (i2 < l1) 
        {
            int j2 = parcel.readInt();
            if (j2 != 0)
            {
                int k2 = parcel.dataPosition();
                Parcel parcel1 = Parcel.obtain();
                parcel1.appendFrom(parcel, k2, j2);
                aparcel[i2] = parcel1;
                parcel.setDataPosition(j2 + k2);
            } else
            {
                aparcel[i2] = null;
            }
            i2++;
        }
        parcel.setDataPosition(k1 + j1);
        return aparcel;
    }

    public static int a(Parcel parcel, int i1)
    {
        if ((i1 & 0xffff0000) != 0xffff0000)
        {
            return 0xffff & i1 >> 16;
        } else
        {
            return parcel.readInt();
        }
    }

    public static Parcelable a(Parcel parcel, int i1, android.os.Parcelable.Creator creator)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        } else
        {
            Parcelable parcelable = (Parcelable)creator.createFromParcel(parcel);
            parcel.setDataPosition(j1 + k1);
            return parcelable;
        }
    }

    private static void a(Parcel parcel, int i1, int j1)
    {
        int k1 = a(parcel, i1);
        if (k1 != j1)
        {
            throw new a((new StringBuilder()).append("Expected size ").append(j1).append(" got ").append(k1).append(" (0x").append(Integer.toHexString(k1)).append(")").toString(), parcel);
        } else
        {
            return;
        }
    }

    private static void a(Parcel parcel, int i1, int j1, int k1)
    {
        if (j1 != k1)
        {
            throw new a((new StringBuilder()).append("Expected size ").append(k1).append(" got ").append(j1).append(" (0x").append(Integer.toHexString(j1)).append(")").toString(), parcel);
        } else
        {
            return;
        }
    }

    public static void a(Parcel parcel, int i1, List list, ClassLoader classloader)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return;
        } else
        {
            parcel.readList(list, classloader);
            parcel.setDataPosition(j1 + k1);
            return;
        }
    }

    public static int ar(int i1)
    {
        return 0xffff & i1;
    }

    public static void b(Parcel parcel, int i1)
    {
        parcel.setDataPosition(a(parcel, i1) + parcel.dataPosition());
    }

    public static Object[] b(Parcel parcel, int i1, android.os.Parcelable.Creator creator)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        } else
        {
            Object aobj[] = parcel.createTypedArray(creator);
            parcel.setDataPosition(j1 + k1);
            return aobj;
        }
    }

    public static ArrayList c(Parcel parcel, int i1, android.os.Parcelable.Creator creator)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        } else
        {
            ArrayList arraylist = parcel.createTypedArrayList(creator);
            parcel.setDataPosition(j1 + k1);
            return arraylist;
        }
    }

    public static boolean c(Parcel parcel, int i1)
    {
        a(parcel, i1, 4);
        return parcel.readInt() != 0;
    }

    public static Boolean d(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        if (j1 == 0)
        {
            return null;
        }
        a(parcel, i1, j1, 4);
        boolean flag;
        if (parcel.readInt() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return Boolean.valueOf(flag);
    }

    public static byte e(Parcel parcel, int i1)
    {
        a(parcel, i1, 4);
        return (byte)parcel.readInt();
    }

    public static short f(Parcel parcel, int i1)
    {
        a(parcel, i1, 4);
        return (short)parcel.readInt();
    }

    public static int g(Parcel parcel, int i1)
    {
        a(parcel, i1, 4);
        return parcel.readInt();
    }

    public static Integer h(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        if (j1 == 0)
        {
            return null;
        } else
        {
            a(parcel, i1, j1, 4);
            return Integer.valueOf(parcel.readInt());
        }
    }

    public static long i(Parcel parcel, int i1)
    {
        a(parcel, i1, 8);
        return parcel.readLong();
    }

    public static Long j(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        if (j1 == 0)
        {
            return null;
        } else
        {
            a(parcel, i1, j1, 8);
            return Long.valueOf(parcel.readLong());
        }
    }

    public static BigInteger k(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        } else
        {
            byte abyte0[] = parcel.createByteArray();
            parcel.setDataPosition(j1 + k1);
            return new BigInteger(abyte0);
        }
    }

    public static float l(Parcel parcel, int i1)
    {
        a(parcel, i1, 4);
        return parcel.readFloat();
    }

    public static double m(Parcel parcel, int i1)
    {
        a(parcel, i1, 8);
        return parcel.readDouble();
    }

    public static BigDecimal n(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        } else
        {
            byte abyte0[] = parcel.createByteArray();
            int l1 = parcel.readInt();
            parcel.setDataPosition(j1 + k1);
            return new BigDecimal(new BigInteger(abyte0), l1);
        }
    }

    public static String o(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        } else
        {
            String s1 = parcel.readString();
            parcel.setDataPosition(j1 + k1);
            return s1;
        }
    }

    public static IBinder p(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        } else
        {
            IBinder ibinder = parcel.readStrongBinder();
            parcel.setDataPosition(j1 + k1);
            return ibinder;
        }
    }

    public static Bundle q(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        } else
        {
            Bundle bundle = parcel.readBundle();
            parcel.setDataPosition(j1 + k1);
            return bundle;
        }
    }

    public static byte[] r(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        } else
        {
            byte abyte0[] = parcel.createByteArray();
            parcel.setDataPosition(j1 + k1);
            return abyte0;
        }
    }

    public static byte[][] s(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return (byte[][])null;
        }
        int l1 = parcel.readInt();
        byte abyte0[][] = new byte[l1][];
        for (int i2 = 0; i2 < l1; i2++)
        {
            abyte0[i2] = parcel.createByteArray();
        }

        parcel.setDataPosition(k1 + j1);
        return abyte0;
    }

    public static boolean[] t(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        } else
        {
            boolean aflag[] = parcel.createBooleanArray();
            parcel.setDataPosition(j1 + k1);
            return aflag;
        }
    }

    public static int[] u(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        } else
        {
            int ai[] = parcel.createIntArray();
            parcel.setDataPosition(j1 + k1);
            return ai;
        }
    }

    public static long[] v(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        } else
        {
            long al[] = parcel.createLongArray();
            parcel.setDataPosition(j1 + k1);
            return al;
        }
    }

    public static BigInteger[] w(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        }
        int l1 = parcel.readInt();
        BigInteger abiginteger[] = new BigInteger[l1];
        for (int i2 = 0; i2 < l1; i2++)
        {
            abiginteger[i2] = new BigInteger(parcel.createByteArray());
        }

        parcel.setDataPosition(k1 + j1);
        return abiginteger;
    }

    public static float[] x(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        } else
        {
            float af[] = parcel.createFloatArray();
            parcel.setDataPosition(j1 + k1);
            return af;
        }
    }

    public static double[] y(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        } else
        {
            double ad[] = parcel.createDoubleArray();
            parcel.setDataPosition(j1 + k1);
            return ad;
        }
    }

    public static BigDecimal[] z(Parcel parcel, int i1)
    {
        int j1 = a(parcel, i1);
        int k1 = parcel.dataPosition();
        if (j1 == 0)
        {
            return null;
        }
        int l1 = parcel.readInt();
        BigDecimal abigdecimal[] = new BigDecimal[l1];
        for (int i2 = 0; i2 < l1; i2++)
        {
            byte abyte0[] = parcel.createByteArray();
            int j2 = parcel.readInt();
            abigdecimal[i2] = new BigDecimal(new BigInteger(abyte0), j2);
        }

        parcel.setDataPosition(k1 + j1);
        return abigdecimal;
    }
}
