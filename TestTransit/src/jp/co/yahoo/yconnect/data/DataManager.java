// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.data;

import android.content.Context;
import android.util.Base64;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import jp.co.yahoo.yconnect.core.oidc.IdTokenObject;
import jp.co.yahoo.yconnect.core.oidc.UserInfoObject;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;
import jp.co.yahoo.yconnect.data.cipher.CipherObject;
import jp.co.yahoo.yconnect.data.cipher.CipherUtil;
import jp.co.yahoo.yconnect.data.storage.SecretStorage;
import jp.co.yahoo.yconnect.data.storage.SecretStorageException;

public class DataManager
{

    public static final String DEFAULT_YID = "default_yid";
    private static final String TAG = jp/co/yahoo/yconnect/data/DataManager.getSimpleName();
    private byte secretKey[];
    private SecretStorage storage;

    public DataManager(Context context, byte abyte0[], String s)
        throws SecretStorageException
    {
        secretKey = abyte0;
        YConnectLogger.debug(TAG, "Call constructor and create new instance of SecretStorage.");
        storage = new SecretStorage(context, s);
        storage.saveSecretKey(Base64.encodeToString(abyte0, 0));
    }

    public static byte[] generateSecretKey()
        throws Exception
    {
        return CipherUtil.generateSecretKey();
    }

    public static byte[] loadSecretKey(Context context, String s)
        throws SecretStorageException
    {
        YConnectLogger.debug(TAG, "Call loadSecretKey method and create new instance of SecretStorage.");
        SecretStorage secretstorage = new SecretStorage(context, s);
        if (secretstorage.loadSecretKey() != null)
        {
            return Base64.decode(secretstorage.loadSecretKey(), 0);
        } else
        {
            return null;
        }
    }

    public void deleteAccessToken()
        throws SecretStorageException
    {
        storage.deleteAccessToken();
        storage.deleteIVAccessToken();
        storage.deleteIVRefreshToken();
    }

    public void deleteIdToken()
        throws SecretStorageException
    {
        storage.deleteIdToken();
        storage.deleteIVIdToken();
    }

    public void deleteNonce()
        throws SecretStorageException
    {
        storage.deleteNonce();
    }

    public void deleteState()
        throws SecretStorageException
    {
        storage.deleteState();
    }

    public void deleteUserInfo()
        throws SecretStorageException
    {
        storage.deleteUserInfo();
    }

    public BearerToken loadAccessToken()
        throws SecretStorageException, Exception
    {
        String s = storage.loadSecretKey();
        String s1 = storage.loadIVAccessToken();
        if (s == null)
        {
            YConnectLogger.info(TAG, "secretKey is null.");
            return null;
        }
        if (s1 == null)
        {
            YConnectLogger.info(TAG, "loadIVAccessToken is null.");
            return null;
        }
        secretKey = Base64.decode(s, 0);
        byte abyte0[] = Base64.decode(s1, 0);
        BearerToken bearertoken = storage.loadAccessToken();
        byte abyte1[] = Base64.decode(bearertoken.getAccessToken(), 0);
        byte abyte2[] = CipherUtil.decrypt(secretKey, new CipherObject(abyte0, abyte1));
        BearerToken bearertoken1;
        if (bearertoken.getRefreshToken() != null)
        {
            byte abyte3[] = Base64.decode(storage.loadIVRefreshToken(), 0);
            byte abyte4[] = Base64.decode(bearertoken.getRefreshToken(), 0);
            byte abyte5[] = CipherUtil.decrypt(secretKey, new CipherObject(abyte3, abyte4));
            bearertoken1 = new BearerToken(new String(abyte2), bearertoken.getExpiration(), new String(abyte5));
        } else
        {
            bearertoken1 = new BearerToken(new String(abyte2), bearertoken.getExpiration());
        }
        YConnectLogger.debug(TAG, (new StringBuilder()).append("[LOAD] IV Access Token: ").append(abyte0).append(", Encrypted Access Token: ").append(abyte1).toString());
        YConnectLogger.debug(TAG, (new StringBuilder()).append("[LOAD] Access Token(byte[]): ").append(abyte2).toString());
        return bearertoken1;
    }

    public IdTokenObject loadIdToken()
        throws SecretStorageException, Exception
    {
        String s = storage.loadSecretKey();
        String s1 = storage.loadIVIdToken();
        if (s == null)
        {
            YConnectLogger.info(TAG, "secretKey is null.");
            return null;
        }
        if (s1 == null)
        {
            YConnectLogger.info(TAG, "ivIdToken is null.");
            return null;
        } else
        {
            secretKey = Base64.decode(s, 0);
            byte abyte0[] = Base64.decode(s1, 0);
            IdTokenObject idtokenobject = storage.loadIdToken();
            byte abyte1[] = Base64.decode(idtokenobject.getUserId(), 0);
            byte abyte2[] = CipherUtil.decrypt(secretKey, new CipherObject(abyte0, abyte1));
            YConnectLogger.debug(TAG, (new StringBuilder()).append("[LOAD] IV: ").append(abyte0).append(", Encrypted ID Token: ").append(abyte1).toString());
            YConnectLogger.debug(TAG, (new StringBuilder()).append("[LOAD] ID Token(byte[]): ").append(abyte2).toString());
            idtokenobject.setUserId(new String(abyte2));
            return idtokenobject;
        }
    }

    public String loadNonce()
        throws SecretStorageException
    {
        return storage.loadNonce();
    }

    public String loadState()
        throws SecretStorageException
    {
        return storage.loadState();
    }

    public UserInfoObject loadUserInfo()
        throws SecretStorageException, Exception
    {
        return storage.loadUserInfo();
    }

    public void saveAccessToken(BearerToken bearertoken)
        throws SecretStorageException, Exception
    {
        byte abyte0[] = bearertoken.getAccessToken().getBytes();
        CipherObject cipherobject = CipherUtil.encrypt(secretKey, abyte0);
        String s = Base64.encodeToString(cipherobject.getIv(), 0);
        String s1 = Base64.encodeToString(cipherobject.getEncryptedString(), 0);
        BearerToken bearertoken1;
        if (bearertoken.getRefreshToken() != null)
        {
            byte abyte1[] = bearertoken.getRefreshToken().getBytes();
            CipherObject cipherobject1 = CipherUtil.encrypt(secretKey, abyte1);
            String s2 = Base64.encodeToString(cipherobject1.getIv(), 0);
            String s3 = Base64.encodeToString(cipherobject1.getEncryptedString(), 0);
            storage.saveIVRefreshToken(s2);
            bearertoken1 = new BearerToken(s1, bearertoken.getExpiration(), s3);
        } else
        {
            bearertoken1 = new BearerToken(s1, bearertoken.getExpiration());
        }
        storage.saveSecretKey(Base64.encodeToString(secretKey, 0));
        storage.saveIVAccessToken(s);
        storage.saveAccessToken(bearertoken1);
        YConnectLogger.debug(TAG, (new StringBuilder()).append("[SAVE] Access Token(byte[]): ").append(abyte0).toString());
        YConnectLogger.debug(TAG, (new StringBuilder()).append("[SAVE] IV Access Token: ").append(s).append(", Encrypted Access Token: ").append(bearertoken1).toString());
    }

    public void saveIdToken(IdTokenObject idtokenobject)
        throws SecretStorageException, Exception
    {
        byte abyte0[] = idtokenobject.getUserId().getBytes();
        CipherObject cipherobject = CipherUtil.encrypt(secretKey, abyte0);
        String s = Base64.encodeToString(cipherobject.getIv(), 0);
        String s1 = Base64.encodeToString(cipherobject.getEncryptedString(), 0);
        IdTokenObject idtokenobject1 = new IdTokenObject();
        idtokenobject1.setIss(idtokenobject.getIss());
        idtokenobject1.setAud(idtokenobject.getAud());
        idtokenobject1.setUserId(s1);
        idtokenobject1.setExp(idtokenobject.getExp());
        idtokenobject1.setIat(idtokenobject.getIat());
        idtokenobject1.setNonce(idtokenobject.getNonce());
        storage.saveSecretKey(Base64.encodeToString(secretKey, 0));
        storage.saveIVIdToken(s);
        storage.saveIdToken(idtokenobject1);
        YConnectLogger.debug(TAG, (new StringBuilder()).append("[SAVE] ID Token(byte[]): ").append(abyte0).toString());
        YConnectLogger.debug(TAG, (new StringBuilder()).append("[SAVE] IV: ").append(s).append(", Encrypted ID Token: ").append(s1).toString());
    }

    public void saveNonce(String s)
        throws SecretStorageException
    {
        storage.saveNonce(s);
    }

    public void saveState(String s)
        throws SecretStorageException
    {
        storage.saveState(s);
    }

    public void saveUserInfo(UserInfoObject userinfoobject)
        throws SecretStorageException, Exception
    {
        storage.saveUserInfo(userinfoobject);
    }

}
