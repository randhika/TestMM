// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.data.cipher;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;

// Referenced classes of package jp.co.yahoo.yconnect.data.cipher:
//            CipherObject

public class CipherUtil
{

    private static final String ALGORITHM = "AES";
    private static final String DEFAULT_SEED = "default_seed";
    private static final String RAMDOM_ALGORITHM = "SHA1PRNG";
    private static final int SECRET_KEY_SIZE = 256;
    private static final String TAG = jp/co/yahoo/yconnect/data/cipher/CipherUtil.getSimpleName();
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    public CipherUtil()
    {
    }

    public static byte[] decrypt(byte abyte0[], CipherObject cipherobject)
        throws Exception
    {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, new SecretKeySpec(abyte0, "AES"), new IvParameterSpec(cipherobject.getIv()));
        YConnectLogger.debug(TAG, "Decrypted the data and returned encrypted data of type byte array.");
        return cipher.doFinal(cipherobject.getEncryptedString());
    }

    public static CipherObject encrypt(byte abyte0[], byte abyte1[])
        throws Exception
    {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, new SecretKeySpec(abyte0, "AES"));
        byte abyte2[] = cipher.getIV();
        byte abyte3[] = cipher.doFinal(abyte1);
        YConnectLogger.debug(TAG, "Encrypted the data and returned CipherObject which has iv and encrypted data.");
        return new CipherObject(abyte2, abyte3);
    }

    public static byte[] generateSecretKey()
        throws Exception
    {
        SecureRandom securerandom = SecureRandom.getInstance("SHA1PRNG");
        securerandom.setSeed(generateSeed());
        KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
        keygenerator.init(256, securerandom);
        YConnectLogger.debug(TAG, "Finished generating secret key.");
        return keygenerator.generateKey().getEncoded();
    }

    private static byte[] generateSeed()
    {
        return "default_seed".getBytes();
    }

}
