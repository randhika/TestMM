// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.android.gms.internal.ij;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package com.google.android.gms.tagmanager:
//            bh, ca, ak

class wY extends SQLiteOpenHelper
{

    final ca agt;
    private boolean wX;
    private long wY;

    private void a(SQLiteDatabase sqlitedatabase)
    {
        Cursor cursor;
        HashSet hashset;
        cursor = sqlitedatabase.rawQuery("SELECT * FROM gtm_hits WHERE 0", null);
        hashset = new HashSet();
        String as[] = cursor.getColumnNames();
        int i = 0;
_L2:
        if (i >= as.length)
        {
            break; /* Loop/switch isn't completed */
        }
        hashset.add(as[i]);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        cursor.close();
        if (!hashset.remove("hit_id") || !hashset.remove("hit_url") || !hashset.remove("hit_time") || !hashset.remove("hit_first_send_time"))
        {
            throw new SQLiteException("Database column missing");
        }
        break MISSING_BLOCK_LABEL_124;
        Exception exception;
        exception;
        cursor.close();
        throw exception;
        if (!hashset.isEmpty())
        {
            throw new SQLiteException("Database has extra columns");
        } else
        {
            return;
        }
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
        bh.D((new StringBuilder()).append("Error querying for table ").append(s).toString());
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
        if (wX && 0x36ee80L + wY > ca.a(agt).currentTimeMillis())
        {
            throw new SQLiteException("Database creation failed");
        }
        wX = true;
        wY = ca.a(agt).currentTimeMillis();
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
        ca.c(agt).getDatabasePath(ca.b(agt)).delete();
        sqlitedatabase = null;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        ak.N(sqlitedatabase.getPath());
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
        if (!a("gtm_hits", sqlitedatabase))
        {
            sqlitedatabase.execSQL(ca.mc());
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

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
    }

    rFactory(ca ca1, Context context, String s)
    {
        agt = ca1;
        super(context, s, null, 1);
        wY = 0L;
    }
}
