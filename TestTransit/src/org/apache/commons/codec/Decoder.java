// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.apache.commons.codec;


// Referenced classes of package org.apache.commons.codec:
//            DecoderException

public interface Decoder
{

    public abstract Object decode(Object obj)
        throws DecoderException;
}
