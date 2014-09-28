// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class YDigest
{

    public YDigest()
    {
    }

    public static String digest(InputStream inputstream, String s)
        throws IOException, NoSuchAlgorithmException
    {
        return toHexString(digestArray(inputstream, s));
    }

    public static String digest(String s, String s1)
        throws NoSuchAlgorithmException
    {
        return toHexString(digestArray(s, s1));
    }

    public static byte[] digestArray(InputStream inputstream, String s)
        throws IOException, NoSuchAlgorithmException
    {
        MessageDigest messagedigest = MessageDigest.getInstance(s);
        byte abyte0[] = new byte[4096];
_L1:
        int i = inputstream.read(abyte0, 0, abyte0.length);
        if (i >= 0)
        {
            try
            {
                messagedigest.update(abyte0, 0, i);
            }
            catch (IOException ioexception)
            {
                throw ioexception;
            }
        } else
        {
            return messagedigest.digest();
        }
          goto _L1
    }

    public static byte[] digestArray(String s, String s1)
        throws NoSuchAlgorithmException
    {
        return MessageDigest.getInstance(s1).digest(s.getBytes());
    }

    public static String md5(InputStream inputstream)
        throws IOException
    {
        String s;
        try
        {
            s = toHexString(digestArray(inputstream, "MD5"));
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception)
        {
            return "";
        }
        return s;
    }

    public static String md5(String s)
    {
        String s1;
        try
        {
            s1 = toHexString(digestArray(s, "MD5"));
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception)
        {
            return "";
        }
        return s1;
    }

    public static String sha1(InputStream inputstream)
        throws IOException
    {
        String s;
        try
        {
            s = toHexString(digestArray(inputstream, "SHA1"));
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception)
        {
            return "";
        }
        return s;
    }

    public static String sha1(String s)
    {
        String s1;
        try
        {
            s1 = toHexString(digestArray(s, "SHA1"));
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception)
        {
            return "";
        }
        return s1;
    }

    private static String toHexString(byte abyte0[])
    {
        StringBuffer stringbuffer = new StringBuffer(2 * abyte0.length);
        for (int i = 0; i < abyte0.length; i++)
        {
            String s = Integer.toHexString(0xff & abyte0[i]);
            if (s.length() == 1)
            {
                stringbuffer.append("0");
            }
            stringbuffer.append(s);
        }

        return stringbuffer.toString();
    }
}
