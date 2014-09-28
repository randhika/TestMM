// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.oidc;


public class IdTokenObject
{

    private String aud;
    private long exp;
    private long iat;
    private String iss;
    private String nonce;
    private String userId;

    public IdTokenObject()
    {
    }

    public IdTokenObject(String s, String s1, String s2, String s3, long l, long l1)
    {
        setIss(s);
        setUserId(s1);
        setAud(s2);
        setNonce(s3);
        setExp(l);
        setIat(l1);
    }

    public String getAud()
    {
        return aud;
    }

    public long getExp()
    {
        return exp;
    }

    public long getIat()
    {
        return iat;
    }

    public String getIss()
    {
        return iss;
    }

    public String getNonce()
    {
        return nonce;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setAud(String s)
    {
        aud = s;
    }

    public void setExp(long l)
    {
        exp = l;
    }

    public void setIat(long l)
    {
        iat = l;
    }

    public void setIss(String s)
    {
        iss = s;
    }

    public void setNonce(String s)
    {
        nonce = s;
    }

    public void setUserId(String s)
    {
        userId = s;
    }

    public String toString()
    {
        return (new StringBuilder()).append("{\"iss\":\"").append(iss).append("\",").append("\"user_id\":\"").append(userId).append("\",").append("\"aud\":\"").append(aud).append("\",").append("\"nonce\":\"").append(nonce).append("\",").append("\"exp\":").append(exp).append(",").append("\"iat\":").append(iat).append("}").toString();
    }
}
