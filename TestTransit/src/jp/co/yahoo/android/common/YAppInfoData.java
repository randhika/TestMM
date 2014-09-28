// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class YAppInfoData
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public YAppInfoData createFromParcel(Parcel parcel)
        {
            return new YAppInfoData(parcel);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public YAppInfoData[] newArray(int i)
        {
            return new YAppInfoData[i];
        }

    };
    public List buttonData;
    public String forceUpdateDate;
    public boolean hasNewUpdate;
    public boolean hasUpdate;
    public boolean isForceUpdateDate;
    public boolean isUnderminVersion;
    public String latestAppVersion;
    public String message;
    public String minAppVersion;
    public String minOsVersion;
    public String title;
    public String xmlString;

    public YAppInfoData()
    {
        title = "";
        message = "";
        minOsVersion = "";
        latestAppVersion = "";
        minAppVersion = "";
        forceUpdateDate = "";
        hasUpdate = true;
        hasNewUpdate = false;
        isUnderminVersion = false;
        isForceUpdateDate = false;
        buttonData = new ArrayList();
        xmlString = "";
    }

    private YAppInfoData(Parcel parcel)
    {
        title = "";
        message = "";
        minOsVersion = "";
        latestAppVersion = "";
        minAppVersion = "";
        forceUpdateDate = "";
        hasUpdate = true;
        hasNewUpdate = false;
        isUnderminVersion = false;
        isForceUpdateDate = false;
        buttonData = new ArrayList();
        xmlString = "";
        readFromParcel(parcel);
    }


    private void readFromParcel(Parcel parcel)
    {
        boolean flag = true;
        title = parcel.readString();
        message = parcel.readString();
        minOsVersion = parcel.readString();
        latestAppVersion = parcel.readString();
        minAppVersion = parcel.readString();
        forceUpdateDate = parcel.readString();
        boolean flag1;
        boolean flag2;
        boolean flag3;
        if (parcel.readInt() == flag)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        hasUpdate = flag1;
        if (parcel.readInt() == flag)
        {
            flag2 = flag;
        } else
        {
            flag2 = false;
        }
        hasNewUpdate = flag2;
        if (parcel.readInt() == flag)
        {
            flag3 = flag;
        } else
        {
            flag3 = false;
        }
        isUnderminVersion = flag3;
        if (parcel.readInt() != flag)
        {
            flag = false;
        }
        isForceUpdateDate = flag;
        parcel.readList(buttonData, getClass().getClassLoader());
        xmlString = parcel.readString();
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int j = 1;
        parcel.writeString(title);
        parcel.writeString(message);
        parcel.writeString(minOsVersion);
        parcel.writeString(latestAppVersion);
        parcel.writeString(minAppVersion);
        parcel.writeString(forceUpdateDate);
        int k;
        int l;
        int i1;
        if (hasUpdate)
        {
            k = j;
        } else
        {
            k = 0;
        }
        parcel.writeInt(k);
        if (hasNewUpdate)
        {
            l = j;
        } else
        {
            l = 0;
        }
        parcel.writeInt(l);
        if (isUnderminVersion)
        {
            i1 = j;
        } else
        {
            i1 = 0;
        }
        parcel.writeInt(i1);
        if (!isForceUpdateDate)
        {
            j = 0;
        }
        parcel.writeInt(j);
        parcel.writeList(buttonData);
        parcel.writeString(xmlString);
    }

}
