// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

// Referenced classes of package com.google.android.gms.common.data:
//            b

public class a
    implements SafeParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new b();
    final int AT;
    ParcelFileDescriptor Ew;
    private Bitmap Ex;
    private boolean Ey;
    private File Ez;
    final int xM;

    a(int i, ParcelFileDescriptor parcelfiledescriptor, int j)
    {
        xM = i;
        Ew = parcelfiledescriptor;
        AT = j;
        Ex = null;
        Ey = false;
    }

    public a(Bitmap bitmap)
    {
        xM = 1;
        Ew = null;
        AT = 0;
        Ex = bitmap;
        Ey = true;
    }

    private void a(Closeable closeable)
    {
        try
        {
            closeable.close();
            return;
        }
        catch (IOException ioexception)
        {
            Log.w("BitmapTeleporter", "Could not close stream", ioexception);
        }
    }

    private FileOutputStream eT()
    {
        if (Ez == null)
        {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
        }
        File file;
        FileOutputStream fileoutputstream;
        try
        {
            file = File.createTempFile("teleporter", ".tmp", Ez);
        }
        catch (IOException ioexception)
        {
            throw new IllegalStateException("Could not create temporary file", ioexception);
        }
        try
        {
            fileoutputstream = new FileOutputStream(file);
            Ew = ParcelFileDescriptor.open(file, 0x10000000);
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            throw new IllegalStateException("Temporary file is somehow already deleted");
        }
        file.delete();
        return fileoutputstream;
    }

    public void a(File file)
    {
        if (file == null)
        {
            throw new NullPointerException("Cannot set null temp directory");
        } else
        {
            Ez = file;
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public Bitmap eS()
    {
        DataInputStream datainputstream;
        if (Ey)
        {
            break MISSING_BLOCK_LABEL_103;
        }
        datainputstream = new DataInputStream(new android.os.ParcelFileDescriptor.AutoCloseInputStream(Ew));
        byte abyte0[];
        int i;
        int j;
        android.graphics.Bitmap.Config config;
        abyte0 = new byte[datainputstream.readInt()];
        i = datainputstream.readInt();
        j = datainputstream.readInt();
        config = android.graphics.Bitmap.Config.valueOf(datainputstream.readUTF());
        datainputstream.read(abyte0);
        a(datainputstream);
        ByteBuffer bytebuffer = ByteBuffer.wrap(abyte0);
        Bitmap bitmap = Bitmap.createBitmap(i, j, config);
        bitmap.copyPixelsFromBuffer(bytebuffer);
        Ex = bitmap;
        Ey = true;
        return Ex;
        IOException ioexception;
        ioexception;
        throw new IllegalStateException("Could not read from parcel file descriptor", ioexception);
        Exception exception;
        exception;
        a(datainputstream);
        throw exception;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        Bitmap bitmap;
        byte abyte0[];
        DataOutputStream dataoutputstream;
        if (Ew != null)
        {
            break MISSING_BLOCK_LABEL_103;
        }
        bitmap = Ex;
        ByteBuffer bytebuffer = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
        bitmap.copyPixelsToBuffer(bytebuffer);
        abyte0 = bytebuffer.array();
        dataoutputstream = new DataOutputStream(eT());
        dataoutputstream.writeInt(abyte0.length);
        dataoutputstream.writeInt(bitmap.getWidth());
        dataoutputstream.writeInt(bitmap.getHeight());
        dataoutputstream.writeUTF(bitmap.getConfig().toString());
        dataoutputstream.write(abyte0);
        a(dataoutputstream);
        b.a(this, parcel, i);
        return;
        IOException ioexception;
        ioexception;
        throw new IllegalStateException("Could not write into unlinked file", ioexception);
        Exception exception;
        exception;
        a(dataoutputstream);
        throw exception;
    }

}
