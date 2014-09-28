// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.oidc;

import jp.co.yahoo.yconnect.core.api.ApiClient;
import jp.co.yahoo.yconnect.core.api.ApiClientException;
import jp.co.yahoo.yconnect.core.oauth2.BearerToken;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.yconnect.core.oidc:
//            UserInfoObject

public class UserInfoClient extends ApiClient
{

    private static final String TAG = jp/co/yahoo/yconnect/core/oidc/UserInfoClient.getSimpleName();
    private UserInfoObject userInfoObject;

    public UserInfoClient(String s)
    {
        super(s);
    }

    public UserInfoClient(BearerToken bearertoken)
    {
        super(bearertoken);
    }

    public void fetchResouce(String s, String s1)
        throws ApiClientException
    {
        setParameter("schema", "openid");
        super.fetchResouce(s, s1);
        String s2 = getResponse();
        try
        {
            JSONObject jsonobject = new JSONObject(s2);
            userInfoObject = new UserInfoObject(jsonobject.optString("user_id"));
            userInfoObject.setLocale(jsonobject.optString("locale"));
            userInfoObject.setName(jsonobject.optString("name"));
            userInfoObject.setGivenName(jsonobject.optString("given_name"));
            userInfoObject.setGivenNameJaKanaJp(jsonobject.optString("given_name#ja-Kana-JP"));
            userInfoObject.setGivenNameJaHaniJp(jsonobject.optString("given_name#ja-Hani-JP"));
            userInfoObject.setFamilyName(jsonobject.optString("family_name"));
            userInfoObject.setFamilyNameJaKanaJp(jsonobject.optString("family_name#ja-Kana-JP"));
            userInfoObject.setFamilyNameJaHaniJp(jsonobject.optString("family_name#ja-Hani-JP"));
            userInfoObject.setEmail(jsonobject.optString("email"));
            userInfoObject.setEmailVerified(jsonobject.optString("email_verified"));
            userInfoObject.setGender(jsonobject.optString("gender"));
            userInfoObject.setBirthday(jsonobject.optString("birthday"));
            userInfoObject.setPhoneNumber(jsonobject.optString("phone_number"));
            if (!jsonobject.optString("address").equals(""))
            {
                JSONObject jsonobject1 = new JSONObject(jsonobject.optString("address"));
                userInfoObject.setAddressCountry(jsonobject1.optString("country"));
                userInfoObject.setAddressPostalCode(jsonobject1.optString("postal_code"));
                userInfoObject.setAddressRegion(jsonobject1.optString("region"));
                userInfoObject.setAddressLocality(jsonobject1.optString("locality"));
                userInfoObject.setAddressStreetAddress(jsonobject1.optString("street_address"));
            }
            userInfoObject.setJsonObject(jsonobject);
            return;
        }
        catch (JSONException jsonexception)
        {
            jsonexception.printStackTrace();
            throw new ApiClientException("JSON error when converted UserInfo response to JSON.", (new StringBuilder()).append(jsonexception.getMessage()).append(" [be thrown by ").append(TAG).append("]").toString());
        }
    }

    public UserInfoObject getUserInfoObject()
    {
        return userInfoObject;
    }

}
