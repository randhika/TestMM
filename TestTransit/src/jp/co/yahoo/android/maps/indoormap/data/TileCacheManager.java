// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.indoormap.data;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentLinkedQueue;

// Referenced classes of package jp.co.yahoo.android.maps.indoormap.data:
//            IndoormapRequest

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

        private byte[] loadCache(IndoormapRequest indoormaprequest)
        {
            this;
            JVM INSTR monitorenter ;
            byte abyte0[];
            m_last_access_time = System.currentTimeMillis();
            m_file.setLastModified(m_last_access_time);
            FileInputStream fileinputstream = new FileInputStream(m_file);
            abyte0 = new byte[fileinputstream.available()];
            fileinputstream.read(abyte0);
            fileinputstream.close();
_L2:
            this;
            JVM INSTR monitorexit ;
            return abyte0;
            Exception exception1;
            exception1;
            m_error = true;
            deleteCache();
            abyte0 = null;
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

        public void saveCache(IndoormapRequest indoormaprequest, byte abyte0[])
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

        public abstract boolean endTileCacheLoad(byte abyte0[], IndoormapRequest indoormaprequest);
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
    private boolean disposed;
    private final Object lock = new Object();
    private Hashtable m_cacheFiles;
    private File m_cache_dir;
    private int m_cache_size;
    private Context m_context;
    private TileCacheListener m_listener;
    private boolean m_removing;
    private final ConcurrentLinkedQueue m_request_tiles = new ConcurrentLinkedQueue();

    public TileCacheManager(Context context, TileCacheListener tilecachelistener)
    {
        super("TileCacheManager");
        m_listener = null;
        m_context = null;
        m_cache_size = 0x1e8480;
        m_cache_dir = null;
        m_removing = false;
        m_cacheFiles = null;
        disposed = false;
        m_context = context;
        m_listener = tilecachelistener;
        File file = new File(m_context.getFilesDir(), "yj_androidsdk");
        if (!file.exists())
        {
            file.mkdir();
        }
        m_cache_dir = new File(file, "cache_tiles_indoormap");
        if (!m_cache_dir.exists())
        {
            m_cache_dir.mkdir();
        }
        initCacheFiles();
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

    public static String makeFileName(IndoormapRequest indoormaprequest)
    {
        String s;
        synchronized (m_wk_filename)
        {
            m_wk_filename.delete(0, m_wk_filename.length());
            m_wk_filename.append("_").append(indoormaprequest.getTileX()).append("_").append(indoormaprequest.getTileY()).append("_").append(indoormaprequest.getTileZ());
            if (indoormaprequest.getStyle() != null)
            {
                m_wk_filename.append("_").append(indoormaprequest.getStyle().hashCode());
            }
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

    public void dispose()
    {
        if (!disposed)
        {
            disposed = true;
            m_listener = null;
            m_context = null;
            m_cache_dir = null;
            if (m_cacheFiles != null)
            {
                m_cacheFiles.clear();
            }
            if (m_request_tiles != null)
            {
                m_request_tiles.clear();
            }
        }
    }

    public boolean existsCache(IndoormapRequest indoormaprequest)
    {
        return m_cacheFiles.containsKey(indoormaprequest.getCacheFileName());
    }

    public byte[] getCache(IndoormapRequest indoormaprequest)
    {
        byte abyte0[];
        try
        {
            abyte0 = ((TileCacheFileWrapper)m_cacheFiles.get(indoormaprequest.getCacheFileName())).loadCache(indoormaprequest);
        }
        catch (Exception exception)
        {
            return null;
        }
        return abyte0;
    }

    public void requestCache(IndoormapRequest indoormaprequest)
    {
        m_request_tiles.add(indoormaprequest);
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
        IndoormapRequest indoormaprequest = (IndoormapRequest)m_request_tiles.poll();
        if (indoormaprequest == null) goto _L2; else goto _L1
_L1:
        byte abyte1[] = ((TileCacheFileWrapper)m_cacheFiles.get(indoormaprequest.getCacheFileName())).loadCache(indoormaprequest);
        byte abyte0[] = abyte1;
_L4:
        m_listener.endTileCacheLoad(abyte0, indoormaprequest);
          goto _L3
_L2:
        synchronized (lock)
        {
            lock.wait();
        }
          goto _L3
        exception;
        obj;
        JVM INSTR monitorexit ;
        try
        {
            throw exception;
        }
        catch (InterruptedException interruptedexception) { }
          goto _L3
        Exception exception1;
        exception1;
        abyte0 = null;
          goto _L4
    }

    public void saveCache(IndoormapRequest indoormaprequest, byte abyte0[])
    {
        if (!m_removing)
        {
            removeCaches();
        }
        if (existsCache(indoormaprequest))
        {
            return;
        } else
        {
            String s = indoormaprequest.getCacheFileName();
            TileCacheFileWrapper tilecachefilewrapper = new TileCacheFileWrapper(new File(m_cache_dir, s), false);
            tilecachefilewrapper.saveCache(indoormaprequest, abyte0);
            m_cacheFiles.put(s, tilecachefilewrapper);
            return;
        }
    }

}
