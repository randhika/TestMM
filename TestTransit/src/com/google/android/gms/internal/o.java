// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package com.google.android.gms.internal:
//            m

public class o
{
    public class a extends Exception
    {

        final o km;

        public a()
        {
            km = o.this;
            super();
        }

        public a(Throwable throwable)
        {
            km = o.this;
            super(throwable);
        }
    }


    private final m jQ;
    private final SecureRandom kl;

    public o(m m1, SecureRandom securerandom)
    {
        jQ = m1;
        kl = securerandom;
    }

    static void c(byte abyte0[])
    {
        for (int i = 0; i < abyte0.length; i++)
        {
            abyte0[i] = (byte)(0x44 ^ abyte0[i]);
        }

    }

    public byte[] b(String s)
        throws a
    {
        byte abyte0[];
        try
        {
            abyte0 = jQ.a(s, false);
            if (abyte0.length != 32)
            {
                throw new a();
            }
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw new a(illegalargumentexception);
        }
        byte abyte1[];
        ByteBuffer bytebuffer = ByteBuffer.wrap(abyte0, 4, 16);
        abyte1 = new byte[16];
        bytebuffer.get(abyte1);
        c(abyte1);
        return abyte1;
    }

    public byte[] c(byte abyte0[], String s)
        throws a
    {
        if (abyte0.length != 16)
        {
            throw new a();
        }
        byte abyte1[];
        ByteBuffer bytebuffer;
        byte abyte2[];
        byte abyte3[];
        SecretKeySpec secretkeyspec;
        Cipher cipher;
        byte abyte4[];
        try
        {
            abyte1 = jQ.a(s, false);
            if (abyte1.length <= 16)
            {
                throw new a();
            }
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception)
        {
            throw new a(nosuchalgorithmexception);
        }
        catch (InvalidKeyException invalidkeyexception)
        {
            throw new a(invalidkeyexception);
        }
        catch (IllegalBlockSizeException illegalblocksizeexception)
        {
            throw new a(illegalblocksizeexception);
        }
        catch (NoSuchPaddingException nosuchpaddingexception)
        {
            throw new a(nosuchpaddingexception);
        }
        catch (BadPaddingException badpaddingexception)
        {
            throw new a(badpaddingexception);
        }
        catch (InvalidAlgorithmParameterException invalidalgorithmparameterexception)
        {
            throw new a(invalidalgorithmparameterexception);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw new a(illegalargumentexception);
        }
        bytebuffer = ByteBuffer.allocate(abyte1.length);
        bytebuffer.put(abyte1);
        bytebuffer.flip();
        abyte2 = new byte[16];
        abyte3 = new byte[-16 + abyte1.length];
        bytebuffer.get(abyte2);
        bytebuffer.get(abyte3);
        secretkeyspec = new SecretKeySpec(abyte0, "AES");
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, secretkeyspec, new IvParameterSpec(abyte2));
        abyte4 = cipher.doFinal(abyte3);
        return abyte4;
    }
}
