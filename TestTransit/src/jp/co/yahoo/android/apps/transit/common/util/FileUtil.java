// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class FileUtil
{
    public static class MediaData
    {

        public ArrayList listDir;
        public ArrayList listId;
        public ArrayList listPath;
        public HashMap mapId;
        public HashMap mapPath;

        public MediaData()
        {
        }
    }


    private static final String IMAGE_DATA = "_data";
    private static final String IMAGE_ID = "_id";

    public FileUtil()
    {
    }

    public static MediaData getAllImageData(Activity activity, boolean flag)
    {
        return getDirectoryImageData(activity, flag, null);
    }

    public static MediaData getDirectoryImageData(Activity activity, boolean flag, String s)
    {
        ArrayList arraylist;
        ArrayList arraylist1;
        ArrayList arraylist2;
        HashMap hashmap;
        HashMap hashmap1;
        String s2;
        long l;
        arraylist = new ArrayList();
        arraylist1 = new ArrayList();
        arraylist2 = new ArrayList();
        hashmap = new HashMap();
        hashmap1 = new HashMap();
        android.net.Uri uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String as[] = {
            "_data", "_id"
        };
        String s1;
        String as1[];
        ContentResolver contentresolver;
        Cursor cursor;
        MediaData mediadata;
        int i;
        int j;
        boolean flag1;
        if (TextUtils.isEmpty(s))
        {
            s1 = null;
            as1 = null;
        } else
        {
            s1 = "_data LIKE ?";
            as1 = new String[1];
            as1[0] = (new StringBuilder()).append(s).append("/%").toString();
        }
        contentresolver = activity.getContentResolver();
        cursor = null;
        if (uri != null)
        {
            cursor = contentresolver.query(uri, as, s1, as1, "date_modified DESC");
        }
        if (cursor == null || cursor.getCount() <= 0) goto _L2; else goto _L1
_L1:
        i = cursor.getColumnIndexOrThrow("_data");
        j = cursor.getColumnIndexOrThrow("_id");
        cursor.moveToFirst();
_L5:
        s2 = cursor.getString(i);
        l = cursor.getLong(j);
        if (!TextUtils.isEmpty(s2)) goto _L4; else goto _L3
_L3:
        flag1 = cursor.moveToNext();
        if (flag1) goto _L5; else goto _L2
_L2:
        if (cursor != null)
        {
            cursor.close();
        }
        mediadata = new MediaData();
        mediadata.listPath = arraylist;
        mediadata.listDir = arraylist1;
        mediadata.listId = arraylist2;
        mediadata.mapPath = hashmap;
        mediadata.mapId = hashmap1;
        return mediadata;
_L4:
        File file = (new File(s2)).getParentFile();
        if (file == null) goto _L3; else goto _L6
_L6:
        if (!TextUtils.isEmpty(s) && !s.equals(file.getAbsolutePath())) goto _L3; else goto _L7
_L7:
        arraylist.add(s2);
        arraylist2.add(Long.valueOf(l));
        if (flag) goto _L9; else goto _L8
_L8:
        if (isHiddenPath(s2)) goto _L3; else goto _L9
_L9:
        if (!arraylist1.contains(file)) goto _L11; else goto _L10
_L10:
        ((ArrayList)hashmap.get(file)).add(s2);
        ((ArrayList)hashmap1.get(file)).add(Long.valueOf(l));
          goto _L3
_L11:
        if (!file.exists()) goto _L3; else goto _L12
_L12:
        arraylist1.add(file);
        ArrayList arraylist3 = new ArrayList();
        arraylist3.add(s2);
        hashmap.put(file, arraylist3);
        ArrayList arraylist4 = new ArrayList();
        arraylist4.add(Long.valueOf(l));
        hashmap1.put(file, arraylist4);
          goto _L3
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
          goto _L2
    }

    public static String getExtension(File file)
    {
        if (file == null)
        {
            return "";
        } else
        {
            return getExtension(file.getName());
        }
    }

    public static String getExtension(String s)
    {
        int i = s.lastIndexOf(".");
        if (i != -1 && i != 0) goto _L2; else goto _L1
_L2:
        String s2 = s.substring(i + 1);
        String s1 = s2;
_L3:
        return s1.toLowerCase();
        IndexOutOfBoundsException indexoutofboundsexception;
        indexoutofboundsexception;
        s1 = "";
        if (true) goto _L3; else goto _L1
_L1:
        return "";
    }

    public static String getMimeType(File file)
    {
        if (file == null)
        {
            return "";
        } else
        {
            return getMimeType(file, "");
        }
    }

    public static String getMimeType(File file, String s)
    {
        if (file != null && !file.isDirectory()) goto _L2; else goto _L1
_L1:
        String s1 = "";
_L4:
        return s1;
_L2:
        String s2;
        s2 = getExtension(file);
        if (s2.length() <= 0)
        {
            break; /* Loop/switch isn't completed */
        }
        s1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(s2);
        if (!TextUtils.isEmpty(s1)) goto _L4; else goto _L3
_L3:
        if (!TextUtils.isEmpty(s2))
        {
            if (s2.equalsIgnoreCase("flv"))
            {
                return "video/x-flv";
            }
            if (s2.equalsIgnoreCase("mkv"))
            {
                return "video/x-matroska";
            }
        }
        return s;
    }

    private static boolean isHiddenPath(String s)
    {
        while (TextUtils.isEmpty(s) || !s.contains("/.")) 
        {
            return false;
        }
        return true;
    }
}
