// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.maps.ar;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;

public class TextureManager
{

    private static Map mTextures = new Hashtable();

    public TextureManager()
    {
    }

    public static final void addTexture(int i, int j)
    {
        mTextures.put(Integer.valueOf(i), Integer.valueOf(j));
    }

    public static final void deleteAll(GL10 gl10)
    {
        Iterator iterator = (new ArrayList(mTextures.keySet())).iterator();
        do
        {
            if (!iterator.hasNext())
            {
                return;
            }
            deleteTexture(gl10, ((Integer)iterator.next()).intValue());
        } while (true);
    }

    public static final void deleteTexture(GL10 gl10, int i)
    {
        if (mTextures.containsKey(Integer.valueOf(i)))
        {
            int ai[] = new int[1];
            ai[0] = ((Integer)mTextures.get(Integer.valueOf(i))).intValue();
            gl10.glDeleteTextures(1, ai, 0);
            mTextures.remove(Integer.valueOf(i));
        }
    }

}
