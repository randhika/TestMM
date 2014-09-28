// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.common.hamburger;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

// Referenced classes of package jp.co.yahoo.android.common.hamburger:
//            YHBGBackgroundHandler, YHBGUtils, YHBGRd, YHBGSearchAssistProvider

public class YHBGSearchActivity extends Activity
    implements android.view.View.OnClickListener, TextWatcher
{
    static class DummyCursor extends MatrixCursor
    {

        private static final String COLS[] = {
            "_id"
        };


        public DummyCursor()
        {
            super(COLS);
        }
    }

    class SearchApadter extends CursorAdapter
    {

        private final DummyCursor mDummyCursor = new DummyCursor();
        private final android.view.View.OnClickListener mOnClickListener;
        private Uri mProviderUrl;
        private final ContentResolver mResolver;
        final YHBGSearchActivity this$0;

        public void bindView(View view, Context context, Cursor cursor)
        {
            TextView textview = (TextView)view.findViewById(0x7f09000e);
            View view1 = view.findViewById(0x7f09000f);
            String s = cursor.getString(1);
            textview.setText(s);
            SearchWordInfo searchwordinfo = new SearchWordInfo();
            searchwordinfo.mWord = s;
            searchwordinfo.mPosition = 1 + cursor.getPosition();
            view.setTag(searchwordinfo);
            view1.setTag(s);
        }

        public volatile CharSequence convertToString(Cursor cursor)
        {
            return convertToString(cursor);
        }

        public String convertToString(Cursor cursor)
        {
            if (cursor instanceof DummyCursor)
            {
                return null;
            } else
            {
                return cursor.getString(1);
            }
        }

        public Object getItem(int i)
        {
            Cursor cursor = getCursor();
            if (cursor == null)
            {
                return null;
            }
            if (cursor.getCount() == i)
            {
                return mDummyCursor;
            } else
            {
                return super.getItem(i);
            }
        }

        public int getItemViewType(int i)
        {
            for (Cursor cursor = getCursor(); cursor == null || cursor.getCount() != i;)
            {
                return 0;
            }

            return 1;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            if (getCursor() == null)
            {
                return super.getView(i, view, viewgroup);
            } else
            {
                return super.getView(i, view, viewgroup);
            }
        }

        public int getViewTypeCount()
        {
            return 2;
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewgroup)
        {
            View view = LayoutInflater.from(context).inflate(0x7f030002, viewgroup, false);
            View view1 = view.findViewById(0x7f09000f);
            view.setOnClickListener(mOnClickListener);
            view1.setOnClickListener(mOnClickListener);
            String s = cursor.getString(1);
            view.setTag(s);
            view1.setTag(s);
            return view;
        }

        public Cursor runQueryOnBackgroundThread(CharSequence charsequence)
        {
            YHBGUtils.debug((new StringBuilder("runQueryOnBackgroundThread: ")).append(charsequence).toString());
            if (charsequence.length() > 20)
            {
                return new DummyCursor();
            } else
            {
                ContentResolver contentresolver = mResolver;
                Uri uri = mProviderUrl;
                String as[] = new String[1];
                as[0] = charsequence.toString();
                return contentresolver.query(uri, null, null, as, null);
            }
        }

        public void setFilter(CharSequence charsequence)
        {
            YHBGUtils.debug((new StringBuilder("YHBGSearchActivity.SearchApadter#setFilter filter=")).append(charsequence).toString());
            getFilter().filter(charsequence);
            notifyDataSetChanged();
        }

        public SearchApadter(Cursor cursor, android.view.View.OnClickListener onclicklistener)
        {
            this$0 = YHBGSearchActivity.this;
            super(getApplicationContext(), cursor);
            mProviderUrl = YHBGSearchAssistProvider.URL_SEARCH;
            Context context = getApplicationContext();
            LayoutInflater.from(context);
            mResolver = context.getContentResolver();
            mOnClickListener = onclicklistener;
            Log.d("YHamburger", "SearchApadter construct");
        }
    }

    static class SearchWordInfo
    {

        public int mPosition;
        public String mWord;

        SearchWordInfo()
        {
        }
    }


    private static final int MSG_SEARCH;
    protected static String sQuery;
    private SearchApadter mAdapter;
    private Handler mBackgroundHandler;
    private ToggleButton mChieButton;
    private EditText mEdtSearch;
    private ImageButton mEraseButton;
    private Handler mHandler;
    private ToggleButton mImageButton;
    private boolean mIsEmptyPrevious;
    private ListView mListSuggest;
    private android.view.View.OnClickListener mOnClickListener;
    private ToggleButton mRealTimeButton;
    private android.view.View.OnClickListener mToggleClick;
    private String mUrl;
    private ToggleButton mVideoButton;
    private ToggleButton mWebButton;

    public YHBGSearchActivity()
    {
        mBackgroundHandler = new Handler(YHBGBackgroundHandler.getLooper());
        mIsEmptyPrevious = true;
        mUrl = "http://search.yahoo.co.jp/search?";
        mToggleClick = new android.view.View.OnClickListener() {

            final YHBGSearchActivity this$0;

            public void onClick(View view)
            {
                int i;
                mWebButton.setChecked(false);
                mImageButton.setChecked(false);
                mVideoButton.setChecked(false);
                mChieButton.setChecked(false);
                mRealTimeButton.setChecked(false);
                ((ToggleButton)view).setChecked(true);
                i = view.getId();
                if (i != 0x7f090007) goto _L2; else goto _L1
_L1:
                mUrl = "http://search.yahoo.co.jp/search?";
_L4:
                String s = mEdtSearch.getText().toString();
                mEdtSearch.setText(s);
                mEdtSearch.setSelection(s.length());
                return;
_L2:
                if (i == 0x7f090008)
                {
                    mUrl = "http://image.search.yahoo.co.jp/search?";
                } else
                if (i == 0x7f090009)
                {
                    mUrl = "http://video.search.yahoo.co.jp/search?";
                } else
                if (i == 0x7f09000a)
                {
                    mUrl = "http://chiebukuro.search.yahoo.co.jp/search?";
                } else
                if (i == 0x7f09000b)
                {
                    mUrl = "http://realtime.search.yahoo.co.jp/search?";
                }
                if (true) goto _L4; else goto _L3
_L3:
            }

            
            {
                this$0 = YHBGSearchActivity.this;
                super();
            }
        };
        mHandler = new Handler(Looper.getMainLooper()) {

            final YHBGSearchActivity this$0;

            public void handleMessage(Message message)
            {
                String s;
                switch (message.what)
                {
                default:
                    return;

                case 0: // '\0'
                    s = mEdtSearch.getText().toString();
                    break;
                }
                performSearch(s);
            }

            
            {
                this$0 = YHBGSearchActivity.this;
                super(looper);
            }
        };
        mOnClickListener = new android.view.View.OnClickListener() {

            final YHBGSearchActivity this$0;

            public void onClick(View view)
            {
                if (view.getId() == 0x7f09000f)
                {
                    String s = (String)view.getTag();
                    if (!Character.isWhitespace(Character.valueOf(s.charAt(-1 + s.length())).charValue()))
                    {
                        s = (new StringBuilder(String.valueOf(s))).append(" ").toString();
                    }
                    mEdtSearch.setText(s);
                    mEdtSearch.setSelection(s.length());
                    return;
                } else
                {
                    SearchWordInfo searchwordinfo = (SearchWordInfo)view.getTag();
                    performSearch(searchwordinfo.mWord);
                    return;
                }
            }

            
            {
                this$0 = YHBGSearchActivity.this;
                super();
            }
        };
    }

    public static String getLastQuery()
    {
        if (sQuery != null)
        {
            return sQuery.trim();
        } else
        {
            return null;
        }
    }

    private void performSearch(String s)
    {
        try
        {
            sQuery = s;
            if (YHBGUtils.startActivity(this, new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder(String.valueOf(mUrl))).append("ei=UTF-8&p=").append(URLEncoder.encode(s, "UTF-8")).toString()))))
            {
                overridePendingTransition(0, 0);
                YHBGRd.sendAsync(getApplicationContext(), new String[] {
                    "search_query"
                });
            }
            return;
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            unsupportedencodingexception.printStackTrace();
        }
    }

    public void afterTextChanged(Editable editable)
    {
    }

    public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    void close()
    {
        if (!isFinishing())
        {
            sQuery = mEdtSearch.getText().toString();
            finish();
            overridePendingTransition(0, 0);
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        if (keyevent.getAction() != 1) goto _L2; else goto _L1
_L1:
        keyevent.getKeyCode();
        JVM INSTR tableswitch 66 66: default 32
    //                   66 38;
           goto _L2 _L3
_L2:
        return super.dispatchKeyEvent(keyevent);
_L3:
        performSearch(mEdtSearch.getText().toString());
        return true;
    }

    public void onBackPressed()
    {
        close();
    }

    public void onClick(View view)
    {
        int i = view.getId();
        if (i == 0x7f090006)
        {
            close();
        } else
        if (i == 0x7f090005)
        {
            mEdtSearch.setText(null);
            return;
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        YHBGUtils.isDebuggable(getApplicationContext());
        setContentView(0x7f030001);
        mEdtSearch = (EditText)findViewById(0x7f090004);
        mEdtSearch.addTextChangedListener(this);
        mWebButton = (ToggleButton)findViewById(0x7f090007);
        mImageButton = (ToggleButton)findViewById(0x7f090008);
        mVideoButton = (ToggleButton)findViewById(0x7f090009);
        mChieButton = (ToggleButton)findViewById(0x7f09000a);
        mRealTimeButton = (ToggleButton)findViewById(0x7f09000b);
        mWebButton.setChecked(true);
        mWebButton.setOnClickListener(mToggleClick);
        mImageButton.setOnClickListener(mToggleClick);
        mVideoButton.setOnClickListener(mToggleClick);
        mChieButton.setOnClickListener(mToggleClick);
        mRealTimeButton.setOnClickListener(mToggleClick);
        ((TextView)findViewById(0x7f090006)).setOnClickListener(this);
        mEraseButton = (ImageButton)findViewById(0x7f090005);
        mEraseButton.setOnClickListener(this);
        mEraseButton.setVisibility(4);
        mListSuggest = (ListView)findViewById(0x7f09000c);
        mAdapter = new SearchApadter(null, mOnClickListener);
        mListSuggest.setAdapter(mAdapter);
    }

    protected void onPause()
    {
        super.onPause();
        if (isFinishing())
        {
            if (mAdapter != null)
            {
                Cursor cursor = mAdapter.getCursor();
                if (cursor != null)
                {
                    cursor.close();
                }
                mAdapter.changeCursor(null);
            }
            overridePendingTransition(0, 0);
        }
    }

    protected void onResume()
    {
        super.onResume();
        if (sQuery != null && sQuery.length() != 0)
        {
            mEdtSearch.setText(sQuery);
            mEdtSearch.setSelection(sQuery.length());
            return;
        } else
        {
            mEdtSearch.setText("");
            return;
        }
    }

    public void onTextChanged(final CharSequence s, int i, int j, int k)
    {
        mBackgroundHandler.post(new Runnable() {

            final YHBGSearchActivity this$0;
            private final CharSequence val$s;

            public void run()
            {
                final boolean isEmpty;
                if (s.length() == 0)
                {
                    isEmpty = true;
                } else
                {
                    isEmpty = false;
                }
                mHandler.post(s. new Runnable() {

                    final _cls4 this$1;
                    private final boolean val$isEmpty;
                    private final CharSequence val$s;

                    public void run()
                    {
                        if (isEmpty != mIsEmptyPrevious)
                        {
                            mAdapter.changeCursor(null);
                        }
                        mIsEmptyPrevious = isEmpty;
                        if (isEmpty)
                        {
                            mEraseButton.setVisibility(4);
                        } else
                        {
                            mEraseButton.setVisibility(0);
                        }
                        mListSuggest.setAdapter(mAdapter);
                        mAdapter.setFilter(s);
                    }

            
            {
                this$1 = final__pcls4;
                isEmpty = flag;
                s = CharSequence.this;
                super();
            }
                });
            }


            
            {
                this$0 = YHBGSearchActivity.this;
                s = charsequence;
                super();
            }
        });
    }

    public void onWindowFocusChanged(boolean flag)
    {
        super.onWindowFocusChanged(flag);
        if (flag && mEdtSearch == getCurrentFocus())
        {
            (new Handler() {

                final YHBGSearchActivity this$0;

                public void dispatchMessage(Message message)
                {
                    ((InputMethodManager)getSystemService("input_method")).showSoftInput(mEdtSearch, 0);
                    super.dispatchMessage(message);
                }

            
            {
                this$0 = YHBGSearchActivity.this;
                super();
            }
            }).sendEmptyMessage(0);
        }
    }

    public void postPerformSearch()
    {
        mHandler.sendEmptyMessage(0);
    }














}
