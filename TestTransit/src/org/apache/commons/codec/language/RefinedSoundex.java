// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

// Referenced classes of package org.apache.commons.codec.language:
//            SoundexUtils

public class RefinedSoundex
    implements StringEncoder
{

    public static final RefinedSoundex US_ENGLISH = new RefinedSoundex();
    public static final char US_ENGLISH_MAPPING[] = "01360240043788015936020505".toCharArray();
    private char soundexMapping[];

    public RefinedSoundex()
    {
        this(US_ENGLISH_MAPPING);
    }

    public RefinedSoundex(char ac[])
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
            throw new EncoderException("Parameter supplied to RefinedSoundex encode is not of type java.lang.String");
        } else
        {
            return soundex((String)obj);
        }
    }

    public String encode(String s)
    {
        return soundex(s);
    }

    char getMappingCode(char c)
    {
        if (!Character.isLetter(c))
        {
            return '\0';
        } else
        {
            return soundexMapping[-65 + Character.toUpperCase(c)];
        }
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
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(s1.charAt(0));
        char c = '*';
        int i = 0;
        while (i < s1.length()) 
        {
            char c1 = getMappingCode(s1.charAt(i));
            if (c1 != c)
            {
                if (c1 != 0)
                {
                    stringbuffer.append(c1);
                }
                c = c1;
            }
            i++;
        }
        return stringbuffer.toString();
    }

}
