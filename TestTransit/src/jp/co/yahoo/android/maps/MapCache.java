// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import jp.co.yahoo.android.maps.viewlayer.tile.Tile;
import jp.co.yahoo.android.maps.viewlayer.tile.TileRequest;

// Referenced classes of package jp.co.yahoo.android.maps:
//            DoublePoint

class MapCache
{

    public static long m_var = 2L;
    public boolean httpState;
    private int m_loadmap_x;
    private int m_loadmap_y;
    private long m_scale;
    private long m_time;
    private int m_type;

    MapCache()
    {
        httpState = false;
        m_time = -1L;
    }

    public static byte[] file2data(String s)
        throws Exception
    {
        FileInputStream fileinputstream;
        Exception exception;
        ByteArrayOutputStream bytearrayoutputstream1;
        FileInputStream fileinputstream1;
        byte abyte0[] = new byte[256];
        ByteArrayOutputStream bytearrayoutputstream;
        Exception exception1;
        int i;
        try
        {
            fileinputstream = new FileInputStream(s);
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            fileinputstream1 = null;
            bytearrayoutputstream1 = null;
            continue; /* Loop/switch isn't completed */
        }
        bytearrayoutputstream = new ByteArrayOutputStream();
_L1:
        i = fileinputstream.read(abyte0);
        if (i > 0)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        fileinputstream.close();
        bytearrayoutputstream.close();
        return bytearrayoutputstream.toByteArray();
        bytearrayoutputstream.write(abyte0, 0, i);
          goto _L1
        exception;
        bytearrayoutputstream1 = bytearrayoutputstream;
        fileinputstream1 = fileinputstream;
_L3:
        if (fileinputstream1 == null)
        {
            break MISSING_BLOCK_LABEL_77;
        }
        fileinputstream1.close();
        if (bytearrayoutputstream1 != null)
        {
            try
            {
                bytearrayoutputstream1.close();
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception1) { }
        }
        throw exception;
        exception;
        fileinputstream1 = fileinputstream;
        bytearrayoutputstream1 = null;
        if (true) goto _L3; else goto _L2
_L2:
    }

    public static byte[] reard(Context context, String s)
    {
        byte abyte0[];
        try
        {
            abyte0 = file2data((new StringBuilder()).append(context.getFilesDir()).append("/").append(s).append("_yahoomap").toString());
        }
        catch (Exception exception)
        {
            return null;
        }
        return abyte0;
    }

    public boolean cmpE(Tile tile)
    {
        return false;
    }

    public double getDist(DoublePoint doublepoint, int i, int j, int k)
    {
        int l = (int)((double)(k * m_loadmap_x) - doublepoint.x);
        int i1 = (int)((double)(k * m_loadmap_x) - doublepoint.y);
        int j1 = l + k / 2;
        int k1 = i1 + k / 2;
        int l1 = i / 2;
        int i2 = j / 2;
        double d = j1 - l1;
        double d1 = k1 - i2;
        return Math.sqrt(d * d + d1 * d1);
    }

    public Bitmap getImage(Context context)
    {
        byte abyte0[];
        abyte0 = reard(context, getKey());
        if (abyte0 == null)
        {
            break MISSING_BLOCK_LABEL_41;
        }
        Bitmap bitmap;
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inPurgeable = true;
        bitmap = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length, options);
        return bitmap;
        Exception exception;
        exception;
        return null;
    }

    public String getKey()
    {
        return (new StringBuilder(String.valueOf(m_loadmap_x))).append("_").append(m_loadmap_y).append("_").append(m_scale).append("_").append(m_type).toString();
    }

    public String getPackKey()
    {
        int i;
        if (m_loadmap_y % 2 == 0)
        {
            i = 1 + m_loadmap_y;
        } else
        {
            i = -1 + m_loadmap_y;
        }
        return (new StringBuilder(String.valueOf(m_loadmap_x))).append("_").append(i).append("_").append(m_scale).append("_").append(m_type).toString();
    }

    public long getScale()
    {
        return m_scale;
    }

    public long getTime()
    {
        return m_time;
    }

    public int getType()
    {
        return m_type;
    }

    public void removeImage(Context context)
    {
        try
        {
            context.deleteFile((new StringBuilder(String.valueOf(getKey()))).append("_yahoomap").toString());
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public void set(int i, int j, long l, int k, long l1)
    {
        m_loadmap_x = i;
        m_loadmap_y = j;
        m_scale = l;
        m_type = k;
        m_time = l1;
    }

    public void set(int i, int j, long l, int k, long l1, 
            byte abyte0[])
    {
        m_type = k;
        set(i, j, l, m_type, l1);
    }

    public void set(Tile tile)
    {
    }

    public void setTileRequest(TileRequest tilerequest)
    {
        set(tilerequest.getTileX(), tilerequest.getTileY(), tilerequest.getTileZ(), tilerequest.getMapType(), 0L, null);
    }

    public void setTime(long l)
    {
        m_time = l;
    }

    public void setType(int i)
    {
        m_type = i;
    }

}
