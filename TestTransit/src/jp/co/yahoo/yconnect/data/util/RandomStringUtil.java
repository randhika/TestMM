// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.data.util;

import java.security.MessageDigest;
import java.util.UUID;

public class RandomStringUtil
{

    private MessageDigest digest;
    private String uuid;

    public RandomStringUtil()
        throws Exception
    {
        uuid = UUID.randomUUID().toString();
        if (digest == null)
        {
            digest = MessageDigest.getInstance("MD5");
        }
    }

    private String encryptMD5(String s)
    {
        digest.update(s.getBytes());
        byte abyte0[] = digest.digest();
        StringBuffer stringbuffer = new StringBuffer();
        int i = abyte0.length;
        for (int j = 0; j < i; j++)
        {
            String s1;
            for (s1 = Integer.toHexString(0xff & abyte0[j]); s1.length() < 2; s1 = (new StringBuilder()).append("0").append(s1).toString()) { }
            stringbuffer.append(s1);
        }

        return stringbuffer.toString();
    }

    public String generateNonce()
    {
        return encryptMD5(uuid.substring(uuid.length() / 2));
    }

    public String generateState()
    {
        return encryptMD5(uuid.substring(0, uuid.length() / 2));
    }
}
