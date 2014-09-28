// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.ar;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

// Referenced classes of package jp.co.yahoo.android.maps.ar:
//            Vector2D, GraphicUtil, NavigationMgr

public class GLRenderer
    implements android.opengl.GLSurfaceView.Renderer
{

    private float arrowA;
    private float arrowB;
    private float arrowG;
    private float arrowR;
    private int mHeight;
    private int mOffsetX;
    private int mOffsetY;
    private boolean mTranslucentBackground;
    private int mWidth;
    private NavigationMgr naviMgr;

    public GLRenderer(boolean flag, NavigationMgr navigationmgr)
    {
        mTranslucentBackground = flag;
        naviMgr = navigationmgr;
        arrowA = 0.3F;
        arrowR = 0.0F;
        arrowG = 0.0F;
        arrowB = 1.0F;
    }

    private void drawArrow(GL10 gl10, double d)
    {
        gl10.glPushMatrix();
        gl10.glRotatef(-(float)d, 0.0F, 0.0F, 1.0F);
        GraphicUtil.drawTriangle(gl10, new Vector2D(0.2F, 0.15F), new Vector2D(0.0F, 0.35F), new Vector2D(-0.2F, 0.15F), arrowR, arrowG, arrowB, arrowA);
        GraphicUtil.drawRectangle(gl10, 0.0F, 0.0F, 0.2F, 0.3F, arrowR, arrowG, arrowB, arrowA);
        gl10.glPopMatrix();
    }

    private void renderMain(GL10 gl10)
    {
        gl10.glMatrixMode(5889);
        gl10.glLoadIdentity();
        gl10.glFrustumf(-0.3F, 0.3F, -0.2F, 0.2F, 0.5F, 20F);
        gl10.glMatrixMode(5888);
        gl10.glLoadIdentity();
        gl10.glTranslatef(0.0F, 0.0F, -1F);
        gl10.glTranslatef(0.0F, -0.25F, 0.0F);
        gl10.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
        gl10.glRotatef(naviMgr.getAzimuth(), 0.0F, 0.0F, 1.0F);
        drawArrow(gl10, naviMgr.getAngle());
    }

    public float getArrayColorA()
    {
        return arrowA;
    }

    public float getArrayColorB()
    {
        return arrowB;
    }

    public float getArrayColorG()
    {
        return arrowG;
    }

    public float getArrayColorR()
    {
        return arrowR;
    }

    public void onDrawFrame(GL10 gl10)
    {
        if (mTranslucentBackground)
        {
            gl10.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
            gl10.glClear(16640);
        } else
        {
            gl10.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
            gl10.glClear(16384);
        }
        gl10.glViewport(mOffsetX, mOffsetY, mWidth, mHeight);
        renderMain(gl10);
    }

    public void onSurfaceChanged(GL10 gl10, int i, int j)
    {
        int k = 0;
        int l = 0;
        do
        {
            if (k >= i || l >= j)
            {
                mWidth = k;
                mHeight = l;
                mOffsetX = (i - k) / 2;
                mOffsetY = (j - l) / 2;
                return;
            }
            k += 3;
            l += 2;
        } while (true);
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eglconfig)
    {
    }

    public void setArrowColor(float f, float f1, float f2, float f3)
    {
        arrowA = f;
        arrowB = f3;
        arrowG = f2;
        arrowR = f1;
    }
}
