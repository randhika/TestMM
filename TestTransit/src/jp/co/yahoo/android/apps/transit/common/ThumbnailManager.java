// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import java.io.File;
import java.io.FileDescriptor;
import jp.co.yahoo.android.apps.transit.common.util.FileUtil;

// Referenced classes of package jp.co.yahoo.android.apps.transit.common:
//            ImageWorker, ImageCache

public class ThumbnailManager extends ImageWorker
{

    private static final ThumbnailManager instance = new ThumbnailManager();
    private Context mContext;
    protected int mSampledImageHeight;
    protected int mSampledImageWidth;

    private ThumbnailManager()
    {
        mSampledImageWidth = 100;
        mSampledImageHeight = 100;
        setImageCache(new ImageCache());
    }

    public static int calculateInSampleSize(android.graphics.BitmapFactory.Options options, int i, int j)
    {
        int k = options.outHeight;
        int l = options.outWidth;
        int i1 = 1;
        if (k > j || l > i)
        {
            int j1 = Math.round((float)k / (float)j);
            int k1 = Math.round((float)l / (float)i);
            float f;
            if (j1 < k1)
            {
                i1 = j1;
            } else
            {
                i1 = k1;
            }
            f = l * k;
            for (float f1 = 2 * (i * j); f / (float)(i1 * i1) > f1; i1++) { }
        }
        return i1;
    }

    public static Bitmap createVideoThumbnail(String s)
    {
        return ThumbnailUtils.createVideoThumbnail(s, 3);
    }

    public static Bitmap decodeSampledBitmapFromDescriptor(FileDescriptor filedescriptor, int i, int j)
    {
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(filedescriptor, null, options);
        options.inSampleSize = calculateInSampleSize(options, i, j);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(filedescriptor, null, options);
    }

    public static Bitmap decodeSampledBitmapFromFile(String s, int i, int j, boolean flag)
    {
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(s, options);
        options.inSampleSize = calculateInSampleSize(options, i, j);
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(s, options);
        if (flag)
        {
            float f = getRotateDegree(s);
            boolean flag1 = getFlip(s);
            if (flag1 || f != 0.0F)
            {
                Matrix matrix = new Matrix();
                if (flag1)
                {
                    matrix.preScale(-1F, 1.0F);
                }
                if (f != 0.0F)
                {
                    matrix.postRotate(f);
                }
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            }
        }
        return bitmap;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources resources, int i, int j, int k)
    {
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inSampleSize = calculateInSampleSize(options, j, k);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, i, options);
    }

    public static boolean getFlip(String s)
    {
        int i;
        try
        {
            i = (new ExifInterface(s)).getAttributeInt("Orientation", 0);
        }
        catch (Exception exception)
        {
            return false;
        }
        switch (i)
        {
        case 3: // '\003'
        case 6: // '\006'
        default:
            return false;

        case 2: // '\002'
        case 4: // '\004'
        case 5: // '\005'
        case 7: // '\007'
            return true;
        }
    }

    public static ThumbnailManager getInstance()
    {
        return instance;
    }

    public static int getRotateDegree(String s)
    {
        int i;
        try
        {
            i = (new ExifInterface(s)).getAttributeInt("Orientation", 0);
        }
        catch (Exception exception)
        {
            return 0;
        }
        switch (i)
        {
        default:
            return 0;

        case 6: // '\006'
        case 7: // '\007'
            return 90;

        case 3: // '\003'
        case 4: // '\004'
            return 180;

        case 5: // '\005'
        case 8: // '\b'
            return 270;
        }
    }

    public static Bitmap loadAppIcon(Context context, String s)
    {
        PackageInfo packageinfo = context.getPackageManager().getPackageArchiveInfo(s, 1);
        if (packageinfo != null)
        {
            ApplicationInfo applicationinfo = packageinfo.applicationInfo;
            if (android.os.Build.VERSION.SDK_INT >= 8)
            {
                applicationinfo.sourceDir = s;
                applicationinfo.publicSourceDir = s;
            }
            return ((BitmapDrawable)applicationinfo.loadIcon(context.getPackageManager())).getBitmap();
        } else
        {
            return null;
        }
    }

    private Bitmap loadAppIcon(String s)
    {
        return loadAppIcon(mContext, s);
    }

    private Bitmap processBitmap(File file)
    {
        String s = FileUtil.getMimeType(file);
        if (s.equals("application/vnd.android.package-archive"))
        {
            return loadAppIcon(file.getPath());
        }
        if (s.startsWith("video/"))
        {
            return createVideoThumbnail(file.getPath());
        }
        if (s.startsWith("image/") || s.startsWith("drawing/"))
        {
            return decodeSampledBitmapFromFile(file.getPath(), mSampledImageWidth, mSampledImageHeight, true);
        }
        if (s.startsWith("audio/"))
        {
            return createAudioThumbnail(file.getPath());
        } else
        {
            return null;
        }
    }

    public Bitmap createAudioThumbnail(String s)
    {
        MediaMetadataRetriever mediametadataretriever = new MediaMetadataRetriever();
        File file = new File(s);
        mediametadataretriever.setDataSource(mContext, Uri.fromFile(file));
        return mediametadataretriever.getFrameAtTime(0L, 0);
    }

    public void init(Context context)
    {
        mContext = context;
        mResources = context.getResources();
    }

    protected Bitmap processBitmap(Object obj)
    {
        return processBitmap((File)obj);
    }

    public void setImageSize(int i)
    {
        setImageSize(i, i);
    }

    public void setImageSize(int i, int j)
    {
        if (i != 0)
        {
            mSampledImageWidth = i;
        }
        if (j != 0)
        {
            mSampledImageHeight = j;
        }
    }

}
