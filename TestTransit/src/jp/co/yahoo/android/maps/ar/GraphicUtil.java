// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.ar;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Hashtable;
import javax.microedition.khronos.opengles.GL10;

// Referenced classes of package jp.co.yahoo.android.maps.ar:
//            Vector2D, TextureManager

public class GraphicUtil
{

    private static Hashtable colorsPool = new Hashtable();
    private static Hashtable coordsPool = new Hashtable();
    private static final android.graphics.BitmapFactory.Options options;
    private static Hashtable polygonColorsPool = new Hashtable();
    private static Hashtable polygonVerticesPool = new Hashtable();
    private static Hashtable texCoordsPool = new Hashtable();
    private static Hashtable verticesPool = new Hashtable();

    public GraphicUtil()
    {
    }

    public static final void drawCircle(GL10 gl10, float f, float f1, int i, float f2, float f3, float f4, float f5, 
            float f6)
    {
        float af[] = getVertices(2 * (i * 3));
        int j = 0;
        int k = 0;
        do
        {
            if (j >= i)
            {
                FloatBuffer floatbuffer = makeVerticesBuffer(af);
                gl10.glColor4f(f3, f4, f5, f6);
                gl10.glDisableClientState(32886);
                gl10.glVertexPointer(2, 5126, 0, floatbuffer);
                gl10.glEnableClientState(32884);
                gl10.glDrawArrays(4, 0, i * 3);
                return;
            }
            float f7 = 3.141593F * ((2.0F / (float)i) * (float)j);
            float f8 = 3.141593F * ((2.0F / (float)i) * (float)(j + 1));
            int l = k + 1;
            af[k] = f;
            int i1 = l + 1;
            af[l] = f1;
            int j1 = i1 + 1;
            af[i1] = f + f2 * (float)Math.cos(f7);
            int k1 = j1 + 1;
            af[j1] = f1 + f2 * (float)Math.sin(f7);
            int l1 = k1 + 1;
            af[k1] = f + f2 * (float)Math.cos(f8);
            k = l1 + 1;
            af[l1] = f1 + f2 * (float)Math.sin(f8);
            j++;
        } while (true);
    }

    public static final void drawCube(GL10 gl10, float f, float f1, float f2, float f3, float f4, float f5, float f6, 
            float f7, float f8, float f9)
    {
        gl10.glEnable(2929);
        float af[] = getColors(16);
        int i = 0;
        do
        {
            if (i >= 4)
            {
                FloatBuffer floatbuffer = makeColorsBuffer(af);
                float af1[] = getVertices(12);
                af1[0] = f + -0.5F * f3;
                af1[1] = f1 + -0.5F * f4;
                af1[2] = f5 + f2;
                af1[3] = f + 0.5F * f3;
                af1[4] = f1 + -0.5F * f4;
                af1[5] = f5 + f2;
                af1[6] = f + -0.5F * f3;
                af1[7] = f1 + 0.5F * f4;
                af1[8] = f5 + f2;
                af1[9] = f + 0.5F * f3;
                af1[10] = f1 + 0.5F * f4;
                af1[11] = f5 + f2;
                gl10.glVertexPointer(3, 5126, 0, makeVerticesBuffer(af1));
                gl10.glEnableClientState(32884);
                gl10.glColorPointer(4, 5121, 0, floatbuffer);
                gl10.glEnableClientState(32886);
                gl10.glDrawArrays(5, 0, 4);
                af1[0] = f + -0.5F * f3;
                af1[1] = f1 + -0.5F * f4;
                af1[2] = f2;
                af1[3] = f + 0.5F * f3;
                af1[4] = f1 + -0.5F * f4;
                af1[5] = f2;
                af1[6] = f + -0.5F * f3;
                af1[7] = f1 + 0.5F * f4;
                af1[8] = f2;
                af1[9] = f + 0.5F * f3;
                af1[10] = f1 + 0.5F * f4;
                af1[11] = f2;
                gl10.glVertexPointer(3, 5126, 0, makeVerticesBuffer(af1));
                gl10.glEnableClientState(32884);
                gl10.glColorPointer(4, 5121, 0, floatbuffer);
                gl10.glEnableClientState(32886);
                gl10.glDrawArrays(5, 0, 4);
                af1[0] = f + -0.5F * f3;
                af1[1] = f1 + 0.5F * f4;
                af1[2] = f2;
                af1[3] = f + 0.5F * f3;
                af1[4] = f1 + 0.5F * f4;
                af1[5] = f2;
                af1[6] = f + -0.5F * f3;
                af1[7] = f1 + 0.5F * f4;
                af1[8] = f5 + f2;
                af1[9] = f + 0.5F * f3;
                af1[10] = f1 + 0.5F * f4;
                af1[11] = f5 + f2;
                gl10.glVertexPointer(3, 5126, 0, makeVerticesBuffer(af1));
                gl10.glEnableClientState(32884);
                gl10.glColorPointer(4, 5121, 0, floatbuffer);
                gl10.glEnableClientState(32886);
                gl10.glDrawArrays(5, 0, 4);
                af1[0] = f + -0.5F * f3;
                af1[1] = f1 + -0.5F * f4;
                af1[2] = f2;
                af1[3] = f + 0.5F * f3;
                af1[4] = f1 + -0.5F * f4;
                af1[5] = f2;
                af1[6] = f + -0.5F * f3;
                af1[7] = f1 + -0.5F * f4;
                af1[8] = f5 + f2;
                af1[9] = f + 0.5F * f3;
                af1[10] = f1 + -0.5F * f4;
                af1[11] = f5 + f2;
                gl10.glVertexPointer(3, 5126, 0, makeVerticesBuffer(af1));
                gl10.glEnableClientState(32884);
                gl10.glColorPointer(4, 5121, 0, floatbuffer);
                gl10.glEnableClientState(32886);
                gl10.glDrawArrays(5, 0, 4);
                af1[0] = f + 0.5F * f3;
                af1[1] = f1 + -0.5F * f4;
                af1[2] = f2;
                af1[3] = f + 0.5F * f3;
                af1[4] = f1 + 0.5F * f4;
                af1[5] = f2;
                af1[6] = f + 0.5F * f3;
                af1[7] = f1 + -0.5F * f4;
                af1[8] = f5 + f2;
                af1[9] = f + 0.5F * f3;
                af1[10] = f1 + 0.5F * f4;
                af1[11] = f5 + f2;
                gl10.glVertexPointer(3, 5126, 0, makeVerticesBuffer(af1));
                gl10.glEnableClientState(32884);
                gl10.glColorPointer(4, 5121, 0, floatbuffer);
                gl10.glEnableClientState(32886);
                gl10.glDrawArrays(5, 0, 4);
                af1[0] = f + -0.5F * f3;
                af1[1] = f1 + -0.5F * f4;
                af1[2] = f2;
                af1[3] = f + -0.5F * f3;
                af1[4] = f1 + 0.5F * f4;
                af1[5] = f2;
                af1[6] = f + -0.5F * f3;
                af1[7] = f1 + -0.5F * f4;
                af1[8] = f5 + f2;
                af1[9] = f + -0.5F * f3;
                af1[10] = f1 + 0.5F * f4;
                af1[11] = f5 + f2;
                gl10.glVertexPointer(3, 5126, 0, makeVerticesBuffer(af1));
                gl10.glEnableClientState(32884);
                gl10.glColorPointer(4, 5121, 0, floatbuffer);
                gl10.glEnableClientState(32886);
                gl10.glDrawArrays(5, 0, 4);
                gl10.glDisable(2929);
                return;
            }
            af[i * 4] = f6;
            af[1 + i * 4] = f7;
            af[2 + i * 4] = f8;
            af[3 + i * 4] = f9;
            i++;
        } while (true);
    }

    public static final void drawNumber(GL10 gl10, float f, float f1, float f2, float f3, int i, int j, float f4, 
            float f5, float f6, float f7)
    {
        drawTexture(gl10, f, f1, f2, f3, i, 0.25F * (float)(j % 4), 0.25F * (float)(j / 4), 0.25F, 0.25F, f4, f5, f6, f7);
    }

    public static final void drawNumbers(GL10 gl10, float f, float f1, float f2, float f3, int i, int j, int k, 
            float f4, float f5, float f6, float f7)
    {
        float f8 = (f + 0.5F * (f2 * (float)k)) - 0.5F * f2;
        int l = 0;
        do
        {
            if (l >= k)
            {
                return;
            }
            float f9 = f8 - f2 * (float)l;
            int i1 = j % 10;
            j /= 10;
            drawNumber(gl10, f9, f1, f2, f3, i, i1, 1.0F, 1.0F, 1.0F, 1.0F);
            l++;
        } while (true);
    }

    public static final void drawRectangle(GL10 gl10, float f, float f1, float f2, float f3, float f4, float f5, float f6, 
            float f7)
    {
        float af[] = getVertices(8);
        af[0] = f + -0.5F * f2;
        af[1] = f1 + -0.5F * f3;
        af[2] = f + 0.5F * f2;
        af[3] = f1 + -0.5F * f3;
        af[4] = f + -0.5F * f2;
        af[5] = f1 + 0.5F * f3;
        af[6] = f + 0.5F * f2;
        af[7] = f1 + 0.5F * f3;
        float af1[] = getColors(16);
        int i = 0;
        do
        {
            if (i >= 16)
            {
                FloatBuffer floatbuffer = makeVerticesBuffer(af);
                FloatBuffer floatbuffer1 = makeColorsBuffer(af1);
                gl10.glVertexPointer(2, 5126, 0, floatbuffer);
                gl10.glEnableClientState(32884);
                gl10.glColorPointer(4, 5126, 0, floatbuffer1);
                gl10.glEnableClientState(32886);
                gl10.glDrawArrays(5, 0, 4);
                return;
            }
            int j = i + 1;
            af1[i] = f4;
            int k = j + 1;
            af1[j] = f5;
            int l = k + 1;
            af1[k] = f6;
            af1[l] = f7;
            i = l + 1;
        } while (true);
    }

    public static final void drawSquare(GL10 gl10)
    {
        drawSquare(gl10, 1.0F, 0.0F, 0.0F, 1.0F);
    }

    public static final void drawSquare(GL10 gl10, float f, float f1, float f2, float f3)
    {
        drawSquare(gl10, 0.0F, 0.0F, f, f1, f2, f3);
    }

    public static final void drawSquare(GL10 gl10, float f, float f1, float f2, float f3, float f4, float f5)
    {
        drawRectangle(gl10, f, f1, 1.0F, 1.0F, f2, f3, f4, f5);
    }

    public static final void drawTexture(GL10 gl10, float f, float f1, float f2, float f3, int i, float f4, float f5, 
            float f6, float f7)
    {
        drawTexture(gl10, f, f1, f2, f3, i, 0.0F, 0.0F, 1.0F, 1.0F, f4, f5, f6, f7);
    }

    public static final void drawTexture(GL10 gl10, float f, float f1, float f2, float f3, int i, float f4, float f5, 
            float f6, float f7, float f8, float f9, float f10, float f11)
    {
        float af[] = getVertices(8);
        af[0] = f + -0.5F * f2;
        af[1] = f1 + -0.5F * f3;
        af[2] = f + 0.5F * f2;
        af[3] = f1 + -0.5F * f3;
        af[4] = f + -0.5F * f2;
        af[5] = f1 + 0.5F * f3;
        af[6] = f + 0.5F * f2;
        af[7] = f1 + 0.5F * f3;
        float af1[] = getColors(16);
        int j = 0;
        do
        {
            if (j >= 16)
            {
                float af2[] = getCoords(8);
                af2[0] = f4;
                af2[1] = f5 + f7;
                af2[2] = f4 + f6;
                af2[3] = f5 + f7;
                af2[4] = f4;
                af2[5] = f5;
                af2[6] = f4 + f6;
                af2[7] = f5;
                FloatBuffer floatbuffer = makeVerticesBuffer(af);
                FloatBuffer floatbuffer1 = makeColorsBuffer(af1);
                FloatBuffer floatbuffer2 = makeTexCoordsBuffer(af2);
                gl10.glEnable(3553);
                gl10.glBindTexture(3553, i);
                gl10.glVertexPointer(2, 5126, 0, floatbuffer);
                gl10.glEnableClientState(32884);
                gl10.glColorPointer(4, 5126, 0, floatbuffer1);
                gl10.glEnableClientState(32886);
                gl10.glTexCoordPointer(2, 5126, 0, floatbuffer2);
                gl10.glEnableClientState(32888);
                gl10.glDrawArrays(5, 0, 4);
                gl10.glDisableClientState(32888);
                gl10.glDisable(3553);
                return;
            }
            int k = j + 1;
            af1[j] = f8;
            int l = k + 1;
            af1[k] = f9;
            int i1 = l + 1;
            af1[l] = f10;
            af1[i1] = f11;
            j = i1 + 1;
        } while (true);
    }

    public static final void drawTriangle(GL10 gl10, Vector2D vector2d, Vector2D vector2d1, Vector2D vector2d2, float f, float f1, float f2, float f3)
    {
        float af[] = getVertices(6);
        af[0] = vector2d.mX;
        af[1] = vector2d.mY;
        af[2] = vector2d1.mX;
        af[3] = vector2d1.mY;
        af[4] = vector2d2.mX;
        af[5] = vector2d2.mY;
        float af1[] = getColors(12);
        int i = 0;
        do
        {
            if (i >= 12)
            {
                FloatBuffer floatbuffer = makeVerticesBuffer(af);
                FloatBuffer floatbuffer1 = makeColorsBuffer(af1);
                gl10.glVertexPointer(2, 5126, 0, floatbuffer);
                gl10.glEnableClientState(32884);
                gl10.glColorPointer(4, 5126, 0, floatbuffer1);
                gl10.glEnableClientState(32886);
                gl10.glDrawArrays(4, 0, 3);
                return;
            }
            int j = i + 1;
            af1[i] = f;
            int k = j + 1;
            af1[j] = f1;
            int l = k + 1;
            af1[k] = f2;
            af1[l] = f3;
            i = l + 1;
        } while (true);
    }

    public static float[] getColors(int i)
    {
        if (colorsPool.containsKey(Integer.valueOf(i)))
        {
            return (float[])colorsPool.get(Integer.valueOf(i));
        } else
        {
            float af[] = new float[i];
            colorsPool.put(Integer.valueOf(i), af);
            return af;
        }
    }

    public static float[] getCoords(int i)
    {
        if (coordsPool.containsKey(Integer.valueOf(i)))
        {
            return (float[])coordsPool.get(Integer.valueOf(i));
        } else
        {
            float af[] = new float[i];
            coordsPool.put(Integer.valueOf(i), af);
            return af;
        }
    }

    public static float[] getVertices(int i)
    {
        if (verticesPool.containsKey(Integer.valueOf(i)))
        {
            return (float[])verticesPool.get(Integer.valueOf(i));
        } else
        {
            float af[] = new float[i];
            verticesPool.put(Integer.valueOf(i), af);
            return af;
        }
    }

    public static final int loadTexture(GL10 gl10, Resources resources, int i)
    {
        int ai[] = new int[1];
        Bitmap bitmap = BitmapFactory.decodeResource(resources, i, options);
        if (bitmap == null)
        {
            return 0;
        } else
        {
            gl10.glGenTextures(1, ai, 0);
            gl10.glBindTexture(3553, ai[0]);
            GLUtils.texImage2D(3553, 0, bitmap, 0);
            gl10.glTexParameterf(3553, 10241, 9729F);
            gl10.glTexParameterf(3553, 10240, 9729F);
            gl10.glBindTexture(3553, 0);
            bitmap.recycle();
            TextureManager.addTexture(i, ai[0]);
            return ai[0];
        }
    }

    public static final FloatBuffer makeColorsBuffer(float af[])
    {
        if (polygonColorsPool.containsKey(Integer.valueOf(af.length)))
        {
            FloatBuffer floatbuffer1 = (FloatBuffer)polygonColorsPool.get(Integer.valueOf(af.length));
            floatbuffer1.clear();
            floatbuffer1.put(af);
            floatbuffer1.position(0);
            return floatbuffer1;
        } else
        {
            FloatBuffer floatbuffer = makeFloatBuffer(af);
            polygonColorsPool.put(Integer.valueOf(af.length), floatbuffer);
            return floatbuffer;
        }
    }

    public static final FloatBuffer makeFloatBuffer(float af[])
    {
        ByteBuffer bytebuffer = ByteBuffer.allocateDirect(4 * af.length);
        bytebuffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatbuffer = bytebuffer.asFloatBuffer();
        floatbuffer.put(af);
        floatbuffer.position(0);
        return floatbuffer;
    }

    public static final FloatBuffer makeTexCoordsBuffer(float af[])
    {
        if (texCoordsPool.containsKey(Integer.valueOf(af.length)))
        {
            FloatBuffer floatbuffer1 = (FloatBuffer)texCoordsPool.get(Integer.valueOf(af.length));
            floatbuffer1.clear();
            floatbuffer1.put(af);
            floatbuffer1.position(0);
            return floatbuffer1;
        } else
        {
            FloatBuffer floatbuffer = makeFloatBuffer(af);
            texCoordsPool.put(Integer.valueOf(af.length), floatbuffer);
            return floatbuffer;
        }
    }

    public static final FloatBuffer makeVerticesBuffer(float af[])
    {
        if (polygonVerticesPool.containsKey(Integer.valueOf(af.length)))
        {
            FloatBuffer floatbuffer1 = (FloatBuffer)polygonVerticesPool.get(Integer.valueOf(af.length));
            floatbuffer1.clear();
            floatbuffer1.put(af);
            floatbuffer1.position(0);
            return floatbuffer1;
        } else
        {
            FloatBuffer floatbuffer = makeFloatBuffer(af);
            polygonVerticesPool.put(Integer.valueOf(af.length), floatbuffer);
            return floatbuffer;
        }
    }

    static 
    {
        options = new android.graphics.BitmapFactory.Options();
        options.inScaled = false;
        options.inPreferredConfig = android.graphics.Bitmap.Config.ARGB_8888;
    }
}
