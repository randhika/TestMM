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

public class RouteMapCache extends SQLiteOpenHelper
{

    public RouteMapCache(Context context)
    {
        super(context, "routemap_cache.db", null, 1);
    }

    public RouteMapCache(Context context, String s)
    {
        super(context, "routemap_cache.db", null, 1);
    }

    public RouteMapCache(Context context, String s, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory, int i)
    {
        super(context, s, cursorfactory, i);
    }

    public void addFileInfo(String s, int i)
    {
        SQLiteDatabase sqlitedatabase = getReadableDatabase();
        Cursor cursor = sqlitedatabase.query("fileinfo", new String[] {
            "id", "name", "size", "updatedate"
        }, "name = ?", new String[] {
            s
        }, null, null, null);
        if (cursor.getCount() == 0)
        {
            SQLiteDatabase sqlitedatabase2 = getWritableDatabase();
            ContentValues contentvalues1 = new ContentValues();
            contentvalues1.put("name", s);
            contentvalues1.put("size", Integer.valueOf(i));
            contentvalues1.put("updatedate", getDate());
            sqlitedatabase2.insert("fileinfo", null, contentvalues1);
            sqlitedatabase2.close();
        } else
        {
            SQLiteDatabase sqlitedatabase1 = getWritableDatabase();
            cursor.moveToFirst();
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("size", Integer.valueOf(i));
            contentvalues.put("updatedate", getDate());
            String as[] = new String[1];
            as[0] = cursor.getString(0);
            sqlitedatabase1.update("fileinfo", contentvalues, "id = ?", as);
            sqlitedatabase1.close();
        }
        cursor.close();
        sqlitedatabase.close();
    }

    public void deleteFileInfo()
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        sqlitedatabase.delete("fileinfo", null, null);
        sqlitedatabase.close();
    }

    public void deleteFileInfo(String s)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        sqlitedatabase.delete("fileinfo", "name = ?", new String[] {
            s
        });
        sqlitedatabase.close();
    }

    protected String getDate()
    {
        Date date = new Date();
        return (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(date);
    }

    public List getFileInfo(String s)
    {
        ArrayList arraylist = new ArrayList();
        SQLiteDatabase sqlitedatabase = getReadableDatabase();
        Cursor cursor = sqlitedatabase.query("fileinfo", new String[] {
            "id", "name", "size", "updatedate"
        }, "name = ?", new String[] {
            s
        }, null, null, null);
        for (boolean flag = cursor.moveToFirst(); flag; flag = cursor.moveToNext())
        {
            arraylist.add(cursor.getString(0));
            arraylist.add(cursor.getString(1));
            arraylist.add(cursor.getString(2));
            arraylist.add(cursor.getString(3));
        }

        cursor.close();
        sqlitedatabase.close();
        return arraylist;
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        Exception exception;
        sqlitedatabase.beginTransaction();
        try
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("create table fileinfo (");
            stringbuilder.append(" id integer primary key autoincrement not null,");
            stringbuilder.append(" name text not null,");
            stringbuilder.append(" size integer not null default 0,");
            stringbuilder.append(" updatedate text not null");
            stringbuilder.append(");");
            sqlitedatabase.execSQL(stringbuilder.toString());
            sqlitedatabase.setTransactionSuccessful();
        }
        catch (SQLiteException sqliteexception)
        {
            sqlitedatabase.endTransaction();
            return;
        }
        finally
        {
            sqlitedatabase.endTransaction();
        }
        sqlitedatabase.endTransaction();
        return;
        throw exception;
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
    }
}
