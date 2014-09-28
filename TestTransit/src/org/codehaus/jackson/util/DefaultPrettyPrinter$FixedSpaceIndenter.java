// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.util;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.impl.Indenter;

// Referenced classes of package org.codehaus.jackson.util:
//            DefaultPrettyPrinter

public static class 
    implements Indenter
{

    public boolean isInline()
    {
        return true;
    }

    public void writeIndentation(JsonGenerator jsongenerator, int i)
        throws IOException, JsonGenerationException
    {
        jsongenerator.writeRaw(' ');
    }

    public ()
    {
    }
}
