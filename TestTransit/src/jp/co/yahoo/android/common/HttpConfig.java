// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Map;

public class HttpConfig
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public HttpConfig createFromParcel(Parcel parcel)
        {
            return new HttpConfig(parcel);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public HttpConfig[] newArray(int i)
        {
            return new HttpConfig[i];
        }

    };
    public static final int DELETE = 3;
    public static final int GET = 1;
    public static final int POST = 2;
    public static final int PUT = 4;
    private boolean mAuthFlag;
    private String mCharset;
    private String mContentType;
    private int mMethod;
    private Map mRequestBodyMap;
    private String mRequestBodyString;
    private int mTimeout;
    private String mUploadFilePath;

    public HttpConfig(int i)
    {
        mRequestBodyMap = null;
        mRequestBodyString = null;
        mCharset = null;
        mUploadFilePath = null;
        mContentType = null;
        mTimeout = 30000;
        mAuthFlag = true;
        mMethod = i;
    }

    public HttpConfig(Parcel parcel)
    {
        boolean flag = true;
        super();
        mRequestBodyMap = null;
        mRequestBodyString = null;
        mCharset = null;
        mUploadFilePath = null;
        mContentType = null;
        mTimeout = 30000;
        mAuthFlag = flag;
        mMethod = parcel.readInt();
        mRequestBodyMap = parcel.readHashMap(null);
        mRequestBodyString = parcel.readString();
        mCharset = parcel.readString();
        mUploadFilePath = parcel.readString();
        mContentType = parcel.readString();
        mTimeout = parcel.readInt();
        if (parcel.readInt() == 0)
        {
            flag = false;
        }
        mAuthFlag = flag;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getCharset()
    {
        return mCharset;
    }

    public String getContentType()
    {
        return mContentType;
    }

    public int getMethod()
    {
        return mMethod;
    }

    public Map getRequestBodyMap()
    {
        return mRequestBodyMap;
    }

    public String getRequestBodyString()
    {
        return mRequestBodyString;
    }

    public int getTimeout()
    {
        return mTimeout;
    }

    public String getUploadFilePath()
    {
        return mUploadFilePath;
    }

    public boolean isAuthApi()
    {
        return mAuthFlag;
    }

    public void setAuthApiFlag(boolean flag)
    {
        mAuthFlag = flag;
    }

    public void setMethod(int i)
    {
        mMethod = i;
    }

    public void setRequestBodyMap(Map map)
    {
        mRequestBodyMap = map;
    }

    public void setRequestBodyString(String s, String s1)
    {
        mRequestBodyString = s;
        mCharset = s1;
    }

    public void setTimeout(int i)
    {
        mTimeout = i;
    }

    public void setUploadFilePath(String s, String s1)
    {
        mUploadFilePath = s;
        mContentType = s1;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mMethod);
        parcel.writeMap(mRequestBodyMap);
        parcel.writeString(mRequestBodyString);
        parcel.writeString(mCharset);
        parcel.writeString(mUploadFilePath);
        parcel.writeString(mContentType);
        parcel.writeInt(mTimeout);
        int j;
        if (mAuthFlag)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
    }

}
