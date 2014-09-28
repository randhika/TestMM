// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.timer.api.data.SkinMetaData;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;

public class SkinDb extends SQLiteOpenHelper
{
    public static class SkinMenuData
    {

        public boolean isDownloaded;
        public String sIconPath;
        public String sId;
        public String sTitle;

        public SkinMenuData()
        {
            isDownloaded = false;
        }
    }


    private static final int SKIN_DB_VER = 2;
    private final String TABLE_NAME;
    private String columns[] = {
        "id_name", "title", "description", "download_url", "thumbnail_url", "icon_url", "image_path", "count", "start_date", "end_date", 
        "update_date", "interval", "spaceid", "is_scalable", "is_setting", "is_download", "is_update", "is_delete"
    };
    private Context context;

    public SkinDb(Context context1)
    {
        super(context1, "skin.db", null, 2);
        TABLE_NAME = "skin";
        context = context1;
        cretateDefaultSet();
    }

    public SkinDb(Context context1, String s)
    {
        super(context1, "skin.db", null, 2);
        TABLE_NAME = "skin";
        context = context1;
        cretateDefaultSet();
    }

    public SkinDb(Context context1, String s, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory, int i)
    {
        super(context1, s, cursorfactory, i);
        TABLE_NAME = "skin";
        context = context1;
        cretateDefaultSet();
    }

    private void createNewTable(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.beginTransaction();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("create table skin (");
        stringbuilder.append(" id integer primary key autoincrement not null,");
        stringbuilder.append(" id_name text not null,");
        stringbuilder.append(" title text not null,");
        stringbuilder.append(" description text,");
        stringbuilder.append(" download_url text,");
        stringbuilder.append(" thumbnail_url text,");
        stringbuilder.append(" icon_url text,");
        stringbuilder.append(" image_path text,");
        stringbuilder.append(" count int,");
        stringbuilder.append(" start_date text,");
        stringbuilder.append(" end_date text,");
        stringbuilder.append(" update_date text,");
        stringbuilder.append(" interval int,");
        stringbuilder.append(" spaceid int,");
        stringbuilder.append(" is_scalable TINYINT,");
        stringbuilder.append(" is_setting TINYINT,");
        stringbuilder.append(" is_download TINYINT,");
        stringbuilder.append(" is_update TINYINT,");
        stringbuilder.append(" is_delete TINYINT");
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

    private void cretateDefaultSet()
    {
        if (count() <= 0)
        {
            SkinMetaData skinmetadata = new SkinMetaData();
            skinmetadata.sId = "normal";
            skinmetadata.sTitle = context.getString(0x7f0d04f7);
            skinmetadata.sDescription = context.getString(0x7f0d04f5);
            skinmetadata.sThumbnailUrl = null;
            skinmetadata.sIconUrl = null;
            skinmetadata.isDownloaded = true;
            skinmetadata.isSetting = true;
            skinmetadata.sUpdateDate = "0";
            addSetting(skinmetadata);
            SharedPreferences sharedpreferences = context.getSharedPreferences(context.getString(0x7f0d01a0), 0);
            if (sharedpreferences.getBoolean(context.getString(0x7f0d0237), true))
            {
                android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(context.getString(0x7f0d0237), false);
                editor.commit();
                return;
            }
        }
    }

    private void upgradeTable(SQLiteDatabase sqlitedatabase)
    {
        Exception exception;
        sqlitedatabase.beginTransaction();
        try
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("alter table skin");
            stringbuilder.append(" add icon_url text after thumbnail_url");
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

    public int addSetting(SkinMetaData skinmetadata)
    {
        Exception exception;
        SQLiteDatabase sqlitedatabase = null;
        long l;
        int i;
        try
        {
            sqlitedatabase = getWritableDatabase();
            l = sqlitedatabase.insert("skin", null, getContentValues(skinmetadata));
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
        i = (int)l;
        sqlitedatabase.close();
        return i;
        throw exception;
    }

    public int count()
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        Cursor cursor = sqlitedatabase.rawQuery("SELECT count(*) FROM skin;", null);
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

    public void deleteData(SkinMetaData skinmetadata)
    {
        String s;
        if (skinmetadata != null)
        {
            if ((s = skinmetadata.sId) != null && s != "")
            {
                SQLiteDatabase sqlitedatabase = getWritableDatabase();
                sqlitedatabase.delete("skin", "id_name = ?", new String[] {
                    s
                });
                sqlitedatabase.close();
                return;
            }
        }
    }

    public void deleteNoIds(String as[])
    {
        if (as == null || as.length < 1)
        {
            return;
        }
        String s = "";
        for (int i = 0; i < as.length; i++)
        {
            if (i > 0)
            {
                s = (new StringBuilder()).append(s).append(" and ").toString();
            }
            s = (new StringBuilder()).append(s).append("id_name != ").append(as[i]).toString();
        }

        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        sqlitedatabase.delete("skin", s, null);
        sqlitedatabase.close();
    }

    public ArrayList getAllSkin()
    {
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        sqlitedatabase = getReadableDatabase();
        cursor = null;
        cursor = sqlitedatabase.query("skin", columns, null, null, null, null, "id", null);
        if (cursor != null)
        {
            break MISSING_BLOCK_LABEL_54;
        }
        sqlitedatabase.close();
        if (cursor != null)
        {
            cursor.close();
        }
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
        }
        return null;
        ArrayList arraylist1;
        cursor.moveToFirst();
        arraylist1 = getObject(cursor);
        ArrayList arraylist;
        arraylist = arraylist1;
        if (cursor != null)
        {
            cursor.close();
        }
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
        }
_L2:
        return arraylist;
        Exception exception1;
        exception1;
        if (cursor != null)
        {
            cursor.close();
        }
        arraylist = null;
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
            arraylist = null;
        }
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        if (cursor != null)
        {
            cursor.close();
        }
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
        }
        throw exception;
    }

    public ArrayList getAllSkinMenu()
    {
        SQLiteDatabase sqlitedatabase;
        String as[];
        Cursor cursor;
        sqlitedatabase = getReadableDatabase();
        as = (new String[] {
            "id_name", "title", "icon_url", "image_path", "is_download"
        });
        cursor = null;
        cursor = sqlitedatabase.query("skin", as, null, null, null, null, "id", null);
        if (cursor != null)
        {
            break MISSING_BLOCK_LABEL_81;
        }
        sqlitedatabase.close();
        if (cursor != null)
        {
            cursor.close();
        }
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
        }
        return null;
        boolean flag;
        ArrayList arraylist1;
        flag = cursor.moveToFirst();
        arraylist1 = new ArrayList();
_L2:
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        SkinMenuData skinmenudata;
        String s;
        String s1;
        skinmenudata = new SkinMenuData();
        skinmenudata.sId = cursor.getString(0);
        skinmenudata.sTitle = cursor.getString(1);
        s = cursor.getString(2);
        s1 = cursor.getString(3);
        ArrayList arraylist;
        Exception exception2;
        boolean flag1;
        String s2;
        boolean flag2;
        if (cursor.getInt(4) == 1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        skinmenudata.isDownloaded = flag1;
        s2 = "";
        if (CountdownUtil.isEmpty(s1))
        {
            break MISSING_BLOCK_LABEL_261;
        }
        flag2 = CountdownUtil.isEmpty(s);
        if (flag2)
        {
            break MISSING_BLOCK_LABEL_261;
        }
        try
        {
            String as1[] = (new URL(s)).getPath().split("/");
            s2 = as1[-1 + as1.length];
        }
        catch (MalformedURLException malformedurlexception) { }
        skinmenudata.sIconPath = (new StringBuilder()).append(s1).append("/").append(s2).toString();
        flag = cursor.moveToNext();
        arraylist1.add(skinmenudata);
        if (true) goto _L2; else goto _L1
        exception2;
        arraylist = arraylist1;
_L8:
        if (cursor != null)
        {
            cursor.close();
        }
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
        }
_L4:
        return arraylist;
_L1:
        if (cursor != null)
        {
            cursor.close();
        }
        Exception exception;
        Exception exception1;
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
            arraylist = arraylist1;
        } else
        {
            arraylist = arraylist1;
        }
        if (true) goto _L4; else goto _L3
_L3:
        exception1;
_L6:
        if (cursor != null)
        {
            cursor.close();
        }
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
        }
        throw exception1;
        exception1;
        if (true) goto _L6; else goto _L5
_L5:
        exception;
        arraylist = null;
        if (true) goto _L8; else goto _L7
_L7:
    }

    protected ContentValues getContentValues(SkinMetaData skinmetadata)
    {
        ContentValues contentvalues = new ContentValues();
        boolean flag = skinmetadata.isScaleble;
        int i = 0;
        if (flag)
        {
            i = 1;
        }
        boolean flag1 = skinmetadata.isSetting;
        int j = 0;
        if (flag1)
        {
            j = 1;
        }
        boolean flag2 = skinmetadata.isDownloaded;
        int k = 0;
        if (flag2)
        {
            k = 1;
        }
        boolean flag3 = skinmetadata.isUpdate;
        int l = 0;
        if (flag3)
        {
            l = 1;
        }
        boolean flag4 = skinmetadata.isDelete;
        int i1 = 0;
        if (flag4)
        {
            i1 = 1;
        }
        contentvalues.put("id_name", skinmetadata.sId);
        contentvalues.put("title", skinmetadata.sTitle);
        contentvalues.put("description", skinmetadata.sDescription);
        contentvalues.put("download_url", skinmetadata.sDownloadUrl);
        contentvalues.put("thumbnail_url", skinmetadata.sThumbnailUrl);
        contentvalues.put("icon_url", skinmetadata.sIconUrl);
        contentvalues.put("image_path", skinmetadata.sPath);
        contentvalues.put("count", Integer.valueOf(skinmetadata.nCount));
        contentvalues.put("start_date", skinmetadata.sStartDate);
        contentvalues.put("end_date", skinmetadata.sEndDate);
        contentvalues.put("update_date", skinmetadata.sUpdateDate);
        contentvalues.put("interval", Integer.valueOf(skinmetadata.nInterval));
        contentvalues.put("spaceid", Integer.valueOf(skinmetadata.nSpaceid));
        contentvalues.put("is_scalable", Integer.valueOf(i));
        contentvalues.put("is_setting", Integer.valueOf(j));
        contentvalues.put("is_download", Integer.valueOf(k));
        contentvalues.put("is_update", Integer.valueOf(l));
        contentvalues.put("is_delete", Integer.valueOf(i1));
        return contentvalues;
    }

    protected String getDate()
    {
        Date date = new Date();
        return (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(date);
    }

    public ArrayList getDeleteSkin()
    {
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        sqlitedatabase = getReadableDatabase();
        cursor = null;
        String as[] = {
            "1"
        };
        cursor = sqlitedatabase.query("skin", columns, "is_delete = ?", as, null, null, "id", "1");
        if (cursor != null)
        {
            break MISSING_BLOCK_LABEL_69;
        }
        sqlitedatabase.close();
        if (cursor != null)
        {
            cursor.close();
        }
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
        }
        return null;
        ArrayList arraylist1;
        cursor.moveToFirst();
        arraylist1 = getObject(cursor);
        ArrayList arraylist;
        arraylist = arraylist1;
        if (cursor != null)
        {
            cursor.close();
        }
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
        }
_L2:
        return arraylist;
        Exception exception1;
        exception1;
        if (cursor != null)
        {
            cursor.close();
        }
        arraylist = null;
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
            arraylist = null;
        }
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        if (cursor != null)
        {
            cursor.close();
        }
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
        }
        throw exception;
    }

    protected ArrayList getObject(Cursor cursor)
    {
        boolean flag = cursor.moveToFirst();
        ArrayList arraylist = new ArrayList();
        while (flag) 
        {
            SkinMetaData skinmetadata = new SkinMetaData();
            skinmetadata.sId = cursor.getString(0);
            skinmetadata.sTitle = cursor.getString(1);
            skinmetadata.sDescription = cursor.getString(2);
            skinmetadata.sDownloadUrl = cursor.getString(3);
            skinmetadata.sThumbnailUrl = cursor.getString(4);
            skinmetadata.sIconUrl = cursor.getString(5);
            skinmetadata.sPath = cursor.getString(6);
            skinmetadata.nCount = cursor.getInt(7);
            skinmetadata.sStartDate = cursor.getString(8);
            skinmetadata.sEndDate = cursor.getString(9);
            skinmetadata.sUpdateDate = cursor.getString(10);
            skinmetadata.nInterval = cursor.getInt(11);
            skinmetadata.nSpaceid = cursor.getInt(12);
            boolean flag1;
            boolean flag2;
            boolean flag3;
            boolean flag4;
            boolean flag5;
            if (cursor.getInt(13) == 1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            skinmetadata.isScaleble = flag1;
            if (cursor.getInt(14) == 1)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            skinmetadata.isSetting = flag2;
            if (cursor.getInt(15) == 1)
            {
                flag3 = true;
            } else
            {
                flag3 = false;
            }
            skinmetadata.isDownloaded = flag3;
            if (cursor.getInt(16) == 1)
            {
                flag4 = true;
            } else
            {
                flag4 = false;
            }
            skinmetadata.isUpdate = flag4;
            if (cursor.getInt(17) == 1)
            {
                flag5 = true;
            } else
            {
                flag5 = false;
            }
            skinmetadata.isDelete = flag5;
            CountdownUtil.getSkinPath(skinmetadata);
            flag = cursor.moveToNext();
            arraylist.add(skinmetadata);
        }
        return arraylist;
    }

    public ArrayList getOverSkin()
    {
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        sqlitedatabase = getReadableDatabase();
        cursor = null;
        String as[] = new String[2];
        as[0] = "0";
        as[1] = Integer.toString(CountdownUtil.getNowDay());
        cursor = sqlitedatabase.query("skin", columns, "end_date != ? and end_date < ?", as, null, null, null, null);
        if (cursor != null)
        {
            break MISSING_BLOCK_LABEL_77;
        }
        sqlitedatabase.close();
        if (cursor != null)
        {
            cursor.close();
        }
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
        }
        return null;
        ArrayList arraylist1;
        cursor.moveToFirst();
        arraylist1 = getObject(cursor);
        ArrayList arraylist;
        arraylist = arraylist1;
        if (cursor != null)
        {
            cursor.close();
        }
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
        }
_L2:
        return arraylist;
        Exception exception1;
        exception1;
        if (cursor != null)
        {
            cursor.close();
        }
        arraylist = null;
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
            arraylist = null;
        }
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        if (cursor != null)
        {
            cursor.close();
        }
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
        }
        throw exception;
    }

    public SkinMetaData getSkin()
    {
        SQLiteDatabase sqlitedatabase;
        String as[];
        sqlitedatabase = getReadableDatabase();
        as = (new String[] {
            "1"
        });
        Cursor cursor1 = sqlitedatabase.query("skin", columns, "is_setting = ?", as, null, null, "id", "1");
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
        cursor.moveToFirst();
        ArrayList arraylist = getObject(cursor);
        int i = arraylist.size();
        SkinMetaData skinmetadata = null;
        if (i > 0)
        {
            skinmetadata = (SkinMetaData)arraylist.get(0);
        }
        cursor.close();
        sqlitedatabase.close();
        return skinmetadata;
    }

    public SkinMetaData getSkin(String s)
    {
        SQLiteDatabase sqlitedatabase;
        String as[];
        sqlitedatabase = getReadableDatabase();
        as = (new String[] {
            s
        });
        Cursor cursor1 = sqlitedatabase.query("skin", columns, "id_name = ?", as, null, null, "id", "1");
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
        cursor.moveToFirst();
        ArrayList arraylist = getObject(cursor);
        int i = arraylist.size();
        SkinMetaData skinmetadata = null;
        if (i > 0)
        {
            skinmetadata = (SkinMetaData)arraylist.get(0);
        }
        cursor.close();
        sqlitedatabase.close();
        return skinmetadata;
    }

    public String getSkinId()
    {
        SQLiteDatabase sqlitedatabase;
        String as[];
        String as1[];
        sqlitedatabase = getReadableDatabase();
        as = (new String[] {
            "id_name"
        });
        as1 = (new String[] {
            "1"
        });
        Cursor cursor1 = sqlitedatabase.query("skin", as, "is_setting = ?", as1, null, null, "id", "1");
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
        String s = null;
        if (flag)
        {
            s = cursor.getString(0);
        }
        cursor.close();
        sqlitedatabase.close();
        return s;
    }

    public String getUpdateDate(String s)
    {
        SQLiteDatabase sqlitedatabase;
        String as[];
        sqlitedatabase = getReadableDatabase();
        as = (new String[] {
            s
        });
        Cursor cursor1 = sqlitedatabase.query("skin", columns, "id_name = ?", as, null, null, "id", "1");
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
        cursor.moveToFirst();
        ArrayList arraylist = getObject(cursor);
        int i = arraylist.size();
        String s1 = null;
        if (i > 0)
        {
            s1 = ((SkinMetaData)arraylist.get(0)).sUpdateDate;
            if (CountdownUtil.isEmpty(s1))
            {
                s1 = "0";
            }
        }
        cursor.close();
        sqlitedatabase.close();
        return s1;
    }

    public ArrayList getUpdatedSkin()
    {
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        sqlitedatabase = getReadableDatabase();
        cursor = null;
        String as[] = {
            "1"
        };
        cursor = sqlitedatabase.query("skin", columns, "is_update = ?", as, null, null, null, null);
        if (cursor != null)
        {
            break MISSING_BLOCK_LABEL_65;
        }
        sqlitedatabase.close();
        if (cursor != null)
        {
            cursor.close();
        }
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
        }
        return null;
        ArrayList arraylist1;
        cursor.moveToFirst();
        arraylist1 = getObject(cursor);
        ArrayList arraylist;
        arraylist = arraylist1;
        if (cursor != null)
        {
            cursor.close();
        }
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
        }
_L2:
        return arraylist;
        Exception exception1;
        exception1;
        if (cursor != null)
        {
            cursor.close();
        }
        arraylist = null;
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
            arraylist = null;
        }
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        if (cursor != null)
        {
            cursor.close();
        }
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
        }
        throw exception;
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        createNewTable(sqlitedatabase);
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        if (i == 1)
        {
            upgradeTable(sqlitedatabase);
        }
    }

    public void updateAllDelete()
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        try
        {
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("is_delete", "1");
            sqlitedatabase.update("skin", contentvalues, null, null);
            sqlitedatabase.close();
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        sqlitedatabase.close();
    }

    public void updateDownload(SkinMetaData skinmetadata)
    {
        if (skinmetadata != null) goto _L2; else goto _L1
_L1:
        String s;
        return;
_L2:
        if ((s = skinmetadata.sId) == null || s == "") goto _L1; else goto _L3
_L3:
        Exception exception;
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        int i;
        if (skinmetadata.isDownloaded)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        contentvalues.put("download_url", skinmetadata.sDownloadUrl);
        contentvalues.put("is_download", Integer.valueOf(i));
        try
        {
            sqlitedatabase.update("skin", contentvalues, "id_name=?", new String[] {
                s
            });
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

    public void updateSetting(String s)
    {
        if (s == null || s == "")
        {
            return;
        }
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        try
        {
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("is_setting", "0");
            sqlitedatabase.update("skin", contentvalues, "is_setting = 1 ", null);
            sqlitedatabase.close();
            sqlitedatabase = getWritableDatabase();
            ContentValues contentvalues1 = new ContentValues();
            contentvalues1.put("is_setting", "1");
            sqlitedatabase.update("skin", contentvalues1, "id_name=?", new String[] {
                s
            });
            sqlitedatabase.close();
        }
        catch (Exception exception)
        {
            sqlitedatabase.close();
        }
        TransitUtil.touchRD(context.getString(0x7f0d0562, new Object[] {
            s
        }));
    }

    public void updateSetting(SkinMetaData skinmetadata)
    {
        String s;
        if (skinmetadata != null)
        {
            if ((s = skinmetadata.sId) != null && s != "")
            {
                updateSetting(s);
                return;
            }
        }
    }

    public void updateSkin(SkinMetaData skinmetadata)
    {
        if (skinmetadata != null) goto _L2; else goto _L1
_L1:
        String s;
        return;
_L2:
        if ((s = skinmetadata.sId) == null || s == "") goto _L1; else goto _L3
_L3:
        Exception exception;
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        ContentValues contentvalues = getContentValues(skinmetadata);
        try
        {
            sqlitedatabase.update("skin", contentvalues, "id_name=?", new String[] {
                s
            });
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

    public void updateSkinAuto(SkinMetaData skinmetadata)
    {
        String s;
        if (skinmetadata != null)
        {
            if ((s = skinmetadata.sId) != null && s != "")
            {
                if (getSkin(skinmetadata.sId) == null)
                {
                    addSetting(skinmetadata);
                    return;
                } else
                {
                    updateSkin(skinmetadata);
                    return;
                }
            }
        }
    }
}
