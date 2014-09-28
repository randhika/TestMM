// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps;

import android.graphics.Point;
import android.os.Handler;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.co.yahoo.android.maps:
//            MapView, MapController, Projection, GeoPoint, 
//            HttpClient

public class LabelTouchManager
{
    public static class LabelInfo
    {

        public double angle;
        public String label;
        public String name;
        public String type;
        public boolean vertical;

        public LabelInfo()
        {
        }
    }

    public static interface LabelTouchListener
    {

        public abstract void onLabelTouch(LabelInfo labelinfo);
    }

    class LoaderResult
        implements Runnable
    {

        final LabelInfo labelInfo;
        final LabelTouchManager this$0;

        public void run()
        {
            processLabelTouchListener(labelInfo);
        }

        LoaderResult(JSONObject jsonobject)
            throws JSONException
        {
            LabelInfo labelinfo;
            this$0 = LabelTouchManager.this;
            super();
            labelinfo = new LabelInfo();
            jsonobject.getInt("type");
            JVM INSTR tableswitch 1 2: default 44
        //                       1 90
        //                       2 99;
               goto _L1 _L2 _L3
_L1:
            labelinfo.label = jsonobject.getString("label");
            labelinfo.name = jsonobject.getString("name");
            labelinfo.angle = jsonobject.getDouble("angle");
            labelinfo.vertical = jsonobject.getBoolean("vertical");
            labelInfo = labelinfo;
            return;
_L2:
            labelinfo.type = "icon";
            continue; /* Loop/switch isn't completed */
_L3:
            labelinfo.type = "text";
            if (true) goto _L1; else goto _L4
_L4:
        }
    }

    class LoaderThread extends Thread
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
            LoaderResult loaderresult;
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
            d = jsonarray1.getDouble(0) * mapView.getTileMagnification();
            d1 = jsonarray1.getDouble(1) * mapView.getTileMagnification();
            d2 = jsonarray1.getDouble(2) * mapView.getTileMagnification();
            d3 = jsonarray1.getDouble(3) * mapView.getTileMagnification();
            if ((double)tx >= d - 20D && (double)tx < 20D + (d + d2) && (double)ty >= d1 - 20D && (double)ty < 20D + (d1 + d3))
            {
                handler = mHandler;
                loaderresult = new LoaderResult(jsonobject);
                handler.post(loaderresult);
                return;
            }
            i++;
            if (true) goto _L2; else goto _L1
_L1:
        }

        LoaderThread(int ai[])
        {
            this$0 = LabelTouchManager.this;
            super("LoaderThread");
            url = (new StringBuilder("http://bounds.olp.yahooapis.jp/OpenLocalPlatform/V1/bounds?appid=ZzZDynixg67NWpLxYgUVFffahJVdIjKfS5PswBs2xSgAKghHIVZWXNCwM3yBhZ8y_MY-&x=")).append(ai[0]).append("&y=").append(ai[1]).append("&z=").append(ai[2]).append("&mode=").append("smartphone").toString();
            tx = ai[3];
            ty = ai[4];
        }
    }


    private final String APPID = "ZzZDynixg67NWpLxYgUVFffahJVdIjKfS5PswBs2xSgAKghHIVZWXNCwM3yBhZ8y_MY-";
    private final List listener = new ArrayList();
    private final Handler mHandler = new Handler();
    private final MapView mapView;
    private final String mode = "smartphone";

    public LabelTouchManager(MapView mapview)
    {
        mapView = mapview;
    }

    private void processLabelTouchListener(LabelInfo labelinfo)
    {
        Iterator iterator = listener.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                return;
            }
            ((LabelTouchListener)iterator.next()).onLabelTouch(labelinfo);
        } while (true);
    }

    public void addLabelTouchListener(LabelTouchListener labeltouchlistener)
    {
        listener.add(labeltouchlistener);
    }

    public void queryLabel(GeoPoint geopoint)
    {
        if (mapView.getMapController().getMaptype() != MapView.MapTypeStandard)
        {
            return;
        } else
        {
            Projection projection = mapView.getProjection();
            Point point = projection.toPixels(geopoint, null);
            (new LoaderThread(projection.getTileIdPos(point.x, point.y))).start();
            return;
        }
    }



}
