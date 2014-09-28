// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.Context;
import java.util.HashMap;
import jp.co.yahoo.android.apps.transit.api.data.TimeTableData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            NaviApiBase

public class TimeTableSearch extends NaviApiBase
{

    public static final int KIND_HOLIDAY = 4;
    public static final int KIND_ORDINARY = 1;
    public static final int KIND_SATURDAY = 2;
    public static final String TRAIN_KIND_EXP = "4";
    public static final String TRAIN_KIND_NORMAL = "1";
    public static final String TRAIN_KIND_SEMIEXP = "3";
    public static final String TRAIN_KIND_SUBEXP = "2";
    public static final String TRAIN_KIND_SUPEXP = "5";
    private TimeTableData timeTableData;

    public TimeTableSearch(Context context, ApiBase.ApiListener apilistener)
    {
        super(context, apilistener);
        super.sMsg = context.getString(0x7f0d04a9);
        super.sMsgErr = context.getString(0x7f0d010b);
        setUri(getContext().getString(0x7f0d0045));
        timeTableData = new TimeTableData();
    }

    protected void analyzeDictionaly(JSONObject jsonobject)
    {
        timeTableData.setDictionary(jsonobject);
    }

    protected void analyzeFeature(JSONArray jsonarray)
    {
        if (jsonarray == null)
        {
            return;
        } else
        {
            timeTableData.setFeatures(jsonarray);
            setResult(timeTableData);
            return;
        }
    }

    public void setCode(String s)
    {
        param.put(getContext().getString(0x7f0d023f), s);
        timeTableData.code = s;
    }

    public void setDate(String s)
    {
        if (!TransitUtil.isEmpty(s))
        {
            param.put(getContext().getString(0x7f0d01b0), s);
            timeTableData.date = s;
        }
    }

    public void setFunc(String s)
    {
        param.put(getContext().getString(0x7f0d01aa), s);
    }

    public void setGroup(String s)
    {
        param.put(getContext().getString(0x7f0d01b1), s);
    }

    public void setId(String s)
    {
        param.put(getContext().getString(0x7f0d01b2), s);
        timeTableData.gId = s;
    }

    public void setKind(int i)
    {
        param.put(getContext().getString(0x7f0d01ab), Integer.toString(i));
        timeTableData.kind = String.valueOf(i);
    }

    public void setSince(int i)
    {
        if (i >= 0)
        {
            param.put(getContext().getString(0x7f0d0235), Integer.toString(i));
        }
    }

    public void setTid(String s)
    {
        param.put(getContext().getString(0x7f0d01ac), s);
    }

    public void setType(String s)
    {
        if (s.equals("bus"))
        {
            setUri(getContext().getString(0x7f0d0044));
            return;
        } else
        {
            setUri(getContext().getString(0x7f0d0045));
            return;
        }
    }

    public void setUntil(int i)
    {
        if (i >= 0)
        {
            param.put(getContext().getString(0x7f0d0248), Integer.toString(i));
        }
    }
}
