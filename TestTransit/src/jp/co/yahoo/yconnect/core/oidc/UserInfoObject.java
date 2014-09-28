// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.yconnect.core.oidc;

import org.json.JSONObject;

public class UserInfoObject
{

    private String addressCountry;
    private String addressLocality;
    private String addressPostalCode;
    private String addressRegion;
    private String addressStreetAddress;
    private String birthday;
    private String email;
    private String emailVerified;
    private String familyName;
    private String familyNameJaHaniJp;
    private String familyNameJaKanaJp;
    private String gender;
    private String givenName;
    private String givenNameJaHaniJp;
    private String givenNameJaKanaJp;
    private JSONObject jsonObject;
    private String locale;
    private String name;
    private String phoneNumber;
    private String userId;

    public UserInfoObject()
    {
        userId = "";
        locale = "";
        name = "";
        givenName = "";
        givenNameJaKanaJp = "";
        givenNameJaHaniJp = "";
        familyName = "";
        familyNameJaKanaJp = "";
        familyNameJaHaniJp = "";
        email = "";
        emailVerified = "";
        gender = "";
        birthday = "";
        addressCountry = "";
        addressPostalCode = "";
        addressRegion = "";
        addressLocality = "";
        addressStreetAddress = "";
        phoneNumber = "";
    }

    public UserInfoObject(String s)
    {
        userId = "";
        locale = "";
        name = "";
        givenName = "";
        givenNameJaKanaJp = "";
        givenNameJaHaniJp = "";
        familyName = "";
        familyNameJaKanaJp = "";
        familyNameJaHaniJp = "";
        email = "";
        emailVerified = "";
        gender = "";
        birthday = "";
        addressCountry = "";
        addressPostalCode = "";
        addressRegion = "";
        addressLocality = "";
        addressStreetAddress = "";
        phoneNumber = "";
        setUserId(s);
    }

    public String getAdditionalValue(String s)
    {
        return jsonObject.optString(s);
    }

    public String getAddressCountry()
    {
        return addressCountry;
    }

    public String getAddressLocality()
    {
        return addressLocality;
    }

    public String getAddressPostalCode()
    {
        return addressPostalCode;
    }

    public String getAddressRegion()
    {
        return addressRegion;
    }

    public String getAddressStreetAddress()
    {
        return addressStreetAddress;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public String getEmail()
    {
        return email;
    }

    public String getEmailVerified()
    {
        return emailVerified;
    }

    public String getFamilyName()
    {
        return familyName;
    }

    public String getFamilyNameJaHaniJp()
    {
        return familyNameJaHaniJp;
    }

    public String getFamilyNameJaKanaJp()
    {
        return familyNameJaKanaJp;
    }

    public String getGender()
    {
        return gender;
    }

    public String getGivenName()
    {
        return givenName;
    }

    public String getGivenNameJaHaniJp()
    {
        return givenNameJaHaniJp;
    }

    public String getGivenNameJaKanaJp()
    {
        return givenNameJaKanaJp;
    }

    public String getLocale()
    {
        return locale;
    }

    public String getName()
    {
        return name;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setAddressCountry(String s)
    {
        addressCountry = s;
    }

    public void setAddressLocality(String s)
    {
        addressLocality = s;
    }

    public void setAddressPostalCode(String s)
    {
        addressPostalCode = s;
    }

    public void setAddressRegion(String s)
    {
        addressRegion = s;
    }

    public void setAddressStreetAddress(String s)
    {
        addressStreetAddress = s;
    }

    public void setBirthday(String s)
    {
        birthday = s;
    }

    public void setEmail(String s)
    {
        email = s;
    }

    public void setEmailVerified(String s)
    {
        emailVerified = s;
    }

    public void setFamilyName(String s)
    {
        familyName = s;
    }

    public void setFamilyNameJaHaniJp(String s)
    {
        familyNameJaHaniJp = s;
    }

    public void setFamilyNameJaKanaJp(String s)
    {
        familyNameJaKanaJp = s;
    }

    public void setGender(String s)
    {
        gender = s;
    }

    public void setGivenName(String s)
    {
        givenName = s;
    }

    public void setGivenNameJaHaniJp(String s)
    {
        givenNameJaHaniJp = s;
    }

    public void setGivenNameJaKanaJp(String s)
    {
        givenNameJaKanaJp = s;
    }

    public void setJsonObject(JSONObject jsonobject)
    {
        jsonObject = jsonobject;
    }

    public void setLocale(String s)
    {
        locale = s;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void setPhoneNumber(String s)
    {
        phoneNumber = s;
    }

    public void setUserId(String s)
    {
        userId = s;
    }

    public String toString()
    {
        return jsonObject.toString();
    }
}
