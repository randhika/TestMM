// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.analytics;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package com.google.android.gms.analytics:
//            aa, ac, i, p

class wY extends SQLiteOpenHelper
{

    final ac wW;
    private boolean wX;
    private long wY;

    private void a(SQLiteDatabase sqlitedatabase)
    {
        Cursor cursor;
        HashSet hashset;
        cursor = sqlitedatabase.rawQuery("SELECT * FROM hits2 WHERE 0", null);
        hashset = new HashSet();
        String as[] = cursor.getColumnNames();
        int j = 0;
_L2:
        if (j >= as.length)
        {
            break; /* Loop/switch isn't completed */
        }
        hashset.add(as[j]);
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        cursor.close();
        if (!hashset.remove("hit_id") || !hashset.remove("hit_url") || !hashset.remove("hit_string") || !hashset.remove("hit_time"))
        {
            throw new SQLiteException("Database column missing");
        }
        break MISSING_BLOCK_LABEL_124;
        Exception exception;
        exception;
        cursor.close();
        throw exception;
        boolean flag = hashset.remove("hit_app_id");
        boolean flag1 = false;
        if (!flag)
        {
            flag1 = true;
        }
        if (!hashset.isEmpty())
        {
            throw new SQLiteException("Database has extra columns");
        }
        if (flag1)
        {
            sqlitedatabase.execSQL("ALTER TABLE hits2 ADD COLUMN hit_app_id");
        }
        return;
    }

    private boolean a(String s, SQLiteDatabase sqlitedatabase)
    {
        Cursor cursor = null;
        Cursor cursor2 = sqlitedatabase.query("SQLITE_MASTER", new String[] {
            "name"
        }, "name=?", new String[] {
            s
        }, null, null, null);
        boolean flag = cursor2.moveToFirst();
        if (cursor2 != null)
        {
            cursor2.close();
        }
        return flag;
        SQLiteException sqliteexception;
        sqliteexception;
        Cursor cursor1 = null;
_L4:
        aa.D((new StringBuilder()).append("Error querying for table ").append(s).toString());
        if (cursor1 != null)
        {
            cursor1.close();
        }
        return false;
        Exception exception;
        exception;
_L2:
        if (cursor != null)
        {
            cursor.close();
        }
        throw exception;
        exception;
        cursor = cursor2;
        continue; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        cursor = cursor1;
        exception = exception1;
        if (true) goto _L2; else goto _L1
_L1:
        SQLiteException sqliteexception1;
        sqliteexception1;
        cursor1 = cursor2;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public SQLiteDatabase getWritableDatabase()
    {
        if (wX && 0x36ee80L + wY > ac.a(wW).currentTimeMillis())
        {
            throw new SQLiteException("Database creation failed");
        }
        wX = true;
        wY = ac.a(wW).currentTimeMillis();
        SQLiteDatabase sqlitedatabase1 = super.getWritableDatabase();
        SQLiteDatabase sqlitedatabase = sqlitedatabase1;
_L2:
        if (sqlitedatabase == null)
        {
            sqlitedatabase = super.getWritableDatabase();
        }
        wX = false;
        return sqlitedatabase;
        SQLiteException sqliteexception;
        sqliteexception;
        ac.c(wW).getDatabasePath(ac.b(wW)).delete();
        sqlitedatabase = null;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        p.N(sqlitedatabase.getPath());
    }

    public void onOpen(SQLiteDatabase sqlitedatabase)
    {
        Cursor cursor;
        if (android.os..VERSION.SDK_INT >= 15)
        {
            break MISSING_BLOCK_LABEL_29;
        }
        cursor = sqlitedatabase.rawQuery("PRAGMA journal_mode=memory", null);
        cursor.moveToFirst();
        cursor.close();
        Exception exception;
        if (!a("hits2", sqlitedatabase))
        {
            sqlitedatabase.execSQL(ac.du());
            return;
        } else
        {
            a(sqlitedatabase);
            return;
        }
        exception;
        cursor.close();
        throw exception;
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int j, int k)
    {
    }

    orFactory(ac ac1, Context context, String s)
    {
        wW = ac1;
        super(context, s, null, 1);
        wY = 0L;
    }
}
