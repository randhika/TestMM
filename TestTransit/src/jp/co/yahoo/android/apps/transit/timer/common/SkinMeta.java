// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer.common;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.timer.api.FileDownloader;
import jp.co.yahoo.android.apps.transit.timer.api.SkinSearch;
import jp.co.yahoo.android.apps.transit.timer.api.data.SkinMetaData;
import jp.co.yahoo.android.apps.transit.timer.db.SkinDb;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;
import jp.co.yahoo.android.yolp.common.ApiBase;

public class SkinMeta
{

    private Context context;
    private SkinDb db;
    private boolean isShowDialog;
    private jp.co.yahoo.android.yolp.common.ApiBase.ApiListener listener;
    private int nDpi;
    private SkinSearch objSearch;
    private String sDirPath;

    public SkinMeta(Context context1)
    {
        db = null;
        objSearch = null;
        nDpi = 320;
        sDirPath = "";
        isShowDialog = false;
        context = context1;
        db = new SkinDb(context1);
        sDirPath = (new File(context1.getFilesDir().getAbsolutePath())).getPath();
    }

    public SkinMeta(Context context1, int i)
    {
        this(context1);
        nDpi = i;
    }

    private String getData()
    {
        objSearch = new SkinSearch(context);
        objSearch.setDisplayDpi(nDpi);
        objSearch.executeAsync(new jp.co.yahoo.android.yolp.common.ApiBase.ApiListener() {

            final SkinMeta this$0;

            public boolean endApi(ApiBase apibase, Object obj)
            {
                updateDB();
                if (listener != null)
                {
                    listener.endApi(apibase, obj);
                }
                return false;
            }

            
            {
                this$0 = SkinMeta.this;
                super();
            }
        }, isShowDialog);
        return "";
    }

    private void updateDB()
    {
        ArrayList arraylist = objSearch.getSkin();
        if (arraylist != null && arraylist.size() >= 1)
        {
            int i = db.count();
            if (i > 1 && i < arraylist.size())
            {
                SharedPreferences sharedpreferences = context.getSharedPreferences(context.getString(0x7f0d01a0), 0);
                if (!sharedpreferences.getBoolean(context.getString(0x7f0d0237), false))
                {
                    android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putBoolean(context.getString(0x7f0d0237), true);
                    editor.commit();
                }
            }
            db.updateAllDelete();
            int j = 0;
            while (j < arraylist.size()) 
            {
                SkinMetaData skinmetadata = db.getSkin(((SkinMetaData)arraylist.get(j)).sId);
                SkinMetaData skinmetadata1 = (SkinMetaData)arraylist.get(j);
                if (skinmetadata != null)
                {
                    skinmetadata1.isDownloaded = skinmetadata.isDownloaded;
                    skinmetadata1.isSetting = skinmetadata.isSetting;
                    skinmetadata1.sPath = skinmetadata.sPath;
                    if (skinmetadata1.sId.equals(context.getString(0x7f0d04e5)))
                    {
                        skinmetadata1.sDownloadUrl = skinmetadata.sDownloadUrl;
                    }
                }
                if (skinmetadata != null && skinmetadata.sUpdateDate != null && skinmetadata1.sUpdateDate != null && skinmetadata.sUpdateDate.compareTo(skinmetadata1.sUpdateDate) < 0)
                {
                    skinmetadata1.isUpdate = true;
                    skinmetadata1.isDownloaded = false;
                }
                skinmetadata1.isDelete = false;
                skinmetadata1.sPath = (new StringBuilder()).append(sDirPath).append("/").append(skinmetadata1.sId).toString();
                CountdownUtil.getSkinPath(skinmetadata1);
                if (!(new File(skinmetadata1.sThumbnailPath)).exists())
                {
                    getThumbnail(skinmetadata1);
                }
                if (!(new File(skinmetadata1.sIconPath)).exists())
                {
                    getIcon(skinmetadata1);
                }
                db.updateSkinAuto(skinmetadata1);
                j++;
            }
        }
    }

    public boolean deleteData(ArrayList arraylist, boolean flag)
    {
        boolean flag1 = true;
        if (arraylist == null || arraylist.size() < flag1)
        {
            flag1 = false;
        } else
        {
            int i = 0;
            while (i < arraylist.size()) 
            {
                SkinMetaData skinmetadata = (SkinMetaData)arraylist.get(i);
                if (!skinmetadata.sId.equals(context.getString(0x7f0d04f6)))
                {
                    String s;
                    if (flag)
                    {
                        s = (new StringBuilder()).append(skinmetadata.sPath).append("/").append(context.getString(0x7f0d04ea)).toString();
                    } else
                    {
                        s = skinmetadata.sPath;
                    }
                    if (skinmetadata.isDownloaded && !CountdownUtil.isEmpty(s))
                    {
                        try
                        {
                            CountdownUtil.delete(new File(s));
                        }
                        catch (Exception exception)
                        {
                            exception.printStackTrace();
                        }
                    }
                    if (skinmetadata.isSetting)
                    {
                        db.updateSetting(context.getString(0x7f0d04f6));
                        skinmetadata.isDownloaded = false;
                        db.updateSkin(skinmetadata);
                    }
                    if (!flag)
                    {
                        db.deleteData(skinmetadata);
                    }
                }
                i++;
            }
        }
        return flag1;
    }

    public void deleteSkin()
    {
        deleteData(db.getDeleteSkin(), false);
        deleteData(db.getOverSkin(), false);
        deleteData(db.getUpdatedSkin(), true);
    }

    public void getIcon(SkinMetaData skinmetadata)
    {
        CountdownUtil.createSkinDirectry(skinmetadata.sPath);
        File file = new File(skinmetadata.sIconPath);
        (new FileDownloader(skinmetadata.sIconUrl, file)).execute(new Void[0]);
    }

    public void getThumbnail(SkinMetaData skinmetadata)
    {
        CountdownUtil.createSkinDirectry(skinmetadata.sPath);
        File file = new File(skinmetadata.sThumbnailPath);
        (new FileDownloader(skinmetadata.sThumbnailUrl, file)).execute(new Void[0]);
    }

    public void setShowDialog(boolean flag, jp.co.yahoo.android.yolp.common.ApiBase.ApiListener apilistener)
    {
        isShowDialog = flag;
        listener = apilistener;
    }

    public void update()
    {
        getData();
    }


}
