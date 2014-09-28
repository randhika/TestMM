// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

final class SoundexUtils
{

    SoundexUtils()
    {
    }

    static String clean(String s)
    {
        if (s == null || s.length() == 0)
        {
            return s;
        }
        int i = s.length();
        char ac[] = new char[i];
        int j = 0;
        int k = 0;
        while (j < i) 
        {
            int l;
            if (Character.isLetter(s.charAt(j)))
            {
                l = k + 1;
                ac[k] = s.charAt(j);
            } else
            {
                l = k;
            }
            j++;
            k = l;
        }
        if (k == i)
        {
            return s.toUpperCase();
        } else
        {
            return (new String(ac, 0, k)).toUpperCase();
        }
    }

    static int difference(StringEncoder stringencoder, String s, String s1)
        throws EncoderException
    {
        return differenceEncoded(stringencoder.encode(s), stringencoder.encode(s1));
    }

    static int differenceEncoded(String s, String s1)
    {
        int i;
        if (s == null || s1 == null)
        {
            i = 0;
        } else
        {
            int j = Math.min(s.length(), s1.length());
            i = 0;
            int k = 0;
            while (k < j) 
            {
                if (s.charAt(k) == s1.charAt(k))
                {
                    i++;
                }
                k++;
            }
        }
        return i;
    }
}
