// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.smile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

// Referenced classes of package org.codehaus.jackson.smile:
//            SmileFactory

public class Tool
{

    public static final String SUFFIX = ".lzf";
    public final JsonFactory jsonFactory = new JsonFactory();
    public final SmileFactory smileFactory = new SmileFactory();

    public Tool()
    {
        smileFactory.configure(SmileGenerator.Feature.CHECK_SHARED_NAMES, true);
        smileFactory.configure(SmileGenerator.Feature.CHECK_SHARED_STRING_VALUES, true);
        smileFactory.configure(SmileGenerator.Feature.ENCODE_BINARY_AS_7BIT, true);
        smileFactory.configure(SmileGenerator.Feature.WRITE_HEADER, true);
        smileFactory.configure(SmileGenerator.Feature.WRITE_END_MARKER, false);
        smileFactory.configure(SmileParser.Feature.REQUIRE_HEADER, false);
    }

    private void decode(InputStream inputstream)
        throws IOException
    {
        SmileParser smileparser = smileFactory.createJsonParser(inputstream);
        JsonGenerator jsongenerator = jsonFactory.createJsonGenerator(System.out, JsonEncoding.UTF8);
        do
        {
            if (smileparser.nextToken() == null && smileparser.nextToken() == null)
            {
                smileparser.close();
                jsongenerator.close();
                return;
            }
            jsongenerator.copyCurrentEvent(smileparser);
        } while (true);
    }

    private void encode(InputStream inputstream)
        throws IOException
    {
        JsonParser jsonparser = jsonFactory.createJsonParser(inputstream);
        SmileGenerator smilegenerator = smileFactory.createJsonGenerator(System.out, JsonEncoding.UTF8);
        for (; jsonparser.nextToken() != null; smilegenerator.copyCurrentEvent(jsonparser)) { }
        jsonparser.close();
        smilegenerator.close();
    }

    private InputStream inputStream(String s)
        throws IOException
    {
        if (s == null)
        {
            return System.in;
        }
        File file = new File(s);
        if (!file.exists())
        {
            System.err.println((new StringBuilder()).append("File '").append(s).append("' does not exist.").toString());
            System.exit(1);
        }
        return new FileInputStream(file);
    }

    public static void main(String args[])
        throws IOException
    {
        (new Tool()).process(args);
    }

    private void process(String as[])
        throws IOException
    {
        String s;
        String s1;
        if (as.length == 2)
        {
            s1 = as[0];
            s = as[1];
        } else
        if (as.length == 1)
        {
            s1 = as[0];
            s = null;
        } else
        {
            showUsage();
            s = null;
            s1 = null;
        }
        if ("-e".equals(s1))
        {
            encode(inputStream(s));
            return;
        }
        if ("-d".equals(s1))
        {
            decode(inputStream(s));
            return;
        }
        if ("-v".equals(s1))
        {
            verify(inputStream(s), inputStream(s));
            return;
        } else
        {
            showUsage();
            return;
        }
    }

    private void verify(InputStream inputstream, InputStream inputstream1)
        throws IOException
    {
        JsonParser jsonparser = jsonFactory.createJsonParser(inputstream);
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(4000);
        SmileGenerator smilegenerator = smileFactory.createJsonGenerator(bytearrayoutputstream, JsonEncoding.UTF8);
        for (; jsonparser.nextToken() != null; smilegenerator.copyCurrentEvent(jsonparser)) { }
        jsonparser.close();
        smilegenerator.close();
        JsonParser jsonparser1 = jsonFactory.createJsonParser(inputstream1);
        byte abyte0[] = bytearrayoutputstream.toByteArray();
        SmileParser smileparser = smileFactory.createJsonParser(abyte0);
        int i = 0;
        do
        {
            org.codehaus.jackson.JsonToken jsontoken = jsonparser1.nextToken();
            if (jsontoken != null)
            {
                org.codehaus.jackson.JsonToken jsontoken1 = smileparser.nextToken();
                i++;
                if (jsontoken != jsontoken1)
                {
                    throw new IOException((new StringBuilder()).append("Input and encoded differ, token #").append(i).append("; expected ").append(jsontoken).append(", got ").append(jsontoken1).toString());
                }
                String s = jsonparser1.getText();
                String s1 = smileparser.getText();
                if (!s.equals(s1))
                {
                    throw new IOException((new StringBuilder()).append("Input and encoded differ, token #").append(i).append("; expected text '").append(s).append("', got '").append(s1).append("'").toString());
                }
            } else
            {
                System.out.println((new StringBuilder()).append("OK: verified ").append(i).append(" tokens (from ").append(abyte0.length).append(" bytes of Smile encoded data), input and encoded contents are identical").toString());
                return;
            }
        } while (true);
    }

    protected void showUsage()
    {
        System.err.println((new StringBuilder()).append("Usage: java ").append(getClass().getName()).append(" -e/-d [file]").toString());
        System.err.println(" (if no file given, reads from stdin -- always writes to stdout)");
        System.err.println(" -d: decode Smile encoded input as JSON");
        System.err.println(" -e: encode JSON (text) input as Smile");
        System.err.println(" -v: encode JSON (text) input as Smile; read back, verify, do not write out");
        System.exit(1);
    }
}
