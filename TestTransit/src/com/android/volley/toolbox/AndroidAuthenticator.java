// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.android.volley.AuthFailureError;

// Referenced classes of package com.android.volley.toolbox:
//            Authenticator

public class AndroidAuthenticator
    implements Authenticator
{

    private final Account mAccount;
    private final String mAuthTokenType;
    private final Context mContext;
    private final boolean mNotifyAuthFailure;

    public AndroidAuthenticator(Context context, Account account, String s)
    {
        this(context, account, s, false);
    }

    public AndroidAuthenticator(Context context, Account account, String s, boolean flag)
    {
        mContext = context;
        mAccount = account;
        mAuthTokenType = s;
        mNotifyAuthFailure = flag;
    }

    public Account getAccount()
    {
        return mAccount;
    }

    public String getAuthToken()
        throws AuthFailureError
    {
        AccountManagerFuture accountmanagerfuture = AccountManager.get(mContext).getAuthToken(mAccount, mAuthTokenType, mNotifyAuthFailure, null, null);
        Bundle bundle;
        boolean flag;
        String s;
        try
        {
            bundle = (Bundle)accountmanagerfuture.getResult();
        }
        catch (Exception exception)
        {
            throw new AuthFailureError("Error while retrieving auth token", exception);
        }
        flag = accountmanagerfuture.isDone();
        s = null;
        if (flag)
        {
            boolean flag1 = accountmanagerfuture.isCancelled();
            s = null;
            if (!flag1)
            {
                if (bundle.containsKey("intent"))
                {
                    throw new AuthFailureError((Intent)bundle.getParcelable("intent"));
                }
                s = bundle.getString("authtoken");
            }
        }
        if (s == null)
        {
            throw new AuthFailureError((new StringBuilder("Got null auth token for type: ")).append(mAuthTokenType).toString());
        } else
        {
            return s;
        }
    }

    public void invalidateAuthToken(String s)
    {
        AccountManager.get(mContext).invalidateAuthToken(mAccount.type, s);
    }
}
