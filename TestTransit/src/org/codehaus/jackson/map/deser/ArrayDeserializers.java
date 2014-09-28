// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.util.HashMap;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.Base64Variants;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.map.util.ObjectBuffer;

// Referenced classes of package org.codehaus.jackson.map.deser:
//            StdDeserializer

public class ArrayDeserializers
{
    static abstract class ArrayDeser extends StdDeserializer
    {

        public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
            throws IOException, JsonProcessingException
        {
            return typedeserializer.deserializeTypedFromArray(jsonparser, deserializationcontext);
        }

        protected ArrayDeser(Class class1)
        {
            super(class1);
        }
    }

    static final class BooleanDeser extends ArrayDeser
    {

        private final boolean[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (!deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
            {
                throw deserializationcontext.mappingException(_valueClass);
            } else
            {
                boolean aflag[] = new boolean[1];
                aflag[0] = _parseBooleanPrimitive(jsonparser, deserializationcontext);
                return aflag;
            }
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public boolean[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (!jsonparser.isExpectedStartArrayToken())
            {
                return handleNonArray(jsonparser, deserializationcontext);
            }
            org.codehaus.jackson.map.util.ArrayBuilders.BooleanBuilder booleanbuilder = deserializationcontext.getArrayBuilders().getBooleanBuilder();
            boolean aflag[] = (boolean[])booleanbuilder.resetAndStart();
            int i;
            int j;
            for (i = 0; jsonparser.nextToken() != JsonToken.END_ARRAY; i = j)
            {
                boolean flag = _parseBooleanPrimitive(jsonparser, deserializationcontext);
                if (i >= aflag.length)
                {
                    aflag = (boolean[])booleanbuilder.appendCompletedChunk(aflag, i);
                    i = 0;
                }
                j = i + 1;
                aflag[i] = flag;
            }

            return (boolean[])booleanbuilder.completeAndClearBuffer(aflag, i);
        }

        public BooleanDeser()
        {
            super([Z);
        }
    }

    static final class ByteDeser extends ArrayDeser
    {

        private final byte[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (!deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
            {
                throw deserializationcontext.mappingException(_valueClass);
            }
            JsonToken jsontoken = jsonparser.getCurrentToken();
            byte byte0;
            if (jsontoken == JsonToken.VALUE_NUMBER_INT || jsontoken == JsonToken.VALUE_NUMBER_FLOAT)
            {
                byte0 = jsonparser.getByteValue();
            } else
            {
                if (jsontoken != JsonToken.VALUE_NULL)
                {
                    throw deserializationcontext.mappingException(_valueClass.getComponentType());
                }
                byte0 = 0;
            }
            return (new byte[] {
                byte0
            });
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public byte[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            JsonToken jsontoken = jsonparser.getCurrentToken();
            if (jsontoken == JsonToken.VALUE_STRING)
            {
                return jsonparser.getBinaryValue(deserializationcontext.getBase64Variant());
            }
            if (jsontoken == JsonToken.VALUE_EMBEDDED_OBJECT)
            {
                Object obj = jsonparser.getEmbeddedObject();
                if (obj == null)
                {
                    return null;
                }
                if (obj instanceof byte[])
                {
                    return (byte[])(byte[])obj;
                }
            }
            if (!jsonparser.isExpectedStartArrayToken())
            {
                return handleNonArray(jsonparser, deserializationcontext);
            }
            org.codehaus.jackson.map.util.ArrayBuilders.ByteBuilder bytebuilder = deserializationcontext.getArrayBuilders().getByteBuilder();
            byte abyte0[] = (byte[])bytebuilder.resetAndStart();
            int i = 0;
            do
            {
                JsonToken jsontoken1 = jsonparser.nextToken();
                if (jsontoken1 != JsonToken.END_ARRAY)
                {
                    byte byte0;
                    int j;
                    if (jsontoken1 == JsonToken.VALUE_NUMBER_INT || jsontoken1 == JsonToken.VALUE_NUMBER_FLOAT)
                    {
                        byte0 = jsonparser.getByteValue();
                    } else
                    {
                        if (jsontoken1 != JsonToken.VALUE_NULL)
                        {
                            throw deserializationcontext.mappingException(_valueClass.getComponentType());
                        }
                        byte0 = 0;
                    }
                    if (i >= abyte0.length)
                    {
                        abyte0 = (byte[])bytebuilder.appendCompletedChunk(abyte0, i);
                        i = 0;
                    }
                    j = i + 1;
                    abyte0[i] = byte0;
                    i = j;
                } else
                {
                    return (byte[])bytebuilder.completeAndClearBuffer(abyte0, i);
                }
            } while (true);
        }

        public ByteDeser()
        {
            super([B);
        }
    }

    static final class CharDeser extends ArrayDeser
    {

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public char[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            JsonToken jsontoken = jsonparser.getCurrentToken();
            if (jsontoken == JsonToken.VALUE_STRING)
            {
                char ac[] = jsonparser.getTextCharacters();
                int i = jsonparser.getTextOffset();
                int j = jsonparser.getTextLength();
                char ac1[] = new char[j];
                System.arraycopy(ac, i, ac1, 0, j);
                return ac1;
            }
            if (jsonparser.isExpectedStartArrayToken())
            {
                StringBuilder stringbuilder = new StringBuilder(64);
                do
                {
                    JsonToken jsontoken1 = jsonparser.nextToken();
                    if (jsontoken1 != JsonToken.END_ARRAY)
                    {
                        if (jsontoken1 != JsonToken.VALUE_STRING)
                        {
                            throw deserializationcontext.mappingException(Character.TYPE);
                        }
                        String s = jsonparser.getText();
                        if (s.length() != 1)
                        {
                            throw JsonMappingException.from(jsonparser, (new StringBuilder()).append("Can not convert a JSON String of length ").append(s.length()).append(" into a char element of char array").toString());
                        }
                        stringbuilder.append(s.charAt(0));
                    } else
                    {
                        return stringbuilder.toString().toCharArray();
                    }
                } while (true);
            }
            if (jsontoken == JsonToken.VALUE_EMBEDDED_OBJECT)
            {
                Object obj = jsonparser.getEmbeddedObject();
                if (obj == null)
                {
                    return null;
                }
                if (obj instanceof char[])
                {
                    return (char[])(char[])obj;
                }
                if (obj instanceof String)
                {
                    return ((String)obj).toCharArray();
                }
                if (obj instanceof byte[])
                {
                    return Base64Variants.getDefaultVariant().encode((byte[])(byte[])obj, false).toCharArray();
                }
            }
            throw deserializationcontext.mappingException(_valueClass);
        }

        public CharDeser()
        {
            super([C);
        }
    }

    static final class DoubleDeser extends ArrayDeser
    {

        private final double[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (!deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
            {
                throw deserializationcontext.mappingException(_valueClass);
            } else
            {
                double ad[] = new double[1];
                ad[0] = _parseDoublePrimitive(jsonparser, deserializationcontext);
                return ad;
            }
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public double[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (!jsonparser.isExpectedStartArrayToken())
            {
                return handleNonArray(jsonparser, deserializationcontext);
            }
            org.codehaus.jackson.map.util.ArrayBuilders.DoubleBuilder doublebuilder = deserializationcontext.getArrayBuilders().getDoubleBuilder();
            double ad[] = (double[])doublebuilder.resetAndStart();
            int i;
            int j;
            for (i = 0; jsonparser.nextToken() != JsonToken.END_ARRAY; i = j)
            {
                double d = _parseDoublePrimitive(jsonparser, deserializationcontext);
                if (i >= ad.length)
                {
                    ad = (double[])doublebuilder.appendCompletedChunk(ad, i);
                    i = 0;
                }
                j = i + 1;
                ad[i] = d;
            }

            return (double[])doublebuilder.completeAndClearBuffer(ad, i);
        }

        public DoubleDeser()
        {
            super([D);
        }
    }

    static final class FloatDeser extends ArrayDeser
    {

        private final float[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (!deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
            {
                throw deserializationcontext.mappingException(_valueClass);
            } else
            {
                float af[] = new float[1];
                af[0] = _parseFloatPrimitive(jsonparser, deserializationcontext);
                return af;
            }
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public float[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (!jsonparser.isExpectedStartArrayToken())
            {
                return handleNonArray(jsonparser, deserializationcontext);
            }
            org.codehaus.jackson.map.util.ArrayBuilders.FloatBuilder floatbuilder = deserializationcontext.getArrayBuilders().getFloatBuilder();
            float af[] = (float[])floatbuilder.resetAndStart();
            int i;
            int j;
            for (i = 0; jsonparser.nextToken() != JsonToken.END_ARRAY; i = j)
            {
                float f = _parseFloatPrimitive(jsonparser, deserializationcontext);
                if (i >= af.length)
                {
                    af = (float[])floatbuilder.appendCompletedChunk(af, i);
                    i = 0;
                }
                j = i + 1;
                af[i] = f;
            }

            return (float[])floatbuilder.completeAndClearBuffer(af, i);
        }

        public FloatDeser()
        {
            super([F);
        }
    }

    static final class IntDeser extends ArrayDeser
    {

        private final int[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (!deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
            {
                throw deserializationcontext.mappingException(_valueClass);
            } else
            {
                int ai[] = new int[1];
                ai[0] = _parseIntPrimitive(jsonparser, deserializationcontext);
                return ai;
            }
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public int[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (!jsonparser.isExpectedStartArrayToken())
            {
                return handleNonArray(jsonparser, deserializationcontext);
            }
            org.codehaus.jackson.map.util.ArrayBuilders.IntBuilder intbuilder = deserializationcontext.getArrayBuilders().getIntBuilder();
            int ai[] = (int[])intbuilder.resetAndStart();
            int i;
            int k;
            for (i = 0; jsonparser.nextToken() != JsonToken.END_ARRAY; i = k)
            {
                int j = _parseIntPrimitive(jsonparser, deserializationcontext);
                if (i >= ai.length)
                {
                    ai = (int[])intbuilder.appendCompletedChunk(ai, i);
                    i = 0;
                }
                k = i + 1;
                ai[i] = j;
            }

            return (int[])intbuilder.completeAndClearBuffer(ai, i);
        }

        public IntDeser()
        {
            super([I);
        }
    }

    static final class LongDeser extends ArrayDeser
    {

        private final long[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (!deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
            {
                throw deserializationcontext.mappingException(_valueClass);
            } else
            {
                long al[] = new long[1];
                al[0] = _parseLongPrimitive(jsonparser, deserializationcontext);
                return al;
            }
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public long[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (!jsonparser.isExpectedStartArrayToken())
            {
                return handleNonArray(jsonparser, deserializationcontext);
            }
            org.codehaus.jackson.map.util.ArrayBuilders.LongBuilder longbuilder = deserializationcontext.getArrayBuilders().getLongBuilder();
            long al[] = (long[])longbuilder.resetAndStart();
            int i;
            int j;
            for (i = 0; jsonparser.nextToken() != JsonToken.END_ARRAY; i = j)
            {
                long l = _parseLongPrimitive(jsonparser, deserializationcontext);
                if (i >= al.length)
                {
                    al = (long[])longbuilder.appendCompletedChunk(al, i);
                    i = 0;
                }
                j = i + 1;
                al[i] = l;
            }

            return (long[])longbuilder.completeAndClearBuffer(al, i);
        }

        public LongDeser()
        {
            super([J);
        }
    }

    static final class ShortDeser extends ArrayDeser
    {

        private final short[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (!deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
            {
                throw deserializationcontext.mappingException(_valueClass);
            } else
            {
                short aword0[] = new short[1];
                aword0[0] = _parseShortPrimitive(jsonparser, deserializationcontext);
                return aword0;
            }
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public short[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (!jsonparser.isExpectedStartArrayToken())
            {
                return handleNonArray(jsonparser, deserializationcontext);
            }
            org.codehaus.jackson.map.util.ArrayBuilders.ShortBuilder shortbuilder = deserializationcontext.getArrayBuilders().getShortBuilder();
            short aword0[] = (short[])shortbuilder.resetAndStart();
            int i;
            int j;
            for (i = 0; jsonparser.nextToken() != JsonToken.END_ARRAY; i = j)
            {
                short word0 = _parseShortPrimitive(jsonparser, deserializationcontext);
                if (i >= aword0.length)
                {
                    aword0 = (short[])shortbuilder.appendCompletedChunk(aword0, i);
                    i = 0;
                }
                j = i + 1;
                aword0[i] = word0;
            }

            return (short[])shortbuilder.completeAndClearBuffer(aword0, i);
        }

        public ShortDeser()
        {
            super([S);
        }
    }

    static final class StringDeser extends ArrayDeser
    {

        private final String[] handleNonArray(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (!deserializationcontext.isEnabled(org.codehaus.jackson.map.DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
            {
                throw deserializationcontext.mappingException(_valueClass);
            }
            String as[] = new String[1];
            String s;
            if (jsonparser.getCurrentToken() == JsonToken.VALUE_NULL)
            {
                s = null;
            } else
            {
                s = jsonparser.getText();
            }
            as[0] = s;
            return as;
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public String[] deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            if (!jsonparser.isExpectedStartArrayToken())
            {
                return handleNonArray(jsonparser, deserializationcontext);
            }
            ObjectBuffer objectbuffer = deserializationcontext.leaseObjectBuffer();
            Object aobj[] = objectbuffer.resetAndStart();
            int i = 0;
            do
            {
                JsonToken jsontoken = jsonparser.nextToken();
                if (jsontoken != JsonToken.END_ARRAY)
                {
                    Object obj;
                    int j;
                    if (jsontoken == JsonToken.VALUE_NULL)
                    {
                        obj = null;
                    } else
                    {
                        obj = jsonparser.getText();
                    }
                    if (i >= aobj.length)
                    {
                        aobj = objectbuffer.appendCompletedChunk(aobj);
                        i = 0;
                    }
                    j = i + 1;
                    aobj[i] = obj;
                    i = j;
                } else
                {
                    String as[] = (String[])objectbuffer.completeAndClearBuffer(aobj, i, java/lang/String);
                    deserializationcontext.returnObjectBuffer(objectbuffer);
                    return as;
                }
            } while (true);
        }

        public StringDeser()
        {
            super([Ljava/lang/String;);
        }
    }


    static final ArrayDeserializers instance = new ArrayDeserializers();
    HashMap _allDeserializers;

    private ArrayDeserializers()
    {
        _allDeserializers = new HashMap();
        add(Boolean.TYPE, new BooleanDeser());
        add(Byte.TYPE, new ByteDeser());
        add(Short.TYPE, new ShortDeser());
        add(Integer.TYPE, new IntDeser());
        add(Long.TYPE, new LongDeser());
        add(Float.TYPE, new FloatDeser());
        add(Double.TYPE, new DoubleDeser());
        add(java/lang/String, new StringDeser());
        add(Character.TYPE, new CharDeser());
    }

    private void add(Class class1, JsonDeserializer jsondeserializer)
    {
        _allDeserializers.put(TypeFactory.defaultInstance().constructType(class1), jsondeserializer);
    }

    public static HashMap getAll()
    {
        return instance._allDeserializers;
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        return typedeserializer.deserializeTypedFromArray(jsonparser, deserializationcontext);
    }

}
