// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.api;

import android.content.Context;
import android.os.Bundle;
import java.util.HashMap;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.api.data.TimeTableItemData;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;
import jp.co.yahoo.android.yolp.common.YolpApiBase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TimeTableSearch extends YolpApiBase
{

    public static final int KIND_HOLIDAY = 4;
    public static final int KIND_ORDINARY = 1;
    public static final int KIND_SATURDAY = 2;
    public static final String TRAIN_KIND_EXP = "3";
    public static final String TRAIN_KIND_NORMAL = "1";
    public static final String TRAIN_KIND_SEMIEXP = "2";
    private Context context;
    private int nKind;
    private int nTraffic;
    private String sName;
    private StationData station;

    public TimeTableSearch(Context context1)
    {
        super(context1);
        nKind = 0;
        context = context1;
        setUri(context1.getString(0x7f0d0045));
        setAppid(context1.getString(0x7f0d003b));
    }

    protected void analyze()
    {
        JSONArray jsonarray = getFeature();
        if (jsonarray == null)
        {
            return;
        }
        Bundle bundle;
        Bundle bundle1;
        JSONArray jsonarray1;
        JSONArray jsonarray2;
        JSONArray jsonarray3;
        TimeTableItemData timetableitemdata;
        bundle = new Bundle();
        bundle1 = new Bundle();
        JSONObject jsonobject = jsonarray.getJSONObject(0);
        if (CountdownUtil.isEmpty(getKind()))
        {
            nKind = Integer.parseInt(jsonobject.getJSONObject("RouteInfo").getJSONObject("Property").getString("DateKind"));
        }
        jsonarray1 = jsonobject.getJSONObject("RouteInfo").getJSONObject("TimeTable").getJSONArray("Departure");
        JSONObject jsonobject1 = getDictionary();
        jsonarray2 = jsonobject1.getJSONObject("DestinationInfo").getJSONArray("Destination");
        jsonarray3 = jsonobject1.getJSONObject("KindInfo").getJSONArray("Kind");
        timetableitemdata = new TimeTableItemData();
        setTraffic(jsonobject.getJSONObject("RouteInfo").getJSONObject("TimeTable").getInt("Traffic"));
        setName(jsonobject.getString("Name"));
        int i;
        int j;
        i = 0;
        j = 0;
_L11:
        if (j >= jsonarray1.length()) goto _L2; else goto _L1
_L1:
        JSONArray jsonarray4;
        String s;
        JSONObject jsonobject2 = jsonarray1.getJSONObject(j);
        jsonarray4 = jsonobject2.getJSONArray("Time");
        s = jsonobject2.getString("Hour");
        int k = 0;
_L7:
        JSONObject jsonobject3;
        TimeTableItemData timetableitemdata1;
        if (k >= jsonarray4.length())
        {
            break MISSING_BLOCK_LABEL_573;
        }
        jsonobject3 = jsonarray4.getJSONObject(k);
        timetableitemdata1 = new TimeTableItemData();
        timetableitemdata1.setMinute(jsonobject3.getInt("Minute"));
        timetableitemdata1.setHour(Integer.parseInt(s));
        int l = 0;
_L9:
        if (l >= jsonarray2.length()) goto _L4; else goto _L3
_L3:
        JSONObject jsonobject5 = jsonarray2.getJSONObject(l);
        if (!jsonobject5.get("Id").equals(jsonobject3.get("DestinationId"))) goto _L6; else goto _L5
_L5:
        timetableitemdata1.setDestmark(jsonobject5.getString("Mark"));
        timetableitemdata1.setDesttyep(jsonobject5.getString("Id"));
        timetableitemdata1.setDestinfo(jsonobject5.getString("Info"));
          goto _L4
_L8:
        int i1;
        if (i1 < jsonarray3.length())
        {
            JSONObject jsonobject4 = jsonarray3.getJSONObject(i1);
            if (!jsonobject4.get("Id").equals(jsonobject3.get("KindId")))
            {
                break MISSING_BLOCK_LABEL_567;
            }
            timetableitemdata1.setTrainmark(jsonobject4.getString("Mark"));
            timetableitemdata1.setTraintype(jsonobject4.getString("Id"));
            timetableitemdata1.setTraininfo(jsonobject4.getString("Info"));
        }
        if (Boolean.valueOf(jsonobject3.getBoolean("FirstStation")).booleanValue())
        {
            timetableitemdata1.setStartStation(true);
        }
        if (j != 0 || k != 0)
        {
            break MISSING_BLOCK_LABEL_453;
        }
        timetableitemdata1.setFirstStation(true);
        Boolean boolean1 = Boolean.valueOf(jsonobject3.getBoolean("ExtraLine"));
        if (boolean1.booleanValue())
        {
            timetableitemdata1.setExtraLine(boolean1.booleanValue());
        }
        timetableitemdata1.setIndex(i);
        bundle1.putSerializable(Integer.toString(i), timetableitemdata1);
        i++;
        timetableitemdata = timetableitemdata1;
        k++;
          goto _L7
_L2:
        try
        {
            timetableitemdata.setLastStation(true);
            bundle.putBundle("timetable", bundle1);
            bundle.putBundle("dest", analyzeDictionary());
            setResult(bundle);
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

    protected Bundle analyzeDictionary()
    {
        Bundle bundle = new Bundle();
        JSONArray jsonarray;
        JSONArray jsonarray1;
        Bundle bundle1;
        Bundle bundle2;
        JSONObject jsonobject = getDictionary();
        jsonarray = jsonobject.optJSONObject("KindInfo").optJSONArray("Kind");
        jsonarray1 = jsonobject.optJSONObject("DestinationInfo").optJSONArray("Destination");
        bundle1 = new Bundle();
        bundle2 = new Bundle();
        if (jsonarray1.length() <= 0)
        {
            break MISSING_BLOCK_LABEL_198;
        }
        int i = 0;
        do
        {
            int j;
            JSONObject jsonobject1;
            String s;
            String s1;
            String s2;
            Bundle bundle3;
            try
            {
                if (i >= jsonarray1.length())
                {
                    break;
                }
                JSONObject jsonobject2 = jsonarray1.optJSONObject(i);
                String s3 = jsonobject2.optString("Info");
                String s4 = jsonobject2.optString("Mark");
                String s5 = jsonobject2.optString("Id");
                if (CountdownUtil.isEmpty(s4))
                {
                    s4 = context.getString(0x7f0d052a);
                }
                Bundle bundle4 = new Bundle();
                bundle4.putString("info", s3);
                bundle4.putString("mark", s4);
                bundle4.putString("Id", s5);
                bundle2.putBundle(Integer.toString(i), bundle4);
            }
            catch (Exception exception)
            {
                return bundle;
            }
            i++;
        } while (true);
        bundle.putBundle("dest", bundle2);
        if (jsonarray.length() <= 0)
        {
            break MISSING_BLOCK_LABEL_340;
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
        s2 = jsonobject1.optString("Id");
        if (CountdownUtil.isEmpty(s1))
        {
            s1 = context.getString(0x7f0d052a);
        }
        bundle3 = new Bundle();
        bundle3.putString("info", s);
        bundle3.putString("mark", s1);
        bundle3.putString("Id", s2);
        bundle1.putBundle(Integer.toString(j), bundle3);
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        bundle.putBundle("kind", bundle1);
        return bundle;
    }

    public String getKind()
    {
        return (String)super.param.get("kind");
    }

    public Bundle getMark()
    {
        Object obj = getResult();
        Bundle bundle = null;
        if (obj != null)
        {
            bundle = ((Bundle)getResult()).getBundle("mark");
        }
        return bundle;
    }

    public StationData getStation()
    {
        return station;
    }

    public int getTodayKind()
    {
        return nKind;
    }

    public void setCode(String s)
    {
        setParam("code", s);
    }

    public void setDate(String s)
    {
        setParam("date", s);
    }

    public void setGroup(String s)
    {
        setParam("grouping", s);
    }

    public void setId(String s)
    {
        setParam("id", s);
    }

    public void setKind(int i)
    {
        setParam("kind", Integer.toString(i));
    }

    public void setName(String s)
    {
        sName = s;
    }

    public void setSince(int i)
    {
        setParam("since", Integer.toString(i));
    }

    public void setStation(StationData stationdata)
    {
        station = stationdata;
    }

    public void setTraffic(int i)
    {
        nTraffic = i;
    }

    public void setType(String s)
    {
        if (s.equals("bus"))
        {
            setUri(context.getString(0x7f0d0044));
            return;
        } else
        {
            setUri(context.getString(0x7f0d0045));
            return;
        }
    }

    public void setUntil(int i)
    {
        setParam("until", Integer.toString(i));
    }
}
