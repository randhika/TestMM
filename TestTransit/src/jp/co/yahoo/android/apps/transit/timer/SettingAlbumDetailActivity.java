// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import jp.co.yahoo.android.apps.transit.common.BackgroundWorker;
import jp.co.yahoo.android.apps.transit.common.ThumbnailManager;
import jp.co.yahoo.android.apps.transit.common.util.FileUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.timer.api.data.SkinMetaData;
import jp.co.yahoo.android.apps.transit.viewparts.CustomDialogTitle;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownBaseActivity

public class SettingAlbumDetailActivity extends CountdownBaseActivity
    implements android.widget.AdapterView.OnItemClickListener
{
    public class AlbumListAdapter extends BaseAdapter
    {

        private Context context;
        private ArrayList listPath;
        final SettingAlbumDetailActivity this$0;

        public int getCount()
        {
            return listPath.size();
        }

        public volatile Object getItem(int i)
        {
            return getItem(i);
        }

        public String getItem(int i)
        {
            return (String)listPath.get(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            ImageView imageview;
            File file;
            android.graphics.Bitmap bitmap;
            ThumbnailManager thumbnailmanager;
            if (view == null)
            {
                imageview = new ImageView(context);
                imageview.setLayoutParams(new android.widget.AbsListView.LayoutParams(columnWidth, columnWidth));
            } else
            {
                imageview = (ImageView)view;
            }
            file = new File(getItem(i));
            bitmap = BitmapFactory.decodeResource(getResources(), 0x7f020078);
            thumbnailmanager = ThumbnailManager.getInstance();
            thumbnailmanager.setImageSize(columnWidth);
            thumbnailmanager.loadImage(file, imageview, bitmap, 0);
            return imageview;
        }

        public AlbumListAdapter(Context context1, ArrayList arraylist)
        {
            this$0 = SettingAlbumDetailActivity.this;
            super();
            context = context1;
            listPath = arraylist;
        }
    }


    private AlbumListAdapter adapter;
    private int columnWidth;
    private ArrayList listId;
    private ArrayList listPath;
    private GridView listView;
    private ProgressDialog m_progDialog;
    private SkinMetaData skinData;
    private TextView txtMessage;

    public SettingAlbumDetailActivity()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03009e);
        setTitle(getString(0x7f0d04e8));
        txtMessage = (TextView)findViewById(0x7f0a01f4);
        listView = (GridView)findViewById(0x7f0a02eb);
        skinData = (SkinMetaData)getIntent().getSerializableExtra(getString(0x7f0d0236));
        m_progDialog = new ProgressDialog(this);
        m_progDialog.setCustomTitle((new CustomDialogTitle(this, getString(0x7f0d04aa), 0)).setIconInfo());
        m_progDialog.setMessage(getString(0x7f0d04f4));
        m_progDialog.setIndeterminate(true);
        m_progDialog.setCancelable(true);
        m_progDialog.show();
        BackgroundWorker.post(new jp.co.yahoo.android.apps.transit.common.BackgroundWorker.WorkerListener() {

            final SettingAlbumDetailActivity this$0;
            final Activity val$activity;

            public boolean doInBackground()
            {
                long l = System.currentTimeMillis();
                jp.co.yahoo.android.apps.transit.common.util.FileUtil.MediaData mediadata = FileUtil.getDirectoryImageData(activity, true, skinData.sDownloadUrl);
                listPath = mediadata.listPath;
                listId = mediadata.listId;
                float f = TransitUtil.dpToPx(activity, 10F);
                float f1 = TransitUtil.dpToPx(activity, 100F);
                float f2 = (float)TransitUtil.getWindowDisplayWidth(activity) - f;
                int i = (int)(f2 / (f + f1));
                if (i < 3)
                {
                    i = 3;
                }
                columnWidth = (int)(0.5F + (f2 / (float)i - f));
                long l1 = System.currentTimeMillis();
                long l2 = 0L;
                if (l1 - l < 500L)
                {
                    l2 = 500L - (l1 - l);
                }
                if (l2 > 0L)
                {
                    try
                    {
                        Thread.sleep(l2);
                    }
                    catch (InterruptedException interruptedexception) { }
                }
                return true;
            }

            public void onPostExecute()
            {
                if (listId == null || listId.isEmpty())
                {
                    txtMessage.setVisibility(0);
                } else
                {
                    adapter = new AlbumListAdapter(activity.getApplicationContext(), listPath);
                    listView.setColumnWidth(columnWidth);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(SettingAlbumDetailActivity.this);
                }
                if (m_progDialog == null || !m_progDialog.isShowing())
                {
                    break MISSING_BLOCK_LABEL_67;
                }
                m_progDialog.dismiss();
                return;
                IllegalArgumentException illegalargumentexception;
                illegalargumentexception;
            }

            
            {
                this$0 = SettingAlbumDetailActivity.this;
                activity = activity1;
                super();
            }
        });
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        Intent intent;
        File file = new File(adapter.getItem(i));
        if (!file.exists())
        {
            break MISSING_BLOCK_LABEL_64;
        }
        String s = FileUtil.getMimeType(file, "image/*");
        intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(file), s);
        startActivity(intent);
        return;
        Exception exception;
        exception;
    }

    public void onStart()
    {
        super.onStart();
        dispAd(this, "2080340009", "pv");
    }

    public void selectAlbum(View view)
    {
        Intent intent = new Intent();
        intent.putExtra(getString(0x7f0d0236), skinData);
        setResult(-1, intent);
        finish();
    }




/*
    static ArrayList access$102(SettingAlbumDetailActivity settingalbumdetailactivity, ArrayList arraylist)
    {
        settingalbumdetailactivity.listPath = arraylist;
        return arraylist;
    }

*/



/*
    static ArrayList access$202(SettingAlbumDetailActivity settingalbumdetailactivity, ArrayList arraylist)
    {
        settingalbumdetailactivity.listId = arraylist;
        return arraylist;
    }

*/



/*
    static int access$302(SettingAlbumDetailActivity settingalbumdetailactivity, int i)
    {
        settingalbumdetailactivity.columnWidth = i;
        return i;
    }

*/




/*
    static AlbumListAdapter access$502(SettingAlbumDetailActivity settingalbumdetailactivity, AlbumListAdapter albumlistadapter)
    {
        settingalbumdetailactivity.adapter = albumlistadapter;
        return albumlistadapter;
    }

*/


}
