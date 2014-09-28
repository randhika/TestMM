// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

// Referenced classes of package jp.co.yahoo.android.common:
//            YDownloader, YDownloadItem, YDownloadListener, YLogger

public class YDownloadManager
{

    private static final YDownloadManager INSTANCE = new YDownloadManager();
    private static int MAX_DOWNLOADER_NUM = 3;
    private static int MAX_DOWNLOAD_SIZE = 0x300000;
    private static int _connectTimeout = 0;
    private static final LinkedList _q = new LinkedList();
    private static int _readTimeout = 0;
    private final ArrayList _downloaders = new ArrayList();
    private final Object _lock = new Object();
    final Handler mHandler = new Handler() {

        final YDownloadManager this$0;

        public void handleMessage(Message message)
        {
            YDownloadItem ydownloaditem = (YDownloadItem)message.obj;
            ydownloaditem.getListener().onCancelled(ydownloaditem);
        }

            
            {
                this$0 = YDownloadManager.this;
                super();
            }
    };

    private YDownloadManager()
    {
    }

    public static YDownloadManager getInstance()
    {
        return INSTANCE;
    }

    private boolean isDownloadingNow(String s)
    {
        for (int i = 0; i < _downloaders.size(); i++)
        {
            YDownloader ydownloader = (YDownloader)_downloaders.get(i);
            if (ydownloader.isDownloading() && ydownloader.getItem().getUrl().equals(s))
            {
                return true;
            }
        }

        return false;
    }

    private boolean isExistInQue(String s)
    {
label0:
        {
            synchronized (_lock)
            {
                ListIterator listiterator = _q.listIterator(0);
                do
                {
                    if (!listiterator.hasNext())
                    {
                        break label0;
                    }
                } while (!((YDownloadItem)listiterator.next()).getUrl().equals(s));
            }
            return true;
        }
        obj;
        JVM INSTR monitorexit ;
        return false;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void startDownload(YDownloadItem ydownloaditem)
    {
        if (!isDownloadingNow(ydownloaditem.getUrl()))
        {
            YDownloader ydownloader = new YDownloader(this);
            if (_connectTimeout != 0)
            {
                ydownloader.setConnectTimeout(_connectTimeout);
            }
            if (_readTimeout != 0)
            {
                ydownloader.setReadTimeout(_readTimeout);
            }
            _downloaders.add(ydownloader);
            if (!ydownloader.isCancelled())
            {
                ydownloaditem.getListener().onStarted(ydownloaditem);
            }
            ydownloader.execute(new YDownloadItem[] {
                ydownloaditem
            });
        }
    }

    public void cancelDownload(String s)
    {
        Object obj = _lock;
        obj;
        JVM INSTR monitorenter ;
        ListIterator listiterator = _q.listIterator(0);
        do
        {
            if (!listiterator.hasNext())
            {
                break;
            }
            YDownloadItem ydownloaditem = (YDownloadItem)listiterator.next();
            if (ydownloaditem.getUrl().equals(s))
            {
                YLogger.log((new StringBuilder()).append("In Queue removed: ").append(s).toString());
                Message message = mHandler.obtainMessage(0, ydownloaditem);
                mHandler.sendMessage(message);
                listiterator.remove();
            }
        } while (true);
        break MISSING_BLOCK_LABEL_109;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        obj;
        JVM INSTR monitorexit ;
        for (int i = 0; i < _downloaders.size(); i++)
        {
            YDownloader ydownloader = (YDownloader)_downloaders.get(i);
            if (ydownloader.isDownloading() && ydownloader.getItem().getUrl().equals(s))
            {
                YLogger.log((new StringBuilder()).append("In Task removed: ").append(s).toString());
                ydownloader.cancel(true);
            }
        }

        return;
    }

    public void cancelDownloadAll()
    {
        Object obj = _lock;
        obj;
        JVM INSTR monitorenter ;
        for (ListIterator listiterator = _q.listIterator(0); listiterator.hasNext(); listiterator.remove())
        {
            YDownloadItem ydownloaditem = (YDownloadItem)listiterator.next();
            YLogger.log((new StringBuilder()).append("In Queue removed: ").append(ydownloaditem.getUrl()).toString());
            Message message = mHandler.obtainMessage(0, ydownloaditem);
            mHandler.sendMessage(message);
        }

        break MISSING_BLOCK_LABEL_97;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        obj;
        JVM INSTR monitorexit ;
        for (int i = 0; i < _downloaders.size(); i++)
        {
            ((YDownloader)_downloaders.get(i)).cancel(true);
        }

        _q.clear();
        _downloaders.clear();
        return;
    }

    public int getConnectTimeout()
    {
        return _connectTimeout;
    }

    public int getMaxDownloadSize()
    {
        return MAX_DOWNLOAD_SIZE;
    }

    public int getMaxDownloaderNum()
    {
        return MAX_DOWNLOADER_NUM;
    }

    public int getReadTimeout()
    {
        return _readTimeout;
    }

    public boolean isReadyUrl(String s)
    {
        while (isExistInQue(s) || isDownloadingNow(s)) 
        {
            return true;
        }
        return false;
    }

    void removeDownloader(YDownloader ydownloader)
    {
        _downloaders.remove(ydownloader);
    }

    void requestAnother()
    {
_L2:
label0:
        {
            if (_downloaders.size() >= MAX_DOWNLOADER_NUM)
            {
                break; /* Loop/switch isn't completed */
            }
            synchronized (_lock)
            {
                if (!_q.isEmpty())
                {
                    break label0;
                }
            }
            return;
        }
        startDownload((YDownloadItem)_q.remove());
        obj;
        JVM INSTR monitorexit ;
        if (true) goto _L2; else goto _L1
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
_L1:
    }

    public void requestDownload(String s, YDownloadListener ydownloadlistener)
    {
        requestDownload(s, ydownloadlistener, null, null);
    }

    public void requestDownload(String s, YDownloadListener ydownloadlistener, Object obj)
    {
        requestDownload(s, ydownloadlistener, null, obj);
    }

    public void requestDownload(String s, YDownloadListener ydownloadlistener, Map map, Object obj)
    {
        if (_downloaders.size() < MAX_DOWNLOADER_NUM)
        {
            startDownload(new YDownloadItem(s, ydownloadlistener, map, obj));
            return;
        }
        synchronized (_lock)
        {
            _q.add(new YDownloadItem(s, ydownloadlistener, map, obj));
        }
        return;
        exception;
        obj1;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setConnectTimeout(int i)
    {
        _connectTimeout = i;
    }

    public void setMaxDownloadSize(int i)
    {
        MAX_DOWNLOAD_SIZE = i;
    }

    public void setMaxDownloaderNum(int i)
    {
        MAX_DOWNLOADER_NUM = i;
    }

    public void setReadTimeout(int i)
    {
        _readTimeout = i;
    }

}
