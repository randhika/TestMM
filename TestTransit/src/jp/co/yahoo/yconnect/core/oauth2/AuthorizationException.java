// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.oauth2;


public class AuthorizationException extends Exception
{

    private static final long serialVersionUID = 1L;
    private String error;
    private String errorDescription;

    public AuthorizationException()
    {
        error = "";
        errorDescription = "";
    }

    public AuthorizationException(String s, String s1)
    {
        super(s1);
        error = "";
        errorDescription = "";
        error = s;
        errorDescription = s1;
    }

    public AuthorizationException(String s, String s1, Throwable throwable)
    {
        super(s1, throwable);
        error = "";
        errorDescription = "";
        error = s;
        errorDescription = s1;
    }

    public AuthorizationException(String s, Throwable throwable)
    {
        super(throwable);
        error = "";
        errorDescription = "";
        error = s;
    }

    public String getError()
    {
        return error;
    }

    public String getErrorDescription()
    {
        return errorDescription;
    }

    public boolean isAccessDenied()
    {
        return "access_denied".equals(error);
    }

    public boolean isInvalidGrant()
    {
        return "invalid_grant".equals(error);
    }

    public boolean isInvalidRequest()
    {
        return "invalid_request".equals(error);
    }

    public boolean isInvalidScope()
    {
        return "invalid_scope".equals(error);
    }

    public boolean isServerError()
    {
        return "server_error".equals(error);
    }

    public boolean isTemporarilyUnavailable()
    {
        return "temporarily_available".equals(error);
    }

    public boolean isUnauthorizedClient()
    {
        return "unauthorized_client".equals(error);
    }

    public boolean isUnsupportedResponseType()
    {
        return "unsupported_response_type".equals(error);
    }

    public String toString()
    {
        return (new StringBuilder()).append("error: ").append(error).append(" error_description: ").append(errorDescription).append(" (").append(jp/co/yahoo/yconnect/core/oauth2/AuthorizationException.getSimpleName()).append(")").toString();
    }
}
