// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.util;

import java.io.IOException;
import java.util.Arrays;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.PrettyPrinter;
import org.codehaus.jackson.impl.Indenter;

public class DefaultPrettyPrinter
    implements PrettyPrinter
{
    public static class FixedSpaceIndenter
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

        public FixedSpaceIndenter()
        {
        }
    }

    public static class Lf2SpacesIndenter
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

        public Lf2SpacesIndenter()
        {
        }
    }

    public static class NopIndenter
        implements Indenter
    {

        public boolean isInline()
        {
            return true;
        }

        public void writeIndentation(JsonGenerator jsongenerator, int i)
        {
        }

        public NopIndenter()
        {
        }
    }


    protected Indenter _arrayIndenter;
    protected int _nesting;
    protected Indenter _objectIndenter;
    protected boolean _spacesInObjectEntries;

    public DefaultPrettyPrinter()
    {
        _arrayIndenter = new FixedSpaceIndenter();
        _objectIndenter = new Lf2SpacesIndenter();
        _spacesInObjectEntries = true;
        _nesting = 0;
    }

    public void beforeArrayValues(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException
    {
        _arrayIndenter.writeIndentation(jsongenerator, _nesting);
    }

    public void beforeObjectEntries(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException
    {
        _objectIndenter.writeIndentation(jsongenerator, _nesting);
    }

    public void indentArraysWith(Indenter indenter)
    {
        if (indenter == null)
        {
            indenter = new NopIndenter();
        }
        _arrayIndenter = indenter;
    }

    public void indentObjectsWith(Indenter indenter)
    {
        if (indenter == null)
        {
            indenter = new NopIndenter();
        }
        _objectIndenter = indenter;
    }

    public void spacesInObjectEntries(boolean flag)
    {
        _spacesInObjectEntries = flag;
    }

    public void writeArrayValueSeparator(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException
    {
        jsongenerator.writeRaw(',');
        _arrayIndenter.writeIndentation(jsongenerator, _nesting);
    }

    public void writeEndArray(JsonGenerator jsongenerator, int i)
        throws IOException, JsonGenerationException
    {
        if (!_arrayIndenter.isInline())
        {
            _nesting = -1 + _nesting;
        }
        if (i > 0)
        {
            _arrayIndenter.writeIndentation(jsongenerator, _nesting);
        } else
        {
            jsongenerator.writeRaw(' ');
        }
        jsongenerator.writeRaw(']');
    }

    public void writeEndObject(JsonGenerator jsongenerator, int i)
        throws IOException, JsonGenerationException
    {
        if (!_objectIndenter.isInline())
        {
            _nesting = -1 + _nesting;
        }
        if (i > 0)
        {
            _objectIndenter.writeIndentation(jsongenerator, _nesting);
        } else
        {
            jsongenerator.writeRaw(' ');
        }
        jsongenerator.writeRaw('}');
    }

    public void writeObjectEntrySeparator(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException
    {
        jsongenerator.writeRaw(',');
        _objectIndenter.writeIndentation(jsongenerator, _nesting);
    }

    public void writeObjectFieldValueSeparator(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException
    {
        if (_spacesInObjectEntries)
        {
            jsongenerator.writeRaw(" : ");
            return;
        } else
        {
            jsongenerator.writeRaw(':');
            return;
        }
    }

    public void writeRootValueSeparator(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException
    {
        jsongenerator.writeRaw(' ');
    }

    public void writeStartArray(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException
    {
        if (!_arrayIndenter.isInline())
        {
            _nesting = 1 + _nesting;
        }
        jsongenerator.writeRaw('[');
    }

    public void writeStartObject(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException
    {
        jsongenerator.writeRaw('{');
        if (!_objectIndenter.isInline())
        {
            _nesting = 1 + _nesting;
        }
    }
}
