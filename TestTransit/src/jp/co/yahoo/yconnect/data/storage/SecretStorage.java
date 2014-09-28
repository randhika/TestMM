// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.data.storage;

import android.content.Context;
import android.content.SharedPreferences;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import jp.co.yahoo.yconnect.core.oidc.IdTokenObject;
import jp.co.yahoo.yconnect.core.oidc.UserInfoObject;
import jp.co.yahoo.yconnect.core.util.YConnectLogger;

// Referenced classes of package jp.co.yahoo.yconnect.data.storage:
//            IFSecretStorage, SecretStorageException

public class SecretStorage
    implements IFSecretStorage
{

    private static final String ACCESS_TOKEN_KEY = "access_token";
    private static final String ADDRESS_COUNTRY_KEY = "address_country";
    private static final String ADDRESS_LOCALITY_KEY = "address_locality";
    private static final String ADDRESS_POSTAL_CODE_KEY = "address_postal_code";
    private static final String ADDRESS_REGION_KEY = "address_region";
    private static final String ADDRESS_STREET_ADDRESS_KEY = "address_street_address";
    private static final String AUD_KEY = "aud";
    private static final String BIRTHDAY_KEY = "birthday";
    private static final String EMAIL_KEY = "email";
    private static final String EMAIL_VERIFIED_KEY = "email_verified";
    private static final String EXPIRATION_KEY = "expires_in";
    private static final String FAMILY_NAME_HANI_KEY = "family_name_hani";
    private static final String FAMILY_NAME_KANA_KEY = "family_name_kana";
    private static final String FAMILY_NAME_KEY = "family_name";
    private static final String GENDER_KEY = "gender";
    private static final String GIVEN_NAME_HANI_KEY = "given_name_hani";
    private static final String GIVEN_NAME_KANA_KEY = "given_name_kana";
    private static final String GIVEN_NAME_KEY = "given_name";
    private static final String IAT_KEY = "iat";
    private static final String ID_TOKEN_EXPIRATION_KEY = "exp";
    private static final String ISS_KEY = "iss";
    private static final String IV_ACCESS_TOKEN_KEY = "iv_access_token";
    private static final String IV_ID_TOKEN_KEY = "iv_id_token";
    private static final String IV_REFRESH_TOKEN_KEY = "iv_refresh_token";
    private static final String LOCALE_KEY = "locale";
    private static final String NAME_KEY = "name";
    private static final String NONCE_KEY = "nonce";
    private static final String PHONE_NUMBER_KEY = "phone_number";
    private static final String REFRESH_TOKEN_KEY = "refresh_token";
    private static final String SCOPE_KEY = "scope";
    private static final String SECRET_KEY = "secret_key";
    private static final String STATE_KEY = "state";
    private static final String TAG = jp/co/yahoo/yconnect/data/storage/SecretStorage.getSimpleName();
    private static final String USER_ID_KEY = "user_id";
    private static final String USER_INFO_USER_ID_KEY = "user_info_user_id";
    private String SHARED_PREFERENCES_NAME;
    private SharedPreferences sharedPreferences;

    public SecretStorage(Context context, String s)
    {
        SHARED_PREFERENCES_NAME = (new StringBuilder()).append("YConnectSecret4").append(s).toString();
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0);
    }

    public void delete(String s)
        throws SecretStorageException
    {
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(s);
        if (editor.commit())
        {
            YConnectLogger.info(TAG, (new StringBuilder()).append("Successfully deleted ").append(s).append(".").toString());
            return;
        } else
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("Failed to delete ").append(s).append(".").toString());
            throw new SecretStorageException((new StringBuilder()).append("Failed to delete ").append(s).append(".").toString(), (new StringBuilder()).append("[be thrown by ").append(TAG).append("]").toString());
        }
    }

    public void deleteAccessToken()
        throws SecretStorageException
    {
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("access_token");
        editor.remove("expires_in");
        editor.remove("refresh_token");
        editor.remove("scope");
        if (editor.commit())
        {
            YConnectLogger.info(TAG, "Successfully deleted Access Token.");
            return;
        } else
        {
            YConnectLogger.error(TAG, "Failed to delete Access Token.");
            throw new SecretStorageException("Failed to delete Access Token.", "");
        }
    }

    public void deleteIVAccessToken()
        throws SecretStorageException
    {
        delete("iv_access_token");
    }

    public void deleteIVIdToken()
        throws SecretStorageException
    {
        delete("iv_id_token");
    }

    public void deleteIVRefreshToken()
        throws SecretStorageException
    {
        delete("iv_refresh_token");
    }

    public void deleteIdToken()
        throws SecretStorageException
    {
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("iss");
        editor.remove("aud");
        editor.remove("user_id");
        editor.remove("nonce");
        editor.remove("exp");
        editor.remove("iat");
        if (editor.commit())
        {
            YConnectLogger.info(TAG, "Successfully deleted ID Token.");
            return;
        } else
        {
            YConnectLogger.error(TAG, "Failed to delete ID Token.");
            throw new SecretStorageException("Failed to delete ID Token.", "");
        }
    }

    public void deleteNonce()
        throws SecretStorageException
    {
        delete("nonce");
    }

    public void deleteSecretKey()
        throws SecretStorageException
    {
        delete("secret_key");
    }

    public void deleteState()
        throws SecretStorageException
    {
        delete("state");
    }

    public void deleteUserInfo()
        throws SecretStorageException
    {
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("user_info_user_id");
        editor.remove("locale");
        editor.remove("name");
        editor.remove("given_name");
        editor.remove("given_name_kana");
        editor.remove("given_name_hani");
        editor.remove("family_name");
        editor.remove("family_name_kana");
        editor.remove("family_name_hani");
        editor.remove("email");
        editor.remove("email_verified");
        editor.remove("gender");
        editor.remove("birthday");
        editor.remove("address_country");
        editor.remove("address_postal_code");
        editor.remove("address_region");
        editor.remove("address_locality");
        editor.remove("address_street_address");
        editor.remove("phone_number");
        if (editor.commit())
        {
            YConnectLogger.info(TAG, "Successfully deleted UserInfo.");
            return;
        } else
        {
            YConnectLogger.error(TAG, "Failed to delete UserInfo.");
            throw new SecretStorageException("Failed to delete UserInfo.", "");
        }
    }

    public String load(String s)
        throws SecretStorageException
    {
        String s1 = sharedPreferences.getString(s, null);
        if (s1 != null)
        {
            YConnectLogger.info(TAG, (new StringBuilder()).append("Successfully Loaded ").append(s).append(".").toString());
            return s1;
        } else
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("Failed to load ").append(s).append(".").toString());
            return s1;
        }
    }

    public BearerToken loadAccessToken()
        throws SecretStorageException
    {
        String s = sharedPreferences.getString("access_token", null);
        long l = sharedPreferences.getLong("expires_in", -1L);
        String s1 = sharedPreferences.getString("refresh_token", null);
        String s2 = sharedPreferences.getString("scope", null);
        if (s != null && l != -1L)
        {
            YConnectLogger.info(TAG, "Successfully Loaded Access Token.");
            return new BearerToken(s, l, s1, s2);
        } else
        {
            YConnectLogger.error(TAG, "Failed to load Access Token.");
            throw new SecretStorageException("Failed to load Access Token", "");
        }
    }

    public String loadIVAccessToken()
        throws SecretStorageException
    {
        return load("iv_access_token");
    }

    public String loadIVIdToken()
        throws SecretStorageException
    {
        return load("iv_id_token");
    }

    public String loadIVRefreshToken()
        throws SecretStorageException
    {
        return load("iv_refresh_token");
    }

    public IdTokenObject loadIdToken()
        throws SecretStorageException
    {
        String s = sharedPreferences.getString("iss", null);
        String s1 = sharedPreferences.getString("aud", null);
        String s2 = sharedPreferences.getString("user_id", null);
        String s3 = sharedPreferences.getString("nonce", null);
        long l = sharedPreferences.getLong("exp", -1L);
        long l1 = sharedPreferences.getLong("iat", -1L);
        if (s != null && s1 != null && s2 != null && s3 != null && l != -1L && l1 != -1L)
        {
            YConnectLogger.info(TAG, "Successfully Loaded ID Token.");
            return new IdTokenObject(s, s2, s1, s3, l, l1);
        } else
        {
            YConnectLogger.error(TAG, "Failed to load Access Token.");
            throw new SecretStorageException("Failed to load ID Token.", "");
        }
    }

    public String loadNonce()
        throws SecretStorageException
    {
        return load("nonce");
    }

    public String loadSecretKey()
    {
        String s;
        try
        {
            s = load("secret_key");
        }
        catch (SecretStorageException secretstorageexception)
        {
            YConnectLogger.error(TAG, "Failed to load secret key.");
            secretstorageexception.printStackTrace();
            return null;
        }
        return s;
    }

    public String loadState()
        throws SecretStorageException
    {
        return load("state");
    }

    public UserInfoObject loadUserInfo()
        throws SecretStorageException
    {
        String s = sharedPreferences.getString("user_info_user_id", null);
        String s1 = sharedPreferences.getString("locale", null);
        String s2 = sharedPreferences.getString("name", null);
        String s3 = sharedPreferences.getString("given_name", null);
        String s4 = sharedPreferences.getString("given_name_kana", null);
        String s5 = sharedPreferences.getString("given_name_hani", null);
        String s6 = sharedPreferences.getString("family_name", null);
        String s7 = sharedPreferences.getString("family_name_kana", null);
        String s8 = sharedPreferences.getString("family_name_hani", null);
        String s9 = sharedPreferences.getString("email", null);
        String s10 = sharedPreferences.getString("email_verified", null);
        String s11 = sharedPreferences.getString("gender", null);
        String s12 = sharedPreferences.getString("birthday", null);
        String s13 = sharedPreferences.getString("address_country", null);
        String s14 = sharedPreferences.getString("address_postal_code", null);
        String s15 = sharedPreferences.getString("address_region", null);
        String s16 = sharedPreferences.getString("address_locality", null);
        String s17 = sharedPreferences.getString("address_street_address", null);
        String s18 = sharedPreferences.getString("phone_number", null);
        if (s != null)
        {
            UserInfoObject userinfoobject = new UserInfoObject(s);
            userinfoobject.setLocale(s1);
            userinfoobject.setName(s2);
            userinfoobject.setGivenName(s3);
            userinfoobject.setGivenNameJaKanaJp(s4);
            userinfoobject.setGivenNameJaHaniJp(s5);
            userinfoobject.setFamilyName(s6);
            userinfoobject.setFamilyNameJaKanaJp(s7);
            userinfoobject.setFamilyNameJaHaniJp(s8);
            userinfoobject.setEmail(s9);
            userinfoobject.setEmailVerified(s10);
            userinfoobject.setGender(s11);
            userinfoobject.setBirthday(s12);
            userinfoobject.setAddressCountry(s13);
            userinfoobject.setAddressPostalCode(s14);
            userinfoobject.setAddressRegion(s15);
            userinfoobject.setAddressLocality(s16);
            userinfoobject.setAddressStreetAddress(s17);
            userinfoobject.setPhoneNumber(s18);
            YConnectLogger.info(TAG, "Successfully Loaded UserInfo.");
            return userinfoobject;
        } else
        {
            YConnectLogger.error(TAG, "Failed to load UserInfo.");
            throw new SecretStorageException("Failed to load UserInfo.", "");
        }
    }

    public void save(String s, String s1)
        throws SecretStorageException
    {
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(s, s1);
        if (editor.commit())
        {
            YConnectLogger.info(TAG, (new StringBuilder()).append("Successfully saved ").append(s).append(".").toString());
            return;
        } else
        {
            YConnectLogger.error(TAG, (new StringBuilder()).append("Failed to save ").append(s).append(".").toString());
            throw new SecretStorageException((new StringBuilder()).append("Failed to save ").append(s).append(".").toString(), (new StringBuilder()).append("[be thrown by ").append(TAG).append("]").toString());
        }
    }

    public void saveAccessToken(BearerToken bearertoken)
        throws SecretStorageException
    {
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("access_token", bearertoken.getAccessToken());
        editor.putLong("expires_in", bearertoken.getExpiration());
        if (bearertoken.getRefreshToken() != null)
        {
            editor.putString("refresh_token", bearertoken.getRefreshToken());
        }
        if (bearertoken.getScope() != null)
        {
            editor.putString("scope", bearertoken.getScope());
        }
        if (editor.commit())
        {
            YConnectLogger.info(TAG, "Successfully saved Access Token.");
            return;
        } else
        {
            YConnectLogger.error(TAG, "Failed to save Access Token.");
            throw new SecretStorageException("Failed to save Access Token.", "");
        }
    }

    public void saveIVAccessToken(String s)
        throws SecretStorageException
    {
        save("iv_access_token", s);
    }

    public void saveIVIdToken(String s)
        throws SecretStorageException
    {
        save("iv_id_token", s);
    }

    public void saveIVRefreshToken(String s)
        throws SecretStorageException
    {
        save("iv_refresh_token", s);
    }

    public void saveIdToken(IdTokenObject idtokenobject)
        throws SecretStorageException
    {
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("iss", idtokenobject.getIss());
        editor.putString("aud", idtokenobject.getAud());
        editor.putString("user_id", idtokenobject.getUserId());
        editor.putString("nonce", idtokenobject.getNonce());
        editor.putLong("exp", idtokenobject.getExp());
        editor.putLong("iat", idtokenobject.getIat());
        if (editor.commit())
        {
            YConnectLogger.info(TAG, "Successfully saved ID Token.");
            return;
        } else
        {
            YConnectLogger.error(TAG, "Failed to save ID Token.");
            throw new SecretStorageException("Failed to save ID Token.", "");
        }
    }

    public void saveNonce(String s)
        throws SecretStorageException
    {
        save("nonce", s);
    }

    public void saveSecretKey(String s)
        throws SecretStorageException
    {
        save("secret_key", s);
    }

    public void saveState(String s)
        throws SecretStorageException
    {
        save("state", s);
    }

    public void saveUserInfo(UserInfoObject userinfoobject)
        throws SecretStorageException
    {
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_info_user_id", userinfoobject.getUserId());
        editor.putString("locale", userinfoobject.getLocale());
        editor.putString("name", userinfoobject.getName());
        editor.putString("given_name", userinfoobject.getGivenName());
        editor.putString("given_name_kana", userinfoobject.getGivenNameJaKanaJp());
        editor.putString("given_name_hani", userinfoobject.getGivenNameJaHaniJp());
        editor.putString("family_name", userinfoobject.getFamilyName());
        editor.putString("family_name_kana", userinfoobject.getFamilyNameJaKanaJp());
        editor.putString("family_name_hani", userinfoobject.getFamilyNameJaHaniJp());
        editor.putString("email", userinfoobject.getEmail());
        editor.putString("email_verified", userinfoobject.getEmailVerified());
        editor.putString("gender", userinfoobject.getGender());
        editor.putString("birthday", userinfoobject.getBirthday());
        editor.putString("address_country", userinfoobject.getAddressCountry());
        editor.putString("address_postal_code", userinfoobject.getAddressPostalCode());
        editor.putString("address_region", userinfoobject.getAddressRegion());
        editor.putString("address_locality", userinfoobject.getAddressLocality());
        editor.putString("address_street_address", userinfoobject.getAddressStreetAddress());
        editor.putString("phone_number", userinfoobject.getPhoneNumber());
        if (editor.commit())
        {
            YConnectLogger.info(TAG, "Successfully saved UserInfo.");
            return;
        } else
        {
            YConnectLogger.error(TAG, "Failed to save UserInfo.");
            throw new SecretStorageException("Failed to save UserInfo.", "");
        }
    }

}
