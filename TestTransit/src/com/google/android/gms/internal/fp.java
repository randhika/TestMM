// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            fq, fm

public class fp
    implements SafeParcelable
{
    public static final class a
    {

        private final String mName;
        private String yi;
        private boolean yj;
        private int yk;
        private boolean yl;
        private String ym;
        private final List yn = new ArrayList();
        private BitSet yo;
        private String yp;

        public a I(int i)
        {
            if (yo == null)
            {
                yo = new BitSet();
            }
            yo.set(i);
            return this;
        }

        public a Z(String s)
        {
            yi = s;
            return this;
        }

        public a aa(String s)
        {
            yp = s;
            return this;
        }

        public fp dQ()
        {
            int i = 0;
            BitSet bitset = yo;
            int ai[] = null;
            if (bitset != null)
            {
                ai = new int[yo.cardinality()];
                for (int j = yo.nextSetBit(0); j >= 0;)
                {
                    int k = i + 1;
                    ai[i] = j;
                    j = yo.nextSetBit(j + 1);
                    i = k;
                }

            }
            return new fp(mName, yi, yj, yk, yl, ym, (fm[])yn.toArray(new fm[yn.size()]), ai, yp);
        }

        public a w(boolean flag)
        {
            yj = flag;
            return this;
        }

        public a x(boolean flag)
        {
            yl = flag;
            return this;
        }

        public a(String s)
        {
            mName = s;
            yk = 1;
        }
    }


    public static final fq CREATOR = new fq();
    public final String name;
    public final int weight;
    final int xM;
    public final String yb;
    public final boolean yc;
    public final boolean yd;
    public final String ye;
    public final fm yf[];
    final int yg[];
    public final String yh;

    fp(int i, String s, String s1, boolean flag, int j, boolean flag1, String s2, 
            fm afm[], int ai[], String s3)
    {
        xM = i;
        name = s;
        yb = s1;
        yc = flag;
        weight = j;
        yd = flag1;
        ye = s2;
        yf = afm;
        yg = ai;
        yh = s3;
    }

    fp(String s, String s1, boolean flag, int i, boolean flag1, String s2, fm afm[], 
            int ai[], String s3)
    {
        this(2, s, s1, flag, i, flag1, s2, afm, ai, s3);
    }

    public int describeContents()
    {
        fq _tmp = CREATOR;
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = obj instanceof fp;
        boolean flag1 = false;
        if (flag)
        {
            fp fp1 = (fp)obj;
            boolean flag2 = name.equals(fp1.name);
            flag1 = false;
            if (flag2)
            {
                boolean flag3 = yb.equals(fp1.yb);
                flag1 = false;
                if (flag3)
                {
                    boolean flag4 = yc;
                    boolean flag5 = fp1.yc;
                    flag1 = false;
                    if (flag4 == flag5)
                    {
                        flag1 = true;
                    }
                }
            }
        }
        return flag1;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        fq _tmp = CREATOR;
        fq.a(this, parcel, i);
    }

}
