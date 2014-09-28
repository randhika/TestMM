// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.impl;

import java.io.IOException;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.JsonLocation;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.util.ByteArrayBuilder;
import org.codehaus.jackson.util.TextBuffer;
import org.codehaus.jackson.util.VersionUtil;

// Referenced classes of package org.codehaus.jackson.impl:
//            JsonParserMinimalBase, JsonReadContext

public abstract class JsonParserBase extends JsonParserMinimalBase
{

    protected byte _binaryValue[];
    protected ByteArrayBuilder _byteArrayBuilder;
    protected boolean _closed;
    protected long _currInputProcessed;
    protected int _currInputRow;
    protected int _currInputRowStart;
    protected int _inputEnd;
    protected int _inputPtr;
    protected final IOContext _ioContext;
    protected boolean _nameCopied;
    protected char _nameCopyBuffer[];
    protected JsonToken _nextToken;
    protected JsonReadContext _parsingContext;
    protected final TextBuffer _textBuffer;
    protected int _tokenInputCol;
    protected int _tokenInputRow;
    protected long _tokenInputTotal;

    protected JsonParserBase(IOContext iocontext, int i)
    {
        _inputPtr = 0;
        _inputEnd = 0;
        _currInputProcessed = 0L;
        _currInputRow = 1;
        _currInputRowStart = 0;
        _tokenInputTotal = 0L;
        _tokenInputRow = 1;
        _tokenInputCol = 0;
        _nameCopyBuffer = null;
        _nameCopied = false;
        _byteArrayBuilder = null;
        _features = i;
        _ioContext = iocontext;
        _textBuffer = iocontext.constructTextBuffer();
        _parsingContext = JsonReadContext.createRootContext(_tokenInputRow, _tokenInputCol);
    }

    protected abstract void _closeInput()
        throws IOException;

    protected abstract byte[] _decodeBase64(Base64Variant base64variant)
        throws IOException, JsonParseException;

    protected abstract void _finishString()
        throws IOException, JsonParseException;

    public ByteArrayBuilder _getByteArrayBuilder()
    {
        if (_byteArrayBuilder == null)
        {
            _byteArrayBuilder = new ByteArrayBuilder();
        } else
        {
            _byteArrayBuilder.reset();
        }
        return _byteArrayBuilder;
    }

    protected void _handleEOF()
        throws JsonParseException
    {
        if (!_parsingContext.inRoot())
        {
            _reportInvalidEOF((new StringBuilder()).append(": expected close marker for ").append(_parsingContext.getTypeDesc()).append(" (from ").append(_parsingContext.getStartLocation(_ioContext.getSourceReference())).append(")").toString());
        }
    }

    protected void _releaseBuffers()
        throws IOException
    {
        _textBuffer.releaseBuffers();
        char ac[] = _nameCopyBuffer;
        if (ac != null)
        {
            _nameCopyBuffer = null;
            _ioContext.releaseNameCopyBuffer(ac);
        }
    }

    protected void _reportMismatchedEndMarker(int i, char c)
        throws JsonParseException
    {
        String s = (new StringBuilder()).append("").append(_parsingContext.getStartLocation(_ioContext.getSourceReference())).toString();
        _reportError((new StringBuilder()).append("Unexpected close marker '").append((char)i).append("': expected '").append(c).append("' (for ").append(_parsingContext.getTypeDesc()).append(" starting at ").append(s).append(")").toString());
    }

    public void close()
        throws IOException
    {
        if (_closed)
        {
            break MISSING_BLOCK_LABEL_20;
        }
        _closed = true;
        _closeInput();
        _releaseBuffers();
        return;
        Exception exception;
        exception;
        _releaseBuffers();
        throw exception;
    }

    public JsonLocation getCurrentLocation()
    {
        int i = 1 + (_inputPtr - _currInputRowStart);
        return new JsonLocation(_ioContext.getSourceReference(), (_currInputProcessed + (long)_inputPtr) - 1L, _currInputRow, i);
    }

    public String getCurrentName()
        throws IOException, JsonParseException
    {
        if (_currToken == JsonToken.START_OBJECT || _currToken == JsonToken.START_ARRAY)
        {
            return _parsingContext.getParent().getCurrentName();
        } else
        {
            return _parsingContext.getCurrentName();
        }
    }

    public volatile JsonStreamContext getParsingContext()
    {
        return getParsingContext();
    }

    public JsonReadContext getParsingContext()
    {
        return _parsingContext;
    }

    public final long getTokenCharacterOffset()
    {
        return _tokenInputTotal;
    }

    public final int getTokenColumnNr()
    {
        return 1 + _tokenInputCol;
    }

    public final int getTokenLineNr()
    {
        return _tokenInputRow;
    }

    public JsonLocation getTokenLocation()
    {
        return new JsonLocation(_ioContext.getSourceReference(), getTokenCharacterOffset(), getTokenLineNr(), getTokenColumnNr());
    }

    public boolean hasTextCharacters()
    {
        if (_currToken == null) goto _L2; else goto _L1
_L1:
        static class _cls1
        {

            static final int $SwitchMap$org$codehaus$jackson$JsonToken[];

            static 
            {
                $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.org.codehaus.jackson.JsonToken[_currToken.ordinal()];
        JVM INSTR tableswitch 1 2: default 40
    //                   1 42
    //                   2 47;
           goto _L2 _L3 _L4
_L2:
        return false;
_L3:
        return _nameCopied;
_L4:
        return true;
    }

    public boolean isClosed()
    {
        return _closed;
    }

    protected abstract boolean loadMore()
        throws IOException;

    protected final void loadMoreGuaranteed()
        throws IOException
    {
        if (!loadMore())
        {
            _reportInvalidEOF();
        }
    }

    public Version version()
    {
        return VersionUtil.versionFor(getClass());
    }
}
