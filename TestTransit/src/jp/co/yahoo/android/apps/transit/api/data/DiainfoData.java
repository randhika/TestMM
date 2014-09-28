// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api.data;

import java.io.Serializable;
import java.util.ArrayList;

public class DiainfoData
    implements Serializable
{
    public static class DiainfoDataDetail
        implements Serializable
    {

        public static final String STATUS_CHANGE = "000200010006";
        public static final String STATUS_DELAY = "000200010003";
        public static final String STATUS_ETC = "000200010099";
        public static final String STATUS_NORMAL = "000200010005";
        public static final String STATUS_RESTART = "000200010002";
        public static final String STATUS_STATUS = "000200010004";
        public static final String STATUS_STOP = "000200010001";
        private static final long serialVersionUID = 0x3675acca21bdd03eL;
        private String Message;
        private String UpdateDate;
        private String cause;
        private String status;
        private String statusCode;

        public String getCause()
        {
            return cause;
        }

        public String getMessage()
        {
            return Message;
        }

        public String getStatus()
        {
            return status;
        }

        public String getStatusCode()
        {
            return statusCode;
        }

        public String getUpdateDate()
        {
            return UpdateDate;
        }

        public void setCause(String s)
        {
            cause = s;
        }

        public void setMessage(String s)
        {
            Message = s;
        }

        public void setStatus(String s)
        {
            status = s;
        }

        public void setStatusCode(String s)
        {
            statusCode = s;
        }

        public void setUpdateDate(String s)
        {
            UpdateDate = s;
        }

        public DiainfoDataDetail()
        {
        }
    }


    private static final long serialVersionUID = 0xca6dd1f296e7e538L;
    private boolean bCondition;
    private String cpId;
    private ArrayList detailinfo;
    private String diainfo;
    private boolean isDetail;
    private String railAreaCode;
    private String railAreaName;
    private String railCompanyCode;
    private String railCompanyName;
    private String railRangeCode;
    private String railTypeCode;
    private String railTypeName;
    private String railcode;
    private String railname;

    public DiainfoData()
    {
        isDetail = false;
    }

    public String getCpId()
    {
        return cpId;
    }

    public ArrayList getDetailinfo()
    {
        return detailinfo;
    }

    public String getDiainfo()
    {
        return diainfo;
    }

    public String getRailAreaCode()
    {
        return railAreaCode;
    }

    public String getRailAreaName()
    {
        return railAreaName;
    }

    public String getRailCode()
    {
        return railcode;
    }

    public String getRailCompanyCode()
    {
        return railCompanyCode;
    }

    public String getRailCompanyName()
    {
        return railCompanyName;
    }

    public String getRailName()
    {
        return railname;
    }

    public String getRailRangeCode()
    {
        return railRangeCode;
    }

    public String getRailTypeCode()
    {
        return railTypeCode;
    }

    public String getRailTypeName()
    {
        return railTypeName;
    }

    public boolean isCondition()
    {
        return bCondition;
    }

    public boolean isDetail()
    {
        return isDetail;
    }

    public void setCondition(boolean flag)
    {
        bCondition = flag;
    }

    public void setCpId(String s)
    {
        cpId = s;
    }

    public void setDetail(boolean flag)
    {
        isDetail = flag;
    }

    public void setDetailinfo(ArrayList arraylist)
    {
        detailinfo = arraylist;
    }

    public void setDiainfo(String s)
    {
        diainfo = s;
    }

    public void setRailAreaCode(String s)
    {
        railAreaCode = s;
    }

    public void setRailAreaName(String s)
    {
        railAreaName = s;
    }

    public void setRailCompanyCode(String s)
    {
        railCompanyCode = s;
    }

    public void setRailCompanyName(String s)
    {
        railCompanyName = s;
    }

    public void setRailName(String s)
    {
        railname = s;
    }

    public void setRailRangeCode(String s)
    {
        railRangeCode = s;
    }

    public void setRailTypeCode(String s)
    {
        railTypeCode = s;
    }

    public void setRailTypeName(String s)
    {
        railTypeName = s;
    }

    public void setRailcode(String s)
    {
        railcode = s;
    }

    public String toString()
    {
        return railname;
    }
}
