// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.apache.commons.codec;

import java.util.Comparator;

// Referenced classes of package org.apache.commons.codec:
//            EncoderException, Encoder, StringEncoder

public class StringEncoderComparator
    implements Comparator
{

    private StringEncoder stringEncoder;

    public StringEncoderComparator()
    {
    }

    public StringEncoderComparator(StringEncoder stringencoder)
    {
        stringEncoder = stringencoder;
    }

    public int compare(Object obj, Object obj1)
    {
        int i;
        try
        {
            i = ((Comparable)stringEncoder.encode(obj)).compareTo((Comparable)stringEncoder.encode(obj1));
        }
        catch (EncoderException encoderexception)
        {
            return 0;
        }
        return i;
    }
}
