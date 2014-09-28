// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Parcel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import jp.co.yahoo.android.apps.transit.timer.api.data.StationData;
import jp.co.yahoo.android.apps.transit.timer.common.Alerm;

public class SettingDb extends SQLiteOpenHelper
{

    private static final int COUNTDOWN_DB_VER = 1;
    private final int COUNTDOWN_COUNT;
    private final String COUNTDOWN_NAME;
    private Context context;

    public SettingDb(Context context1)
    {
        super(context1, "history_timer.db", null, 1);
        COUNTDOWN_NAME = "countdown";
        COUNTDOWN_COUNT = 6;
        context = context1;
    }

    public SettingDb(Context context1, String s)
    {
        super(context1, "history_timer.db", null, 1);
        COUNTDOWN_NAME = "countdown";
        COUNTDOWN_COUNT = 6;
        context = context1;
    }

    public SettingDb(Context context1, String s, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory, int i)
    {
        super(context1, s, cursorfactory, i);
        COUNTDOWN_NAME = "countdown";
        COUNTDOWN_COUNT = 6;
        context = context1;
    }

    private void createNewTable(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.beginTransaction();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("create table countdown (");
        stringbuilder.append(" id integer primary key autoincrement not null,");
        stringbuilder.append(" name text not null,");
        stringbuilder.append(" station_id text,");
        stringbuilder.append(" area text,");
        stringbuilder.append(" type TINYINT,");
        stringbuilder.append(" lon text,");
        stringbuilder.append(" lat text,");
        stringbuilder.append(" railname text,");
        stringbuilder.append(" dirid text,");
        stringbuilder.append(" dirname text,");
        stringbuilder.append(" address text,");
        stringbuilder.append(" setting TINYINT,");
        stringbuilder.append(" updatedate text not null,");
        stringbuilder.append(" timetable blob null");
        stringbuilder.append(");");
        sqlitedatabase.execSQL(stringbuilder.toString());
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        return;
        SQLiteException sqliteexception;
        sqliteexception;
        sqliteexception.printStackTrace();
        sqlitedatabase.endTransaction();
        return;
        Exception exception;
        exception;
        sqlitedatabase.endTransaction();
        throw exception;
    }

    private void deleteTable(SQLiteDatabase sqlitedatabase, String s)
    {
        sqlitedatabase.beginTransaction();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append((new StringBuilder()).append("drop table ").append(s).append(";").toString());
        sqlitedatabase.execSQL(stringbuilder.toString());
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        return;
        SQLiteException sqliteexception;
        sqliteexception;
        sqliteexception.printStackTrace();
        sqlitedatabase.endTransaction();
        return;
        Exception exception;
        exception;
        sqlitedatabase.endTransaction();
        throw exception;
    }

    public int addSetting(StationData stationdata)
    {
        SQLiteDatabase sqlitedatabase;
        ContentValues contentvalues;
        int i;
        sqlitedatabase = getWritableDatabase();
        contentvalues = getContentValues(stationdata);
        i = -1;
        long l = sqlitedatabase.insert("countdown", null, contentvalues);
        i = (int)l;
        sqlitedatabase.close();
_L2:
        Resources resources = context.getResources();
        int j = stationdata.getSettingType();
        if (stationdata.isSetting() && (j == resources.getInteger(0x7f0c0074) || j == resources.getInteger(0x7f0c0073)))
        {
            Intent intent = new Intent("jp.co.yahoo.android.apps.transit.timer.ACTION_DATA_CHANGED");
            intent.putExtra(resources.getString(0x7f0d0247), j);
            context.sendBroadcast(intent);
        }
        return i;
        Exception exception1;
        exception1;
        sqlitedatabase.close();
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        sqlitedatabase.close();
        throw exception;
    }

    public int addSetting(StationData stationdata, int i)
    {
        if (count(i) == 0)
        {
            stationdata.setSetting(true);
        } else
        {
            stationdata.setSetting(false);
        }
        stationdata.setSettingType(i);
        return addSetting(stationdata);
    }

    public int addTempSetting(StationData stationdata)
    {
        if (stationdata.getSettingType() != context.getResources().getInteger(0x7f0c0075) && stationdata.getSettingType() != context.getResources().getInteger(0x7f0c0072))
        {
            return -1;
        }
        StationData stationdata1 = getTimetable(stationdata.getSettingType(), 0, -1);
        if (stationdata1 != null)
        {
            deleteData(stationdata.getSettingType());
            (new Alerm(context)).delAlarmByTimetableId(Integer.parseInt(stationdata1.getId()));
        }
        return addSetting(stationdata);
    }

    public int count(int i)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        Cursor cursor = sqlitedatabase.rawQuery((new StringBuilder()).append("SELECT count(*) FROM countdown Where type = ").append(Integer.toString(i)).append(";").toString(), null);
        if (cursor == null)
        {
            sqlitedatabase.close();
            return 0;
        } else
        {
            cursor.moveToFirst();
            int j = cursor.getInt(0);
            cursor.close();
            sqlitedatabase.close();
            return j;
        }
    }

    public void deleteData(int i)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        sqlitedatabase.delete("countdown", (new StringBuilder()).append("type = ").append(Integer.toString(i)).toString(), null);
        sqlitedatabase.close();
    }

    public void deleteData(StationData stationdata)
    {
        String s;
        if (stationdata != null)
        {
            if ((s = stationdata.getId()) != null && s != "")
            {
                SQLiteDatabase sqlitedatabase = getWritableDatabase();
                sqlitedatabase.delete("countdown", (new StringBuilder()).append("id = ").append(s).toString(), null);
                sqlitedatabase.close();
                Resources resources = context.getResources();
                int i = stationdata.getSettingType();
                if (stationdata.isSetting() && (i == resources.getInteger(0x7f0c0074) || i == resources.getInteger(0x7f0c0073)) && count(i) == 0)
                {
                    Intent intent = new Intent("jp.co.yahoo.android.apps.transit.timer.ACTION_DATA_CHANGED");
                    intent.putExtra(resources.getString(0x7f0d0247), i);
                    context.sendBroadcast(intent);
                    return;
                }
            }
        }
    }

    public ArrayList getAllStation(int i)
    {
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        sqlitedatabase = getReadableDatabase();
        int j = 6;
        String s;
        String as[];
        String as1[];
        Cursor cursor1;
        if (i > 0)
        {
            s = "type = ?";
            as = new String[1];
            as[0] = Integer.toString(i);
        } else
        {
            s = "type < ?";
            as = new String[1];
            as[0] = Integer.toString(3);
            j *= 2;
        }
        as1 = (new String[] {
            "station_id", "name", "railname", "dirid", "dirname", "lon", "lat", "address", "updatedate", "setting", 
            "type", "area", "id"
        });
        cursor1 = sqlitedatabase.query("countdown", as1, s, as, null, null, "station_id", Integer.toString(j));
        cursor = cursor1;
_L2:
        if (cursor == null)
        {
            sqlitedatabase.close();
            return null;
        }
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        exception.printStackTrace();
        cursor = null;
        if (true) goto _L2; else goto _L1
_L1:
        boolean flag = cursor.moveToFirst();
        ArrayList arraylist = new ArrayList();
        while (flag) 
        {
            StationData stationdata = new StationData();
            stationdata.setStationId(cursor.getString(0));
            stationdata.setName(cursor.getString(1));
            stationdata.setRailname(cursor.getString(2));
            stationdata.setDirid(cursor.getString(3));
            stationdata.setDirname(cursor.getString(4));
            stationdata.setLon(cursor.getString(5));
            stationdata.setLat(cursor.getString(6));
            stationdata.setAddress(cursor.getString(7));
            stationdata.setUpdateDate(cursor.getString(8));
            boolean flag1;
            if (cursor.getInt(9) == 1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            stationdata.setSetting(flag1);
            stationdata.setSettingType(cursor.getInt(10));
            stationdata.setGovernmentCode(cursor.getString(11));
            stationdata.setId(cursor.getString(12));
            flag = cursor.moveToNext();
            arraylist.add(stationdata);
        }
        cursor.close();
        sqlitedatabase.close();
        return arraylist;
    }

    public ArrayList getAllTimetable(int i)
    {
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        sqlitedatabase = getReadableDatabase();
        int j = 6;
        String s;
        String as[];
        String as1[];
        Cursor cursor1;
        if (i > 0)
        {
            s = "type = ?";
            as = new String[1];
            as[0] = Integer.toString(i);
        } else
        {
            s = "type != ?";
            as = new String[1];
            as[0] = Integer.toString(3);
            j *= 2;
        }
        as1 = (new String[] {
            "station_id", "name", "area", "railname", "dirid", "dirname", "lon", "lat", "address", "updatedate", 
            "setting", "timetable", "id", "type"
        });
        cursor1 = sqlitedatabase.query("countdown", as1, s, as, null, null, "station_id", Integer.toString(j));
        cursor = cursor1;
_L2:
        if (cursor == null)
        {
            sqlitedatabase.close();
            return null;
        }
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        exception.printStackTrace();
        cursor = null;
        if (true) goto _L2; else goto _L1
_L1:
        boolean flag = cursor.moveToFirst();
        ArrayList arraylist = new ArrayList();
        while (flag) 
        {
            new StationData();
            StationData stationdata = new StationData();
            stationdata.setStationId(cursor.getString(0));
            stationdata.setName(cursor.getString(1));
            stationdata.setGovernmentCode(cursor.getString(2));
            stationdata.setRailname(cursor.getString(3));
            stationdata.setDirid(cursor.getString(4));
            stationdata.setDirname(cursor.getString(5));
            stationdata.setLon(cursor.getString(6));
            stationdata.setLat(cursor.getString(7));
            stationdata.setAddress(cursor.getString(8));
            stationdata.setUpdateDate(cursor.getString(9));
            boolean flag1;
            if (cursor.getInt(10) == 1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            stationdata.setSetting(flag1);
            stationdata.setTimetable(getBundle(cursor.getBlob(11)));
            stationdata.setId(cursor.getString(12));
            stationdata.setSettingType(cursor.getInt(13));
            flag = cursor.moveToNext();
            arraylist.add(stationdata);
        }
        cursor.close();
        sqlitedatabase.close();
        return arraylist;
    }

    public Bundle getBundle(byte abyte0[])
    {
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(abyte0, 0, abyte0.length);
        parcel.setDataPosition(0);
        Bundle bundle = parcel.readBundle();
        parcel.recycle();
        return bundle;
    }

    public byte[] getBytes(Bundle bundle)
    {
        Parcel parcel = Parcel.obtain();
        bundle.writeToParcel(parcel, 0);
        byte abyte0[] = parcel.marshall();
        parcel.recycle();
        return abyte0;
    }

    protected ContentValues getContentValues(StationData stationdata)
    {
        boolean flag = stationdata.isSetting();
        int i = 0;
        if (flag)
        {
            i = 1;
        }
        Bundle bundle = stationdata.getTimetable();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("name", stationdata.getName());
        contentvalues.put("station_id", stationdata.getStationId());
        contentvalues.put("area", stationdata.getGovernmentCode());
        contentvalues.put("railname", stationdata.getRailname());
        contentvalues.put("dirid", stationdata.getDirid());
        contentvalues.put("dirname", stationdata.getDirname());
        contentvalues.put("lon", stationdata.getLon());
        contentvalues.put("lat", stationdata.getLat());
        contentvalues.put("address", stationdata.getAddress());
        contentvalues.put("type", Integer.valueOf(stationdata.getSettingType()));
        contentvalues.put("setting", Integer.valueOf(i));
        contentvalues.put("updatedate", getDate());
        contentvalues.put("timetable", getBytes(bundle));
        return contentvalues;
    }

    protected String getDate()
    {
        Date date = new Date();
        return (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(date);
    }

    public StationData getStation()
    {
        return getStation(context.getResources().getInteger(0x7f0c0074));
    }

    public StationData getStation(int i)
    {
        SQLiteDatabase sqlitedatabase;
        String as[];
        String as1[];
        sqlitedatabase = getReadableDatabase();
        as = new String[1];
        as[0] = Integer.toString(i);
        as1 = (new String[] {
            "station_id", "name", "railname", "dirid", "dirname", "lon", "lat", "address", "updatedate", "setting", 
            "type", "area", "id"
        });
        Cursor cursor1 = sqlitedatabase.query("countdown", as1, "type = ?", as, null, null, "station_id", "1");
        Cursor cursor = cursor1;
_L2:
        if (cursor == null)
        {
            sqlitedatabase.close();
            return null;
        }
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        exception.printStackTrace();
        cursor = null;
        if (true) goto _L2; else goto _L1
_L1:
        boolean flag = cursor.moveToFirst();
        StationData stationdata = null;
        while (flag) 
        {
            stationdata = new StationData();
            stationdata.setStationId(cursor.getString(0));
            stationdata.setName(cursor.getString(1));
            stationdata.setRailname(cursor.getString(2));
            stationdata.setDirid(cursor.getString(3));
            stationdata.setDirname(cursor.getString(4));
            stationdata.setLon(cursor.getString(5));
            stationdata.setLat(cursor.getString(6));
            stationdata.setAddress(cursor.getString(7));
            stationdata.setUpdateDate(cursor.getString(8));
            boolean flag1;
            if (cursor.getInt(9) == 1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            stationdata.setSetting(flag1);
            stationdata.setSettingType(cursor.getInt(10));
            stationdata.setGovernmentCode(cursor.getString(11));
            stationdata.setId(cursor.getString(12));
            flag = cursor.moveToNext();
        }
        cursor.close();
        sqlitedatabase.close();
        return stationdata;
    }

    public StationData getTimetable(int i)
    {
        StationData stationdata = getTimetable(i, 1);
        if (stationdata == null || stationdata.getStationId() == null)
        {
            stationdata = getTimetable(i, 0);
            updateSetting(stationdata, i);
        }
        return stationdata;
    }

    public StationData getTimetable(int i, int j)
    {
        return getTimetable(i, j, -1);
    }

    public StationData getTimetable(int i, int j, int k)
    {
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        sqlitedatabase = getReadableDatabase();
        String s;
        String as[];
        String as1[];
        Cursor cursor1;
        if (k < 0)
        {
            s = "setting = ? and type = ?";
            as = new String[2];
            as[0] = Integer.toString(j);
            as[1] = Integer.toString(i);
        } else
        {
            s = "id = ?";
            as = new String[1];
            as[0] = Integer.toString(k);
        }
        as1 = (new String[] {
            "station_id", "name", "area", "railname", "dirid", "dirname", "lon", "lat", "address", "updatedate", 
            "setting", "timetable", "id", "type"
        });
        cursor1 = sqlitedatabase.query("countdown", as1, s, as, null, null, "station_id", "1");
        cursor = cursor1;
_L2:
        if (cursor == null)
        {
            sqlitedatabase.close();
            return null;
        }
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        exception.printStackTrace();
        cursor = null;
        if (true) goto _L2; else goto _L1
_L1:
        boolean flag = cursor.moveToFirst();
        StationData stationdata = null;
        while (flag) 
        {
            stationdata = new StationData();
            stationdata.setStationId(cursor.getString(0));
            stationdata.setName(cursor.getString(1));
            stationdata.setGovernmentCode(cursor.getString(2));
            stationdata.setRailname(cursor.getString(3));
            stationdata.setDirid(cursor.getString(4));
            stationdata.setDirname(cursor.getString(5));
            stationdata.setLon(cursor.getString(6));
            stationdata.setLat(cursor.getString(7));
            stationdata.setAddress(cursor.getString(8));
            stationdata.setUpdateDate(cursor.getString(9));
            boolean flag1;
            if (cursor.getInt(10) == 1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            stationdata.setSetting(flag1);
            stationdata.setTimetable(getBundle(cursor.getBlob(11)));
            stationdata.setId(cursor.getString(12));
            stationdata.setSettingType(cursor.getInt(13));
            flag = cursor.moveToNext();
        }
        cursor.close();
        sqlitedatabase.close();
        return stationdata;
    }

    public StationData getTimetableById(int i)
    {
        return getTimetable(0, 0, i);
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        createNewTable(sqlitedatabase);
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        createNewTable(sqlitedatabase);
    }

    public void updateSetting(StationData stationdata, int i)
    {
        String s;
        if (stationdata != null)
        {
            if ((s = stationdata.getId()) != null && s != "")
            {
                SQLiteDatabase sqlitedatabase = getWritableDatabase();
                ContentValues contentvalues = new ContentValues();
                contentvalues.put("setting", "0");
                sqlitedatabase.update("countdown", contentvalues, (new StringBuilder()).append("setting = 1 and type = ").append(Integer.toString(i)).toString(), null);
                sqlitedatabase.close();
                SQLiteDatabase sqlitedatabase1 = getWritableDatabase();
                ContentValues contentvalues1 = new ContentValues();
                contentvalues1.put("setting", "1");
                sqlitedatabase1.update("countdown", contentvalues1, (new StringBuilder()).append("id = ").append(s).toString(), null);
                sqlitedatabase1.close();
                Resources resources = context.getResources();
                if (stationdata.isSetting() && (i == resources.getInteger(0x7f0c0074) || i == resources.getInteger(0x7f0c0073)))
                {
                    Intent intent = new Intent("jp.co.yahoo.android.apps.transit.timer.ACTION_DATA_CHANGED");
                    intent.putExtra(resources.getString(0x7f0d0247), i);
                    context.sendBroadcast(intent);
                    return;
                }
            }
        }
    }

    public void updateStation(StationData stationdata, StationData stationdata1, int i)
    {
        if (stationdata != null && stationdata1 != null) goto _L2; else goto _L1
_L1:
        String s;
        return;
_L2:
        if ((s = stationdata.getId()) == null || s == "") goto _L1; else goto _L3
_L3:
        Exception exception;
        stationdata1.setSetting(stationdata.isSetting());
        stationdata1.setSettingType(i);
        stationdata1.setId(s);
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        ContentValues contentvalues = getContentValues(stationdata1);
        try
        {
            sqlitedatabase.update("countdown", contentvalues, (new StringBuilder()).append("id = ").append(s).toString(), null);
        }
        catch (Exception exception1)
        {
            sqlitedatabase.close();
            return;
        }
        finally
        {
            sqlitedatabase.close();
        }
        sqlitedatabase.close();
        return;
        throw exception;
    }

    public void updateTimetable(StationData stationdata, Bundle bundle)
    {
        String s;
        if (stationdata != null)
        {
            if ((s = stationdata.getId()) != null && s != "")
            {
                SQLiteDatabase sqlitedatabase = getWritableDatabase();
                ContentValues contentvalues = new ContentValues();
                contentvalues.put("updatedate", getDate());
                contentvalues.put("timetable", getBytes(bundle));
                sqlitedatabase.update("countdown", contentvalues, (new StringBuilder()).append("id = ").append(s).toString(), null);
                sqlitedatabase.close();
                Resources resources = context.getResources();
                int i = stationdata.getSettingType();
                if (stationdata.isSetting() && (i == resources.getInteger(0x7f0c0074) || i == resources.getInteger(0x7f0c0073)))
                {
                    Intent intent = new Intent("jp.co.yahoo.android.apps.transit.timer.ACTION_DATA_CHANGED");
                    intent.putExtra(resources.getString(0x7f0d0247), i);
                    context.sendBroadcast(intent);
                    return;
                }
            }
        }
    }
}
