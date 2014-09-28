// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

// Referenced classes of package jp.co.yahoo.android.common:
//            YSoftHashMap, YImageDownloader, YImageDownloadItem, YImageDownloadListener, 
//            YLogger

public class YImageDownloadManager
{

    static final YSoftHashMap CACHE = new YSoftHashMap();
    public static boolean DEBUG = false;
    private static final YImageDownloadManager INSTANCE = new YImageDownloadManager();
    private static final int MAX_DOWNLOADER_NUM = 3;
    private static final LinkedList _q = new LinkedList();
    private final ArrayList _downloaders = new ArrayList();

    private YImageDownloadManager()
    {
    }

    public static void clearCache()
    {
        CACHE.clear();
    }

    public static Drawable getCacheEntry(String s)
    {
        return (Drawable)CACHE.get(s);
    }

    public static YImageDownloadManager getInstance()
    {
        return INSTANCE;
    }

    public static boolean hasCacheEntry(String s)
    {
        return CACHE.containsKey(s);
    }

    private boolean isDownloadingUrl(String s)
    {
        for (int i = 0; i < _downloaders.size(); i++)
        {
            YImageDownloader yimagedownloader = (YImageDownloader)_downloaders.get(i);
            if (yimagedownloader.isDownloading() && yimagedownloader.getItem().getUrl().equals(s))
            {
                return true;
            }
        }

        return false;
    }

    public static Drawable removeCacheEntry(String s)
    {
        return (Drawable)CACHE.remove(s);
    }

    private void startDownload(YImageDownloadItem yimagedownloaditem)
    {
        if (!isDownloadingUrl(yimagedownloaditem.getUrl()))
        {
            YImageDownloader yimagedownloader = new YImageDownloader(this);
            _downloaders.add(yimagedownloader);
            if (!yimagedownloader.isCancelled())
            {
                yimagedownloaditem.getListener().onStarted(yimagedownloaditem);
            }
            yimagedownloader.execute(new YImageDownloadItem[] {
                yimagedownloaditem
            });
        }
    }

    public void cancelDownload(String s)
    {
        for (int i = 0; i < _downloaders.size(); i++)
        {
            YImageDownloader yimagedownloader = (YImageDownloader)_downloaders.get(i);
            if (yimagedownloader.isDownloading() && yimagedownloader.getItem().getUrl().equals(s))
            {
                yimagedownloader.cancel(true);
            }
        }

        ListIterator listiterator = _q.listIterator(0);
        do
        {
            if (!listiterator.hasNext())
            {
                break;
            }
            if (!((YImageDownloadItem)listiterator.next()).getUrl().equals(s))
            {
                continue;
            }
            if (DEBUG)
            {
                YLogger.log((new StringBuilder()).append("In Queue removed: ").append(s).toString());
            }
            listiterator.remove();
            break;
        } while (true);
    }

    void removeDownloader(YImageDownloader yimagedownloader)
    {
        _downloaders.remove(yimagedownloader);
    }

    void requestAnother()
    {
        do
        {
            if (_downloaders.size() >= 3 || _q.isEmpty())
            {
                return;
            }
            startDownload((YImageDownloadItem)_q.remove());
        } while (true);
    }

    public void requestDownload(String s, YImageDownloadListener yimagedownloadlistener)
    {
        requestDownload(s, yimagedownloadlistener, null);
    }

    public void requestDownload(String s, YImageDownloadListener yimagedownloadlistener, Object obj)
    {
        if (_downloaders.size() < 3)
        {
            startDownload(new YImageDownloadItem(s, yimagedownloadlistener, obj));
            return;
        } else
        {
            _q.add(new YImageDownloadItem(s, yimagedownloadlistener, obj));
            return;
        }
    }

    static 
    {
        DEBUG = false;
    }
}
