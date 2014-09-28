// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer;

import java.util.Hashtable;

public class LLCalculation
{

    private static final double A = 6377397.1550000003D;
    private static final double E = 0.0066743722313149997D;
    private static final double EPS = 1E-10D;
    private static final double LOG10 = 2.3025850929940459D;
    private static final double LOGdiv2 = -0.69314718055994529D;
    private static final double RAD_PER_DEG = 0.017453292519943295D;
    private static final double SQRT3 = 1.7320508075688772D;
    private static final Hashtable atom_hash_0 = new Hashtable() {

            
            {
                put("a", new Double(6370300D));
                put("b", new Double(6370300D));
                put("e2", new Double(0.0D));
            }
    };
    private static final Hashtable atom_hash_1 = new Hashtable() {

            
            {
                put("a", new Double(6377397.1550000003D));
                put("b", new Double(6356078.96325D));
                put("e2", new Double((6377397.1550000003D * 6377397.1550000003D - 6356078.96325D * 6356078.96325D) / (6377397.1550000003D * 6377397.1550000003D)));
            }
    };

    public LLCalculation()
    {
    }

    private static Hashtable GetAtom(int i)
    {
        if (i == 0)
        {
            return atom_hash_0;
        }
        if (i == 1)
        {
            return atom_hash_1;
        } else
        {
            throw new RuntimeException("RdjLayer#GetAtom(): \u5909\u306A\u6295\u5F71");
        }
    }

    static double InvMercator_lat(double d, double d1, double d2, double d3, 
            int i)
    {
        double d5;
        double d6;
        double d7;
        Hashtable hashtable = GetAtom(i);
        double d4 = ((Double)hashtable.get("a")).doubleValue();
        d5 = ((Double)hashtable.get("e2")).doubleValue();
        d6 = Math.sqrt(d5);
        d7 = (d4 * Math.cos(d2)) / Math.sqrt(1.0D - d5 * Math.sin(d2) * Math.sin(d2));
        if (i != 0) goto _L2; else goto _L1
_L1:
        double d8 = 2D * (atan(exp(d1 / d7) * Math.tan(atan(1.0D) + d2 / 2D)) - atan(1.0D));
_L4:
        return d8;
_L2:
        int j = 0;
        d8 = 2D * (atan(exp(d1 / d7) * Math.tan(atan(1.0D) + d2 / 2D)) - atan(1.0D));
        double d9 = f(d8, d1, d2, d7, d6);
        do
        {
            if (d9 > -1E-10D && 1E-10D > d9)
            {
                continue; /* Loop/switch isn't completed */
            }
            d8 -= d9 / df(d8, d5);
            d9 = f(d8, d1, d2, d7, d6);
            j++;
        } while (100 >= j);
        break; /* Loop/switch isn't completed */
        if (true) goto _L4; else goto _L3
_L3:
        throw new RuntimeException("RdjLayer#InvMercator(): \u30FB\uFF7D\u30FB\uFF7D\u30FB\uFF7D\uFF88\u3091\uFF7F\uFF7D");
    }

    static double InvMercator_lon(double d, double d1, double d2, double d3, 
            int i)
    {
        double d5;
        double d6;
        double d7;
        double d8;
        Hashtable hashtable = GetAtom(i);
        double d4 = ((Double)hashtable.get("a")).doubleValue();
        d5 = ((Double)hashtable.get("e2")).doubleValue();
        d6 = Math.sqrt(d5);
        d7 = (d4 * Math.cos(d2)) / Math.sqrt(1.0D - d5 * Math.sin(d2) * Math.sin(d2));
        d8 = d3 + d / d7;
        if (i != 0) goto _L2; else goto _L1
_L1:
        2D * (atan(exp(d1 / d7) * Math.tan(atan(1.0D) + d2 / 2D)) - atan(1.0D));
_L4:
        return d8;
_L2:
        int j = 0;
        double d9 = 2D * (atan(exp(d1 / d7) * Math.tan(atan(1.0D) + d2 / 2D)) - atan(1.0D));
        double d10 = f(d9, d1, d2, d7, d6);
        do
        {
            if (d10 > -1E-10D && 1E-10D > d10)
            {
                continue; /* Loop/switch isn't completed */
            }
            d9 -= d10 / df(d9, d5);
            d10 = f(d9, d1, d2, d7, d6);
            j++;
        } while (100 >= j);
        break; /* Loop/switch isn't completed */
        if (true) goto _L4; else goto _L3
_L3:
        throw new RuntimeException("RdjLayer#InvMercator(): \u30FB\uFF7D\u30FB\uFF7D\u30FB\uFF7D\uFF88\u3091\uFF7F\uFF7D");
    }

    static double InvTMProjection_lat(double d, double d1, double d2, double d3, 
            double d4)
    {
        double d5 = 1.0D;
        double d6 = 0.62831853071795862D;
        double d7 = d1 / d4 + meridian(0.0D, d2);
        double d8 = d / d4;
        double d9 = Math.sin(d6);
        do
        {
            do
            {
                if (d5 <= 1E-14D)
                {
                    double d12 = Math.cos(d6);
                    double d13 = Math.sqrt(1.0D - d9 * (0.0066743722313149997D * d9));
                    double d14 = d13 / 6377397.1550000003D;
                    double d15 = 1.5785738198998536E-07D * (d13 * (d13 * d13));
                    double d16 = (0.0066743722313149997D * (d12 * d12)) / 0.99332562776868505D;
                    double d17 = Math.tan(d6);
                    double d18 = d17 * d17;
                    double d19 = d18 * d18;
                    double d20 = d8 * d14;
                    double d21 = d20 * d20;
                    double d22 = d21 * d21;
                    double d23 = -0.083333333333333329D * d21 * ((d16 + (5D + 3D * d18)) - d16 * (9D * d18));
                    double d24 = 0.002777777777777777D * d22 * (61D + 90D * d18 + 45D * d19);
                    return d6 - d17 * (d15 * (d20 * (0.5D * d8))) * (d24 + (1.0D + d23));
                }
                double d10 = meridian(0.0D, d6);
                d9 = Math.sin(d6);
                double d11 = d6 + 1.5785738198998536E-07D * ((d7 - d10) * pow(1.0D - d9 * (0.0066743722313149997D * d9), 1.5D));
                d5 = d11 - d6;
                d6 = d11;
            } while (d5 >= 0.0D);
            d5 *= -1D;
        } while (true);
    }

    static double InvTMProjection_lon(double d, double d1, double d2, double d3, 
            double d4)
    {
        double d5 = 1.0D;
        double d6 = 0.62831853071795862D;
        double d7 = d1 / d4 + meridian(0.0D, d2);
        double d8 = d / d4;
        double d9 = Math.sin(d6);
        do
        {
            do
            {
                if (d5 <= 1E-14D)
                {
                    double d12 = Math.cos(d6);
                    double d13 = Math.sqrt(1.0D - d9 * (0.0066743722313149997D * d9)) / 6377397.1550000003D;
                    double d14 = (0.0066743722313149997D * (d12 * d12)) / 0.99332562776868505D;
                    double d15 = Math.tan(d6);
                    double d16 = d15 * d15;
                    double d17 = d16 * d16;
                    double d18 = d8 * d13;
                    double d19 = d18 * d18;
                    double d20 = d19 * d19;
                    double _tmp = -0.083333333333333329D * d19 * ((d14 + (5D + 3D * d16)) - d14 * (9D * d16));
                    double _tmp1 = 0.002777777777777777D * d20 * (61D + 90D * d16 + 45D * d17);
                    double d21 = -0.1666666666666666D * d19 * (d14 + (1.0D + 2D * d16));
                    double d22 = 0.0083333333333333332D * d20 * (5D + 28D * d16 + 24D * d17);
                    return d3 + (d18 / d12) * (d22 + (1.0D + d21));
                }
                double d10 = meridian(0.0D, d6);
                d9 = Math.sin(d6);
                double d11 = d6 + 1.5785738198998536E-07D * ((d7 - d10) * pow(1.0D - d9 * (0.0066743722313149997D * d9), 1.5D));
                d5 = d11 - d6;
                d6 = d11;
            } while (d5 >= 0.0D);
            d5 *= -1D;
        } while (true);
    }

    static double InvYUSMercator_lat(double d, double d1, double d2, int i)
    {
        double d3 = 1 << 26 - i;
        double d4 = d3 / 360D;
        double d5 = d / 1.0D;
        double d6 = d1 / 1.0D;
        double d7 = d5 / d4;
        double d8 = atan((float)sinh(3.1415926535897931D * (d6 / (d3 / 2D)))) / 0.017453292519943295D;
        if (d7 < 0.0D)
        {
            d7 += 360D;
        }
        if (d7 > 360D)
        {
            double _tmp = d7 - 360D;
        }
        if (d8 <= -90D || d8 >= 90D)
        {
            return 0.0D;
        } else
        {
            return d8;
        }
    }

    static double InvYUSMercator_lon(double d, double d1, double d2, int i)
    {
        double d3 = 1 << 26 - i;
        double d4 = d3 / 360D;
        double d5 = d / 1.0D;
        double d6 = d1 / 1.0D;
        double d7 = d5 / d4;
        double d8 = atan((float)sinh(3.1415926535897931D * (d6 / (d3 / 2D)))) / 0.017453292519943295D;
        if (d7 < 0.0D)
        {
            d7 += 360D;
        }
        if (d7 > 360D)
        {
            d7 -= 360D;
        }
        double d9 = d7 - 180D;
        if (d8 > -90D)
        {
            if (d8 < 90D);
        }
        return d9;
    }

    static double Mercator_x(double d, double d1, double d2, double d3, 
            int i)
    {
        Hashtable hashtable = GetAtom(i);
        if (1E-10D + -2D * atan(1.0D) < d && d < 2D * atan(1.0D) - 1E-10D)
        {
            double d4 = ((Double)hashtable.get("a")).doubleValue();
            double d5 = ((Double)hashtable.get("e2")).doubleValue();
            return ((d4 * Math.cos(d2)) / Math.sqrt(1.0D - d5 * Math.sin(d2) * Math.sin(d2))) * (d1 - d3);
        } else
        {
            throw new RuntimeException("RdjLayer#Mercator(): \u30FB\uFF7D\uFF8D\u56F2\u5916");
        }
    }

    static double Mercator_y(double d, double d1, double d2, double d3, 
            int i)
    {
        Hashtable hashtable = GetAtom(i);
        if (1E-10D + -2D * atan(1.0D) < d && d < 2D * atan(1.0D) - 1E-10D)
        {
            double d4 = ((Double)hashtable.get("a")).doubleValue();
            double d5 = ((Double)hashtable.get("e2")).doubleValue();
            double d6 = Math.sqrt(d5);
            double d7 = d6 * Math.sin(d2);
            return ((d4 * Math.cos(d2)) / Math.sqrt(1.0D - d5 * Math.sin(d2) * Math.sin(d2))) * (log(Math.tan(atan(1.0D) + d / 2D) / Math.tan(atan(1.0D) + d2 / 2D)) + (d6 / 2D) * (log((1.0D - d6 * Math.sin(d)) / (1.0D - d7)) - log((1.0D + d6 * Math.sin(d)) / (1.0D + d7))));
        } else
        {
            throw new RuntimeException("RdjLayer#Mercator(): \u30FB\uFF7D\uFF8D\u56F2\u5916");
        }
    }

    static double TMProjection_x(double d, double d1, double d2, double d3, 
            double d4)
    {
        double d5 = d1 - d3;
        double d6 = Math.sin(d);
        double d7 = Math.cos(d);
        double d8 = d7 * d7;
        double d9 = Math.tan(d);
        double d10 = d9 * d9;
        double d11 = d10 * d10;
        double d12 = (0.0066743722313149997D * d8) / 0.99332562776868505D;
        double d13 = d12 * d12;
        double d14 = 6377397.1550000003D / Math.sqrt(1.0D - d6 * (0.0066743722313149997D * d6));
        double d15 = d5 * d5;
        double d16 = d15 * d8;
        double d17 = d16 * d16;
        double d18 = meridian(d2, d);
        double d19 = (d16 * ((5D - d10) + 9D * d12 + 4D * d13)) / 24D;
        double d20 = (d17 * ((d11 + (61D - 58D * d10) + 270D * d12) - d10 * (330D * d12))) / 720D;
        return d4 * (d18 + d15 * (d7 * (d14 * d6)) * (d20 + (0.5D + d19)));
    }

    static double TMProjection_y(double d, double d1, double d2, double d3, 
            double d4)
    {
        double d5 = d1 - d3;
        double d6 = Math.sin(d);
        double d7 = Math.cos(d);
        double d8 = d7 * d7;
        double d9 = Math.tan(d);
        double d10 = d9 * d9;
        double d11 = d10 * d10;
        double d12 = (0.0066743722313149997D * d8) / 0.99332562776868505D;
        double d13 = 6377397.1550000003D / Math.sqrt(1.0D - d6 * (0.0066743722313149997D * d6));
        double d14 = d8 * (d5 * d5);
        double d15 = d14 * d14;
        double d16 = (d14 * (d12 + (1.0D - d10))) / 6D;
        double d17 = (d15 * ((d11 + (5D - 18D * d10) + 14D * d12) - d10 * (58D * d12))) / 120D;
        return d5 * (d7 * (d4 * d13)) * (d17 + (1.0D + d16));
    }

    static double YUSMercator_x(double d, double d1, double d2, int i)
    {
        double d3 = (double)(1 << 26 - i) / 360D;
        double d4 = Math.abs(d);
        double d5 = d1;
        if (d5 > 180D)
        {
            d5 -= 360D;
        }
        double d6 = d5 + 180D;
        if (d4 >= 90D)
        {
            return 0.0D;
        }
        if (d6 > 360D)
        {
            d6 -= 360D;
        }
        if (d6 < 0.0D)
        {
            d6 += 360D;
        }
        double _tmp = d4 * 0.017453292519943295D;
        return 1.0D * (d6 * d3);
    }

    static double YUSMercator_y(double d, double d1, double d2, int i)
    {
        double d3 = 1 << 26 - i;
        double d4 = Math.abs(d);
        double d5 = d1;
        if (d5 > 180D)
        {
            d5 -= 360D;
        }
        double d6 = d5 + 180D;
        if (d4 >= 90D)
        {
            return 0.0D;
        }
        if (d6 > 360D)
        {
            d6 -= 360D;
        }
        if (d6 < 0.0D)
        {
            double _tmp = d6 + 360D;
        }
        double d7 = d4 * 0.017453292519943295D;
        double d8 = (d3 * (log(Math.tan(d7) + 1.0D / Math.cos(d7)) / 3.1415926535897931D)) / 2D;
        if (d < 0.0D)
        {
            d8 = -d8;
        }
        return d8 * 1.0D;
    }

    private static double _log(double d)
    {
        if (d > 0.0D) goto _L2; else goto _L1
_L1:
        double d6 = (0.0D / 0.0D);
_L5:
        return d6;
_L2:
        double d1;
        int i;
        d1 = 0.0D;
        i = 0;
_L6:
        if (d > 0.0D && d <= 1.0D) goto _L4; else goto _L3
_L3:
        int j;
        double d4;
        double d5;
        long l;
        double d2 = d / 2D;
        j = i - 1;
        double d3 = (d2 - 1.0D) / (d2 + 1.0D);
        d4 = d3;
        d5 = d4 * d3;
        l = 1L;
_L7:
        if (l < 50L)
        {
            break MISSING_BLOCK_LABEL_121;
        }
        d6 = d1 * 2D;
        int k = 0;
        while (k < j) 
        {
            d6 -= 0.69314718055994529D;
            k++;
        }
          goto _L5
_L4:
        d *= 2D;
        i++;
          goto _L6
        d1 += d4 / (double)l;
        d4 *= d5;
        l += 2L;
          goto _L7
    }

    public static double acos(double d)
    {
        double d1 = asin(d);
        if (d1 == (0.0D / 0.0D))
        {
            return d1;
        } else
        {
            return 1.5707963267948966D - d1;
        }
    }

    public static double asin(double d)
    {
        if (d < -1D || d > 1.0D)
        {
            return (0.0D / 0.0D);
        }
        if (d == -1D)
        {
            return -1.5707963267948966D;
        }
        if (d == 1.0D)
        {
            return 1.5707963267948966D;
        } else
        {
            return atan(d / Math.sqrt(1.0D - d * d));
        }
    }

    public static double atan(double d)
    {
        boolean flag;
        boolean flag1;
        int k;
        int i = d != 0.0D;
        flag = false;
        if (i < 0)
        {
            d = -d;
            flag = true;
        }
        int j = d != 1.0D;
        flag1 = false;
        k = 0;
        if (j > 0)
        {
            d = 1.0D / d;
            flag1 = true;
        }
_L3:
        if (d > 0.26179938779914941D) goto _L2; else goto _L1
_L1:
        double d2;
        double d1 = d * d;
        d2 = d * ((0.60310578999999997D + 0.55913709D / (d1 + 1.4087812D)) - 0.051604539999999997D * d1);
_L4:
        if (k <= 0)
        {
            if (flag1)
            {
                d2 = 1.5707963267948966D - d2;
            }
            if (flag)
            {
                d2 = -d2;
            }
            return d2;
        }
        break MISSING_BLOCK_LABEL_128;
_L2:
        k++;
        d = (1.0D / (d + 1.7320508075688772D)) * (d * 1.7320508075688772D - 1.0D);
          goto _L3
        d2 += 0.52359877559829882D;
        k--;
          goto _L4
    }

    public static double atan2(double d, double d1)
    {
        if (d == 0.0D && d1 == 0.0D)
        {
            return 0.0D;
        }
        if (d1 > 0.0D)
        {
            return atan(d / d1);
        }
        if (d1 < 0.0D)
        {
            if (d < 0.0D)
            {
                return -(3.1415926535897931D - atan(d / d1));
            } else
            {
                return 3.1415926535897931D - atan(-d / d1);
            }
        }
        return d >= 0.0D ? 1.5707963267948966D : -1.5707963267948966D;
    }

    private static double df(double d, double d1)
    {
        return (1.0D - d1) / (Math.cos(d) * (1.0D - d1 * Math.sin(d)));
    }

    public static double distance(double d, double d1, double d2, double d3)
    {
        double d4 = Math.cos((3.1415926535897931D * d1) / 180D) * Math.cos((3.1415926535897931D * d) / 180D);
        double d5 = Math.sin((3.1415926535897931D * d1) / 180D) * Math.cos((3.1415926535897931D * d) / 180D);
        double d6 = Math.sin((3.1415926535897931D * d) / 180D);
        double d7 = Math.cos((3.1415926535897931D * d3) / 180D) * Math.cos((3.1415926535897931D * d2) / 180D);
        double d8 = Math.sin((3.1415926535897931D * d3) / 180D) * Math.cos((3.1415926535897931D * d2) / 180D);
        double d9 = Math.sin((3.1415926535897931D * d2) / 180D);
        double d10 = d4 * d7 + d5 * d8 + d6 * d9;
        if (d10 < -1D)
        {
            d10 = -1D;
        }
        if (d10 > 1.0D)
        {
            d10 = 1.0D;
        }
        return 6370300D * acos(d10);
    }

    public static double exp(double d)
    {
        if (d != 0.0D) goto _L2; else goto _L1
_L1:
        double d1 = 1.0D;
_L4:
        return d1;
_L2:
        d1 = 1.0D;
        boolean flag;
        double d2;
        long l;
        if (d < 0.0D)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            d = -d;
        }
        d2 = d / (double)1L;
        l = 2L;
        do
        {
label0:
            {
                if (l < 50L)
                {
                    break label0;
                }
                if (flag)
                {
                    return 1.0D / d1;
                }
            }
            if (true)
            {
                continue;
            }
            d1 += d2;
            d2 = (d2 * d) / (double)l;
            l++;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static double f(double d, double d1, double d2, double d3, 
            double d4)
    {
        double d5 = d4 * Math.sin(d2);
        return (log(Math.tan(atan(1.0D) + d / 2D) / Math.tan(atan(1.0D) + d2 / 2D)) + (d4 / 2D) * log(((1.0D - d4 * Math.sin(d)) * (1.0D + d5)) / (1.0D - d5) / (1.0D + d4 * Math.sin(d)))) - d1 / d3;
    }

    public static double latLon2X(double d, double d1, long l, int i, double d2, double d3, double d4, int j, int k)
    {
        double d5;
        d5 = 0.0D;
        if (i != 3)
        {
            d = Math.toRadians(d);
            if (d1 < -30D)
            {
                d1 += 360D;
            }
            d1 = Math.toRadians(d1);
            d2 = Math.toRadians(d2);
            d3 = Math.toRadians(d3);
        }
        i;
        JVM INSTR tableswitch 0 3: default 80
    //                   0 95
    //                   1 111
    //                   2 126
    //                   3 141;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return (10D * (1000D * d5)) / (double)l;
_L2:
        d5 = TMProjection_x(d, d1, d2, d3, d4);
        continue; /* Loop/switch isn't completed */
_L3:
        d5 = Mercator_x(d, d1, d2, d3, 0);
        continue; /* Loop/switch isn't completed */
_L4:
        d5 = Mercator_x(d, d1, d2, d3, 1);
        if (true) goto _L1; else goto _L5
_L5:
        double d6 = l;
        return YUSMercator_x(d, d1, d6, j);
    }

    public static double latLon2Y(double d, double d1, long l, int i, double d2, double d3, double d4, int j, int k)
    {
        double d5;
        d5 = 0.0D;
        if (i != 3)
        {
            d = Math.toRadians(d);
            if (d1 < -30D)
            {
                d1 += 360D;
            }
            d1 = Math.toRadians(d1);
            d2 = Math.toRadians(d2);
            d3 = Math.toRadians(d3);
        }
        i;
        JVM INSTR tableswitch 0 3: default 80
    //                   0 96
    //                   1 112
    //                   2 127
    //                   3 142;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return (10D * (1000D * -d5)) / (double)l;
_L2:
        d5 = TMProjection_y(d, d1, d2, d3, d4);
        continue; /* Loop/switch isn't completed */
_L3:
        d5 = Mercator_y(d, d1, d2, d3, 0);
        continue; /* Loop/switch isn't completed */
_L4:
        d5 = Mercator_y(d, d1, d2, d3, 1);
        if (true) goto _L1; else goto _L5
_L5:
        double d6 = l;
        return -YUSMercator_y(d, d1, d6, j);
    }

    public static double log(double d)
    {
        double d1 = 0.0D;
        if (d <= d1)
        {
            d1 = (0.0D / 0.0D);
        } else
        if (d != 1.0D)
        {
            if (d > 1.0D)
            {
                return -_log(1.0D / d);
            } else
            {
                return _log(d);
            }
        }
        return d1;
    }

    public static double log10(double d)
    {
        return log(d) / 2.3025850929940459D;
    }

    public static double log2Lat(double d, double d1, long l, int i, double d2, double d3, double d4, int j, int k)
    {
        double d5;
        double d6;
        double d7;
        double d8;
        double d9;
        d5 = d;
        d6 = -d1;
        d7 = d2;
        d8 = d3;
        d9 = 0.0D;
        if (i != 3)
        {
            d5 = (d * (double)l) / 1000D / 10D;
            d6 = (-d1 * (double)l) / 1000D / 10D;
            d7 = Math.toRadians(d2);
            d8 = Math.toRadians(d3);
        }
        i;
        JVM INSTR tableswitch 0 3: default 100
    //                   0 106
    //                   1 124
    //                   2 141
    //                   3 158;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return Math.toDegrees(d9);
_L2:
        d9 = InvTMProjection_lat(d5, d6, d7, d8, d4);
        continue; /* Loop/switch isn't completed */
_L3:
        d9 = InvMercator_lat(d5, d6, d7, d8, 0);
        continue; /* Loop/switch isn't completed */
_L4:
        d9 = InvMercator_lat(d5, d6, d7, d8, 1);
        if (true) goto _L1; else goto _L5
_L5:
        double d10 = l;
        return InvYUSMercator_lat(d5, d6, d10, j);
    }

    public static double log2Lon(double d, double d1, long l, int i, double d2, double d3, double d4, int j, int k)
    {
        double d5;
        double d6;
        double d7;
        double d8;
        double d9;
        d5 = d;
        d6 = -d1;
        d7 = d2;
        d8 = d3;
        d9 = 0.0D;
        if (i != 3)
        {
            d5 = (d * (double)l) / 1000D / 10D;
            d6 = (-d1 * (double)l) / 1000D / 10D;
            d7 = Math.toRadians(d2);
            d8 = Math.toRadians(d3);
        }
        i;
        JVM INSTR tableswitch 0 3: default 100
    //                   0 127
    //                   1 145
    //                   2 162
    //                   3 179;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        double d11 = Math.toDegrees(d9);
        if (d11 > 180D)
        {
            d11 -= 360D;
        }
        return d11;
_L2:
        d9 = InvTMProjection_lon(d5, d6, d7, d8, d4);
        continue; /* Loop/switch isn't completed */
_L3:
        d9 = InvMercator_lon(d5, d6, d7, d8, 0);
        continue; /* Loop/switch isn't completed */
_L4:
        d9 = InvMercator_lon(d5, d6, d7, d8, 1);
        if (true) goto _L1; else goto _L5
_L5:
        double d10 = l;
        return InvYUSMercator_lon(d5, d6, d10, j);
    }

    private static double meridian(double d, double d1)
    {
        double d2 = 1.0050373060490001D * (d1 - d);
        double d3 = (0.0050478492402999996D * (Math.sin(2D * d1) - Math.sin(2D * d))) / 2D;
        double d4 = (1.05637868E-05D * (Math.sin(4D * d1) - Math.sin(4D * d))) / 4D;
        double d5 = (2.063332E-08D * (Math.sin(6D * d1) - Math.sin(6D * d))) / 6D;
        return 6334832.0325206015D * ((3.8853E-11D * (Math.sin(8D * d1) - Math.sin(8D * d))) / 8D + ((d4 + (d2 - d3)) - d5));
    }

    public static double pow(double d, double d1)
    {
        if (d == 0.0D)
        {
            d = 0.0D;
        } else
        {
            if (d == 1.0D)
            {
                return 1.0D;
            }
            if (d1 == 0.0D)
            {
                return 1.0D;
            }
            if (d1 != 1.0D)
            {
                long l = (long)Math.floor(d1);
                boolean flag;
                if (d1 == (double)l)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    int i = d1 != 0.0D;
                    boolean flag1 = false;
                    if (i < 0)
                    {
                        flag1 = true;
                    }
                    double d2 = d;
                    long l1 = 1L;
                    do
                    {
                        long l2;
                        if (flag1)
                        {
                            l2 = -l;
                        } else
                        {
                            l2 = l;
                        }
                        if (l1 >= l2)
                        {
                            if (flag1)
                            {
                                return 1.0D / d2;
                            } else
                            {
                                return d2;
                            }
                        }
                        d2 *= d;
                        l1++;
                    } while (true);
                }
                if (d > 0.0D)
                {
                    return exp(d1 * log(d));
                } else
                {
                    return (0.0D / 0.0D);
                }
            }
        }
        return d;
    }

    public static int round(double d)
    {
        double d1;
        if (0.0D > d)
        {
            d1 = d - 0.5D;
        } else
        {
            d1 = d + 0.5D;
        }
        return (int)d1;
    }

    private static double sinh(double d)
    {
        double d1 = exp(d);
        return (d1 - 1.0D / d1) / 2D;
    }

}
