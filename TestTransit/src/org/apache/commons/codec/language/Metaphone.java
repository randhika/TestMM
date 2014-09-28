// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

public class Metaphone
    implements StringEncoder
{

    private String frontv;
    private int maxCodeLen;
    private String varson;
    private String vowels;

    public Metaphone()
    {
        vowels = "AEIOU";
        frontv = "EIY";
        varson = "CSPTG";
        maxCodeLen = 4;
    }

    private boolean isLastChar(int i, int j)
    {
        return j + 1 == i;
    }

    private boolean isNextChar(StringBuffer stringbuffer, int i, char c)
    {
label0:
        {
            boolean flag = false;
            if (i >= 0)
            {
                int j = -1 + stringbuffer.length();
                flag = false;
                if (i < j)
                {
                    if (stringbuffer.charAt(i + 1) != c)
                    {
                        break label0;
                    }
                    flag = true;
                }
            }
            return flag;
        }
        return false;
    }

    private boolean isPreviousChar(StringBuffer stringbuffer, int i, char c)
    {
label0:
        {
            boolean flag = false;
            if (i > 0)
            {
                int j = stringbuffer.length();
                flag = false;
                if (i < j)
                {
                    if (stringbuffer.charAt(i - 1) != c)
                    {
                        break label0;
                    }
                    flag = true;
                }
            }
            return flag;
        }
        return false;
    }

    private boolean isVowel(StringBuffer stringbuffer, int i)
    {
        return vowels.indexOf(stringbuffer.charAt(i)) >= 0;
    }

    private boolean regionMatch(StringBuffer stringbuffer, int i, String s)
    {
        boolean flag = false;
        if (i >= 0)
        {
            int j = -1 + (i + s.length());
            int k = stringbuffer.length();
            flag = false;
            if (j < k)
            {
                flag = stringbuffer.substring(i, i + s.length()).equals(s);
            }
        }
        return flag;
    }

    public Object encode(Object obj)
        throws EncoderException
    {
        if (!(obj instanceof String))
        {
            throw new EncoderException("Parameter supplied to Metaphone encode is not of type java.lang.String");
        } else
        {
            return metaphone((String)obj);
        }
    }

    public String encode(String s)
    {
        return metaphone(s);
    }

    public int getMaxCodeLen()
    {
        return maxCodeLen;
    }

    public boolean isMetaphoneEqual(String s, String s1)
    {
        return metaphone(s).equals(metaphone(s1));
    }

    public String metaphone(String s)
    {
        char ac[];
        StringBuffer stringbuffer;
        StringBuffer stringbuffer1;
        if (s == null || s.length() == 0)
        {
            return "";
        }
        if (s.length() == 1)
        {
            return s.toUpperCase();
        }
        ac = s.toUpperCase().toCharArray();
        stringbuffer = new StringBuffer(40);
        stringbuffer1 = new StringBuffer(10);
        ac[0];
        JVM INSTR lookupswitch 6: default 116
    //                   65: 235
    //                   71: 204
    //                   75: 204
    //                   80: 204
    //                   87: 266
    //                   88: 326;
           goto _L1 _L2 _L3 _L3 _L3 _L4 _L5
_L1:
        stringbuffer.append(ac);
_L9:
        int i;
        i = stringbuffer.length();
_L8:
        int j;
        char c;
        for (j = 0; stringbuffer1.length() >= getMaxCodeLen() || j >= i;)
        {
            break MISSING_BLOCK_LABEL_1322;
        }

        c = stringbuffer.charAt(j);
        if (c == 'C' || !isPreviousChar(stringbuffer, j, c)) goto _L7; else goto _L6
_L6:
        j++;
_L27:
        if (stringbuffer1.length() > getMaxCodeLen())
        {
            stringbuffer1.setLength(getMaxCodeLen());
        }
          goto _L8
_L3:
        if (ac[1] == 'N')
        {
            stringbuffer.append(ac, 1, -1 + ac.length);
        } else
        {
            stringbuffer.append(ac);
        }
          goto _L9
_L2:
        if (ac[1] == 'E')
        {
            stringbuffer.append(ac, 1, -1 + ac.length);
        } else
        {
            stringbuffer.append(ac);
        }
          goto _L9
_L4:
        if (ac[1] == 'R')
        {
            stringbuffer.append(ac, 1, -1 + ac.length);
        } else
        if (ac[1] == 'H')
        {
            stringbuffer.append(ac, 1, -1 + ac.length);
            stringbuffer.setCharAt(0, 'W');
        } else
        {
            stringbuffer.append(ac);
        }
          goto _L9
_L5:
        ac[0] = 'S';
        stringbuffer.append(ac);
          goto _L9
_L7:
        c;
        JVM INSTR tableswitch 65 90: default 460
    //                   65 466
    //                   66 482
    //                   67 516
    //                   68 720
    //                   69 466
    //                   70 1013
    //                   71 788
    //                   72 956
    //                   73 466
    //                   74 1013
    //                   75 1024
    //                   76 1013
    //                   77 1013
    //                   78 1013
    //                   79 466
    //                   80 1063
    //                   81 1097
    //                   82 1013
    //                   83 1108
    //                   84 1166
    //                   85 466
    //                   86 1247
    //                   87 1258
    //                   88 1292
    //                   89 1258
    //                   90 1311;
           goto _L10 _L11 _L12 _L13 _L14 _L11 _L15 _L16 _L17 _L11 _L15 _L18 _L15 _L15 _L15 _L11 _L19 _L20 _L15 _L21 _L22 _L11 _L23 _L24 _L25 _L24 _L26
_L10:
        break; /* Loop/switch isn't completed */
_L26:
        break MISSING_BLOCK_LABEL_1311;
_L28:
        j++;
          goto _L27
_L11:
        if (j == 0)
        {
            stringbuffer1.append(c);
        }
          goto _L28
_L12:
        if (!isPreviousChar(stringbuffer, j, 'M') || !isLastChar(i, j))
        {
            stringbuffer1.append(c);
        }
          goto _L28
_L13:
        if (!isPreviousChar(stringbuffer, j, 'S') || isLastChar(i, j) || frontv.indexOf(stringbuffer.charAt(j + 1)) < 0)
        {
            if (regionMatch(stringbuffer, j, "CIA"))
            {
                stringbuffer1.append('X');
            } else
            if (!isLastChar(i, j) && frontv.indexOf(stringbuffer.charAt(j + 1)) >= 0)
            {
                stringbuffer1.append('S');
            } else
            if (isPreviousChar(stringbuffer, j, 'S') && isNextChar(stringbuffer, j, 'H'))
            {
                stringbuffer1.append('K');
            } else
            if (isNextChar(stringbuffer, j, 'H'))
            {
                if (j == 0 && i >= 3 && isVowel(stringbuffer, 2))
                {
                    stringbuffer1.append('K');
                } else
                {
                    stringbuffer1.append('X');
                }
            } else
            {
                stringbuffer1.append('K');
            }
        }
          goto _L28
_L14:
        if (!isLastChar(i, j + 1) && isNextChar(stringbuffer, j, 'G') && frontv.indexOf(stringbuffer.charAt(j + 2)) >= 0)
        {
            stringbuffer1.append('J');
            j += 2;
        } else
        {
            stringbuffer1.append('T');
        }
          goto _L28
_L16:
        if ((!isLastChar(i, j + 1) || !isNextChar(stringbuffer, j, 'H')) && (isLastChar(i, j + 1) || !isNextChar(stringbuffer, j, 'H') || isVowel(stringbuffer, j + 2)) && (j <= 0 || !regionMatch(stringbuffer, j, "GN") && !regionMatch(stringbuffer, j, "GNED")))
        {
            boolean flag;
            if (isPreviousChar(stringbuffer, j, 'G'))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!isLastChar(i, j) && frontv.indexOf(stringbuffer.charAt(j + 1)) >= 0 && !flag)
            {
                stringbuffer1.append('J');
            } else
            {
                stringbuffer1.append('K');
            }
        }
          goto _L28
_L17:
        if (!isLastChar(i, j) && (j <= 0 || varson.indexOf(stringbuffer.charAt(j - 1)) < 0) && isVowel(stringbuffer, j + 1))
        {
            stringbuffer1.append('H');
        }
          goto _L28
_L15:
        stringbuffer1.append(c);
          goto _L28
_L18:
        if (j > 0)
        {
            if (!isPreviousChar(stringbuffer, j, 'C'))
            {
                stringbuffer1.append(c);
            }
        } else
        {
            stringbuffer1.append(c);
        }
          goto _L28
_L19:
        if (isNextChar(stringbuffer, j, 'H'))
        {
            stringbuffer1.append('F');
        } else
        {
            stringbuffer1.append(c);
        }
          goto _L28
_L20:
        stringbuffer1.append('K');
          goto _L28
_L21:
        if (regionMatch(stringbuffer, j, "SH") || regionMatch(stringbuffer, j, "SIO") || regionMatch(stringbuffer, j, "SIA"))
        {
            stringbuffer1.append('X');
        } else
        {
            stringbuffer1.append('S');
        }
          goto _L28
_L22:
        if (regionMatch(stringbuffer, j, "TIA") || regionMatch(stringbuffer, j, "TIO"))
        {
            stringbuffer1.append('X');
        } else
        if (!regionMatch(stringbuffer, j, "TCH"))
        {
            if (regionMatch(stringbuffer, j, "TH"))
            {
                stringbuffer1.append('0');
            } else
            {
                stringbuffer1.append('T');
            }
        }
          goto _L28
_L23:
        stringbuffer1.append('F');
          goto _L28
_L24:
        if (!isLastChar(i, j) && isVowel(stringbuffer, j + 1))
        {
            stringbuffer1.append(c);
        }
          goto _L28
_L25:
        stringbuffer1.append('K');
        stringbuffer1.append('S');
          goto _L28
        stringbuffer1.append('S');
          goto _L28
        return stringbuffer1.toString();
          goto _L8
    }

    public void setMaxCodeLen(int i)
    {
        maxCodeLen = i;
    }
}
