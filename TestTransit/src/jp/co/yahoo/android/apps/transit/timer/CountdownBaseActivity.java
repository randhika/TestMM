// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package jp.co.yahoo.android.apps.transit.timer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import jp.co.yahoo.android.apps.transit.Transit;
import jp.co.yahoo.android.apps.transit.TransitBaseActivity;
import jp.co.yahoo.android.apps.transit.common.util.TransitUtil;
import jp.co.yahoo.android.apps.transit.timer.api.data.AlermData;
import jp.co.yahoo.android.apps.transit.timer.api.data.SkinMetaData;
import jp.co.yahoo.android.apps.transit.timer.common.SettingShortcut;
import jp.co.yahoo.android.apps.transit.timer.common.SkinMeta;
import jp.co.yahoo.android.apps.transit.timer.db.SkinDb;
import jp.co.yahoo.android.apps.transit.viewparts.TransitDialogBuilder;
import jp.co.yahoo.android.yolp.common.ApiBase;

// Referenced classes of package jp.co.yahoo.android.apps.transit.timer:
//            SettingSkinActivity, CountdownActivity, CountdownListActivity

public abstract class CountdownBaseActivity extends TransitBaseActivity
{
    private class DisplayModeAdapter extends ArrayAdapter
    {

        private LayoutInflater inflater;
        final CountdownBaseActivity this$0;

        public View getDropDownView(int i, View view, ViewGroup viewgroup)
        {
            LinearLayout linearlayout;
            ImageView imageview;
            jp.co.yahoo.android.apps.transit.timer.db.SkinDb.SkinMenuData skinmenudata;
            TextView textview;
            if (view == null)
            {
                linearlayout = (LinearLayout)inflater.inflate(0x7f030072, null);
                imageview = (ImageView)linearlayout.findViewById(0x7f0a0221);
                int j = (int)TransitUtil.dpToPx(CountdownBaseActivity.this, 32F);
                android.view.ViewGroup.LayoutParams layoutparams = imageview.getLayoutParams();
                layoutparams.width = j;
                layoutparams.height = j;
                imageview.setLayoutParams(layoutparams);
            } else
            {
                linearlayout = (LinearLayout)view;
                imageview = null;
            }
            if (imageview == null)
            {
                imageview = (ImageView)linearlayout.findViewById(0x7f0a0221);
            }
            textview = (TextView)linearlayout.findViewById(0x7f0a0222);
            skinmenudata = (jp.co.yahoo.android.apps.transit.timer.db.SkinDb.SkinMenuData)getItem(i);
            if (!TransitUtil.isEmpty(skinmenudata.sId)) goto _L2; else goto _L1
_L1:
            imageview.setImageResource(0x7f020116);
            if (!(CountdownBaseActivity.this instanceof CountdownListActivity)) goto _L4; else goto _L3
_L3:
            linearlayout.setBackgroundColor(getResources().getColor(0x7f090007));
_L12:
            if (!TransitUtil.isEmpty(skinmenudata.sTitle))
            {
                textview.setText(skinmenudata.sTitle);
            }
            return linearlayout;
_L4:
            if (CountdownBaseActivity.this instanceof CountdownActivity)
            {
                linearlayout.setBackgroundResource(0x7f02021b);
            }
            continue; /* Loop/switch isn't completed */
_L2:
            if (TransitUtil.isEmpty(skinmenudata.sIconPath)) goto _L6; else goto _L5
_L5:
            android.graphics.Bitmap bitmap = BitmapFactory.decodeFile(skinmenudata.sIconPath);
            if (bitmap == null) goto _L8; else goto _L7
_L7:
            try
            {
                imageview.setImageBitmap(bitmap);
            }
            catch (Exception exception)
            {
                imageview.setImageResource(0x7f02011e);
            }
_L10:
            if (!(CountdownBaseActivity.this instanceof CountdownListActivity))
            {
                break; /* Loop/switch isn't completed */
            }
            linearlayout.setBackgroundResource(0x7f02021b);
            continue; /* Loop/switch isn't completed */
_L8:
            imageview.setImageResource(0x7f02011e);
            continue; /* Loop/switch isn't completed */
_L6:
            imageview.setImageResource(0x7f02011e);
            if (true) goto _L10; else goto _L9
_L9:
            if (CountdownBaseActivity.this instanceof CountdownActivity)
            {
                if (skinmenudata.sId.equals(((CountdownActivity)CountdownBaseActivity.this).getSkinId()))
                {
                    linearlayout.setBackgroundColor(getResources().getColor(0x7f090007));
                } else
                {
                    linearlayout.setBackgroundResource(0x7f02021b);
                }
            }
            if (true) goto _L12; else goto _L11
_L11:
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            ImageView imageview;
            jp.co.yahoo.android.apps.transit.timer.db.SkinDb.SkinMenuData skinmenudata;
            if (view == null)
            {
                imageview = new ImageView(getContext());
                int j = (int)TransitUtil.dpToPx(CountdownBaseActivity.this, 32F);
                imageview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(j, j));
            } else
            {
                imageview = (ImageView)view;
            }
            skinmenudata = (jp.co.yahoo.android.apps.transit.timer.db.SkinDb.SkinMenuData)getItem(i);
            if (!TransitUtil.isEmpty(skinmenudata.sId)) goto _L2; else goto _L1
_L1:
            imageview.setImageResource(0x7f020116);
_L4:
            imageview.setTag(skinmenudata);
            return imageview;
_L2:
            if (TransitUtil.isEmpty(skinmenudata.sIconPath))
            {
                break MISSING_BLOCK_LABEL_150;
            }
            android.graphics.Bitmap bitmap = BitmapFactory.decodeFile(skinmenudata.sIconPath);
            if (bitmap != null)
            {
                try
                {
                    imageview.setImageBitmap(bitmap);
                }
                catch (Exception exception)
                {
                    imageview.setImageResource(0x7f02011e);
                }
                continue; /* Loop/switch isn't completed */
            }
            imageview.setImageResource(0x7f02011e);
            continue; /* Loop/switch isn't completed */
            imageview.setImageResource(0x7f02011e);
            if (true) goto _L4; else goto _L3
_L3:
        }

        public DisplayModeAdapter(Context context, int i, jp.co.yahoo.android.apps.transit.timer.db.SkinDb.SkinMenuData askinmenudata[])
        {
            this$0 = CountdownBaseActivity.this;
            super(context, i, askinmenudata);
            inflater = (LayoutInflater)context.getSystemService("layout_inflater");
        }
    }

    private class DisplayModeSpinner extends Spinner
    {

        private android.widget.AdapterView.OnItemSelectedListener listener;
        final CountdownBaseActivity this$0;

        public void setOnItemSelectedListener(android.widget.AdapterView.OnItemSelectedListener onitemselectedlistener)
        {
            listener = onitemselectedlistener;
        }

        public void setSelection(int i)
        {
            super.setSelection(i);
            if (listener != null && i == getSelectedItemPosition())
            {
                listener.onItemSelected(this, getSelectedView(), i, 0L);
            }
        }

        public DisplayModeSpinner(Context context)
        {
            this$0 = CountdownBaseActivity.this;
            super(context);
        }

        public DisplayModeSpinner(Context context, AttributeSet attributeset)
        {
            this$0 = CountdownBaseActivity.this;
            super(context, attributeset);
        }

        public DisplayModeSpinner(Context context, AttributeSet attributeset, int i)
        {
            this$0 = CountdownBaseActivity.this;
            super(context, attributeset, i);
        }
    }


    protected LayoutInflater inflater;
    protected SkinDb skindb;

    public CountdownBaseActivity()
    {
        inflater = null;
        skindb = null;
    }

    public void intentToTransit(Bundle bundle, String s)
    {
        String s1 = (new StringBuilder()).append(getString(0x7f0d005c)).append("?").toString();
        Object aobj[] = bundle.keySet().toArray();
        for (int i = 0; i < aobj.length; i++)
        {
            s1 = (new StringBuilder()).append(s1).append(aobj[i].toString()).append("=").append(bundle.getString(aobj[i].toString())).toString();
            if (i < -1 + aobj.length)
            {
                s1 = (new StringBuilder()).append(s1).append("&").toString();
            }
        }

        if (s == null || s.equals(""))
        {
            s = "android.intent.action.VIEW";
        }
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/Transit);
        intent.setAction(s);
        intent.setData(Uri.parse(s1));
        intent.putExtra(getString(0x7f0d01ca), 1);
        try
        {
            startActivity(intent);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    protected boolean isSkin(SkinMetaData skinmetadata)
    {
        return skinmetadata != null && !skinmetadata.sId.equals(getString(0x7f0d04f6));
    }

    protected void launchSettingSkin()
    {
        Intent intent = new Intent(this, jp/co/yahoo/android/apps/transit/timer/SettingSkinActivity);
        intent.putExtra(getString(0x7f0d01df), getResources().getInteger(0x7f0c005c));
        startActivityForResult(intent, getResources().getInteger(0x7f0c005c));
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        inflater = (LayoutInflater)getSystemService("layout_inflater");
        Intent intent = getIntent();
        if ("android.intent.action.CREATE_SHORTCUT".equals(intent.getAction()))
        {
            touchRD(getString(0x7f0d055b));
            setResult(-1, SettingShortcut.getShortcut(this));
            finish();
        } else
        {
            if ("android.intent.action.VIEW".equals(intent.getAction()) && intent.getBooleanExtra(getString(0x7f0d01c7), false))
            {
                touchRD(getString(0x7f0d055c));
            }
            if ((this instanceof CountdownActivity) || (this instanceof CountdownListActivity))
            {
                skindb = new SkinDb(this);
                if (skindb.count() <= 1)
                {
                    DisplayMetrics displaymetrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                    SkinMeta skinmeta = new SkinMeta(this, displaymetrics.densityDpi);
                    skinmeta.setShowDialog(false, new jp.co.yahoo.android.yolp.common.ApiBase.ApiListener() {

                        final CountdownBaseActivity this$0;

                        public boolean endApi(ApiBase apibase, Object obj)
                        {
                            if (skindb.count() > 1)
                            {
                                supportInvalidateOptionsMenu();
                            }
                            return false;
                        }

            
            {
                this$0 = CountdownBaseActivity.this;
                super();
            }
                    });
                    skinmeta.update();
                    return;
                }
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        ArrayList arraylist;
        android.view.MenuItem menuitem;
        final DisplayModeAdapter adapter;
        DisplayModeSpinner displaymodespinner;
        if (!(this instanceof CountdownActivity) && !(this instanceof CountdownListActivity))
        {
            return true;
        }
        (new SkinMeta(this)).deleteSkin();
        if (skindb == null)
        {
            skindb = new SkinDb(this);
        }
        arraylist = skindb.getAllSkinMenu();
        if (!(this instanceof CountdownActivity) || ((CountdownActivity)this).type != getResources().getInteger(0x7f0c0075))
        {
            jp.co.yahoo.android.apps.transit.timer.db.SkinDb.SkinMenuData skinmenudata = new jp.co.yahoo.android.apps.transit.timer.db.SkinDb.SkinMenuData();
            skinmenudata.sTitle = getString(0x7f0d00b3);
            skinmenudata.isDownloaded = true;
            arraylist.add(skinmenudata);
        }
        jp.co.yahoo.android.apps.transit.timer.db.SkinDb.SkinMenuData askinmenudata[] = (jp.co.yahoo.android.apps.transit.timer.db.SkinDb.SkinMenuData[])arraylist.toArray(new jp.co.yahoo.android.apps.transit.timer.db.SkinDb.SkinMenuData[arraylist.size()]);
        menuitem = menu.add(0, 0, 0, "");
        adapter = new DisplayModeAdapter(this, 0x7f030072, askinmenudata);
        displaymodespinner = new DisplayModeSpinner(this);
        displaymodespinner.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-2, -1));
        displaymodespinner.setAdapter(adapter);
        if (!(this instanceof CountdownListActivity)) goto _L2; else goto _L1
_L1:
        int i = -1 + arraylist.size();
_L4:
        displaymodespinner.setSelection(i);
        displaymodespinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

            final CountdownBaseActivity this$0;
            final CountdownBaseActivity val$activity;
            final DisplayModeAdapter val$adapter;

            public void onItemSelected(AdapterView adapterview, View view, int j, long l)
            {
                jp.co.yahoo.android.apps.transit.timer.db.SkinDb.SkinMenuData skinmenudata2 = (jp.co.yahoo.android.apps.transit.timer.db.SkinDb.SkinMenuData)adapter.getItem(j);
                if (!(activity instanceof CountdownListActivity)) goto _L2; else goto _L1
_L1:
                if (TransitUtil.isEmpty(skinmenudata2.sId)) goto _L4; else goto _L3
_L3:
                if (!skinmenudata2.isDownloaded) goto _L6; else goto _L5
_L5:
                skindb.updateSetting(skinmenudata2.sId);
                ((CountdownListActivity)activity).launchCountDown();
_L4:
                return;
_L6:
                launchSettingSkin();
                return;
_L2:
                if (activity instanceof CountdownActivity)
                {
                    if (TransitUtil.isEmpty(skinmenudata2.sId))
                    {
                        touchTapRD(getString(0x7f0d0432));
                        if (!((CountdownActivity)activity).launchCountdownList())
                        {
                            supportInvalidateOptionsMenu();
                            return;
                        }
                    } else
                    {
                        ((CountdownActivity)activity).changeSkin(skinmenudata2);
                        return;
                    }
                }
                if (true) goto _L4; else goto _L7
_L7:
            }

            public void onNothingSelected(AdapterView adapterview)
            {
            }

            
            {
                this$0 = CountdownBaseActivity.this;
                adapter = displaymodeadapter;
                activity = countdownbaseactivity1;
                super();
            }
        });
        MenuItemCompat.setActionView(menuitem, displaymodespinner);
        MenuItemCompat.setShowAsAction(menuitem, 1);
        return true;
_L2:
        String s = skindb.getSkinId();
        Iterator iterator = arraylist.iterator();
        jp.co.yahoo.android.apps.transit.timer.db.SkinDb.SkinMenuData skinmenudata1;
        do
        {
            boolean flag = iterator.hasNext();
            i = 0;
            if (!flag)
            {
                continue; /* Loop/switch isn't completed */
            }
            skinmenudata1 = (jp.co.yahoo.android.apps.transit.timer.db.SkinDb.SkinMenuData)iterator.next();
        } while (s == null || !s.equals(skinmenudata1.sId));
        i = arraylist.indexOf(skinmenudata1);
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void showErrorMessageDialog(String s, String s1)
    {
        if (isFinishing())
        {
            return;
        } else
        {
            (new TransitDialogBuilder(this)).setTitleWarnning(s1).setMessage(s).setPositiveButton(getString(0x7f0d015c), new android.content.DialogInterface.OnClickListener() {

                final CountdownBaseActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.cancel();
                }

            
            {
                this$0 = CountdownBaseActivity.this;
                super();
            }
            }).show();
            return;
        }
    }

    protected void showErrorMessageDialog(String s, String s1, android.content.DialogInterface.OnClickListener onclicklistener)
    {
        if (isFinishing())
        {
            return;
        } else
        {
            (new TransitDialogBuilder(this)).setTitleWarnning(s1).setMessage(s).setPositiveButton(getString(0x7f0d015c), onclicklistener).show();
            return;
        }
    }

    protected void showErrorMessageDialog(String s, String s1, android.content.DialogInterface.OnClickListener onclicklistener, android.content.DialogInterface.OnClickListener onclicklistener1)
    {
        if (isFinishing())
        {
            return;
        } else
        {
            (new TransitDialogBuilder(this)).setTitleWarnning(s1).setMessage(s).setIcon(0x1080027).setPositiveButton(getString(0x7f0d0074), onclicklistener).setNegativeButton(getString(0x7f0d0071), onclicklistener1).show();
            return;
        }
    }

    protected void showErrorMessageDialog(String s, String s1, String s2, android.content.DialogInterface.OnClickListener onclicklistener)
    {
        if (isFinishing())
        {
            return;
        } else
        {
            (new TransitDialogBuilder(this)).setTitleWarnning(s1).setMessage(s).setPositiveButton(s2, onclicklistener).show();
            return;
        }
    }

    public void startCountDown()
    {
        int i = getCountdownType();
        if (i < 0)
        {
            showSettingDialog(false, null);
            return;
        } else
        {
            startCountDown(i, -1, -1, null);
            return;
        }
    }

    public void startCountDown(int i, int j, int k, AlermData alermdata)
    {
        Intent intent = new Intent(getApplicationContext(), jp/co/yahoo/android/apps/transit/timer/CountdownActivity);
        if (i > 0)
        {
            intent.putExtra(getString(0x7f0d0247), i);
        }
        if (j > 0)
        {
            intent.putExtra(getString(0x7f0d024c), j);
        }
        if (k > 0)
        {
            intent.putExtra(getString(0x7f0d0246), k);
        }
        if (alermdata != null)
        {
            intent.putExtra(getString(0x7f0d023d), alermdata);
            intent.setFlags(0x10000000);
        }
        intent.putExtra(getString(0x7f0d01ca), 7);
        startActivityForResult(intent, getResources().getInteger(0x7f0c003b));
    }

    public boolean startSetting()
    {
        SharedPreferences sharedpreferences = getSharedPreferences(getString(0x7f0d01a0), 0);
        if (sharedpreferences.getBoolean(getString(0x7f0d01bb), true))
        {
            showSettingDialog(true, null);
            android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(getString(0x7f0d01bb), false);
            editor.commit();
            return true;
        } else
        {
            return false;
        }
    }

    public boolean startUp()
    {
        return startSetting();
    }
}
