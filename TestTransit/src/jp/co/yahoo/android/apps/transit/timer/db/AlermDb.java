// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;

public class AlermDb extends SQLiteOpenHelper
{

    private static final int ALERM_DB_VER = 1;
    public static final int MAX_ALL = 6;
    public static final int MAX_COUNT = 1;
    public static final int MAX_START = 4;
    public static final int MAX_UPDATE = 1;
    private final String TABLE_ALERN;
    private String columns[] = {
        "id", "line", "timetable_id", "starttime", "countdowntime", "repeat", "setting", "type", "vibration", "sound", 
        "sound_uri", "alerm_length", "alerm_volume"
    };
    private Context context;

    public AlermDb(Context context1)
    {
        super(context1, "alerm.db", null, 1);
        TABLE_ALERN = "alerm";
    }

    public AlermDb(Context context1, String s)
    {
        super(context1, "alerm.db", null, 1);
        TABLE_ALERN = "alerm";
    }

    public AlermDb(Context context1, String s, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory, int i)
    {
        super(context1, s, cursorfactory, i);
        TABLE_ALERN = "alerm";
        context = context1;
    }

    private void createNewAlermTable(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.beginTransaction();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("create table alerm (");
        stringbuilder.append(" id integer primary key autoincrement not null,");
        stringbuilder.append(" line text,");
        stringbuilder.append(" timetable_id int,");
        stringbuilder.append(" updatedate text not null,");
        stringbuilder.append(" starttime int,");
        stringbuilder.append(" countdowntime int,");
        stringbuilder.append(" repeat text,");
        stringbuilder.append(" setting TINYINT,");
        stringbuilder.append(" type TINYINT,");
        stringbuilder.append(" vibration TINYINT,");
        stringbuilder.append(" sound text,");
        stringbuilder.append(" sound_uri text,");
        stringbuilder.append(" alerm_length int,");
        stringbuilder.append(" alerm_volume int");
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

    private ArrayList makeStartData(Cursor cursor)
    {
        ArrayList arraylist = new ArrayList();
        boolean flag = cursor.moveToFirst();
        while (flag) 
        {
            AlermData alermdata = new AlermData();
            alermdata.setId(cursor.getInt(0));
            alermdata.setLine(cursor.getString(1));
            alermdata.setTimetableId(cursor.getInt(2));
            alermdata.setStartTime(cursor.getInt(3));
            alermdata.setCountdownTime(cursor.getInt(4));
            alermdata.setRepeat(cursor.getString(5));
            boolean flag1;
            boolean flag2;
            if (cursor.getInt(6) == 1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            alermdata.setSetting(flag1);
            alermdata.setType(cursor.getInt(7));
            if (cursor.getInt(8) == 1)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            alermdata.setVibration(flag2);
            alermdata.setSound(cursor.getString(9));
            alermdata.setSoundUri(cursor.getString(10));
            alermdata.setAlermLength(cursor.getInt(11));
            alermdata.setAlermVolume(cursor.getInt(12));
            flag = cursor.moveToNext();
            arraylist.add(alermdata);
        }
        return arraylist;
    }

    public int addStart(AlermData alermdata)
    {
        Exception exception;
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("line", alermdata.getLine());
        contentvalues.put("timetable_id", Integer.valueOf(alermdata.getTimetableId()));
        contentvalues.put("updatedate", getDate());
        contentvalues.put("starttime", Integer.valueOf(alermdata.getStartTime()));
        contentvalues.put("countdowntime", Integer.valueOf(alermdata.getCountdownTime()));
        contentvalues.put("repeat", alermdata.getRepeat());
        contentvalues.put("type", Integer.valueOf(alermdata.getType()));
        boolean flag = alermdata.isVibration();
        int i = 0;
        if (flag)
        {
            i = 1;
        }
        contentvalues.put("vibration", Integer.valueOf(i));
        contentvalues.put("sound", alermdata.getSound());
        contentvalues.put("sound_uri", alermdata.getSoundUri());
        contentvalues.put("alerm_length", Integer.valueOf(alermdata.getAlermLength()));
        contentvalues.put("alerm_volume", Integer.valueOf(alermdata.getAlermVolume()));
        boolean flag1 = alermdata.isSetting();
        int j = 0;
        if (flag1)
        {
            j = 1;
        }
        contentvalues.put("setting", Integer.valueOf(j));
        long l;
        int k;
        try
        {
            l = sqlitedatabase.insert("alerm", null, contentvalues);
        }
        catch (Exception exception1)
        {
            sqlitedatabase.close();
            return -1;
        }
        finally
        {
            sqlitedatabase.close();
        }
        k = (int)l;
        sqlitedatabase.close();
        return k;
        throw exception;
    }

    public int countCountAlerm()
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        Cursor cursor = sqlitedatabase.rawQuery("SELECT count(*) FROM alerm WHERE type=2;", null);
        if (cursor == null)
        {
            sqlitedatabase.close();
            return 0;
        } else
        {
            cursor.moveToFirst();
            int i = cursor.getInt(0);
            cursor.close();
            sqlitedatabase.close();
            return i;
        }
    }

    public int countStartAlerm()
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        Cursor cursor = sqlitedatabase.rawQuery("SELECT count(*) FROM alerm WHERE type=1;", null);
        if (cursor == null)
        {
            sqlitedatabase.close();
            return 0;
        } else
        {
            cursor.moveToFirst();
            int i = cursor.getInt(0);
            cursor.close();
            sqlitedatabase.close();
            return i;
        }
    }

    public void deleteAlermByTimetableId(int i)
    {
        if (i < 0)
        {
            return;
        } else
        {
            SQLiteDatabase sqlitedatabase = getWritableDatabase();
            sqlitedatabase.delete("alerm", (new StringBuilder()).append("timetable_id = ").append(Integer.toString(i)).toString(), null);
            sqlitedatabase.close();
            return;
        }
    }

    public void deleteById(int i)
    {
        if (i < 0)
        {
            return;
        } else
        {
            SQLiteDatabase sqlitedatabase = getWritableDatabase();
            sqlitedatabase.delete("alerm", (new StringBuilder()).append("id = ").append(Integer.toString(i)).toString(), null);
            sqlitedatabase.close();
            return;
        }
    }

    public void deleteByType(int i)
    {
        if (i < 0)
        {
            return;
        } else
        {
            SQLiteDatabase sqlitedatabase = getWritableDatabase();
            sqlitedatabase.delete("alerm", (new StringBuilder()).append("type = ").append(Integer.toString(i)).toString(), null);
            sqlitedatabase.close();
            return;
        }
    }

    public AlermData getAlerm(int i)
    {
        SQLiteDatabase sqlitedatabase;
        String as[];
        sqlitedatabase = getReadableDatabase();
        as = new String[1];
        as[0] = Integer.toString(i);
        Cursor cursor1 = sqlitedatabase.query("alerm", columns, "id = ? ", as, null, null, "updatedate", Integer.toString(4));
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
        AlermData alermdata = null;
        if (flag)
        {
            alermdata = new AlermData();
            alermdata.setId(cursor.getInt(0));
            alermdata.setLine(cursor.getString(1));
            alermdata.setTimetableId(cursor.getInt(2));
            alermdata.setStartTime(cursor.getInt(3));
            alermdata.setCountdownTime(cursor.getInt(4));
            alermdata.setRepeat(cursor.getString(5));
            boolean flag1;
            boolean flag2;
            if (cursor.getInt(6) == 1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            alermdata.setSetting(flag1);
            alermdata.setType(cursor.getInt(7));
            if (cursor.getInt(8) == 1)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            alermdata.setVibration(flag2);
            alermdata.setSound(cursor.getString(9));
            alermdata.setSoundUri(cursor.getString(10));
            alermdata.setAlermLength(cursor.getInt(11));
            alermdata.setAlermVolume(cursor.getInt(12));
            cursor.moveToNext();
        }
        cursor.close();
        sqlitedatabase.close();
        return alermdata;
    }

    public ArrayList getAlermAll()
    {
        SQLiteDatabase sqlitedatabase = getReadableDatabase();
        Cursor cursor1 = sqlitedatabase.query("alerm", columns, null, null, null, null, "updatedate", Integer.toString(6));
        Cursor cursor = cursor1;
_L1:
        Exception exception;
        if (cursor == null)
        {
            sqlitedatabase.close();
            return null;
        } else
        {
            ArrayList arraylist = makeStartData(cursor);
            cursor.close();
            sqlitedatabase.close();
            return arraylist;
        }
        exception;
        exception.printStackTrace();
        cursor = null;
          goto _L1
    }

    public ArrayList getAlermAll(int i)
    {
        SQLiteDatabase sqlitedatabase;
        String as[];
        sqlitedatabase = getReadableDatabase();
        as = new String[1];
        as[0] = Integer.toString(i);
        Cursor cursor1 = sqlitedatabase.query("alerm", columns, "type = ?", as, null, null, "updatedate", Integer.toString(4));
        Cursor cursor = cursor1;
_L1:
        Exception exception;
        if (cursor == null)
        {
            sqlitedatabase.close();
            return null;
        } else
        {
            ArrayList arraylist = makeStartData(cursor);
            cursor.close();
            sqlitedatabase.close();
            return arraylist;
        }
        exception;
        exception.printStackTrace();
        cursor = null;
          goto _L1
    }

    public ArrayList getAlermByTimetableId(int i)
    {
        SQLiteDatabase sqlitedatabase;
        String as[];
        sqlitedatabase = getReadableDatabase();
        as = new String[1];
        as[0] = Integer.toString(i);
        Cursor cursor1 = sqlitedatabase.query("alerm", columns, "timetable_id = ?", as, null, null, "updatedate", Integer.toString(4));
        Cursor cursor = cursor1;
_L1:
        Exception exception;
        if (cursor == null)
        {
            sqlitedatabase.close();
            return null;
        } else
        {
            ArrayList arraylist = makeStartData(cursor);
            cursor.close();
            sqlitedatabase.close();
            return arraylist;
        }
        exception;
        exception.printStackTrace();
        cursor = null;
          goto _L1
    }

    public ArrayList getAlermByTimetableId(int i, int j)
    {
        SQLiteDatabase sqlitedatabase;
        String as[];
        sqlitedatabase = getReadableDatabase();
        as = new String[2];
        as[0] = Integer.toString(i);
        as[1] = Integer.toString(j);
        Cursor cursor1 = sqlitedatabase.query("alerm", columns, "timetable_id = ? and type = ?", as, null, null, "updatedate", Integer.toString(4));
        Cursor cursor = cursor1;
_L1:
        Exception exception;
        if (cursor == null)
        {
            sqlitedatabase.close();
            return null;
        } else
        {
            ArrayList arraylist = makeStartData(cursor);
            cursor.close();
            sqlitedatabase.close();
            return arraylist;
        }
        exception;
        exception.printStackTrace();
        cursor = null;
          goto _L1
    }

    public AlermData getCountAlerm()
    {
        ArrayList arraylist = getAlermAll(AlermData.TYPE_ALERT);
        if (arraylist != null && arraylist.size() > 0)
        {
            return (AlermData)arraylist.get(0);
        } else
        {
            return null;
        }
    }

    public AlermData getCountAlerm(int i)
    {
        return getAlerm(i);
    }

    protected String getDate()
    {
        Date date = new Date();
        return (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(date);
    }

    public AlermData getStartAlerm(int i)
    {
        return getAlerm(i);
    }

    public ArrayList getStartAll()
    {
        return getAlermAll(AlermData.TYPE_START);
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        createNewAlermTable(sqlitedatabase);
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
    }

    public void updateAlerm(AlermData alermdata)
    {
        int i;
        if (alermdata != null)
        {
            if ((i = alermdata.getId()) >= 0)
            {
                SQLiteDatabase sqlitedatabase = getWritableDatabase();
                ContentValues contentvalues = new ContentValues();
                contentvalues.put("Id", Integer.valueOf(alermdata.getId()));
                contentvalues.put("line", alermdata.getLine());
                contentvalues.put("timetable_id", Integer.valueOf(alermdata.getTimetableId()));
                contentvalues.put("updatedate", getDate());
                contentvalues.put("starttime", Integer.valueOf(alermdata.getStartTime()));
                contentvalues.put("countdowntime", Integer.valueOf(alermdata.getCountdownTime()));
                contentvalues.put("repeat", alermdata.getRepeat());
                contentvalues.put("type", Integer.valueOf(alermdata.getType()));
                boolean flag = alermdata.isSetting();
                int j = 0;
                if (flag)
                {
                    j = 1;
                }
                contentvalues.put("setting", Integer.valueOf(j));
                boolean flag1 = alermdata.isVibration();
                int k = 0;
                if (flag1)
                {
                    k = 1;
                }
                contentvalues.put("vibration", Integer.valueOf(k));
                contentvalues.put("sound", alermdata.getSound());
                contentvalues.put("sound_uri", alermdata.getSoundUri());
                contentvalues.put("alerm_length", Integer.valueOf(alermdata.getAlermLength()));
                contentvalues.put("alerm_volume", Integer.valueOf(alermdata.getAlermVolume()));
                sqlitedatabase.update("alerm", contentvalues, (new StringBuilder()).append("id = ").append(Integer.toString(i)).toString(), null);
                sqlitedatabase.close();
                return;
            }
        }
    }
}
