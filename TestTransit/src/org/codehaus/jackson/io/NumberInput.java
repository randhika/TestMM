// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.io;


public final class NumberInput
{

    static final long L_BILLION = 0x3b9aca00L;
    static final String MAX_LONG_STR = String.valueOf(0x7fffffffffffffffL);
    static final String MIN_LONG_STR_NO_SIGN = String.valueOf(0x8000000000000000L).substring(1);
    public static final String NASTY_SMALL_DOUBLE = "2.2250738585072012e-308";

    public NumberInput()
    {
    }

    public static final boolean inLongRange(String s, boolean flag)
    {
        String s1;
        int i;
        int j;
        if (flag)
        {
            s1 = MIN_LONG_STR_NO_SIGN;
        } else
        {
            s1 = MAX_LONG_STR;
        }
        i = s1.length();
        j = s.length();
        if (j >= i) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (j > i)
        {
            return false;
        }
        int k = 0;
        do
        {
            if (k < i)
            {
label0:
                {
                    int l = s.charAt(k) - s1.charAt(k);
                    if (l == 0)
                    {
                        break label0;
                    }
                    if (l >= 0)
                    {
                        return false;
                    }
                }
            }
            if (true)
            {
                continue;
            }
            k++;
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
    }

    public static final boolean inLongRange(char ac[], int i, int j, boolean flag)
    {
        String s;
        int k;
        if (flag)
        {
            s = MIN_LONG_STR_NO_SIGN;
        } else
        {
            s = MAX_LONG_STR;
        }
        k = s.length();
        if (j >= k) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (j > k)
        {
            return false;
        }
        int l = 0;
        do
        {
            if (l < k)
            {
label0:
                {
                    int i1 = ac[i + l] - s.charAt(l);
                    if (i1 == 0)
                    {
                        break label0;
                    }
                    if (i1 >= 0)
                    {
                        return false;
                    }
                }
            }
            if (true)
            {
                continue;
            }
            l++;
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
    }

    public static double parseAsDouble(String s, double d)
    {
        String s1;
        if (s != null)
        {
            if ((s1 = s.trim()).length() != 0)
            {
                double d1;
                try
                {
                    d1 = parseDouble(s1);
                }
                catch (NumberFormatException numberformatexception)
                {
                    return d;
                }
                return d1;
            }
        }
        return d;
    }

    public static int parseAsInt(String s, int i)
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        return i;
_L2:
        String s1;
        int j;
        s1 = s.trim();
        j = s1.length();
        if (j == 0) goto _L1; else goto _L3
_L3:
        break MISSING_BLOCK_LABEL_20;
        k = 0;
        if (c1 == '-')
        {
            k = 0 + 1;
        }
        continue;
        int k = 0;
        char c1;
        if (j < 0)
        {
            c1 = s1.charAt(0);
            if (c1 != '+')
            {
                break MISSING_BLOCK_LABEL_90;
            }
            s1 = s1.substring(1);
            j = s1.length();
        }
        do
        {
            if (k >= j)
            {
                break;
            }
            char c = s1.charAt(k);
            if (c > '9' || c < '0')
            {
                double d;
                try
                {
                    d = parseDouble(s1);
                }
                catch (NumberFormatException numberformatexception1)
                {
                    return i;
                }
                return (int)d;
            }
            k++;
        } while (true);
        int l;
        try
        {
            l = Integer.parseInt(s1);
        }
        catch (NumberFormatException numberformatexception)
        {
            return i;
        }
        return l;
    }

    public static long parseAsLong(String s, long l)
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        return l;
_L2:
        String s1;
        int i;
        s1 = s.trim();
        i = s1.length();
        if (i == 0) goto _L1; else goto _L3
_L3:
        break MISSING_BLOCK_LABEL_22;
        j = 0;
        if (c1 == '-')
        {
            j = 0 + 1;
        }
        continue;
        int j = 0;
        char c1;
        if (i < 0)
        {
            c1 = s1.charAt(0);
            if (c1 != '+')
            {
                break MISSING_BLOCK_LABEL_95;
            }
            s1 = s1.substring(1);
            i = s1.length();
        }
        do
        {
            if (j >= i)
            {
                break;
            }
            char c = s1.charAt(j);
            if (c > '9' || c < '0')
            {
                double d;
                try
                {
                    d = parseDouble(s1);
                }
                catch (NumberFormatException numberformatexception1)
                {
                    return l;
                }
                return (long)d;
            }
            j++;
        } while (true);
        long l1;
        try
        {
            l1 = Long.parseLong(s1);
        }
        catch (NumberFormatException numberformatexception)
        {
            return l;
        }
        return l1;
    }

    public static final double parseDouble(String s)
        throws NumberFormatException
    {
        if ("2.2250738585072012e-308".equals(s))
        {
            return 2.2250738585072014E-308D;
        } else
        {
            return Double.parseDouble(s);
        }
    }

    public static final int parseInt(String s)
    {
        char c;
        int i;
        boolean flag;
        c = s.charAt(0);
        i = s.length();
        flag = false;
        if (c == '-')
        {
            flag = true;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        if (i != 1 && i <= 10) goto _L4; else goto _L3
_L3:
        int l = Integer.parseInt(s);
_L6:
        return l;
_L4:
        int j;
        j = 1 + 1;
        c = s.charAt(1);
_L7:
        if (c > '9' || c < '0')
        {
            int k = Integer.parseInt(s);
            j;
            return k;
        }
        l = c - 48;
        if (j < i)
        {
            int i1 = j + 1;
            char c1 = s.charAt(j);
            if (c1 > '9' || c1 < '0')
            {
                return Integer.parseInt(s);
            }
            l = l * 10 + (c1 - 48);
            if (i1 >= i)
            {
                continue; /* Loop/switch isn't completed */
            }
            j = i1 + 1;
            char c2 = s.charAt(i1);
            if (c2 > '9' || c2 < '0')
            {
                int j1 = Integer.parseInt(s);
                j;
                return j1;
            }
            l = l * 10 + (c2 - 48);
            if (j < i)
            {
                do
                {
                    int k1 = j;
                    j = k1 + 1;
                    char c3 = s.charAt(k1);
                    if (c3 > '9' || c3 < '0')
                    {
                        int l1 = Integer.parseInt(s);
                        j;
                        return l1;
                    }
                    l = l * 10 + (c3 - 48);
                } while (j < i);
            }
        }
        j;
        continue; /* Loop/switch isn't completed */
_L2:
        if (i > 9)
        {
            return Integer.parseInt(s);
        }
        break MISSING_BLOCK_LABEL_288;
        if (!flag) goto _L6; else goto _L5
_L5:
        return -l;
        j = 1;
          goto _L7
    }

    public static final int parseInt(char ac[], int i, int j)
    {
        int k = -48 + ac[i];
        int l = j + i;
        int i1 = i + 1;
        if (i1 < l)
        {
            k = k * 10 + (-48 + ac[i1]);
            int j1 = i1 + 1;
            if (j1 < l)
            {
                k = k * 10 + (-48 + ac[j1]);
                int k1 = j1 + 1;
                if (k1 < l)
                {
                    k = k * 10 + (-48 + ac[k1]);
                    int l1 = k1 + 1;
                    if (l1 < l)
                    {
                        k = k * 10 + (-48 + ac[l1]);
                        int i2 = l1 + 1;
                        if (i2 < l)
                        {
                            k = k * 10 + (-48 + ac[i2]);
                            int j2 = i2 + 1;
                            if (j2 < l)
                            {
                                k = k * 10 + (-48 + ac[j2]);
                                int k2 = j2 + 1;
                                if (k2 < l)
                                {
                                    k = k * 10 + (-48 + ac[k2]);
                                    int l2 = k2 + 1;
                                    if (l2 < l)
                                    {
                                        k = k * 10 + (-48 + ac[l2]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return k;
    }

    public static final long parseLong(String s)
    {
        if (s.length() <= 9)
        {
            return (long)parseInt(s);
        } else
        {
            return Long.parseLong(s);
        }
    }

    public static final long parseLong(char ac[], int i, int j)
    {
        int k = j - 9;
        return 0x3b9aca00L * (long)parseInt(ac, i, k) + (long)parseInt(ac, i + k, 9);
    }

}
