// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5
{

    public MD5()
    {
    }

    public static String crypt(InputStream inputstream)
        throws NoSuchAlgorithmException, IOException
    {
        MessageDigest messagedigest;
        if (inputstream == null || inputstream.available() == 0)
        {
            throw new IllegalArgumentException("InputStream can't be null or zero length.");
        }
        messagedigest = MessageDigest.getInstance("MD5");
        byte abyte0[] = new byte[4096];
_L1:
        int i = inputstream.read(abyte0, 0, abyte0.length);
        if (i < 0)
        {
            break MISSING_BLOCK_LABEL_79;
        }
        messagedigest.update(abyte0, 0, i);
          goto _L1
        IOException ioexception1;
        ioexception1;
        throw ioexception1;
        Exception exception;
        exception;
        IOException ioexception2;
        if (inputstream != null)
        {
            try
            {
                inputstream.close();
            }
            catch (IOException ioexception) { }
        }
        throw exception;
        if (inputstream != null)
        {
            try
            {
                inputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch (IOException ioexception2) { }
        }
        return hashByte2MD5(messagedigest.digest());
    }

    public static String crypt(String s)
        throws NoSuchAlgorithmException
    {
        if (s == null || s.length() == 0)
        {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        } else
        {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update(s.getBytes());
            return hashByte2MD5(messagedigest.digest());
        }
    }

    public static String crypt(byte abyte0[])
        throws NoSuchAlgorithmException
    {
        if (abyte0 == null || abyte0.length == 0)
        {
            throw new IllegalArgumentException("bytes to encript cannot be null or zero length");
        } else
        {
            return hashByte2MD5(MessageDigest.getInstance("MD5").digest(abyte0));
        }
    }

    private static String hashByte2MD5(byte abyte0[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i = 0;
        while (i < abyte0.length) 
        {
            if ((0xff & abyte0[i]) < 16)
            {
                stringbuffer.append((new StringBuilder()).append("0").append(Integer.toHexString(0xff & abyte0[i])).toString());
            } else
            {
                stringbuffer.append(Integer.toHexString(0xff & abyte0[i]));
            }
            i++;
        }
        return stringbuffer.toString();
    }
}
