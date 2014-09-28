// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import jp.co.yahoo.android.apps.transit.common.BackgroundWorker;
import jp.co.yahoo.android.apps.transit.common.ThumbnailManager;
import jp.co.yahoo.android.apps.transit.common.util.FileUtil;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.timer.api.data.SkinMetaData;
import jp.co.yahoo.android.apps.transit.viewparts.CustomDialogTitle;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            CountdownBaseActivity, SettingAlbumDetailActivity

public class SettingAlbumListActivity extends CountdownBaseActivity
    implements android.widget.AdapterView.OnItemClickListener
{
    public class AlbumListAdapter extends BaseAdapter
    {

        private Context context;
        private ViewHolder holder;
        private LayoutInflater inflater;
        private ArrayList listDir;
        private HashMap mapPath;
        final SettingAlbumListActivity this$0;

        public int getCount()
        {
            return listDir.size();
        }

        public File getItem(int i)
        {
            return (File)listDir.get(i);
        }

        public volatile Object getItem(int i)
        {
            return getItem(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            File file;
            if (view == null)
            {
                view = inflater.inflate(0x7f030065, viewgroup, false);
                holder = new ViewHolder();
                holder.imgThumb = (ImageView)view.findViewById(0x7f0a0211);
                holder.imgSelect = (ImageView)view.findViewById(0x7f0a0212);
                holder.txtTitle = (TextView)view.findViewById(0x7f0a004d);
                holder.txtNum = (TextView)view.findViewById(0x7f0a0213);
                holder.txtDesc = (TextView)view.findViewById(0x7f0a0131);
                view.setTag(holder);
            } else
            {
                holder = (ViewHolder)view.getTag();
            }
            file = getItem(i);
            if (file == null)
            {
                holder.imgThumb.setImageResource(0);
                holder.imgSelect.setVisibility(8);
                holder.txtTitle.setText("");
                holder.txtNum.setText("");
                holder.txtDesc.setText("");
                return view;
            }
            ArrayList arraylist;
            if (settingPath != null && settingPath.equals(file.getPath()))
            {
                holder.imgSelect.setVisibility(0);
            } else
            {
                holder.imgSelect.setVisibility(8);
            }
            holder.txtTitle.setText(file.getName());
            holder.txtTitle.requestLayout();
            holder.txtDesc.setText(file.getPath());
            arraylist = (ArrayList)mapPath.get(file);
            if (arraylist == null || arraylist.isEmpty())
            {
                holder.imgThumb.setImageResource(0);
                holder.txtNum.setText((new StringBuilder()).append(0).append(context.getString(0x7f0d04e6)).toString());
                return view;
            } else
            {
                holder.txtNum.setText((new StringBuilder()).append(arraylist.size()).append(context.getString(0x7f0d04e6)).toString());
                File file1 = new File((String)arraylist.get(0));
                android.graphics.Bitmap bitmap = BitmapFactory.decodeResource(getResources(), 0x7f020078);
                ThumbnailManager thumbnailmanager = ThumbnailManager.getInstance();
                thumbnailmanager.setImageSize((int)TransitUtil.dpToPx(context, 100F));
                thumbnailmanager.loadImage(file1, holder.imgThumb, bitmap, 0);
                return view;
            }
        }

        public AlbumListAdapter(Context context1, ArrayList arraylist, HashMap hashmap)
        {
            this$0 = SettingAlbumListActivity.this;
            super();
            context = context1;
            listDir = arraylist;
            mapPath = hashmap;
            inflater = (LayoutInflater)context1.getSystemService("layout_inflater");
        }
    }

    class AlbumListAdapter.ViewHolder
    {

        ImageView imgSelect;
        ImageView imgThumb;
        final AlbumListAdapter this$1;
        TextView txtDesc;
        TextView txtNum;
        TextView txtTitle;

        AlbumListAdapter.ViewHolder()
        {
            this$1 = AlbumListAdapter.this;
            super();
        }
    }


    private AlbumListAdapter adapter;
    private ArrayList listDir;
    private ListView listView;
    private ProgressDialog m_progDialog;
    private HashMap mapPath;
    private String settingPath;
    private SkinMetaData skinData;
    private TextView txtMessage;

    public SettingAlbumListActivity()
    {
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (-1 == j && getResources().getInteger(0x7f0c005a) == i)
        {
            setResult(-1, intent);
            finish();
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03009f);
        setTitle(getString(0x7f0d04e8));
        txtMessage = (TextView)findViewById(0x7f0a01f4);
        listView = (ListView)findViewById(0x7f0a02eb);
        skinData = (SkinMetaData)getIntent().getSerializableExtra(getString(0x7f0d0236));
        settingPath = skinData.sDownloadUrl;
        m_progDialog = new ProgressDialog(this);
        m_progDialog.setCustomTitle((new CustomDialogTitle(this, getString(0x7f0d04aa), 0)).setIconInfo());
        m_progDialog.setMessage(getString(0x7f0d04f4));
        m_progDialog.setIndeterminate(true);
        m_progDialog.setCancelable(true);
        m_progDialog.show();
        BackgroundWorker.post(new jp.co.yahoo.android.apps.transit.common.BackgroundWorker.WorkerListener() {

            final SettingAlbumListActivity this$0;
            final Activity val$activity;

            public boolean doInBackground()
            {
                long l = System.currentTimeMillis();
                jp.co.yahoo.android.apps.transit.common.util.FileUtil.MediaData mediadata = FileUtil.getAllImageData(activity, true);
                listDir = mediadata.listDir;
                mapPath = mediadata.mapPath;
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
                    catch (InterruptedException interruptedexception)
                    {
                        return true;
                    }
                }
                return true;
            }

            public void onPostExecute()
            {
                if (mapPath == null || mapPath.isEmpty())
                {
                    txtMessage.setVisibility(0);
                } else
                {
                    adapter = new AlbumListAdapter(activity.getApplicationContext(), listDir, mapPath);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(SettingAlbumListActivity.this);
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
                this$0 = SettingAlbumListActivity.this;
                activity = activity1;
                super();
            }
        });
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        File file = adapter.getItem(i);
        if (file != null)
        {
            skinData.sDownloadUrl = file.getPath();
            Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/timer/SettingAlbumDetailActivity);
            intent.putExtra(getString(0x7f0d0236), skinData);
            startActivityForResult(intent, getResources().getInteger(0x7f0c005a));
        }
    }

    public void onStart()
    {
        super.onStart();
        dispAd(this, "2080340008", "pv");
    }



/*
    static ArrayList access$002(SettingAlbumListActivity settingalbumlistactivity, ArrayList arraylist)
    {
        settingalbumlistactivity.listDir = arraylist;
        return arraylist;
    }

*/



/*
    static HashMap access$102(SettingAlbumListActivity settingalbumlistactivity, HashMap hashmap)
    {
        settingalbumlistactivity.mapPath = hashmap;
        return hashmap;
    }

*/




/*
    static AlbumListAdapter access$302(SettingAlbumListActivity settingalbumlistactivity, AlbumListAdapter albumlistadapter)
    {
        settingalbumlistactivity.adapter = albumlistadapter;
        return albumlistadapter;
    }

*/



}
