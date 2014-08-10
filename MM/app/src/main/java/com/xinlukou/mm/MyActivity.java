package com.xinlukou.mm;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinlukou.metroman.engine.*;

import org.apache.http.util.EncodingUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.StringReader;

public class MyActivity extends Activity {
    private TextView textView;
    private Button testButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        try {

            InputStream is = getAssets().open("data/mmlang.csv");
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            String str = EncodingUtils.getString(bytes, "utf-8");
            System.out.println(str);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        //StringReader sr = new StringReader()

//        textView = (TextView)findViewById(R.id.textView);
//        testButton = (Button)findViewById(R.id.testButton);
//        testButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                DataManage.initData();
//                textView.setText(DataManage.unoList.get(0).English);
//            }
//        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
