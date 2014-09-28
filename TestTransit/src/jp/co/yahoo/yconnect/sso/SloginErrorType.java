// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.sso;

import java.lang.reflect.Field;

public class SloginErrorType
{

    public static String DESCRIPTION_10109 = "required parameter is empty.";
    public static String DESCRIPTION_10110 = "the parameter is invalid.";
    public static String DESCRIPTION_10711 = "ytsession data is empty.";
    public static String DESCRIPTION_10712 = "ytsession data is invalid.";
    public static String DESCRIPTION_10713 = "failed to update session data.";
    public static String DESCRIPTION_10714 = "id key is invalid.";
    public static String DESCRIPTION_10715 = "login_type is invalid.";
    public static String DESCRIPTION_10716 = "verifier is invalid.";
    public static String DESCRIPTION_10717 = "ytsession is expired.";
    public static String DESCRIPTION_10718 = "id_token is invalid.";
    public static String DESCRIPTION_10719 = "failed to issue relogin token.";
    public static String DESCRIPTION_10720 = "error in relogin endpoint.";
    public static String DESCRIPTION_11204 = "server_error.";
    public static String ERROR_10109 = "invalid_request";
    public static String ERROR_10110 = "invalid_request";
    public static String ERROR_10711 = "invalid_grant";
    public static String ERROR_10712 = "invalid_grant";
    public static String ERROR_10713 = "invalid_grant";
    public static String ERROR_10714 = "invalid_grant";
    public static String ERROR_10715 = "invalid_grant";
    public static String ERROR_10716 = "invalid_grant";
    public static String ERROR_10717 = "invalid_grant";
    public static String ERROR_10718 = "invalid_token";
    public static String ERROR_10719 = "invalid_token";
    public static String ERROR_10720 = "server_error";
    public static String ERROR_11204 = "server_error";

    public SloginErrorType()
    {
    }

    public static String getDescription(String s)
    {
        return getMessage((new StringBuilder()).append("DESCRIPTION_").append(s).toString());
    }

    public static String getError(String s)
    {
        return getMessage((new StringBuilder()).append("ERROR_").append(s).toString());
    }

    public static String getMessage(String s)
    {
        String s1;
        try
        {
            Field afield[] = jp/co/yahoo/yconnect/sso/SloginErrorType.getFields();
            s1 = jp/co/yahoo/yconnect/sso/SloginErrorType.getDeclaredField(s).get(afield).toString();
        }
        catch (Exception exception)
        {
            return "";
        }
        return s1;
    }

}
