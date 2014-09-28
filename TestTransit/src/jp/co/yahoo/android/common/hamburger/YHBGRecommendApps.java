// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.common.hamburger:
//            YHBGUtils, YSimpleHttpClient, YIOUtils, YHttpGet, 
//            YMD5

public class YHBGRecommendApps
{
    public static class YHambergerEntry
    {

        public Bitmap mBitmap;
        public String mExcludePackages;
        public String mImageUrl;
        public String mMinOs;
        public String mName;
        public String mPackage;
        public String mUrl;

        public int getMinOsAsInt()
        {
            String s;
            int i;
            int j;
            try
            {
                s = mMinOs;
            }
            catch (NumberFormatException numberformatexception)
            {
                return 0;
            }
            i = 0;
            if (s == null)
            {
                break MISSING_BLOCK_LABEL_23;
            }
            j = Integer.parseInt(mMinOs);
            i = j;
            return i;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(128);
            stringbuilder.append((new StringBuilder("Name = ")).append(mName).append("\n").toString());
            stringbuilder.append((new StringBuilder("Url = ")).append(mUrl).append("\n").toString());
            stringbuilder.append((new StringBuilder("ImageUrl = ")).append(mImageUrl).append("\n").toString());
            stringbuilder.append((new StringBuilder("Package = ")).append(mPackage).append("\n").toString());
            stringbuilder.append((new StringBuilder("ExcludePackages = ")).append(mExcludePackages).append("\n").toString());
            stringbuilder.append((new StringBuilder("MinOs = ")).append(mMinOs).append("\n").toString());
            stringbuilder.append((new StringBuilder("Bitmap = ")).append(mBitmap).append("\n").toString());
            return stringbuilder.toString();
        }

        public YHambergerEntry()
        {
        }
    }

    public static class YHambergerModel
    {

        ArrayList mEntries;
        public String mLastModified;

        void addEntry(YHambergerEntry yhambergerentry)
        {
            mEntries.add(yhambergerentry);
        }

        void clear()
        {
            mLastModified = null;
            mEntries.clear();
        }

        YHambergerEntry get(int i)
        {
            return (YHambergerEntry)mEntries.get(i);
        }

        Iterator getEntries()
        {
            return mEntries.iterator();
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(512);
            stringbuilder.append((new StringBuilder("LastModified = ")).append(mLastModified).append("\n").toString());
            stringbuilder.append("YHamburgerEntry:\n");
            Iterator iterator = getEntries();
            do
            {
                if (!iterator.hasNext())
                {
                    return stringbuilder.toString();
                }
                stringbuilder.append(((YHambergerEntry)iterator.next()).toString());
            } while (true);
        }

        public YHambergerModel()
        {
            mEntries = new ArrayList();
        }
    }


    private static Date sLastChecked = null;
    private static final Object sLock = new Object();
    private static final YHambergerModel sModel = new YHambergerModel();
    private final Activity mActivity;
    private String mJsonUrl;

    public YHBGRecommendApps(Activity activity)
    {
        mActivity = activity;
        YSimpleHttpClient.setUserAgent(YHBGUtils.getBrowserUserAgent(activity.getApplicationContext()));
    }

    private boolean deleteFile(String s)
    {
        boolean flag;
        try
        {
            YHBGUtils.debug((new StringBuilder("deleteFile => ")).append(s).toString());
            flag = mActivity.deleteFile(s);
        }
        catch (Exception exception)
        {
            YHBGUtils.debug((new StringBuilder("deleteFile(failed) => ")).append(s).append(" ").append(exception).toString());
            return false;
        }
        return flag;
    }

    private Bitmap getAppImage(String s)
    {
        String s1 = getImageCacheFilename(s);
        java.io.FileInputStream fileinputstream1 = mActivity.openFileInput(s1);
        java.io.FileInputStream fileinputstream;
        boolean flag;
        fileinputstream = fileinputstream1;
        flag = true;
_L12:
        if (!flag) goto _L2; else goto _L1
_L1:
        byte abyte0[];
        YHBGUtils.debug((new StringBuilder("\u753B\u50CF\u306E\u30AD\u30E3\u30C3\u30B7\u30E5\u5229\u7528 => ")).append(s).toString());
        abyte0 = YIOUtils.getByteFromInput(fileinputstream);
_L10:
        Bitmap bitmap = null;
        if (abyte0 == null) goto _L4; else goto _L3
_L3:
        HttpEntity httpentity;
        byte abyte1[];
        Exception exception;
        try
        {
            bitmap = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length);
        }
        catch (IOException ioexception1)
        {
            YHBGUtils.error((new StringBuilder("\u753B\u50CF\u30C7\u30B3\u30FC\u30C9\u306E\u5931\u6557 ")).append(ioexception1).toString());
            return bitmap;
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YHBGUtils.error("OutOfMemoryError");
            return bitmap;
        }
        if (bitmap == null) goto _L6; else goto _L5
_L5:
        if (flag) goto _L4; else goto _L7
_L7:
        YIOUtils.writeToCache(mActivity.openFileOutput(s1, 0), abyte0);
_L4:
        return bitmap;
        exception;
        deleteFile(s1);
        fileinputstream = null;
        flag = false;
        continue; /* Loop/switch isn't completed */
_L2:
        YHBGUtils.debug((new StringBuilder("\u753B\u50CF\u3092\u30EA\u30E2\u30FC\u30C8\u304B\u3089\u53D6\u5F97 => ")).append(s).toString());
        HttpResponse httpresponse = (new YHttpGet()).get(s);
        abyte0 = null;
        if (httpresponse == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        httpentity = httpresponse.getEntity();
        abyte1 = EntityUtils.toByteArray(httpentity);
        abyte0 = abyte1;
        continue; /* Loop/switch isn't completed */
_L6:
        if (!flag) goto _L4; else goto _L8
_L8:
        deleteFile(s1);
        return bitmap;
        IOException ioexception;
        ioexception;
        abyte0 = null;
        if (true) goto _L10; else goto _L9
_L9:
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        fileinputstream = null;
        flag = false;
        if (true) goto _L12; else goto _L11
_L11:
    }

    private void getAppImages()
    {
        Iterator iterator = getHambergerEntries(mActivity.getApplicationContext());
        do
        {
            YHambergerEntry yhambergerentry;
            do
            {
                if (!iterator.hasNext())
                {
                    return;
                }
                yhambergerentry = (YHambergerEntry)iterator.next();
            } while (yhambergerentry.mImageUrl == null);
            YHBGUtils.debug((new StringBuilder("\u95A2\u9023\u30A2\u30D7\u30EA\u753B\u50CF\u306E\u8AAD\u307F\u8FBC\u307F => ")).append(yhambergerentry.mName).toString());
            yhambergerentry.mBitmap = getAppImage(yhambergerentry.mImageUrl);
        } while (true);
    }

    public static Iterator getHambergerEntries(Context context)
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        Iterator iterator;
        String s;
        arraylist = new ArrayList();
        iterator = sModel.getEntries();
        s = context.getPackageName();
_L1:
        Iterator iterator1;
        if (iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_49;
        }
        iterator1 = arraylist.iterator();
        return iterator1;
        YHambergerEntry yhambergerentry = (YHambergerEntry)iterator.next();
        if (!YHBGUtils.contains(YHBGUtils.parseCsvAndTrim(yhambergerentry.mExcludePackages), s) && android.os.Build.VERSION.SDK_INT >= yhambergerentry.getMinOsAsInt())
        {
            arraylist.add(yhambergerentry);
        }
          goto _L1
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static Iterator getHambergerEntriesAll()
    {
        Iterator iterator;
        synchronized (sLock)
        {
            iterator = sModel.getEntries();
        }
        return iterator;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static YHambergerModel getHambergerModel()
    {
        YHambergerModel yhambergermodel;
        synchronized (sLock)
        {
            yhambergermodel = sModel;
        }
        return yhambergermodel;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private String getImageCacheFilename(String s)
    {
        return (new StringBuilder("hbg__")).append(YMD5.crypt(s)).toString();
    }

    private String getJsonCacheFilename()
    {
        return (new StringBuilder("hbg__")).append(getJsonFilename()).toString();
    }

    private String getJsonFilename()
    {
        return (new StringBuilder("android_")).append(mActivity.getPackageName()).append(".json").toString();
    }

    private String getJsonFromRemote()
    {
        YHttpGet yhttpget;
        String s;
        HttpResponse httpresponse;
        yhttpget = new YHttpGet();
        s = getJsonUrl();
        httpresponse = null;
        Header header1;
        httpresponse = yhttpget.get(s);
        header1 = httpresponse.getFirstHeader("Date");
        Header header = header1;
_L2:
        String s1 = null;
        if (httpresponse != null)
        {
            s1 = null;
            if (header != null)
            {
                Date date = YHBGUtils.parseDate(header.getValue());
                s1 = null;
                if (date != null)
                {
                    if (!YHBGUtils.checkDatesInRange(date, 0x36ee80L))
                    {
                        break; /* Loop/switch isn't completed */
                    }
                    HttpEntity httpentity = httpresponse.getEntity();
                    Exception exception;
                    try
                    {
                        s1 = YIOUtils.readFromStream(httpentity.getContent());
                        YHBGUtils.debug("\u30B5\u30FC\u30D0\u30FC\u304B\u3089\u53D6\u5F97OK");
                    }
                    catch (ParseException parseexception)
                    {
                        return s1;
                    }
                    catch (IOException ioexception)
                    {
                        return s1;
                    }
                }
            }
        }
        return s1;
        exception;
        YHBGUtils.error((new StringBuilder("\u30EA\u30E2\u30FC\u30C8\u306EJSON\u53D6\u5F97\u5931\u6557 ")).append(exception).toString());
        header = null;
        if (true) goto _L2; else goto _L1
_L1:
        YHBGUtils.error("\u30B5\u30FC\u30D0\u30FC\u3068\u306E\u6642\u9593\u304C1\u6642\u9593\u4EE5\u4E0A\u305A\u308C\u3066\u3044\u307E\u3059");
        return null;
    }

    private Date getJsonSavedDate()
    {
        Long long1 = Long.valueOf(mActivity.getSharedPreferences("__hbg__", 0).getLong("json_saved_time", -1L));
        if (long1 != null && long1.longValue() != -1L)
        {
            return new Date(long1.longValue());
        } else
        {
            return null;
        }
    }

    private boolean parseJson(String s)
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        JSONArray jsonarray;
        YHBGUtils.debug((new StringBuilder("JSON:\n")).append(s).toString());
        JSONObject jsonobject = new JSONObject(s);
        sModel.clear();
        jsonarray = jsonobject.getJSONArray("app_info_items");
        int i = 0;
_L1:
        if (i < jsonarray.length())
        {
            break MISSING_BLOCK_LABEL_100;
        }
        YHBGUtils.debug((new StringBuilder("--->\n")).append(sModel.toString()).append("<----\n").toString());
        obj;
        JVM INSTR monitorexit ;
        return true;
        JSONObject jsonobject1 = jsonarray.getJSONObject(i);
        YHambergerEntry yhambergerentry = new YHambergerEntry();
        yhambergerentry.mName = jsonobject1.getString("name");
        yhambergerentry.mUrl = jsonobject1.getString("url");
        if (!yhambergerentry.mName.equals("\u3082\u3063\u3068\u898B\u308B"))
        {
            yhambergerentry.mImageUrl = jsonobject1.getString("imageurl");
            yhambergerentry.mPackage = jsonobject1.getString("package");
            yhambergerentry.mExcludePackages = jsonobject1.getString("exclude_packages");
            yhambergerentry.mMinOs = jsonobject1.getString("min_os");
        }
        sModel.addEntry(yhambergerentry);
        i++;
          goto _L1
        Exception exception1;
        exception1;
        sModel.clear();
        deleteFile(getJsonCacheFilename());
        YHBGUtils.error((new StringBuilder("JSON\u89E3\u6790\u6642\u306E\u30A8\u30E9\u30FC ")).append(exception1).toString());
        obj;
        JVM INSTR monitorexit ;
        return false;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void putJsonSavedDate()
    {
        android.content.SharedPreferences.Editor editor = mActivity.getSharedPreferences("__hbg__", 0).edit();
        editor.putLong("json_saved_time", (new Date()).getTime());
        editor.commit();
    }

    private void saveJson(String s)
    {
        String s1 = getJsonFilename();
        try
        {
            mActivity.openFileOutput((new StringBuilder("hbg__")).append(s1).toString(), 0).write(s.getBytes());
            putJsonSavedDate();
            return;
        }
        catch (IOException ioexception)
        {
            YHBGUtils.error((new StringBuilder("saveJson ")).append(ioexception).toString());
            return;
        }
        catch (Exception exception)
        {
            YHBGUtils.error((new StringBuilder("saveJson ")).append(exception).toString());
        }
    }

    public void clearCacheFiles()
    {
        String as[] = mActivity.fileList();
        int i = as.length;
        int j = 0;
        do
        {
            if (j >= i)
            {
                return;
            }
            String s = as[j];
            if (s.startsWith("hbg__"))
            {
                YHBGUtils.debug((new StringBuilder("\u30AD\u30E3\u30C3\u30B7\u30E5\u30D5\u30A1\u30A4\u30EB\u306E\u524A\u9664 => ")).append(s).toString());
                deleteFile(s);
            }
            j++;
        } while (true);
    }

    public String getJsonUrl()
    {
        if (mJsonUrl == null)
        {
            return (new StringBuilder("http://apn.smpapp.yahoo.co.jp/hbg/android/")).append(getJsonFilename()).toString();
        } else
        {
            return mJsonUrl;
        }
    }

    public void setJsonUrl(String s)
    {
        if (YHBGUtils.isDebuggable(mActivity))
        {
            mJsonUrl = s;
        }
    }

    public void updateIfNecessary(Runnable runnable)
    {
        updateIfNecessary(runnable, false);
    }

    public void updateIfNecessary(final Runnable doneFunc, final boolean notUseCache)
    {
        if (!YSimpleHttpClient.isNetworkAvailable(mActivity))
        {
            YHBGUtils.debug("\u30AA\u30D5\u30E9\u30A4\u30F3\u3067\u3059\u3002\u30B9\u30AD\u30C3\u30D7\u3002");
            return;
        } else
        {
            (new Thread(new Runnable() {

                final YHBGRecommendApps this$0;
                private final Runnable val$doneFunc;
                private final boolean val$notUseCache;

                public void run()
                {
                    AssetManager assetmanager;
                    String s;
                    boolean flag;
                    String s1;
                    boolean flag1;
                    if (notUseCache && !YHBGUtils.isOvertime(YHBGRecommendApps.sLastChecked, 0x493e0L))
                    {
                        YHBGUtils.debug("\u524D\u56DE\u30C1\u30A7\u30C3\u30AF\u3057\u3066\u304B\u30895\u5206\u4EE5\u5185\u3067\u3059\u3002");
                        doneFunc.run();
                        return;
                    }
                    YHBGRecommendApps.sLastChecked = new Date();
                    assetmanager = mActivity.getResources().getAssets();
                    s = getJsonFilename();
                    flag = YHBGUtils.isDebuggable(mActivity);
                    s1 = null;
                    flag1 = false;
                    if (!flag)
                    {
                        break MISSING_BLOCK_LABEL_124;
                    }
                    s1 = YIOUtils.readFromStream(assetmanager.open(s));
                    YHBGUtils.debug((new StringBuilder("assets\u306B\u3042\u308B ")).append(s).append(" \u3092\u5229\u7528").toString());
                    flag1 = true;
_L6:
                    if (flag1) goto _L2; else goto _L1
_L1:
                    Date date = getJsonSavedDate();
                    YHBGUtils.debug((new StringBuilder("\u524D\u56DEJSON \u30AD\u30E3\u30C3\u30B7\u30E5\u4FDD\u5B58\u65E5\u6642 => ")).append(date).toString());
                    if (flag && notUseCache || YHBGUtils.isOvertime(date, 0x5265c00L))
                    {
                        break MISSING_BLOCK_LABEL_220;
                    }
                    YHBGUtils.debug("\u30AD\u30E3\u30C3\u30B7\u30E5\u6E08\u307F\u306EJSON\u30D5\u30A1\u30A4\u30EB\u3092\u53D6\u5F97");
                    String s2 = YIOUtils.readFromStream(mActivity.openFileInput((new StringBuilder("hbg__")).append(s).toString()));
                    s1 = s2;
_L4:
                    if (s1 == null)
                    {
                        YHBGUtils.debug((new StringBuilder("\u30EA\u30E2\u30FC\u30C8\u306E ")).append(s).append(" \u3092\u5229\u7528").toString());
                        s1 = getJsonFromRemote();
                        saveJson(s1);
                    }
_L2:
                    parseJson(s1);
                    getAppImages();
                    doneFunc.run();
                    return;
                    FileNotFoundException filenotfoundexception1;
                    filenotfoundexception1;
                    YHBGUtils.debug((new StringBuilder("assets\u306B ")).append(s).append(" \u306A\u3057").toString());
                    flag1 = false;
                    continue; /* Loop/switch isn't completed */
                    FileNotFoundException filenotfoundexception;
                    filenotfoundexception;
                    if (true) goto _L4; else goto _L3
_L3:
                    Exception exception;
                    exception;
                    flag1 = false;
                    if (true) goto _L6; else goto _L5
_L5:
                }

            
            {
                this$0 = YHBGRecommendApps.this;
                notUseCache = flag;
                doneFunc = runnable;
                super();
            }
            })).start();
            return;
        }
    }










}
