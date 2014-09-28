// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.sym;

import java.util.Arrays;
import org.codehaus.jackson.util.InternCache;

public final class CharsToNameCanonicalizer
{
    static final class Bucket
    {

        private final String _symbol;
        private final Bucket mNext;

        public String find(char ac[], int i, int j)
        {
            String s;
            Bucket bucket;
            s = _symbol;
            bucket = mNext;
_L2:
            if (s.length() != j)
            {
                break MISSING_BLOCK_LABEL_61;
            }
            break MISSING_BLOCK_LABEL_21;
            for (int k = 0; s.charAt(k) == ac[i + k] && ++k < j;) { }
            if (k == j)
            {
                return s;
            }
            break MISSING_BLOCK_LABEL_61;
            if (bucket == null)
            {
                return null;
            }
            s = bucket.getSymbol();
            bucket = bucket.getNext();
            if (true) goto _L2; else goto _L1
_L1:
        }

        public Bucket getNext()
        {
            return mNext;
        }

        public String getSymbol()
        {
            return _symbol;
        }

        public Bucket(String s, Bucket bucket)
        {
            _symbol = s;
            mNext = bucket;
        }
    }


    protected static final int DEFAULT_TABLE_SIZE = 64;
    static final int MAX_ENTRIES_FOR_REUSE = 12000;
    protected static final int MAX_TABLE_SIZE = 0x10000;
    static final CharsToNameCanonicalizer sBootstrapSymbolTable = new CharsToNameCanonicalizer();
    protected Bucket _buckets[];
    protected final boolean _canonicalize;
    protected boolean _dirty;
    protected int _indexMask;
    protected final boolean _intern;
    protected CharsToNameCanonicalizer _parent;
    protected int _size;
    protected int _sizeThreshold;
    protected String _symbols[];

    private CharsToNameCanonicalizer()
    {
        _canonicalize = true;
        _intern = true;
        _dirty = true;
        initTables(64);
    }

    private CharsToNameCanonicalizer(CharsToNameCanonicalizer charstonamecanonicalizer, boolean flag, boolean flag1, String as[], Bucket abucket[], int i)
    {
        _parent = charstonamecanonicalizer;
        _canonicalize = flag;
        _intern = flag1;
        _symbols = as;
        _buckets = abucket;
        _size = i;
        int j = as.length;
        _sizeThreshold = j - (j >> 2);
        _indexMask = j - 1;
        _dirty = false;
    }

    public static int calcHash(String s)
    {
        int i = s.charAt(0);
        int j = 1;
        for (int k = s.length(); j < k; j++)
        {
            i = i * 31 + s.charAt(j);
        }

        return i;
    }

    public static int calcHash(char ac[], int i, int j)
    {
        int k = ac[0];
        for (int l = 1; l < j; l++)
        {
            k = k * 31 + ac[l];
        }

        return k;
    }

    private void copyArrays()
    {
        String as[] = _symbols;
        int i = as.length;
        _symbols = new String[i];
        System.arraycopy(as, 0, _symbols, 0, i);
        Bucket abucket[] = _buckets;
        int j = abucket.length;
        _buckets = new Bucket[j];
        System.arraycopy(abucket, 0, _buckets, 0, j);
    }

    public static CharsToNameCanonicalizer createRoot()
    {
        return sBootstrapSymbolTable.makeOrphan();
    }

    private void initTables(int i)
    {
        _symbols = new String[i];
        _buckets = new Bucket[i >> 1];
        _indexMask = i - 1;
        _size = 0;
        _sizeThreshold = i - (i >> 2);
    }

    private CharsToNameCanonicalizer makeOrphan()
    {
        return new CharsToNameCanonicalizer(null, true, true, _symbols, _buckets, _size);
    }

    private void mergeChild(CharsToNameCanonicalizer charstonamecanonicalizer)
    {
        this;
        JVM INSTR monitorenter ;
        if (charstonamecanonicalizer.size() <= 12000) goto _L2; else goto _L1
_L1:
        initTables(64);
_L5:
        _dirty = false;
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (charstonamecanonicalizer.size() <= size()) goto _L4; else goto _L3
_L3:
        _symbols = charstonamecanonicalizer._symbols;
        _buckets = charstonamecanonicalizer._buckets;
        _size = charstonamecanonicalizer._size;
        _sizeThreshold = charstonamecanonicalizer._sizeThreshold;
        _indexMask = charstonamecanonicalizer._indexMask;
          goto _L5
        Exception exception;
        exception;
        throw exception;
    }

    private void rehash()
    {
        int i = _symbols.length;
        int j = i + i;
        if (j > 0x10000)
        {
            _size = 0;
            Arrays.fill(_symbols, null);
            Arrays.fill(_buckets, null);
            _dirty = true;
        } else
        {
            String as[] = _symbols;
            Bucket abucket[] = _buckets;
            _symbols = new String[j];
            _buckets = new Bucket[j >> 1];
            _indexMask = j - 1;
            _sizeThreshold = _sizeThreshold + _sizeThreshold;
            int k = 0;
            int l = 0;
            while (l < i) 
            {
                String s1 = as[l];
                if (s1 != null)
                {
                    k++;
                    int i2 = calcHash(s1) & _indexMask;
                    if (_symbols[i2] == null)
                    {
                        _symbols[i2] = s1;
                    } else
                    {
                        int j2 = i2 >> 1;
                        _buckets[j2] = new Bucket(s1, _buckets[j2]);
                    }
                }
                l++;
            }
            int i1 = i >> 1;
            for (int j1 = 0; j1 < i1; j1++)
            {
                Bucket bucket = abucket[j1];
                while (bucket != null) 
                {
                    k++;
                    String s = bucket.getSymbol();
                    int k1 = calcHash(s) & _indexMask;
                    if (_symbols[k1] == null)
                    {
                        _symbols[k1] = s;
                    } else
                    {
                        int l1 = k1 >> 1;
                        _buckets[l1] = new Bucket(s, _buckets[l1]);
                    }
                    bucket = bucket.getNext();
                }
            }

            if (k != _size)
            {
                throw new Error((new StringBuilder()).append("Internal error on SymbolTable.rehash(): had ").append(_size).append(" entries; now have ").append(k).append(".").toString());
            }
        }
    }

    public String findSymbol(char ac[], int i, int j, int k)
    {
        if (j < 1)
        {
            String s = "";
            int l;
            int j1;
            do
            {
                do
                {
                    return s;
                } while (j1 == j);
                Bucket bucket = _buckets[l >> 1];
                if (bucket == null)
                {
                    break;
                }
                s = bucket.find(ac, i, j);
            } while (s != null);
            String s1;
            if (!_dirty)
            {
                copyArrays();
                _dirty = true;
            } else
            if (_size >= _sizeThreshold)
            {
                rehash();
                l = calcHash(ac, i, j) & _indexMask;
            }
            _size = 1 + _size;
            s1 = new String(ac, i, j);
            if (_intern)
            {
                s1 = InternCache.instance.intern(s1);
            }
            if (_symbols[l] == null)
            {
                _symbols[l] = s1;
            } else
            {
                int i1 = l >> 1;
                _buckets[i1] = new Bucket(s1, _buckets[i1]);
            }
            return s1;
        } else
        {
            if (!_canonicalize)
            {
                return new String(ac, i, j);
            }
            l = k & _indexMask;
            s = _symbols[l];
            if (s == null)
            {
                break;
            }
            if (s.length() != j)
            {
                break;
            }
            for (j1 = 0; s.charAt(j1) == ac[i + j1] && ++j1 < j;) { }
            continue;
        }
    }

    public CharsToNameCanonicalizer makeChild(boolean flag, boolean flag1)
    {
        this;
        JVM INSTR monitorenter ;
        CharsToNameCanonicalizer charstonamecanonicalizer = new CharsToNameCanonicalizer(this, flag, flag1, _symbols, _buckets, _size);
        this;
        JVM INSTR monitorexit ;
        return charstonamecanonicalizer;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean maybeDirty()
    {
        return _dirty;
    }

    public void release()
    {
        while (!maybeDirty() || _parent == null) 
        {
            return;
        }
        _parent.mergeChild(this);
        _dirty = false;
    }

    public int size()
    {
        return _size;
    }

}
