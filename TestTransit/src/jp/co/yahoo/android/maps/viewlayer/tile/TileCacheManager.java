// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer.tile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentLinkedQueue;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer.tile:
//            TileRequest

public class TileCacheManager extends Thread
{
    private class TileCacheFileWrapper
    {

        private boolean m_error;
        private File m_file;
        private long m_last_access_time;
        private String m_name;
        private long m_size;
        final TileCacheManager this$0;

        private Bitmap loadCache(TileRequest tilerequest)
        {
            this;
            JVM INSTR monitorenter ;
            Bitmap bitmap1;
            m_last_access_time = System.currentTimeMillis();
            m_file.setLastModified(m_last_access_time);
            bitmap1 = BitmapFactory.decodeFile(m_file.getPath());
            Bitmap bitmap = bitmap1;
_L2:
            this;
            JVM INSTR monitorexit ;
            return bitmap;
            Exception exception1;
            exception1;
            exception1.printStackTrace();
            m_error = true;
            deleteCache();
            bitmap = null;
            if (true) goto _L2; else goto _L1
_L1:
            Exception exception;
            exception;
            throw exception;
        }

        public void deleteCache()
        {
            this;
            JVM INSTR monitorenter ;
            if (m_file != null)
            {
                m_file.delete();
                m_file = null;
            }
_L2:
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception1;
            exception1;
            exception1.printStackTrace();
            m_error = true;
            if (true) goto _L2; else goto _L1
_L1:
            Exception exception;
            exception;
            throw exception;
        }

        public long getLastAccessTime()
        {
            return m_last_access_time;
        }

        public String getName()
        {
            return m_name;
        }

        public long getSize()
        {
            return m_size;
        }

        public boolean isError()
        {
            return m_error;
        }

        public void saveCache(TileRequest tilerequest, byte abyte0[])
        {
            this;
            JVM INSTR monitorenter ;
            FileOutputStream fileoutputstream = new FileOutputStream(m_file);
            fileoutputstream.write(abyte0);
            fileoutputstream.close();
            m_last_access_time = System.currentTimeMillis();
            m_size = m_file.length();
_L2:
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception1;
            exception1;
            exception1.printStackTrace();
            m_error = true;
            if (true) goto _L2; else goto _L1
_L1:
            Exception exception;
            exception;
            throw exception;
        }


        public TileCacheFileWrapper(File file, boolean flag)
        {
            this$0 = TileCacheManager.this;
            super();
            m_file = null;
            m_name = null;
            m_last_access_time = 0L;
            m_error = false;
            m_size = 0L;
            m_file = file;
            m_name = file.getName();
            if (flag)
            {
                m_last_access_time = System.currentTimeMillis();
                m_size = m_file.length();
            }
        }
    }

    public static interface TileCacheListener
    {

        public abstract boolean endTileCacheLoad(Bitmap bitmap, TileRequest tilerequest);
    }


    private static final StringBuilder m_wk_filename = new StringBuilder();
    private static Comparator s_sort_comp = new Comparator() {

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((TileCacheFileWrapper)obj, (TileCacheFileWrapper)obj1);
        }

        public int compare(TileCacheFileWrapper tilecachefilewrapper, TileCacheFileWrapper tilecachefilewrapper1)
        {
            return (int)(tilecachefilewrapper1.getLastAccessTime() - tilecachefilewrapper.getLastAccessTime());
        }

    };
    private final Object lock = new Object();
    private Hashtable m_cacheFiles;
    private File m_cache_dir;
    private int m_cache_size;
    private Context m_context;
    private TileCacheListener m_listener;
    private boolean m_removing;
    private final ConcurrentLinkedQueue m_request_tiles = new ConcurrentLinkedQueue();
    private boolean m_threadRun;

    public TileCacheManager(Context context, TileCacheListener tilecachelistener)
    {
        super("TileCacheManager");
        m_listener = null;
        m_context = null;
        m_cache_size = 0x1e8480;
        m_cache_dir = null;
        m_removing = false;
        m_cacheFiles = null;
        m_threadRun = false;
        m_context = context;
        m_listener = tilecachelistener;
        File file = new File(m_context.getFilesDir(), "yj_androidsdk");
        if (!file.exists())
        {
            file.mkdir();
        }
        m_cache_dir = new File(file, "cache_tiles");
        if (!m_cache_dir.exists())
        {
            m_cache_dir.mkdir();
        }
        initCacheFiles();
        m_threadRun = true;
        start();
    }

    private void initCacheFiles()
    {
        File afile[] = m_cache_dir.listFiles();
        m_cacheFiles = new Hashtable();
        int i = 0;
        do
        {
            if (i >= afile.length)
            {
                return;
            }
            File file = afile[i];
            TileCacheFileWrapper tilecachefilewrapper = new TileCacheFileWrapper(file, true);
            m_cacheFiles.put(file.getName(), tilecachefilewrapper);
            i++;
        } while (true);
    }

    public static String makeFileName(TileRequest tilerequest)
    {
        String s;
        synchronized (m_wk_filename)
        {
            m_wk_filename.delete(0, m_wk_filename.length());
            m_wk_filename.append(tilerequest.getMapType()).append("_").append(tilerequest.getTileX()).append("_").append(tilerequest.getTileY()).append("_").append(tilerequest.getTileZ()).append("_").append(tilerequest.getIndoorLevel());
            if (tilerequest.getStyle() != null)
            {
                m_wk_filename.append("_").append(tilerequest.getStyle().hashCode());
            }
            m_wk_filename.append("_").append(tilerequest.getSeamless());
            s = m_wk_filename.toString();
        }
        return s;
        exception;
        stringbuilder;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void removeCaches()
    {
        this;
        JVM INSTR monitorenter ;
        TileCacheFileWrapper atilecachefilewrapper[];
        m_removing = true;
        atilecachefilewrapper = (TileCacheFileWrapper[])m_cacheFiles.values().toArray(new TileCacheFileWrapper[0]);
        Arrays.sort(atilecachefilewrapper, s_sort_comp);
        long l;
        int i;
        l = 0L;
        i = 0;
_L2:
        if (i < atilecachefilewrapper.length)
        {
            break MISSING_BLOCK_LABEL_54;
        }
        m_removing = false;
        this;
        JVM INSTR monitorexit ;
        return;
        TileCacheFileWrapper tilecachefilewrapper;
        tilecachefilewrapper = atilecachefilewrapper[i];
        if (tilecachefilewrapper.isError())
        {
            m_cacheFiles.remove(tilecachefilewrapper.getName());
            tilecachefilewrapper.deleteCache();
            break MISSING_BLOCK_LABEL_133;
        }
        l += tilecachefilewrapper.getSize();
        if (l > (long)m_cache_size)
        {
            m_cacheFiles.remove(tilecachefilewrapper.getName());
            tilecachefilewrapper.deleteCache();
        }
        break MISSING_BLOCK_LABEL_133;
        Exception exception;
        exception;
        throw exception;
        i++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void clearAll()
    {
        this;
        JVM INSTR monitorenter ;
        File afile[] = m_cache_dir.listFiles();
        int i = 0;
_L2:
        if (i < afile.length)
        {
            break MISSING_BLOCK_LABEL_32;
        }
        m_cacheFiles = new Hashtable();
        this;
        JVM INSTR monitorexit ;
        return;
        afile[i].delete();
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public boolean existsCache(TileRequest tilerequest)
    {
        return m_cacheFiles.containsKey(tilerequest.getCacheFileName());
    }

    public void requestCache(TileRequest tilerequest)
    {
        m_request_tiles.add(tilerequest);
        synchronized (lock)
        {
            lock.notify();
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void run()
    {
_L3:
        if (m_threadRun) goto _L2; else goto _L1
_L1:
        return;
_L2:
        TileRequest tilerequest;
        tilerequest = (TileRequest)m_request_tiles.poll();
        if (tilerequest == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        Bitmap bitmap1 = ((TileCacheFileWrapper)m_cacheFiles.get(tilerequest.getCacheFileName())).loadCache(tilerequest);
        Bitmap bitmap = bitmap1;
_L5:
        m_listener.endTileCacheLoad(bitmap, tilerequest);
          goto _L3
        if (!m_threadRun) goto _L1; else goto _L4
_L4:
        synchronized (lock)
        {
            lock.wait();
        }
          goto _L2
        exception;
        obj;
        JVM INSTR monitorexit ;
        try
        {
            throw exception;
        }
        catch (InterruptedException interruptedexception)
        {
            interruptedexception.printStackTrace();
        }
          goto _L2
        Exception exception1;
        exception1;
        exception1.printStackTrace();
        bitmap = null;
          goto _L5
    }

    public void saveCache(TileRequest tilerequest, byte abyte0[])
    {
        if (!m_removing)
        {
            removeCaches();
        }
        if (existsCache(tilerequest))
        {
            return;
        } else
        {
            String s = tilerequest.getCacheFileName();
            TileCacheFileWrapper tilecachefilewrapper = new TileCacheFileWrapper(new File(m_cache_dir, s), false);
            tilecachefilewrapper.saveCache(tilerequest, abyte0);
            m_cacheFiles.put(s, tilecachefilewrapper);
            return;
        }
    }

    public void stopThread()
    {
        m_threadRun = false;
        synchronized (lock)
        {
            lock.notify();
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

}
