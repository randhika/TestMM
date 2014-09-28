// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jp.co.yahoo.android.apps.transit.api.data.StationData;

public class History extends SQLiteOpenHelper
{

    private static final int HISTORY_DB_VER = 2;
    private final String HISTORY_GET_COUNT;
    private final String HISTORY_TABLE_NAME;

    public History(Context context)
    {
        super(context, "history.db", null, 2);
        HISTORY_TABLE_NAME = "history";
        HISTORY_GET_COUNT = "10";
    }

    public History(Context context, String s)
    {
        super(context, "history.db", null, 2);
        HISTORY_TABLE_NAME = "history";
        HISTORY_GET_COUNT = "10";
    }

    public History(Context context, String s, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory, int i)
    {
        super(context, s, cursorfactory, i);
        HISTORY_TABLE_NAME = "history";
        HISTORY_GET_COUNT = "10";
    }

    private void createNewTable(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.beginTransaction();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("create table history (");
        stringbuilder.append(" id integer primary key autoincrement not null,");
        stringbuilder.append(" keyword text not null,");
        stringbuilder.append(" input_count integer not null default 1,");
        stringbuilder.append(" station_id text,");
        stringbuilder.append(" type integer not null default 1,");
        stringbuilder.append(" navi_type integer not null default 1,");
        stringbuilder.append(" lon text,");
        stringbuilder.append(" lat text,");
        stringbuilder.append(" address text,");
        stringbuilder.append(" updatedate text not null");
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

    public void addHistory(StationData stationdata)
    {
        SQLiteDatabase sqlitedatabase;
        String as[];
        String as1[];
        sqlitedatabase = getReadableDatabase();
        as = (new String[] {
            "id", "input_count"
        });
        as1 = new String[2];
        as1[0] = stationdata.getId();
        as1[1] = stationdata.getName();
        Cursor cursor;
        cursor = sqlitedatabase.query("history", as, "station_id = ? AND keyword = ? ", as1, null, null, null);
        if (cursor.getCount() == 0)
        {
            sqlitedatabase.close();
            cursor.close();
            SQLiteDatabase sqlitedatabase2 = getWritableDatabase();
            ContentValues contentvalues1 = new ContentValues();
            contentvalues1.put("keyword", stationdata.getName());
            contentvalues1.put("station_id", stationdata.getId());
            contentvalues1.put("lon", stationdata.getLon());
            contentvalues1.put("lat", stationdata.getLat());
            contentvalues1.put("address", stationdata.getAddress());
            contentvalues1.put("updatedate", getDate());
            contentvalues1.put("type", Integer.valueOf(stationdata.getType()));
            contentvalues1.put("navi_type", Integer.valueOf(stationdata.getnNaviType()));
            sqlitedatabase2.insert("history", null, contentvalues1);
            sqlitedatabase2.close();
            return;
        }
        try
        {
            if (cursor.moveToFirst())
            {
                String s = cursor.getString(0);
                int i = 1 + cursor.getInt(1);
                SQLiteDatabase sqlitedatabase1 = getWritableDatabase();
                cursor.moveToFirst();
                ContentValues contentvalues = new ContentValues();
                contentvalues.put("input_count", Integer.valueOf(i));
                contentvalues.put("updatedate", getDate());
                sqlitedatabase1.update("history", contentvalues, "id = ?", new String[] {
                    s
                });
                sqlitedatabase1.close();
                return;
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return;
    }

    public void deleteAll()
    {
        deleteHistory();
    }

    public void deleteHistory()
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        sqlitedatabase.delete("history", null, null);
        sqlitedatabase.close();
    }

    public void deleteHistory(String s)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        sqlitedatabase.delete("history", "keyword = ?", new String[] {
            s
        });
        sqlitedatabase.close();
    }

    protected String getDate()
    {
        Date date = new Date();
        return (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(date);
    }

    public List getHistory()
    {
        return getHistory(0);
    }

    public List getHistory(int i)
    {
        if (i != 1 && i != 2 && i != 4 && i != 3) goto _L2; else goto _L1
_L1:
        String s;
        String as[];
        s = "type = ?";
        as = new String[1];
        as[0] = String.valueOf(i);
_L4:
        ArrayList arraylist;
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        arraylist = new ArrayList();
        sqlitedatabase = getReadableDatabase();
        String as1[] = {
            "station_id", "keyword", "navi_type", "input_count", "lon", "lat", "address", "updatedate"
        };
        try
        {
            cursor = sqlitedatabase.query("history", as1, s, as, null, null, "updatedate desc", "10");
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        if (cursor == null)
        {
            return null;
        }
        break; /* Loop/switch isn't completed */
_L2:
        s = null;
        as = null;
        if (i == 5)
        {
            s = "type == 2 or type == 1";
            as = new String[0];
        }
        if (true) goto _L4; else goto _L3
_L3:
        for (boolean flag = cursor.moveToFirst(); flag; flag = cursor.moveToNext())
        {
            StationData stationdata = new StationData();
            stationdata.setId(cursor.getString(0));
            stationdata.setName(cursor.getString(1));
            stationdata.setnNaviType(cursor.getInt(2));
            stationdata.setLon(cursor.getString(4));
            stationdata.setLat(cursor.getString(5));
            stationdata.setAddress(cursor.getString(6));
            arraylist.add(stationdata);
        }

        cursor.close();
        sqlitedatabase.close();
        return arraylist;
    }

    public boolean isHistory()
    {
        return getHistory().size() > 0;
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        createNewTable(sqlitedatabase);
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        deleteTable(sqlitedatabase, "startend");
        deleteTable(sqlitedatabase, "via");
        createNewTable(sqlitedatabase);
    }
}
