// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

// Referenced classes of package org.apache.commons.codec.language:
//            SoundexUtils

public class Soundex
    implements StringEncoder
{

    public static final Soundex US_ENGLISH = new Soundex();
    public static final char US_ENGLISH_MAPPING[] = "01230120022455012623010202".toCharArray();
    public static final String US_ENGLISH_MAPPING_STRING = "01230120022455012623010202";
    private int maxLength;
    private char soundexMapping[];

    public Soundex()
    {
        this(US_ENGLISH_MAPPING);
    }

    public Soundex(char ac[])
    {
        maxLength = 4;
        setSoundexMapping(ac);
    }

    private char getMappingCode(String s, int i)
    {
        char c = map(s.charAt(i));
        if (i > 1 && c != '0')
        {
            char c1 = s.charAt(i - 1);
            if ('H' == c1 || 'W' == c1)
            {
                char c2 = s.charAt(i - 2);
                if (map(c2) == c || 'H' == c2 || 'W' == c2)
                {
                    c = '\0';
                }
            }
        }
        return c;
    }

    private char[] getSoundexMapping()
    {
        return soundexMapping;
    }

    private char map(char c)
    {
        int i = c - 65;
        if (i < 0 || i >= getSoundexMapping().length)
        {
            throw new IllegalArgumentException("The character is not mapped: " + c);
        } else
        {
            return getSoundexMapping()[i];
        }
    }

    private void setSoundexMapping(char ac[])
    {
        soundexMapping = ac;
    }

    public int difference(String s, String s1)
        throws EncoderException
    {
        return SoundexUtils.difference(this, s, s1);
    }

    public Object encode(Object obj)
        throws EncoderException
    {
        if (!(obj instanceof String))
        {
            throw new EncoderException("Parameter supplied to Soundex encode is not of type java.lang.String");
        } else
        {
            return soundex((String)obj);
        }
    }

    public String encode(String s)
    {
        return soundex(s);
    }

    public int getMaxLength()
    {
        return maxLength;
    }

    public void setMaxLength(int i)
    {
        maxLength = i;
    }

    public String soundex(String s)
    {
        if (s == null)
        {
            return null;
        }
        String s1 = SoundexUtils.clean(s);
        if (s1.length() == 0)
        {
            return s1;
        }
        char ac[] = {
            '0', '0', '0', '0'
        };
        int i = 1;
        int j = 1;
        ac[0] = s1.charAt(0);
        char c = getMappingCode(s1, 0);
        while (i < s1.length() && j < ac.length) 
        {
            int k = i + 1;
            char c1 = getMappingCode(s1, i);
            if (c1 != 0)
            {
                if (c1 != '0' && c1 != c)
                {
                    int l = j + 1;
                    ac[j] = c1;
                    j = l;
                }
                c = c1;
                i = k;
            } else
            {
                i = k;
            }
        }
        return new String(ac);
    }

}
