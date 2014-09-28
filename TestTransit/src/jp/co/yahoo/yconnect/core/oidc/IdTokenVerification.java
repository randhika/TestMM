// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.oidc;

import jp.co.yahoo.yconnect.core.util.YConnectLogger;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.yconnect.core.oidc:
//            IdTokenException, IdTokenObject

public class IdTokenVerification
{

    private static long ACCEPTABLE_RANGE = 600L;
    private static final String TAG = jp/co/yahoo/yconnect/core/oidc/IdTokenVerification.getSimpleName();

    public IdTokenVerification()
    {
    }

    public static boolean check(String s, String s1, String s2, String s3, long l)
        throws IdTokenException, Exception
    {
        IdTokenObject idtokenobject = parseIdToken(s3);
        return check(s, s1, s2, idtokenobject.getIss(), idtokenobject.getAud(), idtokenobject.getExp(), idtokenobject.getIat(), idtokenobject.getNonce(), l);
    }

    public static boolean check(String s, String s1, String s2, String s3, String s4, long l, long l1, String s5, long l2)
        throws IdTokenException
    {
        YConnectLogger.info(TAG, "Check ID Token in the Claim from check id endpoint.");
        if (!s.equals(s3))
        {
            YConnectLogger.error(TAG, "Invalid issuer.");
            throw new IdTokenException("Invalid issuer.", (new StringBuilder()).append("The issuer did not match. [be thrown by ").append(TAG).append("]").toString());
        }
        if (!s1.equals(s5))
        {
            YConnectLogger.error(TAG, "Not match nonce.");
            throw new IdTokenException("Not match nonce.", "The nonce did not match.");
        }
        if (!s2.equals(s4))
        {
            YConnectLogger.error(TAG, "Invalid audience.");
            throw new IdTokenException("Invalid audience.", "The client id did not match.");
        }
        if (l < l2)
        {
            YConnectLogger.error(TAG, "Expired ID Token.");
            throw new IdTokenException("Expired ID Token.", "Re-issue Id Token.");
        }
        YConnectLogger.debug(TAG, (new StringBuilder()).append("Expiraiton: ").append(Long.toString(l)).append("(Current Tme: ").append(Long.toString(l2)).append(")").toString());
        if (l2 - l1 > ACCEPTABLE_RANGE)
        {
            YConnectLogger.error(TAG, "Over acceptable range.");
            throw new IdTokenException("Over acceptable range.", "This access has expired possible.");
        } else
        {
            YConnectLogger.debug(TAG, (new StringBuilder()).append("Response time - iat = ").append(Long.toString(l2 - l1)).append(" sec").toString());
            YConnectLogger.debug(TAG, (new StringBuilder()).append("Issued time: ").append(Long.toString(l1)).append("(Response Tme: ").append(Long.toString(l2)).append(")").toString());
            return true;
        }
    }

    public static boolean check(String s, String s1, String s2, IdTokenObject idtokenobject, long l)
        throws IdTokenException
    {
        return check(s, s1, s2, idtokenobject.getIss(), idtokenobject.getAud(), idtokenobject.getExp(), idtokenobject.getIat(), idtokenobject.getNonce(), l);
    }

    private static IdTokenObject parseIdToken(String s)
        throws Exception
    {
        IdTokenObject idtokenobject = new IdTokenObject();
        JSONObject jsonobject = new JSONObject(s);
        idtokenobject.setIss(jsonobject.getString("iss"));
        idtokenobject.setUserId(jsonobject.getString("user_id"));
        idtokenobject.setAud(jsonobject.getString("aud"));
        idtokenobject.setNonce(jsonobject.getString("nonce"));
        idtokenobject.setExp(jsonobject.getLong("exp"));
        idtokenobject.setIat(jsonobject.getLong("iat"));
        YConnectLogger.info(TAG, "Parsed ID Token and converted it to a IdTokenObject.");
        return idtokenobject;
    }

    public static void setAcceptableRange(long l)
    {
        ACCEPTABLE_RANGE = l;
    }

}
