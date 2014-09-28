// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.viewlayer.tile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileOutputStream;

// Referenced classes of package jp.co.yahoo.android.maps.viewlayer.tile:
//            TileCacheManager, TileRequest

private class m_file
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


    public (File file, boolean flag)
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
