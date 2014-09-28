// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common.util;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common.util:
//            CipherObject

public class CipherUtil
{

    private static final String ALGORITHM = "AES";
    private static final String DEFAULT_SEED = "default_seed";
    private static final String RAMDOM_ALGORITHM = "SHA1PRNG";
    private static final int SECRET_KEY_SIZE = 256;
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    public CipherUtil()
    {
    }

    public static byte[] decrypt(byte abyte0[], CipherObject cipherobject)
        throws Exception
    {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, new SecretKeySpec(abyte0, "AES"), new IvParameterSpec(cipherobject.getIv()));
        return cipher.doFinal(cipherobject.getEncryptedString());
    }

    public static CipherObject encrypt(byte abyte0[], byte abyte1[])
        throws Exception
    {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, new SecretKeySpec(abyte0, "AES"));
        return new CipherObject(cipher.getIV(), cipher.doFinal(abyte1));
    }

    public static byte[] generateSecretKey()
        throws Exception
    {
        SecureRandom securerandom = SecureRandom.getInstance("SHA1PRNG");
        securerandom.setSeed(generateSeed());
        KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
        keygenerator.init(256, securerandom);
        return keygenerator.generateKey().getEncoded();
    }

    private static byte[] generateSeed()
    {
        return "default_seed".getBytes();
    }
}
