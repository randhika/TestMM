// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.ar;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.opengl.GLSurfaceView;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import jp.co.yahoo.android.maps.GeoPoint;

// Referenced classes of package jp.co.yahoo.android.maps.ar:
//            NavigationMgrListener, POIViewListener, NavigationMgr, GLRenderer, 
//            SubmapView, CameraPreview, MapBgView, POIView, 
//            ARControllerListener

public class ARController
    implements NavigationMgrListener, POIViewListener
{

    private MapBgView bkView;
    private CameraPreview cameraview;
    private GLSurfaceView glSurfaceView;
    private LinearLayout layoutBk;
    private ARControllerListener listener;
    private LinearLayout mapLayout;
    private int mapMargin;
    private int mapSize;
    private SubmapView mapView;
    private int marginBottom;
    private NavigationMgr naviMgr;
    private Activity parentActivity;
    private LinearLayout poiLayout;
    private POIView poiView;
    private GLRenderer renderer;
    private boolean visibleMap;

    public ARController(Activity activity, ARControllerListener arcontrollerlistener)
    {
        parentActivity = activity;
        listener = arcontrollerlistener;
        parentActivity.getWindow().clearFlags(2048);
        parentActivity.getWindow().addFlags(1024);
        naviMgr = new NavigationMgr(parentActivity, this);
        Display display = ((WindowManager)parentActivity.getSystemService("window")).getDefaultDisplay();
        glSurfaceView = new GLSurfaceView(parentActivity);
        glSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        renderer = new GLRenderer(true, naviMgr);
        glSurfaceView.setRenderer(renderer);
        glSurfaceView.getHolder().setFormat(-3);
        parentActivity.setContentView(glSurfaceView);
        mapSize = 200;
        mapMargin = 10;
        marginBottom = 40;
        int i = display.getWidth();
        int j = display.getHeight();
        if (i < j)
        {
            int k = i;
            i = j;
            j = k;
        }
        visibleMap = true;
        if (i <= 480 && j <= 320)
        {
            visibleMap = false;
        }
        if (visibleMap)
        {
            mapView = new SubmapView(parentActivity, "oRB..Ryxg67JCNczATeZciRex.X4wFo06.Z_x6J_P2TRc22XD7Fttjj.702yRblXwCw-", naviMgr);
            mapLayout = new LinearLayout(parentActivity);
            mapLayout.setPadding(display.getWidth() - mapSize - mapMargin, display.getHeight() - mapSize - mapMargin - marginBottom, 0, 0);
            mapLayout.addView(mapView, new android.view.ViewGroup.LayoutParams(mapSize, mapSize));
            parentActivity.addContentView(mapLayout, new android.view.ViewGroup.LayoutParams(-2, -2));
        }
        cameraview = new CameraPreview(parentActivity);
        parentActivity.addContentView(cameraview, new android.view.ViewGroup.LayoutParams(-1, -1));
        if (visibleMap)
        {
            bkView = new MapBgView(parentActivity);
            layoutBk = new LinearLayout(parentActivity);
            layoutBk.setPadding(display.getWidth() - mapSize - 2 * mapMargin, display.getHeight() - mapSize - 2 * mapMargin - marginBottom, 0, 0);
            layoutBk.addView(bkView, new android.view.ViewGroup.LayoutParams(mapSize + 2 * mapMargin, mapSize + 2 * mapMargin));
            parentActivity.addContentView(layoutBk, new android.view.ViewGroup.LayoutParams(-2, -2));
        }
        poiView = new POIView(parentActivity, naviMgr, this);
        poiLayout = new LinearLayout(parentActivity);
        poiLayout.setPadding(0, 0, 0, 0);
        poiLayout.addView(poiView, new android.view.ViewGroup.LayoutParams(display.getWidth(), display.getHeight()));
        parentActivity.addContentView(poiLayout, new android.view.ViewGroup.LayoutParams(-2, -2));
    }

    public void NavigationMgrUpdated()
    {
        poiView.updateLocation();
        if (mapView != null)
        {
            mapView.updateLocation();
        }
    }

    public void POIViewListenerOnPick(int i)
    {
        listener.ARControllerListenerOnPOIPick(i);
    }

    public int addPOI(double d, double d1, Drawable drawable, int i, int j)
    {
        return naviMgr.addPOI(d, d1, drawable, i, j);
    }

    public void clearPOI()
    {
        naviMgr.clearPOI();
    }

    public boolean isSubmapVisible()
    {
        while (mapView == null || mapView.getVisibility() != 0) 
        {
            return false;
        }
        return true;
    }

    public void onPause()
    {
        glSurfaceView.onPause();
        naviMgr.onPause();
        if (mapView != null)
        {
            mapView.onPause();
        }
    }

    public void onResume()
    {
        glSurfaceView.onResume();
        naviMgr.onResume();
        if (mapView != null)
        {
            mapView.onResume();
            float f = renderer.getArrayColorA();
            float f1 = renderer.getArrayColorR();
            float f2 = renderer.getArrayColorG();
            float f3 = renderer.getArrayColorB();
            glSurfaceView = new GLSurfaceView(parentActivity);
            glSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
            renderer = new GLRenderer(true, naviMgr);
            renderer.setArrowColor(f, f1, f2, f3);
            glSurfaceView.setRenderer(renderer);
            glSurfaceView.getHolder().setFormat(-3);
            parentActivity.setContentView(glSurfaceView);
            if (visibleMap)
            {
                parentActivity.addContentView(mapLayout, new android.view.ViewGroup.LayoutParams(-2, -2));
            }
            parentActivity.addContentView(cameraview, new android.view.ViewGroup.LayoutParams(-1, -1));
            if (visibleMap)
            {
                parentActivity.addContentView(layoutBk, new android.view.ViewGroup.LayoutParams(-2, -2));
            }
            parentActivity.addContentView(poiLayout, new android.view.ViewGroup.LayoutParams(-2, -2));
        }
    }

    public void removePOI(int i)
    {
        naviMgr.removePOI(i);
    }

    public void setArrowColor(float f, float f1, float f2, float f3)
    {
        renderer.setArrowColor(f, f1, f2, f3);
    }

    public void setCurrentPos(double d, double d1, double d2, float f)
    {
        naviMgr.setGpsValue(d, d1, d2, f);
    }

    public void setDestination(int i)
    {
        naviMgr.setDestination(i);
    }

    public void setRoute(GeoPoint ageopoint[])
    {
        if (mapView != null)
        {
            mapView.setRoute(ageopoint);
        }
    }

    public void setSubmapVisible(boolean flag)
    {
        if (mapView == null)
        {
            return;
        }
        if (flag)
        {
            mapView.setVisibility(0);
            mapView.setMapVisible(flag);
            bkView.setVisibility(0);
            return;
        } else
        {
            mapView.setVisibility(4);
            mapView.setMapVisible(flag);
            bkView.setVisibility(4);
            return;
        }
    }
}
