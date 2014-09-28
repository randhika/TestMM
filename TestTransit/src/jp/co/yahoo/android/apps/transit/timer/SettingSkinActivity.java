// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.common.BackgroundWorker;
import jp.co.yahoo.android.apps.transit.common.ThumbnailManager;
import jp.co.yahoo.android.apps.transit.common.util.FileUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.timer.api.FileDownloader;
import jp.co.yahoo.android.apps.transit.timer.api.data.SkinMetaData;
import jp.co.yahoo.android.apps.transit.timer.common.SkinMeta;
import jp.co.yahoo.android.apps.transit.timer.db.SkinDb;
import jp.co.yahoo.android.apps.transit.timer.util.CountdownUtil;
import jp.co.yahoo.android.apps.transit.viewparts.CustomDialogTitle;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;
import jp.co.yahoo.android.yolp.common.ApiBase;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownBaseActivity, SettingAlbumListActivity

public class SettingSkinActivity extends CountdownBaseActivity
    implements android.view.View.OnClickListener, android.view.View.OnLongClickListener
{

    private ArrayList arySkin;
    private SkinDb db;
    private FileDownloader fileLoader;
    private ArrayList listPath;
    private LinearLayout lySkin;
    private SkinMetaData objDownload;
    private ProgressDialog progressDialog;
    private Handler progressHandler;
    private int reqCode;
    private String settingId;
    private TextView txtMessage;

    public SettingSkinActivity()
    {
        reqCode = 0;
        lySkin = null;
        txtMessage = null;
        db = null;
        arySkin = null;
        fileLoader = null;
    }

    private void downloadFile(SkinMetaData skinmetadata)
    {
        objDownload = skinmetadata;
        createProgresDialog();
        progressDialog.setProgress(0);
        progressHandler.sendEmptyMessage(0);
        File file = new File((new StringBuilder()).append(skinmetadata.sPath).append("/").append(getString(0x7f0d04ea)).toString());
        if (!file.exists())
        {
            file.mkdir();
        }
        File file1 = new File(file, "data.zip");
        fileLoader = new FileDownloader(skinmetadata.sDownloadUrl, file1);
        fileLoader.execute(new Void[0]);
    }

    private void showDeleteDialog(final SkinMetaData objItem)
    {
        (new TransitDialogBuilder(this)).setTitleWarnning(objItem.sTitle).setMessage(getString(0x7f0d0352)).setNegativeButton(getString(0x7f0d0071), new android.content.DialogInterface.OnClickListener() {

            final SettingSkinActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.cancel();
            }

            
            {
                this$0 = SettingSkinActivity.this;
                super();
            }
        }).setPositiveButton(getString(0x7f0d0069), new android.content.DialogInterface.OnClickListener() {

            final SettingSkinActivity this$0;
            final SkinMetaData val$objItem;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                try
                {
                    CountdownUtil.delete(new File((new StringBuilder()).append(objItem.sPath).append("/").append(getString(0x7f0d04ea)).toString()));
                    objItem.isDownloaded = false;
                    if (objItem.isSetting)
                    {
                        objItem.isSetting = false;
                        db.updateSetting(getString(0x7f0d04f6));
                    }
                    db.updateSkin(objItem);
                }
                catch (Exception exception)
                {
                    showSimpleErrorMessageDialog(getString(0x7f0d04eb));
                    exception.printStackTrace();
                }
                showSkinSetting();
            }

            
            {
                this$0 = SettingSkinActivity.this;
                objItem = skinmetadata;
                super();
            }
        }).show().setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

            final SettingSkinActivity this$0;

            public void onCancel(DialogInterface dialoginterface)
            {
            }

            
            {
                this$0 = SettingSkinActivity.this;
                super();
            }
        });
    }

    private void showDownloadDialog(final SkinMetaData objItem)
    {
        TransitDialogBuilder transitdialogbuilder = (new TransitDialogBuilder(this)).setTitleInfo(objItem.sTitle);
        String s = getString(0x7f0d04cf);
        Object aobj[] = new Object[1];
        aobj[0] = objItem.sTitle;
        transitdialogbuilder.setMessage(String.format(s, aobj)).setNegativeButton(getString(0x7f0d0071), new android.content.DialogInterface.OnClickListener() {

            final SettingSkinActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.cancel();
            }

            
            {
                this$0 = SettingSkinActivity.this;
                super();
            }
        }).setPositiveButton(getString(0x7f0d006a), new android.content.DialogInterface.OnClickListener() {

            final SettingSkinActivity this$0;
            final SkinMetaData val$objItem;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.dismiss();
                downloadFile(objItem);
            }

            
            {
                this$0 = SettingSkinActivity.this;
                objItem = skinmetadata;
                super();
            }
        }).show().setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

            final SettingSkinActivity this$0;

            public void onCancel(DialogInterface dialoginterface)
            {
            }

            
            {
                this$0 = SettingSkinActivity.this;
                super();
            }
        });
    }

    private void showSkinSetting()
    {
        int i;
        arySkin = db.getAllSkin();
        lySkin.removeAllViews();
        i = 0;
_L7:
        if (i >= arySkin.size()) goto _L2; else goto _L1
_L1:
        final SkinMetaData albumSkin;
        TextView textview;
        TextView textview1;
        ImageView imageview;
        LinearLayout linearlayout = (LinearLayout)inflater.inflate(0x7f030079, null);
        albumSkin = (SkinMetaData)arySkin.get(i);
        textview = (TextView)linearlayout.findViewById(0x7f0a004d);
        textview1 = (TextView)linearlayout.findViewById(0x7f0a0131);
        final TextView albumDescSub = (TextView)linearlayout.findViewById(0x7f0a0213);
        final ImageView albumThumbnail = (ImageView)linearlayout.findViewById(0x7f0a0211);
        imageview = (ImageView)linearlayout.findViewById(0x7f0a026c);
        ImageView imageview1 = (ImageView)linearlayout.findViewById(0x7f0a0212);
        ImageView imageview2;
        if (albumSkin.sId.equals(getString(0x7f0d04e5)) && albumSkin.isDownloaded)
        {
            textview.setText((new File(albumSkin.sDownloadUrl)).getName());
            textview1.setText(albumSkin.sDescription);
            albumDescSub.setVisibility(0);
            BackgroundWorker.post(new jp.co.yahoo.android.apps.transit.common.BackgroundWorker.WorkerListener() {

                final SettingSkinActivity this$0;
                final Activity val$activity;
                final TextView val$albumDescSub;
                final SkinMetaData val$albumSkin;
                final ImageView val$albumThumbnail;

                public boolean doInBackground()
                {
                    jp.co.yahoo.android.apps.transit.common.util.FileUtil.MediaData mediadata = FileUtil.getDirectoryImageData(activity, true, albumSkin.sDownloadUrl);
                    listPath = mediadata.listPath;
                    return true;
                }

                public void onPostExecute()
                {
                    ArrayList arraylist = listPath;
                    int j = 0;
                    if (arraylist != null)
                    {
                        boolean flag = listPath.isEmpty();
                        j = 0;
                        if (!flag)
                        {
                            j = listPath.size();
                            File file = new File((String)listPath.get(0));
                            android.graphics.Bitmap bitmap = BitmapFactory.decodeFile(albumSkin.sThumbnailPath);
                            ThumbnailManager thumbnailmanager = ThumbnailManager.getInstance();
                            thumbnailmanager.setImageSize((int)TransitUtil.dpToPx(activity, 100F));
                            thumbnailmanager.loadImage(file, albumThumbnail, bitmap, 0);
                        }
                    }
                    albumDescSub.setText((new StringBuilder()).append(j).append(activity.getString(0x7f0d04e6)).toString());
                }

            
            {
                this$0 = SettingSkinActivity.this;
                activity = activity1;
                albumSkin = skinmetadata;
                albumThumbnail = imageview;
                albumDescSub = textview;
                super();
            }
            });
        } else
        {
            textview.setText(albumSkin.sTitle);
            textview1.setText(albumSkin.sDescription);
            if (CountdownUtil.isEmpty(albumSkin.sThumbnailPath))
            {
                albumThumbnail.setImageResource(0x7f020230);
            } else
            {
                try
                {
                    albumThumbnail.setImageBitmap(BitmapFactory.decodeFile(albumSkin.sThumbnailPath));
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        }
        if (!albumSkin.isSetting) goto _L4; else goto _L3
_L3:
        imageview1.setVisibility(0);
        if (settingId != null && settingId.equals(albumSkin.sId))
        {
            setResult(0);
        } else
        {
            Intent intent = new Intent();
            intent.putExtra(getString(0x7f0d01c5), albumSkin.sId);
            setResult(-1, intent);
        }
_L5:
        linearlayout.setTag(albumSkin);
        lySkin.addView(linearlayout);
        imageview2 = (ImageView)inflater.inflate(0x7f030059, null);
        lySkin.addView(imageview2);
        linearlayout.setOnClickListener(this);
        linearlayout.setOnLongClickListener(this);
        i++;
        continue; /* Loop/switch isn't completed */
_L4:
        if (!albumSkin.isDownloaded)
        {
            if (!albumSkin.sId.equals(getString(0x7f0d04e5)))
            {
                imageview.setVisibility(0);
            }
            textview.setTextColor(getResources().getColor(0x7f090067));
            textview1.setTextColor(getResources().getColor(0x7f090067));
        }
        if (true) goto _L5; else goto _L2
_L2:
        if (arySkin.size() > 1 && txtMessage.getVisibility() == 8)
        {
            txtMessage.setVisibility(0);
        }
        return;
        if (true) goto _L7; else goto _L6
_L6:
    }

    protected void cancelLoad()
    {
        if (fileLoader != null)
        {
            fileLoader.cancel(true);
        }
    }

    protected void createProgresDialog()
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCustomTitle(new CustomDialogTitle(this, getString(0x7f0d04f3), 0));
        progressDialog.setProgressStyle(1);
        progressDialog.setButton(-2, getString(0x7f0d0071), new android.content.DialogInterface.OnClickListener() {

            final SettingSkinActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                cancelLoad();
            }

            
            {
                this$0 = SettingSkinActivity.this;
                super();
            }
        });
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public void launchCountDown(View view)
    {
        if (reqCode == getResources().getInteger(0x7f0c005c))
        {
            finish();
            return;
        } else
        {
            startCountDown();
            return;
        }
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (-1 == j && getResources().getInteger(0x7f0c005a) == i)
        {
            SkinMetaData skinmetadata = (SkinMetaData)intent.getSerializableExtra(getString(0x7f0d0236));
            skinmetadata.isDownloaded = true;
            db.updateDownload(skinmetadata);
            db.updateSetting(skinmetadata);
            showSkinSetting();
        }
    }

    public void onClick(View view)
    {
        SkinMetaData skinmetadata = (SkinMetaData)view.getTag();
        if (skinmetadata.sId.equals(getString(0x7f0d04e5)))
        {
            Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/timer/SettingAlbumListActivity);
            intent.putExtra(getString(0x7f0d0236), skinmetadata);
            startActivityForResult(intent, getResources().getInteger(0x7f0c005a));
        } else
        if (!skinmetadata.isSetting)
        {
            if (skinmetadata.isDownloaded)
            {
                skinmetadata.isSetting = true;
                db.updateSetting(skinmetadata);
                showSkinSetting();
                return;
            } else
            {
                showDownloadDialog(skinmetadata);
                return;
            }
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f0300a1);
        setTitle(getString(0x7f0d04ce));
        reqCode = getIntent().getIntExtra(getString(0x7f0d01df), 0);
        lySkin = (LinearLayout)findViewById(0x7f0a02f1);
        txtMessage = (TextView)findViewById(0x7f0a01f4);
        db = new SkinDb(this);
        settingId = db.getSkinId();
        (new SkinMeta(this)).deleteSkin();
        progressHandler = new Handler() {

            final SettingSkinActivity this$0;

            public void handleMessage(Message message)
            {
                super.handleMessage(message);
                if (fileLoader.isErorr())
                {
                    progressDialog.dismiss();
                    if (objDownload != null)
                    {
                        String s2 = (new StringBuilder()).append(objDownload.sPath).append("/").append(getString(0x7f0d04ea)).append("/data.zip").toString();
                        try
                        {
                            CountdownUtil.delete(new File(s2));
                        }
                        catch (Exception exception2)
                        {
                            exception2.printStackTrace();
                        }
                    }
                    showErrorMessageDialog(getString(0x7f0d04ec), getString(0x7f0d015e));
                } else
                {
                    if (fileLoader.isCancelled())
                    {
                        progressDialog.dismiss();
                        return;
                    }
                    if (fileLoader.getStatus() == android.os.AsyncTask.Status.FINISHED)
                    {
                        progressDialog.dismiss();
                        if (objDownload != null)
                        {
                            String s = (new StringBuilder()).append(objDownload.sPath).append("/").append(getString(0x7f0d04ea)).toString();
                            String s1 = CountdownUtil.extract((new StringBuilder()).append(s).append("/").append("data.zip").toString(), (new StringBuilder()).append(s).append("/").toString(), true);
                            try
                            {
                                CountdownUtil.delete(new File((new StringBuilder()).append(s).append("/").append("data.zip").toString()));
                            }
                            catch (Exception exception)
                            {
                                exception.printStackTrace();
                            }
                            if (s1 == null)
                            {
                                try
                                {
                                    CountdownUtil.delete(new File(s));
                                }
                                catch (Exception exception1)
                                {
                                    exception1.printStackTrace();
                                }
                                showErrorMessageDialog(getString(0x7f0d04ee), getString(0x7f0d015e));
                                return;
                            } else
                            {
                                objDownload.isDownloaded = true;
                                db.updateSkin(objDownload);
                                db.updateSetting(objDownload);
                                objDownload = null;
                                showSkinSetting();
                                return;
                            }
                        }
                    } else
                    {
                        int i = fileLoader.getLoadedBytePercent();
                        progressDialog.setProgress(i);
                        progressHandler.sendEmptyMessageDelayed(0, 100L);
                        return;
                    }
                }
            }

            
            {
                this$0 = SettingSkinActivity.this;
                super();
            }
        };
        if (db.count() <= 1)
        {
            DisplayMetrics displaymetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            SkinMeta skinmeta = new SkinMeta(this, displaymetrics.densityDpi);
            skinmeta.setShowDialog(true, new jp.co.yahoo.android.yolp.common.ApiBase.ApiListener() {

                final SettingSkinActivity this$0;

                public boolean endApi(ApiBase apibase, Object obj)
                {
                    showSkinSetting();
                    return false;
                }

            
            {
                this$0 = SettingSkinActivity.this;
                super();
            }
            });
            skinmeta.update();
            return;
        } else
        {
            showSkinSetting();
            return;
        }
    }

    public boolean onLongClick(View view)
    {
        SkinMetaData skinmetadata;
        for (skinmetadata = (SkinMetaData)view.getTag(); skinmetadata.sId.equals(getString(0x7f0d04f6)) || skinmetadata.sId.equals(getString(0x7f0d04e5)) || !skinmetadata.isDownloaded;)
        {
            return false;
        }

        showDeleteDialog(skinmetadata);
        return true;
    }

    protected void onRestart()
    {
        ArrayList arraylist;
        int i;
        super.onRestart();
        arraylist = db.getAllSkin();
        i = 0;
_L10:
        int j;
        boolean flag;
        j = arraylist.size();
        flag = false;
        if (i >= j) goto _L2; else goto _L1
_L1:
        SkinMetaData skinmetadata;
        int k;
        skinmetadata = (SkinMetaData)arraylist.get(i);
        k = 0;
_L8:
        int l;
        SkinMetaData skinmetadata1;
        l = arySkin.size();
        skinmetadata1 = null;
        if (k >= l) goto _L4; else goto _L3
_L3:
        SkinMetaData skinmetadata2 = (SkinMetaData)arySkin.get(k);
        if (!skinmetadata2.sId.equals(skinmetadata.sId)) goto _L6; else goto _L5
_L5:
        skinmetadata1 = skinmetadata2;
_L4:
        if (skinmetadata1 == null)
        {
            flag = true;
        } else
        if (!skinmetadata1.equals(skinmetadata))
        {
            flag = true;
        } else
        {
label0:
            {
                if (!skinmetadata1.sId.equals(getString(0x7f0d04e5)))
                {
                    break label0;
                }
                if (CountdownUtil.isEmpty(skinmetadata1.sDownloadUrl) != CountdownUtil.isEmpty(skinmetadata.sDownloadUrl))
                {
                    flag = true;
                } else
                if (!CountdownUtil.isEmpty(skinmetadata1.sDownloadUrl) && !skinmetadata1.sDownloadUrl.equals(skinmetadata.sDownloadUrl))
                {
                    flag = true;
                } else
                {
                    if (CountdownUtil.isEmpty(skinmetadata.sDownloadUrl) || skinmetadata.sDownloadUrl.equals(skinmetadata1.sDownloadUrl))
                    {
                        break label0;
                    }
                    flag = true;
                }
            }
        }
_L2:
        if (flag)
        {
            settingId = db.getSkinId();
            showSkinSetting();
        }
        return;
_L6:
        k++;
        if (true) goto _L8; else goto _L7
_L7:
        i++;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public void onStart()
    {
        super.onStart();
        dispAd(this, "2080340006", "pv");
    }





/*
    static SkinMetaData access$202(SettingSkinActivity settingskinactivity, SkinMetaData skinmetadata)
    {
        settingskinactivity.objDownload = skinmetadata;
        return skinmetadata;
    }

*/






/*
    static ArrayList access$602(SettingSkinActivity settingskinactivity, ArrayList arraylist)
    {
        settingskinactivity.listPath = arraylist;
        return arraylist;
    }

*/

}
