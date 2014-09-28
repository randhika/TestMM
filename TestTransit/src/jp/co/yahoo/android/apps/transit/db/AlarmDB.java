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
import android.os.Bundle;
import android.os.Parcel;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

public class AlarmDB extends SQLiteOpenHelper
{

    private static final int ALARM_DB_VER = 1;
    private final String ALARM_DATA_MAX;
    private final String ALARM_DATA_TABLE_NAME;
    private final String ALARM_SETTING_TABLE_NAME;
    private final String COLUMN_ALARM_ID;
    private final String COLUMN_CONDITIONS;
    private final String COLUMN_ID;
    private final String COLUMN_LENGTH;
    private final String COLUMN_MINUTES;
    private final String COLUMN_POSITION;
    private final String COLUMN_RESULTS;
    private final String COLUMN_SAVEDATE;
    private final String COLUMN_SOUND;
    private final String COLUMN_TYPE;
    private Context context;

    public AlarmDB(Context context1)
    {
        super(context1, "alarm.db", null, 1);
        ALARM_DATA_TABLE_NAME = "alarm_data";
        ALARM_SETTING_TABLE_NAME = "alarm_setting";
        ALARM_DATA_MAX = String.valueOf(5);
        COLUMN_ID = "id";
        COLUMN_CONDITIONS = "conditions";
        COLUMN_RESULTS = "results";
        COLUMN_SAVEDATE = "savedate";
        COLUMN_ALARM_ID = "alarm_id";
        COLUMN_MINUTES = "minutes";
        COLUMN_LENGTH = "length";
        COLUMN_SOUND = "sound";
        COLUMN_TYPE = "type";
        COLUMN_POSITION = "position";
        context = context1;
    }

    public AlarmDB(Context context1, String s)
    {
        super(context1, "alarm.db", null, 1);
        ALARM_DATA_TABLE_NAME = "alarm_data";
        ALARM_SETTING_TABLE_NAME = "alarm_setting";
        ALARM_DATA_MAX = String.valueOf(5);
        COLUMN_ID = "id";
        COLUMN_CONDITIONS = "conditions";
        COLUMN_RESULTS = "results";
        COLUMN_SAVEDATE = "savedate";
        COLUMN_ALARM_ID = "alarm_id";
        COLUMN_MINUTES = "minutes";
        COLUMN_LENGTH = "length";
        COLUMN_SOUND = "sound";
        COLUMN_TYPE = "type";
        COLUMN_POSITION = "position";
        context = context1;
    }

    public AlarmDB(Context context1, String s, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory, int i)
    {
        super(context1, s, cursorfactory, i);
        ALARM_DATA_TABLE_NAME = "alarm_data";
        ALARM_SETTING_TABLE_NAME = "alarm_setting";
        ALARM_DATA_MAX = String.valueOf(5);
        COLUMN_ID = "id";
        COLUMN_CONDITIONS = "conditions";
        COLUMN_RESULTS = "results";
        COLUMN_SAVEDATE = "savedate";
        COLUMN_ALARM_ID = "alarm_id";
        COLUMN_MINUTES = "minutes";
        COLUMN_LENGTH = "length";
        COLUMN_SOUND = "sound";
        COLUMN_TYPE = "type";
        COLUMN_POSITION = "position";
        context = context1;
    }

    private int count(String s)
    {
        SQLiteDatabase sqlitedatabase = getReadableDatabase();
        Cursor cursor = sqlitedatabase.rawQuery((new StringBuilder()).append("SELECT count(*) FROM ").append(s).append(";").toString(), null);
        cursor.moveToFirst();
        int i = cursor.getInt(0);
        cursor.close();
        sqlitedatabase.close();
        return i;
    }

    private int count(String s, int i, String s1)
    {
        SQLiteDatabase sqlitedatabase = getReadableDatabase();
        Cursor cursor = sqlitedatabase.rawQuery((new StringBuilder()).append("SELECT count(*) FROM ").append(s).append(" WHERE ").append(s1).append(" = ").append(String.valueOf(i)).append(";").toString(), null);
        cursor.moveToFirst();
        int j = cursor.getInt(0);
        sqlitedatabase.close();
        return j;
    }

    private void createAlarmDataTable(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.beginTransaction();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("create table alarm_data ( ");
        stringbuilder.append("id");
        stringbuilder.append(" integer primary key autoincrement not null, ");
        stringbuilder.append("conditions");
        stringbuilder.append(" blob not null, ");
        stringbuilder.append("results");
        stringbuilder.append(" blob not null, ");
        stringbuilder.append("savedate");
        stringbuilder.append(" text not null");
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

    private void createAlarmSettingTable(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.beginTransaction();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("create table alarm_setting ( ");
        stringbuilder.append("id");
        stringbuilder.append(" integer primary key autoincrement not null, ");
        stringbuilder.append("alarm_id");
        stringbuilder.append(" integer not null, ");
        stringbuilder.append("minutes");
        stringbuilder.append(" integer not null, ");
        stringbuilder.append("length");
        stringbuilder.append(" integer not null, ");
        stringbuilder.append("sound");
        stringbuilder.append(" integer not null, ");
        stringbuilder.append("type");
        stringbuilder.append(" integer not null, ");
        stringbuilder.append("position");
        stringbuilder.append(" integer not null");
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

    private void createNewTable(SQLiteDatabase sqlitedatabase)
    {
        createAlarmDataTable(sqlitedatabase);
        createAlarmSettingTable(sqlitedatabase);
    }

    private void delete(String s, int i)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        String as[] = new String[1];
        as[0] = String.valueOf(i);
        sqlitedatabase.delete(s, "id = ?", as);
        sqlitedatabase.close();
    }

    public int addAlarmData(ConditionData conditiondata, NaviSearchData navisearchdata, ArrayList arraylist)
    {
        int i = -1;
        try
        {
            SQLiteDatabase sqlitedatabase = getWritableDatabase();
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("conditions", TransitUtil.obj2byte(conditiondata));
            contentvalues.put("results", TransitUtil.obj2byte(navisearchdata));
            contentvalues.put("savedate", getDate());
            i = (int)sqlitedatabase.insert("alarm_data", null, contentvalues);
            sqlitedatabase.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return i;
        }
        if (i == -1)
        {
            break MISSING_BLOCK_LABEL_82;
        }
        addAlarmSetting(i, arraylist);
        return i;
    }

    public void addAlarmSetting(int i, ArrayList arraylist)
    {
        SQLiteDatabase sqlitedatabase;
        try
        {
            sqlitedatabase = getWritableDatabase();
            ContentValues contentvalues;
            for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); sqlitedatabase.insert("alarm_setting", null, contentvalues))
            {
                Bundle bundle = (Bundle)iterator.next();
                contentvalues = new ContentValues();
                contentvalues.put("alarm_id", Integer.valueOf(i));
                contentvalues.put("minutes", Integer.valueOf(bundle.getInt(context.getString(0x7f0d01cc))));
                contentvalues.put("length", Integer.valueOf(bundle.getInt(context.getString(0x7f0d01c8))));
                contentvalues.put("sound", Integer.valueOf(bundle.getInt(context.getString(0x7f0d0238))));
                contentvalues.put("type", Integer.valueOf(bundle.getInt(context.getString(0x7f0d0247))));
                contentvalues.put("position", Integer.valueOf(bundle.getInt(context.getString(0x7f0d01d4))));
            }

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return;
        }
        sqlitedatabase.close();
        return;
    }

    public int countAlarmData()
    {
        return count("alarm_data");
    }

    public int countAlarmSetting()
    {
        return count("alarm_setting");
    }

    public int countAlarmSettingByAlarmId(int i)
    {
        return count("alarm_setting", i, "alarm_id");
    }

    public void deleteAlarmData(int i)
    {
        delete("alarm_data", i);
    }

    public void deleteAlarmDataAll()
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        sqlitedatabase.delete("alarm_data", null, null);
        sqlitedatabase.close();
    }

    public void deleteAlarmSetting(int i)
    {
        delete("alarm_setting", i);
    }

    public void deleteAlarmSettingAll()
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        sqlitedatabase.delete("alarm_setting", null, null);
        sqlitedatabase.close();
    }

    public Bundle getAlarmData(int i)
    {
        return getAlarmData(i, false);
    }

    public Bundle getAlarmData(int i, boolean flag)
    {
        Bundle bundle;
        SQLiteDatabase sqlitedatabase;
        String as[];
        String as1[];
        bundle = new Bundle();
        sqlitedatabase = getReadableDatabase();
        as = (new String[] {
            "id", "conditions", "results"
        });
        as1 = new String[1];
        as1[0] = String.valueOf(i);
        Cursor cursor1 = sqlitedatabase.query("alarm_data", as, "id = ?", as1, null, null, "", "1");
        Cursor cursor = cursor1;
_L2:
        if (cursor == null)
        {
            sqlitedatabase.close();
            return bundle;
        }
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        exception.printStackTrace();
        cursor = null;
        if (true) goto _L2; else goto _L1
_L1:
        if (cursor.moveToFirst())
        {
            bundle.putInt(context.getString(0x7f0d01c5), i);
            Object obj = TransitUtil.byte2object(cursor.getBlob(1));
            if (obj == null)
            {
                Bundle bundle2 = getBundle(cursor.getBlob(2));
                obj = TransitUtil.bundle2ConditionData(context, bundle2);
            }
            Object obj1 = TransitUtil.byte2object(cursor.getBlob(2));
            if (obj1 == null)
            {
                Bundle bundle1 = getBundle(cursor.getBlob(2));
                obj1 = TransitUtil.bundle2NaviSearchData(context, bundle1);
            }
            bundle.putSerializable(context.getString(0x7f0d022c), (Serializable)obj);
            bundle.putSerializable(context.getString(0x7f0d0232), (Serializable)obj1);
            if (flag)
            {
                ArrayList arraylist = (ArrayList)getAlarmSetting(i);
                bundle.putParcelableArrayList(context.getString(0x7f0d0234), arraylist);
            }
        }
        cursor.close();
        sqlitedatabase.close();
        return bundle;
    }

    public Bundle getAlarmDataSetting(int i)
    {
        return getAlarmData(i, true);
    }

    public List getAlarmList()
    {
        ArrayList arraylist;
        SQLiteDatabase sqlitedatabase;
        String as[];
        arraylist = new ArrayList();
        sqlitedatabase = getReadableDatabase();
        as = (new String[] {
            "id", "conditions", "results", "savedate"
        });
        Cursor cursor1 = sqlitedatabase.query("alarm_data", as, null, null, null, null, "savedate desc", ALARM_DATA_MAX);
        Cursor cursor = cursor1;
_L2:
        if (cursor == null)
        {
            sqlitedatabase.close();
            return arraylist;
        }
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        exception.printStackTrace();
        cursor = null;
        if (true) goto _L2; else goto _L1
_L1:
        for (boolean flag = cursor.moveToFirst(); flag; flag = cursor.moveToNext())
        {
            int i = cursor.getInt(0);
            ArrayList arraylist1 = (ArrayList)getAlarmSetting(i);
            Bundle bundle = new Bundle();
            bundle.putInt(context.getString(0x7f0d01c5), i);
            Object obj = TransitUtil.byte2object(cursor.getBlob(1));
            if (obj == null)
            {
                Bundle bundle2 = getBundle(cursor.getBlob(1));
                obj = TransitUtil.bundle2ConditionData(context, bundle2);
            }
            bundle.putSerializable(context.getString(0x7f0d022c), (Serializable)obj);
            Object obj1 = TransitUtil.byte2object(cursor.getBlob(2));
            if (obj1 == null)
            {
                Bundle bundle1 = getBundle(cursor.getBlob(2));
                obj1 = TransitUtil.bundle2NaviSearchData(context, bundle1);
            }
            bundle.putSerializable(context.getString(0x7f0d0232), (Serializable)obj1);
            bundle.putString(context.getString(0x7f0d022b), cursor.getString(3));
            bundle.putParcelableArrayList(context.getString(0x7f0d0234), arraylist1);
            arraylist.add(bundle);
        }

        cursor.close();
        sqlitedatabase.close();
        return arraylist;
    }

    public List getAlarmSetting(int i)
    {
        ArrayList arraylist;
        SQLiteDatabase sqlitedatabase;
        String as[];
        String as1[];
        arraylist = new ArrayList();
        sqlitedatabase = getReadableDatabase();
        as = (new String[] {
            "id", "alarm_id", "minutes", "length", "sound", "type", "position"
        });
        as1 = new String[1];
        as1[0] = String.valueOf(i);
        Cursor cursor1 = sqlitedatabase.query("alarm_setting", as, "alarm_id = ?", as1, null, null, "id asc");
        Cursor cursor = cursor1;
_L2:
        if (cursor == null)
        {
            sqlitedatabase.close();
            return arraylist;
        }
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        exception.printStackTrace();
        cursor = null;
        if (true) goto _L2; else goto _L1
_L1:
        for (boolean flag = cursor.moveToFirst(); flag; flag = cursor.moveToNext())
        {
            Bundle bundle = new Bundle();
            bundle.putInt(context.getString(0x7f0d01c5), cursor.getInt(0));
            bundle.putInt(context.getString(0x7f0d017e), cursor.getInt(1));
            bundle.putInt(context.getString(0x7f0d01cc), cursor.getInt(2));
            bundle.putInt(context.getString(0x7f0d01c8), cursor.getInt(3));
            bundle.putInt(context.getString(0x7f0d0238), cursor.getInt(4));
            bundle.putInt(context.getString(0x7f0d0247), cursor.getInt(5));
            bundle.putInt(context.getString(0x7f0d01d4), cursor.getInt(6));
            arraylist.add(bundle);
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

    protected String getDate()
    {
        Date date = new Date();
        return (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(date);
    }

    public int getOldestAlarmId()
    {
        int i;
        SQLiteDatabase sqlitedatabase;
        String as[];
        i = -1;
        sqlitedatabase = getReadableDatabase();
        as = (new String[] {
            "id"
        });
        Cursor cursor1 = sqlitedatabase.query("alarm_data", as, null, null, null, null, "savedate asc", "1");
        Cursor cursor = cursor1;
_L2:
        if (cursor == null)
        {
            sqlitedatabase.close();
            return i;
        }
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        exception.printStackTrace();
        cursor = null;
        if (true) goto _L2; else goto _L1
_L1:
        if (cursor.moveToFirst())
        {
            i = cursor.getInt(0);
        }
        cursor.close();
        sqlitedatabase.close();
        return i;
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        createNewTable(sqlitedatabase);
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
    }
}
