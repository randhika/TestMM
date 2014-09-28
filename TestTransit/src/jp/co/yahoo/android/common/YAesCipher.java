// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class YAesCipher
{

    private static final String ALGORITHM = "PBEWithSHA256and256bitAES-CBC-BC";
    private static final int INTERATION_COUNT = 20;
    private static byte SALT[] = {
        110, 101, 80, 67, 85, 87, 49, 112
    };
    private static final String TAG = "YAesCipher";
    private PBEParameterSpec _pbeParamSpec;

    public YAesCipher()
    {
        _pbeParamSpec = new PBEParameterSpec(SALT, 20);
    }

    private byte[] doFinal(char ac[], byte abyte0[], int i)
    {
        byte abyte1[];
        SecretKey secretkey = generateKey(ac);
        Cipher cipher = getCipher();
        cipher.init(i, secretkey, _pbeParamSpec);
        abyte1 = cipher.doFinal(abyte0);
        return abyte1;
        NoSuchAlgorithmException nosuchalgorithmexception;
        nosuchalgorithmexception;
        nosuchalgorithmexception.printStackTrace();
_L2:
        return null;
        NoSuchPaddingException nosuchpaddingexception;
        nosuchpaddingexception;
        nosuchpaddingexception.printStackTrace();
        continue; /* Loop/switch isn't completed */
        InvalidKeyException invalidkeyexception;
        invalidkeyexception;
        invalidkeyexception.printStackTrace();
        continue; /* Loop/switch isn't completed */
        InvalidAlgorithmParameterException invalidalgorithmparameterexception;
        invalidalgorithmparameterexception;
        invalidalgorithmparameterexception.printStackTrace();
        continue; /* Loop/switch isn't completed */
        IllegalBlockSizeException illegalblocksizeexception;
        illegalblocksizeexception;
        illegalblocksizeexception.printStackTrace();
        continue; /* Loop/switch isn't completed */
        BadPaddingException badpaddingexception;
        badpaddingexception;
        badpaddingexception.printStackTrace();
        if (true) goto _L2; else goto _L1
_L1:
    }

    private SecretKey generateKey(char ac[])
    {
        SecretKey secretkey;
        PBEKeySpec pbekeyspec = new PBEKeySpec(ac);
        secretkey = SecretKeyFactory.getInstance("PBEWithSHA256and256bitAES-CBC-BC").generateSecret(pbekeyspec);
        return secretkey;
        NoSuchAlgorithmException nosuchalgorithmexception;
        nosuchalgorithmexception;
        nosuchalgorithmexception.printStackTrace();
_L2:
        return null;
        InvalidKeySpecException invalidkeyspecexception;
        invalidkeyspecexception;
        invalidkeyspecexception.printStackTrace();
        if (true) goto _L2; else goto _L1
_L1:
    }

    private Cipher getCipher()
        throws NoSuchAlgorithmException, NoSuchPaddingException
    {
        return Cipher.getInstance("PBEWithSHA256and256bitAES-CBC-BC");
    }

    public byte[] decrypt(char ac[], byte abyte0[])
    {
        return doFinal(ac, abyte0, 2);
    }

    public byte[] encrypt(char ac[], byte abyte0[])
    {
        return doFinal(ac, abyte0, 1);
    }

    public void setSalt(byte abyte0[])
    {
        _pbeParamSpec = new PBEParameterSpec(abyte0, 20);
    }

}
