// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.api.data;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import jp.co.yahoo.android.apps.transit.Transit;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.apps.transit.api.data:
//            StationData, RailDirectionData, DiainfoData, TimeTableItemData

public class TimeTableData
    implements Serializable
{
    public static class CarTypeData
        implements Serializable
    {

        private static final long serialVersionUID = 0x7ccfa0dcf2d06ad4L;
        public String cartype;
        public String id;

        public boolean isEqual(CarTypeData cartypedata)
        {
            if (!id.equals(cartypedata.id))
            {
                return false;
            }
            boolean flag;
            try
            {
                flag = cartype.equals(cartypedata.cartype);
            }
            catch (Exception exception)
            {
                return false;
            }
label0:
            {
                if (flag)
                {
                    return true;
                }
                break label0;
            }
        }

        public CarTypeData()
        {
        }
    }

    public static class TimeData
        implements Serializable
    {

        private static final long serialVersionUID = 0x5a5d353c18857374L;
        public int carId;
        public int destId;
        public boolean extraLine;
        public boolean firstStation;
        public int kindId;
        public int lineId;
        public int minute;

        public boolean isEqual(TimeData timedata)
        {
            if (minute != timedata.minute)
            {
                return false;
            }
            boolean flag;
            boolean flag1;
            if (lineId != timedata.lineId || kindId != timedata.kindId || destId != timedata.destId || carId != timedata.carId || extraLine != timedata.extraLine)
            {
                break MISSING_BLOCK_LABEL_88;
            }
            flag = firstStation;
            flag1 = timedata.firstStation;
            if (flag == flag1)
            {
                return true;
            }
            break MISSING_BLOCK_LABEL_88;
            Exception exception;
            exception;
            return false;
        }

        public TimeData()
        {
        }
    }

    public static class TypeData
        implements Serializable
    {

        private static final long serialVersionUID = 0xe36829260285a831L;
        public String id;
        public String info;
        public String mark;

        public boolean isEqual(TypeData typedata)
        {
            if (!id.equals(typedata.id))
            {
                return false;
            }
            boolean flag;
            if (!mark.equals(typedata.mark))
            {
                break MISSING_BLOCK_LABEL_49;
            }
            flag = info.equals(typedata.info);
            if (flag)
            {
                return true;
            }
            break MISSING_BLOCK_LABEL_49;
            Exception exception;
            exception;
            return false;
        }

        public TypeData()
        {
        }
    }

    public static class TypeDataComparator
        implements Comparator
    {

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((TypeData)obj, (TypeData)obj1);
        }

        public int compare(TypeData typedata, TypeData typedata1)
        {
            return Integer.parseInt(typedata.id) >= Integer.parseInt(typedata1.id) ? 1 : -1;
        }

        public TypeDataComparator()
        {
        }
    }


    public static final int DISPLAY_MODE_H = 2;
    public static final int DISPLAY_MODE_V = 1;
    private static final long serialVersionUID = 0x9ed899e526d5cf4aL;
    public ArrayList carInfo;
    public String code;
    public String date;
    public int dateKind;
    public Map departure;
    public ArrayList destInfo;
    public String direction;
    public int displayMode;
    public ArrayList filterDest;
    public ArrayList filterKind;
    public String gId;
    public boolean isEntire;
    public String kind;
    public ArrayList kindInfo;
    public String name;
    public String railName;
    public String source;
    public HashMap timeTable;
    public int traffic;
    public String updateDate;

    public TimeTableData()
    {
        dateKind = 0;
        traffic = 0;
        departure = null;
        timeTable = null;
        kindInfo = null;
        destInfo = null;
        carInfo = null;
        filterKind = null;
        filterDest = null;
        displayMode = 1;
        isEntire = true;
    }

    private void setProperty(JSONObject jsonobject)
    {
        try
        {
            JSONObject jsonobject1 = jsonobject.getJSONObject("Property");
            dateKind = jsonobject1.optInt("DateKind");
            railName = jsonobject1.optString("Railname");
            source = jsonobject1.optString("Source");
            direction = jsonobject1.optString("Direction");
            updateDate = jsonobject1.optString("UpdateDate");
            return;
        }
        catch (Exception exception)
        {
            isEntire = false;
        }
    }

    private void setTimeTable(JSONObject jsonobject)
    {
        JSONArray jsonarray;
        jsonarray = jsonobject.getJSONObject("TimeTable").getJSONArray("Departure");
        traffic = jsonobject.getJSONObject("TimeTable").optInt("Traffic");
        departure = new LinkedHashMap();
        int i = 0;
_L3:
        JSONArray jsonarray1;
        ArrayList arraylist;
        int j;
        if (i >= jsonarray.length())
        {
            break MISSING_BLOCK_LABEL_243;
        }
        JSONObject jsonobject1 = jsonarray.getJSONObject(i);
        jsonarray1 = jsonobject1.getJSONArray("Time");
        arraylist = new ArrayList();
        j = jsonobject1.optInt("Hour");
        int k = 0;
_L2:
        if (k >= jsonarray1.length())
        {
            break; /* Loop/switch isn't completed */
        }
        JSONObject jsonobject2 = jsonarray1.getJSONObject(k);
        TimeData timedata = new TimeData();
        timedata.minute = jsonobject2.optInt("Minute");
        timedata.lineId = jsonobject2.optInt("LineId");
        timedata.kindId = jsonobject2.optInt("KindId");
        timedata.destId = jsonobject2.optInt("DestinationId");
        timedata.carId = jsonobject2.optInt("CarType");
        timedata.extraLine = jsonobject2.optBoolean("ExtraLine");
        timedata.firstStation = jsonobject2.optBoolean("FirstStation");
        arraylist.add(timedata);
        k++;
        if (true) goto _L2; else goto _L1
_L1:
        departure.put(Integer.valueOf(j), arraylist);
        i++;
          goto _L3
        Exception exception;
        exception;
        isEntire = false;
    }

    public String getDisplayDateString()
    {
        int i = Integer.parseInt(date.substring(0, 4));
        int j = Integer.parseInt(date.substring(4, 6));
        int k = Integer.parseInt(date.substring(6, 8));
        String s = Transit.getDayOfWeekJP(i, j, k);
        if (!s.equals("\u65E5") && dateKind == 4)
        {
            s = "\u795D";
        }
        Locale locale = Locale.JAPANESE;
        Object aobj[] = new Object[4];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Integer.valueOf(j);
        aobj[2] = Integer.valueOf(k);
        aobj[3] = s;
        return String.format(locale, "%1$d\u5E74%2$d\u6708%3$d\u65E5(%4$s)", aobj);
    }

    public SparseArray getMappedCarTypeInfo(ArrayList arraylist)
    {
        SparseArray sparsearray;
        if (arraylist == null || arraylist.size() < 1)
        {
            sparsearray = null;
        } else
        {
            sparsearray = new SparseArray();
            Iterator iterator = arraylist.iterator();
            while (iterator.hasNext()) 
            {
                CarTypeData cartypedata = (CarTypeData)iterator.next();
                sparsearray.put(Integer.parseInt(cartypedata.id), cartypedata);
            }
        }
        return sparsearray;
    }

    public SparseArray getMappedTypeInfo(ArrayList arraylist)
    {
        SparseArray sparsearray = new SparseArray();
        TypeData typedata;
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); sparsearray.put(Integer.parseInt(typedata.id), typedata))
        {
            typedata = (TypeData)iterator.next();
        }

        return sparsearray;
    }

    public ArrayList getSortedTypeInfo(ArrayList arraylist)
    {
        ArrayList arraylist1 = new ArrayList();
        arraylist1.addAll(arraylist);
        Collections.sort(arraylist1, new TypeDataComparator());
        return arraylist1;
    }

    public boolean isEqual(TimeTableData timetabledata)
    {
        if (!isEntire)
        {
            break MISSING_BLOCK_LABEL_475;
        }
        if (!timetabledata.isEntire)
        {
            return false;
        }
        Iterator iterator;
        if (!name.equals(timetabledata.name) || dateKind != timetabledata.dateKind || !railName.equals(timetabledata.railName) || !source.equals(timetabledata.source) || !direction.equals(timetabledata.direction) || !updateDate.equals(timetabledata.updateDate) || traffic != timetabledata.traffic || departure.size() != timetabledata.departure.size())
        {
            break MISSING_BLOCK_LABEL_475;
        }
        iterator = departure.keySet().iterator();
_L4:
        ArrayList arraylist;
        ArrayList arraylist1;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        Integer integer = (Integer)iterator.next();
        arraylist = (ArrayList)departure.get(integer);
        arraylist1 = (ArrayList)timetabledata.departure.get(integer);
        if (arraylist == null || arraylist1 == null)
        {
            break MISSING_BLOCK_LABEL_475;
        }
        if (arraylist.size() != arraylist1.size())
        {
            break MISSING_BLOCK_LABEL_475;
        }
        int l = 0;
_L2:
        if (l >= arraylist.size())
        {
            break; /* Loop/switch isn't completed */
        }
        if (!((TimeData)arraylist.get(l)).isEqual((TimeData)arraylist1.get(l)))
        {
            break MISSING_BLOCK_LABEL_475;
        }
        l++;
        if (true) goto _L2; else goto _L1
_L1:
        if (true) goto _L4; else goto _L3
_L3:
        if (kindInfo.size() != timetabledata.kindInfo.size())
        {
            break MISSING_BLOCK_LABEL_475;
        }
        int i = 0;
_L6:
        if (i >= kindInfo.size())
        {
            break; /* Loop/switch isn't completed */
        }
        if (!((TypeData)kindInfo.get(i)).isEqual((TypeData)timetabledata.kindInfo.get(i)))
        {
            break MISSING_BLOCK_LABEL_475;
        }
        i++;
        if (true) goto _L6; else goto _L5
_L5:
        if (destInfo.size() != timetabledata.destInfo.size())
        {
            break MISSING_BLOCK_LABEL_475;
        }
        int j = 0;
_L8:
        if (j >= destInfo.size())
        {
            break; /* Loop/switch isn't completed */
        }
        if (!((TypeData)destInfo.get(j)).isEqual((TypeData)timetabledata.destInfo.get(j)))
        {
            break MISSING_BLOCK_LABEL_475;
        }
        j++;
        if (true) goto _L8; else goto _L7
_L7:
        if (carInfo.size() != timetabledata.carInfo.size())
        {
            break MISSING_BLOCK_LABEL_475;
        }
        int k = 0;
_L10:
        boolean flag;
        if (k >= carInfo.size())
        {
            break; /* Loop/switch isn't completed */
        }
        flag = ((CarTypeData)carInfo.get(k)).isEqual((CarTypeData)timetabledata.carInfo.get(k));
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_475;
        }
        k++;
        if (true) goto _L10; else goto _L9
_L9:
        return true;
        Exception exception;
        exception;
        return false;
    }

    public void setDictionary(JSONObject jsonobject)
    {
        JSONArray jsonarray;
        JSONArray jsonarray1;
        JSONArray jsonarray2;
        jsonarray = jsonobject.getJSONObject("KindInfo").getJSONArray("Kind");
        jsonarray1 = jsonobject.getJSONObject("DestinationInfo").getJSONArray("Destination");
        jsonarray2 = jsonobject.optJSONArray("CarType");
        kindInfo = new ArrayList();
        int i = 0;
_L2:
        if (i >= jsonarray.length())
        {
            break; /* Loop/switch isn't completed */
        }
        JSONObject jsonobject3 = jsonarray.getJSONObject(i);
        TypeData typedata1 = new TypeData();
        typedata1.id = jsonobject3.optString("Id");
        typedata1.mark = jsonobject3.optString("Mark");
        typedata1.info = jsonobject3.optString("Info");
        kindInfo.add(typedata1);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        destInfo = new ArrayList();
        int j = 0;
_L4:
        if (j >= jsonarray1.length())
        {
            break; /* Loop/switch isn't completed */
        }
        JSONObject jsonobject2 = jsonarray1.getJSONObject(j);
        TypeData typedata = new TypeData();
        typedata.id = jsonobject2.optString("Id");
        typedata.mark = jsonobject2.optString("Mark");
        typedata.info = jsonobject2.optString("Info");
        destInfo.add(typedata);
        j++;
        if (true) goto _L4; else goto _L3
_L3:
        if (jsonarray2 == null)
        {
            break MISSING_BLOCK_LABEL_423;
        }
        carInfo = new ArrayList();
        int k = 0;
_L7:
        JSONArray jsonarray3;
        CarTypeData cartypedata;
        if (k >= jsonarray2.length())
        {
            break MISSING_BLOCK_LABEL_423;
        }
        JSONObject jsonobject1 = jsonarray2.getJSONObject(k);
        jsonarray3 = jsonobject1.optJSONArray("Name");
        cartypedata = new CarTypeData();
        cartypedata.id = jsonobject1.optString("Id");
        String s;
        int l;
        s = "";
        l = 0;
_L6:
        if (l >= jsonarray3.length())
        {
            break; /* Loop/switch isn't completed */
        }
        if (l <= 0)
        {
            break MISSING_BLOCK_LABEL_366;
        }
        if (!TransitUtil.isEmpty(s) && !TransitUtil.isEmpty(jsonarray3.optString(l)))
        {
            s = (new StringBuilder()).append(s).append("/").toString();
        }
        s = (new StringBuilder()).append(s).append(jsonarray3.optString(l)).toString();
        l++;
        if (true) goto _L6; else goto _L5
_L5:
        cartypedata.cartype = s;
        carInfo.add(cartypedata);
        k++;
          goto _L7
        Exception exception;
        exception;
    }

    public void setFeatures(JSONArray jsonarray)
    {
        try
        {
            JSONObject jsonobject = jsonarray.getJSONObject(0);
            JSONObject jsonobject1 = jsonobject.getJSONObject("RouteInfo");
            name = jsonobject.optString("Name");
            setProperty(jsonobject1);
            setTimeTable(jsonobject1);
            return;
        }
        catch (Exception exception)
        {
            isEntire = false;
        }
    }

    public void setMemoCondition(Bundle bundle, Context context)
    {
        isEntire = false;
        try
        {
            StationData stationdata = (StationData)(StationData)bundle.getSerializable(context.getString(0x7f0d023e));
            RailDirectionData raildirectiondata = (RailDirectionData)(RailDirectionData)bundle.getSerializable(context.getString(0x7f0d01b3));
            name = stationdata.getName();
            dateKind = bundle.getInt(context.getString(0x7f0d01a6), 1);
            railName = stationdata.getDiainfo().getRailName();
            source = raildirectiondata.getSource();
            direction = raildirectiondata.getDirection();
            code = stationdata.getId();
            gId = raildirectiondata.getGroupid();
            kind = String.valueOf(dateKind);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public void setMemoResult(Bundle bundle, Context context)
    {
        int j;
        int k;
        isEntire = false;
        Bundle bundle2;
        Bundle bundle3;
        HashMap hashmap;
        HashMap hashmap1;
        String s;
        int i;
        Bundle bundle4;
        ArrayList arraylist;
        TimeTableItemData timetableitemdata;
        TimeData timedata;
        String s1;
        int l;
        Bundle bundle5;
        TypeData typedata;
        String s2;
        String s3;
        Bundle bundle6;
        TypeData typedata1;
        String s4;
        try
        {
            Bundle bundle1 = bundle.getBundle("mark");
            bundle2 = bundle1.getBundle("kind");
            bundle3 = bundle1.getBundle("dest");
            hashmap = new HashMap();
            hashmap1 = new HashMap();
            s = context.getString(0x7f0d052a);
            destInfo = new ArrayList();
        }
        catch (Exception exception)
        {
            return;
        }
        i = 0;
        if (i >= bundle3.size())
        {
            break MISSING_BLOCK_LABEL_199;
        }
        bundle6 = bundle3.getBundle(Integer.toString(i));
        typedata1 = new TypeData();
        typedata1.id = String.valueOf(i);
        s4 = bundle6.getString("mark");
        if (!s4.equals(s))
        {
            break MISSING_BLOCK_LABEL_189;
        }
        typedata1.mark = "";
_L1:
        typedata1.info = bundle6.getString("info");
        destInfo.add(typedata1);
        hashmap1.put(typedata1.mark, typedata1.id);
        i++;
        break MISSING_BLOCK_LABEL_75;
        typedata1.mark = s4;
          goto _L1
        departure = new LinkedHashMap();
        j = 1;
_L9:
        if (j > 35) goto _L3; else goto _L2
_L2:
        if (!bundle.containsKey(String.valueOf(j))) goto _L5; else goto _L4
_L4:
        bundle4 = bundle.getBundle(String.valueOf(j));
        if (bundle4.size() >= 1) goto _L6; else goto _L5
_L6:
        arraylist = new ArrayList();
        k = 0;
_L10:
        if (k >= bundle4.size())
        {
            break MISSING_BLOCK_LABEL_422;
        }
        timetableitemdata = (TimeTableItemData)bundle4.getSerializable(String.valueOf(k));
        if (timetableitemdata == null)
        {
            break MISSING_BLOCK_LABEL_601;
        }
        timedata = new TimeData();
        timedata.minute = timetableitemdata.getMinute();
        timedata.kindId = Integer.parseInt(timetableitemdata.getTraintype());
        timedata.extraLine = timetableitemdata.isExtraLine();
        timedata.firstStation = timetableitemdata.isFirstStation();
        s1 = (String)hashmap1.get(timetableitemdata.getDestmark());
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_379;
        }
        timedata.destId = Integer.parseInt(s1);
        arraylist.add(timedata);
        if (!hashmap.containsKey(Integer.valueOf(timedata.kindId)))
        {
            hashmap.put(timetableitemdata.getTrainmark(), timetableitemdata.getTraintype());
        }
        break MISSING_BLOCK_LABEL_601;
        departure.put(Integer.valueOf(j), arraylist);
          goto _L5
_L3:
        kindInfo = new ArrayList();
        l = 0;
_L8:
        if (l >= bundle2.size())
        {
            break MISSING_BLOCK_LABEL_587;
        }
        bundle5 = bundle2.getBundle(Integer.toString(l));
        typedata = new TypeData();
        s2 = bundle5.getString("mark");
        if (!s2.equals(s))
        {
            break MISSING_BLOCK_LABEL_577;
        }
        typedata.mark = "";
_L7:
        s3 = (String)hashmap.get(typedata.mark);
        if (!TextUtils.isEmpty(s3))
        {
            typedata.id = String.valueOf(s3);
        }
        typedata.info = bundle5.getString("info");
        kindInfo.add(typedata);
        l++;
        continue; /* Loop/switch isn't completed */
        typedata.mark = s2;
          goto _L7
        displayMode = 2;
        return;
        if (true) goto _L8; else goto _L5
_L5:
        j++;
          goto _L9
        k++;
          goto _L10
    }

    public void updateTimeTable(TimeTableData timetabledata)
    {
        isEntire = timetabledata.isEntire;
        name = timetabledata.name;
        dateKind = timetabledata.dateKind;
        railName = timetabledata.railName;
        source = timetabledata.source;
        direction = timetabledata.direction;
        updateDate = timetabledata.updateDate;
        traffic = timetabledata.traffic;
        departure = timetabledata.departure;
        kindInfo = timetabledata.kindInfo;
        destInfo = timetabledata.destInfo;
    }
}
