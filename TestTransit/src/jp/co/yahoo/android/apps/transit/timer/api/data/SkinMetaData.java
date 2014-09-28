// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api.data;

import java.io.Serializable;

public class SkinMetaData
    implements Serializable
{

    private static final long serialVersionUID = 0xf5321cb226007016L;
    public boolean isDelete;
    public boolean isDownloaded;
    public boolean isScaleble;
    public boolean isSetting;
    public boolean isUpdate;
    public int nCount;
    public int nInterval;
    public int nSpaceid;
    public String sDescription;
    public String sDownloadUrl;
    public String sEndDate;
    public String sIconPath;
    public String sIconUrl;
    public String sId;
    public String sPath;
    public String sStartDate;
    public String sThumbnailPath;
    public String sThumbnailUrl;
    public String sTitle;
    public String sUpdateDate;

    public SkinMetaData()
    {
        nInterval = 20;
        nSpaceid = 0;
        isScaleble = true;
        isSetting = false;
        isDownloaded = false;
        isUpdate = false;
        isDelete = false;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof SkinMetaData)
        {
            for (SkinMetaData skinmetadata = (SkinMetaData)obj; isSetting != skinmetadata.isSetting || isDownloaded != skinmetadata.isDownloaded || isUpdate != skinmetadata.isUpdate || isDelete != skinmetadata.isDelete;)
            {
                return false;
            }

            return true;
        } else
        {
            return super.equals(obj);
        }
    }
}
