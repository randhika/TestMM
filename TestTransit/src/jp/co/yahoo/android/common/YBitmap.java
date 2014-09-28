// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package jp.co.yahoo.android.common:
//            YLogger

public class YBitmap
{

    public static final android.graphics.Bitmap.Config DEFAULT_BITMAP_CONFIG;
    public static final android.graphics.Bitmap.Config FAST_BITMAP_CONFIG;

    public YBitmap()
    {
    }

    public static String bitmap2base64(Bitmap bitmap, android.graphics.Bitmap.CompressFormat compressformat, int i)
    {
        return "";
    }

    public static byte[] bitmap2data(Bitmap bitmap)
    {
        return bitmap2data(bitmap, android.graphics.Bitmap.CompressFormat.JPEG, 100);
    }

    public static byte[] bitmap2data(Bitmap bitmap, android.graphics.Bitmap.CompressFormat compressformat, int i)
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        bitmap.compress(compressformat, i, bytearrayoutputstream);
        byte abyte0[] = bytearrayoutputstream.toByteArray();
        try
        {
            bytearrayoutputstream.close();
        }
        catch (IOException ioexception)
        {
            return abyte0;
        }
        return abyte0;
    }

    public static Drawable bitmap2drawable(Bitmap bitmap)
    {
        return (new BitmapDrawable(bitmap)).getCurrent();
    }

    public static Bitmap createBitmap(Context context, int i)
    {
        Bitmap bitmap;
        try
        {
            bitmap = BitmapFactory.decodeResource(context.getResources(), i);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YLogger.e(outofmemoryerror.toString());
            return null;
        }
        return bitmap;
    }

    public static Bitmap createBitmap(String s)
    {
        return null;
    }

    public static Bitmap createBitmap(byte abyte0[])
    {
        Bitmap bitmap;
        try
        {
            bitmap = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YLogger.e(outofmemoryerror.toString());
            return null;
        }
        return bitmap;
    }

    public static Bitmap createBitmap(byte abyte0[], android.graphics.BitmapFactory.Options options)
    {
        Bitmap bitmap;
        try
        {
            bitmap = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length, options);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YLogger.e(outofmemoryerror.toString());
            return null;
        }
        return bitmap;
    }

    public static Bitmap createHorizontalCompositionBitmap(List list, int i, int j, int k)
    {
        Bitmap abitmap[];
        int l1;
        ArrayList arraylist;
        ArrayList arraylist1;
        int i3;
        Bitmap bitmap;
        abitmap = new Bitmap[list.size()];
        int l = list.size();
        for (int i1 = 0; i1 < l; i1++)
        {
            abitmap[i1] = (Bitmap)list.get(i1);
        }

        int j1 = 0;
        int k1 = 0;
        l1 = abitmap.length;
        arraylist = new ArrayList();
        arraylist1 = new ArrayList();
        int i2 = 0;
        int j2 = 0;
        for (int k2 = 0; k2 < l1; k2++)
        {
            int k4 = abitmap[k2].getWidth();
            int l4 = j2 + k4;
            if (l4 > i && k2 > 0)
            {
                arraylist.add(Integer.valueOf(k2));
                arraylist1.add(Integer.valueOf(i2));
                if (j1 == 0)
                {
                    j1 = l4 - k4 - j;
                }
                k1 += i2 + 1;
                l4 = k4;
                i2 = 0;
            }
            j2 = l4 + j;
            i2 = Math.max(i2, abitmap[k2].getHeight());
        }

        if (j1 == 0)
        {
            j1 = j2;
        }
        int l2 = k1 + i2;
        i3 = arraylist.size();
        if (i3 > 0)
        {
            l2 += -1 + k * i3;
        }
        android.graphics.Bitmap.Config config = DEFAULT_BITMAP_CONFIG;
        bitmap = wrappedCreateBitmap(j1, l2, config);
        if (bitmap != null) goto _L2; else goto _L1
_L1:
        bitmap = null;
_L4:
        return bitmap;
_L2:
        Canvas canvas;
        int j3;
        int k3;
        int l3;
        int i4;
        canvas = new Canvas(bitmap);
        j3 = 0;
        k3 = 0;
        l3 = 0;
        i4 = 0;
_L5:
        if (i4 >= l1) goto _L4; else goto _L3
_L3:
        int j4 = 0;
_L6:
label0:
        {
            if (j4 < i3)
            {
                if (i4 != ((Integer)arraylist.get(j4)).intValue())
                {
                    break label0;
                }
                k3 += k + ((Integer)arraylist1.get(l3)).intValue();
                j3 = 0;
                l3++;
            }
            canvas.drawBitmap(abitmap[i4], j3, k3, null);
            j3 += j + abitmap[i4].getWidth();
            i4++;
        }
          goto _L5
          goto _L4
        j4++;
          goto _L6
    }

    public static Bitmap createHorizontalCompositionBitmap(Bitmap abitmap[], int i)
    {
        return createHorizontalCompositionBitmap(abitmap, i, 0, 0);
    }

    public static Bitmap createHorizontalCompositionBitmap(Bitmap abitmap[], int i, int j, int k)
    {
        ArrayList arraylist = new ArrayList();
        int l = abitmap.length;
        for (int i1 = 0; i1 < l; i1++)
        {
            if (abitmap[i1] != null)
            {
                arraylist.add(abitmap[i1]);
            }
        }

        return createHorizontalCompositionBitmap(((List) (arraylist)), i, j, k);
    }

    public static Drawable createHorizontalCompositionDrawable(Bitmap abitmap[], int i)
    {
        return bitmap2drawable(createHorizontalCompositionBitmap(abitmap, i, 0, 0));
    }

    public static Drawable createHorizontalCompositionDrawable(Bitmap abitmap[], int i, int j, int k)
    {
        return bitmap2drawable(createHorizontalCompositionBitmap(abitmap, i, j, k));
    }

    public static Bitmap createSampleSizeBitmap(Context context, int i, int j, int k, android.graphics.BitmapFactory.Options options)
    {
        int l;
        Bitmap bitmap;
        int i1;
        l = j;
        bitmap = null;
        i1 = 0;
_L6:
        if (i1 >= k) goto _L2; else goto _L1
_L1:
        Bitmap bitmap1;
        options.inSampleSize = l;
        bitmap1 = BitmapFactory.decodeResource(context.getResources(), i, options);
        bitmap = bitmap1;
_L4:
        if (bitmap == null && !options.mCancel)
        {
            break; /* Loop/switch isn't completed */
        }
_L2:
        return bitmap;
        OutOfMemoryError outofmemoryerror;
        outofmemoryerror;
        YLogger.e(outofmemoryerror.toString());
        continue; /* Loop/switch isn't completed */
        Error error;
        error;
        YLogger.log((new StringBuilder()).append("Faild load image : inSampleSize=").append(l).toString());
        if (true) goto _L4; else goto _L3
_L3:
        l++;
        i1++;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static Bitmap createSampleSizeBitmap(String s, int i, int j, android.graphics.BitmapFactory.Options options)
    {
        int k;
        Bitmap bitmap;
        int l;
        k = i;
        bitmap = null;
        l = 0;
_L6:
        if (l >= j) goto _L2; else goto _L1
_L1:
        Bitmap bitmap1;
        options.inSampleSize = k;
        bitmap1 = BitmapFactory.decodeFile(s, options);
        bitmap = bitmap1;
_L4:
        if (bitmap == null && !options.mCancel)
        {
            break; /* Loop/switch isn't completed */
        }
_L2:
        return bitmap;
        OutOfMemoryError outofmemoryerror;
        outofmemoryerror;
        YLogger.e(outofmemoryerror.toString());
        continue; /* Loop/switch isn't completed */
        Error error;
        error;
        YLogger.log((new StringBuilder()).append("Faild load image : ").append(k).append(" ").append(s).toString());
        if (true) goto _L4; else goto _L3
_L3:
        k++;
        l++;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static Bitmap drawable2bitmap(Drawable drawable)
    {
        return ((BitmapDrawable)drawable).getBitmap();
    }

    public static Bitmap drawable2bitmap(Drawable drawable, int i, int j)
    {
        return drawable2bitmap(drawable, i, j, DEFAULT_BITMAP_CONFIG);
    }

    public static Bitmap drawable2bitmap(Drawable drawable, int i, int j, android.graphics.Bitmap.Config config)
    {
        Bitmap bitmap = wrappedCreateBitmap(i, j, config);
        if (bitmap == null)
        {
            return null;
        } else
        {
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, i, j);
            drawable.draw(canvas);
            return bitmap;
        }
    }

    public static android.graphics.BitmapFactory.Options getImageInfo(Context context, Bitmap bitmap)
    {
        android.graphics.BitmapFactory.Options options;
        options = null;
        if (bitmap == null)
        {
            break MISSING_BLOCK_LABEL_43;
        }
        android.graphics.BitmapFactory.Options options1 = new android.graphics.BitmapFactory.Options();
        options1.inJustDecodeBounds = true;
        byte abyte0[] = bitmap2data(bitmap, android.graphics.Bitmap.CompressFormat.JPEG, 100);
        BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length, options1);
        options = options1;
        return options;
        OutOfMemoryError outofmemoryerror;
        outofmemoryerror;
_L2:
        YLogger.e(outofmemoryerror.toString());
        return options;
        Exception exception1;
        exception1;
        return null;
        Exception exception;
        exception;
        return options1;
        outofmemoryerror;
        options = options1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static android.graphics.BitmapFactory.Options getImageInfo(Context context, Uri uri)
    {
        InputStream inputstream;
        android.graphics.BitmapFactory.Options options;
        inputstream = null;
        options = null;
        if (uri == null)
        {
            break MISSING_BLOCK_LABEL_51;
        }
        android.graphics.BitmapFactory.Options options1;
        inputstream = context.getContentResolver().openInputStream(uri);
        options1 = new android.graphics.BitmapFactory.Options();
        options1.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputstream, null, options1);
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_145;
        }
        try
        {
            inputstream.close();
        }
        catch (IOException ioexception3)
        {
            return options1;
        }
        options = options1;
_L2:
        return options;
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
_L7:
        YLogger.e(filenotfoundexception.toString());
        if (inputstream == null) goto _L2; else goto _L1
_L1:
        try
        {
            inputstream.close();
        }
        catch (IOException ioexception2)
        {
            return options;
        }
        return options;
        OutOfMemoryError outofmemoryerror;
        outofmemoryerror;
_L6:
        YLogger.e(outofmemoryerror.toString());
        if (inputstream == null) goto _L2; else goto _L3
_L3:
        try
        {
            inputstream.close();
        }
        catch (IOException ioexception1)
        {
            return options;
        }
        return options;
        Exception exception;
        exception;
_L5:
        if (inputstream != null)
        {
            try
            {
                inputstream.close();
            }
            catch (IOException ioexception) { }
        }
        throw exception;
        exception;
        if (true) goto _L5; else goto _L4
_L4:
        outofmemoryerror;
        options = options1;
          goto _L6
        filenotfoundexception;
        options = options1;
          goto _L7
        return options1;
    }

    public static Bitmap resizeBitmap(Bitmap bitmap, int i, int j)
    {
        int k = bitmap.getWidth();
        int l = bitmap.getHeight();
        float f = (float)i / (float)k;
        float f1 = (float)j / (float)l;
        Matrix matrix = new Matrix();
        matrix.postScale(f, f1);
        Bitmap bitmap1;
        try
        {
            bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, k, l, matrix, true);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YLogger.e(outofmemoryerror.toString());
            return null;
        }
        return bitmap1;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int i)
    {
        int j = bitmap.getWidth();
        int k = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        Bitmap bitmap1;
        try
        {
            bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, j, k, matrix, true);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YLogger.e(outofmemoryerror.toString());
            return null;
        }
        return bitmap1;
    }

    public static Bitmap roundCornerBitmap(Bitmap bitmap)
    {
        return roundCornerBitmap(bitmap, 8F);
    }

    public static Bitmap roundCornerBitmap(Bitmap bitmap, float f)
    {
        Bitmap bitmap1 = wrappedCreateBitmap(bitmap.getWidth(), bitmap.getHeight(), DEFAULT_BITMAP_CONFIG);
        if (bitmap1 == null)
        {
            return null;
        } else
        {
            Canvas canvas = new Canvas(bitmap1);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            RectF rectf = new RectF(rect);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(0xff424242);
            canvas.drawRoundRect(rectf, f, f, paint);
            paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            return bitmap1;
        }
    }

    public static Bitmap stream2bitmap(InputStream inputstream)
    {
        Bitmap bitmap;
        try
        {
            bitmap = BitmapFactory.decodeStream(new BufferedInputStream(inputstream));
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YLogger.e(outofmemoryerror.toString());
            return null;
        }
        return bitmap;
    }

    public static Bitmap stream2bitmap(InputStream inputstream, android.graphics.BitmapFactory.Options options)
    {
        Bitmap bitmap;
        try
        {
            bitmap = BitmapFactory.decodeStream(inputstream, null, options);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YLogger.e(outofmemoryerror.toString());
            return null;
        }
        return bitmap;
    }

    public static Drawable stream2drawable(InputStream inputstream)
    {
        Drawable drawable;
        try
        {
            drawable = Drawable.createFromStream(inputstream, "");
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YLogger.e(outofmemoryerror.toString());
            return null;
        }
        return drawable;
    }

    public static Bitmap trimBitmap(Bitmap bitmap, int i, int j, int k, int l)
    {
        Bitmap bitmap1;
        try
        {
            bitmap1 = Bitmap.createBitmap(bitmap, i, j, k, l);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YLogger.e(outofmemoryerror.toString());
            return null;
        }
        return bitmap1;
    }

    public static Bitmap wrappedCreateBitmap(int i, int j, android.graphics.Bitmap.Config config)
    {
        Bitmap bitmap;
        try
        {
            bitmap = Bitmap.createBitmap(i, j, config);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            YLogger.e(outofmemoryerror.toString());
            return null;
        }
        return bitmap;
    }

    static 
    {
        DEFAULT_BITMAP_CONFIG = android.graphics.Bitmap.Config.ARGB_4444;
        FAST_BITMAP_CONFIG = android.graphics.Bitmap.Config.RGB_565;
    }
}
