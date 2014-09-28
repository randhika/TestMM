// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import jp.co.yahoo.android.apps.transit.common.ThumbnailManager;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            SettingAlbumListActivity

public class inflater extends BaseAdapter
{
    class ViewHolder
    {

        ImageView imgSelect;
        ImageView imgThumb;
        final SettingAlbumListActivity.AlbumListAdapter this$1;
        TextView txtDesc;
        TextView txtNum;
        TextView txtTitle;

        ViewHolder()
        {
            this$1 = SettingAlbumListActivity.AlbumListAdapter.this;
            super();
        }
    }


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
        if (SettingAlbumListActivity.access$600(SettingAlbumListActivity.this) != null && SettingAlbumListActivity.access$600(SettingAlbumListActivity.this).equals(file.getPath()))
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

    public ViewHolder.this._cls1(Context context1, ArrayList arraylist, HashMap hashmap)
    {
        this$0 = SettingAlbumListActivity.this;
        super();
        context = context1;
        listDir = arraylist;
        mapPath = hashmap;
        inflater = (LayoutInflater)context1.getSystemService("layout_inflater");
    }
}
