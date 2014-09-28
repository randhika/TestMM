// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Referenced classes of package jp.co.yahoo.android.common.hamburger:
//            YIOUtils

public class YMD5
{

    public YMD5()
    {
    }

    public static String crypt(InputStream inputstream)
    {
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_11;
        }
        if (inputstream.available() != 0)
        {
            break MISSING_BLOCK_LABEL_21;
        }
        throw new IllegalArgumentException("InputStream can't be null or zero length.");
        MessageDigest messagedigest = MessageDigest.getInstance("MD5");
        byte abyte0[] = new byte[8192];
_L1:
        int i = inputstream.read(abyte0, 0, abyte0.length);
        if (i < 0)
        {
            Exception exception;
            String s;
            try
            {
                YIOUtils.closeQuietely(inputstream);
                s = hashByte2MD5(messagedigest.digest());
            }
            catch (IOException ioexception)
            {
                return null;
            }
            catch (NoSuchAlgorithmException nosuchalgorithmexception)
            {
                return null;
            }
            return s;
        }
        messagedigest.update(abyte0, 0, i);
          goto _L1
        exception;
        YIOUtils.closeQuietely(inputstream);
        throw exception;
    }

    public static String crypt(String s)
    {
        if (s == null || s.length() == 0)
        {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }
        String s1;
        try
        {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update(s.getBytes());
            s1 = hashByte2MD5(messagedigest.digest());
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception)
        {
            return null;
        }
        return s1;
    }

    public static String crypt(byte abyte0[])
    {
        if (abyte0 == null || abyte0.length == 0)
        {
            throw new IllegalArgumentException("bytes to encript cannot be null or zero length");
        }
        String s;
        try
        {
            s = hashByte2MD5(MessageDigest.getInstance("MD5").digest(abyte0));
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception)
        {
            return null;
        }
        return s;
    }

    private static String hashByte2MD5(byte abyte0[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i = 0;
        do
        {
            if (i >= abyte0.length)
            {
                return stringbuffer.toString();
            }
            if ((0xff & abyte0[i]) < 16)
            {
                stringbuffer.append((new StringBuilder("0")).append(Integer.toHexString(0xff & abyte0[i])).toString());
            } else
            {
                stringbuffer.append(Integer.toHexString(0xff & abyte0[i]));
            }
            i++;
        } while (true);
    }
}
