// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.sym;

import java.util.Arrays;
import org.codehaus.jackson.util.InternCache;

// Referenced classes of package org.codehaus.jackson.sym:
//            Name1, Name2, Name3, NameN, 
//            Name

public final class BytesToNameCanonicalizer
{
    static final class Bucket
    {

        protected final Name _name;
        protected final Bucket _next;

        public Name find(int i, int j, int k)
        {
            if (_name.hashCode() != i || !_name.equals(j, k)) goto _L2; else goto _L1
_L1:
            Name name = _name;
_L4:
            return name;
_L2:
            Bucket bucket = _next;
label0:
            do
            {
label1:
                {
                    if (bucket == null)
                    {
                        break label1;
                    }
                    name = bucket._name;
                    if (name.hashCode() == i && name.equals(j, k))
                    {
                        break label0;
                    }
                    bucket = bucket._next;
                }
            } while (true);
            if (true) goto _L4; else goto _L3
_L3:
            return null;
        }

        public Name find(int i, int ai[], int j)
        {
            if (_name.hashCode() != i || !_name.equals(ai, j)) goto _L2; else goto _L1
_L1:
            Name name = _name;
_L4:
            return name;
_L2:
            Bucket bucket = _next;
label0:
            do
            {
label1:
                {
                    if (bucket == null)
                    {
                        break label1;
                    }
                    name = bucket._name;
                    if (name.hashCode() == i && name.equals(ai, j))
                    {
                        break label0;
                    }
                    bucket = bucket._next;
                }
            } while (true);
            if (true) goto _L4; else goto _L3
_L3:
            return null;
        }

        public int length()
        {
            int i = 1;
            for (Bucket bucket = _next; bucket != null; bucket = bucket._next)
            {
                i++;
            }

            return i;
        }

        Bucket(Name name, Bucket bucket)
        {
            _name = name;
            _next = bucket;
        }
    }


    protected static final int DEFAULT_TABLE_SIZE = 64;
    static final int INITIAL_COLLISION_LEN = 32;
    static final int LAST_VALID_BUCKET = 254;
    static final int MAX_ENTRIES_FOR_REUSE = 6000;
    protected static final int MAX_TABLE_SIZE = 0x10000;
    static final int MIN_HASH_SIZE = 16;
    private int _collCount;
    private int _collEnd;
    private Bucket _collList[];
    private boolean _collListShared;
    private int _count;
    final boolean _intern;
    private int _mainHash[];
    private int _mainHashMask;
    private boolean _mainHashShared;
    private Name _mainNames[];
    private boolean _mainNamesShared;
    private transient boolean _needRehash;
    final BytesToNameCanonicalizer _parent;

    private BytesToNameCanonicalizer(int i, boolean flag)
    {
        _parent = null;
        _intern = flag;
        if (i >= 16) goto _L2; else goto _L1
_L1:
        i = 16;
_L4:
        initTables(i);
        return;
_L2:
        if ((i & i - 1) != 0)
        {
            int j;
            for (j = 16; j < i; j += j) { }
            i = j;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private BytesToNameCanonicalizer(BytesToNameCanonicalizer bytestonamecanonicalizer, boolean flag)
    {
        _parent = bytestonamecanonicalizer;
        _intern = flag;
        _count = bytestonamecanonicalizer._count;
        _mainHashMask = bytestonamecanonicalizer._mainHashMask;
        _mainHash = bytestonamecanonicalizer._mainHash;
        _mainNames = bytestonamecanonicalizer._mainNames;
        _collList = bytestonamecanonicalizer._collList;
        _collCount = bytestonamecanonicalizer._collCount;
        _collEnd = bytestonamecanonicalizer._collEnd;
        _needRehash = false;
        _mainHashShared = true;
        _mainNamesShared = true;
        _collListShared = true;
    }

    private void _addSymbol(int i, Name name)
    {
        if (_mainHashShared)
        {
            unshareMain();
        }
        if (_needRehash)
        {
            rehash();
        }
        _count = 1 + _count;
        int j = i & _mainHashMask;
        int j1;
        if (_mainNames[j] == null)
        {
            _mainHash[j] = i << 8;
            if (_mainNamesShared)
            {
                unshareNames();
            }
            _mainNames[j] = name;
        } else
        {
            if (_collListShared)
            {
                unshareCollision();
            }
            _collCount = 1 + _collCount;
            int k = _mainHash[j];
            int l = k & 0xff;
            int i1;
            if (l == 0)
            {
                if (_collEnd <= 254)
                {
                    i1 = _collEnd;
                    _collEnd = 1 + _collEnd;
                    if (i1 >= _collList.length)
                    {
                        expandCollision();
                    }
                } else
                {
                    i1 = findBestBucket();
                }
                _mainHash[j] = k & 0xffffff00 | i1 + 1;
            } else
            {
                i1 = l - 1;
            }
            _collList[i1] = new Bucket(name, _collList[i1]);
        }
        j1 = _mainHash.length;
        if (_count > j1 >> 1)
        {
            int k1 = j1 >> 2;
            if (_count > j1 - k1)
            {
                _needRehash = true;
            } else
            if (_collCount >= k1)
            {
                _needRehash = true;
                return;
            }
        }
    }

    public static final int calcHash(int i)
    {
        int j = i ^ i >>> 16;
        return j ^ j >>> 8;
    }

    public static final int calcHash(int i, int j)
    {
        int k = j + i * 31;
        int l = k ^ k >>> 16;
        return l ^ l >>> 8;
    }

    public static final int calcHash(int ai[], int i)
    {
        int j = ai[0];
        for (int k = 1; k < i; k++)
        {
            j = j * 31 + ai[k];
        }

        int l = j ^ j >>> 16;
        return l ^ l >>> 8;
    }

    private static Name constructName(int i, String s, int j, int k)
    {
        if (k == 0)
        {
            return new Name1(s, i, j);
        } else
        {
            return new Name2(s, i, j, k);
        }
    }

    private static Name constructName(int i, String s, int ai[], int j)
    {
        if (j >= 4) goto _L2; else goto _L1
_L1:
        j;
        JVM INSTR tableswitch 1 3: default 32
    //                   1 61
    //                   2 74
    //                   3 90;
           goto _L2 _L3 _L4 _L5
_L2:
        int ai1[];
        ai1 = new int[j];
        for (int k = 0; k < j; k++)
        {
            ai1[k] = ai[k];
        }

        break; /* Loop/switch isn't completed */
_L3:
        return new Name1(s, i, ai[0]);
_L4:
        return new Name2(s, i, ai[0], ai[1]);
_L5:
        return new Name3(s, i, ai[0], ai[1], ai[2]);
        return new NameN(s, i, ai1, j);
    }

    public static BytesToNameCanonicalizer createRoot()
    {
        return new BytesToNameCanonicalizer(64, true);
    }

    private void expandCollision()
    {
        Bucket abucket[] = _collList;
        int i = abucket.length;
        _collList = new Bucket[i + i];
        System.arraycopy(abucket, 0, _collList, 0, i);
    }

    private int findBestBucket()
    {
        Bucket abucket[] = _collList;
        int i = 0x7fffffff;
        int j = -1;
        int k = 0;
        for (int l = _collEnd; k < l; k++)
        {
            int i1 = abucket[k].length();
            if (i1 >= i)
            {
                continue;
            }
            if (i1 == 1)
            {
                return k;
            }
            i = i1;
            j = k;
        }

        return j;
    }

    public static Name getEmptyName()
    {
        return Name1.getEmptyName();
    }

    private void initTables(int i)
    {
        _count = 0;
        _mainHash = new int[i];
        _mainNames = new Name[i];
        _mainHashShared = false;
        _mainNamesShared = false;
        _mainHashMask = i - 1;
        _collListShared = true;
        _collList = null;
        _collEnd = 0;
        _needRehash = false;
    }

    private void markAsShared()
    {
        _mainHashShared = true;
        _mainNamesShared = true;
        _collListShared = true;
    }

    private void mergeChild(BytesToNameCanonicalizer bytestonamecanonicalizer)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        int j;
        i = bytestonamecanonicalizer._count;
        j = _count;
        if (i > j) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (bytestonamecanonicalizer.size() <= 6000)
        {
            break MISSING_BLOCK_LABEL_46;
        }
        initTables(64);
          goto _L1
        Exception exception;
        exception;
        throw exception;
        _count = bytestonamecanonicalizer._count;
        _mainHash = bytestonamecanonicalizer._mainHash;
        _mainNames = bytestonamecanonicalizer._mainNames;
        _mainHashShared = true;
        _mainNamesShared = true;
        _mainHashMask = bytestonamecanonicalizer._mainHashMask;
        _collList = bytestonamecanonicalizer._collList;
        _collCount = bytestonamecanonicalizer._collCount;
        _collEnd = bytestonamecanonicalizer._collEnd;
          goto _L1
    }

    private void nukeSymbols()
    {
        _count = 0;
        Arrays.fill(_mainHash, 0);
        Arrays.fill(_mainNames, null);
        Arrays.fill(_collList, null);
        _collCount = 0;
        _collEnd = 0;
    }

    private void rehash()
    {
        _needRehash = false;
        _mainNamesShared = false;
        int i = _mainHash.length;
        int j = i + i;
        if (j > 0x10000)
        {
            nukeSymbols();
        } else
        {
            _mainHash = new int[j];
            _mainHashMask = j - 1;
            Name aname[] = _mainNames;
            _mainNames = new Name[j];
            int k = 0;
            for (int l = 0; l < i; l++)
            {
                Name name1 = aname[l];
                if (name1 != null)
                {
                    k++;
                    int l2 = name1.hashCode();
                    int i3 = l2 & _mainHashMask;
                    _mainNames[i3] = name1;
                    _mainHash[i3] = l2 << 8;
                }
            }

            int i1 = _collEnd;
            if (i1 != 0)
            {
                _collCount = 0;
                _collEnd = 0;
                _collListShared = false;
                Bucket abucket[] = _collList;
                _collList = new Bucket[abucket.length];
                for (int j1 = 0; j1 < i1; j1++)
                {
                    Bucket bucket = abucket[j1];
                    while (bucket != null) 
                    {
                        k++;
                        Name name = bucket._name;
                        int k1 = name.hashCode();
                        int l1 = k1 & _mainHashMask;
                        int i2 = _mainHash[l1];
                        if (_mainNames[l1] == null)
                        {
                            _mainHash[l1] = k1 << 8;
                            _mainNames[l1] = name;
                        } else
                        {
                            _collCount = 1 + _collCount;
                            int j2 = i2 & 0xff;
                            int k2;
                            if (j2 == 0)
                            {
                                Bucket abucket1[];
                                Bucket bucket1;
                                if (_collEnd <= 254)
                                {
                                    k2 = _collEnd;
                                    _collEnd = 1 + _collEnd;
                                    if (k2 >= _collList.length)
                                    {
                                        expandCollision();
                                    }
                                } else
                                {
                                    k2 = findBestBucket();
                                }
                                _mainHash[l1] = i2 & 0xffffff00 | k2 + 1;
                            } else
                            {
                                k2 = j2 - 1;
                            }
                            abucket1 = _collList;
                            bucket1 = new Bucket(name, _collList[k2]);
                            abucket1[k2] = bucket1;
                        }
                        bucket = bucket._next;
                    }
                }

                if (k != _count)
                {
                    throw new RuntimeException((new StringBuilder()).append("Internal error: count after rehash ").append(k).append("; should be ").append(_count).toString());
                }
            }
        }
    }

    private void unshareCollision()
    {
        Bucket abucket[] = _collList;
        if (abucket == null)
        {
            _collList = new Bucket[32];
        } else
        {
            int i = abucket.length;
            _collList = new Bucket[i];
            System.arraycopy(abucket, 0, _collList, 0, i);
        }
        _collListShared = false;
    }

    private void unshareMain()
    {
        int ai[] = _mainHash;
        int i = _mainHash.length;
        _mainHash = new int[i];
        System.arraycopy(ai, 0, _mainHash, 0, i);
        _mainHashShared = false;
    }

    private void unshareNames()
    {
        Name aname[] = _mainNames;
        int i = aname.length;
        _mainNames = new Name[i];
        System.arraycopy(aname, 0, _mainNames, 0, i);
        _mainNamesShared = false;
    }

    public Name addName(String s, int i, int j)
    {
        if (_intern)
        {
            s = InternCache.instance.intern(s);
        }
        int k;
        Name name;
        if (j == 0)
        {
            k = calcHash(i);
        } else
        {
            k = calcHash(i, j);
        }
        name = constructName(k, s, i, j);
        _addSymbol(k, name);
        return name;
    }

    public Name addName(String s, int ai[], int i)
    {
        if (_intern)
        {
            s = InternCache.instance.intern(s);
        }
        int j = calcHash(ai, i);
        Name name = constructName(j, s, ai, i);
        _addSymbol(j, name);
        return name;
    }

    public Name findName(int i)
    {
        int j;
        int k;
        int l;
        j = calcHash(i);
        k = j & _mainHashMask;
        l = _mainHash[k];
        if ((j ^ l >> 8) << 8 != 0) goto _L2; else goto _L1
_L1:
        Name name = _mainNames[k];
        if (name != null) goto _L4; else goto _L3
_L3:
        name = null;
_L6:
        return name;
_L4:
        if (name.equals(i)) goto _L6; else goto _L5
_L5:
        int i1 = l & 0xff;
        if (i1 > 0)
        {
            int j1 = i1 - 1;
            Bucket bucket = _collList[j1];
            if (bucket != null)
            {
                return bucket.find(j, i, 0);
            }
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (l == 0)
        {
            return null;
        }
        if (true) goto _L5; else goto _L7
_L7:
        return null;
    }

    public Name findName(int i, int j)
    {
        int k;
        int l;
        int i1;
        k = calcHash(i, j);
        l = k & _mainHashMask;
        i1 = _mainHash[l];
        if ((k ^ i1 >> 8) << 8 != 0) goto _L2; else goto _L1
_L1:
        Name name = _mainNames[l];
        if (name != null) goto _L4; else goto _L3
_L3:
        name = null;
_L6:
        return name;
_L4:
        if (name.equals(i, j)) goto _L6; else goto _L5
_L5:
        int j1 = i1 & 0xff;
        if (j1 > 0)
        {
            int k1 = j1 - 1;
            Bucket bucket = _collList[k1];
            if (bucket != null)
            {
                return bucket.find(k, i, j);
            }
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (i1 == 0)
        {
            return null;
        }
        if (true) goto _L5; else goto _L7
_L7:
        return null;
    }

    public Name findName(int ai[], int i)
    {
        int j = calcHash(ai, i);
        int k = j & _mainHashMask;
        int l = _mainHash[k];
        if ((j ^ l >> 8) << 8 == 0)
        {
            Name name = _mainNames[k];
            if (name == null || name.equals(ai, i))
            {
                return name;
            }
        } else
        if (l == 0)
        {
            return null;
        }
        int i1 = l & 0xff;
        if (i1 > 0)
        {
            int j1 = i1 - 1;
            Bucket bucket = _collList[j1];
            if (bucket != null)
            {
                return bucket.find(j, ai, i);
            }
        }
        return null;
    }

    public BytesToNameCanonicalizer makeChild(boolean flag, boolean flag1)
    {
        this;
        JVM INSTR monitorenter ;
        BytesToNameCanonicalizer bytestonamecanonicalizer = new BytesToNameCanonicalizer(this, flag1);
        this;
        JVM INSTR monitorexit ;
        return bytestonamecanonicalizer;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean maybeDirty()
    {
        return !_mainHashShared;
    }

    public void release()
    {
        if (maybeDirty() && _parent != null)
        {
            _parent.mergeChild(this);
            markAsShared();
        }
    }

    public int size()
    {
        return _count;
    }
}
