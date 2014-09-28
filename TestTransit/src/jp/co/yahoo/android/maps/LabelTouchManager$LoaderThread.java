// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.os.Handler;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.maps:
//            HttpClient, LabelTouchManager, MapView

class ty extends Thread
{

    final LabelTouchManager this$0;
    final int tx;
    final int ty;
    final String url;

    private String getHttp()
    {
        String s = "[]";
        InputStream inputstream = HttpClient.httprun(url);
        if (inputstream == null)
        {
            return s;
        }
        ByteArrayOutputStream bytearrayoutputstream;
        byte abyte0[];
        bytearrayoutputstream = new ByteArrayOutputStream();
        abyte0 = new byte[1024];
_L1:
        int i = inputstream.read(abyte0);
        if (i <= 0)
        {
            try
            {
                bytearrayoutputstream.close();
                inputstream.close();
                s = new String(bytearrayoutputstream.toByteArray(), "UTF-8");
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
            break MISSING_BLOCK_LABEL_94;
        }
        bytearrayoutputstream.write(abyte0, 0, i);
          goto _L1
        return s;
    }

    public void run()
    {
        int i;
        String s = getHttp();
        JSONArray jsonarray;
        JSONObject jsonobject;
        JSONArray jsonarray1;
        double d;
        double d1;
        double d2;
        double d3;
        Handler handler;
        url url1;
        try
        {
            jsonarray = new JSONArray(s);
        }
        catch (JSONException jsonexception)
        {
            jsonexception.printStackTrace();
            return;
        }
        i = 0;
_L2:
        if (i >= jsonarray.length())
        {
            return;
        }
        jsonobject = jsonarray.getJSONObject(i);
        jsonarray1 = jsonobject.getJSONArray("bounds");
        d = jsonarray1.getDouble(0) * LabelTouchManager.access$0(LabelTouchManager.this).getTileMagnification();
        d1 = jsonarray1.getDouble(1) * LabelTouchManager.access$0(LabelTouchManager.this).getTileMagnification();
        d2 = jsonarray1.getDouble(2) * LabelTouchManager.access$0(LabelTouchManager.this).getTileMagnification();
        d3 = jsonarray1.getDouble(3) * LabelTouchManager.access$0(LabelTouchManager.this).getTileMagnification();
        if ((double)tx >= d - 20D && (double)tx < 20D + (d + d2) && (double)ty >= d1 - 20D && (double)ty < 20D + (d1 + d3))
        {
            handler = LabelTouchManager.access$1(LabelTouchManager.this);
            url1 = new <init>(LabelTouchManager.this, jsonobject);
            handler.post(url1);
            return;
        }
        i++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    (int ai[])
    {
        this$0 = LabelTouchManager.this;
        super("LoaderThread");
        url = (new StringBuilder("http://bounds.olp.yahooapis.jp/OpenLocalPlatform/V1/bounds?appid=ZzZDynixg67NWpLxYgUVFffahJVdIjKfS5PswBs2xSgAKghHIVZWXNCwM3yBhZ8y_MY-&x=")).append(ai[0]).append("&y=").append(ai[1]).append("&z=").append(ai[2]).append("&mode=").append("smartphone").toString();
        tx = ai[3];
        ty = ai[4];
    }
}
