// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import jp.co.yahoo.android.apps.transit.api.data.ConditionData;
import jp.co.yahoo.android.apps.transit.api.data.NaviSearchData;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

public class ResultDB extends SQLiteOpenHelper
{

    private static final int SEARCH_RESULT_DB_VER = 5;
    private final String MYROUTE_TABLE_NAME;
    private final String SEARCH_RESULT_HISTORY_TABLE_NAME;
    private final String SEARCH_RESULT_MAX;
    private final String SEARCH_RESULT_TABLE_NAME;
    private final String TIMETABLE_TABLE_NAME;
    private Context m_Context;

    public ResultDB(Context context)
    {
        super(context, "result.db", null, 5);
        SEARCH_RESULT_TABLE_NAME = "search_result";
        SEARCH_RESULT_HISTORY_TABLE_NAME = "search_result_history";
        TIMETABLE_TABLE_NAME = "timetable";
        MYROUTE_TABLE_NAME = "myroute";
        SEARCH_RESULT_MAX = "20";
        m_Context = context;
    }

    public ResultDB(Context context, String s)
    {
        super(context, "result.db", null, 5);
        SEARCH_RESULT_TABLE_NAME = "search_result";
        SEARCH_RESULT_HISTORY_TABLE_NAME = "search_result_history";
        TIMETABLE_TABLE_NAME = "timetable";
        MYROUTE_TABLE_NAME = "myroute";
        SEARCH_RESULT_MAX = "20";
        m_Context = context;
    }

    public ResultDB(Context context, String s, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory, int i)
    {
        super(context, s, cursorfactory, i);
        SEARCH_RESULT_TABLE_NAME = "search_result";
        SEARCH_RESULT_HISTORY_TABLE_NAME = "search_result_history";
        TIMETABLE_TABLE_NAME = "timetable";
        MYROUTE_TABLE_NAME = "myroute";
        SEARCH_RESULT_MAX = "20";
        m_Context = context;
    }

    private int count(String s, String s1, String s2)
    {
        SQLiteDatabase sqlitedatabase = getReadableDatabase();
        Cursor cursor = sqlitedatabase.rawQuery((new StringBuilder()).append("SELECT count(*) FROM ").append(s).append(" WHERE ").append(s2).append(" = ").append(s1).append(";").toString(), null);
        cursor.moveToFirst();
        int i = cursor.getInt(0);
        sqlitedatabase.close();
        return i;
    }

    private void createMyrouteTable(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.beginTransaction();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("create table myroute (");
        stringbuilder.append(" id integer primary key autoincrement not null,");
        stringbuilder.append(" query text not null,");
        stringbuilder.append(" sortorder integer not null,");
        stringbuilder.append(" timestamp integer not null,");
        stringbuilder.append(" updatedate text not null,");
        stringbuilder.append(" referdate text not null");
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
        createSearchResultMemoTable(sqlitedatabase);
        createTimetableTable(sqlitedatabase);
        createSearchResultHistoryTable(sqlitedatabase);
        createMyrouteTable(sqlitedatabase);
    }

    private void createSearchResultHistoryTable(SQLiteDatabase sqlitedatabase)
    {
        createSearchResultTable(sqlitedatabase, "search_result_history");
    }

    private void createSearchResultMemoTable(SQLiteDatabase sqlitedatabase)
    {
        createSearchResultTable(sqlitedatabase, "search_result");
    }

    private void createSearchResultTable(SQLiteDatabase sqlitedatabase, String s)
    {
        sqlitedatabase.beginTransaction();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append((new StringBuilder()).append("create table ").append(s).append(" (").toString());
        stringbuilder.append(" id integer primary key autoincrement not null,");
        stringbuilder.append(" condition blob not null,");
        stringbuilder.append(" result blob not null,");
        if (s.equals("search_result"))
        {
            stringbuilder.append(" category text not null,");
        }
        stringbuilder.append(" updatedate text not null,");
        stringbuilder.append(" referdate text not null");
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

    private void createTimetableTable(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.beginTransaction();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("create table timetable (");
        stringbuilder.append(" id integer primary key autoincrement not null,");
        stringbuilder.append(" condition blob not null,");
        stringbuilder.append(" result blob not null,");
        stringbuilder.append(" updatedate text not null,");
        stringbuilder.append(" referdate text not null,");
        stringbuilder.append(" savedate text not null");
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

    private void deleteOverMax(String s)
    {
        String s1;
        String as[];
        s1 = null;
        as = null;
        if (!s.equals("timetable")) goto _L2; else goto _L1
_L1:
        String s2 = "savedate asc";
_L4:
        SQLiteDatabase sqlitedatabase;
        String as1[];
        Cursor cursor;
        if (count(s) <= Integer.parseInt("20"))
        {
            break MISSING_BLOCK_LABEL_105;
        }
        sqlitedatabase = getReadableDatabase();
        as1 = (new String[] {
            "id"
        });
        cursor = null;
        cursor = sqlitedatabase.query(s, as1, s1, as, null, null, s2, "1");
        cursor.moveToFirst();
        String s3 = cursor.getString(0);
        cursor.close();
        sqlitedatabase.close();
        delete(s, s3);
        return;
_L2:
        s2 = "updatedate asc";
        boolean flag = s.equals("search_result");
        s1 = null;
        as = null;
        if (flag)
        {
            s1 = "category = ?";
            as = new String[1];
            as[0] = m_Context.getString(0x7f0d048f);
        }
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        if (cursor != null)
        {
            cursor.close();
        }
        sqlitedatabase.close();
        exception.printStackTrace();
        return;
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

    private String getMyrouteQuery(ConditionData conditiondata)
    {
        ConditionData conditiondata1 = (ConditionData)conditiondata.clone();
        conditiondata1.resultId = -1;
        conditiondata1.mtf = -1;
        conditiondata1.afterFinal = false;
        conditiondata1.midnightBus = false;
        return TransitUtil.condToUri("?", conditiondata1, m_Context, true, false, true).toString();
    }

    private Bundle setSearchConditionData(Cursor cursor)
    {
        Bundle bundle = new Bundle();
        bundle.putString("id", cursor.getString(0));
        Object obj = TransitUtil.byte2object(cursor.getBlob(1));
        if (obj == null)
        {
            Bundle bundle1 = getBundle(cursor.getBlob(1));
            obj = TransitUtil.bundle2ConditionData(m_Context, bundle1);
        }
        bundle.putSerializable(m_Context.getString(0x7f0d022c), (Serializable)obj);
        bundle.putSerializable(m_Context.getString(0x7f0d0232), (Serializable)null);
        bundle.putString(m_Context.getString(0x7f0d0249), cursor.getString(2));
        return bundle;
    }

    private Bundle setSearchResultsData(Cursor cursor)
    {
        Bundle bundle = new Bundle();
        bundle.putString("id", cursor.getString(0));
        Object obj = TransitUtil.byte2object(cursor.getBlob(1));
        if (obj == null)
        {
            Bundle bundle2 = getBundle(cursor.getBlob(1));
            obj = TransitUtil.bundle2ConditionData(m_Context, bundle2);
        }
        bundle.putSerializable(m_Context.getString(0x7f0d022c), (Serializable)obj);
        Object obj1 = TransitUtil.byte2object(cursor.getBlob(2));
        if (obj1 == null)
        {
            Bundle bundle1 = getBundle(cursor.getBlob(2));
            obj1 = TransitUtil.bundle2NaviSearchData(m_Context, bundle1);
        }
        bundle.putSerializable(m_Context.getString(0x7f0d0232), (Serializable)obj1);
        bundle.putString(m_Context.getString(0x7f0d0249), cursor.getString(3));
        return bundle;
    }

    private void updateMyrouteOrderAll()
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        ArrayList arraylist = new ArrayList();
        Cursor cursor = sqlitedatabase.query("myroute", new String[] {
            "id"
        }, null, null, null, null, "timestamp desc", "20");
        if (cursor != null)
        {
            for (boolean flag = cursor.moveToFirst(); flag; flag = cursor.moveToNext())
            {
                arraylist.add(cursor.getString(0));
            }

            cursor.close();
        }
        int i = 0;
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext();)
        {
            String s = (String)iterator.next();
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("sortorder", Integer.valueOf(i));
            sqlitedatabase.update("myroute", contentvalues, (new StringBuilder()).append("id = ").append(s).toString(), null);
            i++;
        }

        sqlitedatabase.close();
    }

    private String updateRefer(String s, String s1)
    {
        String s2 = getDate();
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("referdate", s2);
        sqlitedatabase.update(s, contentvalues, (new StringBuilder()).append("id = ").append(s1).toString(), null);
        sqlitedatabase.close();
        return s2;
    }

    private void upgradeSearchResultMemoTable(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.beginTransaction();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("alter table search_result");
        stringbuilder.append(" add category text after result default ");
        stringbuilder.append(m_Context.getString(0x7f0d048f));
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

    public void addMyroute(ConditionData conditiondata)
    {
        String s = getMyrouteQuery(conditiondata);
        String s1 = getDate();
        long l = System.currentTimeMillis() / 1000L;
        int i = count("myroute");
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        if (i >= Integer.valueOf("20").intValue())
        {
            int j = 1 + (i - Integer.valueOf("20").intValue());
            ArrayList arraylist = new ArrayList(j);
            Cursor cursor = sqlitedatabase.query("myroute", new String[] {
                "id"
            }, null, null, null, null, "timestamp asc", String.valueOf(j));
            if (cursor != null)
            {
                for (boolean flag = cursor.moveToFirst(); flag; flag = cursor.moveToNext())
                {
                    arraylist.add(cursor.getString(0));
                }

                cursor.close();
            }
            for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); sqlitedatabase.delete("myroute", "id = ?", new String[] {
    (String)iterator.next()
})) { }
            i = Integer.valueOf("20").intValue() - j;
        }
        try
        {
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("query", s);
            contentvalues.put("sortorder", Integer.valueOf(0));
            contentvalues.put("timestamp", Long.valueOf(l));
            contentvalues.put("updatedate", s1);
            contentvalues.put("referdate", s1);
            sqlitedatabase.insert("myroute", null, contentvalues);
            sqlitedatabase.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        if (i > 0)
        {
            updateMyrouteOrderAll();
        }
    }

    public void addSearchResults(String s, ConditionData conditiondata, Object obj, int i)
    {
        long l = -1L;
        try
        {
            SQLiteDatabase sqlitedatabase = getWritableDatabase();
            String s1 = getDate();
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("condition", TransitUtil.obj2byte(conditiondata));
            contentvalues.put("result", TransitUtil.obj2byte(obj));
            if (s.equals("search_result"))
            {
                contentvalues.put("category", m_Context.getString(0x7f0d048f));
            }
            contentvalues.put("updatedate", s1);
            contentvalues.put("referdate", s1);
            l = sqlitedatabase.insert(s, null, contentvalues);
            sqlitedatabase.close();
            deleteOverMax(s);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        if (l != -1L && s.equals("search_result"))
        {
            android.content.SharedPreferences.Editor editor = m_Context.getSharedPreferences(m_Context.getString(0x7f0d04e1), 0).edit();
            editor.putString(m_Context.getString(0x7f0d039f), String.valueOf(l));
            editor.commit();
        }
    }

    public void addSearchResultsHistory(ConditionData conditiondata, NaviSearchData navisearchdata, int i)
    {
        addSearchResults("search_result_history", conditiondata, navisearchdata, i);
    }

    public void addSearchResultsMemo(ConditionData conditiondata, NaviSearchData navisearchdata, int i)
    {
        addSearchResults("search_result", conditiondata, navisearchdata, i);
    }

    public void addTimetable(Bundle bundle, Bundle bundle1)
    {
        long l = -1L;
        try
        {
            SQLiteDatabase sqlitedatabase = getWritableDatabase();
            String s = getDate();
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("condition", getBytes(bundle));
            contentvalues.put("result", getBytes(bundle1));
            contentvalues.put("updatedate", s);
            contentvalues.put("referdate", s);
            contentvalues.put("savedate", s);
            l = sqlitedatabase.insert("timetable", null, contentvalues);
            sqlitedatabase.close();
            deleteTimetableOverMax();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        if (l != -1L)
        {
            android.content.SharedPreferences.Editor editor = m_Context.getSharedPreferences(m_Context.getString(0x7f0d04e1), 0).edit();
            editor.putString(m_Context.getString(0x7f0d03a0), String.valueOf(l));
            editor.commit();
        }
    }

    public int count(String s)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        Cursor cursor = sqlitedatabase.rawQuery((new StringBuilder()).append("SELECT count(*) FROM ").append(s).append(";").toString(), null);
        cursor.moveToFirst();
        int i = cursor.getInt(0);
        cursor.close();
        sqlitedatabase.close();
        return i;
    }

    public int countMyroute()
    {
        int i = count("myroute");
        if (i < Integer.parseInt("20"))
        {
            return i;
        } else
        {
            return Integer.parseInt("20");
        }
    }

    public int countSearchResultMemo(String s)
    {
        int i = count("search_result", s, "category");
        if (i < Integer.parseInt("20"))
        {
            return i;
        } else
        {
            return Integer.parseInt("20");
        }
    }

    public int countSearchResultsHistory()
    {
        int i = count("search_result_history");
        if (i < Integer.parseInt("20"))
        {
            return i;
        } else
        {
            return Integer.parseInt("20");
        }
    }

    public int countSearchResultsMemo()
    {
        int i = count("search_result");
        if (i < Integer.parseInt("20"))
        {
            return i;
        } else
        {
            return Integer.parseInt("20");
        }
    }

    public int countTimetable()
    {
        int i = count("timetable");
        if (i < Integer.parseInt("20"))
        {
            return i;
        } else
        {
            return Integer.parseInt("20");
        }
    }

    public void delete(String s, String s1)
    {
        if (!s.equals("search_result")) goto _L2; else goto _L1
_L1:
        SharedPreferences sharedpreferences1 = m_Context.getSharedPreferences(m_Context.getString(0x7f0d04e1), 0);
        if (s1.equals(sharedpreferences1.getString(m_Context.getString(0x7f0d039f), "")))
        {
            android.content.SharedPreferences.Editor editor1 = sharedpreferences1.edit();
            editor1.putString(m_Context.getString(0x7f0d039f), "");
            editor1.commit();
        }
_L4:
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        sqlitedatabase.delete(s, "id = ?", new String[] {
            s1
        });
        sqlitedatabase.close();
        return;
_L2:
        if (s.equals("timetable"))
        {
            SharedPreferences sharedpreferences = m_Context.getSharedPreferences(m_Context.getString(0x7f0d04e1), 0);
            if (s1.equals(sharedpreferences.getString(m_Context.getString(0x7f0d03a0), "")))
            {
                android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(m_Context.getString(0x7f0d03a0), "");
                editor.commit();
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void deleteAll()
    {
        deleteResults();
    }

    public void deleteMyroute(String as[])
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        int i = as.length;
        for (int j = 0; j < i; j++)
        {
            sqlitedatabase.delete("myroute", "id = ?", new String[] {
                as[j]
            });
        }

        sqlitedatabase.close();
        updateMyrouteOrderAll();
    }

    public void deleteResults()
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        sqlitedatabase.delete("search_result", null, null);
        sqlitedatabase.close();
    }

    public void deleteResultsHistory(String s)
    {
        delete("search_result_history", s);
    }

    public void deleteResultsMemo(String s)
    {
        delete("search_result", s);
    }

    public void deleteSearchResultHistoryOverMax()
    {
        deleteOverMax("search_result_history");
    }

    public void deleteSearchResultMemoOverMax()
    {
        deleteOverMax("search_result");
    }

    public void deleteTimetable(String s)
    {
        delete("timetable", s);
    }

    public void deleteTimetableOverMax()
    {
        deleteOverMax("timetable");
    }

    public Bundle getBundle(byte abyte0[])
    {
        if (abyte0 == null || abyte0.length <= 0)
        {
            return null;
        } else
        {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(abyte0, 0, abyte0.length);
            parcel.setDataPosition(0);
            Bundle bundle = parcel.readBundle();
            parcel.recycle();
            return bundle;
        }
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

    public String getMaxMyroute()
    {
        return "20";
    }

    public String getMaxSearchResultsHistory()
    {
        return "20";
    }

    public String getMaxSearchResultsMemo()
    {
        return "20";
    }

    public String getMaxTimetable()
    {
        return "20";
    }

    public List getMyroute()
    {
        ArrayList arraylist;
        SQLiteDatabase sqlitedatabase;
        String as[];
        arraylist = new ArrayList();
        sqlitedatabase = getReadableDatabase();
        as = (new String[] {
            "id", "query"
        });
        Cursor cursor1 = sqlitedatabase.query("myroute", as, null, null, null, null, "timestamp desc", "20");
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
        boolean flag = cursor.moveToFirst();
        while (flag) 
        {
            Bundle bundle = new Bundle();
            bundle.putString("id", cursor.getString(0));
            String s = cursor.getString(1);
            if (TransitUtil.isEmpty(s))
            {
                bundle.putSerializable(m_Context.getString(0x7f0d022c), (ConditionData)null);
            } else
            {
                ConditionData conditiondata = new ConditionData();
                TransitUtil.uriToCond(Uri.parse(s), conditiondata, m_Context, true);
                bundle.putSerializable(m_Context.getString(0x7f0d022c), conditiondata);
            }
            arraylist.add(bundle);
            flag = cursor.moveToNext();
        }
        cursor.close();
        sqlitedatabase.close();
        return arraylist;
    }

    public List getSearchCondition(String s)
    {
        ArrayList arraylist;
        SQLiteDatabase sqlitedatabase;
        String as[];
        arraylist = new ArrayList();
        sqlitedatabase = getReadableDatabase();
        as = (new String[] {
            "id", "condition", "updatedate", "referdate"
        });
        Cursor cursor1 = sqlitedatabase.query(s, as, null, null, null, null, "updatedate desc", "20");
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
            arraylist.add(setSearchConditionData(cursor));
        }

        cursor.close();
        sqlitedatabase.close();
        return arraylist;
    }

    public List getSearchHistoryCondition()
    {
        return getSearchCondition("search_result_history");
    }

    public List getSearchMenoCondition()
    {
        return getSearchCondition("search_result");
    }

    public Bundle getSearchResult(String s, String s1)
    {
        Bundle bundle = new Bundle();
        SQLiteDatabase sqlitedatabase;
        String as[];
        String as1[];
        Exception exception1;
        Cursor cursor;
        Bundle bundle1;
        Cursor cursor1;
        try
        {
            sqlitedatabase = getReadableDatabase();
            as = (new String[] {
                "id", "condition", "result", "updatedate", "referdate"
            });
            as1 = (new String[] {
                s
            });
        }
        catch (Exception exception)
        {
            return null;
        }
        cursor1 = sqlitedatabase.query(s1, as, "id = ?", as1, null, null, "updatedate desc", "20");
        cursor = cursor1;
_L2:
        if (cursor != null)
        {
            break; /* Loop/switch isn't completed */
        }
        sqlitedatabase.close();
        return bundle;
        exception1;
        exception1.printStackTrace();
        cursor = null;
        if (true) goto _L2; else goto _L1
_L1:
        cursor.moveToFirst();
        bundle1 = setSearchResultsData(cursor);
        cursor.close();
        sqlitedatabase.close();
        return bundle1;
    }

    public Bundle getSearchResultHistory(String s)
    {
        return getSearchResult(s, "search_result_history");
    }

    public Bundle getSearchResultMemo(String s)
    {
        return getSearchResult(s, "search_result");
    }

    public List getSearchResults(String s)
    {
        ArrayList arraylist;
        SQLiteDatabase sqlitedatabase;
        String as[];
        arraylist = new ArrayList();
        sqlitedatabase = getReadableDatabase();
        as = (new String[] {
            "id", "condition", "result", "updatedate", "referdate"
        });
        Cursor cursor1 = sqlitedatabase.query(s, as, null, null, null, null, "updatedate desc", "20");
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
            arraylist.add(setSearchResultsData(cursor));
        }

        cursor.close();
        sqlitedatabase.close();
        return arraylist;
    }

    public List getSearchResultsHistory()
    {
        return getSearchResults("search_result_history");
    }

    public HashMap getSearchResultsMemo()
    {
        HashMap hashmap;
        ArrayList arraylist;
        ArrayList arraylist1;
        ArrayList arraylist2;
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        hashmap = new HashMap();
        arraylist = new ArrayList();
        arraylist1 = new ArrayList();
        arraylist2 = new ArrayList();
        sqlitedatabase = getReadableDatabase();
        String as[] = {
            "id", "condition", "result", "updatedate", "referdate", "category"
        };
        Cursor cursor1;
        try
        {
            cursor1 = sqlitedatabase.query("search_result", as, null, null, null, null, "updatedate desc", "20");
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            cursor = null;
            continue; /* Loop/switch isn't completed */
        }
        cursor = cursor1;
_L7:
        if (cursor != null) goto _L2; else goto _L1
_L1:
        sqlitedatabase.close();
_L4:
        return hashmap;
_L2:
        String s;
        s = null;
        boolean flag = cursor.moveToFirst();
        while (flag) 
        {
            Bundle bundle = setSearchResultsData(cursor);
            if (s == null)
            {
                s = cursor.getString(0);
            }
            String s1 = cursor.getString(5);
            if (TextUtils.isEmpty(s1))
            {
                s1 = m_Context.getString(0x7f0d048f);
            }
            bundle.putString(m_Context.getString(0x7f0d0247), s1);
            if (s1.equals(m_Context.getString(0x7f0d048d)))
            {
                arraylist1.add(bundle);
            } else
            if (s1.equals(m_Context.getString(0x7f0d048e)))
            {
                arraylist2.add(bundle);
            } else
            {
                arraylist.add(bundle);
            }
            flag = cursor.moveToNext();
        }
        hashmap.put(m_Context.getString(0x7f0d048f), arraylist);
        hashmap.put(m_Context.getString(0x7f0d048d), arraylist1);
        hashmap.put(m_Context.getString(0x7f0d048e), arraylist2);
        cursor.close();
        sqlitedatabase.close();
        if (s == null) goto _L4; else goto _L3
_L3:
        SharedPreferences sharedpreferences = m_Context.getSharedPreferences(m_Context.getString(0x7f0d04e1), 0);
        if (sharedpreferences.getString(m_Context.getString(0x7f0d039f), null) != null) goto _L4; else goto _L5
_L5:
        android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(m_Context.getString(0x7f0d039f), s);
        editor.commit();
        return hashmap;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public List getTimetable()
    {
        ArrayList arraylist;
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        arraylist = new ArrayList();
        sqlitedatabase = getReadableDatabase();
        String as[] = {
            "id", "condition", "result", "updatedate", "referdate", "savedate"
        };
        Cursor cursor1;
        try
        {
            cursor1 = sqlitedatabase.query("timetable", as, null, null, null, null, "savedate desc", "20");
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            cursor = null;
            continue; /* Loop/switch isn't completed */
        }
        cursor = cursor1;
_L7:
        if (cursor != null) goto _L2; else goto _L1
_L1:
        sqlitedatabase.close();
_L4:
        return arraylist;
_L2:
        String s;
        s = null;
        for (boolean flag = cursor.moveToFirst(); flag; flag = cursor.moveToNext())
        {
            if (s == null)
            {
                s = cursor.getString(0);
            }
            Bundle bundle = new Bundle();
            bundle.putString("id", cursor.getString(0));
            bundle.putBundle(m_Context.getString(0x7f0d022c), getBundle(cursor.getBlob(1)));
            bundle.putBundle(m_Context.getString(0x7f0d0232), getBundle(cursor.getBlob(2)));
            bundle.putString(m_Context.getString(0x7f0d0249), cursor.getString(3));
            bundle.putString(m_Context.getString(0x7f0d01dd), cursor.getString(4));
            bundle.putString(m_Context.getString(0x7f0d022b), cursor.getString(5));
            arraylist.add(bundle);
        }

        cursor.close();
        sqlitedatabase.close();
        if (s == null) goto _L4; else goto _L3
_L3:
        SharedPreferences sharedpreferences = m_Context.getSharedPreferences(m_Context.getString(0x7f0d04e1), 0);
        if (sharedpreferences.getString(m_Context.getString(0x7f0d03a0), null) != null) goto _L4; else goto _L5
_L5:
        android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(m_Context.getString(0x7f0d03a0), s);
        editor.commit();
        return arraylist;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        createNewTable(sqlitedatabase);
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        if (i != 1) goto _L2; else goto _L1
_L1:
        createTimetableTable(sqlitedatabase);
        createSearchResultHistoryTable(sqlitedatabase);
        upgradeSearchResultMemoTable(sqlitedatabase);
_L4:
        createMyrouteTable(sqlitedatabase);
        return;
_L2:
        if (i == 2)
        {
            createSearchResultHistoryTable(sqlitedatabase);
            upgradeSearchResultMemoTable(sqlitedatabase);
        } else
        if (i == 3)
        {
            upgradeSearchResultMemoTable(sqlitedatabase);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void updateMyrouteQuery(String s, ConditionData conditiondata)
    {
        String s1 = getMyrouteQuery(conditiondata);
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        String s2 = getDate();
        contentvalues.put("query", s1);
        contentvalues.put("updatedate", s2);
        sqlitedatabase.update("myroute", contentvalues, (new StringBuilder()).append("id = ").append(s).toString(), null);
        sqlitedatabase.close();
    }

    public String updateMyrouteRefer(String s)
    {
        return updateRefer("myroute", s);
    }

    public void updateSearchHistoryRefer(String s)
    {
        updateRefer("search_result_history", s);
    }

    public void updateSearchMemoCategory(String s, String s1)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("category", s1);
        sqlitedatabase.update("search_result", contentvalues, (new StringBuilder()).append("id = ").append(s).toString(), null);
        sqlitedatabase.close();
    }

    public void updateSearchMemoRefer(String s)
    {
        updateRefer("search_result", s);
    }

    public void updateTimetable(String s, Bundle bundle, Bundle bundle1)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        String s1 = getDate();
        contentvalues.put("referdate", s1);
        contentvalues.put("updatedate", s1);
        contentvalues.put("condition", getBytes(bundle));
        contentvalues.put("result", getBytes(bundle1));
        sqlitedatabase.update("timetable", contentvalues, (new StringBuilder()).append("id = ").append(s).toString(), null);
        sqlitedatabase.close();
    }

    public String updateTimetableRefer(String s)
    {
        return updateRefer("timetable", s);
    }

    public String updateTimetableResult(String s, Bundle bundle, Bundle bundle1)
    {
        String s1 = getDate();
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("referdate", s1);
        contentvalues.put("condition", getBytes(bundle));
        contentvalues.put("result", getBytes(bundle1));
        sqlitedatabase.update("timetable", contentvalues, (new StringBuilder()).append("id = ").append(s).toString(), null);
        sqlitedatabase.close();
        return s1;
    }
}
