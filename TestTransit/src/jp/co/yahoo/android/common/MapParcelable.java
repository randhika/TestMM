// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Map;

public class MapParcelable
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public MapParcelable createFromParcel(Parcel parcel)
        {
            return new MapParcelable(parcel);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public MapParcelable[] newArray(int i)
        {
            return new MapParcelable[i];
        }

    };
    public Map mMap;

    public MapParcelable(Parcel parcel)
    {
        mMap = parcel.readHashMap(null);
    }

    public MapParcelable(Map map)
    {
        mMap = map;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeMap(mMap);
    }

}
