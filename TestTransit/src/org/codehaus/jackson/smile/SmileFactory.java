// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.smile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.format.InputAccessor;
import org.codehaus.jackson.format.MatchStrength;
import org.codehaus.jackson.io.IOContext;

// Referenced classes of package org.codehaus.jackson.smile:
//            SmileGenerator, SmileParserBootstrapper, SmileParser

public class SmileFactory extends JsonFactory
{

    static final int DEFAULT_SMILE_GENERATOR_FEATURE_FLAGS = 0;
    static final int DEFAULT_SMILE_PARSER_FEATURE_FLAGS = 0;
    public static final String FORMAT_NAME_SMILE = "Smile";
    protected boolean _cfgDelegateToTextual;
    protected int _smileGeneratorFeatures;
    protected int _smileParserFeatures;

    public SmileFactory()
    {
        this(null);
    }

    public SmileFactory(ObjectCodec objectcodec)
    {
        super(objectcodec);
        _smileParserFeatures = DEFAULT_SMILE_PARSER_FEATURE_FLAGS;
        _smileGeneratorFeatures = DEFAULT_SMILE_GENERATOR_FEATURE_FLAGS;
    }

    protected JsonGenerator _createJsonGenerator(Writer writer, IOContext iocontext)
        throws IOException
    {
        if (_cfgDelegateToTextual)
        {
            return super._createJsonGenerator(writer, iocontext);
        } else
        {
            throw new UnsupportedOperationException("Can not create generator for non-byte-based target");
        }
    }

    protected SmileGenerator _createJsonGenerator(OutputStream outputstream, IOContext iocontext)
        throws IOException
    {
        int i = _smileGeneratorFeatures;
        SmileGenerator smilegenerator = new SmileGenerator(iocontext, _generatorFeatures, i, _objectCodec, outputstream);
        if ((i & SmileGenerator.Feature.WRITE_HEADER.getMask()) != 0)
        {
            smilegenerator.writeHeader();
        } else
        {
            if ((i & SmileGenerator.Feature.CHECK_SHARED_STRING_VALUES.getMask()) != 0)
            {
                throw new JsonGenerationException("Inconsistent settings: WRITE_HEADER disabled, but CHECK_SHARED_STRING_VALUES enabled; can not construct generator due to possible data loss (either enable WRITE_HEADER, or disable CHECK_SHARED_STRING_VALUES to resolve)");
            }
            if ((i & SmileGenerator.Feature.ENCODE_BINARY_AS_7BIT.getMask()) == 0)
            {
                throw new JsonGenerationException("Inconsistent settings: WRITE_HEADER disabled, but ENCODE_BINARY_AS_7BIT disabled; can not construct generator due to possible data loss (either enable WRITE_HEADER, or ENCODE_BINARY_AS_7BIT to resolve)");
            }
        }
        return smilegenerator;
    }

    protected volatile JsonParser _createJsonParser(InputStream inputstream, IOContext iocontext)
        throws IOException, JsonParseException
    {
        return _createJsonParser(inputstream, iocontext);
    }

    protected JsonParser _createJsonParser(Reader reader, IOContext iocontext)
        throws IOException, JsonParseException
    {
        if (_cfgDelegateToTextual)
        {
            return super._createJsonParser(reader, iocontext);
        } else
        {
            throw new UnsupportedOperationException("Can not create generator for non-byte-based target");
        }
    }

    protected volatile JsonParser _createJsonParser(byte abyte0[], int i, int j, IOContext iocontext)
        throws IOException, JsonParseException
    {
        return _createJsonParser(abyte0, i, j, iocontext);
    }

    protected SmileParser _createJsonParser(InputStream inputstream, IOContext iocontext)
        throws IOException, JsonParseException
    {
        return (new SmileParserBootstrapper(iocontext, inputstream)).constructParser(_parserFeatures, _smileParserFeatures, _objectCodec, _rootByteSymbols);
    }

    protected SmileParser _createJsonParser(byte abyte0[], int i, int j, IOContext iocontext)
        throws IOException, JsonParseException
    {
        return (new SmileParserBootstrapper(iocontext, abyte0, i, j)).constructParser(_parserFeatures, _smileParserFeatures, _objectCodec, _rootByteSymbols);
    }

    protected Writer _createWriter(OutputStream outputstream, JsonEncoding jsonencoding, IOContext iocontext)
        throws IOException
    {
        if (_cfgDelegateToTextual)
        {
            return super._createWriter(outputstream, jsonencoding, iocontext);
        } else
        {
            throw new UnsupportedOperationException("Can not create generator for non-byte-based target");
        }
    }

    public final SmileFactory configure(SmileGenerator.Feature feature, boolean flag)
    {
        if (flag)
        {
            enable(feature);
            return this;
        } else
        {
            disable(feature);
            return this;
        }
    }

    public final SmileFactory configure(SmileParser.Feature feature, boolean flag)
    {
        if (flag)
        {
            enable(feature);
            return this;
        } else
        {
            disable(feature);
            return this;
        }
    }

    public volatile JsonGenerator createJsonGenerator(OutputStream outputstream)
        throws IOException
    {
        return createJsonGenerator(outputstream);
    }

    public volatile JsonGenerator createJsonGenerator(OutputStream outputstream, JsonEncoding jsonencoding)
        throws IOException
    {
        return createJsonGenerator(outputstream, jsonencoding);
    }

    public SmileGenerator createJsonGenerator(OutputStream outputstream)
        throws IOException
    {
        return _createJsonGenerator(outputstream, _createContext(outputstream, false));
    }

    public SmileGenerator createJsonGenerator(OutputStream outputstream, JsonEncoding jsonencoding)
        throws IOException
    {
        return createJsonGenerator(outputstream);
    }

    public volatile JsonParser createJsonParser(File file)
        throws IOException, JsonParseException
    {
        return createJsonParser(file);
    }

    public volatile JsonParser createJsonParser(InputStream inputstream)
        throws IOException, JsonParseException
    {
        return createJsonParser(inputstream);
    }

    public volatile JsonParser createJsonParser(URL url)
        throws IOException, JsonParseException
    {
        return createJsonParser(url);
    }

    public volatile JsonParser createJsonParser(byte abyte0[])
        throws IOException, JsonParseException
    {
        return createJsonParser(abyte0);
    }

    public volatile JsonParser createJsonParser(byte abyte0[], int i, int j)
        throws IOException, JsonParseException
    {
        return createJsonParser(abyte0, i, j);
    }

    public SmileParser createJsonParser(File file)
        throws IOException, JsonParseException
    {
        return _createJsonParser(new FileInputStream(file), _createContext(file, true));
    }

    public SmileParser createJsonParser(InputStream inputstream)
        throws IOException, JsonParseException
    {
        return _createJsonParser(inputstream, _createContext(inputstream, false));
    }

    public SmileParser createJsonParser(URL url)
        throws IOException, JsonParseException
    {
        return _createJsonParser(_optimizedStreamFromURL(url), _createContext(url, true));
    }

    public SmileParser createJsonParser(byte abyte0[])
        throws IOException, JsonParseException
    {
        return _createJsonParser(abyte0, 0, abyte0.length, _createContext(abyte0, true));
    }

    public SmileParser createJsonParser(byte abyte0[], int i, int j)
        throws IOException, JsonParseException
    {
        return _createJsonParser(abyte0, i, j, _createContext(abyte0, true));
    }

    public void delegateToTextual(boolean flag)
    {
        _cfgDelegateToTextual = flag;
    }

    public SmileFactory disable(SmileGenerator.Feature feature)
    {
        _smileGeneratorFeatures = _smileGeneratorFeatures & (-1 ^ feature.getMask());
        return this;
    }

    public SmileFactory disable(SmileParser.Feature feature)
    {
        _smileParserFeatures = _smileParserFeatures & (-1 ^ feature.getMask());
        return this;
    }

    public SmileFactory enable(SmileGenerator.Feature feature)
    {
        _smileGeneratorFeatures = _smileGeneratorFeatures | feature.getMask();
        return this;
    }

    public SmileFactory enable(SmileParser.Feature feature)
    {
        _smileParserFeatures = _smileParserFeatures | feature.getMask();
        return this;
    }

    public String getFormatName()
    {
        return "Smile";
    }

    public MatchStrength hasFormat(InputAccessor inputaccessor)
        throws IOException
    {
        return SmileParserBootstrapper.hasSmileFormat(inputaccessor);
    }

    public final boolean isEnabled(SmileGenerator.Feature feature)
    {
        return (_smileGeneratorFeatures & feature.getMask()) != 0;
    }

    public final boolean isEnabled(SmileParser.Feature feature)
    {
        return (_smileParserFeatures & feature.getMask()) != 0;
    }

    static 
    {
        DEFAULT_SMILE_PARSER_FEATURE_FLAGS = SmileParser.Feature.collectDefaults();
        DEFAULT_SMILE_GENERATOR_FEATURE_FLAGS = SmileGenerator.Feature.collectDefaults();
    }
}
