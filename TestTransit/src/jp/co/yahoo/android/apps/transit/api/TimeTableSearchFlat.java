// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api;

import android.content.Context;
import android.os.Bundle;
import java.util.HashMap;
import jp.co.yahoo.android.apps.transit.api.data.TimeTableItemData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api:
//            NaviApiBase

public class TimeTableSearchFlat extends NaviApiBase
{

    public static final int KIND_HOLIDAY = 4;
    public static final int KIND_ORDINARY = 1;
    public static final int KIND_SATURDAY = 2;
    public static final String TRAIN_KIND_EXP = "3";
    public static final String TRAIN_KIND_NORMAL = "1";
    public static final String TRAIN_KIND_SEMIEXP = "2";
    private String name;
    private Bundle objResult;
    private Bundle objResultDict;

    public TimeTableSearchFlat(Context context, ApiBase.ApiListener apilistener)
    {
        super(context, apilistener);
        super.sMsg = context.getString(0x7f0d04a9);
        super.sMsgErr = context.getString(0x7f0d010b);
        setUri(getContext().getString(0x7f0d0045));
    }

    protected void analyzeDictionaly(JSONObject jsonobject)
    {
        objResultDict = new Bundle();
        JSONArray jsonarray;
        JSONArray jsonarray1;
        Bundle bundle;
        Bundle bundle1;
        jsonarray = jsonobject.optJSONObject("KindInfo").optJSONArray("Kind");
        jsonarray1 = jsonobject.optJSONObject("DestinationInfo").optJSONArray("Destination");
        bundle = new Bundle();
        bundle1 = new Bundle();
        if (jsonarray1.length() <= 0)
        {
            break MISSING_BLOCK_LABEL_179;
        }
        int i = 0;
        do
        {
            int j;
            JSONObject jsonobject1;
            String s;
            String s1;
            Bundle bundle2;
            try
            {
                if (i >= jsonarray1.length())
                {
                    break;
                }
                JSONObject jsonobject2 = jsonarray1.optJSONObject(i);
                String s2 = jsonobject2.optString("Info");
                String s3 = jsonobject2.optString("Mark");
                if (TransitUtil.isEmpty(s3))
                {
                    s3 = getContext().getString(0x7f0d052a);
                }
                Bundle bundle3 = new Bundle();
                bundle3.putString("info", s2);
                bundle3.putString("mark", s3);
                bundle1.putBundle(jsonobject2.optString("Id"), bundle3);
            }
            catch (Exception exception)
            {
                return;
            }
            i++;
        } while (true);
        objResultDict.putBundle("dest", bundle1);
        if (jsonarray.length() <= 0)
        {
            break MISSING_BLOCK_LABEL_301;
        }
        j = 0;
_L2:
        if (j >= jsonarray.length())
        {
            break; /* Loop/switch isn't completed */
        }
        jsonobject1 = jsonarray.optJSONObject(j);
        s = jsonobject1.optString("Info");
        s1 = jsonobject1.optString("Mark");
        if (TransitUtil.isEmpty(s1))
        {
            s1 = getContext().getString(0x7f0d052a);
        }
        bundle2 = new Bundle();
        bundle2.putString("info", s);
        bundle2.putString("mark", s1);
        bundle.putBundle(jsonobject1.optString("Id"), bundle2);
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        objResultDict.putBundle("kind", bundle);
    }

    protected void analyzeFeature(JSONArray jsonarray)
    {
        if (jsonarray == null)
        {
            return;
        }
        Bundle bundle;
        JSONArray jsonarray1;
        JSONArray jsonarray2;
        JSONArray jsonarray3;
        objResult = new Bundle();
        bundle = new Bundle();
        jsonarray1 = jsonarray.getJSONObject(0).getJSONObject("RouteInfo").getJSONObject("TimeTable").getJSONArray("Departure");
        JSONObject jsonobject = getDictionary();
        jsonarray2 = jsonobject.getJSONObject("DestinationInfo").getJSONArray("Destination");
        jsonarray3 = jsonobject.getJSONObject("KindInfo").getJSONArray("Kind");
        int i;
        int j;
        i = 0;
        j = 0;
_L11:
        if (j >= jsonarray1.length()) goto _L2; else goto _L1
_L1:
        JSONObject jsonobject1;
        JSONArray jsonarray4;
        jsonobject1 = jsonarray1.getJSONObject(j);
        jsonarray4 = jsonobject1.getJSONArray("Time");
        int k = 0;
_L7:
        JSONObject jsonobject2;
        TimeTableItemData timetableitemdata;
        if (k >= jsonarray4.length())
        {
            break MISSING_BLOCK_LABEL_510;
        }
        jsonobject2 = jsonarray4.getJSONObject(k);
        timetableitemdata = new TimeTableItemData();
        timetableitemdata.setMinute(jsonobject2.getInt("Minute"));
        StringBuilder stringbuilder = new StringBuilder();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(Integer.parseInt(jsonobject1.getString("Hour")));
        StringBuilder stringbuilder1 = stringbuilder.append(String.format("%02d", aobj));
        Object aobj1[] = new Object[1];
        aobj1[0] = Integer.valueOf(jsonobject2.getInt("Minute"));
        timetableitemdata.setHourMin(stringbuilder1.append(String.format("%02d", aobj1)).toString());
        timetableitemdata.setDesttype((String)jsonobject2.get("DestinationId"));
        int l = 0;
_L9:
        if (l >= jsonarray2.length()) goto _L4; else goto _L3
_L3:
        JSONObject jsonobject4 = jsonarray2.getJSONObject(l);
        if (!jsonobject4.get("Id").equals(jsonobject2.get("DestinationId"))) goto _L6; else goto _L5
_L5:
        timetableitemdata.setDestmark(jsonobject4.getString("Mark"));
          goto _L4
_L8:
        int i1;
        if (i1 < jsonarray3.length())
        {
            JSONObject jsonobject3 = jsonarray3.getJSONObject(i1);
            if (!jsonobject3.get("Id").equals(jsonobject2.get("KindId")))
            {
                break MISSING_BLOCK_LABEL_504;
            }
            timetableitemdata.setTrainmark(jsonobject3.getString("Mark"));
            timetableitemdata.setTraintype(jsonobject3.getString("Id"));
        }
        Boolean boolean1 = Boolean.valueOf(jsonobject2.getBoolean("FirstStation"));
        if (boolean1.booleanValue())
        {
            timetableitemdata.setFirstStation(boolean1.booleanValue());
        }
        Boolean boolean2 = Boolean.valueOf(jsonobject2.getBoolean("ExtraLine"));
        if (boolean2.booleanValue())
        {
            timetableitemdata.setExtraLine(boolean2.booleanValue());
        }
        bundle.putSerializable(String.valueOf(i), timetableitemdata);
        i++;
        k++;
          goto _L7
_L2:
        try
        {
            objResult.putBundle("timetable", bundle);
            objResult.putBundle("mark", objResultDict);
            return;
        }
        catch (JSONException jsonexception)
        {
            jsonexception.getStackTrace();
        }
        return;
_L4:
        i1 = 0;
          goto _L8
_L6:
        l++;
          goto _L9
        i1++;
          goto _L8
        j++;
        if (true) goto _L11; else goto _L10
_L10:
    }

    public String getCode()
    {
        return (String)param.get(getContext().getString(0x7f0d023f));
    }

    public String getDate()
    {
        return (String)param.get(getContext().getString(0x7f0d01b0));
    }

    public Bundle getMark()
    {
        Bundle bundle = objResult;
        Bundle bundle1 = null;
        if (bundle != null)
        {
            bundle1 = objResult.getBundle("mark");
        }
        return bundle1;
    }

    public String getName()
    {
        return name;
    }

    public Bundle getResults()
    {
        return objResult;
    }

    public void setCode(String s)
    {
        param.put(getContext().getString(0x7f0d023f), s);
    }

    public void setDate(String s)
    {
        param.put(getContext().getString(0x7f0d01b0), s);
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
    }

    public void setName(String s)
    {
        name = s;
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
