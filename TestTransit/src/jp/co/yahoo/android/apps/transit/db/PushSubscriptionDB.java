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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class PushSubscriptionDB extends SQLiteOpenHelper
{

    private static final int DB_VER = 1;
    private final String COLUMN_SUBFLAG;
    private final String COLUMN_TOPIC_ID;
    private final String TABLE_NAME_DIAINFO;
    private Context m_Context;

    public PushSubscriptionDB(Context context)
    {
        super(context, "push_subscription.db", null, 1);
        TABLE_NAME_DIAINFO = "diainfo";
        COLUMN_TOPIC_ID = "topic_id";
        COLUMN_SUBFLAG = "subflag";
        m_Context = context;
    }

    public PushSubscriptionDB(Context context, String s)
    {
        super(context, s, null, 1);
        TABLE_NAME_DIAINFO = "diainfo";
        COLUMN_TOPIC_ID = "topic_id";
        COLUMN_SUBFLAG = "subflag";
        m_Context = context;
    }

    public PushSubscriptionDB(Context context, String s, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory, int i)
    {
        super(context, s, cursorfactory, i);
        TABLE_NAME_DIAINFO = "diainfo";
        COLUMN_TOPIC_ID = "topic_id";
        COLUMN_SUBFLAG = "subflag";
        m_Context = context;
    }

    private void createDiainfoTable(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.beginTransaction();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("create table diainfo (");
        stringbuilder.append(" topic_id text primary key not null,");
        stringbuilder.append(" subflag TINYINT not null");
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
        createDiainfoTable(sqlitedatabase);
    }

    public void deleteDiainfoAll()
    {
        getWritableDatabase().delete("diainfo", null, null);
    }

    public HashMap getDiainfoAll()
    {
        HashMap hashmap;
        SQLiteDatabase sqlitedatabase;
        String as[];
        hashmap = new HashMap();
        sqlitedatabase = getReadableDatabase();
        as = (new String[] {
            "topic_id", "subflag"
        });
        Cursor cursor1 = sqlitedatabase.query("diainfo", as, null, null, null, null, null, null);
        Cursor cursor = cursor1;
_L2:
        if (cursor == null)
        {
            sqlitedatabase.close();
            return hashmap;
        }
        boolean flag = cursor.moveToFirst();
        while (flag) 
        {
            String s = cursor.getString(0);
            boolean flag1;
            if (cursor.getInt(1) == 1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            hashmap.put(s, Boolean.valueOf(flag1));
            flag = cursor.moveToNext();
        }
        cursor.close();
        sqlitedatabase.close();
        return hashmap;
        Exception exception;
        exception;
        cursor = null;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public boolean getDiainfoFlag(String s)
    {
        SQLiteDatabase sqlitedatabase;
        String as[];
        sqlitedatabase = getReadableDatabase();
        as = (new String[] {
            "topic_id", "subflag"
        });
        Cursor cursor1 = sqlitedatabase.query("diainfo", as, "topic_id=?", new String[] {
            s
        }, null, null, null, "1");
        Cursor cursor = cursor1;
_L2:
        if (cursor == null)
        {
            sqlitedatabase.close();
            return false;
        }
        boolean flag = cursor.moveToFirst();
        boolean flag1 = false;
        if (flag)
        {
            if (cursor.getInt(1) == 1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
        }
        cursor.close();
        sqlitedatabase.close();
        return flag1;
        Exception exception;
        exception;
        cursor = null;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        createNewTable(sqlitedatabase);
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        createNewTable(sqlitedatabase);
    }

    public void updateDiainfo(HashMap hashmap)
    {
        HashMap hashmap1 = getDiainfoAll();
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        Iterator iterator = hashmap.keySet().iterator();
        do
        {
            if (iterator.hasNext())
            {
                String s = (String)iterator.next();
                boolean flag = ((Boolean)hashmap.get(s)).booleanValue();
                if (hashmap1.containsKey(s))
                {
                    if (((Boolean)hashmap1.get(s)).booleanValue() != flag)
                    {
                        ContentValues contentvalues1 = new ContentValues();
                        int j;
                        if (flag)
                        {
                            j = 1;
                        } else
                        {
                            j = 0;
                        }
                        contentvalues1.put("subflag", Integer.valueOf(j));
                        sqlitedatabase.update("diainfo", contentvalues1, "topic_id=?", new String[] {
                            s
                        });
                    }
                } else
                {
                    ContentValues contentvalues = new ContentValues();
                    contentvalues.put("topic_id", s);
                    int i;
                    if (flag)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    contentvalues.put("subflag", Integer.valueOf(i));
                    sqlitedatabase.insert("diainfo", null, contentvalues);
                }
            } else
            {
                sqlitedatabase.close();
                return;
            }
        } while (true);
    }
}
