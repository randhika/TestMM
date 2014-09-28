// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api.data;

import java.io.Serializable;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api.data:
//            RailDirectionData, StationData

public class DiainfoCgmData
    implements Serializable
{

    private static final long serialVersionUID = 0xf8f4e201c41f8977L;
    private String cause;
    private String causeId;
    private String cpid;
    private String crowd;
    private String crowdId;
    private String postId;
    private RailDirectionData raildir;
    private StationData station;
    private String status;
    private String statusId;
    private String updateDate;

    public DiainfoCgmData()
    {
    }

    public String getCause()
    {
        return cause;
    }

    public String getCauseId()
    {
        return causeId;
    }

    public String getCpId()
    {
        return cpid;
    }

    public String getCrowd()
    {
        return crowd;
    }

    public String getCrowdId()
    {
        return crowdId;
    }

    public String getPostId()
    {
        return postId;
    }

    public RailDirectionData getRaildir()
    {
        return raildir;
    }

    public StationData getStation()
    {
        return station;
    }

    public String getStatus()
    {
        return status;
    }

    public String getStatusId()
    {
        return statusId;
    }

    public String getUpdateDate()
    {
        return updateDate;
    }

    public void setCause(String s)
    {
        cause = s;
    }

    public void setCauseId(String s)
    {
        causeId = s;
    }

    public void setCpId(String s)
    {
        cpid = s;
    }

    public void setCrowd(String s)
    {
        crowd = s;
    }

    public void setCrowdId(String s)
    {
        crowdId = s;
    }

    public void setPostId(String s)
    {
        postId = s;
    }

    public void setRaildir(RailDirectionData raildirectiondata)
    {
        raildir = raildirectiondata;
    }

    public void setStation(StationData stationdata)
    {
        station = stationdata;
    }

    public void setStatus(String s)
    {
        status = s;
    }

    public void setStatusId(String s)
    {
        statusId = s;
    }

    public void setUpdateDate(String s)
    {
        updateDate = s;
    }
}
