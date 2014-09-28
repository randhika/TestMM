// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer;

import java.io.PrintStream;
import java.util.Hashtable;
import java.util.Vector;
import jp.co.yahoo.android.maps.DoublePoint;

public final class Coordinate
{

    private static final double A = 6377397.1550000003D;
    private static final double E = 0.0066743722313149997D;
    private static final double EPS = 1E-10D;
    private static final double LOG10 = 2.3025850929940459D;
    private static final double LOGdiv2 = -0.69314718055994529D;
    private static int MAXLEVEL = 0;
    private static double RAD_PER_DEG = 0D;
    private static final double SQRT3 = 1.7320508075688772D;
    public double lat;
    public double lon;

    public Coordinate()
    {
        this(0.0D, 0.0D);
    }

    public Coordinate(double d, double d1)
    {
        lat = d;
        lon = d1;
    }

    public Coordinate(Coordinate coordinate)
    {
        this(coordinate.lat, coordinate.lon);
    }

    public Coordinate(double ad[])
    {
        lat = ad[0];
        lon = ad[1];
    }

    private static int GetAtom(int i, Hashtable hashtable)
    {
        if (i == 0)
        {
            hashtable.put("a", new Double(6370300D));
            hashtable.put("b", new Double(6370300D));
            hashtable.put("e2", new Double(0.0D));
        } else
        if (i == 1)
        {
            hashtable.put("a", new Double(6377397.1550000003D));
            hashtable.put("b", new Double(6356078.96325D));
            hashtable.put("e2", new Double((6377397.1550000003D * 6377397.1550000003D - 6356078.96325D * 6356078.96325D) / (6377397.1550000003D * 6377397.1550000003D)));
        } else
        {
            throw new RuntimeException("RdjLayer#GetAtom(): \u5909\u306A\u6295\u5F71");
        }
        return 0;
    }

    static double[] InvMercator(double d, double d1, double d2, double d3, 
            int i)
    {
        double d5;
        double d6;
        double d7;
        double d8;
        Hashtable hashtable = new Hashtable();
        if (GetAtom(i, hashtable) < 0)
        {
            return null;
        }
        double d4 = ((Double)hashtable.get("a")).doubleValue();
        d5 = ((Double)hashtable.get("e2")).doubleValue();
        d6 = Math.sqrt(d5);
        d7 = (d4 * Math.cos(d2)) / Math.sqrt(1.0D - d5 * Math.sin(d2) * Math.sin(d2));
        d8 = d3 + d / d7;
        if (i != 0) goto _L2; else goto _L1
_L1:
        double d9 = 2D * (atan(exp(d1 / d7) * Math.tan(atan(1.0D) + d2 / 2D)) - atan(1.0D));
_L4:
        return (new double[] {
            d9, d8
        });
_L2:
        int j = 0;
        d9 = 2D * (atan(exp(d1 / d7) * Math.tan(atan(1.0D) + d2 / 2D)) - atan(1.0D));
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

    static double[] InvTMProjection(double d, double d1, double d2, double d3, 
            double d4)
    {
        double d5 = 1.0D;
        double d6 = 0.62831853071795862D;
        double d7 = d1 / d4 + Meridian(0.0D, d2);
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
                    double d25 = d6 - d17 * (d15 * (d20 * (0.5D * d8))) * (d24 + (1.0D + d23));
                    double d26 = -0.1666666666666666D * d21 * (d16 + (1.0D + 2D * d18));
                    double d27 = 0.0083333333333333332D * d22 * (5D + 28D * d18 + 24D * d19);
                    return (new double[] {
                        d25, d3 + (d20 / d12) * (d27 + (1.0D + d26))
                    });
                }
                double d10 = Meridian(0.0D, d6);
                d9 = Math.sin(d6);
                double d11 = d6 + 1.5785738198998536E-07D * ((d7 - d10) * pow(1.0D - d9 * (0.0066743722313149997D * d9), 1.5D));
                d5 = d11 - d6;
                d6 = d11;
            } while (d5 >= 0.0D);
            d5 *= -1D;
        } while (true);
    }

    static double[] InvYUSMercator(double d, double d1, double d2, int i)
    {
        double d3 = 1 << 26 - i;
        double d4 = d3 / 360D;
        double d5 = d / 1.0D;
        double d6 = d1 / 1.0D;
        double d7 = d5 / d4;
        double d8 = atan((float)sinh(3.1415926535897931D * (d6 / (d3 / 2D)))) / RAD_PER_DEG;
        if (d7 < 0.0D)
        {
            d7 += 360D;
        }
        if (d7 > 360D)
        {
            d7 -= 360D;
        }
        double d9 = d7 - 180D;
        if (d8 <= -90D || d8 >= 90D)
        {
            return (new double[] {
                0.0D, d9
            });
        } else
        {
            return (new double[] {
                d8, d9
            });
        }
    }

    static double[] Mercator(double d, double d1, double d2, double d3, 
            int i)
    {
        Hashtable hashtable = new Hashtable();
        if (GetAtom(i, hashtable) < 0)
        {
            return null;
        }
        if (1E-10D + -2D * atan(1.0D) < d && d < 2D * atan(1.0D) - 1E-10D)
        {
            double d4 = ((Double)hashtable.get("a")).doubleValue();
            double d5 = ((Double)hashtable.get("e2")).doubleValue();
            double d6 = Math.sqrt(d5);
            double d7 = d6 * Math.sin(d2);
            double d8 = (d4 * Math.cos(d2)) / Math.sqrt(1.0D - d5 * Math.sin(d2) * Math.sin(d2));
            return (new double[] {
                d8 * (d1 - d3), d8 * (log(Math.tan(atan(1.0D) + d / 2D) / Math.tan(atan(1.0D) + d2 / 2D)) + (d6 / 2D) * (log((1.0D - d6 * Math.sin(d)) / (1.0D - d7)) - log((1.0D + d6 * Math.sin(d)) / (1.0D + d7))))
            });
        } else
        {
            throw new RuntimeException("RdjLayer#Mercator(): \u30FB\uFF7D\uFF8D\u56F2\u5916");
        }
    }

    private static double Meridian(double d, double d1)
    {
        double d2 = 1.0050373060490001D * (d1 - d);
        double d3 = (0.0050478492402999996D * (Math.sin(2D * d1) - Math.sin(2D * d))) / 2D;
        double d4 = (1.05637868E-05D * (Math.sin(4D * d1) - Math.sin(4D * d))) / 4D;
        double d5 = (2.063332E-08D * (Math.sin(6D * d1) - Math.sin(6D * d))) / 6D;
        return 6334832.0325206015D * ((3.8853E-11D * (Math.sin(8D * d1) - Math.sin(8D * d))) / 8D + ((d4 + (d2 - d3)) - d5));
    }

    static double[] TMProjection(double d, double d1, double d2, double d3, 
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
        double d18 = Meridian(d2, d);
        double d19 = (d16 * ((5D - d10) + 9D * d12 + 4D * d13)) / 24D;
        double d20 = (d17 * ((d11 + (61D - 58D * d10) + 270D * d12) - d10 * (330D * d12))) / 720D;
        double d21 = (d16 * (d12 + (1.0D - d10))) / 6D;
        double d22 = (d17 * ((d11 + (5D - 18D * d10) + 14D * d12) - d10 * (58D * d12))) / 120D;
        double d23 = d4 * (d18 + d15 * (d7 * (d14 * d6)) * (d20 + (0.5D + d19)));
        return (new double[] {
            d5 * (d7 * (d4 * d14)) * (d22 + (1.0D + d21)), d23
        });
    }

    static double[] YUSMercator(double d, double d1, double d2, int i)
    {
        double d3 = 1 << 26 - i;
        double d4 = d3 / 360D;
        double d5 = Math.abs(d);
        double d6 = d1;
        if (d6 > 180D)
        {
            d6 -= 360D;
        }
        double d7 = d6 + 180D;
        DoublePoint doublepoint = new DoublePoint(0.0D, 0.0D);
        if (d5 >= 90D)
        {
            double ad1[] = new double[2];
            ad1[0] = doublepoint.x;
            ad1[1] = doublepoint.y;
            return ad1;
        }
        if (d7 > 360D)
        {
            d7 -= 360D;
        }
        if (d7 < 0.0D)
        {
            d7 += 360D;
        }
        double d8 = d5 * RAD_PER_DEG;
        doublepoint.x = d7 * d4;
        doublepoint.y = (d3 * (log(Math.tan(d8) + 1.0D / Math.cos(d8)) / 3.1415926535897931D)) / 2D;
        if (d < 0.0D)
        {
            doublepoint.y = -doublepoint.y;
        }
        doublepoint.x = 1.0D * doublepoint.x;
        doublepoint.y = 1.0D * doublepoint.y;
        double ad[] = new double[2];
        ad[0] = doublepoint.x;
        ad[1] = doublepoint.y;
        return ad;
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

    public static Coordinate besslWgs(int i, Coordinate coordinate)
    {
        double ad[] = {
            6377397.1550000003D, 6378137D
        };
        double ad1[] = {
            0.0033427731799399794D, 0.0033528106647474805D
        };
        double ad2[] = new double[2];
        ad2[0] = ad1[0] * (2D - ad1[0]);
        ad2[1] = ad1[1] * (2D - ad1[1]);
        double ad3[] = new double[2];
        double ad4[] = new double[2];
        double ad5[] = new double[2];
        ad3[0] = -146.43000000000001D;
        ad4[0] = 507.88999999999999D;
        ad5[0] = 681.46000000000004D;
        ad3[1] = 146.43000000000001D;
        ad4[1] = -507.88999999999999D;
        ad5[1] = -681.46000000000004D;
        int j = 1 - i;
        double d = (3.1415926535897931D * coordinate.getLatitude()) / 180D;
        double d1 = (3.1415926535897931D * coordinate.getLongitude()) / 180D;
        double d2 = ad[i] / Math.sqrt(1.0D - ad2[i] * Math.sin(d) * Math.sin(d));
        double d3 = (d2 + 0.0D) * Math.cos(d) * Math.cos(d1);
        double d4 = (d2 + 0.0D) * Math.cos(d) * Math.sin(d1);
        double d5 = (0.0D + d2 * (1.0D - ad2[i])) * Math.sin(d);
        double d6 = d3 + ad3[i];
        double d7 = d4 + ad4[i];
        double d8 = d5 + ad5[i];
        double d9 = Math.sqrt(d6 * d6 + d7 * d7);
        double d10 = d8 / d9;
        double d11 = Math.sqrt(1.0D + d10 * (d10 * (1.0D - ad2[j])));
        double d12 = d11 * (d11 * d11);
        double d13 = d10 - d8 / d9 - (d10 * (ad[j] * ad2[j])) / d9 / d11;
        double d14 = d10 - d13 / (1.0D - (ad[j] * ad2[j]) / d9 / d12);
        int k = 0;
        do
        {
            if (d13 <= 1.0000000000000001E-15D && -1.0000000000000001E-15D <= d13 || k >= 10)
            {
                double d19 = atan2(d14, 1.0D);
                double d20 = atan2(d7, d6);
                return new Coordinate((180D * d19) / 3.1415926535897931D, (180D * d20) / 3.1415926535897931D);
            }
            double d15 = d14;
            double d16 = Math.sqrt(1.0D + d15 * (d15 * (1.0D - ad2[j])));
            double d17 = d16 * (d16 * d16);
            double d18 = (ad[j] * ad2[j]) / d9;
            d13 = d15 - d8 / d9 - (d18 * d15) / d16;
            d14 = d15 - d13 / (1.0D - d18 / d17);
            k++;
        } while (true);
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

    public static boolean latLon2Log(DoublePoint doublepoint, double d, double d1, long l, int i, 
            double d2, double d3, double d4, int j, 
            int k)
    {
        double ad[];
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
        ad = null;
        i;
        JVM INSTR tableswitch 0 3: default 80
    //                   0 87
    //                   1 103
    //                   2 118
    //                   3 133;
           goto _L1 _L2 _L3 _L4 _L5
_L4:
        break MISSING_BLOCK_LABEL_118;
_L1:
        break; /* Loop/switch isn't completed */
_L5:
        break MISSING_BLOCK_LABEL_133;
_L6:
        if (ad == null)
        {
            return false;
        } else
        {
            doublepoint.x = (10D * (1000D * ad[0])) / (double)l;
            doublepoint.y = (10D * (1000D * -ad[1])) / (double)l;
            return true;
        }
_L2:
        ad = TMProjection(d, d1, d2, d3, d4);
          goto _L6
_L3:
        ad = Mercator(d, d1, d2, d3, 0);
          goto _L6
        ad = Mercator(d, d1, d2, d3, 1);
          goto _L6
        double d5 = l;
        double ad1[] = YUSMercator(d, d1, d5, j);
        doublepoint.x = ad1[0];
        doublepoint.y = -ad1[1];
        return true;
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

    public static Coordinate log2LatLon(double d, double d1, long l, int i, Coordinate coordinate, 
            double d2, int j, int k)
    {
        double d3;
        double d4;
        double d5;
        double d6;
        double ad[];
        d3 = d;
        d4 = -d1;
        d5 = coordinate.lat;
        d6 = coordinate.lon;
        if (i != 3)
        {
            d3 = (d * (double)l) / 1000D / 10D;
            d4 = (-d1 * (double)l) / 1000D / 10D;
            d5 = Math.toRadians(coordinate.lat);
            d6 = Math.toRadians(coordinate.lon);
        }
        ad = null;
        i;
        JVM INSTR tableswitch 0 3: default 112
    //                   0 119
    //                   1 137
    //                   2 154
    //                   3 171;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        if (ad == null)
        {
            return null;
        }
        break; /* Loop/switch isn't completed */
_L2:
        ad = InvTMProjection(d3, d4, d5, d6, d2);
        continue; /* Loop/switch isn't completed */
_L3:
        ad = InvMercator(d3, d4, d5, d6, 0);
        continue; /* Loop/switch isn't completed */
_L4:
        ad = InvMercator(d3, d4, d5, d6, 1);
        if (true) goto _L1; else goto _L6
_L5:
        double d7 = l;
        double ad1[] = InvYUSMercator(d3, d4, d7, j);
        Coordinate coordinate1 = new Coordinate(ad1[0], ad1[1]);
        return coordinate1;
_L6:
        double d8 = Math.toDegrees(ad[0]);
        double d9 = Math.toDegrees(ad[1]);
        if (d9 > 180D)
        {
            d9 -= 360D;
        }
        Coordinate coordinate2 = new Coordinate(d8, d9);
        return coordinate2;
    }

    public static Coordinate log2LatLon(DoublePoint doublepoint, long l, int i, Coordinate coordinate, double d, int j, 
            int k)
    {
        double d1;
        double d2;
        double d3;
        double d4;
        double ad[];
        d1 = doublepoint.x;
        d2 = -doublepoint.y;
        d3 = coordinate.lat;
        d4 = coordinate.lon;
        if (i != 3)
        {
            d1 = (doublepoint.x * (double)l) / 1000D / 10D;
            d2 = (-doublepoint.y * (double)l) / 1000D / 10D;
            d3 = Math.toRadians(coordinate.lat);
            d4 = Math.toRadians(coordinate.lon);
        }
        ad = null;
        i;
        JVM INSTR tableswitch 0 3: default 120
    //                   0 127
    //                   1 145
    //                   2 162
    //                   3 179;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        if (ad == null)
        {
            return null;
        }
        break; /* Loop/switch isn't completed */
_L2:
        ad = InvTMProjection(d1, d2, d3, d4, d);
        continue; /* Loop/switch isn't completed */
_L3:
        ad = InvMercator(d1, d2, d3, d4, 0);
        continue; /* Loop/switch isn't completed */
_L4:
        ad = InvMercator(d1, d2, d3, d4, 1);
        if (true) goto _L1; else goto _L6
_L5:
        double d5 = l;
        double ad1[] = InvYUSMercator(d1, d2, d5, j);
        Coordinate coordinate1 = new Coordinate(ad1[0], ad1[1]);
        return coordinate1;
_L6:
        double d6 = Math.toDegrees(ad[0]);
        double d7 = Math.toDegrees(ad[1]);
        if (d7 > 180D)
        {
            d7 -= 360D;
        }
        Coordinate coordinate2 = new Coordinate(d6, d7);
        return coordinate2;
    }

    public static Coordinate[] parse(String s)
    {
        Coordinate acoordinate[] = parse(s, null);
        Vector vector = new Vector();
        int i = 0;
        do
        {
            if (i >= acoordinate.length)
            {
                Coordinate acoordinate1[] = new Coordinate[vector.size()];
                vector.copyInto(acoordinate1);
                return acoordinate1;
            }
            if (acoordinate[i] != null)
            {
                vector.addElement(acoordinate[i]);
            }
            i++;
        } while (true);
    }

    public static Coordinate[] parse(String s, Coordinate coordinate)
    {
        String as[];
        Coordinate acoordinate[];
        int i;
        as = split(',', s.trim());
        acoordinate = new Coordinate[as.length / 2];
        i = 0;
_L2:
        if (i >= acoordinate.length)
        {
            return acoordinate;
        }
        if (!as[i * 2].trim().equals("") && !as[1 + i * 2].trim().equals(""))
        {
            break; /* Loop/switch isn't completed */
        }
        if (coordinate == null)
        {
            acoordinate[i] = null;
        } else
        {
            acoordinate[i] = new Coordinate(coordinate);
        }
_L7:
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        double d;
        int j;
        d = 0.0D;
        j = i * 2;
        String as1[] = split('/', as[j].trim());
        int k = 0;
_L9:
        if (k < as1.length) goto _L4; else goto _L3
_L3:
        double d1 = 0.0D;
        String as2[] = split('/', as[1 + i * 2].trim());
        int l = 0;
_L8:
        if (l < as2.length) goto _L6; else goto _L5
_L5:
        NumberFormatException numberformatexception;
        acoordinate[i] = new Coordinate(d, d1);
          goto _L7
_L4:
        double d2;
        double d3;
        try
        {
            if (!as1[k].trim().equals(""))
            {
                d += Double.parseDouble(as1[k].trim()) / pow(60D, k);
            }
            break MISSING_BLOCK_LABEL_310;
        }
        // Misplaced declaration of an exception variable
        catch (NumberFormatException numberformatexception)
        {
            if (coordinate == null)
            {
                acoordinate[i] = null;
            } else
            {
                acoordinate[i].setCoordinate(coordinate);
            }
        }
          goto _L7
_L6:
        if (as2[l].trim().equals(""))
        {
            break MISSING_BLOCK_LABEL_293;
        }
        d2 = Double.parseDouble(as2[l].trim());
        d3 = pow(60D, l);
        d1 += d2 / d3;
        l++;
          goto _L8
        k++;
          goto _L9
    }

    public static Coordinate[] parsere(String s, Coordinate coordinate)
    {
        String as[];
        Coordinate acoordinate[];
        int i;
        as = split(',', s.trim());
        acoordinate = new Coordinate[as.length / 2];
        i = 0;
_L2:
        if (i >= acoordinate.length)
        {
            return acoordinate;
        }
        if (!as[i * 2].trim().equals("") && !as[1 + i * 2].trim().equals(""))
        {
            break; /* Loop/switch isn't completed */
        }
        if (coordinate == null)
        {
            acoordinate[i] = null;
        } else
        {
            acoordinate[i] = new Coordinate(coordinate);
        }
_L7:
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        double d;
        int j;
        d = 0.0D;
        j = i * 2;
        String as1[] = split('.', as[j].trim());
        int k = 0;
_L15:
        if (k < as1.length) goto _L4; else goto _L3
_L3:
        double d1 = 0.0D;
        String as2[] = split('.', as[1 + i * 2].trim());
        int l = 0;
_L16:
        if (l < as2.length) goto _L6; else goto _L5
_L5:
        NumberFormatException numberformatexception;
        acoordinate[i] = new Coordinate(d, d1);
          goto _L7
_L4:
        if (as1[k].trim().equals("")) goto _L9; else goto _L8
_L8:
        if (k >= 2) goto _L11; else goto _L10
_L10:
        d += Double.parseDouble(as1[k]) / pow(60D, k);
          goto _L9
_L11:
        if (k != 2) goto _L13; else goto _L12
_L12:
        d += Double.parseDouble(as1[k]) / pow(60D, 2D);
          goto _L9
_L13:
        if (k != 3) goto _L9; else goto _L14
_L14:
        d += Double.parseDouble(as1[k]) / (10000D * pow(60D, 2D));
          goto _L9
_L6:
        if (as2[l].trim().equals(""))
        {
            break MISSING_BLOCK_LABEL_455;
        }
label0:
        {
            if (l >= 2)
            {
                break label0;
            }
            double d2;
            double d3;
            try
            {
                d1 += Double.parseDouble(as2[l]) / pow(60D, l);
                break MISSING_BLOCK_LABEL_455;
            }
            // Misplaced declaration of an exception variable
            catch (NumberFormatException numberformatexception)
            {
                if (coordinate == null)
                {
                    acoordinate[i] = null;
                } else
                {
                    acoordinate[i].setCoordinate(coordinate);
                }
            }
        }
          goto _L7
        if (l != 2)
        {
            break MISSING_BLOCK_LABEL_394;
        }
        d1 += Double.parseDouble(as2[l]) / pow(60D, 2D);
        break MISSING_BLOCK_LABEL_455;
        if (l != 3)
        {
            break MISSING_BLOCK_LABEL_455;
        }
        d2 = Double.parseDouble(as2[l]);
        d3 = pow(60D, 2D);
        d1 += d2 / (d3 * 10000D);
        break MISSING_BLOCK_LABEL_455;
_L9:
        k++;
          goto _L15
        l++;
          goto _L16
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

    public static String[] split(char c, String s)
    {
        Vector vector = new Vector();
        do
        {
            int i = s.indexOf(c);
            if (i < 0)
            {
                vector.addElement(s);
                String as[] = new String[vector.size()];
                vector.copyInto(as);
                return as;
            }
            vector.addElement(s.substring(0, i));
            if (i + 1 < s.length())
            {
                s = s.substring(i + 1);
            } else
            {
                s = "";
            }
        } while (true);
    }

    public Coordinate displace(int i, double d)
    {
        double ad[][];
        double ad3[];
        double ad4[];
        int j;
        if (i == 0)
        {
            return this;
        }
        double d1 = (double)i / 6370300D;
        double d2 = Math.cos((3.1415926535897931D * lat) / 180D);
        double d3 = Math.sin((3.1415926535897931D * lat) / 180D);
        double d4 = Math.cos((3.1415926535897931D * lon) / 180D);
        double d5 = Math.sin((3.1415926535897931D * lon) / 180D);
        double d6 = Math.cos((3.1415926535897931D * d) / 180D);
        double d7 = Math.sin((3.1415926535897931D * d) / 180D);
        double d8 = Math.cos(d1);
        double d9 = Math.sin(d1);
        ad = new double[3][];
        double ad1[] = new double[3];
        ad1[0] = -d5;
        ad1[1] = d3 * -d4;
        ad1[2] = d4 * d2;
        ad[0] = ad1;
        double ad2[] = new double[3];
        ad2[0] = d4;
        ad2[1] = d3 * -d5;
        ad2[2] = d5 * d2;
        ad[1] = ad2;
        ad[2] = (new double[] {
            0.0D, d2, d3
        });
        ad3 = new double[3];
        ad3[0] = d7 * d9;
        ad3[1] = d6 * d9;
        ad3[2] = d8;
        ad4 = (new double[] {
            0.0D, 0.0D, 0.0D
        });
        j = 0;
_L2:
        if (j >= 3)
        {
            double d10 = ad4[0];
            double d11 = ad4[1];
            double d12 = ad4[2];
            Coordinate coordinate = new Coordinate(180D * (atan2(d12, Math.sqrt(1.0D - d12 * d12)) / 3.1415926535897931D), 180D * (atan2(d11, d10) / 3.1415926535897931D));
            return coordinate;
        }
        int k = 0;
        do
        {
label0:
            {
                if (k < 3)
                {
                    break label0;
                }
                j++;
            }
            if (true)
            {
                continue;
            }
            ad4[j] = ad4[j] + ad[j][k] * ad3[k];
            k++;
        } while (true);
        if (true) goto _L2; else goto _L1
_L1:
    }

    public double distance(Coordinate coordinate)
    {
        double d = Math.cos((3.1415926535897931D * lon) / 180D) * Math.cos((3.1415926535897931D * lat) / 180D);
        double d1 = Math.sin((3.1415926535897931D * lon) / 180D) * Math.cos((3.1415926535897931D * lat) / 180D);
        double d2 = Math.sin((3.1415926535897931D * lat) / 180D);
        double d3 = Math.cos((3.1415926535897931D * coordinate.lon) / 180D) * Math.cos((3.1415926535897931D * coordinate.lat) / 180D);
        double d4 = Math.sin((3.1415926535897931D * coordinate.lon) / 180D) * Math.cos((3.1415926535897931D * coordinate.lat) / 180D);
        double d5 = Math.sin((3.1415926535897931D * coordinate.lat) / 180D);
        double d6 = d * d3 + d1 * d4 + d2 * d5;
        if (d6 < -1D)
        {
            d6 = -1D;
        }
        if (d6 > 1.0D)
        {
            d6 = 1.0D;
        }
        return 6370300D * acos(d6);
    }

    public Coordinate getCoordinate()
    {
        return this;
    }

    public double getLatitude()
    {
        return lat;
    }

    public double getLongitude()
    {
        return lon;
    }

    public void move(double d, double d1)
    {
        setCoordinate(d, d1);
    }

    public void setCoordinate(double d, double d1)
    {
        this;
        JVM INSTR monitorenter ;
        lat = d;
        lon = d1;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setCoordinate(Coordinate coordinate)
    {
        setCoordinate(coordinate.lat, coordinate.lon);
    }

    public double[] toDoubleArray()
    {
        double ad[] = new double[2];
        ad[0] = lat;
        ad[1] = lon;
        return ad;
    }

    public String toString()
    {
        byte byte0;
        byte byte1;
        double d;
        double d1;
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        String s;
        String s1;
        String s2;
        String s3;
        if (lat < 0.0D)
        {
            byte0 = -1;
        } else
        {
            byte0 = 1;
        }
        if (lon < 0.0D)
        {
            byte1 = -1;
        } else
        {
            byte1 = 1;
        }
        d = Math.abs(lat);
        d1 = Math.abs(lon);
        i = (int)d;
        j = (int)d1;
        k = (int)(60D * (d - (double)i));
        l = (int)(60D * (d1 - (double)j));
        i1 = (int)(3600D * (d - (double)i - (double)k / 60D));
        j1 = (int)(3600D * (d1 - (double)j - (double)l / 60D));
        k1 = (int)(3600000D * (d - (double)i - (double)k / 60D - (double)i1 / 3600D));
        l1 = (int)(3600000D * (d1 - (double)j - (double)l / 60D - (double)j1 / 3600D));
        s = (new StringBuilder("000")).append(k1).toString();
        s1 = s.substring(-3 + s.length());
        s2 = (new StringBuilder("000")).append(l1).toString();
        s3 = s2.substring(-3 + s2.length());
        return (new StringBuilder(String.valueOf(byte0 * i))).append("/").append(k).append("/").append(i1).append(".").append(s1).append(",").append(byte1 * j).append("/").append(l).append("/").append(j1).append(".").append(s3).toString();
    }

    public String toStringLat()
    {
        byte byte0;
        double d;
        int i;
        double d1;
        StringBuilder stringbuilder;
        String s;
        String s1;
        if (lat < 0.0D)
        {
            byte0 = -1;
        } else
        {
            byte0 = 1;
        }
        d = Math.abs(lat);
        i = (int)d;
        d1 = 60D * (d - (double)i);
        stringbuilder = new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(String.valueOf(byte0 * i)))).append(String.valueOf(d1)).append(",").toString()));
        if (lat < 0.0D)
        {
            s = "S";
        } else
        {
            s = "N";
        }
        s1 = stringbuilder.append(s).toString();
        System.out.print((new StringBuilder("\n")).append(i).append("::").append(d1).toString());
        return s1;
    }

    public String toStringLon()
    {
        byte byte0;
        double d;
        int i;
        double d1;
        StringBuilder stringbuilder;
        String s;
        if (lon < 0.0D)
        {
            byte0 = -1;
        } else
        {
            byte0 = 1;
        }
        d = Math.abs(lon);
        i = (int)d;
        d1 = 60D * (d - (double)i);
        stringbuilder = new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(String.valueOf(byte0 * i)))).append(String.valueOf(d1)).append(",").toString()));
        if (lon < 0.0D)
        {
            s = "W";
        } else
        {
            s = "E";
        }
        return stringbuilder.append(s).toString();
    }

    public void translate(double d, double d1)
    {
        setCoordinate(d + lat, d1 + lon);
    }

    public void translate(int i, int j)
    {
        setCoordinate(displace(i, j));
    }

    static 
    {
        RAD_PER_DEG = 0.017453292519943295D;
        MAXLEVEL = 18;
    }
}
