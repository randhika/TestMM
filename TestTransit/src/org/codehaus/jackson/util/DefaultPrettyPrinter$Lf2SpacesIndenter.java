// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.util;

import java.io.IOException;
import java.util.Arrays;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.impl.Indenter;

// Referenced classes of package org.codehaus.jackson.util:
//            DefaultPrettyPrinter

public static class 
    implements Indenter
{

    static final char SPACES[];
    static final int SPACE_COUNT = 64;
    static final String SYSTEM_LINE_SEPARATOR;

    public boolean isInline()
    {
        return false;
    }

    public void writeIndentation(JsonGenerator jsongenerator, int i)
        throws IOException, JsonGenerationException
    {
        jsongenerator.writeRaw(SYSTEM_LINE_SEPARATOR);
        int j;
        for (j = i + i; j > 64; j -= SPACES.length)
        {
            jsongenerator.writeRaw(SPACES, 0, 64);
        }

        jsongenerator.writeRaw(SPACES, 0, j);
    }

    static 
    {
        String s1 = System.getProperty("line.separator");
        String s = s1;
_L2:
        if (s == null)
        {
            s = "\n";
        }
        SYSTEM_LINE_SEPARATOR = s;
        SPACES = new char[64];
        Arrays.fill(SPACES, ' ');
        return;
        Throwable throwable;
        throwable;
        s = null;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public ()
    {
    }
}
