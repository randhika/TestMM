// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            hy, kv, hv, ks, 
//            kw, kx

public static final class adN extends hy
    implements SafeParcelable, com.google.android.gms.plus.model.people.n.Cover
{
    public static final class a extends hy
        implements SafeParcelable, com.google.android.gms.plus.model.people.Person.Cover.CoverInfo
    {

        public static final kw CREATOR = new kw();
        private static final HashMap acr;
        private final Set acs;
        private int adO;
        private int adP;
        private final int xM;

        protected boolean a(hy.a a1)
        {
            return acs.contains(Integer.valueOf(a1.fN()));
        }

        protected Object aF(String s)
        {
            return null;
        }

        protected boolean aG(String s)
        {
            return false;
        }

        protected Object b(hy.a a1)
        {
            switch (a1.fN())
            {
            default:
                throw new IllegalStateException((new StringBuilder()).append("Unknown safe parcelable id=").append(a1.fN()).toString());

            case 2: // '\002'
                return Integer.valueOf(adO);

            case 3: // '\003'
                return Integer.valueOf(adP);
            }
        }

        public int describeContents()
        {
            kw _tmp = CREATOR;
            return 0;
        }

        public boolean equals(Object obj)
        {
label0:
            {
                if (!(obj instanceof a))
                {
                    return false;
                }
                if (this == obj)
                {
                    return true;
                }
                a a1 = (a)obj;
                hy.a a2;
label1:
                do
                {
                    for (Iterator iterator = acr.values().iterator(); iterator.hasNext();)
                    {
                        a2 = (hy.a)iterator.next();
                        if (!a(a2))
                        {
                            continue label1;
                        }
                        if (a1.a(a2))
                        {
                            if (!b(a2).equals(a1.b(a2)))
                            {
                                return false;
                            }
                        } else
                        {
                            return false;
                        }
                    }

                    break label0;
                } while (!a1.a(a2));
                return false;
            }
            return true;
        }

        public HashMap fG()
        {
            return acr;
        }

        public Object freeze()
        {
            return kR();
        }

        public int getLeftImageOffset()
        {
            return adO;
        }

        public int getTopImageOffset()
        {
            return adP;
        }

        int getVersionCode()
        {
            return xM;
        }

        public boolean hasLeftImageOffset()
        {
            return acs.contains(Integer.valueOf(2));
        }

        public boolean hasTopImageOffset()
        {
            return acs.contains(Integer.valueOf(3));
        }

        public int hashCode()
        {
            Iterator iterator = acr.values().iterator();
            int i = 0;
            while (iterator.hasNext()) 
            {
                hy.a a1 = (hy.a)iterator.next();
                int j;
                if (a(a1))
                {
                    j = i + a1.fN() + b(a1).hashCode();
                } else
                {
                    j = i;
                }
                i = j;
            }
            return i;
        }

        public boolean isDataValid()
        {
            return true;
        }

        public a kR()
        {
            return this;
        }

        Set kk()
        {
            return acs;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            kw _tmp = CREATOR;
            kw.a(this, parcel, i);
        }

        static 
        {
            acr = new HashMap();
            acr.put("leftImageOffset", hy.a.g("leftImageOffset", 2));
            acr.put("topImageOffset", hy.a.g("topImageOffset", 3));
        }

        public a()
        {
            xM = 1;
            acs = new HashSet();
        }

        a(Set set, int i, int j, int k)
        {
            acs = set;
            xM = i;
            adO = j;
            adP = k;
        }
    }

    public static final class b extends hy
        implements SafeParcelable, com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto
    {

        public static final kx CREATOR = new kx();
        private static final HashMap acr;
        private final Set acs;
        private int ku;
        private int kv;
        private String qY;
        private final int xM;

        protected boolean a(hy.a a1)
        {
            return acs.contains(Integer.valueOf(a1.fN()));
        }

        protected Object aF(String s)
        {
            return null;
        }

        protected boolean aG(String s)
        {
            return false;
        }

        protected Object b(hy.a a1)
        {
            switch (a1.fN())
            {
            default:
                throw new IllegalStateException((new StringBuilder()).append("Unknown safe parcelable id=").append(a1.fN()).toString());

            case 2: // '\002'
                return Integer.valueOf(kv);

            case 3: // '\003'
                return qY;

            case 4: // '\004'
                return Integer.valueOf(ku);
            }
        }

        public int describeContents()
        {
            kx _tmp = CREATOR;
            return 0;
        }

        public boolean equals(Object obj)
        {
label0:
            {
                if (!(obj instanceof b))
                {
                    return false;
                }
                if (this == obj)
                {
                    return true;
                }
                b b1 = (b)obj;
                hy.a a1;
label1:
                do
                {
                    for (Iterator iterator = acr.values().iterator(); iterator.hasNext();)
                    {
                        a1 = (hy.a)iterator.next();
                        if (!a(a1))
                        {
                            continue label1;
                        }
                        if (b1.a(a1))
                        {
                            if (!b(a1).equals(b1.b(a1)))
                            {
                                return false;
                            }
                        } else
                        {
                            return false;
                        }
                    }

                    break label0;
                } while (!b1.a(a1));
                return false;
            }
            return true;
        }

        public HashMap fG()
        {
            return acr;
        }

        public Object freeze()
        {
            return kS();
        }

        public int getHeight()
        {
            return kv;
        }

        public String getUrl()
        {
            return qY;
        }

        int getVersionCode()
        {
            return xM;
        }

        public int getWidth()
        {
            return ku;
        }

        public boolean hasHeight()
        {
            return acs.contains(Integer.valueOf(2));
        }

        public boolean hasUrl()
        {
            return acs.contains(Integer.valueOf(3));
        }

        public boolean hasWidth()
        {
            return acs.contains(Integer.valueOf(4));
        }

        public int hashCode()
        {
            Iterator iterator = acr.values().iterator();
            int i = 0;
            while (iterator.hasNext()) 
            {
                hy.a a1 = (hy.a)iterator.next();
                int j;
                if (a(a1))
                {
                    j = i + a1.fN() + b(a1).hashCode();
                } else
                {
                    j = i;
                }
                i = j;
            }
            return i;
        }

        public boolean isDataValid()
        {
            return true;
        }

        public b kS()
        {
            return this;
        }

        Set kk()
        {
            return acs;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            kx _tmp = CREATOR;
            kx.a(this, parcel, i);
        }

        static 
        {
            acr = new HashMap();
            acr.put("height", hy.a.g("height", 2));
            acr.put("url", hy.a.j("url", 3));
            acr.put("width", hy.a.g("width", 4));
        }

        public b()
        {
            xM = 1;
            acs = new HashSet();
        }

        b(Set set, int i, int j, String s, int k)
        {
            acs = set;
            xM = i;
            kv = j;
            qY = s;
            ku = k;
        }
    }


    public static final kv CREATOR = new kv();
    private static final HashMap acr;
    private final Set acs;
    private a adL;
    private b adM;
    private int adN;
    private final int xM;

    protected boolean a(nit> nit>)
    {
        return acs.contains(Integer.valueOf(nit>.fN()));
    }

    protected Object aF(String s)
    {
        return null;
    }

    protected boolean aG(String s)
    {
        return false;
    }

    protected Object b(fN fn)
    {
        switch (fn.fN())
        {
        default:
            throw new IllegalStateException((new StringBuilder()).append("Unknown safe parcelable id=").append(fn.fN()).toString());

        case 2: // '\002'
            return adL;

        case 3: // '\003'
            return adM;

        case 4: // '\004'
            return Integer.valueOf(adN);
        }
    }

    public int describeContents()
    {
        kv _tmp = CREATOR;
        return 0;
    }

    public boolean equals(Object obj)
    {
label0:
        {
            if (!(obj instanceof CREATOR))
            {
                return false;
            }
            if (this == obj)
            {
                return true;
            }
            CREATOR creator = (CREATOR)obj;
            CREATOR creator1;
label1:
            do
            {
                for (Iterator iterator = acr.values().iterator(); iterator.hasNext();)
                {
                    creator1 = (acr)iterator.next();
                    if (!a(creator1))
                    {
                        continue label1;
                    }
                    if (creator.a(creator1))
                    {
                        if (!b(creator1).equals(creator.b(creator1)))
                        {
                            return false;
                        }
                    } else
                    {
                        return false;
                    }
                }

                break label0;
            } while (!creator.a(creator1));
            return false;
        }
        return true;
    }

    public HashMap fG()
    {
        return acr;
    }

    public Object freeze()
    {
        return kQ();
    }

    public com.google.android.gms.plus.model.people.n.Cover.CoverInfo getCoverInfo()
    {
        return adL;
    }

    public com.google.android.gms.plus.model.people.n.Cover.CoverPhoto getCoverPhoto()
    {
        return adM;
    }

    public int getLayout()
    {
        return adN;
    }

    int getVersionCode()
    {
        return xM;
    }

    public boolean hasCoverInfo()
    {
        return acs.contains(Integer.valueOf(2));
    }

    public boolean hasCoverPhoto()
    {
        return acs.contains(Integer.valueOf(3));
    }

    public boolean hasLayout()
    {
        return acs.contains(Integer.valueOf(4));
    }

    public int hashCode()
    {
        Iterator iterator = acr.values().iterator();
        int i = 0;
        while (iterator.hasNext()) 
        {
            acs acs1 = (acr)iterator.next();
            int j;
            if (a(acs1))
            {
                j = i + acs1.fN() + b(acs1).hashCode();
            } else
            {
                j = i;
            }
            i = j;
        }
        return i;
    }

    public boolean isDataValid()
    {
        return true;
    }

    a kO()
    {
        return adL;
    }

    b kP()
    {
        return adM;
    }

    public adM kQ()
    {
        return this;
    }

    Set kk()
    {
        return acs;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        kv _tmp = CREATOR;
        kv.a(this, parcel, i);
    }

    static 
    {
        acr = new HashMap();
        acr.put("coverInfo", a("coverInfo", 2, com/google/android/gms/internal/ks$b$a));
        acr.put("coverPhoto", a("coverPhoto", 3, com/google/android/gms/internal/ks$b$b));
        acr.put("layout", a("layout", 4, (new hv()).f("banner", 0), false));
    }

    public b()
    {
        xM = 1;
        acs = new HashSet();
    }

    b(Set set, int i, a a1, b b1, int j)
    {
        acs = set;
        xM = i;
        adL = a1;
        adM = b1;
        adN = j;
    }
}
