// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.data.storage;

import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import jp.co.yahoo.yconnect.core.oidc.IdTokenObject;

// Referenced classes of package jp.co.yahoo.yconnect.data.storage:
//            SecretStorageException

public interface IFSecretStorage
{

    public abstract BearerToken loadAccessToken()
        throws SecretStorageException;

    public abstract String loadIVAccessToken()
        throws SecretStorageException;

    public abstract IdTokenObject loadIdToken()
        throws SecretStorageException;

    public abstract String loadNonce()
        throws SecretStorageException;

    public abstract String loadSecretKey()
        throws SecretStorageException;

    public abstract String loadState()
        throws SecretStorageException;

    public abstract void saveAccessToken(BearerToken bearertoken)
        throws SecretStorageException;

    public abstract void saveIVAccessToken(String s)
        throws SecretStorageException;

    public abstract void saveIdToken(IdTokenObject idtokenobject)
        throws SecretStorageException;

    public abstract void saveNonce(String s)
        throws SecretStorageException;

    public abstract void saveSecretKey(String s)
        throws SecretStorageException;

    public abstract void saveState(String s)
        throws SecretStorageException;
}
